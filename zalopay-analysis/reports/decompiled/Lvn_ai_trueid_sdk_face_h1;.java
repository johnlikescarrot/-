package vn.ai.trueid.sdk.face;
public final enum class h1 extends java.lang.Enum implements android.os.Parcelable {
    private static final synthetic vn.ai.trueid.sdk.face.h1[] $VALUES;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final enum vn.ai.trueid.sdk.face.h1 STEP_FIRST_CHECK;
    public static final enum vn.ai.trueid.sdk.face.h1 STEP_ZOOM_IN;
    public static final enum vn.ai.trueid.sdk.face.h1 STEP_ZOOM_OUT;
    private final String textValue;

    private static final synthetic vn.ai.trueid.sdk.face.h1[] $values()
    {
        return new vn.ai.trueid.sdk.face.h1[] {vn.ai.trueid.sdk.face.h1.STEP_FIRST_CHECK, vn.ai.trueid.sdk.face.h1.STEP_ZOOM_IN, vn.ai.trueid.sdk.face.h1.STEP_ZOOM_OUT});
    }

    public static h1()
    {
        vn.ai.trueid.sdk.face.h1.STEP_FIRST_CHECK = new vn.ai.trueid.sdk.face.h1(wmoi.igob.ggcn(12827), 0, wmoi.igob.ggcn(12826));
        vn.ai.trueid.sdk.face.h1.STEP_ZOOM_IN = new vn.ai.trueid.sdk.face.h1(wmoi.igob.ggcn(12829), 1, wmoi.igob.ggcn(12828));
        vn.ai.trueid.sdk.face.h1.STEP_ZOOM_OUT = new vn.ai.trueid.sdk.face.h1(wmoi.igob.ggcn(12831), 2, wmoi.igob.ggcn(12830));
        vn.ai.trueid.sdk.face.h1.$VALUES = vn.ai.trueid.sdk.face.h1.$values();
        vn.ai.trueid.sdk.face.h1.CREATOR = new vn.ai.trueid.sdk.face.g1();
        return;
    }

    private h1(String p1, int p2, String p3)
    {
        super(p1, p2);
        super.textValue = p3;
        return;
    }

    public static vn.ai.trueid.sdk.face.h1 valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.face.h1) Enum.valueOf(vn.ai.trueid.sdk.face.h1, p1));
    }

    public static vn.ai.trueid.sdk.face.h1[] values()
    {
        return ((vn.ai.trueid.sdk.face.h1[]) vn.ai.trueid.sdk.face.h1.$VALUES.clone());
    }

    public int describeContents()
    {
        return 0;
    }

    public final String getTextValue()
    {
        return this.textValue;
    }

    public void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.name());
        return;
    }
}
