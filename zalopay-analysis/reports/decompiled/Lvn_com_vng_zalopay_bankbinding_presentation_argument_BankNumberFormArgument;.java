package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankNumberFormArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity backAction;
    private final String bankCode;
    private final String bankName;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity cardGuide;
    private final String clientBankCode;
    private final vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity dialogEntity;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity helpers;
    private final String hint;
    private final String id;
    private final String information;
    private final String initBankNumber;
    private final String label;
    private final int maxLength;
    private final String placeHolder;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity popup;
    private final String qrContent;
    private final Integer suggestionType;
    private final String tabLabel;
    private final java.util.List toggleView;
    private final java.util.List validators;

    static BankNumberFormArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument.CREATOR = new Zn.a(28);
        return;
    }

    public BankNumberFormArgument(String p1, String p2, String p3, java.util.List p4, String p5, String p6, String p7, int p8, String p9, String p10, String p11, vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p12, vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p13, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p14, Integer p15, String p16, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p17, String p18, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p19, java.util.List p20)
    {
        this.bankCode = p1;
        this.clientBankCode = p2;
        this.id = p3;
        this.validators = p4;
        this.information = p5;
        this.hint = p6;
        this.bankName = p7;
        this.maxLength = p8;
        this.label = p9;
        this.placeHolder = p10;
        this.tabLabel = p11;
        this.cardGuide = p12;
        this.backAction = p13;
        this.helpers = p14;
        this.suggestionType = p15;
        this.initBankNumber = p16;
        this.dialogEntity = p17;
        this.qrContent = p18;
        this.popup = p19;
        this.toggleView = p20;
        return;
    }

    public synthetic BankNumberFormArgument(String p23, String p24, String p25, java.util.List p26, String p27, String p28, String p29, int p30, String p31, String p32, String p33, vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p34, vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p35, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p36, Integer p37, String p38, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p39, String p40, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p41, java.util.List p42, int p43, kotlin.jvm.internal.DefaultConstructorMarker p44)
    {
        Integer v16;
        if ((p43 & 16384) == 0) {
            v16 = p37;
        } else {
            v16 = 0;
        }
        this(p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, v16, p38, p39, p40, p41, p42);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument p17, String p18, String p19, String p20, java.util.List p21, String p22, String p23, String p24, int p25, String p26, String p27, String p28, vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p29, vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p30, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p31, Integer p32, String p33, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p34, String p35, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p36, java.util.List p37, int p38, Object p39)
    {
        Integer v2_1;
        if ((p38 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.bankCode;
        }
        String v3_1;
        if ((p38 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.clientBankCode;
        }
        String v4_1;
        if ((p38 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.id;
        }
        java.util.List v5_1;
        if ((p38 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.validators;
        }
        String v6_1;
        if ((p38 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.information;
        }
        String v7_1;
        if ((p38 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.hint;
        }
        String v8_1;
        if ((p38 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.bankName;
        }
        int v9_1;
        if ((p38 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.maxLength;
        }
        String v10_1;
        if ((p38 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.label;
        }
        String v11_1;
        if ((p38 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.placeHolder;
        }
        String v12_1;
        if ((p38 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.tabLabel;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity v13_1;
        if ((p38 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.cardGuide;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity v14_1;
        if ((p38 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.backAction;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity v15_1;
        if ((p38 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.helpers;
        }
        Integer v2_3;
        if ((p38 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.suggestionType;
        }
        java.util.List v1_1;
        if ((p38 & 32768) == 0) {
            v1_1 = p33;
        } else {
            v1_1 = p17.initBankNumber;
        }
        java.util.List v1_2;
        if ((p38 & 65536) == 0) {
            v1_2 = p34;
        } else {
            v1_2 = p17.dialogEntity;
        }
        java.util.List v1_3;
        if ((p38 & 131072) == 0) {
            v1_3 = p35;
        } else {
            v1_3 = p17.qrContent;
        }
        java.util.List v1_4;
        if ((p38 & 262144) == 0) {
            v1_4 = p36;
        } else {
            v1_4 = p17.popup;
        }
        java.util.List v37_1;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity v38_1;
        if ((p38 & 524288) == 0) {
            v38_1 = p37;
            v37_1 = v1_4;
        } else {
            v37_1 = v1_4;
            v38_1 = p17.toggleView;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_1, v1_2, v1_3, v37_1, v38_1);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component10()
    {
        return this.placeHolder;
    }

    public final String component11()
    {
        return this.tabLabel;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity component12()
    {
        return this.cardGuide;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity component13()
    {
        return this.backAction;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity component14()
    {
        return this.helpers;
    }

    public final Integer component15()
    {
        return this.suggestionType;
    }

    public final String component16()
    {
        return this.initBankNumber;
    }

    public final vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity component17()
    {
        return this.dialogEntity;
    }

    public final String component18()
    {
        return this.qrContent;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity component19()
    {
        return this.popup;
    }

    public final String component2()
    {
        return this.clientBankCode;
    }

    public final java.util.List component20()
    {
        return this.toggleView;
    }

    public final String component3()
    {
        return this.id;
    }

    public final java.util.List component4()
    {
        return this.validators;
    }

    public final String component5()
    {
        return this.information;
    }

    public final String component6()
    {
        return this.hint;
    }

    public final String component7()
    {
        return this.bankName;
    }

    public final int component8()
    {
        return this.maxLength;
    }

    public final String component9()
    {
        return this.label;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument copy(String p22, String p23, String p24, java.util.List p25, String p26, String p27, String p28, int p29, String p30, String p31, String p32, vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p33, vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p34, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p35, Integer p36, String p37, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p38, String p39, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p40, java.util.List p41)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument(p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).clientBankCode)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.id, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).id)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).validators)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.information, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).information)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.hint, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).hint)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).bankName)) {
                                            if (this.maxLength == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).maxLength) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.label, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).label)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.placeHolder, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).placeHolder)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.tabLabel, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).tabLabel)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardGuide, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).cardGuide)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.backAction, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).backAction)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.helpers, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).helpers)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.suggestionType, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).suggestionType)) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.initBankNumber, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).initBankNumber)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.dialogEntity, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).dialogEntity)) {
                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.qrContent, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).qrContent)) {
                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.popup, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).popup)) {
                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.toggleView, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankNumberFormArgument) p5).toggleView)) {
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity getBackAction()
    {
        return this.backAction;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getBankName()
    {
        return this.bankName;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity getCardGuide()
    {
        return this.cardGuide;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity getDialogEntity()
    {
        return this.dialogEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity getHelpers()
    {
        return this.helpers;
    }

    public final String getHint()
    {
        return this.hint;
    }

    public final String getId()
    {
        return this.id;
    }

    public final String getInformation()
    {
        return this.information;
    }

    public final String getInitBankNumber()
    {
        return this.initBankNumber;
    }

    public final String getLabel()
    {
        return this.label;
    }

    public final int getMaxLength()
    {
        return this.maxLength;
    }

    public final String getPlaceHolder()
    {
        return this.placeHolder;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity getPopup()
    {
        return this.popup;
    }

    public final String getQrContent()
    {
        return this.qrContent;
    }

    public final Integer getSuggestionType()
    {
        return this.suggestionType;
    }

    public final String getTabLabel()
    {
        return this.tabLabel;
    }

    public final java.util.List getToggleView()
    {
        return this.toggleView;
    }

    public final java.util.List getValidators()
    {
        return this.validators;
    }

    public int hashCode()
    {
        int v1_30;
        int v0_15 = (this.bankCode.hashCode() * 31);
        int v1_16 = this.clientBankCode;
        int v2 = 0;
        if (v1_16 != 0) {
            v1_30 = v1_16.hashCode();
        } else {
            v1_30 = 0;
        }
        int v1_24;
        int v0_27 = ((((((((((((((((((((v0_15 + v1_30) * 31) + this.id.hashCode()) * 31) + this.validators.hashCode()) * 31) + this.information.hashCode()) * 31) + this.hint.hashCode()) * 31) + this.bankName.hashCode()) * 31) + Integer.hashCode(this.maxLength)) * 31) + this.label.hashCode()) * 31) + this.placeHolder.hashCode()) * 31) + this.tabLabel.hashCode()) * 31);
        int v1_23 = this.cardGuide;
        if (v1_23 != 0) {
            v1_24 = v1_23.hashCode();
        } else {
            v1_24 = 0;
        }
        int v1_26;
        int v0_29 = ((v0_27 + v1_24) * 31);
        int v1_25 = this.backAction;
        if (v1_25 != 0) {
            v1_26 = v1_25.hashCode();
        } else {
            v1_26 = 0;
        }
        int v1_28;
        int v0_31 = ((v0_29 + v1_26) * 31);
        int v1_27 = this.helpers;
        if (v1_27 != 0) {
            v1_28 = v1_27.hashCode();
        } else {
            v1_28 = 0;
        }
        int v1_31;
        int v0_33 = ((v0_31 + v1_28) * 31);
        int v1_29 = this.suggestionType;
        if (v1_29 != 0) {
            v1_31 = v1_29.hashCode();
        } else {
            v1_31 = 0;
        }
        int v1_35;
        int v0_37 = ((((v0_33 + v1_31) * 31) + this.initBankNumber.hashCode()) * 31);
        int v1_34 = this.dialogEntity;
        if (v1_34 != 0) {
            v1_35 = v1_34.hashCode();
        } else {
            v1_35 = 0;
        }
        int v1_0;
        int v0_39 = ((v0_37 + v1_35) * 31);
        int v1_36 = this.qrContent;
        if (v1_36 != 0) {
            v1_0 = v1_36.hashCode();
        } else {
            v1_0 = 0;
        }
        int v0_3 = ((v0_39 + v1_0) * 31);
        int v1_1 = this.popup;
        if (v1_1 != 0) {
            v2 = v1_1.hashCode();
        }
        return (((v0_3 + v2) * 31) + this.toggleView.hashCode());
    }

    public String toString()
    {
        String v1_0 = this.bankCode;
        String v2 = this.clientBankCode;
        String v3 = this.id;
        java.util.List v4 = this.validators;
        String v5 = this.information;
        String v6 = this.hint;
        String v7 = this.bankName;
        int v8 = this.maxLength;
        String v9 = this.label;
        String v10 = this.placeHolder;
        String v11 = this.tabLabel;
        vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity v12 = this.cardGuide;
        vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity v13 = this.backAction;
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity v14 = this.helpers;
        Integer v16 = this.suggestionType;
        String v17 = this.initBankNumber;
        vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity v18 = this.dialogEntity;
        String v19 = this.qrContent;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity v20 = this.popup;
        java.util.List v21 = this.toggleView;
        String v0_2 = new StringBuilder("BankNumberFormArgument(bankCode=");
        v0_2.append(v1_0);
        v0_2.append(", clientBankCode=");
        v0_2.append(v2);
        v0_2.append(", id=");
        v0_2.append(v3);
        v0_2.append(", validators=");
        v0_2.append(v4);
        v0_2.append(", information=");
        v0_2.append(v5);
        v0_2.append(", hint=");
        v0_2.append(v6);
        v0_2.append(", bankName=");
        v0_2.append(v7);
        v0_2.append(", maxLength=");
        v0_2.append(v8);
        v0_2.append(", label=");
        v0_2.append(v9);
        v0_2.append(", placeHolder=");
        v0_2.append(v10);
        v0_2.append(", tabLabel=");
        v0_2.append(v11);
        v0_2.append(", cardGuide=");
        v0_2.append(v12);
        v0_2.append(", backAction=");
        v0_2.append(v13);
        v0_2.append(", helpers=");
        v0_2.append(v14);
        v0_2.append(", suggestionType=");
        v0_2.append(v16);
        v0_2.append(", initBankNumber=");
        v0_2.append(v17);
        v0_2.append(", dialogEntity=");
        v0_2.append(v18);
        v0_2.append(", qrContent=");
        v0_2.append(v19);
        v0_2.append(", popup=");
        v0_2.append(v20);
        v0_2.append(", toggleView=");
        v0_2.append(v21);
        v0_2.append(")");
        return v0_2.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        p4.writeString(this.bankCode);
        p4.writeString(this.clientBankCode);
        p4.writeString(this.id);
        java.util.Iterator v0_20 = this.validators;
        p4.writeInt(v0_20.size());
        java.util.Iterator v0_1 = v0_20.iterator();
        while (v0_1.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) v0_1.next()).writeToParcel(p4, p5);
        }
        p4.writeString(this.information);
        p4.writeString(this.hint);
        p4.writeString(this.bankName);
        p4.writeInt(this.maxLength);
        p4.writeString(this.label);
        p4.writeString(this.placeHolder);
        p4.writeString(this.tabLabel);
        java.util.Iterator v0_10 = this.cardGuide;
        if (v0_10 != null) {
            p4.writeInt(1);
            v0_10.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        java.util.Iterator v0_11 = this.backAction;
        if (v0_11 != null) {
            p4.writeInt(1);
            v0_11.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        java.util.Iterator v0_13 = this.helpers;
        if (v0_13 != null) {
            p4.writeInt(1);
            v0_13.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        java.util.Iterator v0_14 = this.suggestionType;
        if (v0_14 != null) {
            p4.writeInt(1);
            p4.writeInt(v0_14.intValue());
        } else {
            p4.writeInt(0);
        }
        p4.writeString(this.initBankNumber);
        p4.writeParcelable(this.dialogEntity, p5);
        p4.writeString(this.qrContent);
        java.util.Iterator v0_19 = this.popup;
        if (v0_19 != null) {
            p4.writeInt(1);
            v0_19.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        java.util.Iterator v0_21 = this.toggleView;
        p4.writeInt(v0_21.size());
        java.util.Iterator v0_22 = v0_21.iterator();
        while (v0_22.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) v0_22.next()).writeToParcel(p4, p5);
        }
        return;
    }
}
