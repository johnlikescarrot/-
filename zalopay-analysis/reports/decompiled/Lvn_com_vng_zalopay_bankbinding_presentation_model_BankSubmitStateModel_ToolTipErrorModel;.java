package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$ToolTipErrorModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final uo.E Companion;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String issuerBankName;
    private final String message;
    private final int page;
    private final String suggestAction;
    private final String zpTransId;

    static BankSubmitStateModel$ToolTipErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel.Companion = new uo.E();
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel.CREATOR = new uo.y(25);
        return;
    }

    public BankSubmitStateModel$ToolTipErrorModel(String p1, String p2, int p3, int p4, String p5, String p6, int p7)
    {
        this.suggestAction = p1;
        this.message = p2;
        this.page = p3;
        this.bcErrorCode = p4;
        this.zpTransId = p5;
        this.issuerBankName = p6;
        this.bmErrorCode = p7;
        return;
    }

    public synthetic BankSubmitStateModel$ToolTipErrorModel(String p9, String p10, int p11, int p12, String p13, String p14, int p15, int p16, kotlin.jvm.internal.DefaultConstructorMarker p17)
    {
        if ((p16 & 4) != 0) {
            p11 = 2;
        }
        this(p9, p10, p11, p12, p13, p14, p15);
        return;
    }

    private final String component1()
    {
        return this.suggestAction;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel p0, String p1, String p2, int p3, int p4, String p5, String p6, int p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.suggestAction;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.message;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.page;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.bcErrorCode;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.zpTransId;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.issuerBankName;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
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

    public final String component6()
    {
        return this.issuerBankName;
    }

    public final int component7()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel copy(String p9, String p10, int p11, int p12, String p13, String p14, int p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.suggestAction, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) p5).suggestAction)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) p5).message)) {
                        if (this.page == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) p5).page) {
                            if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) p5).bcErrorCode) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) p5).zpTransId)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) p5).issuerBankName)) {
                                        if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ToolTipErrorModel) p5).bmErrorCode) {
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

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final int getBmErrorCode()
    {
        return this.bmErrorCode;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
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
        return ((((((((((((this.suggestAction.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.page)) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.zpTransId.hashCode()) * 31) + this.issuerBankName.hashCode()) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.suggestAction;
        String v1 = this.message;
        int v2 = this.page;
        int v3 = this.bcErrorCode;
        String v4 = this.zpTransId;
        String v5 = this.issuerBankName;
        int v6 = this.bmErrorCode;
        StringBuilder v7_1 = new StringBuilder("ToolTipErrorModel(suggestAction=");
        v7_1.append(v0_0);
        v7_1.append(", message=");
        v7_1.append(v1);
        v7_1.append(", page=");
        v7_1.append(v2);
        v7_1.append(", bcErrorCode=");
        v7_1.append(v3);
        v7_1.append(", zpTransId=");
        v7_1.append(v4);
        v7_1.append(", issuerBankName=");
        v7_1.append(v5);
        v7_1.append(", bmErrorCode=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final int transformFieldIdFromAction()
    {
        int v0_0 = this.suggestAction;
        switch (v0_0.hashCode()) {
            case -1300785858:
                if (v0_0.equals("HOLDER_NAME")) {
                    return 2;
                } else {
                }
            case 67139:
                if (v0_0.equals("CVV")) {
                    return 5;
                } else {
                }
            case 646865086:
                if (v0_0.equals("IDENTITY")) {
                    return 6;
                } else {
                }
            case 1957526388:
                if (v0_0.equals("ISSUE_DATE")) {
                    return 3;
                } else {
                }
            case 2085849806:
                if (v0_0.equals("EXPIRE_DATE")) {
                    return 4;
                } else {
                }
            case 2093734338:
                if (v0_0.equals("CARD_ACCOUNT_NO")) {
                    return 1;
                } else {
                }
            default:
        }
        return 0;
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.suggestAction);
        p1.writeString(this.message);
        p1.writeInt(this.page);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.zpTransId);
        p1.writeString(this.issuerBankName);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
