package vn.com.vng.zalopay.domain.topupwithdraw.entity.result;
public final class FundingResultPageExtraCtaEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String title;
    private final String url;

    static FundingResultPageExtraCtaEntity()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity.CREATOR = new OU.a(17);
        return;
    }

    public FundingResultPageExtraCtaEntity(String p1, String p2)
    {
        this.url = p1;
        this.title = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity copy$default(vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.url;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.title;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.url;
    }

    public final String component2()
    {
        return this.title;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity) p5).url)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.result.FundingResultPageExtraCtaEntity) p5).title)) {
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

    public final String getTitle()
    {
        return this.title;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return (this.title.hashCode() + (this.url.hashCode() * 31));
    }

    public String toString()
    {
        return androidx.camera.camera2.internal.G.f("FundingResultPageExtraCtaEntity(url=", this.url, ", title=", this.title, ")");
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.url);
        p1.writeString(this.title);
        return;
    }
}
