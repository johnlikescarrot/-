package vn.ai.trueid.sdk.networks;
public final class m {
    public static final java.util.List c;
    public final retrofit2.Retrofit a;
    public String b;

    static m()
    {
        vn.ai.trueid.sdk.networks.m.c = kotlin.collections.E.emptyList();
        return;
    }

    public m(String p5)
    {
        okhttp3.OkHttpClient v0_1 = new Lk.a();
        this.b = "";
        if (kotlin.text.StringsKt.isBlank(p5)) {
            p5 = "https://localhost";
        }
        vn.ai.trueid.sdk.networks.i v1_0 = new retrofit2.Retrofit$Builder();
        javax.net.ssl.X509TrustManager v2_1 = new StringBuilder();
        v2_1.append(p5);
        v2_1.append("/");
        retrofit2.Retrofit v5_4 = v1_0.baseUrl(v2_1.toString()).addConverterFactory(v0_1);
        vn.ai.trueid.sdk.networks.i v1_1 = java.util.concurrent.TimeUnit.SECONDS;
        okhttp3.OkHttpClient v0_6 = new okhttp3.OkHttpClient$Builder().connectTimeout(90, v1_1).readTimeout(90, v1_1).writeTimeout(90, v1_1);
        try {
            vn.ai.trueid.sdk.networks.i v1_4 = new vn.ai.trueid.sdk.liveness.networks.m(1);
            javax.net.ssl.X509TrustManager v2_5 = ((javax.net.ssl.TrustManager[]) v1_4.c);
        } catch (java.security.KeyStoreException) {
            v0_6.hostnameVerifier(okhttp3.internal.tls.OkHostnameVerifier.INSTANCE);
            v0_6.addInterceptor(new vn.ai.trueid.sdk.networks.i());
            this.a = v5_4.client(v0_6.build()).build();
            return;
        }
        if (v2_5 == null) {
            v2_5 = 0;
        }
        v0_6.sslSocketFactory(v1_4, ((javax.net.ssl.X509TrustManager) v2_5[0]));
        v0_6.hostnameVerifier(okhttp3.internal.tls.OkHostnameVerifier.INSTANCE);
        v0_6.addInterceptor(new vn.ai.trueid.sdk.networks.i());
        this.a = v5_4.client(v0_6.build()).build();
        return;
    }

    public static final void a(vn.ai.trueid.sdk.networks.m p4, Throwable p5, vn.ai.trueid.sdk.networks.k p6)
    {
        vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {androidx.activity.result.e.p("Failure: URL=", p4.b, ", Message=", p5.getMessage())}));
        vn.ai.trueid.sdk.models.TrueError v4_2 = p5.getMessage();
        if (v4_2 == null) {
            v4_2 = "";
        }
        if ((!(p5 instanceof java.net.SocketTimeoutException)) && (!kotlin.text.StringsKt.e(v4_2, "timeout"))) {
            if (!(p5 instanceof java.net.UnknownHostException)) {
                if ((!kotlin.text.StringsKt.g(v4_2, "trueid")) && ((!kotlin.text.StringsKt.g(v4_2, "host")) && ((!kotlin.text.StringsKt.g(v4_2, "network")) && ((!kotlin.text.StringsKt.g(v4_2, "connect")) && (!kotlin.text.StringsKt.g(v4_2, "java.lang.String cannot")))))) {
                    p6.fail(new vn.ai.trueid.sdk.models.TrueError(-1, v4_2));
                    return;
                } else {
                    p6.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getConnectionError());
                    return;
                }
            } else {
                p6.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getSERVER_UNAVAILABLE());
                return;
            }
        } else {
            p6.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getSessionTimeout());
            return;
        }
    }

    public static synthetic void c(vn.ai.trueid.sdk.networks.m p6, String p7, java.util.Map p8, java.io.File p9)
    {
        p6.b(p7, p8, p9, vn.ai.trueid.sdk.networks.l.e, vn.ai.trueid.sdk.networks.l.f);
        return;
    }

    public final void b(String p3, java.util.Map p4, java.io.File p5, kotlin.jvm.functions.Function0 p6, kotlin.jvm.functions.Function0 p7)
    {
        vn.ai.trueid.sdk.networks.h v0_2 = ((vn.ai.trueid.sdk.networks.h) this.a.create(vn.ai.trueid.sdk.networks.h));
        this.b = p3;
        v0_2.c(p3, p4).enqueue(new r2.j(p6, this, p5, p7));
        return;
    }

    public final void d(String p8, java.util.Map p9, java.util.Map p10, vn.ai.trueid.sdk.networks.k p11)
    {
        if (vn.ai.trueid.sdk.utils.Helpers.Companion.verifyInternal()) {
            okhttp3.MediaType v1_0;
            vn.ai.trueid.sdk.networks.h v0_5 = vn.ai.trueid.sdk.models.TrueIDVaultStatus.INSTANCE;
            okhttp3.MediaType v1_8 = p10.get("signature");
            if (!(v1_8 instanceof String)) {
                v1_0 = 0;
            } else {
                v1_0 = ((String) v1_8);
            }
            okhttp3.MediaType v1_1 = v0_5.codeForSentinel(v1_0);
            if (v1_1 == null) {
                vn.ai.trueid.sdk.networks.h v0_4 = ((vn.ai.trueid.sdk.networks.h) this.a.create(vn.ai.trueid.sdk.networks.h));
                okhttp3.MediaType v1_4 = new org.json.JSONObject(p10);
                okhttp3.RequestBody v10_3 = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json"), v1_4.toString());
                if (p9.containsKey("Content-Type")) {
                    int v4_2 = ((CharSequence) p9.get("Content-Type"));
                    if ((v4_2 != 0) && ((v4_2.length() != 0) && (kotlin.text.y.equals$default(((String) p9.get("Content-Type")), "application/x-protobuf", 0, 2, 0)))) {
                        v10_3 = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/x-protobuf"), v1_4.toString().getBytes(kotlin.text.Charsets.UTF_8));
                    }
                }
                this.b = p8;
                v0_4.a(p8, p9, v10_3).enqueue(new h3.j(this, p11));
                return;
            } else {
                retrofit2.Call v8_2 = v1_1.intValue();
                p11.fail(new vn.ai.trueid.sdk.models.TrueError(v8_2, v0_5.message(v8_2)));
                return;
            }
        } else {
            p11.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getConnectionError());
            return;
        }
    }

    public final void e(String p3, java.util.Map p4, java.util.Map p5, Mk.a p6)
    {
        if (vn.ai.trueid.sdk.utils.Helpers.Companion.verifyInternal()) {
            vn.ai.trueid.sdk.networks.h v0_4 = ((vn.ai.trueid.sdk.networks.h) this.a.create(vn.ai.trueid.sdk.networks.h));
            int v5_3 = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json"), new org.json.JSONObject(p5).toString());
            this.b = p3;
            v0_4.a(p3, p4, v5_3).enqueue(new j4.h(p6, this, 17));
            return;
        } else {
            p6.a(new vn.ai.trueid.sdk.models.TrueError(vn.ai.trueid.sdk.models.TrueError.Companion.getNetworkError()));
            return;
        }
    }
}
