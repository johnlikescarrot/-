package vn.com.vng.zalopay.homev5.presenter.ui.story_collections_player;
public final class StoryCollectionsPlayerActivity extends vn.com.vng.zalopay.homev5.presenter.base.BaseComposeActivity {
    public static final synthetic int o;
    public final androidx.lifecycle.r0 j;
    public final KG.a k;
    public final d6.l l;
    public final WB.b m;
    public final O5.b n;

    public StoryCollectionsPlayerActivity()
    {
        this.n = new O5.b(kotlin.jvm.internal.M.a.b(RB.J), new RB.b(this, 0), new BF.a(22, this), new RB.b(this, 1));
        aA.a v0_1 = com.google.android.gms.internal.mlkit_vision_barcode.i6.b;
        if (v0_1 == null) {
            aA.a v0_2 = com.google.android.gms.internal.mlkit_vision_barcode.i6.a;
            if (v0_2 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling HomeV5Plugin.install()");
            } else {
                String v1_4 = new XQ.i(9, v0_2);
                aA.a v0_5 = com.google.android.gms.internal.mlkit_vision_barcode.i6.c;
                if (v0_5 == null) {
                    v0_5 = gi.N.d();
                    com.google.android.gms.internal.mlkit_vision_barcode.i6.c = v0_5;
                }
                aA.a v3_3 = new aA.a(v1_4, new androidx.camera.camera2.impl.a(7, gi.L.a(v0_5)), new kX.d());
                com.google.android.gms.internal.mlkit_vision_barcode.i6.b = v3_3;
                v0_1 = v3_3;
            }
        }
        this.j = ((androidx.lifecycle.r0) v0_1.K.get());
        String v1_12 = ((aA.c) v0_1.a.b).b();
        p9.n7.c(v1_12);
        this.k = v1_12;
        this.l = new d6.l(new D5.f(16));
        this.m = ((WB.b) v0_1.t.get());
        return;
    }

    public static void i1(String p2)
    {
        Object[] v1_1 = new Object[0];
        ck.b.d("StoryCollectionsPlayerActivity").f(p2, v1_1);
        return;
    }

    public final void e1(int p18, q0.s p19)
    {
        int v2_0;
        q0.v v8 = p19.x(-1552139121);
        if (!v8.I(this)) {
            v2_0 = 16;
        } else {
            v2_0 = 32;
        }
        if ((((v2_0 | p18) & 19) == 18) && (v8.c())) {
            v8.m();
        } else {
            AA.e v3_3 = ((RB.G) m9.k3.a(this.h1().k0(), v8, 0).getValue());
            int v4_0 = this.h1().p;
            int v2_10 = this.m;
            if (v2_10 == 0) {
                v2_10 = 0;
            }
            WB.b v5 = v2_10;
            RB.J v11 = this.h1();
            int v2_11 = v8.I(v11);
            kotlin.jvm.functions.Function1 v6_0 = v8.G();
            kotlin.jvm.functions.Function1 v7_0 = q0.r.a;
            if ((v2_11 != 0) || (v6_0 == v7_0)) {
                int v9_1 = new AA.z(1, v11, RB.J, "processIntent", "processIntent(Ljava/lang/Object;)V", 0, 29);
                v8.B(v9_1);
                v6_0 = v9_1;
            }
            kotlin.jvm.functions.Function1 v6_2 = ((kotlin.jvm.functions.Function1) ((kotlin.reflect.g) v6_0));
            int v2_12 = v8.I(this);
            int v9_2 = v8.G();
            if ((v2_12 != 0) || (v9_2 == v7_0)) {
                v9_2 = new Am.b(26, this);
                v8.B(v9_2);
            }
            RB.F.a(v3_3, v4_0, v5, v6_2, ((kotlin.jvm.functions.Function1) v9_2), v8, 196608);
        }
        int v2_15 = v8.Y();
        if (v2_15 != 0) {
            v2_15.d = new AA.e(this, p18, 18);
        }
        return;
    }

    public final q0.V0 g1(q0.s p14)
    {
        p14.q(424851473);
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
        q0.V0 v0_2 = l4.o.a.a(v2_1.a());
        p14.n();
        return v0_2;
    }

    public final RB.J h1()
    {
        return ((RB.J) this.n.getValue());
    }

    public final void onCreate(android.os.Bundle p7)
    {
        super.onCreate(p7);
        androidx.activity.q.a(this, new androidx.activity.J(0, 0, 2, androidx.activity.I.f), 2);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new RB.a(this, 0, 0), 3, 0);
        String v7_3 = this.getIntent();
        if (v7_3 != null) {
            String v7_4 = v7_3.getStringExtra("focused_story_collection_id");
            if (v7_4 != null) {
                this.h1().o0(new RB.q(v7_4));
            }
        }
        return;
    }

    public final void onDestroy()
    {
        String v0_0 = this.m;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.a();
        vn.com.vng.zalopay.homev5.presenter.ui.story_collections_player.StoryCollectionsPlayerActivity.i1("Released all media players");
        super.onDestroy();
        return;
    }
}
