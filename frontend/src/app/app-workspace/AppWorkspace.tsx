import { useScreens } from "../../framework/screen-api/ScreenContext";
import {Tabs} from "antd";
import {observer} from "mobx-react";
import {TabHeading} from "../../framework/components/tab-heading/TabHeading";
import {BreadcrumbsArea} from "../../framework/components/breadcrumbs-area/BreadcrumbsArea";

export const AppWorkspace = observer(() => {
  const {
    tabs,
    activeTabKey,
    makeTabActive,
    closeTab,
    activeBreadcrumb,
    activeTabIndex
  } = useScreens();

  return (
    <Tabs activeKey={activeTabKey}
          onChange={makeTabActive}
    >
      {
        tabs.map(tab => (
          <Tabs.TabPane key={tab.key}
                        tab={<TabHeading caption={tab.caption}
                                         onClose={(e) => {
                                           closeTab(tab.key);
                                           e.stopPropagation();
                                         }}
                             />}
          >
            <BreadcrumbsArea breadcrumbs={tab.breadcrumbs} />
            {activeBreadcrumb?.content}
          </Tabs.TabPane>
        ))
      }
    </Tabs>
  );
});
