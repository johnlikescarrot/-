package vn.ai.trueid.sdk.models;
public final class TrueError {
    private static final vn.ai.trueid.sdk.models.TrueError BACKGROUND_MODE;
    public static final vn.ai.trueid.sdk.models.TrueError$Companion Companion;
    private static final vn.ai.trueid.sdk.models.TrueError DATA_ERROR;
    private static final vn.ai.trueid.sdk.models.TrueError EKYC_REJECTED_BEFORE_NFC;
    private static final vn.ai.trueid.sdk.models.TrueError ERROR_CONFIG_SDK;
    private static final vn.ai.trueid.sdk.models.TrueError ID_NUMBER_MISMATCH;
    private static final vn.ai.trueid.sdk.models.TrueError ID_NUMBER_TOO_SHORT;
    private static final vn.ai.trueid.sdk.models.TrueError LIVENESS_SESSION_TIMEOUT;
    private static final vn.ai.trueid.sdk.models.TrueError MAX_RETRY;
    private static final vn.ai.trueid.sdk.models.TrueError NFC_DECODE_ERROR;
    private static final vn.ai.trueid.sdk.models.TrueError NFC_EXPIRED;
    private static final vn.ai.trueid.sdk.models.TrueError NFC_NOT_AVAILABLE;
    private static final vn.ai.trueid.sdk.models.TrueError NFC_NOT_ENABLED;
    private static final vn.ai.trueid.sdk.models.TrueError NOT_SUPPORT_SIMULATOR;
    private static final int NetworkError;
    private static final vn.ai.trueid.sdk.models.TrueError PERMISSION_DENIED;
    private static final vn.ai.trueid.sdk.models.TrueError QR_NFC_MISMATCH;
    private static final vn.ai.trueid.sdk.models.TrueError SELFIE_NOT_MATCH_FACE_NFC;
    private static final vn.ai.trueid.sdk.models.TrueError SERVER_UNAVAILABLE;
    private static final vn.ai.trueid.sdk.models.TrueError Success;
    private static final vn.ai.trueid.sdk.models.TrueError USER_BLOCKED;
    private static final vn.ai.trueid.sdk.models.TrueError USER_CANCELLED;
    private static final vn.ai.trueid.sdk.models.TrueError connectionError;
    private static final vn.ai.trueid.sdk.models.TrueError sessionTimeout;
    private static final vn.ai.trueid.sdk.models.TrueError unknownError;
    private final int code;
    private String message;

