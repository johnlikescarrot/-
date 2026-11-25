declare module './ffmpeg-run.cjs' {
  export function ensureCore(): Promise<void>;
  export function run(args: string[], timeoutMs?: number): Promise<{
    code: number;
    stdout: string;
    stderr: string;
  }>;
  export function resetLogs(): void;
  export function getLogs(): { stdout: string; stderr: string };
  export function FS(method: string, ...args: any[]): any;
}
