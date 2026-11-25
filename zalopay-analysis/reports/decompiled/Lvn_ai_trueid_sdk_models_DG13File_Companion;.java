package vn.ai.trueid.sdk.models;
public final class DG13File$Companion {

    private DG13File$Companion()
    {
        return;
    }

    public synthetic DG13File$Companion(kotlin.jvm.internal.DefaultConstructorMarker p1)
    {
        return;
    }

    public final vn.ai.trueid.sdk.models.CitizenInfo parseTLV_DG13_ToCitizen(String p8)
    {
        vn.ai.trueid.sdk.models.DG13File v0_0 = 0;
        vn.ai.trueid.sdk.models.CitizenInfo v8_3 = android.util.Base64.decode(p8, 0);
        char[] v1_2 = new char[(v8_3.length * 2)];
        int v2 = v8_3.length;
        while (v0_0 < v2) {
            char v3_0 = v8_3[v0_0];
            int v5_0 = (v0_0 * 2);
            char[] v6 = Ok.w.a;
            v1_2[v5_0] = v6[((v3_0 & 255) >> 4)];
            v1_2[(v5_0 + 1)] = v6[(v3_0 & 15)];
            v0_0++;
        }
        vn.ai.trueid.sdk.models.CitizenInfo v8_1 = new String(v1_2);
        vn.ai.trueid.sdk.models.DG13File v0_2 = new vn.ai.trueid.sdk.models.DG13File();
        v0_2.setAllHex(v8_1);
        return v0_2.readContent();
    }
}
