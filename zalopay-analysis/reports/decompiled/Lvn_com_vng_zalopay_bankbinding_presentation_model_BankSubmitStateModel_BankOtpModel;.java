package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$BankOtpModel extends uo.F implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final Integer charType;
    private final int functionId;
    private final Integer maxLength;
    private final String message;
    private final Long timeOut;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity validators;
    private final String zpTransId;

    static BankSubmitStateModel$BankOtpModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel.CREATOR = new uo.y(15);
        return;
    }

    public BankSubmitStateModel$BankOtpModel(String p1, Long p2, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p3, Integer p4, String p5, int p6, String p7, Integer p8)
    {
        this.message = p1;
        this.timeOut = p2;
        this.validators = p3;
        this.charType = p4;
        this.bankCode = p5;
        this.functionId = p6;
        this.zpTransId = p7;
        this.maxLength = p8;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel p0, String p1, Long p2, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p3, Integer p4, String p5, int p6, String p7, Integer p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.timeOut;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.validators;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.charType;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.bankCode;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.functionId;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.zpTransId;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.maxLength;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.message;
    }

    public final Long component2()
    {
        return this.timeOut;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity component3()
    {
        return this.validators;
    }

    public final Integer component4()
    {
        return this.charType;
    }

    public final String component5()
    {
        return this.bankCode;
    }

    public final int component6()
    {
        return this.functionId;
    }

    public final String component7()
    {
        return this.zpTransId;
    }

    public final Integer component8()
    {
        return this.maxLength;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel copy(String p10, Long p11, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p12, Integer p13, String p14, int p15, String p16, Integer p17)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).message)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.timeOut, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).timeOut)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).validators)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.charType, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).charType)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).bankCode)) {
                                    if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).functionId) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).zpTransId)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.maxLength, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankOtpModel) p5).maxLength)) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final Integer getCharType()
    {
        return this.charType;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final Integer getMaxLength()
    {
        return this.maxLength;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final Long getTimeOut()
    {
        return this.timeOut;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity getValidators()
    {
        return this.validators;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v0_11;
        int v0_0 = this.message;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_11 = v0_0.hashCode();
        } else {
            v0_11 = 0;
        }
        Integer v2_0;
        int v0_15 = (v0_11 * 31);
        Integer v2_12 = this.timeOut;
        if (v2_12 != null) {
            v2_0 = v2_12.hashCode();
        } else {
            v2_0 = 0;
        }
        Integer v2_2;
        int v0_2 = ((v0_15 + v2_0) * 31);
        Integer v2_1 = this.validators;
        if (v2_1 != null) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        Integer v2_4;
        int v0_4 = ((v0_2 + v2_2) * 31);
        Integer v2_3 = this.charType;
        if (v2_3 != null) {
            v2_4 = v2_3.hashCode();
        } else {
            v2_4 = 0;
        }
        int v0_13 = ((((((((v0_4 + v2_4) * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.functionId)) * 31) + this.zpTransId.hashCode()) * 31);
        Integer v2_11 = this.maxLength;
        if (v2_11 != null) {
            v1 = v2_11.hashCode();
        }
        return (v0_13 + v1);
    }

    public String toString()
    {
        String v0_0 = this.message;
        Long v1 = this.timeOut;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity v2 = this.validators;
        Integer v3 = this.charType;
        String v4 = this.bankCode;
        int v5 = this.functionId;
        String v6 = this.zpTransId;
        Integer v7 = this.maxLength;
        StringBuilder v8_0 = new StringBuilder("BankOtpModel(message=");
        v8_0.append(v0_0);
        v8_0.append(", timeOut=");
        v8_0.append(v1);
        v8_0.append(", validators=");
        v8_0.append(v2);
        v8_0.append(", charType=");
        v8_0.append(v3);
        v8_0.append(", bankCode=");
        v8_0.append(v4);
        v8_0.append(", functionId=");
        v8_0.append(v5);
        v8_0.append(", zpTransId=");
        v8_0.append(v6);
        v8_0.append(", maxLength=");
        v8_0.append(v7);
        v8_0.append(")");
        return v8_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p6, int p7)
    {
        p6.writeString(this.message);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity v0_2 = this.timeOut;
        if (v0_2 != null) {
            p6.writeInt(1);
            p6.writeLong(v0_2.longValue());
        } else {
            p6.writeInt(0);
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity v0_1 = this.validators;
        if (v0_1 != null) {
            p6.writeInt(1);
            v0_1.writeToParcel(p6, p7);
        } else {
            p6.writeInt(0);
        }
        int v7_1 = this.charType;
        if (v7_1 != 0) {
            p6.writeInt(1);
            p6.writeInt(v7_1.intValue());
        } else {
            p6.writeInt(0);
        }
        p6.writeString(this.bankCode);
        p6.writeInt(this.functionId);
        p6.writeString(this.zpTransId);
        int v7_6 = this.maxLength;
        if (v7_6 != 0) {
            p6.writeInt(1);
            p6.writeInt(v7_6.intValue());
            return;
        } else {
            p6.writeInt(0);
            return;
        }
    }
}
