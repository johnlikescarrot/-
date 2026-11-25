package vn.ai.trueid.sdk.liveness.networks;
public abstract class k {
    public static final vn.ai.trueid.sdk.liveness.networks.f a;
    public static java.util.Map b;
    public static java.util.Map c;

    static k()
    {
        okhttp3.OkHttpClient v2_6 = new Hk.a();
        java.util.LinkedHashMap v0_1 = new vn.ai.trueid.sdk.liveness.networks.f();
        v0_1.b = "";
        vn.ai.trueid.sdk.liveness.networks.d v3_1 = new retrofit2.Retrofit$Builder();
        javax.net.ssl.X509TrustManager v4_1 = new StringBuilder();
        v4_1.append(Gk.c.a().a);
        v4_1.append(47);
        retrofit2.Retrofit v1_3 = v3_1.baseUrl(v4_1.toString()).addConverterFactory(v2_6);
        int v5_0 = java.util.concurrent.TimeUnit.SECONDS;
        okhttp3.OkHttpClient v2_4 = new okhttp3.OkHttpClient$Builder().connectTimeout(((long) Gk.c.a().p), v5_0).readTimeout(((long) Gk.c.a().p), v5_0).writeTimeout(((long) Gk.c.a().p), v5_0);
        try {
            vn.ai.trueid.sdk.liveness.networks.d v3_12 = new vn.ai.trueid.sdk.liveness.networks.m(0);
            int v5_2 = ((javax.net.ssl.TrustManager[]) v3_12.c);
        } catch (vn.ai.trueid.sdk.liveness.networks.d v3_15) {
            v3_15.printStackTrace();
            v2_4.hostnameVerifier(new vn.ai.trueid.sdk.liveness.networks.b());
            v2_4.addInterceptor(new vn.ai.trueid.sdk.liveness.networks.d());
            v0_1.a = v1_3.client(v2_4.build()).build();
            vn.ai.trueid.sdk.liveness.networks.k.a = v0_1;
            vn.ai.trueid.sdk.liveness.networks.k.b = new java.util.LinkedHashMap();
            vn.ai.trueid.sdk.liveness.networks.k.c = new java.util.LinkedHashMap();
            return;
        } catch (vn.ai.trueid.sdk.liveness.networks.d v3_14) {
            v3_14.printStackTrace();
            v2_4.hostnameVerifier(new vn.ai.trueid.sdk.liveness.networks.b());
            v2_4.addInterceptor(new vn.ai.trueid.sdk.liveness.networks.d());
            v0_1.a = v1_3.client(v2_4.build()).build();
            vn.ai.trueid.sdk.liveness.networks.k.a = v0_1;
            vn.ai.trueid.sdk.liveness.networks.k.b = new java.util.LinkedHashMap();
            vn.ai.trueid.sdk.liveness.networks.k.c = new java.util.LinkedHashMap();
            return;
        } catch (vn.ai.trueid.sdk.liveness.networks.d v3_13) {
            v3_13.printStackTrace();
            v2_4.hostnameVerifier(new vn.ai.trueid.sdk.liveness.networks.b());
            v2_4.addInterceptor(new vn.ai.trueid.sdk.liveness.networks.d());
            v0_1.a = v1_3.client(v2_4.build()).build();
            vn.ai.trueid.sdk.liveness.networks.k.a = v0_1;
            vn.ai.trueid.sdk.liveness.networks.k.b = new java.util.LinkedHashMap();
            vn.ai.trueid.sdk.liveness.networks.k.c = new java.util.LinkedHashMap();
            return;
        }
        if (v5_2 == 0) {
            v5_2 = 0;
        }
        v2_4.sslSocketFactory(v3_12, ((javax.net.ssl.X509TrustManager) v5_2[0]));
        v2_4.hostnameVerifier(new vn.ai.trueid.sdk.liveness.networks.b());
        v2_4.addInterceptor(new vn.ai.trueid.sdk.liveness.networks.d());
        v0_1.a = v1_3.client(v2_4.build()).build();
        vn.ai.trueid.sdk.liveness.networks.k.a = v0_1;
        vn.ai.trueid.sdk.liveness.networks.k.b = new java.util.LinkedHashMap();
        vn.ai.trueid.sdk.liveness.networks.k.c = new java.util.LinkedHashMap();
        return;
    }
}
