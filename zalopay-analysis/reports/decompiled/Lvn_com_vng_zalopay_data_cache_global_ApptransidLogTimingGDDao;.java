package vn.com.vng.zalopay.data.cache.global;
public class ApptransidLogTimingGDDao extends sj.a {
    public static final String TABLENAME = "APPTRANSID_LOG_TIMING_GD";

    public final void b(android.database.sqlite.SQLiteStatement p4, Object p5)
    {
        p4.clearBindings();
        int v0_3 = ((Qt.c) p5).a;
        if (v0_3 != 0) {
            p4.bindString(1, v0_3);
        }
        int v0_4 = ((Qt.c) p5).b;
        if (v0_4 != 0) {
            p4.bindLong(2, ((long) v0_4.intValue()));
        }
        int v0_1 = ((Qt.c) p5).c;
        if (v0_1 != 0) {
            p4.bindString(3, v0_1);
        }
        p4.bindLong(4, ((Qt.c) p5).d);
        return;
    }

    public final void c(Object p4, xj.b p5)
    {
        p5.A();
        int v0_3 = ((Qt.c) p4).a;
        if (v0_3 != 0) {
            p5.F(1, v0_3);
        }
        int v0_4 = ((Qt.c) p4).b;
        if (v0_4 != 0) {
            p5.z(2, ((long) v0_4.intValue()));
        }
        int v0_1 = ((Qt.c) p4).c;
        if (v0_1 != 0) {
            p5.F(3, v0_1);
        }
        p5.z(4, ((Qt.c) p4).d);
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        long v1_7;
        Qt.c v0_1 = new Qt.c();
        String v3 = 0;
        if (!p5.isNull(0)) {
            v1_7 = p5.getString(0);
        } else {
            v1_7 = 0;
        }
        long v1_2;
        v0_1.a = v1_7;
        if (!p5.isNull(1)) {
            v1_2 = Integer.valueOf(p5.getInt(1));
        } else {
            v1_2 = 0;
        }
        v0_1.b = v1_2;
        if (!p5.isNull(2)) {
            v3 = p5.getString(2);
        }
        v0_1.c = v3;
        v0_1.d = p5.getLong(3);
        return v0_1;
    }

    public final Object q(android.database.Cursor p3)
    {
        return Long.valueOf(p3.getLong(3));
    }

    public final Object r(long p1, Object p3)
    {
        ((Qt.c) p3).d = p1;
        return Long.valueOf(p1);
    }
}
