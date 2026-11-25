package vn.com.vng.zalopay.data.cache.global;
public class GoogleAnalyticsDao extends sj.a {
    public static final String TABLENAME = "GOOGLE_ANALYTICS";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        int v0_2 = ((Qt.e) p6).a;
        if (v0_2 != 0) {
            p5.bindLong(1, v0_2.longValue());
        }
        p5.bindString(2, ((Qt.e) p6).b);
        p5.bindString(3, ((Qt.e) p6).c);
        p5.bindLong(4, ((Qt.e) p6).d);
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        int v0_2 = ((Qt.e) p5).a;
        if (v0_2 != 0) {
            p6.z(1, v0_2.longValue());
        }
        p6.F(2, ((Qt.e) p5).b);
        p6.F(3, ((Qt.e) p5).c);
        p6.z(4, ((Qt.e) p5).d);
        return;
    }

    public final Object p(android.database.Cursor p4)
    {
        long v1_0;
        Qt.e v0_1 = new Qt.e();
        if (!p4.isNull(0)) {
            v1_0 = Long.valueOf(p4.getLong(0));
        } else {
            v1_0 = 0;
        }
        v0_1.a = v1_0;
        v0_1.b = p4.getString(1);
        v0_1.c = p4.getString(2);
        v0_1.d = p4.getLong(3);
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
        ((Qt.e) p4).a = Long.valueOf(p2);
        return Long.valueOf(p2);
    }
}
