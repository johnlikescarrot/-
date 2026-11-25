package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankOtpFragment extends TO.a {
    public String A;
    public Ew.D B;
    public android.os.CountDownTimer C;
    public boolean D;
    public Runnable E;
    public final ng.S F;
    public final ng.S G;
    public final vn.com.vng.zalopay.bankbinding.presentation.broadcastreceiver.BankSmsBroadcastReceiver H;
    public final boolean I;
    public Sn.f s;
    public final a9.f t;
    public final ng.S u;
    public Z2.L v;
    public Eo.a w;
    public To.g x;
    public final ng.w y;
    public ji.u0 z;

    public BankOtpFragment()
    {
        vn.com.vng.zalopay.bankbinding.presentation.broadcastreceiver.BankSmsBroadcastReceiver v1_0 = kotlin.jvm.internal.M.a;
        this.t = new a9.f(v1_0.b(Lo.W), new Lo.F(this, 5));
        this.u = ng.x.b(new Lo.S(this, 0));
        int v0_5 = new Lo.S(this, 1);
        Lo.S v2_4 = ng.x.a(ng.y.c, new Io.M(13, new Lo.F(this, 6)));
        this.y = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.I), new Io.N(v2_4, 26), new Io.N(v2_4, 27), v0_5);
        this.F = ng.x.b(new Ho.P(7));
        this.G = ng.x.b(new Lo.S(this, 2));
        this.H = new vn.com.vng.zalopay.bankbinding.presentation.broadcastreceiver.BankSmsBroadcastReceiver(this.registerForActivityResult(new androidx.fragment.app.f0(6), new Lo.T(this)), new Lo.S(this, 3), new Lo.S(this, 4));
        this.I = 1;
        return;
    }

    public final boolean m1()
    {
        return this.I;
    }

    public final int n1()
    {
        return -2079850483;
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
            this.w = v2_4;
            v2_4.m().e(this);
            return;
        }
    }

    public final void onDestroyView()
    {
        Runnable v0 = this.E;
        if (v0 != null) {
            ((android.os.Handler) this.F.getValue()).removeCallbacks(v0);
        }
        super.onDestroyView();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (this.D) {
            int v0_6 = ((Ro.I) this.y.getValue());
            int v0_1 = v0_6.h;
            v0_6.k.l(new Xs.b(new vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel(v0_1.getZpTransId(), v0_1.getBankCode())));
            this.D = 0;
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        try {
            Throwable v0_0 = this.getActivity();
        } catch (Throwable v0_1) {
            Ao.c.b("Register sms otp error", v0_1);
            return;
        }
        if (v0_0 == null) {
            return;
        } else {
            v0_0.registerReceiver(this.H, new android.content.IntentFilter("com.google.android.gms.auth.api.phone.SMS_RETRIEVED"));
            return;
        }
    }

    public final void onStop()
    {
        try {
            Throwable v0_0 = this.getActivity();
        } catch (Throwable v0_1) {
            Ao.c.b("UnRegister sms otp error", v0_1);
            super.onStop();
            return;
        }
        if (v0_0 == null) {
            super.onStop();
            return;
        } else {
            v0_0.unregisterReceiver(this.H);
            super.onStop();
            return;
        }
    }

    public final void onViewCreated(android.view.View p15, android.os.Bundle p16)
    {
        this = super.onViewCreated(p15, p16);
        E8.x v1_0 = -2079915934;
        com.google.android.gms.common.api.h v6_3 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915934, p15));
        if (v6_3 != null) {
            v1_0 = -2079915909;
            int v7_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView) l5.s.a(-2079915909, p15));
            if (v7_1 != 0) {
                v1_0 = -2079915901;
                com.google.android.gms.common.api.j v8_1 = ((android.widget.TextView) l5.s.a(-2079915901, p15));
                if (v8_1 != null) {
                    v1_0 = -2079915842;
                    kotlin.Pair v9_1 = ((android.widget.TextView) l5.s.a(-2079915842, p15));
                    if (v9_1 != null) {
                        v1_0 = -2079915841;
                        Ro.I v10_3 = ((android.widget.TextView) l5.s.a(-2079915841, p15));
                        if (v10_3 != null) {
                            v1_0 = -2079915811;
                            androidx.lifecycle.T v11_3 = ((android.widget.TextView) l5.s.a(-2079915811, p15));
                            if (v11_3 != null) {
                                v1_0 = -2079915825;
                                cV.y v12_5 = ((android.widget.TextView) l5.s.a(-2079915825, p15));
                                if (v12_5 != null) {
                                    v1_0 = -2079915793;
                                    android.widget.TextView v13_1 = ((android.widget.TextView) l5.s.a(-2079915793, p15));
                                    if (v13_1 != null) {
                                        this.s = new Sn.f(((androidx.constraintlayout.widget.ConstraintLayout) p15), v6_3, v7_1, v8_1, v9_1, v10_3, v11_3, v12_5, v13_1);
                                        E8.q0 v0_40 = this.s1();
                                        C3.a v4_16 = kotlin.TuplesKt.to("bankCode", v0_40.a);
                                        kotlin.TuplesKt.to("zpTransID", v0_40.b);
                                        kotlin.TuplesKt.to("linkingType", Integer.valueOf(v0_40.c));
                                        kotlin.TuplesKt.to("link_source", Integer.valueOf(v0_40.d));
                                        kotlin.TuplesKt.to("flowSessionId", v0_40.e);
                                        E8.q0 v0_44 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {v4_16, kotlin.TuplesKt.to("kycLevel", Integer.valueOf(v0_40.f))}));
                                        UR.a.e("01.2023.000", v0_44);
                                        java.util.Objects.toString(v0_44);
                                        this.v = q9.p0.a(this);
                                        ((android.widget.TextView) this.r1().g).setOnClickListener(new Lo.Q(this, 1));
                                        E8.q0 v0_6 = ((android.widget.TextView) this.r1().j);
                                        E8.x v1_5 = this.q1().getMessage();
                                        if (v1_5 == null) {
                                            v1_5 = this.getString(-2079719357);
                                        }
                                        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(v0_6, v1_5);
                                        if (this.q1().getStep() == 5) {
                                            this.r1().b.setText(this.getString(-2079719384));
                                        }
                                        E8.x v1_15;
                                        this.r1().b.setOnClickListener(new Lo.Q(this, 0));
                                        E8.q0 v0_15 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView) this.r1().d);
                                        v0_15.getAfterTextChange().f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.d(this, 2)));
                                        E8.x v1_14 = this.q1().getCharType();
                                        if (v1_14 == null) {
                                            v1_15 = 1;
                                        } else {
                                            v1_15 = v1_14.intValue();
                                        }
                                        E8.x v1_16;
                                        C3.a v4_4 = this.q1().getMaxLength();
                                        v0_15.h = 1;
                                        if (v1_15 != null) {
                                            v1_16 = 4096;
                                        } else {
                                            v1_16 = 2;
                                        }
                                        E8.x v1_17;
                                        v0_15.setInputType(v1_16);
                                        if (v4_4 == null) {
                                            v1_17 = 12;
                                        } else {
                                            v1_17 = v4_4.intValue();
                                        }
                                        com.google.android.gms.internal.mlkit_vision_barcode.V7.d(v0_15, v1_17);
                                        E8.q0 v0_17 = new Io.k(6, this);
                                        this.E = v0_17;
                                        com.google.android.gms.common.api.j v8_2 = 300;
                                        ((android.os.Handler) this.F.getValue()).postDelayed(v0_17, 300);
                                        Ro.I v10_1 = ((Ro.I) this.y.getValue());
                                        v10_1.j.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.d(this, 1)));
                                        v10_1.l.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankOtpFragment, "handleBankOtpStatusBySource", "handleBankOtpStatusBySource(Lvn/com/vng/zalopay/bankbinding/presentation/model/BankOtpStateModel;)V", 0, 11)));
                                        v10_1.n.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankOtpFragment, "handleLoadingState", "handleLoadingState(Z)V", 0, 12)));
                                        v10_1.p.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.d(this, 5)));
                                        E8.q0 v0_28 = this.C;
                                        if (v0_28 != null) {
                                            v0_28.cancel();
                                        }
                                        E8.x v1_31 = this.q1().getTimeOut();
                                        if (v1_31 != null) {
                                            v8_2 = v1_31.longValue();
                                        }
                                        b9.i v3_16 = new Lo.V(this, java.util.concurrent.TimeUnit.SECONDS.toMillis(v8_2));
                                        this.C = v3_16;
                                        v3_16.start();
                                        E8.q0 v0_32 = this.q1().getOtpDetectorEntity();
                                        if (v0_32 != null) {
                                            C3.a v4_11 = this.requireActivity();
                                            b9.i v3_19 = new b9.i(v4_11, v4_11, y8.c.a, 0, com.google.android.gms.common.api.j.c);
                                            E8.q0 v0_33 = v0_32.getSender();
                                            E8.x v1_32 = E8.y.a();
                                            v1_32.c = new C3.a(v3_19, v0_33);
                                            v1_32.d = new C8.d[] {b9.a.a});
                                            v3_19.doWrite(v1_32.a());
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p15.getResources().getResourceName(v1_0)));
    }

    public final boolean p1()
    {
        String v1_0 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument(0, 0, this.getString(-2079719413), -2079915959, "bank_otp_cancel_confirm_dialog_request", this.getString(-2079719410), this.getString(-2079719414), 3, 0);
        this.u1();
        UnsupportedOperationException v0_1 = this.v;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        v0_1.getClass();
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
        v0_1.k(-2079915995, v3_2, 0);
        return 1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) this.u.getValue());
    }

    public final Sn.f r1()
    {
        IllegalStateException v0_0 = this.s;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final Bo.g s1()
    {
        return ((Bo.g) this.G.getValue());
    }

    public final void t1(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument p7)
    {
        String v0_0 = this.v;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.getClass();
        android.os.Bundle v2_1 = new android.os.Bundle();
        if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument)) {
            if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument)) {
                throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
            } else {
                v2_1.putSerializable("bank_result_args", ((java.io.Serializable) p7));
            }
        } else {
            v2_1.putParcelable("bank_result_args", p7);
        }
        v0_0.k(-2079916009, v2_1, 0);
        return;
    }

    public final void u1()
    {
        androidx.lifecycle.T v0_2 = Integer.valueOf(0);
        androidx.lifecycle.F v1_5 = m9.C.c(this, v0_2, "bank_otp_retry_dialog_request");
        if (v1_5 != null) {
            v1_5.f(this.getViewLifecycleOwner(), new Fo.i(6, new Ko.d(this, 3)));
        }
        androidx.lifecycle.T v0_1 = m9.C.c(this, v0_2, "bank_otp_cancel_confirm_dialog_request");
        if (v0_1 != null) {
            v0_1.f(this.getViewLifecycleOwner(), new Fo.i(6, new Ko.d(this, 4)));
        }
        return;
    }
}
