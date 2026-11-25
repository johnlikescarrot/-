package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class IdentityOptionEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int charType;
    private final String fullName;
    private final int idType;
    private final int maxLength;
    private final String name;
    private final java.util.List validators;

    static IdentityOptionEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity.CREATOR = new Io.S(23);
        return;
    }

    public IdentityOptionEntity(int p1, int p2, String p3, java.util.List p4, String p5, int p6)
    {
        this.idType = p1;
        this.charType = p2;
        this.name = p3;
        this.validators = p4;
        this.fullName = p5;
        this.maxLength = p6;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity p0, int p1, int p2, String p3, java.util.List p4, String p5, int p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.idType;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.charType;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.name;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.validators;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.fullName;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.maxLength;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final int component1()
    {
        return this.idType;
    }

    public final int component2()
    {
        return this.charType;
    }

    public final String component3()
    {
        return this.name;
    }

    public final java.util.List component4()
    {
        return this.validators;
    }

    public final String component5()
    {
        return this.fullName;
    }

    public final int component6()
    {
        return this.maxLength;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity copy(int p8, int p9, String p10, java.util.List p11, String p12, int p13)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity)) {
                if (this.idType == ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) p5).idType) {
                    if (this.charType == ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) p5).charType) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) p5).name)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) p5).validators)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.fullName, ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) p5).fullName)) {
                                    if (this.maxLength == ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) p5).maxLength) {
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

    public final int getCharType()
    {
        return this.charType;
    }

    public final String getFullName()
    {
        return this.fullName;
    }

    public final int getIdType()
    {
        return this.idType;
    }

    public final int getMaxLength()
    {
        return this.maxLength;
    }

    public final String getName()
    {
        return this.name;
    }

    public final java.util.List getValidators()
    {
        return this.validators;
    }

    public int hashCode()
    {
        return ((((((((((Integer.hashCode(this.idType) * 31) + Integer.hashCode(this.charType)) * 31) + this.name.hashCode()) * 31) + this.validators.hashCode()) * 31) + this.fullName.hashCode()) * 31) + Integer.hashCode(this.maxLength));
    }

    public String toString()
    {
        String v0_0 = this.idType;
        int v1 = this.charType;
        String v2 = this.name;
        java.util.List v3 = this.validators;
        String v4 = this.fullName;
        int v5 = this.maxLength;
        StringBuilder v6_1 = new StringBuilder("IdentityOptionEntity(idType=");
        v6_1.append(v0_0);
        v6_1.append(", charType=");
        v6_1.append(v1);
        v6_1.append(", name=");
        v6_1.append(v2);
        v6_1.append(", validators=");
        v6_1.append(v3);
        v6_1.append(", fullName=");
        v6_1.append(v4);
        v6_1.append(", maxLength=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.idType);
        p3.writeInt(this.charType);
        p3.writeString(this.name);
        java.util.Iterator v0_4 = this.validators;
        p3.writeInt(v0_4.size());
        java.util.Iterator v0_1 = v0_4.iterator();
        while (v0_1.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) v0_1.next()).writeToParcel(p3, p4);
        }
        p3.writeString(this.fullName);
        p3.writeInt(this.maxLength);
        return;
    }
}
