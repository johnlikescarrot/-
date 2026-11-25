package vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup;
public final class RoutingTopupEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String deeplink;
    private final vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity mainCta;
    private final vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity subCta;

    static RoutingTopupEntity()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity.CREATOR = new OU.a(18);
        return;
    }

    public RoutingTopupEntity(String p1, vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity p2, vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity p3)
    {
        this.deeplink = p1;
        this.mainCta = p2;
        this.subCta = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity copy$default(vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity p0, String p1, vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity p2, vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.deeplink;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.mainCta;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.subCta;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.deeplink;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity component2()
    {
        return this.mainCta;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity component3()
    {
        return this.subCta;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity copy(String p2, vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity p3, vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity p4)
    {
        return new vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.deeplink, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity) p5).deeplink)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.mainCta, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity) p5).mainCta)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.subCta, ((vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity) p5).subCta)) {
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

    public final String getDeeplink()
    {
        return this.deeplink;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity getMainCta()
    {
        return this.mainCta;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity getSubCta()
    {
        return this.subCta;
    }

    public int hashCode()
    {
        vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity v1_2;
        int v0_5 = (this.deeplink.hashCode() * 31);
        vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity v1_1 = this.mainCta;
        int v2 = 0;
        if (v1_1 != null) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        int v0_2 = ((v0_5 + v1_2) * 31);
        vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity v1_0 = this.subCta;
        if (v1_0 != null) {
            v2 = v1_0.hashCode();
        }
        return (v0_2 + v2);
    }

    public final boolean isValidMiddlePageEntity()
    {
        if (this.mainCta == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString()
    {
        String v0_0 = this.deeplink;
        vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity v1 = this.mainCta;
        vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity v2 = this.subCta;
        StringBuilder v3_1 = new StringBuilder("RoutingTopupEntity(deeplink=");
        v3_1.append(v0_0);
        v3_1.append(", mainCta=");
        v3_1.append(v1);
        v3_1.append(", subCta=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        p4.writeString(this.deeplink);
        vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity v0_1 = this.mainCta;
        if (v0_1 != null) {
            p4.writeInt(1);
            v0_1.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        vn.com.vng.zalopay.domain.topupwithdraw.entity.FundingSolutionCtaEntity v0_2 = this.subCta;
        if (v0_2 != null) {
            p4.writeInt(1);
            v0_2.writeToParcel(p4, p5);
            return;
        } else {
            p4.writeInt(0);
            return;
        }
    }
}
