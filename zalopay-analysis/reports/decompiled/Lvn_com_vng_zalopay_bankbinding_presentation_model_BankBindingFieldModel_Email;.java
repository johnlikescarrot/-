package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingFieldModel$Email extends uo.c {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final uo.b Companion;
    private final int id;
    private final int order;
    private final Wn.k validationState;
    private final java.util.List validators;
    private final String value;

    static BankBindingFieldModel$Email()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email.Companion = new uo.b();
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email.CREATOR = new no.e(22);
        return;
    }

    public BankBindingFieldModel$Email(int p1, int p2, java.util.List p3, Wn.k p4, String p5)
    {
        super(p1, p2, p5);
        super.id = p1;
        super.order = p2;
        super.validators = p3;
        super.validationState = p4;
        super.value = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email p0, int p1, int p2, java.util.List p3, Wn.k p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.order;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.validators;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.validationState;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.value;
        }
        return p0.copy(p1, p2, p3, p4, p5);
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
        return this.validators;
    }

    public final Wn.k component4()
    {
        return this.validationState;
    }

    public final String component5()
    {
        return this.value;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email copy(int p7, int p8, java.util.List p9, Wn.k p10, String p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email)) {
                if (this.id == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email) p5).id) {
                    if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email) p5).order) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email) p5).validators)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.validationState, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email) p5).validationState)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.value, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email) p5).value)) {
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

    public int getId()
    {
        return this.id;
    }

    public int getOrder()
    {
        return this.order;
    }

    public final Wn.k getValidationState()
    {
        return this.validationState;
    }

    public final java.util.List getValidators()
    {
        return this.validators;
    }

    public String getValue()
    {
        return this.value;
    }

    public int hashCode()
    {
        int v1_2;
        int v0_2 = (((((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.order)) * 31) + this.validators.hashCode()) * 31);
        int v1_1 = this.validationState;
        if (v1_1 != 0) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        return (((v0_2 + v1_2) * 31) + this.value.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        int v1 = this.order;
        java.util.List v2 = this.validators;
        Wn.k v3 = this.validationState;
        String v4 = this.value;
        StringBuilder v5_1 = new StringBuilder("Email(id=");
        v5_1.append(v0_0);
        v5_1.append(", order=");
        v5_1.append(v1);
        v5_1.append(", validators=");
        v5_1.append(v2);
        v5_1.append(", validationState=");
        v5_1.append(v3);
        v5_1.append(", value=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.id);
        p3.writeInt(this.order);
        Wn.k v0_3 = this.validators;
        p3.writeInt(v0_3.size());
        Wn.k v0_4 = v0_3.iterator();
        while (v0_4.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) v0_4.next()).writeToParcel(p3, p4);
        }
        p3.writeParcelable(this.validationState, p4);
        p3.writeString(this.value);
        return;
    }
}
