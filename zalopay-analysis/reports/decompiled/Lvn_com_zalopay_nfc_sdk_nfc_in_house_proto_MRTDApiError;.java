package vn.com.zalopay.nfc_sdk.nfc_in_house.proto;
public final class MRTDApiError extends java.lang.Exception {
    public final String a;

    public MRTDApiError(String p1, gX.l p2)
    {
        super(p1);
        super.a = p1;
        return;
    }

    public final String getMessage()
    {
        return this.a;
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.e("MRTDApiError: ", this.a);
    }
}
