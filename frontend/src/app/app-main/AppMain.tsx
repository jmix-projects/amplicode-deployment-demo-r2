import React from "react";
import { observer } from "mobx-react";
import { Layout } from "antd";
import { AppHeader } from "../app-header/AppHeader";
import "./AppMain.css";
import { AppMenu } from "../AppMenu";
import {AppWorkspace} from "../app-workspace/AppWorkspace";
import {HashRouter} from "react-router-dom";

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
            <HashRouter>
              <AppWorkspace/>
            </HashRouter>
          </Layout.Content>
        </Layout>
      </Layout>
    </Layout>
  );
});
