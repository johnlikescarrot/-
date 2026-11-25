# Method — REA + classic Android RE toolchain

Primary framework: **REA — Reverse Engineer Anything** (https://github.com/morluto/rea, npm `rea-agents`).
REA provides an MCP/CLI bridge to analysis engines (Jadx for Android, Ghidra/Hopper/IDA/Kuna for native).
We use REA's evidence contract: every conclusion records *evidence* and *limitations*.

Complementary tools (all local, static-only):
- Python `zipfile` + custom AXML/ARSC parsers (triage without heavy deps)
- `androguard` (manifest, permissions, components, DEX)
- Jadx (DEX→Java decompilation, sampled — full decompile of ~60MB DEX is heavy; we decompile targeted packages)
- Apktool (resource decoding, sampled)
- `strings`/regex mining over DEX + `.so` + assets for endpoints, SDKs, crypto APIs
- ELF parsing of native libs (readelf/objdump if available, else Python ELF parser)

No dynamic execution of the target, no network calls to Zalopay servers, no secret extraction.
