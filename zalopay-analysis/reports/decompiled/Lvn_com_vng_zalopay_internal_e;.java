package vn.com.vng.zalopay.internal;
public final synthetic class e implements com.appsflyer.deeplink.DeepLinkListener, mf.o, mf.g, gf.f, mf.a, com.vnpay.vntaxi.interfaces.CallbackP, com.google.android.gms.tasks.OnSuccessListener, mf.r, com.visa.visapaysdk.sdk.oOOO00 {
    public static final String qalxt = "VideoCheckLive";
    public static final String wjtwpts;
    public final synthetic int a;
    public final synthetic Object b;

    static e()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.internal.e, 17202, 17202);
        return;
    }

    public synthetic e(int p1, Object p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public synthetic e(Runnable p1, Object p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        return;
    }

    public void OooO00o(String p8, String p9, java.util.Map p10, Runnable p11, java.util.function.Consumer p12)
    {
        ((com.visa.visapaysdk.sdk.oOOO000o) this.b).OooO00o(p8, p9, p10, p11, p12);
        return;
    }

    public void accept(Object p5)
    {
        switch (this.a) {
            case 2:
                ((vn.com.vng.zalopay.internal.g) this.b).invoke(p5);
                return;
            case 3:
                ((jV.k) this.b).invoke(p5);
                return;
            case 4:
                ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0) this.b).invoke(p5);
                return;
            case 5:
                ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0) this.b).invoke(p5);
                return;
            case 12:
                io.reactivex.subjects.f v0_2 = ((xt.b) this.b);
                v0_2.c = ((Long) p5).longValue();
                v0_2.b.d(((Long) p5).longValue());
                if (v0_2.d.a.e()) {
                    v0_2.d.onNext(((Long) p5));
                }
                return;
            case 13:
                ((BZ.a) this.b).invoke(p5);
                return;
            case 24:
                ((zv.f) this.b).invoke(p5);
                return;
            default:
                ((zv.g) this.b).invoke(p5);
                return;
        }
    }

    public Object apply(Object p2)
    {
        switch (this.a) {
            case 1:
                return ((kotlin.Pair) ((vn.com.vng.zalopay.internal.g) this.b).invoke(p2));
            case 6:
                return ((kotlin.Pair) ((vn.com.vng.zalopay.internal.g) this.b).invoke(p2));
            case 14:
                return ((Ew.k) ((xu.f) this.b).invoke(p2));
            case 16:
                return ((java.util.List) ((xu.f) this.b).invoke(p2));
            case 17:
                return ((java.util.List) ((xu.f) this.b).invoke(p2));
            case 18:
                return ((Ew.k) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0) this.b).invoke(p2));
            case 21:
                return ((ft.o) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0) this.b).invoke(p2));
            case 23:
                return ((gf.F) ((XA.e) this.b).invoke(p2));
            case 25:
                return ((gf.F) ((zv.f) this.b).invoke(p2));
            case 26:
                return ((gf.F) ((zv.g) this.b).invoke(p2));
            default:
                return ((gf.F) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0) this.b).invoke(p2));
        }
    }

    public void b(rf.a p1)
    {
        throw 0;
    }

    public void call(Object p3)
    {
        com.vnpay.vntaxi.ui.activities.report.FastReportTaxiActivity v0_1 = ((com.vnpay.vntaxi.ui.activities.report.FastReportTaxiActivity) this.b);
        Class vtmp1 = v0_1.getClass();
        ((Ae.w) p3).dismiss();
        v0_1.finish();
        return;
    }

    public void onDeepLinking(com.appsflyer.deeplink.DeepLinkResult p4)
    {
        String v1_1;
        Xv.d v0_1 = ((vn.com.vng.zalopay.internal.f) this.b);
        String v1_3 = p4.getDeepLink().getStringValue(wmoi.igob.ggcn(2336));
        if (v1_3 == null) {
            v1_1 = 0;
        } else {
            String v1_0 = java.net.URI.create(v1_3);
            if (v1_0 == null) {
            } else {
                v1_1 = v1_0.getQuery();
            }
        }
        String v4_2 = p4.getDeepLink().getDeepLinkValue();
        if (v4_2 != null) {
            if (v1_1 != null) {
                String v2_5;
                if (new java.net.URI(v4_2).getQuery() == null) {
                    v2_5 = androidx.activity.result.e.o(v4_2, wmoi.igob.ggcn(2338), v1_1);
                } else {
                    v2_5 = androidx.activity.result.e.o(v4_2, wmoi.igob.ggcn(2337), v1_1);
                    if (v2_5 == null) {
                    }
                }
                if (v2_5 != null) {
                    v4_2 = v2_5;
                }
            }
            v0_1.b.c(v4_2);
        }
        return;
    }

    public void onSuccess(Object p24)
    {
        switch (this.a) {
            case 10:
                Throwable v0_7 = ((x7.n) ((com.google.android.gms.cloudmessaging.i) this.b).f).d;
                if (v0_7 != null) {
                    int v3_0 = v0_7.a;
                    if ((!v3_0.J) && (v3_0.getContext() != null)) {
                        G8.b v2_12;
                        Throwable v0_19;
                        String v4_8 = v3_0.getString(2131953444);
                        Object v5_10 = ((java.util.List) p24).size();
                        if (v5_10 <= 1) {
                            if (v5_10 >= 1) {
                                Throwable v0_35 = ((com.google.mlkit.vision.face.Face) ((java.util.List) p24).get(0));
                                G8.b v2_25 = new x7.x(v3_0.S, v0_35);
                                if (v3_0.getContext() != null) {
                                    int v13_3;
                                    String v4_11 = v3_0.C;
                                    if (v4_11 == null) {
                                        v13_3 = 0;
                                    } else {
                                        v13_3 = v4_11;
                                    }
                                    int v13_9;
                                    String v4_12 = v13_3.getRect();
                                    Object v5_31 = v0_35.getBoundingBox();
                                    com.google.android.gms.internal.ads.TG v6_26 = (m9.J3.j(v3_0.requireContext()) / 15);
                                    java.io.File v7_27 = ((v6_26 * 3) / 2);
                                    double v10_14 = v0_35.getHeadEulerAngleY();
                                    int v11_14 = v0_35.getHeadEulerAngleX();
                                    Throwable v0_66 = v0_35.getHeadEulerAngleZ();
                                    if ((v2_25.c(((float) v5_31.left)) - v2_25.c(((float) v5_31.right))) <= ((v4_12.right - v4_12.left) + ((float) (v6_26 * 4)))) {
                                        v13_9 = 0;
                                    } else {
                                        v13_9 = 1;
                                    }
                                    boolean v8_0;
                                    if ((v2_25.c(((float) v5_31.left)) - v2_25.c(((float) v5_31.right))) >= ((v4_12.right - v4_12.left) - ((float) v6_26))) {
                                        v8_0 = 0;
                                    } else {
                                        v8_0 = 1;
                                    }
                                    String v4_6;
                                    float v24_1 = v10_14;
                                    String v4_2 = ((double) (v4_12.centerY() - v2_25.d(v5_31.exactCenterY())));
                                    double v9_2 = Math.sqrt((((double) (v4_12.centerX() - v2_25.c(v5_31.exactCenterX()))) * ((double) (v4_12.centerX() - v2_25.c(v5_31.exactCenterX())))));
                                    double vtmp49 = Math.sqrt((v4_2 * v4_2));
                                    if ((v9_2 <= ((double) v6_26)) && (vtmp49 <= ((double) v7_27))) {
                                        v4_6 = 0;
                                    } else {
                                        v4_6 = 1;
                                    }
                                    if ((v11_14 <= ((float) 6)) && (v11_14 >= ((float) -20))) {
                                        Object v5_5 = 0;
                                    } else {
                                        v5_5 = 1;
                                    }
                                    if ((v24_1 >= ((float) -10)) && (v24_1 <= ((float) 10))) {
                                        G8.b v2_4 = 0;
                                    } else {
                                        v2_4 = 1;
                                    }
                                    if ((v0_66 >= ((float) -20)) && (v0_66 <= ((float) 20))) {
                                        Throwable v0_6 = 0;
                                    } else {
                                        v0_6 = 1;
                                    }
                                    if ((v13_9 == 0) && ((!v8_0) && ((v4_6 == null) && ((v2_4 == null) && ((v5_5 == null) && (v0_6 == null)))))) {
                                        v4_8 = v3_0.getString(2131953447);
                                        Throwable v0_8 = v3_0.K cmp 0;
                                        Object v5_7 = System.currentTimeMillis();
                                        if (v0_8 != null) {
                                            if (((v5_7 - v3_0.K) > 700) && ((!v3_0.F) && (!v3_0.E))) {
                                                v3_0.E = 1;
                                                Throwable v0_12 = v3_0.Q;
                                                if (v0_12 != null) {
                                                    G8.b v2_5 = v3_0.V;
                                                    Object v5_9 = v3_0.W;
                                                    com.google.android.gms.internal.ads.TG v6_13 = v3_0.X;
                                                    java.io.File v7_4 = ((com.fpt.fci.ekycfull.presentation.view.EkycActivity) v3_0.getActivity()).k1(v3_0.n1().j.name());
                                                    boolean v8_6 = v3_0.x1().i;
                                                    v0_12.p = v2_5;
                                                    v0_12.q = v5_9;
                                                    if ((v0_12.e != null) && (v0_12.s.isAvailable())) {
                                                        if (v0_12.m != null) {
                                                            v0_12.u = v8_6;
                                                            v0_12.c(v7_4);
                                                            v0_12.e(0);
                                                            v3_0.requireActivity().runOnUiThread(new kO.f(v3_0, v4_8, 10));
                                                            return;
                                                        } else {
                                                        }
                                                    }
                                                    v6_13.a.F = 0;
                                                }
                                            }
                                        } else {
                                            v3_0.K = v5_7;
                                        }
                                    } else {
                                        String v4_9;
                                        Throwable v0_18;
                                        if (v13_9 == 0) {
                                            if (!v8_0) {
                                                G8.b v2_10;
                                                if (v2_4 == null) {
                                                    if (v5_5 == null) {
                                                        Throwable v0_17;
                                                        if (v0_6 == null) {
                                                            v0_17 = 2131953444;
                                                        } else {
                                                            v0_17 = 2131953452;
                                                        }
                                                        v0_18 = v3_0.getString(v0_17);
                                                        v4_8 = v0_18;
                                                        v0_19 = v3_0.requireActivity();
                                                        v2_12 = new t7.E(v3_0, 0);
                                                        v0_19.runOnUiThread(v2_12);
                                                    } else {
                                                        v2_10 = 2131953451;
                                                    }
                                                } else {
                                                    v2_10 = 2131953450;
                                                }
                                                v0_18 = v3_0.getString(v2_10);
                                            } else {
                                                v4_9 = 2131953445;
                                                v0_18 = v3_0.getString(v4_9);
                                            }
                                        } else {
                                            v4_9 = 2131953446;
                                        }
                                    }
                                }
                            } else {
                                v4_8 = v3_0.getString(2131953444);
                                System.out.println(androidx.activity.result.e.g(v5_10, vn.com.vng.zalopay.internal.e.wjtwpts));
                                v0_19 = v3_0.requireActivity();
                                v2_12 = new t7.E(v3_0, 0);
                            }
                        } else {
                            v4_8 = v3_0.getString(2131953448);
                            v0_19 = v3_0.requireActivity();
                            v2_12 = new t7.E(v3_0, 0);
                        }
                    }
                }
                return;
            default:
                Throwable v0_28 = ((x7.w) ((com.google.android.gms.cloudmessaging.i) this.b).f).d;
                if (v0_28 != null) {
                    String v4_10 = v0_28.a;
                    if ((!v4_10.J) && (v4_10.getContext() != null)) {
                        G8.b v2_30;
                        Throwable v0_31 = v4_10.getString(2131953444);
                        Object v5_11 = ((java.util.List) p24).size();
                        if (v5_11 <= 1) {
                            if (v5_11 >= 1) {
                                int v3_4 = ((com.google.mlkit.vision.face.Face) ((java.util.List) p24).get(0));
                                Object v5_13 = new x7.x(v4_10.Q, v3_4);
                                double v10_1 = v4_10.getContext();
                                if (v10_1 != 0) {
                                    Throwable v0_32 = v4_10.C;
                                    if (v0_32 == null) {
                                        v0_32 = 0;
                                    }
                                    com.google.android.gms.internal.ads.TG v6_15;
                                    Throwable v0_33 = v0_32.getRect();
                                    int v12_4 = v3_4.getBoundingBox();
                                    float v18_1 = m9.J3.j(v4_10.requireContext());
                                    if (!m9.J3.k(v10_1)) {
                                        v6_15 = 20;
                                    } else {
                                        if (!v4_10.R) {
                                            v6_15 = 10;
                                        } else {
                                            v6_15 = 50;
                                        }
                                    }
                                    java.io.File v7_12;
                                    float v18_2 = (v18_1 / v6_15);
                                    com.google.android.gms.internal.ads.TG v6_17 = v3_4.getHeadEulerAngleY();
                                    float v20 = v3_4.getHeadEulerAngleX();
                                    int v3_5 = v3_4.getHeadEulerAngleZ();
                                    G8.b v2_20 = v5_13.c(((float) v12_4.left));
                                    int v11_2 = v5_13.c(((float) v12_4.right));
                                    if (!v4_10.R) {
                                        v7_12 = v18_2;
                                    } else {
                                        if (!m9.J3.k(v10_1)) {
                                            v7_12 = (v18_2 * 4);
                                        } else {
                                            v7_12 = (v18_2 * 12);
                                        }
                                    }
                                    G8.b v2_23;
                                    if ((v2_20 - v11_2) <= ((v0_33.right - v0_33.left) + ((float) v7_12))) {
                                        v2_23 = 0;
                                    } else {
                                        v2_23 = 1;
                                    }
                                    float v18_3;
                                    java.io.File v7_17 = v5_13.c(((float) v12_4.left));
                                    double v10_4 = v5_13.c(((float) v12_4.right));
                                    if (!v4_10.R) {
                                        v18_3 = (v18_2 * 4);
                                    } else {
                                        v18_3 = (v18_2 * 2);
                                    }
                                    java.io.File v7_20;
                                    if ((v7_17 - v10_4) >= ((v0_33.right - v0_33.left) - ((float) v18_3))) {
                                        v7_20 = 0;
                                    } else {
                                        v7_20 = 1;
                                    }
                                    Object v5_22;
                                    Object v5_18 = (v0_33.right - v0_33.left);
                                    if ((Math.sqrt((((double) (v0_33.centerX() - v5_13.c(v12_4.exactCenterX()))) * ((double) (v0_33.centerX() - v5_13.c(v12_4.exactCenterX()))))) <= ((double) (v5_18 / ((float) 6)))) && (Math.sqrt((((double) (v0_33.centerY() - v5_13.d(v12_4.exactCenterY()))) * ((double) (v0_33.centerY() - v5_13.d(v12_4.exactCenterY()))))) <= ((double) (v5_18 / ((float) 4))))) {
                                        v5_22 = 0;
                                    } else {
                                        v5_22 = 1;
                                    }
                                    if ((v20 <= ((float) 6)) && (v20 >= ((float) -20))) {
                                        Throwable v0_41 = 0;
                                    } else {
                                        v0_41 = 1;
                                    }
                                    if ((v6_17 >= ((float) -10)) && (v6_17 <= ((float) 10))) {
                                        com.google.android.gms.internal.ads.TG v6_19 = 0;
                                    } else {
                                        v6_19 = 1;
                                    }
                                    if ((v3_5 >= ((float) -20)) && (v3_5 <= ((float) 20))) {
                                        int v3_7 = 0;
                                    } else {
                                        v3_7 = 1;
                                    }
                                    if ((v2_23 == null) && ((v7_20 == null) && ((v5_22 == null) && ((v6_19 == null) && ((v0_41 == null) && (v3_7 == 0)))))) {
                                        G8.b v2_26 = v4_10.getString(2131953447);
                                        Throwable v0_42 = v4_10.K cmp 0;
                                        Object v5_25 = System.currentTimeMillis();
                                        if (v0_42 != null) {
                                            if ((v5_25 - v4_10.K) > 800) {
                                                Throwable v0_44 = v4_10.F;
                                                if ((v0_44 != null) || (v4_10.E)) {
                                                    if ((v0_44 != null) && ((!v4_10.V) && (!v4_10.U))) {
                                                        v4_10.U = 1;
                                                        v4_10.V = 1;
                                                        try {
                                                            Throwable v0_47 = v4_10.W;
                                                        } catch (Throwable v0_48) {
                                                            throw v0_48;
                                                        }
                                                        if (v0_47 != null) {
                                                            v0_47.e(v4_10.d0, new t7.J(v4_10));
                                                        }
                                                    }
                                                } else {
                                                    v4_10.E = 1;
                                                    Throwable v0_49 = v4_10.S;
                                                    int v3_12 = Integer.valueOf(v4_10.T);
                                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_12, ((Integer) v0_49.e))) {
                                                        v0_49.a();
                                                        v0_49.e = v3_12;
                                                        ((android.os.Handler) v0_49.c).postDelayed(((androidx.media3.exoplayer.video.K) v0_49.d), 100);
                                                    }
                                                    Throwable v0_52 = v4_10.Q;
                                                    if (v0_52 != null) {
                                                        v0_52.a();
                                                    }
                                                    Throwable v0_54;
                                                    Throwable v0_53 = v4_10.S;
                                                    if (v0_53 == null) {
                                                        v0_54 = 0;
                                                    } else {
                                                        int v11_13;
                                                        if (Float.compare(v0_53.b, 1065353216) != 0) {
                                                            v11_13 = 0;
                                                        } else {
                                                            v11_13 = 1;
                                                        }
                                                        v0_54 = Boolean.valueOf(v11_13);
                                                    }
                                                    if (!v0_54.booleanValue()) {
                                                        Throwable v0_58 = v4_10.Q;
                                                        if (v0_58 != null) {
                                                            int v13_4;
                                                            Object v5_33 = v4_10.C;
                                                            if (v5_33 == null) {
                                                                v13_4 = 0;
                                                            } else {
                                                                v13_4 = v5_33;
                                                            }
                                                            try {
                                                                v0_58.b.add(new x7.y(v0_58, v13_4.getRect(), v4_10.S));
                                                            } catch (Throwable v0_60) {
                                                                throw v0_60;
                                                            }
                                                        }
                                                    }
                                                    Throwable v0_61 = v4_10.Q;
                                                    if (v0_61 != null) {
                                                        v0_61.invalidate();
                                                    }
                                                    Throwable v0_62 = v4_10.W;
                                                    if (v0_62 != null) {
                                                        int v3_18 = v4_10.a0;
                                                        Object v5_35 = v4_10.b0;
                                                        com.google.android.gms.internal.ads.TG v6_25 = v4_10.c0;
                                                        java.io.File v7_25 = ((com.fpt.fci.ekycfull.presentation.view.EkycActivity) v4_10.getActivity()).k1(vn.com.vng.zalopay.internal.e.qalxt);
                                                        boolean v8_16 = v4_10.x1().i;
                                                        v0_62.p = v3_18;
                                                        v0_62.q = v5_35;
                                                        if ((v0_62.e != null) && (v0_62.s.isAvailable())) {
                                                            if (v0_62.m != null) {
                                                                v0_62.v = v8_16;
                                                                v0_62.d(v7_25);
                                                                v0_62.g(0);
                                                                v0_31 = v2_26;
                                                                v4_10.requireActivity().runOnUiThread(new kO.f(v4_10, v0_31, 11));
                                                                return;
                                                            } else {
                                                            }
                                                        }
                                                        v6_25.a.F = 0;
                                                    }
                                                }
                                            }
                                        } else {
                                            v4_10.K = v5_25;
                                        }
                                    } else {
                                        G8.b v2_27;
                                        if (v2_23 == null) {
                                            if (v7_20 == null) {
                                                if (v6_19 == null) {
                                                    Throwable v0_65;
                                                    if (v0_41 == null) {
                                                        if (v3_7 == 0) {
                                                            v0_65 = 2131953444;
                                                        } else {
                                                            v0_65 = 2131953452;
                                                        }
                                                    } else {
                                                        v0_65 = 2131953451;
                                                    }
                                                    v0_31 = v4_10.getString(v0_65);
                                                } else {
                                                    v2_27 = 2131953450;
                                                    v0_31 = v4_10.getString(v2_27);
                                                }
                                            } else {
                                                v2_27 = 2131953445;
                                            }
                                        } else {
                                            v2_27 = 2131953446;
                                        }
                                        if (v4_10.R) {
                                            v4_10.K = 0;
                                        } else {
                                            v2_30 = v4_10.E;
                                            v4_10.D1(v2_30);
                                        }
                                    }
                                }
                            } else {
                                v0_31 = v4_10.getString(2131953444);
                                v4_10.D1(v4_10.E);
                                v2_30 = v4_10.F;
                            }
                        } else {
                            v0_31 = v4_10.getString(2131953448);
                        }
                    }
                }
                return;
        }
    }

    public void run()
    {
        ((java.util.concurrent.Semaphore) this.b).release();
        return;
    }

    public boolean test(Object p2)
    {
        switch (this.a) {
            case 15:
                return ((Boolean) ((kO.m) this.b).invoke(p2)).booleanValue();
            case 20:
                return ((Boolean) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0) this.b).invoke(p2)).booleanValue();
            case 22:
                return ((Boolean) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0) this.b).invoke(p2)).booleanValue();
            default:
                return ((Boolean) ((zv.g) this.b).invoke(p2)).booleanValue();
        }
    }
}
