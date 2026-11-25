package vn.ai.trueid.sdk.TrueShield;
public final class SecureVault$Companion {

    private SecureVault$Companion()
    {
        return;
    }

    public synthetic SecureVault$Companion(kotlin.jvm.internal.DefaultConstructorMarker p1)
    {
        return;
    }

    public static final synthetic String access$s(vn.ai.trueid.sdk.TrueShield.SecureVault$Companion p0, int[] p1)
    {
        return p0.s(p1);
    }

    private final String s(int[] p8)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(p8.length);
        String v1_1 = p8.length;
        int v2_1 = 0;
        int v3_6 = 0;
        while (v2_1 < v1_1) {
            int v5_0 = (v3_6 + 1);
            v0_1.add(Character.valueOf(((char) ("TrueID".charAt((v3_6 % 6)) ^ p8[v2_1]))));
            v2_1++;
            v3_6 = v5_0;
        }
        return kotlin.collections.CollectionsKt.l(v0_1, "", 0, 0, 0, 62);
    }
}
