package vn.ai.trueid.sdk.face;
public final class a0 extends androidx.fragment.app.Fragment {
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
    public vn.ai.trueid.sdk.face.b0 n;
    public int o;
    public final ng.w p;

    public a0()
    {
        super(vn.ai.trueid.sdk.face.R$layout.trueid_facesdk_fragment_camerax);
        super.b = "LivenessViewModel";
        super.e = new java.util.LinkedHashMap();
        super.f = new java.util.ArrayList();
        super.g = new java.util.ArrayList();
        super.h = new java.util.ArrayList();
        super.i = ng.x.b(vn.ai.trueid.sdk.face.t.a);
        super.j = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(super, kotlin.jvm.internal.M.a.b(vn.ai.trueid.sdk.face.r0), new vn.ai.trueid.sdk.face.z(new vn.ai.trueid.sdk.face.y(super)), vn.ai.trueid.sdk.face.q.a);
        super.k = ng.x.b(vn.ai.trueid.sdk.face.p.a);
        super.n = vn.ai.trueid.sdk.face.b0.FIRST;
        super.p = ng.x.b(vn.ai.trueid.sdk.face.n.a);
        return;
    }

    public static final void a(kotlin.jvm.functions.Function1 p0, Object p1)
    {
        p0.invoke(p1);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a0 p8)
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

    public static final void a(vn.ai.trueid.sdk.face.a0 p7, android.view.View p8)
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

    public static final void a(vn.ai.trueid.sdk.face.a0 p4, androidx.camera.core.d p5)
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

