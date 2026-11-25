"""Run phases 0-4 in order. Idempotent; safe to re-run after an interrupted agent run."""
import subprocess, sys, os
HERE = os.path.dirname(os.path.abspath(__file__))
STEPS = ['inventory.py', 'manifest.py', 'resources.py', 'dex.py', 'elf.py', 'signing.py',
         'rea_evidence.py', 'jsbundle.py', 'assets.py', 'strings.py', 'report.py']
for s in STEPS:
    print(f'\n=== {s} ===', flush=True)
    r = subprocess.run([sys.executable, os.path.join(HERE, s)], cwd=HERE)
    if r.returncode != 0:
        print(f'FAILED: {s}'); sys.exit(r.returncode)
print('\nAll analysis phases complete.')
