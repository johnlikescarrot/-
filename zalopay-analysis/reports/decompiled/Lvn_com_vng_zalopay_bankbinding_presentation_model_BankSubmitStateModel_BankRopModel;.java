package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$BankRopModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final java.util.List fields;
    private final int functionId;
    private final vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams infoParams;
    private final vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel kycInputModel;

    static BankSubmitStateModel$BankRopModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel.CREATOR = new uo.y(16);
        return;
    }

    public BankSubmitStateModel$BankRopModel(int p1, java.util.List p2, vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p3, String p4, vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams p5)
    {
        this.functionId = p1;
        this.fields = p2;
        this.kycInputModel = p3;
        this.bankCode = p4;
        this.infoParams = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel p0, int p1, java.util.List p2, vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p3, String p4, vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.functionId;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.fields;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.kycInputModel;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.bankCode;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.infoParams;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final int component1()
    {
        return this.functionId;
    }

    public final java.util.List component2()
    {
        return this.fields;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel component3()
    {
        return this.kycInputModel;
    }

    public final String component4()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams component5()
    {
        return this.infoParams;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel copy(int p7, java.util.List p8, vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p9, String p10, vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel)) {
                if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel) p5).functionId) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.fields, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel) p5).fields)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.kycInputModel, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel) p5).kycInputModel)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel) p5).bankCode)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.infoParams, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopModel) p5).infoParams)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final java.util.List getFields()
    {
        return this.fields;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams getInfoParams()
    {
        return this.infoParams;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel getKycInputModel()
    {
        return this.kycInputModel;
    }

    public int hashCode()
    {
        return ((((((((Integer.hashCode(this.functionId) * 31) + this.fields.hashCode()) * 31) + this.kycInputModel.hashCode()) * 31) + this.bankCode.hashCode()) * 31) + this.infoParams.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.functionId;
        java.util.List v1 = this.fields;
        vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel v2 = this.kycInputModel;
        String v3 = this.bankCode;
        vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams v4 = this.infoParams;
        StringBuilder v5_1 = new StringBuilder("BankRopModel(functionId=");
        v5_1.append(v0_0);
        v5_1.append(", fields=");
        v5_1.append(v1);
        v5_1.append(", kycInputModel=");
        v5_1.append(v2);
        v5_1.append(", bankCode=");
        v5_1.append(v3);
        v5_1.append(", infoParams=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.functionId);
        vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams v0_4 = this.fields;
        p3.writeInt(v0_4.size());
        vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams v0_5 = v0_4.iterator();
        while (v0_5.hasNext()) {
            p3.writeParcelable(((android.os.Parcelable) v0_5.next()), p4);
        }
        this.kycInputModel.writeToParcel(p3, p4);
        p3.writeString(this.bankCode);
        this.infoParams.writeToParcel(p3, p4);
        return;
    }
}
