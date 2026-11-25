package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankRopStateModel$ToolTipErrorModel extends uo.A implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String message;
    private final int page;
    private final String suggestAction;
    private final String zpTransId;

    static BankRopStateModel$ToolTipErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel.CREATOR = new uo.y(14);
        return;
    }

    public BankRopStateModel$ToolTipErrorModel(String p1, String p2, int p3, int p4, String p5, int p6)
    {
        this.suggestAction = p1;
        this.message = p2;
        this.page = p3;
        this.bcErrorCode = p4;
        this.zpTransId = p5;
        this.bmErrorCode = p6;
        return;
    }

    public synthetic BankRopStateModel$ToolTipErrorModel(String p8, String p9, int p10, int p11, String p12, int p13, int p14, kotlin.jvm.internal.DefaultConstructorMarker p15)
    {
        if ((p14 & 4) != 0) {
            p10 = 2;
        }
        this(p8, p9, p10, p11, p12, p13);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel p0, String p1, String p2, int p3, int p4, String p5, int p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.suggestAction;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.message;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.page;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.bcErrorCode;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.zpTransId;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final String component1()
    {
        return this.suggestAction;
    }

    public final String component2()
    {
        return this.message;
    }

    public final int component3()
    {
        return this.page;
    }

    public final int component4()
    {
        return this.bcErrorCode;
    }

    public final String component5()
    {
        return this.zpTransId;
    }

    public final int component6()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel copy(String p8, String p9, int p10, int p11, String p12, int p13)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.suggestAction, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel) p5).suggestAction)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel) p5).message)) {
                        if (this.page == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel) p5).page) {
                            if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel) p5).bcErrorCode) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel) p5).zpTransId)) {
                                    if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankRopStateModel$ToolTipErrorModel) p5).bmErrorCode) {
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

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final int getBmErrorCode()
    {
        return this.bmErrorCode;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final int getPage()
    {
        return this.page;
    }

    public final String getSuggestAction()
    {
        return this.suggestAction;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((((((this.suggestAction.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.page)) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.suggestAction;
        String v1 = this.message;
        int v2 = this.page;
        int v3 = this.bcErrorCode;
        String v4 = this.zpTransId;
        int v5 = this.bmErrorCode;
        StringBuilder v6_1 = new StringBuilder("ToolTipErrorModel(suggestAction=");
        v6_1.append(v0_0);
        v6_1.append(", message=");
        v6_1.append(v1);
        v6_1.append(", page=");
        v6_1.append(v2);
        v6_1.append(", bcErrorCode=");
        v6_1.append(v3);
        v6_1.append(", zpTransId=");
        v6_1.append(v4);
        v6_1.append(", bmErrorCode=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel transform()
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel(this.suggestAction, this.message, this.page, this.bcErrorCode, this.zpTransId, "", this.bmErrorCode);
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.suggestAction);
        p1.writeString(this.message);
        p1.writeInt(this.page);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
