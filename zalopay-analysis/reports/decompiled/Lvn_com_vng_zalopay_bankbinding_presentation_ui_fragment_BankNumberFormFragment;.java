package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankNumberFormFragment extends TO.a implements Po.l {
    public To.f A;
    public final ng.w B;
    public Bo.n C;
    public final ng.w D;
    public Yv.i E;
    public ji.u0 F;
    public Ew.D G;
    public Ju.b H;
    public vn.com.vng.zalopay.feature.argument.BankBindingArgs I;
    public Bx.b J;
    public final ng.S K;
    public To.e L;
    public final ng.w M;
    public final boolean N;
    public boolean O;
    public Sn.g s;
    public final a9.f t;
    public final ng.S u;
    public Z2.L v;
    public Runnable w;
    public final ng.S x;
    public Eo.a y;
    public Po.n z;

    public BankNumberFormFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.t = new a9.f(v1_0.b(Lo.G), new Io.i(this, 28));
        this.u = ng.x.b(new Ho.o(this, 6));
        this.x = ng.x.b(new Ho.P(6));
        int v0_8 = new Ho.o(this, 7);
        Io.N v3_1 = ng.y.c;
        ng.w v2_5 = ng.x.a(v3_1, new Io.M(8, new Lo.F(this, 0)));
        this.B = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.F), new Io.N(v2_5, 16), new Io.N(v2_5, 17), v0_8);
        int v0_11 = new Ho.o(this, 8);
        ng.w v2_10 = ng.x.a(v3_1, new Io.M(9, new Lo.F(this, 1)));
        this.D = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.t), new Io.N(v2_10, 18), new Io.N(v2_10, 19), v0_11);
        this.K = ng.x.b(new Ho.o(this, 9));
        int v0_17 = new Ho.o(this, 10);
        ng.w v2_15 = ng.x.a(v3_1, new Io.M(7, new Io.i(this, 29)));
        this.M = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.q), new Io.N(v2_15, 14), new Io.N(v2_15, 15), v0_17);
        this.N = 1;
        return;
    }

    public final void G0(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p3, int p4)
    {
        java.util.Map v4_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", this.u1().a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p4))}));
        UR.a.e("01.2026.012", v4_3);
        java.util.Objects.toString(v4_3);
        this.v1(p3);
        return;
    }

    public final void f0(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p3, int p4)
    {
        java.util.Map v4_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", this.u1().a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p4))}));
        UR.a.e("01.2026.010", v4_3);
        java.util.Objects.toString(v4_3);
        this.v1(p3);
        return;
    }

    public final boolean m1()
    {
        return this.N;
    }

    public final int n1()
    {
        return -2079850484;
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
            this.y = v2_4;
            v2_4.m().l(this);
            return;
        }
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        android.view.View v1_1 = super.onCreateView(p1, p2, p3);
        this.J = new Bx.b(this);
        return v1_1;
    }

    public final void onDestroy()
    {
        KR.c v0_0 = this.J;
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

    public final void onDestroyView()
    {
        super.onDestroyView();
        this.s = 0;
        Runnable v0_1 = this.w;
        if (v0_1 != null) {
            ((android.os.Handler) this.x.getValue()).removeCallbacks(v0_1);
        }
        return;
    }

    public final void onPause()
    {
        android.view.inputmethod.InputMethodManager v0_0 = this.getActivity();
        if (v0_0 != null) {
            android.os.IBinder v1_3 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g);
            if (!v0_0.isFinishing()) {
                android.view.inputmethod.InputMethodManager v0_2 = ((android.view.inputmethod.InputMethodManager) v0_0.getSystemService("input_method"));
                if ((v0_2 != null) && (v1_3.getWindowToken() != null)) {
                    v0_2.hideSoftInputFromWindow(v1_3.getWindowToken(), 0);
                }
            }
        }
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        UnsupportedOperationException v1_32 = this.u1();
        String v2_22 = v1_32.b;
        if (v2_22 == null) {
            v2_22 = "";
        }
        UnsupportedOperationException v1_36 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v1_32.a), kotlin.TuplesKt.to("ccBankCode", v2_22), kotlin.TuplesKt.to("appVer", "11.17.0"), kotlin.TuplesKt.to("flowSessionId", v1_32.e), kotlin.TuplesKt.to("kycLevel", Integer.valueOf(v1_32.c.v))}));
        UR.a.e("01.2026.000", v1_36);
        java.util.Objects.toString(v1_36);
        UnsupportedOperationException v1_42 = new Io.k(5, this);
        this.w = v1_42;
        String v2_23 = this.x;
        ((android.os.Handler) v2_23.getValue()).postDelayed(v1_42, 300);
        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g).g();
        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g).getOnFocusChange().f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 16)));
        UnsupportedOperationException v1_52 = this.s1();
        String v3_34 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g).getTextChangeStateFlow();
        v1_52.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1_52), 0, 0, new Fo.f(v3_34, v1_52, 0, 21), 3, 0);
        UnsupportedOperationException v1_55 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) m9.C.b(this, "bank_number_invalid_result"));
        if (v1_55 != null) {
            this.q1().h0(new no.b(v1_55.getBcErrorCode(), v1_55.getMessage(), v1_55.getLinkingType(), v1_55.getBankCode(), v1_55.getBmErrorCode(), v1_55.getZpTransId()));
        }
        UnsupportedOperationException v1_2 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) m9.C.b(this, "result_action_show_tooltip"));
        if (v1_2 != null) {
            ((android.os.Handler) v2_23.getValue()).postDelayed(new Lo.C(this, v1_2, 0), 1000);
        }
        UnsupportedOperationException v1_5 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebFailResultModel) m9.C.b(this, "bank_web_app_fail"));
        if (v1_5 != null) {
            this.q1().h0(new no.b(0, v1_5.getMessage(), v1_5.getLinkingType(), v1_5.getBankCode(), 0, v1_5.getZpTransId()));
        }
        UnsupportedOperationException v1_8 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ProcessingModel) m9.C.b(this, "bank_web_processing_result"));
        if (v1_8 != null) {
            this.q1().h0(new no.b(v1_8.getBcErrorCode(), "Li\u00ean k\u1ebft \u0111ang x\u1eed l\u00fd.\nVui l\u00f2ng ki\u1ec3m tra k\u1ebft qu\u1ea3 trong ph\u1ea7n Li\u00ean K\u1ebft Th\u1ebb sau 20 ph\u00fat", v1_8.getLinkingType(), v1_8.getBankCode(), v1_8.getBmErrorCode(), v1_8.getZpTransId()));
        }
        UnsupportedOperationException v1_11 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$GenericErrorModel) m9.C.b(this, "bank_web_error_generic_result"));
        if (v1_11 != null) {
            String v2_5 = this.q1();
            Class v4_7 = v1_11.getMessage();
            if (v4_7 == null) {
                v4_7 = "K\u1ebft n\u1ed1i v\u1edbi ng\u00e2n h\u00e0ng kh\u00f4ng th\u00e0nh c\u00f4ng. Vui l\u00f2ng th\u1eed l\u1ea1i sau.";
            }
            v2_5.h0(new no.b(0, v4_7, v1_11.getLinkingType(), v1_11.getBankCode(), 0, v1_11.getZpTransId()));
        }
        UnsupportedOperationException v1_14 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) m9.C.b(this, "bank_web_error_bottom_popup_result"));
        if (v1_14 != null) {
            this.q1().h0(new no.b(v1_14.getBcErrorCode(), v1_14.getMessage(), v1_14.getLinkingType(), v1_14.getBankCode(), v1_14.getBmErrorCode(), v1_14.getZpTransId()));
        }
        UnsupportedOperationException v1_17 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel) m9.C.b(this, "bank_submit_processing_result"));
        if (v1_17 != null) {
            this.q1().h0(new no.b(v1_17.getBcErrorCode(), "Li\u00ean k\u1ebft \u0111ang x\u1eed l\u00fd.\nVui l\u00f2ng ki\u1ec3m tra k\u1ebft qu\u1ea3 trong ph\u1ea7n Li\u00ean K\u1ebft Th\u1ebb sau 20 ph\u00fat", v1_17.getLinkingType(), v1_17.getBankCode(), v1_17.getBmErrorCode(), v1_17.getZpTransId()));
        }
        UnsupportedOperationException v1_22 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ProcessingModel) m9.C.b(this, "bank_otp_processing_result"));
        if (v1_22 != null) {
            this.q1().h0(new no.b(v1_22.getBcErrorCode(), "Li\u00ean k\u1ebft \u0111ang x\u1eed l\u00fd.\nVui l\u00f2ng ki\u1ec3m tra k\u1ebft qu\u1ea3 trong ph\u1ea7n Li\u00ean K\u1ebft Th\u1ebb sau 20 ph\u00fat", v1_22.getLinkingType(), v1_22.getBankCode(), v1_22.getBmErrorCode(), v1_22.getZpTransId()));
        }
        UnsupportedOperationException v1_26 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ProcessingModel) m9.C.b(this, "bank_rop_result"));
        if (v1_26 != null) {
            this.q1().h0(new no.b(v1_26.getBcErrorCode(), "Li\u00ean k\u1ebft \u0111ang x\u1eed l\u00fd.\nVui l\u00f2ng ki\u1ec3m tra k\u1ebft qu\u1ea3 trong ph\u1ea7n Li\u00ean K\u1ebft Th\u1ebb sau 20 ph\u00fat", v1_26.getLinkingType(), v1_26.getBankCode(), v1_26.getBmErrorCode(), v1_26.getZpTransId()));
        }
        UnsupportedOperationException v1_29 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$WebPopupError) m9.C.b(this, "bank_web_popup_result"));
        if (v1_29 != null) {
            Ao.c.v(this, new vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument(-2079915961, "page_1_request_bank_web_popup_request", v1_29.getUrl(), 0, v1_29.getBcErrorCode(), v1_29.getBankCode(), v1_29.getZpTransId(), v1_29.getLinkingType(), v1_29.getBmErrorCode(), v1_29.getIssuerBankName(), 1, 8, 0));
        }
        UnsupportedOperationException v1_35 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ChangePhoneDialogModel) m9.C.b(this, "result_action_show_change_phone_popup"));
        if (v1_35 != null) {
            this.q1().h0(new no.b(v1_35.getBcErrorCode(), v1_35.getMessage(), v1_35.getLinkingType(), v1_35.getBankCode(), v1_35.getBmErrorCode(), v1_35.getZpTransId()));
        }
        UnsupportedOperationException v1_39 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) m9.C.b(this, "result_action_show_media_bottom_popup_from_page_2"));
        if (v1_39 == null) {
            return;
        } else {
            Bo.c.d(this.u1(), "", v1_39.getBcErrorCode(), v1_39.getIssuerBankName(), 0, 16);
            String v12_1 = this.getString(-2079719295);
            String v13_1 = this.getString(-2079719270);
            String v2_15 = v1_39.getButtonLabel();
            if (v2_15 == null) {
                v2_15 = this.getString(-2079719292);
            }
            java.io.Serializable v11_4 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument(v12_1, v13_1, v2_15, -2079915961, 0, v1_39.getDisplayContents(), v1_39.getBankCode(), v1_39.getBcErrorCode(), v1_39.getNotifyType(), "error", 16, 0);
            UnsupportedOperationException v1_41 = this.v;
            if (v1_41 == null) {
                v1_41 = 0;
            }
            v1_41.getClass();
            String v2_18 = new android.os.Bundle();
            if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument)) {
                if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument)) {
                    throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                } else {
                    v2_18.putSerializable("bank_media_bottom_dialog_args", ((java.io.Serializable) v11_4));
                }
            } else {
                v2_18.putParcelable("bank_media_bottom_dialog_args", v11_4);
            }
            v1_41.k(-2079915996, v2_18, 0);
            return;
        }
    }

    public final void onViewCreated(android.view.View p12, android.os.Bundle p13)
    {
        super.onViewCreated(p12, p13);
        cV.y v13_1 = this.F;
        if (v13_1 == null) {
            v13_1 = 0;
        }
        v13_1.getClass();
        v13_1.l(0, uo.h.a);
        cV.y v13_2 = -2079915971;
        vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankNumberFormFragment v4_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915971, p12));
        if (v4_1 != null) {
            v13_2 = -2079915962;
            androidx.lifecycle.A v5_2 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) l5.s.a(-2079915962, p12));
            if (v5_2 != null) {
                v13_2 = -2079915926;
                int v6_3 = ((android.widget.LinearLayout) l5.s.a(-2079915926, p12));
                if (v6_3 != 0) {
                    v13_2 = -2079915855;
                    int v7_4 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(-2079915855, p12));
                    if (v7_4 != 0) {
                        v13_2 = -2079915832;
                        Lo.x v8_5 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(-2079915832, p12));
                        if (v8_5 != null) {
                            v13_2 = -2079915827;
                            int v9_4 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(-2079915827, p12));
                            if (v9_4 != 0) {
                                v13_2 = -2079915798;
                                int v10_3 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915798, p12));
                                if (v10_3 != 0) {
                                    this.s = new Sn.g(((android.widget.RelativeLayout) p12), v4_1, v5_2, v6_3, v7_4, v8_5, v9_4, v10_3);
                                    this.v = q9.p0.a(this);
                                    int v12_22 = this.s1();
                                    v12_22.getClass();
                                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v12_22), 0, 0, new Ro.A(v12_22, 0, 0), 3, 0);
                                    int v12_23 = this.s1();
                                    v12_23.getClass();
                                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v12_23), 0, 0, new Ro.A(v12_23, 0, 2), 3, 0);
                                    int v12_24 = this.t1();
                                    cV.y v13_31 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) v12_24.g);
                                    vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankNumberFormFragment v4_9 = this.r1().getHint();
                                    androidx.lifecycle.A v5_0 = this.r1().getMaxLength();
                                    androidx.lifecycle.F v1_3 = this.r1().getLabel();
                                    if (kotlin.text.StringsKt.isBlank(v1_3)) {
                                        v1_3 = 0;
                                    }
                                    if (v1_3 == null) {
                                        androidx.lifecycle.F v1_5 = this.r1().getId();
                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_5, "account")) {
                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_5, "card")) {
                                                v1_3 = "S\u1ed1 th\u1ebb/ t\u00e0i kho\u1ea3n ng\u00e2n h\u00e0ng";
                                            } else {
                                                v1_3 = "S\u1ed1 th\u1ebb ng\u00e2n h\u00e0ng";
                                            }
                                        } else {
                                            v1_3 = "S\u1ed1 t\u00e0i kho\u1ea3n";
                                        }
                                    }
                                    int v6_1 = v1_3;
                                    androidx.lifecycle.F v1_8 = this.r1().getPlaceHolder();
                                    if (kotlin.text.StringsKt.isBlank(v1_8)) {
                                        v1_8 = 0;
                                    }
                                    if (v1_8 == null) {
                                        androidx.lifecycle.F v1_10 = this.r1().getId();
                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_10, "account")) {
                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_10, "card")) {
                                                v1_8 = "Nh\u1eadp s\u1ed1 th\u1ebb/ s\u1ed1 t\u00e0i kho\u1ea3n";
                                            } else {
                                                v1_8 = "Nh\u1eadp s\u1ed1 th\u1ebb ng\u00e2n h\u00e0ng";
                                            }
                                        } else {
                                            v1_8 = "Nh\u1eadp s\u1ed1 t\u00e0i kho\u1ea3n";
                                        }
                                    }
                                    v13_31.f(new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel(1, v4_9, v5_0, v6_1, 0, v1_8, 16, 0), 0);
                                    com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v12_24.f, this.r1().getInformation(), new mR.a(this));
                                    v12_24.c.setContent(new y0.b(new Lo.D(this, 0), 1, -576418476));
                                    ((androidx.compose.ui.platform.ComposeView) v12_24.h).setContent(new y0.b(new Lo.D(this, 1), 1, 2085212669));
                                    ((androidx.compose.ui.platform.ComposeView) v12_24.i).setContent(new y0.b(new Lo.D(this, 2), 1, -499103780));
                                    int v12_3 = this.F;
                                    if (v12_3 == 0) {
                                        v12_3 = 0;
                                    }
                                    cV.y v13_10 = new uo.j(this.r1().getBankName());
                                    v12_3.getClass();
                                    v12_3.l(0, v13_10);
                                    this.t1().d.setOnClickListener(new Io.A(5, this));
                                    ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g).getEditText().setOnEditorActionListener(new Lo.q(this, 1));
                                    int v12_10 = this.s1();
                                    v12_10.o.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 3)));
                                    v12_10.m.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 4)));
                                    v12_10.q.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankNumberFormFragment, "handleCheckBankNumberUiState", "handleCheckBankNumberUiState(Lvn/com/vng/zalopay/bankbinding/presentation/model/UiStateModel;)V", 0, 10)));
                                    v12_10.r.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 5)));
                                    v12_10.s.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 6)));
                                    v12_10.u.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 7)));
                                    v12_10.w.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 8)));
                                    v12_10.E.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 9)));
                                    ((Ro.t) this.D.getValue()).f.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.b(this, 10)));
                                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.j(this.getViewLifecycleOwner()), 0, 0, new Lo.x(this, 0, 1), 3, 0);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p12.getResources().getResourceName(v13_2)));
    }

    public final void onViewStateRestored(android.os.Bundle p5)
    {
        super.onViewStateRestored(p5);
        if (p5 == null) {
            vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity v5_2 = this.r1().getDialogEntity();
            if (v5_2 != null) {
                ((android.os.Handler) this.x.getValue()).postDelayed(new Lo.C(this, v5_2, 2), 1000);
            }
        }
        return;
    }

    public final boolean p1()
    {
        int v0_0 = this.z;
        if (v0_0 != 0) {
            v0_0.dismiss();
        }
        if (!this.O) {
            int v0_7 = this.I;
            if (v0_7 == 0) {
                v0_7 = 0;
            }
            if (!v0_7.getHideToro()) {
                Ao.c.u(this, this.r1().getBankCode(), "back");
                return 1;
            }
        }
        this.u1().a();
        return 0;
    }

    public final Ro.q q1()
    {
        return ((Ro.q) this.M.getValue());
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument r1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) this.u.getValue());
    }

    public final Ro.F s1()
    {
        return ((Ro.F) this.B.getValue());
    }

    public final Sn.g t1()
    {
        IllegalStateException v0_0 = this.s;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final Bo.c u1()
    {
        return ((Bo.c) this.K.getValue());
    }

    public final void v1(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2)
    {
        p2.setEnable(1);
        p2.h();
        com.google.android.gms.internal.mlkit_vision_barcode.A0.b(this.getActivity(), p2.getEditText());
        return;
    }

    public final void w1()
    {
        androidx.lifecycle.T v0_1 = Integer.valueOf(0);
        androidx.lifecycle.F v1_8 = m9.C.c(this, v0_1, "page_1_request_middle_dialog_result");
        if (v1_8 != null) {
            v1_8.f(this.getViewLifecycleOwner(), new Fo.i(5, new Ko.b(this, 11)));
        }
        androidx.lifecycle.F v1_1 = m9.C.c(this, v0_1, "page_1_request_exit_dialog_result");
        if (v1_1 != null) {
            v1_1.f(this.getViewLifecycleOwner(), new Fo.i(5, new Ko.b(this, 12)));
        }
        androidx.lifecycle.F v1_4 = m9.C.c(this, v0_1, "request_card_guide_dialog_result");
        if (v1_4 != null) {
            v1_4.f(this.getViewLifecycleOwner(), new Fo.i(5, new Ko.b(this, 13)));
        }
        androidx.lifecycle.T v0_2 = m9.C.c(this, v0_1, "request_auto_card_guide_dialog_result");
        if (v0_2 != null) {
            v0_2.f(this.getViewLifecycleOwner(), new Fo.i(5, new Ko.b(this, 14)));
        }
        return;
    }

    public final void x(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p3, int p4)
    {
        java.util.Map v4_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", this.u1().a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p4))}));
        UR.a.e("01.2026.011", v4_3);
        java.util.Objects.toString(v4_3);
        this.v1(p3);
        return;
    }

    public final void x1(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel p8)
    {
        int v0_0 = this.getActivity();
        if (v0_0 != 0) {
            int v0_4;
            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g).setEnable(0);
            ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g).i();
            com.google.android.gms.internal.mlkit_vision_barcode.A0.d(v0_0);
            Po.n v1_4 = new Po.n(v0_0, ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) this.t1().g), p8.getBcErrorCode(), this);
            this.z = v1_4;
            int v0_3 = this.t1().e.getRootView();
            if (!(v0_3 instanceof android.view.ViewGroup)) {
                v0_4 = 0;
            } else {
                v0_4 = ((android.view.ViewGroup) v0_3);
            }
            if (v0_4 != 0) {
                java.util.Map v2_8 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", this.u1().a), kotlin.TuplesKt.to("displayMessage", p8.getMessage()), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p8.getBcErrorCode())), kotlin.TuplesKt.to("bankNameCYBS", p8.getIssuerBankName())}));
                UR.a.e("01.2026.009", v2_8);
                java.util.Objects.toString(v2_8);
                v1_4.b(v0_4, p8.getMessage());
            }
        }
        return;
    }
}
