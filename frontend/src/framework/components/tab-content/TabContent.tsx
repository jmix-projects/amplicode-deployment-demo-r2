import { useScreens } from "../../screen-api/ScreenContext";
import {Breadcrumb} from "antd";
import {observer} from "mobx-react";
import { BreadcrumbState } from "../../screen-api/ScreenStore";

export const TabContent = observer(() => {
  const screens = useScreens();

  if (screens == null) {
    return null;
  }

  const {activeTab} = screens;

  if (activeTab == null) {
    return null;
  }

  return (
    <>
      {(activeTab.breadcrumbs.length > 1) && (
        <Breadcrumb>
          {activeTab.breadcrumbs.map((breadcrumb: BreadcrumbState, index: number) => (
            <Breadcrumb.Item>
              {isLastElement(index, activeTab.breadcrumbs) &&
                breadcrumb.caption
              }
              {!isLastElement(index, activeTab?.breadcrumbs) && (
                <a href="#"
                   onClick={() => activeTab?.makeBreadcrumbActive(breadcrumb.key)}
                >
                  {breadcrumb.caption}
                </a>
              )}
            </Breadcrumb.Item>
          ))}
        </Breadcrumb>
      )}
    </>
  );
});

function isLastElement(index: number, arr: unknown[]): boolean {
  return index < arr.length - 1;
}