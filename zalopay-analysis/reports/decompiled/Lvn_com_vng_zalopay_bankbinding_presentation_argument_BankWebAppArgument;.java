package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankWebAppArgument implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity cancelConsentDialog;
    private final String clientBankCode;
    private final String firstNum;
    private final int functionId;
    private final String issuerBankName;
    private final String lastNum;
    private final String traceId;
    private final String url;
    private final String zpTransId;

    static BankWebAppArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument.CREATOR = new no.e(7);
        return;
    }

    public BankWebAppArgument(String p1, String p2, String p3, String p4, int p5, String p6, String p7, String p8, String p9, vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity p10)
    {
        this.bankCode = p1;
        this.url = p2;
        this.zpTransId = p3;
        this.traceId = p4;
        this.functionId = p5;
        this.issuerBankName = p6;
        this.firstNum = p7;
        this.lastNum = p8;
        this.clientBankCode = p9;
        this.cancelConsentDialog = p10;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument p0, String p1, String p2, String p3, String p4, int p5, String p6, String p7, String p8, String p9, vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity p10, int p11, Object p12)
    {
        if ((p11 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p11 & 2) != 0) {
            p2 = p0.url;
        }
        if ((p11 & 4) != 0) {
            p3 = p0.zpTransId;
        }
        if ((p11 & 8) != 0) {
            p4 = p0.traceId;
        }
        if ((p11 & 16) != 0) {
            p5 = p0.functionId;
        }
        if ((p11 & 32) != 0) {
            p6 = p0.issuerBankName;
        }
        if ((p11 & 64) != 0) {
            p7 = p0.firstNum;
        }
        if ((p11 & 128) != 0) {
            p8 = p0.lastNum;
        }
        if ((p11 & 256) != 0) {
            p9 = p0.clientBankCode;
        }
        if ((p11 & 512) != 0) {
            p10 = p0.cancelConsentDialog;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity component10()
    {
        return this.cancelConsentDialog;
    }

    public final String component2()
    {
        return this.url;
    }

    public final String component3()
    {
        return this.zpTransId;
    }

    public final String component4()
    {
        return this.traceId;
    }

    public final int component5()
    {
        return this.functionId;
    }

    public final String component6()
    {
        return this.issuerBankName;
    }

    public final String component7()
    {
        return this.firstNum;
    }

    public final String component8()
    {
        return this.lastNum;
    }

    public final String component9()
    {
        return this.clientBankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument copy(String p12, String p13, String p14, String p15, int p16, String p17, String p18, String p19, String p20, vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity p21)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).url)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).zpTransId)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.traceId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).traceId)) {
                                if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).functionId) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).issuerBankName)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.firstNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).firstNum)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.lastNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).lastNum)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).clientBankCode)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cancelConsentDialog, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) p5).cancelConsentDialog)) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity getCancelConsentDialog()
    {
        return this.cancelConsentDialog;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final String getFirstNum()
    {
        return this.firstNum;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public final String getLastNum()
    {
        return this.lastNum;
    }

    public final String getTraceId()
    {
        return this.traceId;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v1_15;
        int v0_16 = (((((((((((((((((this.bankCode.hashCode() * 31) + this.url.hashCode()) * 31) + this.zpTransId.hashCode()) * 31) + this.traceId.hashCode()) * 31) + Integer.hashCode(this.functionId)) * 31) + this.issuerBankName.hashCode()) * 31) + this.firstNum.hashCode()) * 31) + this.lastNum.hashCode()) * 31) + this.clientBankCode.hashCode()) * 31);
        int v1_14 = this.cancelConsentDialog;
        if (v1_14 != 0) {
            v1_15 = v1_14.hashCode();
        } else {
            v1_15 = 0;
        }
        return (v0_16 + v1_15);
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.url;
        String v2 = this.zpTransId;
        String v3 = this.traceId;
        int v4 = this.functionId;
        String v5 = this.issuerBankName;
        String v6 = this.firstNum;
        String v7 = this.lastNum;
        String v8 = this.clientBankCode;
        vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity v9 = this.cancelConsentDialog;
        StringBuilder v10_1 = new StringBuilder("BankWebAppArgument(bankCode=");
        v10_1.append(v0_0);
        v10_1.append(", url=");
        v10_1.append(v1);
        v10_1.append(", zpTransId=");
        v10_1.append(v2);
        v10_1.append(", traceId=");
        v10_1.append(v3);
        v10_1.append(", functionId=");
        v10_1.append(v4);
        v10_1.append(", issuerBankName=");
        v10_1.append(v5);
        v10_1.append(", firstNum=");
        v10_1.append(v6);
        v10_1.append(", lastNum=");
        v10_1.append(v7);
        v10_1.append(", clientBankCode=");
        v10_1.append(v8);
        v10_1.append(", cancelConsentDialog=");
        v10_1.append(v9);
        v10_1.append(")");
        return v10_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.bankCode);
        p3.writeString(this.url);
        p3.writeString(this.zpTransId);
        p3.writeString(this.traceId);
        p3.writeInt(this.functionId);
        p3.writeString(this.issuerBankName);
        p3.writeString(this.firstNum);
        p3.writeString(this.lastNum);
        p3.writeString(this.clientBankCode);
        vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity v0_5 = this.cancelConsentDialog;
        if (v0_5 != null) {
            p3.writeInt(1);
            v0_5.writeToParcel(p3, p4);
            return;
        } else {
            p3.writeInt(0);
            return;
        }
    }
}
