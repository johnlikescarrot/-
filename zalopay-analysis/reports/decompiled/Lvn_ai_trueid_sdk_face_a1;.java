package vn.ai.trueid.sdk.face;
public final class a1 extends androidx.fragment.app.Fragment {
    public vn.ai.trueid.sdk.face.l1 a;
    public vn.ai.trueid.sdk.face.y0 b;
    public final ng.w c;

    public a1()
    {
        super(vn.ai.trueid.sdk.face.R$layout.trueid_facesdk_fragment_permission);
        super.c = ng.x.b(vn.ai.trueid.sdk.face.z0.a);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a1 p7, android.view.View p8)
    {
        kotlin.jvm.functions.Function1 v8_3 = ((vn.ai.trueid.sdk.face.f1) p7.c.getValue());
        vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_0 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(0, String.valueOf(new vn.ai.trueid.sdk.face.m0("USER_CANCELED").a), 0, 0, 12, 0);
        v8_3.getClass();
        v8_3.a.invoke(v0_0);
        p7.requireActivity().finish();
        return;
    }

    public static final void b(vn.ai.trueid.sdk.face.a1 p1, android.view.View p2)
    {
        p1.requestPermissions(vn.ai.trueid.sdk.face.b1.a, 10);
        return;
    }

    public final void a()
    {
        int v2_2;
        android.widget.Button v0_1 = this.a.f;
        vn.ai.trueid.sdk.face.B v1_6 = this.getString(vn.ai.trueid.sdk.face.R$string.facesdk_access_camera_request);
        int v2_7 = this.getContext();
        if (v2_7 == 0) {
            v2_2 = 0;
        } else {
            v2_2 = v2_7.getApplicationInfo().loadLabel(v2_7.getPackageManager()).toString();
        }
        v0_1.setText(String.format(v1_6, java.util.Arrays.copyOf(new Object[] {v2_2}), 1)));
        this.a.d.setOnClickListener(new vn.ai.trueid.sdk.face.B(this, 0));
        this.a.c.setOnClickListener(new vn.ai.trueid.sdk.face.B(this, 1));
        return;
    }

    public final void onAttach(android.content.Context p1)
    {
        super.onAttach(p1);
        this.b = ((vn.ai.trueid.sdk.face.y0) p1);
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p9, android.view.ViewGroup p10, android.os.Bundle p11)
    {
        String v9_1 = p9.inflate(vn.ai.trueid.sdk.face.R$layout.trueid_facesdk_fragment_permission, p10, 0);
        android.widget.RelativeLayout v1_1 = ((android.widget.RelativeLayout) v9_1);
        int v10_1 = vn.ai.trueid.sdk.face.R$id.btnAccept;
        android.widget.Button v3_0 = ((android.widget.Button) l5.s.a(v10_1, v9_1));
        if (v3_0 != null) {
            v10_1 = vn.ai.trueid.sdk.face.R$id.btnBack;
            android.widget.ImageView v4_1 = ((android.widget.ImageView) l5.s.a(v10_1, v9_1));
            if (v4_1 != null) {
                v10_1 = vn.ai.trueid.sdk.face.R$id.toolbarText;
                android.widget.TextView v5_1 = ((android.widget.TextView) l5.s.a(v10_1, v9_1));
                if (v5_1 != null) {
                    v10_1 = vn.ai.trueid.sdk.face.R$id.txtRequestAccess;
                    android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(v10_1, v9_1));
                    if (v6_1 != null) {
                        v10_1 = vn.ai.trueid.sdk.face.R$id.txtSub;
                        android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(v10_1, v9_1));
                        if (v7_1 != null) {
                            this.a = new vn.ai.trueid.sdk.face.l1(v1_1, v1_1, v3_0, v4_1, v5_1, v6_1, v7_1);
                            return v1_1;
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v9_1.getResources().getResourceName(v10_1)));
    }

    public final void onRequestPermissionsResult(int p8, String[] p9, int[] p10)
    {
        super.onRequestPermissionsResult(p8, p9, p10);
        if (p8 == 10) {
            kotlin.jvm.functions.Function1 v8_9 = kotlin.collections.y.firstOrNull(p10);
            if ((v8_9 == null) || (v8_9.intValue() != 0)) {
                android.widget.Toast.makeText(this.getContext(), "Permission request denied", 1).show();
                kotlin.jvm.functions.Function1 v8_15 = ((vn.ai.trueid.sdk.face.f1) this.c.getValue());
                vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_5 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(306, String.valueOf(new vn.ai.trueid.sdk.face.l0("PERMISSION_DENIED").a), 0, 0, 12, 0);
                v8_15.getClass();
                v8_15.a.invoke(v0_5);
                kotlin.jvm.functions.Function1 v8_17 = this.b;
                if (v8_17 != null) {
                    kotlin.jvm.functions.Function1 v8_18 = ((vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity) v8_17);
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(Boolean.FALSE, Boolean.TRUE)) {
                        kotlin.jvm.functions.Function1 v8_21 = ((vn.ai.trueid.sdk.face.f1) v8_18.a.getValue());
                        vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_8 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(306, String.valueOf(new vn.ai.trueid.sdk.face.l0("PERMISSION_DENIED").a), 0, 0, 12, 0);
                        v8_21.getClass();
                        v8_21.a.invoke(v0_8);
                    } else {
                        v8_18.a();
                    }
                }
                this.requireActivity().finish();
            } else {
                android.widget.Toast.makeText(this.getContext(), "Permission request granted", 1).show();
                kotlin.jvm.functions.Function1 v8_4 = this.b;
                if (v8_4 != null) {
                    kotlin.jvm.functions.Function1 v8_5 = ((vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity) v8_4);
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(Boolean.TRUE, Boolean.TRUE)) {
                        kotlin.jvm.functions.Function1 v8_8 = ((vn.ai.trueid.sdk.face.f1) v8_5.a.getValue());
                        vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_2 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(306, String.valueOf(new vn.ai.trueid.sdk.face.l0("PERMISSION_DENIED").a), 0, 0, 12, 0);
                        v8_8.getClass();
                        v8_8.a.invoke(v0_2);
                        return;
                    } else {
                        v8_5.a();
                        return;
                    }
                }
            }
        }
        return;
    }

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        Object[] v1_6;
        super.onViewCreated(p4, p5);
        this.a();
        org.json.JSONObject v4_2 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
        android.widget.Button v5_18 = v4_2.getConfig$face_release().getCloseColor();
        if (!v4_2.getConfig$face_release().isCancelable()) {
            v1_6 = 4;
        } else {
            v1_6 = 0;
        }
        this.a.d.setVisibility(v1_6);
        this.a.d.setColorFilter(android.graphics.Color.parseColor(v5_18), android.graphics.PorterDuff$Mode.SRC_IN);
        android.widget.Button v5_16 = v4_2.getConfig$face_release().getTextColor();
        this.a.e.setTextColor(android.graphics.Color.parseColor(v5_16));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.a.e, v4_2.getConfig$face_release().getFontName(), ((float) v4_2.getConfig$face_release().getTextSizeTitle()));
        this.a.g.setTextColor(android.graphics.Color.parseColor(v5_16));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.a.g, v4_2.getConfig$face_release().getFontName(), ((float) v4_2.getConfig$face_release().getTextSize()));
        this.a.f.setTextColor(android.graphics.Color.parseColor(v5_16));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.a.f, v4_2.getConfig$face_release().getFontName(), ((float) v4_2.getConfig$face_release().getTextSize()));
        android.widget.Button v5_23 = vn.ai.trueid.sdk.face.i.LEFT.toString();
        String v0_32 = new android.widget.RelativeLayout$LayoutParams(-2, -1);
        if (kotlin.jvm.internal.Intrinsics.areEqual(v5_23, vn.ai.trueid.sdk.face.i.RIGHT.toString())) {
            v0_32.addRule(21);
            this.a.d.setLayoutParams(v0_32);
        }
        vn.ai.trueid.sdk.face.e0.a(android.graphics.Color.parseColor(v4_2.getConfig$face_release().getPrimaryColor()), this.a.c);
        android.widget.Button v5_3 = v4_2.getConfig$face_release().getBackgroundColor();
        this.a.c.setTextColor(android.graphics.Color.parseColor(v4_2.getConfig$face_release().getTextButtonColor()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.a.c, v4_2.getConfig$face_release().getFontName(), ((float) v4_2.getConfig$face_release().getTextSizeButton()));
        this.a.b.setBackgroundColor(android.graphics.Color.parseColor(v5_3));
        if (v4_2.getLanguageJSON() != null) {
            Object[] v1_11;
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.a.e, "facesdk_access_camera", v4_2.getLanguageJSON());
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.a.g, "facesdk_access_camera_description", v4_2.getLanguageJSON());
            android.widget.Button v5_11 = this.a.f;
            String v0_12 = vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v5_11.getContext(), "facesdk_access_camera_request", v4_2.getLanguageJSON());
            Object[] v1_10 = this.getContext();
            if (v1_10 == null) {
                v1_11 = 0;
            } else {
                v1_11 = v1_10.getApplicationInfo().loadLabel(v1_10.getPackageManager()).toString();
            }
            v5_11.setText(String.format(v0_12, java.util.Arrays.copyOf(new Object[] {v1_11}), 1)));
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.a.c, "facesdk_accept_button", v4_2.getLanguageJSON());
        }
        return;
    }
}
