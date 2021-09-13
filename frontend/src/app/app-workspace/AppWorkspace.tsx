import { useScreens } from "../../framework/screen-api/ScreenContext";
import {Tabs} from "antd";
import {observer} from "mobx-react";
import { TabContent } from "../../framework/components/tabs/tab-content/TabContent";
import {TabHeading} from "../../framework/components/tabs/tab-heading/TabHeading";

export const AppWorkspace = observer(() => {
  const {
    tabs,
    activeTabKey,
    makeTabActive,
    closeTab
  } = useScreens();

  return (
    <Tabs activeKey={activeTabKey}
          onChange={makeTabActive}
    >
      {
        tabs.map(tab => (
          <Tabs.TabPane tab={<TabHeading caption={tab.caption}
                                         onClose={() => closeTab(tab.key)}
                             />}
                        key={tab.key}
          >
            <TabContent tab={tab} />
          </Tabs.TabPane>
        ))
      }
    </Tabs>
  );
});
