package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankConfirmBottomDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String confirmButtonText;
    private final int icon;
    private final String message;
    private final int parentId;
    private final String requestResult;
    private final String title;

    static BankConfirmBottomDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument.CREATOR = new Zn.a(18);
        return;
    }

    public BankConfirmBottomDialogArgument(String p1, String p2, int p3, int p4, String p5, String p6)
    {
        this.title = p1;
        this.message = p2;
        this.icon = p3;
        this.parentId = p4;
        this.requestResult = p5;
        this.confirmButtonText = p6;
        return;
    }

    public synthetic BankConfirmBottomDialogArgument(String p2, String p3, int p4, int p5, String p6, String p7, int p8, kotlin.jvm.internal.DefaultConstructorMarker p9)
    {
        if ((p8 & 1) != 0) {
            p2 = "";
        }
        String v8_2;
        String v7_1;
        if ((p8 & 16) == 0) {
            v8_2 = p7;
            v7_1 = p6;
        } else {
            v8_2 = p7;
            v7_1 = "";
        }
        this(p2, p3, p4, p5, v7_1, v8_2);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument p0, String p1, String p2, int p3, int p4, String p5, String p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.message;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.icon;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.parentId;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.requestResult;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.confirmButtonText;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final String component1()
    {
        return this.title;
    }

    public final String component2()
    {
        return this.message;
    }

    public final int component3()
    {
        return this.icon;
    }

    public final int component4()
    {
        return this.parentId;
    }

    public final String component5()
    {
        return this.requestResult;
    }

    public final String component6()
    {
        return this.confirmButtonText;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument copy(String p8, String p9, int p10, int p11, String p12, String p13)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument) p5).message)) {
                        if (this.icon == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument) p5).icon) {
                            if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument) p5).parentId) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument) p5).requestResult)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButtonText, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument) p5).confirmButtonText)) {
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
            return 1;
        }
    }

    public final String getConfirmButtonText()
    {
        return this.confirmButtonText;
    }

    public final int getIcon()
    {
        return this.icon;
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

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        return ((((((((((this.title.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.icon)) * 31) + Integer.hashCode(this.parentId)) * 31) + this.requestResult.hashCode()) * 31) + this.confirmButtonText.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.message;
        int v2 = this.icon;
        int v3 = this.parentId;
        String v4 = this.requestResult;
        String v5 = this.confirmButtonText;
        StringBuilder v6_1 = new StringBuilder("BankConfirmBottomDialogArgument(title=");
        v6_1.append(v0_0);
        v6_1.append(", message=");
        v6_1.append(v1);
        v6_1.append(", icon=");
        v6_1.append(v2);
        v6_1.append(", parentId=");
        v6_1.append(v3);
        v6_1.append(", requestResult=");
        v6_1.append(v4);
        v6_1.append(", confirmButtonText=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.title);
        p1.writeString(this.message);
        p1.writeInt(this.icon);
        p1.writeInt(this.parentId);
        p1.writeString(this.requestResult);
        p1.writeString(this.confirmButtonText);
        return;
    }
}
