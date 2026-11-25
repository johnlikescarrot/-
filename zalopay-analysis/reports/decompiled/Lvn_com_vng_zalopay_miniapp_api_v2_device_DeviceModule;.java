package vn.com.vng.zalopay.miniapp.api.v2.device;
public final class DeviceModule implements LR.a {
    public static final rF.h B = None;
    public static final int C = 8;
    public static final int D = 21;
    public static final int E = 32;
    public static final int F = 0;
    public static final int G = 23;
    public static final int H = 24;
    private static final String I = "yyyy-MM-dd-HH-mm-ss-SSS";
    private static final String J = "appId";
    private static final String K = "";
    private static final String L = "keys";
    private static final String M = "value";
    private static final String N = "data";
    private static final String O = "accuracy";
    private static final String P;
    private final ng.w A;
    private final MD.g a;
    private final Is.a b;
    private final XG.c c;
    private final Yv.i d;
    private final vn.com.vng.zalopay.navigation.b e;
    private final IE.a f;
    private final HF.a g;
    private final Ns.a h;
    private final Yt.q i;
    private final Yt.a j;
    private ref.WeakReference k;
    private MR.b l;
    private final androidx.lifecycle.T m;
    private final androidx.lifecycle.N n;
    private GX.b o;
    private GX.b p;
    private GX.b q;
    private GX.b r;
    private java.io.File s;
    private String t;
    private ZF.f u;
    private GX.b v;
    private GX.b w;
    private GX.b x;
    private MD.j y;
    private final ng.w z;

