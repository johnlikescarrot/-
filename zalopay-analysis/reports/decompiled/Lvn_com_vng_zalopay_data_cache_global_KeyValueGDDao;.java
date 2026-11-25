package vn.com.vng.zalopay.data.cache.global;
public class KeyValueGDDao extends sj.a {
    public static final String TABLENAME = "KEY_VALUE_GD";

    public final void b(android.database.sqlite.SQLiteStatement p3, Object p4)
    {
        p3.clearBindings();
        p3.bindString(1, ((Qt.f) p4).a);
        String v4_2 = ((Qt.f) p4).b;
        if (v4_2 != null) {
            p3.bindString(2, v4_2);
        }
        return;
    }

    public final void c(Object p3, xj.b p4)
    {
        p4.A();
        p4.F(1, ((Qt.f) p3).a);
        String v3_2 = ((Qt.f) p3).b;
        if (v3_2 != null) {
            p4.F(2, v3_2);
        }
        return;
    }

    public final Object p(android.database.Cursor p4)
    {
        String v4_1;
        Qt.f v0_1 = new Qt.f();
        v0_1.a = p4.getString(0);
        if (!p4.isNull(1)) {
            v4_1 = p4.getString(1);
        } else {
            v4_1 = 0;
        }
        v0_1.b = v4_1;
        return v0_1;
    }

    public final Object q(android.database.Cursor p2)
    {
        return p2.getString(0);
    }

    public final Object r(long p1, Object p3)
    {
        return ((Qt.f) p3).a;
    }
}
