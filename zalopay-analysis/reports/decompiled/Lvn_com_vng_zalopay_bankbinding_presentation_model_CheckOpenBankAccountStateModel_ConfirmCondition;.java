package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class CheckOpenBankAccountStateModel$ConfirmCondition extends vn.com.vng.zalopay.bankbinding.presentation.model.a implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int bankBindingFunctionId;
    private final String bankLogoUrl;
    private final String bannerUrl;
    private final java.util.List benefits;
    private final String conditionTitle;
    private final java.util.List conditions;
    private final String ctaButtonText;
    private final String ctaDescription;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity extraInfo;
    private final int kycSourceParam;
    private final java.util.Map metadata;
    private final String nextAction;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity otpDetectorEntity;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity suggestBank;

    static CheckOpenBankAccountStateModel$ConfirmCondition()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition.CREATOR = new uo.L(5);
        return;
    }

    public CheckOpenBankAccountStateModel$ConfirmCondition(String p1, int p2, vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity p3, java.util.Map p4, java.util.List p5, String p6, String p7, java.util.List p8, String p9, String p10, int p11, String p12, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p13, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity p14)
    {
        this.nextAction = p1;
        this.bankBindingFunctionId = p2;
        this.suggestBank = p3;
        this.metadata = p4;
        this.conditions = p5;
        this.bannerUrl = p6;
        this.conditionTitle = p7;
        this.benefits = p8;
        this.ctaDescription = p9;
        this.ctaButtonText = p10;
        this.kycSourceParam = p11;
        this.bankLogoUrl = p12;
        this.otpDetectorEntity = p13;
        this.extraInfo = p14;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition p14, String p15, int p16, vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity p17, java.util.Map p18, java.util.List p19, String p20, String p21, java.util.List p22, String p23, String p24, int p25, String p26, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p27, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity p28, int p29, Object p30)
    {
        String v1_1;
        if ((p29 & 1) == 0) {
            v1_1 = p15;
        } else {
            v1_1 = p14.nextAction;
        }
        int v2_1;
        if ((p29 & 2) == 0) {
            v2_1 = p16;
        } else {
            v2_1 = p14.bankBindingFunctionId;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity v3_0;
        if ((p29 & 4) == 0) {
            v3_0 = p17;
        } else {
            v3_0 = p14.suggestBank;
        }
        java.util.Map v4_1;
        if ((p29 & 8) == 0) {
            v4_1 = p18;
        } else {
            v4_1 = p14.metadata;
        }
        java.util.List v5_1;
        if ((p29 & 16) == 0) {
            v5_1 = p19;
        } else {
            v5_1 = p14.conditions;
        }
        String v6_1;
        if ((p29 & 32) == 0) {
            v6_1 = p20;
        } else {
            v6_1 = p14.bannerUrl;
        }
        String v7_1;
        if ((p29 & 64) == 0) {
            v7_1 = p21;
        } else {
            v7_1 = p14.conditionTitle;
        }
        java.util.List v8_1;
        if ((p29 & 128) == 0) {
            v8_1 = p22;
        } else {
            v8_1 = p14.benefits;
        }
        String v9_1;
        if ((p29 & 256) == 0) {
            v9_1 = p23;
        } else {
            v9_1 = p14.ctaDescription;
        }
        String v10_1;
        if ((p29 & 512) == 0) {
            v10_1 = p24;
        } else {
            v10_1 = p14.ctaButtonText;
        }
        int v11_1;
        if ((p29 & 1024) == 0) {
            v11_1 = p25;
        } else {
            v11_1 = p14.kycSourceParam;
        }
        String v12_1;
        if ((p29 & 2048) == 0) {
            v12_1 = p26;
        } else {
            v12_1 = p14.bankLogoUrl;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v13_1;
        if ((p29 & 4096) == 0) {
            v13_1 = p27;
        } else {
            v13_1 = p14.otpDetectorEntity;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v29_1;
        if ((p29 & 8192) == 0) {
            v29_1 = p28;
        } else {
            v29_1 = p14.extraInfo;
        }
        return p14.copy(v1_1, v2_1, v3_0, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v29_1);
    }

    public final String component1()
    {
        return this.nextAction;
    }

    public final String component10()
    {
        return this.ctaButtonText;
    }

    public final int component11()
    {
        return this.kycSourceParam;
    }

    public final String component12()
    {
        return this.bankLogoUrl;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity component13()
    {
        return this.otpDetectorEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity component14()
    {
        return this.extraInfo;
    }

    public final int component2()
    {
        return this.bankBindingFunctionId;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity component3()
    {
        return this.suggestBank;
    }

    public final java.util.Map component4()
    {
        return this.metadata;
    }

    public final java.util.List component5()
    {
        return this.conditions;
    }

    public final String component6()
    {
        return this.bannerUrl;
    }

    public final String component7()
    {
        return this.conditionTitle;
    }

    public final java.util.List component8()
    {
        return this.benefits;
    }

    public final String component9()
    {
        return this.ctaDescription;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition copy(String p16, int p17, vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity p18, java.util.Map p19, java.util.List p20, String p21, String p22, java.util.List p23, String p24, String p25, int p26, String p27, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p28, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity p29)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition(p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.nextAction, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).nextAction)) {
                    if (this.bankBindingFunctionId == ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).bankBindingFunctionId) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.suggestBank, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).suggestBank)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.metadata, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).metadata)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.conditions, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).conditions)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bannerUrl, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).bannerUrl)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.conditionTitle, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).conditionTitle)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.benefits, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).benefits)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.ctaDescription, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).ctaDescription)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.ctaButtonText, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).ctaButtonText)) {
                                                        if (this.kycSourceParam == ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).kycSourceParam) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankLogoUrl, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).bankLogoUrl)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpDetectorEntity, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).otpDetectorEntity)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.extraInfo, ((vn.com.vng.zalopay.bankbinding.presentation.model.CheckOpenBankAccountStateModel$ConfirmCondition) p5).extraInfo)) {
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
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int getBankBindingFunctionId()
    {
        return this.bankBindingFunctionId;
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity getExtraInfo()
    {
        return this.extraInfo;
    }

    public final int getKycSourceParam()
    {
        return this.kycSourceParam;
    }

    public final java.util.Map getMetadata()
    {
        return this.metadata;
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
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v1_22;
        int v0_22 = (((((((((((((((((((((((this.nextAction.hashCode() * 31) + Integer.hashCode(this.bankBindingFunctionId)) * 31) + this.suggestBank.hashCode()) * 31) + this.metadata.hashCode()) * 31) + this.conditions.hashCode()) * 31) + this.bannerUrl.hashCode()) * 31) + this.conditionTitle.hashCode()) * 31) + this.benefits.hashCode()) * 31) + this.ctaDescription.hashCode()) * 31) + this.ctaButtonText.hashCode()) * 31) + Integer.hashCode(this.kycSourceParam)) * 31) + this.bankLogoUrl.hashCode()) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v1_20 = this.otpDetectorEntity;
        int v2 = 0;
        if (v1_20 != null) {
            v1_22 = v1_20.hashCode();
        } else {
            v1_22 = 0;
        }
        int v0_24 = ((v0_22 + v1_22) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v1_23 = this.extraInfo;
        if (v1_23 != null) {
            v2 = v1_23.hashCode();
        }
        return (v0_24 + v2);
    }

    public String toString()
    {
        String v1 = this.nextAction;
        int v2 = this.bankBindingFunctionId;
        vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity v3 = this.suggestBank;
        java.util.Map v4 = this.metadata;
        java.util.List v5 = this.conditions;
        String v6 = this.bannerUrl;
        String v7 = this.conditionTitle;
        java.util.List v8 = this.benefits;
        String v9 = this.ctaDescription;
        String v10 = this.ctaButtonText;
        int v11 = this.kycSourceParam;
        String v12 = this.bankLogoUrl;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v13 = this.otpDetectorEntity;
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v14 = this.extraInfo;
        StringBuilder v15_1 = new StringBuilder("ConfirmCondition(nextAction=");
        v15_1.append(v1);
        v15_1.append(", bankBindingFunctionId=");
        v15_1.append(v2);
        v15_1.append(", suggestBank=");
        v15_1.append(v3);
        v15_1.append(", metadata=");
        v15_1.append(v4);
        v15_1.append(", conditions=");
        v15_1.append(v5);
        v15_1.append(", bannerUrl=");
        v15_1.append(v6);
        v15_1.append(", conditionTitle=");
        v15_1.append(v7);
        v15_1.append(", benefits=");
        v15_1.append(v8);
        v15_1.append(", ctaDescription=");
        v15_1.append(v9);
        v15_1.append(", ctaButtonText=");
        v15_1.append(v10);
        v15_1.append(", kycSourceParam=");
        v15_1.append(v11);
        v15_1.append(", bankLogoUrl=");
        v15_1.append(v12);
        v15_1.append(", otpDetectorEntity=");
        v15_1.append(v13);
        v15_1.append(", extraInfo=");
        v15_1.append(v14);
        v15_1.append(")");
        return v15_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        p4.writeString(this.nextAction);
        p4.writeInt(this.bankBindingFunctionId);
        this.suggestBank.writeToParcel(p4, p5);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v0_15 = this.metadata;
        p4.writeInt(v0_15.size());
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v0_2 = v0_15.entrySet().iterator();
        while (v0_2.hasNext()) {
            int v1_3 = ((java.util.Map$Entry) v0_2.next());
            p4.writeString(((String) v1_3.getKey()));
            p4.writeString(((String) v1_3.getValue()));
        }
        p4.writeStringList(this.conditions);
        p4.writeString(this.bannerUrl);
        p4.writeString(this.conditionTitle);
        p4.writeStringList(this.benefits);
        p4.writeString(this.ctaDescription);
        p4.writeString(this.ctaButtonText);
        p4.writeInt(this.kycSourceParam);
        p4.writeString(this.bankLogoUrl);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v0_12 = this.otpDetectorEntity;
        if (v0_12 != null) {
            p4.writeInt(1);
            v0_12.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity v0_14 = this.extraInfo;
        if (v0_14 != null) {
            p4.writeInt(1);
            v0_14.writeToParcel(p4, p5);
            return;
        } else {
            p4.writeInt(0);
            return;
        }
    }
}
