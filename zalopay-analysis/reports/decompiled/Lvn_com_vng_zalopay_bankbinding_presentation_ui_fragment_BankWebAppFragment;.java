package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankWebAppFragment extends TO.a {
    public final ng.w A;
    public To.b B;
    public final ng.w C;
    public ji.u0 D;
    public ji.u0 E;
    public final ng.S F;
    public final ng.S G;
    public final a9.f s;
    public final ng.S t;
    public final boolean u;
    public Z2.L v;
    public Sn.b w;
    public Eo.a x;
    public Qo.e y;
    public To.h z;

    public BankWebAppFragment()
    {
        int v1_0 = kotlin.jvm.internal.M.a;
        this.s = new a9.f(v1_0.b(Lo.i0), new Lo.F(this, 15));
        this.t = ng.x.b(new Lo.g0(this, 0));
        this.u = 1;
        ng.S v0_6 = new Lo.g0(this, 1);
        Lo.Y v3_1 = ng.y.c;
        ng.w v2_5 = ng.x.a(v3_1, new Io.M(18, new Lo.F(this, 16)));
        this.A = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.P), new Lo.Y(v2_5, 6), new Lo.Y(v2_5, 7), v0_6);
        ng.S v0_9 = new Lo.g0(this, 2);
        ng.w v2_10 = ng.x.a(v3_1, new Io.M(19, new Lo.F(this, 17)));
        this.C = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.s0), new Lo.Y(v2_10, 8), new Lo.Y(v2_10, 9), v0_9);
        this.F = ng.x.b(new Lo.g0(this, 3));
        this.G = ng.x.b(new Lo.g0(this, 4));
        return;
    }

    public final boolean m1()
    {
        return this.u;
    }

    public final int n1()
    {
        return -2079850476;
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
            this.x = v2_4;
            v2_4.m().d(this);
            return;
        }
    }

    public final void onDestroyView()
    {
        vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebView v0_2 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebView) this.r1().d);
        v0_2.removeJavascriptInterface("ZP_BANK_WEB_APP_INTERFACE");
        v0_2.removeAllViews();
        v0_2.clearHistory();
        v0_2.clearCache(1);
        v0_2.destroy();
        super.onDestroyView();
        return;
    }

    public final void onViewCreated(android.view.View p16, android.os.Bundle p17)
    {
        this = super.onViewCreated(p16, p17);
        int v1_17 = this.E;
        vn.com.vng.zalopay.bankbinding.presentation.ui.activity.BankBindingActivity v8_1 = 0;
        if (v1_17 == 0) {
            v1_17 = 0;
        }
        v1_17.getClass();
        v1_17.l(0, uo.p.a);
        int v4_7 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebView) l5.s.a(-2079915774, p16));
        if (v4_7 == 0) {
            throw new NullPointerException("Missing required view with ID: ".concat(p16.getResources().getResourceName(-2079915774)));
        } else {
            int v4_16;
            this.w = new Sn.b(((android.widget.FrameLayout) p16), ((android.widget.FrameLayout) p16), v4_7, 2);
            String[] v0_37 = ((Bo.j) this.F.getValue());
            int v1_31 = v0_37.c;
            int v3_24 = v0_37.a;
            if ((!kotlin.jvm.internal.Intrinsics.areEqual(v3_24, "SCB")) || (v1_31 != 6)) {
                v4_16 = "01.2032.000";
            } else {
                v4_16 = "01.2036.000";
            }
            String[] v0_41 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v3_24), kotlin.TuplesKt.to("bankNameCYBS", v0_37.b), kotlin.TuplesKt.to("linkingType", Integer.valueOf(v1_31))}));
            UR.a.e(v4_16, v0_41);
            java.util.Objects.toString(v0_41);
            this.v = q9.p0.a(this);
            U2.a v9_1 = this.A;
            int v10_3 = ((Ro.P) v9_1.getValue());
            v10_3.l.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankWebAppFragment, "handleLoadingState", "handleLoadingState(Z)V", 0, 15)));
            v10_3.x.f(this.getViewLifecycleOwner(), new Fo.i(8, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankWebAppFragment, "handleWebError", "handleWebError(Lvn/com/vng/zalopay/bankbinding/presentation/model/WebErrorModel;)V", 0, 16)));
            v10_3.p.f(this.getViewLifecycleOwner(), new Fo.i(8, new Ko.f(this, 3)));
            v10_3.n.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankWebAppFragment, "handleTitleLoadingState", "handleTitleLoadingState(Z)V", 0, 17)));
            v10_3.r.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.f(this, 4)));
            v10_3.t.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.f(this, 5)));
            v10_3.v.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankWebAppFragment, "handleSubmitState", "handleSubmitState(Lvn/com/vng/zalopay/bankbinding/presentation/model/BankWebThreeDsStateModel;)V", 0, 18)));
            String[] v0_11 = ((Ro.P) v9_1.getValue());
            int v1_12 = this.y;
            if (v1_12 == 0) {
                v1_12 = 0;
            }
            int v1_13 = v1_12.d;
            v0_11.getClass();
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v0_11), 0, 0, new Fo.f(v1_13, v0_11, 0, 25), 3, 0);
            String[] v0_13 = this.y;
            if (v0_13 == null) {
                v0_13 = 0;
            }
            int v1_16 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebView) this.r1().d);
            int v3_14 = this.q1().getUrl();
            v0_13.getClass();
            v1_16.addJavascriptInterface(new vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebViewInterface(v1_16.a), "ZP_BANK_WEB_APP_INTERFACE");
            v1_16.setWebViewClient(v0_13);
            v1_16.resumeTimers();
            v1_16.loadUrl(v3_14);
            ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebView) this.r1().d).setWebChromeClient(new Lo.h0(this));
            ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebView) this.r1().d).getWebJsEvent().f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.f(this, 1)));
            ((Ro.s0) this.C.getValue()).j.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.f(this, 2)));
            if (kotlin.text.y.equals(this.q1().getBankCode(), "CAKE", 1)) {
                String[] v0_30 = this.getActivity();
                if ((v0_30 instanceof vn.com.vng.zalopay.bankbinding.presentation.ui.activity.BankBindingActivity)) {
                    v8_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.activity.BankBindingActivity) v0_30);
                }
                if ((v8_1 != null) && (!com.google.android.gms.internal.mlkit_vision_barcode.u0.d(v8_1, new String[] {"android.permission.CAMERA"})))) {
                    androidx.core.app.d.e(v8_1, new String[] {"android.permission.CAMERA"}), 11);
                }
            }
            return;
        }
    }

    public final boolean p1()
    {
        UnsupportedOperationException v0_1;
        UnsupportedOperationException v0_4 = this.q1().getCancelConsentDialog();
        if (v0_4 == null) {
            v0_1 = 0;
        } else {
            v0_1 = v0_4.getMessage();
            if ((v0_1 == null) || (kotlin.text.StringsKt.isBlank(v0_1))) {
            }
        }
        String v6_0;
        String vtmp5 = this.getString(-2079719372);
        if (v0_1 != null) {
            v6_0 = v0_1;
        } else {
            v6_0 = vtmp5;
        }
        java.io.Serializable v3_1 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument(0, 0, v6_0, -2079915952, "cancel_confirm_dialog_request_bank_web_app", this.getString(-2079719291), this.getString(-2079719337), 3, 0);
        UnsupportedOperationException v0_7 = m9.C.c(this, Integer.valueOf(0), "cancel_confirm_dialog_request_bank_web_app");
        if (v0_7 != null) {
            v0_7.f(this.getViewLifecycleOwner(), new Fo.i(1, new Ko.f(this, 0)));
        }
        UnsupportedOperationException v0_8 = this.v;
        if (v0_8 == null) {
            v0_8 = 0;
        }
        v0_8.getClass();
        String v2_8 = new android.os.Bundle();
        if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument)) {
            if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument)) {
                throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
            } else {
                v2_8.putSerializable("bank_middle_dialog_args", ((java.io.Serializable) v3_1));
            }
        } else {
            v2_8.putParcelable("bank_middle_dialog_args", v3_1);
        }
        v0_8.k(-2079915995, v2_8, 0);
        return 1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) this.t.getValue());
    }

    public final Sn.b r1()
    {
        IllegalStateException v0_0 = this.w;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void s1()
    {
        m9.C.g(this, new vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingProcessingModel(this.q1().getFunctionId(), this.getString(-2079719392)), "vcb_online_unknown_error");
        int v0_1 = this.v;
        if (v0_1 == 0) {
            v0_1 = 0;
        }
        v0_1.o();
        return;
    }

    public final void t1(uo.l1 p10)
    {
        String v0_0 = this.getContext();
        if (v0_0 != null) {
            String v1_1 = new java.util.HashMap();
            int v2_3 = new java.util.ArrayList();
            v1_1.put(v0_0.getString(2131954591), v0_0.getString(2131954599));
            v1_1.put(v0_0.getString(2131954594), v0_0.getString(2131954601));
            v1_1.put(v0_0.getString(2131954593), v0_0.getString(2131954601));
            v1_1.put(v0_0.getString(2131954588), v0_0.getString(2131954597));
            v1_1.put(v0_0.getString(2131954595), v0_0.getString(2131954598));
            v1_1.put(v0_0.getString(2131954587), v0_0.getString(2131954596));
            v1_1.put(v0_0.getString(2131954590), v0_0.getString(2131954600));
            v1_1.put(v0_0.getString(2131954589), v0_0.getString(2131954600));
            v1_1.put(v0_0.getString(2131954592), v0_0.getString(2131954596));
            v2_3.add(v0_0.getString(2131954590));
            v2_3.add(v0_0.getString(2131954589));
            int v10_3 = p10.a;
            if (!v1_1.containsKey(v10_3)) {
                if (java.nio.charset.StandardCharsets.US_ASCII.newEncoder().canEncode(v10_3)) {
                    v10_3 = v0_0.getString(2131954598);
                }
            } else {
                v10_3 = ((String) v1_1.get(v10_3));
            }
            if (v10_3 == 0) {
                v10_3 = this.getString(-2079719400);
            }
            m9.C.g(this, new vn.com.vng.zalopay.bankbinding.presentation.model.BankWebFailResultModel(this.q1().getBankCode(), this.q1().getZpTransId(), this.q1().getFunctionId(), v10_3), "bank_web_app_fail");
            int v10_2 = this.v;
            if (v10_2 == 0) {
                v10_2 = 0;
            }
            v10_2.o();
            return;
        } else {
            return;
        }
    }
}
