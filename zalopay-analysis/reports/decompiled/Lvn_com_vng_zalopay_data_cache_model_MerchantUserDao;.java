package vn.com.vng.zalopay.data.cache.model;
public class MerchantUserDao extends sj.a {
    public static final String TABLENAME = "MERCHANT_USER";

    public final void b(android.database.sqlite.SQLiteStatement p4, Object p5)
    {
        p4.clearBindings();
        p4.bindLong(1, ((Rt.g) p5).a);
        int v0_5 = ((Rt.g) p5).b;
        if (v0_5 != 0) {
            p4.bindString(2, v0_5);
        }
        int v0_6 = ((Rt.g) p5).c;
        if (v0_6 != 0) {
            p4.bindString(3, v0_6);
        }
        int v0_0 = ((Rt.g) p5).d;
        if (v0_0 != 0) {
            p4.bindString(4, v0_0);
        }
        int v0_1 = ((Rt.g) p5).e;
        if (v0_1 != 0) {
            p4.bindString(5, v0_1);
        }
        int v0_3 = ((Rt.g) p5).f;
        if (v0_3 != 0) {
            p4.bindString(6, v0_3);
        }
        Long v5_2 = ((Rt.g) p5).g;
        if (v5_2 != null) {
            p4.bindLong(7, v5_2.longValue());
        }
        return;
    }

    public final void c(Object p4, xj.b p5)
    {
        p5.A();
        p5.z(1, ((Rt.g) p4).a);
        int v0_5 = ((Rt.g) p4).b;
        if (v0_5 != 0) {
            p5.F(2, v0_5);
        }
        int v0_6 = ((Rt.g) p4).c;
        if (v0_6 != 0) {
            p5.F(3, v0_6);
        }
        int v0_0 = ((Rt.g) p4).d;
        if (v0_0 != 0) {
            p5.F(4, v0_0);
        }
        int v0_1 = ((Rt.g) p4).e;
        if (v0_1 != 0) {
            p5.F(5, v0_1);
        }
        int v0_3 = ((Rt.g) p4).f;
        if (v0_3 != 0) {
            p5.F(6, v0_3);
        }
        Long v4_2 = ((Rt.g) p4).g;
        if (v4_2 != null) {
            p5.z(7, v4_2.longValue());
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        long v1_0;
        Rt.g v0_1 = new Rt.g();
        v0_1.a = p5.getLong(0);
        Long v3 = 0;
        if (!p5.isNull(1)) {
            v1_0 = p5.getString(1);
        } else {
            v1_0 = 0;
        }
        long v1_3;
        v0_1.b = v1_0;
        if (!p5.isNull(2)) {
            v1_3 = p5.getString(2);
        } else {
            v1_3 = 0;
        }
        long v1_5;
        v0_1.c = v1_3;
        if (!p5.isNull(3)) {
            v1_5 = p5.getString(3);
        } else {
            v1_5 = 0;
        }
        long v1_7;
        v0_1.d = v1_5;
        if (!p5.isNull(4)) {
            v1_7 = p5.getString(4);
        } else {
            v1_7 = 0;
        }
        long v1_10;
        v0_1.e = v1_7;
        if (!p5.isNull(5)) {
            v1_10 = p5.getString(5);
        } else {
            v1_10 = 0;
        }
        v0_1.f = v1_10;
        if (!p5.isNull(6)) {
            v3 = Long.valueOf(p5.getLong(6));
        }
        v0_1.g = v3;
        return v0_1;
    }

    public final Object q(android.database.Cursor p3)
    {
        return Long.valueOf(p3.getLong(0));
    }

    public final Object r(long p1, Object p3)
    {
        ((Rt.g) p3).a = p1;
        return Long.valueOf(p1);
    }
}
