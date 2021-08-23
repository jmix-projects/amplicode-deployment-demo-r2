import React from 'react';
import './App.css';
import { gql, useQuery } from '@apollo/client';
import {Result, Skeleton} from "antd";
import {securityStore} from "./index";
import Login from './login/Login';
import {observer} from "mobx-react";
import {Main} from "./main/Main";

export const App = observer(() => {
  if (!securityStore.isLoggedIn) {
    return (
      <Login />
    );
  }

  return <Main/>;
});

export default App;
