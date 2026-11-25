package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$MediaBottomDialogModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final String buttonLabel;
    private final java.util.List displayContents;
    private final String displayErrorType;
    private final String issuerBankName;
    private final int notifyType;
    private final int page;
    private final String zpTransId;

    static BankSubmitStateModel$MediaBottomDialogModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel.CREATOR = new uo.y(22);
        return;
    }

    public BankSubmitStateModel$MediaBottomDialogModel(String p1, String p2, java.util.List p3, int p4, String p5, int p6, String p7, int p8, String p9)
    {
        this.displayErrorType = p1;
        this.zpTransId = p2;
        this.displayContents = p3;
        this.bcErrorCode = p4;
        this.bankCode = p5;
        this.notifyType = p6;
        this.buttonLabel = p7;
        this.page = p8;
        this.issuerBankName = p9;
        return;
    }

    public synthetic BankSubmitStateModel$MediaBottomDialogModel(String p12, String p13, java.util.List p14, int p15, String p16, int p17, String p18, int p19, String p20, int p21, kotlin.jvm.internal.DefaultConstructorMarker p22)
    {
        int v9;
        if ((p21 & 128) == 0) {
            v9 = p19;
        } else {
            v9 = 2;
        }
        this(p12, p13, p14, p15, p16, p17, p18, v9, p20);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel p0, String p1, String p2, java.util.List p3, int p4, String p5, int p6, String p7, int p8, String p9, int p10, Object p11)
    {
        if ((p10 & 1) != 0) {
            p1 = p0.displayErrorType;
        }
        if ((p10 & 2) != 0) {
            p2 = p0.zpTransId;
        }
        if ((p10 & 4) != 0) {
            p3 = p0.displayContents;
        }
        if ((p10 & 8) != 0) {
            p4 = p0.bcErrorCode;
        }
        if ((p10 & 16) != 0) {
            p5 = p0.bankCode;
        }
        if ((p10 & 32) != 0) {
            p6 = p0.notifyType;
        }
        if ((p10 & 64) != 0) {
            p7 = p0.buttonLabel;
        }
        if ((p10 & 128) != 0) {
            p8 = p0.page;
        }
        if ((p10 & 256) != 0) {
            p9 = p0.issuerBankName;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public final String component1()
    {
        return this.displayErrorType;
    }

    public final String component2()
    {
        return this.zpTransId;
    }

    public final java.util.List component3()
    {
        return this.displayContents;
    }

    public final int component4()
    {
        return this.bcErrorCode;
    }

    public final String component5()
    {
        return this.bankCode;
    }

    public final int component6()
    {
        return this.notifyType;
    }

    public final String component7()
    {
        return this.buttonLabel;
    }

    public final int component8()
    {
        return this.page;
    }

    public final String component9()
    {
        return this.issuerBankName;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel copy(String p11, String p12, java.util.List p13, int p14, String p15, int p16, String p17, int p18, String p19)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel(p11, p12, p13, p14, p15, p16, p17, p18, p19);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.displayErrorType, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).displayErrorType)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).zpTransId)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.displayContents, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).displayContents)) {
                            if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).bcErrorCode) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).bankCode)) {
                                    if (this.notifyType == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).notifyType) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.buttonLabel, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).buttonLabel)) {
                                            if (this.page == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).page) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$MediaBottomDialogModel) p5).issuerBankName)) {
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

    public final String getButtonLabel()
    {
        return this.buttonLabel;
    }

    public final java.util.List getDisplayContents()
    {
        return this.displayContents;
    }

    public final String getDisplayErrorType()
    {
        return this.displayErrorType;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public final int getNotifyType()
    {
        return this.notifyType;
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
        int v1_8;
        int v0_9 = (((((((((((this.displayErrorType.hashCode() * 31) + this.zpTransId.hashCode()) * 31) + this.displayContents.hashCode()) * 31) + Integer.hashCode(this.bcErrorCode)) * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.notifyType)) * 31);
        int v1_7 = this.buttonLabel;
        if (v1_7 != 0) {
            v1_8 = v1_7.hashCode();
        } else {
            v1_8 = 0;
        }
        return (((((v0_9 + v1_8) * 31) + Integer.hashCode(this.page)) * 31) + this.issuerBankName.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.displayErrorType;
        String v1 = this.zpTransId;
        java.util.List v2 = this.displayContents;
        int v3 = this.bcErrorCode;
        String v4 = this.bankCode;
        int v5 = this.notifyType;
        String v6 = this.buttonLabel;
        int v7 = this.page;
        String v8 = this.issuerBankName;
        StringBuilder v9_0 = new StringBuilder("MediaBottomDialogModel(displayErrorType=");
        v9_0.append(v0_0);
        v9_0.append(", zpTransId=");
        v9_0.append(v1);
        v9_0.append(", displayContents=");
        v9_0.append(v2);
        v9_0.append(", bcErrorCode=");
        v9_0.append(v3);
        v9_0.append(", bankCode=");
        v9_0.append(v4);
        v9_0.append(", notifyType=");
        v9_0.append(v5);
        v9_0.append(", buttonLabel=");
        v9_0.append(v6);
        v9_0.append(", page=");
        v9_0.append(v7);
        v9_0.append(", issuerBankName=");
        v9_0.append(v8);
        v9_0.append(")");
        return v9_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.displayErrorType);
        p3.writeString(this.zpTransId);
        java.util.Iterator v0_2 = this.displayContents;
        p3.writeInt(v0_2.size());
        java.util.Iterator v0_3 = v0_2.iterator();
        while (v0_3.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity) v0_3.next()).writeToParcel(p3, p4);
        }
        p3.writeInt(this.bcErrorCode);
        p3.writeString(this.bankCode);
        p3.writeInt(this.notifyType);
        p3.writeString(this.buttonLabel);
        p3.writeInt(this.page);
        p3.writeString(this.issuerBankName);
        return;
    }
}
