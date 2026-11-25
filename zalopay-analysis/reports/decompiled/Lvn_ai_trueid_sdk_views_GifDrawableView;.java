package vn.ai.trueid.sdk.views;
public final class GifDrawableView extends androidx.appcompat.widget.AppCompatImageView {

    public GifDrawableView(android.content.Context p3, android.util.AttributeSet p4)
    {
        super(p3, p4, 0);
        if (p4 == null) {
            return;
        } else {
            android.content.res.TypedArray v3_2 = p3.getTheme().obtainStyledAttributes(p4, dk.a.c, 0, 0);
            try {
                int v0_1 = v3_2.getResourceId(0, -1);
            } catch (Throwable v4_1) {
                v3_2.recycle();
                throw v4_1;
            }
            if (v0_1 != -1) {
                super.setGifResource(v0_1);
            }
            v3_2.recycle();
            return;
        }
    }

    public final void setGifResource(int p3)
    {
        if (android.os.Build$VERSION.SDK_INT < 28) {
            this.setImageResource(p3);
            return;
        } else {
            try {
                android.graphics.drawable.AnimatedImageDrawable v3_1 = android.graphics.ImageDecoder.decodeDrawable(android.graphics.ImageDecoder.createSource(this.getResources(), p3));
                this.setImageDrawable(v3_1);
            } catch (java.io.IOException) {
                return;
            }
            if (!k0.l.q(v3_1)) {
                return;
            } else {
                k0.l.a(v3_1).start();
                return;
            }
        }
    }
}
