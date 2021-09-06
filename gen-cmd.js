const execSync = require('child_process').execSync;

const runCmdSync = (command, cwd = process.cwd()) => {
  try {
    console.log(command);
    execSync(command, {
      stdio: 'inherit',
      cwd: cwd,
      shell: '/bin/bash'
    });
  } catch (err) {
    const errorCode = Number.isFinite(parseInt(err.status)) ? parseInt(err.status) : 1;
    process.exit(errorCode);
  }
};

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

const petDeleteMutation = `
mutation Delete_Pet($id: Long!) {
  delete_Pet(id: $id)
}
`;

const ownerDeleteMutation = `
mutation Delete_Owner($id: Long!) {
  delete_Owner(id: $id)
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

const petUpsertMutation = `
mutation Update_Pet($input: PetInputDTOInput) {
  update_Pet(input: $input) {
    id
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

function esc(str) {
  return str.replace('/\\n/g', '\\n')
    .replace('/\\r/g', '\\r')
    .replace('/\\t/g', '\\t');
}

function btoa(str) {
  return Buffer.from(str).toString('base64');
}

const petListAnswers = btoa(JSON.stringify({
  componentName: 'pet-list',
  menuItem: 'ROOT',
  query: esc(petListQuery),
  mutation: esc(petDeleteMutation),
  nameField: 'firstName'
}));

const ownerListAnswers = btoa(JSON.stringify({
  componentName: 'owner-list',
  menuItem: 'ROOT',
  query: esc(ownerListQuery),
  mutation: esc(ownerDeleteMutation)
}));

const readOnlyOwnerListAnswers = btoa(JSON.stringify({
  componentName: 'read-only-owner-list',
  menuItem: 'ROOT',
  query: esc(ownerListQuery)
}));

const petEditorAnswers = btoa(JSON.stringify({
  componentName: "pet-editor",
  menuItem: "ROOT",
  query: esc(petDetailsQuery),
  mutation: esc(petUpsertMutation),
  listQueryName: "petList"
}));

const ownerEditorAnswers = btoa(JSON.stringify({
  componentName: 'owner-editor',
  menuItem: "ROOT",
  query: esc(ownerDetailsQuery),
  mutation: esc(ownerUpsertMutation),
  listQueryName: 'ownerList'
}));

const readOnlyOwnerDetailsAnswers = btoa(JSON.stringify({
  componentName: 'read-only-owner-details',
  menuItem: 'ROOT',
  query: esc(ownerDetailsQuery),
  listQueryName: 'ownerList'
}));

const appAnswers = btoa(JSON.stringify({
  appTitle: "Jmix2 Petclinic",
  appShortName: "jmix2-petclinic",
  graphqlUri: "/graphql"
}));

// To use this script, you need to export MVP_GEN_JMIX_FRONT environment variable containing path to gen-jmix-front.js
const gjf = process.env.MVP_GEN_JMIX_FRONT;

const appCommand = `
${gjf} react-typescript:mvp-app \\
  --answers ${appAnswers} \\
  --dest frontend
`;

const petListCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${petListAnswers} \\
  --schema ../schema.json \\
  --dest ./src/app/pet-list \\
  --dirShift ../../
`;

const ownerListCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${ownerListAnswers} \\
  --schema ../schema.json \\
  --dest ./src/app/owner-list \\
  --dirShift ../../
`;

const readOnlyOwnerListCommand = `
${gjf} react-typescript:mvp-entity-browser \\
  --answers ${readOnlyOwnerListAnswers} \\
  --schema ../schema.json \\
  --dest ./src/app/read-only-owner-list \\
  --dirShift ../../
`;

const petEditorCommand = `
${gjf} react-typescript:mvp-entity-editor \\
  --answers ${petEditorAnswers} \\
  --schema ../schema.json \\
  --dest ./src/app/petEditor \\
  --dirShift ../../
`;

const ownerEditorCommand = `
${gjf} react-typescript:mvp-entity-editor \\
  --answers ${ownerEditorAnswers}
  --schema ../schema.json \\
  --dest ./src/app/owner-editor \\
  --dirShift ../../
`;

const readOnlyOwnerDetailsCommand = `
${gjf} react-typescript:mvp-entity-editor \\
  --answers ${readOnlyOwnerDetailsAnswers}
  --schema ../schema.json \\
  --dest ./src/app/read-only-owner-details \\
  --dirShift ../../
`;

runCmdSync(appCommand);
// runCmdSync('cd frontend');
// runCmdSync(ownerListCommand);
// runCmdSync(ownerEditorCommand);
// runCmdSync(petListCommand);
// runCmdSync(petEditorCommand);
// runCmdSync(readOnlyOwnerListCommand);
// runCmdSync(readOnlyOwnerDetailsCommand);
