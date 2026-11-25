package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankRopArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams bankBindingInfoParams;
    private final String bankCode;
    private final String clientBankCode;
    private final java.util.List fields;
    private final int functionId;
    private final vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel initKycValueModel;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity otpDetectorEntity;

    static BankRopArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument.CREATOR = new no.e(3);
        return;
    }

    public BankRopArgument(int p1, java.util.List p2, vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p3, vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams p4, String p5, String p6, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p7)
    {
        this.functionId = p1;
        this.fields = p2;
        this.initKycValueModel = p3;
        this.bankBindingInfoParams = p4;
        this.bankCode = p5;
        this.clientBankCode = p6;
        this.otpDetectorEntity = p7;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument p0, int p1, java.util.List p2, vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p3, vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams p4, String p5, String p6, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.functionId;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.fields;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.initKycValueModel;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.bankBindingInfoParams;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.bankCode;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.clientBankCode;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.otpDetectorEntity;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final int component1()
    {
        return this.functionId;
    }

    public final java.util.List component2()
    {
        return this.fields;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel component3()
    {
        return this.initKycValueModel;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams component4()
    {
        return this.bankBindingInfoParams;
    }

    public final String component5()
    {
        return this.bankCode;
    }

    public final String component6()
    {
        return this.clientBankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity component7()
    {
        return this.otpDetectorEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument copy(int p9, java.util.List p10, vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p11, vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams p12, String p13, String p14, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument)) {
                if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) p5).functionId) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.fields, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) p5).fields)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.initKycValueModel, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) p5).initKycValueModel)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankBindingInfoParams, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) p5).bankBindingInfoParams)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) p5).bankCode)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) p5).clientBankCode)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpDetectorEntity, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopArgument) p5).otpDetectorEntity)) {
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

    public final vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams getBankBindingInfoParams()
    {
        return this.bankBindingInfoParams;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getBankHolderName()
    {
        String v0_1 = this.bankBindingInfoParams.getHolderName();
        if (v0_1 == null) {
            v0_1 = "";
        }
        return v0_1;
    }

    public final String getBankNumber()
    {
        String v0_1 = this.bankBindingInfoParams.getBankNumber();
        if (v0_1 == null) {
            v0_1 = "";
        }
        return v0_1;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final java.util.List getFields()
    {
        return this.fields;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel getInitKycValueModel()
    {
        return this.initKycValueModel;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity getOtpDetectorEntity()
    {
        return this.otpDetectorEntity;
    }

    public int hashCode()
    {
        int v1_8;
        int v0_9 = (((((((((((Integer.hashCode(this.functionId) * 31) + this.fields.hashCode()) * 31) + this.initKycValueModel.hashCode()) * 31) + this.bankBindingInfoParams.hashCode()) * 31) + this.bankCode.hashCode()) * 31) + this.clientBankCode.hashCode()) * 31);
        int v1_7 = this.otpDetectorEntity;
        if (v1_7 != 0) {
            v1_8 = v1_7.hashCode();
        } else {
            v1_8 = 0;
        }
        return (v0_9 + v1_8);
    }

    public String toString()
    {
        String v0_0 = this.functionId;
        java.util.List v1 = this.fields;
        vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel v2 = this.initKycValueModel;
        vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams v3 = this.bankBindingInfoParams;
        String v4 = this.bankCode;
        String v5 = this.clientBankCode;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v6 = this.otpDetectorEntity;
        StringBuilder v7_1 = new StringBuilder("BankRopArgument(functionId=");
        v7_1.append(v0_0);
        v7_1.append(", fields=");
        v7_1.append(v1);
        v7_1.append(", initKycValueModel=");
        v7_1.append(v2);
        v7_1.append(", bankBindingInfoParams=");
        v7_1.append(v3);
        v7_1.append(", bankCode=");
        v7_1.append(v4);
        v7_1.append(", clientBankCode=");
        v7_1.append(v5);
        v7_1.append(", otpDetectorEntity=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.functionId);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_6 = this.fields;
        p3.writeInt(v0_6.size());
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_7 = v0_6.iterator();
        while (v0_7.hasNext()) {
            p3.writeParcelable(((android.os.Parcelable) v0_7.next()), p4);
        }
        this.initKycValueModel.writeToParcel(p3, p4);
        this.bankBindingInfoParams.writeToParcel(p3, p4);
        p3.writeString(this.bankCode);
        p3.writeString(this.clientBankCode);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_5 = this.otpDetectorEntity;
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
