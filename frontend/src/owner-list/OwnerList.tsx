import {gql, useQuery} from "@apollo/client";
import {Result, Skeleton} from "antd";
import React from "react";

const QUERY = gql`
    query Get_Owner_List($page: PaginationInput) {
        ownerList(page: $page) {
            id
            firstName
            lastName
        }
    }
`;

export const Main = () => {
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