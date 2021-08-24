import { Menu } from "antd";
import {HomeOutlined} from "@ant-design/icons";

export const AppMenu = () => {
  return (
    <Menu
      mode={"inline"}
      className='app-menu'
    >
      <Menu.Item
        title="Home"
        icon={<HomeOutlined />}
        key={"home"}
        onClick={() => {}}
      >
        Home
      </Menu.Item>
    </Menu>
  );
};