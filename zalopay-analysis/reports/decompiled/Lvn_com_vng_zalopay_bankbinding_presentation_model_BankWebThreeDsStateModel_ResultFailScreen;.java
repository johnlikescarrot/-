package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankWebThreeDsStateModel$ResultFailScreen extends uo.M {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final String description;
    private final String zpTransId;

    static BankWebThreeDsStateModel$ResultFailScreen()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen.CREATOR = new uo.L(1);
        return;
    }

    public BankWebThreeDsStateModel$ResultFailScreen(String p1, String p2, int p3, String p4)
    {
        this.bankCode = p1;
        this.zpTransId = p2;
        this.bcErrorCode = p3;
        this.description = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen p0, String p1, String p2, int p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.zpTransId;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.bcErrorCode;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.description;
        }
        return p0.copy(p1, p2, p3, p4);
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

    public final String component4()
    {
        return this.description;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen copy(String p2, String p3, int p4, String p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen) p5).zpTransId)) {
                        if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen) p5).bcErrorCode) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.description, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankWebThreeDsStateModel$ResultFailScreen) p5).description)) {
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

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((this.bankCode.hashCode() * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.description.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.zpTransId;
        int v2 = this.bcErrorCode;
        String v3 = this.description;
        StringBuilder v4_1 = new StringBuilder("ResultFailScreen(bankCode=");
        v4_1.append(v0_0);
        v4_1.append(", zpTransId=");
        v4_1.append(v1);
        v4_1.append(", bcErrorCode=");
        v4_1.append(v2);
        v4_1.append(", description=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.description);
        return;
    }
}
