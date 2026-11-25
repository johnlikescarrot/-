package vn.com.vng.zalopay.feature.argument;
public final class BankLinkStatusArgs implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int linkingType;
    private final int step;
    private final String zpTransId;

    static BankLinkStatusArgs()
    {
        vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs.CREATOR = new iG.g(19);
        return;
    }

    public BankLinkStatusArgs(String p1, int p2, int p3, String p4)
    {
        this.zpTransId = p1;
        this.linkingType = p2;
        this.step = p3;
        this.bankCode = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs copy$default(vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs p0, String p1, int p2, int p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.zpTransId;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.linkingType;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.step;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.zpTransId;
    }

    public final int component2()
    {
        return this.linkingType;
    }

    public final int component3()
    {
        return this.step;
    }

    public final String component4()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs copy(String p2, int p3, int p4, String p5)
    {
        return new vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs) p5).zpTransId)) {
                    if (this.linkingType == ((vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs) p5).linkingType) {
                        if (this.step == ((vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs) p5).step) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs) p5).bankCode)) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final int getStep()
    {
        return this.step;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return (this.bankCode.hashCode() + Kb.e.v(this.step, Kb.e.v(this.linkingType, (this.zpTransId.hashCode() * 31), 31), 31));
    }

    public String toString()
    {
        return com.alibaba.ariver.commonability.device.jsapi.clipboard.a.h(this.step, ", bankCode=", this.bankCode, ")", androidx.compose.foundation.layout.G0.p(this.linkingType, "BankLinkStatusArgs(zpTransId=", this.zpTransId, ", linkingType=", ", step="));
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.zpTransId);
        p1.writeInt(this.linkingType);
        p1.writeInt(this.step);
        p1.writeString(this.bankCode);
        return;
    }
}
