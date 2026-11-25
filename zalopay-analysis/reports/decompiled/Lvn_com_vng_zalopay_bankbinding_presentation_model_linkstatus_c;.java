package vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus;
public final class c implements android.os.Parcelable$Creator {

    public final Object createFromParcel(android.os.Parcel p9)
    {
        String v1 = p9.readString();
        int v2 = p9.readInt();
        String v3 = p9.readString();
        vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel v0_2 = p9.readInt();
        java.util.ArrayList v4_1 = new java.util.ArrayList(v0_2);
        String v5_0 = 0;
        while (v5_0 != v0_2) {
            v4_1.add(vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity.CREATOR.createFromParcel(p9));
            v5_0++;
        }
        return new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel(v1, v2, v3, v4_1, p9.readString(), p9.readInt(), p9.readString());
    }

    public final Object[] newArray(int p1)
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel[] v1_1 = new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel[p1];
        return v1_1;
    }
}
