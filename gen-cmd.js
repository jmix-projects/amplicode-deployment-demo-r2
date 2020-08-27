const dir = 'mvp';

const listQuery = `
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

const deleteMutation = `
mutation Delete_Pet($id: Long!) {
  delete_Pet(id: $id)
}
`;

const instanceQuery = `
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

const upsertMutation = `
mutation Update_Pet($input: PetInputDTOInput) {
  update_Pet(input: $input) {
    id
  }
}
`;

function esc(str) {
  return str.replace('/\\n/g', '\\n')
    .replace('/\\r/g', '\\r')
    .replace('/\\t/g', '\\t');
}

function btoa(str) {
  return Buffer.from(str).toString('base64');
}

const browserAnswers = btoa(JSON.stringify({
  componentName: 'pet-list',
  menuItem: 'ROOT',
  query: esc(listQuery),
  mutation: esc(deleteMutation),
  nameField: 'firstName'
}));

const editorAnswers = btoa(JSON.stringify({
  componentName: "pet-editor",
  menuItem: "ROOT",
  query: esc(instanceQuery),
  mutation: esc(upsertMutation),
  listQueryName: "petList"
}));

const browserCommand = `
gjf react-typescript:mvp-entity-browser \\
  --answers ${browserAnswers} \\
  --schema ../schema.json \\
  --dest ./src/app/${dir} \\
  --dirShift ../../
`;

const editorCommand = `
gjf react-typescript:mvp-entity-editor \\
  --answers ${editorAnswers} \\
  --schema ../schema.json \\
  --dest ./src/app/${dir} \\
  --dirShift ../../
`;

console.log(browserCommand);
console.log(editorCommand);


