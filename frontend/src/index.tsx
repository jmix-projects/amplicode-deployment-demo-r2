import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./app/App";
import reportWebVitals from "./reportWebVitals";
import {
  ApolloClient,
  ApolloProvider,
  createHttpLink, gql,
  InMemoryCache, useMutation
} from "@apollo/client";
import "antd/dist/antd.min.css";
import axios from "axios";
import { SecurityStore } from "./framework/security/security";
import { onError } from "@apollo/client/link/error";
import { IntlProvider } from "react-intl";
import en from "./i18n/en.json";
import { GRAPHQL_URI } from "./config";
import { ScreenContext } from "./framework/screen-api/ScreenContext";
import { Screens } from "./framework/screen-api/Screens";
import {HashRouter} from "react-router-dom";
import {DevSupport} from "@react-buddy/ide-toolbox";
import {ComponentPreviews} from "./dev/previews";
import {useInitial} from "./dev/hook";

export const securityStore = new SecurityStore();

axios.interceptors.response.use(response => {
  if (response.status === 401) {
    securityStore.logout();
  }
  return response;
});

const httpLink = createHttpLink({
  uri: GRAPHQL_URI,
  credentials: "same-origin"
});

const logoutLink = onError(({ networkError }) => {
  if (networkError == null || !("statusCode" in networkError)) {
    return;
  }
  if (networkError.statusCode === 401) {
    securityStore.logout();
  }
});

const client = new ApolloClient({
  link: logoutLink.concat(httpLink),
  cache: new InMemoryCache({
    addTypename: false
  }),
  defaultOptions: {
    query: {
      fetchPolicy: "network-only"
    },
    watchQuery: {
      fetchPolicy: "cache-and-network"
    }
  }
});

const screens = new Screens();

ReactDOM.render(
  <React.StrictMode>
    <ScreenContext.Provider value={screens}>
      <ApolloProvider client={client}>
        <IntlProvider locale="en" messages={en}>
            <HashRouter>
              <DevSupport
                  ComponentPreviews={<ComponentPreviews />}
                  useInitialHook={useInitial}
              >
              <App />
              </DevSupport>
            </HashRouter>

        </IntlProvider>
      </ApolloProvider>
    </ScreenContext.Provider>
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
