package vn.ai.trueid.sdk.TrueShield;
public final synthetic class ChallengeManager$WhenMappings {
    public static final synthetic int[] $EnumSwitchMapping$0;

    static ChallengeManager$WhenMappings()
    {
        int[] v0_2 = new int[vn.ai.trueid.sdk.TrueShield.ImageType.values().length];
        vn.ai.trueid.sdk.TrueShield.ImageType.ID_FRONT.ordinal()[int v1_8] = 1;
        vn.ai.trueid.sdk.TrueShield.ImageType.ID_BACK.ordinal()[int v1_0] = 2;
        vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE1.ordinal()[int v1_2] = 3;
        vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE2.ordinal()[int v1_4] = 4;
        try {
            vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE3.ordinal()[int v1_6] = 5;
        } catch (NoSuchFieldError) {
        }
        vn.ai.trueid.sdk.TrueShield.ChallengeManager$WhenMappings.$EnumSwitchMapping$0 = v0_2;
        return;
    }
}
