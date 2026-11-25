package vn.ai.trueid.lottie.utils;
public class OffscreenLayer$ComposeOp {
    public int alpha;
    public androidx.core.graphics.b blendMode;
    public android.graphics.ColorFilter colorFilter;
    public vn.ai.trueid.lottie.utils.DropShadow shadow;

    public OffscreenLayer$ComposeOp()
    {
        this.reset();
        return;
    }

    public boolean hasBlendMode()
    {
        int v0_0 = this.blendMode;
        if ((v0_0 == 0) || (v0_0 == androidx.core.graphics.b.d)) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean hasColorFilter()
    {
        if (this.colorFilter == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean hasShadow()
    {
        if (this.shadow == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isNoop()
    {
        if ((this.isTranslucent()) || ((this.hasBlendMode()) || ((this.hasShadow()) || (this.hasColorFilter())))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isTranslucent()
    {
        if (this.alpha >= 255) {
            return 0;
        } else {
            return 1;
        }
    }

    public void reset()
    {
        this.alpha = 255;
        this.blendMode = 0;
        this.colorFilter = 0;
        this.shadow = 0;
        return;
    }
}
