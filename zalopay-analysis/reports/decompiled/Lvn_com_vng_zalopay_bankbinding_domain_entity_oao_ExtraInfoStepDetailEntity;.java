package vn.com.vng.zalopay.bankbinding.domain.entity.oao;
public final class ExtraInfoStepDetailEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String benefits;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity inputFields;
    private final boolean isSubmitInfo;

    static ExtraInfoStepDetailEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity.CREATOR = new Zn.a(5);
        return;
    }

    public ExtraInfoStepDetailEntity(vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity p1, String p2, boolean p3)
    {
        this.inputFields = p1;
        this.benefits = p2;
        this.isSubmitInfo = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity p0, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity p1, String p2, boolean p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.inputFields;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.benefits;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.isSubmitInfo;
        }
        return p0.copy(p1, p2, p3);
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity component1()
    {
        return this.inputFields;
    }

    public final String component2()
    {
        return this.benefits;
    }

    public final boolean component3()
    {
        return this.isSubmitInfo;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity copy(vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity p2, String p3, boolean p4)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.inputFields, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity) p5).inputFields)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.benefits, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity) p5).benefits)) {
                        if (this.isSubmitInfo == ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity) p5).isSubmitInfo) {
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
            return 1;
        }
    }

    public final String getBenefits()
    {
        return this.benefits;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity getInputFields()
    {
        return this.inputFields;
    }

    public int hashCode()
    {
        int v0_3;
        int v0_0 = this.inputFields;
        if (v0_0 != 0) {
            v0_3 = v0_0.hashCode();
        } else {
            v0_3 = 0;
        }
        return ((((v0_3 * 31) + this.benefits.hashCode()) * 31) + Boolean.hashCode(this.isSubmitInfo));
    }

    public final boolean isSubmitInfo()
    {
        return this.isSubmitInfo;
    }

    public String toString()
    {
        String v0_0 = this.inputFields;
        String v1 = this.benefits;
        boolean v2 = this.isSubmitInfo;
        StringBuilder v3_1 = new StringBuilder("ExtraInfoStepDetailEntity(inputFields=");
        v3_1.append(v0_0);
        v3_1.append(", benefits=");
        v3_1.append(v1);
        v3_1.append(", isSubmitInfo=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity v0 = this.inputFields;
        if (v0 != null) {
            p3.writeInt(1);
            v0.writeToParcel(p3, p4);
        } else {
            p3.writeInt(0);
        }
        p3.writeString(this.benefits);
        p3.writeInt(this.isSubmitInfo);
        return;
    }
}
