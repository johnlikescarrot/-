package vn.com.vng.zalopay.domain.topupwithdraw.entity;
public final class TopUpWithdrawCTAEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String extraInfo;
    private final String url;

    static TopUpWithdrawCTAEntity()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity.CREATOR = new OU.a(13);
        return;
    }

    public TopUpWithdrawCTAEntity(String p1, String p2)
    {
        this.url = p1;
        this.extraInfo = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity copy$default(vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.url;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.extraInfo;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.url;
    }

    public final String component2()
    {
        return this.extraInfo;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity) p5).url)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.extraInfo, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity) p5).extraInfo)) {
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

    public final String getExtraInfo()
    {
        return this.extraInfo;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return (this.extraInfo.hashCode() + (this.url.hashCode() * 31));
    }

    public String toString()
    {
        return androidx.camera.camera2.internal.G.f(wmoi.igob.ggcn(967), this.url, wmoi.igob.ggcn(965), this.extraInfo, wmoi.igob.ggcn(966));
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.url);
        p1.writeString(this.extraInfo);
        return;
    }
}
