package vn.ai.trueid.sdk.face;
public final class d implements android.os.Parcelable$Creator {

    public d()
    {
        return;
    }

    public final Object createFromParcel(android.os.Parcel p6)
    {
        vn.ai.trueid.sdk.face.i1 v0_1 = vn.ai.trueid.sdk.face.i1.valueOf(p6.readString());
        int v1 = p6.readInt();
        java.util.ArrayList v2_1 = new java.util.ArrayList(v1);
        int v3 = 0;
        while (v3 != v1) {
            v2_1.add(vn.ai.trueid.sdk.face.h1.CREATOR.createFromParcel(p6));
            v3++;
        }
        return new vn.ai.trueid.sdk.face.e(v0_1, v2_1);
    }

    public final Object[] newArray(int p1)
    {
        vn.ai.trueid.sdk.face.e[] v1_1 = new vn.ai.trueid.sdk.face.e[p1];
        return v1_1;
    }
}
