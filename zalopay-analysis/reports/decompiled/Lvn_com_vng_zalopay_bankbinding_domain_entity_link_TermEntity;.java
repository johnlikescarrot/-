package vn.com.vng.zalopay.bankbinding.domain.entity.link;
public final class TermEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final boolean autoCheck;
    private final String message;
    private final int order;
    private final boolean required;
    private final String submitKey;

    static TermEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity.CREATOR = new Zn.a(0);
        return;
    }

    public TermEntity(int p1, String p2, boolean p3, String p4, boolean p5)
    {
        this.order = p1;
        this.message = p2;
        this.autoCheck = p3;
        this.submitKey = p4;
        this.required = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity p0, int p1, String p2, boolean p3, String p4, boolean p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.order;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.message;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.autoCheck;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.submitKey;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.required;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final int component1()
    {
        return this.order;
    }

    public final String component2()
    {
        return this.message;
    }

    public final boolean component3()
    {
        return this.autoCheck;
    }

    public final String component4()
    {
        return this.submitKey;
    }

    public final boolean component5()
    {
        return this.required;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity copy(int p7, String p8, boolean p9, String p10, boolean p11)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity)) {
                if (this.order == ((vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity) p5).order) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity) p5).message)) {
                        if (this.autoCheck == ((vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity) p5).autoCheck) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.submitKey, ((vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity) p5).submitKey)) {
                                if (this.required == ((vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity) p5).required) {
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

    public final boolean getAutoCheck()
    {
        return this.autoCheck;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final int getOrder()
    {
        return this.order;
    }

    public final boolean getRequired()
    {
        return this.required;
    }

    public final String getSubmitKey()
    {
        return this.submitKey;
    }

    public int hashCode()
    {
        return ((((((((Integer.hashCode(this.order) * 31) + this.message.hashCode()) * 31) + Boolean.hashCode(this.autoCheck)) * 31) + this.submitKey.hashCode()) * 31) + Boolean.hashCode(this.required));
    }

    public String toString()
    {
        String v0_0 = this.order;
        String v1 = this.message;
        boolean v2 = this.autoCheck;
        String v3 = this.submitKey;
        boolean v4 = this.required;
        StringBuilder v5_1 = new StringBuilder("TermEntity(order=");
        v5_1.append(v0_0);
        v5_1.append(", message=");
        v5_1.append(v1);
        v5_1.append(", autoCheck=");
        v5_1.append(v2);
        v5_1.append(", submitKey=");
        v5_1.append(v3);
        v5_1.append(", required=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.order);
        p1.writeString(this.message);
        p1.writeInt(this.autoCheck);
        p1.writeString(this.submitKey);
        p1.writeInt(this.required);
        return;
    }
}
