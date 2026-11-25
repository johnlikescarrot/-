package vn.ai.trueid.sdk.models;
public final enum class DocType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.models.DocType[] $VALUES;
    public static final enum vn.ai.trueid.sdk.models.DocType ID_CARD;
    public static final enum vn.ai.trueid.sdk.models.DocType OTHER;
    public static final enum vn.ai.trueid.sdk.models.DocType PASSPORT;

    private static final synthetic vn.ai.trueid.sdk.models.DocType[] $values()
    {
        return new vn.ai.trueid.sdk.models.DocType[] {vn.ai.trueid.sdk.models.DocType.PASSPORT, vn.ai.trueid.sdk.models.DocType.ID_CARD, vn.ai.trueid.sdk.models.DocType.OTHER});
    }

    static DocType()
    {
        vn.ai.trueid.sdk.models.DocType.PASSPORT = new vn.ai.trueid.sdk.models.DocType("PASSPORT", 0);
        vn.ai.trueid.sdk.models.DocType.ID_CARD = new vn.ai.trueid.sdk.models.DocType("ID_CARD", 1);
        vn.ai.trueid.sdk.models.DocType.OTHER = new vn.ai.trueid.sdk.models.DocType("OTHER", 2);
        vn.ai.trueid.sdk.models.DocType.$VALUES = vn.ai.trueid.sdk.models.DocType.$values();
        return;
    }

    private DocType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.models.DocType valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.models.DocType) Enum.valueOf(vn.ai.trueid.sdk.models.DocType, p1));
    }

    public static vn.ai.trueid.sdk.models.DocType[] values()
    {
        return ((vn.ai.trueid.sdk.models.DocType[]) vn.ai.trueid.sdk.models.DocType.$VALUES.clone());
    }
}
