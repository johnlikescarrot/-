package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankCardGuideDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.data.bank.ui.BankStyle bankStyle;
    private final String boldMessage;
    private final String message;
    private final int parentId;
    private final String requestResult;
    private final String retryButtonText;
    private final String title;

    static BankCardGuideDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument.CREATOR = new Zn.a(16);
        return;
    }

    public BankCardGuideDialogArgument(vn.com.vng.zalopay.data.bank.ui.BankStyle p1, int p2, String p3, String p4, String p5, String p6, String p7)
    {
        this.bankStyle = p1;
        this.parentId = p2;
        this.requestResult = p3;
        this.boldMessage = p4;
        this.message = p5;
        this.retryButtonText = p6;
        this.title = p7;
        return;
    }

    public synthetic BankCardGuideDialogArgument(vn.com.vng.zalopay.data.bank.ui.BankStyle p9, int p10, String p11, String p12, String p13, String p14, String p15, int p16, kotlin.jvm.internal.DefaultConstructorMarker p17)
    {
        if ((p16 & 4) != 0) {
            p11 = "";
        }
        String v4;
        if ((p16 & 8) == 0) {
            v4 = p12;
        } else {
            v4 = 0;
        }
        String v5;
        if ((p16 & 16) == 0) {
            v5 = p13;
        } else {
            v5 = 0;
        }
        String v7;
        String v6;
        vn.com.vng.zalopay.data.bank.ui.BankStyle v1;
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument v0_1;
        int v2;
        if ((p16 & 32) == 0) {
            v6 = p14;
            v0_1 = this;
            v1 = p9;
            v2 = p10;
            v7 = p15;
        } else {
            v6 = 0;
            v1 = p9;
            v2 = p10;
            v7 = p15;
            v0_1 = this;
        }
        v0_1(v1, v2, p11, v4, v5, v6, v7);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument p0, vn.com.vng.zalopay.data.bank.ui.BankStyle p1, int p2, String p3, String p4, String p5, String p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.bankStyle;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.parentId;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.requestResult;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.boldMessage;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.message;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.retryButtonText;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.title;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle component1()
    {
        return this.bankStyle;
    }

    public final int component2()
    {
        return this.parentId;
    }

    public final String component3()
    {
        return this.requestResult;
    }

    public final String component4()
    {
        return this.boldMessage;
    }

    public final String component5()
    {
        return this.message;
    }

    public final String component6()
    {
        return this.retryButtonText;
    }

    public final String component7()
    {
        return this.title;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument copy(vn.com.vng.zalopay.data.bank.ui.BankStyle p9, int p10, String p11, String p12, String p13, String p14, String p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankStyle, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) p5).bankStyle)) {
                    if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) p5).parentId) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) p5).requestResult)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.boldMessage, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) p5).boldMessage)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) p5).message)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.retryButtonText, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) p5).retryButtonText)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) p5).title)) {
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

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle getBankStyle()
    {
        return this.bankStyle;
    }

    public final String getBoldMessage()
    {
        return this.boldMessage;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final int getParentId()
    {
        return this.parentId;
    }

    public final String getRequestResult()
    {
        return this.requestResult;
    }

    public final String getRetryButtonText()
    {
        return this.retryButtonText;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        int v1_2;
        int v0_2 = (((((this.bankStyle.hashCode() * 31) + Integer.hashCode(this.parentId)) * 31) + this.requestResult.hashCode()) * 31);
        int v1_1 = this.boldMessage;
        int v2 = 0;
        if (v1_1 != 0) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        int v1_4;
        int v0_5 = ((v0_2 + v1_2) * 31);
        int v1_3 = this.message;
        if (v1_3 != 0) {
            v1_4 = v1_3.hashCode();
        } else {
            v1_4 = 0;
        }
        int v0_7 = ((v0_5 + v1_4) * 31);
        int v1_5 = this.retryButtonText;
        if (v1_5 != 0) {
            v2 = v1_5.hashCode();
        }
        return (((v0_7 + v2) * 31) + this.title.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankStyle;
        int v1 = this.parentId;
        String v2 = this.requestResult;
        String v3 = this.boldMessage;
        String v4 = this.message;
        String v5 = this.retryButtonText;
        String v6 = this.title;
        StringBuilder v7_1 = new StringBuilder("BankCardGuideDialogArgument(bankStyle=");
        v7_1.append(v0_0);
        v7_1.append(", parentId=");
        v7_1.append(v1);
        v7_1.append(", requestResult=");
        v7_1.append(v2);
        v7_1.append(", boldMessage=");
        v7_1.append(v3);
        v7_1.append(", message=");
        v7_1.append(v4);
        v7_1.append(", retryButtonText=");
        v7_1.append(v5);
        v7_1.append(", title=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeParcelable(this.bankStyle, p3);
        p2.writeInt(this.parentId);
        p2.writeString(this.requestResult);
        p2.writeString(this.boldMessage);
        p2.writeString(this.message);
        p2.writeString(this.retryButtonText);
        p2.writeString(this.title);
        return;
    }
}
