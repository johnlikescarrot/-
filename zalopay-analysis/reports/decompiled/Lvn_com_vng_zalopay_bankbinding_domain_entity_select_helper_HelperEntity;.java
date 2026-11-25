package vn.com.vng.zalopay.bankbinding.domain.entity.select.helper;
public final class HelperEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity toroEntity;

    static HelperEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity.CREATOR = new Zn.a(11);
        return;
    }

    public HelperEntity(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity p1)
    {
        this.toroEntity = p1;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity p0, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.toroEntity;
        }
        return p0.copy(p1);
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity component1()
    {
        return this.toroEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity copy(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity p2)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity(p2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.toroEntity, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.HelperEntity) p4).toroEntity)) {
                    return 1;
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity getToroEntity()
    {
        return this.toroEntity;
    }

    public int hashCode()
    {
        int v0_0 = this.toroEntity;
        if (v0_0 != 0) {
            return v0_0.hashCode();
        } else {
            return 0;
        }
    }

    public String toString()
    {
        String v0_0 = this.toroEntity;
        StringBuilder v1_1 = new StringBuilder("HelperEntity(toroEntity=");
        v1_1.append(v0_0);
        v1_1.append(")");
        return v1_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity v0 = this.toroEntity;
        if (v0 != null) {
            p3.writeInt(1);
            v0.writeToParcel(p3, p4);
            return;
        } else {
            p3.writeInt(0);
            return;
        }
    }
}
