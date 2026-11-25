package vn.com.vng.zalopay.domain.topupwithdraw.entity;
public final class TopUpWithdrawMiniAppEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity cta;
    private final String logo;
    private final String name;

    static TopUpWithdrawMiniAppEntity()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity.CREATOR = new OU.a(14);
        return;
    }

    public TopUpWithdrawMiniAppEntity(String p1, String p2, vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity p3)
    {
        this.name = p1;
        this.logo = p2;
        this.cta = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity copy$default(vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity p0, String p1, String p2, vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.name;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.logo;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.cta;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.name;
    }

    public final String component2()
    {
        return this.logo;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity component3()
    {
        return this.cta;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity copy(String p2, String p3, vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity p4)
    {
        return new vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity) p5).name)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.logo, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity) p5).logo)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.cta, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawMiniAppEntity) p5).cta)) {
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
            return 1;
        }
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity getCta()
    {
        return this.cta;
    }

    public final String getLogo()
    {
        return this.logo;
    }

    public final String getName()
    {
        return this.name;
    }

    public int hashCode()
    {
        int v1_0;
        int v0_4 = e5.i.c((this.name.hashCode() * 31), 31, this.logo);
        int v1_2 = this.cta;
        if (v1_2 != 0) {
            v1_0 = v1_2.hashCode();
        } else {
            v1_0 = 0;
        }
        return (v0_4 + v1_0);
    }

    public String toString()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity v2 = this.cta;
        String v0_2 = androidx.camera.camera2.internal.G.i("TopUpWithdrawMiniAppEntity(name=", this.name, ", logo=", this.logo, ", cta=");
        v0_2.append(v2);
        v0_2.append(")");
        return v0_2.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.name);
        p3.writeString(this.logo);
        vn.com.vng.zalopay.domain.topupwithdraw.entity.TopUpWithdrawCTAEntity v0_2 = this.cta;
        if (v0_2 != null) {
            p3.writeInt(1);
            v0_2.writeToParcel(p3, p4);
            return;
        } else {
            p3.writeInt(0);
            return;
        }
    }
}
