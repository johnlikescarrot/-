package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class ConfirmConditionArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankLogoUrl;
    private final String bannerUrl;
    private final java.util.List benefits;
    private final String conditionTitle;
    private final java.util.List conditions;
    private final String ctaButtonText;
    private final String ctaDescription;
    private final String entryPoint;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity extraInfo;
    private final int kycSourceParam;
    private final String nextAction;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity otpDetectorEntity;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity suggestBank;

    static ConfirmConditionArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument.CREATOR = new no.e(11);
        return;
    }

    public ConfirmConditionArgument(vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity p1, String p2, String p3, String p4, java.util.List p5, String p6, String p7, int p8, String p9, java.util.List p10, String p11, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p12, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity p13)
    {
        this.suggestBank = p1;
        this.nextAction = p2;
        this.bannerUrl = p3;
        this.conditionTitle = p4;
        this.benefits = p5;
        this.ctaDescription = p6;
        this.ctaButtonText = p7;
        this.kycSourceParam = p8;
        this.bankLogoUrl = p9;
        this.conditions = p10;
        this.entryPoint = p11;
        this.otpDetectorEntity = p12;
        this.extraInfo = p13;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument p12, vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity p13, String p14, String p15, String p16, java.util.List p17, String p18, String p19, int p20, String p21, java.util.List p22, String p23, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p24, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity p25, int p26, Object p27)
    {
        if ((p26 & 1) != 0) {
            p13 = p12.suggestBank;
        }
        String v1_2;
        if ((p26 & 2) == 0) {
            v1_2 = p14;
        } else {
            v1_2 = p12.nextAction;
        }
        String v2_0;
        if ((p26 & 4) == 0) {
            v2_0 = p15;
        } else {
            v2_0 = p12.bannerUrl;
        }
        String v3_1;
        if ((p26 & 8) == 0) {
            v3_1 = p16;
        } else {
            v3_1 = p12.conditionTitle;
        }
        java.util.List v4_1;
        if ((p26 & 16) == 0) {
            v4_1 = p17;
        } else {
            v4_1 = p12.benefits;
        }
        String v5_1;
        if ((p26 & 32) == 0) {
            v5_1 = p18;
        } else {
            v5_1 = p12.ctaDescription;
        }
        String v6_1;
        if ((p26 & 64) == 0) {
            v6_1 = p19;
        } else {
            v6_1 = p12.ctaButtonText;
        }
        int v7_1;
        if ((p26 & 128) == 0) {
            v7_1 = p20;
        } else {
            v7_1 = p12.kycSourceParam;
        }
        String v8_1;
        if ((p26 & 256) == 0) {
            v8_1 = p21;
        } else {
            v8_1 = p12.bankLogoUrl;
        }
        java.util.List v9_1;
        if ((p26 & 512) == 0) {
            v9_1 = p22;
        } else {
            v9_1 = p12.conditions;
        }
        String v10_1;
        if ((p26 & 1024) == 0) {
            v10_1 = p23;
        } else {
            v10_1 = p12.entryPoint;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v11_1;
        if ((p26 & 2048) == 0) {
            v11_1 = p24;
        } else {
            v11_1 = p12.otpDetectorEntity;
        }
        if ((p26 & 4096) == 0) {
            p27 = p25;
        } else {
            p27 = p12.extraInfo;
        }
        return p12.copy(p13, v1_2, v2_0, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, p27);
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity component1()
    {
        return this.suggestBank;
    }

    public final java.util.List component10()
    {
        return this.conditions;
    }

    public final String component11()
    {
        return this.entryPoint;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity component12()
    {
        return this.otpDetectorEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity component13()
    {
        return this.extraInfo;
    }

    public final String component2()
    {
        return this.nextAction;
    }

    public final String component3()
    {
        return this.bannerUrl;
    }

    public final String component4()
    {
        return this.conditionTitle;
    }

    public final java.util.List component5()
    {
        return this.benefits;
    }

    public final String component6()
    {
        return this.ctaDescription;
    }

    public final String component7()
    {
        return this.ctaButtonText;
    }

    public final int component8()
    {
        return this.kycSourceParam;
    }

    public final String component9()
    {
        return this.bankLogoUrl;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument copy(vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity p15, String p16, String p17, String p18, java.util.List p19, String p20, String p21, int p22, String p23, java.util.List p24, String p25, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p26, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity p27)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument(p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.suggestBank, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).suggestBank)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.nextAction, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).nextAction)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bannerUrl, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).bannerUrl)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.conditionTitle, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).conditionTitle)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.benefits, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).benefits)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.ctaDescription, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).ctaDescription)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.ctaButtonText, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).ctaButtonText)) {
                                            if (this.kycSourceParam == ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).kycSourceParam) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankLogoUrl, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).bankLogoUrl)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.conditions, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).conditions)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.entryPoint, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).entryPoint)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpDetectorEntity, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).otpDetectorEntity)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.extraInfo, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) p5).extraInfo)) {
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
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final String getBankLogoUrl()
    {
        return this.bankLogoUrl;
    }

    public final String getBannerUrl()
    {
        return this.bannerUrl;
    }

    public final java.util.List getBenefits()
    {
        return this.benefits;
    }

    public final String getConditionTitle()
    {
        return this.conditionTitle;
    }

    public final java.util.List getConditions()
    {
        return this.conditions;
    }

    public final String getCtaButtonText()
    {
        return this.ctaButtonText;
    }

    public final String getCtaDescription()
    {
        return this.ctaDescription;
    }

    public final String getEntryPoint()
    {
        return this.entryPoint;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity getExtraInfo()
    {
        return this.extraInfo;
    }

    public final int getKycSourceParam()
    {
        return this.kycSourceParam;
    }

    public final String getNextAction()
    {
        return this.nextAction;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity getOtpDetectorEntity()
    {
        return this.otpDetectorEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity getSuggestBank()
    {
        return this.suggestBank;
    }

    public int hashCode()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v1_19;
        int v0_20 = (((((((((((((((((((((this.suggestBank.hashCode() * 31) + this.nextAction.hashCode()) * 31) + this.bannerUrl.hashCode()) * 31) + this.conditionTitle.hashCode()) * 31) + this.benefits.hashCode()) * 31) + this.ctaDescription.hashCode()) * 31) + this.ctaButtonText.hashCode()) * 31) + Integer.hashCode(this.kycSourceParam)) * 31) + this.bankLogoUrl.hashCode()) * 31) + this.conditions.hashCode()) * 31) + this.entryPoint.hashCode()) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v1_18 = this.otpDetectorEntity;
        int v2 = 0;
        if (v1_18 != null) {
            v1_19 = v1_18.hashCode();
        } else {
            v1_19 = 0;
        }
        int v0_22 = ((v0_20 + v1_19) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v1_21 = this.extraInfo;
        if (v1_21 != null) {
            v2 = v1_21.hashCode();
        }
        return (v0_22 + v2);
    }

    public String toString()
    {
        String v0_0 = this.suggestBank;
        String v1 = this.nextAction;
        String v2 = this.bannerUrl;
        String v3 = this.conditionTitle;
        java.util.List v4 = this.benefits;
        String v5 = this.ctaDescription;
        String v6 = this.ctaButtonText;
        int v7 = this.kycSourceParam;
        String v8 = this.bankLogoUrl;
        java.util.List v9 = this.conditions;
        String v10 = this.entryPoint;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v11 = this.otpDetectorEntity;
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v12 = this.extraInfo;
        StringBuilder v13_1 = new StringBuilder("ConfirmConditionArgument(suggestBank=");
        v13_1.append(v0_0);
        v13_1.append(", nextAction=");
        v13_1.append(v1);
        v13_1.append(", bannerUrl=");
        v13_1.append(v2);
        v13_1.append(", conditionTitle=");
        v13_1.append(v3);
        v13_1.append(", benefits=");
        v13_1.append(v4);
        v13_1.append(", ctaDescription=");
        v13_1.append(v5);
        v13_1.append(", ctaButtonText=");
        v13_1.append(v6);
        v13_1.append(", kycSourceParam=");
        v13_1.append(v7);
        v13_1.append(", bankLogoUrl=");
        v13_1.append(v8);
        v13_1.append(", conditions=");
        v13_1.append(v9);
        v13_1.append(", entryPoint=");
        v13_1.append(v10);
        v13_1.append(", otpDetectorEntity=");
        v13_1.append(v11);
        v13_1.append(", extraInfo=");
        v13_1.append(v12);
        v13_1.append(")");
        return v13_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        this.suggestBank.writeToParcel(p4, p5);
        p4.writeString(this.nextAction);
        p4.writeString(this.bannerUrl);
        p4.writeString(this.conditionTitle);
        p4.writeStringList(this.benefits);
        p4.writeString(this.ctaDescription);
        p4.writeString(this.ctaButtonText);
        p4.writeInt(this.kycSourceParam);
        p4.writeString(this.bankLogoUrl);
        p4.writeStringList(this.conditions);
        p4.writeString(this.entryPoint);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v0_8 = this.otpDetectorEntity;
        if (v0_8 != null) {
            p4.writeInt(1);
            v0_8.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v0_9 = this.extraInfo;
        if (v0_9 != null) {
            p4.writeInt(1);
            v0_9.writeToParcel(p4, p5);
            return;
        } else {
            p4.writeInt(0);
            return;
        }
    }
}
