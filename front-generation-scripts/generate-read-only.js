const { runCmdSync, esc, btoa, gjf } = require("./common");

const ownerListQuery = `
query Get_Owner_List {
  ownerList {
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
  componentName: 'ReadOnlyOwnerList',
  shouldAddToMenu: true,
  query: esc(ownerListQuery),
  mode: 'view'
}));

const readOnlyOwnerListWithDetailsAnswers = btoa(JSON.stringify({
  componentName: 'ReadOnlyOwnerListWithDetails',
  shouldAddToMenu: true,
  query: esc(ownerListQuery),
  mode: 'viewWithDetails'
}));

const readOnlyOwnerDetailsAnswers = btoa(JSON.stringify({
  componentName: 'ReadOnlyOwnerDetails',
  shouldAddToMenu: false,
  query: esc(ownerDetailsQuery),
  listQueryName: 'ownerList'
}));

const readOnlyOwnerListCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${readOnlyOwnerListAnswers} \\
  --schema ./schema.graphql \\
  --dest ../frontend/src/app/read-only-owner-list \\
  --dirShift ../../
`;

const readOnlyOwnerListWithDetailsCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${readOnlyOwnerListWithDetailsAnswers} \\
  --schema ./schema.graphql \\
  --dest ../frontend/src/app/read-only-owner-list-with-details \\
  --dirShift ../../
`;
const readOnlyOwnerDetailsCommand = `
${gjf} react-typescript:mvp-entity-editor \\
  --answers ${readOnlyOwnerDetailsAnswers} \\
  --schema ./schema.graphql \\
  --dest ../frontend/src/app/read-only-owner-details \\
  --dirShift ../../
`;

runCmdSync(readOnlyOwnerListCommand);
runCmdSync(readOnlyOwnerListWithDetailsCommand);
runCmdSync(readOnlyOwnerDetailsCommand);
