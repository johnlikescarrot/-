package vn.ai.faceauth.sdk.presentation.domain.configs;
public final class LivenessConfig implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final float _blurProbability;
    private final float _eyeOpenProbability;
    private final float _luminosityProbability;
    private final float _smileProbability;

    static LivenessConfig()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(41);
        vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig.CREATOR = new vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig$Creator();
        return;
    }

    public LivenessConfig()
    {
        this(0, 0, 0, 0, 15, 0);
        return;
    }

    public LivenessConfig(float p1, float p2, float p3, float p4)
    {
        this._eyeOpenProbability = p1;
        this._smileProbability = p2;
        this._blurProbability = p3;
        this._luminosityProbability = p4;
        return;
    }

    public synthetic LivenessConfig(float p1, float p2, float p3, float p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 1) != 0) {
            p1 = vn.ai.faceauth.sdk.presentation.domain.model.Constants.Companion.getEYE_OPENED_PROBABILITY();
        }
        if ((p5 & 2) != 0) {
            p2 = vn.ai.faceauth.sdk.presentation.domain.model.Constants.Companion.getSMILE_PROBABILITY();
        }
        if ((p5 & 4) != 0) {
            p3 = vn.ai.faceauth.sdk.presentation.domain.model.Constants.Companion.getBLUR_PROBABILITY();
        }
        if ((p5 & 8) != 0) {
            p4 = vn.ai.faceauth.sdk.presentation.domain.model.Constants.Companion.getLUMINOSITY_PROBABILITY();
        }
        this(p1, p2, p3, p4);
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig copy$default(vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig p0, float p1, float p2, float p3, float p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0._eyeOpenProbability;
        }
        if ((p5 & 2) != 0) {
            p2 = p0._smileProbability;
        }
        if ((p5 & 4) != 0) {
            p3 = p0._blurProbability;
        }
        if ((p5 & 8) != 0) {
            p4 = p0._luminosityProbability;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final native float component1();

    public final native float component2();

    public final native float component3();

    public final native float component4();

    public final native vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig copy(float p0, float p1, float p2, float p3);

    public native int describeContents();

    public native boolean equals(Object p0);

    public final native float getBlurProbability();

    public final native float getEyeOpenProbability();

    public final native float getLuminosityProbability();

    public final native float getSmileProbability();

    public final native float get_blurProbability();

    public final native float get_eyeOpenProbability();

    public final native float get_luminosityProbability();

    public final native float get_smileProbability();

    public native int hashCode();

    public native String toString();

    public native void writeToParcel(android.os.Parcel p0, int p1);
}
