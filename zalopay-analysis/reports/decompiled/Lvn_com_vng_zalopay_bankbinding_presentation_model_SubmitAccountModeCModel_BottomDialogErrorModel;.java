package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class SubmitAccountModeCModel$BottomDialogErrorModel extends uo.s0 implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final String issuerBankName;
    private final int linkingType;
    private final String message;
    private final String zpTransId;

    static SubmitAccountModeCModel$BottomDialogErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel.CREATOR = new uo.L(9);
        return;
    }

    public SubmitAccountModeCModel$BottomDialogErrorModel(String p1, int p2, String p3, String p4, int p5, int p6)
    {
        this.message = p1;
        this.bcErrorCode = p2;
        this.zpTransId = p3;
        this.issuerBankName = p4;
        this.linkingType = p5;
        this.bmErrorCode = p6;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel p0, String p1, int p2, String p3, String p4, int p5, int p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.bcErrorCode;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.zpTransId;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.issuerBankName;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.linkingType;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.bmErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
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

    public final vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel copy(String p8, int p9, String p10, String p11, int p12, int p13)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel) p5).message)) {
                    if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel) p5).bcErrorCode) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel) p5).zpTransId)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel) p5).issuerBankName)) {
                                if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel) p5).linkingType) {
                                    if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$BottomDialogErrorModel) p5).bmErrorCode) {
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
        return ((((((((((this.message.hashCode() * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.zpTransId.hashCode()) * 31) + this.issuerBankName.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + Integer.hashCode(this.bmErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.message;
        int v1 = this.bcErrorCode;
        String v2 = this.zpTransId;
        String v3 = this.issuerBankName;
        int v4 = this.linkingType;
        int v5 = this.bmErrorCode;
        StringBuilder v6_1 = new StringBuilder("BottomDialogErrorModel(message=");
        v6_1.append(v0_0);
        v6_1.append(", bcErrorCode=");
        v6_1.append(v1);
        v6_1.append(", zpTransId=");
        v6_1.append(v2);
        v6_1.append(", issuerBankName=");
        v6_1.append(v3);
        v6_1.append(", linkingType=");
        v6_1.append(v4);
        v6_1.append(", bmErrorCode=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeInt(this.bcErrorCode);
        p1.writeString(this.zpTransId);
        p1.writeString(this.issuerBankName);
        p1.writeInt(this.linkingType);
        p1.writeInt(this.bmErrorCode);
        return;
    }
}
