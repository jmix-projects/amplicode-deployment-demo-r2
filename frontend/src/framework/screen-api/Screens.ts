import {ReactComponent} from "./ReactComponent";
import {action, computed, makeObservable, observable} from "mobx";
import {TabState} from "./TabState";

export interface OpenInBreadcrumbParams {
  breadcrumbCaption: string;
  component: ReactComponent;
  props?: any;
}

export interface OpenInTabParams extends OpenInBreadcrumbParams {
  tabCaption: string;
  tabKey?: string;
}

export class Screens {
  @observable tabs: TabState[] = [];
  @observable activeTabIndex?: number;

  enableScrollToTop: boolean = true;

  @computed
  get activeTab() {
    if (this.activeTabIndex == null) {
      return undefined;
    }
    return this.tabs[this.activeTabIndex];
  }

  @computed
  get activeTabKey() {
    return this.activeTab?.key;
  }

  constructor() {
    makeObservable(this)
  }

  @action
  openInTab = (params: OpenInTabParams) => {
    const {tabCaption, breadcrumbCaption, component, props, tabKey} = params;

    if (tabKey != null && this.tabs.some(t => t.key === tabKey)) {
      // Tab with given key already exists so we just activate it
      this.makeTabActive(tabKey);
    } else {
      // We create a new tab and activate it
      const newTab = new TabState({tabCaption, breadcrumbCaption, component, props, tabKey});
      this.tabs.push(newTab);
      this.makeTabActive(newTab.key);
    }

    this.scrollToTop();
  };

  @action
  openInBreadcrumb = (params: OpenInBreadcrumbParams) => {
    const {breadcrumbCaption, component, props} = params;
    this.activeTab?.newBreadcrumb(breadcrumbCaption, component, props);
    this.scrollToTop();
  };

  @action
  makeTabActive = (key: string) => {
    this.activeTabIndex = this.tabs.findIndex(t => t.key === key);
  };

  @action
  makeBreadcrumbActive = (key: string) => {
    this.activeTab?.makeBreadcrumbActive(key);
  };

  @action
  closeTab = (key: string) => {
    this.tabs = this.tabs.filter(t => t.key !== key);
  };

  @action
  closeBreadcrumb = (key: string) => {
    this.activeTab?.closeBreadcrumb(key);
    this.scrollToTop();
  };

  @action
  closeActiveBreadcrumb = () => {
    this.activeTab?.closeActiveBreadcrumb();
    this.scrollToTop();
  };

  scrollToTop = () => {
    if (this.enableScrollToTop) {
      window.scrollTo(0, 0);
    }
  }
}
