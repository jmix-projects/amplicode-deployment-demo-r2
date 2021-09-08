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
  PlusOutlined
} from "@ant-design/icons";
import { Button, Card, Modal, Spin, Empty, Result } from "antd";
import { FormattedMessage, IntlShape, useIntl } from "react-intl";
import { MutationFunctionOptions } from "@apollo/client/react/types/types";
import { FetchResult } from "@apollo/client/link/core";
import { EntityListScreenProps } from "../../framework/components/entity-list-screen/EntityListScreenProps";
import { openBreadcrumb } from "../../framework/screen-api/openBreadcrumb";
import { guessDisplayName } from "../../framework/util/guessDisplayName";
import { guessLabel } from "../../framework/util/guessLabel";

const ENTITY_NAME = "OwnerDTO";
const ROUTING_PATH = "/readOnlyOwnerList";

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

const ReadOnlyOwnerList = observer(({ onSelect }: EntityListScreenProps) => {
  const screens: Screens = useScreens();
  const intl = useIntl();
  const goToParentScreen = useParentScreen(ROUTING_PATH);

  const { loading, error, data } = useQuery(OWNER_LIST);

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
          <strong>{guessLabel(p)}:</strong> {renderFieldValue(entity, p)}
        </div>
      ))}
  </>
);

function renderFieldValue(entity: any, property: string): string {
  return typeof entity[property] === "object"
    ? guessDisplayName(entity[property])
    : String(entity[property]);
}

interface CardActionsInput {
  entityInstance: any;
  onSelect?: (entityInstance: this["entityInstance"]) => void;
  intl: IntlShape;
  goToParentScreen: () => void;
}

function getCardActions(input: CardActionsInput) {
  const { entityInstance, onSelect, intl, goToParentScreen } = input;

  if (onSelect == null) {
    return [];
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
  component: ReadOnlyOwnerList,
  caption: "screen.ReadOnlyOwnerList",
  screenId: "ReadOnlyOwnerList",
  entityName: ENTITY_NAME,
  menuOptions: {
    pathPattern: `${ROUTING_PATH}/:entityId?`,
    menuLink: ROUTING_PATH
  }
});

export default ReadOnlyOwnerList;
