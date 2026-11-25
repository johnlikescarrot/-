package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankOtpArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final Integer charType;
    private final String clientBankCode;
    private final String firstNum;
    private final int fromSource;
    private final int functionId;
    private final String lastNum;
    private final Integer maxLength;
    private final String message;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity otpDetectorEntity;
    private final int step;
    private final Long timeOut;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity validator;
    private final String zpTransId;

    static BankOtpArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument.CREATOR = new Zn.a(29);
        return;
    }

    public BankOtpArgument(String p1, int p2, String p3, Long p4, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p5, Integer p6, String p7, Integer p8, int p9, String p10, String p11, String p12, int p13, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p14)
    {
        this.bankCode = p1;
        this.functionId = p2;
        this.message = p3;
        this.timeOut = p4;
        this.validator = p5;
        this.charType = p6;
        this.zpTransId = p7;
        this.maxLength = p8;
        this.step = p9;
        this.firstNum = p10;
        this.lastNum = p11;
        this.clientBankCode = p12;
        this.fromSource = p13;
        this.otpDetectorEntity = p14;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument p14, String p15, int p16, String p17, Long p18, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p19, Integer p20, String p21, Integer p22, int p23, String p24, String p25, String p26, int p27, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p28, int p29, Object p30)
    {
        String v1_1;
        if ((p29 & 1) == 0) {
            v1_1 = p15;
        } else {
            v1_1 = p14.bankCode;
        }
        int v2_1;
        if ((p29 & 2) == 0) {
            v2_1 = p16;
        } else {
            v2_1 = p14.functionId;
        }
        String v3_0;
        if ((p29 & 4) == 0) {
            v3_0 = p17;
        } else {
            v3_0 = p14.message;
        }
        Long v4_1;
        if ((p29 & 8) == 0) {
            v4_1 = p18;
        } else {
            v4_1 = p14.timeOut;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity v5_1;
        if ((p29 & 16) == 0) {
            v5_1 = p19;
        } else {
            v5_1 = p14.validator;
        }
        Integer v6_1;
        if ((p29 & 32) == 0) {
            v6_1 = p20;
        } else {
            v6_1 = p14.charType;
        }
        String v7_1;
        if ((p29 & 64) == 0) {
            v7_1 = p21;
        } else {
            v7_1 = p14.zpTransId;
        }
        Integer v8_1;
        if ((p29 & 128) == 0) {
            v8_1 = p22;
        } else {
            v8_1 = p14.maxLength;
        }
        int v9_1;
        if ((p29 & 256) == 0) {
            v9_1 = p23;
        } else {
            v9_1 = p14.step;
        }
        String v10_1;
        if ((p29 & 512) == 0) {
            v10_1 = p24;
        } else {
            v10_1 = p14.firstNum;
        }
        String v11_1;
        if ((p29 & 1024) == 0) {
            v11_1 = p25;
        } else {
            v11_1 = p14.lastNum;
        }
        String v12_1;
        if ((p29 & 2048) == 0) {
            v12_1 = p26;
        } else {
            v12_1 = p14.clientBankCode;
        }
        int v13_1;
        if ((p29 & 4096) == 0) {
            v13_1 = p27;
        } else {
            v13_1 = p14.fromSource;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v29_1;
        if ((p29 & 8192) == 0) {
            v29_1 = p28;
        } else {
            v29_1 = p14.otpDetectorEntity;
        }
        return p14.copy(v1_1, v2_1, v3_0, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v29_1);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component10()
    {
        return this.firstNum;
    }

    public final String component11()
    {
        return this.lastNum;
    }

    public final String component12()
    {
        return this.clientBankCode;
    }

    public final int component13()
    {
        return this.fromSource;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity component14()
    {
        return this.otpDetectorEntity;
    }

    public final int component2()
    {
        return this.functionId;
    }

    public final String component3()
    {
        return this.message;
    }

    public final Long component4()
    {
        return this.timeOut;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity component5()
    {
        return this.validator;
    }

    public final Integer component6()
    {
        return this.charType;
    }

    public final String component7()
    {
        return this.zpTransId;
    }

    public final Integer component8()
    {
        return this.maxLength;
    }

    public final int component9()
    {
        return this.step;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument copy(String p16, int p17, String p18, Long p19, vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p20, Integer p21, String p22, Integer p23, int p24, String p25, String p26, String p27, int p28, vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p29)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument(p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).bankCode)) {
                    if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).functionId) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).message)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.timeOut, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).timeOut)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.validator, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).validator)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.charType, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).charType)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).zpTransId)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.maxLength, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).maxLength)) {
                                                if (this.step == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).step) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.firstNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).firstNum)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.lastNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).lastNum)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).clientBankCode)) {
                                                                if (this.fromSource == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).fromSource) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpDetectorEntity, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) p5).otpDetectorEntity)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final Integer getCharType()
    {
        return this.charType;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final String getFirstNum()
    {
        return this.firstNum;
    }

    public final int getFromSource()
    {
        return this.fromSource;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final String getLastNum()
    {
        return this.lastNum;
    }

    public final Integer getMaxLength()
    {
        return this.maxLength;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity getOtpDetectorEntity()
    {
        return this.otpDetectorEntity;
    }

    public final int getStep()
    {
        return this.step;
    }

    public final Long getTimeOut()
    {
        return this.timeOut;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity getValidator()
    {
        return this.validator;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_0;
        int v0_27 = (((this.bankCode.hashCode() * 31) + Integer.hashCode(this.functionId)) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_24 = this.message;
        int v2 = 0;
        if (v1_24 != null) {
            v1_0 = v1_24.hashCode();
        } else {
            v1_0 = 0;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_2;
        int v0_2 = ((v0_27 + v1_0) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_1 = this.timeOut;
        if (v1_1 != null) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_4;
        int v0_5 = ((v0_2 + v1_2) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_3 = this.validator;
        if (v1_3 != null) {
            v1_4 = v1_3.hashCode();
        } else {
            v1_4 = 0;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_6;
        int v0_8 = ((v0_5 + v1_4) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_5 = this.charType;
        if (v1_5 != null) {
            v1_6 = v1_5.hashCode();
        } else {
            v1_6 = 0;
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_11;
        int v0_12 = ((((v0_8 + v1_6) * 31) + this.zpTransId.hashCode()) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_10 = this.maxLength;
        if (v1_10 != null) {
            v1_11 = v1_10.hashCode();
        } else {
            v1_11 = 0;
        }
        int v0_25 = ((((((((((((v0_12 + v1_11) * 31) + Integer.hashCode(this.step)) * 31) + this.firstNum.hashCode()) * 31) + this.lastNum.hashCode()) * 31) + this.clientBankCode.hashCode()) * 31) + Integer.hashCode(this.fromSource)) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v1_23 = this.otpDetectorEntity;
        if (v1_23 != null) {
            v2 = v1_23.hashCode();
        }
        return (v0_25 + v2);
    }

    public String toString()
    {
        String v1 = this.bankCode;
        int v2 = this.functionId;
        String v3 = this.message;
        Long v4 = this.timeOut;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity v5 = this.validator;
        Integer v6 = this.charType;
        String v7 = this.zpTransId;
        Integer v8 = this.maxLength;
        int v9 = this.step;
        String v10 = this.firstNum;
        String v11 = this.lastNum;
        String v12 = this.clientBankCode;
        int v13 = this.fromSource;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v14 = this.otpDetectorEntity;
        StringBuilder v15_1 = new StringBuilder("BankOtpArgument(bankCode=");
        v15_1.append(v1);
        v15_1.append(", functionId=");
        v15_1.append(v2);
        v15_1.append(", message=");
        v15_1.append(v3);
        v15_1.append(", timeOut=");
        v15_1.append(v4);
        v15_1.append(", validator=");
        v15_1.append(v5);
        v15_1.append(", charType=");
        v15_1.append(v6);
        v15_1.append(", zpTransId=");
        v15_1.append(v7);
        v15_1.append(", maxLength=");
        v15_1.append(v8);
        v15_1.append(", step=");
        v15_1.append(v9);
        v15_1.append(", firstNum=");
        v15_1.append(v10);
        v15_1.append(", lastNum=");
        v15_1.append(v11);
        v15_1.append(", clientBankCode=");
        v15_1.append(v12);
        v15_1.append(", fromSource=");
        v15_1.append(v13);
        v15_1.append(", otpDetectorEntity=");
        v15_1.append(v14);
        v15_1.append(")");
        return v15_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p6, int p7)
    {
        p6.writeString(this.bankCode);
        p6.writeInt(this.functionId);
        p6.writeString(this.message);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_15 = this.timeOut;
        if (v0_15 != null) {
            p6.writeInt(1);
            p6.writeLong(v0_15.longValue());
        } else {
            p6.writeInt(0);
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_1 = this.validator;
        if (v0_1 != null) {
            p6.writeInt(1);
            v0_1.writeToParcel(p6, p7);
        } else {
            p6.writeInt(0);
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_3 = this.charType;
        if (v0_3 != null) {
            p6.writeInt(1);
            p6.writeInt(v0_3.intValue());
        } else {
            p6.writeInt(0);
        }
        p6.writeString(this.zpTransId);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_6 = this.maxLength;
        if (v0_6 != null) {
            p6.writeInt(1);
            p6.writeInt(v0_6.intValue());
        } else {
            p6.writeInt(0);
        }
        p6.writeInt(this.step);
        p6.writeString(this.firstNum);
        p6.writeString(this.lastNum);
        p6.writeString(this.clientBankCode);
        p6.writeInt(this.fromSource);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity v0_14 = this.otpDetectorEntity;
        if (v0_14 != null) {
            p6.writeInt(1);
            v0_14.writeToParcel(p6, p7);
            return;
        } else {
            p6.writeInt(0);
            return;
        }
    }
}
