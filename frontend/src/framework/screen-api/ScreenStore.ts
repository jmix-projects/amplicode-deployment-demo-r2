import { ReactComponent } from "./ReactComponent";
import {observable} from "mobx";

export class TabStore {
  @observable breadcrumbs: Record<string, BreadcrumbStore> = {};
  @observable activeBreadcrumbKey?: string;

  get activeBreadcrumb() {
    if (this.activeBreadcrumbKey == null) {
      return undefined;
    }
    return this.breadcrumbs[this.activeBreadcrumbKey];
  }

  constructor(component?: ReactComponent) {
    if (component != null) {
      this.newBreadcrumb(component);
    }
  }

  newBreadcrumb(component: ReactComponent) {
    const breadcrumbKey = generateKey();
    this.activeBreadcrumbKey = breadcrumbKey;
    this.breadcrumbs[breadcrumbKey] = new BreadcrumbStore(component);
  }

  activateBreadcrumb(key: string) {
    this.activeBreadcrumbKey = key;
  }

  closeBreadcrumb(key: string) {
    delete this.breadcrumbs[key];
  }
}

export class BreadcrumbStore {
  component?: ReactComponent;

  constructor(component?: ReactComponent) {
    this.component = component;
  }
}

export class ScreenStore {
  @observable tabs: Record<string, TabStore> = {};
  @observable activeTabKey?: string;

  get activeTab() {
    if (this.activeTabKey == null) {
      return undefined;
    }
    return this.tabs[this.activeTabKey];
  }

  newTab(component: ReactComponent) {
    const key = generateKey();
    this.tabs[key] = new TabStore(component);
    this.activeTabKey = key;
  }

  newBreadcrumb(component: ReactComponent) {
    this.activeTab?.newBreadcrumb(component);
  }

  activateTab(key: string) {
    this.activeTabKey = key;
  }

  activateBreadcrumb(key: string) {
    this.activeTab?.activateBreadcrumb(key);
  }

  closeTab(key: string) {
    delete this.tabs[key];
  }

  closeBreadcrumb(key: string) {
    this.activeTab?.closeBreadcrumb(key);
  }
}

function generateKey() {
  // TODO Consider replacing with uuid.v4()
  return String(window.crypto.getRandomValues(new Uint32Array(1))[0]);
}