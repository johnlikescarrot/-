package vn.ai.trueid.sdk.networks;
public final class Y implements vn.ai.trueid.sdk.networks.k {
    public final synthetic lO.o a;

    public Y(lO.o p1)
    {
        this.a = p1;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.a.fail(p2);
        return;
    }

    public final void success(org.json.JSONObject p7)
    {
        int v0_1 = wmoi.igob.ggcn(6437);
        lO.o v1 = this.a;
        if (p7.optInt(v0_1, -1) != 1) {
            v1.fail(new vn.ai.trueid.sdk.models.TrueError(p7.optInt(v0_1, -1)));
            return;
        } else {
            v1.success(p7.optString(wmoi.igob.ggcn(6438), ""));
            return;
        }
    }
}
