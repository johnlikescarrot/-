package vn.com.vng.zalopay.webapp.fragment;
public abstract class a extends vn.com.vng.zalopay.account.ui.fragment.AbsPickerImageFragment implements hR.a, vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment$a {
    public android.widget.ImageView A;
    public android.widget.TextView B;
    public vn.zalopay.ui.MultiSwipeRefreshLayout C;
    public androidx.compose.ui.platform.ComposeView D;
    public vn.com.vng.webapp.framework.ZPWebViewApp E;
    public iR.l F;
    public com.facebook.internal.i G;
    public com.facebook.internal.i H;
    public F6.c I;
    public F6.c J;
    public long K;
    public long L;
    public boolean M;
    public long N;
    public int O;
    public vn.com.vng.zalopay.navigation.b P;
    public xn.b Q;
    public Yv.i R;
    public nY.p S;
    public IE.b T;
    public String U;
    public String V;
    public final ng.S W;
    public final ng.S X;
    public final Ae.r Y;
    public Kk.f w;
    public android.os.Handler x;
    public android.widget.Button y;
    public android.widget.LinearLayout z;

    public a()
    {
        this.M = 1;
        this.W = ng.x.b(new WQ.c(this, 0));
        this.X = ng.x.b(new WQ.c(this, 1));
        this.Y = new Ae.r(21, this);
        return;
    }

    public static void A1(String p4, String p5, String p6, String p7)
    {
        String v0 = wmoi.igob.ggcn(14985);
        try {
            android.webkit.CookieManager.getInstance().setAcceptCookie(1);
            okhttp3.Cookie$Builder v1_2 = new okhttp3.Cookie$Builder();
            v1_2.name(p4);
            v1_2.value(p5);
            v1_2.domain(p6);
        } catch (String v4_1) {
            android.webkit.CookieManager v5_2 = new Object[0];
            ck.b.g(v4_1, wmoi.igob.ggcn(14987), v5_2);
            return;
        }
        if (p7 == null) {
            p7 = wmoi.igob.ggcn(14986);
        }
        v1_2.path(p7);
        v1_2.secure();
        v1_2.httpOnly();
        String v4_3 = okhttp3.internal.Internal.cookieToString(v1_2.build(), 1);
        android.webkit.CookieManager v5_3 = android.webkit.CookieManager.getInstance();
        v5_3.setCookie(v0.concat(p6), v4_3);
        v5_3.flush();
        return;
    }

    public void B1(boolean p1)
    {
        return;
    }

    public final void C1(boolean p11)
    {
        android.hardware.camera2.CameraAccessException v1_0;
        android.hardware.camera2.CameraManager v0_0 = this.X;
        android.hardware.camera2.CameraAccessException v1_3 = this.getContext();
        int v2 = 0;
        if (v1_3 != null) {
            v1_0 = v1_3.getPackageManager().hasSystemFeature(wmoi.igob.ggcn(14988));
        } else {
            v1_0 = 0;
        }
        try {
            if (v1_0 != null) {
                android.hardware.camera2.CameraAccessException v1_2 = ((android.hardware.camera2.CameraManager) v0_0.getValue());
                String v3_0 = 0;
                if (v1_2 != null) {
                    try {
                        String[] v4 = v1_2.getCameraIdList();
                    } catch (android.hardware.camera2.CameraAccessException v1_4) {
                        v1_4.toString();
                    }
                    if (v4 != null) {
                        while (v2 < v4.length) {
                            String v6 = v4[v2];
                            int v7_2 = ((Integer) v1_2.getCameraCharacteristics(v6).get(android.hardware.camera2.CameraCharacteristics.LENS_FACING));
                            if (v7_2 != 0) {
                                if (v7_2.intValue() == 1) {
                                    v3_0 = v6;
                                    break;
                                }
                            } else {
                            }
                            v2++;
                        }
                    } else {
                    }
                }
                if (v3_0 != null) {
                    android.hardware.camera2.CameraManager v0_2 = ((android.hardware.camera2.CameraManager) v0_0.getValue());
                    if (v0_2 != null) {
                        v0_2.setTorchMode(v3_0, p11);
                    }
                }
            }
        } catch (android.hardware.camera2.CameraAccessException) {
        }
        return;
    }

