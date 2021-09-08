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

const ownerDeleteMutation = `
mutation Delete_Owner($id: Long!) {
  delete_Owner(id: $id)
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

const ownerUpsertMutation = `
mutation Update_Owner($input: OwnerInputDTOInput) {
  update_Owner(input: $input) {
    id
  }
}
`;

const ownerListAnswers = btoa(JSON.stringify({
  componentName: 'owner-list',
  menuItem: 'ROOT',
  query: esc(ownerListQuery),
  mutation: esc(ownerDeleteMutation)
}));

const ownerEditorAnswers = btoa(JSON.stringify({
  componentName: 'owner-editor',
  menuItem: "ROOT",
  query: esc(ownerDetailsQuery),
  mutation: esc(ownerUpsertMutation),
  listQueryName: 'ownerList'
}));

const ownerListCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${ownerListAnswers} \\
  --schema ../schema.json \\
  --dest ../frontend/src/app/owner-list \\
  --dirShift ../../
`;

const ownerEditorCommand = `
${gjf} react-typescript:mvp-entity-editor \\
  --answers ${ownerEditorAnswers} \\
  --schema ../schema.json \\
  --dest ../frontend/src/app/owner-editor \\
  --dirShift ../../
`;

runCmdSync(ownerListCommand);
runCmdSync(ownerEditorCommand);