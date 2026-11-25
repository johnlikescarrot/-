package vn.ai.trueid.sdk.facev3.present.presentation.widgets;
public final class GifDrawableView extends android.view.View {
    public final android.graphics.Movie a;
    public final long b;
    public final int c;
    public final int d;

    public GifDrawableView(android.content.Context p3, android.util.AttributeSet p4)
    {
        super(p3, p4, 0);
        if (p4 == 0) {
            return;
        } else {
            android.content.res.TypedArray v3_2 = p3.getTheme().obtainStyledAttributes(p4, dk.a.c, 0, 0);
            try {
                long v0_1 = v3_2.getResourceId(0, -1);
            } catch (int v4_6) {
                v3_2.recycle();
                throw v4_6;
            }
            if (v0_1 != -1) {
                int v4_4 = android.graphics.Movie.decodeStream(super.getContext().getResources().openRawResource(v0_1));
                super.a = v4_4;
                if (v4_4 != 0) {
                    super.c = v4_4.width();
                    super.d = v4_4.height();
                }
                super.b = android.os.SystemClock.uptimeMillis();
            }
            v3_2.recycle();
            return;
        }
    }

    public final void onDraw(android.graphics.Canvas p10)
    {
        super.onDraw(p10);
        android.graphics.Movie v0 = this.a;
        if (v0 != null) {
            int v1_4 = ((float) this.getWidth());
            float v2_0 = ((float) this.getHeight());
            float v3_1 = Math.min((v1_4 / ((float) this.c)), (v2_0 / ((float) this.d)));
            int v1_1 = ((v1_4 - (((float) this.c) * v3_1)) / ((float) 2));
            float v2_2 = ((v2_0 - (((float) this.d) * v3_1)) / ((float) 2));
            int v4_8 = android.os.SystemClock.uptimeMillis();
            long v6_1 = Integer.valueOf(v0.duration());
            if (v6_1.intValue() <= 0) {
                v6_1 = 0;
            }
            long v6_2;
            if (v6_1 == 0) {
                v6_2 = 1000;
            } else {
                v6_2 = v6_1.intValue();
            }
            v0.setTime(((int) ((v4_8 - this.b) % ((long) v6_2))));
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