    public final void D1(boolean p4)
    {
        bx.a v0_1;
        bx.a v0_0 = android.os.Build$VERSION.SDK_INT;
        if (v0_0 < 33) {
            if ((v0_0 < 29) || (v0_0 >= 33)) {
                v0_1 = wmoi.igob.ggcn(14990);
            } else {
                v0_1 = wmoi.igob.ggcn(14989);
            }
        } else {
            v0_1 = "";
        }
        if ((v0_1.length() != 0) && ((p4 == null) && (!this.shouldShowRequestPermissionRationale(v0_1)))) {
            androidx.fragment.app.n0 v4_2 = this.getParentFragmentManager();
            bx.a v0_2 = p9.E4.a;
            if (v0_2 != null) {
                v0_2.o1(0, 0);
            }
            bx.a v0_4 = new bx.a();
            p9.E4.a = v0_4;
            v0_4.v1(v4_2, wmoi.igob.ggcn(14991));
        }
        return;
    }

    public final void E1(Jw.f p8)
    {
        android.content.Context v0 = this.getContext();
        String v1 = p8.b;
        String v2_0 = "";
        if (v1 == null) {
            v1 = "";
        }
        String v3_1 = p8.c;
        if (v3_1 == null) {
            v3_1 = "";
        }
        android.net.Uri v4_1 = p8.d;
        if (v4_1 != null) {
            v2_0 = v4_1;
        }
        com.google.android.gms.internal.mlkit_vision_barcode.B0.c(v0, v1, v3_1, v2_0, p8.a, 0);
        return;
    }

    public void Z0()
    {
        vn.zalopay.ui.MultiSwipeRefreshLayout v0_0 = this.z;
        if (v0_0 != null) {
            v0_0.setVisibility(8);
        }
        vn.zalopay.ui.MultiSwipeRefreshLayout v0_4 = this.F;
        int v1_0 = 0;
        if (v0_4 == null) {
            v0_4 = 0;
        }
        v0_4.O.j(Boolean.FALSE);
        vn.zalopay.ui.MultiSwipeRefreshLayout v0_1 = v0_4.b3;
        if (v0_1 != null) {
            v1_0 = v0_1;
        }
        v1_0.c(v1_0.a);
        vn.zalopay.ui.MultiSwipeRefreshLayout v0_3 = this.C;
        if (v0_3 != null) {
            v0_3.setRefreshing(0);
        }
        return;
    }

    public final void l0()
    {
        androidx.fragment.app.FragmentActivity v0_0 = this.getActivity();
        if ((v0_0 != null) && (!v0_0.isFinishing())) {
            androidx.fragment.app.FragmentActivity v0_2 = this.getActivity();
            if (v0_2 != null) {
                v0_2.finish();
            }
        }
        return;
    }

    public void onActivityResult(int p3, int p4, android.content.Intent p5)
    {
        super.onActivityResult(p3, p4, p5);
        com.facebook.internal.i v0_0 = this.F;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.B0(p3, p4, p5, this.U);
        com.facebook.internal.i v0_1 = this.G;
        if (v0_1 != null) {
            v0_1.onActivityResult(p3, p4, p5);
        }
        com.facebook.internal.i v0_2 = this.H;
        if (v0_2 != null) {
            v0_2.onActivityResult(p3, p4, p5);
        }
        return;
    }

    public final boolean onBackPressed()
    {
        int v0_0 = this.E;
        vn.com.vng.webapp.framework.ZPWebViewApp v1 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        if (!v0_0.canGoBack()) {
            return 0;
        } else {
            this.C1(0);
            int v0_1 = this.E;
            if (v0_1 != 0) {
                v1 = v0_1;
            }
            v1.goBack();
            return 1;
        }
    }

