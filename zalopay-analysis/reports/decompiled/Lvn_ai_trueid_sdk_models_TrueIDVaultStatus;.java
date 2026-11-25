package vn.ai.trueid.sdk.models;
public final class TrueIDVaultStatus {
    public static final vn.ai.trueid.sdk.models.TrueIDVaultStatus INSTANCE = None;
    public static final int OK = 1;
    public static final String SENTINEL_INVALID = "ERR_SECURITY_VAULT_INVALID";
    public static final String SENTINEL_MISSING = "ERR_SECURITY_VAULT_MISSING";
    public static final int VAULT_INVALID = 455;
    public static final int VAULT_MISSING = 454;

    static TrueIDVaultStatus()
    {
        vn.ai.trueid.sdk.models.TrueIDVaultStatus.INSTANCE = new vn.ai.trueid.sdk.models.TrueIDVaultStatus();
        return;
    }

    private TrueIDVaultStatus()
    {
        return;
    }

    public final Integer codeForSentinel(String p2)
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p2, "ERR_SECURITY_VAULT_MISSING")) {
            if (!kotlin.jvm.internal.Intrinsics.areEqual(p2, "ERR_SECURITY_VAULT_INVALID")) {
                return 0;
            } else {
                return Integer.valueOf(455);
            }
        } else {
            return Integer.valueOf(454);
        }
    }

    public final boolean isSentinel(String p2)
    {
        if ((!kotlin.jvm.internal.Intrinsics.areEqual(p2, "ERR_SECURITY_VAULT_MISSING")) && (!kotlin.jvm.internal.Intrinsics.areEqual(p2, "ERR_SECURITY_VAULT_INVALID"))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final String message(int p2)
    {
        if (p2 == 454) {
            return "trueidlib.so not found. Add the vault file provided by TrueID to the app bundle (Android: src/main/assets, iOS: Copy Bundle Resources).";
        } else {
            if (p2 == 455) {
                return "Data signing failed with trueidlib.so. Verify the app applicationId/bundleId matches the one registered with TrueID for this vault file.";
            } else {
                return "Vault error";
            }
        }
    }
}
