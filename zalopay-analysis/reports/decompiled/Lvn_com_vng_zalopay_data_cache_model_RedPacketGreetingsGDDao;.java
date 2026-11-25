package vn.com.vng.zalopay.data.cache.model;
public class RedPacketGreetingsGDDao extends sj.a {
    public static final String TABLENAME = "RED_PACKET_GREETINGS_GD";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        int v0_4 = ((Rt.k) p6).a;
        if (v0_4 != 0) {
            p5.bindLong(1, v0_4.longValue());
        }
        int v0_14 = ((Rt.k) p6).b;
        if (v0_14 != 0) {
            p5.bindLong(2, ((long) v0_14.intValue()));
        }
        int v0_2 = ((Rt.k) p6).c;
        if (v0_2 != 0) {
            p5.bindString(3, v0_2);
        }
        int v0_3 = ((Rt.k) p6).d;
        if (v0_3 != 0) {
            p5.bindString(4, v0_3);
        }
        int v0_5 = ((Rt.k) p6).e;
        if (v0_5 != 0) {
            p5.bindString(5, v0_5);
        }
        int v0_6 = ((Rt.k) p6).f;
        if (v0_6 != 0) {
            p5.bindString(6, v0_6);
        }
        int v0_7 = ((Rt.k) p6).g;
        if (v0_7 != 0) {
            p5.bindLong(7, ((long) v0_7.intValue()));
        }
        int v0_10 = ((Rt.k) p6).h;
        if (v0_10 != 0) {
            int v0_12;
            if (!v0_10.booleanValue()) {
                v0_12 = 0;
            } else {
                v0_12 = 1;
            }
            p5.bindLong(8, v0_12);
        }
        Long v6_2 = ((Rt.k) p6).i;
        if (v6_2 != null) {
            p5.bindLong(9, v6_2.longValue());
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        int v0_4 = ((Rt.k) p5).a;
        if (v0_4 != 0) {
            p6.z(1, v0_4.longValue());
        }
        int v0_14 = ((Rt.k) p5).b;
        if (v0_14 != 0) {
            p6.z(2, ((long) v0_14.intValue()));
        }
        int v0_2 = ((Rt.k) p5).c;
        if (v0_2 != 0) {
            p6.F(3, v0_2);
        }
        int v0_3 = ((Rt.k) p5).d;
        if (v0_3 != 0) {
            p6.F(4, v0_3);
        }
        int v0_5 = ((Rt.k) p5).e;
        if (v0_5 != 0) {
            p6.F(5, v0_5);
        }
        int v0_6 = ((Rt.k) p5).f;
        if (v0_6 != 0) {
            p6.F(6, v0_6);
        }
        int v0_7 = ((Rt.k) p5).g;
        if (v0_7 != 0) {
            p6.z(7, ((long) v0_7.intValue()));
        }
        int v0_10 = ((Rt.k) p5).h;
        if (v0_10 != 0) {
            int v0_12;
            if (!v0_10.booleanValue()) {
                v0_12 = 0;
            } else {
                v0_12 = 1;
            }
            p6.z(8, v0_12);
        }
        Long v5_2 = ((Rt.k) p5).i;
        if (v5_2 != null) {
            p6.z(9, v5_2.longValue());
        }
        return;
    }

    public final Object p(android.database.Cursor p7)
    {
        boolean v2_0;
        Rt.k v0_1 = new Rt.k();
        long v1_0 = 0;
        Long v3 = 0;
        if (!p7.isNull(0)) {
            v2_0 = Long.valueOf(p7.getLong(0));
        } else {
            v2_0 = 0;
        }
        short v4_2;
        v0_1.a = v2_0;
        if (!p7.isNull(1)) {
            v4_2 = Integer.valueOf(p7.getInt(1));
        } else {
            v4_2 = 0;
        }
        short v4_4;
        v0_1.b = v4_2;
        if (!p7.isNull(2)) {
            v4_4 = p7.getString(2);
        } else {
            v4_4 = 0;
        }
        short v4_6;
        v0_1.c = v4_4;
        if (!p7.isNull(3)) {
            v4_6 = p7.getString(3);
        } else {
            v4_6 = 0;
        }
        short v4_8;
        v0_1.d = v4_6;
        if (!p7.isNull(4)) {
            v4_8 = p7.getString(4);
        } else {
            v4_8 = 0;
        }
        short v4_10;
        v0_1.e = v4_8;
        if (!p7.isNull(5)) {
            v4_10 = p7.getString(5);
        } else {
            v4_10 = 0;
        }
        short v4_13;
        v0_1.f = v4_10;
        if (!p7.isNull(6)) {
            v4_13 = Integer.valueOf(p7.getInt(6));
        } else {
            v4_13 = 0;
        }
        long v1_1;
        v0_1.g = v4_13;
        if (!p7.isNull(7)) {
            if (p7.getShort(7) != 0) {
                v1_0 = 1;
            }
            v1_1 = Boolean.valueOf(v1_0);
        } else {
            v1_1 = 0;
        }
        v0_1.h = v1_1;
        if (!p7.isNull(8)) {
            v3 = Long.valueOf(p7.getLong(8));
        }
        v0_1.i = v3;
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
        ((Rt.k) p4).a = Long.valueOf(p2);
        return Long.valueOf(p2);
    }
}
