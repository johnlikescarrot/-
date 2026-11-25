package vn.ai.trueid.sdk.TrueShield;
public final class ChallengeManager {
    public static final vn.ai.trueid.sdk.TrueShield.ChallengeManager INSTANCE;
    private static String solvedRowId;

    static ChallengeManager()
    {
        vn.ai.trueid.sdk.TrueShield.ChallengeManager.INSTANCE = new vn.ai.trueid.sdk.TrueShield.ChallengeManager();
        vn.ai.trueid.sdk.TrueShield.ChallengeManager.solvedRowId = "";
        return;
    }

    private ChallengeManager()
    {
        return;
    }

    public final String getHexColor(vn.ai.trueid.sdk.TrueShield.ImageType p4)
    {
        String v0_2;
        if (vn.ai.trueid.sdk.TrueShield.ChallengeManager.solvedRowId.length() != 0) {
            v0_2 = vn.ai.trueid.sdk.TrueShield.ChallengeManager.solvedRowId;
        } else {
            v0_2 = "G1R1";
        }
        kotlin.NoWhenBranchMatchedException v4_5 = vn.ai.trueid.sdk.TrueShield.ChallengeManager$WhenMappings.$EnumSwitchMapping$0[p4.ordinal()];
        int v1_0 = 1;
        if (v4_5 == 1) {
            v1_0 = 0;
        } else {
            int v2 = 2;
            if (v4_5 != 2) {
                v1_0 = 3;
                if (v4_5 != 3) {
                    v2 = 4;
                    if (v4_5 == 4) {
                        kotlin.NoWhenBranchMatchedException v4_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.getChallengeColorNative(v0_2, v1_0);
                        if (v4_3 == null) {
                            v4_3 = "#A9A9A9";
                        }
                        return v4_3;
                    } else {
                        if (v4_5 != 5) {
                            throw new kotlin.NoWhenBranchMatchedException();
                        }
                    }
                }
                v1_0 = v2;
            }
        }
    }

    public final void solve(android.content.Context p1, String p2, String p3, String p4)
    {
        String v1_2 = new vn.ai.trueid.sdk.TrueShield.SecureVault(p1).getVaultData();
        if (v1_2 != null) {
            String v1_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.solveChallengeNative(p2, p3, v1_2);
            if ((v1_3 != null) && (v1_3.length() != 0)) {
                vn.ai.trueid.sdk.TrueShield.ChallengeManager.solvedRowId = v1_3;
                return;
            } else {
                vn.ai.trueid.sdk.TrueShield.ChallengeManager.solvedRowId = "G1R1";
                return;
            }
        } else {
            return;
        }
    }
}
