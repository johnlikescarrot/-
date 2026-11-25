package vn.com.vng.zalopay.data.cache.model;
public class NotificationGDDao extends sj.a {
    public static final String TABLENAME = "NOTIFICATION_GD";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        int v0_1 = ((Rt.i) p6).a;
        if (v0_1 != 0) {
            p5.bindLong(1, v0_1.longValue());
        }
        int v0_13 = ((Rt.i) p6).b;
        if (v0_13 != 0) {
            p5.bindLong(2, v0_13.longValue());
        }
        int v0_0 = ((Rt.i) p6).c;
        if (v0_0 != 0) {
            p5.bindLong(3, v0_0.longValue());
        }
        int v0_2 = ((Rt.i) p6).d;
        if (v0_2 != 0) {
            p5.bindLong(4, v0_2.longValue());
        }
        int v0_3 = ((Rt.i) p6).e;
        if (v0_3 != 0) {
            p5.bindString(5, v0_3);
        }
        int v0_4 = ((Rt.i) p6).f;
        if (v0_4 != 0) {
            p5.bindString(6, v0_4);
        }
        int v0_5 = ((Rt.i) p6).g;
        if (v0_5 != 0) {
            p5.bindString(7, v0_5);
        }
        int v0_6 = ((Rt.i) p6).h;
        if (v0_6 != 0) {
            p5.bindLong(8, v0_6.longValue());
        }
        int v0_7 = ((Rt.i) p6).i;
        if (v0_7 != 0) {
            p5.bindLong(9, v0_7.longValue());
        }
        int v0_8 = ((Rt.i) p6).j;
        if (v0_8 != 0) {
            p5.bindLong(10, v0_8.longValue());
        }
        int v0_9 = ((Rt.i) p6).k;
        if (v0_9 != 0) {
            p5.bindLong(11, v0_9.longValue());
        }
        int v0_10 = ((Rt.i) p6).l;
        if (v0_10 != 0) {
            p5.bindLong(12, v0_10.longValue());
        }
        int v0_11 = ((Rt.i) p6).m;
        if (v0_11 != 0) {
            p5.bindString(13, v0_11);
        }
        Long v6_2 = ((Rt.i) p6).n;
        if (v6_2 != null) {
            p5.bindLong(14, v6_2.longValue());
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        int v0_1 = ((Rt.i) p5).a;
        if (v0_1 != 0) {
            p6.z(1, v0_1.longValue());
        }
        int v0_13 = ((Rt.i) p5).b;
        if (v0_13 != 0) {
            p6.z(2, v0_13.longValue());
        }
        int v0_0 = ((Rt.i) p5).c;
        if (v0_0 != 0) {
            p6.z(3, v0_0.longValue());
        }
        int v0_2 = ((Rt.i) p5).d;
        if (v0_2 != 0) {
            p6.z(4, v0_2.longValue());
        }
        int v0_3 = ((Rt.i) p5).e;
        if (v0_3 != 0) {
            p6.F(5, v0_3);
        }
        int v0_4 = ((Rt.i) p5).f;
        if (v0_4 != 0) {
            p6.F(6, v0_4);
        }
        int v0_5 = ((Rt.i) p5).g;
        if (v0_5 != 0) {
            p6.F(7, v0_5);
        }
        int v0_6 = ((Rt.i) p5).h;
        if (v0_6 != 0) {
            p6.z(8, v0_6.longValue());
        }
        int v0_7 = ((Rt.i) p5).i;
        if (v0_7 != 0) {
            p6.z(9, v0_7.longValue());
        }
        int v0_8 = ((Rt.i) p5).j;
        if (v0_8 != 0) {
            p6.z(10, v0_8.longValue());
        }
        int v0_9 = ((Rt.i) p5).k;
        if (v0_9 != 0) {
            p6.z(11, v0_9.longValue());
        }
        int v0_10 = ((Rt.i) p5).l;
        if (v0_10 != 0) {
            p6.z(12, v0_10.longValue());
        }
        int v0_11 = ((Rt.i) p5).m;
        if (v0_11 != 0) {
            p6.F(13, v0_11);
        }
        Long v5_2 = ((Rt.i) p5).n;
        if (v5_2 != null) {
            p6.z(14, v5_2.longValue());
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        long v1_0;
        Rt.i v0_1 = new Rt.i();
        Long v3 = 0;
        if (!p5.isNull(0)) {
            v1_0 = Long.valueOf(p5.getLong(0));
        } else {
            v1_0 = 0;
        }
        long v1_13;
        v0_1.a = v1_0;
        if (!p5.isNull(1)) {
            v1_13 = Long.valueOf(p5.getLong(1));
        } else {
            v1_13 = 0;
        }
        long v1_16;
        v0_1.b = v1_13;
        if (!p5.isNull(2)) {
            v1_16 = Long.valueOf(p5.getLong(2));
        } else {
            v1_16 = 0;
        }
        long v1_19;
        v0_1.c = v1_16;
        if (!p5.isNull(3)) {
            v1_19 = Long.valueOf(p5.getLong(3));
        } else {
            v1_19 = 0;
        }
        long v1_21;
        v0_1.d = v1_19;
        if (!p5.isNull(4)) {
            v1_21 = p5.getString(4);
        } else {
            v1_21 = 0;
        }
        long v1_23;
        v0_1.e = v1_21;
        if (!p5.isNull(5)) {
            v1_23 = p5.getString(5);
        } else {
            v1_23 = 0;
        }
        long v1_25;
        v0_1.f = v1_23;
        if (!p5.isNull(6)) {
            v1_25 = p5.getString(6);
        } else {
            v1_25 = 0;
        }
        long v1_28;
        v0_1.g = v1_25;
        if (!p5.isNull(7)) {
            v1_28 = Long.valueOf(p5.getLong(7));
        } else {
            v1_28 = 0;
        }
        long v1_32;
        v0_1.h = v1_28;
        if (!p5.isNull(8)) {
            v1_32 = Long.valueOf(p5.getLong(8));
        } else {
            v1_32 = 0;
        }
        long v1_35;
        v0_1.i = v1_32;
        if (!p5.isNull(9)) {
            v1_35 = Long.valueOf(p5.getLong(9));
        } else {
            v1_35 = 0;
        }
        long v1_2;
        v0_1.j = v1_35;
        if (!p5.isNull(10)) {
            v1_2 = Long.valueOf(p5.getLong(10));
        } else {
            v1_2 = 0;
        }
        long v1_5;
        v0_1.k = v1_2;
        if (!p5.isNull(11)) {
            v1_5 = Long.valueOf(p5.getLong(11));
        } else {
            v1_5 = 0;
        }
        long v1_7;
        v0_1.l = v1_5;
        if (!p5.isNull(12)) {
            v1_7 = p5.getString(12);
        } else {
            v1_7 = 0;
        }
        v0_1.m = v1_7;
        if (!p5.isNull(13)) {
            v3 = Long.valueOf(p5.getLong(13));
        }
        v0_1.n = v3;
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
        ((Rt.i) p4).a = Long.valueOf(p2);
        return Long.valueOf(p2);
    }
}
