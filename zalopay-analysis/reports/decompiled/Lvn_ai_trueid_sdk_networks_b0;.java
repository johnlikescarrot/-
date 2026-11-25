package vn.ai.trueid.sdk.networks;
public final class b0 extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function1 {
    public final synthetic byte[] d;
    public final synthetic vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 e;
    public final synthetic boolean f;
    public final synthetic String g;
    public final synthetic boolean h;
    public final synthetic android.content.Context i;
    public final synthetic int j;
    public final synthetic org.json.JSONObject k;

    public b0(byte[] p1, vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 p2, boolean p3, String p4, boolean p5, android.content.Context p6, int p7, org.json.JSONObject p8)
    {
        this.d = p1;
        this.e = p2;
        this.f = p3;
        this.g = p4;
        this.h = p5;
        this.i = p6;
        this.j = p7;
        this.k = p8;
        super(1);
        return;
    }

    public final Object invoke(Object p10)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 v6 = this.e;
        String v1_1 = this.d;
        if ((((String) p10) != null) && (((String) p10).length() != 0)) {
            vn.ai.trueid.sdk.networks.c0.a(this.f, v1_1, this.g, this.h, this.i, this.j, v6, this.k, ((String) p10));
        } else {
            java.util.Arrays.fill(v1_1, 0);
            v6.fail(new vn.ai.trueid.sdk.models.TrueError(-1, "PIN setup cancelled"));
        }
        return kotlin.Unit.INSTANCE;
    }
}
