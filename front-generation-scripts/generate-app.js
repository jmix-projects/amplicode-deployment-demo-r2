const { runCmdSync, btoa, gjf } = require("./common");

const appAnswers = btoa(JSON.stringify({
  appTitle: "Jmix2 Petclinic",
  appShortName: "jmix2-petclinic",
  graphqlUri: "/graphql"
}));

const appCommand = `
${gjf} react-typescript:mvp-app \\
  --answers ${appAnswers} \\
  --dest ../frontend
`;

runCmdSync('mkdir ../frontend');
runCmdSync(appCommand);
