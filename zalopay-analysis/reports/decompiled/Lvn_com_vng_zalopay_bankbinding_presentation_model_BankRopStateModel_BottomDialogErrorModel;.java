package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankRopStateModel$BottomDialogErrorModel extends uo.A implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final int linkingType;
    private final String message;
    private final int page;
    private final String zpTransId;

    static BankRopStateModel$BottomDialogErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel.CREATOR = new uo.y(10);
        return;
    }

    public BankRopStateModel$BottomDialogErrorModel(String p1, int p2, int p3, String p4, int p5, String p6, int p7)
    {
        this.message = p1;
        this.page = p2;
        this.bcErrorCode = p3;
        this.zpTransId = p4;
        this.linkingType = p5;
        this.bankCode = p6;
        this.bmErrorCode = p7;
        return;
    }

    public synthetic BankRopStateModel$BottomDialogErrorModel(String p11, int p12, int p13, String p14, int p15, String p16, int p17, int p18, kotlin.jvm.internal.DefaultConstructorMarker p19)
    {
        int v4;
        if ((p18 & 2) == 0) {
            v4 = p12;
        } else {
            v4 = 2;
        }
        this(p11, v4, p13, p14, p15, p16, p17);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel p0, String p1, int p2, int p3, String p4, int p5, String p6, int p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.page;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.bcErrorCode;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.zpTransId;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.linkingType;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.bankCode;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.message;
    }

    public final int component2()
    {
        return this.page;
    }

    public final int component3()
    {
        return this.bcErrorCode;
    }

    public final String component4()
    {
        return this.zpTransId;
    }

    public final int component5()
    {
        return this.linkingType;
    }

    public final String component6()
    {
        return this.bankCode;
    }

    public final int component7()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel copy(String p9, int p10, int p11, String p12, int p13, String p14, int p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) p5).message)) {
                    if (this.page == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) p5).page) {
                        if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) p5).bcErrorCode) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) p5).zpTransId)) {
                                if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) p5).linkingType) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) p5).bankCode)) {
                                        if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$BottomDialogErrorModel) p5).bmErrorCode) {
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

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final int getBmErrorCode()
    {
        return this.bmErrorCode;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final int getPage()
    {
        return this.page;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((((((((this.message.hashCode() * 31) + Integer.hashCode(this.page)) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.message;
        int v1 = this.page;
        int v2 = this.bcErrorCode;
        String v3 = this.zpTransId;
        int v4 = this.linkingType;
        String v5 = this.bankCode;
        int v6 = this.bmErrorCode;
        StringBuilder v7_1 = new StringBuilder("BottomDialogErrorModel(message=");
        v7_1.append(v0_0);
        v7_1.append(", page=");
        v7_1.append(v1);
        v7_1.append(", bcErrorCode=");
        v7_1.append(v2);
        v7_1.append(", zpTransId=");
        v7_1.append(v3);
        v7_1.append(", linkingType=");
        v7_1.append(v4);
        v7_1.append(", bankCode=");
        v7_1.append(v5);
        v7_1.append(", bmErrorCode=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel transform()
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel(this.message, this.page, this.bcErrorCode, this.zpTransId, "", this.linkingType, this.bankCode, this.bmErrorCode);
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeInt(this.page);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.linkingType);
        p1.writeString(this.bankCode);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
