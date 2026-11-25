package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingFieldModel$IssueDateModel extends uo.c {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int defaultYear;
    private final int endYear;
    private final int id;
    private final boolean isShowGuide;
    private final int order;
    private final int startYear;

    static BankBindingFieldModel$IssueDateModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel.CREATOR = new no.e(26);
        return;
    }

    public BankBindingFieldModel$IssueDateModel(int p2, int p3, int p4, int p5, int p6, boolean p7)
    {
        super(p2, p6, "");
        super.id = p2;
        super.startYear = p3;
        super.endYear = p4;
        super.defaultYear = p5;
        super.order = p6;
        super.isShowGuide = p7;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel p0, int p1, int p2, int p3, int p4, int p5, boolean p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.startYear;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.endYear;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.defaultYear;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.order;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.isShowGuide;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final int component1()
    {
        return this.id;
    }

    public final int component2()
    {
        return this.startYear;
    }

    public final int component3()
    {
        return this.endYear;
    }

    public final int component4()
    {
        return this.defaultYear;
    }

    public final int component5()
    {
        return this.order;
    }

    public final boolean component6()
    {
        return this.isShowGuide;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel copy(int p8, int p9, int p10, int p11, int p12, boolean p13)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel)) {
                if (this.id == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel) p5).id) {
                    if (this.startYear == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel) p5).startYear) {
                        if (this.endYear == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel) p5).endYear) {
                            if (this.defaultYear == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel) p5).defaultYear) {
                                if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel) p5).order) {
                                    if (this.isShowGuide == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel) p5).isShowGuide) {
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

    public final int getDefaultYear()
    {
        return this.defaultYear;
    }

    public final int getEndYear()
    {
        return this.endYear;
    }

    public int getId()
    {
        return this.id;
    }

    public int getOrder()
    {
        return this.order;
    }

    public final int getStartYear()
    {
        return this.startYear;
    }

    public int hashCode()
    {
        return ((((((((((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.startYear)) * 31) + Integer.hashCode(this.endYear)) * 31) + Integer.hashCode(this.defaultYear)) * 31) + Integer.hashCode(this.order)) * 31) + Boolean.hashCode(this.isShowGuide));
    }

    public final boolean isShowGuide()
    {
        return this.isShowGuide;
    }

    public String toString()
    {
        String v0_0 = this.id;
        int v1 = this.startYear;
        int v2 = this.endYear;
        int v3 = this.defaultYear;
        int v4 = this.order;
        boolean v5 = this.isShowGuide;
        StringBuilder v6_1 = new StringBuilder("IssueDateModel(id=");
        v6_1.append(v0_0);
        v6_1.append(", startYear=");
        v6_1.append(v1);
        v6_1.append(", endYear=");
        v6_1.append(v2);
        v6_1.append(", defaultYear=");
        v6_1.append(v3);
        v6_1.append(", order=");
        v6_1.append(v4);
        v6_1.append(", isShowGuide=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.id);
        p1.writeInt(this.startYear);
        p1.writeInt(this.endYear);
        p1.writeInt(this.defaultYear);
        p1.writeInt(this.order);
        p1.writeInt(this.isShowGuide);
        return;
    }
}
