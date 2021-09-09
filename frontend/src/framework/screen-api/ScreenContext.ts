import React, {useContext} from "react";
import {ScreenStore} from "./ScreenStore";

export const ScreenContext = React.createContext<ScreenStore>(new ScreenStore());

export function useScreens(): ScreenStore {
  return useContext(ScreenContext);
}