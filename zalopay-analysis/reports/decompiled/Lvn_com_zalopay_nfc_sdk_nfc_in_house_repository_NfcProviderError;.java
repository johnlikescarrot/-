package vn.com.zalopay.nfc_sdk.nfc_in_house.repository;
public final class NfcProviderError extends vn.com.zalopay.nfc_sdk.nfc_in_house.domain.ComProviderError {

    public NfcProviderError()
    {
        super("");
        return;
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.e("NfcProviderError: ", this.getMessage());
    }
}
