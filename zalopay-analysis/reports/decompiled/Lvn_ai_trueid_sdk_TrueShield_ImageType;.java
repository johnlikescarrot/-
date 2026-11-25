package vn.ai.trueid.sdk.TrueShield;
public final enum class ImageType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.TrueShield.ImageType[] $VALUES;
    public static final enum vn.ai.trueid.sdk.TrueShield.ImageType ID_BACK;
    public static final enum vn.ai.trueid.sdk.TrueShield.ImageType ID_FRONT;
    public static final enum vn.ai.trueid.sdk.TrueShield.ImageType SELFIE1;
    public static final enum vn.ai.trueid.sdk.TrueShield.ImageType SELFIE2;
    public static final enum vn.ai.trueid.sdk.TrueShield.ImageType SELFIE3;

    private static final synthetic vn.ai.trueid.sdk.TrueShield.ImageType[] $values()
    {
        return new vn.ai.trueid.sdk.TrueShield.ImageType[] {vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE1, vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE2, vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE3, vn.ai.trueid.sdk.TrueShield.ImageType.ID_FRONT, vn.ai.trueid.sdk.TrueShield.ImageType.ID_BACK});
    }

    static ImageType()
    {
        vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE1 = new vn.ai.trueid.sdk.TrueShield.ImageType("SELFIE1", 0);
        vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE2 = new vn.ai.trueid.sdk.TrueShield.ImageType("SELFIE2", 1);
        vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE3 = new vn.ai.trueid.sdk.TrueShield.ImageType("SELFIE3", 2);
        vn.ai.trueid.sdk.TrueShield.ImageType.ID_FRONT = new vn.ai.trueid.sdk.TrueShield.ImageType("ID_FRONT", 3);
        vn.ai.trueid.sdk.TrueShield.ImageType.ID_BACK = new vn.ai.trueid.sdk.TrueShield.ImageType("ID_BACK", 4);
        vn.ai.trueid.sdk.TrueShield.ImageType.$VALUES = vn.ai.trueid.sdk.TrueShield.ImageType.$values();
        return;
    }

    private ImageType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.TrueShield.ImageType valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.TrueShield.ImageType) Enum.valueOf(vn.ai.trueid.sdk.TrueShield.ImageType, p1));
    }

    public static vn.ai.trueid.sdk.TrueShield.ImageType[] values()
    {
        return ((vn.ai.trueid.sdk.TrueShield.ImageType[]) vn.ai.trueid.sdk.TrueShield.ImageType.$VALUES.clone());
    }
}
