package vn.com.vng.zalopay.feature.argument;
public final class BankDetailArgs implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String background;
    private final String bankCode;
    private final String bankConnectorCode;
    private final int bankType;
    private final String description;
    private final String firstNum;
    private final String lastNum;
    private final String logo;
    private final String name;

    static BankDetailArgs()
    {
        vn.com.vng.zalopay.feature.argument.BankDetailArgs.CREATOR = new iG.g(18);
        return;
    }

    public BankDetailArgs(String p1, String p2, String p3, String p4, int p5, String p6, String p7, String p8, String p9)
    {
        this.bankCode = p1;
        this.bankConnectorCode = p2;
        this.firstNum = p3;
        this.lastNum = p4;
        this.bankType = p5;
        this.name = p6;
        this.description = p7;
        this.background = p8;
        this.logo = p9;
        return;
    }

    public static synthetic vn.com.vng.zalopay.feature.argument.BankDetailArgs copy$default(vn.com.vng.zalopay.feature.argument.BankDetailArgs p0, String p1, String p2, String p3, String p4, int p5, String p6, String p7, String p8, String p9, int p10, Object p11)
    {
        if ((p10 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p10 & 2) != 0) {
            p2 = p0.bankConnectorCode;
        }
        if ((p10 & 4) != 0) {
            p3 = p0.firstNum;
        }
        if ((p10 & 8) != 0) {
            p4 = p0.lastNum;
        }
        if ((p10 & 16) != 0) {
            p5 = p0.bankType;
        }
        if ((p10 & 32) != 0) {
            p6 = p0.name;
        }
        if ((p10 & 64) != 0) {
            p7 = p0.description;
        }
        if ((p10 & 128) != 0) {
            p8 = p0.background;
        }
        if ((p10 & 256) != 0) {
            p9 = p0.logo;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.bankConnectorCode;
    }

    public final String component3()
    {
        return this.firstNum;
    }

    public final String component4()
    {
        return this.lastNum;
    }

    public final int component5()
    {
        return this.bankType;
    }

    public final String component6()
    {
        return this.name;
    }

    public final String component7()
    {
        return this.description;
    }

    public final String component8()
    {
        return this.background;
    }

    public final String component9()
    {
        return this.logo;
    }

    public final vn.com.vng.zalopay.feature.argument.BankDetailArgs copy(String p11, String p12, String p13, String p14, int p15, String p16, String p17, String p18, String p19)
    {
        return new vn.com.vng.zalopay.feature.argument.BankDetailArgs(p11, p12, p13, p14, p15, p16, p17, p18, p19);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.feature.argument.BankDetailArgs)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankConnectorCode, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).bankConnectorCode)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.firstNum, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).firstNum)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.lastNum, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).lastNum)) {
                                if (this.bankType == ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).bankType) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).name)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.description, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).description)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.background, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).background)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.logo, ((vn.com.vng.zalopay.feature.argument.BankDetailArgs) p5).logo)) {
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
            return 1;
        }
    }

    public final String getBackground()
    {
        return this.background;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getBankConnectorCode()
    {
        return this.bankConnectorCode;
    }

    public final int getBankType()
    {
        return this.bankType;
    }

    public final String getDescription()
    {
        return this.description;
    }

    public final String getFirstNum()
    {
        return this.firstNum;
    }

    public final String getLastNum()
    {
        return this.lastNum;
    }

    public final String getLogo()
    {
        return this.logo;
    }

    public final String getName()
    {
        return this.name;
    }

    public int hashCode()
    {
        return (this.logo.hashCode() + e5.i.c(e5.i.c(e5.i.c(Kb.e.v(this.bankType, e5.i.c(e5.i.c(e5.i.c((this.bankCode.hashCode() * 31), 31, this.bankConnectorCode), 31, this.firstNum), 31, this.lastNum), 31), 31, this.name), 31, this.description), 31, this.background));
    }

    public String toString()
    {
        String v2_1 = this.firstNum;
        String v3 = this.lastNum;
        int v4 = this.bankType;
        String v5 = this.name;
        String v6 = this.description;
        String v7 = this.background;
        String v8 = this.logo;
        String v0_1 = androidx.camera.camera2.internal.G.i("BankDetailArgs(bankCode=", this.bankCode, ", bankConnectorCode=", this.bankConnectorCode, ", firstNum=");
        q9.H.p(v0_1, v2_1, ", lastNum=", v3, ", bankType=");
        e5.i.s(v4, ", name=", v5, ", description=", v0_1);
        q9.H.p(v0_1, v6, ", background=", v7, ", logo=");
        return A1.a.l(v0_1, v8, ")");
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.bankConnectorCode);
        p1.writeString(this.firstNum);
        p1.writeString(this.lastNum);
        p1.writeInt(this.bankType);
        p1.writeString(this.name);
        p1.writeString(this.description);
        p1.writeString(this.background);
        p1.writeString(this.logo);
        return;
    }
}
