package vn.ai.trueid.sdk.utils.facedetector;
public abstract class DataSignService {

    static DataSignService()
    {
        return;
    }

    public static final synthetic byte[] a(String p0, int p1, byte[] p2)
    {
        return vn.ai.trueid.sdk.utils.facedetector.DataSignService.signPayloadNative(p0, p1, p2);
    }

    private static final native byte[] signPayloadNative(String p0, int p1, byte[] p2);
}
