import { Input } from "antd";
import {LinkOutlined} from "@ant-design/icons";
import {useCallback} from "react";
import { openEntityListScreen } from "@haulmont/jmix-react-ui";
import {useScreens} from "@haulmont/jmix-react-core";
import {useIntl} from "react-intl";
import { instanceName } from "../instance-name/instanceName";

interface Props {
  value?: any;
  onChange?: (value: this['value']) => void;
  entityName?: string;
  entityBrowserScreenId?: string;
}

export function RelationInput(props: Props) {
  const {value, onChange, entityName, entityBrowserScreenId} = props;

  const screens = useScreens();
  const intl = useIntl();

  const handleClick = useCallback(() => {
    const screenProps = {
      mode: 'select',
      onSelect: (entityInstance: any) => {
        if (onChange != null) {
          onChange(entityInstance);
        }
      }
    };

    if (entityBrowserScreenId != null) {
      // TODO open screen by id
      return;
    }

    if (entityName != null) {
      openEntityListScreen({
        entityName,
        screens,
        screenProps,
        intl
      });
      return;
    }
  }, [onChange, entityName, entityBrowserScreenId]);

  return (
    <Input prefix={<LinkOutlined />}
           onClick={handleClick}
           value={instanceName(entityName)(value)}
    />
  );
}