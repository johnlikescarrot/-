package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$BankSecurityModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity authenCancelConsentDialogEntity;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity data;
    private final int functionId;
    private final String traceId;
    private final String zpTransId;

    static BankSubmitStateModel$BankSecurityModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel.CREATOR = new uo.y(18);
        return;
    }

    public BankSubmitStateModel$BankSecurityModel(vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity p1, String p2, String p3, int p4, vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity p5)
    {
        this.data = p1;
        this.zpTransId = p2;
        this.traceId = p3;
        this.functionId = p4;
        this.authenCancelConsentDialogEntity = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel p0, vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity p1, String p2, String p3, int p4, vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.data;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.zpTransId;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.traceId;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.functionId;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.authenCancelConsentDialogEntity;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity component1()
    {
        return this.data;
    }

    public final String component2()
    {
        return this.zpTransId;
    }

    public final String component3()
    {
        return this.traceId;
    }

    public final int component4()
    {
        return this.functionId;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity component5()
    {
        return this.authenCancelConsentDialogEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel copy(vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity p7, String p8, String p9, int p10, vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.data, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel) p5).data)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel) p5).zpTransId)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.traceId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel) p5).traceId)) {
                            if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel) p5).functionId) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.authenCancelConsentDialogEntity, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankSecurityModel) p5).authenCancelConsentDialogEntity)) {
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
            return 1;
        }
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity getAuthenCancelConsentDialogEntity()
    {
        return this.authenCancelConsentDialogEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSecurityEntity getData()
    {
        return this.data;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final String getTraceId()
    {
        return this.traceId;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v0_8;
        int v0_0 = this.data;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_8 = v0_0.hashCode();
        } else {
            v0_8 = 0;
        }
        int v0_6 = (((((((v0_8 * 31) + this.zpTransId.hashCode()) * 31) + this.traceId.hashCode()) * 31) + Integer.hashCode(this.functionId)) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity v2_4 = this.authenCancelConsentDialogEntity;
        if (v2_4 != null) {
            v1 = v2_4.hashCode();
        }
        return (v0_6 + v1);
    }

    public String toString()
    {
        String v0_0 = this.data;
        String v1 = this.zpTransId;
        String v2 = this.traceId;
        int v3 = this.functionId;
        vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity v4 = this.authenCancelConsentDialogEntity;
        StringBuilder v5_1 = new StringBuilder("BankSecurityModel(data=");
        v5_1.append(v0_0);
        v5_1.append(", zpTransId=");
        v5_1.append(v1);
        v5_1.append(", traceId=");
        v5_1.append(v2);
        v5_1.append(", functionId=");
        v5_1.append(v3);
        v5_1.append(", authenCancelConsentDialogEntity=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity v0_0 = this.data;
        if (v0_0 != null) {
            p4.writeInt(1);
            v0_0.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        p4.writeString(this.zpTransId);
        p4.writeString(this.traceId);
        p4.writeInt(this.functionId);
        vn.com.vng.zalopay.bankbinding.domain.entity.link.AuthenCancelConsentDialogEntity v0_2 = this.authenCancelConsentDialogEntity;
        if (v0_2 != null) {
            p4.writeInt(1);
            v0_2.writeToParcel(p4, p5);
            return;
        } else {
            p4.writeInt(0);
            return;
        }
    }
}
