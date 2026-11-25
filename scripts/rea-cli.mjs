import { existsSync } from "node:fs";
import { resolve } from "node:path";
import { pathToFileURL } from "node:url";

const candidates = [
  process.env.REA_AGENTS_ROOT,
  "/tmp/rea-tools/node_modules/rea-agents",
  resolve(process.cwd(), "node_modules/rea-agents"),
].filter(Boolean);

const root = candidates.find((p) =>
  existsSync(resolve(p, "dist/cli-runtime/cliOneShot.js")),
);

if (!root) {
  console.error(
    "rea-agents runtime not found. Install rea-agents@6.10.1 into /tmp/rea-tools or set REA_AGENTS_ROOT.",
  );
  process.exit(1);
}

const { runOneShotCli } = await import(
  pathToFileURL(resolve(root, "dist/cli-runtime/cliOneShot.js")).href
);

const args = process.argv.slice(2);
await runOneShotCli(args, {
  environment: process.env,
  runtime: {
    packageName: "rea-agents",
    packageVersion: "6.10.1",
    versions: process.versions,
    platform: process.platform,
    architecture: process.arch,
  },
  stdoutIsTerminal: false,
  resultDestination: process.stdout,
  closedStdoutStatus: 141,
  writeStdout: (s) => process.stdout.write(s),
  writeStderr: (s) => process.stderr.write(s),
  stdoutClosed: () => false,
  exitCode: () => (typeof process.exitCode === "number" ? process.exitCode : 0),
  setExitCode: (c) => {
    process.exitCode = c;
  },
  ownCliResources: () => {},
  exitRequested: () => false,
  exit: (code) => {
    process.exit(code);
  },
});
