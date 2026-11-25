package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankSuccessFragment extends Ts.c {
    public Z2.L A;
    public final a9.f B;
    public final ng.S C;
    public final ng.S D;
    public final boolean E;
    public Sn.g s;
    public Eo.a t;
    public Yv.i u;
    public vn.com.vng.zalopay.navigation.b v;
    public ji.u0 w;
    public com.airbnb.lottie.network.g x;
    public vn.com.vng.zalopay.feature.argument.BankBindingArgs y;
    public final ng.w z;

    public BankSuccessFragment()
    {
        int v0_3 = new Lo.c0(this, 0);
        int v1_0 = ng.x.a(ng.y.c, new Io.M(17, new Lo.F(this, 13)));
        Lo.F v2_0 = kotlin.jvm.internal.M.a;
        this.z = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v2_0.b(FT.b), new Lo.Y(v1_0, 4), new Lo.Y(v1_0, 5), v0_3);
        this.B = new a9.f(v2_0.b(Lo.d0), new Lo.F(this, 12));
        this.C = ng.x.b(new Lo.c0(this, 1));
        this.D = ng.x.b(new Lo.c0(this, 2));
        this.E = 1;
        return;
    }

    public final boolean m1()
    {
        return this.E;
    }

    public final int n1()
    {
        return -2079850479;
    }

    public final void onAttach(android.content.Context p2)
    {
        IllegalAccessException v2_4;
        super.onAttach(p2);
        IllegalAccessException v2_3 = this.getActivity();
        if (!(v2_3 instanceof Eo.a)) {
            v2_4 = 0;
        } else {
            v2_4 = ((Eo.a) v2_3);
        }
        if (v2_4 == null) {
            throw new IllegalAccessException("Parent activity must implement BankComponentListener");
        } else {
            this.t = v2_4;
            v2_4.m().h(this);
            return;
        }
    }

    public final void onViewCreated(android.view.View p12, android.os.Bundle p13)
    {
        super.onViewCreated(p12, p13);
        androidx.appcompat.widget.AppCompatButton v13_1 = -2079915950;
        int v3_5 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(-2079915950, p12));
        if (v3_5 != 0) {
            v13_1 = -2079915943;
            int v4_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915943, p12));
            if (v4_1 != 0) {
                v13_1 = -2079915937;
                int v5_2 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915937, p12));
                if (v5_2 != 0) {
                    v13_1 = -2079915917;
                    if (((android.widget.LinearLayout) l5.s.a(-2079915917, p12)) != null) {
                        v13_1 = -2079915876;
                        int v6_2 = ((android.widget.LinearLayout) l5.s.a(-2079915876, p12));
                        if (v6_2 != 0) {
                            v13_1 = -2079915874;
                            int v1_48 = l5.s.a(-2079915874, p12);
                            if (v1_48 != 0) {
                                androidx.appcompat.widget.AppCompatButton v13_48 = -2079915875;
                                if (((android.widget.LinearLayout) l5.s.a(-2079915875, v1_48)) != null) {
                                    v13_48 = -2079915873;
                                    if (((android.widget.LinearLayout) l5.s.a(-2079915873, v1_48)) != null) {
                                        int v2_10 = -2079915782;
                                        java.util.Date v7_4 = ((android.widget.TextView) l5.s.a(-2079915782, v1_48));
                                        if (v7_4 != null) {
                                            v2_10 = -2079915822;
                                            java.util.Locale v8_2 = ((android.widget.TextView) l5.s.a(-2079915822, v1_48));
                                            if (v8_2 != null) {
                                                v2_10 = -2079915781;
                                                androidx.appcompat.widget.AppCompatTextView v9_1 = ((android.widget.TextView) l5.s.a(-2079915781, v1_48));
                                                if (v9_1 != null) {
                                                    java.util.Date v7_6 = new Sn.v(((android.widget.LinearLayout) v1_48), v7_4, v8_2, v9_1);
                                                    v13_1 = -2079915838;
                                                    if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915838, p12)) == null) {
                                                        throw new NullPointerException("Missing required view with ID: ".concat(p12.getResources().getResourceName(v13_1)));
                                                    } else {
                                                        v13_1 = -2079915837;
                                                        if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915837, p12)) == null) {
                                                        } else {
                                                            v13_1 = -2079915808;
                                                            java.util.Locale v8_4 = ((android.widget.TextView) l5.s.a(-2079915808, p12));
                                                            if (v8_4 == null) {
                                                            } else {
                                                                v13_1 = -2079915823;
                                                                androidx.appcompat.widget.AppCompatTextView v9_3 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915823, p12));
                                                                if (v9_3 == null) {
                                                                } else {
                                                                    this.s = new Sn.g(((android.widget.RelativeLayout) p12), v3_5, v4_1, v5_2, v6_2, v7_6, v8_4, v9_3);
                                                                    this.A = q9.p0.a(this);
                                                                    int v12_5 = this.D;
                                                                    androidx.appcompat.widget.AppCompatButton v13_5 = ((Bo.i) v12_5.getValue());
                                                                    androidx.appcompat.widget.AppCompatButton v13_9 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v13_5.a), kotlin.TuplesKt.to("bankNameCYBS", v13_5.b), kotlin.TuplesKt.to("linkingType", Integer.valueOf(v13_5.c)), kotlin.TuplesKt.to("zpTransID", v13_5.d), kotlin.TuplesKt.to("appVer", "11.17.0")}));
                                                                    UR.a.e("01.2003.000", v13_9);
                                                                    java.util.Objects.toString(v13_9);
                                                                    java.util.Objects.toString(v13_9);
                                                                    androidx.appcompat.widget.AppCompatButton v13_10 = this.w;
                                                                    int v0_3 = 0;
                                                                    if (v13_10 == null) {
                                                                        v13_10 = 0;
                                                                    }
                                                                    androidx.appcompat.widget.AppCompatButton v13_17;
                                                                    v13_10.getClass();
                                                                    v13_10.l(0, uo.n.a);
                                                                    this.r1().f.setText(this.q1().getTitle());
                                                                    androidx.appcompat.widget.AppCompatButton v13_15 = com.google.android.gms.internal.mlkit_vision_barcode.j7.a(this.q1().getZpTransId());
                                                                    com.google.android.gms.internal.mlkit_vision_barcode.V7.e(((android.widget.TextView) ((Sn.v) this.r1().h).c), v13_15);
                                                                    if ((v13_15 != null) && (v13_15.length() != 0)) {
                                                                        v13_17 = 0;
                                                                    } else {
                                                                        v13_17 = 1;
                                                                    }
                                                                    com.google.android.gms.internal.mlkit_vision_barcode.W7.d(((Sn.v) this.r1().h).d, (v13_17 ^ 1));
                                                                    ((Sn.v) this.r1().h).e.setText(new java.text.SimpleDateFormat("HH:mm - dd/MM/yyyy", java.util.Locale.getDefault()).format(new java.util.Date(new java.util.Date().getTime())));
                                                                    androidx.appcompat.widget.AppCompatButton v13_24 = this.r1().e;
                                                                    androidx.core.view.h0.l(v13_24, new hJ.g(v13_24));
                                                                    if (!v13_24.isAttachedToWindow()) {
                                                                        v13_24.addOnAttachStateChangeListener(new nY.e());
                                                                    } else {
                                                                        v13_24.requestApplyInsets();
                                                                    }
                                                                    if (this.q1().getMessage().length() <= 0) {
                                                                        ((android.widget.TextView) this.r1().i).setVisibility(8);
                                                                    } else {
                                                                        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(((android.widget.TextView) this.r1().i), this.q1().getMessage());
                                                                        com.google.android.gms.internal.mlkit_vision_barcode.W7.d(((android.widget.TextView) this.r1().i), 1);
                                                                    }
                                                                    this.r1().c.setContent(new y0.b(new Fo.b(8, this), 1, 316176822));
                                                                    androidx.appcompat.widget.AppCompatButton v13_39 = this.y;
                                                                    if (v13_39 == null) {
                                                                        v13_39 = 0;
                                                                    }
                                                                    androidx.appcompat.widget.AppCompatButton v13_40 = v13_39.getTextMainCtaResultPage();
                                                                    if (kotlin.text.StringsKt.isBlank(v13_40)) {
                                                                        v13_40 = 0;
                                                                    }
                                                                    if (v13_40 != null) {
                                                                        this.r1().d.setText(v13_40);
                                                                    }
                                                                    this.r1().d.setOnClickListener(new Io.A(7, this));
                                                                    androidx.appcompat.widget.AppCompatButton v13_45 = kotlin.collections.CollectionsKt.firstOrNull(this.q1().getCta());
                                                                    int v1_41 = ((vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity) v13_45);
                                                                    if (v1_41 == 0) {
                                                                        v13_45 = 0;
                                                                    } else {
                                                                        int v5_9 = v1_41.getUrl();
                                                                        if ((v5_9 == 0) || ((kotlin.text.StringsKt.isBlank(v5_9)) || (kotlin.text.StringsKt.isBlank(v1_41.getLabel())))) {
                                                                        }
                                                                    }
                                                                    androidx.appcompat.widget.AppCompatButton v13_46 = ((vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity) v13_45);
                                                                    int v1_44 = this.y;
                                                                    if (v1_44 != 0) {
                                                                        v0_3 = v1_44;
                                                                    }
                                                                    if ((v0_3.getEnableSubCtaResultPage()) && (v13_46 != null)) {
                                                                        int v12_7 = ((Bo.i) v12_5.getValue());
                                                                        int v0_6 = v13_46.getUrl();
                                                                        int v1_45 = v13_46.getLabel();
                                                                        v12_7.getClass();
                                                                        int v12_11 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("title", v1_45), kotlin.TuplesKt.to("deeplink", v0_6), kotlin.TuplesKt.to("appVer", "11.17.0")}));
                                                                        UR.a.e("01.2003.006", v12_11);
                                                                        java.util.Objects.toString(v12_11);
                                                                        ((androidx.appcompat.widget.AppCompatButton) this.r1().g).setOnClickListener(new Io.L(this, v13_46, 6));
                                                                        ((androidx.appcompat.widget.AppCompatButton) this.r1().g).setText(v13_46.getLabel());
                                                                        com.google.android.gms.internal.mlkit_vision_barcode.W7.d(((androidx.appcompat.widget.AppCompatButton) this.r1().g), 1);
                                                                        int v12_21 = this.getContext();
                                                                        if (v12_21 != 0) {
                                                                            this.r1().d.setBackground(v12_21.getDrawable(-2079981516));
                                                                            this.r1().d.setTextColor(v12_21.getColor(2131100627));
                                                                        }
                                                                    }
                                                                    FT.b.g0(((FT.b) this.z.getValue()), this.q1().getBankCode(), this.q1().getLinkingFunctionId(), 3, 0, 96);
                                                                    return;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        v13_48 = v2_10;
                                    }
                                }
                                throw new NullPointerException("Missing required view with ID: ".concat(v1_48.getResources().getResourceName(v13_48)));
                            }
                        }
                    }
                }
            }
        }
    }

    public final boolean p1()
    {
        int v0_7 = ((Bo.i) this.D.getValue());
        int v0_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v0_7.a), kotlin.TuplesKt.to("bankNameCYBS", v0_7.b)}));
        UR.a.e("01.2003.001", v0_3);
        java.util.Objects.toString(v0_3);
        java.util.Objects.toString(v0_3);
        int v0_5 = this.getActivity();
        if (v0_5 != 0) {
            v0_5.finish();
        }
        return 1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) this.C.getValue());
    }

    public final Sn.g r1()
    {
        IllegalStateException v0_0 = this.s;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }
}
