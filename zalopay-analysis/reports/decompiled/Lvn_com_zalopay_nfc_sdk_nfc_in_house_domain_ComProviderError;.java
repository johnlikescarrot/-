package vn.com.zalopay.nfc_sdk.nfc_in_house.domain;
public class ComProviderError extends java.lang.Exception {

    public ComProviderError()
    {
        this("");
        return;
    }

    public ComProviderError(String p2)
    {
        super(androidx.camera.camera2.internal.G.e("ComProviderError: ", p2));
        return;
    }
}
