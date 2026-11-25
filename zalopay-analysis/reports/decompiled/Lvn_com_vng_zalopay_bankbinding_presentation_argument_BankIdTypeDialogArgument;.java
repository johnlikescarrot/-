package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankIdTypeDialogArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int currentIdType;
    private final java.util.List options;
    private final int parentId;
    private final String requestCode;

    static BankIdTypeDialogArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument.CREATOR = new Zn.a(24);
        return;
    }

    public BankIdTypeDialogArgument(int p1, String p2, java.util.List p3, int p4)
    {
        this.parentId = p1;
        this.requestCode = p2;
        this.options = p3;
        this.currentIdType = p4;
        return;
    }

    public synthetic BankIdTypeDialogArgument(int p1, String p2, java.util.List p3, int p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 8) != 0) {
            p4 = 0;
        }
        this(p1, p2, p3, p4);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument p0, int p1, String p2, java.util.List p3, int p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.parentId;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.requestCode;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.options;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.currentIdType;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final int component1()
    {
        return this.parentId;
    }

    public final String component2()
    {
        return this.requestCode;
    }

    public final java.util.List component3()
    {
        return this.options;
    }

    public final int component4()
    {
        return this.currentIdType;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument copy(int p2, String p3, java.util.List p4, int p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument)) {
                if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument) p5).parentId) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument) p5).requestCode)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.options, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument) p5).options)) {
                            if (this.currentIdType == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument) p5).currentIdType) {
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

    public final int getCurrentIdType()
    {
        return this.currentIdType;
    }

    public final java.util.List getOptions()
    {
        return this.options;
    }

    public final int getParentId()
    {
        return this.parentId;
    }

    public final String getRequestCode()
    {
        return this.requestCode;
    }

    public int hashCode()
    {
        return ((((((Integer.hashCode(this.parentId) * 31) + this.requestCode.hashCode()) * 31) + this.options.hashCode()) * 31) + Integer.hashCode(this.currentIdType));
    }

    public String toString()
    {
        String v0_0 = this.parentId;
        String v1 = this.requestCode;
        java.util.List v2 = this.options;
        int v3 = this.currentIdType;
        StringBuilder v4_1 = new StringBuilder("BankIdTypeDialogArgument(parentId=");
        v4_1.append(v0_0);
        v4_1.append(", requestCode=");
        v4_1.append(v1);
        v4_1.append(", options=");
        v4_1.append(v2);
        v4_1.append(", currentIdType=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.parentId);
        p3.writeString(this.requestCode);
        java.util.Iterator v0_2 = this.options;
        p3.writeInt(v0_2.size());
        java.util.Iterator v0_3 = v0_2.iterator();
        while (v0_3.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) v0_3.next()).writeToParcel(p3, p4);
        }
        p3.writeInt(this.currentIdType);
        return;
    }
}
