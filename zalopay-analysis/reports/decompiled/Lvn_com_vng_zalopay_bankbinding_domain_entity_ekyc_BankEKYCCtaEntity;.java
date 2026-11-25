package vn.com.vng.zalopay.bankbinding.domain.entity.ekyc;
public final class BankEKYCCtaEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String action;
    private final String subTitle;
    private final String title;
    private final String url;

    static BankEKYCCtaEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity.CREATOR = new Io.S(27);
        return;
    }

    public BankEKYCCtaEntity(String p1, String p2, String p3, String p4)
    {
        this.action = p1;
        this.title = p2;
        this.url = p3;
        this.subTitle = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity p0, String p1, String p2, String p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.action;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.title;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.url;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.subTitle;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.action;
    }

    public final String component2()
    {
        return this.title;
    }

    public final String component3()
    {
        return this.url;
    }

    public final String component4()
    {
        return this.subTitle;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity copy(String p2, String p3, String p4, String p5)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.action, ((vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity) p5).action)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity) p5).title)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity) p5).url)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.subTitle, ((vn.com.vng.zalopay.bankbinding.domain.entity.ekyc.BankEKYCCtaEntity) p5).subTitle)) {
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

    public final String getAction()
    {
        return this.action;
    }

    public final String getSubTitle()
    {
        return this.subTitle;
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
        return ((((((this.action.hashCode() * 31) + this.title.hashCode()) * 31) + this.url.hashCode()) * 31) + this.subTitle.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.action;
        String v1 = this.title;
        String v2 = this.url;
        String v3 = this.subTitle;
        StringBuilder v4_1 = new StringBuilder("BankEKYCCtaEntity(action=");
        v4_1.append(v0_0);
        v4_1.append(", title=");
        v4_1.append(v1);
        v4_1.append(", url=");
        v4_1.append(v2);
        v4_1.append(", subTitle=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.action);
        p1.writeString(this.title);
        p1.writeString(this.url);
        p1.writeString(this.subTitle);
        return;
    }
}
