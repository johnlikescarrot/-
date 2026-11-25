package vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus;
public final class BankLinkingStatusModel$BottomDialogErrorModel extends vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String issuerBankName;
    private final int linkingType;
    private final String message;
    private final String zpTransId;

    static BankLinkingStatusModel$BottomDialogErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel.CREATOR = new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.a();
        return;
    }

    public BankLinkingStatusModel$BottomDialogErrorModel(String p2, int p3, String p4, String p5, int p6, int p7, String p8)
    {
        super(0);
        super.message = p2;
        super.bcErrorCode = p3;
        super.zpTransId = p4;
        super.issuerBankName = p5;
        super.linkingType = p6;
        super.bmErrorCode = p7;
        super.bankCode = p8;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel p0, String p1, int p2, String p3, String p4, int p5, int p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.bcErrorCode;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.zpTransId;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.issuerBankName;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.linkingType;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.bmErrorCode;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.message;
    }

    public final int component2()
    {
        return this.bcErrorCode;
    }

    public final String component3()
    {
        return this.zpTransId;
    }

    public final String component4()
    {
        return this.issuerBankName;
    }

    public final int component5()
    {
        return this.linkingType;
    }

    public final int component6()
    {
        return this.bmErrorCode;
    }

    public final String component7()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel copy(String p9, int p10, String p11, String p12, int p13, int p14, String p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel) p5).message)) {
                    if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel) p5).bcErrorCode) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel) p5).zpTransId)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel) p5).issuerBankName)) {
                                if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel) p5).linkingType) {
                                    if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel) p5).bmErrorCode) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel) p5).bankCode)) {
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

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((((((((this.message.hashCode() * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.zpTransId.hashCode()) * 31) + this.issuerBankName.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + Integer.hashCode(this.bmErrorCode)) * 31) + this.bankCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.message;
        int v1 = this.bcErrorCode;
        String v2 = this.zpTransId;
        String v3 = this.issuerBankName;
        int v4 = this.linkingType;
        int v5 = this.bmErrorCode;
        String v6 = this.bankCode;
        StringBuilder v7_1 = new StringBuilder("BottomDialogErrorModel(message=");
        v7_1.append(v0_0);
        v7_1.append(", bcErrorCode=");
        v7_1.append(v1);
        v7_1.append(", zpTransId=");
        v7_1.append(v2);
        v7_1.append(", issuerBankName=");
        v7_1.append(v3);
        v7_1.append(", linkingType=");
        v7_1.append(v4);
        v7_1.append(", bmErrorCode=");
        v7_1.append(v5);
        v7_1.append(", bankCode=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.zpTransId);
        p1.writeString(this.issuerBankName);
        p1.writeInt(this.linkingType);
        p1.writeInt(this.bmErrorCode);
        p1.writeString(this.bankCode);
        return;
    }
}
