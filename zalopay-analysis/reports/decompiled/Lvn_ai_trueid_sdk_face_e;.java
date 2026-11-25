package vn.ai.trueid.sdk.face;
public final class e implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    public final vn.ai.trueid.sdk.face.i1 a;
    public final java.util.ArrayList b;

    public static e()
    {
        vn.ai.trueid.sdk.face.e.CREATOR = new vn.ai.trueid.sdk.face.d();
        return;
    }

    public e(vn.ai.trueid.sdk.face.i1 p1, java.util.ArrayList p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.face.e)) {
                if (this.a == ((vn.ai.trueid.sdk.face.e) p5).a) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.ai.trueid.sdk.face.e) p5).b)) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (this.b.hashCode() + (this.a.hashCode() * 31));
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("CameraSettings(storageType=");
        v0_2.append(this.a);
        v0_2.append(", livenessStepList=");
        v0_2.append(this.b);
        v0_2.append(41);
        return v0_2.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.a.name());
        java.util.Iterator v0_2 = this.b;
        p3.writeInt(v0_2.size());
        java.util.Iterator v0_3 = v0_2.iterator();
        while (v0_3.hasNext()) {
            ((vn.ai.trueid.sdk.face.h1) v0_3.next()).writeToParcel(p3, p4);
        }
        return;
    }
}
