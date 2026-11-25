package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankMediaEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String type;
    private final java.util.List values;

    static BankMediaEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity.CREATOR = new Io.S(12);
        return;
    }

    public BankMediaEntity(String p1, java.util.List p2)
    {
        this.type = p1;
        this.values = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity p0, String p1, java.util.List p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.type;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.values;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.type;
    }

    public final java.util.List component2()
    {
        return this.values;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity copy(String p2, java.util.List p3)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.type, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity) p5).type)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.values, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity) p5).values)) {
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

    public final String getType()
    {
        return this.type;
    }

    public final java.util.List getValues()
    {
        return this.values;
    }

    public int hashCode()
    {
        return ((this.type.hashCode() * 31) + this.values.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.type;
        java.util.List v1 = this.values;
        StringBuilder v2_1 = new StringBuilder("BankMediaEntity(type=");
        v2_1.append(v0_0);
        v2_1.append(", values=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.type);
        java.util.Iterator v0_1 = this.values;
        p3.writeInt(v0_1.size());
        java.util.Iterator v0_2 = v0_1.iterator();
        while (v0_2.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) v0_2.next()).writeToParcel(p3, p4);
        }
        return;
    }
}
