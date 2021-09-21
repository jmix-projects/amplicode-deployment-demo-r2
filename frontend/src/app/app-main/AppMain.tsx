import React from "react";
import { observer } from "mobx-react";
import {Layout} from "antd";
import { AppHeader } from "../app-header/AppHeader";
import "./AppMain.css";
import { AppMenu } from "../AppMenu";
import {AppScreenArea} from "../app-workspace/AppScreenArea";
import {Route, Switch} from "react-router-dom";
import {Component1} from "../custom-routes/Component1";
import { Component2 } from "../custom-routes/Component2";
import {Page404} from "../../framework/components/page-404/Page404";
import {getScreenPaths, screenRegistry} from "../screenRegistry";

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
            <Switch>
              {/*You can use React Router components in addition to or instead of Screen API*/}
              <Route path='/component1'>
                <Component1/>
              </Route>
              <Route path='/component2'>
                <Component2/>
              </Route>
              <Route path={getScreenPaths()}>
                <AppScreenArea/>
              </Route>
              <Route path='/' exact />
              <Route>
                <Page404/>
              </Route>
            </Switch>
          </Layout.Content>
        </Layout>
      </Layout>
    </Layout>
  );
});
