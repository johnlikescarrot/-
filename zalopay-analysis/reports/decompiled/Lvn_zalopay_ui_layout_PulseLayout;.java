package vn.zalopay.ui.layout;
public class PulseLayout extends android.widget.RelativeLayout {
    public final int a;
    public final long b;
    public final int c;
    public final int d;
    public final float e;
    public final android.graphics.Paint f;
    public boolean g;
    public final android.animation.AnimatorSet h;
    public final java.util.ArrayList i;
    public final java.util.ArrayList j;

    public PulseLayout(android.content.Context p9, android.util.AttributeSet p10)
    {
        super(p9, p10);
        super.a = super.getResources().getColor(2131100654);
        super.b = 500;
        super.c = 500;
        super.d = 2;
        super.e = super.getResources().getDimension(2131166189);
        int v3_1 = 0;
        super.g = 0;
        super.j = new java.util.ArrayList();
        if (!super.isInEditMode()) {
            if (p10 != null) {
                android.animation.AnimatorSet v9_1 = p9.obtainStyledAttributes(p10, sZ.h.m);
                super.a = v9_1.getColor(0, super.getResources().getColor(2131100654));
                super.b = ((long) v9_1.getInt(2, 500));
                super.c = v9_1.getInt(3, 3000);
                super.d = v9_1.getInt(1, 2);
                super.e = v9_1.getDimension(5, super.getResources().getDimension(2131166189));
                v9_1.recycle();
            }
            android.animation.AnimatorSet v9_3 = new android.graphics.Paint();
            super.f = v9_3;
            v9_3.setAntiAlias(1);
            super.f.setStyle(android.graphics.Paint$Style.FILL);
            super.f.setColor(super.a);
            super.h = new android.animation.AnimatorSet();
            super.i = new java.util.ArrayList();
            android.animation.AnimatorSet v9_11 = ((int) (super.e * 1073741824));
            while (v3_1 < super.d) {
                Bk.h v10_21 = new OZ.f(super, super.getContext());
                java.util.ArrayList v0_18 = new android.widget.RelativeLayout$LayoutParams(v9_11, v9_11);
                v0_18.addRule(13, -1);
                super.addView(v10_21, v0_18);
                super.j.add(v10_21);
                java.util.ArrayList v0_1 = new float[2];
                v0_1 = {0, 1065353216};
                java.util.ArrayList v0_2 = android.animation.ObjectAnimator.ofFloat(v10_21, "ScaleX", v0_1);
                long v4_0 = ((long) v3_1);
                v0_2.setStartDelay((super.b * v4_0));
                v0_2.setDuration(((long) super.c));
                super.i.add(v0_2);
                java.util.ArrayList v0_3 = new float[2];
                v0_3 = {0, 1065353216};
                java.util.ArrayList v0_4 = android.animation.ObjectAnimator.ofFloat(v10_21, "ScaleY", v0_3);
                v0_4.setDuration(((long) super.c));
                v0_4.setStartDelay((super.b * v4_0));
                super.i.add(v0_4);
                java.util.ArrayList v0_5 = new float[2];
                v0_5 = {1065353216, 0};
                Bk.h v10_1 = android.animation.ObjectAnimator.ofFloat(v10_21, "Alpha", v0_5);
                v10_1.setDuration(((long) super.c));
                v10_1.setStartDelay((v4_0 * super.b));
                super.i.add(v10_1);
                v3_1++;
            }
            super.h.playTogether(super.i);
            super.h.addListener(new Bk.h(2, super));
            return;
        } else {
            return;
        }
    }

    public final void onDetachedFromWindow()
    {
        if (this.g) {
            this.g = 0;
            this.h.end();
        }
        super.onDetachedFromWindow();
        return;
    }

    public final void onWindowVisibilityChanged(int p4)
    {
        if ((p4 == 0) && ((this.getVisibility() == 0) && (!this.g))) {
            int v1_8 = this.j.iterator();
            while (v1_8.hasNext()) {
                ((OZ.f) v1_8.next()).setVisibility(0);
            }
            this.h.start();
            this.g = 1;
        }
        if ((p4 == 8) && ((this.getVisibility() == 0) && (this.g))) {
            this.g = 0;
            this.h.end();
        }
        super.onWindowVisibilityChanged(p4);
        return;
    }
}
