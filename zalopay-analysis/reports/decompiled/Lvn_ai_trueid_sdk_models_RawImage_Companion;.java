package vn.ai.trueid.sdk.models;
public final class RawImage$Companion {

    private RawImage$Companion()
    {
        return;
    }

    public synthetic RawImage$Companion(kotlin.jvm.internal.DefaultConstructorMarker p1)
    {
        return;
    }

    public final vn.ai.trueid.sdk.models.RawImage fromBitmaps$trueid_release(android.graphics.Bitmap p3, android.graphics.Bitmap p4, android.graphics.Bitmap p5)
    {
        int v3_1;
        String v0_0 = 0;
        if (p3 == 0) {
            v3_1 = 0;
        } else {
            v3_1 = vn.ai.trueid.sdk.utils.Helpers.Companion.bitmapToBase64(p3);
        }
        int v4_1;
        if (p4 == 0) {
            v4_1 = 0;
        } else {
            v4_1 = vn.ai.trueid.sdk.utils.Helpers.Companion.bitmapToBase64(p4);
        }
        if (p5 != null) {
            v0_0 = vn.ai.trueid.sdk.utils.Helpers.Companion.bitmapToBase64(p5);
        }
        return new vn.ai.trueid.sdk.models.RawImage(v3_1, v4_1, v0_0);
    }
}
