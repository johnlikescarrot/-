package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankDetailFormFragment extends TO.a implements Po.l {
    public ji.u0 A;
    public vn.com.vng.zalopay.feature.argument.BankBindingArgs B;
    public Bo.d C;
    public final android.util.SparseArray D;
    public ji.u0 E;
    public Bo.n F;
    public final ng.w G;
    public Yv.i H;
    public Ew.D I;
    public final java.util.ArrayList J;
    public final java.util.LinkedHashMap K;
    public To.e L;
    public final ng.w M;
    public final boolean N;
    public Sn.f s;
    public Z2.L t;
    public final a9.f u;
    public final ng.S v;
    public Eo.a w;
    public Po.n x;
    public To.c y;
    public final ng.w z;

    public BankDetailFormFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.u = new a9.f(v1_0.b(Lo.t), new Io.i(this, 21));
        this.v = ng.x.b(new Lo.n(this, 0));
        int v0_5 = new Lo.n(this, 1);
        Io.N v3_1 = ng.y.c;
        ng.w v2_4 = ng.x.a(v3_1, new Io.M(5, new Io.i(this, 23)));
        this.z = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.o), new Io.N(v2_4, 10), new Io.N(v2_4, 11), v0_5);
        this.D = new android.util.SparseArray();
        int v0_10 = new Lo.n(this, 3);
        ng.w v2_10 = ng.x.a(v3_1, new Io.M(6, new Io.i(this, 24)));
        this.G = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.t), new Io.N(v2_10, 12), new Io.N(v2_10, 13), v0_10);
        this.J = new java.util.ArrayList();
        this.K = new java.util.LinkedHashMap();
        int v0_17 = new Lo.n(this, 4);
        ng.w v2_14 = ng.x.a(v3_1, new Io.M(4, new Io.i(this, 22)));
        this.M = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.q), new Io.N(v2_14, 8), new Io.N(v2_14, 9), v0_17);
        this.N = 1;
        return;
    }

    public final void G0(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2, int p3)
    {
        this.v1(p2);
        java.util.Map v2_1 = this.C;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        java.util.Map v2_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v2_1.a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p3))}));
        UR.a.e("01.2027.028", v2_3);
        java.util.Objects.toString(v2_3);
        return;
    }

    public final void f0(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2, int p3)
    {
        this.v1(p2);
        java.util.Map v2_1 = this.C;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        java.util.Map v2_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v2_1.a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p3))}));
        UR.a.e("01.2027.017", v2_3);
        java.util.Objects.toString(v2_3);
        return;
    }

    public final boolean m1()
    {
        return this.N;
    }

    public final int n1()
    {
        return -2079850487;
    }

    public final void onAttach(android.content.Context p2)
    {
        IllegalAccessException v2_3;
        super.onAttach(p2);
        IllegalAccessException v2_2 = this.getActivity();
        if (!(v2_2 instanceof Eo.a)) {
            v2_3 = 0;
        } else {
            v2_3 = ((Eo.a) v2_2);
        }
        if (v2_3 == null) {
            throw new IllegalAccessException("Parent activity must implement BankComponentListener");
        } else {
            this.w = v2_3;
            return;
        }
    }

    public final void onCreate(android.os.Bundle p21)
    {
        super.onCreate(p21);
        int v1_1 = this.w;
        if (v1_1 == 0) {
            v1_1 = 0;
        }
        int v1_6 = v1_1.m().a();
        Is.a v2_0 = this.q1();
        v2_0.getClass();
        int v1_0 = v1_6.a;
        dagger.internal.o v11 = dagger.internal.f.b(new so.a(new Bo.p(), dagger.internal.k.a(v2_0), v1_0.o, v1_0.Y));
        this.y = ((To.c) dagger.internal.f.b(new To.d(v1_0.U, v1_0.X, v1_0.Z, v11, v1_0.Y, v1_0.b0, new Pn.i(v1_0.c0, 12), v1_0.d0, v1_0.e0, v1_0.o, v1_0.f0, v1_0.g0)).get());
        this.A = v1_0.a;
        this.B = v1_0.e;
        this.C = ((Bo.d) v11.get());
        this.E = v1_0.b;
        this.F = v1_0.t();
        Is.a v2_13 = v1_0.c;
        this.H = v2_13.K1();
        To.e v3_5 = v2_13.r();
        p9.n7.b(v3_5);
        this.I = v3_5;
        int v1_5 = ((go.h) v1_0.z.get());
        Is.a v2_14 = v2_13.m();
        p9.n7.b(v2_14);
        this.L = new To.e(new Bo.n(v1_5, v2_14, 10), 0);
        return;
    }

    public final void onDestroyView()
    {
        this.s = 0;
        super.onDestroyView();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        kotlin.NoWhenBranchMatchedException v0_0 = this.C;
        String v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        String v4_12 = kotlin.TuplesKt.to("bankCode", v0_0.a);
        kotlin.TuplesKt.to("ccBankCode", v0_0.b);
        kotlin.TuplesKt.to("bankNameCYBS", v0_0.e);
        kotlin.TuplesKt.to("appVer", "11.17.0");
        kotlin.TuplesKt.to("flowSessionId", v0_0.g);
        kotlin.NoWhenBranchMatchedException v0_28 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {v4_12, kotlin.TuplesKt.to("kycLevel", Integer.valueOf(v0_0.h))}));
        UR.a.e("01.2027.000", v0_28);
        java.util.Objects.toString(v0_28);
        int v2_23 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebFailResultModel) m9.C.b(this, "bank_web_app_fail"));
        if (v2_23 != 0) {
            m9.C.g(this, v2_23, "bank_web_app_fail");
            kotlin.NoWhenBranchMatchedException v0_30 = this.t;
            if (v0_30 == null) {
                v0_30 = 0;
            }
            v0_30.o();
        }
        kotlin.NoWhenBranchMatchedException v0_33 = ((uo.M) m9.C.b(this, "bank_3ds_submit_result"));
        if (v0_33 != null) {
            if (!(v0_33 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$SuccessModel)) {
                if (!(v0_33 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ToolTipErrorModel)) {
                    if (!(v0_33 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$BottomDialogErrorModel)) {
                        if (!(v0_33 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ProcessingModel)) {
                            if (!(v0_33 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$GenericErrorModel)) {
                                if ((!(v0_33 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen)) && (!(v0_33 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen))) {
                                    throw new kotlin.NoWhenBranchMatchedException();
                                }
                            } else {
                                m9.C.g(this, v0_33, "bank_web_error_generic_result");
                                kotlin.NoWhenBranchMatchedException v0_37 = this.t;
                                if (v0_37 == null) {
                                    v0_37 = 0;
                                }
                                v0_37.o();
                            }
                        } else {
                            m9.C.g(this, v0_33, "bank_web_processing_result");
                            kotlin.NoWhenBranchMatchedException v0_38 = this.t;
                            if (v0_38 == null) {
                                v0_38 = 0;
                            }
                            v0_38.o();
                        }
                    } else {
                        Ao.c.n(this, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$BottomDialogErrorModel) v0_33).transform(), "bank_web_error_bottom_popup_result");
                    }
                } else {
                    this.z1(((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ToolTipErrorModel) v0_33).transform());
                }
            } else {
                int v3_16 = this.A;
                if (v3_16 == 0) {
                    v3_16 = 0;
                }
                kotlin.NoWhenBranchMatchedException v0_43 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$SuccessModel) v0_33);
                int v5_4 = v0_43.getZpTransId();
                String v6_2 = v0_43.getResultCode();
                int v7_1 = this.q1().getBankCode();
                int v8_1 = this.q1().getIssuerBankName();
                String v9_0 = v0_43.getMessage();
                String v10_2 = com.google.android.gms.internal.mlkit_vision_barcode.D0.h(this.q1().getCardNumber());
                int v11_2 = com.google.android.gms.internal.mlkit_vision_barcode.D0.i(4, this.q1().getCardNumber());
                this.q1().getClientBankCode();
                String v4_0 = new uo.v(v5_4, v6_2, v7_1, v8_1, v9_0, v10_2, v11_2, this.q1().getFunctionId(), v0_43.getCta(), v0_43.getTitle());
                v3_16.getClass();
                v3_16.l(0, v4_0);
            }
        }
        kotlin.NoWhenBranchMatchedException v0_3 = ((uo.z) m9.C.b(this, "bank_otp_result"));
        if (v0_3 != null) {
            if (!(v0_3 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$GenericErrorModel)) {
                if (!(v0_3 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel)) {
                    if (!(v0_3 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ToolTipErrorModel)) {
                        if (!(v0_3 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ProcessingModel)) {
                            if (!(v0_3 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel)) {
                                if ((v0_3 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel)) {
                                    kotlin.NoWhenBranchMatchedException v0_4 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel) v0_3);
                                    Ao.c.m(this, v0_4.getMessage(), v0_4.getZpTransId(), v0_4.getBcErrorCode(), v0_4.getBmErrorCode());
                                }
                            } else {
                                Ao.c.m(this, this.getString(-2079719394), ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel) v0_3).getZpTransId(), 0, 0);
                            }
                        } else {
                            m9.C.g(this, v0_3, "bank_otp_processing_result");
                            kotlin.NoWhenBranchMatchedException v0_8 = this.t;
                            if (v0_8 == null) {
                                v0_8 = 0;
                            }
                            v0_8.o();
                        }
                    } else {
                        kotlin.NoWhenBranchMatchedException v0_9 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ToolTipErrorModel) v0_3);
                        this.z1(new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel(v0_9.getSuggestAction(), v0_9.getMessage(), v0_9.getPage(), v0_9.getBcErrorCode(), v0_9.getZpTransId(), this.q1().getIssuerBankName(), v0_9.getBmErrorCode()));
                    }
                } else {
                    kotlin.NoWhenBranchMatchedException v0_10 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel) v0_3);
                    Ao.c.n(this, new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel(v0_10.getMessage(), v0_10.getPage(), v0_10.getBcErrorCode(), v0_10.getZpTransId(), this.q1().getIssuerBankName(), this.q1().getFunctionId(), this.q1().getBankCode(), v0_10.getBmErrorCode()), "bank_web_error_bottom_popup_result");
                }
            } else {
                kotlin.NoWhenBranchMatchedException v0_11 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$GenericErrorModel) v0_3);
                Ao.c.m(this, v0_11.getMessage(), v0_11.getZpTransId(), 0, 0);
            }
        }
        int v2_14 = ((uo.A) m9.C.b(this, "bank_rop_result"));
        if (v2_14 != 0) {
            if (!(v2_14 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel)) {
                if (!(v2_14 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel)) {
                    if (!(v2_14 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ProcessingModel)) {
                        if (!(v2_14 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$GenericErrorModel)) {
                            if (!(v2_14 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BankOtpModel)) {
                                if (!(v2_14 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$SuccessModel)) {
                                    throw new kotlin.NoWhenBranchMatchedException();
                                }
                            } else {
                                int v3_5 = new Object[0];
                                ck.b.a("[Bank] Not load OTP screen", v3_5);
                                int v2_15 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BankOtpModel) v2_14);
                                Ao.c.m(this, 0, v2_15.getZpTransId(), v2_15.getBcErrorCode(), v2_15.getBmErrorCode());
                                return;
                            }
                        } else {
                            int v2_17 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$GenericErrorModel) v2_14);
                            Ao.c.m(this, v2_17.getMessage(), v2_17.getZpTransId(), 0, 0);
                            return;
                        }
                    } else {
                        m9.C.g(this, v2_14, "bank_rop_result");
                        kotlin.NoWhenBranchMatchedException v0_24 = this.t;
                        if (v0_24 != null) {
                            v1_0 = v0_24;
                        }
                        v1_0.o();
                        return;
                    }
                } else {
                    Ao.c.n(this, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) v2_14).transform(), "bank_number_invalid_result");
                    return;
                }
            } else {
                this.z1(((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel) v2_14).transform());
                return;
            }
        }
        return;
    }

    public final void onViewCreated(android.view.View p22, android.os.Bundle p23)
    {
        void v21_1 = super.onViewCreated(p22, p23);
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_50 = this.E;
        if (v1_50 == null) {
            v1_50 = 0;
        }
        Ro.o v3_0 = new uo.j(this.q1().getBankName());
        v1_50.getClass();
        v1_50.l(0, v3_0);
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_14 = -2079915934;
        int v11_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915934, p22));
        if (v11_1 != 0) {
            v1_14 = -2079915932;
            BZ.n v12_2 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.BankCardView) l5.s.a(-2079915932, p22));
            if (v12_2 != null) {
                v1_14 = -2079915926;
                Ho.t v13_5 = ((android.widget.LinearLayout) l5.s.a(-2079915926, p22));
                if (v13_5 != null) {
                    v1_14 = -2079915920;
                    int v14_1 = ((android.widget.LinearLayout) l5.s.a(-2079915920, p22));
                    if (v14_1 != 0) {
                        v1_14 = -2079915919;
                        U2.a v15_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) l5.s.a(-2079915919, p22));
                        if (v15_1 != null) {
                            v1_14 = -2079915918;
                            int v16_1 = ((android.widget.LinearLayout) l5.s.a(-2079915918, p22));
                            if (v16_1 != 0) {
                                v1_14 = -2079915916;
                                int v17_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) l5.s.a(-2079915916, p22));
                                if (v17_1 != 0) {
                                    v1_14 = -2079915855;
                                    Ro.m v18_2 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(-2079915855, p22));
                                    if (v18_2 != null) {
                                        this.s = new Sn.f(((android.widget.ScrollView) p22), v11_1, v12_2, v13_5, v14_1, v15_1, v16_1, v17_1, v18_2);
                                        this.t = q9.p0.a(this);
                                        if (this.q1().getFunctionId() != 5) {
                                            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.BankCardView) this.s1().d).setCardNumber(this.q1().getCardNumber());
                                            if (this.q1().getFunctionId() != 2) {
                                                ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.BankCardView) this.s1().d).setLabel(this.getString(-2079719285));
                                            } else {
                                                ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.BankCardView) this.s1().d).setLabel(this.getString(-2079719289));
                                            }
                                            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.BankCardView) this.s1().d).setVisibleEditButton((this.q1().getFromDeeplink() ^ 1));
                                            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.BankCardView) this.s1().d).setOnClickListener(new Io.A(4, this));
                                        } else {
                                            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.BankCardView) this.s1().d).setVisibility(8);
                                        }
                                        Throwable v0_17 = this.D;
                                        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).removeAllViews();
                                        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().i).removeAllViews();
                                        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_21 = ((Iterable) this.q1().getBankBindingFields()).iterator();
                                        while (v1_21.hasNext()) {
                                            Ro.o v3_64 = ((uo.c) v1_21.next());
                                            int v5_24 = android.view.LayoutInflater.from(this.getContext()).inflate(-2079850447, 0, 0);
                                            int v7_17 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) l5.s.a(-2079915904, v5_24));
                                            if (v7_17 == 0) {
                                                throw new NullPointerException("Missing required view with ID: ".concat(v5_24.getResources().getResourceName(-2079915904)));
                                            } else {
                                                int v5_25 = ((android.widget.FrameLayout) v5_24);
                                                if (!(v3_64 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel)) {
                                                    if (!(v3_64 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel)) {
                                                        if (!(v3_64 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel)) {
                                                            if (!(v3_64 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel)) {
                                                                if (!(v3_64 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel)) {
                                                                    if (!(v3_64 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term)) {
                                                                        Ro.o v3_66 = v3_64.getClass();
                                                                        int v5_28 = new StringBuilder("[BankBinding] Not handle field ");
                                                                        v5_28.append(v3_66);
                                                                        String v4_10 = new Object[0];
                                                                        ck.b.e(v5_28.toString(), v4_10);
                                                                    } else {
                                                                        Ro.o v3_68 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term) v3_64);
                                                                        int v6_23 = this.C;
                                                                        if (v6_23 == 0) {
                                                                            v6_23 = 0;
                                                                        }
                                                                        int v6_27 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v6_23.a), kotlin.TuplesKt.to("description", v3_68.getEntity().getSubmitKey())}));
                                                                        UR.a.e("01.2027.035", v6_27);
                                                                        java.util.Objects.toString(v6_27);
                                                                        int v6_29 = this.r1().i0;
                                                                        if (!v6_29.containsKey(v3_68)) {
                                                                            v6_29.put(v3_68, Boolean.FALSE);
                                                                        }
                                                                        int v6_32 = Sn.x.a(android.view.LayoutInflater.from(this.getContext()), 0);
                                                                        int v7_22 = v6_32.b;
                                                                        this.K.put(v3_68, v6_32);
                                                                        com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v6_32.d, v3_68.getEntity().getMessage(), new BZ.n(2, new Ho.t(this, v3_68, 4)));
                                                                        v7_22.setOnCheckedChangeListener(new Lo.o(this, v3_68));
                                                                        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().i).addView(v6_32.c, ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().i).getChildCount());
                                                                        if (v3_68.getEntity().getAutoCheck()) {
                                                                            int v6_36 = this.C;
                                                                            if (v6_36 == 0) {
                                                                                v6_36 = 0;
                                                                            }
                                                                            Ro.o v3_74 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v6_36.a), kotlin.TuplesKt.to("description", v3_68.getEntity().getSubmitKey())}));
                                                                            UR.a.e("01.2027.036", v3_74);
                                                                            java.util.Objects.toString(v3_74);
                                                                            v7_22.setChecked(1);
                                                                        }
                                                                    }
                                                                } else {
                                                                    Ro.o v3_75 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel) v3_64);
                                                                    this.r1().j0(v3_75.getId());
                                                                    v0_17.put(v3_75.getId(), v7_17);
                                                                    v7_17.f(v3_75, this.q1().getFunctionId());
                                                                    ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).addView(v5_25, ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).getChildCount());
                                                                    this.r1().k0(v7_17.getTextChangeStateFlow(), v3_75.getId());
                                                                    v7_17.getButtonSelectType().setOnClickListener(new Io.L(this, v3_75, 3));
                                                                }
                                                            } else {
                                                                Ro.o v3_76 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel) v3_64);
                                                                this.r1().j0(v3_76.getId());
                                                                v0_17.put(v3_76.getId(), v7_17);
                                                                v7_17.f(v3_76, this.q1().getFunctionId());
                                                                ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).addView(v5_25, ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).getChildCount());
                                                                this.r1().k0(v7_17.getTextChangeStateFlow(), v3_76.getId());
                                                            }
                                                        } else {
                                                            Ro.o v3_78 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) v3_64);
                                                            this.r1().j0(v3_78.getId());
                                                            v0_17.put(v3_78.getId(), v7_17);
                                                            v7_17.f(v3_78, this.q1().getFunctionId());
                                                            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).addView(v5_25, ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).getChildCount());
                                                            this.r1().k0(v7_17.getTextChangeStateFlow(), v3_78.getId());
                                                        }
                                                    } else {
                                                        Ro.o v3_80 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel) v3_64);
                                                        this.r1().j0(v3_80.getId());
                                                        v0_17.put(v3_80.getId(), v7_17);
                                                        v7_17.f(v3_80, this.q1().getFunctionId());
                                                        v7_17.getEditText().setOnClickListener(new Io.L(this, v3_80, 4));
                                                        this.r1().k0(v7_17.getTextChangeStateFlow(), v3_80.getId());
                                                        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).addView(v5_25);
                                                    }
                                                } else {
                                                    Ro.o v3_85 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel) v3_64);
                                                    this.r1().j0(v3_85.getId());
                                                    v0_17.put(v3_85.getId(), v7_17);
                                                    v7_17.f(v3_85, this.q1().getFunctionId());
                                                    v7_17.getEditText().setOnClickListener(new Io.L(this, v3_85, 2));
                                                    this.r1().k0(v7_17.getTextChangeStateFlow(), v3_85.getId());
                                                    ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) this.s1().h).addView(v5_25);
                                                }
                                            }
                                        }
                                        if (!this.q1().getBankBindingFields().isEmpty()) {
                                            vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_29 = ((uo.c) kotlin.collections.CollectionsKt.first(this.q1().getBankBindingFields())).getId();
                                            Ro.o v3_8 = ((uo.c) kotlin.collections.CollectionsKt.last(this.q1().getBankBindingFields())).getId();
                                            int v5_0 = v0_17.size();
                                            int v6_0 = 0;
                                            while (v6_0 < v5_0) {
                                                int v7_0 = v0_17.keyAt(v6_0);
                                                int v11_3 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v0_17.valueAt(v6_0));
                                                if (v7_0 != v1_29) {
                                                    v11_3.i();
                                                } else {
                                                    v11_3.h();
                                                }
                                                if (v7_0 == v3_8) {
                                                    int v7_1 = v11_3.getEditText();
                                                    v7_1.setImeOptions((v7_1.getImeOptions() | 6));
                                                    v7_1.setOnEditorActionListener(new Lo.q(this, 0));
                                                }
                                                v6_0++;
                                            }
                                        }
                                        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_30 = v0_17.size();
                                        Ro.o v3_9 = 0;
                                        while (v3_9 < v1_30) {
                                            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v0_17.valueAt(v3_9)).getOnFocusChange().f(this.getViewLifecycleOwner(), new cV.y(2, new Lo.r(this, v0_17.keyAt(v3_9))));
                                            v3_9++;
                                        }
                                        Throwable v0_18 = this.J;
                                        v0_18.clear();
                                        ((android.widget.LinearLayout) this.s1().f).removeAllViews();
                                        if (!((java.util.Collection) this.q1().getTerms()).isEmpty()) {
                                            vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_38 = this.C;
                                            if (v1_38 == null) {
                                                v1_38 = 0;
                                            }
                                            vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_41 = kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("bankCode", v1_38.a));
                                            UR.a.e("01.2027.031", v1_41);
                                            java.util.Objects.toString(v1_41);
                                        }
                                        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_45 = ((Iterable) this.q1().getTerms()).iterator();
                                        while (v1_45.hasNext()) {
                                            Ro.o v3_58 = ((String) v1_45.next());
                                            int v5_17 = Sn.x.a(android.view.LayoutInflater.from(this.getContext()), ((android.widget.LinearLayout) this.s1().f));
                                            v0_18.add(v5_17);
                                            com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v5_17.d, v3_58, new BZ.n(2));
                                            v5_17.b.setOnCheckedChangeListener(new Lo.s(0, this));
                                            ((android.widget.LinearLayout) this.s1().f).addView(v5_17.c);
                                        }
                                        Throwable v0_19 = this.r1();
                                        v0_19.h0 = this.u1();
                                        v0_19.n0();
                                        ((android.widget.LinearLayout) this.s1().g).removeAllViews();
                                        Throwable v0_26 = ((Iterable) this.q1().getBankConditions()).iterator();
                                        while (v0_26.hasNext()) {
                                            vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_96 = ((String) v0_26.next());
                                            Ro.o v3_53 = android.view.LayoutInflater.from(this.getContext()).inflate(-2079850460, ((android.widget.LinearLayout) this.s1().g), 0);
                                            int v6_5 = ((android.widget.TextView) l5.s.a(-2079915812, v3_53));
                                            if (v6_5 == 0) {
                                                throw new NullPointerException("Missing required view with ID: ".concat(v3_53.getResources().getResourceName(-2079915812)));
                                            } else {
                                                Ro.o v3_56 = ((android.widget.LinearLayout) v3_53);
                                                com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v6_5, v1_96, new BZ.n(1, new Lo.m(this, 13)));
                                                ((android.widget.LinearLayout) this.s1().g).addView(v3_56);
                                            }
                                        }
                                        int v11_8 = this.r1();
                                        v11_8.E.f(v21_1.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankDetailFormFragment, "changeSubmitButtonState", "changeSubmitButtonState(Z)V", 0, 4)));
                                        Ro.m v2_1 = v21_1;
                                        v11_8.G.f(v2_1.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, v2_1, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankDetailFormFragment, "onValidateComplete", "onValidateComplete(Lkotlin/Pair;)V", 0, 5)));
                                        v11_8.I.f(v2_1.getViewLifecycleOwner(), new Fo.i(3, new Lo.m(v2_1, 1)));
                                        v11_8.K.f(v21_1.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, v2_1, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankDetailFormFragment, "handleLoadingState", "handleLoadingState(Z)V", 0, 6)));
                                        v11_8.M.f(v21_1.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, v2_1, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankDetailFormFragment, "handleSubmitState", "handleSubmitState(Lvn/com/vng/zalopay/bankbinding/presentation/model/BankSubmitStateModel;)V", 0, 7)));
                                        v11_8.k.f(v21_1.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, v2_1, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankDetailFormFragment, "handleChangeIdType", "handleChangeIdType(Lvn/com/vng/zalopay/bankbinding/domain/entity/IdentityOptionEntity;)V", 0, 8)));
                                        int v14_2 = v2_1;
                                        v11_8.m.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, v2_1, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankDetailFormFragment, "handleUpdateKycValue", "handleUpdateKycValue(Lvn/com/vng/zalopay/bankbinding/presentation/model/ekyc/BankEKycInputModel;)V", 0, 9)));
                                        v11_8.O.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 4)));
                                        v11_8.Q.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 5)));
                                        v11_8.S.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 6)));
                                        v11_8.U.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 7)));
                                        v11_8.W.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 8)));
                                        v11_8.Y.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 9)));
                                        v11_8.a0.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 10)));
                                        v11_8.c0.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 11)));
                                        v11_8.p.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 12)));
                                        v11_8.u.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 2)));
                                        v11_8.e0.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 3)));
                                        ((Ro.t) v14_2.G.getValue()).f.f(v14_2.getViewLifecycleOwner(), new cV.y(2, new Lo.m(v14_2, 20)));
                                        v14_2.s1().b.setOnClickListener(new com.google.android.material.snackbar.q(new Lo.m(v14_2, 0)));
                                        ((androidx.compose.ui.platform.ComposeView) v14_2.s1().j).setContent(new y0.b(new Fo.b(5, v14_2), 1, 1301099508));
                                        Throwable v0_69 = ((Iterable) v14_2.q1().getBankBindingFields()).iterator();
                                        while (v0_69.hasNext()) {
                                            vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel v1_93 = v0_69.next();
                                            if (((uo.c) v1_93).getId() == 6) {
                                            }
                                            int v5_8;
                                            if (!(v1_93 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel)) {
                                                v5_8 = 0;
                                            } else {
                                                v5_8 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel) v1_93);
                                            }
                                            Ro.o v3_50 = v14_2.r1();
                                            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v3_50), 0, 0, new Ro.m(v3_50, v14_2.q1().getBankCode(), v5_8, 0, 4), 3, 0);
                                            return;
                                        }
                                        v1_93 = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p22.getResources().getResourceName(v1_14)));
    }

    public final boolean p1()
    {
        int v0_0 = this.x;
        if (v0_0 != 0) {
            v0_0.dismiss();
        }
        int v0_6 = this.C;
        if (v0_6 == 0) {
            v0_6 = 0;
        }
        int v0_4 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v0_6.a), kotlin.TuplesKt.to("bankNameCYBS", v0_6.e)}));
        UR.a.e("01.2027.001", v0_4);
        java.util.Objects.toString(v0_4);
        return 0;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) this.v.getValue());
    }

    public final Ro.o r1()
    {
        return ((Ro.o) this.z.getValue());
    }

    public final Sn.f s1()
    {
        IllegalStateException v0_0 = this.s;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void t1(int p7, vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument p8)
    {
        if ((p7 == 3) || (p7 == 4)) {
            android.util.SparseArray v0_2 = this.D;
            java.util.Iterator v1_2 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v0_2.get(p7));
            if (v1_2 != null) {
                if ((p8.getMonth().length() != 0) && (p8.getYear().length() != 0)) {
                    v1_2.getEditText().d();
                    int v2_5 = v1_2.getEditText();
                    v2_5.setText(v2_5.getContext().getString(-2079719408, new Object[] {p8.getMonth(), p8.getYear()})));
                    v2_5.i();
                    v1_2.b();
                    java.util.List v8_5 = this.q1().getBankBindingFields();
                    java.util.Iterator v1_0 = v8_5.iterator();
                    int v2_6 = 0;
                    while (v1_0.hasNext()) {
                        if (((uo.c) v1_0.next()).getId() != p7) {
                            v2_6++;
                        }
                        vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView v7_2 = ((uo.c) kotlin.collections.CollectionsKt.getOrNull(v8_5, (v2_6 + 1)));
                        if (v7_2 != null) {
                            vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView v7_5 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v0_2.get(v7_2.getId()));
                            if (v7_5 != null) {
                                v7_5.h();
                                v7_5.requestFocus();
                            }
                        }
                    }
                    v2_6 = -1;
                } else {
                    v1_2.getEditText().g();
                    vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView v7_7 = v1_2.getEditText();
                    v7_7.setText("");
                    v7_7.i();
                    return;
                }
            }
        }
        return;
    }

    public final boolean u1()
    {
        int v0_3 = this.J.iterator();
        while (v0_3.hasNext()) {
            if (!((Sn.x) v0_3.next()).b.isChecked()) {
                int v0_1 = 1;
            }
            return (v0_1 ^ 1);
        }
        v0_1 = 0;
        return (v0_1 ^ 1);
    }

    public final void v1(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p7)
    {
        androidx.fragment.app.FragmentActivity v0_0 = this.D;
        int v1_1 = v0_0.size();
        int v2 = 0;
        while (v2 < v1_1) {
            v0_0.keyAt(v2);
            vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView v3_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v0_0.valueAt(v2));
            v3_1.i();
            vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView v3_2 = v3_1.getEditText();
            v3_2.clearFocus();
            v3_2.setBackground(v3_2.getContext().getDrawable(-2079981528));
            v2++;
        }
        p7.c();
        p7.setEnable(1);
        p7.h();
        p7.getEditText().requestFocus();
        if (p7.getEditText().getBankInfoType() != 2) {
            com.google.android.gms.internal.mlkit_vision_barcode.A0.b(this.getActivity(), p7.getEditText());
        }
        return;
    }

    public final void w1()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument.Companion.getClass();
        androidx.lifecycle.T v0_7 = m9.C.c(this, vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument.access$getUNDEFINED$cp(), "request_issue_date");
        if (v0_7 != null) {
            v0_7.f(this.getViewLifecycleOwner(), new Fo.i(3, new Lo.m(this, 14)));
        }
        androidx.lifecycle.T v0_2 = m9.C.c(this, vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument.access$getUNDEFINED$cp(), "request_expiry_date");
        if (v0_2 != null) {
            v0_2.f(this.getViewLifecycleOwner(), new Fo.i(3, new Lo.m(this, 15)));
        }
        androidx.lifecycle.T v0_6 = m9.C.c(this, Integer.valueOf(0), "select_id_type_request_code");
        if (v0_6 != null) {
            v0_6.f(this.getViewLifecycleOwner(), new Fo.i(3, new Lo.m(this, 16)));
        }
        return;
    }

    public final void x(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2, int p3)
    {
        this.v1(p2);
        java.util.Map v2_1 = this.C;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        java.util.Map v2_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v2_1.a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p3))}));
        UR.a.e("01.2027.018", v2_3);
        java.util.Objects.toString(v2_3);
        return;
    }

    public final void x1(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel p24)
    {
        this.w1();
        UnsupportedOperationException v1_10 = this.D.get(4);
        if (v1_10 != null) {
            UnsupportedOperationException v1_16 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v1_10);
            String v3_0 = this.t;
            if (v3_0 == null) {
                v3_0 = 0;
            }
            String v7_0 = this.getString(-2079719334);
            String v8 = this.getString(-2079719335);
            String v9 = this.getString(-2079719336);
            int v10 = p24.getStartYear();
            int v11 = p24.getEndYear();
            int v12 = p24.getDefaultYear();
            String v14 = v1_16.getEditText().getData();
            String v15 = this.q1().getBankCode();
            String v16 = this.q1().getClientBankCode();
            String v17 = com.google.android.gms.internal.mlkit_vision_barcode.D0.h(this.q1().getCardNumber());
            String v18 = com.google.android.gms.internal.mlkit_vision_barcode.D0.i(4, this.q1().getCardNumber());
            String v19 = this.q1().getIssuerBankName();
            int v20 = this.q1().getFunctionId();
            UnsupportedOperationException v1_13 = this.B;
            if (v1_13 == null) {
                v1_13 = 0;
            }
            String v21 = v1_13.getFlowSessionId();
            UnsupportedOperationException v1_14 = this.I;
            if (v1_14 == null) {
                v1_14 = 0;
            }
            java.io.Serializable v5_1 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument("request_expiry_date", v7_0, v8, v9, v10, v11, v12, 0, v14, v15, v16, v17, v18, v19, v20, v21, v1_14.v);
            v3_0.getClass();
            UnsupportedOperationException v1_18 = new android.os.Bundle();
            if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument)) {
                if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument)) {
                    throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                } else {
                    v1_18.putSerializable("bank_date_picker_args", ((java.io.Serializable) v5_1));
                }
            } else {
                v1_18.putParcelable("bank_date_picker_args", v5_1);
            }
            v3_0.k(-2079916025, v1_18, 0);
            return;
        } else {
            UnsupportedOperationException v1_1 = new Object[0];
            ck.b.a("[BankBinding] Cannot show expired date picker", v1_1);
            return;
        }
    }

    public final void y1(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel p23)
    {
        this.w1();
        UnsupportedOperationException v1_10 = this.D.get(3);
        if (v1_10 != null) {
            UnsupportedOperationException v1_16 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v1_10);
            String v2_1 = this.t;
            if (v2_1 == null) {
                v2_1 = 0;
            }
            Class v6_0 = this.getString(-2079719316);
            String v7_0 = this.getString(-2079719317);
            String v8 = this.getString(-2079719318);
            int v9 = p23.getStartYear();
            int v10 = p23.getEndYear();
            int v11 = p23.getDefaultYear();
            String v13 = v1_16.getEditText().getData();
            String v14 = this.q1().getBankCode();
            String v15 = this.q1().getClientBankCode();
            String v16 = com.google.android.gms.internal.mlkit_vision_barcode.D0.h(this.q1().getCardNumber());
            String v17 = com.google.android.gms.internal.mlkit_vision_barcode.D0.i(4, this.q1().getCardNumber());
            String v18 = this.q1().getIssuerBankName();
            int v19 = this.q1().getFunctionId();
            UnsupportedOperationException v1_13 = this.B;
            if (v1_13 == null) {
                v1_13 = 0;
            }
            String v20 = v1_13.getFlowSessionId();
            UnsupportedOperationException v1_14 = this.I;
            if (v1_14 == null) {
                v1_14 = 0;
            }
            java.io.Serializable v4_1 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument("request_issue_date", v6_0, v7_0, v8, v9, v10, v11, 1, v13, v14, v15, v16, v17, v18, v19, v20, v1_14.v);
            v2_1.getClass();
            UnsupportedOperationException v1_18 = new android.os.Bundle();
            if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument)) {
                if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument)) {
                    throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                } else {
                    v1_18.putSerializable("bank_date_picker_args", ((java.io.Serializable) v4_1));
                }
            } else {
                v1_18.putParcelable("bank_date_picker_args", v4_1);
            }
            v2_1.k(-2079916025, v1_18, 0);
            return;
        } else {
            UnsupportedOperationException v1_1 = new Object[0];
            ck.b.a("[BankBinding] Cannot show issue date picker", v1_1);
            return;
        }
    }

    public final void z1(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel p18)
    {
        android.view.ViewGroup v3_0 = 0;
        if (p18.getPage() != 1) {
            Po.n v1_13 = this.C;
            if (v1_13 == null) {
                v1_13 = 0;
            }
            Po.n v1_7 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v1_13.a), kotlin.TuplesKt.to("displayMessage", p18.getMessage()), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p18.getBcErrorCode())), kotlin.TuplesKt.to("bankNameCYBS", v1_13.e)}));
            UR.a.e("01.2027.016", v1_7);
            java.util.Objects.toString(v1_7);
            this.s1().b.setEnabled(0);
            Po.n v1_10 = p18.transformFieldIdFromAction();
            boolean v5_5 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.D.get(v1_10));
            if (v5_5) {
                androidx.fragment.app.FragmentActivity v6_3 = this.getActivity();
                if (v6_3 != null) {
                    com.google.android.gms.internal.mlkit_vision_barcode.S7.b(v6_3);
                    v5_5.setEnable(0);
                    v5_5.i();
                    if (v1_10 == 6) {
                        v5_5.e();
                    }
                    Po.n v1_12 = new Po.n(v6_3, v5_5, p18.getBcErrorCode(), this);
                    this.x = v1_12;
                    android.view.View v4_9 = ((android.widget.LinearLayout) this.s1().e).getRootView();
                    if ((v4_9 instanceof android.view.ViewGroup)) {
                        v3_0 = ((android.view.ViewGroup) v4_9);
                    }
                    if (v3_0 != null) {
                        v1_12.b(v3_0, p18.getMessage());
                    }
                }
                return;
            } else {
                Ao.c.n(this, new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel(p18.getMessage(), 0, p18.getBcErrorCode(), p18.getZpTransId(), this.q1().getIssuerBankName(), this.q1().getFunctionId(), this.q1().getBankCode(), p18.getBmErrorCode(), 2, 0), "bank_number_invalid_result");
                return;
            }
        } else {
            m9.C.g(this, p18, "result_action_show_tooltip");
            Po.n v1_2 = this.t;
            if (v1_2 != null) {
                v3_0 = v1_2;
            }
            v3_0.o();
            return;
        }
    }
}
