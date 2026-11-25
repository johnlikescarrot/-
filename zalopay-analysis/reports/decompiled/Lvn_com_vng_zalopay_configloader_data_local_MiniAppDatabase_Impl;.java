package vn.com.vng.zalopay.configloader.data.local;
public final class MiniAppDatabase_Impl extends vn.com.vng.zalopay.configloader.data.local.MiniAppDatabase {
    public final ng.S o;
    public final ng.S p;

    public MiniAppDatabase_Impl()
    {
        this.o = ng.x.b(new ws.j(this, 0));
        this.p = ng.x.b(new ws.j(this, 1));
        return;
    }

    public final java.util.List d(java.util.LinkedHashMap p1)
    {
        return new java.util.ArrayList();
    }

    public final s3.G e()
    {
        return new s3.G(this, new java.util.LinkedHashMap(), new java.util.LinkedHashMap(), new String[] {"MiniAppInfo", "MiniAppInfoNavBar", "MiniAppInfoGroup", "micro_app_version"}));
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
        kotlin.reflect.d v1_1 = kotlin.jvm.internal.M.a;
        v0_1.put(v1_1.b(ws.l), kotlin.collections.E.emptyList());
        v0_1.put(v1_1.b(ws.f), kotlin.collections.E.emptyList());
        return v0_1;
    }

    public final ws.f r()
    {
        return ((ws.f) this.p.getValue());
    }

    public final ws.l s()
    {
        return ((ws.l) this.o.getValue());
    }
}
