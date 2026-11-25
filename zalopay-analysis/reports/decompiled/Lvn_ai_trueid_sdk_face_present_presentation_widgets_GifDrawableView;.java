package vn.ai.trueid.sdk.face.present.presentation.widgets;
public final class GifDrawableView extends android.view.View {
    private int gifResourceId;
    private android.graphics.Movie movie;
    private int movieHeight;
    private long movieStart;
    private int movieWidth;

    public GifDrawableView(android.content.Context p7)
    {
        this(p7, 0, 0, 6, 0);
        return;
    }

    public GifDrawableView(android.content.Context p7, android.util.AttributeSet p8)
    {
        this(p7, p8, 0, 4, 0);
        return;
    }

    public GifDrawableView(android.content.Context p3, android.util.AttributeSet p4, int p5)
    {
        super(p3, p4, p5);
        super.gifResourceId = -1;
        if (p4 == null) {
            return;
        } else {
            android.content.res.TypedArray v3_1 = p3.getTheme().obtainStyledAttributes(p4, vn.ai.trueid.sdk.face.R$styleable.GifDrawableView, 0, 0);
            try {
                super.gifResourceId = v3_1.getResourceId(vn.ai.trueid.sdk.face.R$styleable.GifDrawableView_gifSrc, -1);
                super.init();
                v3_1.recycle();
                return;
            } catch (Throwable v4_3) {
                v3_1.recycle();
                throw v4_3;
            }
        }
    }

    public synthetic GifDrawableView(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 2) != 0) {
            p2 = 0;
        }
        if ((p4 & 4) != 0) {
            p3 = 0;
        }
        this(p1, p2, p3);
        return;
    }

    private final void init()
    {
        if (this.gifResourceId != -1) {
            long v0_1 = android.graphics.Movie.decodeStream(this.getContext().getResources().openRawResource(this.gifResourceId));
            this.movie = v0_1;
            if (v0_1 != 0) {
                this.movieWidth = v0_1.width();
                this.movieHeight = v0_1.height();
            }
            this.movieStart = android.os.SystemClock.uptimeMillis();
        }
        return;
    }

    public void onDraw(android.graphics.Canvas p10)
    {
        super.onDraw(p10);
        android.graphics.Movie v0 = this.movie;
        if (v0 != null) {
            int v1_4 = ((float) this.getWidth());
            float v2_0 = ((float) this.getHeight());
            float v3_1 = Math.min((v1_4 / ((float) this.movieWidth)), (v2_0 / ((float) this.movieHeight)));
            int v1_1 = ((v1_4 - (((float) this.movieWidth) * v3_1)) / ((float) 2));
            float v2_2 = ((v2_0 - (((float) this.movieHeight) * v3_1)) / ((float) 2));
            int v4_8 = android.os.SystemClock.uptimeMillis();
            long v6_0 = v0.duration();
            long v7_0 = Integer.valueOf(v6_0);
            if (v6_0 <= 0) {
                v7_0 = 0;
            }
            long v6_1;
            if (v7_0 == 0) {
                v6_1 = 1000;
            } else {
                v6_1 = v7_0.intValue();
            }
            v0.setTime(((int) ((v4_8 - this.movieStart) % ((long) v6_1))));
            p10.save();
            p10.translate(v1_1, v2_2);
            p10.scale(v3_1, v3_1);
            v0.draw(p10, 0, 0);
            p10.restore();
            this.invalidate();
        }
        return;
    }
}
