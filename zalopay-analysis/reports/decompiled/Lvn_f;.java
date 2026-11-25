package vn;
public final class f extends androidx.lifecycle.ViewModel {
    public final Is.a c;
    public final n9.i3 d;

    public f(Is.a p3)
    {
        this.c = p3;
        n9.i3 v3_2 = new n9.i3();
        androidx.lifecycle.T v0_1 = new androidx.lifecycle.T(vn.b.a);
        v3_2.a = v0_1;
        v3_2.b = v0_1;
        this.d = v3_2;
        return;
    }

    public static final Object g0(vn.f p11, String p12, wg.c p13)
    {
        int v0_3;
        if (!(p13 instanceof vn.e)) {
            v0_3 = new vn.e(p11, p13);
        } else {
            v0_3 = ((vn.e) p13);
            String v1_2 = ((vn.e) p13).g;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((vn.e) p13).g = (v1_2 - -2147483648);
            }
        }
        sn.a v13_1 = v0_3.e;
        String v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        Integer v2_0 = v0_3.g;
        int v3_0 = 1;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v13_1);
            v0_3.d = p12;
            v0_3.g = 1;
            v13_1 = kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getIO(), new CS.r(2, 17, 0), v0_3);
            if (v13_1 == v1_1) {
                return v1_1;
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                p12 = v0_3.d;
                kotlin.ResultKt.throwOnFailure(v13_1);
            }
        }
        sn.a v13_3 = ((sn.a) v13_1);
        int v0_1 = 0;
        if (v13_3 != null) {
            String v1_3 = v13_3.a;
            vn.a v12_7 = m9.Z3.a(p12);
            Integer v2_5 = ((Number) v12_7.a).intValue();
            Iterable v4_5 = ((Number) v12_7.b).intValue();
            vn.a v12_10 = ((Number) v12_7.c).intValue();
            String v1_4 = m9.Z3.a(v1_3);
            if (kotlin.collections.CollectionsKt.l(((Iterable) kotlin.collections.E.listOf(new Integer[] {Integer.valueOf(((Number) v1_4.a).intValue()), Integer.valueOf(((Number) v1_4.b).intValue()), Integer.valueOf(((Number) v1_4.c).intValue())}))), ".", 0, 0, 0, 62).compareTo(kotlin.collections.CollectionsKt.l(((Iterable) kotlin.collections.E.listOf(new Integer[] {Integer.valueOf(v2_5), Integer.valueOf(v4_5), Integer.valueOf(v12_10)}))), ".", 0, 0, 0, 62)) <= 0) {
                v3_0 = 0;
            }
            v0_1 = v3_0;
        }
        vn.a v12_4;
        kotlin.Unit v11_1 = p11.d;
        if (v13_3 == null) {
            v12_4 = new vn.a();
        } else {
            v12_4 = new vn.c(v13_3, v0_1);
        }
        ((androidx.lifecycle.T) v11_1.a).j(v12_4);
        return kotlin.Unit.INSTANCE;
    }
}
