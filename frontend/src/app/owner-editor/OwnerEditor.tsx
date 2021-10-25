import { useEffect, useCallback, useState } from "react";
import {
  gql,
  useLazyQuery,
  useMutation,
  FetchResult,
  ApolloError,
  ApolloCache
} from "@apollo/client";
import dayjs from 'dayjs';
import { Form, Button, Card, message, Alert, Spin, Result, Input } from "antd";
import { useForm } from "antd/es/form/Form";
import { observer } from "mobx-react";
import { FormattedMessage, FormattedNumber, useIntl } from "react-intl";
import { useHistory } from "react-router-dom";
import { EntityDetailsScreenProps, useScreens } from "@amplicode/react-core";

const OWNER = gql`
  query owner($id: Long) {
    owner(id: $id) {
      address
      city
      email
      firstName
      id
      lastName
      telephone
    }
  }
`;

const UPDATE__OWNER = gql`
  mutation update_Owner($input: OwnerInputDTOInput) {
    update_Owner(input: $input) {
      address
      city
      email
      firstName
      id
      lastName
      telephone
    }
  }
`;

const OwnerEditor = observer(({ id }: EntityDetailsScreenProps) => {
  const [state, setStatue] = useState<{ someProp: string }>({ someProp: "some custom property of custom object" });
  const [form] = useForm();
  const intl = useIntl();
  const screens = useScreens();
  const history = useHistory();

  const [
    loadItem,
    { loading: queryLoading, error: queryError, data }
  ] = useLazyQuery(OWNER, {
    variables: {
      id
    }
  });

  const [executeUpsertMutation, { loading: upsertInProcess }] = useMutation(
    UPDATE__OWNER
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
    return (
      <Result
        status="error"
        title={<FormattedMessage id="common.requestFailed" />}
      />
    );
  }

  return (
    <Card className="narrow-layout">
      <div>
        <FormattedMessage // we don't have information on front-side about user at moment
          id={"userLogin"}
          values={{
            userLogin: "unable"
          }}
        />
      </div>
      
      <div> 
        <FormattedMessage // you can see all dayjs formats on this link https://day.js.org/docs/en/display/format
          id={"currentDate"}
          values={{
            currentDate: dayjs().format("YYYY-M-D"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"currentTime"}
          values={{
            currentTime: dayjs().format("HH:mm:ss"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"currentDateTime"}
          values={{
            currentDateTime: dayjs().format("YYYY-M-D HH:mm:ss"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"firstYearDay"}
          values={{
            firstYearDay: dayjs().startOf("year").format("YYYY-M-D")
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"lastYearDay"}
          values={{
            lastYearDay: dayjs().endOf("year").format("YYYY-M-D")
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"firstMonthDay"}
          values={{
            firstMonthDay: dayjs().startOf("month").format("YYYY-M-D")
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"lastMonthDay"}
          values={{
            lastMonthDay: dayjs().endOf("month").format("YYYY-M-D"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"firstWeekDay"}
          values={{
            firstWeekDay: dayjs().startOf("week").format("YYYY-M-D")
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"lastWeekDay"}
          values={{
            lastWeekDay: dayjs().endOf("week").format("YYYY-M-D")
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"startPreviousDay"}
          values={{
            startPreviousDay: dayjs().subtract(1, "day").startOf("day").format("YYYY-M-D HH:mm:ss")
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"startNextDay"}
          values={{
            startNextDay: dayjs().add(1, "day").startOf("day").format("YYYY-M-D HH:mm:ss"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"startCurrentHour"}
          values={{
            startCurrentHour: dayjs().startOf("hour").format("HH:mm:ss"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"endCurrentHour"}
          values={{
            endCurrentHour: dayjs().endOf("hour").format("HH:mm:ss"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"startCurrentMinute"}
          values={{
            startCurrentMinute: dayjs().startOf("minute").format("HH:mm:ss"),
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"endCurrentMinute"}
          values={{
            endCurrentMinute: dayjs().endOf("minute").format("HH:mm:ss")
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"testedNumber"}
          values={{
            testedNumber: <FormattedNumber value={45412.564} maximumFractionDigits={2} />
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"testedEntityProp"}
          values={{
            testedEntityProp: item?.city ?? "non value",
          }}
        />
      </div>
      <div>
        <FormattedMessage
          id={"testedSomeComponentVariable"}
          values={{
            testedSomeComponentVariable: state.someProp
          }}
        />
      </div>
      <div>
        <FormattedMessage // you can see all propertes of FormattedNumber on this link https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat/NumberFormat#parameters
          id={"allTypesKey"}
          values={{
            testedEntityProp: item?.city ?? "non value",
            currentDateTime: dayjs().format("YYYY"),
            testedNumber: <FormattedNumber value={45412.564} maximumFractionDigits={2} /> // maximumFractionDigits - the maximum number of fraction digits to use
          }}
        />
      </div>



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
    const updateResult = result.data?.["update_Owner"];
    // Reflect the update in Apollo cache
    cache.modify({
      fields: {
        ownerList(existingRefs = []) {
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

export default OwnerEditor;
