package vn.com.vng.zalopay.bankbinding.domain.entity.select.helper;
public final class FaqItemEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final java.util.List autoExpandEntrypoint;
    private final java.util.List contentV2;
    private final java.util.List contents;
    private final String title;
    private final String type;

    static FaqItemEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity.CREATOR = new Zn.a(10);
        return;
    }

    public FaqItemEntity(String p1, String p2, java.util.List p3, java.util.List p4, java.util.List p5)
    {
        this.type = p1;
        this.title = p2;
        this.contents = p3;
        this.contentV2 = p4;
        this.autoExpandEntrypoint = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity p0, String p1, String p2, java.util.List p3, java.util.List p4, java.util.List p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.type;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.title;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.contents;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.contentV2;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.autoExpandEntrypoint;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.type;
    }

    public final String component2()
    {
        return this.title;
    }

    public final java.util.List component3()
    {
        return this.contents;
    }

    public final java.util.List component4()
    {
        return this.contentV2;
    }

    public final java.util.List component5()
    {
        return this.autoExpandEntrypoint;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity copy(String p7, String p8, java.util.List p9, java.util.List p10, java.util.List p11)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.type, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity) p5).type)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity) p5).title)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.contents, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity) p5).contents)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.contentV2, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity) p5).contentV2)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.autoExpandEntrypoint, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemEntity) p5).autoExpandEntrypoint)) {
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
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final java.util.List getAutoExpandEntrypoint()
    {
        return this.autoExpandEntrypoint;
    }

    public final java.util.List getContentV2()
    {
        return this.contentV2;
    }

    public final java.util.List getContents()
    {
        return this.contents;
    }

    public final java.util.List getFaqContents()
    {
        java.util.List v0 = this.contentV2;
        if (((java.util.Collection) v0).isEmpty()) {
            v0 = 0;
        }
        if (v0 == null) {
            v0 = this.contents;
        }
        return v0;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public final String getType()
    {
        return this.type;
    }

    public int hashCode()
    {
        int v1_6;
        int v0_9 = (this.type.hashCode() * 31);
        int v1_5 = this.title;
        int v2 = 0;
        if (v1_5 != 0) {
            v1_6 = v1_5.hashCode();
        } else {
            v1_6 = 0;
        }
        int v0_2 = ((v0_9 + v1_6) * 31);
        int v1_0 = this.contents;
        if (v1_0 != 0) {
            v2 = v1_0.hashCode();
        }
        return (((((v0_2 + v2) * 31) + this.contentV2.hashCode()) * 31) + this.autoExpandEntrypoint.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.type;
        String v1 = this.title;
        java.util.List v2 = this.contents;
        java.util.List v3 = this.contentV2;
        java.util.List v4 = this.autoExpandEntrypoint;
        StringBuilder v5_1 = new StringBuilder("FaqItemEntity(type=");
        v5_1.append(v0_0);
        v5_1.append(", title=");
        v5_1.append(v1);
        v5_1.append(", contents=");
        v5_1.append(v2);
        v5_1.append(", contentV2=");
        v5_1.append(v3);
        v5_1.append(", autoExpandEntrypoint=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.type);
        p3.writeString(this.title);
        java.util.Iterator v0_6 = this.contents;
        if (v0_6 != null) {
            p3.writeInt(1);
            p3.writeInt(v0_6.size());
            java.util.Iterator v0_1 = v0_6.iterator();
            while (v0_1.hasNext()) {
                ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) v0_1.next()).writeToParcel(p3, p4);
            }
        } else {
            p3.writeInt(0);
        }
        java.util.Iterator v0_4 = this.contentV2;
        p3.writeInt(v0_4.size());
        java.util.Iterator v0_5 = v0_4.iterator();
        while (v0_5.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.select.helper.FaqItemContentEntity) v0_5.next()).writeToParcel(p3, p4);
        }
        p3.writeStringList(this.autoExpandEntrypoint);
        return;
    }
}
