package vn.ai.trueid.sdk.TrueShield;
public final class SecureVault {
    public static final vn.ai.trueid.sdk.TrueShield.SecureVault$Companion Companion;
    private static final String DAT_FILE_NAME;
    private final android.content.Context context;

    static SecureVault()
    {
        String v0_2 = new vn.ai.trueid.sdk.TrueShield.SecureVault$Companion(0);
        vn.ai.trueid.sdk.TrueShield.SecureVault.Companion = v0_2;
        System.loadLibrary("trueidlib");
        int[] v1_3 = new int[12];
        v1_3 = {32, 0, 0, 0, 32, 32, 56, 27, 23, 75, 58, 43};
        vn.ai.trueid.sdk.TrueShield.SecureVault.DAT_FILE_NAME = vn.ai.trueid.sdk.TrueShield.SecureVault$Companion.access$s(v0_2, v1_3);
        return;
    }

    public SecureVault(android.content.Context p1)
    {
        this.context = p1;
        return;
    }

    private final native String[] getPinHashesNative(byte[] p0);

    public final java.util.List getPinHashes()
    {
        try {
            java.util.List v0_0 = this.getVaultData();
        } catch (Exception) {
            return kotlin.collections.E.emptyList();
        }
        if (v0_0 != null) {
            return kotlin.collections.y.toList(this.getPinHashesNative(v0_0));
        } else {
            return kotlin.collections.E.emptyList();
        }
    }

    public final byte[] getVaultData()
    {
        try {
            return kotlin.io.ByteStreamsKt.readBytes(this.context.getApplicationContext().getAssets().open(vn.ai.trueid.sdk.TrueShield.SecureVault.DAT_FILE_NAME));
        } catch (Exception) {
            return 0;
        }
    }

    public final boolean isVaultAvailable()
    {
        if (this.getVaultData() == null) {
            return 0;
        } else {
            return 1;
        }
    }
}
