import { ScreenContext, useScreens } from "../../framework/screen-api/ScreenContext";
import {Tabs} from "antd";
import {useCallback} from "react";
import {observer} from "mobx-react";
import { TabContent } from "../../framework/components/tab-content/TabContent";

export const AppWorkspace = observer(() => {
  const {
    tabs,
    activeTab,
    makeTabActive
  } = useScreens();

  return (
    <Tabs activeKey={activeTab?.key}
          onChange={makeTabActive}
    >
      {
        Object.keys(tabs).map(tabKey => (
          <Tabs.TabPane key={tabKey}>
            <TabContent />
          </Tabs.TabPane>
        ))
      }
    </Tabs>
  );
});