package vn.com.zalopay.nfc_sdk.nfc_in_house.proto.iso7816;
public final class ICCError extends java.lang.Exception {
    public final gX.l a;

    public ICCError(String p1, gX.l p2)
    {
        super(p1);
        super.a = p2;
        return;
    }

    public final String toString()
    {
        String v0_0 = this.getMessage();
        StringBuilder v1_1 = new StringBuilder("ICC Error: ");
        v1_1.append(v0_0);
        v1_1.append(" ");
        v1_1.append(this.a);
        return v1_1.toString();
    }
}
