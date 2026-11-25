/**
 * String intelligence: extraction, entropy, indicator matching and safe
 * publication helpers (redaction of credentials, keys and identifiers).
 */

export interface IndicatorHit {
  id: string;
  label: string;
  category: "hardening" | "evasion" | "crypto" | "privacy" | "network" | "supply-chain" | "storage";
  matches: number;
  samples: string[];
}

interface IndicatorRule {
  id: string;
  label: string;
  category: IndicatorHit["category"];
  patterns: RegExp[];
  maxSamples?: number;
}

export const INDICATORS: IndicatorRule[] = [
  {
    id: "root-detection", label: "Root / su detection", category: "hardening",
    patterns: [/\/system\/bin\/su\b/i, /\/system\/xbin\/su\b/i, /superuser/i, /\bmagisk\b/i, /test-keys/i, /isRooted|isDeviceRooted|checkRoot/i],
  },
  {
    id: "hook-detection", label: "Hook / instrumentation detection (Frida, Xposed)", category: "evasion",
    patterns: [/frida/i, /xposed/i, /substrate/i, /\/proc\/self\/maps/i, /TracerPid/i, /ptrace/i, /gum-js-loop/i, /gdbserver/i],
  },
  {
    id: "emulator-detection", label: "Emulator detection", category: "evasion",
    patterns: [/goldfish/i, /ranchu/i, /generic_x86/i, /genymotion/i, /bluestacks/i, /nox/i, /qemu/i],
  },
  {
    id: "integrity", label: "Signature / integrity verification", category: "hardening",
    patterns: [/getPackageInfo/i, /signingInfo|signatures/i, /SourceStamp/i, /apksig/i, /tamper/i, /integrity/i, /safemode/i],
  },
  {
    id: "pinning", label: "Certificate pinning / custom trust manager", category: "network",
    patterns: [/CertificatePinner/i, /X509TrustManager/i, /TrustManagerImpl/i, /sha256\//i, /pin[iI]ng/i, /TrustKit/i, /CertificatePinning/i],
  },
  {
    id: "cleartext", label: "Cleartext HTTP endpoints", category: "network",
    patterns: [/^http:\/\//i],
  },
  {
    id: "biometric-keystore", label: "Keystore / biometric-gated keys", category: "crypto",
    patterns: [/KeyGenParameterSpec/i, /setUserAuthenticationRequired/i, /BiometricPrompt/i, /StrongBox/i, /KeyAttestation/i],
  },
  {
    id: "whitebox-crypto", label: "White-box / obfuscated crypto", category: "crypto",
    patterns: [/whitebox|white-box|wb_aes|WBAES/i, /obfuscated.*key/i],
  },
  {
    id: "device-identifiers", label: "Hardware / advertising identifiers", category: "privacy",
    patterns: [/getDeviceId/i, /ANDROID_ID/i, /AdvertisingIdClient/i, /getMacAddress/i, /getImei|getMeid/i, /getSubscriberId/i, /getSimSerialNumber/i],
  },
  {
    id: "contacts-calendar", label: "Contacts / calendar access", category: "privacy",
    patterns: [/ContactsContract/i, /CalendarContract/i, /READ_CONTACTS/i],
  },
  {
    id: "screen-capture", label: "Screen-capture detection", category: "privacy",
    patterns: [/DETECT_SCREEN_CAPTURE/i, /FLAG_SECURE/i, /setFlags.*0x2000/i],
  },
  {
    id: "dynamic-code", label: "Dynamic code loading", category: "supply-chain",
    patterns: [/DexClassLoader/i, /InMemoryDexClassLoader/i, /PathClassLoader/i, /createPackageContext/i, /loadDex/i],
  },
  {
    id: "js-bridge", label: "WebView JavaScript bridge", category: "supply-chain",
    patterns: [/addJavascriptInterface/i, /evaluateJavascript/i, /shouldOverrideUrlLoading/i, /@JavascriptInterface/i],
  },
  {
    id: "mpaas-bridge", label: "Mini-program container JS bridge", category: "supply-chain",
    patterns: [/GriverBridge/i, /ariver/i, /TinyApp/i, /mini_program|miniProgram|miniapp/i],
  },
  {
    id: "sqlcipher", label: "Encrypted local database (SQLCipher)", category: "storage",
    patterns: [/net\.sqlcipher/i, /SQLiteDatabase.loadLibs/i, /PRAGMA key/i],
  },
  {
    id: "obfuscation-markers", label: "Obfuscation / R8 markers", category: "hardening",
    patterns: [/^com\/xq\//i, /\ba\(\)/, /^[a-z]{1,3}\/[a-z]{1,3}\//],
  },
];

export function extractAsciiStrings(buf: Buffer, min = 5, max = 4096): string[] {
  const out: string[] = [];
  let start = -1;
  for (let i = 0; i < buf.length; i++) {
    const c = buf[i];
    const printable = c >= 0x20 && c <= 0x7e;
    if (printable) {
      if (start < 0) start = i;
    } else {
      if (start >= 0 && i - start >= min && i - start <= max) out.push(buf.toString("latin1", start, i));
      start = -1;
    }
  }
  if (start >= 0 && buf.length - start >= min) out.push(buf.toString("latin1", start, buf.length));
  return out;
}

export function extractUtf16Strings(buf: Buffer, min = 6): string[] {
  const out: string[] = [];
  let run: number[] = [];
  for (let i = 0; i + 1 < buf.length; i += 2) {
    const unit = buf.readUInt16LE(i);
    if (unit >= 0x20 && unit <= 0x7e) run.push(unit);
    else {
      if (run.length >= min) out.push(String.fromCharCode(...run));
      run = [];
    }
  }
  if (run.length >= min) out.push(String.fromCharCode(...run));
  return out;
}

const URL_RE = /\b(?:https?|wss?):\/\/[a-zA-Z0-9._~:/?#\[\]@!$&'()*+,;=%-]{4,2048}/g;

export function extractUrls(texts: Iterable<string>): { url: string; count: number }[] {
  const map = new Map<string, number>();
  for (const t of texts) {
    if (t.length > 4096) continue;
    const m = t.match(URL_RE);
    if (!m) continue;
    for (let u of m) {
      u = u.replace(/[),.;'"\]]+$/, "");
      map.set(u, (map.get(u) ?? 0) + 1);
    }
  }
  return [...map.entries()].map(([url, count]) => ({ url, count })).sort((a, b) => b.count - a.count);
}

const VALID_HOST = /^[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)+$/;

export function domainOf(url: string): string | null {
  try {
    const h = new URL(url).hostname.toLowerCase();
    return VALID_HOST.test(h) ? h : null;
  } catch {
    const m = url.match(/^(?:https?|wss?):\/\/([^/:]+)/i);
    if (!m) return null;
    const h = m[1].toLowerCase();
    return VALID_HOST.test(h) ? h : null;
  }
}

export function matchIndicators(texts: Iterable<string>): IndicatorHit[] {
  const pool = [...new Set(texts)].filter((s) => s.length <= 2048);
  const hits: IndicatorHit[] = [];
  for (const rule of INDICATORS) {
    const samples = new Set<string>();
    let matches = 0;
    for (const s of pool) {
      for (const rx of rule.patterns) {
        if (rx.test(s)) {
          matches++;
          if (samples.size < (rule.maxSamples ?? 8)) samples.add(s.length > 120 ? s.slice(0, 117) + "..." : s);
          break;
        }
      }
    }
    if (matches > 0) {
      hits.push({ id: rule.id, label: rule.label, category: rule.category, matches, samples: [...samples] });
    }
  }
  return hits.sort((a, b) => b.matches - a.matches);
}

/**
 * Redaction policy for responsible publication: never publish material that
 * could be replayed, and never publish customer-identifying data.
 */
const SECRET_PATTERNS: { re: RegExp; label: string; replacement: string }[] = [
  { re: /-----BEGIN [A-Z ]+-----[\s\S]+?-----END [A-Z ]+-----/g, label: "pem", replacement: "<PEM_REDACTED>" },
  { re: /\bAIza[0-9A-Za-z_-]{35}\b/g, label: "google-api-key", replacement: "<GOOGLE_API_KEY_REDACTED>" },
  { re: /\bAKIA[0-9A-Z]{16}\b/g, label: "aws-key", replacement: "<AWS_KEY_REDACTED>" },
  { re: /\beyJ[A-Za-z0-9_-]{10,}\.[A-Za-z0-9_-]{10,}\.[A-Za-z0-9_-]{10,}\b/g, label: "jwt", replacement: "<JWT_REDACTED>" },
  { re: /\b[0-9a-f]{32,128}\b/g, label: "hex-blob", replacement: "<HEX_REDACTED>" },
];

export function redact(text: string): string {
  let out = text;
  for (const p of SECRET_PATTERNS) out = out.replace(p.re, p.replacement);
  return out;
}

export function findSensitive(texts: Iterable<string>): { label: string; count: number; samples: string[] }[] {
  const found = new Map<string, { count: number; samples: Set<string> }>();
  for (const t of texts) {
    if (t.length > 8192) continue;
    for (const p of SECRET_PATTERNS) {
      if (!p.re.test(t)) { p.re.lastIndex = 0; continue; }
      p.re.lastIndex = 0;
      const cur = found.get(p.label) ?? { count: 0, samples: new Set<string>() };
      cur.count++;
      if (cur.samples.size < 4) cur.samples.add(redact(t.length > 100 ? t.slice(0, 97) + "..." : t));
      found.set(p.label, cur);
    }
  }
  return [...found.entries()].map(([label, v]) => ({ label, count: v.count, samples: [...v.samples] }));
}

/** Shannon entropy of an identifier string, used for obfuscation metrics. */
export function identifierEntropy(s: string): number {
  if (!s) return 0;
  const hist = new Map<string, number>();
  for (const ch of s) hist.set(ch, (hist.get(ch) ?? 0) + 1);
  let h = 0;
  for (const c of hist.values()) { const p = c / s.length; h -= p * Math.log2(p); }
  return h;
}

/**
 * Heuristic eTLD+1 roll-up. We deliberately avoid shipping a full Public
 * Suffix List: the two-label heuristic is accurate for the domains observed in
 * this artifact and is documented as a limitation in the paper.
 */
const MULTI_PART_TLDS = new Set(["com.vn", "co.uk", "com.sg", "co.id", "com.my", "com.ph", "co.th", "com.tw", "com.hk", "co.jp", "com.cn", "com.au", "com.br"]);

export function registrableDomain(host: string): string {
  const parts = host.split(".");
  if (parts.length <= 2) return host;
  const lastTwo = parts.slice(-2).join(".");
  if (MULTI_PART_TLDS.has(lastTwo) && parts.length >= 3) return parts.slice(-3).join(".");
  return lastTwo;
}
