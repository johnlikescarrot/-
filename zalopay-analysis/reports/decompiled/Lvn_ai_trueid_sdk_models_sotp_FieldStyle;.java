package vn.ai.trueid.sdk.models.sotp;
public final enum class FieldStyle extends java.lang.Enum implements java.io.Serializable {
    private static final synthetic vn.ai.trueid.sdk.models.sotp.FieldStyle[] $VALUES;
    public static final enum vn.ai.trueid.sdk.models.sotp.FieldStyle DANGER;
    public static final enum vn.ai.trueid.sdk.models.sotp.FieldStyle HIGHLIGHT;
    public static final enum vn.ai.trueid.sdk.models.sotp.FieldStyle MUTED;
    public static final enum vn.ai.trueid.sdk.models.sotp.FieldStyle NORMAL;
    public static final enum vn.ai.trueid.sdk.models.sotp.FieldStyle WARNING;

    private static final synthetic vn.ai.trueid.sdk.models.sotp.FieldStyle[] $values()
    {
        return new vn.ai.trueid.sdk.models.sotp.FieldStyle[] {vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL, vn.ai.trueid.sdk.models.sotp.FieldStyle.HIGHLIGHT, vn.ai.trueid.sdk.models.sotp.FieldStyle.WARNING, vn.ai.trueid.sdk.models.sotp.FieldStyle.DANGER, vn.ai.trueid.sdk.models.sotp.FieldStyle.MUTED});
    }

    static FieldStyle()
    {
        vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL = new vn.ai.trueid.sdk.models.sotp.FieldStyle("NORMAL", 0);
        vn.ai.trueid.sdk.models.sotp.FieldStyle.HIGHLIGHT = new vn.ai.trueid.sdk.models.sotp.FieldStyle("HIGHLIGHT", 1);
        vn.ai.trueid.sdk.models.sotp.FieldStyle.WARNING = new vn.ai.trueid.sdk.models.sotp.FieldStyle("WARNING", 2);
        vn.ai.trueid.sdk.models.sotp.FieldStyle.DANGER = new vn.ai.trueid.sdk.models.sotp.FieldStyle("DANGER", 3);
        vn.ai.trueid.sdk.models.sotp.FieldStyle.MUTED = new vn.ai.trueid.sdk.models.sotp.FieldStyle("MUTED", 4);
        vn.ai.trueid.sdk.models.sotp.FieldStyle.$VALUES = vn.ai.trueid.sdk.models.sotp.FieldStyle.$values();
        return;
    }

    private FieldStyle(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.models.sotp.FieldStyle valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.models.sotp.FieldStyle) Enum.valueOf(vn.ai.trueid.sdk.models.sotp.FieldStyle, p1));
    }

    public static vn.ai.trueid.sdk.models.sotp.FieldStyle[] values()
    {
        return ((vn.ai.trueid.sdk.models.sotp.FieldStyle[]) vn.ai.trueid.sdk.models.sotp.FieldStyle.$VALUES.clone());
    }
}
