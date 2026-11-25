package vn.com.vng.zalopay.nfc.state;
public interface annotation NFCState implements java.lang.annotation.Annotation {
    public static final int ACCESS_DENIED = 0;
    public static final int BAC_DENIED = 254;
    public static final int CHIP_DETECTED = 4;
    public static final vn.com.vng.zalopay.nfc.state.NFCState$Companion Companion = None;
    public static final int FINISH = 3;
    public static final int GENERAL_ERROR = 0;
    public static final int IDENTITY_VERIFICATION_DONE = 6;
    public static final int ID_CARD_ERROR = 252;
    public static final int INIT_PREVIOUS_EKYC_INFO_ERROR = 0;
    public static final int PACE_EXCEPTION = 253;
    public static final int READY_TO_SCAN = 0;
    public static final int START = 0;
    public static final int SUBMIT_TO_VERIFY_WITH_C06 = 0;
    public static final int SUCCESS = 1;
    public static final int TAG_LOST_CONNECT_EXCEPTION = 250;

    static NFCState()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.nfc.state.NFCState, 18210, 18214);
        vn.com.vng.zalopay.nfc.state.NFCState.Companion = vn.com.vng.zalopay.nfc.state.NFCState$Companion.$$INSTANCE;
        return;
    }
}
