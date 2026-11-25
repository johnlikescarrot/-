package vn.ai.trueid.lottie.animation;
public class LPaint extends android.graphics.Paint {

    public LPaint()
    {
        return;
    }

    public LPaint(int p1)
    {
        super(p1);
        return;
    }

    public LPaint(int p1, android.graphics.PorterDuff$Mode p2)
    {
        super(p1);
        super.setXfermode(new android.graphics.PorterDuffXfermode(p2));
        return;
    }

    public LPaint(android.graphics.PorterDuff$Mode p2)
    {
        this.setXfermode(new android.graphics.PorterDuffXfermode(p2));
        return;
    }

    public void setAlpha(int p5)
    {
        if (android.os.Build$VERSION.SDK_INT >= 30) {
            super.setAlpha(vn.ai.trueid.lottie.utils.MiscUtils.clamp(p5, 0, 255));
            return;
        } else {
            this.setColor(((vn.ai.trueid.lottie.utils.MiscUtils.clamp(p5, 0, 255) << 24) | (this.getColor() & 16777215)));
            return;
        }
    }

    public void setTextLocales(android.os.LocaleList p1)
    {
        return;
    }
}
