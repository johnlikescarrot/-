package vn.com.vng.zalopay.skewface.presentation.args;
public final class UploadPhotoFragmentArgument implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final float frontFaceLimitFileSizeMb;
    private final String frontFacePhotoUri;
    private final float skewFaceLimitFileSizeMb;
    private final String skewFacePhotoUri;

    static UploadPhotoFragmentArgument()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument, 16987, 16987);
        vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument.CREATOR = new y9.z(25);
        return;
    }

    public UploadPhotoFragmentArgument(String p1, String p2, float p3, float p4)
    {
        this.frontFacePhotoUri = p1;
        this.skewFacePhotoUri = p2;
        this.frontFaceLimitFileSizeMb = p3;
        this.skewFaceLimitFileSizeMb = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument copy$default(vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument p0, String p1, String p2, float p3, float p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.frontFacePhotoUri;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.skewFacePhotoUri;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.frontFaceLimitFileSizeMb;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.skewFaceLimitFileSizeMb;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.frontFacePhotoUri;
    }

    public final String component2()
    {
        return this.skewFacePhotoUri;
    }

    public final float component3()
    {
        return this.frontFaceLimitFileSizeMb;
    }

    public final float component4()
    {
        return this.skewFaceLimitFileSizeMb;
    }

    public final vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument copy(String p2, String p3, float p4, float p5)
    {
        return new vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.frontFacePhotoUri, ((vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument) p5).frontFacePhotoUri)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.skewFacePhotoUri, ((vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument) p5).skewFacePhotoUri)) {
                        if (Float.compare(this.frontFaceLimitFileSizeMb, ((vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument) p5).frontFaceLimitFileSizeMb) == 0) {
                            if (Float.compare(this.skewFaceLimitFileSizeMb, ((vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument) p5).skewFaceLimitFileSizeMb) == 0) {
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

    public final float getFrontFaceLimitFileSizeMb()
    {
        return this.frontFaceLimitFileSizeMb;
    }

    public final String getFrontFacePhotoUri()
    {
        return this.frontFacePhotoUri;
    }

    public final float getSkewFaceLimitFileSizeMb()
    {
        return this.skewFaceLimitFileSizeMb;
    }

    public final String getSkewFacePhotoUri()
    {
        return this.skewFacePhotoUri;
    }

    public int hashCode()
    {
        return (Float.hashCode(this.skewFaceLimitFileSizeMb) + Kb.e.d(this.frontFaceLimitFileSizeMb, e5.i.c((this.frontFacePhotoUri.hashCode() * 31), 31, this.skewFacePhotoUri), 31));
    }

    public String toString()
    {
        float v2 = this.frontFaceLimitFileSizeMb;
        float v3 = this.skewFaceLimitFileSizeMb;
        String v0_2 = androidx.camera.camera2.internal.G.i("UploadPhotoFragmentArgument(frontFacePhotoUri=", this.frontFacePhotoUri, ", skewFacePhotoUri=", this.skewFacePhotoUri, ", frontFaceLimitFileSizeMb=");
        v0_2.append(v2);
        v0_2.append(", skewFaceLimitFileSizeMb=");
        v0_2.append(v3);
        v0_2.append(")");
        return v0_2.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.frontFacePhotoUri);
        p1.writeString(this.skewFacePhotoUri);
        p1.writeFloat(this.frontFaceLimitFileSizeMb);
        p1.writeFloat(this.skewFaceLimitFileSizeMb);
        return;
    }
}
