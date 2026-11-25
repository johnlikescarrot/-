package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingTypeModel$InternetBankingModel extends uo.r implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String bankName;
    private final String clientBankCode;
    private final vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity dialogEntity;
    private final String id;
    private final String information;
    private final String initBankNumber;
    private final int order;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity popup;
    private final String qrContent;
    private final String tabLabel;
    private final String title;
    private final java.util.List toggleView;

    static BankBindingTypeModel$InternetBankingModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel.CREATOR = new no.e(29);
        return;
    }

    public BankBindingTypeModel$InternetBankingModel(String p12, String p13, String p14, String p15, int p16, String p17, String p18, String p19, String p20, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p21, String p22, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p23, java.util.List p24)
    {
        super(p12, p16, p17, p14, p18, p19, p20, p21, p22, p24);
        super.id = p12;
        super.information = p13;
        super.tabLabel = p14;
        super.title = p15;
        super.order = p16;
        super.bankName = p17;
        super.bankCode = p18;
        super.clientBankCode = p19;
        super.initBankNumber = p20;
        super.dialogEntity = p21;
        super.qrContent = p22;
        super.popup = p23;
        super.toggleView = p24;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel p12, String p13, String p14, String p15, String p16, int p17, String p18, String p19, String p20, String p21, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p22, String p23, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p24, java.util.List p25, int p26, Object p27)
    {
        if ((p26 & 1) != 0) {
            p13 = p12.id;
        }
        String v1_2;
        if ((p26 & 2) == 0) {
            v1_2 = p14;
        } else {
            v1_2 = p12.information;
        }
        String v2_0;
        if ((p26 & 4) == 0) {
            v2_0 = p15;
        } else {
            v2_0 = p12.tabLabel;
        }
        String v3_1;
        if ((p26 & 8) == 0) {
            v3_1 = p16;
        } else {
            v3_1 = p12.title;
        }
        int v4_1;
        if ((p26 & 16) == 0) {
            v4_1 = p17;
        } else {
            v4_1 = p12.order;
        }
        String v5_1;
        if ((p26 & 32) == 0) {
            v5_1 = p18;
        } else {
            v5_1 = p12.bankName;
        }
        String v6_1;
        if ((p26 & 64) == 0) {
            v6_1 = p19;
        } else {
            v6_1 = p12.bankCode;
        }
        String v7_1;
        if ((p26 & 128) == 0) {
            v7_1 = p20;
        } else {
            v7_1 = p12.clientBankCode;
        }
        String v8_1;
        if ((p26 & 256) == 0) {
            v8_1 = p21;
        } else {
            v8_1 = p12.initBankNumber;
        }
        vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity v9_1;
        if ((p26 & 512) == 0) {
            v9_1 = p22;
        } else {
            v9_1 = p12.dialogEntity;
        }
        String v10_1;
        if ((p26 & 1024) == 0) {
            v10_1 = p23;
        } else {
            v10_1 = p12.qrContent;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity v11_1;
        if ((p26 & 2048) == 0) {
            v11_1 = p24;
        } else {
            v11_1 = p12.popup;
        }
        if ((p26 & 4096) == 0) {
            p27 = p25;
        } else {
            p27 = p12.toggleView;
        }
        return p12.copy(p13, v1_2, v2_0, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, p27);
    }

    public final String component1()
    {
        return this.id;
    }

    public final vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity component10()
    {
        return this.dialogEntity;
    }

    public final String component11()
    {
        return this.qrContent;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity component12()
    {
        return this.popup;
    }

    public final java.util.List component13()
    {
        return this.toggleView;
    }

    public final String component2()
    {
        return this.information;
    }

    public final String component3()
    {
        return this.tabLabel;
    }

    public final String component4()
    {
        return this.title;
    }

    public final int component5()
    {
        return this.order;
    }

    public final String component6()
    {
        return this.bankName;
    }

    public final String component7()
    {
        return this.bankCode;
    }

    public final String component8()
    {
        return this.clientBankCode;
    }

    public final String component9()
    {
        return this.initBankNumber;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel copy(String p15, String p16, String p17, String p18, int p19, String p20, String p21, String p22, String p23, vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p24, String p25, vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p26, java.util.List p27)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel(p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.id, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).id)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.information, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).information)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.tabLabel, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).tabLabel)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).title)) {
                                if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).order) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).bankName)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).bankCode)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).clientBankCode)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.initBankNumber, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).initBankNumber)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.dialogEntity, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).dialogEntity)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.qrContent, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).qrContent)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.popup, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).popup)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.toggleView, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingTypeModel$InternetBankingModel) p5).toggleView)) {
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

    public String getBankCode()
    {
        return this.bankCode;
    }

    public String getBankName()
    {
        return this.bankName;
    }

    public String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity getDialogEntity()
    {
        return this.dialogEntity;
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

    public int getOrder()
    {
        return this.order;
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

    public final String getTitle()
    {
        return this.title;
    }

    public java.util.List getToggleView()
    {
        return this.toggleView;
    }

    public int hashCode()
    {
        int v1_10;
        int v0_12 = (((((((((((((this.id.hashCode() * 31) + this.information.hashCode()) * 31) + this.tabLabel.hashCode()) * 31) + this.title.hashCode()) * 31) + Integer.hashCode(this.order)) * 31) + this.bankName.hashCode()) * 31) + this.bankCode.hashCode()) * 31);
        int v1_9 = this.clientBankCode;
        int v2 = 0;
        if (v1_9 != 0) {
            v1_10 = v1_9.hashCode();
        } else {
            v1_10 = 0;
        }
        int v1_15;
        int v0_16 = ((((v0_12 + v1_10) * 31) + this.initBankNumber.hashCode()) * 31);
        int v1_14 = this.dialogEntity;
        if (v1_14 != 0) {
            v1_15 = v1_14.hashCode();
        } else {
            v1_15 = 0;
        }
        int v1_17;
        int v0_18 = ((v0_16 + v1_15) * 31);
        int v1_16 = this.qrContent;
        if (v1_16 != 0) {
            v1_17 = v1_16.hashCode();
        } else {
            v1_17 = 0;
        }
        int v0_20 = ((v0_18 + v1_17) * 31);
        int v1_19 = this.popup;
        if (v1_19 != 0) {
            v2 = v1_19.hashCode();
        }
        return (((v0_20 + v2) * 31) + this.toggleView.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        String v1 = this.information;
        String v2 = this.tabLabel;
        String v3 = this.title;
        int v4 = this.order;
        String v5 = this.bankName;
        String v6 = this.bankCode;
        String v7 = this.clientBankCode;
        String v8 = this.initBankNumber;
        vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity v9 = this.dialogEntity;
        String v10 = this.qrContent;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity v11 = this.popup;
        java.util.List v12 = this.toggleView;
        StringBuilder v13_1 = new StringBuilder("InternetBankingModel(id=");
        v13_1.append(v0_0);
        v13_1.append(", information=");
        v13_1.append(v1);
        v13_1.append(", tabLabel=");
        v13_1.append(v2);
        v13_1.append(", title=");
        v13_1.append(v3);
        v13_1.append(", order=");
        v13_1.append(v4);
        v13_1.append(", bankName=");
        v13_1.append(v5);
        v13_1.append(", bankCode=");
        v13_1.append(v6);
        v13_1.append(", clientBankCode=");
        v13_1.append(v7);
        v13_1.append(", initBankNumber=");
        v13_1.append(v8);
        v13_1.append(", dialogEntity=");
        v13_1.append(v9);
        v13_1.append(", qrContent=");
        v13_1.append(v10);
        v13_1.append(", popup=");
        v13_1.append(v11);
        v13_1.append(", toggleView=");
        v13_1.append(v12);
        v13_1.append(")");
        return v13_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.id);
        p3.writeString(this.information);
        p3.writeString(this.tabLabel);
        p3.writeString(this.title);
        p3.writeInt(this.order);
        p3.writeString(this.bankName);
        p3.writeString(this.bankCode);
        p3.writeString(this.clientBankCode);
        p3.writeString(this.initBankNumber);
        p3.writeParcelable(this.dialogEntity, p4);
        p3.writeString(this.qrContent);
        java.util.Iterator v0_8 = this.popup;
        if (v0_8 != null) {
            p3.writeInt(1);
            v0_8.writeToParcel(p3, p4);
        } else {
            p3.writeInt(0);
        }
        java.util.Iterator v0_10 = this.toggleView;
        p3.writeInt(v0_10.size());
        java.util.Iterator v0_11 = v0_10.iterator();
        while (v0_11.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) v0_11.next()).writeToParcel(p3, p4);
        }
        return;
    }
}
