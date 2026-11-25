package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankWebThreeDsStateModel$ResultProcessingScreen extends uo.M {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final String zpTransId;

    static BankWebThreeDsStateModel$ResultProcessingScreen()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen.CREATOR = new uo.L(2);
        return;
    }

    public BankWebThreeDsStateModel$ResultProcessingScreen(String p1, String p2, int p3)
    {
        this.bankCode = p1;
        this.zpTransId = p2;
        this.bcErrorCode = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen p0, String p1, String p2, int p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.zpTransId;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.bcErrorCode;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.zpTransId;
    }

    public final int component3()
    {
        return this.bcErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen copy(String p2, String p3, int p4)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen) p5).zpTransId)) {
                        if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultProcessingScreen) p5).bcErrorCode) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((this.bankCode.hashCode() * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.zpTransId;
        int v2 = this.bcErrorCode;
        StringBuilder v3_1 = new StringBuilder("ResultProcessingScreen(bankCode=");
        v3_1.append(v0_0);
        v3_1.append(", zpTransId=");
        v3_1.append(v1);
        v3_1.append(", bcErrorCode=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bcErrorCode);
        return;
    }
}
