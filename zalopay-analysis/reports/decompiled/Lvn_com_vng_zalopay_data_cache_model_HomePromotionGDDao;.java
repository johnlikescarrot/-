package vn.com.vng.zalopay.data.cache.model;
public class HomePromotionGDDao extends sj.a {
    public static final String TABLENAME;

    static HomePromotionGDDao()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.data.cache.model.HomePromotionGDDao, 20301, 20301);
        return;
    }

    public final void b(android.database.sqlite.SQLiteStatement p4, Object p5)
    {
        p4.clearBindings();
        p4.bindLong(1, ((Rt.e) p5).a);
        p4.bindString(2, ((Rt.e) p5).b);
        p4.bindString(3, ((Rt.e) p5).c);
        p4.bindString(4, ((Rt.e) p5).d);
        p4.bindString(5, ((Rt.e) p5).e);
        p4.bindLong(6, ((long) ((Rt.e) p5).f));
        return;
    }

    public final void c(Object p4, xj.b p5)
    {
        p5.A();
        p5.z(1, ((Rt.e) p4).a);
        p5.F(2, ((Rt.e) p4).b);
        p5.F(3, ((Rt.e) p4).c);
        p5.F(4, ((Rt.e) p4).d);
        p5.F(5, ((Rt.e) p4).e);
        p5.z(6, ((long) ((Rt.e) p4).f));
        return;
    }

    public final Object p(android.database.Cursor p4)
    {
        Rt.e v0_1 = new Rt.e();
        v0_1.a = p4.getLong(0);
        v0_1.b = p4.getString(1);
        v0_1.c = p4.getString(2);
        v0_1.d = p4.getString(3);
        v0_1.e = p4.getString(4);
        v0_1.f = p4.getInt(5);
        return v0_1;
    }

    public final Object q(android.database.Cursor p3)
    {
        return Long.valueOf(p3.getLong(0));
    }

    public final Object r(long p1, Object p3)
    {
        ((Rt.e) p3).a = p1;
        return Long.valueOf(p1);
    }
}
