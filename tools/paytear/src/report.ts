/** Report assembly helpers: markdown tables and deterministic JSON serialisation. */

export function mdTable(headers: string[], rows: (string | number)[][]): string {
  const head = `| ${headers.join(" | ")} |`;
  const sep = `| ${headers.map(() => "---").join(" | ")} |`;
  const body = rows.map((r) => `| ${r.map((c) => String(c).replace(/\|/g, "\\|")).join(" | ")} |`).join("\n");
  return `${head}\n${sep}\n${body}`;
}

export function fmtBytes(n: number): string {
  if (n < 1024) return `${n} B`;
  if (n < 1024 * 1024) return `${(n / 1024).toFixed(1)} KiB`;
  if (n < 1024 * 1024 * 1024) return `${(n / (1024 * 1024)).toFixed(2)} MiB`;
  return `${(n / (1024 * 1024 * 1024)).toFixed(2)} GiB`;
}

export function fmtNum(n: number): string {
  return n.toLocaleString("en-US");
}

export function pct(a: number, b: number, digits = 1): string {
  if (!b) return "0%";
  return `${((100 * a) / b).toFixed(digits)}%`;
}

export function stableJson(value: unknown): string {
  return JSON.stringify(value, (_k, v) => {
    if (v && typeof v === "object" && !Array.isArray(v)) {
      return Object.fromEntries(Object.entries(v as Record<string, unknown>).sort(([a], [b]) => a.localeCompare(b)));
    }
    return v;
  }, 2);
}
