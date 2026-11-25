package vn.com.vng.zalopay.nfc.state;
public final class NFCState$Companion {
    static final synthetic vn.com.vng.zalopay.nfc.state.NFCState$Companion $$INSTANCE = None;
    public static final int ACCESS_DENIED = 255;
    public static final int BAC_DENIED = 254;
    public static final int CHIP_DETECTED = 4;
    public static final int FINISH = 3;
    public static final int GENERAL_ERROR = 251;
    public static final int IDENTITY_VERIFICATION_DONE = 6;
    public static final int ID_CARD_ERROR = 252;
    public static final int INIT_PREVIOUS_EKYC_INFO_ERROR = 248;
    public static final int PACE_EXCEPTION = 253;
    public static final int READY_TO_SCAN = 0;
    public static final int START = 2;
    public static final int SUBMIT_TO_VERIFY_WITH_C06 = 5;
    public static final int SUCCESS = 1;
    public static final int TAG_LOST_CONNECT_EXCEPTION;

    static NFCState$Companion()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.nfc.state.NFCState$Companion, 17052, 17053);
        vn.com.vng.zalopay.nfc.state.NFCState$Companion.$$INSTANCE = new vn.com.vng.zalopay.nfc.state.NFCState$Companion();
        return;
    }

    private NFCState$Companion()
    {
        return;
    }
}
