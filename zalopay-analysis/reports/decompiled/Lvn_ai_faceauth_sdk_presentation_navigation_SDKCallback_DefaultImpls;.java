package vn.ai.faceauth.sdk.presentation.navigation;
public final class SDKCallback$DefaultImpls {

    public static synthetic void complete$default(vn.ai.faceauth.sdk.presentation.navigation.SDKCallback p1, int p2, String p3, String p4, String p5, int p6, Object p7)
    {
        if (p7 != 0) {
            throw new UnsupportedOperationException(jipm.njcx.zbjt(43));
        } else {
            if ((p6 & 1) != 0) {
                p2 = 0;
            }
            if ((p6 & 2) != 0) {
                p3 = "";
            }
            if ((p6 & 4) != 0) {
                p4 = "";
            }
            if ((p6 & 8) != 0) {
                p5 = "";
            }
            p1.complete(p2, p3, p4, p5);
            return;
        }
    }
}
