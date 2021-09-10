import {computed, observable} from "mobx";
import {ReactComponent} from "./ReactComponent";
import {generateKey} from "./generateKey";
import {BreadcrumbState} from "./BreadcrumbState";
import React from "react";

export class TabState {
  @observable breadcrumbs: BreadcrumbState[] = [];
  @observable activeIndex?: number;
  caption: string;
  key: string;

  @computed
  get activeBreadcrumb() {
    if (this.activeIndex == null) {
      return undefined;
    }
    return this.breadcrumbs[this.activeIndex];
  }

  constructor(tabCaption: string, breadcrumbTitle?: string, component?: ReactComponent, props?: any) {
    this.caption = tabCaption;
    this.key = generateKey();
    if (breadcrumbTitle != null && component != null) {
      this.newBreadcrumb(breadcrumbTitle, component, props);
    }
  }

  newBreadcrumb(breadcrumbCaption: string, component: ReactComponent, props?: any) {
    const node = React.createElement(component, props);
    this.breadcrumbs.push(new BreadcrumbState(breadcrumbCaption, node));
    this.activeIndex = this.breadcrumbs.length - 1;
  }

  makeBreadcrumbActive(key: string) {
    this.activeIndex = this.breadcrumbs.findIndex(b => b.key === key);
  }

  closeBreadcrumb(key: string) {
    this.breadcrumbs = this.breadcrumbs.filter(b => b.key !== key);
  }
}
