package vn.com.vng.zalopay.data.cache.model;
public class HomeBadgeGDDao extends sj.a {
    public static final String TABLENAME = "HOME_BADGE_GD";

    public final void b(android.database.sqlite.SQLiteStatement p4, Object p5)
    {
        p4.clearBindings();
        p4.bindLong(1, ((Rt.d) p5).a);
        p4.bindLong(2, ((Rt.d) p5).b);
        p4.bindString(3, ((Rt.d) p5).c);
        p4.bindString(4, ((Rt.d) p5).d);
        p4.bindString(5, ((Rt.d) p5).e);
        return;
    }

    public final void c(Object p4, xj.b p5)
    {
        p5.A();
        p5.z(1, ((Rt.d) p4).a);
        p5.z(2, ((Rt.d) p4).b);
        p5.F(3, ((Rt.d) p4).c);
        p5.F(4, ((Rt.d) p4).d);
        p5.F(5, ((Rt.d) p4).e);
        return;
    }

    public final Object p(android.database.Cursor p4)
    {
        Rt.d v0_1 = new Rt.d();
        v0_1.a = p4.getLong(0);
        v0_1.b = p4.getLong(1);
        v0_1.c = p4.getString(2);
        v0_1.d = p4.getString(3);
        v0_1.e = p4.getString(4);
        return v0_1;
    }

    public final Object q(android.database.Cursor p3)
    {
        return Long.valueOf(p3.getLong(0));
    }

    public final Object r(long p1, Object p3)
    {
        ((Rt.d) p3).a = p1;
        return Long.valueOf(p1);
    }
}
