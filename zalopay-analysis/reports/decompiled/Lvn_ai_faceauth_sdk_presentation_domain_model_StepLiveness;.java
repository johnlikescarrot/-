package vn.ai.faceauth.sdk.presentation.domain.model;
public final enum class StepLiveness extends java.lang.Enum implements android.os.Parcelable {
    private static final synthetic vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness[] $VALUES;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final enum vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness STEP_FIRST_CHECK;
    public static final enum vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness STEP_ZOOM_IN;
    public static final enum vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness STEP_ZOOM_OUT;
    private final String textValue;

    private static final synthetic vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness[] $values()
    {
        return new vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness[] {vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.STEP_FIRST_CHECK, vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.STEP_ZOOM_IN, vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.STEP_ZOOM_OUT});
    }

    static StepLiveness()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(7);
        vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.STEP_FIRST_CHECK = new vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness(jipm.njcx.zbjt(56), 0, jipm.njcx.zbjt(55));
        vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.STEP_ZOOM_IN = new vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness(jipm.njcx.zbjt(58), 1, jipm.njcx.zbjt(57));
        vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.STEP_ZOOM_OUT = new vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness(jipm.njcx.zbjt(60), 2, jipm.njcx.zbjt(59));
        vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.$VALUES = vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.$values();
        vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.CREATOR = new vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness$Creator();
        return;
    }

    private StepLiveness(String p1, int p2, String p3)
    {
        super(p1, p2);
        super.textValue = p3;
        return;
    }

    public static native vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness valueOf(String p0);

    public static native vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness[] values();

    public native int describeContents();

    public final native String getTextValue();

    public native void writeToParcel(android.os.Parcel p0, int p1);
}
