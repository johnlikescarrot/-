package vn.com.zalopay.nfc_sdk.nfc_in_house.lds;
public final class EfParseError extends java.lang.Exception {

    public final String toString()
    {
        String v0 = this.getMessage();
        if (v0 == null) {
            v0 = "EfParseError";
        }
        return v0;
    }
}
