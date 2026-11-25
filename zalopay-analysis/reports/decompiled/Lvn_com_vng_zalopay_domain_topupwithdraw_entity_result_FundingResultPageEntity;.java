package vn.com.vng.zalopay.domain.topupwithdraw.entity.result;
public final class FundingResultPageEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity cta;
    private final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity customBlock;

    static FundingResultPageEntity()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity.CREATOR = new OU.a(16);
        return;
    }

    public FundingResultPageEntity(vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity p1, vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity p2)
    {
        this.customBlock = p1;
        this.cta = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity copy$default(vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity p0, vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity p1, vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.customBlock;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.cta;
        }
        return p0.copy(p1, p2);
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity component1()
    {
        return this.customBlock;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity component2()
    {
        return this.cta;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity copy(vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity p2, vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity p3)
    {
        return new vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.customBlock, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity) p5).customBlock)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cta, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageEntity) p5).cta)) {
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

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity getCta()
    {
        return this.cta;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity getCustomBlock()
    {
        return this.customBlock;
    }

    public int hashCode()
    {
        int v0_2;
        int v0_0 = this.customBlock;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        int v0_3 = (v0_2 * 31);
        vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity v2 = this.cta;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_3 + v1);
    }

    public String toString()
    {
        String v0_0 = this.customBlock;
        vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity v1 = this.cta;
        StringBuilder v2_1 = new StringBuilder("FundingResultPageEntity(customBlock=");
        v2_1.append(v0_0);
        v2_1.append(", cta=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity v0_0 = this.customBlock;
        if (v0_0 != null) {
            p4.writeInt(1);
            v0_0.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity v0_1 = this.cta;
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
