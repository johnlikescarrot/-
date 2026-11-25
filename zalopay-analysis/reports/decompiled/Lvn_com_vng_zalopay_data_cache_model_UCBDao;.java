package vn.com.vng.zalopay.data.cache.model;
public class UCBDao extends sj.a {
    public static final String TABLENAME = "UCB";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        int v0_3 = ((Rt.l) p6).a;
        if (v0_3 != 0) {
            p5.bindLong(1, v0_3.longValue());
        }
        p5.bindString(2, ((Rt.l) p6).b);
        int v0_0 = ((Rt.l) p6).c;
        if (v0_0 != 0) {
            p5.bindString(3, v0_0);
        }
        int v0_1 = ((Rt.l) p6).d;
        if (v0_1 != 0) {
            p5.bindString(4, v0_1);
        }
        int v0_2 = ((Rt.l) p6).e;
        if (v0_2 != 0) {
            p5.bindString(5, v0_2);
        }
        int v0_4 = ((Rt.l) p6).f;
        if (v0_4 != 0) {
            p5.bindString(6, v0_4);
        }
        int v0_5 = ((Rt.l) p6).g;
        if (v0_5 != 0) {
            p5.bindString(7, v0_5);
        }
        String v6_2 = ((Rt.l) p6).h;
        if (v6_2 != null) {
            p5.bindString(8, v6_2);
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        int v0_3 = ((Rt.l) p5).a;
        if (v0_3 != 0) {
            p6.z(1, v0_3.longValue());
        }
        p6.F(2, ((Rt.l) p5).b);
        int v0_0 = ((Rt.l) p5).c;
        if (v0_0 != 0) {
            p6.F(3, v0_0);
        }
        int v0_1 = ((Rt.l) p5).d;
        if (v0_1 != 0) {
            p6.F(4, v0_1);
        }
        int v0_2 = ((Rt.l) p5).e;
        if (v0_2 != 0) {
            p6.F(5, v0_2);
        }
        int v0_4 = ((Rt.l) p5).f;
        if (v0_4 != 0) {
            p6.F(6, v0_4);
        }
        int v0_5 = ((Rt.l) p5).g;
        if (v0_5 != 0) {
            p6.F(7, v0_5);
        }
        String v5_2 = ((Rt.l) p5).h;
        if (v5_2 != null) {
            p6.F(8, v5_2);
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        int v1_0;
        Rt.l v0_1 = new Rt.l();
        String v3 = 0;
        if (!p5.isNull(0)) {
            v1_0 = Long.valueOf(p5.getLong(0));
        } else {
            v1_0 = 0;
        }
        int v1_5;
        v0_1.a = v1_0;
        v0_1.b = p5.getString(1);
        if (!p5.isNull(2)) {
            v1_5 = p5.getString(2);
        } else {
            v1_5 = 0;
        }
        int v1_7;
        v0_1.c = v1_5;
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
        v0_1.g = v1_13;
        if (!p5.isNull(7)) {
            v3 = p5.getString(7);
        }
        v0_1.h = v3;
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
        ((Rt.l) p4).a = Long.valueOf(p2);
        return Long.valueOf(p2);
    }
}
