import {ReactComponent} from "./ReactComponent";
import {action, computed, makeObservable, observable} from "mobx";
import React, {ReactNode} from "react";
import {generateKey} from "../util/generateKey";

export interface OpenInBreadcrumbParams {
  breadcrumbCaption: string;
  component: ReactComponent;
  props?: any;
}

export interface OpenInTabParams extends OpenInBreadcrumbParams {
  tabCaption: string;
  tabKey?: string;
}

export interface TabState {
  breadcrumbs: BreadcrumbState[];
  activeBreadcrumbIndex?: number;
  caption: string;
  key: string;
}

export interface BreadcrumbState {
  content: ReactNode;
  caption: string;
  key: string;
}

export class Screens {
  @observable tabs: TabState[] = [];
  @observable activeTabIndex?: number;

  @computed
  get activeTab() {
    if (this.activeTabIndex == null) {
      return undefined;
    }
    return this.tabs[this.activeTabIndex];
  }

  @computed
  get activeBreadcrumb() {
    if (this.activeTab?.activeBreadcrumbIndex == null) {
      return undefined;
    }
    return this.activeTab.breadcrumbs[this.activeTab.activeBreadcrumbIndex];
  }

  @computed
  get activeTabKey() {
    return this.activeTab?.key;
  }

  @computed
  get activeContent() {
    return this.activeBreadcrumb?.content;
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
      const newTab: TabState = {
        breadcrumbs: [],
        caption: tabCaption,
        key: tabKey ?? generateKey(),
      };
      this.tabs.push(newTab);
      this.activeTabIndex = this.tabs.length - 1;
      this.openInBreadcrumb({
        component,
        props,
        breadcrumbCaption
      });
    }
  };

  @action
  openInBreadcrumb = (params: OpenInBreadcrumbParams) => {
    const {breadcrumbCaption, component, props} = params;

    if (this.activeTab == null) {
      throw new Error('No active tab found');
    }

    this.activeTab.breadcrumbs.push({
      caption: breadcrumbCaption,
      content: React.createElement(component, props),
      key: generateKey()
    });
    this.activeTab.activeBreadcrumbIndex = this.activeTab.breadcrumbs.length - 1;
  };

  @action
  makeTabActive = (key: string) => {
    this.activeTabIndex = this.tabs.findIndex(t => t.key === key);
  };

  @action
  makeBreadcrumbActive = (key: string) => {
    if (this.activeTab == null) {
      return;
    }
    this.activeTab.activeBreadcrumbIndex = this.activeTab.breadcrumbs.findIndex(b => b.key === key);
  };

  @action
  closeTab = (key: string) => {
    const closedTabIndex = this.tabs.findIndex(t => t.key === key);
    this.tabs = this.tabs.filter(t => t.key !== key);

    this.fixActiveTabIndex(closedTabIndex);
  };

  @action
  closeBreadcrumb = (key: string) => {
    if (this.activeTab == null) {
      return;
    }
    this.activeTab.breadcrumbs = this.activeTab.breadcrumbs.filter(b => b.key !== key);
  };

  @action
  closeActiveBreadcrumb = () => {
    if (this.activeTab == null) {
      return;
    }
    this.activeTab.breadcrumbs = this.activeTab?.breadcrumbs.slice(0, this.activeTab?.activeBreadcrumbIndex);
    this.activeTab.activeBreadcrumbIndex = this.activeTab.breadcrumbs.length - 1;
  };

  private fixActiveTabIndex(closedTabIndex: number) {
    if (this.activeTabIndex == null) {
      return;
    }

    if (this.tabs.length === 0) {
      // The was only one tab and we have closed it. Clear the active tab index.
      this.activeTabIndex = undefined;
      return;
    }

    if (closedTabIndex === this.activeTabIndex) {
      // We have closed the active tab. Make the rightmost tab active.
      this.activeTabIndex = this.tabs.length - 1;
      return;
    }

    // We have closed an inactive tab.
    if (closedTabIndex > this.activeTabIndex) {
      // Closed tab was to the right of the active tab. Active tab index remains correct.
      return;
    }
    if (closedTabIndex < this.activeTabIndex) {
      // Closed tab was to the left of the active tab. Active tab index is now off by one.
      this.activeTabIndex = this.activeTabIndex - 1;
    }
  }
}
