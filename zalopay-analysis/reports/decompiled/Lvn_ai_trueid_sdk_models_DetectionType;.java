package vn.ai.trueid.sdk.models;
public final enum class DetectionType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.models.DetectionType[] $VALUES;
    public static final enum vn.ai.trueid.sdk.models.DetectionType faceComparision;
    public static final enum vn.ai.trueid.sdk.models.DetectionType idVerification;
    public static final enum vn.ai.trueid.sdk.models.DetectionType livenessDetection;
    public static final enum vn.ai.trueid.sdk.models.DetectionType ocrScan;

    private static final synthetic vn.ai.trueid.sdk.models.DetectionType[] $values()
    {
        return new vn.ai.trueid.sdk.models.DetectionType[] {vn.ai.trueid.sdk.models.DetectionType.ocrScan, vn.ai.trueid.sdk.models.DetectionType.livenessDetection, vn.ai.trueid.sdk.models.DetectionType.faceComparision, vn.ai.trueid.sdk.models.DetectionType.idVerification});
    }

    static DetectionType()
    {
        vn.ai.trueid.sdk.models.DetectionType.ocrScan = new vn.ai.trueid.sdk.models.DetectionType("ocrScan", 0);
        vn.ai.trueid.sdk.models.DetectionType.livenessDetection = new vn.ai.trueid.sdk.models.DetectionType("livenessDetection", 1);
        vn.ai.trueid.sdk.models.DetectionType.faceComparision = new vn.ai.trueid.sdk.models.DetectionType("faceComparision", 2);
        vn.ai.trueid.sdk.models.DetectionType.idVerification = new vn.ai.trueid.sdk.models.DetectionType("idVerification", 3);
        vn.ai.trueid.sdk.models.DetectionType.$VALUES = vn.ai.trueid.sdk.models.DetectionType.$values();
        return;
    }

    private DetectionType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.models.DetectionType valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.models.DetectionType) Enum.valueOf(vn.ai.trueid.sdk.models.DetectionType, p1));
    }

    public static vn.ai.trueid.sdk.models.DetectionType[] values()
    {
        return ((vn.ai.trueid.sdk.models.DetectionType[]) vn.ai.trueid.sdk.models.DetectionType.$VALUES.clone());
    }
}
