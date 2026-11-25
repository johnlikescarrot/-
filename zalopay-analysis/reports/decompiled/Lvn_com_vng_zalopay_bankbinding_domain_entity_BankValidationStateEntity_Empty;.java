package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankValidationStateEntity$Empty extends Wn.k {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Empty INSTANCE;

    static BankValidationStateEntity$Empty()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Empty.INSTANCE = new vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Empty();
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Empty.CREATOR = new Io.S(17);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidationStateEntity$Empty.$stable = 8;
        return;
    }

    private BankValidationStateEntity$Empty()
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
