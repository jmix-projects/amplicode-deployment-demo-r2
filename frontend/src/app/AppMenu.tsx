import {Menu } from "antd";
import {HomeOutlined} from "@ant-design/icons";
import {useIntl} from "react-intl";
import {useScreens} from "../framework/screen-api/ScreenContext";
import {useCallback} from "react";
import {observer} from "mobx-react";
import {screenRegistry} from "./screenRegistry";

export const AppMenu = observer(() => {
  const intl = useIntl();
  const screens = useScreens();

  const handleClick = useCallback(({key}: {key: string}) => {
    const menuItemInfo = screenRegistry[key];
    if (menuItemInfo == null) {
      console.error(`MenuItemInfo is not found for menu item with key ${key}`);
      return;
    }
    const tabCaption = intl.formatMessage({id: menuItemInfo.captionKey});
    const breadcrumbCaption = intl.formatMessage({
      id: menuItemInfo.captionKey
    });
    const {component} = menuItemInfo;

    screens.openInTab({tabCaption, breadcrumbCaption, component, tabKey: key});
  }, [intl, screens]);

  const getCaption = useCallback((key: string) => {
    return intl.formatMessage({id: screenRegistry[key].captionKey});
  }, [intl]);

  return (
    <Menu onClick={handleClick}
          selectedKeys={screens.activeTab?.key ? [screens.activeTab.key] : []}
    >
      <Menu.Item
        icon={<HomeOutlined />}
        title={getCaption('home')}
        key={'home'}
      >
        {getCaption('home')}
      </Menu.Item>
      <Menu.Item
        title={getCaption('owner-list')}
        key={'owner-list'}
      >
        {getCaption('owner-list')}
      </Menu.Item>
      <Menu.Item
        title={getCaption('owner-editor')}
        key={'owner-editor'}
      >
        {getCaption('owner-editor')}
      </Menu.Item>
    </Menu>
  );
});
