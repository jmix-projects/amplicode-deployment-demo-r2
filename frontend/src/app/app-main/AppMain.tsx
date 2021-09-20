import React from "react";
import { observer } from "mobx-react";
import { Layout } from "antd";
import { AppHeader } from "../app-header/AppHeader";
import "./AppMain.css";
import { AppMenu } from "../AppMenu";
import {AppWorkspace} from "../app-workspace/AppWorkspace";
import {HashRouter, Route, Switch} from "react-router-dom";
import {Component1} from "../custom-routes/Component1";
import { Component2 } from "../custom-routes/Component2";

export const AppMain = observer(() => {
  return (
    <HashRouter>
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
              <Switch>
                <Route path='/component1'>
                  <Component1/>
                </Route>
                <Route path='/component2'>
                  <Component2/>
                </Route>
                <Route>
                  <AppWorkspace/>
                </Route>
              </Switch>
            </Layout.Content>
          </Layout>
        </Layout>
      </Layout>
    </HashRouter>
  );
});
