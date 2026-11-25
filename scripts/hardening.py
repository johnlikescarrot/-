#!/usr/bin/env python3
"""ELF hardening + symbol/import census for native libraries of the ABI split.
Reads .so files from a directory given on argv[1] (default /tmp/nat)."""
import json, os, re, subprocess, sys
SRC = sys.argv[1] if len(sys.argv) > 1 else "/tmp/nat"
OUT = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "research", "native-hardening.json")

def run(cmd):
    try:
        return subprocess.run(cmd, capture_output=True, text=True, timeout=120).stdout
    except Exception:
        return ""

rows = {}
for f in sorted(os.listdir(SRC)):
    if not f.endswith(".so"):
        continue
    p = os.path.join(SRC, f)
    dyn = run(["readelf", "-dW", p])
    hdr = run(["readelf", "-hW", p])
    seg = run(["readelf", "-lW", p])
    syms = run(["readelf", "-sW", p])
    types = re.findall(r"Type:\s+(\S+)", hdr)
    machine = re.findall(r"Machine:\s+(.+)", hdr)
    flags = {
        "pie": "DYN (" in seg,
        "nx_stack": "GNU_STACK" in seg and "RWE" not in re.sub(r"\s+", " ", seg),
        "relro": "GNU_RELRO" in seg,
        "bind_now": "BIND_NOW" in dyn or "NOW" in dyn,
        "canary": "__stack_chk_fail" in syms,
        "fortify": "_chk" in syms,
        "textrel": "TEXTREL" in dyn,
    }
    funcs = len([l for l in syms.splitlines() if re.search(r"\bFUNC\b", l)])
    imports = sorted(set(re.findall(r"UND\s+(\S+)", syms)))
    jni = sorted(set(re.findall(r"(Java_[A-Za-z0-9_]+)", syms)))
    rows[f] = {
        "type": types[0] if types else None,
        "machine": machine[0].strip() if machine else None,
        "size": os.path.getsize(p),
        "flags": flags,
        "function_symbols": funcs,
        "jni_exports": jni,
        "imports_sample": imports[:25],
        "notable_imports": [i for i in imports if re.search(r"ptrace|dlopen|dlsym|fork|exec|system|popen|socket|connect|SSL|AES|EVP|sqlite3_key|__android_log", i)][:20],
    }
    print(f, json.dumps(flags), "jni=%d" % len(jni))
json.dump(rows, open(OUT, "w"), indent=1)
print("wrote", OUT)
