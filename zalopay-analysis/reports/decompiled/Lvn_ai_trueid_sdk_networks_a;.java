package vn.ai.trueid.sdk.networks;
public final class a implements vn.ai.trueid.sdk.networks.k {
    public final synthetic j9.q a;

    public a(j9.q p1)
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
        j9.q v0 = this.a;
        try {
            v0.success(p3.getString("access_token"));
            return;
        } catch (Exception) {
            v0.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getConnectionError());
            return;
        }
    }
}
