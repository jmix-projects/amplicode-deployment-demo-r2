import React from "react";
import {observer} from "mobx-react";
import {Layout} from "antd";
import { AppHeader } from "../app-header/AppHeader";
import './AppMain.css';
import {AppMenu} from "../app-menu/AppMenu";
import { Home } from "../home/Home";

export const AppMain = observer(() => {
  return (
    <Layout className="main-layout">
      <Layout.Header>
        <AppHeader />
      </Layout.Header>
      <Layout className="layout-container">
        <Layout.Sider
          width={200}
          breakpoint="sm"
          collapsedWidth={0}
          className="layout-sider"
        >
          <AppMenu />
        </Layout.Sider>

        <Layout className="layout-content">
          <Layout.Content>
            <Home/>
          </Layout.Content>
        </Layout>
      </Layout>
    </Layout>
  );
});