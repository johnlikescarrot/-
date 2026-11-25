package vn.com.vng.zalopay.search.presentation.ui.search;
public final class SearchActivity extends vn.com.vng.zalopay.search.presentation.base.BaseComposeActivity {
    public static final synthetic int q;
    public SM.h0 j;
    public KG.a k;
    public final ng.S l;
    public final ng.S m;
    public final ng.S n;
    public final O5.b o;
    public final q0.N0 p;

    public SearchActivity()
    {
        this.l = ng.x.b(new SM.b(this, 0));
        this.m = ng.x.b(new SM.b(this, 1));
        this.n = ng.x.b(new SM.b(this, 2));
        this.o = new O5.b(kotlin.jvm.internal.M.a.b(SM.n0), new SM.d(this, 0), new SM.b(this, 3), new SM.d(this, 1));
        this.p = q0.d.G(Boolean.FALSE, q0.D0.e);
        return;
    }

    public final void e1(int p5, q0.s p6)
    {
        AA.e v0_8;
        q0.Y0 v6_1 = p6.x(-122795890);
        if (!v6_1.I(this)) {
            v0_8 = 16;
        } else {
            v0_8 = 32;
        }
        if ((((v0_8 | p5) & 19) == 18) && (v6_1.c())) {
            v6_1.m();
        } else {
            int v2_1;
            AA.e v0_4 = ((SM.n0) this.o.getValue());
            int v1_4 = ((Boolean) this.p.getValue()).booleanValue();
            if (android.os.Build$VERSION.SDK_INT > 27) {
                v2_1 = 0;
            } else {
                v2_1 = 1;
            }
            SM.Q.b(v0_4, v1_4, v2_1, v6_1, 3072);
        }
        q0.Y0 v6_2 = v6_1.Y();
        if (v6_2 != null) {
            v6_2.d = new AA.e(this, p5, 20);
        }
        return;
    }

    public final void finish()
    {
        super.finish();
        if (android.os.Build$VERSION.SDK_INT > 27) {
            this.overridePendingTransition(0, 0);
            return;
        } else {
            return;
        }
    }

    public final java.util.List g1(q0.s p14)
    {
        p14.q(-1391232441);
        h4.j v1_4 = ((android.content.Context) p14.d(androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.b));
        h4.d v2_1 = new h4.d(v1_4);
        java.util.ArrayList v3_0 = new java.util.ArrayList();
        java.util.ArrayList v4_1 = new java.util.ArrayList();
        java.util.ArrayList v5_1 = new java.util.ArrayList();
        java.util.ArrayList v6_1 = new java.util.ArrayList();
        SP.n.s(v1_4, v6_1);
        if (android.os.Build$VERSION.SDK_INT < 28) {
            SP.n.A(v6_1, 1);
        } else {
            SP.n.t(v1_4, v6_1, 1);
        }
        v2_1.c = new com.google.firebase.messaging.p(kotlin.collections.CollectionsKt.toList(v3_0), kotlin.collections.CollectionsKt.toList(v4_1), kotlin.collections.CollectionsKt.toList(v5_1), kotlin.collections.CollectionsKt.toList(v6_1), 24);
        v2_1.c(kotlinx.coroutines.Dispatchers.getIO());
        v2_1.e();
        v2_1.d();
        v2_1.b();
        java.util.List v0_3 = kotlin.collections.C.listOf(l4.o.a.a(v2_1.a()));
        p14.n();
        return v0_3;
    }

    public final void onCreate(android.os.Bundle p7)
    {
        super.onCreate(p7);
        if (android.os.Build$VERSION.SDK_INT > 27) {
            this.overridePendingTransition(0, 0);
        }
        try {
            String v7_4 = m9.k.a;
        } catch (Throwable) {
            this.finish();
            n9.s4.a(this.getOnBackPressedDispatcher(), this, new Am.b(29, this));
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new SM.c(this, 0, 0), 3, 0);
            return;
        }
        if (v7_4 == null) {
            String v7_14 = m9.k.b;
            if (v7_14 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling SearchPlugin.install()");
            } else {
                v7_4 = new AM.f(new AW.a(9, v7_14));
                m9.k.a = v7_4;
            }
        } else {
        }
        this.j = ((SM.h0) ((dagger.internal.k) v7_4.h).a);
        String v7_9 = ((FM.a) ((AW.a) v7_4.a).b).b();
        p9.n7.c(v7_9);
        this.k = v7_9;
        n9.s4.a(this.getOnBackPressedDispatcher(), this, new Am.b(29, this));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new SM.c(this, 0, 0), 3, 0);
        return;
    }
}
