package vn.com.vng.zalopay.user_profile.presentation.additionalinfo;
public final class a extends sZ.b {
    public static final vn.com.vng.zalopay.user_profile.presentation.additionalinfo.b O;
    public static final synthetic kotlin.reflect.n[] P;
    public final java.util.List H;
    public vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a$a I;
    public YP.c J;
    public final Ai.t K;
    public final Ai.t L;
    public final Ai.t M;
    public final Ai.t N;

    static a()
    {
        kotlin.reflect.p v1_1 = kotlin.jvm.internal.M.a;
        vn.com.vng.zalopay.user_profile.presentation.additionalinfo.b v0_1 = v1_1.g(new kotlin.jvm.internal.D(wmoi.igob.ggcn(2138), 0, wmoi.igob.ggcn(2139), vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a));
        kotlin.reflect.p v3_2 = Kb.e.r(vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a, wmoi.igob.ggcn(2140), wmoi.igob.ggcn(2141), 0, v1_1);
        kotlin.reflect.p v5_4 = Kb.e.r(vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a, wmoi.igob.ggcn(2142), wmoi.igob.ggcn(2143), 0, v1_1);
        kotlin.reflect.p v1_3 = Kb.e.r(vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a, wmoi.igob.ggcn(2144), wmoi.igob.ggcn(2145), 0, v1_1);
        kotlin.reflect.n[] v4_1 = new kotlin.reflect.n[4];
        v4_1[0] = v0_1;
        v4_1[1] = v3_2;
        v4_1[2] = v5_4;
        v4_1[3] = v1_3;
        vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a.P = v4_1;
        vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a.O = new vn.com.vng.zalopay.user_profile.presentation.additionalinfo.b();
        return;
    }

    public a()
    {
        this.H = kotlin.collections.E.emptyList();
        this.J = new YP.c(0, 0, 7);
        this.K = new Ai.t(2131364793, 22);
        this.L = new Ai.t(2131362082, 22);
        this.M = new Ai.t(2131363097, 22);
        this.N = new Ai.t(2131364014, 22);
        return;
    }

    public final void onStart()
    {
        android.view.Window v0_0 = this.C;
        if (v0_0 != null) {
            android.view.Window v0_1 = v0_0.getWindow();
            if (v0_1 != null) {
                v0_1.setGravity(81);
                v0_1.setLayout(-1, -2);
            }
        }
        super.onStart();
        return;
    }

    public final void onViewCreated(android.view.View p9, android.os.Bundle p10)
    {
        android.view.View v9_1;
        super.onViewCreated(p9, p10);
        android.view.View v9_2 = this.getArguments();
        if (v9_2 == null) {
            v9_1 = 0;
        } else {
            v9_1 = ((YP.a) v9_2.getParcelable(wmoi.igob.ggcn(2146)));
        }
        kotlin.reflect.n v10_2 = new FP.b(new AA.z(1, this, vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a, wmoi.igob.ggcn(2147), wmoi.igob.ggcn(2148), 0, 18));
        kotlin.reflect.n[] v0_2 = vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a.P;
        if (v9_1 != null) {
            ((android.widget.TextView) this.K.j(this, v0_2[0])).setText(v9_1.a);
            android.view.View v9_3 = v9_1.b;
            int v3_4 = v10_2.h;
            v3_4.clear();
            v3_4.addAll(v9_3);
            v10_2.notifyDataSetChanged();
        }
        Ai.t v4_4 = this.N;
        ((androidx.recyclerview.widget.RecyclerView) v4_4.j(this, v0_2[3])).setAdapter(v10_2);
        android.view.View v9_7 = ((androidx.recyclerview.widget.RecyclerView) v4_4.j(this, v0_2[3]));
        this.requireContext();
        v9_7.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(1));
        ((android.view.View) this.L.j(this, v0_2[1])).setEnabled(0);
        return;
    }

    public final boolean w1()
    {
        return 0;
    }

    public final int x1()
    {
        return 2131559451;
    }

    public final void z1()
    {
        FP.c v1_0 = vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a.P;
        ((android.widget.ImageView) this.M.j(this, v1_0[2])).setOnClickListener(new FP.c(this, 0));
        ((android.view.View) this.L.j(this, v1_0[1])).setOnClickListener(new FP.c(this, 1));
        return;
    }
}
