import {MenuProps} from "antd";
import {HomeOutlined, MenuOutlined} from "@ant-design/icons";
import {MenuItem, VerticalMenu } from "@haulmont/jmix-react-ui";

export interface AppMenuProps extends MenuProps {}

export const AppMenu = (props: AppMenuProps) => {
  return (
    <VerticalMenu {...props}>
      <MenuItem
        screenId="HomePage"
        icon={<HomeOutlined />}
        caption={"screen.home"}
        key={"home"}
      />
      <MenuItem
        screenId="OwnerList"
        icon={<MenuOutlined />}
        caption={"screen.ownerList"}
        key={"ownerList"}
      />
    </VerticalMenu>
  );
};