    static TrueError()
    {
        vn.ai.trueid.sdk.models.TrueError.Companion = new vn.ai.trueid.sdk.models.TrueError$Companion(0);
        vn.ai.trueid.sdk.models.TrueError.NetworkError = 603;
        vn.ai.trueid.sdk.models.TrueError.Success = new vn.ai.trueid.sdk.models.TrueError(1, "Success");
        vn.ai.trueid.sdk.models.TrueError.USER_CANCELLED = new vn.ai.trueid.sdk.models.TrueError(0, "User cancelled");
        vn.ai.trueid.sdk.models.TrueError.DATA_ERROR = new vn.ai.trueid.sdk.models.TrueError(400, "data response incorrect format");
        vn.ai.trueid.sdk.models.TrueError.connectionError = new vn.ai.trueid.sdk.models.TrueError(603);
        vn.ai.trueid.sdk.models.TrueError.SERVER_UNAVAILABLE = new vn.ai.trueid.sdk.models.TrueError(603, "Cannot connect to server. Please try again later.");
        vn.ai.trueid.sdk.models.TrueError.sessionTimeout = new vn.ai.trueid.sdk.models.TrueError(301, "Liveness session timeout");
        vn.ai.trueid.sdk.models.TrueError.unknownError = new vn.ai.trueid.sdk.models.TrueError(604, "Something error");
        vn.ai.trueid.sdk.models.TrueError.NFC_NOT_ENABLED = new vn.ai.trueid.sdk.models.TrueError(101, "NFC is not enabled");
        vn.ai.trueid.sdk.models.TrueError.NFC_NOT_AVAILABLE = new vn.ai.trueid.sdk.models.TrueError(102, "NFC not available");
        vn.ai.trueid.sdk.models.TrueError.ERROR_CONFIG_SDK = new vn.ai.trueid.sdk.models.TrueError(300, "Error config SDK");
        vn.ai.trueid.sdk.models.TrueError.LIVENESS_SESSION_TIMEOUT = new vn.ai.trueid.sdk.models.TrueError(301, "Liveness session timeout");
        vn.ai.trueid.sdk.models.TrueError.PERMISSION_DENIED = new vn.ai.trueid.sdk.models.TrueError(302, "Permission denied");
        vn.ai.trueid.sdk.models.TrueError.NOT_SUPPORT_SIMULATOR = new vn.ai.trueid.sdk.models.TrueError(303, "SDK does not support simulator");
        vn.ai.trueid.sdk.models.TrueError.BACKGROUND_MODE = new vn.ai.trueid.sdk.models.TrueError(304, "App is running in background mode");
        vn.ai.trueid.sdk.models.TrueError.MAX_RETRY = new vn.ai.trueid.sdk.models.TrueError(305, "Max retry");
        vn.ai.trueid.sdk.models.TrueError.QR_NFC_MISMATCH = new vn.ai.trueid.sdk.models.TrueError(307, "QR info mismatches NFC info");
        vn.ai.trueid.sdk.models.TrueError.SELFIE_NOT_MATCH_FACE_NFC = new vn.ai.trueid.sdk.models.TrueError(308, "Selfie not match with NFC face");
        vn.ai.trueid.sdk.models.TrueError.ID_NUMBER_TOO_SHORT = new vn.ai.trueid.sdk.models.TrueError(309, "ID number is less than 12 digits");
        vn.ai.trueid.sdk.models.TrueError.ID_NUMBER_MISMATCH = new vn.ai.trueid.sdk.models.TrueError(310, "ID number mismatch with NFC chip");
        vn.ai.trueid.sdk.models.TrueError.NFC_EXPIRED = new vn.ai.trueid.sdk.models.TrueError(311, "NFC expired date reached");
        vn.ai.trueid.sdk.models.TrueError.NFC_DECODE_ERROR = new vn.ai.trueid.sdk.models.TrueError(312, "NFC decode error or tampered NFC");
        vn.ai.trueid.sdk.models.TrueError.EKYC_REJECTED_BEFORE_NFC = new vn.ai.trueid.sdk.models.TrueError(313, "eKYC result rejected before NFC check with RAR");
        vn.ai.trueid.sdk.models.TrueError.USER_BLOCKED = new vn.ai.trueid.sdk.models.TrueError(331, "User is blocked");
        return;
    }

