package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankMediaValueEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String action;
    private final String content;
    private final String externalResource;
    private final String viewMode;

    static BankMediaValueEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity.CREATOR = new Io.S(13);
        return;
    }

    public BankMediaValueEntity(String p1, String p2, String p3, String p4)
    {
        this.content = p1;
        this.action = p2;
        this.externalResource = p3;
        this.viewMode = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity p0, String p1, String p2, String p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.content;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.action;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.externalResource;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.viewMode;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.content;
    }

    public final String component2()
    {
        return this.action;
    }

    public final String component3()
    {
        return this.externalResource;
    }

    public final String component4()
    {
        return this.viewMode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity copy(String p2, String p3, String p4, String p5)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.content, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) p5).content)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.action, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) p5).action)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.externalResource, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) p5).externalResource)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.viewMode, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) p5).viewMode)) {
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

    public final String getContent()
    {
        return this.content;
    }

    public final String getExternalResource()
    {
        return this.externalResource;
    }

    public final String getViewMode()
    {
        return this.viewMode;
    }

    public int hashCode()
    {
        return ((((((this.content.hashCode() * 31) + this.action.hashCode()) * 31) + this.externalResource.hashCode()) * 31) + this.viewMode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.content;
        String v1 = this.action;
        String v2 = this.externalResource;
        String v3 = this.viewMode;
        StringBuilder v4_1 = new StringBuilder("BankMediaValueEntity(content=");
        v4_1.append(v0_0);
        v4_1.append(", action=");
        v4_1.append(v1);
        v4_1.append(", externalResource=");
        v4_1.append(v2);
        v4_1.append(", viewMode=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.content);
        p1.writeString(this.action);
        p1.writeString(this.externalResource);
        p1.writeString(this.viewMode);
        return;
    }
}
