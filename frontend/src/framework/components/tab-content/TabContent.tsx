import { useScreens } from "../../screen-api/ScreenContext";
import {Breadcrumb} from "antd";

export const TabContent = () => {
  const screens = useScreens();

  if (screens == null) {
    return null;
  }

  const {activeTab} = screens;

  return (
    <>
      <Breadcrumb>

      </Breadcrumb>
    </>
  );
};