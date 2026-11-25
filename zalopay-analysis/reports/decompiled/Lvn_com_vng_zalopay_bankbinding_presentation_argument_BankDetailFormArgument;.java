package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankDetailFormArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final no.a Companion;
    private final java.util.List bankBindingFields;
    private final String bankCode;
    private final java.util.List bankConditions;
    private final String bankName;
    private final String cardNumber;
    private final String clientBankCode;
    private final boolean fromDeeplink;
    private final int functionId;
    private final String issuerBankName;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity otpDetectorEntity;
    private final java.util.List terms;
    private final java.util.Map userConsent;

    static BankDetailFormArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument.Companion = new no.a();
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument.CREATOR = new Zn.a(20);
        return;
    }

    public BankDetailFormArgument(int p1, String p2, String p3, String p4, String p5, java.util.List p6, java.util.List p7, String p8, java.util.List p9, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p10, java.util.Map p11, boolean p12)
    {
        this.functionId = p1;
        this.bankCode = p2;
        this.bankName = p3;
        this.clientBankCode = p4;
        this.cardNumber = p5;
        this.bankBindingFields = p6;
        this.bankConditions = p7;
        this.issuerBankName = p8;
        this.terms = p9;
        this.otpDetectorEntity = p10;
        this.userConsent = p11;
        this.fromDeeplink = p12;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument p0, int p1, String p2, String p3, String p4, String p5, java.util.List p6, java.util.List p7, String p8, java.util.List p9, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p10, java.util.Map p11, boolean p12, int p13, Object p14)
    {
        if ((p13 & 1) != 0) {
            p1 = p0.functionId;
        }
        if ((p13 & 2) != 0) {
            p2 = p0.bankCode;
        }
        if ((p13 & 4) != 0) {
            p3 = p0.bankName;
        }
        if ((p13 & 8) != 0) {
            p4 = p0.clientBankCode;
        }
        if ((p13 & 16) != 0) {
            p5 = p0.cardNumber;
        }
        if ((p13 & 32) != 0) {
            p6 = p0.bankBindingFields;
        }
        if ((p13 & 64) != 0) {
            p7 = p0.bankConditions;
        }
        if ((p13 & 128) != 0) {
            p8 = p0.issuerBankName;
        }
        if ((p13 & 256) != 0) {
            p9 = p0.terms;
        }
        if ((p13 & 512) != 0) {
            p10 = p0.otpDetectorEntity;
        }
        if ((p13 & 1024) != 0) {
            p11 = p0.userConsent;
        }
        if ((p13 & 2048) != 0) {
            p12 = p0.fromDeeplink;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
    }

    public final int component1()
    {
        return this.functionId;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity component10()
    {
        return this.otpDetectorEntity;
    }

    public final java.util.Map component11()
    {
        return this.userConsent;
    }

    public final boolean component12()
    {
        return this.fromDeeplink;
    }

    public final String component2()
    {
        return this.bankCode;
    }

    public final String component3()
    {
        return this.bankName;
    }

    public final String component4()
    {
        return this.clientBankCode;
    }

    public final String component5()
    {
        return this.cardNumber;
    }

    public final java.util.List component6()
    {
        return this.bankBindingFields;
    }

    public final java.util.List component7()
    {
        return this.bankConditions;
    }

    public final String component8()
    {
        return this.issuerBankName;
    }

    public final java.util.List component9()
    {
        return this.terms;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument copy(int p14, String p15, String p16, String p17, String p18, java.util.List p19, java.util.List p20, String p21, java.util.List p22, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p23, java.util.Map p24, boolean p25)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument(p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument)) {
                if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).functionId) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).bankCode)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).bankName)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).clientBankCode)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardNumber, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).cardNumber)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankBindingFields, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).bankBindingFields)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankConditions, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).bankConditions)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).issuerBankName)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.terms, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).terms)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpDetectorEntity, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).otpDetectorEntity)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.userConsent, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).userConsent)) {
                                                            if (this.fromDeeplink == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDetailFormArgument) p5).fromDeeplink) {
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
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final java.util.List getBankBindingFields()
    {
        return this.bankBindingFields;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final java.util.List getBankConditions()
    {
        return this.bankConditions;
    }

    public final String getBankName()
    {
        return this.bankName;
    }

    public final String getCardNumber()
    {
        return this.cardNumber;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final boolean getFromDeeplink()
    {
        return this.fromDeeplink;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity getOtpDetectorEntity()
    {
        return this.otpDetectorEntity;
    }

    public final java.util.List getTerms()
    {
        return this.terms;
    }

    public final java.util.Map getUserConsent()
    {
        return this.userConsent;
    }

    public int hashCode()
    {
        int v1_15;
        int v0_16 = (((((((((((((((((Integer.hashCode(this.functionId) * 31) + this.bankCode.hashCode()) * 31) + this.bankName.hashCode()) * 31) + this.clientBankCode.hashCode()) * 31) + this.cardNumber.hashCode()) * 31) + this.bankBindingFields.hashCode()) * 31) + this.bankConditions.hashCode()) * 31) + this.issuerBankName.hashCode()) * 31) + this.terms.hashCode()) * 31);
        int v1_14 = this.otpDetectorEntity;
        if (v1_14 != 0) {
            v1_15 = v1_14.hashCode();
        } else {
            v1_15 = 0;
        }
        return (((((v0_16 + v1_15) * 31) + this.userConsent.hashCode()) * 31) + Boolean.hashCode(this.fromDeeplink));
    }

    public String toString()
    {
        String v0_0 = this.functionId;
        String v1 = this.bankCode;
        String v2 = this.bankName;
        String v3 = this.clientBankCode;
        String v4 = this.cardNumber;
        java.util.List v5 = this.bankBindingFields;
        java.util.List v6 = this.bankConditions;
        String v7 = this.issuerBankName;
        java.util.List v8 = this.terms;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v9 = this.otpDetectorEntity;
        java.util.Map v10 = this.userConsent;
        boolean v11 = this.fromDeeplink;
        StringBuilder v12_1 = new StringBuilder("BankDetailFormArgument(functionId=");
        v12_1.append(v0_0);
        v12_1.append(", bankCode=");
        v12_1.append(v1);
        v12_1.append(", bankName=");
        v12_1.append(v2);
        v12_1.append(", clientBankCode=");
        v12_1.append(v3);
        v12_1.append(", cardNumber=");
        v12_1.append(v4);
        v12_1.append(", bankBindingFields=");
        v12_1.append(v5);
        v12_1.append(", bankConditions=");
        v12_1.append(v6);
        v12_1.append(", issuerBankName=");
        v12_1.append(v7);
        v12_1.append(", terms=");
        v12_1.append(v8);
        v12_1.append(", otpDetectorEntity=");
        v12_1.append(v9);
        v12_1.append(", userConsent=");
        v12_1.append(v10);
        v12_1.append(", fromDeeplink=");
        v12_1.append(v11);
        v12_1.append(")");
        return v12_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.functionId);
        p3.writeString(this.bankCode);
        p3.writeString(this.bankName);
        p3.writeString(this.clientBankCode);
        p3.writeString(this.cardNumber);
        boolean v0_1 = this.bankBindingFields;
        p3.writeInt(v0_1.size());
        boolean v0_2 = v0_1.iterator();
        while (v0_2.hasNext()) {
            p3.writeParcelable(((android.os.Parcelable) v0_2.next()), p4);
        }
        p3.writeStringList(this.bankConditions);
        p3.writeString(this.issuerBankName);
        p3.writeStringList(this.terms);
        boolean v0_7 = this.otpDetectorEntity;
        if (v0_7) {
            p3.writeInt(1);
            v0_7.writeToParcel(p3, p4);
        } else {
            p3.writeInt(0);
        }
        boolean v4_2 = this.userConsent;
        p3.writeInt(v4_2.size());
        boolean v4_4 = v4_2.entrySet().iterator();
        while (v4_4.hasNext()) {
            boolean v0_12 = ((java.util.Map$Entry) v4_4.next());
            p3.writeString(((String) v0_12.getKey()));
            p3.writeInt(((Boolean) v0_12.getValue()).booleanValue());
        }
        p3.writeInt(this.fromDeeplink);
        return;
    }
}
