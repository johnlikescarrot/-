package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankRopFragment extends TO.a implements Po.l {
    public ji.u0 A;
    public Po.n B;
    public Bo.h C;
    public ji.u0 D;
    public Bo.n E;
    public final ng.w F;
    public Yv.i G;
    public final boolean H;
    public Sn.h s;
    public final a9.f t;
    public final ng.S u;
    public Eo.a v;
    public Z2.L w;
    public To.h x;
    public final ng.w y;
    public aP.a z;

    public BankRopFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.t = new a9.f(v1_0.b(Lo.Z), new Lo.F(this, 7));
        this.u = ng.x.b(new Lo.X(this, 0));
        int v0_5 = new Lo.X(this, 1);
        Lo.Y v3_1 = ng.y.c;
        ng.w v2_4 = ng.x.a(v3_1, new Io.M(14, new Lo.F(this, 8)));
        this.y = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.L), new Io.N(v2_4, 28), new Io.N(v2_4, 29), v0_5);
        int v0_8 = new Lo.X(this, 2);
        ng.w v2_10 = ng.x.a(v3_1, new Io.M(15, new Lo.F(this, 9)));
        this.F = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.t), new Lo.Y(v2_10, 0), new Lo.Y(v2_10, 1), v0_8);
        this.H = 1;
        return;
    }

    public final void G0(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2, int p3)
    {
        this.t1(p2);
        java.util.Map v2_1 = this.C;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        Bo.h.a("01.2021.017", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v2_1.a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p3))})));
        return;
    }

    public final void f0(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2, int p3)
    {
        this.t1(p2);
        java.util.Map v2_1 = this.C;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        Bo.h.a("01.2021.010", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v2_1.a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p3))})));
        return;
    }

    public final boolean m1()
    {
        return this.H;
    }

    public final int n1()
    {
        return -2079850481;
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
            v2_4.m().f(this);
            return;
        }
    }

    public final void onViewCreated(android.view.View p20, android.os.Bundle p21)
    {
        super.onViewCreated(p20, p21);
        cV.y v1_36 = this.D;
        aP.a v7_3 = 0;
        if (v1_36 == null) {
            v1_36 = 0;
        }
        v1_36.getClass();
        v1_36.l(0, uo.e.a);
        cV.y v1_0 = -2079915934;
        int v10_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915934, p20));
        if (v10_1 != 0) {
            v1_0 = -2079915930;
            Ho.y0 v11_1 = ((android.widget.CheckBox) l5.s.a(-2079915930, p20));
            if (v11_1 != null) {
                v1_0 = -2079915919;
                if (((vn.com.vng.zalopay.bankbinding.presentation.ui.view.InputFieldContainerView) l5.s.a(-2079915919, p20)) != null) {
                    v1_0 = -2079915911;
                    int v12_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) l5.s.a(-2079915911, p20));
                    if (v12_1 != 0) {
                        v1_0 = -2079915878;
                        int v13_1 = ((android.widget.LinearLayout) l5.s.a(-2079915878, p20));
                        if (v13_1 != 0) {
                            v1_0 = -2079915856;
                            if (((androidx.appcompat.widget.LinearLayoutCompat) l5.s.a(-2079915856, p20)) != null) {
                                v1_0 = -2079915850;
                                if (((android.widget.ScrollView) l5.s.a(-2079915850, p20)) != null) {
                                    v1_0 = -2079915820;
                                    androidx.appcompat.widget.AppCompatTextView v15_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915820, p20));
                                    if (v15_1 != null) {
                                        v1_0 = -2079915800;
                                        androidx.appcompat.widget.AppCompatTextView v16_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915800, p20));
                                        if (v16_1 != null) {
                                            v1_0 = -2079915798;
                                            if (((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915798, p20)) != null) {
                                                v1_0 = -2079915790;
                                                vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView v17_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView) l5.s.a(-2079915790, p20));
                                                if (v17_1 != null) {
                                                    v1_0 = -2079915789;
                                                    androidx.appcompat.widget.AppCompatTextView v18_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915789, p20));
                                                    if (v18_1 != null) {
                                                        this.s = new Sn.h(((android.widget.RelativeLayout) p20), v10_1, v11_1, v12_1, v13_1, ((android.widget.RelativeLayout) p20), v15_1, v16_1, v17_1, v18_1);
                                                        cV.y v1_62 = this.q1().getBankCode();
                                                        this.C = new Bo.h(v1_62, this.q1().getFunctionId());
                                                        Bo.h.a("01.2021.000", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("bankCode", v1_62)));
                                                        this.w = q9.p0.a(this);
                                                        this.s1().i.setupPhoneNumber(this.q1().getFunctionId());
                                                        androidx.lifecycle.T v0_10 = this.q1().getBankNumber().length();
                                                        cV.y v1_5 = this.q1().getBankNumber();
                                                        if (v1_5.length() != 0) {
                                                            cV.y v1_6 = new kotlin.text.Regex("\\D").replace(v1_5, "");
                                                            if (v1_6.length() > v0_10) {
                                                                v1_6 = v1_6.substring(0, v0_10);
                                                            }
                                                            v1_5 = new kotlin.text.Regex("(\\d{4})(?=\\d)").replace(v1_6, "$1 ");
                                                        }
                                                        androidx.lifecycle.T v0_14 = this.q1().getFunctionId();
                                                        if ((v0_14 != 2) && (v0_14 != 5)) {
                                                            this.s1().j.setText(this.getString(-2079719418));
                                                            com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.s1().g, this.getString(-2079719388, new Object[] {v1_5})));
                                                            com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.s1().h, this.getString(-2079719386, new Object[] {this.q1().getBankHolderName().toUpperCase(java.util.Locale.ROOT)})));
                                                        } else {
                                                            this.s1().j.setText(this.getString(-2079719419));
                                                            com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.s1().g, this.getString(-2079719389, new Object[] {v1_5})));
                                                            com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.s1().h, this.getString(-2079719387, new Object[] {this.q1().getBankHolderName().toUpperCase(java.util.Locale.ROOT)})));
                                                        }
                                                        com.google.android.gms.internal.mlkit_vision_barcode.V7.b(this.s1().c, this.getString(-2079719383), new BZ.n(2, new Ko.e(this, 1)));
                                                        androidx.lifecycle.T v0_32 = ((Iterable) this.q1().getFields()).iterator();
                                                        while (v0_32.hasNext()) {
                                                            aP.a v14_1;
                                                            cV.y v1_50 = ((uo.c) v0_32.next());
                                                            if (!(v1_50 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel)) {
                                                                v14_1 = v7_3;
                                                            } else {
                                                                cV.y v1_51 = ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel) v1_50);
                                                                this.s1().d.f(v1_51, this.q1().getFunctionId());
                                                                this.s1().d.getButtonSelectType().setOnClickListener(new Io.L(this, v1_51, 5));
                                                                this.s1().d.getAfterTextChangeLiveData().f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.e(this, 4)));
                                                                this.r1().h0(this.q1().getInitKycValueModel().getIdValue());
                                                                Ro.L v5_7 = this.r1();
                                                                vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel v6_1 = this.q1().getInitKycValueModel();
                                                                int v4_24 = this.q1().getFields();
                                                                v5_7.getClass();
                                                                v14_1 = v7_3;
                                                                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v5_7), 0, 0, new Ho.y0(v4_24, v5_7, v6_1, v7_3, 2), 3, 0);
                                                            }
                                                            v7_3 = v14_1;
                                                        }
                                                        aP.a v14_0 = v7_3;
                                                        this.s1().c.setOnCheckedChangeListener(new Lo.s(1, this));
                                                        this.s1().b.setOnClickListener(new Io.A(6, this));
                                                        U2.a v8_0 = this.r1();
                                                        v8_0.z.f(this.getViewLifecycleOwner(), new Fo.i(7, new Ko.e(this, 6)));
                                                        v8_0.k.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.e(this, 7)));
                                                        v8_0.m.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.e(this, 8)));
                                                        v8_0.B.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.e(this, 9)));
                                                        v8_0.D.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankRopFragment, "handleValidationState", "handleValidationState(Lvn/com/vng/zalopay/bankbinding/domain/entity/BankValidationStateEntity;)V", 0, 13)));
                                                        v8_0.F.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.e(this, 10)));
                                                        v8_0.H.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankRopFragment, "handleRopState", "handleRopState(Lvn/com/vng/zalopay/bankbinding/presentation/model/BankRopStateModel;)V", 0, 14)));
                                                        aP.a v7_2 = this.z;
                                                        if (v7_2 == null) {
                                                            v7_2 = v14_0;
                                                        }
                                                        v7_2.d.f(this.getViewLifecycleOwner(), new Fo.i(7, new Ko.e(this, 5)));
                                                        ((Ro.t) this.F.getValue()).f.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.e(this, 11)));
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p20.getResources().getResourceName(v1_0)));
    }

    public final boolean p1()
    {
        UnsupportedOperationException v0_0 = this.B;
        if (v0_0 != null) {
            v0_0.dismiss();
        }
        String v1_0 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument(0, 0, this.getString(-2079719413), -2079915957, "bank_rop_cancel_confirm_request", this.getString(-2079719410), this.getString(-2079719414), 3, 0);
        this.u1();
        UnsupportedOperationException v0_2 = this.w;
        if (v0_2 == null) {
            v0_2 = 0;
        }
        v0_2.getClass();
        android.os.Bundle v3_2 = new android.os.Bundle();
        if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument)) {
            if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument)) {
                throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
            } else {
                v3_2.putSerializable("bank_middle_dialog_args", ((java.io.Serializable) v1_0));
            }
        } else {
            v3_2.putParcelable("bank_middle_dialog_args", v1_0);
        }
        v0_2.k(-2079915995, v3_2, 0);
        return 1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) this.u.getValue());
    }

    public final Ro.L r1()
    {
        return ((Ro.L) this.y.getValue());
    }

    public final Sn.h s1()
    {
        IllegalStateException v0_0 = this.s;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void t1(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2)
    {
        p2.setEnable(1);
        this.s1().d.c();
        com.google.android.gms.internal.mlkit_vision_barcode.A0.b(this.getActivity(), p2.getEditText());
        return;
    }

    public final void u1()
    {
        androidx.lifecycle.T v0_2 = Integer.valueOf(0);
        androidx.lifecycle.F v1_5 = m9.C.c(this, v0_2, "bank_rop_id_type_selection_request");
        if (v1_5 != null) {
            v1_5.f(this.getViewLifecycleOwner(), new Fo.i(7, new Ko.e(this, 2)));
        }
        androidx.lifecycle.T v0_1 = m9.C.c(this, v0_2, "bank_rop_cancel_confirm_request");
        if (v0_1 != null) {
            v0_1.f(this.getViewLifecycleOwner(), new Fo.i(7, new Ko.e(this, 3)));
        }
        return;
    }

    public final void x(vn.com.vng.zalopay.bankbinding.presentation.ui.view.EditTextCompatView p2, int p3)
    {
        this.t1(p2);
        java.util.Map v2_1 = this.C;
        if (v2_1 == null) {
            v2_1 = 0;
        }
        Bo.h.a("01.2021.016", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v2_1.a), kotlin.TuplesKt.to("BCErrorCode", Integer.valueOf(p3))})));
        return;
    }
}
