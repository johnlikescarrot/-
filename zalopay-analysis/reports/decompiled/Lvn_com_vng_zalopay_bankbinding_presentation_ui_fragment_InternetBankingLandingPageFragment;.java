package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class InternetBankingLandingPageFragment extends TO.a {
    public Ew.D A;
    public Ju.b B;
    public Bo.n C;
    public final ng.w D;
    public Yv.i E;
    public String F;
    public final ng.S G;
    public final ng.S H;
    public Bx.b I;
    public final a9.f s;
    public final ng.S t;
    public Sn.c u;
    public Eo.a v;
    public Z2.L w;
    public ji.u0 x;
    public To.g y;
    public final ng.w z;

    public InternetBankingLandingPageFragment()
    {
        int v1_0 = kotlin.jvm.internal.M.a;
        this.s = new a9.f(v1_0.b(Lo.n0), new Lo.F(this, 18));
        this.t = ng.x.b(new Lo.l0(this, 0));
        ng.S v0_5 = new Lo.l0(this, 4);
        Lo.Y v3_1 = ng.y.c;
        ng.w v2_4 = ng.x.a(v3_1, new Io.M(20, new Lo.F(this, 19)));
        this.z = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.V), new Lo.Y(v2_4, 10), new Lo.Y(v2_4, 11), v0_5);
        ng.S v0_8 = new Lo.l0(this, 5);
        ng.w v2_10 = ng.x.a(v3_1, new Io.M(21, new Lo.F(this, 20)));
        this.D = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.t), new Lo.Y(v2_10, 12), new Lo.Y(v2_10, 13), v0_8);
        this.G = ng.x.b(new Lo.l0(this, 6));
        this.H = ng.x.b(new Lo.l0(this, 7));
        return;
    }

    public final int n1()
    {
        return -2079850468;
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
            this.v = v2_4;
            v2_4.m().r(this);
            return;
        }
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        android.view.View v1_1 = super.onCreateView(p1, p2, p3);
        this.I = new Bx.b(this);
        return v1_1;
    }

    public final void onDestroy()
    {
        KR.c v0_0 = this.I;
        KR.c v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        KR.c v0_2 = ((KR.c) v0_0.m);
        if (v0_2 != null) {
            v1 = v0_2;
        }
        KR.c.i(v1);
        super.onDestroy();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        UnsupportedOperationException v1_1 = kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("bankCode", ((Bo.o) this.G.getValue()).a));
        UR.a.e("01.2030.000", v1_1);
        java.util.Objects.toString(v1_1);
        UnsupportedOperationException v1_12 = ((vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel) m9.C.b(this, "vcb_online_link_fail"));
        if (v1_12 != null) {
            Ao.c.q(this, v1_12.getMessage(), "", "VCB", v1_12.getLinkingType());
        }
        UnsupportedOperationException v1_17 = ((vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingProcessingModel) m9.C.b(this, "vcb_online_unknown_error"));
        if (v1_17 != null) {
            String v2_9 = v1_17;
            Ao.c.t(this, v2_9.getMessage(), "", "VCB", v2_9.getLinkingType(), 0, 0);
        }
        UnsupportedOperationException v1_22 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) m9.C.b(this, "bank_web_error_bottom_popup_result"));
        if (v1_22 != null) {
            Bo.c.d(this.s1(), v1_22.getMessage(), v1_22.getBcErrorCode(), v1_22.getIssuerBankName(), 0, 24);
            java.io.Serializable v8_2 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument(this.getString(-2079719399), v1_22.getMessage(), 2131234260, -2079915887, "page_internet_banking_bottom_dialog_result", v1_22.getBcErrorCode(), 0, v1_22.getBankCode(), v1_22.getLinkingType(), v1_22.getZpTransId(), v1_22.getBmErrorCode(), 64, 0);
            Ao.c.l(this);
            UnsupportedOperationException v1_25 = this.w;
            if (v1_25 == null) {
                v1_25 = 0;
            }
            v1_25.getClass();
            String v3_7 = new android.os.Bundle();
            if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument)) {
                if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument)) {
                    throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                } else {
                    v3_7.putSerializable("bank_bottom_dialog_args", ((java.io.Serializable) v8_2));
                }
            } else {
                v3_7.putParcelable("bank_bottom_dialog_args", v8_2);
            }
            v1_25.k(-2079916004, v3_7, 0);
        }
        UnsupportedOperationException v1_4 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ProcessingModel) m9.C.b(this, "bank_web_processing_result"));
        if (v1_4 != null) {
            int v4_1 = v1_4;
            Class v5_0 = v4_1;
            String v6_0 = v5_0;
            Ao.c.t(this, this.getString(-2079719392), v4_1.getZpTransId(), v1_4.getBankCode(), v5_0.getLinkingType(), v6_0.getBmErrorCode(), v6_0.getBcErrorCode());
        }
        UnsupportedOperationException v1_8 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$GenericErrorModel) m9.C.b(this, "bank_web_error_generic_result"));
        if (v1_8 != null) {
            String v2_6 = v1_8.getMessage();
            if (v2_6 == null) {
                v2_6 = this.getString(-2079719397);
            }
            Ao.c.q(this, v2_6, v1_8.getZpTransId(), v1_8.getBankCode(), v1_8.getLinkingType());
        }
        return;
    }

    public final void onViewCreated(android.view.View p13, android.os.Bundle p14)
    {
        this = super.onViewCreated(p13, p14);
        cV.y v1_0 = -2079915941;
        int v6_4 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915941, p13));
        if (v6_4 != 0) {
            v1_0 = -2079915891;
            int v7_1 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915891, p13));
            if (v7_1 != 0) {
                v1_0 = -2079915871;
                if (((android.widget.LinearLayout) l5.s.a(-2079915871, p13)) != null) {
                    v1_0 = -2079915848;
                    if (((android.widget.ScrollView) l5.s.a(-2079915848, p13)) != null) {
                        v1_0 = -2079915832;
                        androidx.lifecycle.T v8_3 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(-2079915832, p13));
                        if (v8_3 != null) {
                            v1_0 = -2079915827;
                            cV.y v9_4 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(-2079915827, p13));
                            if (v9_4 != null) {
                                v1_0 = -2079915797;
                                cV.y v10_3 = ((android.widget.TextView) l5.s.a(-2079915797, p13));
                                if (v10_3 != null) {
                                    v1_0 = -2079915783;
                                    android.widget.TextView v11_1 = ((android.widget.TextView) l5.s.a(-2079915783, p13));
                                    if (v11_1 != null) {
                                        this.u = new Sn.c(((vn.zalopay.ui.KeyboardRelativeLayout) p13), v6_4, v7_1, v8_3, v9_4, v10_3, v11_1);
                                        this.w = q9.p0.a(this);
                                        IllegalStateException v0_28 = this.u;
                                        if (v0_28 == null) {
                                            throw new IllegalStateException("Required value was null.");
                                        } else {
                                            String v5_0;
                                            com.google.android.gms.internal.mlkit_vision_barcode.V7.c(v0_28.e, this.q1().getTitle());
                                            com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v0_28.d, this.q1().getInformation(), new mR.a(this, new Ko.g(this, 7)));
                                            androidx.lifecycle.F v3_29 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) v0_28.h);
                                            int v4_21 = this.getContext();
                                            String v5_12 = this.q1().getBankCode();
                                            if (!android.text.TextUtils.isEmpty(v5_12)) {
                                                v5_0 = v5_12.toLowerCase();
                                            } else {
                                                v5_0 = "";
                                            }
                                            v3_29.setImageName(String.format(v4_21.getString(2131952892), new Object[] {v5_0})));
                                            ((androidx.appcompat.widget.AppCompatButton) v0_28.f).setOnClickListener(new Io.A(9, this));
                                            IllegalStateException v0_2 = this.x;
                                            if (v0_2 == null) {
                                                v0_2 = 0;
                                            }
                                            int v4_4 = new uo.j(this.q1().getBankName());
                                            v0_2.getClass();
                                            v0_2.l(0, v4_4);
                                            IllegalStateException v0_3 = this.u;
                                            if (v0_3 == null) {
                                                throw new IllegalStateException("Required value was null.");
                                            } else {
                                                ((androidx.compose.ui.platform.ComposeView) v0_3.g).setContent(new y0.b(new Lo.m0(this, 0), 1, 618664276));
                                                IllegalStateException v0_8 = this.u;
                                                if (v0_8 == null) {
                                                    throw new IllegalStateException("Required value was null.");
                                                } else {
                                                    ((androidx.compose.ui.platform.ComposeView) v0_8.c).setContent(new y0.b(new Lo.m0(this, 1), 1, 372728587));
                                                    androidx.lifecycle.T v8_0 = this.r1();
                                                    v8_0.k.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.g(this, 5)));
                                                    v8_0.m.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.InternetBankingLandingPageFragment, "handleCheckIBankingState", "handleCheckIBankingState(Lvn/com/vng/zalopay/bankbinding/presentation/model/UiStateModel;)V", 0, 19)));
                                                    v8_0.o.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.InternetBankingLandingPageFragment, "handleSubmitAccountModeCState", "handleSubmitAccountModeCState(Lvn/com/vng/zalopay/bankbinding/presentation/model/UiStateModel;)V", 0, 20)));
                                                    ((Ro.t) this.D.getValue()).f.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.g(this, 6)));
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p13.getResources().getResourceName(v1_0)));
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) this.t.getValue());
    }

    public final Ro.V r1()
    {
        return ((Ro.V) this.z.getValue());
    }

    public final Bo.c s1()
    {
        return ((Bo.c) this.H.getValue());
    }
}
