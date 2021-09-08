import { MenuProps } from "antd";
import { HomeOutlined, BarsOutlined } from "@ant-design/icons";
import { MenuItem, VerticalMenu } from "@haulmont/jmix-react-ui";

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
        screenId={"OwnerList"}
        icon={<BarsOutlined />}
        caption={"screen.OwnerList"}
        key={"3607afaa-52f5-43f2-a267-59608f97d1b2"}
      />
      <MenuItem
        screenId={"OwnerEditor"}
        icon={<BarsOutlined />}
        caption={"screen.OwnerEditor"}
        key={"2797bc65-abbe-464a-8cb6-554d56fa1e7e"}
      />
      <MenuItem
        screenId={"PetList"}
        icon={<BarsOutlined />}
        caption={"screen.PetList"}
        key={"74e70596-e754-4a6c-8a93-09c36714a632"}
      />
      <MenuItem
        screenId={"PetEditor"}
        icon={<BarsOutlined />}
        caption={"screen.PetEditor"}
        key={"d3e8e635-eb59-42ab-aac8-b6d1e3190f70"}
      />
    </VerticalMenu>
  );
};
