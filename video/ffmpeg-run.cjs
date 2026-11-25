'use strict';
/*
 * Minimal ffmpeg.wasm (0.11.x) runner for Node.js.
 * - Loads @ffmpeg/core locally (no CDN).
 * - Injects wasmBinary directly so no network fetch of the wasm binary.
 * - run(args) returns a Promise resolved when ffmpeg prints 'FFMPEG_END'.
 * - Exposes Core.FS for MEMFS read/write.
 */
const fs = require('fs');
const createFFmpegCore = require('@ffmpeg/core');

const CORE_JS = require.resolve('@ffmpeg/core/dist/ffmpeg-core.js');
const CORE_WASM = require.resolve('@ffmpeg/core/dist/ffmpeg-core.wasm');

let Core = null;
let proxy_main = null;

let _stdout = '';
let _stderr = '';
let runResolve = null;
let runReject = null;
let pendingBufs = null;
let pendingArgvPtr = null;
let proxyCode = 0;

const capture = {
  print: (m) => {
    const msg = String(m);
    _stdout += msg + '\n';
    if (msg.trim() === 'FFMPEG_END' && runResolve) {
      const { code, stdout, stderr } = finalize();
      runResolve({ code, stdout, stderr });
      runResolve = null; runReject = null;
    }
  },
  printErr: (m) => {
    _stderr += String(m) + '\n';
  },
};

function finalize() {
  if (pendingBufs) { pendingBufs.forEach((b) => Core._free(b)); pendingBufs = null; }
  if (pendingArgvPtr !== null) { Core._free(pendingArgvPtr); pendingArgvPtr = null; }
  return { code: proxyCode, stdout: _stdout, stderr: _stderr };
}

async function ensureCore() {
  if (Core) return;
  const wasmBinary = fs.readFileSync(CORE_WASM);
  Core = await createFFmpegCore({
    mainScriptUrlOrBlob: CORE_JS,
    wasmBinary,
    locateFile: (p, prefix) => prefix + p,
    print: capture.print,
    printErr: capture.printErr,
  });
  proxy_main = Core.cwrap('proxy_main', 'number', ['number', 'number']);
}

function mallocStr(Core, s) {
  const sz = Buffer.byteLength(s, 'utf8') + 1;
  const buf = Core._malloc(sz);
  Core.stringToUTF8(s, buf, sz);
  return buf;
}

const DEFAULT_ARGS = ['./ffmpeg', '-nostdin', '-y'];

function run(args, timeoutMs = 300000) {
  if (!Core) throw new Error('ffmpeg.core not loaded. call ensureCore() first.');
  const fullArgs = [...DEFAULT_ARGS, ...args];
  return new Promise((resolve, reject) => {
    let timer = null;
    const onTimeout = () => {
      reject(new Error('ffmpeg.run timeout'));
    };
    timer = setTimeout(onTimeout, timeoutMs);
    const argc = fullArgs.length;
    const argvPtr = Core._malloc(argc * 4);
    const bufs = fullArgs.map((s) => mallocStr(Core, s));
    bufs.forEach((b, i) => Core.setValue(argvPtr + i * 4, b, 'i32'));
    pendingBufs = bufs;
    pendingArgvPtr = argvPtr;
    runResolve = resolve;
    runReject = reject;
    proxyCode = proxy_main(argc, argvPtr);
    // If proxy_main completed synchronously and already resolved, timer cleared in handler.
  });
}

function resetLogs() { _stdout = ''; _stderr = ''; }
function getLogs() { return { stdout: _stdout, stderr: _stderr }; }

module.exports = { ensureCore, run, resetLogs, getLogs, FS: (method, ...a) => (Core ? Core.FS[method](...a) : null) };
