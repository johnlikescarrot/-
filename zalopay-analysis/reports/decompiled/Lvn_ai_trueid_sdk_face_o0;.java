package vn.ai.trueid.sdk.face;
public final class o0 implements android.os.Parcelable$Creator {

    public o0()
    {
        return;
    }

    public final Object createFromParcel(android.os.Parcel p5)
    {
        return new vn.ai.trueid.sdk.face.p0(p5.readFloat(), p5.readFloat(), p5.readFloat(), p5.readFloat());
    }

    public final Object[] newArray(int p1)
    {
        vn.ai.trueid.sdk.face.p0[] v1_1 = new vn.ai.trueid.sdk.face.p0[p1];
        return v1_1;
    }
}
