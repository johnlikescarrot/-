package vn.ai.trueid.sdk.face;
public final class a2 extends androidx.fragment.app.Fragment implements vn.ai.trueid.sdk.face.u0 {
    public static final synthetic int q;
    public long a;
    public final String b;
    public vn.ai.trueid.sdk.face.k1 c;
    public String d;
    public final java.util.LinkedHashMap e;
    public final java.util.ArrayList f;
    public final java.util.ArrayList g;
    public final java.util.ArrayList h;
    public final ng.w i;
    public final ng.w j;
    public final ng.w k;
    public java.util.concurrent.ExecutorService l;
    public com.google.common.util.concurrent.ListenableFuture m;
    public vn.ai.trueid.sdk.face.b2 n;
    public int o;
    public final ng.w p;

    public a2()
    {
        super(vn.ai.trueid.sdk.face.R$layout.trueid_facesdk_fragment_camerax);
        super.b = "LivenessViewModel";
        super.e = new java.util.LinkedHashMap();
        super.f = new java.util.ArrayList();
        super.g = new java.util.ArrayList();
        super.h = new java.util.ArrayList();
        super.i = ng.x.b(vn.ai.trueid.sdk.face.t1.a);
        super.j = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(super, kotlin.jvm.internal.M.a.b(vn.ai.trueid.sdk.face.r0), new vn.ai.trueid.sdk.face.z1(new vn.ai.trueid.sdk.face.y1(super)), vn.ai.trueid.sdk.face.s1.a);
        super.k = ng.x.b(vn.ai.trueid.sdk.face.r1.a);
        super.n = vn.ai.trueid.sdk.face.b2.IN;
        super.p = ng.x.b(vn.ai.trueid.sdk.face.p1.a);
        return;
    }

