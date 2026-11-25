package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankIdentityEntryPointArgument implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final no.d Companion;
    private final String bankCode;
    private final String bankName;
    private final String cardBrand;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity cta;
    private final int eKycSource;
    private final int linkingType;
    private final String mode;

    static BankIdentityEntryPointArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument.Companion = new no.d();
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument.CREATOR = new Zn.a(25);
        return;
    }

    public BankIdentityEntryPointArgument(String p1, String p2, int p3, String p4, vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity p5, int p6, String p7)
    {
        this.bankName = p1;
        this.cardBrand = p2;
        this.eKycSource = p3;
        this.bankCode = p4;
        this.cta = p5;
        this.linkingType = p6;
        this.mode = p7;
        return;
    }

    public synthetic BankIdentityEntryPointArgument(String p9, String p10, int p11, String p12, vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity p13, int p14, String p15, int p16, kotlin.jvm.internal.DefaultConstructorMarker p17)
    {
        if ((p16 & 16) != 0) {
            p13 = 0;
        }
        this(p9, p10, p11, p12, p13, p14, p15);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument p0, String p1, String p2, int p3, String p4, vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity p5, int p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.bankName;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.cardBrand;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.eKycSource;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.bankCode;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.cta;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.linkingType;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.mode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.bankName;
    }

    public final String component2()
    {
        return this.cardBrand;
    }

    public final int component3()
    {
        return this.eKycSource;
    }

    public final String component4()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity component5()
    {
        return this.cta;
    }

    public final int component6()
    {
        return this.linkingType;
    }

    public final String component7()
    {
        return this.mode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument copy(String p9, String p10, int p11, String p12, vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity p13, int p14, String p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) p5).bankName)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardBrand, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) p5).cardBrand)) {
                        if (this.eKycSource == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) p5).eKycSource) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) p5).bankCode)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.cta, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) p5).cta)) {
                                    if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) p5).linkingType) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.mode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) p5).mode)) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getBankName()
    {
        return this.bankName;
    }

    public final String getCardBrand()
    {
        return this.cardBrand;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity getCta()
    {
        return this.cta;
    }

    public final int getEKycSource()
    {
        return this.eKycSource;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final String getMode()
    {
        return this.mode;
    }

    public int hashCode()
    {
        int v1_4;
        int v0_4 = (((((((this.bankName.hashCode() * 31) + this.cardBrand.hashCode()) * 31) + Integer.hashCode(this.eKycSource)) * 31) + this.bankCode.hashCode()) * 31);
        int v1_3 = this.cta;
        if (v1_3 != 0) {
            v1_4 = v1_3.hashCode();
        } else {
            v1_4 = 0;
        }
        return (((((v0_4 + v1_4) * 31) + Integer.hashCode(this.linkingType)) * 31) + this.mode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankName;
        String v1 = this.cardBrand;
        int v2 = this.eKycSource;
        String v3 = this.bankCode;
        vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity v4 = this.cta;
        int v5 = this.linkingType;
        String v6 = this.mode;
        StringBuilder v7_1 = new StringBuilder("BankIdentityEntryPointArgument(bankName=");
        v7_1.append(v0_0);
        v7_1.append(", cardBrand=");
        v7_1.append(v1);
        v7_1.append(", eKycSource=");
        v7_1.append(v2);
        v7_1.append(", bankCode=");
        v7_1.append(v3);
        v7_1.append(", cta=");
        v7_1.append(v4);
        v7_1.append(", linkingType=");
        v7_1.append(v5);
        v7_1.append(", mode=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.bankName);
        p3.writeString(this.cardBrand);
        p3.writeInt(this.eKycSource);
        p3.writeString(this.bankCode);
        vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity v0_4 = this.cta;
        if (v0_4 != null) {
            p3.writeInt(1);
            v0_4.writeToParcel(p3, p4);
        } else {
            p3.writeInt(0);
        }
        p3.writeInt(this.linkingType);
        p3.writeString(this.mode);
        return;
    }
}
