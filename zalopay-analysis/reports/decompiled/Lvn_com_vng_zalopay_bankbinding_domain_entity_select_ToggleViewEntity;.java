package vn.com.vng.zalopay.bankbinding.domain.entity.select;
public final class ToggleViewEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final boolean defaultEnable;
    private final String infoUrl;
    private final String message;
    private final boolean required;
    private final String submitKey;
    private final String title;

    static ToggleViewEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity.CREATOR = new Zn.a(7);
        return;
    }

    public ToggleViewEntity(String p1, String p2, String p3, boolean p4, boolean p5, String p6)
    {
        this.infoUrl = p1;
        this.title = p2;
        this.message = p3;
        this.defaultEnable = p4;
        this.required = p5;
        this.submitKey = p6;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity p0, String p1, String p2, String p3, boolean p4, boolean p5, String p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.infoUrl;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.title;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.message;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.defaultEnable;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.required;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.submitKey;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final String component1()
    {
        return this.infoUrl;
    }

    public final String component2()
    {
        return this.title;
    }

    public final String component3()
    {
        return this.message;
    }

    public final boolean component4()
    {
        return this.defaultEnable;
    }

    public final boolean component5()
    {
        return this.required;
    }

    public final String component6()
    {
        return this.submitKey;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity copy(String p8, String p9, String p10, boolean p11, boolean p12, String p13)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.infoUrl, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) p5).infoUrl)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) p5).title)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) p5).message)) {
                            if (this.defaultEnable == ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) p5).defaultEnable) {
                                if (this.required == ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) p5).required) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.submitKey, ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) p5).submitKey)) {
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
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final boolean getDefaultEnable()
    {
        return this.defaultEnable;
    }

    public final String getInfoUrl()
    {
        return this.infoUrl;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final boolean getRequired()
    {
        return this.required;
    }

    public final String getSubmitKey()
    {
        return this.submitKey;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        return ((((((((((this.infoUrl.hashCode() * 31) + this.title.hashCode()) * 31) + this.message.hashCode()) * 31) + Boolean.hashCode(this.defaultEnable)) * 31) + Boolean.hashCode(this.required)) * 31) + this.submitKey.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.infoUrl;
        String v1 = this.title;
        String v2 = this.message;
        boolean v3 = this.defaultEnable;
        boolean v4 = this.required;
        String v5 = this.submitKey;
        StringBuilder v6_1 = new StringBuilder("ToggleViewEntity(infoUrl=");
        v6_1.append(v0_0);
        v6_1.append(", title=");
        v6_1.append(v1);
        v6_1.append(", message=");
        v6_1.append(v2);
        v6_1.append(", defaultEnable=");
        v6_1.append(v3);
        v6_1.append(", required=");
        v6_1.append(v4);
        v6_1.append(", submitKey=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.infoUrl);
        p1.writeString(this.title);
        p1.writeString(this.message);
        p1.writeInt(this.defaultEnable);
        p1.writeInt(this.required);
        p1.writeString(this.submitKey);
        return;
    }
}
