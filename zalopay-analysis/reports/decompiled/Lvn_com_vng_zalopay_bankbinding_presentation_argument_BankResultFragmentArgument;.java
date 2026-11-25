package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankResultFragmentArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final Integer bcErrorCode;
    private final String description;
    private final Integer errorCode;
    private final int functionId;
    private final int resultType;
    private final String title;
    private final String zpTransId;

    static BankResultFragmentArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument.CREATOR = new no.e(2);
        return;
    }

    public BankResultFragmentArgument(String p1, String p2, String p3, String p4, Integer p5, Integer p6, int p7, int p8)
    {
        this.bankCode = p1;
        this.title = p2;
        this.description = p3;
        this.zpTransId = p4;
        this.bcErrorCode = p5;
        this.errorCode = p6;
        this.resultType = p7;
        this.functionId = p8;
        return;
    }

    public synthetic BankResultFragmentArgument(String p2, String p3, String p4, String p5, Integer p6, Integer p7, int p8, int p9, int p10, kotlin.jvm.internal.DefaultConstructorMarker p11)
    {
        if ((p10 & 2) != 0) {
            p3 = 0;
        }
        if ((p10 & 4) != 0) {
            p4 = 0;
        }
        if ((p10 & 8) != 0) {
            p5 = 0;
        }
        if ((p10 & 16) != 0) {
            p6 = 0;
        }
        int v9_1;
        int v10_2;
        Integer v8_1;
        if ((p10 & 32) == 0) {
            v10_2 = p9;
            v9_1 = p8;
            v8_1 = p7;
        } else {
            v10_2 = p9;
            v9_1 = p8;
            v8_1 = 0;
        }
        this(p2, p3, p4, p5, p6, v8_1, v9_1, v10_2);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument p0, String p1, String p2, String p3, String p4, Integer p5, Integer p6, int p7, int p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.title;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.description;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.zpTransId;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.bcErrorCode;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.errorCode;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.resultType;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.functionId;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.title;
    }

    public final String component3()
    {
        return this.description;
    }

    public final String component4()
    {
        return this.zpTransId;
    }

    public final Integer component5()
    {
        return this.bcErrorCode;
    }

    public final Integer component6()
    {
        return this.errorCode;
    }

    public final int component7()
    {
        return this.resultType;
    }

    public final int component8()
    {
        return this.functionId;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument copy(String p10, String p11, String p12, String p13, Integer p14, Integer p15, int p16, int p17)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).title)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.description, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).description)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).zpTransId)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bcErrorCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).bcErrorCode)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).errorCode)) {
                                        if (this.resultType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).resultType) {
                                            if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) p5).functionId) {
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

    public final Integer getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final String getDescription()
    {
        return this.description;
    }

    public final Integer getErrorCode()
    {
        return this.errorCode;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final int getResultType()
    {
        return this.resultType;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v1_12;
        int v0_8 = (this.bankCode.hashCode() * 31);
        int v1_8 = this.title;
        int v2 = 0;
        if (v1_8 != 0) {
            v1_12 = v1_8.hashCode();
        } else {
            v1_12 = 0;
        }
        int v1_1;
        int v0_2 = ((v0_8 + v1_12) * 31);
        int v1_0 = this.description;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        int v1_3;
        int v0_4 = ((v0_2 + v1_1) * 31);
        int v1_2 = this.zpTransId;
        if (v1_2 != 0) {
            v1_3 = v1_2.hashCode();
        } else {
            v1_3 = 0;
        }
        int v1_5;
        int v0_7 = ((v0_4 + v1_3) * 31);
        int v1_4 = this.bcErrorCode;
        if (v1_4 != 0) {
            v1_5 = v1_4.hashCode();
        } else {
            v1_5 = 0;
        }
        int v0_10 = ((v0_7 + v1_5) * 31);
        int v1_6 = this.errorCode;
        if (v1_6 != 0) {
            v2 = v1_6.hashCode();
        }
        return (((((v0_10 + v2) * 31) + Integer.hashCode(this.resultType)) * 31) + Integer.hashCode(this.functionId));
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.title;
        String v2 = this.description;
        String v3 = this.zpTransId;
        Integer v4 = this.bcErrorCode;
        Integer v5 = this.errorCode;
        int v6 = this.resultType;
        int v7 = this.functionId;
        StringBuilder v8_0 = new StringBuilder("BankResultFragmentArgument(bankCode=");
        v8_0.append(v0_0);
        v8_0.append(", title=");
        v8_0.append(v1);
        v8_0.append(", description=");
        v8_0.append(v2);
        v8_0.append(", zpTransId=");
        v8_0.append(v3);
        v8_0.append(", bcErrorCode=");
        v8_0.append(v4);
        v8_0.append(", errorCode=");
        v8_0.append(v5);
        v8_0.append(", resultType=");
        v8_0.append(v6);
        v8_0.append(", functionId=");
        v8_0.append(v7);
        v8_0.append(")");
        return v8_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.bankCode);
        p3.writeString(this.title);
        p3.writeString(this.description);
        p3.writeString(this.zpTransId);
        int v4_9 = this.bcErrorCode;
        if (v4_9 != 0) {
            p3.writeInt(1);
            p3.writeInt(v4_9.intValue());
        } else {
            p3.writeInt(0);
        }
        int v4_2 = this.errorCode;
        if (v4_2 != 0) {
            p3.writeInt(1);
            p3.writeInt(v4_2.intValue());
        } else {
            p3.writeInt(0);
        }
        p3.writeInt(this.resultType);
        p3.writeInt(this.functionId);
        return;
    }
}
