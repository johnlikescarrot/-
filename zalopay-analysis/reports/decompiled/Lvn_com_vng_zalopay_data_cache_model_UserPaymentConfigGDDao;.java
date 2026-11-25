package vn.com.vng.zalopay.data.cache.model;
public class UserPaymentConfigGDDao extends sj.a {
    public static final String TABLENAME = "USER_PAYMENT_CONFIG_GD";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        long v0_4 = ((Rt.n) p6).a;
        if (v0_4 != 0) {
            p5.bindLong(1, v0_4.longValue());
        }
        long v0_21 = ((Rt.n) p6).b;
        if (v0_21 != 0) {
            p5.bindLong(2, v0_21.longValue());
        }
        long v0_1 = ((Rt.n) p6).c;
        if (v0_1 != 0) {
            p5.bindLong(3, ((long) v0_1.intValue()));
        }
        long v0_5 = ((Rt.n) p6).d;
        if (v0_5 != 0) {
            p5.bindString(4, v0_5);
        }
        long v0_6 = ((Rt.n) p6).e;
        if (v0_6 != 0) {
            p5.bindString(5, v0_6);
        }
        long v0_7 = ((Rt.n) p6).f;
        if (v0_7 != 0) {
            p5.bindString(6, v0_7);
        }
        long v0_8 = ((Rt.n) p6).g;
        if (v0_8 != 0) {
            p5.bindLong(7, ((long) v0_8.intValue()));
        }
        long v0_11 = ((Rt.n) p6).h;
        if (v0_11 != 0) {
            p5.bindString(8, v0_11);
        }
        long v0_12 = ((Rt.n) p6).i;
        if (v0_12 != 0) {
            p5.bindLong(9, ((long) v0_12.intValue()));
        }
        long v0_15 = ((Rt.n) p6).j;
        if (v0_15 != 0) {
            p5.bindLong(10, v0_15.longValue());
        }
        long v0_16 = ((Rt.n) p6).k;
        if (v0_16 != 0) {
            p5.bindLong(11, v0_16.longValue());
        }
        long v0_17 = ((Rt.n) p6).l;
        if (v0_17 != 0) {
            p5.bindDouble(12, v0_17.doubleValue());
        }
        long v0_18 = ((Rt.n) p6).m;
        if (v0_18 != 0) {
            p5.bindDouble(13, v0_18.doubleValue());
        }
        long v0_19 = ((Rt.n) p6).n;
        if (v0_19 != 0) {
            p5.bindString(14, v0_19);
        }
        long v0_20 = ((Rt.n) p6).o;
        if (v0_20 != 0) {
            p5.bindLong(15, v0_20.longValue());
        }
        long v0_22 = ((Rt.n) p6).p;
        if (v0_22 != 0) {
            p5.bindLong(16, ((long) v0_22.intValue()));
        }
        long v0_25 = ((Rt.n) p6).q;
        if (v0_25 != 0) {
            p5.bindLong(17, ((long) v0_25.intValue()));
        }
        int v6_4 = ((Rt.n) p6).r;
        if (v6_4 != 0) {
            p5.bindLong(18, ((long) v6_4.intValue()));
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        long v0_4 = ((Rt.n) p5).a;
        if (v0_4 != 0) {
            p6.z(1, v0_4.longValue());
        }
        long v0_21 = ((Rt.n) p5).b;
        if (v0_21 != 0) {
            p6.z(2, v0_21.longValue());
        }
        long v0_1 = ((Rt.n) p5).c;
        if (v0_1 != 0) {
            p6.z(3, ((long) v0_1.intValue()));
        }
        long v0_5 = ((Rt.n) p5).d;
        if (v0_5 != 0) {
            p6.F(4, v0_5);
        }
        long v0_6 = ((Rt.n) p5).e;
        if (v0_6 != 0) {
            p6.F(5, v0_6);
        }
        long v0_7 = ((Rt.n) p5).f;
        if (v0_7 != 0) {
            p6.F(6, v0_7);
        }
        long v0_8 = ((Rt.n) p5).g;
        if (v0_8 != 0) {
            p6.z(7, ((long) v0_8.intValue()));
        }
        long v0_11 = ((Rt.n) p5).h;
        if (v0_11 != 0) {
            p6.F(8, v0_11);
        }
        long v0_12 = ((Rt.n) p5).i;
        if (v0_12 != 0) {
            p6.z(9, ((long) v0_12.intValue()));
        }
        long v0_15 = ((Rt.n) p5).j;
        if (v0_15 != 0) {
            p6.z(10, v0_15.longValue());
        }
        long v0_16 = ((Rt.n) p5).k;
        if (v0_16 != 0) {
            p6.z(11, v0_16.longValue());
        }
        long v0_17 = ((Rt.n) p5).l;
        if (v0_17 != 0) {
            p6.W(v0_17.doubleValue(), 12);
        }
        long v0_18 = ((Rt.n) p5).m;
        if (v0_18 != 0) {
            p6.W(v0_18.doubleValue(), 13);
        }
        long v0_19 = ((Rt.n) p5).n;
        if (v0_19 != 0) {
            p6.F(14, v0_19);
        }
        long v0_20 = ((Rt.n) p5).o;
        if (v0_20 != 0) {
            p6.z(15, v0_20.longValue());
        }
        long v0_22 = ((Rt.n) p5).p;
        if (v0_22 != 0) {
            p6.z(16, ((long) v0_22.intValue()));
        }
        long v0_25 = ((Rt.n) p5).q;
        if (v0_25 != 0) {
            p6.z(17, ((long) v0_25.intValue()));
        }
        int v5_4 = ((Rt.n) p5).r;
        if (v5_4 != 0) {
            p6.z(18, ((long) v5_4.intValue()));
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        int v1_0;
        Rt.n v0_1 = new Rt.n();
        Integer v3 = 0;
        if (!p5.isNull(0)) {
            v1_0 = Long.valueOf(p5.getLong(0));
        } else {
            v1_0 = 0;
        }
        int v1_24;
        v0_1.a = v1_0;
        if (!p5.isNull(1)) {
            v1_24 = Long.valueOf(p5.getLong(1));
        } else {
            v1_24 = 0;
        }
        int v1_27;
        v0_1.b = v1_24;
        if (!p5.isNull(2)) {
            v1_27 = Integer.valueOf(p5.getInt(2));
        } else {
            v1_27 = 0;
        }
        int v1_29;
        v0_1.c = v1_27;
        if (!p5.isNull(3)) {
            v1_29 = p5.getString(3);
        } else {
            v1_29 = 0;
        }
        int v1_31;
        v0_1.d = v1_29;
        if (!p5.isNull(4)) {
            v1_31 = p5.getString(4);
        } else {
            v1_31 = 0;
        }
        int v1_33;
        v0_1.e = v1_31;
        if (!p5.isNull(5)) {
            v1_33 = p5.getString(5);
        } else {
            v1_33 = 0;
        }
        int v1_36;
        v0_1.f = v1_33;
        if (!p5.isNull(6)) {
            v1_36 = Integer.valueOf(p5.getInt(6));
        } else {
            v1_36 = 0;
        }
        int v1_38;
        v0_1.g = v1_36;
        if (!p5.isNull(7)) {
            v1_38 = p5.getString(7);
        } else {
            v1_38 = 0;
        }
        int v1_42;
        v0_1.h = v1_38;
        if (!p5.isNull(8)) {
            v1_42 = Integer.valueOf(p5.getInt(8));
        } else {
            v1_42 = 0;
        }
        int v1_45;
        v0_1.i = v1_42;
        if (!p5.isNull(9)) {
            v1_45 = Long.valueOf(p5.getLong(9));
        } else {
            v1_45 = 0;
        }
        int v1_2;
        v0_1.j = v1_45;
        if (!p5.isNull(10)) {
            v1_2 = Long.valueOf(p5.getLong(10));
        } else {
            v1_2 = 0;
        }
        int v1_5;
        v0_1.k = v1_2;
        if (!p5.isNull(11)) {
            v1_5 = Double.valueOf(p5.getDouble(11));
        } else {
            v1_5 = 0;
        }
        int v1_8;
        v0_1.l = v1_5;
        if (!p5.isNull(12)) {
            v1_8 = Double.valueOf(p5.getDouble(12));
        } else {
            v1_8 = 0;
        }
        int v1_11;
        v0_1.m = v1_8;
        if (!p5.isNull(13)) {
            v1_11 = p5.getString(13);
        } else {
            v1_11 = 0;
        }
        int v1_14;
        v0_1.n = v1_11;
        if (!p5.isNull(14)) {
            v1_14 = Long.valueOf(p5.getLong(14));
        } else {
            v1_14 = 0;
        }
        int v1_17;
        v0_1.o = v1_14;
        if (!p5.isNull(15)) {
            v1_17 = Integer.valueOf(p5.getInt(15));
        } else {
            v1_17 = 0;
        }
        int v1_20;
        v0_1.p = v1_17;
        if (!p5.isNull(16)) {
            v1_20 = Integer.valueOf(p5.getInt(16));
        } else {
            v1_20 = 0;
        }
        v0_1.q = v1_20;
        if (!p5.isNull(17)) {
            v3 = Integer.valueOf(p5.getInt(17));
        }
        v0_1.r = v3;
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
        ((Rt.n) p4).a = Long.valueOf(p2);
        return Long.valueOf(p2);
    }
}
