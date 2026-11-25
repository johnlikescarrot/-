package vn.ai.trueid.lottie.model;
public class DocumentData {
    public float baselineShift;
    public android.graphics.PointF boxPosition;
    public android.graphics.PointF boxSize;
    public int color;
    public String fontName;
    public vn.ai.trueid.lottie.model.DocumentData$Justification justification;
    public float lineHeight;
    public float size;
    public int strokeColor;
    public boolean strokeOverFill;
    public float strokeWidth;
    public String text;
    public int tracking;

    public DocumentData()
    {
        return;
    }

    public DocumentData(String p1, String p2, float p3, vn.ai.trueid.lottie.model.DocumentData$Justification p4, int p5, float p6, float p7, int p8, int p9, float p10, boolean p11, android.graphics.PointF p12, android.graphics.PointF p13)
    {
        this.set(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13);
        return;
    }

    public int hashCode()
    {
        long v3_0 = ((long) Float.floatToRawIntBits(this.lineHeight));
        return (((((((this.justification.ordinal() + (((int) (((float) e5.i.c((this.text.hashCode() * 31), 31, this.fontName)) + this.size)) * 31)) * int v1) + this.tracking) * 31) + ((int) (v3_0 ^ (v3_0 >> 32)))) * 31) + this.color);
    }

    public void set(String p1, String p2, float p3, vn.ai.trueid.lottie.model.DocumentData$Justification p4, int p5, float p6, float p7, int p8, int p9, float p10, boolean p11, android.graphics.PointF p12, android.graphics.PointF p13)
    {
        this.text = p1;
        this.fontName = p2;
        this.size = p3;
        this.justification = p4;
        this.tracking = p5;
        this.lineHeight = p6;
        this.baselineShift = p7;
        this.color = p8;
        this.strokeColor = p9;
        this.strokeWidth = p10;
        this.strokeOverFill = p11;
        this.boxPosition = p12;
        this.boxSize = p13;
        return;
    }
}
