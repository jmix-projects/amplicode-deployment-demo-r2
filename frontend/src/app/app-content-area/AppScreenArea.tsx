import { ScreenContext, useScreens } from "../../framework/screen-api/ScreenContext";
import {Tabs} from "antd";
import {useCallback} from "react";
import {observer} from "mobx-react";

export const AppScreenArea = observer(() => {
  const {
    tabs,
    activeTabKey,
    activateTab
  } = useScreens();

  return (
    <Tabs activeKey={activeTabKey}
          onChange={activateTab}
    >
      {
        Object.keys(tabs).map(tabKey => (
          <Tabs.TabPane key={tabKey}>
            <Tab
          </Tabs.TabPane>
        ))
      }
    </Tabs>
  );
});