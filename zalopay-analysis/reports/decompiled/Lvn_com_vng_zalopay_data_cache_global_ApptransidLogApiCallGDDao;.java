package vn.com.vng.zalopay.data.cache.global;
public class ApptransidLogApiCallGDDao extends sj.a {
    public static final String TABLENAME = "APPTRANSID_LOG_API_CALL_GD";

    public final void b(android.database.sqlite.SQLiteStatement p5, Object p6)
    {
        p5.clearBindings();
        long v0_2 = ((Qt.a) p6).a;
        if (v0_2 != 0) {
            p5.bindLong(1, v0_2.longValue());
        }
        long v0_5 = ((Qt.a) p6).b;
        if (v0_5 != 0) {
            p5.bindString(2, v0_5);
        }
        long v0_0 = ((Qt.a) p6).c;
        if (v0_0 != 0) {
            p5.bindLong(3, v0_0.longValue());
        }
        long v0_1 = ((Qt.a) p6).d;
        if (v0_1 != 0) {
            p5.bindLong(4, v0_1.longValue());
        }
        long v0_3 = ((Qt.a) p6).e;
        if (v0_3 != 0) {
            p5.bindLong(5, v0_3.longValue());
        }
        int v6_2 = ((Qt.a) p6).f;
        if (v6_2 != 0) {
            p5.bindLong(6, ((long) v6_2.intValue()));
        }
        return;
    }

    public final void c(Object p5, xj.b p6)
    {
        p6.A();
        long v0_2 = ((Qt.a) p5).a;
        if (v0_2 != 0) {
            p6.z(1, v0_2.longValue());
        }
        long v0_5 = ((Qt.a) p5).b;
        if (v0_5 != 0) {
            p6.F(2, v0_5);
        }
        long v0_0 = ((Qt.a) p5).c;
        if (v0_0 != 0) {
            p6.z(3, v0_0.longValue());
        }
        long v0_1 = ((Qt.a) p5).d;
        if (v0_1 != 0) {
            p6.z(4, v0_1.longValue());
        }
        long v0_3 = ((Qt.a) p5).e;
        if (v0_3 != 0) {
            p6.z(5, v0_3.longValue());
        }
        int v5_2 = ((Qt.a) p5).f;
        if (v5_2 != 0) {
            p6.z(6, ((long) v5_2.intValue()));
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        int v1_0;
        Qt.a v0_1 = new Qt.a();
        Integer v3 = 0;
        if (!p5.isNull(0)) {
            v1_0 = Long.valueOf(p5.getLong(0));
        } else {
            v1_0 = 0;
        }
        int v1_2;
        v0_1.a = v1_0;
        if (!p5.isNull(1)) {
            v1_2 = p5.getString(1);
        } else {
            v1_2 = 0;
        }
        int v1_6;
        v0_1.b = v1_2;
        if (!p5.isNull(2)) {
            v1_6 = Long.valueOf(p5.getLong(2));
        } else {
            v1_6 = 0;
        }
        int v1_9;
        v0_1.c = v1_6;
        if (!p5.isNull(3)) {
            v1_9 = Long.valueOf(p5.getLong(3));
        } else {
            v1_9 = 0;
        }
        int v1_12;
        v0_1.d = v1_9;
        if (!p5.isNull(4)) {
            v1_12 = Long.valueOf(p5.getLong(4));
        } else {
            v1_12 = 0;
        }
        v0_1.e = v1_12;
        if (!p5.isNull(5)) {
            v3 = Integer.valueOf(p5.getInt(5));
        }
        v0_1.f = v3;
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
        ((Qt.a) p4).a = Long.valueOf(p2);
        return Long.valueOf(p2);
    }
}
