package vn.ai.trueid.sdk.networks;
public final class G implements vn.ai.trueid.sdk.networks.k {
    public final synthetic io.reactivex.internal.operators.observable.f1 a;

    public G(io.reactivex.internal.operators.observable.f1 p1)
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
        io.reactivex.internal.operators.observable.f1 v0 = this.a;
        try {
            v0.success(p3.getString("access_token"));
            return;
        } catch (Exception) {
            v0.fail(new vn.ai.trueid.sdk.models.TrueError(vn.ai.trueid.sdk.models.TrueError.Companion.getNetworkError()));
            return;
        }
    }
}
