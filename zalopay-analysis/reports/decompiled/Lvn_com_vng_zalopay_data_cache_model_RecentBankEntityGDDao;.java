package vn.com.vng.zalopay.data.cache.model;
public class RecentBankEntityGDDao extends sj.a {
    public static final String TABLENAME;

    static RecentBankEntityGDDao()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.data.cache.model.RecentBankEntityGDDao, 17084, 17084);
        return;
    }

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        long v0_4 = ((Rt.j) p6).a;
        if (v0_4 != 0) {
            p5.bindString(1, v0_4);
        }
        long v0_7 = ((Rt.j) p6).b;
        if (v0_7 != 0) {
            p5.bindString(2, v0_7);
        }
        long v0_0 = ((Rt.j) p6).c;
        if (v0_0 != 0) {
            p5.bindString(3, v0_0);
        }
        long v0_1 = ((Rt.j) p6).d;
        if (v0_1 != 0) {
            p5.bindLong(4, ((long) v0_1.intValue()));
        }
        long v0_5 = ((Rt.j) p6).e;
        if (v0_5 != 0) {
            p5.bindLong(5, v0_5.longValue());
        }
        int v6_2 = ((Rt.j) p6).f;
        if (v6_2 != 0) {
            p5.bindLong(6, ((long) v6_2.intValue()));
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        long v0_4 = ((Rt.j) p5).a;
        if (v0_4 != 0) {
            p6.F(1, v0_4);
        }
        long v0_7 = ((Rt.j) p5).b;
        if (v0_7 != 0) {
            p6.F(2, v0_7);
        }
        long v0_0 = ((Rt.j) p5).c;
        if (v0_0 != 0) {
            p6.F(3, v0_0);
        }
        long v0_1 = ((Rt.j) p5).d;
        if (v0_1 != 0) {
            p6.z(4, ((long) v0_1.intValue()));
        }
        long v0_5 = ((Rt.j) p5).e;
        if (v0_5 != 0) {
            p6.z(5, v0_5.longValue());
        }
        int v5_2 = ((Rt.j) p5).f;
        if (v5_2 != 0) {
            p6.z(6, ((long) v5_2.intValue()));
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        int v1_12;
        Rt.j v0_1 = new Rt.j();
        Integer v3 = 0;
        if (!p5.isNull(0)) {
            v1_12 = p5.getString(0);
        } else {
            v1_12 = 0;
        }
        int v1_1;
        v0_1.a = v1_12;
        if (!p5.isNull(1)) {
            v1_1 = p5.getString(1);
        } else {
            v1_1 = 0;
        }
        int v1_4;
        v0_1.b = v1_1;
        if (!p5.isNull(2)) {
            v1_4 = p5.getString(2);
        } else {
            v1_4 = 0;
        }
        int v1_7;
        v0_1.c = v1_4;
        if (!p5.isNull(3)) {
            v1_7 = Integer.valueOf(p5.getInt(3));
        } else {
            v1_7 = 0;
        }
        int v1_10;
        v0_1.d = v1_7;
        if (!p5.isNull(4)) {
            v1_10 = Long.valueOf(p5.getLong(4));
        } else {
            v1_10 = 0;
        }
        v0_1.e = v1_10;
        if (!p5.isNull(5)) {
            v3 = Integer.valueOf(p5.getInt(5));
        }
        v0_1.f = v3;
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
