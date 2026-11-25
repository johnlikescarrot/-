package vn.com.zalopay.nfc_sdk.nfc_in_house.proto;
public final class PaceConnectionLostError extends java.lang.Exception {

    public PaceConnectionLostError()
    {
        this(0);
        return;
    }

    public PaceConnectionLostError(Exception p2)
    {
        super("Tag was lost during PACE handshake", p2);
        return;
    }
}
