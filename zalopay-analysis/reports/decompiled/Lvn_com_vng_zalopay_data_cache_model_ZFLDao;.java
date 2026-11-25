package vn.com.vng.zalopay.data.cache.model;
public class ZFLDao extends sj.a {
    public static final String TABLENAME;

    static ZFLDao()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.data.cache.model.ZFLDao, 23115, 23115);
        return;
    }

    public final void b(android.database.sqlite.SQLiteStatement p4, Object p5)
    {
        p4.clearBindings();
        p4.bindLong(1, ((Rt.o) p5).a);
        long v0_7 = ((Rt.o) p5).b;
        if (v0_7 != 0) {
            p4.bindString(2, v0_7);
        }
        long v0_8 = ((Rt.o) p5).c;
        if (v0_8 != 0) {
            p4.bindString(3, v0_8);
        }
        long v0_0 = ((Rt.o) p5).d;
        if (v0_0 != 0) {
            p4.bindString(4, v0_0);
        }
        long v0_1 = ((Rt.o) p5).e;
        if (v0_1 != 0) {
            p4.bindString(5, v0_1);
        }
        long v0_3 = ((Rt.o) p5).f;
        if (v0_3 != 0) {
            p4.bindString(6, v0_3);
        }
        long v0_4 = ((Rt.o) p5).g;
        if (v0_4 != 0) {
            p4.bindString(7, v0_4);
        }
        long v0_6;
        p4.bindLong(8, ((Rt.o) p5).h);
        if (!((Rt.o) p5).i) {
            v0_6 = 0;
        } else {
            v0_6 = 1;
        }
        p4.bindLong(9, v0_6);
        return;
    }

    public final void c(Object p4, xj.b p5)
    {
        p5.A();
        p5.z(1, ((Rt.o) p4).a);
        long v0_7 = ((Rt.o) p4).b;
        if (v0_7 != 0) {
            p5.F(2, v0_7);
        }
        long v0_8 = ((Rt.o) p4).c;
        if (v0_8 != 0) {
            p5.F(3, v0_8);
        }
        long v0_0 = ((Rt.o) p4).d;
        if (v0_0 != 0) {
            p5.F(4, v0_0);
        }
        long v0_1 = ((Rt.o) p4).e;
        if (v0_1 != 0) {
            p5.F(5, v0_1);
        }
        long v0_3 = ((Rt.o) p4).f;
        if (v0_3 != 0) {
            p5.F(6, v0_3);
        }
        long v0_4 = ((Rt.o) p4).g;
        if (v0_4 != 0) {
            p5.F(7, v0_4);
        }
        long v0_6;
        p5.z(8, ((Rt.o) p4).h);
        if (!((Rt.o) p4).i) {
            v0_6 = 0;
        } else {
            v0_6 = 1;
        }
        p5.z(9, v0_6);
        return;
    }

    public final Object p(android.database.Cursor p7)
    {
        int v3_0;
        Rt.o v0_1 = new Rt.o();
        int v1 = 0;
        v0_1.a = p7.getLong(0);
        String v4 = 0;
        if (!p7.isNull(1)) {
            v3_0 = p7.getString(1);
        } else {
            v3_0 = 0;
        }
        int v3_2;
        v0_1.b = v3_0;
        if (!p7.isNull(2)) {
            v3_2 = p7.getString(2);
        } else {
            v3_2 = 0;
        }
        int v3_4;
        v0_1.c = v3_2;
        if (!p7.isNull(3)) {
            v3_4 = p7.getString(3);
        } else {
            v3_4 = 0;
        }
        int v3_6;
        v0_1.d = v3_4;
        if (!p7.isNull(4)) {
            v3_6 = p7.getString(4);
        } else {
            v3_6 = 0;
        }
        int v3_8;
        v0_1.e = v3_6;
        if (!p7.isNull(5)) {
            v3_8 = p7.getString(5);
        } else {
            v3_8 = 0;
        }
        v0_1.f = v3_8;
        if (!p7.isNull(6)) {
            v4 = p7.getString(6);
        }
        v0_1.g = v4;
        v0_1.h = p7.getLong(7);
        if (p7.getShort(8) != 0) {
            v1 = 1;
        }
        v0_1.i = v1;
        return v0_1;
    }

    public final Object q(android.database.Cursor p3)
    {
        return Long.valueOf(p3.getLong(0));
    }

    public final Object r(long p1, Object p3)
    {
        ((Rt.o) p3).a = p1;
        return Long.valueOf(p1);
    }
}
