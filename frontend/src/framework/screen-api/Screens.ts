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
  tabKey: string;
}

export interface TabState {
  breadcrumbs: BreadcrumbState[];
  activeBreadcrumbIndex?: number;
  caption: string;
  key: string;
  route: string;
}

export interface BreadcrumbState {
  content: ReactNode;
  caption: string;
  key: string;
}

export class Screens {
  private _tabs: TabState[] = [];
  private _activeTabIndex?: number;

  get tabs() {
    return this._tabs;
  }

  get activeTab() {
    if (this._activeTabIndex == null) {
      return undefined;
    }
    return this._tabs[this._activeTabIndex];
  }

  get activeBreadcrumb() {
    if (this.activeTab?.activeBreadcrumbIndex == null) {
      return undefined;
    }
    return this.activeTab.breadcrumbs[this.activeTab.activeBreadcrumbIndex];
  }

  get activeContent() {
    return this.activeBreadcrumb?.content;
  }

  constructor() {
    makeObservable<Screens, '_tabs' | '_activeTabIndex'>(this,{
      _tabs: observable,
      _activeTabIndex: observable,
      tabs: computed,
      activeTab: computed,
      activeBreadcrumb: computed,
      activeContent: computed,
      openInTab: action,
      openInBreadcrumb: action,
      makeTabActive: action,
      setActiveTabIndex: action,
      makeBreadcrumbActive: action,
      closeTab: action,
      closeBreadcrumb: action,
      closeActiveBreadcrumb: action
    });
  }

  openInTab = (params: OpenInTabParams) => {
    const {tabCaption, breadcrumbCaption, component, props, tabKey} = params;

    if (this._tabs.some(t => t.key === tabKey)) {
      // Tab with given key already exists so we just activate it
      this.makeTabActive(tabKey);
    } else {
      // We create a new tab and activate it
      const newTab: TabState = {
        breadcrumbs: [],
        caption: tabCaption,
        key: tabKey,
        route: tabKey
      };
      this._tabs.push(newTab);
      this.setActiveTabIndex(this._tabs.length - 1);
      this.openInBreadcrumb({
        component,
        props,
        breadcrumbCaption
      });
    }
  };

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

  makeTabActive = (key: string) => {
    this.setActiveTabIndex(this._tabs.findIndex(t => t.key === key));
  };

  setActiveTabIndex = (index: number) => {
    this.saveTabRoute();
    this._activeTabIndex = index;
  };

  makeBreadcrumbActive = (key: string) => {
    if (this.activeTab == null) {
      return;
    }
    this.activeTab.activeBreadcrumbIndex = this.activeTab.breadcrumbs.findIndex(b => b.key === key);
  };

  closeTab = (key: string) => {
    const closedTabIndex = this._tabs.findIndex(t => t.key === key);
    this._tabs = this._tabs.filter(t => t.key !== key);

    this.fixActiveTabIndex(closedTabIndex);
  };

  closeBreadcrumb = (key: string) => {
    if (this.activeTab == null) {
      return;
    }
    this.activeTab.breadcrumbs = this.activeTab.breadcrumbs.filter(b => b.key !== key);
  };

  closeActiveBreadcrumb = () => {
    if (this.activeTab == null) {
      return;
    }
    this.activeTab.breadcrumbs = this.activeTab?.breadcrumbs.slice(0, this.activeTab?.activeBreadcrumbIndex);
    this.activeTab.activeBreadcrumbIndex = this.activeTab.breadcrumbs.length - 1;
  };

  private saveTabRoute() {
    if (this.activeTab != null) {
      // Active tab might have modified the route (e.g. added pagination parameters, etc.)
      // Before switching to a new tab, we need to save the actual route, so that when we change back, we don't lose the changes.
      const {hash} = window.location;
      this.activeTab.route = hash.split('#/')[1];
    }
  }

  private fixActiveTabIndex(closedTabIndex: number) {
    if (this._activeTabIndex == null) {
      return;
    }

    if (this._tabs.length === 0) {
      // The was only one tab and we have closed it. Clear the active tab index.
      this._activeTabIndex = undefined;
      return;
    }

    if (closedTabIndex === this._activeTabIndex) {
      // We have closed the active tab. Make the rightmost tab active.
      this._activeTabIndex = this._tabs.length - 1;
      return;
    }

    // We have closed an inactive tab.
    if (closedTabIndex > this._activeTabIndex) {
      // Closed tab was to the right of the active tab. Active tab index remains correct.
      return;
    }
    if (closedTabIndex < this._activeTabIndex) {
      // Closed tab was to the left of the active tab. Active tab index is now off by one.
      this._activeTabIndex = this._activeTabIndex - 1;
    }
  }
}
