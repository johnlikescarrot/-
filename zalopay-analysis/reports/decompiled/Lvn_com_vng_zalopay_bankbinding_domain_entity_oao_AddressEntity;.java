package vn.com.vng.zalopay.bankbinding.domain.entity.oao;
public final class AddressEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int maxLength;
    private final int minLength;
    private final int order;
    private final java.util.List validators;

    static AddressEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity.CREATOR = new Zn.a(1);
        return;
    }

    public AddressEntity(int p1, int p2, int p3, java.util.List p4)
    {
        this.order = p1;
        this.minLength = p2;
        this.maxLength = p3;
        this.validators = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity p0, int p1, int p2, int p3, java.util.List p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.order;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.minLength;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.maxLength;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.validators;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final int component1()
    {
        return this.order;
    }

    public final int component2()
    {
        return this.minLength;
    }

    public final int component3()
    {
        return this.maxLength;
    }

    public final java.util.List component4()
    {
        return this.validators;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity copy(int p2, int p3, int p4, java.util.List p5)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity)) {
                if (this.order == ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity) p5).order) {
                    if (this.minLength == ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity) p5).minLength) {
                        if (this.maxLength == ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity) p5).maxLength) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.AddressEntity) p5).validators)) {
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
            return 1;
        }
    }

    public final int getMaxLength()
    {
        return this.maxLength;
    }

    public final int getMinLength()
    {
        return this.minLength;
    }

    public final int getOrder()
    {
        return this.order;
    }

    public final java.util.List getValidators()
    {
        return this.validators;
    }

    public int hashCode()
    {
        return ((((((Integer.hashCode(this.order) * 31) + Integer.hashCode(this.minLength)) * 31) + Integer.hashCode(this.maxLength)) * 31) + this.validators.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.order;
        int v1 = this.minLength;
        int v2 = this.maxLength;
        java.util.List v3 = this.validators;
        StringBuilder v4_1 = new StringBuilder("AddressEntity(order=");
        v4_1.append(v0_0);
        v4_1.append(", minLength=");
        v4_1.append(v1);
        v4_1.append(", maxLength=");
        v4_1.append(v2);
        v4_1.append(", validators=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.order);
        p3.writeInt(this.minLength);
        p3.writeInt(this.maxLength);
        java.util.Iterator v0_4 = this.validators;
        p3.writeInt(v0_4.size());
        java.util.Iterator v0_1 = v0_4.iterator();
        while (v0_1.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) v0_1.next()).writeToParcel(p3, p4);
        }
        return;
    }
}
