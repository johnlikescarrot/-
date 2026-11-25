package vn.com.vng.zalopay.mini_app_state.data.local;
public final class MiniAppStateDatabase_Impl extends vn.com.vng.zalopay.mini_app_state.data.local.MiniAppStateDatabase {
    public final ng.S o;

    public MiniAppStateDatabase_Impl()
    {
        this.o = ng.x.b(new TT.a(3, this));
        return;
    }

    public final java.util.List d(java.util.LinkedHashMap p5)
    {
        java.util.ArrayList v5_1 = new java.util.ArrayList();
        v5_1.add(new UE.b(1, 2, 0));
        return v5_1;
    }

    public final s3.G e()
    {
        return new s3.G(this, new java.util.LinkedHashMap(), new java.util.LinkedHashMap(), new String[] {"recent_viewed_mini_app"}));
    }

    public final s3.w0 f()
    {
        return new UE.c(this);
    }

    public final java.util.Set j()
    {
        return new java.util.LinkedHashSet();
    }

    public final java.util.LinkedHashMap k()
    {
        java.util.LinkedHashMap v0_1 = new java.util.LinkedHashMap();
        v0_1.put(kotlin.jvm.internal.M.a.b(VE.d), kotlin.collections.E.emptyList());
        return v0_1;
    }

    public final VE.d r()
    {
        return ((VE.d) this.o.getValue());
    }
}
