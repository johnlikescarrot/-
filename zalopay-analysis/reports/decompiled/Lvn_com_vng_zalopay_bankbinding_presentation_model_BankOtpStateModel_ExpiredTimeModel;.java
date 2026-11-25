package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankOtpStateModel$ExpiredTimeModel extends uo.z implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String zpTransId;

    static BankOtpStateModel$ExpiredTimeModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel.CREATOR = new uo.y(1);
        return;
    }

    public BankOtpStateModel$ExpiredTimeModel(String p1, String p2)
    {
        this.zpTransId = p1;
        this.bankCode = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.zpTransId;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.bankCode;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.zpTransId;
    }

    public final String component2()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel) p5).zpTransId)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$ExpiredTimeModel) p5).bankCode)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((this.zpTransId.hashCode() * 31) + this.bankCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.zpTransId;
        String v1 = this.bankCode;
        StringBuilder v2_1 = new StringBuilder("ExpiredTimeModel(zpTransId=");
        v2_1.append(v0_0);
        v2_1.append(", bankCode=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.zpTransId);
        p1.writeString(this.bankCode);
        return;
    }
}
