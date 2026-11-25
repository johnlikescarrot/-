package vn.com.vng.zalopay.data.cache.model;
public class MerchantVerifyPinOrderGDDao extends sj.a {
    public static final String TABLENAME = "MERCHANT_VERIFY_PIN_ORDER_GD";

    public final void b(android.database.sqlite.SQLiteStatement p3, Object p4)
    {
        p3.clearBindings();
        long v0_1 = ((Rt.h) p4).a;
        if (v0_1 != 0) {
            p3.bindString(1, v0_1);
        }
        int v4_3 = ((Rt.h) p4).b;
        if (v4_3 != 0) {
            p3.bindLong(2, ((long) v4_3.intValue()));
        }
        return;
    }

    public final void c(Object p3, xj.b p4)
    {
        p4.A();
        long v0_1 = ((Rt.h) p3).a;
        if (v0_1 != 0) {
            p4.F(1, v0_1);
        }
        int v3_3 = ((Rt.h) p3).b;
        if (v3_3 != 0) {
            p4.z(2, ((long) v3_3.intValue()));
        }
        return;
    }

    public final Object p(android.database.Cursor p5)
    {
        int v1_2;
        Rt.h v0_1 = new Rt.h();
        Integer v3 = 0;
        if (!p5.isNull(0)) {
            v1_2 = p5.getString(0);
        } else {
            v1_2 = 0;
        }
        v0_1.a = v1_2;
        if (!p5.isNull(1)) {
            v3 = Integer.valueOf(p5.getInt(1));
        }
        v0_1.b = v3;
        return v0_1;
    }

    public final Object q(android.database.Cursor p3)
    {
        if (!p3.isNull(0)) {
            return p3.getString(0);
        } else {
            return 0;
        }
    }

    public final Object r(long p1, Object p3)
    {
        return ((Rt.h) p3).a;
    }
}
