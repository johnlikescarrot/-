package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankBottomDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String confirmButtonText;
    private final int icon;
    private final int linkingType;
    private final String message;
    private final int parentId;
    private final String requestResult;
    private final String title;
    private final String zpTransId;

    static BankBottomDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument.CREATOR = new Zn.a(14);
        return;
    }

    public BankBottomDialogArgument(String p1, String p2, int p3, int p4, String p5, int p6, String p7, String p8, int p9, String p10, int p11)
    {
        this.title = p1;
        this.message = p2;
        this.icon = p3;
        this.parentId = p4;
        this.requestResult = p5;
        this.bcErrorCode = p6;
        this.confirmButtonText = p7;
        this.bankCode = p8;
        this.linkingType = p9;
        this.zpTransId = p10;
        this.bmErrorCode = p11;
        return;
    }

    public synthetic BankBottomDialogArgument(String p2, String p3, int p4, int p5, String p6, int p7, String p8, String p9, int p10, String p11, int p12, int p13, kotlin.jvm.internal.DefaultConstructorMarker p14)
    {
        if ((p13 & 1) != 0) {
            p2 = "";
        }
        if ((p13 & 16) != 0) {
            p6 = "";
        }
        if ((p13 & 64) != 0) {
            p8 = 0;
        }
        this(p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument p0, String p1, String p2, int p3, int p4, String p5, int p6, String p7, String p8, int p9, String p10, int p11, int p12, Object p13)
    {
        if ((p12 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p12 & 2) != 0) {
            p2 = p0.message;
        }
        if ((p12 & 4) != 0) {
            p3 = p0.icon;
        }
        if ((p12 & 8) != 0) {
            p4 = p0.parentId;
        }
        if ((p12 & 16) != 0) {
            p5 = p0.requestResult;
        }
        if ((p12 & 32) != 0) {
            p6 = p0.bcErrorCode;
        }
        if ((p12 & 64) != 0) {
            p7 = p0.confirmButtonText;
        }
        if ((p12 & 128) != 0) {
            p8 = p0.bankCode;
        }
        if ((p12 & 256) != 0) {
            p9 = p0.linkingType;
        }
        if ((p12 & 512) != 0) {
            p10 = p0.zpTransId;
        }
        if ((p12 & 1024) != 0) {
            p11 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
    }

    public final String component1()
    {
        return this.title;
    }

    public final String component10()
    {
        return this.zpTransId;
    }

    public final int component11()
    {
        return this.bmErrorCode;
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

    public final int component6()
    {
        return this.bcErrorCode;
    }

    public final String component7()
    {
        return this.confirmButtonText;
    }

    public final String component8()
    {
        return this.bankCode;
    }

    public final int component9()
    {
        return this.linkingType;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument copy(String p13, String p14, int p15, int p16, String p17, int p18, String p19, String p20, int p21, String p22, int p23)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).message)) {
                        if (this.icon == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).icon) {
                            if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).parentId) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).requestResult)) {
                                    if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).bcErrorCode) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButtonText, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).confirmButtonText)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).bankCode)) {
                                                if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).linkingType) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).zpTransId)) {
                                                        if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) p5).bmErrorCode) {
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

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final int getBmErrorCode()
    {
        return this.bmErrorCode;
    }

    public final String getConfirmButtonText()
    {
        return this.confirmButtonText;
    }

    public final int getIcon()
    {
        return this.icon;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
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

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v1_8;
        int v0_9 = (((((((((((this.title.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.icon)) * 31) + Integer.hashCode(this.parentId)) * 31) + this.requestResult.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode)) * 31);
        int v1_7 = this.confirmButtonText;
        if (v1_7 != 0) {
            v1_8 = v1_7.hashCode();
        } else {
            v1_8 = 0;
        }
        return (((((((((v0_9 + v1_8) * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.message;
        int v2 = this.icon;
        int v3 = this.parentId;
        String v4 = this.requestResult;
        int v5 = this.bcErrorCode;
        String v6 = this.confirmButtonText;
        String v7 = this.bankCode;
        int v8 = this.linkingType;
        String v9 = this.zpTransId;
        int v10 = this.bmErrorCode;
        StringBuilder v11_1 = new StringBuilder("BankBottomDialogArgument(title=");
        v11_1.append(v0_0);
        v11_1.append(", message=");
        v11_1.append(v1);
        v11_1.append(", icon=");
        v11_1.append(v2);
        v11_1.append(", parentId=");
        v11_1.append(v3);
        v11_1.append(", requestResult=");
        v11_1.append(v4);
        v11_1.append(", bcErrorCode=");
        v11_1.append(v5);
        v11_1.append(", confirmButtonText=");
        v11_1.append(v6);
        v11_1.append(", bankCode=");
        v11_1.append(v7);
        v11_1.append(", linkingType=");
        v11_1.append(v8);
        v11_1.append(", zpTransId=");
        v11_1.append(v9);
        v11_1.append(", bmErrorCode=");
        v11_1.append(v10);
        v11_1.append(")");
        return v11_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.title);
        p1.writeString(this.message);
        p1.writeInt(this.icon);
        p1.writeInt(this.parentId);
        p1.writeString(this.requestResult);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.confirmButtonText);
        p1.writeString(this.bankCode);
        p1.writeInt(this.linkingType);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
