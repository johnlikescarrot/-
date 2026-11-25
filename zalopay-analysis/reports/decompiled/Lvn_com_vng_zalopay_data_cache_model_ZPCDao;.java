package vn.com.vng.zalopay.data.cache.model;
public class ZPCDao extends sj.a {
    public static final String TABLENAME = "ZPC";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        long v0_3 = ((Rt.p) p6).a;
        if (v0_3 != 0) {
            p5.bindLong(1, v0_3.longValue());
        }
        p5.bindLong(2, ((Rt.p) p6).b);
        p5.bindLong(3, ((Rt.p) p6).c);
        long v0_1 = ((Rt.p) p6).d;
        if (v0_1 != 0) {
            p5.bindString(4, v0_1);
        }
        long v0_2 = ((Rt.p) p6).e;
        if (v0_2 != 0) {
            p5.bindString(5, v0_2);
        }
        long v0_4 = ((Rt.p) p6).f;
        if (v0_4 != 0) {
            p5.bindString(6, v0_4);
        }
        long v0_5 = ((Rt.p) p6).g;
        if (v0_5 != 0) {
            p5.bindString(7, v0_5);
        }
        long v0_6 = ((Rt.p) p6).h;
        if (v0_6 != 0) {
            p5.bindString(8, v0_6);
        }
        long v0_7 = ((Rt.p) p6).i;
        if (v0_7 != 0) {
            p5.bindString(9, v0_7);
        }
        p5.bindLong(10, ((Rt.p) p6).j);
        int v6_2 = ((Rt.p) p6).k;
        if (v6_2 != 0) {
            p5.bindLong(11, ((long) v6_2.intValue()));
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        long v0_3 = ((Rt.p) p5).a;
        if (v0_3 != 0) {
            p6.z(1, v0_3.longValue());
        }
        p6.z(2, ((Rt.p) p5).b);
        p6.z(3, ((Rt.p) p5).c);
        long v0_1 = ((Rt.p) p5).d;
        if (v0_1 != 0) {
            p6.F(4, v0_1);
        }
        long v0_2 = ((Rt.p) p5).e;
        if (v0_2 != 0) {
            p6.F(5, v0_2);
        }
        long v0_4 = ((Rt.p) p5).f;
        if (v0_4 != 0) {
            p6.F(6, v0_4);
        }
        long v0_5 = ((Rt.p) p5).g;
        if (v0_5 != 0) {
            p6.F(7, v0_5);
        }
        long v0_6 = ((Rt.p) p5).h;
        if (v0_6 != 0) {
            p6.F(8, v0_6);
        }
        long v0_7 = ((Rt.p) p5).i;
        if (v0_7 != 0) {
            p6.F(9, v0_7);
        }
        p6.z(10, ((Rt.p) p5).j);
        int v5_2 = ((Rt.p) p5).k;
        if (v5_2 != 0) {
            p6.z(11, ((long) v5_2.intValue()));
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        int v1_0;
        Rt.p v0_1 = new Rt.p();
        Integer v3 = 0;
        if (!p5.isNull(0)) {
            v1_0 = Long.valueOf(p5.getLong(0));
        } else {
            v1_0 = 0;
        }
        int v1_7;
        v0_1.a = v1_0;
        v0_1.b = p5.getLong(1);
        v0_1.c = p5.getLong(2);
        if (!p5.isNull(3)) {
            v1_7 = p5.getString(3);
        } else {
            v1_7 = 0;
        }
        int v1_9;
        v0_1.d = v1_7;
        if (!p5.isNull(4)) {
            v1_9 = p5.getString(4);
        } else {
            v1_9 = 0;
        }
        int v1_11;
        v0_1.e = v1_9;
        if (!p5.isNull(5)) {
            v1_11 = p5.getString(5);
        } else {
            v1_11 = 0;
        }
        int v1_13;
        v0_1.f = v1_11;
        if (!p5.isNull(6)) {
            v1_13 = p5.getString(6);
        } else {
            v1_13 = 0;
        }
        int v1_15;
        v0_1.g = v1_13;
        if (!p5.isNull(7)) {
            v1_15 = p5.getString(7);
        } else {
            v1_15 = 0;
        }
        int v1_17;
        v0_1.h = v1_15;
        if (!p5.isNull(8)) {
            v1_17 = p5.getString(8);
        } else {
            v1_17 = 0;
        }
        v0_1.i = v1_17;
        v0_1.j = p5.getLong(9);
        if (!p5.isNull(10)) {
            v3 = Integer.valueOf(p5.getInt(10));
        }
        v0_1.k = v3;
        return v0_1;
    }

    public final Object q(android.database.Cursor p3)
    {
        if (!p3.isNull(0)) {
            return Long.valueOf(p3.getLong(0));
        } else {
            return 0;
        }
    }

    public final Object r(long p2, Object p4)
    {
        ((Rt.p) p4).a = Long.valueOf(p2);
        return Long.valueOf(p2);
    }
}
