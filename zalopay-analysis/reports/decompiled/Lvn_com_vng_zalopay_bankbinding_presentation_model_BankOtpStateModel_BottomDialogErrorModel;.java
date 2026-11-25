package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankOtpStateModel$BottomDialogErrorModel extends uo.z implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String message;
    private final int page;
    private final String zpTransId;

    static BankOtpStateModel$BottomDialogErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel.CREATOR = new uo.y(0);
        return;
    }

    public BankOtpStateModel$BottomDialogErrorModel(String p1, int p2, int p3, String p4, int p5)
    {
        this.message = p1;
        this.page = p2;
        this.bcErrorCode = p3;
        this.zpTransId = p4;
        this.bmErrorCode = p5;
        return;
    }

    public synthetic BankOtpStateModel$BottomDialogErrorModel(String p7, int p8, int p9, String p10, int p11, int p12, kotlin.jvm.internal.DefaultConstructorMarker p13)
    {
        int v2;
        if ((p12 & 2) == 0) {
            v2 = p8;
        } else {
            v2 = 2;
        }
        this(p7, v2, p9, p10, p11);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel p0, String p1, int p2, int p3, String p4, int p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.page;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.bcErrorCode;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.zpTransId;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.message;
    }

    public final int component2()
    {
        return this.page;
    }

    public final int component3()
    {
        return this.bcErrorCode;
    }

    public final String component4()
    {
        return this.zpTransId;
    }

    public final int component5()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel copy(String p7, int p8, int p9, String p10, int p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel) p5).message)) {
                    if (this.page == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel) p5).page) {
                        if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel) p5).bcErrorCode) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel) p5).zpTransId)) {
                                if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$BottomDialogErrorModel) p5).bmErrorCode) {
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

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final int getBmErrorCode()
    {
        return this.bmErrorCode;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final int getPage()
    {
        return this.page;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((((this.message.hashCode() * 31) + Integer.hashCode(this.page)) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.message;
        int v1 = this.page;
        int v2 = this.bcErrorCode;
        String v3 = this.zpTransId;
        int v4 = this.bmErrorCode;
        StringBuilder v5_1 = new StringBuilder("BottomDialogErrorModel(message=");
        v5_1.append(v0_0);
        v5_1.append(", page=");
        v5_1.append(v1);
        v5_1.append(", bcErrorCode=");
        v5_1.append(v2);
        v5_1.append(", zpTransId=");
        v5_1.append(v3);
        v5_1.append(", bmErrorCode=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeInt(this.page);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
