package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankCenterDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String cancelButton;
    private final String confirmButton;
    private final int drawableIcon;
    private final String message;
    private final int parentId;
    private final String requestResult;
    private final String title;

    static BankCenterDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument.CREATOR = new Zn.a(17);
        return;
    }

    public BankCenterDialogArgument(String p1, String p2, int p3, String p4, String p5, String p6, int p7)
    {
        this.message = p1;
        this.confirmButton = p2;
        this.parentId = p3;
        this.requestResult = p4;
        this.title = p5;
        this.cancelButton = p6;
        this.drawableIcon = p7;
        return;
    }

    public synthetic BankCenterDialogArgument(String p2, String p3, int p4, String p5, String p6, String p7, int p8, int p9, kotlin.jvm.internal.DefaultConstructorMarker p10)
    {
        if ((p9 & 16) != 0) {
            p6 = 0;
        }
        if ((p9 & 32) != 0) {
            p7 = 0;
        }
        if ((p9 & 64) != 0) {
            p8 = 0;
        }
        this(p2, p3, p4, p5, p6, p7, p8);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument p0, String p1, String p2, int p3, String p4, String p5, String p6, int p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.confirmButton;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.parentId;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.requestResult;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.title;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.cancelButton;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.drawableIcon;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.message;
    }

    public final String component2()
    {
        return this.confirmButton;
    }

    public final int component3()
    {
        return this.parentId;
    }

    public final String component4()
    {
        return this.requestResult;
    }

    public final String component5()
    {
        return this.title;
    }

    public final String component6()
    {
        return this.cancelButton;
    }

    public final int component7()
    {
        return this.drawableIcon;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument copy(String p9, String p10, int p11, String p12, String p13, String p14, int p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) p5).message)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButton, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) p5).confirmButton)) {
                        if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) p5).parentId) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) p5).requestResult)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) p5).title)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cancelButton, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) p5).cancelButton)) {
                                        if (this.drawableIcon == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) p5).drawableIcon) {
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

    public final int getDrawableIcon()
    {
        return this.drawableIcon;
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
        int v1_4;
        int v0_4 = (((((((this.message.hashCode() * 31) + this.confirmButton.hashCode()) * 31) + Integer.hashCode(this.parentId)) * 31) + this.requestResult.hashCode()) * 31);
        int v1_3 = this.title;
        int v2 = 0;
        if (v1_3 != 0) {
            v1_4 = v1_3.hashCode();
        } else {
            v1_4 = 0;
        }
        int v0_7 = ((v0_4 + v1_4) * 31);
        int v1_5 = this.cancelButton;
        if (v1_5 != 0) {
            v2 = v1_5.hashCode();
        }
        return (((v0_7 + v2) * 31) + Integer.hashCode(this.drawableIcon));
    }

    public String toString()
    {
        String v0_0 = this.message;
        String v1 = this.confirmButton;
        int v2 = this.parentId;
        String v3 = this.requestResult;
        String v4 = this.title;
        String v5 = this.cancelButton;
        int v6 = this.drawableIcon;
        StringBuilder v7_1 = new StringBuilder("BankCenterDialogArgument(message=");
        v7_1.append(v0_0);
        v7_1.append(", confirmButton=");
        v7_1.append(v1);
        v7_1.append(", parentId=");
        v7_1.append(v2);
        v7_1.append(", requestResult=");
        v7_1.append(v3);
        v7_1.append(", title=");
        v7_1.append(v4);
        v7_1.append(", cancelButton=");
        v7_1.append(v5);
        v7_1.append(", drawableIcon=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeString(this.confirmButton);
        p1.writeInt(this.parentId);
        p1.writeString(this.requestResult);
        p1.writeString(this.title);
        p1.writeString(this.cancelButton);
        p1.writeInt(this.drawableIcon);
        return;
    }
}