    static DeviceModule()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule, 23185, 23187);
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.B = new rF.h();
        return;
    }

    public DeviceModule(MD.g p1, Is.a p2, XG.c p3, Yv.i p4, vn.com.vng.zalopay.navigation.b p5, IE.a p6, HF.a p7, Ns.a p8, Yt.q p9, Yt.a p10)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        this.g = p7;
        this.h = p8;
        this.i = p9;
        this.j = p10;
        ng.S v1_2 = new androidx.lifecycle.T(sF.a.a);
        this.m = v1_2;
        this.n = v1_2;
        this.z = ng.x.b(new rF.a(this, 0));
        this.A = ng.x.b(new rF.a(this, 1));
        return;
    }

    public static synthetic kotlin.Unit A(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.N1(p0, p1, p2);
    }

    private final void A0(MR.b p6)
    {
        String v0_1 = java.util.TimeZone.getDefault().getID();
        if (v0_1 == null) {
            v0_1 = "";
        }
        this.c2(p6, new eR.i(v0_1, new java.text.SimpleDateFormat("ZZZZZ", java.util.Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()))));
        return;
    }

    private final void A1()
    {
        a9.f v0_0 = this.k;
        GX.c v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        a9.f v0_3 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_3 != null) {
            GX.c v1_2 = new String[0];
            v1_0 = com.google.android.gms.internal.mlkit_vision_barcode.h5.d(v0_3, "android.permission.RECORD_AUDIO", v1_2).g();
        }
        this.w = v1_0;
        return;
    }

    public static synthetic kotlin.Unit B(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2, MD.j p3)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.H1(p0, p1, p2, p3);
    }

    private final void B0(org.json.JSONObject p3, MR.b p4)
    {
        this.l = p4;
        if (p3 != null) {
            androidx.fragment.app.FragmentActivity v0_1 = this.k;
            if (v0_1 == null) {
                v0_1 = 0;
            }
            androidx.fragment.app.FragmentActivity v0_3 = ((androidx.fragment.app.Fragment) v0_1.get());
            if (v0_3 != null) {
                androidx.fragment.app.FragmentActivity v0_0 = v0_3.getActivity();
                if (v0_0 != null) {
                    try {
                        v0_0.runOnUiThread(new androidx.camera.camera2.internal.E0(p3.getBoolean("keepScreenOn"), v0_0, this));
                        return;
                    } catch (Exception) {
                        if (p4 == null) {
                            return;
                        } else {
                            p4.h(ZF.m.c);
                            return;
                        }
                    }
                }
            }
            if (p4 != null) {
                p4.d();
            }
        } else {
            if (p4 != null) {
                p4.h(ZF.m.c);
                return;
            }
        }
        return;
    }

    private final void B1()
    {
        if (android.os.Build$VERSION.SDK_INT >= 33) {
            a9.f v0_2 = this.k;
            GX.c v1_3 = 0;
            if (v0_2 == null) {
                v0_2 = 0;
            }
            a9.f v0_4 = ((androidx.fragment.app.Fragment) v0_2.get());
            if (v0_4 != null) {
                GX.c v1_2 = new String[0];
                v1_3 = com.google.android.gms.internal.mlkit_vision_barcode.h5.d(v0_4, "android.permission.POST_NOTIFICATIONS", v1_2).g();
            }
            this.x = v1_3;
        }
        return;
    }

    public static synthetic void C(kotlin.jvm.functions.Function1 p0, java.util.ArrayList p1)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.O(p0, p1);
        return;
    }

    private static final void C0(boolean p2, androidx.fragment.app.FragmentActivity p3, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p4)
    {
        if (p2 == null) {
            MR.b v2_3 = p3.getWindow();
            if (v2_3 != null) {
                v2_3.clearFlags(128);
            }
            MR.b v2_4 = p4.l;
            if (v2_4 != null) {
                v2_4.b(new org.json.JSONObject().put("status", "off"));
            }
        } else {
            MR.b v2_1 = p3.getWindow();
            if (v2_1 != null) {
                v2_1.addFlags(128);
            }
            MR.b v2_2 = p4.l;
            if (v2_2 != null) {
                v2_2.b(new org.json.JSONObject().put("status", "on"));
                return;
            }
        }
        return;
    }

    private final void C1()
    {
        this.y1();
        this.w1();
        this.x1();
        this.A1();
        this.B1();
        return;
    }

    public static synthetic void D(boolean p0, androidx.fragment.app.FragmentActivity p1, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p2)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.C0(p0, p1, p2);
        return;
    }

    private final void D0(MR.b p3, kotlin.jvm.functions.Function1 p4)
    {
        this.l = p3;
        this.y = 0;
        GX.b v3_3 = this.o;
        if (v3_3 != null) {
            v3_3.c();
        }
        GX.b v3_4 = this.o;
        if (v3_4 != null) {
            v3_4.d(new rF.f(0, p4));
        }
        GX.b v3_2 = this.o;
        if (v3_2 != null) {
            v3_2.b();
        }
        return;
    }

    private static final kotlin.Unit D1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        p0.k0(p1, p2);
        return kotlin.Unit.INSTANCE;
    }

    public static synthetic kotlin.Unit E(MR.b p0, org.json.JSONObject p1)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.V0(p0, p1);
    }

    private static final void E0(kotlin.jvm.functions.Function1 p0, java.util.List p1)
    {
        p0.invoke(p1);
        return;
    }

    private static final kotlin.Unit E1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2, MD.j p3)
    {
        p0.T0(p1, p2, p3);
        return kotlin.Unit.INSTANCE;
    }

    public static final synthetic void F(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, boolean p1)
    {
        p0.X(p1);
        return;
    }

    private final void F0(org.json.JSONObject p6, MR.b p7, boolean p8, kotlin.jvm.functions.Function2 p9)
    {
        java.util.Iterator v0_1 = MD.j.d.iterator();
        while (v0_1.hasNext()) {
            boolean v2_1;
            MD.j v1_2 = v0_1.next();
            boolean v2_0 = ((MD.j) v1_2);
            if (p6 == null) {
                v2_1 = 0;
            } else {
                String v3_1 = p6.optString("accuracy");
                if (v3_1 == null) {
                } else {
                    v2_1 = kotlin.text.y.equals(v3_1, v2_0.name(), 1);
                }
            }
            if (v2_1) {
            }
            MD.j v1_0 = ((MD.j) v1_2);
            if (v1_0 == null) {
                v1_0 = MD.j.b;
            }
            this.l = p7;
            this.y = v1_0;
            java.util.ArrayList v6_1 = this.p;
            if (v6_1 != null) {
                v6_1.c();
            }
            this.z1(v1_0);
            java.util.ArrayList v6_2 = this.p;
            if (v6_2 != null) {
                if (p8 == 0) {
                    v6_2.d(new CA.c(p9, v1_0, 1));
                    v6_2.b();
                } else {
                    p9.invoke(v6_2.f(), v1_0);
                    return;
                }
            }
            return;
        }
        v1_2 = 0;
    }

    private static final kotlin.Unit F1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        p0.d1(p1, p2);
        return kotlin.Unit.INSTANCE;
    }

    public static final synthetic XG.c G(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0)
    {
        return p0.c;
    }

    public static synthetic void G0(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2, boolean p3, kotlin.jvm.functions.Function2 p4, int p5, Object p6)
    {
        if ((p5 & 4) != 0) {
            p3 = 0;
        }
        p0.F0(p1, p2, p3, p4);
        return;
    }

    private static final kotlin.Unit G1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p7, org.json.JSONObject p8, MR.b p9)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.G0(p7, p8, p9, 0, new rF.g(p7, p9, 0), 4, 0);
        return kotlin.Unit.INSTANCE;
    }

    public static final synthetic ref.WeakReference H(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0)
    {
        return p0.k;
    }

    private static final void H0(kotlin.jvm.functions.Function2 p0, MD.j p1, java.util.List p2)
    {
        p0.invoke(p2, p1);
        return;
    }

    private static final kotlin.Unit H1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2, MD.j p3)
    {
        p0.T0(p1, p2, p3);
        return kotlin.Unit.INSTANCE;
    }

    public static final synthetic void I(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.o0(p0, p1);
        return;
    }

    private final void I0(MR.b p7, java.util.List p8)
    {
        String v8_1;
        String v1_0 = (((Iterable) p8) instanceof java.util.Collection);
        if ((v1_0 == null) || (!((java.util.Collection) ((Iterable) p8)).isEmpty())) {
            boolean v3_0 = ((Iterable) p8).iterator();
            while (v3_0.hasNext()) {
                if ((VE.c.m(p8)) && ((((HX.d) v3_0.next()) instanceof HX.b))) {
                    v8_1 = "PERMISSION_ALWAYS";
                }
            }
            if ((v1_0 == null) || (!((java.util.Collection) ((Iterable) p8)).isEmpty())) {
                org.json.JSONObject v0_2 = ((Iterable) p8).iterator();
                while (v0_2.hasNext()) {
                    if ((VE.c.m(p8)) && ((((HX.d) v0_2.next()) instanceof HX.a))) {
                        v8_1 = "PERMISSION_NOT_GRANTED";
                        if (p7 != null) {
                            org.json.JSONObject v0_4 = new org.json.JSONObject();
                            v0_4.put("user_allow", kotlin.jvm.internal.Intrinsics.areEqual(v8_1, "PERMISSION_ALWAYS"));
                            v0_4.put("detail", v8_1);
                            p7.b(v0_4);
                        }
                        return;
                    }
                }
            }
            v8_1 = "PERMISSION_DENIED";
        }
    }

    private static final kotlin.Unit I1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        p0.e1(p1, p2);
        return kotlin.Unit.INSTANCE;
    }

    public static final synthetic void J(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, java.io.File p1)
    {
        p0.k2(p1);
        return;
    }

    private final void J0(android.content.Context p3, MR.b p4, String p5)
    {
        if (!new androidx.core.app.J(p3).a.areNotificationsEnabled()) {
            p5 = "PERMISSION_DENIED";
        }
        if (p4 != null) {
            org.json.JSONObject v3_4 = new org.json.JSONObject();
            v3_4.put("user_allow", kotlin.jvm.internal.Intrinsics.areEqual(p5, "PERMISSION_ALWAYS"));
            v3_4.put("detail", p5);
            p4.b(v3_4);
        }
        return;
    }

    private static final kotlin.Unit J1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        p0.h1(p1, p2);
        return kotlin.Unit.INSTANCE;
    }

    private static final android.media.AudioManager K(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p2)
    {
        android.media.AudioManager v2_1 = p2.k;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        android.media.AudioManager v2_3;
        android.media.AudioManager v2_6 = ((androidx.fragment.app.Fragment) v2_1.get());
        if (v2_6 == null) {
            v2_3 = 0;
        } else {
            android.media.AudioManager v2_7 = v2_6.getActivity();
            if (v2_7 == null) {
            } else {
                android.media.AudioManager v2_2 = v2_7.getApplicationContext();
                if (v2_2 == null) {
                } else {
                    v2_3 = v2_2.getSystemService("audio");
                }
            }
        }
        if (!(v2_3 instanceof android.media.AudioManager)) {
            return 0;
        } else {
            return ((android.media.AudioManager) v2_3);
        }
    }

    private final void K0(android.content.Context p1, MR.b p2, java.util.List p3)
    {
        String v3_2;
        if (!VE.c.n(p3)) {
            v3_2 = "PERMISSION_DENIED";
        } else {
            v3_2 = "PERMISSION_ALWAYS";
        }
        this.J0(p1, p2, v3_2);
        return;
    }

    private static final kotlin.Unit K1(org.json.JSONObject p2, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p3, MR.b p4, java.util.List p5, MD.j p6)
    {
        boolean v0 = 1;
        if (p2 != null) {
            v0 = p2.optBoolean("showServiceAlert", 1);
        }
        p3.k1(p4, p5, p6, v0);
        return kotlin.Unit.INSTANCE;
    }

    private final void L0()
    {
        MR.b v0 = this.l;
        if (v0 != null) {
            v0.h(ZF.j.a);
        }
        return;
    }

    private static final kotlin.Unit L1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        p0.Z0(p1, p2);
        return kotlin.Unit.INSTANCE;
    }

    private static final android.hardware.camera2.CameraManager M(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p2)
    {
        android.hardware.camera2.CameraManager v2_1 = p2.k;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        android.hardware.camera2.CameraManager v2_3;
        android.hardware.camera2.CameraManager v2_6 = ((androidx.fragment.app.Fragment) v2_1.get());
        if (v2_6 == null) {
            v2_3 = 0;
        } else {
            android.hardware.camera2.CameraManager v2_7 = v2_6.getActivity();
            if (v2_7 == null) {
            } else {
                android.hardware.camera2.CameraManager v2_2 = v2_7.getApplicationContext();
                if (v2_2 == null) {
                } else {
                    v2_3 = v2_2.getSystemService("camera");
                }
            }
        }
        if (!(v2_3 instanceof android.hardware.camera2.CameraManager)) {
            return 0;
        } else {
            return ((android.hardware.camera2.CameraManager) v2_3);
        }
    }

    private final void M0(org.json.JSONObject p3, MR.b p4)
    {
        boolean v3_2;
        if (!p3) {
            v3_2 = 0;
        } else {
            boolean v3_1 = p3.optString("sourceType");
            if (!v3_1) {
            } else {
                v3_2 = kotlin.text.y.equals(v3_1, "front", 1);
            }
        }
        this.Q1(new rF.e(this, p4, v3_2));
        return;
    }

    private static final kotlin.Unit M1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        p0.b1(p1, p2);
        return kotlin.Unit.INSTANCE;
    }

    private final void N(kotlin.jvm.functions.Function1 p5)
    {
        if (android.os.Build$VERSION.SDK_INT < 29) {
            GX.c v0_5 = this.k;
            if (v0_5 == null) {
                v0_5 = 0;
            }
            GX.c v0_9 = ((androidx.fragment.app.Fragment) v0_5.get());
            if (v0_9 != null) {
                GX.c v0_1 = v0_9.getActivity();
                if (v0_1 != null) {
                    GX.c v0_2 = com.google.android.gms.internal.mlkit_vision_barcode.h5.a(v0_1);
                    if (v0_2 != null) {
                        this.v = v0_2;
                        GX.a v1_2 = ((GX.a) v0_2);
                        v1_2.c();
                        v1_2.d(new rF.f(1, p5));
                        ((GX.c) v0_2).b();
                        return;
                    } else {
                        p5.invoke(Boolean.TRUE);
                        return;
                    }
                }
            }
            p5.invoke(Boolean.FALSE);
            return;
        } else {
            p5.invoke(Boolean.TRUE);
            return;
        }
    }

    private static final void N0(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p1, MR.b p2, boolean p3, java.util.List p4)
    {
        if ((!VE.c.d(p4)) || (!VE.c.c(p4))) {
            if (p2 != null) {
                p2.h(ZF.a.c);
            }
            return;
        } else {
            p1.l = p2;
            p1.e2(p2, p3);
            return;
        }
    }

    private static final kotlin.Unit N1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        p0.Y0(p1, p2);
        return kotlin.Unit.INSTANCE;
    }

    private static final void O(kotlin.jvm.functions.Function1 p4, java.util.List p5)
    {
        if ((!(((Iterable) p5) instanceof java.util.Collection)) || (!((java.util.Collection) ((Iterable) p5)).isEmpty())) {
            java.util.Iterator v0_2 = ((Iterable) p5).iterator();
            while (v0_2.hasNext()) {
                boolean v1_2 = ((HX.d) v0_2.next());
                if ((kotlin.jvm.internal.Intrinsics.areEqual(v1_2.a(), "android.permission.WRITE_EXTERNAL_STORAGE")) || (kotlin.jvm.internal.Intrinsics.areEqual(v1_2.a(), "android.permission.READ_EXTERNAL_STORAGE"))) {
                    p4.invoke(Boolean.valueOf(VE.c.l(p5)));
                    break;
                }
            }
        }
        return;
    }

    private final void O0(org.json.JSONObject p3, MR.b p4)
    {
        this.l = p4;
        if (p3 == null) {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, 0, 0, 3, 0);
            return;
        } else {
            String v3_2 = ((eR.n$d) nY.h.b(p3.toString(), new vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule$handleOpenPhoneV2$lambda$72$$inlined$parseJson$1().getType()));
            if (v3_2 != null) {
                String v3_3 = v3_2.a();
                if (v3_3 != null) {
                    this.f2(v3_3);
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.d2(this, 0, 0, 3, 0);
                    return;
                }
            }
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, 0, 0, 3, 0);
            return;
        }
    }

    private final boolean O1(Number p3, double p4, double p6)
    {
        long v0;
        if (p3 == 0) {
            v0 = -4616189618054758400;
        } else {
            v0 = p3.doubleValue();
        }
        if ((p4 > v0) || (v0 > p6)) {
            return 0;
        } else {
            return 1;
        }
    }

    private final void P(org.json.JSONObject p9, MR.b p10)
    {
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("action", "checkAppAvailability"), kotlin.TuplesKt.to("data", q9.y6.g(p9))})));
        java.util.Iterator v9_3 = ((eR.n$a) q9.s6.a(q9.y6.g(p9), eR.n$a));
        org.json.JSONObject v0_5 = new org.json.JSONObject();
        String v2_1 = new org.json.JSONArray();
        android.content.Context v3_0 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v3_0 == null) {
            v3_0 = 0;
        }
        android.content.Context v3_1 = v3_0.getApplicationContext();
        if (v9_3 != null) {
            java.util.Iterator v9_4 = v9_3.a();
            if (v9_4 != null) {
                java.util.Iterator v9_6 = ((Iterable) v9_4).iterator();
                while (v9_6.hasNext()) {
                    boolean v4_2 = ((eR.a) v9_6.next());
                    org.json.JSONObject v5_1 = new org.json.JSONObject();
                    v5_1.put("scheme", v4_2.b());
                    v5_1.put("package_name", v4_2.a());
                    boolean v4_3 = v4_2.a();
                    if (!v4_3) {
                        v4_3 = "";
                    }
                    v5_1.put("is_available", com.google.android.gms.internal.mlkit_vision_barcode.x0.c(v3_1, v4_3));
                    v2_1.put(v5_1);
                }
            }
        }
        v0_5.put("apps", v2_1);
        java.util.Iterator v9_9 = new StringBuilder("data ");
        v9_9.append(v0_5);
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("callback", v9_9.toString())));
        if (p10 != null) {
            p10.f(v0_5);
        }
        return;
    }

    private final void P0(org.json.JSONObject p4, MR.b p5)
    {
        this.l = p5;
        if (p4 == null) {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, 0, 0, 3, 0);
            return;
        } else {
            String v4_2 = ((eR.n$e) nY.h.b(p4.toString(), new vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule$handleOpenSMSV2$lambda$68$$inlined$parseJson$1().getType()));
            if (v4_2 != null) {
                String v1_1 = v4_2.b();
                if (v1_1 != null) {
                    this.g2(new Jw.d(v1_1, v4_2.a()));
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.d2(this, 0, 0, 3, 0);
                    return;
                }
            }
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, 0, 0, 3, 0);
            return;
        }
    }

    public static synthetic boolean P1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p6, Number p7, double p8, double p10, int p12, Object p13)
    {
        if ((p12 & 1) != 0) {
            p8 = 0;
        }
        if ((p12 & 2) != 0) {
            p10 = 4607182418800017408;
        }
        return p6.O1(p7, p8, p10);
    }

    private final void Q(MR.b p5)
    {
        ZF.b v0_1 = new android.content.Intent();
        v0_1.setType("image/*");
        v0_1.setAction("android.intent.action.GET_CONTENT");
        int v1_8 = this.k;
        ref.WeakReference v2 = 0;
        if (v1_8 == 0) {
            v1_8 = 0;
        }
        int v1_2;
        int v1_1 = ((androidx.fragment.app.Fragment) v1_8.get());
        if (v1_1 == 0) {
            v1_2 = 0;
        } else {
            v1_2 = v1_1.getActivity();
        }
        if ((v1_2 != 0) && (!v1_2.isFinishing())) {
            if (v0_1.resolveActivity(v1_2.getPackageManager()) == null) {
                if (p5 != null) {
                    p5.h(ZF.b.c);
                    return;
                }
            } else {
                androidx.fragment.app.Fragment v5_1 = this.k;
                if (v5_1 != null) {
                    v2 = v5_1;
                }
                androidx.fragment.app.Fragment v5_3 = ((androidx.fragment.app.Fragment) v2.get());
                if (v5_3 != null) {
                    v5_3.startActivityForResult(v0_1, 24);
                    return;
                }
            }
        } else {
            if (p5 != null) {
                p5.h(ZF.b.c);
            }
        }
        return;
    }

    private final void Q0(org.json.JSONObject p4, MR.b p5)
    {
        androidx.fragment.app.FragmentActivity v4_0 = this.k;
        if (v4_0 == null) {
            v4_0 = 0;
        }
        androidx.fragment.app.FragmentActivity v4_2 = ((androidx.fragment.app.Fragment) v4_0.get());
        if (v4_2 != null) {
            androidx.fragment.app.FragmentActivity v4_3 = v4_2.requireActivity();
            if (v4_3 != null) {
                android.content.Intent v0_1 = new android.content.Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                v0_1.setData(android.net.Uri.fromParts("package", v4_3.getPackageName(), 0));
                v4_3.startActivity(v0_1);
            }
        }
        return;
    }

    private final void Q1(GX.b$a p2)
    {
        GX.b v0 = this.r;
        if (v0 != null) {
            v0.e(p2);
            v0.d(p2);
            v0.b();
        }
        return;
    }

    private final void R(org.json.JSONObject p3, MR.b p4)
    {
        if (p3 == null) {
            if (p4 != null) {
                p4.h(ZF.m.c);
            }
        } else {
            kotlin.Unit v0 = 0;
            try {
                this.g.b(p3.getString("appId")).edit().clear().apply();
            } catch (Exception) {
                if (p4 == null) {
                    if (v0 == null) {
                    }
                } else {
                    p4.h(ZF.m.c);
                    v0 = kotlin.Unit.INSTANCE;
                }
            }
            if (p4 == null) {
            } else {
                p4.b(Boolean.TRUE);
                v0 = kotlin.Unit.INSTANCE;
            }
        }
        return;
    }

    private final void R0(MR.b p5, java.util.List p6)
    {
        String v6_1;
        if (!VE.c.c(p6)) {
            if ((!(((Iterable) p6) instanceof java.util.Collection)) || (!((java.util.Collection) ((Iterable) p6)).isEmpty())) {
                org.json.JSONObject v0_1 = ((Iterable) p6).iterator();
                while (v0_1.hasNext()) {
                    if ((VE.c.d(p6)) && ((((HX.d) v0_1.next()) instanceof HX.a))) {
                        v6_1 = "PERMISSION_NOT_GRANTED";
                        if (p5 != null) {
                            org.json.JSONObject v0_3 = new org.json.JSONObject();
                            v0_3.put("user_allow", kotlin.jvm.internal.Intrinsics.areEqual(v6_1, "PERMISSION_ALWAYS"));
                            v0_3.put("detail", v6_1);
                            p5.b(v0_3);
                        }
                        return;
                    }
                }
            }
            v6_1 = "PERMISSION_DENIED";
        } else {
            v6_1 = "PERMISSION_ALWAYS";
        }
    }

    private final void R1(GX.b$a p2)
    {
        GX.b v0 = this.q;
        if (v0 != null) {
            v0.e(p2);
            v0.d(p2);
            v0.b();
        }
        return;
    }

    private final void S()
    {
        this.t = 0;
        this.u = 0;
        this.l = 0;
        return;
    }

    private final void S0(MR.b p5, java.util.List p6)
    {
        String v6_1;
        if (!VE.c.e(p6)) {
            if ((!(((Iterable) p6) instanceof java.util.Collection)) || (!((java.util.Collection) ((Iterable) p6)).isEmpty())) {
                org.json.JSONObject v0_1 = ((Iterable) p6).iterator();
                while (v0_1.hasNext()) {
                    if ((VE.c.f(p6)) && ((((HX.d) v0_1.next()) instanceof HX.a))) {
                        v6_1 = "PERMISSION_NOT_GRANTED";
                        if (p5 != null) {
                            org.json.JSONObject v0_3 = new org.json.JSONObject();
                            v0_3.put("user_allow", kotlin.jvm.internal.Intrinsics.areEqual(v6_1, "PERMISSION_ALWAYS"));
                            v0_3.put("detail", v6_1);
                            p5.b(v0_3);
                        }
                        return;
                    }
                }
            }
            v6_1 = "PERMISSION_DENIED";
        } else {
            v6_1 = "PERMISSION_ALWAYS";
        }
    }

    private final void S1(android.location.Location p5, MR.b p6)
    {
        if (p5 != null) {
            this.c2(p6, new eR.g(String.valueOf(p5.getLatitude()), String.valueOf(p5.getLongitude())));
            return;
        } else {
            if (p6 != null) {
                p6.h(ZF.j.a);
            }
            return;
        }
    }

    private final android.content.Intent T(boolean p6)
    {
        String v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        String v0_9 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_9 != null) {
            String v0_10 = v0_9.requireActivity();
            if (v0_10 != null) {
                String v0_1 = v0_10.getPackageManager();
                if (v0_1 != null) {
                    android.content.Context v2_0 = this.k;
                    if (v2_0 == null) {
                        v2_0 = 0;
                    }
                    android.content.Context v2_2 = ((androidx.fragment.app.Fragment) v2_0.get());
                    if (v2_2 != null) {
                        android.content.Context v2_3 = v2_2.requireActivity();
                        if (v2_3 != null) {
                            android.content.Context v2_4 = v2_3.getBaseContext();
                            if (v2_4 != null) {
                                android.content.Intent v3_1 = new android.content.Intent("android.media.action.IMAGE_CAPTURE");
                                if (v3_1.resolveActivity(v0_1) != null) {
                                    try {
                                        int v1_1 = this.U();
                                    } catch (String v0_3) {
                                        java.io.File v4_1 = this.k;
                                        if (v4_1 == null) {
                                            v4_1 = v1_1;
                                        }
                                        java.io.File v4_3 = ((androidx.fragment.app.Fragment) v4_1.get());
                                        if (v4_3 == null) {
                                            java.io.File v4_4 = v1_1;
                                        } else {
                                            v4_4 = v4_3.requireActivity();
                                        }
                                        com.google.android.gms.internal.mlkit_vision_barcode.h7.e(v4_4, v0_3.getMessage());
                                    }
                                    if (v1_1 != 0) {
                                        String v0_8 = androidx.core.content.FileProvider.getUriForFile(v2_4, v2_4.getString(2131952832), v1_1);
                                        if (p6 != null) {
                                            v3_1.putExtra("android.intent.extras.CAMERA_FACING", 1);
                                            v3_1.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                                            v3_1.putExtra("android.intent.extra.USE_FRONT_CAMERA", 1);
                                        }
                                        v3_1.putExtra("output", v0_8);
                                    }
                                }
                                return v3_1;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private final void T0(MR.b p2, java.util.List p3, MD.j p4)
    {
        org.json.JSONObject v4_4;
        if (!p4.e()) {
            v4_4 = VE.c.g(p3);
        } else {
            v4_4 = VE.c.h(p3);
        }
        String v3_1;
        if (v4_4 == null) {
            if (!VE.c.k(p3)) {
                v3_1 = "PERMISSION_DENIED";
            } else {
                v3_1 = "PERMISSION_NOT_GRANTED";
            }
        } else {
            v3_1 = "PERMISSION_ALWAYS";
        }
        if (p2 != null) {
            org.json.JSONObject v4_3 = new org.json.JSONObject();
            v4_3.put("permissionStatus", v3_1);
            p2.b(v4_3);
        }
        return;
    }

    public static synthetic void T1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, android.location.Location p1, MR.b p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p2 = p0.l;
        }
        p0.S1(p1, p2);
        return;
    }

    private final java.io.File U()
    {
        java.io.File v0_5 = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", java.util.Locale.US).format(Long.valueOf(System.currentTimeMillis()));
        String v1_1 = this.k;
        java.io.File v2_0 = 0;
        if (v1_1 == null) {
            v1_1 = 0;
        }
        String v1_3 = ((androidx.fragment.app.Fragment) v1_1.get());
        if (v1_3 != null) {
            String v1_4 = v1_3.requireActivity();
            if (v1_4 != null) {
                v2_0 = v1_4.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
            }
        }
        String v1_6 = new StringBuilder("JPEG_");
        v1_6.append(v0_5);
        v1_6.append("_");
        java.io.File v0_4 = java.io.File.createTempFile(v1_6.toString(), ".jpg", v2_0);
        this.s = v0_4;
        return v0_4;
    }

    private final void U0(org.json.JSONObject p10, MR.b p11, sF.d p12, kotlin.jvm.functions.Function0 p13)
    {
        int v1_3;
        if (p10 == 0) {
            v1_3 = 0;
        } else {
            v1_3 = p10.optBoolean("showPopup", 0);
        }
        if (v1_3 == 0) {
            p13.invoke();
            return;
        } else {
            int v1_1;
            if (p10 == 0) {
                v1_1 = p12.a;
            } else {
                v1_1 = p10.optString("title", p12.a);
                if (v1_1 == 0) {
                }
            }
            String v3 = v1_1;
            int v1_4;
            if (p10 == 0) {
                v1_4 = p12.b;
            } else {
                v1_4 = p10.optString("message", p12.b);
                if (v1_4 == 0) {
                }
            }
            String v4 = v1_4;
            String v6;
            if (p10 == 0) {
                v6 = "Cho ph\u00e9p";
            } else {
                int v1_6 = p10.optString("primaryButtonText", "Cho ph\u00e9p");
                if (v1_6 != 0) {
                    v6 = v1_6;
                }
            }
            String v5;
            if (p10 == 0) {
                v5 = "T\u1eeb ch\u1ed1i";
            } else {
                int v10_1 = p10.optString("secondaryButtonText", "T\u1eeb ch\u1ed1i");
                if (v10_1 != 0) {
                    v5 = v10_1;
                }
            }
            int v10_3 = new org.json.JSONObject();
            v10_3.put("user_allow", 0);
            this.m.j(new sF.b(new sF.e(v3, v4, v5, v6, new nK.F(p11, v10_3, 3), new oM.g(21, p13))));
            return;
        }
    }

    private final void U1(android.location.Location p13, MD.j p14, MR.b p15, java.util.List p16)
    {
        if (p13 != null) {
            Am.a v0_0 = this.k;
            if (v0_0 == null) {
                v0_0 = 0;
            }
            Am.a v0_5 = ((androidx.fragment.app.Fragment) v0_0.get());
            if (v0_5 != null) {
                Am.a v0_6 = v0_5.getActivity();
                if (v0_6 != null) {
                    android.content.Context v7 = v0_6.getBaseContext();
                    if (v7 != null) {
                        MD.i.a(v7, p13.getLatitude(), p13.getLongitude(), new Am.a(p13, this, p15, p16, p14, 9));
                        return;
                    }
                }
            }
            if (p15 != null) {
                p15.d();
            }
        } else {
            if (p15 != null) {
                p15.h(ZF.k.d);
                return;
            }
        }
        return;
    }

    private final void V(String p3)
    {
        androidx.fragment.app.Fragment v0_0 = this.k;
        android.content.Context v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.Fragment v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            v1 = v0_2.requireContext();
        }
        int v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.v0.d(v1, p3);
        if (v3_1 == 0) {
            this.X(0);
            return;
        } else {
            this.k2(v3_1);
            return;
        }
    }

    private static final kotlin.Unit V0(MR.b p0, org.json.JSONObject p1)
    {
        if (p0 != null) {
            p0.b(p1);
        }
        return kotlin.Unit.INSTANCE;
    }

    public static synthetic void V1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, android.location.Location p1, MD.j p2, MR.b p3, java.util.List p4, int p5, Object p6)
    {
        if ((p5 & 4) != 0) {
            p3 = p0.l;
        }
        p0.U1(p1, p2, p3, p4);
        return;
    }

    public static synthetic void W(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, String p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.t;
        }
        p0.V(p1);
        return;
    }

    private static final kotlin.Unit W0(kotlin.jvm.functions.Function0 p0)
    {
        p0.invoke();
        return kotlin.Unit.INSTANCE;
    }

    private static final kotlin.Unit W1(android.location.Location p15, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p16, MR.b p17, java.util.List p18, MD.j p19, java.util.List p20)
    {
        if ((p20 != null) && ((((java.util.Collection) p20).isEmpty() ^ 1) == 1)) {
            MR.b v0_1 = ((android.location.Address) kotlin.collections.CollectionsKt.first(p20));
            double v2 = p15.getLatitude();
            double v4 = p15.getLongitude();
            String v6 = v0_1.getLocality();
            String v7_0 = "";
            if (v6 == null) {
                v6 = v0_1.getAdminArea();
                if (v6 == null) {
                    v6 = "";
                }
            }
            String v8_0 = v0_1.getSubAdminArea();
            if (v8_0 == null) {
                v8_0 = "";
            }
            String v9_0 = v0_1.getSubLocality();
            if (v9_0 == null) {
                v9_0 = "";
            }
            String v10_0 = v0_1.getThoroughfare();
            if (v10_0 == null) {
                v10_0 = "";
            }
            MR.b v0_3 = v0_1.getCountryCode();
            if (v0_3 != null) {
                v7_0 = v0_3;
            }
            p16.c2(p17, new eR.h(v2, v4, v6, v8_0, v9_0, v10_0, v7_0, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.X1(p18, p19), ((double) p15.getTime())));
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void X(boolean p2)
    {
        if (p2 != 1) {
            MR.b v2_6;
            MR.b v2_4 = this.u;
            if (v2_4 != null) {
                v2_6 = rF.i.a[v2_4.ordinal()];
            } else {
                v2_6 = -1;
            }
            if (v2_6 != 2) {
                this.a2(this.l, ZF.h.f);
            } else {
                this.a2(this.l, ZF.h.d);
            }
        } else {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.d2(this, 0, 0, 3, 0);
        }
        this.S();
        return;
    }

    private final void X0(org.json.JSONObject p3, MR.b p4)
    {
        if (p3 == 0) {
            this.h2(0);
        } else {
            try {
                this.h2(p3.optInt("constant"));
            } catch (Exception) {
                this.h2(0);
            }
        }
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.d2(this, p4, 0, 2, 0);
        return;
    }

    private static final String X1(java.util.List p1, MD.j p2)
    {
        if ((!VE.c.h(p1)) || (!VE.c.g(p1))) {
            if (!p2.h()) {
                return "reducedAccuracy";
            } else {
                return "Approximate".toLowerCase(java.util.Locale.ROOT);
            }
        } else {
            if (!p2.h()) {
                return "fullAccuracy";
            } else {
                return "Precise".toLowerCase(java.util.Locale.ROOT);
            }
        }
    }

    private final void Y(String p13)
    {
        if (p13 != 0) {
            String v4 = kotlin.text.StringsKt.H(p13, "/");
            if (v4 != null) {
                androidx.lifecycle.A v0_5 = this.k;
                if (v0_5 == null) {
                    v0_5 = 0;
                }
                androidx.lifecycle.A v0_2 = ((androidx.fragment.app.Fragment) v0_5.get());
                if (v0_2 == null) {
                    return;
                } else {
                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_2.getLifecycle()), this.b.c, 0, new ey.c(this, p13, v4, 0, 13), 2, 0);
                    return;
                }
            }
        }
        this.X(0);
        return;
    }

    private final void Y0(org.json.JSONObject p8, MR.b p9)
    {
        int v8_0 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v8_0 == 0) {
            v8_0 = 0;
        }
        int v8_7 = v8_0.b;
        v8_7.getClass();
        q9.A0.a = new lO.o(26, v8_7);
        int v8_1 = this.k;
        if (v8_1 == 0) {
            v8_1 = 0;
        }
        int v8_3 = ((androidx.fragment.app.Fragment) v8_1.get());
        if (v8_3 != 0) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v8_3.getLifecycle()), this.b.a, 0, new pI.c(this, p9, 0, 4), 2, 0);
        }
        return;
    }

    public static synthetic void Z(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, String p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.t;
        }
        p0.Y(p1);
        return;
    }

    private final void Z0(org.json.JSONObject p2, MR.b p3)
    {
        this.Q1(new rF.d(this, p3, 3));
        return;
    }

    private final android.media.AudioManager a0()
    {
        return ((android.media.AudioManager) this.z.getValue());
    }

    private static final void a1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        p0.R0(p1, p2);
        return;
    }

    public static synthetic kotlin.Unit b(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, android.location.Location p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.j1(p0, p1, p2);
    }

    private final android.hardware.camera2.CameraManager b0()
    {
        return ((android.hardware.camera2.CameraManager) this.A.getValue());
    }

    private final void b1(org.json.JSONObject p2, MR.b p3)
    {
        this.R1(new rF.d(this, p3, 2));
        return;
    }

    public static synthetic void b2(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, QR.d p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.l;
        }
        if ((p3 & 2) != 0) {
            p2 = ZF.m.c;
        }
        p0.a2(p1, p2);
        return;
    }

    public static synthetic kotlin.Unit c(kotlin.jvm.functions.Function0 p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.W0(p0);
    }

    private final void c0(org.json.JSONObject p4, MR.b p5)
    {
        if (p4 == null) {
            if (p5 != null) {
                p5.h(ZF.m.c);
            }
        } else {
            kotlin.Unit v0 = 0;
            try {
                Object vtmp3 = this.g.a(p4.getString("appId"), p4.getString("key"));
            } catch (Exception) {
                if (p5 == null) {
                    if (v0 == null) {
                    }
                } else {
                    p5.h(ZF.m.c);
                    v0 = kotlin.Unit.INSTANCE;
                }
            }
            if (p5 == null) {
            } else {
                p5.b(vtmp3);
                v0 = kotlin.Unit.INSTANCE;
            }
        }
        return;
    }

    private static final void c1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        p0.S0(p1, p2);
        return;
    }

    public static synthetic void d(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.ArrayList p2)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.c1(p0, p1, p2);
        return;
    }

    private final void d1(MR.b p4, java.util.List p5)
    {
        String v5_2;
        if (!VE.c.i(p5)) {
            v5_2 = "PERMISSION_DENIED";
        } else {
            v5_2 = "PERMISSION_ALWAYS";
        }
        if (p4 != null) {
            org.json.JSONObject v1_1 = new org.json.JSONObject();
            v1_1.put("user_allow", kotlin.jvm.internal.Intrinsics.areEqual(v5_2, "PERMISSION_ALWAYS"));
            v1_1.put("detail", v5_2);
            p4.b(v1_1);
        }
        return;
    }

    public static synthetic void d2(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, eR.m p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.l;
        }
        if ((p3 & 2) != 0) {
            p2 = eR.j.a;
        }
        p0.c2(p1, p2);
        return;
    }

    public static synthetic kotlin.Unit e(androidx.fragment.app.FragmentActivity p0, String p1, String p2, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p3, MR.b p4, androidx.fragment.app.FragmentActivity p5, boolean p6)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.p0(p0, p1, p2, p3, p4, p5, p6);
    }

    private final void e0(org.json.JSONObject p10, MR.b p11)
    {
        if (p10 == null) {
            if (p11 != null) {
                p11.h(ZF.m.c);
            }
        } else {
            kotlin.Unit v0 = 0;
            try {
                String v1_3 = p10.getString("appId");
                org.json.JSONObject v10_5 = p10.getJSONArray("keys");
                org.json.JSONObject v2_2 = new org.json.JSONObject();
                org.json.JSONArray v3_1 = new org.json.JSONArray();
                int v4 = v10_5.length();
                int v5 = 0;
            } catch (Exception) {
                if (p11 == null) {
                    if (v0 == null) {
                    }
                } else {
                    p11.h(ZF.m.c);
                    v0 = kotlin.Unit.INSTANCE;
                }
            }
            while (v5 < v4) {
                Object v8;
                String v6 = v10_5.getString(v5);
                Object v7_1 = this.g.a(v1_3, v6);
                if (v7_1 != null) {
                    v8 = v7_1;
                } else {
                    v8 = "";
                }
                v2_2.put(v6, v8);
                if (v7_1 == null) {
                    v3_1.put(v6);
                }
                v5++;
            }
            org.json.JSONObject v10_2 = new org.json.JSONObject();
            v10_2.put("dataStorage", v2_2);
            v10_2.put("fallbackKeys", v3_1);
            if (p11 == null) {
            } else {
                p11.b(v10_2);
                v0 = kotlin.Unit.INSTANCE;
            }
        }
        return;
    }

    private final void e1(org.json.JSONObject p3, MR.b p4)
    {
        p3 = this.w;
        if (p3 == null) {
            if (p4 != null) {
                p4.d();
            }
            return;
        } else {
            p3.c();
            p3.d(new rF.d(this, p4, 0));
            p3.b();
            return;
        }
    }

    private final void e2(MR.b p2, boolean p3)
    {
        ZF.a v3_1 = this.T(p3);
        if (v3_1 != null) {
            androidx.fragment.app.Fragment v2_1 = this.k;
            if (v2_1 == null) {
                v2_1 = 0;
            }
            androidx.fragment.app.Fragment v2_3 = ((androidx.fragment.app.Fragment) v2_1.get());
            if (v2_3 != null) {
                v2_3.startActivityForResult(v3_1, 23);
            }
        } else {
            if (p2 != null) {
                p2.h(ZF.a.c);
                return;
            }
        }
        return;
    }

    public static synthetic void f(kotlin.jvm.functions.Function1 p0, java.util.ArrayList p1)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.E0(p0, p1);
        return;
    }

    private final void f0(org.json.JSONObject p11, MR.b p12)
    {
        float v11_2;
        if (p11 == 0) {
            v11_2 = 0;
        } else {
            float v11_3 = p11.toString();
            if (v11_3 == 0) {
            } else {
                v11_2 = ((eR.n$g) nY.h.b(v11_3, new vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule$handleBrightnessV2$$inlined$parseJson$1().getType()));
            }
        }
        if (v11_2 == 0) {
            v11_2 = 0;
        }
        if (v11_2 != 0) {
            int v3_0 = v11_2.a();
            if ((v3_0 == 0) || ((vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.P1(this, v3_0, 0, 0, 3, 0) ^ 1) != 1)) {
                float v11_5;
                float v11_4 = v11_2.a();
                if (v11_4 == 0) {
                    v11_5 = 0;
                } else {
                    v11_5 = Float.valueOf(v11_4.floatValue());
                }
                if (v11_5 != 0) {
                    this.l2(v11_5.floatValue());
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.d2(this, p12, 0, 2, 0);
                    return;
                } else {
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p12, 0, 2, 0);
                    return;
                }
            }
        }
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p12, 0, 2, 0);
        return;
    }

    private static final void f1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        p0.I0(p1, p2);
        return;
    }

    private final void f2(String p2)
    {
        androidx.fragment.app.FragmentActivity v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.FragmentActivity v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            androidx.fragment.app.FragmentActivity v0_3 = v0_2.getActivity();
            if (v0_3 != null) {
                q9.W4.b(v0_3, p2);
            }
        }
        return;
    }

    public static synthetic kotlin.Unit g(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, boolean p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.r0(p0, p1, p2);
    }

    private final void g0(MR.b p2)
    {
        java.util.ArrayList v0_0 = this.w;
        if (v0_0 == null) {
            if (p2 != null) {
                p2.d();
            }
            return;
        } else {
            this.I0(p2, v0_0.f());
            return;
        }
    }

    private final void g1(int p6, android.content.Intent p7)
    {
        if (p6 != null) {
            if (p7 != null) {
                String v7_2 = p7.getData();
                if (v7_2 != null) {
                    StringBuilder v0_7 = this.k;
                    String v1 = 0;
                    if (v0_7 == null) {
                        v0_7 = 0;
                    }
                    StringBuilder v0_2;
                    StringBuilder v0_1 = ((androidx.fragment.app.Fragment) v0_7.get());
                    if (v0_1 == null) {
                        v0_2 = 0;
                    } else {
                        v0_2 = v0_1.getContext();
                    }
                    if ((v0_2 != null) && (p6 == -1)) {
                        MR.b v6_2 = this.l;
                        try {
                            String v4_1 = v0_2.getContentResolver().getType(v7_2);
                        } catch (Exception) {
                            if (v1 == null) {
                                v1 = "";
                            }
                            this.c2(v6_2, new eR.c(v1));
                            return;
                        }
                        if (v4_1 == null) {
                            v4_1 = "image/jpeg";
                        }
                        try {
                            String v7_3 = v0_2.getContentResolver().openInputStream(v7_2);
                        } catch (Exception) {
                            String v7_5 = 0;
                            StringBuilder v0_6 = new StringBuilder("data:");
                            v0_6.append(v4_1);
                            v0_6.append(";base64,");
                            v0_6.append(v7_5);
                            v1 = v0_6.toString();
                        }
                        if (v7_3 == null) {
                        } else {
                            StringBuilder v0_4 = kotlin.io.ByteStreamsKt.readBytes(v7_3);
                            if (v0_4 != null) {
                                v7_3.close();
                                v7_5 = android.util.Base64.encodeToString(v0_4, 2);
                            } else {
                            }
                        }
                    }
                }
            }
            this.a2(this.l, ZF.b.d);
            return;
        } else {
            this.a2(this.l, ZF.b.e);
            return;
        }
    }

    private final void g2(Jw.d p2)
    {
        androidx.fragment.app.FragmentActivity v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.FragmentActivity v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            androidx.fragment.app.FragmentActivity v0_3 = v0_2.getActivity();
            if (v0_3 != null) {
                q9.W4.a(v0_3, p2);
            }
        }
        return;
    }

    public static synthetic kotlin.Unit h(android.location.Location p0, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p1, MR.b p2, java.util.List p3, MD.j p4, java.util.List p5)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.W1(p0, p1, p2, p3, p4, p5);
    }

    private final void h0(MR.b p3)
    {
        android.content.Context v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        android.content.Context v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            android.content.Context v0_3 = v0_2.getContext();
            if (v0_3 != null) {
                String v1_0 = this.x;
                if (v1_0 == null) {
                    this.J0(v0_3, p3, "PERMISSION_ALWAYS");
                    return;
                } else {
                    this.K0(v0_3, p3, v1_0.f());
                    return;
                }
            }
        }
        if (p3 != null) {
            p3.d();
        }
        return;
    }

    private final void h1(MR.b p6, java.util.List p7)
    {
        androidx.fragment.app.Fragment v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.Fragment v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            if (!VE.c.i(p7)) {
                if (p6 != null) {
                    p6.h(ZF.j.a);
                }
            } else {
                this.a.a(v0_2, MD.j.a, 0, new rF.c(this, p6, 0));
                return;
            }
        } else {
            if (p6 != null) {
                p6.d();
                return;
            }
        }
        return;
    }

    private final void h2(int p2)
    {
        android.view.View v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        android.view.View v0_4 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_4 != null) {
            android.view.View v0_5 = v0_4.getActivity();
            if (v0_5 != null) {
                v0_5.getWindow().getDecorView().performHapticFeedback(p2);
            }
        }
        return;
    }

    public static synthetic kotlin.Unit i(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.J1(p0, p1, p2);
    }

    private final void i0(org.json.JSONObject p1, MR.b p2)
    {
        java.util.ArrayList v1_0 = this.r;
        if (v1_0 == null) {
            if (p2 != null) {
                p2.d();
            }
            return;
        } else {
            this.R0(p2, v1_0.f());
            return;
        }
    }

    public static synthetic void i1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.l;
        }
        p0.h1(p1, p2);
        return;
    }

    private final void i2(org.json.JSONObject p4, MR.b p5)
    {
        if (p4 == null) {
            if (p5 != null) {
                p5.h(ZF.m.c);
            }
        } else {
            kotlin.Unit v0 = 0;
            try {
                this.g.c(p4.getString("appId"), p4.getString("key"));
            } catch (Exception) {
                if (p5 == null) {
                    if (v0 == null) {
                    }
                } else {
                    p5.h(ZF.m.c);
                    v0 = kotlin.Unit.INSTANCE;
                }
            }
            if (p5 == null) {
            } else {
                p5.b(Boolean.TRUE);
                v0 = kotlin.Unit.INSTANCE;
            }
        }
        return;
    }

    public static synthetic kotlin.Unit j(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.D1(p0, p1, p2);
    }

    private final void j0(org.json.JSONObject p1, MR.b p2)
    {
        java.util.ArrayList v1_0 = this.q;
        if (v1_0 == null) {
            if (p2 != null) {
                p2.d();
            }
            return;
        } else {
            this.S0(p2, v1_0.f());
            return;
        }
    }

    private static final kotlin.Unit j1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, android.location.Location p2)
    {
        p0.S1(p2, p1);
        return kotlin.Unit.INSTANCE;
    }

    private final void j2(org.json.JSONObject p8, MR.b p9)
    {
        if (p8 == null) {
            if (p9 != null) {
                p9.h(ZF.m.c);
            }
        } else {
            kotlin.Unit v0 = 0;
            try {
                String v1_2 = p8.getString("appId");
                org.json.JSONObject v8_6 = p8.getJSONArray("keys");
                org.json.JSONArray v2_2 = new org.json.JSONArray();
                int v3 = v8_6.length();
                int v4 = 0;
            } catch (Exception) {
                if (p9 == null) {
                    if (v0 == null) {
                    }
                } else {
                    p9.h(ZF.m.c);
                    v0 = kotlin.Unit.INSTANCE;
                }
            }
            while (v4 < v3) {
                this.g.c(v1_2, v8_6.getString(v4));
                v4++;
            }
            org.json.JSONObject vtmp5 = new org.json.JSONObject().put("errorKeys", v2_2);
            if (p9 == null) {
            } else {
                p9.b(vtmp5);
                v0 = kotlin.Unit.INSTANCE;
            }
        }
        return;
    }

    public static synthetic void k(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.ArrayList p2)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.f1(p0, p1, p2);
        return;
    }

    private final void k0(MR.b p4, java.util.List p5)
    {
        String v5_2;
        if (!VE.c.i(p5)) {
            if (!VE.c.k(p5)) {
                v5_2 = "PERMISSION_DENIED";
            } else {
                v5_2 = "PERMISSION_NOT_GRANTED";
            }
        } else {
            v5_2 = "PERMISSION_ALWAYS";
        }
        if (p4 != null) {
            org.json.JSONObject v0_2 = new org.json.JSONObject();
            v0_2.put("user_allow", kotlin.jvm.internal.Intrinsics.areEqual(v5_2, "PERMISSION_ALWAYS"));
            v0_2.put("detail", v5_2);
            p4.b(v0_2);
        }
        return;
    }

    private final void k1(MR.b p10, java.util.List p11, MD.j p12, boolean p13)
    {
        androidx.fragment.app.Fragment v0_0 = this.k;
        EF.l v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.Fragment v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            if (!VE.c.i(p11)) {
                if ((!(((Iterable) p11) instanceof java.util.Collection)) || (!((java.util.Collection) ((Iterable) p11)).isEmpty())) {
                    ZF.k v10_5 = ((Iterable) p11).iterator();
                    while (v10_5.hasNext()) {
                        kotlin.jvm.internal.Intrinsics.areEqual(((HX.d) v10_5.next()).a(), "android.permission.ACCESS_COARSE_LOCATION");
                    }
                }
                if (p10 != null) {
                    p10.h(ZF.k.c);
                }
            } else {
                MD.g v2_0 = this.a;
                if (p13 == 0) {
                    v1_0 = new EF.l(p10, 2);
                }
                v2_0.a(v0_2, p12, v1_0, new Cm.d(this, p12, p10, p11, 18));
                return;
            }
        } else {
            if (p10 != null) {
                p10.d();
                return;
            }
        }
        return;
    }

    private final void k2(java.io.File p7)
    {
        String v0_0 = 0;
        try {
            android.content.ContentResolver v1_0 = this.k;
        } catch (Exception) {
            int v7_1 = p7.delete();
            if (v0_0 != null) {
                if (v0_0.length() != 0) {
                    if (v7_1 != 0) {
                        this.X(1);
                        return;
                    }
                }
            }
            this.X(0);
            return;
        }
        if (v1_0 == null) {
            v1_0 = 0;
        }
        android.content.ContentResolver v1_1;
        android.content.ContentResolver v1_3 = ((androidx.fragment.app.Fragment) v1_0.get());
        if (v1_3 == null) {
            v1_1 = 0;
        } else {
            android.content.ContentResolver v1_4 = v1_3.getActivity();
            if (v1_4 == null) {
            } else {
                v1_1 = v1_4.getContentResolver();
            }
        }
        String v2 = p7.getAbsolutePath();
        String v3 = p7.getName();
        int v4_0 = this.k;
        if (v4_0 == 0) {
            v4_0 = 0;
        }
        int v4_3;
        int v4_2 = ((androidx.fragment.app.Fragment) v4_0.get());
        if (v4_2 == 0) {
            v4_3 = 0;
        } else {
            v4_3 = v4_2.getString(2131955799);
        }
        v0_0 = android.provider.MediaStore$Images$Media.insertImage(v1_1, v2, v3, v4_3);
    }

    public static synthetic kotlin.Unit l(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.L1(p0, p1, p2);
    }

    private final void l0(org.json.JSONObject p1, MR.b p2)
    {
        this.l = p2;
        this.Q(p2);
        return;
    }

    public static synthetic void l1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2, MD.j p3, boolean p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.l;
        }
        if ((p5 & 8) != 0) {
            p4 = 1;
        }
        p0.k1(p1, p2, p3, p4);
        return;
    }

    private final void l2(float p9)
    {
        Is.a v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        Is.a v0_3 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_3 != null) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_3.getLifecycle()), this.b.a, 0, new rF.j(this, p9, 0), 2, 0);
        }
        return;
    }

    public static synthetic void m(kotlin.jvm.functions.Function2 p0, MD.j p1, java.util.ArrayList p2)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.H0(p0, p1, p2);
        return;
    }

    private final void m0(org.json.JSONObject p17, MR.b p18)
    {
        if (p17 != null) {
            String v2_2 = this.k;
            if (v2_2 == null) {
                v2_2 = 0;
            }
            String v2_0 = ((androidx.fragment.app.Fragment) v2_2.get());
            if (v2_0 != null) {
                String v2_1 = v2_0.getActivity();
                if (v2_1 != null) {
                    try {
                        String v5 = String.valueOf(p17.getInt("appId"));
                        String v4_3 = p17.getString("label");
                        ZF.m v0_1 = p17.getString("imageUrl");
                        int v8_1 = new StringBuilder("zalopay://launch/app/");
                        v8_1.append(v5);
                        android.content.Intent v6_5 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(v8_1.toString())).setPackage("vn.com.vng.zalopay").setClass(v2_1, vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2).setFlags(67108864);
                        li.e v10 = gi.L.a(kotlinx.coroutines.Dispatchers.getMain());
                        B2.I v13 = new B2.I;
                        v13(v2_1, v0_1, p18, v4_3, v5, v6_5, 0, 21);
                        kotlinx.coroutines.BuildersKt.launch$default(v10, 0, 0, v13, 3, 0);
                        return;
                    } catch (Exception) {
                        if (p18 == null) {
                            return;
                        } else {
                            p18.h(ZF.m.c);
                            return;
                        }
                    }
                }
            }
            if (p18 != null) {
                p18.d();
            }
        } else {
            if (p18 != null) {
                p18.h(ZF.m.c);
                return;
            }
        }
        return;
    }

    private static final kotlin.Unit m1(MR.b p0, android.content.IntentSender p1)
    {
        if (p0 != null) {
            p0.h(ZF.k.e);
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void m2(org.json.JSONObject p5, MR.b p6)
    {
        if (p5 == null) {
            if (p6 != null) {
                p6.h(ZF.m.c);
            }
        } else {
            kotlin.Unit v0 = 0;
            try {
                this.g.d(p5.getString("appId"), p5.getString("key"), p5.getString("value"));
            } catch (Exception) {
                if (p6 == null) {
                    if (v0 == null) {
                    }
                } else {
                    p6.h(ZF.m.c);
                    v0 = kotlin.Unit.INSTANCE;
                }
            }
            if (p6 == null) {
            } else {
                p6.b(Boolean.TRUE);
                v0 = kotlin.Unit.INSTANCE;
            }
        }
        return;
    }

    public static synthetic kotlin.Unit n(MR.b p0, android.content.IntentSender p1)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.m1(p0, p1);
    }

    private final void n0(org.json.JSONObject p10, MR.b p11)
    {
        String v3;
        int v0_0 = "";
        if (p10 == null) {
            v3 = "";
        } else {
            androidx.fragment.app.Fragment v10_5 = p10.optString("url", "");
            if (v10_5 != null) {
                v3 = v10_5;
            }
        }
        try {
            androidx.fragment.app.Fragment v10_1 = android.net.Uri.parse(v3).getPath();
        } catch (Exception) {
            int v4_1 = v0_0;
            int v0_1 = 0;
            if (!kotlin.text.StringsKt.isBlank(v3)) {
                if (!kotlin.text.StringsKt.isBlank(v4_1)) {
                    androidx.fragment.app.Fragment v10_4 = this.k;
                    if (v10_4 != null) {
                        v0_1 = v10_4;
                    }
                    androidx.fragment.app.Fragment v10_7 = ((androidx.fragment.app.Fragment) v0_1.get());
                    if (v10_7 != null) {
                        androidx.fragment.app.FragmentActivity v2_4 = v10_7.getActivity();
                        if (v2_4 != null) {
                            this.N(new c00.a(v2_4, v3, v4_1, this, p11, v2_4, 3));
                            return;
                        }
                    }
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.o0(this, p11);
                    return;
                }
            }
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p11, 0, 2, 0);
            return;
        }
        if (v10_1 == null) {
            v10_1 = "";
        }
        c00.a v1_1 = kotlin.text.StringsKt.n(v10_1, 47, 0, 6);
        if ((v1_1 == -1) || (v1_1 >= (v10_1.length() - 1))) {
        } else {
            v0_0 = v10_1.substring((v1_1 + 1));
        }
    }

    private static final kotlin.Unit n1(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MD.j p1, MR.b p2, java.util.List p3, android.location.Location p4)
    {
        p0.U1(p4, p1, p2, p3);
        return kotlin.Unit.INSTANCE;
    }

    private final void n2(boolean p9)
    {
        android.hardware.camera2.CameraManager v0_0 = this.k;
        String v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        android.hardware.camera2.CameraManager v0_1;
        android.hardware.camera2.CameraManager v0_8 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_8 == null) {
            v0_1 = 0;
        } else {
            v0_1 = v0_8.requireContext();
        }
        android.hardware.camera2.CameraManager v0_3;
        int v2 = 0;
        if (v0_1 != null) {
            v0_3 = v0_1.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        } else {
            v0_3 = 0;
        }
        try {
            if (v0_3 != null) {
                android.hardware.camera2.CameraManager v0_4 = this.b0();
                if (v0_4 != null) {
                    try {
                        String[] v3_1 = v0_4.getCameraIdList();
                    } catch (android.hardware.camera2.CameraManager v0_5) {
                        v0_5.toString();
                    }
                    if (v3_1 != null) {
                        while (v2 < v3_1.length) {
                            String v5 = v3_1[v2];
                            int v6_2 = ((Integer) v0_4.getCameraCharacteristics(v5).get(android.hardware.camera2.CameraCharacteristics.LENS_FACING));
                            if (v6_2 != 0) {
                                if (v6_2.intValue() == 1) {
                                    v1 = v5;
                                    break;
                                }
                            } else {
                            }
                            v2++;
                        }
                    } else {
                    }
                }
                if (v1 != null) {
                    android.hardware.camera2.CameraManager v0_6 = this.b0();
                    if (v0_6 != null) {
                        v0_6.setTorchMode(v1, p9);
                    }
                }
            }
        } catch (android.hardware.camera2.CameraAccessException) {
        }
        return;
    }

    public static synthetic kotlin.Unit o(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MD.j p1, MR.b p2, java.util.List p3, android.location.Location p4)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.n1(p0, p1, p2, p3, p4);
    }

    private static final void o0(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p1, MR.b p2)
    {
        p1.a2(p2, ZF.g.c);
        return;
    }

    private final void o1(int p8)
    {
        if (p8 != null) {
            String v0_0 = this.s;
            if ((p8 != -1) || (v0_0 == null)) {
                this.a2(this.l, ZF.a.d);
                return;
            } else {
                MR.b v8_3 = this.l;
                try {
                    String v0_2 = android.graphics.BitmapFactory.decodeFile(v0_0.getAbsolutePath());
                    StringBuilder v4_1 = new java.io.ByteArrayOutputStream();
                    v0_2.compress(android.graphics.Bitmap$CompressFormat.JPEG, 80, v4_1);
                    String v0_3 = v4_1.toByteArray();
                    v4_1.close();
                    String v0_4 = android.util.Base64.encodeToString(v0_3, 2);
                    try {
                        StringBuilder v4_4 = new StringBuilder("data:image/jpeg;base64,");
                        v4_4.append(v0_4);
                        String v0_5 = v4_4.toString();
                    } catch (Exception) {
                        v0_5 = 0;
                    }
                    if (v0_5 == null) {
                        v0_5 = "";
                    }
                    this.c2(v8_3, new eR.l(v0_5));
                    this.s = 0;
                    return;
                } catch (Exception) {
                    v0_4 = 0;
                }
            }
        } else {
            this.a2(this.l, ZF.a.e);
            return;
        }
    }

    private final void o2(org.json.JSONObject p8, MR.b p9)
    {
        if (p8 == null) {
            if (p9 != null) {
                p9.h(ZF.m.c);
            }
        } else {
            kotlin.Unit v0 = 0;
            try {
                String v1_2 = p8.getString("appId");
                org.json.JSONObject v8_5 = p8.getJSONObject("data");
                org.json.JSONArray v2_2 = new org.json.JSONArray();
                java.util.Iterator v3 = v8_5.keys();
            } catch (Exception) {
                if (p9 == null) {
                    if (v0 == null) {
                    }
                } else {
                    p9.h(ZF.m.c);
                    v0 = kotlin.Unit.INSTANCE;
                }
            }
            while (v3.hasNext()) {
                String v4_2 = ((String) v3.next());
                this.g.d(v1_2, v4_2, v8_5.getString(v4_2));
            }
            org.json.JSONObject v8_2 = new org.json.JSONObject();
            v8_2.put("errorKeys", v2_2);
            if (p9 == null) {
            } else {
                p9.b(v8_2);
                v0 = kotlin.Unit.INSTANCE;
            }
        }
        return;
    }

    public static synthetic kotlin.Unit p(org.json.JSONObject p0, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p1, MR.b p2, java.util.List p3, MD.j p4)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.K1(p0, p1, p2, p3, p4);
    }

    private static final kotlin.Unit p0(androidx.fragment.app.FragmentActivity p8, String p9, String p10, vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p11, MR.b p12, androidx.lifecycle.F p13, boolean p14)
    {
        if (p14 == 0) {
            p11.a2(p12, ZF.g.d);
        } else {
            int v10_1 = new androidx.work.h(com.alibaba.ariver.commonability.device.jsapi.clipboard.a.r("url", p9, "filename", p10));
            androidx.work.h.c(v10_1);
            String v9_6 = ((androidx.work.t) ((androidx.work.t) new androidx.work.t(vn.com.vng.zalopay.miniapp.worker_manager.DownloadWorker).g(v10_1)).a("download_file"));
            int v10_3 = v9_6.c;
            v10_3.q = 1;
            v10_3.r = 1;
            String v9_8 = ((androidx.work.u) v9_6.b());
            CS.c v1_0 = V3.l.e(p8);
            v1_0.getClass();
            androidx.lifecycle.F v4_0 = java.util.Collections.singletonList(v9_8);
            if (v4_0.isEmpty()) {
                throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
            } else {
                new V3.e(v1_0, 0, 2, v4_0, 0).a();
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.j(p8), 0, 0, new CS.c(p8, v9_8.a, p13, p12, p11, 0), 3, 0);
            }
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void p1(boolean p3, android.content.Intent p4)
    {
        if ((p3 != null) && (p4 != null)) {
            ZF.p v4_2 = p4.getStringArrayExtra("codes");
            org.json.JSONObject v0_1 = new org.json.JSONObject();
            v0_1.put("codes", new org.json.JSONArray(v4_2));
            MR.b v3_1 = this.l;
            if (v3_1 != null) {
                v3_1.b(v0_1);
                return;
            }
        } else {
            MR.b v3_2 = this.l;
            if (v3_2 != null) {
                v3_2.h(ZF.p.c);
            }
        }
        return;
    }

    private final void p2(double p5)
    {
        android.media.AudioManager v0 = this.a0();
        if (v0 != null) {
            v0.setStreamVolume(3, Kg.c.a((p5 * ((double) v0.getStreamMaxVolume(3)))), 16);
        }
        return;
    }

    public static synthetic android.media.AudioManager q(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.K(p0);
    }

    private final void q0(org.json.JSONObject p8, MR.b p9)
    {
        String v2_5;
        if (p8 == null) {
            v2_5 = 0;
        } else {
            v2_5 = p8.optString("type");
        }
        ZF.f.a.getClass();
        int v0_2 = d6.u.i(v2_5);
        if (v0_2 != 0) {
            rF.c v8_1;
            if (p8 == null) {
                v8_1 = 0;
            } else {
                v8_1 = p8.optString(v0_2.name().toLowerCase(java.util.Locale.ROOT));
            }
            String v2_2 = v0_2.ordinal();
            if (v2_2 == null) {
                try {
                    if ((v8_1 != null) && (!kotlin.text.StringsKt.isBlank(v8_1))) {
                        String v2_4 = android.net.Uri.parse(v8_1);
                        if (v2_4.getHost() != null) {
                            String v2_6 = v2_4.getScheme();
                            if ((v2_6 != null) && (kotlin.text.y.startsWith$default(v2_6, "http", 0, 2, 0) == 1)) {
                                this.u = v0_2;
                                this.t = v8_1;
                                this.l = p9;
                                this.N(new rF.c(this, p9, 1));
                                return;
                            }
                        }
                    }
                } catch (Exception) {
                }
                this.a2(p9, ZF.h.c);
                return;
            } else {
                if (v2_2 != 1) {
                    throw new kotlin.NoWhenBranchMatchedException();
                }
            }
            this.u = v0_2;
            this.t = v8_1;
            this.l = p9;
            this.N(new rF.c(this, p9, 1));
            return;
        } else {
            this.a2(p9, ZF.h.e);
            return;
        }
    }

    private final void q1(boolean p2, android.content.Intent p3)
    {
        if (p2 != null) {
            String v2_5;
            if (p3 == null) {
                v2_5 = 0;
            } else {
                v2_5 = p3.getStringArrayExtra("resultStr");
            }
            String v3_1 = "";
            if ((v2_5 != null) && (v2_5.length != 0)) {
                String v2_2 = ((String) kotlin.collections.y.firstOrNull(v2_5));
                if (v2_2 != null) {
                    v3_1 = v2_2;
                }
            }
            String v2_3 = this.l;
            if (v2_3 != null) {
                v2_3.b(v3_1);
            }
        } else {
            String v2_4 = this.l;
            if (v2_4 != null) {
                v2_4.d();
                return;
            }
        }
        return;
    }

    private final void q2(android.net.Uri p4)
    {
        Yv.i v0 = this.d;
        androidx.fragment.app.Fragment v1_0 = this.k;
        androidx.fragment.app.FragmentActivity v2 = 0;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        androidx.fragment.app.Fragment v1_2 = ((androidx.fragment.app.Fragment) v1_0.get());
        if (v1_2 != null) {
            v2 = v1_2.getActivity();
        }
        q9.X4.b(v0, v2, p4);
        return;
    }

    public static synthetic kotlin.Unit r(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.F1(p0, p1, p2);
    }

    private static final kotlin.Unit r0(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, boolean p2)
    {
        if (!p2) {
            if (p1 != null) {
                p1.h(ZF.m.c);
            }
        } else {
            p0.r2();
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void r1(org.json.JSONObject p4, MR.b p5)
    {
        android.content.Intent v4_2;
        this.l = p5;
        ZF.p v5_1 = 0;
        try {
            if (p4 == null) {
                v4_2 = 0;
                androidx.fragment.app.Fragment v0_1 = this.k;
                if (v0_1 == null) {
                    v0_1 = 0;
                }
                androidx.fragment.app.Fragment v0_3 = ((androidx.fragment.app.Fragment) v0_1.get());
                if (v0_3 == null) {
                    android.content.Intent v4_3 = this.l;
                    if (v4_3 != null) {
                        v4_3.h(ZF.p.d);
                        return;
                    }
                } else {
                    String v1_0 = this.e;
                    if (v4_2 != null) {
                        v5_1 = v4_2.a();
                    }
                    v1_0.getClass();
                    android.content.Intent v4_5 = new android.content.Intent(v0_3.getContext(), vn.com.vng.zalopay.scancode.presentation.ScanCodeActivity);
                    v4_5.putExtra("types", v5_1);
                    v0_3.startActivityForResult(v4_5, 32);
                    return;
                }
            } else {
                android.content.Intent v4_6 = p4.toString();
                if (v4_6 == null) {
                } else {
                    v4_2 = ((eR.n$f) nY.h.b(v4_6, new vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule$handleScanCode$$inlined$parseJson$1().getType()));
                }
            }
        } catch (Exception) {
            android.content.Intent v4_7 = this.l;
            if (v4_7 != null) {
                v4_7.h(ZF.p.d);
            }
        }
        return;
    }

    private final void r2()
    {
        int v0_3;
        int v0_0 = this.u;
        if (v0_0 != 0) {
            v0_3 = rF.i.a[v0_0.ordinal()];
        } else {
            v0_3 = -1;
        }
        if (v0_3 == 1) {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.Z(this, 0, 1, 0);
            return;
        } else {
            if (v0_3 == 2) {
                vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.W(this, 0, 1, 0);
                return;
            } else {
                this.X(0);
                return;
            }
        }
    }

    public static synthetic kotlin.Unit s(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.List p2, MD.j p3)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.E1(p0, p1, p2, p3);
    }

    private final void s0(boolean p9)
    {
        java.util.ArrayList v0_2;
        if (this.y == null) {
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        if (p9 != 1) {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.t0(this, v0_2);
            return;
        } else {
            if (v0_2 == null) {
                int v2_0 = this.o;
                if (v2_0 == 0) {
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.t0(this, v0_2);
                    return;
                } else {
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.i1(this, 0, v2_0.f(), 1, 0);
                    return;
                }
            } else {
                vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule v9_2 = this.p;
                if (v9_2 == null) {
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.t0(this, v0_2);
                    return;
                } else {
                    java.util.ArrayList v3 = v9_2.f();
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule v9_3 = this.y;
                    if (v9_3 == null) {
                        v9_3 = MD.j.b;
                    }
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.l1(this, 0, v3, v9_3, 0, 9, 0);
                    return;
                }
            }
        }
    }

    private final void s1(MR.b p4)
    {
        this.l = p4;
        androidx.fragment.app.Fragment v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.Fragment v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        this.e.getClass();
        android.content.Intent v4_2 = new android.content.Intent(v0_2.getContext(), vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2);
        v4_2.putExtra("needResult", 1);
        v0_2.startActivityForResult(v4_2, 21);
        return;
    }

    public static synthetic void t(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, boolean p2, java.util.ArrayList p3)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.N0(p0, p1, p2, p3);
        return;
    }

    private static final void t0(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p1, boolean p2)
    {
        ZF.j v2_1;
        if (p2 == null) {
            v2_1 = ZF.j.a;
        } else {
            v2_1 = ZF.k.e;
        }
        p1.a2(p1.l, v2_1);
        return;
    }

    private final void t1(MR.b p3)
    {
        int v0_4 = bR.a.a(kotlin.collections.E.listOf(new String[] {"f", "qrcode"})));
        if (v0_4 != 0) {
            if (p3 != null) {
                p3.success();
            }
            this.q2(v0_4.build());
            return;
        } else {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p3, 0, 2, 0);
            return;
        }
    }

    public static synthetic android.hardware.camera2.CameraManager u(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.M(p0);
    }

    private final void u0(org.json.JSONObject p3, MR.b p4)
    {
        int v3_2;
        if (p3 == 0) {
            v3_2 = 0;
        } else {
            int v3_5 = p3.toString();
            if (v3_5 == 0) {
            } else {
                v3_2 = ((eR.n$h) nY.h.b(v3_5, new vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule$handleFlashlightV2$$inlined$parseJson$1().getType()));
            }
        }
        if (v3_2 == 0) {
            v3_2 = 0;
        }
        if (v3_2 != 0) {
            int v3_4;
            int v3_3 = v3_2.a();
            if (v3_3 == 0) {
                v3_4 = 0;
            } else {
                v3_4 = v3_3.booleanValue();
            }
            this.n2(v3_4);
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.d2(this, p4, 0, 2, 0);
            return;
        } else {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p4, 0, 2, 0);
            return;
        }
    }

    private final void u1(MR.b p9)
    {
        ZF.r v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        ZF.r v0_4 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_4 == null) {
            this.a2(p9, ZF.r.a);
            return;
        } else {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_4.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new lT.c(p9, this, 0, 23), 2, 0);
            return;
        }
    }

    public static synthetic kotlin.Unit v(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.G1(p0, p1, p2);
    }

    private final void v0(MR.b p9)
    {
        String v0_0 = this.k;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        String v0_1;
        String v0_5 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_5 == null) {
            v0_1 = 0;
        } else {
            v0_1 = v0_5.getContext();
        }
        if (v0_1 != null) {
            try {
                String v0_2 = v0_1.getPackageManager();
                org.json.JSONObject v1_3 = ((Iterable) v0_2.getInstalledApplications(128));
                java.util.Iterator v2_1 = new java.util.ArrayList();
                org.json.JSONObject v1_4 = v1_3.iterator();
            } catch (Exception) {
                this.a2(p9, ZF.i.a);
                return;
            }
            while (v1_4.hasNext()) {
                org.json.JSONArray v3_3 = v1_4.next();
                if (!kotlin.text.StringsKt.g(((android.content.pm.ApplicationInfo) v3_3).sourceDir, "/system/")) {
                    v2_1.add(v3_3);
                }
            }
            if (v2_1.isEmpty()) {
                this.a2(p9, ZF.i.a);
            }
            org.json.JSONObject v1_8 = new org.json.JSONObject();
            org.json.JSONArray v3_2 = new org.json.JSONArray();
            java.util.Iterator v2_2 = v2_1.iterator();
            while (v2_2.hasNext()) {
                String v4_2 = ((android.content.pm.ApplicationInfo) v2_2.next());
                org.json.JSONObject v5_1 = new org.json.JSONObject();
                v5_1.put("name", v4_2.loadLabel(v0_2).toString());
                v5_1.put("package_name", v4_2.packageName);
                String v4_6 = ((String) YF.a.a.get(Integer.valueOf(v4_2.category)));
                if (v4_6 == null) {
                    v4_6 = "CATEGORY_UNDEFINED";
                }
                v5_1.put("category", v4_6);
                v3_2.put(v5_1);
            }
            v1_8.put("apps", v3_2);
            if (p9 != 0) {
                p9.b(v1_8);
            }
            return;
        } else {
            vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, 0, 0, 3, 0);
            return;
        }
    }

    private final void v1(org.json.JSONObject p11, MR.b p12)
    {
        int v11_2;
        if (p11 == 0) {
            v11_2 = 0;
        } else {
            int v11_3 = p11.toString();
            if (v11_3 == 0) {
            } else {
                v11_2 = ((eR.n$i) nY.h.b(v11_3, new vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule$handleVolumeV2$$inlined$parseJson$1().getType()));
            }
        }
        if (v11_2 == 0) {
            v11_2 = 0;
        }
        if (v11_2 != 0) {
            double v3_0 = v11_2.a();
            if ((v3_0 == 0) || ((vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.P1(this, v3_0, 0, 0, 3, 0) ^ 1) != 1)) {
                int v11_5;
                int v11_4 = v11_2.a();
                if (v11_4 == 0) {
                    v11_5 = 0;
                } else {
                    v11_5 = Double.valueOf(v11_4.doubleValue());
                }
                if (v11_5 != 0) {
                    this.p2(v11_5.doubleValue());
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.d2(this, p12, 0, 2, 0);
                    return;
                } else {
                    vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p12, 0, 2, 0);
                    return;
                }
            }
        }
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p12, 0, 2, 0);
        return;
    }

    public static synthetic void w(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.ArrayList p2)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.x0(p0, p1, p2);
        return;
    }

    private final void w0(MR.b p3)
    {
        this.R1(new rF.d(this, p3, 1));
        return;
    }

    private final void w1()
    {
        a9.f v0_0 = this.k;
        GX.c v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        a9.f v0_3 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_3 != null) {
            GX.c v1_2 = new String[0];
            v1_0 = com.google.android.gms.internal.mlkit_vision_barcode.h5.d(v0_3, "android.permission.CAMERA", v1_2).g();
        }
        this.r = v1_0;
        return;
    }

    public static synthetic kotlin.Unit x(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.M1(p0, p1, p2);
    }

    private static final void x0(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p8, MR.b p9, java.util.List p10)
    {
        if (!VE.c.e(p10)) {
            if (p9 != null) {
                p9.h(ZF.j.a);
            }
        } else {
            Throwable v10_6;
            org.json.JSONObject v8_8 = p8.f.a;
            if (!com.google.android.gms.internal.mlkit_vision_barcode.u0.e(com.google.android.gms.internal.mlkit_vision_barcode.u0.c(v8_8, IE.a.b))) {
                v10_6 = 0;
            } else {
                v10_6 = new java.util.ArrayList();
                kotlin.Unit v1 = v8_8.getContentResolver();
                org.json.JSONObject v8_5 = v1.query(android.provider.ContactsContract$Contacts.CONTENT_URI, 0, 0, 0, 0);
                if (v8_5 != null) {
                    try {
                        if (v8_5.moveToFirst()) {
                            do {
                                boolean v2_4 = v8_5.getString(v8_5.getColumnIndex("_id"));
                                String v7 = v8_5.getString(v8_5.getColumnIndex("display_name"));
                                if (v8_5.getInt(v8_5.getColumnIndex("has_phone_number")) > 0) {
                                    boolean v2_6 = v1.query(android.provider.ContactsContract$CommonDataKinds$Phone.CONTENT_URI, 0, "contact_id = ?", new String[] {v2_4}), 0);
                                    if (v2_6) {
                                        if (v2_6.moveToFirst()) {
                                            kotlin.Unit v3_11 = v2_6.getString(v2_6.getColumnIndex("data1"));
                                            org.json.JSONObject v4_3 = new org.json.JSONObject();
                                            v4_3.put("name", v7);
                                            v4_3.put("phone", v3_11);
                                            v10_6.add(v4_3);
                                        }
                                        kotlin.io.CloseableKt.closeFinally(v2_6, 0);
                                    }
                                }
                            } while(v8_5.moveToNext());
                        }
                    } catch (Throwable v0_4) {
                        Throwable v9_2 = v0_4;
                        try {
                            throw v9_2;
                        } catch (Throwable v0_5) {
                            Throwable v10_10 = v0_5;
                            kotlin.io.CloseableKt.closeFinally(v8_5, v9_2);
                            throw v10_10;
                        }
                    }
                    kotlin.io.CloseableKt.closeFinally(v8_5, 0);
                }
            }
            if (v10_6 != null) {
                try {
                    Throwable v0_0 = android.util.Base64.encodeToString(new org.json.JSONArray(v10_6).toString().getBytes(kotlin.text.Charsets.UTF_8), 2);
                } catch (Exception) {
                }
                if (v0_0 != null) {
                    if (p9 != null) {
                        org.json.JSONObject v8_3 = new org.json.JSONObject();
                        v8_3.put("contact", v0_0);
                        p9.b(v8_3);
                    }
                } else {
                    if (p9 != null) {
                        p9.d();
                    }
                }
            } else {
                if (p9 != null) {
                    p9.h(ZF.j.a);
                    return;
                }
            }
        }
        return;
    }

    private final void x1()
    {
        a9.f v0_0 = this.k;
        GX.c v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        a9.f v0_3 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_3 != null) {
            GX.c v1_2 = new String[0];
            v1_0 = com.google.android.gms.internal.mlkit_vision_barcode.h5.d(v0_3, "android.permission.READ_CONTACTS", v1_2).g();
        }
        this.q = v1_0;
        return;
    }

    public static synthetic void y(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, MR.b p1, java.util.ArrayList p2)
    {
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.a1(p0, p1, p2);
        return;
    }

    private final void y0(MR.b p4)
    {
        ZF.d v0_1 = new org.json.JSONObject();
        String v1_1 = this.h.a();
        if (v1_1.length() != 0) {
            v0_1.put("deviceId", v1_1);
            if (p4 != null) {
                p4.b(v0_1);
            }
            return;
        } else {
            this.a2(p4, ZF.d.a);
            return;
        }
    }

    private final void y1()
    {
        a9.f v0_0 = this.k;
        GX.c v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        a9.f v0_3 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_3 != null) {
            v1_0 = com.google.android.gms.internal.mlkit_vision_barcode.h5.d(v0_3, "android.permission.ACCESS_FINE_LOCATION", new String[] {"android.permission.ACCESS_COARSE_LOCATION"})).g();
        }
        this.o = v1_0;
        return;
    }

    public static synthetic kotlin.Unit z(vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule p0, org.json.JSONObject p1, MR.b p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.I1(p0, p1, p2);
    }

    private final void z0(org.json.JSONObject p7, MR.b p8)
    {
        kotlin.Pair v0_7;
        sF.f.b.getClass();
        kotlin.NoWhenBranchMatchedException v7_10 = kc.a.f(p7);
        if (v7_10.contains(sF.f.c)) {
            kotlin.Pair v0_3 = this.k;
            if (v0_3 == null) {
                v0_3 = 0;
            }
            kotlin.Pair v0_5 = ((androidx.fragment.app.Fragment) v0_3.get());
            if (v0_5 == null) {
                v0_7 = 0;
            } else {
                kotlin.Pair v0_6 = v0_5.getActivity();
                if (v0_6 == null) {
                } else {
                    v0_7 = HF.b.i(v0_6);
                }
            }
        } else {
            if (!v7_10.contains(sF.f.d)) {
            } else {
            }
        }
        org.json.JSONObject v2_0 = new org.json.JSONObject();
        kotlin.NoWhenBranchMatchedException v7_9 = ((Iterable) v7_10).iterator();
        while (v7_9.hasNext()) {
            String v3_2 = ((sF.f) v7_9.next());
            String v3_3 = v3_2.a;
            int v5_0 = 0;
            switch (v3_2.ordinal()) {
                case 0:
                    this.i.d();
                    v2_0.put(v3_3, android.os.Build.MODEL);
                    break;
                case 1:
                    v2_0.put(v3_3, this.h.getLanguage());
                    break;
                case 2:
                    v2_0.put(v3_3, this.h.getAppVersion());
                    break;
                case 3:
                    v2_0.put(v3_3, q9.y6.g(this.h.b()));
                    break;
                case 4:
                    v2_0.put(v3_3, String.valueOf(this.j.b()));
                    break;
                case 5:
                    this.i.c();
                    v2_0.put(v3_3, android.os.Build$VERSION.RELEASE);
                    break;
                case 6:
                    this.h.getClass();
                    v2_0.put(v3_3, nY.y.a("android"));
                    break;
                case 7:
                    this.i.b();
                    v2_0.put(v3_3, android.os.Build.MANUFACTURER);
                    break;
                case 8:
                    float v4_14;
                    if (v0_7 == null) {
                        v4_14 = 0;
                    } else {
                        v4_14 = ((Integer) v0_7.getFirst());
                    }
                    if (v4_14 != 0) {
                        v5_0 = Kg.c.b((((float) v4_14.intValue()) / android.content.res.Resources.getSystem().getDisplayMetrics().density));
                    } else {
                    }
                    v2_0.put(v3_3, v5_0);
                    break;
                case 9:
                    float v4_9;
                    if (v0_7 == null) {
                        v4_9 = 0;
                    } else {
                        v4_9 = ((Integer) v0_7.getSecond());
                    }
                    if (v4_9 != 0) {
                        v5_0 = Kg.c.b((((float) v4_9.intValue()) / android.content.res.Resources.getSystem().getDisplayMetrics().density));
                    } else {
                    }
                    v2_0.put(v3_3, v5_0);
                    break;
                default:
                    throw new kotlin.NoWhenBranchMatchedException();
            }
        }
        if ((!(v2_0 instanceof ng.K)) && (p8 != null)) {
            p8.b(((org.json.JSONObject) v2_0));
        }
        if ((ng.L.a(v2_0) != null) && (p8 != null)) {
            p8.d();
        }
        return;
    }

    private final void z1(MD.j p4)
    {
        a9.f v4_3;
        GX.c v1 = 0;
        if (!p4.e()) {
            a9.f v4_7 = this.k;
            if (v4_7 == null) {
                v4_7 = 0;
            }
            a9.f v4_2 = ((androidx.fragment.app.Fragment) v4_7.get());
            if (v4_2 == null) {
                v4_3 = 0;
            } else {
                String[] v2_1 = new String[0];
                v4_3 = com.google.android.gms.internal.mlkit_vision_barcode.h5.d(v4_2, "android.permission.ACCESS_COARSE_LOCATION", v2_1);
            }
        } else {
            a9.f v4_4 = this.k;
            if (v4_4 == null) {
                v4_4 = 0;
            }
            a9.f v4_6 = ((androidx.fragment.app.Fragment) v4_4.get());
            if (v4_6 == null) {
            } else {
                v4_3 = com.google.android.gms.internal.mlkit_vision_barcode.h5.d(v4_6, "android.permission.ACCESS_FINE_LOCATION", new String[] {"android.permission.ACCESS_COARSE_LOCATION"}));
            }
        }
        if (v4_3 != null) {
            v1 = v4_3.g();
        }
        this.p = v1;
        return;
    }

    public final void L(ref.WeakReference p1)
    {
        this.k = p1;
        return;
    }

    public final void Y1(int p3, int p4, android.content.Intent p5)
    {
        int v0_1;
        if (p4 != -1) {
            v0_1 = 0;
        } else {
            v0_1 = 1;
        }
        if (p3 == 32) {
            this.p1(v0_1, p5);
            return;
        } else {
            switch (p3) {
                case 21:
                    this.q1(v0_1, p5);
                    return;
                case 22:
                    this.s0(v0_1);
                    return;
                case 23:
                    this.o1(p4);
                    return;
                case 24:
                    this.g1(p4, p5);
                    return;
                case 25:
                    this.s0(v0_1);
                    return;
                default:
                    return;
            }
        }
    }

    public void Z1()
    {
        return;
    }

    public void a(String p3, org.json.JSONObject p4, MR.b p5)
    {
        if (p3 != null) {
            switch (p3.hashCode()) {
                case -2015391848:
                    if (p3.equals("checkCameraPermissionV2")) {
                        this.i0(p4, p5);
                        return;
                    }
                case -1987940465:
                    if (p3.equals("downloadImageV2")) {
                        this.q0(p4, p5);
                        return;
                    }
                case -1956235029:
                    if (p3.equals("openCameraV2")) {
                        this.M0(p4, p5);
                        return;
                    }
                case -1760033721:
                    if (p3.equals("scanQRCodeV2")) {
                        this.s1(p5);
                        return;
                    }
                case -1742585193:
                    if (p3.equals("showScanQRV2")) {
                        this.t1(p5);
                        return;
                    }
                case -1643908049:
                    if (p3.equals("setBrightnessV2")) {
                        this.f0(p4, p5);
                        return;
                    }
                case -1544496140:
                    if (p3.equals("checkAppAvailability")) {
                        this.P(p4, p5);
                        return;
                    }
                case -1461874811:
                    if (p3.equals("checkPermissionContactV2")) {
                        this.j0(p4, p5);
                        return;
                    }
                case -1271387241:
                    if (p3.equals("clearData")) {
                        this.R(p4, p5);
                        return;
                    }
                case -1261136959:
                    if (p3.equals("checkMicrophonePermission")) {
                        this.g0(p5);
                        return;
                    }
                case -1107875961:
                    if (p3.equals("getDeviceId")) {
                        this.y0(p5);
                        return;
                    }
                case -1066742763:
                    if (p3.equals("setStorageV2")) {
                        this.o2(p4, p5);
                        return;
                    }
                case -1032947735:
                    if (p3.equals("openSettingsV2")) {
                        this.Q0(p4, p5);
                        return;
                    }
                case -966475416:
                    if (p3.equals("checkPermissionLocationV2")) {
                        this.D0(p5, new rF.c(this, p5, 3));
                        return;
                    }
                case -966475415:
                    if (p3.equals("checkPermissionLocationV3")) {
                        this.F0(p4, p5, 1, new rF.g(this, p5, 1));
                        return;
                    }
                case -899719298:
                    if (p3.equals("createShortcutV2")) {
                        this.m0(p4, p5);
                        return;
                    }
                case -891002358:
                    if (p3.equals("scanCode")) {
                        this.r1(p4, p5);
                        return;
                    }
                case -802694078:
                    if (p3.equals("checkNotificationPermission")) {
                        this.h0(p5);
                        return;
                    }
                case -792310048:
                    if (p3.equals("openPhoneV2")) {
                        this.O0(p4, p5);
                        return;
                    }
                case -702507064:
                    if (p3.equals("requestMicrophonePermission")) {
                        this.U0(p4, p5, sF.d.f, new rF.b(this, p4, p5, 1));
                        return;
                    }
                case -626798905:
                    if (p3.equals("playHapticV2")) {
                        this.X0(p4, p5);
                        return;
                    }
                case -626798904:
                    if (p3.equals("playHapticV3")) {
                        this.X0(p4, p5);
                        return;
                    }
                case -525000252:
                    if (p3.equals("setFlashlightV2")) {
                        this.u0(p4, p5);
                        return;
                    }
                case -494528973:
                    if (p3.equals("removeStorageV2")) {
                        this.j2(p4, p5);
                        return;
                    }
                case -405137411:
                    if (p3.equals("getContactLocalV2")) {
                        this.w0(p5);
                        return;
                    }
                case -399642040:
                    if (p3.equals("getDataByKey")) {
                        this.c0(p4, p5);
                        return;
                    }
                case 119550392:
                    if (p3.equals("setVolumeV2")) {
                        this.v1(p4, p5);
                        return;
                    }
                case 344806259:
                    if (p3.equals("getSystemInfo")) {
                        this.z0(p4, p5);
                        return;
                    }
                case 1006416993:
                    if (p3.equals("getAllInstalledApps")) {
                        this.v0(p5);
                        return;
                    }
                case 1108651556:
                    if (p3.equals("downloadFile")) {
                        this.n0(p4, p5);
                        return;
                    }
                case 1134244640:
                    if (p3.equals("chooseImageV2")) {
                        this.l0(p4, p5);
                        return;
                    }
                case 1149350588:
                    if (p3.equals("setDataByKey")) {
                        this.m2(p4, p5);
                        return;
                    }
                case 1244088583:
                    if (p3.equals("getLocationV2")) {
                        this.D0(p5, new rF.c(this, p5, 2));
                        return;
                    }
                case 1244088584:
                    if (p3.equals("getLocationV3")) {
                        this.F0(p4, p5, 1, new nK.A(p4, this, p5, 5));
                        return;
                    }
                case 1300501926:
                    if (p3.equals("syncAllInstalledApps")) {
                        this.u1(p5);
                        return;
                    }
                case 1301110865:
                    if (p3.equals("keepScreen")) {
                        this.B0(p4, p5);
                        return;
                    }
                case 1534073227:
                    if (p3.equals("openSMSV2")) {
                        this.P0(p4, p5);
                        return;
                    }
                case 1607331915:
                    if (p3.equals("getTimezoneV2")) {
                        this.A0(p5);
                        return;
                    }
                case 1679231905:
                    if (p3.equals("getStorageV2")) {
                        this.e0(p4, p5);
                        return;
                    }
                case 1721564378:
                    if (p3.equals("removeDataByKey")) {
                        this.i2(p4, p5);
                        return;
                    }
                case 1788537183:
                    if (p3.equals("requestCameraPermissionV2")) {
                        this.U0(p4, p5, sF.d.e, new rF.b(this, p4, p5, 2));
                        return;
                    }
                case 1789652527:
                    if (p3.equals("requestLocationPermissionV2")) {
                        this.D0(p5, new rF.c(this, p5, 4));
                        return;
                    }
                case 1789652528:
                    if (p3.equals("requestLocationPermissionV3")) {
                        this.U0(p4, p5, sF.d.d, new rF.b(this, p4, p5, 0));
                        return;
                    }
                case 1846562425:
                    if (p3.equals("requestAndSyncContactV2")) {
                        this.U0(p4, p5, sF.d.c, new rF.b(this, p4, p5, 4));
                        return;
                    }
                case 1858811356:
                    if (p3.equals("requestContactPermissionV2")) {
                        this.U0(p4, p5, sF.d.c, new rF.b(this, p4, p5, 3));
                        return;
                    }
                default:
            }
        }
        vn.com.vng.zalopay.miniapp.api.v2.device.DeviceModule.b2(this, p5, 0, 2, 0);
        return;
    }

    public final void a2(MR.b p1, QR.d p2)
    {
        if (p1 != null) {
            p1.h(p2);
        }
        return;
    }

    public final void c2(MR.b p2, eR.m p3)
    {
        if (!(p3 instanceof eR.j)) {
            if (p2 != null) {
                p2.b(p3.a());
            }
        } else {
            if (p2 != null) {
                p2.success();
                return;
            }
        }
        return;
    }

    public final androidx.lifecycle.N d0()
    {
        return this.n;
    }

    public void onCreate()
    {
        this.C1();
        return;
    }
}
