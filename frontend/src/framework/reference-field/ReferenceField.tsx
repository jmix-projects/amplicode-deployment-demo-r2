import { Input } from "antd";
import {LinkOutlined} from "@ant-design/icons";
import {useCallback} from "react";
import { openEntityListScreen } from "@haulmont/jmix-react-ui";
import {useScreens} from "@haulmont/jmix-react-core";
import {useIntl} from "react-intl";
import { instanceName } from "../instance-name/instanceName";
import {ReactComponent} from "../screen-api/ReactComponent";
import {openBreadcrumb} from "../screen-api/openBreadcrumb";

export interface ReferenceFieldProps {
  value?: any;
  onChange?: (value: this['value']) => void;
  listComponent: ReactComponent;
  listComponentProps?: any;
  getDisplayName: (value: this['value']) => string;
}

export function ReferenceField(props: ReferenceFieldProps) {
  const {value, onChange, listComponent, listComponentProps, getDisplayName} = props;

  const screens = useScreens();
  const intl = useIntl();

  const handleClick = useCallback(() => {
    const enableSelectModeProps = {
      mode: 'select',
      onSelect: (entityInstance: any) => {
        if (onChange != null) {
          onChange(entityInstance);
        }
      }
    };

    openBreadcrumb({
      component: listComponent,
      props: {
        ...enableSelectModeProps,
        ...listComponentProps
      },
      title: 'Select entity instance',
      screens
    });
  }, [onChange, listComponent]);

  return (
    <Input prefix={<LinkOutlined />}
           onClick={handleClick}
           value={value ? getDisplayName(value) : ''}
    />
  );
}