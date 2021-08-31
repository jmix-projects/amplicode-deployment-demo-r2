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
        key={"934ac023-1834-46a7-95c6-cbda5fb7a024"}
      />
      <MenuItem
        screenId={"OwnerEditor"}
        icon={<BarsOutlined />}
        caption={"screen.OwnerEditor"}
        key={"1e7c52c6-0256-4f5d-9f0f-fe13cb0c9346"}
      />
      <MenuItem
        screenId={"PetList"}
        icon={<BarsOutlined />}
        caption={"screen.PetList"}
        key={"53953958-1995-46dc-86e5-ac594b0e5dc6"}
      />
      <MenuItem
        screenId={"PetEditor"}
        icon={<BarsOutlined />}
        caption={"screen.PetEditor"}
        key={"364abbe0-a9f6-4a03-bbe7-89eaaed6b469"}
      />
    </VerticalMenu>
  );
};
