import { useScreens } from "../../framework/screen-api/ScreenContext";
import {Tabs} from "antd";
import {observer} from "mobx-react";
import {TabHeading} from "../../framework/components/tab-heading/TabHeading";
import {BreadcrumbsArea} from "../../framework/components/breadcrumbs-area/BreadcrumbsArea";
import {useHistory, useLocation} from "react-router-dom";
import {screenRegistry} from "../screenRegistry";
import {useIntl} from "react-intl";
import {useEffect} from "react";

export const AppWorkspace = observer(() => {
  const {
    tabs,
    activeTab,
    makeTabActive,
    closeTab,
    activeBreadcrumb,
    openInTab
  } = useScreens();

  const history = useHistory();
  const location = useLocation();
  const intl = useIntl();

  useEffect(() => {
    if (activeTab?.key != null) {
      // There is an active tab. We may need to update the route.
      if (getScreenKey(location.pathname) !== activeTab.key) {
        history.replace('/' + activeTab.route);
        return;
      }
    }
    if (activeTab?.key == null) {
      // There is no active tab. We need to check the url for a tab route and open a tab if needed.

      // Here we only check the first segment of the route.
      // Dealing with the remaining route is the responsibility of the component that corresponds.
      const screenKey = getScreenKey(location.pathname);
      if (screenKey == null) {
        return;
      }

      const tabItem = screenRegistry[screenKey];
      if (tabItem != null) {
        const {component, props, captionKey} = tabItem;
        openInTab({
          tabCaption: intl.formatMessage({id: captionKey}),
          breadcrumbCaption: intl.formatMessage({id: captionKey}),
          component,
          props,
          tabKey: screenKey
        });
      }
    }
  }, [activeTab, history, location, intl, openInTab]);

  return (
    <Tabs activeKey={activeTab?.key}
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

function getScreenKey(pathname: string): string | undefined {
  return pathname.split(/[/?]/)?.[1];
}

