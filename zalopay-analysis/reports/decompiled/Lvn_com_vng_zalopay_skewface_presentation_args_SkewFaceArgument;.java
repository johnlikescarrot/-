package vn.com.vng.zalopay.skewface.presentation.args;
public final class SkewFaceArgument implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String appSessionId;
    private final String bankCode;
    private final String redirectUrl;
    private final String source;
    private final String transactionId;

    static SkewFaceArgument()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument, 17117, 17117);
        vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument.CREATOR = new y9.z(24);
        return;
    }

    public SkewFaceArgument(String p1, String p2, String p3, String p4, String p5)
    {
        this.source = p1;
        this.appSessionId = p2;
        this.redirectUrl = p3;
        this.transactionId = p4;
        this.bankCode = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument copy$default(vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument p0, String p1, String p2, String p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.source;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.appSessionId;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.redirectUrl;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.transactionId;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.source;
    }

    public final String component2()
    {
        return this.appSessionId;
    }

    public final String component3()
    {
        return this.redirectUrl;
    }

    public final String component4()
    {
        return this.transactionId;
    }

    public final String component5()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument copy(String p7, String p8, String p9, String p10, String p11)
    {
        return new vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.source, ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) p5).source)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.appSessionId, ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) p5).appSessionId)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.redirectUrl, ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) p5).redirectUrl)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.transactionId, ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) p5).transactionId)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) p5).bankCode)) {
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

    public final String getAppSessionId()
    {
        return this.appSessionId;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getRedirectUrl()
    {
        return this.redirectUrl;
    }

    public final String getSource()
    {
        return this.source;
    }

    public final String getTransactionId()
    {
        return this.transactionId;
    }

    public int hashCode()
    {
        return (this.bankCode.hashCode() + e5.i.c(e5.i.c(e5.i.c((this.source.hashCode() * 31), 31, this.appSessionId), 31, this.redirectUrl), 31, this.transactionId));
    }

    public String toString()
    {
        String v2 = this.redirectUrl;
        String v3 = this.transactionId;
        String v4 = this.bankCode;
        String v0_2 = androidx.camera.camera2.internal.G.i("SkewFaceArgument(source=", this.source, ", appSessionId=", this.appSessionId, ", redirectUrl=");
        q9.H.p(v0_2, v2, ", transactionId=", v3, ", bankCode=");
        return A1.a.l(v0_2, v4, ")");
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.source);
        p1.writeString(this.appSessionId);
        p1.writeString(this.redirectUrl);
        p1.writeString(this.transactionId);
        p1.writeString(this.bankCode);
        return;
    }
}
