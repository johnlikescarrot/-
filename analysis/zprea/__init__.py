"""zprea — a dependency-light static reverse-engineering toolkit for Android app bundles.

The toolkit deliberately avoids the JDK-based toolchain (apktool / jadx / dex2jar).
Instead it re-implements the container formats it needs directly on top of the
published specifications:

* DEX  — `zprea.dex` (Dalvik Executable header + string/type/method/class tables)
* APK Signing Block — `zprea.signing` (v1 / v2 / v3 / v3.1 / v4 scheme detection)
* ELF  — `zprea.elfprobe` (via pyelftools: JNI exports, imports, section entropy)
* AXML — `zprea.manifest` (via pyaxmlparser)

Design constraint: the pipeline must run in a sandbox with no Java runtime and
no network access to third-party services, and it must terminate in minutes, not
hours, on a 100 MB multi-dex sample.
"""

__version__ = "1.0.0"
__all__ = ["dex", "signing", "elfprobe", "manifest", "fingerprints", "pipeline"]
