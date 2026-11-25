package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankGuideFindTimeDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.data.bank.ui.BankStyle bankStyle;
    private final int fieldId;
    private final String message;
    private final int parentId;
    private final String requestResult;
    private final String retryButtonText;
    private final String title;

    static BankGuideFindTimeDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument.CREATOR = new Zn.a(21);
        return;
    }

    public BankGuideFindTimeDialogArgument(vn.com.vng.zalopay.data.bank.ui.BankStyle p1, int p2, String p3, String p4, String p5, String p6, int p7)
    {
        this.bankStyle = p1;
        this.parentId = p2;
        this.requestResult = p3;
        this.message = p4;
        this.retryButtonText = p5;
        this.title = p6;
        this.fieldId = p7;
        return;
    }

    public synthetic BankGuideFindTimeDialogArgument(vn.com.vng.zalopay.data.bank.ui.BankStyle p9, int p10, String p11, String p12, String p13, String p14, int p15, int p16, kotlin.jvm.internal.DefaultConstructorMarker p17)
    {
        if ((p16 & 4) != 0) {
            p11 = "";
        }
        if ((p16 & 8) != 0) {
            p12 = 0;
        }
        this(p9, p10, p11, p12, p13, p14, p15);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument p0, vn.com.vng.zalopay.data.bank.ui.BankStyle p1, int p2, String p3, String p4, String p5, String p6, int p7, int p8, Object p9)
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
            p4 = p0.message;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.retryButtonText;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.title;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.fieldId;
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
        return this.message;
    }

    public final String component5()
    {
        return this.retryButtonText;
    }

    public final String component6()
    {
        return this.title;
    }

    public final int component7()
    {
        return this.fieldId;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument copy(vn.com.vng.zalopay.data.bank.ui.BankStyle p9, int p10, String p11, String p12, String p13, String p14, int p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankStyle, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) p5).bankStyle)) {
                    if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) p5).parentId) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) p5).requestResult)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) p5).message)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.retryButtonText, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) p5).retryButtonText)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) p5).title)) {
                                        if (this.fieldId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) p5).fieldId) {
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

    public final int getFieldId()
    {
        return this.fieldId;
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
        int v1_1 = this.message;
        if (v1_1 != 0) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        return (((((((v0_2 + v1_2) * 31) + this.retryButtonText.hashCode()) * 31) + this.title.hashCode()) * 31) + Integer.hashCode(this.fieldId));
    }

    public String toString()
    {
        String v0_0 = this.bankStyle;
        int v1 = this.parentId;
        String v2 = this.requestResult;
        String v3 = this.message;
        String v4 = this.retryButtonText;
        String v5 = this.title;
        int v6 = this.fieldId;
        StringBuilder v7_1 = new StringBuilder("BankGuideFindTimeDialogArgument(bankStyle=");
        v7_1.append(v0_0);
        v7_1.append(", parentId=");
        v7_1.append(v1);
        v7_1.append(", requestResult=");
        v7_1.append(v2);
        v7_1.append(", message=");
        v7_1.append(v3);
        v7_1.append(", retryButtonText=");
        v7_1.append(v4);
        v7_1.append(", title=");
        v7_1.append(v5);
        v7_1.append(", fieldId=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeParcelable(this.bankStyle, p3);
        p2.writeInt(this.parentId);
        p2.writeString(this.requestResult);
        p2.writeString(this.message);
        p2.writeString(this.retryButtonText);
        p2.writeString(this.title);
        p2.writeInt(this.fieldId);
        return;
    }
}
