import React from 'react';
import './App.css';
import { gql, useQuery } from '@apollo/client';
import {Result, Skeleton} from "antd";

const QUERY = gql`
  query Get_Owner_List($page: PaginationInput) {
    ownerList(page: $page) {
      id
      firstName
      lastName
    }
  }
`;

function App() {
  const {loading, error, data} = useQuery(QUERY);

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
}

export default App;
