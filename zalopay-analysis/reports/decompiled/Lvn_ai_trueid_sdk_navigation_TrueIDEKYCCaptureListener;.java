package vn.ai.trueid.sdk.navigation;
public interface TrueIDEKYCCaptureListener {

    public abstract void onApiCall(String p0, java.util.Map p1, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p2);

    public abstract void onComplete(vn.ai.trueid.sdk.models.VerificationResult p0);
}
