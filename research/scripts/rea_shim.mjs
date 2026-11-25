import { readFileSync } from "node:fs";

const REA_ROOT = process.env.REA_ROOT || "/tmp/rea-test/node_modules/rea-agents";
const { runOneShotCli } = await import(`${REA_ROOT}/dist/cli-runtime/cliOneShot.js`);
const pkg = JSON.parse(readFileSync(`${REA_ROOT}/package.json`, "utf8"));

const args = process.argv.slice(2);
let exitCode = 0;

await runOneShotCli(args, {
  environment: process.env,
  runtime: {
    packageName: pkg.name,
    packageVersion: pkg.version,
    versions: process.versions,
    platform: process.platform,
    architecture: process.arch,
  },
  stdoutIsTerminal: false,
  resultDestination: process.stdout,
  closedStdoutStatus: 141,
  writeStdout: (t) => process.stdout.write(t),
  writeStderr: (t) => process.stderr.write(t),
  stdoutClosed: () => false,
  exitCode: () => exitCode,
  setExitCode: (c) => {
    exitCode = c;
  },
  ownCliResources: () => {},
  exitRequested: () => false,
  exit: (code, finish) =>
    finish().then(() => {
      process.exit(code);
    }),
});
process.exit(exitCode);
