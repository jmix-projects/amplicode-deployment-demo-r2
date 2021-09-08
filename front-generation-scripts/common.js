const execSync = require('child_process').execSync;

exports.runCmdSync = (command, cwd = process.cwd()) => {
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

exports.esc = (str) => {
  return str.replace('/\\n/g', '\\n')
    .replace('/\\r/g', '\\r')
    .replace('/\\t/g', '\\t');
};

exports.btoa = (str) => {
  return Buffer.from(str).toString('base64');
};

// To use the scripts, you need to export MVP_GEN_JMIX_FRONT environment variable containing path to gen-jmix-front.js
exports.gjf = process.env.MVP_GEN_JMIX_FRONT;
