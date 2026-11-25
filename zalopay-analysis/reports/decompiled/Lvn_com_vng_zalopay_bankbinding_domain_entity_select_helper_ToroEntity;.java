package vn.com.vng.zalopay.bankbinding.domain.entity.select.helper;
public final class ToroEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final java.util.List faqItems;
    private final String hint;

    static ToroEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity.CREATOR = new Zn.a(12);
        return;
    }

    public ToroEntity(String p1, java.util.List p2)
    {
        this.hint = p1;
        this.faqItems = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity p0, String p1, java.util.List p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.hint;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.faqItems;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.hint;
    }

    public final java.util.List component2()
    {
        return this.faqItems;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity copy(String p2, java.util.List p3)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.hint, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity) p5).hint)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.faqItems, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.ToroEntity) p5).faqItems)) {
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

    public final java.util.List getFaqItems()
    {
        return this.faqItems;
    }

    public final String getHint()
    {
        return this.hint;
    }

    public int hashCode()
    {
        int v0_2;
        int v0_0 = this.hint;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        int v0_3 = (v0_2 * 31);
        java.util.List v2 = this.faqItems;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_3 + v1);
    }

    public String toString()
    {
        String v0_0 = this.hint;
        java.util.List v1 = this.faqItems;
        StringBuilder v2_1 = new StringBuilder("ToroEntity(hint=");
        v2_1.append(v0_0);
        v2_1.append(", faqItems=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.hint);
        java.util.Iterator v0_2 = this.faqItems;
        if (v0_2 != null) {
            p3.writeInt(1);
            p3.writeInt(v0_2.size());
            java.util.Iterator v0_1 = v0_2.iterator();
            while (v0_1.hasNext()) {
                ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity) v0_1.next()).writeToParcel(p3, p4);
            }
            return;
        } else {
            p3.writeInt(0);
            return;
        }
    }
}
