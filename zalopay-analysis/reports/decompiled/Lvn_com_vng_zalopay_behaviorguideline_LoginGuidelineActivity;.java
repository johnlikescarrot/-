package vn.com.vng.zalopay.behaviorguideline;
public final class LoginGuidelineActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity {
    public static final synthetic int s;
    public Is.a k;
    public org.greenrobot.eventbus.e l;
    public final ng.S m;
    public final ng.S n;
    public final ng.S o;
    public final ng.S p;
    public final gi.x0 q;
    public final ng.S r;

    public LoginGuidelineActivity()
    {
        this.m = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364476);
        this.n = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364662);
        this.o = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131363231);
        this.p = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362073);
        this.q = gi.N.c();
        this.r = ng.x.b(new TT.a(8, this));
        return;
    }

    public final int e1()
    {
        return 2131558442;
    }

    public final OD.a g1()
    {
        return new OD.a("[ZaloPayLoginGuideline]", -1);
    }

    public final void onCreate(android.os.Bundle p9)
    {
        super.onCreate(p9);
        UR.a.d("01.1507.000");
        Ae.p v0_10 = ((CC.f) q9.a5.a());
        v0_10.e0.get();
        this.k = ((Is.a) v0_10.f0.get());
        Ae.p v0_3 = ((org.greenrobot.eventbus.e) v0_10.g.get());
        this.l = v0_3;
        if (v0_3 == null) {
            v0_3 = 0;
        }
        v0_3.k(this);
        kotlinx.coroutines.BuildersKt.launch$default(((kotlinx.coroutines.CoroutineScope) this.r.getValue()), 0, 0, new RB.a(this, 0, 7), 3, 0);
        if (p9 == null) {
            android.widget.Button v9_2 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
            v9_2.i(2131362803, new Uo.a(), 0, 1);
            v9_2.f();
        }
        this.setSupportActionBar(((androidx.appcompat.widget.Toolbar) this.m.getValue()));
        android.widget.Button v9_6 = this.getSupportActionBar();
        if (v9_6 != null) {
            v9_6.y("");
        }
        ((android.widget.TextView) this.n.getValue()).setText(android.text.Html.fromHtml(this.getString(2131953619), 0));
        ((android.widget.Button) this.p.getValue()).setOnClickListener(new Ae.p(24, this));
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        gi.x0 v0_0 = this.l;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.p(this);
        this.q.cancel(0);
        return;
    }

    public final void onResourceReady$app_productionRelease(eu.h p7)
    {
        kotlinx.coroutines.BuildersKt.launch$default(((kotlinx.coroutines.CoroutineScope) this.r.getValue()), 0, 0, new RB.a(this, 0, 7), 3, 0);
        return;
    }
}
