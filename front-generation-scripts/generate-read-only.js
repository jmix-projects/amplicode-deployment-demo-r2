const { runCmdSync, esc, btoa, gjf } = require("./common");

const ownerListQuery = `
query Get_Owner_List($page: PaginationInput) {
  ownerList(page: $page) {
    id
    firstName
    lastName
    city
  }
}
`;

const ownerDetailsQuery = `
query Get_Owner($id: Long) {
  owner(id: $id) {
    id
    firstName
    lastName
    city
    address
    email
    telephone
  }
}
`;

const readOnlyOwnerListAnswers = btoa(JSON.stringify({
  componentName: 'read-only-owner-list',
  menuItem: 'ROOT',
  query: esc(ownerListQuery),
  mode: 'view'
}));

const readOnlyOwnerListWithDetailsAnswers = btoa(JSON.stringify({
  componentName: 'read-only-owner-list-with-details',
  menuItem: 'ROOT',
  query: esc(ownerListQuery),
  mode: 'viewWithDetails'
}));

const readOnlyOwnerDetailsAnswers = btoa(JSON.stringify({
  componentName: 'read-only-owner-details',
  menuItem: 'ROOT',
  query: esc(ownerDetailsQuery),
  listQueryName: 'ownerList'
}));

const readOnlyOwnerListCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${readOnlyOwnerListAnswers} \\
  --schema ../schema.json \\
  --dest ../frontend/src/app/read-only-owner-list \\
  --dirShift ../../
`;

const readOnlyOwnerListWithDetailsCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${readOnlyOwnerListWithDetailsAnswers} \\
  --schema ../schema.json \\
  --dest ../frontend/src/app/read-only-owner-list-with-details \\
  --dirShift ../../
`;
const readOnlyOwnerDetailsCommand = `
${gjf} react-typescript:mvp-entity-editor \\
  --answers ${readOnlyOwnerDetailsAnswers}
  --schema ../schema.json \\
  --dest ../frontend/src/app/read-only-owner-details \\
  --dirShift ../../
`;

runCmdSync(readOnlyOwnerListCommand);
runCmdSync(readOnlyOwnerListWithDetailsCommand);
runCmdSync(readOnlyOwnerDetailsCommand);