    public static final void a(kotlin.jvm.functions.Function1 p0, Object p1)
    {
        p0.invoke(p1);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p8)
    {
        p8.c().b();
        androidx.fragment.app.FragmentActivity v0_1 = p8.getActivity();
        if (v0_1 != null) {
            kotlin.jvm.functions.Function1 v8_4 = ((vn.ai.trueid.sdk.face.f1) p8.i.getValue());
            vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v1_1 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(vn.ai.trueid.sdk.face.k.TIME_OUT.getCode(), "TIME_OUT", 0, 0, 12, 0);
            v8_4.getClass();
            v8_4.a.invoke(v1_1);
            v0_1.finish();
        }
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p7, android.view.View p8)
    {
        p8 = p7.getActivity();
        if (p8 != null) {
            kotlin.jvm.functions.Function1 v7_4 = ((vn.ai.trueid.sdk.face.f1) p7.i.getValue());
            vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_0 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(0, String.valueOf(new vn.ai.trueid.sdk.face.m0("USER_CANCELED").a), 0, 0, 12, 0);
            v7_4.getClass();
            v7_4.a.invoke(v0_0);
            p8.finish();
        }
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p4, androidx.camera.core.d p5)
    {
        if ((p4.c.b.getVisibility() != 0) && ((p4.c.d.getVisibility() != 0) && ((!p4.e.containsKey(p4.n)) && ((System.currentTimeMillis() - p4.a) >= 200)))) {
            p4.a = System.currentTimeMillis();
            android.graphics.Bitmap v0_8 = p5.u1();
            com.google.mlkit.vision.common.InputImage v1_3 = com.google.mlkit.vision.common.InputImage.fromBitmap(v0_8, p5.H().d());
            p4.o = p5.H().d();
            p4.a(v1_3, v0_8, p5);
            return;
        } else {
            p5.close();
            return;
        }
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p1, androidx.camera.core.d p2, Exception p3)
    {
        p1.getClass();
        String v1_2 = new StringBuilder("L\u1ed7i nh\u1eadn di\u1ec7n: ");
        v1_2.append(p3.getMessage());
        android.util.Log.e("SelfieCapture", v1_2.toString());
        p2.close();
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p5, String p6)
    {
        p5.c.k.setVisibility(0);
        android.widget.TextView v0_5 = p5.c;
        vn.ai.trueid.sdk.face.navigation.FaceSDK v2 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v0_5.k, vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v0_5.l.getContext(), "facesdk_scan_fail_title", v2.getLanguageJSON()));
        android.widget.TextView v0_4 = p5.c.l;
        if (p6.length() <= 0) {
            p6 = vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(p5.c.l.getContext(), "facesdk_scan_fail", v2.getLanguageJSON());
        }
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v0_4, p6);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p7, java.util.List p8)
    {
        kotlin.jvm.functions.Function1 v7_4 = ((vn.ai.trueid.sdk.face.f1) p7.i.getValue());
        vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_0 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(1, 0, p8, 0, 10, 0);
        v7_4.getClass();
        v7_4.a.invoke(v0_0);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p17, java.util.List p18, android.graphics.Bitmap p19, androidx.camera.core.d p20)
    {
        p17.getClass();
        vn.ai.trueid.sdk.face.b2[] v2_14 = ((com.google.mlkit.vision.face.Face) kotlin.collections.CollectionsKt.firstOrNull(p18));
        kotlin.Unit v3_0 = 0;
        if (v2_14 != null) {
            java.util.ArrayList v4_4 = vn.ai.trueid.sdk.face.n1.a[p17.n.ordinal()];
            if (v4_4 == 1) {
                if ((p17.a(v2_14, p19, p20.H().d())) && (p17.g.size() >= 5)) {
                    p17.e.put(p17.n, ((android.graphics.Bitmap) p17.g.get(2)));
                    p17.f.clear();
                    p17.g.clear();
                    kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getMain()), 0, 0, new vn.ai.trueid.sdk.face.q1(p17, p17.c.i, 0), 3, 0);
                }
            } else {
                if ((v4_4 == 2) && ((p17.a(v2_14, p19, p20.H().d())) && (p17.g.size() >= 5))) {
                    p17.e.put(p17.n, ((android.graphics.Bitmap) p17));
                    p17.f.clear();
                    p17.g.clear();
                    int v8_2 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
                    p17.h.add(Long.valueOf((System.currentTimeMillis() - v8_2.getStartTime())));
                    p17.h.add(Long.valueOf(0));
                    p17.e.put(p17.n, p19);
                    p17.c.l.setTextColor(android.graphics.Color.parseColor(v8_2.getConfig$face_release().getTextColor()));
                    java.util.ArrayList v1_2 = p17.c.l;
                    vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v1_2, vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v1_2.getContext(), "facesdk_checking", v8_2.getLanguageJSON()));
                    java.util.ArrayList v1_4 = p17.c.i;
                    v1_4.e();
                    vn.ai.trueid.sdk.face.b2[] v2_3 = v1_4.c;
                    if (v2_3 != null) {
                        v2_3.setColor(android.graphics.Color.parseColor(v1_4.p));
                    }
                    v1_4.invalidate();
                    v1_4.l = 1;
                    v1_4.invalidate();
                    v1_4.v = 1;
                    v1_4.invalidate();
                    p17.c.c.setVisibility(8);
                    android.util.Log.e(p17.b, "nextStep validDataAndCallApi");
                    java.util.ArrayList v1_9 = new java.util.ArrayList();
                    vn.ai.trueid.sdk.face.b2[] v2_6 = vn.ai.trueid.sdk.face.b2.values();
                    java.util.ArrayList v4_6 = new java.util.ArrayList();
                    int v5_2 = v2_6.length;
                    int v8_0 = 0;
                    while (v8_0 < v5_2) {
                        String v9_2;
                        java.io.ByteArrayOutputStream v10_2 = ((android.graphics.Bitmap) p17.e.get(v2_6[v8_0]));
                        if (v10_2 == null) {
                            v9_2 = 0;
                        } else {
                            String v9_3 = p17.o;
                            if (v9_3 != null) {
                                android.graphics.Matrix v15_1 = new android.graphics.Matrix();
                                v15_1.postRotate(((float) v9_3));
                                v10_2 = android.graphics.Bitmap.createBitmap(v10_2, 0, 0, v10_2.getWidth(), v10_2.getHeight(), v15_1, 1);
                            }
                            android.graphics.Bitmap$CompressFormat v11_3 = Math.max((((float) 600) / ((float) v10_2.getWidth())), (((float) 800) / ((float) v10_2.getHeight())));
                            int v13_6 = ((int) (((float) v10_2.getWidth()) * v11_3));
                            android.graphics.Bitmap$CompressFormat v11_4 = ((int) (((float) v10_2.getHeight()) * v11_3));
                            String v9_6 = android.graphics.Bitmap.createBitmap(android.graphics.Bitmap.createScaledBitmap(v10_2, v13_6, v11_4, 1), ((v13_6 - 600) / 2), ((v11_4 - 800) / 2), 600, 800);
                            java.io.ByteArrayOutputStream v10_5 = new java.io.ByteArrayOutputStream();
                            v9_6.compress(android.graphics.Bitmap$CompressFormat.JPEG, 100, v10_5);
                            v9_2 = android.util.Base64.encodeToString(v10_5.toByteArray(), 2);
                        }
                        if (v9_2 != null) {
                            v4_6.add(v9_2);
                        }
                        v8_0++;
                    }
                    kotlin.collections.CollectionsKt.s(v4_6, v1_9);
                    p17.a(v1_9);
                }
            }
            p20.close();
            v3_0 = kotlin.Unit.INSTANCE;
        }
        if (v3_0 == null) {
            vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_1 = p17.c;
            java.util.ArrayList v1_10 = v0_1.l;
            vn.ai.trueid.sdk.face.b2[] v2_7 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(v1_10, "facesdk_frame_face_inside", v2_7.getLanguageJSON());
            v1_10.setTextColor(android.graphics.Color.parseColor(v2_7.getConfig$face_release().getTextColor()));
            v0_1.i.a();
            p20.close();
        }
        return;
    }

    public static final void b(vn.ai.trueid.sdk.face.a2 p0)
    {
        p0.getClass();
        return;
    }

    public static final void b(vn.ai.trueid.sdk.face.a2 p2, android.view.View p3)
    {
        p2.c.k.setVisibility(8);
        p2.c.b.setVisibility(4);
        p2.c.j.setVisibility(4);
        p2.c.d.setVisibility(4);
        vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.setStartTime(System.currentTimeMillis());
        return;
    }

    public static final void c(vn.ai.trueid.sdk.face.a2 p3)
    {
        p3.c.i.post(new vn.ai.trueid.sdk.face.D(p3, 0));
        return;
    }

    public static final void c(vn.ai.trueid.sdk.face.a2 p4, android.view.View p5)
    {
        p4.h.clear();
        p4.e.clear();
        p4.n = vn.ai.trueid.sdk.face.b2.IN;
        p4.c.b.setVisibility(0);
        p4.c.j.setVisibility(0);
        p4.c.k.setVisibility(8);
        p4.c.d.setVisibility(8);
        int v5_9 = p4.c.l;
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v5_9, vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v5_9.getContext(), "facesdk_scan_status_default", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON()));
        p4.b();
        p4.e();
        int v5_10 = p4.c();
        double v0_3 = v5_10.b;
        if (v0_3 != 0) {
            double v0_4 = v0_3.i;
            if (v0_4 != 0) {
                v5_10.a();
                int v5_12 = v0_4.c;
                if (v5_12 != 0) {
                    v5_12.setColor(android.graphics.Color.parseColor(v0_4.n));
                }
                v0_4.invalidate();
            }
        }
        int v5_14 = p4.getResources().getDisplayMetrics();
        if (((double) (((float) v5_14.widthPixels) / ((float) v5_14.heightPixels))) <= 4604480259023595110) {
            p4.c.i.setScaleFlashCheck(1062333317);
            return;
        } else {
            p4.c.i.setScaleFlashCheck(1059984507);
            return;
        }
    }

    public static final void d(vn.ai.trueid.sdk.face.a2 p3)
    {
        android.widget.TextView v0_1 = p3.c.i;
        android.view.ViewGroup$MarginLayoutParams v1_3 = ((android.view.ViewGroup$MarginLayoutParams) p3.c.g.getLayoutParams());
        v1_3.setMargins(0, 0, 0, (((int) (((float) v0_1.getHeight()) - v0_1.getRectZoomIn().top)) + 48));
        p3.c.g.setLayoutParams(v1_3);
        p3.c.l.setVisibility(0);
        p3.c.l.invalidate();
        return;
    }

    public static final void e(vn.ai.trueid.sdk.face.a2 p6)
    {
        String v0_0 = p6.m;
        String v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        String v0_5 = ((androidx.camera.lifecycle.g) v0_0.get());
        int v2_0 = new F.I0().d();
        v2_0.F(p6.c.n.getSurfaceProvider());
        F.c0 v3_4 = new F.Z();
        v3_4.e();
        F.c0 v3_5 = v3_4.d();
        F.f1[] v4_0 = p6.l;
        if (v4_0 != null) {
            v1_0 = v4_0;
        }
        v3_5.G(v1_0, new ju.c(29, p6));
        try {
            v0_5.h();
            F.f1[] v4_4 = new F.f1[2];
            v4_4[0] = v2_0;
            v4_4[1] = v3_5;
            v0_5.c(p6, F.x.b, v4_4);
            return;
        } catch (String v6_1) {
            String v0_2 = new StringBuilder("L\u1ed7i CameraX: ");
            v0_2.append(v6_1.getMessage());
            android.util.Log.e("SelfieCapture", v0_2.toString());
            return;
        }
    }

    public static final void f(vn.ai.trueid.sdk.face.a2 p3)
    {
        p3.c.c.setVisibility(8);
        p3.c.b.setVisibility(8);
        p3.c.d.setVisibility(8);
        p3.c.n.setVisibility(8);
        p3.c.f.setVisibility(8);
        p3.c.i.setVisibility(8);
        p3.c.h.setVisibility(0);
        p3.c.l.setVisibility(8);
        p3.c.h.setVisibility(0);
        return;
    }

    public final void a()
    {
        android.util.Log.e(this.b, "action caseTimeOut");
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.D(this, 2), 100);
        return;
    }

    public final void a(com.google.mlkit.vision.common.InputImage p3, android.graphics.Bitmap p4, androidx.camera.core.d p5)
    {
        ((com.google.mlkit.vision.face.FaceDetector) this.p.getValue()).process(p3).addOnSuccessListener(new ju.c(28, new vn.ai.trueid.sdk.face.o1(this, p4, p5))).addOnFailureListener(new androidx.media3.exoplayer.analytics.f(this, p5, 13));
        return;
    }

    public final void a(String p4)
    {
        this.requireActivity().runOnUiThread(new kO.f(this, p4, 17));
        return;
    }

    public final void a(java.util.ArrayList p6)
    {
        vn.ai.trueid.sdk.face.r0 v0 = this.c();
        java.util.ArrayList v1_4 = this.getActivity();
        if (v1_4 != null) {
            this.c.i.a();
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(this.c.l, vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v1_4, "facesdk_processing", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON()));
        }
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new kO.f(this, p6, 18), 100);
        v0.a(p6, this.h);
        return;
    }

    public final boolean a(android.graphics.RectF p5)
    {
        int v1_5;
        String v0_2 = this.c.i.getRectOval();
        if ((v0_2.left > p5.left) || ((v0_2.top > p5.top) || ((v0_2.right < p5.right) || (v0_2.bottom < p5.bottom)))) {
            v1_5 = 0;
        } else {
            v1_5 = 1;
        }
        float v2_3 = new StringBuilder("isFaceInsideOval=");
        v2_3.append(v1_5);
        v2_3.append("\nface=");
        v2_3.append(p5);
        v2_3.append("\noval=");
        v2_3.append(v0_2);
        android.util.Log.e("OverlayView", v2_3.toString());
        return v1_5;
    }

    public final boolean a(com.google.mlkit.vision.face.Face p17, android.graphics.Bitmap p18, int p19)
    {
        double v7_6;
        long v3_14 = new android.graphics.RectF(p17.getBoundingBox());
        java.util.ArrayList v2_20 = p18.getWidth();
        java.util.Iterator v4_13 = p18.getHeight();
        java.util.ArrayList v5_5 = this.c.i.getWidth();
        Double v6_7 = this.c.i.getHeight();
        int v9_0 = 1;
        int v10 = 0;
        if ((p19 != 90) && (p19 != 270)) {
            v7_6 = 0;
        } else {
            v7_6 = 1;
        }
        int v11_5;
        if (v7_6 == 0) {
            v11_5 = v2_20;
        } else {
            v11_5 = v4_13;
        }
        if (v7_6 == 0) {
            v2_20 = v4_13;
        }
        Double v6_20;
        java.util.ArrayList v5_15;
        java.util.Iterator v4_9 = ((float) v5_5);
        java.util.ArrayList v5_13 = ((float) v6_7);
        double v7_11 = ((float) v11_5);
        java.util.ArrayList v2_22 = ((float) v2_20);
        int v11_10 = 0;
        if ((v7_11 / v2_22) <= (v4_9 / v5_13)) {
            v6_20 = (v5_13 / v2_22);
            v5_15 = 0;
            v11_10 = ((v4_9 - (v7_11 * v6_20)) / ((float) 2));
        } else {
            v6_20 = (v4_9 / v7_11);
            v5_15 = ((v5_13 - (v2_22 * v6_20)) / ((float) 2));
        }
        double v1_58;
        java.util.ArrayList v2_34 = ((v3_14.left * v6_20) + v11_10);
        double v7_15 = ((v3_14.top * v6_20) + v5_15);
        double v13_3 = ((v3_14.right * v6_20) + v11_10);
        long v3_17 = ((v3_14.bottom * v6_20) + v5_15);
        if (p19 != 270) {
            v1_58 = new android.graphics.RectF(v2_34, v7_15, v13_3, v3_17);
        } else {
            v1_58 = new android.graphics.RectF((v4_9 - v13_3), v7_15, (v4_9 - v2_34), v3_17);
        }
        java.util.ArrayList v5_4;
        java.util.ArrayList v2_36 = (v1_58.width() * 1036831949);
        long v3_20 = new android.graphics.RectF((v1_58.left + v2_36), v1_58.top, (v1_58.right - v2_36), v1_58.bottom);
        this.c.i.a();
        this.c.i.a(v3_20);
        double v1_67 = this.c.i.getRectOval();
        java.util.ArrayList v2_38 = (v3_20.width() / v1_67.width());
        java.util.Iterator v4_1 = (v3_20.height() / v1_67.height());
        if ((v4_1 < 1064514355) || (v2_38 >= 1061158912)) {
            v5_4 = 0;
        } else {
            v5_4 = 1;
        }
        if ((v2_38 < 1064514355) || (v4_1 >= 1061158912)) {
            double v7_2 = 0;
        } else {
            v7_2 = 1;
        }
        if ((v5_4 != null) || ((v7_2 != 0) || (Math.min(v2_38, v4_1) >= 1061158912))) {
            Double v6_6;
            java.util.ArrayList v2_6 = this.c.i.getRectOval();
            java.util.Iterator v4_3 = (v3_20.width() / v2_6.width());
            java.util.ArrayList v5_8 = (v3_20.height() / v2_6.height());
            if ((v5_8 < 1065772646) || (v4_3 > 1062836634)) {
                v6_6 = 0;
            } else {
                v6_6 = 1;
            }
            if ((v4_3 < 1065772646) || (v5_8 > 1064514355)) {
                double v1_8 = 0;
            } else {
                v1_8 = 1;
            }
            if ((v6_6 != null) || ((v1_8 != 0) || (Math.max(v4_3, v5_8) < 1065772646))) {
                if (this.a(v3_20)) {
                    double v1_16 = p17.getHeadEulerAngleX();
                    java.util.ArrayList v2_11 = p17.getHeadEulerAngleY();
                    java.util.Iterator v4_4 = p17.getHeadEulerAngleZ();
                    java.util.ArrayList v5_10 = -1052770304 cmp v1_16;
                    // Both branches of the condition point to the same code.
                    // if ((v5_10 <= null) && ((v1_16 <= 1094713344) && ((-1056964608 <= v2_11) && ((v2_11 <= 1090519040) && (-1056964608 > v4_4))))) {
                        if ((v5_10 > null) || ((v1_16 > 1094713344) || ((-1056964608 > v2_11) || ((v2_11 > 1090519040) || ((-1056964608 > v4_4) || (v4_4 > 1090519040)))))) {
                            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.l, "facesdk_expression_too_tilted", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
                            return 0;
                        } else {
                            double v1_24 = p17.getLeftEyeOpenProbability();
                            if (v1_24 != 0) {
                                java.util.ArrayList v2_14 = p17.getRightEyeOpenProbability();
                                if ((v2_14 != null) && ((v1_24.floatValue() < 1045220557) && (v2_14.floatValue() < 1045220557))) {
                                    vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.l, "facesdk_eye_closed", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
                                    return 0;
                                }
                            }
                            double v1_29 = p17.getSmilingProbability();
                            if ((v1_29 == 0) || (v1_29.floatValue() <= 1050253722)) {
                                double v1_33 = this.c.i;
                                v1_33.v = 1;
                                v1_33.invalidate();
                                if (this.f.size() >= 5) {
                                    this.f.remove(0);
                                    this.g.remove(0);
                                }
                                int v11_6;
                                this.f.add(v3_20);
                                this.g.add(p18);
                                vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.l, "facesdk_holdon_processing", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
                                if (this.f.size() >= 5) {
                                    double v1_44 = this.f;
                                    java.util.ArrayList v2_18 = new java.util.ArrayList();
                                    double v1_45 = v1_44.iterator();
                                    while (v1_45.hasNext()) {
                                        long v3_9 = ((android.graphics.RectF) v1_45.next());
                                        java.util.Iterator v4_7 = this.f;
                                        java.util.ArrayList v5_12 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v4_7, 10));
                                        java.util.Iterator v4_8 = v4_7.iterator();
                                        while (v4_8.hasNext()) {
                                            Double v6_13 = ((android.graphics.RectF) v4_8.next());
                                            int v15_1 = v9_0;
                                            int v11_8 = v10;
                                            v5_12.add(Double.valueOf(Math.sqrt((Math.pow(((double) (v6_13.centerY() - v3_9.centerY())), ((double) 2)) + Math.pow(((double) (v6_13.centerX() - v3_9.centerX())), ((double) 2))))));
                                            v10 = v11_8;
                                            v9_0 = v15_1;
                                        }
                                        int v11 = v10;
                                        kotlin.collections.J.addAll(v2_18, v5_12);
                                    }
                                    double v1_47;
                                    int v15_0 = v9_0;
                                    v11_6 = v10;
                                    double v1_46 = kotlin.collections.CollectionsKt.n(v2_18);
                                    if (v1_46 == 0) {
                                        v1_47 = 0;
                                    } else {
                                        v1_47 = v1_46.doubleValue();
                                    }
                                    if (v1_47 <= 4627730092099895296) {
                                        return v15_0;
                                    }
                                } else {
                                    v11_6 = 0;
                                }
                                return v11_6;
                            } else {
                                vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.l, "facesdk_expression_no_smiling", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
                                return 0;
                            }
                        }
                    // }
                } else {
                    vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.l, "facesdk_frame_face_inside", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
                    this.f.clear();
                    this.g.clear();
                    return 0;
                }
            } else {
                vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.l, "facesdk_frame_face_away", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
                this.f.clear();
                this.g.clear();
                return 0;
            }
        } else {
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.l, "facesdk_frame_face_closer", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON());
            this.f.clear();
            this.g.clear();
            return 0;
        }
    }

    public final void b()
    {
        this.c.b.setEnabled(1);
        try {
            vn.ai.trueid.sdk.face.e0.a(android.graphics.Color.parseColor(this.d), this.c.b);
            vn.ai.trueid.sdk.face.e0.a(android.graphics.Color.parseColor(this.d), this.c.d);
            return;
        } catch (String v0_4) {
            System.out.println(v0_4.toString());
            return;
        }
    }

    public final vn.ai.trueid.sdk.face.r0 c()
    {
        return ((vn.ai.trueid.sdk.face.r0) this.j.getValue());
    }

    public final void d()
    {
        this.c.l.setText("");
        this.e();
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.D(this, 4), 500);
        return;
    }

    public final void e()
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.D(this, 1), 500);
        return;
    }

    public final void f()
    {
        int v3_2;
        this.c.n.setScaleType(androidx.camera.view.t.e);
        android.widget.Button v0_26 = p9.B.a(this.requireContext());
        this.m = v0_26;
        v0_26.e(new vn.ai.trueid.sdk.face.D(this, 5), androidx.core.content.c.c(this.requireContext()));
        android.widget.Button v0_7 = this.c.i;
        vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView.a(v0_7);
        v0_7.b();
        v0_7.setVisibility(0);
        v0_7.invalidate();
        this.c.g.setVisibility(0);
        android.widget.Button v0_12 = this.c.l.getContext();
        vn.ai.trueid.sdk.face.C v2_4 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
        if (!v2_4.getConfig$face_release().isAutoProcess()) {
            v3_2 = "facesdk_scan_status_default";
        } else {
            v3_2 = "facesdk_frame_face_inside";
        }
        this.c.l.setText(vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v0_12, v3_2, v2_4.getLanguageJSON()));
        this.e();
        android.widget.Button v0_15 = this.getResources().getDisplayMetrics();
        if (((double) (((float) v0_15.widthPixels) / ((float) v0_15.heightPixels))) <= 4604480259023595110) {
            this.c.i.setScaleFlashCheck(1062333317);
        } else {
            this.c.i.setScaleFlashCheck(1059984507);
        }
        android.widget.Button v0_25 = this.c.i;
        vn.ai.trueid.sdk.face.C v2_6 = v2_4.getConfig$face_release().getBackgroundColor();
        v0_25.getClass();
        int v3_12 = new android.graphics.Paint();
        v3_12.setColor(android.graphics.Color.parseColor(v2_6));
        v3_12.setStyle(android.graphics.Paint$Style.FILL);
        v0_25.a = v3_12;
        v0_25.invalidate();
        this.c.c.setOnClickListener(new vn.ai.trueid.sdk.face.C(this, 0));
        this.c.b.setOnClickListener(new vn.ai.trueid.sdk.face.C(this, 1));
        this.c.d.setOnClickListener(new vn.ai.trueid.sdk.face.C(this, 2));
        this.c.b.setEnabled(0);
        vn.ai.trueid.sdk.face.e0.a(-3355444, this.c.b);
        return;
    }

    public final void g()
    {
        android.util.Log.e(this.b, "show LayoutSuccessScan");
        androidx.fragment.app.FragmentActivity v0_1 = this.getActivity();
        if (v0_1 != null) {
            v0_1.runOnUiThread(new vn.ai.trueid.sdk.face.D(this, 3));
        }
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        this.c = vn.ai.trueid.sdk.face.k1.a(p1, p2);
        this.l = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.c.i.setOnCallbackOverlayListener(this);
        return this.c.a;
    }

    public final void onDestroyView()
    {
        super.onDestroyView();
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.shutdown();
        return;
    }

    public final void onStop()
    {
        super.onStop();
        androidx.fragment.app.FragmentActivity v0 = this.getActivity();
        if ((v0 != null) && (!v0.isFinishing())) {
            kotlin.jvm.functions.Function1 v1_0 = ((vn.ai.trueid.sdk.face.f1) this.i.getValue());
            vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v2_1 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(304, String.valueOf(new vn.ai.trueid.sdk.face.k0("CONTEXT_SWITCHED").a), 0, 0, 12, 0);
            v1_0.getClass();
            v1_0.a.invoke(v2_1);
            v0.finish();
        }
        return;
    }

    public final void onViewCreated(android.view.View p5, android.os.Bundle p6)
    {
        super.onViewCreated(p5, p6);
        android.view.Window v5_4 = this.c();
        this.k.getValue();
        Class vtmp3 = v5_4.getClass();
        android.view.Window v5_7 = this.c();
        android.view.WindowManager$LayoutParams v6_1 = this.c;
        v5_7.getClass();
        v5_7.b = v6_1;
        this.f();
        int v0_24;
        android.view.Window v5_1 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
        this.c.c.setColorFilter(android.graphics.Color.parseColor(v5_1.getConfig$face_release().getCloseColor()), android.graphics.PorterDuff$Mode.SRC_IN);
        this.c.c.setEnabled(v5_1.getConfig$face_release().isCancelable());
        if (!v5_1.getConfig$face_release().isCancelable()) {
            v0_24 = 4;
        } else {
            v0_24 = 0;
        }
        this.c.c.setVisibility(v0_24);
        this.d = v5_1.getConfig$face_release().getPrimaryColor();
        android.view.WindowManager$LayoutParams v6_46 = v5_1.getConfig$face_release().getBackgroundColor();
        this.c.b.setTextColor(android.graphics.Color.parseColor(v5_1.getConfig$face_release().getTextButtonColor()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.c.b, v5_1.getConfig$face_release().getFontName(), ((float) v5_1.getConfig$face_release().getTextSizeButton()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.c.d, v5_1.getConfig$face_release().getFontName(), ((float) v5_1.getConfig$face_release().getTextSizeButton()));
        this.c.e.setBackgroundColor(android.graphics.Color.parseColor(v6_46));
        this.c.k.setTextColor(android.graphics.Color.parseColor(v5_1.getConfig$face_release().getErrorColor()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.c.k, v5_1.getConfig$face_release().getFontName(), ((float) v5_1.getConfig$face_release().getTextSize()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.c.m, v5_1.getConfig$face_release().getFontName(), ((float) v5_1.getConfig$face_release().getTextSize()));
        this.c.l.setTextColor(android.graphics.Color.parseColor(v5_1.getConfig$face_release().getTextColor()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.c.l, v5_1.getConfig$face_release().getFontName(), ((float) v5_1.getConfig$face_release().getTextSize()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(this.c.j, "facesdk_scan_identity", v5_1.getLanguageJSON());
        this.c.j.setTextColor(android.graphics.Color.parseColor(v5_1.getConfig$face_release().getTextColor()));
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(this.c.j, v5_1.getConfig$face_release().getFontName(), ((float) v5_1.getConfig$face_release().getTextSizeTitle()));
        if (!v5_1.getConfig$face_release().isAutoProcess()) {
            this.b();
            android.view.Window v5_3 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
            v5_3.setShowSuccess(new vn.ai.trueid.sdk.face.u1(this));
            v5_3.setTimeoutSDK(new vn.ai.trueid.sdk.face.v1(this));
            v5_3.setShowError(new vn.ai.trueid.sdk.face.w1(this));
            v5_3.setCloseSDK(new vn.ai.trueid.sdk.face.x1(this));
            android.view.Window v5_5 = this.getActivity();
            if (v5_5 != null) {
                android.view.Window v5_6 = v5_5.getWindow();
                if (v5_6 != null) {
                    android.view.WindowManager$LayoutParams v6_35 = v5_6.getAttributes();
                    v6_35.screenBrightness = 1065353216;
                    v5_6.setAttributes(v6_35);
                }
            }
            return;
        } else {
            this.c.k.setVisibility(8);
            this.c.b.setVisibility(4);
            this.c.j.setVisibility(4);
            this.c.d.setVisibility(4);
            v5_1.setStartTime(System.currentTimeMillis());
        }
    }
}
