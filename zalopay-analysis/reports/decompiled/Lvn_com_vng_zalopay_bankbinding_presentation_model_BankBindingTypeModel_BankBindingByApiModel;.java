package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingTypeModel$BankBindingByApiModel extends uo.r implements android.os.Parcelable {
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
    private final int order;
    private final String placeHolder;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity popup;
    private final String qrContent;
    private final String tabLabel;
    private final java.util.List toggleView;
    private final java.util.List validators;

    static BankBindingTypeModel$BankBindingByApiModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel.CREATOR = new no.e(28);
        return;
    }

    public BankBindingTypeModel$BankBindingByApiModel(String p12, java.util.List p13, String p14, String p15, String p16, int p17, String p18, String p19, String p20, int p21, vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p22, vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p23, String p24, String p25, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p26, String p27, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p28, String p29, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p30, java.util.List p31)
    {
        super(p12, p21, p16, p20, p24, p25, p27, p28, p29, p31);
        super.id = p12;
        super.validators = p13;
        super.information = p14;
        super.hint = p15;
        super.bankName = p16;
        super.maxLength = p17;
        super.label = p18;
        super.placeHolder = p19;
        super.tabLabel = p20;
        super.order = p21;
        super.cardGuide = p22;
        super.backAction = p23;
        super.bankCode = p24;
        super.clientBankCode = p25;
        super.helpers = p26;
        super.initBankNumber = p27;
        super.dialogEntity = p28;
        super.qrContent = p29;
        super.popup = p30;
        super.toggleView = p31;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel p17, String p18, java.util.List p19, String p20, String p21, String p22, int p23, String p24, String p25, String p26, int p27, vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p28, vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p29, String p30, String p31, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p32, String p33, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p34, String p35, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p36, java.util.List p37, int p38, Object p39)
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity v2_1;
        if ((p38 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.id;
        }
        java.util.List v3_1;
        if ((p38 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.validators;
        }
        String v4_1;
        if ((p38 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.information;
        }
        String v5_1;
        if ((p38 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.hint;
        }
        String v6_1;
        if ((p38 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.bankName;
        }
        int v7_1;
        if ((p38 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.maxLength;
        }
        String v8_1;
        if ((p38 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.label;
        }
        String v9_1;
        if ((p38 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.placeHolder;
        }
        String v10_1;
        if ((p38 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.tabLabel;
        }
        int v11_1;
        if ((p38 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.order;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity v12_1;
        if ((p38 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.cardGuide;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity v13_1;
        if ((p38 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.backAction;
        }
        String v14_1;
        if ((p38 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.bankCode;
        }
        String v15_1;
        if ((p38 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.clientBankCode;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity v2_3;
        if ((p38 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.helpers;
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
        return this.id;
    }

    public final int component10()
    {
        return this.order;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity component11()
    {
        return this.cardGuide;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity component12()
    {
        return this.backAction;
    }

    public final String component13()
    {
        return this.bankCode;
    }

    public final String component14()
    {
        return this.clientBankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity component15()
    {
        return this.helpers;
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

    public final java.util.List component2()
    {
        return this.validators;
    }

    public final java.util.List component20()
    {
        return this.toggleView;
    }

    public final String component3()
    {
        return this.information;
    }

    public final String component4()
    {
        return this.hint;
    }

    public final String component5()
    {
        return this.bankName;
    }

    public final int component6()
    {
        return this.maxLength;
    }

    public final String component7()
    {
        return this.label;
    }

    public final String component8()
    {
        return this.placeHolder;
    }

    public final String component9()
    {
        return this.tabLabel;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel copy(String p22, java.util.List p23, String p24, String p25, String p26, int p27, String p28, String p29, String p30, int p31, vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p32, vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p33, String p34, String p35, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p36, String p37, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p38, String p39, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p40, java.util.List p41)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel(p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.id, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).id)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).validators)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.information, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).information)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.hint, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).hint)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).bankName)) {
                                    if (this.maxLength == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).maxLength) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.label, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).label)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.placeHolder, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).placeHolder)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.tabLabel, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).tabLabel)) {
                                                    if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).order) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardGuide, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).cardGuide)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.backAction, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).backAction)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).bankCode)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).clientBankCode)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.helpers, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).helpers)) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.initBankNumber, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).initBankNumber)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.dialogEntity, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).dialogEntity)) {
                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.qrContent, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).qrContent)) {
                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.popup, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).popup)) {
                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.toggleView, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$BankBindingByApiModel) p5).toggleView)) {
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

    public String getBankCode()
    {
        return this.bankCode;
    }

    public String getBankName()
    {
        return this.bankName;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity getCardGuide()
    {
        return this.cardGuide;
    }

    public String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity getDialogEntity()
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

    public String getId()
    {
        return this.id;
    }

    public final String getInformation()
    {
        return this.information;
    }

    public String getInitBankNumber()
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

    public int getOrder()
    {
        return this.order;
    }

    public final String getPlaceHolder()
    {
        return this.placeHolder;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity getPopup()
    {
        return this.popup;
    }

    public String getQrContent()
    {
        return this.qrContent;
    }

    public String getTabLabel()
    {
        return this.tabLabel;
    }

    public java.util.List getToggleView()
    {
        return this.toggleView;
    }

    public final java.util.List getValidators()
    {
        return this.validators;
    }

    public int hashCode()
    {
        int v1_20;
        int v0_23 = (((((((((((((((((((this.id.hashCode() * 31) + this.validators.hashCode()) * 31) + this.information.hashCode()) * 31) + this.hint.hashCode()) * 31) + this.bankName.hashCode()) * 31) + Integer.hashCode(this.maxLength)) * 31) + this.label.hashCode()) * 31) + this.placeHolder.hashCode()) * 31) + this.tabLabel.hashCode()) * 31) + Integer.hashCode(this.order)) * 31);
        int v1_19 = this.cardGuide;
        int v2 = 0;
        if (v1_19 != 0) {
            v1_20 = v1_19.hashCode();
        } else {
            v1_20 = 0;
        }
        int v1_23;
        int v0_25 = ((v0_23 + v1_20) * 31);
        int v1_21 = this.backAction;
        if (v1_21 != 0) {
            v1_23 = v1_21.hashCode();
        } else {
            v1_23 = 0;
        }
        int v1_27;
        int v0_29 = ((((v0_25 + v1_23) * 31) + this.bankCode.hashCode()) * 31);
        int v1_26 = this.clientBankCode;
        if (v1_26 != 0) {
            v1_27 = v1_26.hashCode();
        } else {
            v1_27 = 0;
        }
        int v1_29;
        int v0_32 = ((v0_29 + v1_27) * 31);
        int v1_28 = this.helpers;
        if (v1_28 != 0) {
            v1_29 = v1_28.hashCode();
        } else {
            v1_29 = 0;
        }
        int v1_34;
        int v0_37 = ((((v0_32 + v1_29) * 31) + this.initBankNumber.hashCode()) * 31);
        int v1_32 = this.dialogEntity;
        if (v1_32 != 0) {
            v1_34 = v1_32.hashCode();
        } else {
            v1_34 = 0;
        }
        int v1_36;
        int v0_39 = ((v0_37 + v1_34) * 31);
        int v1_35 = this.qrContent;
        if (v1_35 != 0) {
            v1_36 = v1_35.hashCode();
        } else {
            v1_36 = 0;
        }
        int v0_2 = ((v0_39 + v1_36) * 31);
        int v1_0 = this.popup;
        if (v1_0 != 0) {
            v2 = v1_0.hashCode();
        }
        return (((v0_2 + v2) * 31) + this.toggleView.hashCode());
    }

    public String toString()
    {
        String v1_0 = this.id;
        java.util.List v2 = this.validators;
        String v3 = this.information;
        String v4 = this.hint;
        String v5 = this.bankName;
        int v6 = this.maxLength;
        String v7 = this.label;
        String v8 = this.placeHolder;
        String v9 = this.tabLabel;
        int v10 = this.order;
        vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity v11 = this.cardGuide;
        vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity v12 = this.backAction;
        String v13 = this.bankCode;
        String v14 = this.clientBankCode;
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity v16 = this.helpers;
        String v17 = this.initBankNumber;
        vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity v18 = this.dialogEntity;
        String v19 = this.qrContent;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity v20 = this.popup;
        java.util.List v21 = this.toggleView;
        String v0_2 = new StringBuilder("BankBindingByApiModel(id=");
        v0_2.append(v1_0);
        v0_2.append(", validators=");
        v0_2.append(v2);
        v0_2.append(", information=");
        v0_2.append(v3);
        v0_2.append(", hint=");
        v0_2.append(v4);
        v0_2.append(", bankName=");
        v0_2.append(v5);
        v0_2.append(", maxLength=");
        v0_2.append(v6);
        v0_2.append(", label=");
        v0_2.append(v7);
        v0_2.append(", placeHolder=");
        v0_2.append(v8);
        v0_2.append(", tabLabel=");
        v0_2.append(v9);
        v0_2.append(", order=");
        v0_2.append(v10);
        v0_2.append(", cardGuide=");
        v0_2.append(v11);
        v0_2.append(", backAction=");
        v0_2.append(v12);
        v0_2.append(", bankCode=");
        v0_2.append(v13);
        v0_2.append(", clientBankCode=");
        v0_2.append(v14);
        v0_2.append(", helpers=");
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
        p4.writeString(this.id);
        java.util.Iterator v0_6 = this.validators;
        p4.writeInt(v0_6.size());
        java.util.Iterator v0_21 = v0_6.iterator();
        while (v0_21.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) v0_21.next()).writeToParcel(p4, p5);
        }
        p4.writeString(this.information);
        p4.writeString(this.hint);
        p4.writeString(this.bankName);
        p4.writeInt(this.maxLength);
        p4.writeString(this.label);
        p4.writeString(this.placeHolder);
        p4.writeString(this.tabLabel);
        p4.writeInt(this.order);
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
        p4.writeString(this.bankCode);
        p4.writeString(this.clientBankCode);
        java.util.Iterator v0_14 = this.helpers;
        if (v0_14 != null) {
            p4.writeInt(1);
            v0_14.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        p4.writeString(this.initBankNumber);
        p4.writeParcelable(this.dialogEntity, p5);
        p4.writeString(this.qrContent);
        java.util.Iterator v0_18 = this.popup;
        if (v0_18 != null) {
            p4.writeInt(1);
            v0_18.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        java.util.Iterator v0_19 = this.toggleView;
        p4.writeInt(v0_19.size());
        java.util.Iterator v0_20 = v0_19.iterator();
        while (v0_20.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) v0_20.next()).writeToParcel(p4, p5);
        }
        return;
    }
}
