package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$BottomDialogErrorModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String issuerBankName;
    private final int linkingType;
    private final String message;
    private final int page;
    private final String zpTransId;

    static BankSubmitStateModel$BottomDialogErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel.CREATOR = new uo.y(19);
        return;
    }

    public BankSubmitStateModel$BottomDialogErrorModel(String p1, int p2, int p3, String p4, String p5, int p6, String p7, int p8)
    {
        this.message = p1;
        this.page = p2;
        this.bcErrorCode = p3;
        this.zpTransId = p4;
        this.issuerBankName = p5;
        this.linkingType = p6;
        this.bankCode = p7;
        this.bmErrorCode = p8;
        return;
    }

    public synthetic BankSubmitStateModel$BottomDialogErrorModel(String p12, int p13, int p14, String p15, String p16, int p17, String p18, int p19, int p20, kotlin.jvm.internal.DefaultConstructorMarker p21)
    {
        int v4;
        if ((p20 & 2) == 0) {
            v4 = p13;
        } else {
            v4 = 2;
        }
        this(p12, v4, p14, p15, p16, p17, p18, p19);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel p0, String p1, int p2, int p3, String p4, String p5, int p6, String p7, int p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.page;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.bcErrorCode;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.zpTransId;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.issuerBankName;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.linkingType;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.bankCode;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
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

    public final String component5()
    {
        return this.issuerBankName;
    }

    public final int component6()
    {
        return this.linkingType;
    }

    public final String component7()
    {
        return this.bankCode;
    }

    public final int component8()
    {
        return this.bmErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel copy(String p10, int p11, int p12, String p13, String p14, int p15, String p16, int p17)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).message)) {
                    if (this.page == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).page) {
                        if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).bcErrorCode) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).zpTransId)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).issuerBankName)) {
                                    if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).linkingType) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).bankCode)) {
                                            if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BottomDialogErrorModel) p5).bmErrorCode) {
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
        return ((((((((((((((this.message.hashCode() * 31) + Integer.hashCode(this.page)) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.zpTransId.hashCode()) * 31) + this.issuerBankName.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.message;
        int v1 = this.page;
        int v2 = this.bcErrorCode;
        String v3 = this.zpTransId;
        String v4 = this.issuerBankName;
        int v5 = this.linkingType;
        String v6 = this.bankCode;
        int v7 = this.bmErrorCode;
        StringBuilder v8_0 = new StringBuilder("BottomDialogErrorModel(message=");
        v8_0.append(v0_0);
        v8_0.append(", page=");
        v8_0.append(v1);
        v8_0.append(", bcErrorCode=");
        v8_0.append(v2);
        v8_0.append(", zpTransId=");
        v8_0.append(v3);
        v8_0.append(", issuerBankName=");
        v8_0.append(v4);
        v8_0.append(", linkingType=");
        v8_0.append(v5);
        v8_0.append(", bankCode=");
        v8_0.append(v6);
        v8_0.append(", bmErrorCode=");
        v8_0.append(v7);
        v8_0.append(")");
        return v8_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeInt(this.page);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.zpTransId);
        p1.writeString(this.issuerBankName);
        p1.writeInt(this.linkingType);
        p1.writeString(this.bankCode);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
