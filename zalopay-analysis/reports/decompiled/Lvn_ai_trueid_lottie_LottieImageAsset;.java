package vn.ai.trueid.lottie;
public class LottieImageAsset {
    private android.graphics.Bitmap bitmap;
    private final String dirName;
    private final String fileName;
    private final int height;
    private final String id;
    private final int width;

    public LottieImageAsset(int p1, int p2, String p3, String p4, String p5)
    {
        this.width = p1;
        this.height = p2;
        this.id = p3;
        this.fileName = p4;
        this.dirName = p5;
        return;
    }

    public vn.ai.trueid.lottie.LottieImageAsset copyWithScale(float p7)
    {
        vn.ai.trueid.lottie.LottieImageAsset v0_1 = new vn.ai.trueid.lottie.LottieImageAsset(((int) (((float) this.width) * p7)), ((int) (((float) this.height) * p7)), this.id, this.fileName, this.dirName);
        android.graphics.Bitmap v7_1 = this.bitmap;
        if (v7_1 != null) {
            v0_1.setBitmap(android.graphics.Bitmap.createScaledBitmap(v7_1, v0_1.width, v0_1.height, 1));
        }
        return v0_1;
    }

    public android.graphics.Bitmap getBitmap()
    {
        return this.bitmap;
    }

    public String getDirName()
    {
        return this.dirName;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public int getHeight()
    {
        return this.height;
    }

    public String getId()
    {
        return this.id;
    }

    public int getWidth()
    {
        return this.width;
    }

    public boolean hasBitmap()
    {
        if ((this.bitmap == null) && ((!this.fileName.startsWith("data:")) || (this.fileName.indexOf("base64,") <= 0))) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setBitmap(android.graphics.Bitmap p1)
    {
        this.bitmap = p1;
        return;
    }
}
