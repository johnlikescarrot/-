package vn.com.vng.zalopay.bankbinding.presentation.argument.fm;
public final class LinkedBankDetailArgument implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String background;
    private final vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData bankDetail;
    private final String description;
    private final String logo;
    private final String name;

    static LinkedBankDetailArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument.CREATOR = new no.e(16);
        return;
    }

    public LinkedBankDetailArgument(vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData p1, String p2, String p3, String p4, String p5)
    {
        this.bankDetail = p1;
        this.name = p2;
        this.description = p3;
        this.background = p4;
        this.logo = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument p0, vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData p1, String p2, String p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.bankDetail;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.name;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.description;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.background;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.logo;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData component1()
    {
        return this.bankDetail;
    }

    public final String component2()
    {
        return this.name;
    }

    public final String component3()
    {
        return this.description;
    }

    public final String component4()
    {
        return this.background;
    }

    public final String component5()
    {
        return this.logo;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument copy(vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData p7, String p8, String p9, String p10, String p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankDetail, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument) p5).bankDetail)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument) p5).name)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.description, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument) p5).description)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.background, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument) p5).background)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.logo, ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument) p5).logo)) {
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
            return 1;
        }
    }

    public final String getBackground()
    {
        return this.background;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData getBankDetail()
    {
        return this.bankDetail;
    }

    public final String getDescription()
    {
        return this.description;
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
        return ((((((((this.bankDetail.hashCode() * 31) + this.name.hashCode()) * 31) + this.description.hashCode()) * 31) + this.background.hashCode()) * 31) + this.logo.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankDetail;
        String v1 = this.name;
        String v2 = this.description;
        String v3 = this.background;
        String v4 = this.logo;
        StringBuilder v5_1 = new StringBuilder("LinkedBankDetailArgument(bankDetail=");
        v5_1.append(v0_0);
        v5_1.append(", name=");
        v5_1.append(v1);
        v5_1.append(", description=");
        v5_1.append(v2);
        v5_1.append(", background=");
        v5_1.append(v3);
        v5_1.append(", logo=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        this.bankDetail.writeToParcel(p2, p3);
        p2.writeString(this.name);
        p2.writeString(this.description);
        p2.writeString(this.background);
        p2.writeString(this.logo);
        return;
    }
}
