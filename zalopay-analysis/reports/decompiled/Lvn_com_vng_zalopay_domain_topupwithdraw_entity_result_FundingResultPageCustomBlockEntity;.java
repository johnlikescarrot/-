package vn.com.vng.zalopay.domain.topupwithdraw.entity.result;
public final class FundingResultPageCustomBlockEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final float ratio;
    private final String url;

    static FundingResultPageCustomBlockEntity()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity.CREATOR = new OU.a(15);
        return;
    }

    public FundingResultPageCustomBlockEntity(String p1, float p2)
    {
        this.url = p1;
        this.ratio = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity copy$default(vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity p0, String p1, float p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.url;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.ratio;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.url;
    }

    public final float component2()
    {
        return this.ratio;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity copy(String p2, float p3)
    {
        return new vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity) p5).url)) {
                    if (Float.compare(this.ratio, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageCustomBlockEntity) p5).ratio) == 0) {
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

    public final float getRatio()
    {
        return this.ratio;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return (Float.hashCode(this.ratio) + (this.url.hashCode() * 31));
    }

    public String toString()
    {
        String v0_0 = this.url;
        float v1 = this.ratio;
        StringBuilder v2_1 = new StringBuilder("FundingResultPageCustomBlockEntity(url=");
        v2_1.append(v0_0);
        v2_1.append(", ratio=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.url);
        p1.writeFloat(this.ratio);
        return;
    }
}
