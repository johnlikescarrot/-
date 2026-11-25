package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class ToroFragment extends Io.d {
    public static final synthetic int S;
    public Eo.a K;
    public final Bo.p L;
    public To.a M;
    public final ng.w N;
    public final a9.f O;
    public final ng.S P;
    public Runnable Q;
    public final ng.S R;

    public ToroFragment()
    {
        this.L = new Bo.p();
        ng.S v0_12 = new Io.c0(this, 0);
        int v1_0 = ng.x.a(ng.y.c, new Io.M(1, new Io.i(this, 15)));
        Io.i v2_1 = kotlin.jvm.internal.M.a;
        this.N = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v2_1.b(Ro.p0), new Io.N(v1_0, 2), new Io.N(v1_0, 3), v0_12);
        this.O = new a9.f(v2_1.b(Io.m0), new Io.i(this, 14));
        this.P = ng.x.b(new Io.c0(this, 1));
        this.R = ng.x.b(new Ho.P(3));
        return;
    }

    public static final Object E1(vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment p8, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity p9, wg.c p10)
    {
        int v0_1;
        p8.getClass();
        if (!(p10 instanceof Io.f0)) {
            v0_1 = new Io.f0(p8, p10);
        } else {
            v0_1 = ((Io.f0) p10);
            IZ.f v1_3 = ((Io.f0) p10).i;
            if ((v1_3 & -2147483648) == 0) {
            } else {
                ((Io.f0) p10).i = (v1_3 - -2147483648);
            }
        }
        IZ.f v9_1;
        int v0_4;
        IZ.f v1_2;
        Io.A v10_6 = v0_1.g;
        IZ.f v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        android.view.View v2_0 = v0_1.i;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v10_6);
            android.view.View v2_2 = new IZ.f(p8.requireContext());
            v2_2.setParentLayoutResource(-2079850444);
            v2_2.setSecondLayoutResource(-2079850445);
            v2_2.setSpinnerRotation(90);
            v2_2.setExpandableAnimation(IZ.b.c);
            v0_1.d = p9;
            v0_1.e = v2_2;
            v0_1.f = v2_2;
            v0_1.i = 1;
            v10_6 = p8.H1(p9, v0_1);
            if (v10_6 != v1_1) {
                v0_4 = p9;
                v9_1 = v2_2;
                v1_2 = v9_1;
            } else {
                return v1_1;
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                v9_1 = v0_1.f;
                v1_2 = v0_1.e;
                v0_4 = ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity) v0_1.d);
                kotlin.ResultKt.throwOnFailure(v10_6);
            }
        }
        android.view.View v2_5 = ((android.widget.LinearLayout) v9_1.getSecondLayout().findViewById(-2079915906));
        Io.A v10_14 = ((Iterable) ((java.util.List) v10_6)).iterator();
        while (v10_14.hasNext()) {
            v2_5.addView(((android.view.View) v10_14.next()));
        }
        android.view.View v8_10 = ((Ro.p0) p8.N.getValue());
        Io.A v10_15 = v0_4.getTitle();
        if (v10_15 == null) {
            v10_15 = "";
        }
        android.view.View v2_7 = v10_15;
        String v3_6 = v8_10.e.d;
        android.view.View v8_12 = Long.valueOf(v3_6);
        if (v3_6 <= 0) {
            v8_12 = 0;
        }
        if (v8_12 != null) {
            android.view.View v8_13 = kotlin.text.y.replace$default(v2_7, "PHONE_NUMBER", String.valueOf(v8_12.longValue()), 0, 4, 0);
            if (v8_13 != null) {
                v2_7 = v8_13;
            }
        }
        ((android.widget.TextView) v9_1.getParentLayout().findViewById(-2079915834)).setText(android.text.Html.fromHtml(v2_7, 0));
        v9_1.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, -1));
        v9_1.getParentLayout().setOnClickListener(new Io.A(3, v9_1));
        return v1_2;
    }

    public final M3.a C1(android.view.LayoutInflater p8, android.view.ViewGroup p9)
    {
        String v8_1 = p8.inflate(-2079850469, p9, 0);
        int v9_1 = -2079915971;
        androidx.appcompat.widget.AppCompatButton v3_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915971, v8_1));
        if (v3_1 != null) {
            v9_1 = -2079915929;
            androidx.appcompat.widget.AppCompatImageView v4_1 = ((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915929, v8_1));
            if (v4_1 != null) {
                v9_1 = -2079915905;
                android.widget.LinearLayout v5_1 = ((android.widget.LinearLayout) l5.s.a(-2079915905, v8_1));
                if (v5_1 != null) {
                    v9_1 = -2079915833;
                    android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(-2079915833, v8_1));
                    if (v6_1 != null) {
                        return new Sn.m(((androidx.core.widget.NestedScrollView) v8_1), v3_1, v4_1, v5_1, v6_1);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v8_1.getResources().getResourceName(v9_1)));
    }

    public final Object F1(String p7, wg.c p8)
    {
        android.widget.ImageView v0_2;
        if (!(p8 instanceof Io.h0)) {
            v0_2 = new Io.h0(this, p8);
        } else {
            v0_2 = ((Io.h0) p8);
            android.widget.LinearLayout$LayoutParams v1_2 = ((Io.h0) p8).g;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((Io.h0) p8).g = (v1_2 - -2147483648);
            }
        }
        android.widget.LinearLayout v7_5;
        android.graphics.drawable.Drawable v8_1 = v0_2.e;
        android.widget.LinearLayout$LayoutParams v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        int v2_0 = v0_2.g;
        if (v2_0 == 0) {
            kotlin.ResultKt.throwOnFailure(v8_1);
            android.graphics.drawable.Drawable v8_4 = android.view.LayoutInflater.from(this.getContext()).inflate(-2079850438, 0, 0);
            android.widget.ImageView v4_2 = ((android.widget.ImageView) l5.s.a(-2079915894, v8_4));
            if (v4_2 == null) {
                throw new NullPointerException("Missing required view with ID: ".concat(v8_4.getResources().getResourceName(-2079915894)));
            } else {
                int v2_4 = new Sn.b(((android.widget.LinearLayout) v8_4), v4_2, ((android.widget.LinearLayout) v8_4), 4);
                android.graphics.drawable.Drawable v8_9 = new t4.g(this.requireContext());
                v8_9.c = p7;
                android.widget.LinearLayout v7_4 = v8_9.a();
                android.graphics.drawable.Drawable v8_11 = h4.a.a(v4_2.getContext());
                v0_2.d = v2_4;
                v0_2.g = 1;
                v8_1 = v8_11.a(v7_4, v0_2);
                if (v8_1 != v1_1) {
                    v7_5 = v2_4;
                } else {
                    return v1_1;
                }
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                v7_5 = ((Sn.b) v0_2.d);
                kotlin.ResultKt.throwOnFailure(v8_1);
            }
        }
        android.graphics.drawable.Drawable v8_14 = ((t4.k) v8_1).a();
        if (v8_14 != null) {
            ((android.widget.LinearLayout) v7_5.d).setLayoutParams(new android.widget.LinearLayout$LayoutParams(v8_14.getIntrinsicWidth(), v8_14.getIntrinsicHeight()));
            ((android.widget.ImageView) v7_5.c).setImageDrawable(v8_14);
        }
        return ((android.widget.LinearLayout) v7_5.d);
    }

    public final Object G1(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity p17, wg.c p18)
    {
        java.util.Iterator v2_2;
        if (!(p18 instanceof Io.i0)) {
            v2_2 = new Io.i0(this, p18);
        } else {
            v2_2 = ((Io.i0) p18);
            android.widget.LinearLayout$LayoutParams v3_16 = ((Io.i0) p18).l;
            if ((v3_16 & -2147483648) == 0) {
            } else {
                ((Io.i0) p18).l = (v3_16 - -2147483648);
            }
        }
        boolean v7_0;
        boolean v10_0;
        boolean v9_0;
        java.util.Iterator v8_0;
        int v6_2;
        android.widget.TextView v4_6;
        Object v1_4 = v2_2.j;
        android.widget.LinearLayout$LayoutParams v3_14 = vg.i.getCOROUTINE_SUSPENDED();
        android.widget.TextView v4_4 = v2_2.l;
        if (v4_4 == null) {
            android.widget.TextView v4_1;
            kotlin.ResultKt.throwOnFailure(v1_4);
            Object v1_8 = p17.getType();
            int v13_8 = 0;
            switch (v1_8.hashCode()) {
                case -1377687758:
                    v4_1 = p17;
                    if (v1_8.equals("button")) {
                        Object v1_7 = new java.util.ArrayList();
                        java.util.Iterator v2_6 = p17.getValues();
                        if (v2_6 != null) {
                            java.util.Iterator v2_8 = ((Iterable) v2_6).iterator();
                            while (v2_8.hasNext()) {
                                int v15_5;
                                android.widget.LinearLayout$LayoutParams v3_13 = ((String) v2_8.next());
                                android.widget.TextView v4_3 = new androidx.appcompat.widget.AppCompatButton(this.requireContext(), v13_8);
                                switch (v3_13.hashCode()) {
                                    case -1889321102:
                                        if (v3_13.equals("CHANGE_PHONE_BUTTON")) {
                                            v15_5 = this.getString(-2079719365);
                                        } else {
                                            int v13_1 = new Object[0];
                                            ck.b.e("[Bank][Toro] undefine cta type ".concat(v3_13), v13_1);
                                            v15_5 = "";
                                        }
                                        if (!v8_0.hasNext()) {
                                            return v10_0;
                                        } else {
                                            Object v1_16 = ((String) v8_0.next());
                                            v2_2.d = v10_0;
                                            v2_2.e = v7_0;
                                            v2_2.f = v8_0;
                                            v2_2.g = v7_0;
                                            v2_2.h = v6_2;
                                            v2_2.i = v4_6;
                                            v2_2.l = 1;
                                            v1_4 = this.F1(v1_16, v2_2);
                                            if (v1_4 != v3_14) {
                                                v9_0 = v7_0;
                                                v7_0.add(v1_4);
                                                v7_0 = v9_0;
                                            } else {
                                                return v3_14;
                                            }
                                        }
                                    case -785498532:
                                        if (v3_13.equals("SEND_FEEDBACK")) {
                                            v15_5 = this.getString(-2079719299);
                                        }
                                        break;
                                    case -375824631:
                                        if (v3_13.equals("OPEN_ACB_ACCOUNT_AND_LINK_BUTTON")) {
                                            v15_5 = this.getString(-2079719424);
                                        }
                                        break;
                                    case -246641509:
                                        if (v3_13.equals("OPEN_SCB_ACCOUNT_AND_LINK_BUTTON")) {
                                            v15_5 = this.getString(-2079719290);
                                        }
                                        break;
                                    default:
                                }
                                v4_3.setText(v15_5);
                                v4_3.setAllCaps(0);
                                int v13_3 = new android.widget.LinearLayout$LayoutParams(-2, -2);
                                v13_3.setMargins(0, 16, 0, 16);
                                v13_3.setLayoutDirection(1);
                                v4_3.setLayoutParams(v13_3);
                                v4_3.setTextColor(v4_3.getContext().getColor(2131100627));
                                v4_3.setTextSize(2, 1097859072);
                                v4_3.setPadding(24, 0, 24, 0);
                                v4_3.setBackgroundResource(-2079981520);
                                v4_3.setOnClickListener(new Io.L(this, v3_13, 1));
                                v1_7.add(v4_3);
                                v13_8 = 0;
                            }
                        }
                        return v1_7;
                    } else {
                    }
                case 98832:
                    if (v1_8.equals("cta")) {
                        Object v1_3 = new java.util.ArrayList();
                        java.util.Iterator v2_4 = new androidx.appcompat.widget.AppCompatButton(this.requireContext(), 0);
                        v2_4.setText(p17.getName());
                        v2_4.setAllCaps(0);
                        android.widget.LinearLayout$LayoutParams v3_6 = new android.widget.LinearLayout$LayoutParams(-2, -2);
                        v3_6.setMargins(0, 16, 0, 16);
                        v3_6.setLayoutDirection(1);
                        v2_4.setLayoutParams(v3_6);
                        v2_4.setTextColor(v2_4.getContext().getColor(2131100627));
                        v2_4.setTextSize(2, 1097859072);
                        v2_4.setPadding(24, 0, 24, 0);
                        v2_4.setBackgroundResource(-2079981520);
                        v2_4.setOnClickListener(new Io.d0(v2_4, p17, this));
                        v1_3.add(v2_4);
                        return v1_3;
                    } else {
                        v4_1 = p17;
                    }
                case 3556653:
                    if (!v1_8.equals("text")) {
                    } else {
                        Object v1_22 = new java.util.ArrayList();
                        java.util.Iterator v2_16 = p17.getValues();
                        if (v2_16 != null) {
                            java.util.Iterator v2_18 = ((Iterable) v2_16).iterator();
                            while (v2_18.hasNext()) {
                                android.widget.LinearLayout$LayoutParams v3_19 = ((String) v2_18.next());
                                android.widget.TextView v4_13 = new android.widget.TextView(this.getContext());
                                v4_13.setText(android.text.Html.fromHtml(v3_19, 0));
                                v4_13.setTextColor(v4_13.getContext().getColor(-2080112632));
                                v4_13.setTextSize(2, 1096810496);
                                v4_13.setTypeface(android.graphics.Typeface.create("sans-serif", 0));
                                v4_13.setLineSpacing(android.util.TypedValue.applyDimension(1, 1084227584, v4_13.getResources().getDisplayMetrics()), 1066192077);
                                android.widget.LinearLayout$LayoutParams v3_1 = new android.widget.LinearLayout$LayoutParams(-1, -2);
                                v3_1.setMargins(8, 8, 8, 8);
                                v4_13.setLayoutParams(v3_1);
                                v1_22.add(v4_13);
                            }
                        }
                        return v1_22;
                    }
                case 100313435:
                    if (v1_8.equals("image")) {
                        Object v1_19 = new java.util.ArrayList();
                        android.widget.TextView v4_8 = p17.getValues();
                        if (v4_8 == null) {
                            return v1_19;
                        } else {
                            v7_0 = v1_19;
                            v10_0 = v7_0;
                            v8_0 = ((Iterable) v4_8).iterator();
                            v4_6 = 0;
                            v6_2 = 0;
                        }
                    }
                default:
            }
            Object v1_9 = v4_1.getType();
            java.util.Iterator v2_11 = new StringBuilder("[Bank][Toro] undefine view type  ");
            v2_11.append(v1_9);
            java.util.Iterator v2_12 = new Object[0];
            ck.b.e(v2_11.toString(), v2_12);
            return kotlin.collections.E.emptyList();
        } else {
            if (v4_4 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                v4_6 = v2_2.i;
                v6_2 = v2_2.h;
                v7_0 = v2_2.g;
                v8_0 = v2_2.f;
                v9_0 = v2_2.e;
                v10_0 = v2_2.d;
                kotlin.ResultKt.throwOnFailure(v1_4);
            }
        }
    }

    public final Object H1(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity p9, wg.c p10)
    {
        Io.V v0_2;
        if (!(p10 instanceof Io.j0)) {
            v0_2 = new Io.j0(this, p10);
        } else {
            v0_2 = ((Io.j0) p10);
            int v1_2 = ((Io.j0) p10).k;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((Io.j0) p10).k = (v1_2 - -2147483648);
            }
        }
        java.util.Iterator v4_1;
        String v6;
        int v9_4;
        Po.t v2_3;
        String v5;
        java.util.Collection v10_4 = v0_2.i;
        int v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        Po.t v2_0 = v0_2.k;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v10_4);
            java.util.Collection v10_6 = new java.util.ArrayList();
            if (!kotlin.jvm.internal.Intrinsics.areEqual(p9.getType(), "QUIT_SURVEY")) {
                int v9_1 = p9.getFaqContents();
                if (v9_1 != 0) {
                    v2_3 = 0;
                    v4_1 = ((Iterable) v9_1).iterator();
                    v5 = v10_6;
                    v6 = v5;
                    v9_4 = 0;
                    if (!v4_1.hasNext()) {
                        return v6;
                    } else {
                        java.util.Collection v10_3 = ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) v4_1.next());
                        v0_2.d = v6;
                        v0_2.e = v5;
                        v0_2.f = v4_1;
                        v0_2.g = v2_3;
                        v0_2.h = v9_4;
                        v0_2.k = 1;
                        v10_4 = this.G1(v10_3, v0_2);
                        if (v10_4 != v1_1) {
                            if (((java.util.Collection) ((java.util.List) v10_4)).isEmpty()) {
                                v10_4 = 0;
                            }
                            java.util.Collection v10_8 = ((java.util.List) v10_4);
                            if (v10_8 != null) {
                                v5.addAll(((java.util.Collection) v10_8));
                            }
                        } else {
                            return v1_1;
                        }
                    }
                }
            } else {
                int v9_5 = p9.getFaqContents();
                if (v9_5 != 0) {
                    int v9_7 = ((Iterable) v9_5).iterator();
                    while (v9_7.hasNext()) {
                        Io.V v0_5 = ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) v9_7.next());
                        int v1_3 = v0_5.getType();
                        if ((kotlin.jvm.internal.Intrinsics.areEqual(v1_3, "radio_list")) || (kotlin.jvm.internal.Intrinsics.areEqual(v1_3, "radio_list_and_another_input_form"))) {
                            Io.V v0_6 = v0_5.getValues();
                            if (v0_6 != null) {
                                Po.t v2_9 = new Po.t(this.requireContext());
                                Po.t.g(v2_9, kotlin.collections.C.shuffled(((Iterable) v0_6)));
                                v2_9.r = new Io.V(1, this);
                                v10_6.add(v2_9);
                            }
                        }
                    }
                }
            }
            return v10_6;
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                v9_4 = v0_2.h;
                v2_3 = v0_2.g;
                v4_1 = v0_2.f;
                v5 = v0_2.e;
                v6 = v0_2.d;
                kotlin.ResultKt.throwOnFailure(v10_4);
            }
        }
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument I1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument) this.P.getValue());
    }

    public final void J1(String p7, String p8)
    {
        ng.w v2 = this.N;
        Bo.p v5 = this.L;
        switch (p7.hashCode()) {
            case -1889321102:
                if (p7.equals("CHANGE_PHONE_BUTTON")) {
                    kotlin.Unit v7_2 = this.I1().getBankCode();
                    v5.getClass();
                    UR.a.e("01.2026.074", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("bankCode", v7_2)));
                    this.m1();
                    m9.C.f(this, this.I1().getParentId(), vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$ChangePhone.INSTANCE, this.I1().getRequestResult());
                    return;
                } else {
                }
            case -785498532:
                if (p7.equals("SEND_FEEDBACK")) {
                    kotlin.Unit v7_21 = this.I1().getBankCode();
                    v5.getClass();
                    kotlin.Unit v7_22 = kotlin.TuplesKt.to("bankCode", v7_21);
                    if (p8 == null) {
                        p8 = "";
                    }
                    kotlin.Unit v7_24 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {v7_22, kotlin.TuplesKt.to("message", p8)}));
                    UR.a.e("01.2026.071", v7_24);
                    java.util.Objects.toString(v7_24);
                    this.m1();
                    m9.C.f(this, this.I1().getParentId(), vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$FeedbackConfirmation.INSTANCE, this.I1().getRequestResult());
                    return;
                } else {
                }
            case -375824631:
                if (p7.equals("OPEN_ACB_ACCOUNT_AND_LINK_BUTTON")) {
                    v2.getValue();
                    Class vtmp21 = v5.getClass();
                    UR.a.e("01.2026.075", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("bankCode", "ACB")));
                    this.m1();
                    m9.C.f(this, this.I1().getParentId(), new vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount("ACB", "toropopup"), this.I1().getRequestResult());
                    return;
                } else {
                }
            case -246641509:
                if (p7.equals("OPEN_SCB_ACCOUNT_AND_LINK_BUTTON")) {
                    v2.getValue();
                    Class vtmp29 = v5.getClass();
                    UR.a.e("01.2026.075", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("bankCode", "SCB")));
                    this.m1();
                    m9.C.f(this, this.I1().getParentId(), new vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount("SCB", "toropopup"), this.I1().getRequestResult());
                    return;
                } else {
                }
            default:
        }
        vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$FeedbackConfirmation v8_8 = new Object[0];
        ck.b.e("[Bank][Toro] Unknown cta type ".concat(p7), v8_8);
        return;
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
            this.K = v2_4;
            v2_4.m().k(this);
            return;
        }
    }

    public final void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        this.t1(2, -2079653887);
        return;
    }

    public final void onDestroyView()
    {
        super.onDestroyView();
        Runnable v0 = this.Q;
        if (v0 != null) {
            ((android.os.Handler) this.R.getValue()).removeCallbacks(v0);
        }
        return;
    }

    public final void onViewCreated(android.view.View p10, android.os.Bundle p11)
    {
        super.onViewCreated(p10, p11);
        q9.p0.a(this);
        Ro.p0 v10_9 = this.I1().getBankCode();
        String v11_12 = this.I1().getEntryPoint();
        this.L.getClass();
        Ro.p0 v10_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v10_9), kotlin.TuplesKt.to("entrypoint", v11_12)}));
        UR.a.e("01.2026.070", v10_3);
        java.util.Objects.toString(v10_3);
        Ro.p0 v10_5 = ((Sn.m) this.y1());
        v10_5.c.setOnClickListener(new Io.b0(this, 0));
        v10_5.b.setOnClickListener(new Io.b0(this, 1));
        Ro.p0 v10_8 = this.N;
        ((Ro.p0) v10_8.getValue()).g.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment, "handleCheckBankNumberUiState", "handleCheckBankNumberUiState(Lvn/com/vng/zalopay/bankbinding/presentation/model/UiStateModel;)V", 0, 0)));
        Ro.p0 v10_11 = ((Ro.p0) v10_8.getValue());
        String v11_10 = this.I1().getBankCode();
        v10_11.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v10_11), 0, 0, new Ro.X(v10_11, v11_10, 0, 5), 3, 0);
        return;
    }

    public final android.app.Dialog q1(android.os.Bundle p3)
    {
        android.app.Dialog v3_1 = super.q1(p3);
        android.view.Window v0 = v3_1.getWindow();
        if (v0 != null) {
            v0.setSoftInputMode(16);
        }
        return v3_1;
    }
}
