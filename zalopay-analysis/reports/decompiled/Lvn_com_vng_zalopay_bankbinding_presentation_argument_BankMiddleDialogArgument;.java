package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankMiddleDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String cancelButton;
    private final String confirmButton;
    private final int iconType;
    private final String message;
    private final int parentId;
    private final String requestResult;
    private final String title;

    static BankMiddleDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument.CREATOR = new Zn.a(27);
        return;
    }

    public BankMiddleDialogArgument(int p1, String p2, String p3, int p4, String p5, String p6, String p7)
    {
        this.iconType = p1;
        this.title = p2;
        this.message = p3;
        this.parentId = p4;
        this.requestResult = p5;
        this.confirmButton = p6;
        this.cancelButton = p7;
        return;
    }

    public synthetic BankMiddleDialogArgument(int p2, String p3, String p4, int p5, String p6, String p7, String p8, int p9, kotlin.jvm.internal.DefaultConstructorMarker p10)
    {
        if ((p9 & 1) != 0) {
            p2 = 3;
        }
        if ((p9 & 2) != 0) {
            p3 = 0;
        }
        if ((p9 & 32) != 0) {
            p7 = 0;
        }
        int v7_1;
        int v4_1;
        String v8_1;
        int v9_2;
        int v6_1;
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument v3_1;
        String v10_1;
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument v5_1;
        if ((p9 & 64) == 0) {
            v10_1 = p8;
            v9_2 = p7;
            v7_1 = p5;
            v8_1 = p6;
            v5_1 = p3;
            v6_1 = p4;
            v3_1 = this;
            v4_1 = p2;
        } else {
            v10_1 = 0;
            v8_1 = p6;
            v9_2 = p7;
            v6_1 = p4;
            v7_1 = p5;
            v4_1 = p2;
            v5_1 = p3;
            v3_1 = this;
        }
        v3_1(v4_1, v5_1, v6_1, v7_1, v8_1, v9_2, v10_1);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument p0, int p1, String p2, String p3, int p4, String p5, String p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.iconType;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.title;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.message;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.parentId;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.requestResult;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.confirmButton;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.cancelButton;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final int component1()
    {
        return this.iconType;
    }

    public final String component2()
    {
        return this.title;
    }

    public final String component3()
    {
        return this.message;
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
        return this.confirmButton;
    }

    public final String component7()
    {
        return this.cancelButton;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument copy(int p9, String p10, String p11, int p12, String p13, String p14, String p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument)) {
                if (this.iconType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) p5).iconType) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) p5).title)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) p5).message)) {
                            if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) p5).parentId) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) p5).requestResult)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButton, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) p5).confirmButton)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.cancelButton, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) p5).cancelButton)) {
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

    public final String getCancelButton()
    {
        return this.cancelButton;
    }

    public final String getConfirmButton()
    {
        return this.confirmButton;
    }

    public final int getIconType()
    {
        return this.iconType;
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
        String v1_10;
        int v0_10 = (Integer.hashCode(this.iconType) * 31);
        String v1_9 = this.title;
        int v2 = 0;
        if (v1_9 != null) {
            v1_10 = v1_9.hashCode();
        } else {
            v1_10 = 0;
        }
        String v1_7;
        int v0_9 = ((((((((v0_10 + v1_10) * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.parentId)) * 31) + this.requestResult.hashCode()) * 31);
        String v1_6 = this.confirmButton;
        if (v1_6 != null) {
            v1_7 = v1_6.hashCode();
        } else {
            v1_7 = 0;
        }
        int v0_12 = ((v0_9 + v1_7) * 31);
        String v1_8 = this.cancelButton;
        if (v1_8 != null) {
            v2 = v1_8.hashCode();
        }
        return (v0_12 + v2);
    }

    public String toString()
    {
        String v0_0 = this.iconType;
        String v1 = this.title;
        String v2 = this.message;
        int v3 = this.parentId;
        String v4 = this.requestResult;
        String v5 = this.confirmButton;
        String v6 = this.cancelButton;
        StringBuilder v7_1 = new StringBuilder("BankMiddleDialogArgument(iconType=");
        v7_1.append(v0_0);
        v7_1.append(", title=");
        v7_1.append(v1);
        v7_1.append(", message=");
        v7_1.append(v2);
        v7_1.append(", parentId=");
        v7_1.append(v3);
        v7_1.append(", requestResult=");
        v7_1.append(v4);
        v7_1.append(", confirmButton=");
        v7_1.append(v5);
        v7_1.append(", cancelButton=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.iconType);
        p1.writeString(this.title);
        p1.writeString(this.message);
        p1.writeInt(this.parentId);
        p1.writeString(this.requestResult);
        p1.writeString(this.confirmButton);
        p1.writeString(this.cancelButton);
        return;
    }
}
