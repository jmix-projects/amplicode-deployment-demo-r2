import {Breadcrumb} from "antd";
import {observer} from "mobx-react";
import { BreadcrumbState } from "../../../screen-api/BreadcrumbState";
import {TabState} from "../../../screen-api/TabState";

type Props = {
  tab: TabState;
}

export const TabContent = observer(({tab}: Props) => {
  return (
    <>
      <BreadcrumbsArea tab={tab}/>
      {tab.content}
    </>
  );
});

const BreadcrumbsArea = ({tab}: Props) => {
  if (tab.breadcrumbs.length > 1) {
    return (
      <Breadcrumb>
        {tab.breadcrumbs.map((breadcrumb: BreadcrumbState, index: number) => (
          <Breadcrumb.Item>
            {isLastElement(index, tab.breadcrumbs) &&
            breadcrumb.caption
            }
            {!isLastElement(index, tab?.breadcrumbs) && (
              <button onClick={() => tab?.makeBreadcrumbActive(breadcrumb.key)}>
                {breadcrumb.caption}
              </button>
            )}
          </Breadcrumb.Item>
        ))}
      </Breadcrumb>
    );
  }

  return null;
};

function isLastElement(index: number, arr: unknown[]): boolean {
  return index < arr.length - 1;
}