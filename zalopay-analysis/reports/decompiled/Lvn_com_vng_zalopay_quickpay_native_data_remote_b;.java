package vn.com.vng.zalopay.quickpay_native.data.remote;
public final class b {
    public final com.google.gson.Gson a;

    public b()
    {
        this.a = new com.google.gson.GsonBuilder().setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return;
    }

    public static String a(vn.com.vng.zalopay.quickpay_native.data.remote.b p3, String p4)
    {
        return p3.a.toJson(new vn.com.vng.zalopay.quickpay_native.data.remote.a(System.currentTimeMillis(), p4));
    }
}
