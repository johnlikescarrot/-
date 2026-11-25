package vn.com.vng.zalopay.bankbinding.domain.entity.select.helper;
public final class FaqItemContentEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity cta;
    private final String name;
    private final String type;
    private final java.util.List values;

    static FaqItemContentEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity.CREATOR = new Zn.a(9);
        return;
    }

    public FaqItemContentEntity(String p1, java.util.List p2, String p3, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity p4)
    {
        this.type = p1;
        this.values = p2;
        this.name = p3;
        this.cta = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity p0, String p1, java.util.List p2, String p3, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.type;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.values;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.name;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.cta;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.type;
    }

    public final java.util.List component2()
    {
        return this.values;
    }

    public final String component3()
    {
        return this.name;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity component4()
    {
        return this.cta;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity copy(String p2, java.util.List p3, String p4, vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity p5)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.type, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) p5).type)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.values, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) p5).values)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) p5).name)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cta, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) p5).cta)) {
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity getCta()
    {
        return this.cta;
    }

    public final String getName()
    {
        return this.name;
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
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity v1_4;
        int v0_7 = (this.type.hashCode() * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity v1_3 = this.values;
        int v2 = 0;
        if (v1_3 != null) {
            v1_4 = v1_3.hashCode();
        } else {
            v1_4 = 0;
        }
        int v0_4 = ((((v0_7 + v1_4) * 31) + this.name.hashCode()) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity v1_2 = this.cta;
        if (v1_2 != null) {
            v2 = v1_2.hashCode();
        }
        return (v0_4 + v2);
    }

    public String toString()
    {
        String v0_0 = this.type;
        java.util.List v1 = this.values;
        String v2 = this.name;
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity v3 = this.cta;
        StringBuilder v4_1 = new StringBuilder("FaqItemContentEntity(type=");
        v4_1.append(v0_0);
        v4_1.append(", values=");
        v4_1.append(v1);
        v4_1.append(", name=");
        v4_1.append(v2);
        v4_1.append(", cta=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.type);
        p3.writeStringList(this.values);
        p3.writeString(this.name);
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity v0_3 = this.cta;
        if (v0_3 != null) {
            p3.writeInt(1);
            v0_3.writeToParcel(p3, p4);
            return;
        } else {
            p3.writeInt(0);
            return;
        }
    }
}
