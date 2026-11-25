package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankWebPopupResultArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final no.g Companion;
    private static final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument DEFAULT;
    private final String bankCode;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final int linkingType;
    private final int resultCode;
    private final String transId;

    static BankWebPopupResultArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument.Companion = new no.g();
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument.CREATOR = new no.e(9);
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument.DEFAULT = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument(0, 0, "", "", 0, 0);
        return;
    }

    public BankWebPopupResultArgument(int p1, int p2, String p3, String p4, int p5, int p6)
    {
        this.resultCode = p1;
        this.bcErrorCode = p2;
        this.bankCode = p3;
        this.transId = p4;
        this.linkingType = p5;
        this.bmErrorCode = p6;
        return;
    }

    public static final synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument access$getDEFAULT$cp()
    {
        return vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument.DEFAULT;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument p0, int p1, int p2, String p3, String p4, int p5, int p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.resultCode;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.bcErrorCode;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.bankCode;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.transId;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.linkingType;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final int component1()
    {
        return this.resultCode;
    }

    public final int component2()
    {
        return this.bcErrorCode;
    }

    public final String component3()
    {
        return this.bankCode;
    }

    public final String component4()
    {
        return this.transId;
    }

    public final int component5()
    {
        return this.linkingType;
    }

    public final int component6()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument copy(int p8, int p9, String p10, String p11, int p12, int p13)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument)) {
                if (this.resultCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument) p5).resultCode) {
                    if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument) p5).bcErrorCode) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument) p5).bankCode)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.transId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument) p5).transId)) {
                                if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument) p5).linkingType) {
                                    if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupResultArgument) p5).bmErrorCode) {
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

    public final int getBmErrorCode()
    {
        return this.bmErrorCode;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final int getResultCode()
    {
        return this.resultCode;
    }

    public final String getTransId()
    {
        return this.transId;
    }

    public int hashCode()
    {
        return ((((((((((Integer.hashCode(this.resultCode) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.bankCode.hashCode()) * 31) + this.transId.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.resultCode;
        int v1 = this.bcErrorCode;
        String v2 = this.bankCode;
        String v3 = this.transId;
        int v4 = this.linkingType;
        int v5 = this.bmErrorCode;
        StringBuilder v6_1 = new StringBuilder("BankWebPopupResultArgument(resultCode=");
        v6_1.append(v0_0);
        v6_1.append(", bcErrorCode=");
        v6_1.append(v1);
        v6_1.append(", bankCode=");
        v6_1.append(v2);
        v6_1.append(", transId=");
        v6_1.append(v3);
        v6_1.append(", linkingType=");
        v6_1.append(v4);
        v6_1.append(", bmErrorCode=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.resultCode);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.bankCode);
        p1.writeString(this.transId);
        p1.writeInt(this.linkingType);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
