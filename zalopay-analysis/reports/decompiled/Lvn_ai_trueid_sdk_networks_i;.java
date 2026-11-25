package vn.ai.trueid.sdk.networks;
public final class i implements okhttp3.Interceptor {

    public final okhttp3.Response intercept(okhttp3.Interceptor$Chain p8)
    {
        okhttp3.Request v0_0 = p8.request();
        if ((v0_0.body() != null) && (v0_0.header("Content-Encoding") == null)) {
            if (!kotlin.text.y.equals$default(v0_0.header("Content-Type"), "application/x-protobuf", 0, 2, 0)) {
                return p8.proceed(v0_0);
            } else {
                return p8.proceed(v0_0.newBuilder().header("Content-Encoding", "gzip").method(v0_0.method(), new vn.ai.trueid.sdk.liveness.networks.c(v0_0.body(), 1)).build());
            }
        } else {
            return p8.proceed(v0_0);
        }
    }
}
