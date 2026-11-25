package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankDatePickerDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bindingType;
    private final String clientBankCode;
    private final int defaultYear;
    private final int endYear;
    private final String error;
    private final String firstNum;
    private final String flowSessionId;
    private final boolean isIssueDate;
    private final String issuerBankName;
    private final int kycLevel;
    private final String lastNum;
    private final String message;
    private final String previousValue;
    private final String requestCode;
    private final int startYear;
    private final String title;

    static BankDatePickerDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument.CREATOR = new Zn.a(19);
        return;
    }

    public BankDatePickerDialogArgument(String p1, String p2, String p3, String p4, int p5, int p6, int p7, boolean p8, String p9, String p10, String p11, String p12, String p13, String p14, int p15, String p16, int p17)
    {
        this.requestCode = p1;
        this.title = p2;
        this.message = p3;
        this.error = p4;
        this.startYear = p5;
        this.endYear = p6;
        this.defaultYear = p7;
        this.isIssueDate = p8;
        this.previousValue = p9;
        this.bankCode = p10;
        this.clientBankCode = p11;
        this.firstNum = p12;
        this.lastNum = p13;
        this.issuerBankName = p14;
        this.bindingType = p15;
        this.flowSessionId = p16;
        this.kycLevel = p17;
        return;
    }

    public synthetic BankDatePickerDialogArgument(String p21, String p22, String p23, String p24, int p25, int p26, int p27, boolean p28, String p29, String p30, String p31, String p32, String p33, String p34, int p35, String p36, int p37, int p38, kotlin.jvm.internal.DefaultConstructorMarker p39)
    {
        String v6;
        if ((p38 & 8) == 0) {
            v6 = p24;
        } else {
            v6 = "";
        }
        boolean v10;
        if ((p38 & 128) == 0) {
            v10 = p28;
        } else {
            v10 = 1;
        }
        this(p21, p22, p23, v6, p25, p26, p27, v10, p29, p30, p31, p32, p33, p34, p35, p36, p37);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument p17, String p18, String p19, String p20, String p21, int p22, int p23, int p24, boolean p25, String p26, String p27, String p28, String p29, String p30, String p31, int p32, String p33, int p34, int p35, Object p36)
    {
        int v2_1;
        if ((p35 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.requestCode;
        }
        String v3_1;
        if ((p35 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.title;
        }
        String v4_1;
        if ((p35 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.message;
        }
        String v5_1;
        if ((p35 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.error;
        }
        int v6_1;
        if ((p35 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.startYear;
        }
        int v7_1;
        if ((p35 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.endYear;
        }
        int v8_1;
        if ((p35 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.defaultYear;
        }
        boolean v9_1;
        if ((p35 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.isIssueDate;
        }
        String v10_1;
        if ((p35 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.previousValue;
        }
        String v11_1;
        if ((p35 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.bankCode;
        }
        String v12_1;
        if ((p35 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.clientBankCode;
        }
        String v13_1;
        if ((p35 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.firstNum;
        }
        String v14_1;
        if ((p35 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.lastNum;
        }
        String v15_1;
        if ((p35 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.issuerBankName;
        }
        int v2_3;
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument v18_2 = v2_1;
        if ((p35 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.bindingType;
        }
        int v1_1;
        if ((p35 & 32768) == 0) {
            v1_1 = p33;
        } else {
            v1_1 = p17.flowSessionId;
        }
        String v35_1;
        String v27_1;
        String v30_1;
        String v29_1;
        String v32_1;
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument v19_1;
        String v28_1;
        int v25_1;
        boolean v26_1;
        int v34_1;
        int v24_1;
        String v31_1;
        int v33_1;
        int v23_1;
        String v21_1;
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument v18_1;
        String v22_1;
        String v20_1;
        if ((p35 & 65536) == 0) {
            v35_1 = p34;
            v34_1 = v1_1;
            v19_1 = v18_2;
            v18_1 = p17;
            v33_1 = v2_3;
            v20_1 = v3_1;
            v21_1 = v4_1;
            v22_1 = v5_1;
            v23_1 = v6_1;
            v24_1 = v7_1;
            v25_1 = v8_1;
            v26_1 = v9_1;
            v27_1 = v10_1;
            v28_1 = v11_1;
            v29_1 = v12_1;
            v30_1 = v13_1;
            v31_1 = v14_1;
            v32_1 = v15_1;
        } else {
            v34_1 = v1_1;
            v35_1 = p17.kycLevel;
            v33_1 = v2_3;
            v20_1 = v3_1;
            v21_1 = v4_1;
            v22_1 = v5_1;
            v23_1 = v6_1;
            v24_1 = v7_1;
            v25_1 = v8_1;
            v26_1 = v9_1;
            v27_1 = v10_1;
            v28_1 = v11_1;
            v29_1 = v12_1;
            v30_1 = v13_1;
            v31_1 = v14_1;
            v32_1 = v15_1;
            v19_1 = v18_2;
            v18_1 = p17;
        }
        return v18_1.copy(v19_1, v20_1, v21_1, v22_1, v23_1, v24_1, v25_1, v26_1, v27_1, v28_1, v29_1, v30_1, v31_1, v32_1, v33_1, v34_1, v35_1);
    }

    public final String component1()
    {
        return this.requestCode;
    }

    public final String component10()
    {
        return this.bankCode;
    }

    public final String component11()
    {
        return this.clientBankCode;
    }

    public final String component12()
    {
        return this.firstNum;
    }

    public final String component13()
    {
        return this.lastNum;
    }

    public final String component14()
    {
        return this.issuerBankName;
    }

    public final int component15()
    {
        return this.bindingType;
    }

    public final String component16()
    {
        return this.flowSessionId;
    }

    public final int component17()
    {
        return this.kycLevel;
    }

    public final String component2()
    {
        return this.title;
    }

    public final String component3()
    {
        return this.message;
    }

    public final String component4()
    {
        return this.error;
    }

    public final int component5()
    {
        return this.startYear;
    }

    public final int component6()
    {
        return this.endYear;
    }

    public final int component7()
    {
        return this.defaultYear;
    }

    public final boolean component8()
    {
        return this.isIssueDate;
    }

    public final String component9()
    {
        return this.previousValue;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument copy(String p19, String p20, String p21, String p22, int p23, int p24, int p25, boolean p26, String p27, String p28, String p29, String p30, String p31, String p32, int p33, String p34, int p35)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument(p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).requestCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).title)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).message)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.error, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).error)) {
                                if (this.startYear == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).startYear) {
                                    if (this.endYear == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).endYear) {
                                        if (this.defaultYear == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).defaultYear) {
                                            if (this.isIssueDate == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).isIssueDate) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.previousValue, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).previousValue)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).bankCode)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).clientBankCode)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.firstNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).firstNum)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.lastNum, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).lastNum)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).issuerBankName)) {
                                                                        if (this.bindingType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).bindingType) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.flowSessionId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).flowSessionId)) {
                                                                                if (this.kycLevel == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) p5).kycLevel) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final int getBindingType()
    {
        return this.bindingType;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final int getDefaultYear()
    {
        return this.defaultYear;
    }

    public final int getEndYear()
    {
        return this.endYear;
    }

    public final String getError()
    {
        return this.error;
    }

    public final String getFirstNum()
    {
        return this.firstNum;
    }

    public final String getFlowSessionId()
    {
        return this.flowSessionId;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public final int getKycLevel()
    {
        return this.kycLevel;
    }

    public final String getLastNum()
    {
        return this.lastNum;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final String getPreviousValue()
    {
        return this.previousValue;
    }

    public final String getRequestCode()
    {
        return this.requestCode;
    }

    public final int getStartYear()
    {
        return this.startYear;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        return ((((((((((((((((((((((((((((((((this.requestCode.hashCode() * 31) + this.title.hashCode()) * 31) + this.message.hashCode()) * 31) + this.error.hashCode()) * 31) + Integer.hashCode(this.startYear)) * 31) + Integer.hashCode(this.endYear)) * 31) + Integer.hashCode(this.defaultYear)) * 31) + Boolean.hashCode(this.isIssueDate)) * 31) + this.previousValue.hashCode()) * 31) + this.bankCode.hashCode()) * 31) + this.clientBankCode.hashCode()) * 31) + this.firstNum.hashCode()) * 31) + this.lastNum.hashCode()) * 31) + this.issuerBankName.hashCode()) * 31) + Integer.hashCode(this.bindingType)) * 31) + this.flowSessionId.hashCode()) * 31) + Integer.hashCode(this.kycLevel));
    }

    public final boolean isIssueDate()
    {
        return this.isIssueDate;
    }

    public String toString()
    {
        String v1_0 = this.requestCode;
        String v2 = this.title;
        String v3 = this.message;
        String v4 = this.error;
        int v5 = this.startYear;
        int v6 = this.endYear;
        int v7 = this.defaultYear;
        boolean v8 = this.isIssueDate;
        String v9 = this.previousValue;
        String v10 = this.bankCode;
        String v11 = this.clientBankCode;
        String v12 = this.firstNum;
        String v13 = this.lastNum;
        String v14 = this.issuerBankName;
        int v16 = this.bindingType;
        String v17 = this.flowSessionId;
        int v18 = this.kycLevel;
        String v0_2 = new StringBuilder("BankDatePickerDialogArgument(requestCode=");
        v0_2.append(v1_0);
        v0_2.append(", title=");
        v0_2.append(v2);
        v0_2.append(", message=");
        v0_2.append(v3);
        v0_2.append(", error=");
        v0_2.append(v4);
        v0_2.append(", startYear=");
        v0_2.append(v5);
        v0_2.append(", endYear=");
        v0_2.append(v6);
        v0_2.append(", defaultYear=");
        v0_2.append(v7);
        v0_2.append(", isIssueDate=");
        v0_2.append(v8);
        v0_2.append(", previousValue=");
        v0_2.append(v9);
        v0_2.append(", bankCode=");
        v0_2.append(v10);
        v0_2.append(", clientBankCode=");
        v0_2.append(v11);
        v0_2.append(", firstNum=");
        v0_2.append(v12);
        v0_2.append(", lastNum=");
        v0_2.append(v13);
        v0_2.append(", issuerBankName=");
        v0_2.append(v14);
        v0_2.append(", bindingType=");
        v0_2.append(v16);
        v0_2.append(", flowSessionId=");
        v0_2.append(v17);
        v0_2.append(", kycLevel=");
        v0_2.append(v18);
        v0_2.append(")");
        return v0_2.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.requestCode);
        p1.writeString(this.title);
        p1.writeString(this.message);
        p1.writeString(this.error);
        p1.writeInt(this.startYear);
        p1.writeInt(this.endYear);
        p1.writeInt(this.defaultYear);
        p1.writeInt(this.isIssueDate);
        p1.writeString(this.previousValue);
        p1.writeString(this.bankCode);
        p1.writeString(this.clientBankCode);
        p1.writeString(this.firstNum);
        p1.writeString(this.lastNum);
        p1.writeString(this.issuerBankName);
        p1.writeInt(this.bindingType);
        p1.writeString(this.flowSessionId);
        p1.writeInt(this.kycLevel);
        return;
    }
}
