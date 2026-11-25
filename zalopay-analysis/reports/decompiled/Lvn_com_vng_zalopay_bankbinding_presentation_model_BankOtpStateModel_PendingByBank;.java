package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankOtpStateModel$PendingByBank extends uo.z implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final String description;
    private final String title;
    private final String zpTransId;

    static BankOtpStateModel$PendingByBank()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank.CREATOR = new uo.y(4);
        return;
    }

    public BankOtpStateModel$PendingByBank(String p1, String p2, String p3, String p4, int p5)
    {
        this.title = p1;
        this.description = p2;
        this.bankCode = p3;
        this.zpTransId = p4;
        this.bcErrorCode = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank p0, String p1, String p2, String p3, String p4, int p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.description;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.bankCode;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.zpTransId;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.bcErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5);
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

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank copy(String p7, String p8, String p9, String p10, int p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.description, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank) p5).description)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank) p5).bankCode)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank) p5).zpTransId)) {
                                if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$PendingByBank) p5).bcErrorCode) {
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
        int v0_8;
        int v0_0 = this.title;
        int v1_0 = 0;
        if (v0_0 != 0) {
            v0_8 = v0_0.hashCode();
        } else {
            v0_8 = 0;
        }
        int v0_9 = (v0_8 * 31);
        String v2 = this.description;
        if (v2 != null) {
            v1_0 = v2.hashCode();
        }
        return (((((((v0_9 + v1_0) * 31) + this.bankCode.hashCode()) * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.description;
        String v2 = this.bankCode;
        String v3 = this.zpTransId;
        int v4 = this.bcErrorCode;
        StringBuilder v5_1 = new StringBuilder("PendingByBank(title=");
        v5_1.append(v0_0);
        v5_1.append(", description=");
        v5_1.append(v1);
        v5_1.append(", bankCode=");
        v5_1.append(v2);
        v5_1.append(", zpTransId=");
        v5_1.append(v3);
        v5_1.append(", bcErrorCode=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.title);
        p1.writeString(this.description);
        p1.writeString(this.bankCode);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bcErrorCode);
        return;
    }
}
