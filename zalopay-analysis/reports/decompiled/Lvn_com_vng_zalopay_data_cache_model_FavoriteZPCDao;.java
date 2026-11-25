package vn.com.vng.zalopay.data.cache.model;
public class FavoriteZPCDao extends sj.a {
    public static final String TABLENAME = "FAVORITE_ZPC";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        int v0_3 = ((Rt.c) p6).a;
        if (v0_3 != 0) {
            p5.bindString(1, v0_3);
        }
        p5.bindLong(2, ((Rt.c) p6).b);
        p5.bindLong(3, ((Rt.c) p6).c);
        p5.bindLong(4, ((Rt.c) p6).d);
        p5.bindLong(5, ((Rt.c) p6).e);
        int v0_2 = ((Rt.c) p6).f;
        if (v0_2 != 0) {
            p5.bindString(6, v0_2);
        }
        int v0_4 = ((Rt.c) p6).g;
        if (v0_4 != 0) {
            p5.bindString(7, v0_4);
        }
        int v0_5 = ((Rt.c) p6).h;
        if (v0_5 != 0) {
            p5.bindString(8, v0_5);
        }
        int v0_6 = ((Rt.c) p6).i;
        if (v0_6 != 0) {
            p5.bindLong(9, v0_6.longValue());
        }
        int v0_7 = ((Rt.c) p6).j;
        if (v0_7 != 0) {
            p5.bindString(10, v0_7);
        }
        p5.bindLong(11, ((Rt.c) p6).k);
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        int v0_3 = ((Rt.c) p5).a;
        if (v0_3 != 0) {
            p6.F(1, v0_3);
        }
        p6.z(2, ((Rt.c) p5).b);
        p6.z(3, ((Rt.c) p5).c);
        p6.z(4, ((Rt.c) p5).d);
        p6.z(5, ((Rt.c) p5).e);
        int v0_2 = ((Rt.c) p5).f;
        if (v0_2 != 0) {
            p6.F(6, v0_2);
        }
        int v0_4 = ((Rt.c) p5).g;
        if (v0_4 != 0) {
            p6.F(7, v0_4);
        }
        int v0_5 = ((Rt.c) p5).h;
        if (v0_5 != 0) {
            p6.F(8, v0_5);
        }
        int v0_6 = ((Rt.c) p5).i;
        if (v0_6 != 0) {
            p6.z(9, v0_6.longValue());
        }
        int v0_7 = ((Rt.c) p5).j;
        if (v0_7 != 0) {
            p6.F(10, v0_7);
        }
        p6.z(11, ((Rt.c) p5).k);
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        long v1_21;
        Rt.c v0_1 = new Rt.c();
        String v3 = 0;
        if (!p5.isNull(0)) {
            v1_21 = p5.getString(0);
        } else {
            v1_21 = 0;
        }
        long v1_10;
        v0_1.a = v1_21;
        v0_1.b = p5.getLong(1);
        v0_1.c = p5.getLong(2);
        v0_1.d = p5.getLong(3);
        v0_1.e = p5.getLong(4);
        if (!p5.isNull(5)) {
            v1_10 = p5.getString(5);
        } else {
            v1_10 = 0;
        }
        long v1_12;
        v0_1.f = v1_10;
        if (!p5.isNull(6)) {
            v1_12 = p5.getString(6);
        } else {
            v1_12 = 0;
        }
        long v1_14;
        v0_1.g = v1_12;
        if (!p5.isNull(7)) {
            v1_14 = p5.getString(7);
        } else {
            v1_14 = 0;
        }
        long v1_17;
        v0_1.h = v1_14;
        if (!p5.isNull(8)) {
            v1_17 = Long.valueOf(p5.getLong(8));
        } else {
            v1_17 = 0;
        }
        v0_1.i = v1_17;
        if (!p5.isNull(9)) {
            v3 = p5.getString(9);
        }
        v0_1.j = v3;
        v0_1.k = p5.getLong(10);
        return v0_1;
    }

    public final bridge synthetic Object q(android.database.Cursor p1)
    {
        return 0;
    }

    public final bridge synthetic Object r(long p1, Object p3)
    {
        return 0;
    }
}
