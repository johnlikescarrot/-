package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankSecurityEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int actionType;
    private final Integer charType;
    private final java.util.List extraInfoStep;
    private final Integer maxLength;
    private final String message;
    private final Long otpTimeout;
    private final String redirectUrl;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity validator;

    static BankSecurityEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity.CREATOR = new Io.S(15);
        return;
    }

    public BankSecurityEntity(String p1, int p2, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p3, Long p4, String p5, Integer p6, Integer p7, java.util.List p8)
    {
        this.redirectUrl = p1;
        this.actionType = p2;
        this.validator = p3;
        this.otpTimeout = p4;
        this.message = p5;
        this.charType = p6;
        this.maxLength = p7;
        this.extraInfoStep = p8;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity p0, String p1, int p2, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p3, Long p4, String p5, Integer p6, Integer p7, java.util.List p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.redirectUrl;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.actionType;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.validator;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.otpTimeout;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.message;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.charType;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.maxLength;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.extraInfoStep;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.redirectUrl;
    }

    public final int component2()
    {
        return this.actionType;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity component3()
    {
        return this.validator;
    }

    public final Long component4()
    {
        return this.otpTimeout;
    }

    public final String component5()
    {
        return this.message;
    }

    public final Integer component6()
    {
        return this.charType;
    }

    public final Integer component7()
    {
        return this.maxLength;
    }

    public final java.util.List component8()
    {
        return this.extraInfoStep;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity copy(String p10, int p11, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p12, Long p13, String p14, Integer p15, Integer p16, java.util.List p17)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.redirectUrl, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).redirectUrl)) {
                    if (this.actionType == ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).actionType) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.validator, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).validator)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpTimeout, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).otpTimeout)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).message)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.charType, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).charType)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.maxLength, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).maxLength)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.extraInfoStep, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity) p5).extraInfoStep)) {
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

    public final int getActionType()
    {
        return this.actionType;
    }

    public final Integer getCharType()
    {
        return this.charType;
    }

    public final java.util.List getExtraInfoStep()
    {
        return this.extraInfoStep;
    }

    public final Integer getMaxLength()
    {
        return this.maxLength;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final Long getOtpTimeout()
    {
        return this.otpTimeout;
    }

    public final String getRedirectUrl()
    {
        return this.redirectUrl;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity getValidator()
    {
        return this.validator;
    }

    public int hashCode()
    {
        java.util.List v1_0;
        int v0_15 = (((this.redirectUrl.hashCode() * 31) + Integer.hashCode(this.actionType)) * 31);
        java.util.List v1_12 = this.validator;
        int v2 = 0;
        if (v1_12 != null) {
            v1_0 = v1_12.hashCode();
        } else {
            v1_0 = 0;
        }
        java.util.List v1_2;
        int v0_2 = ((v0_15 + v1_0) * 31);
        java.util.List v1_1 = this.otpTimeout;
        if (v1_1 != null) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        java.util.List v1_4;
        int v0_5 = ((v0_2 + v1_2) * 31);
        java.util.List v1_3 = this.message;
        if (v1_3 != null) {
            v1_4 = v1_3.hashCode();
        } else {
            v1_4 = 0;
        }
        java.util.List v1_6;
        int v0_8 = ((v0_5 + v1_4) * 31);
        java.util.List v1_5 = this.charType;
        if (v1_5 != null) {
            v1_6 = v1_5.hashCode();
        } else {
            v1_6 = 0;
        }
        java.util.List v1_9;
        int v0_10 = ((v0_8 + v1_6) * 31);
        java.util.List v1_7 = this.maxLength;
        if (v1_7 != null) {
            v1_9 = v1_7.hashCode();
        } else {
            v1_9 = 0;
        }
        int v0_12 = ((v0_10 + v1_9) * 31);
        java.util.List v1_10 = this.extraInfoStep;
        if (v1_10 != null) {
            v2 = v1_10.hashCode();
        }
        return (v0_12 + v2);
    }

    public String toString()
    {
        String v0_0 = this.redirectUrl;
        int v1 = this.actionType;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity v2 = this.validator;
        Long v3 = this.otpTimeout;
        String v4 = this.message;
        Integer v5 = this.charType;
        Integer v6 = this.maxLength;
        java.util.List v7 = this.extraInfoStep;
        StringBuilder v8_0 = new StringBuilder("BankSecurityEntity(redirectUrl=");
        v8_0.append(v0_0);
        v8_0.append(", actionType=");
        v8_0.append(v1);
        v8_0.append(", validator=");
        v8_0.append(v2);
        v8_0.append(", otpTimeout=");
        v8_0.append(v3);
        v8_0.append(", message=");
        v8_0.append(v4);
        v8_0.append(", charType=");
        v8_0.append(v5);
        v8_0.append(", maxLength=");
        v8_0.append(v6);
        v8_0.append(", extraInfoStep=");
        v8_0.append(v7);
        v8_0.append(")");
        return v8_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p6, int p7)
    {
        p6.writeString(this.redirectUrl);
        p6.writeInt(this.actionType);
        java.util.Iterator v0_9 = this.validator;
        if (v0_9 != null) {
            p6.writeInt(1);
            v0_9.writeToParcel(p6, p7);
        } else {
            p6.writeInt(0);
        }
        java.util.Iterator v0_1 = this.otpTimeout;
        if (v0_1 != null) {
            p6.writeInt(1);
            p6.writeLong(v0_1.longValue());
        } else {
            p6.writeInt(0);
        }
        p6.writeString(this.message);
        java.util.Iterator v0_4 = this.charType;
        if (v0_4 != null) {
            p6.writeInt(1);
            p6.writeInt(v0_4.intValue());
        } else {
            p6.writeInt(0);
        }
        java.util.Iterator v0_6 = this.maxLength;
        if (v0_6 != null) {
            p6.writeInt(1);
            p6.writeInt(v0_6.intValue());
        } else {
            p6.writeInt(0);
        }
        java.util.Iterator v0_8 = this.extraInfoStep;
        if (v0_8 != null) {
            p6.writeInt(1);
            p6.writeInt(v0_8.size());
            java.util.Iterator v0_10 = v0_8.iterator();
            while (v0_10.hasNext()) {
                ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity) v0_10.next()).writeToParcel(p6, p7);
            }
            return;
        } else {
            p6.writeInt(0);
            return;
        }
    }
}
