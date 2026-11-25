package vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus;
public final class b implements android.os.Parcelable$Creator {

    public final Object createFromParcel(android.os.Parcel p2)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError(p2.readString());
    }

    public final Object[] newArray(int p1)
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError[] v1_1 = new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError[p1];
        return v1_1;
    }
}
