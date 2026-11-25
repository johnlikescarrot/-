package vn.ai.trueid.sdk.face.present.presentation;
public final class LivenessCameraXActivity extends androidx.appcompat.app.AppCompatActivity implements vn.ai.trueid.sdk.face.y0 {
    public final ng.w a;
    public final int b;

    public LivenessCameraXActivity()
    {
        this.a = ng.x.b(vn.ai.trueid.sdk.face.h0.a);
        this.b = 1001;
        return;
    }

    public static boolean a(android.content.Context p3)
    {
        try {
            boolean v3_4 = p3.getPackageManager().getPackageInfo(p3.getPackageName(), 4096).requestedPermissions;
        } catch (Exception) {
            return 0;
        }
        if (v3_4) {
            return kotlin.collections.y.contains(v3_4, "android.permission.NFC");
        } else {
            return 0;
        }
    }

    public final void a()
    {
        vn.ai.trueid.sdk.face.a2 v0_1;
        if (vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getConfig$face_release().getLivenessType() != 1) {
            v0_1 = new vn.ai.trueid.sdk.face.a2();
        } else {
            v0_1 = new vn.ai.trueid.sdk.face.a0();
        }
        androidx.fragment.app.a v2_1 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
        v2_1.i(vn.ai.trueid.sdk.face.R$id.fragmentContainer, v0_1, 0, 1);
        v2_1.f();
        return;
    }

    public final void attachBaseContext(android.content.Context p2)
    {
        super.attachBaseContext(vn.ai.trueid.sdk.face.present.presentation.utils.LocaleHelper.INSTANCE.onAttach(p2));
        return;
    }

    public final void onBackPressed()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        this.getWindow().getDecorView().setSystemUiVisibility(4098);
        vn.ai.trueid.sdk.face.a1 v5_1 = this.getLayoutInflater().inflate(vn.ai.trueid.sdk.face.R$layout.trueid_facesdk_camerax_activity, 0, 0);
        Throwable v0_0 = vn.ai.trueid.sdk.face.R$id.fragmentContainer;
        if (((android.widget.FrameLayout) l5.s.a(v0_0, v5_1)) == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(v5_1.getResources().getResourceName(v0_0)));
        } else {
            vn.ai.trueid.sdk.face.a1 v5_6 = ((android.widget.RelativeLayout) v5_1);
            new vn.ai.trueid.sdk.face.j1(v5_6);
            this.setContentView(v5_6);
            androidx.fragment.app.a v1_5 = this.getApplication();
            if (vn.ai.trueid.sdk.face.g0.b == null) {
                try {
                    vn.ai.trueid.sdk.face.g0.b = v1_5;
                } catch (Throwable v0_3) {
                    throw v0_3;
                }
            }
            vn.ai.trueid.sdk.face.c.a.a(this.getApplication());
            if (androidx.core.content.c.a(this, "android.permission.CAMERA") == 0) {
                this.a();
                return;
            } else {
                vn.ai.trueid.sdk.face.a1 v5_12 = new vn.ai.trueid.sdk.face.a1();
                v5_12.setArguments(new android.os.Bundle());
                v5_12.b = this;
                androidx.fragment.app.a v1_10 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
                v1_10.i(v0_0, v5_12, 0, 1);
                v1_10.f();
                return;
            }
        }
    }

    public final void onDestroy()
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.removeAction();
        super.onDestroy();
        return;
    }

    public final void onPause()
    {
        super.onPause();
        if (vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity.a(this)) {
            android.nfc.NfcAdapter v0_1 = android.nfc.NfcAdapter.getDefaultAdapter(this);
            if (v0_1 != null) {
                v0_1.disableForegroundDispatch(this);
            }
        }
        return;
    }

    public final void onRequestPermissionsResult(int p8, String[] p9, int[] p10)
    {
        super.onRequestPermissionsResult(p8, p9, p10);
        if (p8 == this.b) {
            kotlin.jvm.functions.Function1 v8_6;
            if (p10.length != 0) {
                v8_6 = 0;
            } else {
                v8_6 = 1;
            }
            if ((v8_6 != null) || (p10[0] != null)) {
                kotlin.jvm.functions.Function1 v8_3 = ((vn.ai.trueid.sdk.face.f1) this.a.getValue());
                vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_1 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(0, String.valueOf(new vn.ai.trueid.sdk.face.m0("USER_CANCELED").a), 0, 0, 12, 0);
                v8_3.getClass();
                v8_3.a.invoke(v0_1);
            } else {
                this.a();
                return;
            }
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity.a(this)) {
            android.nfc.NfcAdapter v0_1 = android.nfc.NfcAdapter.getDefaultAdapter(this);
            android.app.PendingIntent v1_2 = new android.content.Intent(this, vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity);
            v1_2.addFlags(536870912);
            android.app.PendingIntent vtmp4 = android.app.PendingIntent.getActivity(this, 0, v1_2, 33554432);
            if (v0_1 != null) {
                v0_1.enableForegroundDispatch(this, vtmp4, 0, 0);
            }
        }
        return;
    }

    public final void onWindowFocusChanged(boolean p2)
    {
        super.onWindowFocusChanged(p2);
        if (p2 != null) {
            this.getWindow().getDecorView().setSystemUiVisibility(4098);
        }
        return;
    }
}
