import {ReactComponent} from "./ReactComponent";
import {computed, observable} from "mobx";
import {TabState} from "./TabState";

export class ScreenStore {
  @observable tabs: TabState[] = [];
  @observable activeIndex?: number;

  @computed
  get activeTab() {
    if (this.activeIndex == null) {
      return undefined;
    }
    return this.tabs[this.activeIndex];
  }

  newTab(tabCaption: string, breadcrumbCaption?: string, component?: ReactComponent, props?: any) {
    this.tabs.push(new TabState(tabCaption, breadcrumbCaption, component, props));
    window.scrollTo(0, 0);
  }

  newBreadcrumb(breadcrumbCaption: string, component: ReactComponent, props?: any) {
    this.activeTab?.newBreadcrumb(breadcrumbCaption, component, props);
    window.scrollTo(0, 0);
  }

  makeTabActive(key: string) {
    this.activeIndex = this.tabs.findIndex(t => t.key === key);
  }

  makeBreadcrumbActive(key: string) {
    this.activeTab?.makeBreadcrumbActive(key);
  }

  closeTab(key: string) {
    this.tabs = this.tabs.filter(t => t.key !== key);
  }

  closeBreadcrumb(key: string) {
    this.activeTab?.closeBreadcrumb(key);
  }
}
