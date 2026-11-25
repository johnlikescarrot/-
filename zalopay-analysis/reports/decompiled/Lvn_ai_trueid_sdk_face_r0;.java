package vn.ai.trueid.sdk.face;
public final class r0 extends androidx.lifecycle.ViewModel {
    public final vn.ai.trueid.sdk.face.c1 a;
    public vn.ai.trueid.sdk.face.k1 b;

    public r0(vn.ai.trueid.sdk.face.c1 p1, vn.ai.trueid.sdk.face.q0 p2, vn.ai.trueid.sdk.face.c0 p3)
    {
        vn.ai.trueid.sdk.face.t0 v2_1 = new vn.ai.trueid.sdk.face.t0();
        new androidx.lifecycle.T(v2_1);
        this.a = p1;
        new java.util.LinkedList();
        new java.util.LinkedList();
        kotlin.collections.E.emptyList();
        new androidx.lifecycle.T();
        new androidx.lifecycle.T();
        new androidx.lifecycle.T();
        new androidx.lifecycle.T();
        new androidx.lifecycle.T();
        new androidx.lifecycle.T();
        new androidx.lifecycle.T();
        new androidx.lifecycle.T();
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.r0 p3)
    {
        boolean v0_0 = p3.b;
        if (v0_0) {
            boolean v0_4 = v0_0.i;
            if (v0_4) {
                v0_4.m = "";
                v0_4.invalidate();
            }
        }
        boolean v0_6 = p3.b;
        if (v0_6) {
            boolean v0_1 = v0_6.i;
            if (v0_1) {
                v0_1.l = 0;
                v0_1.v = 0;
                v0_1.invalidate();
            }
        }
        boolean v0_2 = p3.b;
        if (v0_2) {
            boolean v0_3 = v0_2.c;
            if ((v0_3) && (v0_3.isEnabled() == 1)) {
                int v3_2;
                int v3_1 = p3.b;
                if (v3_1 == 0) {
                    v3_2 = 0;
                } else {
                    v3_2 = v3_1.c;
                }
                if (v3_2 != 0) {
                    v3_2.setVisibility(0);
                } else {
                    return;
                }
            }
        }
        return;
    }

    public final void a()
    {
        vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_0 = this.b;
        if (v0_0 != null) {
            vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_1 = v0_0.i;
            if (v0_1 != null) {
                v0_1.post(new tZ.a(3, this));
            }
        }
        return;
    }

    public final void a(java.util.ArrayList p5, java.util.ArrayList p6)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK v0_0 = this.b;
        if (v0_0 != null) {
            vn.ai.trueid.sdk.face.navigation.FaceSDK v0_3 = v0_0.i;
            if (v0_3 != null) {
                v0_3.m = vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(this.a.a, "facesdk_uploading_encrypt", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
                v0_3.invalidate();
            }
        }
        vn.ai.trueid.sdk.face.navigation.FaceSDK v0_2;
        vn.ai.trueid.sdk.face.navigation.FaceSDK v0_1 = this.b;
        if (v0_1 == null) {
            v0_2 = 0;
        } else {
            v0_2 = v0_1.c;
        }
        if (v0_2 != null) {
            v0_2.setVisibility(8);
        }
        vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.postResultCallback$face_release(new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(1, "", p5, p6));
        return;
    }

    public final void b()
    {
        this.a();
        vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_0 = this.b;
        if (v0_0 != null) {
            vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_1 = v0_0.i;
            if (v0_1 != null) {
                int v1_1 = v0_1.c;
                if (v1_1 != 0) {
                    v1_1.setColor(android.graphics.Color.parseColor(v0_1.p));
                }
                v0_1.invalidate();
                v0_1.l = 1;
                v0_1.invalidate();
            }
        }
        return;
    }
}
