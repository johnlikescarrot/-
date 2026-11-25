package vn.ai.trueid.sdk.face;
public final class p0 implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    public final float a;
    public final float b;
    public final float c;
    public final float d;

    public static p0()
    {
        vn.ai.trueid.sdk.face.p0.CREATOR = new vn.ai.trueid.sdk.face.o0();
        return;
    }

    public synthetic p0()
    {
        this(1045220557, 1050253722, 1061997773, 1053609165);
        return;
    }

    public p0(float p1, float p2, float p3, float p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.face.p0)) {
                if (Float.compare(this.a, ((vn.ai.trueid.sdk.face.p0) p5).a) == 0) {
                    if (Float.compare(this.b, ((vn.ai.trueid.sdk.face.p0) p5).b) == 0) {
                        if (Float.compare(this.c, ((vn.ai.trueid.sdk.face.p0) p5).c) == 0) {
                            if (Float.compare(this.d, ((vn.ai.trueid.sdk.face.p0) p5).d) == 0) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (Float.hashCode(this.d) + Kb.e.d(this.c, Kb.e.d(this.b, (Float.hashCode(this.a) * 31), 31), 31));
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("LivenessConfig(_eyeOpenProbability=");
        v0_2.append(this.a);
        v0_2.append(", _smileProbability=");
        v0_2.append(this.b);
        v0_2.append(", _blurProbability=");
        v0_2.append(this.c);
        v0_2.append(", _luminosityProbability=");
        return Kb.e.p(v0_2, this.d, 41);
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeFloat(this.a);
        p1.writeFloat(this.b);
        p1.writeFloat(this.c);
        p1.writeFloat(this.d);
        return;
    }
}
