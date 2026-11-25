"""zalopay_re - a dependency-free-ish static analysis toolkit for the
ZaloPay 11.17.0 (vn.com.vng.zalopay) Android App Bundle delivered as an
APKPure XAPK container.

The package is organised as a set of *parsers* (dex, elf, apksig, axml,
arsc, container) plus *analyses* (inventory, intel, findings, report) that
turn the parsers' output into machine-readable evidence under ``data/``.

Design constraints
------------------
* Everything must be reproducible from the committed container alone:
  ``python -m zalopay_re run``.
* No dynamic execution of the target, no network access, no exploitation.
  This is *static* analysis of a publicly distributed artifact.
* Aggressive memory discipline: the DEX payload is ~82 MB across 20 files,
  each parsed once, streamed into aggregates, then dropped.
"""

__version__ = "1.0.0"
__all__ = ["dex", "elf", "apksig", "container", "inventory", "intel", "findings"]
