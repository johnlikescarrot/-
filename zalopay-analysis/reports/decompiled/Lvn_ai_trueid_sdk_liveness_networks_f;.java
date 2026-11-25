package vn.ai.trueid.sdk.liveness.networks;
public final class f {
    public retrofit2.Retrofit a;
    public String b;

    public final void a(String p8, java.util.Map p9, java.util.Map p10, vn.ai.trueid.sdk.liveness.networks.e p11)
    {
        vn.ai.trueid.sdk.liveness.networks.a v0_2 = ((vn.ai.trueid.sdk.liveness.networks.a) this.a.create(vn.ai.trueid.sdk.liveness.networks.a));
        vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.e(new Object[] {"post url:", p8}));
        vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.e(new Object[] {"headers:", p9}));
        vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.e(new Object[] {"params:", p10.toString()}));
        okhttp3.MediaType v1_6 = new org.json.JSONObject(p10);
        okhttp3.RequestBody v10_3 = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json"), v1_6.toString());
        if (p9.containsKey("Content-Type")) {
            int v3_2 = ((CharSequence) p9.get("Content-Type"));
            if ((v3_2 != 0) && ((v3_2.length() != 0) && (kotlin.text.y.equals$default(((String) p9.get("Content-Type")), "application/x-protobuf", 0, 2, 0)))) {
                v10_3 = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/x-protobuf"), v1_6.toString().getBytes(kotlin.text.Charsets.UTF_8));
            }
        }
        this.b = p8;
        v0_2.a(p8, p9, v10_3).enqueue(new g4.a(this, p11, 21));
        return;
    }
}
