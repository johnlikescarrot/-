package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankMediaWebViewActivityArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String displayMode;
    private final String entryPoint;
    private final boolean ignoreOpenLinkOnClick;
    private final String url;

    static BankMediaWebViewActivityArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument.CREATOR = new Zn.a(26);
        return;
    }

    public BankMediaWebViewActivityArgument(String p1, String p2, boolean p3, String p4, String p5)
    {
        this.url = p1;
        this.displayMode = p2;
        this.ignoreOpenLinkOnClick = p3;
        this.entryPoint = p4;
        this.bankCode = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument p0, String p1, String p2, boolean p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.url;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.displayMode;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.ignoreOpenLinkOnClick;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.entryPoint;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.url;
    }

    public final String component2()
    {
        return this.displayMode;
    }

    public final boolean component3()
    {
        return this.ignoreOpenLinkOnClick;
    }

    public final String component4()
    {
        return this.entryPoint;
    }

    public final String component5()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument copy(String p7, String p8, boolean p9, String p10, String p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument) p5).url)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.displayMode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument) p5).displayMode)) {
                        if (this.ignoreOpenLinkOnClick == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument) p5).ignoreOpenLinkOnClick) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.entryPoint, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument) p5).entryPoint)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMediaWebViewActivityArgument) p5).bankCode)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getDisplayMode()
    {
        return this.displayMode;
    }

    public final String getEntryPoint()
    {
        return this.entryPoint;
    }

    public final boolean getIgnoreOpenLinkOnClick()
    {
        return this.ignoreOpenLinkOnClick;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return ((((((((this.url.hashCode() * 31) + this.displayMode.hashCode()) * 31) + Boolean.hashCode(this.ignoreOpenLinkOnClick)) * 31) + this.entryPoint.hashCode()) * 31) + this.bankCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.url;
        String v1 = this.displayMode;
        boolean v2 = this.ignoreOpenLinkOnClick;
        String v3 = this.entryPoint;
        String v4 = this.bankCode;
        StringBuilder v5_1 = new StringBuilder("BankMediaWebViewActivityArgument(url=");
        v5_1.append(v0_0);
        v5_1.append(", displayMode=");
        v5_1.append(v1);
        v5_1.append(", ignoreOpenLinkOnClick=");
        v5_1.append(v2);
        v5_1.append(", entryPoint=");
        v5_1.append(v3);
        v5_1.append(", bankCode=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.url);
        p1.writeString(this.displayMode);
        p1.writeInt(this.ignoreOpenLinkOnClick);
        p1.writeString(this.entryPoint);
        p1.writeString(this.bankCode);
        return;
    }
}