    public TrueError(int p7)
    {
        this(p7, "");
        if (p7 == 4) {
            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955495);
            return;
        } else {
            if (p7 == 10) {
                this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955566);
                return;
            } else {
                if (p7 != 36) {
                    if ((p7 != 84) && (p7 != 104)) {
                        if (p7 == 110) {
                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465);
                            return;
                        } else {
                            if (p7 == 401) {
                                this.message = "token_expired";
                                return;
                            } else {
                                if (p7 == 603) {
                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955470);
                                    return;
                                } else {
                                    if (p7 == 14) {
                                        this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955492);
                                        return;
                                    } else {
                                        if (p7 == 15) {
                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955566);
                                            return;
                                        } else {
                                            if (p7 == 17) {
                                                this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955467);
                                                return;
                                            } else {
                                                if (p7 != 18) {
                                                    if (p7 != 33) {
                                                        if (p7 == 34) {
                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955498);
                                                            return;
                                                        } else {
                                                            if (p7 == 101) {
                                                                this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955519);
                                                                return;
                                                            } else {
                                                                if (p7 == 102) {
                                                                    this.message = "Device not support NFC";
                                                                    return;
                                                                } else {
                                                                    switch (p7) {
                                                                        case -6:
                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465);
                                                                            return;
                                                                        case -5:
                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955458);
                                                                            return;
                                                                        case -4:
                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955460);
                                                                            return;
                                                                        case -3:
                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955459);
                                                                            return;
                                                                        case -2:
                                                                        case -1:
                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955461);
                                                                            return;
                                                                        case 0:
                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465);
                                                                            return;
                                                                        case 1:
                                                                            this.message = "";
                                                                            return;
                                                                        default:
                                                                            switch (p7) {
                                                                                case 23:
                                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955493);
                                                                                    return;
                                                                                case 27:
                                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955491);
                                                                                    return;
                                                                                case 28:
                                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955471);
                                                                                    return;
                                                                                case 31:
                                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955434);
                                                                                    return;
                                                                                default:
                                                                                    switch (p7) {
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        case 41:
                                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955457);
                                                                                            return;
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        case 39:
                                                                                        case 40:
                                                                                        case 43:
                                                                                        case 44:
                                                                                        case 45:
                                                                                        case 46:
                                                                                        case 47:
                                                                                        case 48:
                                                                                            break;
                                                                                        default:
                                                                                            switch (p7) {
                                                                                                case 51:
                                                                                                case 52:
                                                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955456);
                                                                                                    return;
                                                                                                case 53:
                                                                                                case 54:
                                                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955430);
                                                                                                    return;
                                                                                                case 55:
                                                                                                    break;
                                                                                                default:
                                                                                                    switch (p7) {
                                                                                                        case 64:
                                                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955498);
                                                                                                            return;
                                                                                                        case 65:
                                                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955528);
                                                                                                            return;
                                                                                                        default:
                                                                                                            this.message = "Something error";
                                                                                                            return;
                                                                                                    }
                                                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955498);
                                                                                                    return;
                                                                                            }
                                                                                            this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955566);
                                                                                            return;
                                                                                    }
                                                                            }
                                                                    }
                                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955566);
                                                                    return;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955528);
                                                    return;
                                                }
                                                this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955498);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955498);
                    return;
                }
                this.message = vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955566);
                return;
            }
        }
    }

    public TrueError(int p1, String p2)
    {
        this.code = p1;
        this.message = p2;
        return;
    }

    public synthetic TrueError(int p1, String p2, int p3, kotlin.jvm.internal.DefaultConstructorMarker p4)
    {
        if ((p3 & 2) != 0) {
            p2 = "";
        }
        this(p1, p2);
        return;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getBACKGROUND_MODE$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.BACKGROUND_MODE;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getConnectionError$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.connectionError;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getDATA_ERROR$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.DATA_ERROR;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getEKYC_REJECTED_BEFORE_NFC$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.EKYC_REJECTED_BEFORE_NFC;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getERROR_CONFIG_SDK$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.ERROR_CONFIG_SDK;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getID_NUMBER_MISMATCH$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.ID_NUMBER_MISMATCH;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getID_NUMBER_TOO_SHORT$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.ID_NUMBER_TOO_SHORT;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getLIVENESS_SESSION_TIMEOUT$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.LIVENESS_SESSION_TIMEOUT;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getMAX_RETRY$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.MAX_RETRY;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getNFC_DECODE_ERROR$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.NFC_DECODE_ERROR;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getNFC_EXPIRED$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.NFC_EXPIRED;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getNFC_NOT_AVAILABLE$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.NFC_NOT_AVAILABLE;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getNFC_NOT_ENABLED$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.NFC_NOT_ENABLED;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getNOT_SUPPORT_SIMULATOR$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.NOT_SUPPORT_SIMULATOR;
    }

    public static final synthetic int access$getNetworkError$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.NetworkError;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getPERMISSION_DENIED$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.PERMISSION_DENIED;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getQR_NFC_MISMATCH$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.QR_NFC_MISMATCH;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getSELFIE_NOT_MATCH_FACE_NFC$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.SELFIE_NOT_MATCH_FACE_NFC;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getSERVER_UNAVAILABLE$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.SERVER_UNAVAILABLE;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getSessionTimeout$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.sessionTimeout;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getSuccess$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.Success;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getUSER_BLOCKED$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.USER_BLOCKED;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getUSER_CANCELLED$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.USER_CANCELLED;
    }

    public static final synthetic vn.ai.trueid.sdk.models.TrueError access$getUnknownError$cp()
    {
        return vn.ai.trueid.sdk.models.TrueError.unknownError;
    }

    public static synthetic vn.ai.trueid.sdk.models.TrueError copy$default(vn.ai.trueid.sdk.models.TrueError p0, int p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.message;
        }
        return p0.copy(p1, p2);
    }

    public final int component1()
    {
        return this.code;
    }

    public final String component2()
    {
        return this.message;
    }

    public final vn.ai.trueid.sdk.models.TrueError copy(int p2, String p3)
    {
        return new vn.ai.trueid.sdk.models.TrueError(p2, p3);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.TrueError)) {
                if (this.code == ((vn.ai.trueid.sdk.models.TrueError) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.ai.trueid.sdk.models.TrueError) p5).message)) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int getCode()
    {
        return this.code;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public int hashCode()
    {
        return (this.message.hashCode() + (Integer.hashCode(this.code) * 31));
    }

    public final void setMessage(String p1)
    {
        this.message = p1;
        return;
    }

    public String toString()
    {
        return com.alibaba.ariver.commonability.device.jsapi.clipboard.a.g(this.code, "TrueError(code=", ", message=", this.message, ")");
    }
}
