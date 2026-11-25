package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankOAOSelectionFragment extends Lo.j0 {
    public com.airbnb.lottie.network.g A;
    public final ji.u0 B;
    public final ji.u0 C;
    public final ng.w D;
    public To.a u;
    public final ng.w v;
    public To.b w;
    public final ng.w x;
    public ji.u0 y;
    public Bo.n z;

    public BankOAOSelectionFragment()
    {
        ng.w v0_4 = new Ho.s(this, 1);
        kotlin.reflect.d v2_3 = ng.y.c;
        ng.w v1_0 = ng.x.a(v2_3, new Io.M(11, new Lo.F(this, 3)));
        Io.N v3_1 = kotlin.jvm.internal.M.a;
        this.v = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_1.b(Ro.g), new Io.N(v1_0, 22), new Io.N(v1_0, 23), v0_4);
        ng.w v0_3 = new Ho.s(this, 2);
        ng.w v1_5 = ng.x.a(v2_3, new Io.M(12, new Lo.F(this, 4)));
        this.x = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_1.b(Ro.b0), new Io.N(v1_5, 24), new Io.N(v1_5, 25), v0_3);
        ng.w v0_6 = DT.c.a;
        this.B = ji.j0.c(v0_6);
        this.C = ji.j0.c(v0_6);
        ng.w v0_9 = new Ho.s(this, 3);
        ng.w v1_12 = ng.x.a(v2_3, new Io.M(10, new Lo.F(this, 2)));
        this.D = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_1.b(FT.b), new Io.N(v1_12, 20), new Io.N(v1_12, 21), v0_9);
        return;
    }

    public final void m1(int p9, q0.s p10)
    {
        q0.Y0 v10_7;
        q0.v v5 = p10.x(-1601343803);
        if (!v5.I(this)) {
            v10_7 = 2;
        } else {
            v10_7 = 4;
        }
        if ((((v10_7 | p9) & 3) == 2) && (v5.c())) {
            v5.m();
        } else {
            G4.a.a(0, 0, 0, y0.c.c(718585713, new Lo.I(this, 1), v5), v5, 3072, 7);
        }
        q0.Y0 v10_5 = v5.Y();
        if (v10_5 != null) {
            v10_5.d = new Lo.I(this, p9, 2);
        }
        return;
    }

    public final void o1(int p29, q0.s p30)
    {
        String v2_0;
        q0.v v9 = p30.x(-202545005);
        if (!v9.I(this)) {
            v2_0 = 2;
        } else {
            v2_0 = 4;
        }
        String v2_1 = (v2_0 | p29);
        if (((v2_1 & 3) == 2) && (v9.c())) {
            v9.m();
        } else {
            q0.C0 v13 = q0.d.q(this.p1().h, v9, 0);
            IO.a v14_7 = this.v;
            int v15_5 = m9.A3.d(((Ro.g) v14_7.getValue()).g, v9);
            uo.F0 v3_46 = Al.o.c(((xo.c) v13.getValue()).e, v9);
            Bo.n v4_0 = v9.I(this);
            String v5_14 = v9.G();
            int v6_19 = q0.r.a;
            if ((v4_0 != null) || (v5_14 == v6_19)) {
                v5_14 = new Ho.s(this, 4);
                v9.B(v5_14);
            }
            uo.F0 v3_71;
            q0.D0 v11_0 = v6_19;
            Al.o.a(v3_46, ((kotlin.jvm.functions.Function0) v5_14), androidx.compose.foundation.layout.P0.e(D0.p.a, 1065353216), 0, 0, y0.c.c(773826718, new Ho.S(v13, this, 5), v9), v9, 196992);
            uo.F0 v3_70 = ((Xs.b) v15_5.getValue());
            Bo.n v4_1 = 0;
            if (v3_70 == null) {
                v3_71 = 0;
            } else {
                v3_71 = ((uo.g1) v3_70.a());
            }
            if (v3_71 != null) {
                v9.q(416304157);
                if (!(v3_71 instanceof uo.f1)) {
                    if (!(v3_71 instanceof uo.e1)) {
                        if (!(v3_71 instanceof uo.d1)) {
                            v9.q(191069469);
                            v9.W(0);
                            throw new kotlin.NoWhenBranchMatchedException();
                        } else {
                            v9.q(1628478916);
                            v9.W(0);
                            uo.F0 v3_74 = ((uo.d1) v3_71).a;
                            String v5_25 = ((vn.com.vng.zalopay.bankbinding.presentation.model.a) v3_74);
                            if (!(v5_25 instanceof uo.m0)) {
                                if (!(v5_25 instanceof uo.n0)) {
                                    if (!(v5_25 instanceof uo.l0)) {
                                        if (!(v5_25 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition)) {
                                            throw new kotlin.NoWhenBranchMatchedException();
                                        } else {
                                            uo.F0 v3_0 = ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) v3_74);
                                            IO.a v14_1 = new vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument(v3_0.getSuggestBank(), v3_0.getNextAction(), v3_0.getBannerUrl(), v3_0.getConditionTitle(), v3_0.getBenefits(), v3_0.getCtaDescription(), v3_0.getCtaButtonText(), v3_0.getKycSourceParam(), v3_0.getBankLogoUrl(), v3_0.getConditions(), "oaohubchoose", v3_0.getOtpDetectorEntity(), v3_0.getExtraInfo());
                                            uo.F0 v3_2 = this.r;
                                            if (v3_2 == null) {
                                                v3_2 = 0;
                                            }
                                            v3_2.getClass();
                                            String v5_2 = new android.os.Bundle();
                                            if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument)) {
                                                if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument)) {
                                                    throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                                                } else {
                                                    v5_2.putSerializable("confirm_condition_args", ((java.io.Serializable) v14_1));
                                                }
                                            } else {
                                                v5_2.putParcelable("confirm_condition_args", v14_1);
                                            }
                                            v3_2.k(-2079915987, v5_2, 0);
                                        }
                                    } else {
                                        uo.F0 v3_4 = ((uo.l0) v3_74);
                                        v3_4.getClass();
                                        IO.a v14_4 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument(v3_4.a, this.getString(-2079719305), v3_4.b, 0, 0, 0, 1, 6, 56, 0);
                                        uo.F0 v3_6 = this.y;
                                        if (v3_6 == null) {
                                            v3_6 = 0;
                                        }
                                        String v5_4 = Ao.c.I(v14_4);
                                        v3_6.getClass();
                                        v3_6.l(0, v5_4);
                                        uo.F0 v3_7 = this.r;
                                        if (v3_7 == null) {
                                            v3_7 = 0;
                                        }
                                        v3_7.getClass();
                                        String v5_6 = new android.os.Bundle();
                                        if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument)) {
                                            if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument)) {
                                                throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                                            } else {
                                                v5_6.putSerializable("bank_result_args", ((java.io.Serializable) v14_4));
                                            }
                                        } else {
                                            v5_6.putParcelable("bank_result_args", v14_4);
                                        }
                                        v3_7.k(-2079916009, v5_6, 0);
                                    }
                                } else {
                                    uo.F0 v3_10 = ((uo.n0) v3_74);
                                    ((Ro.g) v14_7.getValue()).h0(v3_10.a, v3_10.b);
                                }
                            } else {
                                String v5_9 = this.r;
                                if (v5_9 == null) {
                                    v5_9 = 0;
                                }
                                uo.F0 v3_13 = ((uo.m0) v3_74).a();
                                v5_9.getClass();
                                int v6_8 = new android.os.Bundle();
                                if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument)) {
                                    if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument)) {
                                        throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                                    } else {
                                        v6_8.putSerializable("waiting_ekyc_approval_args", ((java.io.Serializable) v3_13));
                                    }
                                } else {
                                    v6_8.putParcelable("waiting_ekyc_approval_args", v3_13);
                                }
                                v5_9.k(-2079916012, v6_8, 0);
                            }
                        }
                    } else {
                        v9.q(1628260490);
                        uo.F0 v3_17 = ((uo.e1) v3_71).a;
                        String v5_11 = v9.I(this);
                        int v6_9 = v9.G();
                        if ((v5_11 != null) || (v6_9 == v11_0)) {
                            v6_9 = new Ho.s(this, 5);
                            v9.B(v6_9);
                        }
                        Ho.Q.d(v3_17, ((kotlin.jvm.functions.Function0) v6_9), v9, 0);
                        v9.W(0);
                    }
                } else {
                    v9.q(191070019);
                    com.google.android.gms.internal.mlkit_vision_barcode.a0.a(0, v9);
                    v9.W(0);
                }
            } else {
                v9.q(416304156);
            }
            v9.W(0);
            if (!((xo.c) v13.getValue()).a.a) {
                v9.q(401792623);
            } else {
                v9.q(417434820);
                uo.F0 v3_27 = this.z;
                if (v3_27 == null) {
                    v3_27 = 0;
                }
                ((xo.c) v13.getValue()).a.getClass();
                v3_27.i(new uo.J0("", ((xo.c) v13.getValue()).a.d));
                uo.F0 v3_33 = ((xo.c) v13.getValue()).a.c;
                String v7_4 = (v9.I(this) | v9.p(v13));
                String v8_12 = v9.G();
                if ((v7_4 != null) || (v8_12 == v11_0)) {
                    v8_12 = new Lo.H(this, v13, 2);
                    v9.B(v8_12);
                }
                Ho.Q.d(v3_33, ((kotlin.jvm.functions.Function0) v8_12), v9, 0);
            }
            v9.W(0);
            if (!((xo.c) v13.getValue()).b.c) {
                v9.q(401792623);
            } else {
                v9.q(418159383);
                uo.F0 v3_40 = this.z;
                if (v3_40 == null) {
                    v3_40 = 0;
                }
                v3_40.i(new uo.J0(((xo.c) v13.getValue()).b.d, 2));
                uo.F0 v3_44 = ((xo.c) v13.getValue()).b;
                String v7_11 = (v9.I(this) | v9.p(v13));
                String v8_18 = v9.G();
                if ((v7_11 != null) || (v8_18 == v11_0)) {
                    v8_18 = new Lo.H(this, v13, 0);
                    v9.B(v8_18);
                }
                String v8_20 = ((kotlin.jvm.functions.Function0) v8_18);
                String v7_14 = (v9.I(this) | v9.p(v13));
                kotlin.jvm.functions.Function0 v10_6 = v9.G();
                if ((v7_14 != null) || (v10_6 == v11_0)) {
                    v10_6 = new Lo.H(this, v13, 1);
                    v9.B(v10_6);
                }
                Ho.Q.e(v3_44, v8_20, ((kotlin.jvm.functions.Function0) v10_6), v9, 0);
            }
            v9.W(0);
            if (!((xo.c) v13.getValue()).c.b) {
                v9.q(401792623);
            } else {
                v9.q(419649181);
                uo.F0 v3_52 = v9.G();
                if (v3_52 == v11_0) {
                    v3_52 = q0.d.G(Boolean.TRUE, q0.D0.e);
                    v9.B(v3_52);
                }
                uo.F0 v3_54 = ((q0.C0) v3_52);
                if (((Boolean) v3_54.getValue()).booleanValue()) {
                    String v8_22;
                    v3_54.setValue(Boolean.FALSE);
                    uo.F0 v3_58 = ((xo.c) v13.getValue()).c.a;
                    String v8_21 = v3_58.a;
                    if (v8_21 == null) {
                        v8_22 = 0;
                    } else {
                        v8_22 = v8_21.a;
                    }
                    int v19_2;
                    if (v8_22 != null) {
                        v19_2 = v8_22;
                    } else {
                        v19_2 = "";
                    }
                    IO.a v14_9 = new IO.a(v3_58.g, v3_58.f, v3_58.e, v3_58.j, v19_2, v3_58.h, v3_58.i);
                    uo.F0 v3_60 = this.z;
                    if (v3_60 != null) {
                        v4_1 = v3_60;
                    }
                    v4_1.i(new uo.F0(v14_9));
                }
                Ho.Q.c(this, ((xo.c) v13.getValue()).c.a, v9, (v2_1 & 14));
            }
            v9.W(0);
        }
        String v2_10 = v9.Y();
        if (v2_10 != null) {
            v2_10.d = new Lo.I(this, p29, 0);
        }
        return;
    }

    public final void onAttach(android.content.Context p1)
    {
        super.onAttach(p1);
        ro.a v1_1 = this.t;
        if (v1_1 == null) {
            v1_1 = 0;
        }
        v1_1.m().c(this);
        return;
    }

    public final void onCreate(android.os.Bundle p14)
    {
        super.onCreate(p14);
        this.p1().h0(this.requireContext());
        Ro.b0 v14_2 = this.p1();
        v14_2.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v14_2), 0, 0, new Lo.x(v14_2, 0, 8), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new Fo.f(v14_2, this, 0, 10), 3, 0);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        int v0 = this.z;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.i(uo.Q0.a);
        return;
    }

    public final void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        int v2_1 = this.s;
        if (v2_1 == 0) {
            v2_1 = 0;
        }
        v2_1.getClass();
        v2_1.l(0, uo.l.a);
        return;
    }

    public final Ro.b0 p1()
    {
        return ((Ro.b0) this.x.getValue());
    }
}
