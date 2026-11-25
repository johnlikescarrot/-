package vn.ai.faceauth.sdk;
public final enum class h extends java.lang.Enum {
    private static final synthetic vn.ai.faceauth.sdk.h[] $VALUES;
    public static final enum vn.ai.faceauth.sdk.h STEP_BLINK;
    public static final enum vn.ai.faceauth.sdk.h STEP_FIRST_CHECK;
    public static final enum vn.ai.faceauth.sdk.h STEP_HEAD_FRONTAL;
    public static final enum vn.ai.faceauth.sdk.h STEP_LUMINOSITY;
    public static final enum vn.ai.faceauth.sdk.h STEP_N0_SMILE;
    public static final enum vn.ai.faceauth.sdk.h STEP_ZOOM_IN;
    public static final enum vn.ai.faceauth.sdk.h STEP_ZOOM_OUT;
    private final String textValue;

    private static final synthetic vn.ai.faceauth.sdk.h[] $values()
    {
        vn.ai.faceauth.sdk.h v2 = vn.ai.faceauth.sdk.h.STEP_HEAD_FRONTAL;
        vn.ai.faceauth.sdk.h v4 = vn.ai.faceauth.sdk.h.STEP_BLINK;
        return new vn.ai.faceauth.sdk.h[] {vn.ai.faceauth.sdk.h.STEP_LUMINOSITY, vn.ai.faceauth.sdk.h.STEP_ZOOM_OUT});
    }

    static h()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(24);
        vn.ai.faceauth.sdk.h.STEP_LUMINOSITY = new vn.ai.faceauth.sdk.h(jipm.njcx.zbjt(68), 0, jipm.njcx.zbjt(67));
        vn.ai.faceauth.sdk.h.STEP_N0_SMILE = new vn.ai.faceauth.sdk.h(jipm.njcx.zbjt(70), 1, jipm.njcx.zbjt(69));
        vn.ai.faceauth.sdk.h.STEP_HEAD_FRONTAL = new vn.ai.faceauth.sdk.h(jipm.njcx.zbjt(72), 2, jipm.njcx.zbjt(71));
        vn.ai.faceauth.sdk.h.STEP_FIRST_CHECK = new vn.ai.faceauth.sdk.h(jipm.njcx.zbjt(74), 3, jipm.njcx.zbjt(73));
        vn.ai.faceauth.sdk.h.STEP_BLINK = new vn.ai.faceauth.sdk.h(jipm.njcx.zbjt(76), 4, jipm.njcx.zbjt(75));
        vn.ai.faceauth.sdk.h.STEP_ZOOM_IN = new vn.ai.faceauth.sdk.h(jipm.njcx.zbjt(78), 5, jipm.njcx.zbjt(77));
        vn.ai.faceauth.sdk.h.STEP_ZOOM_OUT = new vn.ai.faceauth.sdk.h(jipm.njcx.zbjt(80), 6, jipm.njcx.zbjt(79));
        vn.ai.faceauth.sdk.h.$VALUES = vn.ai.faceauth.sdk.h.$values();
        return;
    }

    private h(String p1, int p2, String p3)
    {
        super(p1, p2);
        super.textValue = p3;
        return;
    }

    public static native vn.ai.faceauth.sdk.h valueOf(String p0);

    public static native vn.ai.faceauth.sdk.h[] values();

    public final native String getTextValue();
}
