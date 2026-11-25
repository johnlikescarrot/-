package vn.ai.trueid.sdk.navigation;
public interface TrueIDLivenessCaptureListener {

    public abstract void onCaptured(java.util.Map p0, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p1);

    public abstract void onComplete(vn.ai.trueid.sdk.models.FaceLivenessResult p0);

    public abstract void onRequestCreate(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p0);
}
