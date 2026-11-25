package vn.ai.trueid.sdk.networks;
public final class X implements vn.ai.trueid.sdk.networks.k {
    public final synthetic vn.ai.trueid.sdk.networks.W a;

    public X(vn.ai.trueid.sdk.networks.W p1)
    {
        this.a = p1;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.a.fail(p2);
        return;
    }

    public final void success(org.json.JSONObject p3)
    {
        vn.ai.trueid.sdk.networks.W v0 = this.a;
        try {
            v0.success(p3.getString("access_token"));
            return;
        } catch (vn.ai.trueid.sdk.models.TrueError v3_3) {
            v3_3.printStackTrace();
            v0.fail(new vn.ai.trueid.sdk.models.TrueError(vn.ai.trueid.sdk.models.TrueError.Companion.getNetworkError()));
            return;
        }
    }
}
