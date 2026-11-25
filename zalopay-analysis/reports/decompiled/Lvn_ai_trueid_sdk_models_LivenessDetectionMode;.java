package vn.ai.trueid.sdk.models;
public final enum class LivenessDetectionMode extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.models.LivenessDetectionMode[] $VALUES;
    public static final enum vn.ai.trueid.sdk.models.LivenessDetectionMode Active;
    public static final enum vn.ai.trueid.sdk.models.LivenessDetectionMode None;
    public static final enum vn.ai.trueid.sdk.models.LivenessDetectionMode PassLiveness;
    public static final enum vn.ai.trueid.sdk.models.LivenessDetectionMode Passive;

    private static final synthetic vn.ai.trueid.sdk.models.LivenessDetectionMode[] $values()
    {
        return new vn.ai.trueid.sdk.models.LivenessDetectionMode[] {vn.ai.trueid.sdk.models.LivenessDetectionMode.None, vn.ai.trueid.sdk.models.LivenessDetectionMode.Active, vn.ai.trueid.sdk.models.LivenessDetectionMode.PassLiveness, vn.ai.trueid.sdk.models.LivenessDetectionMode.Passive});
    }

    static LivenessDetectionMode()
    {
        vn.ai.trueid.sdk.models.LivenessDetectionMode.None = new vn.ai.trueid.sdk.models.LivenessDetectionMode("None", 0);
        vn.ai.trueid.sdk.models.LivenessDetectionMode.Active = new vn.ai.trueid.sdk.models.LivenessDetectionMode("Active", 1);
        vn.ai.trueid.sdk.models.LivenessDetectionMode.PassLiveness = new vn.ai.trueid.sdk.models.LivenessDetectionMode("PassLiveness", 2);
        vn.ai.trueid.sdk.models.LivenessDetectionMode.Passive = new vn.ai.trueid.sdk.models.LivenessDetectionMode("Passive", 3);
        vn.ai.trueid.sdk.models.LivenessDetectionMode.$VALUES = vn.ai.trueid.sdk.models.LivenessDetectionMode.$values();
        return;
    }

    private LivenessDetectionMode(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.models.LivenessDetectionMode valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.models.LivenessDetectionMode) Enum.valueOf(vn.ai.trueid.sdk.models.LivenessDetectionMode, p1));
    }

    public static vn.ai.trueid.sdk.models.LivenessDetectionMode[] values()
    {
        return ((vn.ai.trueid.sdk.models.LivenessDetectionMode[]) vn.ai.trueid.sdk.models.LivenessDetectionMode.$VALUES.clone());
    }
}
