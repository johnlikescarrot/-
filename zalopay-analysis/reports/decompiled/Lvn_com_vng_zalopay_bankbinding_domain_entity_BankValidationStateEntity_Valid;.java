package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankValidationStateEntity$Valid extends Wn.k {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Valid INSTANCE;

    static BankValidationStateEntity$Valid()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Valid.INSTANCE = new vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Valid();
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Valid.CREATOR = new Io.S(19);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Valid.$stable = 8;
        return;
    }

    private BankValidationStateEntity$Valid()
    {
        return;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(1);
        return;
    }
}
