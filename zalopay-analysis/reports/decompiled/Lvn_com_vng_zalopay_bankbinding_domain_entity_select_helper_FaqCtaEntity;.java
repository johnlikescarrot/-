package vn.com.vng.zalopay.bankbinding.domain.entity.select.helper;
public final class FaqCtaEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String action;
    private final String redirectUrl;

    static FaqCtaEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity.CREATOR = new Zn.a(8);
        return;
    }

    public FaqCtaEntity(String p1, String p2)
    {
        this.action = p1;
        this.redirectUrl = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.action;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.redirectUrl;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.action;
    }

    public final String component2()
    {
        return this.redirectUrl;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.action, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity) p5).action)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.redirectUrl, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqCtaEntity) p5).redirectUrl)) {
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

    public final String getAction()
    {
        return this.action;
    }

    public final String getRedirectUrl()
    {
        return this.redirectUrl;
    }

    public int hashCode()
    {
        return ((this.action.hashCode() * 31) + this.redirectUrl.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.action;
        String v1 = this.redirectUrl;
        StringBuilder v2_1 = new StringBuilder("FaqCtaEntity(action=");
        v2_1.append(v0_0);
        v2_1.append(", redirectUrl=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.action);
        p1.writeString(this.redirectUrl);
        return;
    }
}
