package vn.com.vng.zalopay.data.cache.global;
public class ApptransidLogGDDao extends sj.a {
    public static final String TABLENAME = "APPTRANSID_LOG_GD";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        p5.bindString(1, ((Qt.b) p6).a);
        int v0_20 = ((Qt.b) p6).b;
        if (v0_20 != 0) {
            p5.bindLong(2, v0_20.longValue());
        }
        int v0_2 = ((Qt.b) p6).c;
        if (v0_2 != 0) {
            p5.bindLong(3, ((long) v0_2.intValue()));
        }
        int v0_6 = ((Qt.b) p6).d;
        if (v0_6 != 0) {
            p5.bindLong(4, ((long) v0_6.intValue()));
        }
        int v0_10 = ((Qt.b) p6).e;
        if (v0_10 != 0) {
            p5.bindLong(5, ((long) v0_10.intValue()));
        }
        int v0_13 = ((Qt.b) p6).f;
        if (v0_13 != 0) {
            p5.bindLong(6, ((long) v0_13.intValue()));
        }
        int v0_16 = ((Qt.b) p6).g;
        if (v0_16 != 0) {
            p5.bindLong(7, v0_16.longValue());
        }
        int v0_17 = ((Qt.b) p6).h;
        if (v0_17 != 0) {
            p5.bindLong(8, ((long) v0_17.intValue()));
        }
        int v0_21 = ((Qt.b) p6).i;
        if (v0_21 != 0) {
            p5.bindLong(9, ((long) v0_21.intValue()));
        }
        int v0_24 = ((Qt.b) p6).j;
        if (v0_24 != 0) {
            p5.bindLong(10, ((long) v0_24.intValue()));
        }
        int v0_27 = ((Qt.b) p6).k;
        if (v0_27 != 0) {
            p5.bindLong(11, v0_27.longValue());
        }
        int v0_28 = ((Qt.b) p6).l;
        if (v0_28 != 0) {
            p5.bindLong(12, v0_28.longValue());
        }
        int v0_29 = ((Qt.b) p6).m;
        if (v0_29 != 0) {
            p5.bindString(13, v0_29);
        }
        int v0_30 = ((Qt.b) p6).n;
        if (v0_30 != 0) {
            p5.bindLong(14, ((long) v0_30.intValue()));
        }
        int v0_33 = ((Qt.b) p6).o;
        if (v0_33 != 0) {
            p5.bindString(15, v0_33);
        }
        int v0_34 = ((Qt.b) p6).p;
        if (v0_34 != 0) {
            p5.bindLong(16, v0_34.longValue());
        }
        int v0_35 = ((Qt.b) p6).q;
        if (v0_35 != 0) {
            p5.bindString(17, v0_35);
        }
        int v0_0 = ((Qt.b) p6).r;
        if (v0_0 != 0) {
            p5.bindString(18, v0_0);
        }
        int v0_1 = ((Qt.b) p6).s;
        if (v0_1 != 0) {
            p5.bindString(19, v0_1);
        }
        String v6_2 = ((Qt.b) p6).t;
        if (v6_2 != null) {
            p5.bindString(20, v6_2);
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        p6.F(1, ((Qt.b) p5).a);
        int v0_20 = ((Qt.b) p5).b;
        if (v0_20 != 0) {
            p6.z(2, v0_20.longValue());
        }
        int v0_2 = ((Qt.b) p5).c;
        if (v0_2 != 0) {
            p6.z(3, ((long) v0_2.intValue()));
        }
        int v0_6 = ((Qt.b) p5).d;
        if (v0_6 != 0) {
            p6.z(4, ((long) v0_6.intValue()));
        }
        int v0_10 = ((Qt.b) p5).e;
        if (v0_10 != 0) {
            p6.z(5, ((long) v0_10.intValue()));
        }
        int v0_13 = ((Qt.b) p5).f;
        if (v0_13 != 0) {
            p6.z(6, ((long) v0_13.intValue()));
        }
        int v0_16 = ((Qt.b) p5).g;
        if (v0_16 != 0) {
            p6.z(7, v0_16.longValue());
        }
        int v0_17 = ((Qt.b) p5).h;
        if (v0_17 != 0) {
            p6.z(8, ((long) v0_17.intValue()));
        }
        int v0_21 = ((Qt.b) p5).i;
        if (v0_21 != 0) {
            p6.z(9, ((long) v0_21.intValue()));
        }
        int v0_24 = ((Qt.b) p5).j;
        if (v0_24 != 0) {
            p6.z(10, ((long) v0_24.intValue()));
        }
        int v0_27 = ((Qt.b) p5).k;
        if (v0_27 != 0) {
            p6.z(11, v0_27.longValue());
        }
        int v0_28 = ((Qt.b) p5).l;
        if (v0_28 != 0) {
            p6.z(12, v0_28.longValue());
        }
        int v0_29 = ((Qt.b) p5).m;
        if (v0_29 != 0) {
            p6.F(13, v0_29);
        }
        int v0_30 = ((Qt.b) p5).n;
        if (v0_30 != 0) {
            p6.z(14, ((long) v0_30.intValue()));
        }
        int v0_33 = ((Qt.b) p5).o;
        if (v0_33 != 0) {
            p6.F(15, v0_33);
        }
        int v0_34 = ((Qt.b) p5).p;
        if (v0_34 != 0) {
            p6.z(16, v0_34.longValue());
        }
        int v0_35 = ((Qt.b) p5).q;
        if (v0_35 != 0) {
            p6.F(17, v0_35);
        }
        int v0_0 = ((Qt.b) p5).r;
        if (v0_0 != 0) {
            p6.F(18, v0_0);
        }
        int v0_1 = ((Qt.b) p5).s;
        if (v0_1 != 0) {
            p6.F(19, v0_1);
        }
        String v5_2 = ((Qt.b) p5).t;
        if (v5_2 != null) {
            p6.F(20, v5_2);
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        int v1_12;
        Qt.b v0_1 = new Qt.b();
        v0_1.a = p5.getString(0);
        String v3 = 0;
        if (!p5.isNull(1)) {
            v1_12 = Long.valueOf(p5.getLong(1));
        } else {
            v1_12 = 0;
        }
        int v1_27;
        v0_1.b = v1_12;
        if (!p5.isNull(2)) {
            v1_27 = Integer.valueOf(p5.getInt(2));
        } else {
            v1_27 = 0;
        }
        int v1_30;
        v0_1.c = v1_27;
        if (!p5.isNull(3)) {
            v1_30 = Integer.valueOf(p5.getInt(3));
        } else {
            v1_30 = 0;
        }
        int v1_34;
        v0_1.d = v1_30;
        if (!p5.isNull(4)) {
            v1_34 = Integer.valueOf(p5.getInt(4));
        } else {
            v1_34 = 0;
        }
        int v1_37;
        v0_1.e = v1_34;
        if (!p5.isNull(5)) {
            v1_37 = Integer.valueOf(p5.getInt(5));
        } else {
            v1_37 = 0;
        }
        int v1_41;
        v0_1.f = v1_37;
        if (!p5.isNull(6)) {
            v1_41 = Long.valueOf(p5.getLong(6));
        } else {
            v1_41 = 0;
        }
        int v1_44;
        v0_1.g = v1_41;
        if (!p5.isNull(7)) {
            v1_44 = Integer.valueOf(p5.getInt(7));
        } else {
            v1_44 = 0;
        }
        int v1_47;
        v0_1.h = v1_44;
        if (!p5.isNull(8)) {
            v1_47 = Integer.valueOf(p5.getInt(8));
        } else {
            v1_47 = 0;
        }
        int v1_50;
        v0_1.i = v1_47;
        if (!p5.isNull(9)) {
            v1_50 = Integer.valueOf(p5.getInt(9));
        } else {
            v1_50 = 0;
        }
        int v1_1;
        v0_1.j = v1_50;
        if (!p5.isNull(10)) {
            v1_1 = Long.valueOf(p5.getLong(10));
        } else {
            v1_1 = 0;
        }
        int v1_4;
        v0_1.k = v1_1;
        if (!p5.isNull(11)) {
            v1_4 = Long.valueOf(p5.getLong(11));
        } else {
            v1_4 = 0;
        }
        int v1_7;
        v0_1.l = v1_4;
        if (!p5.isNull(12)) {
            v1_7 = p5.getString(12);
        } else {
            v1_7 = 0;
        }
        int v1_10;
        v0_1.m = v1_7;
        if (!p5.isNull(13)) {
            v1_10 = Integer.valueOf(p5.getInt(13));
        } else {
            v1_10 = 0;
        }
        int v1_13;
        v0_1.n = v1_10;
        if (!p5.isNull(14)) {
            v1_13 = p5.getString(14);
        } else {
            v1_13 = 0;
        }
        int v1_16;
        v0_1.o = v1_13;
        if (!p5.isNull(15)) {
            v1_16 = Long.valueOf(p5.getLong(15));
        } else {
            v1_16 = 0;
        }
        int v1_18;
        v0_1.p = v1_16;
        if (!p5.isNull(16)) {
            v1_18 = p5.getString(16);
        } else {
            v1_18 = 0;
        }
        int v1_20;
        v0_1.q = v1_18;
        if (!p5.isNull(17)) {
            v1_20 = p5.getString(17);
        } else {
            v1_20 = 0;
        }
        int v1_23;
        v0_1.r = v1_20;
        if (!p5.isNull(18)) {
            v1_23 = p5.getString(18);
        } else {
            v1_23 = 0;
        }
        v0_1.s = v1_23;
        if (!p5.isNull(19)) {
            v3 = p5.getString(19);
        }
        v0_1.t = v3;
        return v0_1;
    }

    public final Object q(android.database.Cursor p2)
    {
        return p2.getString(0);
    }

    public final Object r(long p1, Object p3)
    {
        return ((Qt.b) p3).a;
    }
}
