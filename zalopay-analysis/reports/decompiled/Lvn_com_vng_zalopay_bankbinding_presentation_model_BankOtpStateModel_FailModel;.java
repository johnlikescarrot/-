package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankOtpStateModel$FailModel extends uo.z implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final String description;
    private final int errorCode;
    private final String title;
    private final String zpTransId;

    static BankOtpStateModel$FailModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel.CREATOR = new uo.y(2);
        return;
    }

    public BankOtpStateModel$FailModel(String p1, String p2, String p3, String p4, int p5, int p6)
    {
        this.title = p1;
        this.description = p2;
        this.bankCode = p3;
        this.zpTransId = p4;
        this.bcErrorCode = p5;
        this.errorCode = p6;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel p0, String p1, String p2, String p3, String p4, int p5, int p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.description;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.bankCode;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.zpTransId;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.bcErrorCode;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.errorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final String component1()
    {
        return this.title;
    }

    public final String component2()
    {
        return this.description;
    }

    public final String component3()
    {
        return this.bankCode;
    }

    public final String component4()
    {
        return this.zpTransId;
    }

    public final int component5()
    {
        return this.bcErrorCode;
    }

    public final int component6()
    {
        return this.errorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel copy(String p8, String p9, String p10, String p11, int p12, int p13)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.description, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel) p5).description)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel) p5).bankCode)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel) p5).zpTransId)) {
                                if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel) p5).bcErrorCode) {
                                    if (this.errorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$FailModel) p5).errorCode) {
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

    public final String getDescription()
    {
        return this.description;
    }

    public final int getErrorCode()
    {
        return this.errorCode;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v0_10;
        int v0_0 = this.title;
        int v1_0 = 0;
        if (v0_0 != 0) {
            v0_10 = v0_0.hashCode();
        } else {
            v0_10 = 0;
        }
        int v0_11 = (v0_10 * 31);
        String v2 = this.description;
        if (v2 != null) {
            v1_0 = v2.hashCode();
        }
        return (((((((((v0_11 + v1_0) * 31) + this.bankCode.hashCode()) * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + Integer.hashCode(this.errorCode));
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.description;
        String v2 = this.bankCode;
        String v3 = this.zpTransId;
        int v4 = this.bcErrorCode;
        int v5 = this.errorCode;
        StringBuilder v6_1 = new StringBuilder("FailModel(title=");
        v6_1.append(v0_0);
        v6_1.append(", description=");
        v6_1.append(v1);
        v6_1.append(", bankCode=");
        v6_1.append(v2);
        v6_1.append(", zpTransId=");
        v6_1.append(v3);
        v6_1.append(", bcErrorCode=");
        v6_1.append(v4);
        v6_1.append(", errorCode=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.title);
        p1.writeString(this.description);
        p1.writeString(this.bankCode);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bcErrorCode);
        p1.writeInt(this.errorCode);
        return;
    }
}
