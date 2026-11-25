package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankWebPopupArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String confirmButton;
    private final boolean enableFaq;
    private final String issuerBankName;
    private final int linkingType;
    private final int parentId;
    private final String requestCode;
    private final String transId;
    private final String url;

    static BankWebPopupArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument.CREATOR = new no.e(8);
        return;
    }

    public BankWebPopupArgument(int p1, String p2, String p3, String p4, int p5, String p6, String p7, int p8, int p9, String p10, boolean p11)
    {
        this.parentId = p1;
        this.requestCode = p2;
        this.url = p3;
        this.confirmButton = p4;
        this.bcErrorCode = p5;
        this.bankCode = p6;
        this.transId = p7;
        this.linkingType = p8;
        this.bmErrorCode = p9;
        this.issuerBankName = p10;
        this.enableFaq = p11;
        return;
    }

    public synthetic BankWebPopupArgument(int p2, String p3, String p4, String p5, int p6, String p7, String p8, int p9, int p10, String p11, boolean p12, int p13, kotlin.jvm.internal.DefaultConstructorMarker p14)
    {
        if ((p13 & 2) != 0) {
            p3 = "";
        }
        String v13_1;
        String v9_1;
        int v12_1;
        String v6_1;
        int v8_1;
        String v10_1;
        String v7_1;
        int v11_1;
        if ((p13 & 8) == 0) {
            v13_1 = p12;
            v12_1 = p11;
            v11_1 = p10;
            v10_1 = p9;
            v9_1 = p8;
            v8_1 = p7;
            v7_1 = p6;
            v6_1 = p5;
        } else {
            v13_1 = p12;
            v12_1 = p11;
            v11_1 = p10;
            v10_1 = p9;
            v9_1 = p8;
            v8_1 = p7;
            v7_1 = p6;
            v6_1 = "";
        }
        this(p2, p3, p4, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument p0, int p1, String p2, String p3, String p4, int p5, String p6, String p7, int p8, int p9, String p10, boolean p11, int p12, Object p13)
    {
        if ((p12 & 1) != 0) {
            p1 = p0.parentId;
        }
        if ((p12 & 2) != 0) {
            p2 = p0.requestCode;
        }
        if ((p12 & 4) != 0) {
            p3 = p0.url;
        }
        if ((p12 & 8) != 0) {
            p4 = p0.confirmButton;
        }
        if ((p12 & 16) != 0) {
            p5 = p0.bcErrorCode;
        }
        if ((p12 & 32) != 0) {
            p6 = p0.bankCode;
        }
        if ((p12 & 64) != 0) {
            p7 = p0.transId;
        }
        if ((p12 & 128) != 0) {
            p8 = p0.linkingType;
        }
        if ((p12 & 256) != 0) {
            p9 = p0.bmErrorCode;
        }
        if ((p12 & 512) != 0) {
            p10 = p0.issuerBankName;
        }
        if ((p12 & 1024) != 0) {
            p11 = p0.enableFaq;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
    }

    public final int component1()
    {
        return this.parentId;
    }

    public final String component10()
    {
        return this.issuerBankName;
    }

    public final boolean component11()
    {
        return this.enableFaq;
    }

    public final String component2()
    {
        return this.requestCode;
    }

    public final String component3()
    {
        return this.url;
    }

    public final String component4()
    {
        return this.confirmButton;
    }

    public final int component5()
    {
        return this.bcErrorCode;
    }

    public final String component6()
    {
        return this.bankCode;
    }

    public final String component7()
    {
        return this.transId;
    }

    public final int component8()
    {
        return this.linkingType;
    }

    public final int component9()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument copy(int p13, String p14, String p15, String p16, int p17, String p18, String p19, int p20, int p21, String p22, boolean p23)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument)) {
                if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).parentId) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).requestCode)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).url)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButton, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).confirmButton)) {
                                if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).bcErrorCode) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).bankCode)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.transId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).transId)) {
                                            if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).linkingType) {
                                                if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).bmErrorCode) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).issuerBankName)) {
                                                        if (this.enableFaq == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) p5).enableFaq) {
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

    public final String getConfirmButton()
    {
        return this.confirmButton;
    }

    public final boolean getEnableFaq()
    {
        return this.enableFaq;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final int getParentId()
    {
        return this.parentId;
    }

    public final String getRequestCode()
    {
        return this.requestCode;
    }

    public final String getTransId()
    {
        return this.transId;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return ((((((((((((((((((((Integer.hashCode(this.parentId) * 31) + this.requestCode.hashCode()) * 31) + this.url.hashCode()) * 31) + this.confirmButton.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.bankCode.hashCode()) * 31) + this.transId.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + Integer.hashCode(this.bmErrorCode)) * 31) + this.issuerBankName.hashCode()) * 31) + Boolean.hashCode(this.enableFaq));
    }

    public String toString()
    {
        String v0_0 = this.parentId;
        String v1 = this.requestCode;
        String v2 = this.url;
        String v3 = this.confirmButton;
        int v4 = this.bcErrorCode;
        String v5 = this.bankCode;
        String v6 = this.transId;
        int v7 = this.linkingType;
        int v8 = this.bmErrorCode;
        String v9 = this.issuerBankName;
        boolean v10 = this.enableFaq;
        StringBuilder v11_1 = new StringBuilder("BankWebPopupArgument(parentId=");
        v11_1.append(v0_0);
        v11_1.append(", requestCode=");
        v11_1.append(v1);
        v11_1.append(", url=");
        v11_1.append(v2);
        v11_1.append(", confirmButton=");
        v11_1.append(v3);
        v11_1.append(", bcErrorCode=");
        v11_1.append(v4);
        v11_1.append(", bankCode=");
        v11_1.append(v5);
        v11_1.append(", transId=");
        v11_1.append(v6);
        v11_1.append(", linkingType=");
        v11_1.append(v7);
        v11_1.append(", bmErrorCode=");
        v11_1.append(v8);
        v11_1.append(", issuerBankName=");
        v11_1.append(v9);
        v11_1.append(", enableFaq=");
        v11_1.append(v10);
        v11_1.append(")");
        return v11_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.parentId);
        p1.writeString(this.requestCode);
        p1.writeString(this.url);
        p1.writeString(this.confirmButton);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.bankCode);
        p1.writeString(this.transId);
        p1.writeInt(this.linkingType);
        p1.writeInt(this.bmErrorCode);
        p1.writeString(this.issuerBankName);
        p1.writeInt(this.enableFaq);
        return;
    }
}
