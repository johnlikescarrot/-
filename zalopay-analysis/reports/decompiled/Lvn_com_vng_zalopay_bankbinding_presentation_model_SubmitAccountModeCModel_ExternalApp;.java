package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class SubmitAccountModeCModel$ExternalApp extends uo.s0 implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bankType;
    private final String url;
    private final String zpTransId;

    static SubmitAccountModeCModel$ExternalApp()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp.CREATOR = new uo.L(11);
        return;
    }

    public SubmitAccountModeCModel$ExternalApp(String p1, String p2, String p3, int p4)
    {
        this.bankCode = p1;
        this.zpTransId = p2;
        this.url = p3;
        this.bankType = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp p0, String p1, String p2, String p3, int p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.zpTransId;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.url;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.bankType;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.zpTransId;
    }

    public final String component3()
    {
        return this.url;
    }

    public final int component4()
    {
        return this.bankType;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp copy(String p2, String p3, String p4, int p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp) p5).zpTransId)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp) p5).url)) {
                            if (this.bankType == ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$ExternalApp) p5).bankType) {
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

    public final int getBankType()
    {
        return this.bankType;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((this.bankCode.hashCode() * 31) + this.zpTransId.hashCode()) * 31) + this.url.hashCode()) * 31) + Integer.hashCode(this.bankType));
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.zpTransId;
        String v2 = this.url;
        int v3 = this.bankType;
        StringBuilder v4_1 = new StringBuilder("ExternalApp(bankCode=");
        v4_1.append(v0_0);
        v4_1.append(", zpTransId=");
        v4_1.append(v1);
        v4_1.append(", url=");
        v4_1.append(v2);
        v4_1.append(", bankType=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.zpTransId);
        p1.writeString(this.url);
        p1.writeInt(this.bankType);
        return;
    }
}
