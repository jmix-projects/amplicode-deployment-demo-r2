import React, { useEffect, useCallback, useState } from "react";
import {
  gql,
  useLazyQuery,
  useMutation,
  FetchResult,
  ApolloError,
  ApolloCache
} from "@apollo/client";
import { Form, Button, Card, message, Alert, Spin, Result, Input } from "antd";
import { useForm } from "antd/es/form/Form";
import { observer } from "mobx-react";
import { FormattedMessage, useIntl } from "react-intl";
import {
  useMultiScreen,
  useParentScreen,
  registerEntityEditor
} from "@haulmont/jmix-react-ui";
import {EntityDetailsProps} from "../../framework/entity-editor-props/EntityDetailsProps";

const ENTITY_NAME = "OwnerDTO";
const ROUTING_PATH = "/ownerEditor";

const OWNER = gql`
  query Get_Owner($id: Long) {
    owner(id: $id) {
      id
      firstName
      lastName
      city
      address
      email
      telephone
    }
  }
`;

const UPDATE__OWNER = gql`
  mutation Update_Owner($input: OwnerInputDTOInput) {
    update_Owner(input: $input) {
      id
    }
  }
`;

const OwnerEditor = observer(({id}: EntityDetailsProps) => {
  const [form] = useForm();
  const intl = useIntl();

  // TODO: id variable name
  const [
    loadItem,
    { loading: queryLoading, error: queryError, data }
  ] = useLazyQuery(OWNER, {
    variables: {
      id
    }
  });

  const goToParentScreen = useParentScreen(ROUTING_PATH);
  const handleCancel = useCallback(() => {
    goToParentScreen();
    window.scrollTo(0, 0);
  }, [goToParentScreen]);

  const [executeUpsertMutation] = useMutation(UPDATE__OWNER);

  const [formError, setFormError] = useState<string | undefined>();

  const handleSubmit = useCallback(
    values => {
      executeUpsertMutation({
        variables: {
          input: formValuesToData(values, id)
        },
        update: getUpdateFn(values)
      })
        .then(({ errors }: FetchResult) => {
          if (errors == null || errors.length === 0) {
            goToParentScreen();
            window.scrollTo(0, 0);
            message.success("Saved successfully");
            return;
          }
          setFormError(errors.join("\n"));
          console.log(errors);
          message.error(intl.formatMessage({ id: "common.requestFailed" }));
        })
        .catch((e: Error | ApolloError) => {
          setFormError(e.message);
          console.log(e);
          message.error(intl.formatMessage({ id: "common.requestFailed" }));
        });
    },
    [executeUpsertMutation]
  );

  const handleSubmitFailed = useCallback(() => {
    message.error("Validation Error. Please check the data you entered.");
  }, []);

  useEffect(() => {
    if (id != null) {
      loadItem();
    }
  }, [loadItem]);

  const item = data?.["owner"];

  useEffect(() => {
    if (item != null) {
      form.setFieldsValue(dataToFormValues(item));
    }
  }, [item, form]);

  if (queryLoading) {
    return <Spin />;
  }

  if (queryError) {
    return <Result status="error" title="Query failed" />;
  }

  return (
    <Card className="narrow-layout">
      <Form
        onFinish={handleSubmit}
        onFinishFailed={handleSubmitFailed}
        layout="vertical"
        form={form}
      >
        <Form.Item
          name="address"
          label="Address"
          style={{ marginBottom: "12px" }}
        >
          <Input />
        </Form.Item>

        <Form.Item name="city" label="City" style={{ marginBottom: "12px" }}>
          <Input />
        </Form.Item>

        <Form.Item name="email" label="Email" style={{ marginBottom: "12px" }}>
          <Input />
        </Form.Item>

        <Form.Item
          name="firstName"
          label="First Name"
          style={{ marginBottom: "12px" }}
        >
          <Input />
        </Form.Item>

        <Form.Item
          name="lastName"
          label="Last Name"
          style={{ marginBottom: "12px" }}
        >
          <Input />
        </Form.Item>

        <Form.Item
          name="telephone"
          label="Telephone"
          style={{ marginBottom: "12px" }}
        >
          <Input />
        </Form.Item>

        {formError && (
          <Alert
            message={formError}
            type="error"
            style={{ marginBottom: "18px" }}
          />
        )}

        <Form.Item style={{ textAlign: "center" }}>
          <Button htmlType="button" onClick={handleCancel}>
            <FormattedMessage id="common.cancel" />
          </Button>
          <Button
            type="primary"
            htmlType="submit"
            loading={false} // TODO
            style={{ marginLeft: "8px" }}
          >
            <FormattedMessage id={"common.submit"} />
          </Button>
        </Form.Item>
      </Form>
    </Card>
  );
});

function formValuesToData(values: any, id?: string): any {
  return {
    ...values,
    id
  };
}

function dataToFormValues(data: any): any {
  return data;
}

function getUpdateFn(values: any) {
  return (cache: ApolloCache<any>, result: FetchResult) => {
    const updateResult = result.data?.["update_Owner"];
    // Reflect the update in Apollo cache
    cache.modify({
      fields: {
        ["ownerList"](existingRefs = []) {
          const updatedItemRef = cache.writeFragment({
            id: `OwnerDTO:${updateResult.id}`,
            data: values,
            fragment: gql(`
              fragment New_OwnerDTO on OwnerDTO {
                id
              }
            `)
          });
          return [...existingRefs, updatedItemRef];
        }
      }
    });
  };
}

registerEntityEditor({
  component: OwnerEditor,
  caption: "screen.OwnerEditor",
  screenId: "OwnerEditor",
  entityName: ENTITY_NAME,
  menuOptions: {
    pathPattern: `${ROUTING_PATH}/:entityId?`,
    menuLink: ROUTING_PATH
  }
});

export default OwnerEditor;
