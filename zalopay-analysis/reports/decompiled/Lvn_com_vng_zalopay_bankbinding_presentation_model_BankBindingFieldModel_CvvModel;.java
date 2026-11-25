package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingFieldModel$CvvModel extends uo.c {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int id;
    private final int length;
    private final int order;
    private final java.util.List validators;

    static BankBindingFieldModel$CvvModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel.CREATOR = new no.e(21);
        return;
    }

    public BankBindingFieldModel$CvvModel(int p2, int p3, int p4, java.util.List p5)
    {
        super(p2, p4, "");
        super.id = p2;
        super.length = p3;
        super.order = p4;
        super.validators = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel p0, int p1, int p2, int p3, java.util.List p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.length;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.order;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.validators;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final int component1()
    {
        return this.id;
    }

    public final int component2()
    {
        return this.length;
    }

    public final int component3()
    {
        return this.order;
    }

    public final java.util.List component4()
    {
        return this.validators;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel copy(int p2, int p3, int p4, java.util.List p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel)) {
                if (this.id == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel) p5).id) {
                    if (this.length == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel) p5).length) {
                        if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel) p5).order) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel) p5).validators)) {
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

    public int getId()
    {
        return this.id;
    }

    public final int getLength()
    {
        return this.length;
    }

    public int getOrder()
    {
        return this.order;
    }

    public final java.util.List getValidators()
    {
        return this.validators;
    }

    public int hashCode()
    {
        return ((((((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.length)) * 31) + Integer.hashCode(this.order)) * 31) + this.validators.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        int v1 = this.length;
        int v2 = this.order;
        java.util.List v3 = this.validators;
        StringBuilder v4_1 = new StringBuilder("CvvModel(id=");
        v4_1.append(v0_0);
        v4_1.append(", length=");
        v4_1.append(v1);
        v4_1.append(", order=");
        v4_1.append(v2);
        v4_1.append(", validators=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.id);
        p3.writeInt(this.length);
        p3.writeInt(this.order);
        java.util.Iterator v0_4 = this.validators;
        p3.writeInt(v0_4.size());
        java.util.Iterator v0_1 = v0_4.iterator();
        while (v0_1.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) v0_1.next()).writeToParcel(p3, p4);
        }
        return;
    }
}
