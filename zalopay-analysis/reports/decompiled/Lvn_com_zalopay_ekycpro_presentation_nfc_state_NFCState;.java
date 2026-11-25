package vn.com.zalopay.ekycpro.presentation.nfc.state;
public interface annotation NFCState implements java.lang.annotation.Annotation {
    public static final int ACCESS_DENIED = 255;
    public static final int BAC_DENIED = 254;
    public static final int CHIP_DETECTED = 4;
    public static final vn.com.zalopay.ekycpro.presentation.nfc.state.NFCState$Companion Companion = None;
    public static final int FINISH = 3;
    public static final int GENERAL_ERROR = 0;
    public static final int IDENTITY_VERIFICATION_DONE = 6;
    public static final int ID_CARD_ERROR = 0;
    public static final int PACE_EXCEPTION = 253;
    public static final int READY_TO_SCAN = 0;
    public static final int START = 0;
    public static final int SUBMIT_TO_VERIFY_WITH_C06 = 5;
    public static final int SUCCESS = 1;
    public static final int TAG_LOST_CONNECT_EXCEPTION = 250;

    static NFCState()
    {
        wmoi.igob.jtgo(vn.com.zalopay.ekycpro.presentation.nfc.state.NFCState, 18641, 18643);
        vn.com.zalopay.ekycpro.presentation.nfc.state.NFCState.Companion = vn.com.zalopay.ekycpro.presentation.nfc.state.NFCState$Companion.$$INSTANCE;
        return;
    }
}
