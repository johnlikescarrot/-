package vn.ai.trueid.sdk.face;
public final synthetic class i0 extends kotlin.jvm.internal.q implements kotlin.jvm.functions.Function1 {

    public i0(vn.ai.trueid.sdk.face.navigation.FaceSDK p8)
    {
        super(1, 0, vn.ai.trueid.sdk.face.navigation.FaceSDK, p8, "postResultCallback", "postResultCallback$face_release(Lvn/ai/trueid/sdk/face/present/domain/model/FaceSDKResult;)V");
        return;
    }

    public final Object invoke(Object p2)
    {
        ((vn.ai.trueid.sdk.face.navigation.FaceSDK) this.receiver).postResultCallback$face_release(((vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult) p2));
        return kotlin.Unit.INSTANCE;
    }
}
