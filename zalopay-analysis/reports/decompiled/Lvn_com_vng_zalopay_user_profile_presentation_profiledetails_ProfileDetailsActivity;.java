package vn.com.vng.zalopay.user_profile.presentation.profiledetails;
public final class ProfileDetailsActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity {
    public static final synthetic int I;
    public final ng.S A;
    public final ng.S B;
    public final ng.S C;
    public final ng.S D;
    public final ng.S E;
    public final ng.S F;
    public final ng.S G;
    public final ng.S H;
    public KG.a k;
    public KG.b l;
    public Pt.n m;
    public Ux.S n;
    public final O5.b o;
    public NP.b p;
    public dQ.c q;
    public boolean r;
    public Ju.a s;
    public cQ.h t;
    public final li.e u;
    public final ng.S v;
    public final ng.S w;
    public final ng.S x;
    public final ng.S y;
    public final ng.S z;

    public ProfileDetailsActivity()
    {
        gi.L.a(kotlinx.coroutines.Dispatchers.getDefault().plus(gi.N.c()));
        this.o = new O5.b(kotlin.jvm.internal.M.a.b(WP.f), new NP.f(this, 0), new BF.a(17, this), new NP.f(this, 1));
        this.s = Ju.a.d;
        this.t = new cQ.h("", "", "", 2131233252, Ju.a.f, new cQ.m());
        this.u = gi.L.a(kotlin.coroutines.f.a(gi.N.c(), kotlinx.coroutines.Dispatchers.getMain()));
        this.v = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364127);
        this.w = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364132);
        this.x = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131363687);
        this.y = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365086);
        this.z = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364740);
        this.A = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365116);
        this.B = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365089);
        this.C = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365080);
        this.D = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365128);
        this.E = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365096);
        this.F = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364565);
        this.G = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365094);
        this.H = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365119);
        return;
    }

    public final int e1()
    {
        return 2131559552;
    }

    public final OD.a g1()
    {
        return new OD.a("[Me][Profile]", -1);
    }

    public final WP.f j1()
    {
        return ((WP.f) this.o.getValue());
    }

    public final androidx.recyclerview.widget.RecyclerView k1()
    {
        return ((androidx.recyclerview.widget.RecyclerView) this.v.getValue());
    }

    public final void l1()
    {
        if (!n9.O.e()) {
            com.airbnb.lottie.network.f v0_3 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a;
            v0_3.getClass();
            n9.O.a = new rv.b(17, v0_3);
        }
        n9.O.c(this, new pS.f(HS.c.e, "from_internal_feature", 0, 0, 28), new com.airbnb.lottie.network.f(20, this));
        return;
    }

    public final void onCreate(android.os.Bundle p18)
    {
        android.widget.TextView v0_14 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a;
        androidx.core.graphics.e v4_10 = new lS.b(25, v0_14);
        NP.c v1_40 = new yP.d(v4_10, 0);
        vn.zalopay.ui.navigationbar.NavigationBar v5_0 = new yP.d(v4_10, 5);
        androidx.core.graphics.e v6_1 = new yP.d(v4_10, 3);
        android.view.View v7_3 = new yP.d(v4_10, 4);
        long v11_3 = new yP.d(v4_10, 2);
        androidx.core.graphics.e v8_2 = new xC.b(v4_10, v11_3, 3);
        int v9_1 = new yP.d(v4_10, 6);
        int v3_28 = new DC.Q0(v4_10, v5_0, v6_1, v7_3, v8_2, v9_1, 7);
        vn.zalopay.ui.navigationbar.NavigationBar v5_6 = new yP.d(v4_10, 1);
        vn.zalopay.ui.navigationbar.NavigationBar v5_8 = new DC.Q0(v1_40, v3_28, new yP.c(v4_10, v5_6, 0), new yP.c(v4_10, v5_6, 1), v11_3, v9_1, 2);
        int v3_29 = v0_14.b();
        p9.n7.c(v3_29);
        this.k = v3_29;
        int v3_30 = v0_14.s();
        p9.n7.c(v3_30);
        this.l = v3_30;
        this.m = v0_14.h();
        this.n = ((Ux.S) v5_8.get());
        void v17_1 = super.onCreate(p18);
        androidx.core.graphics.e v8_7 = new dQ.c(new AA.z(1, this, vn.com.vng.zalopay.user_profile.presentation.profiledetails.ProfileDetailsActivity, "onCTABannerClicked", "onCTABannerClicked(Lvn/com/vng/zalopay/user_profile/presentation/viewstate/reformkyc/ReformKycStepUIState;)V", 0, 23));
        this.q = v8_7;
        v8_7.setHasStableIds(1);
        androidx.recyclerview.widget.RecyclerView vtmp5 = this.k1();
        NP.c v1_41 = this.q;
        androidx.core.graphics.e v8_0 = 0;
        if (v1_41 == null) {
            v1_41 = 0;
        }
        vtmp5.setAdapter(v1_41);
        android.widget.TextView v0_41 = this.q;
        if (v0_41 == null) {
            v0_41 = 0;
        }
        v0_41.i = ((int) ((this.getResources().getDisplayMetrics().density * 1098907648) + ((float) 23)));
        this.k1().setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(0));
        this.k1().addItemDecoration(new VP.b(((int) ((this.getResources().getDisplayMetrics().density * 1098907648) + ((float) 4))), 0));
        if (this.k1().getOnFlingListener() == null) {
            new sZ.j().a(this.k1());
        }
        void v2_1 = v17_1;
        long v11_1 = new NP.b(new AA.z(1, this, vn.com.vng.zalopay.user_profile.presentation.profiledetails.ProfileDetailsActivity, "onItemPersonalDetailClicked", "onItemPersonalDetailClicked(Lvn/com/vng/zalopay/user_profile/presentation/viewstate/profiledetails/SectionDetailsViewItem;)V", 0, 24), new AA.z(1, v2_1, vn.com.vng.zalopay.user_profile.presentation.profiledetails.ProfileDetailsActivity, "onItemBodySectionDetailClicked", "onItemBodySectionDetailClicked(Lvn/com/vng/zalopay/user_profile/presentation/viewstate/profiledetails/BodySectionDetailsRecyclerViewItem;)V", 0, 25));
        v2_1.p = v11_1;
        v11_1.setHasStableIds(0);
        android.widget.TextView v0_10 = v2_1.w;
        NP.c v1_6 = v0_10.getValue();
        int v3_4 = v2_1.p;
        if (v3_4 != 0) {
            v8_0 = v3_4;
        }
        ((androidx.recyclerview.widget.RecyclerView) v1_6).setAdapter(v8_0);
        ((androidx.recyclerview.widget.RecyclerView) v0_10.getValue()).setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(1));
        if (((androidx.recyclerview.widget.RecyclerView) v0_10.getValue()).getOnFlingListener() == null) {
            new sZ.j().a(((androidx.recyclerview.widget.RecyclerView) v0_10.getValue()));
        }
        NP.c v1_16 = v2_1.getDrawable(2131234569);
        if (v1_16 != null) {
            ((androidx.recyclerview.widget.RecyclerView) v0_10.getValue()).addItemDecoration(new VP.a(v1_16));
        }
        android.widget.TextView v0_13 = v2_1.x;
        ((vn.zalopay.ui.navigationbar.NavigationBar) v0_13.getValue()).setTitle(v2_1.getString(2131955060));
        ((vn.zalopay.ui.navigationbar.NavigationBar) v0_13.getValue()).setIsShowCloseButton(0);
        ((vn.zalopay.ui.navigationbar.NavigationBar) v0_13.getValue()).setListener(new IK.n(v2_1, 1));
        NP.c v1_25 = ((android.view.View) v2_1.H.getValue());
        vn.zalopay.ui.navigationbar.NavigationBar v5_4 = ((vn.zalopay.ui.navigationbar.NavigationBar) v0_13.getValue());
        android.view.View v7_4 = v2_1.findViewById(2131364164);
        androidx.core.graphics.e v4_9 = androidx.core.graphics.e.b(v1_25.getPaddingLeft(), v1_25.getPaddingTop(), v1_25.getPaddingRight(), v1_25.getPaddingBottom());
        androidx.core.graphics.e v6_4 = androidx.core.graphics.e.b(v5_4.getPaddingLeft(), 0, v5_4.getPaddingRight(), 0);
        androidx.core.graphics.e v8_3 = androidx.core.graphics.e.b(v7_4.getPaddingLeft(), 0, v7_4.getPaddingRight(), 0);
        v5_4.setBackground(new android.graphics.drawable.ColorDrawable(K0.X.z(ql.b.a)));
        androidx.core.view.h0.l(v1_25, new NP.e(v4_9, v5_4, v6_4, v7_4, v8_3));
        v2_1.j1().j.f(v2_1, new CS.v(6, new NP.d(v2_1, 0)));
        v2_1.j1().n.f(v2_1, new CS.v(6, new NP.d(v2_1, 1)));
        ((vn.zalopay.ui.IconFontTextView) v2_1.y.getValue()).setOnClickListener(new NP.c(v2_1, 0));
        ((android.widget.TextView) v2_1.z.getValue()).setOnClickListener(new NP.c(v2_1, 2));
        ((android.widget.TextView) v2_1.B.getValue()).setOnClickListener(new NP.c(v2_1, 3));
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        gi.L.b(this.u, 0);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        WP.f v0 = this.j1();
        v0.i.j(new Ew.s());
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v0), 0, 0, new WP.b(v0, 0, 2), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v0), 0, 0, new WP.b(v0, 0, 4), 3, 0);
        return;
    }
}
