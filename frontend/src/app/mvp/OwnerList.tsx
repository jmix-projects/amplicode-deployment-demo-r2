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
  openEntityEditorScreen, useParentScreen
} from "@haulmont/jmix-react-ui";
import {Screens, useScreens} from "@haulmont/jmix-react-core";
import {
  DeleteOutlined,
  EditOutlined,
  PlusOutlined,
  SelectOutlined
} from "@ant-design/icons";
import { Button, Card, Tooltip, List, Modal, Spin, Empty, Result } from "antd";
import { FormattedMessage, IntlShape, useIntl } from "react-intl";
import {EntityListProps} from "../../framework/entity-list-props/EntityListProps";
import {FetchResult} from "@apollo/client/link/core";
import {MutationFunctionOptions} from "@apollo/client/react/types/types";

const ENTITY_NAME = "OwnerDTO";
const ROUTING_PATH = "/ownerList";

const OWNER_LIST = gql`
  query Get_Owner_List($page: PaginationInput) {
    ownerList(page: $page) {
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

const DELETE__OWNER = gql`
  mutation Delete_Owner($id: Long!) {
    delete_Owner(id: $id)
  }
`;

const OwnerList = observer((props: EntityListProps) => {
  const {
    mode = 'crud',
    onSelect
  } = props;

  const screens = useScreens();
  const intl = useIntl();
  const goToParentScreen = useParentScreen(ROUTING_PATH);

  const { loading, error, data } = useQuery(OWNER_LIST);
  const [executeDeleteMutation] = useMutation(DELETE__OWNER);

  if (mode === 'select' && onSelect == null) {
    throw new Error('Missing onSelect callback');
  }

  if (loading) {
    return <Spin />;
  }

  if (error) {
    return <Result status="error" title="Query failed" />;
  }

  const items = data?.["ownerList"];

  if (items == null || items.length === 0) {
    return <Empty />;
  }

  return (
    <div className="narrow-layout">
      {mode === 'crud' && (
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
      )}

      {items.map((e: any) => (
        <Card
          key={e["id"]}
          title={e["firstName"]}
          style={{ marginBottom: "12px" }}
          actions={getCardActions({
            mode,
            executeDeleteMutation,
            intl,
            entityInstance: e,
            screens,
            onSelect,
            goToParentScreen
          })}
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
    ? JSON.stringify(entity[property])
    : entity[property].toString();
}

function renderLabel(property: string): string {
  const split = property.replace(/([^A-Z])([A-Z])/g, "$1 $2");
  return split[0].toUpperCase() + split.slice(1);
}

interface CardActionsInput {
  mode: 'crud' | 'select',
  executeDeleteMutation: (options?: MutationFunctionOptions) => Promise<FetchResult>,
  intl: IntlShape,
  entityInstance: any,
  screens: Screens,
  onSelect?: (entityInstance: this['entityInstance']) => void,
  goToParentScreen?: () => void;
}

function getCardActions({
  mode,
  executeDeleteMutation,
  intl,
  entityInstance,
  screens,
  onSelect,
  goToParentScreen
}: CardActionsInput) {
  if (mode === 'crud') {
    return [
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
                  id: entityInstance.id
                },
                update: getUpdateFn(entityInstance)
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
            entityIdToLoad: entityInstance.id,
            routingPath: ROUTING_PATH // TODO: can we get rid of it?
          });
          window.scrollTo(0, 0);
        }}
      />
    ];
  }

  if (mode === 'select') {
    return [
      <SelectOutlined
        key='select'
        onClick={() => {
          if (onSelect != null && goToParentScreen != null) {
            onSelect(entityInstance);
            goToParentScreen();
            window.scrollTo(0, 0);
          }
        }}
      />
    ];
  }
}

function getUpdateFn(e: any) {
  return (cache: ApolloCache<any>) => {
    cache.modify({
      fields: {
        ["ownerList"](existingRefs, { readField }) {
          return existingRefs.filter(
            (ref: Reference) => e["id"] !== readField("id", ref)
          );
        }
      }
    });
  };
}

registerEntityList({
  component: OwnerList,
  caption: "screen.OwnerList",
  screenId: "OwnerList",
  entityName: ENTITY_NAME,
  menuOptions: {
    pathPattern: `${ROUTING_PATH}/:entityId?`,
    menuLink: ROUTING_PATH
  }
});

export default OwnerList;
