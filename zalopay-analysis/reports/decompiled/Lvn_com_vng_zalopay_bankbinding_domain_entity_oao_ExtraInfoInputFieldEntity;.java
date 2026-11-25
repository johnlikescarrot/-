package vn.com.vng.zalopay.bankbinding.domain.entity.oao;
public final class ExtraInfoInputFieldEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity addressEntity;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.oao.EmailEntity email;

    static ExtraInfoInputFieldEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity.CREATOR = new Zn.a(4);
        return;
    }

    public ExtraInfoInputFieldEntity(vn.com.vng.zalopay.bankbinding.domain.entity.oao.EmailEntity p1, vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity p2)
    {
        this.email = p1;
        this.addressEntity = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity p0, vn.com.vng.zalopay.bankbinding.domain.entity.oao.EmailEntity p1, vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.email;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.addressEntity;
        }
        return p0.copy(p1, p2);
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.EmailEntity component1()
    {
        return this.email;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity component2()
    {
        return this.addressEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity copy(vn.com.vng.zalopay.bankbinding.domain.entity.oao.EmailEntity p2, vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity p3)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.email, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity) p5).email)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.addressEntity, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoInputFieldEntity) p5).addressEntity)) {
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
            return 1;
        }
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity getAddressEntity()
    {
        return this.addressEntity;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.EmailEntity getEmail()
    {
        return this.email;
    }

    public int hashCode()
    {
        int v0_2;
        int v0_0 = this.email;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        int v0_3 = (v0_2 * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity v2 = this.addressEntity;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_3 + v1);
    }

    public String toString()
    {
        String v0_0 = this.email;
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity v1 = this.addressEntity;
        StringBuilder v2_1 = new StringBuilder("ExtraInfoInputFieldEntity(email=");
        v2_1.append(v0_0);
        v2_1.append(", addressEntity=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity v0_0 = this.email;
        if (v0_0 != null) {
            p4.writeInt(1);
            v0_0.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity v0_1 = this.addressEntity;
        if (v0_1 != null) {
            p4.writeInt(1);
            v0_1.writeToParcel(p4, p5);
            return;
        } else {
            p4.writeInt(0);
            return;
        }
    }
}
