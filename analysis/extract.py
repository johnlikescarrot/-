#!/usr/bin/env python3
"""Stage 1: ZIP -> XAPK -> APK splits + chain of custody (read-only wrt source ZIP)."""
import hashlib, json, pathlib, sys, zipfile

ROOT = pathlib.Path(__file__).resolve().parent.parent
OUT = ROOT / "extracted"
REPORTS = ROOT / "reports"

def sha256(p: pathlib.Path) -> str:
    h = hashlib.sha256()
    with open(p, "rb") as f:
        for b in iter(lambda: f.read(1 << 20), b""):
            h.update(b)
    return h.hexdigest()

def main() -> None:
    zips = list(ROOT.glob("*.zip"))
    if not zips:
        print("No source ZIP found", file=sys.stderr); sys.exit(1)
    src = zips[0]
    print(f"Source ZIP: {src.name} ({src.stat().st_size/1024/1024:.2f} MB)")
    OUT.mkdir(exist_ok=True); REPORTS.mkdir(exist_ok=True)
    custody = {"source_zip": {"name": src.name, "bytes": src.stat().st_size, "sha256": sha256(src)}, "inner": []}
    with zipfile.ZipFile(src) as z:
        infos = z.infolist()
        assert len(infos) >= 1, "empty zip"
        for i in infos:
            print(f"  inner: {i.filename} ({i.file_size/1024/1024:.2f} MB)")
            data = z.read(i.filename)
            ihash = hashlib.sha256(data).hexdigest()
            rec = {"name": i.filename, "bytes": len(data), "sha256": ihash, "contains": []}
            # If inner is itself a zip (XAPK), unpack one level into extracted/
            if i.filename.lower().endswith((".xapk", ".zip", ".apk")):
                try:
                    zx = zipfile.ZipFile(__import__("io").BytesIO(data))
                    for j in zx.infolist():
                        target = OUT / j.filename
                        if j.is_dir():
                            target.mkdir(parents=True, exist_ok=True); continue
                        target.parent.mkdir(parents=True, exist_ok=True)
                        jb = zx.read(j.filename)
                        target.write_bytes(jb)
                        rec["contains"].append({"name": j.filename, "bytes": len(jb),
                                                "sha256": hashlib.sha256(jb).hexdigest()})
                    print(f"    unpacked {len(rec['contains'])} entries -> extracted/")
                except zipfile.BadZipFile:
                    (OUT / i.filename).write_bytes(data)
            else:
                (OUT / i.filename).write_bytes(data)
            custody["inner"].append(rec)
    (REPORTS / "chain_of_custody.json").write_text(json.dumps(custody, indent=2, ensure_ascii=False))
    # copy xapk manifest for the record (small + safe)
    for c in custody["inner"]:
        for f in c["contains"]:
            if f["name"] == "manifest.json":
                import shutil
                shutil.copy(OUT / "manifest.json", REPORTS / "xapk_manifest.json")
    print("Wrote reports/chain_of_custody.json")
    print("extracted/:", sorted(p.name for p in OUT.iterdir()))

if __name__ == "__main__":
    main()
