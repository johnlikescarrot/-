package vn.com.vng.zalopay.bankbinding.presentation.ui.activity;
public final class BankMediaWebViewActivity extends vn.com.vng.zalopay.bankbinding.presentation.ui.activity.PerformanceActivity {
    public static final synthetic int r;
    public final a9.f m;
    public final ng.S n;
    public final ng.S o;
    public vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankMediaWebView p;
    public androidx.compose.ui.platform.ComposeView q;

    public BankMediaWebViewActivity()
    {
        this.m = new a9.f(kotlin.jvm.internal.M.a.b(Fo.l), new Fo.j(this, 2));
        this.n = ng.x.b(new Fo.k(this, 0));
        this.o = ng.x.b(new Fo.k(this, 1));
        return;
    }

    public final int e1()
    {
        return -2079850495;
    }

    public final OD.a g1()
    {
        return new OD.a("", -1);
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        int v5_14 = ((Bo.f) this.o.getValue());
        int v5_5 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v5_14.a), kotlin.TuplesKt.to("url", v5_14.c), kotlin.TuplesKt.to("entrypoint", v5_14.b)}));
        UR.a.e("01.2026.070", v5_5);
        java.util.Objects.toString(v5_5);
        this.p = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankMediaWebView) this.findViewById(-2079915869));
        this.q = ((androidx.compose.ui.platform.ComposeView) this.findViewById(-2079915927));
        int v5_13 = this.p;
        androidx.core.view.m1 v0_1 = this.n;
        if (v5_13 != 0) {
            androidx.core.view.e v1_2 = new Qo.a(((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument) v0_1.getValue()).getUrl(), v5_13);
            int v5_15 = v1_2.b;
            v5_15.setWebViewClient(v1_2);
            v5_15.resumeTimers();
            v5_15.loadUrl(v1_2.a);
        }
        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument) v0_1.getValue()).getDisplayMode(), "NORMAL")) {
            int v5_20 = this.q;
            if (v5_20 != 0) {
                v5_20.setVisibility(8);
            }
            androidx.core.view.m1 v0_9;
            int v5_21 = this.getWindow();
            androidx.core.view.e v1_6 = new androidx.core.view.e(2, this.getWindow().getDecorView());
            androidx.core.view.m1 v0_7 = android.os.Build$VERSION.SDK_INT;
            if (v0_7 < 35) {
                if (v0_7 < 30) {
                    v0_9 = new androidx.core.view.m1(v5_21, v1_6);
                } else {
                    v0_9 = new androidx.core.view.n1(v5_21, v1_6);
                }
            } else {
                v0_9 = new androidx.core.view.o1(v5_21, v1_6);
            }
            v0_9.f();
            v0_9.a(519);
            return;
        } else {
            int v5_23 = this.q;
            if (v5_23 != 0) {
                v5_23.setContent(new y0.b(new Fo.b(1, this), 1, -1617164902));
            }
            return;
        }
    }

    public final void onDestroy()
    {
        vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankMediaWebView v0 = this.p;
        if (v0 != null) {
            v0.removeAllViews();
            v0.clearHistory();
            v0.clearCache(1);
            v0.destroy();
        }
        super.onDestroy();
        return;
    }
}
