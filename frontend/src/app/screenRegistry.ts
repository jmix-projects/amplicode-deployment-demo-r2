import {Home} from "./home/Home";
import OwnerList from "./owner-list/OwnerList";
import OwnerEditor from "./owner-editor/OwnerEditor";
import {ReactComponent} from "../framework/screen-api/ReactComponent";

export interface ScreenInfo {
  /**
   * i18n key for menu item / tab caption
   */
  captionKey: string;
  /**
   * Component that will be rendered in a new tab when menu item is clicked
   */
  component: ReactComponent;
  props?: any;
}

export const screenRegistry: Record<string, ScreenInfo> = {
  'home': {
    component: Home,
    captionKey: 'screen.home',
  },
  'owner-list': {
    component: OwnerList,
    captionKey: 'screen.OwnerList',
  },
  'owner-editor': {
    component: OwnerEditor,
    captionKey: 'screen.OwnerEditor',
  }
};
