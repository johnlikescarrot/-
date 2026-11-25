package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingFieldModel$Term extends uo.c {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity entity;
    private final int id;
    private final int order;

    static BankBindingFieldModel$Term()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term.CREATOR = new no.e(27);
        return;
    }

    public BankBindingFieldModel$Term(int p2, int p3, vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity p4)
    {
        super(p2, p3, "");
        super.id = p2;
        super.order = p3;
        super.entity = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term p0, int p1, int p2, vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.order;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.entity;
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity component3()
    {
        return this.entity;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term copy(int p2, int p3, vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity p4)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term)) {
                if (this.id == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term) p5).id) {
                    if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term) p5).order) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.entity, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term) p5).entity)) {
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity getEntity()
    {
        return this.entity;
    }

    public int getId()
    {
        return this.id;
    }

    public int getOrder()
    {
        return this.order;
    }

    public int hashCode()
    {
        return ((((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.order)) * 31) + this.entity.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        int v1 = this.order;
        vn.com.vng.zalopay.bankbinding.domain.entity.link.TermEntity v2 = this.entity;
        StringBuilder v3_1 = new StringBuilder("Term(id=");
        v3_1.append(v0_0);
        v3_1.append(", order=");
        v3_1.append(v1);
        v3_1.append(", entity=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeInt(this.id);
        p2.writeInt(this.order);
        this.entity.writeToParcel(p2, p3);
        return;
    }
}
