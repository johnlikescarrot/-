package vn.com.vng.zalopay.feature.argument;
public final class FundingManagerArgs implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.feature.argument.BankDetailArgs bankDetailArgs;
    private final vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs unLinkStatusArgs;

    static FundingManagerArgs()
    {
        vn.com.vng.zalopay.feature.argument.FundingManagerArgs.CREATOR = new iG.g(21);
        return;
    }

    public FundingManagerArgs()
    {
        this(0, 0, 3, 0);
        return;
    }

    public FundingManagerArgs(vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs p1, vn.com.vng.zalopay.feature.argument.BankDetailArgs p2)
    {
        this.unLinkStatusArgs = p1;
        this.bankDetailArgs = p2;
        return;
    }

    public synthetic FundingManagerArgs(vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs p2, vn.com.vng.zalopay.feature.argument.BankDetailArgs p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 1) != 0) {
            p2 = 0;
        }
        if ((p4 & 2) != 0) {
            p3 = 0;
        }
        this(p2, p3);
        return;
    }

    public static synthetic vn.com.vng.zalopay.feature.argument.FundingManagerArgs copy$default(vn.com.vng.zalopay.feature.argument.FundingManagerArgs p0, vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs p1, vn.com.vng.zalopay.feature.argument.BankDetailArgs p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.unLinkStatusArgs;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.bankDetailArgs;
        }
        return p0.copy(p1, p2);
    }

    public final vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs component1()
    {
        return this.unLinkStatusArgs;
    }

    public final vn.com.vng.zalopay.feature.argument.BankDetailArgs component2()
    {
        return this.bankDetailArgs;
    }

    public final vn.com.vng.zalopay.feature.argument.FundingManagerArgs copy(vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs p2, vn.com.vng.zalopay.feature.argument.BankDetailArgs p3)
    {
        return new vn.com.vng.zalopay.feature.argument.FundingManagerArgs(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.feature.argument.FundingManagerArgs)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.unLinkStatusArgs, ((vn.com.vng.zalopay.feature.argument.FundingManagerArgs) p5).unLinkStatusArgs)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankDetailArgs, ((vn.com.vng.zalopay.feature.argument.FundingManagerArgs) p5).bankDetailArgs)) {
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
            return 1;
        }
    }

    public final vn.com.vng.zalopay.feature.argument.BankDetailArgs getBankDetailArgs()
    {
        return this.bankDetailArgs;
    }

    public final vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs getUnLinkStatusArgs()
    {
        return this.unLinkStatusArgs;
    }

    public int hashCode()
    {
        int v0_2;
        int v0_0 = this.unLinkStatusArgs;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        int v0_3 = (v0_2 * 31);
        vn.com.vng.zalopay.feature.argument.BankDetailArgs v2 = this.bankDetailArgs;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_3 + v1);
    }

    public String toString()
    {
        String v0_0 = this.unLinkStatusArgs;
        vn.com.vng.zalopay.feature.argument.BankDetailArgs v1 = this.bankDetailArgs;
        StringBuilder v2_1 = new StringBuilder(wmoi.igob.ggcn(1328));
        v2_1.append(v0_0);
        v2_1.append(wmoi.igob.ggcn(1329));
        v2_1.append(v1);
        v2_1.append(wmoi.igob.ggcn(1330));
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        vn.com.vng.zalopay.feature.argument.BankDetailArgs v0_0 = this.unLinkStatusArgs;
        if (v0_0 != null) {
            p4.writeInt(1);
            v0_0.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        vn.com.vng.zalopay.feature.argument.BankDetailArgs v0_1 = this.bankDetailArgs;
        if (v0_1 != null) {
            p4.writeInt(1);
            v0_1.writeToParcel(p4, p5);
            return;
        } else {
            p4.writeInt(0);
            return;
        }
    }
}
