package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankSuccessArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final java.util.List cta;
    private final String issuerBankName;
    private final int linkingFunctionId;
    private final String message;
    private final int resultCode;
    private final String title;
    private final String zpTransId;

    static BankSuccessArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument.CREATOR = new no.e(5);
        return;
    }

    public BankSuccessArgument(String p1, String p2, int p3, String p4, String p5, int p6, java.util.List p7, String p8)
    {
        this.zpTransId = p1;
        this.bankCode = p2;
        this.resultCode = p3;
        this.issuerBankName = p4;
        this.message = p5;
        this.linkingFunctionId = p6;
        this.cta = p7;
        this.title = p8;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument p0, String p1, String p2, int p3, String p4, String p5, int p6, java.util.List p7, String p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.zpTransId;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.bankCode;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.resultCode;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.issuerBankName;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.message;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.linkingFunctionId;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.cta;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.title;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.zpTransId;
    }

    public final String component2()
    {
        return this.bankCode;
    }

    public final int component3()
    {
        return this.resultCode;
    }

    public final String component4()
    {
        return this.issuerBankName;
    }

    public final String component5()
    {
        return this.message;
    }

    public final int component6()
    {
        return this.linkingFunctionId;
    }

    public final java.util.List component7()
    {
        return this.cta;
    }

    public final String component8()
    {
        return this.title;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument copy(String p10, String p11, int p12, String p13, String p14, int p15, java.util.List p16, String p17)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).zpTransId)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).bankCode)) {
                        if (this.resultCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).resultCode) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).issuerBankName)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).message)) {
                                    if (this.linkingFunctionId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).linkingFunctionId) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.cta, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).cta)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankSuccessArgument) p5).title)) {
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

    public final java.util.List getCta()
    {
        return this.cta;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public final int getLinkingFunctionId()
    {
        return this.linkingFunctionId;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final int getResultCode()
    {
        return this.resultCode;
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
        return ((((((((((((((this.zpTransId.hashCode() * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.resultCode)) * 31) + this.issuerBankName.hashCode()) * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.linkingFunctionId)) * 31) + this.cta.hashCode()) * 31) + this.title.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.zpTransId;
        String v1 = this.bankCode;
        int v2 = this.resultCode;
        String v3 = this.issuerBankName;
        String v4 = this.message;
        int v5 = this.linkingFunctionId;
        java.util.List v6 = this.cta;
        String v7 = this.title;
        StringBuilder v8_0 = new StringBuilder("BankSuccessArgument(zpTransId=");
        v8_0.append(v0_0);
        v8_0.append(", bankCode=");
        v8_0.append(v1);
        v8_0.append(", resultCode=");
        v8_0.append(v2);
        v8_0.append(", issuerBankName=");
        v8_0.append(v3);
        v8_0.append(", message=");
        v8_0.append(v4);
        v8_0.append(", linkingFunctionId=");
        v8_0.append(v5);
        v8_0.append(", cta=");
        v8_0.append(v6);
        v8_0.append(", title=");
        v8_0.append(v7);
        v8_0.append(")");
        return v8_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.zpTransId);
        p3.writeString(this.bankCode);
        p3.writeInt(this.resultCode);
        p3.writeString(this.issuerBankName);
        p3.writeString(this.message);
        p3.writeInt(this.linkingFunctionId);
        java.util.Iterator v0_2 = this.cta;
        p3.writeInt(v0_2.size());
        java.util.Iterator v0_3 = v0_2.iterator();
        while (v0_3.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity) v0_3.next()).writeToParcel(p3, p4);
        }
        p3.writeString(this.title);
        return;
    }
}
