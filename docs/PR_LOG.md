# PR / checkpoint log

| PR | State | Head | Base | Contents |
| --- | --- | --- | --- | --- |
| [#9](https://github.com/johnlikescarrot/-/pull/9) | OPEN | `arena/e160031d-repo` | `main` | The single, continuous checkpoint channel for this session. |

## Pushed commits (each one synchronised into PR #9)

| Phase | Commit subject | Evidence added |
| --- | --- | --- |
| 0 | `phase0: corpus inventory of all 6,472 nested ZIP members + evidence model scaffold` | `inventory.json`, `inventory_summary.json`, `docs/*`, `README.md` |
| 1 | `phase1: AXML + resources.arsc readers; 5319 binary XMLs, 5 manifests, 13 resource packages` | `manifest.json`, `resources.json`, `formats.py`, `manifest.py`, `resources.py` |
| 2 | `phase2: DEX structural parser; 21 DEX files, checksum+SHA1 verified, class-data/code-item walk` | `dex.json`, `supply_chain.json`, `dex.py` |
| 3 | `phase3: ELF + APK signing blocks + real REA CLI evidence + Metro bundle` | `elf.json`, `signing.json`, `rea_javascript.json`, `nested.json`, `jsbundle.json` |
| 4 | `phase4: asset/config/string statistics with redaction + unified report` | `assets.json`, `strings.json`, `report.json`, `library_versions.json` |
| 5 | `phase5: citable preprint, citation metadata, captions & narration script` | `paper/*`, `media/script.md`, `media/subtitles.*` |
| 6 | `phase6: TypeScript film engine (rasteriser, scenes, audio synthesis, muxer)` | `tools/film/*` |
| 7 | `phase7: final MP4 + poster + QC` | `media/zalopay-rea.mp4`, `media/poster.png`, `docs/QC.md` |

## Why one PR, not several

GitHub accepts only one **open** pull request for a given `(head, base)` pair, and this
session is bound to exactly one head branch (`arena/e160031d-repo`). Creating, switching or
pushing other branches is forbidden by the session contract. The robust way to satisfy
"all operations synchronised into PRs" is therefore to open the PR at phase 0 and push every
phase as a commit: the PR is a complete, ordered, always-up-to-date record of the entire run.
If the agent is interrupted by a tool-call limit, the branch on GitHub already contains all
completed phases and the next run resumes at the next phase. PRs are deliberately **not merged**.