    public static final void a(vn.ai.trueid.sdk.face.a0 p1, androidx.camera.core.d p2, Exception p3)
    {
        p1.getClass();
        String v1_2 = new StringBuilder("L\u1ed7i nh\u1eadn di\u1ec7n: ");
        v1_2.append(p3.getMessage());
        android.util.Log.e("SelfieCapture", v1_2.toString());
        p2.close();
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a0 p7, java.util.List p8)
    {
        kotlin.jvm.functions.Function1 v7_4 = ((vn.ai.trueid.sdk.face.f1) p7.i.getValue());
        vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult v0_0 = new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(1, 0, p8, 0, 10, 0);
        v7_4.getClass();
        v7_4.a.invoke(v0_0);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a0 p18, java.util.List p19, android.graphics.Bitmap p20, androidx.camera.core.d p21)
    {
        p18.getClass();
        int v3_3 = 0;
        if (!p19.isEmpty()) {
            vn.ai.trueid.sdk.face.b0[] v2_40 = ((com.google.mlkit.vision.face.Face) kotlin.collections.CollectionsKt.firstOrNull(p19));
            kotlin.Unit v4_0 = 0;
            if (v2_40 != null) {
                java.util.ArrayList v5_9 = vn.ai.trueid.sdk.face.l.a[p18.n.ordinal()];
                if (v5_9 == 1) {
                    if ((p18.a(v2_40, p20, p21.H().d())) && (p18.g.size() >= 5)) {
                        p18.e.put(p18.n, ((android.graphics.Bitmap) p18.g.get(2)));
                        p18.f.clear();
                        p18.g.clear();
                        java.util.ArrayList v1_29 = p18.c;
                        v1_29.l.setTextColor(-1);
                        java.util.ArrayList v1_30 = v1_29.i;
                        v1_30.getClass();
                        vn.ai.trueid.sdk.face.b0[] v2_33 = new android.graphics.Paint();
                        v2_33.setColor(android.graphics.Color.parseColor("#00FF00"));
                        java.util.ArrayList v5_23 = android.graphics.Paint$Style.FILL;
                        v2_33.setStyle(v5_23);
                        v1_30.a = v2_33;
                        vn.ai.trueid.sdk.face.b0[] v2_35 = new android.graphics.Paint();
                        v2_35.setAntiAlias(1);
                        v2_35.setColor(android.graphics.Color.parseColor("#00FF00"));
                        v2_35.setStyle(v5_23);
                        v2_35.setAlpha(200);
                        v1_30.k = v2_35;
                        vn.ai.trueid.sdk.face.b0[] v2_36 = v1_30.c;
                        if (v2_36 != null) {
                            v2_36.setColor(-1);
                            v2_36.setAlpha(150);
                        }
                        v1_30.invalidate();
                        kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getMain()), 0, 0, new vn.ai.trueid.sdk.face.s(p18, v1_30, 0), 3, 0);
                    }
                } else {
                    if (v5_9 == 2) {
                        p18.e.put(p18.n, p20);
                        java.util.ArrayList v1_32 = p18.c.i;
                        v1_32.getClass();
                        vn.ai.trueid.sdk.face.b0[] v2_42 = new android.graphics.Paint();
                        v2_42.setColor(android.graphics.Color.parseColor("#FF0000"));
                        java.util.ArrayList v5_26 = android.graphics.Paint$Style.FILL;
                        v2_42.setStyle(v5_26);
                        v1_32.a = v2_42;
                        vn.ai.trueid.sdk.face.b0[] v2_1 = new android.graphics.Paint();
                        v2_1.setAntiAlias(1);
                        v2_1.setColor(android.graphics.Color.parseColor("#FF0000"));
                        v2_1.setStyle(v5_26);
                        v2_1.setAlpha(200);
                        v1_32.k = v2_1;
                        vn.ai.trueid.sdk.face.b0[] v2_2 = v1_32.c;
                        if (v2_2 != null) {
                            v2_2.setColor(-1);
                            v2_2.setAlpha(150);
                        }
                        v1_32.invalidate();
                        kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getMain()), 0, 0, new vn.ai.trueid.sdk.face.o(p18, 0), 3, 0);
                    } else {
                        if (v5_9 == 3) {
                            String v7_1 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
                            p18.h.add(Long.valueOf((System.currentTimeMillis() - v7_1.getStartTime())));
                            p18.h.add(Long.valueOf(0));
                            p18.e.put(p18.n, p20);
                            p18.c.l.setTextColor(android.graphics.Color.parseColor(v7_1.getConfig$face_release().getTextColor()));
                            java.util.ArrayList v1_5 = p18.c.l;
                            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v1_5, vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v1_5.getContext(), "facesdk_checking", v7_1.getLanguageJSON()));
                            java.util.ArrayList v1_7 = p18.c.i;
                            v1_7.getClass();
                            vn.ai.trueid.sdk.face.b0[] v2_13 = new android.graphics.Paint();
                            v2_13.setColor(android.graphics.Color.parseColor(v7_1.getConfig$face_release().getBackgroundColor()));
                            v2_13.setStyle(android.graphics.Paint$Style.FILL);
                            v1_7.a = v2_13;
                            vn.ai.trueid.sdk.face.b0[] v2_15 = new android.graphics.Paint();
                            v2_15.setAlpha(0);
                            v1_7.k = v2_15;
                            vn.ai.trueid.sdk.face.b0[] v2_16 = v1_7.c;
                            if (v2_16 != null) {
                                v2_16.setColor(android.graphics.Color.parseColor(v1_7.n));
                            }
                            v1_7.invalidate();
                            v1_7.e();
                            vn.ai.trueid.sdk.face.b0[] v2_17 = v1_7.c;
                            if (v2_17 != null) {
                                v2_17.setColor(android.graphics.Color.parseColor(v1_7.p));
                            }
                            v1_7.invalidate();
                            v1_7.l = 1;
                            v1_7.invalidate();
                            v1_7.v = 1;
                            v1_7.invalidate();
                            p18.c.c.setVisibility(8);
                            android.util.Log.e(p18.b, "nextStep validDataAndCallApi");
                            java.util.ArrayList v1_12 = new java.util.ArrayList();
                            vn.ai.trueid.sdk.face.b0[] v2_20 = vn.ai.trueid.sdk.face.b0.values();
                            java.util.ArrayList v5_20 = new java.util.ArrayList();
                            int v6_4 = v2_20.length;
                            while (v3_3 < v6_4) {
                                String v7_5;
                                android.graphics.Bitmap$CompressFormat v11_2 = ((android.graphics.Bitmap) p18.e.get(v2_20[v3_3]));
                                if (v11_2 == null) {
                                    v7_5 = 0;
                                } else {
                                    String v7_6 = p18.o;
                                    if (v7_6 != null) {
                                        java.io.ByteArrayOutputStream v8_5 = new android.graphics.Matrix();
                                        v8_5.postRotate(((float) v7_6));
                                        v11_2 = android.graphics.Bitmap.createBitmap(v11_2, 0, 0, v11_2.getWidth(), v11_2.getHeight(), v8_5, 1);
                                    }
                                    java.io.ByteArrayOutputStream v8_8 = Math.max((((float) 600) / ((float) v11_2.getWidth())), (((float) 800) / ((float) v11_2.getHeight())));
                                    int v13_6 = ((int) (((float) v11_2.getWidth()) * v8_8));
                                    java.io.ByteArrayOutputStream v8_9 = ((int) (((float) v11_2.getHeight()) * v8_8));
                                    String v7_9 = android.graphics.Bitmap.createBitmap(android.graphics.Bitmap.createScaledBitmap(v11_2, v13_6, v8_9, 1), ((v13_6 - 600) / 2), ((v8_9 - 800) / 2), 600, 800);
                                    java.io.ByteArrayOutputStream v8_13 = new java.io.ByteArrayOutputStream();
                                    v7_9.compress(android.graphics.Bitmap$CompressFormat.JPEG, 100, v8_13);
                                    v7_5 = android.util.Base64.encodeToString(v8_13.toByteArray(), 2);
                                }
                                if (v7_5 != null) {
                                    v5_20.add(v7_5);
                                }
                                v3_3++;
                            }
                            kotlin.collections.CollectionsKt.s(v5_20, v1_12);
                            p18.a(v1_12);
                        }
                    }
                }
                p21.close();
                v4_0 = kotlin.Unit.INSTANCE;
            }
            if (v4_0 == null) {
                p21.close();
            }
            return;
        } else {
            p18.e.clear();
            p18.n = vn.ai.trueid.sdk.face.b0.FIRST;
            vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_1 = p18.c;
            java.util.ArrayList v1_16 = v0_1.l;
            vn.ai.trueid.sdk.face.b0[] v2_21 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.setTextByKey(v1_16, "facesdk_frame_face_inside", v2_21.getLanguageJSON());
            v1_16.setTextColor(android.graphics.Color.parseColor(v2_21.getConfig$face_release().getTextColor()));
            vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_2 = v0_1.i;
            v0_2.a();
            java.util.ArrayList v1_19 = new android.graphics.Paint();
            v1_19.setColor(android.graphics.Color.parseColor(v2_21.getConfig$face_release().getBackgroundColor()));
            v1_19.setStyle(android.graphics.Paint$Style.FILL);
            v0_2.a = v1_19;
            java.util.ArrayList v1_22 = new android.graphics.Paint();
            v1_22.setAlpha(0);
            v0_2.k = v1_22;
            java.util.ArrayList v1_23 = v0_2.c;
            if (v1_23 != null) {
                v1_23.setColor(android.graphics.Color.parseColor(v0_2.n));
            }
            v0_2.invalidate();
            p21.close();
            return;
        }
    }

    public static final void b(vn.ai.trueid.sdk.face.a0 p3)
    {
        p3.c.i.post(new vn.ai.trueid.sdk.face.r(p3, 3));
        return;
    }

    public static final void b(vn.ai.trueid.sdk.face.a0 p2, android.view.View p3)
    {
        p2.c.k.setVisibility(8);
        p2.c.b.setVisibility(4);
        p2.c.j.setVisibility(4);
        p2.c.d.setVisibility(4);
        vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.setStartTime(System.currentTimeMillis());
        return;
    }

    public static final void c(vn.ai.trueid.sdk.face.a0 p3)
    {
        android.widget.TextView v0_1 = p3.c.i;
        android.view.ViewGroup$MarginLayoutParams v1_3 = ((android.view.ViewGroup$MarginLayoutParams) p3.c.g.getLayoutParams());
        v1_3.setMargins(0, 0, 0, (((int) (((float) v0_1.getHeight()) - v0_1.getRectZoomIn().top)) + 48));
        p3.c.g.setLayoutParams(v1_3);
        p3.c.l.setVisibility(0);
        p3.c.l.invalidate();
        return;
    }

    public static final void c(vn.ai.trueid.sdk.face.a0 p3, android.view.View p4)
    {
        p3.h.clear();
        p3.e.clear();
        p3.n = vn.ai.trueid.sdk.face.b0.FIRST;
        p3.c.b.setVisibility(0);
        p3.c.j.setVisibility(0);
        p3.c.k.setVisibility(8);
        p3.c.d.setVisibility(8);
        vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v4_9 = p3.c.l;
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v4_9, vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v4_9.getContext(), "facesdk_scan_status_default", vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLanguageJSON()));
        p3.b();
        p3.d();
        android.graphics.Paint v3_1 = p3.c();
        vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v4_10 = v3_1.b;
        if (v4_10 != null) {
            vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v4_11 = v4_10.i;
            if (v4_11 != null) {
                v3_1.a();
                android.graphics.Paint v3_2 = v4_11.c;
                if (v3_2 != null) {
                    v3_2.setColor(android.graphics.Color.parseColor(v4_11.n));
                }
                v4_11.invalidate();
            }
        }
        return;
    }

    public static final void d(vn.ai.trueid.sdk.face.a0 p6)
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
        v3_5.G(v1_0, new ju.c(26, p6));
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

    public final void a()
    {
        android.util.Log.e(this.b, "action caseTimeOut");
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.r(this, 2), 100);
        return;
    }

    public final void a(com.google.mlkit.vision.common.InputImage p3, android.graphics.Bitmap p4, androidx.camera.core.d p5)
    {
        ((com.google.mlkit.vision.face.FaceDetector) this.p.getValue()).process(p3).addOnSuccessListener(new ju.c(27, new vn.ai.trueid.sdk.face.m(this, p4, p5))).addOnFailureListener(new androidx.media3.exoplayer.analytics.f(this, p5, 12));
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
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new kO.f(this, p6, 16), 100);
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
        java.util.ArrayList v5_15;
        Double v6_20;
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
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.r(this, 1), 500);
        return;
    }

    public final void e()
    {
        int v3_4;
        this.c.n.setScaleType(androidx.camera.view.t.e);
        android.widget.Button v0_26 = p9.B.a(this.requireContext());
        this.m = v0_26;
        v0_26.e(new vn.ai.trueid.sdk.face.r(this, 0), androidx.core.content.c.c(this.requireContext()));
        android.widget.Button v0_7 = this.c.i;
        vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView.a(v0_7);
        v0_7.b();
        v0_7.setVisibility(0);
        v0_7.invalidate();
        this.c.g.setVisibility(0);
        android.widget.Button v0_12 = this.c.l.getContext();
        int v1_4 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
        if (!v1_4.getConfig$face_release().isAutoProcess()) {
            v3_4 = "facesdk_scan_status_default";
        } else {
            v3_4 = "facesdk_frame_face_inside";
        }
        this.c.l.setText(vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v0_12, v3_4, v1_4.getLanguageJSON()));
        this.d();
        android.widget.Button v0_15 = this.getResources().getDisplayMetrics();
        if (((double) (((float) v0_15.widthPixels) / ((float) v0_15.heightPixels))) <= 4604480259023595110) {
            this.c.i.setScaleFlashCheck(1064011039);
        } else {
            this.c.i.setScaleFlashCheck(1061158912);
        }
        android.widget.Button v0_25 = this.c.i;
        int v1_6 = v1_4.getConfig$face_release().getBackgroundColor();
        v0_25.getClass();
        int v3_14 = new android.graphics.Paint();
        v3_14.setColor(android.graphics.Color.parseColor(v1_6));
        v3_14.setStyle(android.graphics.Paint$Style.FILL);
        v0_25.a = v3_14;
        v0_25.invalidate();
        this.c.c.setOnClickListener(new vn.ai.trueid.sdk.face.A(this, 0));
        this.c.b.setOnClickListener(new vn.ai.trueid.sdk.face.A(this, 1));
        this.c.d.setOnClickListener(new vn.ai.trueid.sdk.face.A(this, 2));
        this.c.b.setEnabled(0);
        vn.ai.trueid.sdk.face.e0.a(-3355444, this.c.b);
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        this.c = vn.ai.trueid.sdk.face.k1.a(p1, p2);
        this.l = java.util.concurrent.Executors.newSingleThreadExecutor();
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
        this.e();
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
            v5_3.setShowSuccess(new vn.ai.trueid.sdk.face.u(this));
            v5_3.setTimeoutSDK(new vn.ai.trueid.sdk.face.v(this));
            v5_3.setShowError(new vn.ai.trueid.sdk.face.w(this));
            v5_3.setCloseSDK(new vn.ai.trueid.sdk.face.x(this));
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
