package vn.com.vng.zalopay.exception;
public class KeyInvalidatedException extends java.lang.Exception {

    public KeyInvalidatedException()
    {
        return;
    }

    public final String getMessage()
    {
        return "The key is invalidated cause A new fingerprint was added to this device";
    }
}
