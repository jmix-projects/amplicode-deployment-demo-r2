const { runCmdSync, esc, btoa, gjf } = require("./common");

const petListQuery = `
query Get_Pet_List($page: PaginationInput) {
  petList(page: $page) {
     id
     identificationNumber
     owner {
       firstName
       lastName
     }
  }
}
`;

const petDeleteMutation = `
mutation Delete_Pet($id: Long!) {
  delete_Pet(id: $id)
}
`;

const petDetailsQuery = `
query Get_Pet($id: Long) {
  pet(id: $id) {
    id
    identificationNumber
    owner {
      firstName
      lastName
    }
  }
}
`;

const petUpsertMutation = `
mutation Update_Pet($input: PetInputDTOInput) {
  update_Pet(input: $input) {
    id
  }
}
`;

const petListAnswers = btoa(JSON.stringify({
  componentName: 'pet-list',
  shouldAddToMenu: true,
  query: esc(petListQuery),
  mutation: esc(petDeleteMutation),
}));

const petEditorAnswers = btoa(JSON.stringify({
  componentName: "pet-editor",
  shouldAddToMenu: false,
  query: esc(petDetailsQuery),
  mutation: esc(petUpsertMutation),
  listQueryName: "petList"
}));

const petListCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${petListAnswers} \\
  --schema ../schema.json \\
  --dest ../frontend/src/app/pet-list \\
  --dirShift ../../
`;

const petEditorCommand = `
${gjf} react-typescript:mvp-entity-editor \\
  --answers ${petEditorAnswers} \\
  --schema ../schema.json \\
  --dest ../frontend/src/app/petEditor \\
  --dirShift ../../
`;

runCmdSync(petListCommand);
runCmdSync(petEditorCommand);