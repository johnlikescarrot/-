package vn.ai.trueid.lottie.utils;
public class DropShadow {
    private int color;
    private float dx;
    private float dy;
    private float radius;
    private float[] vecs;

    public DropShadow()
    {
        this.radius = 0;
        this.dx = 0;
        this.dy = 0;
        this.color = 0;
        return;
    }

    public DropShadow(float p1, float p2, float p3, int p4)
    {
        this.radius = p1;
        this.dx = p2;
        this.dy = p3;
        this.color = p4;
        this.vecs = 0;
        return;
    }

    public DropShadow(vn.ai.trueid.lottie.utils.DropShadow p2)
    {
        this.radius = 0;
        this.dx = 0;
        this.dy = 0;
        this.color = 0;
        this.radius = p2.radius;
        this.dx = p2.dx;
        this.dy = p2.dy;
        this.color = p2.color;
        this.vecs = 0;
        return;
    }

    public void applyTo(android.graphics.Paint p5)
    {
        if (android.graphics.Color.alpha(this.color) <= 0) {
            p5.clearShadowLayer();
            return;
        } else {
            p5.setShadowLayer(Math.max(this.radius, 1), this.dx, this.dy, this.color);
            return;
        }
    }

    public void applyTo(vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp p2)
    {
        if (android.graphics.Color.alpha(this.color) <= 0) {
            p2.shadow = 0;
            return;
        } else {
            p2.shadow = this;
            return;
        }
    }

    public void applyWithAlpha(int p4, android.graphics.Paint p5)
    {
        int v4_3 = vn.ai.trueid.lottie.utils.Utils.mixOpacities(android.graphics.Color.alpha(this.color), vn.ai.trueid.lottie.utils.MiscUtils.clamp(p4, 0, 255));
        if (v4_3 <= 0) {
            p5.clearShadowLayer();
            return;
        } else {
            p5.setShadowLayer(Math.max(this.radius, 1), this.dx, this.dy, android.graphics.Color.argb(v4_3, android.graphics.Color.red(this.color), android.graphics.Color.green(this.color), android.graphics.Color.blue(this.color)));
            return;
        }
    }

    public void applyWithAlpha(int p2, vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp p3)
    {
        vn.ai.trueid.lottie.utils.DropShadow v0_1 = new vn.ai.trueid.lottie.utils.DropShadow(this);
        p3.shadow = v0_1;
        v0_1.multiplyOpacity(p2);
        return;
    }

    public int getColor()
    {
        return this.color;
    }

    public float getDx()
    {
        return this.dx;
    }

    public float getDy()
    {
        return this.dy;
    }

    public float getRadius()
    {
        return this.radius;
    }

    public void multiplyOpacity(int p4)
    {
        this.color = android.graphics.Color.argb(Math.round((((float) (vn.ai.trueid.lottie.utils.MiscUtils.clamp(p4, 0, 255) * android.graphics.Color.alpha(this.color))) / 1132396544)), android.graphics.Color.red(this.color), android.graphics.Color.green(this.color), android.graphics.Color.blue(this.color));
        return;
    }

    public boolean sameAs(vn.ai.trueid.lottie.utils.DropShadow p3)
    {
        if ((this.radius != p3.radius) || ((this.dx != p3.dx) || ((this.dy != p3.dy) || (this.color != p3.color)))) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setColor(int p1)
    {
        this.color = p1;
        return;
    }

    public void setDx(float p1)
    {
        this.dx = p1;
        return;
    }

    public void setDy(float p1)
    {
        this.dy = p1;
        return;
    }

    public void setRadius(float p1)
    {
        this.radius = p1;
        return;
    }

    public void transformBy(android.graphics.Matrix p5)
    {
        if (this.vecs == null) {
            float v0_5 = new float[2];
            this.vecs = v0_5;
        }
        float v0_6 = this.vecs;
        v0_6[0] = this.dx;
        v0_6[1] = this.dy;
        p5.mapVectors(v0_6);
        float v0_1 = this.vecs;
        this.dx = v0_1[0];
        this.dy = v0_1[1];
        this.radius = p5.mapRadius(this.radius);
        return;
    }
}
