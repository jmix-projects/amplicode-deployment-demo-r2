import {generateKey} from "./generateKey";
import {ReactNode} from "react";

export class BreadcrumbState {
  node: ReactNode;
  caption: string;
  key: string;

  constructor(breadcrumbCaption: string, node: ReactNode) {
    this.caption = breadcrumbCaption;
    this.key = generateKey();
    this.node = node;
  }
}