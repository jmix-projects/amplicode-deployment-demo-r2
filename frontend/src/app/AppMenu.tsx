import {Menu } from "antd";
import {HomeOutlined, MenuOutlined} from "@ant-design/icons";
import {FormattedMessage, useIntl} from "react-intl";
import OwnerList from "./owner-list/OwnerList";
import OwnerEditor from "./owner-editor/OwnerEditor";
import {useScreens} from "../framework/screen-api/ScreenContext";
import {useCallback} from "react";
import {ReactComponent} from "../framework/screen-api/ReactComponent";
import {Home} from "./home/Home";

export const AppMenu = () => {
  const intl = useIntl();
  const screens = useScreens();

  const handleClick = useCallback(({key}: {key: string}) => {
    const menuItemInfo = menuComponentRegistry[key];
    if (menuItemInfo == null) {
      console.error(`MenuItemInfo is not found for menu item with key ${key}`);
      return;
    }
    const tabCaption = intl.formatMessage({id: menuItemInfo.tabCaptionI18nKey});
    const breadcrumbCaption = intl.formatMessage({
      id: menuItemInfo.breadcrumbCaptionI18nKey ?? menuItemInfo.tabCaptionI18nKey
    });
    const {component} = menuItemInfo;

    screens.openInTab({tabCaption, breadcrumbCaption, component, tabKey: key});
  }, [intl, screens]);

  return (
    <Menu onClick={handleClick}>
      <Menu.Item
        icon={<HomeOutlined />}
        title={'Home'}
        key={"home"}
      >
        Home
      </Menu.Item>
      <Menu.Item
        icon={<MenuOutlined />}
        title={intl.formatMessage({id: "screen.OwnerList"})}
        key={"ce22d23e-340d-4a0c-ba70-d26a51a045f9"}
      >
        <FormattedMessage id="screen.OwnerList" />
      </Menu.Item>
      <Menu.Item
        icon={<MenuOutlined />}
        title={intl.formatMessage({id: "screen.OwnerEditor"})}
        key={"3f2019b4-6945-4591-a8f1-a3521e64023a"}
      >
        <FormattedMessage id="screen.OwnerEditor" />
      </Menu.Item>
    </Menu>
  );
};

export interface MenuItemInfo {
  tabCaptionI18nKey: string;
  /**
   * If omitted, {@link tabCaptionI18nKey} will be used in its place
   */
  breadcrumbCaptionI18nKey?: string;
  component: ReactComponent;
}

export const menuComponentRegistry: Record<string, MenuItemInfo> = {
  'home': {
    component: Home,
    tabCaptionI18nKey: 'screen.home',
  },
  'ce22d23e-340d-4a0c-ba70-d26a51a045f9': {
    component: OwnerList,
    tabCaptionI18nKey: 'screen.OwnerList',
  },
  '3f2019b4-6945-4591-a8f1-a3521e64023a': {
    component: OwnerEditor,
    tabCaptionI18nKey: 'screen.OwnerEditor',
  }
};
