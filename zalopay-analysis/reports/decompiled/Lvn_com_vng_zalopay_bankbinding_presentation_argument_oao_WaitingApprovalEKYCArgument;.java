package vn.com.vng.zalopay.bankbinding.presentation.argument.oao;
public final class WaitingApprovalEKYCArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String bankName;
    private final String ctaDeepLink;
    private final String ctaLabel;
    private final String message;
    private final String mode;
    private final String title;

    static WaitingApprovalEKYCArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument.CREATOR = new no.e(18);
        return;
    }

    public WaitingApprovalEKYCArgument(String p1, String p2, String p3, String p4, String p5, String p6, String p7)
    {
        this.bankCode = p1;
        this.bankName = p2;
        this.title = p3;
        this.message = p4;
        this.ctaLabel = p5;
        this.mode = p6;
        this.ctaDeepLink = p7;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument p0, String p1, String p2, String p3, String p4, String p5, String p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.bankName;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.title;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.message;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.ctaLabel;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.mode;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.ctaDeepLink;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.bankName;
    }

    public final String component3()
    {
        return this.title;
    }

    public final String component4()
    {
        return this.message;
    }

    public final String component5()
    {
        return this.ctaLabel;
    }

    public final String component6()
    {
        return this.mode;
    }

    public final String component7()
    {
        return this.ctaDeepLink;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument copy(String p9, String p10, String p11, String p12, String p13, String p14, String p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) p5).bankName)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) p5).title)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) p5).message)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.ctaLabel, ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) p5).ctaLabel)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.mode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) p5).mode)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.ctaDeepLink, ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) p5).ctaDeepLink)) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getBankName()
    {
        return this.bankName;
    }

    public final String getCtaDeepLink()
    {
        return this.ctaDeepLink;
    }

    public final String getCtaLabel()
    {
        return this.ctaLabel;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final String getMode()
    {
        return this.mode;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        return ((((((((((((this.bankCode.hashCode() * 31) + this.bankName.hashCode()) * 31) + this.title.hashCode()) * 31) + this.message.hashCode()) * 31) + this.ctaLabel.hashCode()) * 31) + this.mode.hashCode()) * 31) + this.ctaDeepLink.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.bankName;
        String v2 = this.title;
        String v3 = this.message;
        String v4 = this.ctaLabel;
        String v5 = this.mode;
        String v6 = this.ctaDeepLink;
        StringBuilder v7_1 = new StringBuilder("WaitingApprovalEKYCArgument(bankCode=");
        v7_1.append(v0_0);
        v7_1.append(", bankName=");
        v7_1.append(v1);
        v7_1.append(", title=");
        v7_1.append(v2);
        v7_1.append(", message=");
        v7_1.append(v3);
        v7_1.append(", ctaLabel=");
        v7_1.append(v4);
        v7_1.append(", mode=");
        v7_1.append(v5);
        v7_1.append(", ctaDeepLink=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.bankName);
        p1.writeString(this.title);
        p1.writeString(this.message);
        p1.writeString(this.ctaLabel);
        p1.writeString(this.mode);
        p1.writeString(this.ctaDeepLink);
        return;
    }
}
