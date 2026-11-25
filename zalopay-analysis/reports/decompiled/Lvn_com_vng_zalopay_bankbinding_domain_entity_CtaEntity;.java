package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class CtaEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final Wn.I Companion;
    private static final vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity topup;
    private final String label;
    private final String url;

    static CtaEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity.Companion = new Wn.I();
        vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity.CREATOR = new Io.S(22);
        vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity.topup = new vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity("N\u1ea1p ti\u1ec1n", "zalopay://launch/f/wallettopup");
        return;
    }

    public CtaEntity(String p1, String p2)
    {
        this.label = p1;
        this.url = p2;
        return;
    }

    public static final synthetic vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity access$getTopup$cp()
    {
        return vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity.topup;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.label;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.url;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.label;
    }

    public final String component2()
    {
        return this.url;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.label, ((vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity) p5).label)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity) p5).url)) {
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

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return ((this.label.hashCode() * 31) + this.url.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.label;
        String v1 = this.url;
        StringBuilder v2_1 = new StringBuilder("CtaEntity(label=");
        v2_1.append(v0_0);
        v2_1.append(", url=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.label);
        p1.writeString(this.url);
        return;
    }
}
