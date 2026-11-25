package vn.ai.trueid.sdk.networks;
public final class p implements vn.ai.trueid.sdk.networks.k {
    public final synthetic Ek.Z a;

    public p(Ek.Z p1)
    {
        this.a = p1;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.a.a(p2);
        return;
    }

    public final void success(org.json.JSONObject p7)
    {
        kotlin.jvm.functions.Function0 v7_6 = p7.getInt(wmoi.igob.ggcn(6568));
        Ek.Z v1 = this.a;
        if (v7_6 != 1) {
            if (v7_6 != null) {
                v1.a(new vn.ai.trueid.sdk.models.TrueError(v7_6));
                return;
            } else {
                v1.a(new vn.ai.trueid.sdk.models.TrueError(0, vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465)));
                return;
            }
        } else {
            v1.getClass();
            kotlin.jvm.functions.Function0 v7_3 = wk.a.h;
            if (v7_3 != null) {
                v7_3.invoke();
            }
            kotlin.jvm.functions.Function0 v7_4 = m9.F2.a;
            if (v7_4 != null) {
                v7_4.dismiss();
            }
            kotlin.jvm.functions.Function0 v7_5 = 0;
            m9.F2.a = 0;
            int v3_4 = ((String) v1.a.c.get(0));
            int v3_6 = android.util.Base64.decode(v3_4.substring((kotlin.text.StringsKt.l(v3_4, wmoi.igob.ggcn(6569), 0, 0, 6) + 1)), 0);
            fk.b.n = android.graphics.BitmapFactory.decodeByteArray(v3_6, 0, v3_6.length);
            Ek.d.r1(0, 7);
            fk.b.h = 1;
            fk.d v0_6 = v1.b.r;
            if (v0_6 != null) {
                v7_5 = v0_6;
            }
            v7_5.i0();
            kotlin.jvm.functions.Function0 v7_7 = wk.a.j;
            if (v7_7 != null) {
                v7_7.invoke();
            }
            return;
        }
    }
}
