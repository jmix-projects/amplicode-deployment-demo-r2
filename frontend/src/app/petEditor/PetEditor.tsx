import { useEffect, useCallback, useState } from "react";
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
import { useHistory } from "react-router-dom";
import { EntityDetailsScreenProps } from "../../framework/components/entity-details-screen/EntityDetailsScreenProps";
import { useScreens } from "../../framework/screen-api/ScreenContext";
import { EntityLookupField } from "../../framework/components/entity-lookup-field/EntityLookupField";
import { guessDisplayName } from "../../framework/util/guessDisplayName";
import OwnerList from "../owner-list/OwnerList";

const PET = gql`
  query Get_Pet($id: Long) {
    pet(id: $id) {
      id
      identificationNumber
      owner {
        firstName
        lastName
      }
    }
  }
`;

const UPDATE__PET = gql`
  mutation Update_Pet($input: PetInputDTOInput) {
    update_Pet(input: $input) {
      id
    }
  }
`;

const PetEditor = observer(({ id }: EntityDetailsScreenProps) => {
  const [form] = useForm();
  const intl = useIntl();
  const screens = useScreens();
  const history = useHistory();

  const [
    loadItem,
    { loading: queryLoading, error: queryError, data }
  ] = useLazyQuery(PET, {
    variables: {
      id
    }
  });

  const [executeUpsertMutation, { loading: upsertInProcess }] = useMutation(
    UPDATE__PET
  );

  const [formError, setFormError] = useState<string | undefined>();

  const goToParentScreen = useCallback(() => {
    history.push("."); // Remove entity id part from url
    screens.closeActiveBreadcrumb();
  }, [screens, history]);

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
            message.success(
              intl.formatMessage({
                id: "EntityDetailsScreen.savedSuccessfully"
              })
            );
            return;
          }
          setFormError(errors.join("\n"));
          console.error(errors);
          message.error(intl.formatMessage({ id: "common.requestFailed" }));
        })
        .catch((e: Error | ApolloError) => {
          setFormError(e.message);
          console.error(e);
          message.error(intl.formatMessage({ id: "common.requestFailed" }));
        });
    },
    [executeUpsertMutation, id, intl, goToParentScreen]
  );

  const handleSubmitFailed = useCallback(() => {
    message.error(
      intl.formatMessage({ id: "EntityDetailsScreen.validationError" })
    );
  }, [intl]);

  useEffect(() => {
    if (id != null) {
      loadItem();
    }
  }, [loadItem, id]);

  const item = data?.["pet"];

  useEffect(() => {
    if (item != null) {
      form.setFieldsValue(dataToFormValues(item));
    }
  }, [item, form]);

  if (queryLoading) {
    return <Spin />;
  }

  if (queryError) {
    return (
      <Result
        status="error"
        title={<FormattedMessage id="common.requestFailed" />}
      />
    );
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
          name="birthDate"
          label="Birth Date"
          style={{ marginBottom: "12px" }}
        >
          <Input />
        </Form.Item>

        <Form.Item
          name="identificationNumber"
          label="Identification Number"
          style={{ marginBottom: "12px" }}
        >
          <Input />
        </Form.Item>

        <Form.Item name="owner" label="Owner" style={{ marginBottom: "12px" }}>
          <EntityLookupField
            getDisplayName={(value: Record<string, unknown>) =>
              guessDisplayName(value)
            }
            label="Owner"
            listComponent={OwnerList}
          />
        </Form.Item>

        <Form.Item name="type" label="Type" style={{ marginBottom: "12px" }}>
          <EntityLookupField
            getDisplayName={(value: Record<string, unknown>) =>
              guessDisplayName(value)
            }
            label="Type"
            // TODO Uncomment the code and specify the list component
            // listComponent={YourEntityListComponentName}
          />
        </Form.Item>

        {formError && (
          <Alert
            message={formError}
            type="error"
            style={{ marginBottom: "18px" }}
          />
        )}

        <Form.Item style={{ textAlign: "center" }}>
          <Button htmlType="button" onClick={goToParentScreen}>
            <FormattedMessage id="common.cancel" />
          </Button>
          <Button
            type="primary"
            htmlType="submit"
            loading={upsertInProcess}
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
    const updateResult = result.data?.["update_Pet"];
    // Reflect the update in Apollo cache
    cache.modify({
      fields: {
        petList(existingRefs = []) {
          const updatedItemRef = cache.writeFragment({
            id: `PetDTO:${updateResult.id}`,
            data: values,
            fragment: gql(`
              fragment New_PetDTO on PetDTO {
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

export default PetEditor;
