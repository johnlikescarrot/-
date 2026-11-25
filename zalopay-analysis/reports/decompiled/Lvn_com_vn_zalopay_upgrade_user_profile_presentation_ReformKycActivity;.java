package vn.com.vn.zalopay.upgrade_user_profile.presentation;
public final class ReformKycActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int l;
    public al.i i;
    public boolean j;
    public KG.a k;

    public ReformKycActivity()
    {
        gi.L.a(kotlinx.coroutines.Dispatchers.getDefault().plus(gi.N.c()));
        return;
    }

    public final void onCreate(android.os.Bundle p11)
    {
        super.onCreate(p11);
        p9.U.a(this.getWindow(), 0);
        androidx.activity.G v11_9 = m9.e4.a;
        boolean v3_2 = ((Xk.a) ((CC.k) v11_9.a).U().create(Xk.a));
        p9.n7.c(v3_2);
        this.i = new al.i(new al.j(new c6.a(27, v3_2)));
        androidx.activity.G v11_5 = ((KG.a) ((dagger.internal.o) v11_9.c).get());
        p9.n7.c(v11_5);
        this.k = v11_5;
        k.e.a(this, new y0.b(new al.e(this, 0), 1, -1371301159));
        androidx.activity.G v11_8 = this.i;
        int v1_4 = 0;
        if (v11_8 == null) {
            v11_8 = 0;
        }
        boolean v3_4 = this.getLifecycle();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v3_4), 0, 0, new Vv.c(v3_4, v11_8, 0, this), 3, 0);
        androidx.activity.G v11_11 = this.getIntent().getStringExtra("reform_kyc_extra_params");
        if (v11_11 == null) {
            v11_11 = "";
        }
        Gy.a v0_1 = this.getIntent().getIntExtra("reform_kyc_source", 0);
        boolean v3_8 = this.getIntent().getBooleanExtra("should_show_landing_screen", 1);
        boolean vtmp15 = this.getIntent().getBooleanExtra("should_show_result_screen", 1);
        hl.g v4_7 = this.i;
        if (v4_7 != null) {
            v1_4 = v4_7;
        }
        v1_4.n0(new hl.g(v11_11, v0_1, v3_8, vtmp15));
        this.getOnBackPressedDispatcher().a(this, new Gy.a(this, 0, 7));
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        bl.a.a.clear();
        return;
    }
}