    public final void onCreateOptionsMenu(android.view.Menu p6, android.view.MenuInflater p7)
    {
        boolean v3_0;
        p7.inflate(2131623945, p6);
        super.onCreateOptionsMenu(p6, p7);
        WQ.d v7_1 = this.O;
        int v0_7 = p6.findItem(2131361925);
        android.os.Bundle v1_1 = v0_7.getActionView();
        vn.zalopay.ui.IconFont v2_1 = 0;
        if (v1_1 == null) {
            v3_0 = 0;
        } else {
            v3_0 = ((vn.zalopay.ui.IconFont) v1_1.findViewById(2131363114));
        }
        if (v3_0) {
            v3_0.setIcon(2131955793);
        }
        if (v3_0) {
            v3_0.setIconColor(v7_1);
        }
        if (v1_1 != null) {
            v1_1.setOnClickListener(new WQ.d(this, 0));
        }
        android.os.Bundle v1_0 = this.getArguments();
        boolean v3_5 = 1;
        if (v1_0 != null) {
            v3_5 = v1_0.getBoolean(wmoi.igob.ggcn(14992), 1);
        }
        v0_7.setVisible(v3_5);
        android.view.View v6_2 = p6.findItem(2131361910).getActionView();
        if (v6_2 != null) {
            v2_1 = ((vn.zalopay.ui.IconFont) v6_2.findViewById(2131363097));
        }
        if (v2_1 != null) {
            v2_1.setIcon(2131955797);
        }
        if (v2_1 != null) {
            v2_1.setIconColor(v7_1);
        }
        if (v6_2 != null) {
            v6_2.setOnClickListener(new WQ.d(this, 1));
        }
        return;
    }

    public final void onDestroy()
    {
        qm.m v0_0 = this.F;
        qm.m v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        qm.m v0_1 = v0_0.b3;
        if (v0_1 != null) {
            v1 = v0_1;
        }
        v1.getClass();
        super.onDestroy();
        return;
    }

    public void onDestroyView()
    {
        qm.m v0_0 = this.w;
        if (v0_0 != null) {
            qm.m v1_1 = this.x;
            if (v1_1 != null) {
                v1_1.removeCallbacks(v0_0);
            }
        }
        qm.m v0_1 = this.F;
        qm.m v1_0 = 0;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        qm.m v0_2 = v0_1.b3;
        if (v0_2 != null) {
            v1_0 = v0_2;
        }
        v1_0.b();
        super.onDestroyView();
        return;
    }

    public void onPause()
    {
        this.C1(0);
        XQ.n v0_2 = this.F;
        vn.com.vng.webapp.framework.ZPWebViewApp v1_0 = 0;
        if (v0_2 == null) {
            v0_2 = 0;
        }
        qm.m v2 = v0_2.b3;
        if (v2 != null) {
            v1_0 = v2;
        }
        vn.com.vng.webapp.framework.ZPWebViewApp v1_1 = v1_0.c;
        if (v1_1 != null) {
            v1_1.onPause();
        }
        XQ.n v0_1 = v0_2.r3;
        if (v0_1 != null) {
            v0_1.b();
        }
        super.onPause();
        return;
    }

    public void onResume()
    {
        XQ.n v0_0 = this.E;
        vn.com.vng.webapp.framework.ZPWebViewApp v1_0 = 0;
        if (v0_0 != null) {
            if (v0_0 == null) {
                v0_0 = 0;
            }
            v0_0.onResume();
            XQ.n v0_3 = this.E;
            if (v0_3 == null) {
                v0_3 = 0;
            }
            v0_3.resumeTimers();
        }
        super.onResume();
        XQ.n v0_1 = this.F;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        qm.m v2 = v0_1.b3;
        if (v2 != null) {
            v1_0 = v2;
        }
        vn.com.vng.webapp.framework.ZPWebViewApp v1_1 = v1_0.c;
        if (v1_1 != null) {
            v1_1.onResume();
        }
        XQ.n v0_2 = v0_1.r3;
        if (v0_2 != null) {
            v0_2.a();
        }
        return;
    }

