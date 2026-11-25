package vn.com.vng.zalopay.bankbinding.presentation.argument.fm;
public final class LinkedBankDetailData implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final oo.a Companion;
    private final String bankCode;
    private final String bankConnectorCode;
    private final int bankType;
    private final String firstNum;
    private final String lastNum;

    static LinkedBankDetailData()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData.Companion = new oo.a();
        vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData.CREATOR = new no.e(17);
        return;
    }

    public LinkedBankDetailData(String p1, String p2, int p3, String p4, String p5)
    {
        this.bankCode = p1;
        this.bankConnectorCode = p2;
        this.bankType = p3;
        this.firstNum = p4;
        this.lastNum = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData p0, String p1, String p2, int p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.bankConnectorCode;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.bankType;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.firstNum;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.lastNum;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.bankConnectorCode;
    }

    public final int component3()
    {
        return this.bankType;
    }

    public final String component4()
    {
        return this.firstNum;
    }

    public final String component5()
    {
        return this.lastNum;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData copy(String p7, String p8, int p9, String p10, String p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankConnectorCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData) p5).bankConnectorCode)) {
                        if (this.bankType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData) p5).bankType) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.firstNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData) p5).firstNum)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.lastNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData) p5).lastNum)) {
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

    public final String getBankConnectorCode()
    {
        return this.bankConnectorCode;
    }

    public final int getBankType()
    {
        return this.bankType;
    }

    public final String getFirstNum()
    {
        return this.firstNum;
    }

    public final String getLastNum()
    {
        return this.lastNum;
    }

    public int hashCode()
    {
        return ((((((((this.bankCode.hashCode() * 31) + this.bankConnectorCode.hashCode()) * 31) + Integer.hashCode(this.bankType)) * 31) + this.firstNum.hashCode()) * 31) + this.lastNum.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.bankConnectorCode;
        int v2 = this.bankType;
        String v3 = this.firstNum;
        String v4 = this.lastNum;
        StringBuilder v5_1 = new StringBuilder("LinkedBankDetailData(bankCode=");
        v5_1.append(v0_0);
        v5_1.append(", bankConnectorCode=");
        v5_1.append(v1);
        v5_1.append(", bankType=");
        v5_1.append(v2);
        v5_1.append(", firstNum=");
        v5_1.append(v3);
        v5_1.append(", lastNum=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.bankConnectorCode);
        p1.writeInt(this.bankType);
        p1.writeString(this.firstNum);
        p1.writeString(this.lastNum);
        return;
    }
}
