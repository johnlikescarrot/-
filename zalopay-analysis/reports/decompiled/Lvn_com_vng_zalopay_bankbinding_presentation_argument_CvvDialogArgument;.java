package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class CvvDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.data.bank.ui.BankStyle bankStyle;
    private final String message;
    private final int parentId;
    private final String requestResult;

    static CvvDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument.CREATOR = new no.e(12);
        return;
    }

    public CvvDialogArgument(vn.com.vng.zalopay.data.bank.ui.BankStyle p1, int p2, String p3, String p4)
    {
        this.bankStyle = p1;
        this.parentId = p2;
        this.message = p3;
        this.requestResult = p4;
        return;
    }

    public synthetic CvvDialogArgument(vn.com.vng.zalopay.data.bank.ui.BankStyle p1, int p2, String p3, String p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 8) != 0) {
            p4 = "";
        }
        this(p1, p2, p3, p4);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument p0, vn.com.vng.zalopay.data.bank.ui.BankStyle p1, int p2, String p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.bankStyle;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.parentId;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.message;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.requestResult;
        }
        return p0.copy(p1, p2, p3, p4);
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
        return this.message;
    }

    public final String component4()
    {
        return this.requestResult;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument copy(vn.com.vng.zalopay.data.bank.ui.BankStyle p2, int p3, String p4, String p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankStyle, ((vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument) p5).bankStyle)) {
                    if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument) p5).parentId) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument) p5).message)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument) p5).requestResult)) {
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

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle getBankStyle()
    {
        return this.bankStyle;
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

    public int hashCode()
    {
        return ((((((this.bankStyle.hashCode() * 31) + Integer.hashCode(this.parentId)) * 31) + this.message.hashCode()) * 31) + this.requestResult.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankStyle;
        int v1 = this.parentId;
        String v2 = this.message;
        String v3 = this.requestResult;
        StringBuilder v4_1 = new StringBuilder("CvvDialogArgument(bankStyle=");
        v4_1.append(v0_0);
        v4_1.append(", parentId=");
        v4_1.append(v1);
        v4_1.append(", message=");
        v4_1.append(v2);
        v4_1.append(", requestResult=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeParcelable(this.bankStyle, p3);
        p2.writeInt(this.parentId);
        p2.writeString(this.message);
        p2.writeString(this.requestResult);
        return;
    }
}
