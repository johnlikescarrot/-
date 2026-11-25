package vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus;
public final class a implements android.os.Parcelable$Creator {

    public final Object createFromParcel(android.os.Parcel p9)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel(p9.readString(), p9.readInt(), p9.readString(), p9.readString(), p9.readInt(), p9.readInt(), p9.readString());
    }

    public final Object[] newArray(int p1)
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel[] v1_1 = new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$BottomDialogErrorModel[p1];
        return v1_1;
    }
}
