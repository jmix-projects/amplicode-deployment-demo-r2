import { observer } from "mobx-react";
import {
  gql,
  useQuery,
  useMutation,
  ApolloCache,
  Reference
} from "@apollo/client";
import { registerEntityList, useParentScreen } from "@haulmont/jmix-react-ui";
import { useScreens, Screens } from "@haulmont/jmix-react-core";
import {
  CheckOutlined,
  CloseOutlined,
  DeleteOutlined,
  EditOutlined,
  PlusOutlined
} from "@ant-design/icons";
import { Button, Card, Modal, Spin, Empty, Result } from "antd";
import { FormattedMessage, IntlShape, useIntl } from "react-intl";
import { MutationFunctionOptions } from "@apollo/client/react/types/types";
import { FetchResult } from "@apollo/client/link/core";
import { EntityListScreenProps } from "../../framework/components/entity-list-screen/EntityListScreenProps";
import { openBreadcrumb } from "../../framework/screen-api/openBreadcrumb";
import { guessDisplayName } from "../../framework/util/guessDisplayName";
import OwnerEditor from "../owner-editor/OwnerEditor";

const ENTITY_NAME = "OwnerDTO";
const ROUTING_PATH = "/ownerList";

const OWNER_LIST = gql`
  query Get_Owner_List($page: PaginationInput) {
    ownerList(page: $page) {
      id
      firstName
      lastName
      city
    }
  }
`;

const DELETE__OWNER = gql`
  mutation Delete_Owner($id: Long!) {
    delete_Owner(id: $id)
  }
`;

const OwnerList = observer(({ onSelect }: EntityListScreenProps) => {
  const screens: Screens = useScreens();
  const intl = useIntl();
  const goToParentScreen = useParentScreen(ROUTING_PATH);

  const { loading, error, data } = useQuery(OWNER_LIST);

  const [executeDeleteMutation] = useMutation(DELETE__OWNER);

  // Entity list can work in select mode, which means that you can select an entity instance and it will be passed to onSelect callback.
  // This functionality is used in EntityLookupField.
  const isSelectMode = onSelect != null;

  if (loading) {
    return <Spin />;
  }

  if (error) {
    return (
      <Result
        status="error"
        title={<FormattedMessage id="common.requestFailed" />}
      />
    );
  }

  const items = data?.["ownerList"];

  if (items == null || items.length === 0) {
    return <Empty />;
  }

  return (
    <div className="narrow-layout">
      {!isSelectMode && (
        <div style={{ marginBottom: "12px" }}>
          <Button
            htmlType="button"
            key="create"
            title='intl.formatMessage({id: "common.create"})'
            type="primary"
            icon={<PlusOutlined />}
            onClick={() => {
              openBreadcrumb({
                component: OwnerEditor,
                title: 'Owner Editor',
                screens,
              });
            }}
          >
            <span>
              <FormattedMessage id="common.create" />
            </span>
          </Button>
        </div>
      )}
      {isSelectMode && (
        <div style={{ marginBottom: "12px" }}>
          <Button
            htmlType="button"
            key="close"
            title='intl.formatMessage({id: "common.close"})'
            type="primary"
            icon={<CloseOutlined />}
            onClick={goToParentScreen}
          >
            <span>
              <FormattedMessage id="common.close" />
            </span>
          </Button>
        </div>
      )}

      {items.map((e: any) => (
        <Card
          key={e["id"]}
          title={guessDisplayName(e)}
          style={{ marginBottom: "12px" }}
          actions={getCardActions({
            entityInstance: e,
            onSelect,
            executeDeleteMutation,
            screens,
            intl,
            goToParentScreen
          })}
        >
          <Fields entity={e} />
        </Card>
      ))}
    </div>
  );
});

const Fields = ({ entity }: { entity: any }) => (
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
  entityInstance: any;
  onSelect?: (entityInstance: this["entityInstance"]) => void;
  executeDeleteMutation: (
    options?: MutationFunctionOptions
  ) => Promise<FetchResult>;
  screens: Screens;
  intl: IntlShape;
  goToParentScreen: () => void;
}

function getCardActions(input: CardActionsInput) {
  const {
    entityInstance,
    onSelect,
    executeDeleteMutation,
    screens,
    intl,
    goToParentScreen
  } = input;

  if (onSelect == null) {
    return [
      <DeleteOutlined
        key="delete"
        title={intl.formatMessage({ id: "common.remove" })}
        onClick={() => {
          Modal.confirm({
            content: intl.formatMessage({
              id: "EntityListScreen.deleteConfirmation"
            }),
            okText: intl.formatMessage({ id: "common.ok" }),
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
        title={intl.formatMessage({ id: "common.edit" })}
        onClick={() => {
          openBreadcrumb({
            component: OwnerEditor,
            props: {
              id: entityInstance.id
            },
            title: 'Owner List',
            screens,
          });
        }}
      />
    ];
  }

  if (onSelect != null) {
    return [
      <CheckOutlined
        key="select"
        title={intl.formatMessage({
          id: "EntityLookupField.selectEntityInstance"
        })}
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
