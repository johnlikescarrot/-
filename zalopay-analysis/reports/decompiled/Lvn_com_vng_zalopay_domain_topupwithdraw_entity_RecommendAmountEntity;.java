package vn.com.vng.zalopay.domain.topupwithdraw.entity;
public final class RecommendAmountEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String label;
    private final long value;

    static RecommendAmountEntity()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity.CREATOR = new OU.a(12);
        return;
    }

    public RecommendAmountEntity(long p1, String p3)
    {
        this.value = p1;
        this.label = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity copy$default(vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity p0, long p1, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.value;
        }
        if ((p4 & 2) != 0) {
            p3 = p0.label;
        }
        return p0.copy(p1, p3);
    }

    public final long component1()
    {
        return this.value;
    }

    public final String component2()
    {
        return this.label;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity copy(long p2, String p4)
    {
        return new vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity(p2, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity)) {
                if (this.value == ((vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity) p8).value) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.label, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.RecommendAmountEntity) p8).label)) {
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

    public final String getLabel()
    {
        return this.label;
    }

    public final long getValue()
    {
        return this.value;
    }

    public int hashCode()
    {
        return (this.label.hashCode() + (Long.hashCode(this.value) * 31));
    }

    public String toString()
    {
        String v0_1 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.n("RecommendAmountEntity(value=", this.value, ", label=", this.label);
        v0_1.append(")");
        return v0_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeLong(this.value);
        p3.writeString(this.label);
        return;
    }
}
