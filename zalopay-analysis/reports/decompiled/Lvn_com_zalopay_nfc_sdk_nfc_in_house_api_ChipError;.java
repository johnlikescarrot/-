package vn.com.zalopay.nfc_sdk.nfc_in_house.api;
public final class ChipError extends java.lang.Exception {

    public final String toString()
    {
        String v0 = this.getMessage();
        if (v0 == null) {
            v0 = "ChipError";
        }
        return v0;
    }
}
