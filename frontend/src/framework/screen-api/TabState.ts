import {action, computed, observable} from "mobx";
import {ReactComponent} from "./ReactComponent";
import {generateKey} from "../util/generateKey";
import {BreadcrumbState} from "./BreadcrumbState";
import React from "react";

export interface TabParams {
  tabKey?: string;
  tabCaption: string;
  breadcrumbCaption: string;
  component: ReactComponent;
  props?: any
}

export class TabState {
  @observable breadcrumbs: BreadcrumbState[] = [];
  @observable activeBreadcrumbIndex?: number;
  caption: string;
  key: string;

  @computed
  get activeBreadcrumb() {
    if (this.activeBreadcrumbIndex == null) {
      return undefined;
    }
    return this.breadcrumbs[this.activeBreadcrumbIndex];
  }

  @computed
  get content() {
    return this.activeBreadcrumb?.node;
  }

  constructor(tabInput: TabParams) {
    const {tabCaption, breadcrumbCaption, component, props, tabKey} = tabInput;

    this.caption = tabCaption;
    this.key = tabKey ?? generateKey();
    if (breadcrumbCaption != null && component != null) {
      this.newBreadcrumb(breadcrumbCaption, component, props);
    }
  }

  @action
  newBreadcrumb = (breadcrumbCaption: string, component: ReactComponent, props?: any) => {
    const node = React.createElement(component, props);
    this.breadcrumbs.push(new BreadcrumbState(breadcrumbCaption, node));
    this.activeBreadcrumbIndex = this.breadcrumbs.length - 1;
  };

  @action
  makeBreadcrumbActive = (key: string) => {
    this.activeBreadcrumbIndex = this.breadcrumbs.findIndex(b => b.key === key);
  };

  @action
  closeBreadcrumb = (key: string) => {
    this.breadcrumbs = this.breadcrumbs.filter(b => b.key !== key);
  };

  @action
  closeActiveBreadcrumb = () => {
    this.breadcrumbs = this.breadcrumbs.slice(0, this.activeBreadcrumbIndex);
  };
}
