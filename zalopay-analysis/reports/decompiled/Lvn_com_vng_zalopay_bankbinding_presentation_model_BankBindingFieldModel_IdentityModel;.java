package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingFieldModel$IdentityModel extends uo.c {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int id;
    private final java.util.List options;
    private final int order;

    static BankBindingFieldModel$IdentityModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel.CREATOR = new no.e(25);
        return;
    }

    public BankBindingFieldModel$IdentityModel(int p2, int p3, java.util.List p4)
    {
        super(p2, p3, "");
        super.id = p2;
        super.order = p3;
        super.options = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel p0, int p1, int p2, java.util.List p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.order;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.options;
        }
        return p0.copy(p1, p2, p3);
    }

    public final int component1()
    {
        return this.id;
    }

    public final int component2()
    {
        return this.order;
    }

    public final java.util.List component3()
    {
        return this.options;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel copy(int p2, int p3, java.util.List p4)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel)) {
                if (this.id == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel) p5).id) {
                    if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel) p5).order) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.options, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel) p5).options)) {
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
            return 1;
        }
    }

    public int getId()
    {
        return this.id;
    }

    public final java.util.List getOptions()
    {
        return this.options;
    }

    public int getOrder()
    {
        return this.order;
    }

    public int hashCode()
    {
        return ((((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.order)) * 31) + this.options.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        int v1 = this.order;
        java.util.List v2 = this.options;
        StringBuilder v3_1 = new StringBuilder("IdentityModel(id=");
        v3_1.append(v0_0);
        v3_1.append(", order=");
        v3_1.append(v1);
        v3_1.append(", options=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.id);
        p3.writeInt(this.order);
        java.util.Iterator v0_2 = this.options;
        p3.writeInt(v0_2.size());
        java.util.Iterator v0_3 = v0_2.iterator();
        while (v0_3.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity) v0_3.next()).writeToParcel(p3, p4);
        }
        return;
    }
}
