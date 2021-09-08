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
        key={"ce22d23e-340d-4a0c-ba70-d26a51a045f9"}
      />
      <MenuItem
        screenId={"OwnerEditor"}
        icon={<BarsOutlined />}
        caption={"screen.OwnerEditor"}
        key={"3f2019b4-6945-4591-a8f1-a3521e64023a"}
      />
      <MenuItem
        screenId={"PetList"}
        icon={<BarsOutlined />}
        caption={"screen.PetList"}
        key={"0423b6c5-d8d3-4502-a23f-b660f28bb582"}
      />
      <MenuItem
        screenId={"PetEditor"}
        icon={<BarsOutlined />}
        caption={"screen.PetEditor"}
        key={"51c17ae2-5fa5-44e5-808f-ec25d067434b"}
      />
    </VerticalMenu>
  );
};
