package vn.ai.trueid.sdk.facev3.present.presentation;
public final class LivenessCameraXActivity extends androidx.appcompat.app.AppCompatActivity implements Bk.C$a {
    public final ng.S i;
    public final int j;

    public LivenessCameraXActivity()
    {
        this.i = ng.x.b(Ak.a.d);
        this.j = 1001;
        return;
    }

    public final void attachBaseContext(android.content.Context p4)
    {
        super.attachBaseContext(com.google.android.gms.internal.mlkit_vision_barcode.R4.a(p4, android.preference.PreferenceManager.getDefaultSharedPreferences(p4).getString("Locale.Helper.Selected.Language", java.util.Locale.getDefault().getLanguage())));
        return;
    }

    public final void e1()
    {
        Bk.z v0_0 = wk.a.c;
        if (v0_0 == null) {
            v0_0 = new yk.b("#26B840", "#42526E", "#26B840", 0, "#FF6234", "#000000", "#253858", "#253858", "#FFFFFF", 0, 1, 1, "", 0, 0, 0, 0, 0, 0, 8126472);
        }
        Bk.z v0_3;
        Bk.z v0_1 = v0_0.r;
        if (v0_1 == 1) {
            v0_3 = new Bk.o();
        } else {
            if ((v0_1 == 2) || (v0_1 == 3)) {
                v0_3 = new Bk.s();
            } else {
                v0_3 = new Bk.G();
            }
        }
        if (vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.isZlpSupported$trueid_release()) {
            v0_3 = new Bk.z();
        }
        androidx.fragment.app.a v2_5 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
        v2_5.i(2131362460, v0_3, 0, 1);
        v2_5.f();
        return;
    }

    public final void onBackPressed()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.setSessionActive$trueid_release(1);
        this.getWindow().getDecorView().setSystemUiVisibility(4098);
        Bk.C v5_3 = this.getLayoutInflater().inflate(2131560349, 0, 0);
        if (v5_3 == null) {
            throw new NullPointerException("rootView");
        } else {
            this.setContentView(((android.widget.FrameLayout) v5_3));
            androidx.fragment.app.a v1_1 = this.getApplication();
            if (xk.b.b == null) {
                try {
                    xk.b.b = v1_1;
                } catch (Throwable v0_2) {
                    throw v0_2;
                }
            }
            androidx.fragment.app.a v1_2 = this.getApplication();
            if (ok.b.b == null) {
                try {
                    ok.b.b = v1_2;
                } catch (Throwable v0_3) {
                    throw v0_3;
                }
            }
            if (androidx.core.content.c.a(this, "android.permission.CAMERA") == 0) {
                this.e1();
                return;
            } else {
                Bk.C v5_10 = new Bk.C();
                v5_10.setArguments(new android.os.Bundle());
                v5_10.s = this;
                androidx.fragment.app.a v1_6 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
                v1_6.i(2131362460, v5_10, 0, 1);
                v1_6.f();
                return;
            }
        }
    }

    public final void onDestroy()
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.releaseSession$trueid_release();
        wk.a.h = 0;
        wk.a.j = 0;
        wk.a.k = 0;
        super.onDestroy();
        return;
    }

    public final void onPause()
    {
        super.onPause();
        try {
            android.nfc.NfcAdapter v0_4 = this.getPackageManager().getPackageInfo(this.getPackageName(), 4096).requestedPermissions;
        } catch (Exception) {
            android.nfc.NfcAdapter v0_0 = 0;
            if (v0_0 != null) {
                android.nfc.NfcAdapter v0_1 = android.nfc.NfcAdapter.getDefaultAdapter(this);
                if (v0_1 != null) {
                    v0_1.disableForegroundDispatch(this);
                }
            }
            return;
        }
        if (v0_4 != null) {
            v0_0 = kotlin.collections.y.contains(v0_4, "android.permission.NFC");
        } else {
        }
    }

    public final void onRequestPermissionsResult(int p7, String[] p8, int[] p9)
    {
        super.onRequestPermissionsResult(p7, p8, p9);
        if (p7 == this.j) {
            if ((p9.length == 0) || (p9[0] != null)) {
                ((vk.c) this.i.getValue()).a(new zk.a(0, String.valueOf(new vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$UserCanceledException(0).a), 0, 0, 28));
            } else {
                this.e1();
                return;
            }
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        try {
            android.nfc.NfcAdapter v1_4 = this.getPackageManager().getPackageInfo(this.getPackageName(), 4096).requestedPermissions;
        } catch (Exception) {
            android.nfc.NfcAdapter v1_0 = 0;
            if (v1_0 != null) {
                android.os.Bundle v3_3;
                android.nfc.NfcAdapter v1_1 = android.nfc.NfcAdapter.getDefaultAdapter(this);
                android.content.Intent v2_2 = new android.content.Intent(this, vn.ai.trueid.sdk.facev3.present.presentation.LivenessCameraXActivity);
                v2_2.addFlags(536870912);
                android.os.Bundle v3_2 = android.os.Build$VERSION.SDK_INT;
                if (v3_2 < 34) {
                    v3_3 = 0;
                } else {
                    int v4_1 = android.app.ActivityOptions.makeBasic();
                    if (v3_2 < 35) {
                        v4_1.setPendingIntentBackgroundActivityStartMode(1);
                    } else {
                        v4_1.setPendingIntentCreatorBackgroundActivityStartMode(1);
                    }
                    v3_3 = v4_1.toBundle();
                }
                android.app.PendingIntent vtmp10 = android.app.PendingIntent.getActivity(this, 0, v2_2, 33554432, v3_3);
                if (v1_1 != null) {
                    v1_1.enableForegroundDispatch(this, vtmp10, 0, 0);
                }
            }
            return;
        }
        if (v1_4 != null) {
            v1_0 = kotlin.collections.y.contains(v1_4, "android.permission.NFC");
        } else {
        }
    }

    public final void onWindowFocusChanged(boolean p2)
    {
        super.onWindowFocusChanged(p2);
        if (p2 != null) {
            this.getWindow().getDecorView().setSystemUiVisibility(4098);
        }
        return;
    }

    public final void x0(Boolean p7)
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p7, Boolean.TRUE)) {
            ((vk.c) this.i.getValue()).a(new zk.a(306, String.valueOf(new vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$PermissionDenied(0).a), 0, 0, 28));
            return;
        } else {
            this.e1();
            return;
        }
    }
}
