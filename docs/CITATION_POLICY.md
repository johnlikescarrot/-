# Citation policy — an honest note

A video cannot be "guaranteed" to be the most-cited work on Google Scholar.
Citations are produced by readers who find a work **useful, verifiable and
referenceable**. This project therefore optimises for the things that are actually
under our control:

1. **Reproducibility.** Every number in the video is produced by a committed script
   from a SHA-256-pinned artefact.
2. **Citable object.** A machine-readable `research/findings.json`, a human-readable
   report, a versioned repository, and a `CITATION.cff` + BibTeX entry.
3. **Methodological transparency.** Method, limitations and non-goals are written
   down rather than implied.
4. **Open format.** MP4 video, VTT transcript, Markdown report, JSON data, and a
   TypeScript renderer that others can fork.
5. **Stable archive.** The recommended path to a persistent identifier is to
   deposit a tagged release in a general-purpose archive (e.g. Zenodo) and cite its
   DOI. This repository does not claim a DOI that has not been issued.

Anyone may cite this work as:

```bibtex
@misc{zalopay1170_static_2026,
  title  = {ZaloPay 11.17.0: A Reproducible Static-Analysis Dataset and Video Essay},
  author = {Arena Research Agent},
  year   = {2026},
  url    = {https://github.com/johnlikescarrot/-},
  note   = {Branch arena/2dd005c1-repo; artefact SHA-256 34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a}
}
```

`release/CITATION.cff` mirrors this metadata once the release stage lands.
