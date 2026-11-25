package vn.com.vng.zalopay.search.presentation.analytics;
public final class A {
    public final wM.a a;
    public final eN.a b;
    public final eN.b c;
    public final String d;

    public A(wM.a p1, eN.a p2, eN.b p3, String p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final void a(C3.c p9)
    {
        wM.a v1_0;
        java.util.Map v0_0 = kotlin.collections.Z.createMapBuilder();
        wM.a v1_8 = this.b.ordinal();
        java.util.Map v2_0 = "unknown";
        if (v1_8 == null) {
            v1_0 = "home_screen";
        } else {
            if (v1_8 == 1) {
                v1_0 = "home_more";
            } else {
                if (v1_8 != 2) {
                    throw new kotlin.NoWhenBranchMatchedException();
                } else {
                    v1_0 = "unknown";
                }
            }
        }
        v0_0.put("source", v1_0);
        wM.a v1_3 = this.c.ordinal();
        if (v1_3 == null) {
            v2_0 = "new";
        } else {
            if (v1_3 == 1) {
                v2_0 = "current";
            } else {
                if (v1_3 != 2) {
                    throw new kotlin.NoWhenBranchMatchedException();
                }
            }
        }
        v0_0.put("user_type", v2_0);
        v0_0.put("app_session_id", this.d);
        wM.a v1_7 = p9.getClass().getDeclaredFields();
        java.util.Map v2_3 = new java.util.ArrayList();
        int v3_0 = v1_7.length;
        int v5_1 = 0;
        while (v5_1 < v3_0) {
            kotlin.Pair v6_2;
            kotlin.Pair v6_0 = v1_7[v5_1];
            String v7_2 = ((vn.com.vng.zalopay.search.presentation.analytics.a) v6_0.getAnnotation(vn.com.vng.zalopay.search.presentation.analytics.a));
            if (v7_2 == null) {
                v6_2 = 0;
            } else {
                String v7_3 = v7_2.name();
                if (v7_3 != null) {
                    v6_0.setAccessible(1);
                    v6_2 = kotlin.TuplesKt.to(v7_3, v6_0.get(p9));
                }
            }
            if (v6_2 != null) {
                v2_3.add(v6_2);
            }
            v5_1++;
        }
        v0_0.putAll(kotlin.collections.a0.plus(kotlin.collections.a0.toMap(v2_3), p9.n()));
        this.a.b(String.valueOf(p9.b), kotlin.collections.Z.build(v0_0));
        return;
    }
}
