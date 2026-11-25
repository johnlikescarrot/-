package vn.zalopay.ui;
public class RippleBackground extends android.widget.RelativeLayout {
    public final float a;
    public final android.graphics.Paint b;
    public final android.animation.AnimatorSet c;
    public final java.util.ArrayList d;

    public RippleBackground(android.content.Context p18, android.util.AttributeSet p19)
    {
        super(p18, p19);
        super.d = new java.util.ArrayList();
        if (!super.isInEditMode()) {
            if (p19 == 0) {
                throw new IllegalArgumentException("Attributes should be provided to this view,");
            } else {
                android.animation.AnimatorSet v1_3 = p18.obtainStyledAttributes(p19, sZ.h.q);
                int v3_4 = 0;
                android.widget.RelativeLayout$LayoutParams v2_4 = v1_3.getColor(0, super.getContext().getColor(2131100654));
                super.a = v1_3.getDimension(5, super.getResources().getDimension(2131166190));
                int v5_1 = 2;
                java.util.ArrayList v4_5 = v1_3.getDimension(2, super.getResources().getDimension(2131166189));
                int v7_2 = 1;
                int v6_1 = v1_3.getInt(1, 3000);
                int v8_0 = v1_3.getInt(3, 6);
                float v10_1 = v1_3.getFloat(4, 1086324736);
                int v9_1 = v1_3.getInt(6, 0);
                v1_3.recycle();
                android.animation.AnimatorSet v1_4 = (v6_1 / v8_0);
                int v11_3 = new android.graphics.Paint();
                super.b = v11_3;
                v11_3.setAntiAlias(1);
                if (v9_1 != 0) {
                    super.b.setStyle(android.graphics.Paint$Style.STROKE);
                } else {
                    super.a = 0;
                    super.b.setStyle(android.graphics.Paint$Style.FILL);
                }
                super.b.setColor(v2_4);
                android.widget.RelativeLayout$LayoutParams v2_9 = new android.widget.RelativeLayout$LayoutParams(((int) ((v4_5 + super.a) * 1073741824)), ((int) ((v4_5 + super.a) * 1073741824)));
                v2_9.addRule(13, -1);
                java.util.ArrayList v4_11 = new android.animation.AnimatorSet();
                super.c = v4_11;
                v4_11.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());
                java.util.ArrayList v4_13 = new java.util.ArrayList();
                int v11_0 = 0;
                while (v11_0 < v8_0) {
                    sZ.i v12_0 = new sZ.i(super, super.getContext());
                    super.addView(v12_0, v2_9);
                    super.d.add(v12_0);
                    int v13_1 = new float[v5_1];
                    v13_1[v3_4] = 1065353216;
                    v13_1[v7_2] = v10_1;
                    int v13_2 = android.animation.ObjectAnimator.ofFloat(v12_0, "ScaleX", v13_1);
                    v13_2.setRepeatCount(-1);
                    v13_2.setRepeatMode(v7_2);
                    int v19_1 = v7_2;
                    int v16 = v8_0;
                    long v14_2 = (((long) v11_0) * ((long) v1_4));
                    v13_2.setStartDelay(v14_2);
                    int v7_1 = ((long) v6_1);
                    v13_2.setDuration(v7_1);
                    v4_13.add(v13_2);
                    int v13_3 = new float[v5_1];
                    v13_3[v3_4] = 1065353216;
                    v13_3[v19_1] = v10_1;
                    int v3_1 = android.animation.ObjectAnimator.ofFloat(v12_0, "ScaleY", v13_3);
                    v3_1.setRepeatCount(-1);
                    int v13_4 = v19_1;
                    v3_1.setRepeatMode(v13_4);
                    v3_1.setStartDelay(v14_2);
                    v3_1.setDuration(v7_1);
                    v4_13.add(v3_1);
                    int v3_2 = new float[v5_1];
                    v3_2 = {1065353216, 0};
                    int v3_3 = android.animation.ObjectAnimator.ofFloat(v12_0, "Alpha", v3_2);
                    v3_3.setRepeatCount(-1);
                    v3_3.setRepeatMode(v13_4);
                    v3_3.setStartDelay(v14_2);
                    v3_3.setDuration(v7_1);
                    v4_13.add(v3_3);
                    v11_0++;
                    v7_2 = v13_4;
                    v8_0 = v16;
                    v3_4 = 0;
                    v5_1 = 2;
                }
                super.c.playTogether(v4_13);
                return;
            }
        } else {
            return;
        }
    }
}