    public void onViewCreated(android.view.View p12, android.os.Bundle p13)
    {
        long v3_46;
        int v1_0 = this.getArguments();
        if (v1_0 == 0) {
            v3_46 = 0;
        } else {
            v3_46 = v1_0.getLong(wmoi.igob.ggcn(14993));
        }
        this.N = v3_46;
        this.K = System.currentTimeMillis();
        void v11_1 = super.onViewCreated(p12, p13);
        v11_1.y = ((android.widget.Button) p12.findViewById(2131362112));
        v11_1.z = ((android.widget.LinearLayout) p12.findViewById(2131363391));
        v11_1.B = ((android.widget.TextView) p12.findViewById(2131364632));
        v11_1.A = ((android.widget.ImageView) p12.findViewById(2131363099));
        v11_1.C = ((vn.zalopay.ui.MultiSwipeRefreshLayout) p12.findViewById(2131364026));
        v11_1.E = ((vn.com.vng.webapp.framework.ZPWebViewApp) p12.findViewById(2131365205));
        v11_1.D = ((androidx.compose.ui.platform.ComposeView) p12.findViewById(2131362447));
        long v0_62 = v11_1.y;
        if (v0_62 != 0) {
            v0_62.setOnClickListener(new Ae.p(25, v11_1));
        }
        long v0_73 = v11_1.C;
        if (v0_73 != 0) {
            v0_73.setOnRefreshListener(new MO.j(23, v11_1));
        }
        v11_1.G = new com.facebook.internal.i();
        long v0_91 = new F6.c(v11_1);
        v11_1.I = v0_91;
        int v1_117 = v11_1.G;
        if (v1_117 != 0) {
            v0_91.c(v1_117, new WQ.e(v11_1));
        }
        v11_1.H = new com.facebook.internal.i();
        long v0_114 = new F6.c(v11_1);
        v11_1.J = v0_114;
        int v1_132 = v11_1.H;
        if (v1_132 != 0) {
            v0_114.c(v1_132, new AW.a(23, v11_1));
        }
        v11_1.v1();
        long v0_131 = v11_1.z;
        if (v0_131 != 0) {
            v0_131.setVisibility(8);
        }
        long v0_137 = v11_1.F;
        androidx.lifecycle.T v8_0 = 0;
        if (v0_137 == 0) {
            v0_137 = 0;
        }
        int v1_142 = v11_1.E;
        if (v1_142 == 0) {
            v1_142 = 0;
        }
        v0_137.x0(v1_142, v11_1.getArguments(), v11_1.getActivity());
        long v0_0 = v11_1.F;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.y.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 0)));
        long v0_2 = v11_1.F;
        if (v0_2 == 0) {
            v0_2 = 0;
        }
        v0_2.A.f(v11_1.getViewLifecycleOwner(), new CS.v(8, new WQ.a(v11_1, 2)));
        long v0_4 = v11_1.F;
        if (v0_4 == 0) {
            v0_4 = 0;
        }
        v0_4.C.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 14)));
        long v0_6 = v11_1.F;
        if (v0_6 == 0) {
            v0_6 = 0;
        }
        v0_6.Z2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 26)));
        long v0_8 = v11_1.F;
        if (v0_8 == 0) {
            v0_8 = 0;
        }
        v0_8.D.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 28)));
        long v0_10 = v11_1.F;
        if (v0_10 == 0) {
            v0_10 = 0;
        }
        v0_10.F.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 29)));
        long v0_12 = v11_1.F;
        if (v0_12 == 0) {
            v0_12 = 0;
        }
        v0_12.u.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 0)));
        long v0_14 = v11_1.F;
        if (v0_14 == 0) {
            v0_14 = 0;
        }
        v0_14.s.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 1)));
        long v0_16 = v11_1.F;
        if (v0_16 == 0) {
            v0_16 = 0;
        }
        v0_16.w.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 2)));
        long v0_18 = v11_1.F;
        if (v0_18 == 0) {
            v0_18 = 0;
        }
        v0_18.Q.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 4)));
        long v0_20 = v11_1.F;
        if (v0_20 == 0) {
            v0_20 = 0;
        }
        v0_20.S.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 11)));
        long v0_22 = v11_1.F;
        if (v0_22 == 0) {
            v0_22 = 0;
        }
        v0_22.U.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 22)));
        long v0_24 = v11_1.F;
        if (v0_24 == 0) {
            v0_24 = 0;
        }
        v0_24.W.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 3)));
        long v0_26 = v11_1.F;
        if (v0_26 == 0) {
            v0_26 = 0;
        }
        v0_26.Y.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 9)));
        long v0_28 = v11_1.F;
        if (v0_28 == 0) {
            v0_28 = 0;
        }
        v0_28.a0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 12)));
        long v0_30 = v11_1.F;
        if (v0_30 == 0) {
            v0_30 = 0;
        }
        v0_30.c0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 13)));
        long v0_32 = v11_1.F;
        if (v0_32 == 0) {
            v0_32 = 0;
        }
        v0_32.n2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 14)));
        long v0_34 = v11_1.F;
        if (v0_34 == 0) {
            v0_34 = 0;
        }
        v0_34.e0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 15)));
        long v0_36 = v11_1.F;
        if (v0_36 == 0) {
            v0_36 = 0;
        }
        v0_36.s2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 16)));
        long v0_38 = v11_1.F;
        if (v0_38 == 0) {
            v0_38 = 0;
        }
        v0_38.x2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 1)));
        long v0_40 = v11_1.F;
        if (v0_40 == 0) {
            v0_40 = 0;
        }
        v0_40.g0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 3)));
        long v0_42 = v11_1.F;
        if (v0_42 == 0) {
            v0_42 = 0;
        }
        v0_42.i0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 4)));
        long v0_44 = v11_1.F;
        if (v0_44 == 0) {
            v0_44 = 0;
        }
        v0_44.k0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 5)));
        long v0_46 = v11_1.F;
        if (v0_46 == 0) {
            v0_46 = 0;
        }
        v0_46.m0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 6)));
        long v0_48 = v11_1.F;
        if (v0_48 == 0) {
            v0_48 = 0;
        }
        v0_48.o0.f(v11_1.getViewLifecycleOwner(), new CS.v(8, new WQ.a(v11_1, 7)));
        long v0_50 = v11_1.F;
        if (v0_50 == 0) {
            v0_50 = 0;
        }
        v0_50.q0.f(v11_1.getViewLifecycleOwner(), new CS.v(8, new WQ.a(v11_1, 8)));
        long v0_52 = v11_1.F;
        if (v0_52 == 0) {
            v0_52 = 0;
        }
        v0_52.s0.f(v11_1.getViewLifecycleOwner(), new CS.v(8, new WQ.a(v11_1, 9)));
        long v0_54 = v11_1.F;
        if (v0_54 == 0) {
            v0_54 = 0;
        }
        v0_54.u0.f(v11_1.getViewLifecycleOwner(), new CS.v(8, new WQ.a(v11_1, 10)));
        long v0_57 = v11_1.F;
        if (v0_57 == 0) {
            v0_57 = 0;
        }
        v0_57.w0.f(v11_1.getViewLifecycleOwner(), new CS.v(8, new WQ.a(v11_1, 12)));
        long v0_60 = v11_1.F;
        if (v0_60 == 0) {
            v0_60 = 0;
        }
        v0_60.y0.f(v11_1.getViewLifecycleOwner(), new CS.v(8, new WQ.a(v11_1, 13)));
        long v0_63 = v11_1.F;
        if (v0_63 == 0) {
            v0_63 = 0;
        }
        v0_63.C0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 15)));
        long v0_65 = v11_1.F;
        if (v0_65 == 0) {
            v0_65 = 0;
        }
        v0_65.E0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 16)));
        long v0_67 = v11_1.F;
        if (v0_67 == 0) {
            v0_67 = 0;
        }
        v0_67.G0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 17)));
        long v0_69 = v11_1.F;
        if (v0_69 == 0) {
            v0_69 = 0;
        }
        v0_69.I0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 18)));
        long v0_71 = v11_1.F;
        if (v0_71 == 0) {
            v0_71 = 0;
        }
        v0_71.y1.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 19)));
        long v0_74 = v11_1.F;
        if (v0_74 == 0) {
            v0_74 = 0;
        }
        v0_74.v1.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 20)));
        long v0_76 = v11_1.F;
        if (v0_76 == 0) {
            v0_76 = 0;
        }
        v0_76.V1.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 21)));
        long v0_78 = v11_1.F;
        if (v0_78 == 0) {
            v0_78 = 0;
        }
        v0_78.Q0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 23)));
        long v0_80 = v11_1.F;
        if (v0_80 == 0) {
            v0_80 = 0;
        }
        v0_80.S0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 24)));
        long v0_82 = v11_1.F;
        if (v0_82 == 0) {
            v0_82 = 0;
        }
        v0_82.U0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 25)));
        long v0_86 = v11_1.F;
        if (v0_86 == 0) {
            v0_86 = 0;
        }
        v0_86.W0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.a(v11_1, 27)));
        long v0_88 = v11_1.F;
        if (v0_88 == 0) {
            v0_88 = 0;
        }
        v0_88.P2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new RS.f(1, v11_1, vn.com.vng.zalopay.webapp.fragment.a, wmoi.igob.ggcn(14994), wmoi.igob.ggcn(14995), 0, 17)));
        long v0_94 = v11_1.F;
        if (v0_94 == 0) {
            v0_94 = 0;
        }
        v0_94.K0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 5)));
        long v0_96 = v11_1.F;
        if (v0_96 == 0) {
            v0_96 = 0;
        }
        v0_96.M0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 6)));
        long v0_98 = v11_1.F;
        if (v0_98 == 0) {
            v0_98 = 0;
        }
        v0_98.O0.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 7)));
        long v0_100 = v11_1.F;
        if (v0_100 == 0) {
            v0_100 = 0;
        }
        v0_100.g1.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 8)));
        long v0_102 = v11_1.F;
        if (v0_102 == 0) {
            v0_102 = 0;
        }
        v0_102.n1.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 10)));
        long v0_105 = v11_1.F;
        if (v0_105 == 0) {
            v0_105 = 0;
        }
        v0_105.s1.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new WQ.b(v11_1, 11)));
        long v0_108 = v11_1.F;
        if (v0_108 == 0) {
            v0_108 = 0;
        }
        v0_108.R2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new RS.f(1, v11_1, vn.com.vng.zalopay.webapp.fragment.a, wmoi.igob.ggcn(14996), wmoi.igob.ggcn(14997), 0, 15)));
        long v0_113 = v11_1.F;
        if (v0_113 == 0) {
            v0_113 = 0;
        }
        v0_113.T2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new RS.f(1, v11_1, vn.com.vng.zalopay.webapp.fragment.a, wmoi.igob.ggcn(14998), wmoi.igob.ggcn(14999), 0, 16)));
        long v0_118 = v11_1.F;
        if (v0_118 == 0) {
            v0_118 = 0;
        }
        v0_118.V2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new RS.f(1, v11_1, vn.com.vng.zalopay.webapp.fragment.a, wmoi.igob.ggcn(15000), wmoi.igob.ggcn(15001), 0, 14)));
        long v0_122 = v11_1.F;
        if (v0_122 == 0) {
            v0_122 = 0;
        }
        v0_122.N.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new RS.f(1, v11_1, vn.com.vng.zalopay.webapp.fragment.a, wmoi.igob.ggcn(15002), wmoi.igob.ggcn(15003), 0, 12)));
        long v0_126 = v11_1.F;
        if (v0_126 == 0) {
            v0_126 = 0;
        }
        v0_126.J.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new RS.f(1, v11_1, vn.com.vng.zalopay.webapp.fragment.a, wmoi.igob.ggcn(15004), wmoi.igob.ggcn(15005), 0, 13)));
        long v0_130 = v11_1.F;
        if (v0_130 != 0) {
            v8_0 = v0_130;
        }
        v8_0.X2.f(v11_1.getViewLifecycleOwner(), new cV.y(2, new RS.f(1, v11_1, vn.com.vng.zalopay.webapp.fragment.a, wmoi.igob.ggcn(15006), wmoi.igob.ggcn(15007), 0, 11)));
        v11_1.L = (System.currentTimeMillis() - v11_1.K);
        return;
    }

    public void r1(int p4, boolean p5)
    {
        android.content.Intent v1 = 0;
        if (p4 == 19) {
            boolean v4_6 = this.F;
            if (v4_6) {
                v1 = v4_6;
            }
            iR.l.n0(v1, p5);
            this.D1(p5);
            return;
        } else {
            if (p4 == 20) {
                boolean v4_1 = this.F;
                if (v4_1) {
                    v1 = v4_1;
                }
                if (p5 != null) {
                    v1.u0();
                    return;
                } else {
                    v1.v0(0);
                    return;
                }
            } else {
                if (p4 == 22) {
                    if (this.q1(new String[] {wmoi.igob.ggcn(15010)}), 22)) {
                        this.startActivityForResult(this.t1(), 23);
                    }
                    return;
                } else {
                    if (p4 == 305) {
                        boolean v4_8 = this.F;
                        if (v4_8) {
                            v1 = v4_8;
                        }
                        v1.r0(p5, v1.v3);
                        this.D1(p5);
                        return;
                    } else {
                        if (p4 == 4632) {
                            if (p5 == null) {
                                boolean v4_10 = this.F;
                                if (!v4_10) {
                                    v4_10 = 0;
                                }
                                android.webkit.ValueCallback v5_2 = v4_10.q;
                                if (v5_2 != null) {
                                    android.net.Uri[] v0_4 = new android.net.Uri[0];
                                    v5_2.onReceiveValue(v0_4);
                                }
                                v4_10.q = 0;
                                return;
                            } else {
                                boolean v4_11 = this.V;
                                if (v4_11) {
                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v4_11, wmoi.igob.ggcn(15008))) {
                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v4_11, wmoi.igob.ggcn(15009))) {
                                            this.x1();
                                            return;
                                        } else {
                                            this.w1(0);
                                            return;
                                        }
                                    } else {
                                        this.w1(1);
                                        return;
                                    }
                                } else {
                                    this.x1();
                                    return;
                                }
                            }
                        } else {
                            super.r1(p4, p5);
                            return;
                        }
                    }
                }
            }
        }
    }

    public final android.content.Intent t1()
    {
        android.content.Intent v0_1 = new android.content.Intent(wmoi.igob.ggcn(15011));
        if (v0_1.resolveActivity(this.requireActivity().getPackageManager()) != null) {
            try {
                android.net.Uri v1_1 = this.u1();
            } catch (android.net.Uri v1_2) {
                com.google.android.gms.internal.mlkit_vision_barcode.h7.e(this.getActivity(), v1_2.getMessage());
                v1_1 = 0;
            }
            if (v1_1 != null) {
                android.net.Uri v1_4 = androidx.core.content.FileProvider.getUriForFile(this.requireContext(), this.getString(2131952832), v1_1);
                int v3_2 = 0;
                while (v3_2 < nY.a.a) {
                    int v4_1 = new android.hardware.Camera$CameraInfo();
                    android.hardware.Camera.getCameraInfo(v3_2, v4_1);
                    if (1 != v4_1.facing) {
                        v3_2++;
                    } else {
                        v0_1.putExtra(wmoi.igob.ggcn(15012), 1);
                        break;
                    }
                }
                v0_1.putExtra(wmoi.igob.ggcn(15013), v1_4);
            }
        }
        return v0_1;
    }

    public final java.io.File u1()
    {
        String v1_2;
        java.io.File v0_1 = new java.text.SimpleDateFormat(wmoi.igob.ggcn(15014), java.util.Locale.US).format(Long.valueOf(System.currentTimeMillis()));
        String v1_1 = this.getActivity();
        if (v1_1 == null) {
            v1_2 = 0;
        } else {
            v1_2 = v1_1.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        }
        String v2_2 = new StringBuilder(wmoi.igob.ggcn(15015));
        v2_2.append(v0_1);
        v2_2.append(wmoi.igob.ggcn(15016));
        java.io.File v0_5 = java.io.File.createTempFile(v2_2.toString(), wmoi.igob.ggcn(15017), v1_2);
        this.U = androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(15018), v0_5.getAbsolutePath());
        return v0_5;
    }

    public void v1()
    {
        return;
    }

    public final void w1(int p4)
    {
        android.content.Intent v0_1 = new android.content.Intent(this.getContext(), vn.com.vng.zalopay.camera.CustomDefaultCamera);
        v0_1.putExtra(wmoi.igob.ggcn(15019), p4);
        this.startActivityForResult(v0_1, 3652);
        return;
    }

    public final void x1()
    {
        int v0_7 = new android.content.Intent(wmoi.igob.ggcn(15020));
        v0_7.addCategory(wmoi.igob.ggcn(15021));
        v0_7.setType(wmoi.igob.ggcn(15022));
        android.content.Intent v1_3 = new android.content.Intent(wmoi.igob.ggcn(15023));
        v1_3.putExtra(wmoi.igob.ggcn(15024), v0_7);
        v1_3.putExtra(wmoi.igob.ggcn(15025), this.getString(2131955281));
        v1_3.putExtra(wmoi.igob.ggcn(15026), ((android.os.Parcelable[]) new android.content.Intent[] {this.t1()})));
        this.startActivityForResult(v1_3, 3652);
        return;
    }

    public final void y1(java.io.File p6)
    {
        iR.l v0 = 0;
        try {
            int v1_3;
            int v1_1 = this.getContext();
        } catch (Exception) {
            int v1_0 = 0;
            int v6_1 = p6.delete();
            if ((v1_0 == 0) || ((v1_0.length() == 0) || (v6_1 == 0))) {
                int v6_4 = this.F;
                if (v6_4 != 0) {
                    v0 = v6_4;
                }
                iR.l.o0(v0, 0);
                return;
            } else {
                int v6_2 = this.F;
                if (v6_2 != 0) {
                    v0 = v6_2;
                }
                iR.l.o0(v0, 1);
                return;
            }
        }
        if (v1_1 == 0) {
            v1_3 = 0;
        } else {
            v1_3 = v1_1.getContentResolver();
        }
        v1_0 = android.provider.MediaStore$Images$Media.insertImage(v1_3, p6.getAbsolutePath(), p6.getName(), this.getString(2131955799));
    }

    public final void z1(java.io.File p6)
    {
        iR.l v0 = 0;
        try {
            int v1_3;
            int v1_1 = this.getContext();
        } catch (Exception) {
            int v1_0 = 0;
            int v6_1 = p6.delete();
            if ((v1_0 == 0) || ((v1_0.length() == 0) || (v6_1 == 0))) {
                int v6_4 = this.F;
                if (v6_4 != 0) {
                    v0 = v6_4;
                }
                iR.l.q0(v0, 0);
                return;
            } else {
                int v6_2 = this.F;
                if (v6_2 != 0) {
                    v0 = v6_2;
                }
                iR.l.q0(v0, 1);
                return;
            }
        }
        if (v1_1 == 0) {
            v1_3 = 0;
        } else {
            v1_3 = v1_1.getContentResolver();
        }
        v1_0 = android.provider.MediaStore$Images$Media.insertImage(v1_3, p6.getAbsolutePath(), p6.getName(), this.getString(2131955799));
    }
}
