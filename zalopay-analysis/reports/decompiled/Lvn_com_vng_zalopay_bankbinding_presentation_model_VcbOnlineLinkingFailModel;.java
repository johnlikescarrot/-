package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class VcbOnlineLinkingFailModel implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int linkingType;
    private final String message;

    static VcbOnlineLinkingFailModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel.CREATOR = new uo.L(13);
        return;
    }

    public VcbOnlineLinkingFailModel(int p1, String p2)
    {
        this.linkingType = p1;
        this.message = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel p0, int p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.linkingType;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.message;
        }
        return p0.copy(p1, p2);
    }

    public final int component1()
    {
        return this.linkingType;
    }

    public final String component2()
    {
        return this.message;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel copy(int p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel)) {
                if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel) p5).linkingType) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.VcbOnlineLinkingFailModel) p5).message)) {
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

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public int hashCode()
    {
        return ((Integer.hashCode(this.linkingType) * 31) + this.message.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.linkingType;
        String v1 = this.message;
        StringBuilder v2_1 = new StringBuilder("VcbOnlineLinkingFailModel(linkingType=");
        v2_1.append(v0_0);
        v2_1.append(", message=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.linkingType);
        p1.writeString(this.message);
        return;
    }
}
