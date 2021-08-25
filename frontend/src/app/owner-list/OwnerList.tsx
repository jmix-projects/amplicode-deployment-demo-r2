import {gql, useQuery} from "@apollo/client";
import {Result, Skeleton} from "antd";
import React from "react";
import {registerScreen} from "@haulmont/jmix-react-ui";
import {Home} from "../home/Home";

const ROUTING_PATH = "/owner-list";

const QUERY = gql`
    query Get_Owner_List($page: PaginationInput) {
        ownerList(page: $page) {
            id
            firstName
            lastName
        }
    }
`;

export const OwnerList = () => {
  const {loading, error, data} = useQuery(QUERY, {
    variables: {
      page: {
        pageNumber: 0,
        pageSize: 20,
        sort: {orders : []}
      }
    }
  });

  if (loading) {
    return <Skeleton/>;
  }

  if (error) {
    return <Result status='error'
                   title='Query failed'
                   subTitle={error.message}
    />;
  }

  return (
    <div className="App">
      <header className="App-header">jmix2-petclinic</header>
      <div>{JSON.stringify(data)}</div>
    </div>
  );
};

registerScreen({
  component: OwnerList,
  caption: "screen.ownerList",
  screenId: "OwnerList",
  menuOptions: {
    pathPattern: ROUTING_PATH,
    menuLink: ROUTING_PATH
  }
});
