package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankBottomMediaDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final java.util.List bankMediaEntities;
    private final int bcErrorCode;
    private final String cancelButton;
    private final String confirmButton;
    private final String entryPoint;
    private final int notifyType;
    private final int parentId;
    private final String requestCode;
    private final String title;

    static BankBottomMediaDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument.CREATOR = new Zn.a(15);
        return;
    }

    public BankBottomMediaDialogArgument(String p1, String p2, String p3, int p4, String p5, java.util.List p6, String p7, int p8, int p9, String p10)
    {
        this.title = p1;
        this.cancelButton = p2;
        this.confirmButton = p3;
        this.parentId = p4;
        this.requestCode = p5;
        this.bankMediaEntities = p6;
        this.bankCode = p7;
        this.bcErrorCode = p8;
        this.notifyType = p9;
        this.entryPoint = p10;
        return;
    }

    public synthetic BankBottomMediaDialogArgument(String p13, String p14, String p15, int p16, String p17, java.util.List p18, String p19, int p20, int p21, String p22, int p23, kotlin.jvm.internal.DefaultConstructorMarker p24)
    {
        String v6;
        if ((p23 & 16) == 0) {
            v6 = p17;
        } else {
            v6 = "";
        }
        this(p13, p14, p15, p16, v6, p18, p19, p20, p21, p22);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument p0, String p1, String p2, String p3, int p4, String p5, java.util.List p6, String p7, int p8, int p9, String p10, int p11, Object p12)
    {
        if ((p11 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p11 & 2) != 0) {
            p2 = p0.cancelButton;
        }
        if ((p11 & 4) != 0) {
            p3 = p0.confirmButton;
        }
        if ((p11 & 8) != 0) {
            p4 = p0.parentId;
        }
        if ((p11 & 16) != 0) {
            p5 = p0.requestCode;
        }
        if ((p11 & 32) != 0) {
            p6 = p0.bankMediaEntities;
        }
        if ((p11 & 64) != 0) {
            p7 = p0.bankCode;
        }
        if ((p11 & 128) != 0) {
            p8 = p0.bcErrorCode;
        }
        if ((p11 & 256) != 0) {
            p9 = p0.notifyType;
        }
        if ((p11 & 512) != 0) {
            p10 = p0.entryPoint;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
    }

    public final String component1()
    {
        return this.title;
    }

    public final String component10()
    {
        return this.entryPoint;
    }

    public final String component2()
    {
        return this.cancelButton;
    }

    public final String component3()
    {
        return this.confirmButton;
    }

    public final int component4()
    {
        return this.parentId;
    }

    public final String component5()
    {
        return this.requestCode;
    }

    public final java.util.List component6()
    {
        return this.bankMediaEntities;
    }

    public final String component7()
    {
        return this.bankCode;
    }

    public final int component8()
    {
        return this.bcErrorCode;
    }

    public final int component9()
    {
        return this.notifyType;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument copy(String p12, String p13, String p14, int p15, String p16, java.util.List p17, String p18, int p19, int p20, String p21)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cancelButton, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).cancelButton)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButton, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).confirmButton)) {
                            if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).parentId) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).requestCode)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankMediaEntities, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).bankMediaEntities)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).bankCode)) {
                                            if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).bcErrorCode) {
                                                if (this.notifyType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).notifyType) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.entryPoint, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) p5).entryPoint)) {
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

    public final java.util.List getBankMediaEntities()
    {
        return this.bankMediaEntities;
    }

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final String getCancelButton()
    {
        return this.cancelButton;
    }

    public final String getConfirmButton()
    {
        return this.confirmButton;
    }

    public final String getEntryPoint()
    {
        return this.entryPoint;
    }

    public final int getNotifyType()
    {
        return this.notifyType;
    }

    public final int getParentId()
    {
        return this.parentId;
    }

    public final String getRequestCode()
    {
        return this.requestCode;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        return ((((((((((((((((((this.title.hashCode() * 31) + this.cancelButton.hashCode()) * 31) + this.confirmButton.hashCode()) * 31) + Integer.hashCode(this.parentId)) * 31) + this.requestCode.hashCode()) * 31) + this.bankMediaEntities.hashCode()) * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + Integer.hashCode(this.notifyType)) * 31) + this.entryPoint.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.cancelButton;
        String v2 = this.confirmButton;
        int v3 = this.parentId;
        String v4 = this.requestCode;
        java.util.List v5 = this.bankMediaEntities;
        String v6 = this.bankCode;
        int v7 = this.bcErrorCode;
        int v8 = this.notifyType;
        String v9 = this.entryPoint;
        StringBuilder v10_1 = new StringBuilder("BankBottomMediaDialogArgument(title=");
        v10_1.append(v0_0);
        v10_1.append(", cancelButton=");
        v10_1.append(v1);
        v10_1.append(", confirmButton=");
        v10_1.append(v2);
        v10_1.append(", parentId=");
        v10_1.append(v3);
        v10_1.append(", requestCode=");
        v10_1.append(v4);
        v10_1.append(", bankMediaEntities=");
        v10_1.append(v5);
        v10_1.append(", bankCode=");
        v10_1.append(v6);
        v10_1.append(", bcErrorCode=");
        v10_1.append(v7);
        v10_1.append(", notifyType=");
        v10_1.append(v8);
        v10_1.append(", entryPoint=");
        v10_1.append(v9);
        v10_1.append(")");
        return v10_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.title);
        p3.writeString(this.cancelButton);
        p3.writeString(this.confirmButton);
        p3.writeInt(this.parentId);
        p3.writeString(this.requestCode);
        java.util.Iterator v0_1 = this.bankMediaEntities;
        p3.writeInt(v0_1.size());
        java.util.Iterator v0_2 = v0_1.iterator();
        while (v0_2.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity) v0_2.next()).writeToParcel(p3, p4);
        }
        p3.writeString(this.bankCode);
        p3.writeInt(this.bcErrorCode);
        p3.writeInt(this.notifyType);
        p3.writeString(this.entryPoint);
        return;
    }
}
