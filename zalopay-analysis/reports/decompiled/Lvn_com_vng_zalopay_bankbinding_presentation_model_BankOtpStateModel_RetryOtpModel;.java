package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankOtpStateModel$RetryOtpModel extends uo.z implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String message;
    private final String zpTransId;

    static BankOtpStateModel$RetryOtpModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel.CREATOR = new uo.y(6);
        return;
    }

    public BankOtpStateModel$RetryOtpModel(String p1, String p2, int p3, int p4)
    {
        this.message = p1;
        this.zpTransId = p2;
        this.bcErrorCode = p3;
        this.bmErrorCode = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel p0, String p1, String p2, int p3, int p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.zpTransId;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.bcErrorCode;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.message;
    }

    public final String component2()
    {
        return this.zpTransId;
    }

    public final int component3()
    {
        return this.bcErrorCode;
    }

    public final int component4()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel copy(String p2, String p3, int p4, int p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel) p5).message)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel) p5).zpTransId)) {
                        if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel) p5).bcErrorCode) {
                            if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$RetryOtpModel) p5).bmErrorCode) {
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

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((this.message.hashCode() * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.message;
        String v1 = this.zpTransId;
        int v2 = this.bcErrorCode;
        int v3 = this.bmErrorCode;
        StringBuilder v4_1 = new StringBuilder("RetryOtpModel(message=");
        v4_1.append(v0_0);
        v4_1.append(", zpTransId=");
        v4_1.append(v1);
        v4_1.append(", bcErrorCode=");
        v4_1.append(v2);
        v4_1.append(", bmErrorCode=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bcErrorCode);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
