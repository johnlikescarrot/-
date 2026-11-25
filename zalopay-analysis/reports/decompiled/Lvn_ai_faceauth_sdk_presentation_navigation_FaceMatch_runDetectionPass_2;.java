package vn.ai.faceauth.sdk.presentation.navigation;
public final class FaceMatch$runDetectionPass$2 implements vn.ai.faceauth.sdk.b {
    final synthetic int $padX;
    final synthetic int $padY;
    final synthetic android.graphics.Bitmap $paddedBitmap;
    final synthetic android.graphics.Bitmap $root;

    static FaceMatch$runDetectionPass$2()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(17);
        return;
    }

    public FaceMatch$runDetectionPass$2(int p1, int p2, android.graphics.Bitmap p3, android.graphics.Bitmap p4)
    {
        this.$padX = p1;
        this.$padY = p2;
        this.$root = p3;
        this.$paddedBitmap = p4;
        return;
    }

    public native void processBitmap(com.google.mlkit.vision.face.Face p0, java.util.ArrayList p1);
}
