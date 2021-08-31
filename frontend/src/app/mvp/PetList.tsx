import React, { useCallback } from "react";
import { observer } from "mobx-react";
import {
  gql,
  useQuery,
  useMutation,
  ApolloCache,
  Reference
} from "@apollo/client";
import {
  registerEntityList,
  openEntityEditorScreen
} from "@haulmont/jmix-react-ui";
import { useScreens } from "@haulmont/jmix-react-core";
import {
  DeleteOutlined,
  EditOutlined,
  PlusOutlined,
  LeftOutlined
} from "@ant-design/icons";
import { Button, Card, Tooltip, List, Modal, Spin, Empty, Result } from "antd";
import { FormattedMessage, useIntl } from "react-intl";
import { instanceName } from "../../framework/instance-name/instanceName";

const ENTITY_NAME = "PetDTO";
const ROUTING_PATH = "/petList";

const PET_LIST = gql`
  query Get_Pet_List($page: PaginationInput) {
    petList(page: $page) {
      id
      identificationNumber
      owner {
        firstName
        lastName
      }
    }
  }
`;

const DELETE__PET = gql`
  mutation Delete_Pet($id: Long!) {
    delete_Pet(id: $id)
  }
`;

const PetList = observer(() => {
  const screens = useScreens();
  const intl = useIntl();

  const { loading, error, data } = useQuery(PET_LIST);
  const [executeDeleteMutation] = useMutation(DELETE__PET);

  if (loading) {
    return <Spin />;
  }

  if (error) {
    return <Result status="error" title="Query failed" />;
  }

  const items = data?.["petList"];

  if (items == null || items.length === 0) {
    return <Empty />;
  }

  return (
    <div className="narrow-layout">
      <div style={{ marginBottom: "12px" }}>
        <Button
          htmlType="button"
          type="primary"
          icon={<PlusOutlined />}
          onClick={() => {
            openEntityEditorScreen({
              screens,
              entityName: ENTITY_NAME,
              intl
            });
            window.scrollTo(0, 0);
          }}
        >
          <span>
            <FormattedMessage id="common.create" />
          </span>
        </Button>
      </div>

      {items.map((e: any) => (
        <Card
          key={e["id"]}
          title={e["firstName"]}
          style={{ marginBottom: "12px" }}
          actions={[
            <DeleteOutlined
              key="delete"
              onClick={() => {
                Modal.confirm({
                  content: "Are you sure you want to delete this record?",
                  okText: "OK",
                  cancelText: intl.formatMessage({ id: "common.cancel" }),
                  onOk: () => {
                    executeDeleteMutation({
                      variables: {
                        id: e.id
                      },
                      update: getUpdateFn(e)
                    });
                  }
                });
              }}
            />,
            <EditOutlined
              key="edit"
              onClick={() => {
                openEntityEditorScreen({
                  screens,
                  entityName: ENTITY_NAME,
                  intl,
                  entityIdToLoad: e.id,
                  routingPath: ROUTING_PATH // TODO: can we get rid of it?
                });
                window.scrollTo(0, 0);
              }}
            />
          ]}
        >
          <Fields entity={e} />
        </Card>
      ))}
    </div>
  );
});

interface FieldsProps {
  entity: any;
}

const Fields = (props: FieldsProps) => {
  const { entity } = props;
  return (
    <>
      {Object.keys(entity)
        .filter(p => p !== "id" && entity[p] != null)
        .map(p => (
          <div>
            <strong>{renderLabel(p)}:</strong> {renderFieldValue(entity, p)}
          </div>
        ))}
    </>
  );
};

function renderFieldValue(entity: any, property: string): string {
  return typeof entity[property] === "object"
    ? instanceName('OwnerDTO')(entity[property])
    : entity[property].toString();
}

function renderLabel(property: string): string {
  const split = property.replace(/([^A-Z])([A-Z])/g, "$1 $2");
  return split[0].toUpperCase() + split.slice(1);
}

function getUpdateFn(e: any) {
  return (cache: ApolloCache<any>) => {
    cache.modify({
      fields: {
        ["petList"](existingRefs, { readField }) {
          return existingRefs.filter(
            (ref: Reference) => e["id"] !== readField("id", ref)
          );
        }
      }
    });
  };
}

registerEntityList({
  component: PetList,
  caption: "screen.PetList",
  screenId: "PetList",
  entityName: ENTITY_NAME,
  menuOptions: {
    pathPattern: `${ROUTING_PATH}/:entityId?`,
    menuLink: ROUTING_PATH
  }
});

export default PetList;
