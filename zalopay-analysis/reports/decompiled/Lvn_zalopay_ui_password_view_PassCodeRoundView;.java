package vn.zalopay.ui.password.view;
public class PassCodeRoundView extends android.widget.RelativeLayout {
    public final android.content.Context a;
    public final java.util.ArrayList b;
    public android.graphics.drawable.Drawable c;
    public android.graphics.drawable.Drawable d;
    public final android.view.ViewGroup e;

    public PassCodeRoundView(android.content.Context p2, android.util.AttributeSet p3)
    {
        this(p2, p3, 0);
        return;
    }

    public PassCodeRoundView(android.content.Context p3, android.util.AttributeSet p4, int p5)
    {
        super(p3, p4, 0);
        super.a = p3;
        if ((p4 != 0) && (!super.isInEditMode())) {
            int v4_1 = p3.getTheme().obtainStyledAttributes(p4, sZ.h.l, 0, 0);
            int v5_2 = v4_1.getDrawable(1);
            super.c = v5_2;
            if (v5_2 == 0) {
                super.c = super.getResources().getDrawable(2131234709);
            }
            int v4_2 = v4_1.getDrawable(2);
            super.d = v4_2;
            if (v4_2 == 0) {
                super.d = super.getResources().getDrawable(2131234712);
            }
            super.e = ((android.view.ViewGroup) ((vn.zalopay.ui.password.view.PassCodeRoundView) ((android.view.LayoutInflater) p3.getSystemService("layout_inflater")).inflate(2131560385, super)).findViewById(2131364111));
            super.b = new java.util.ArrayList();
        }
        return;
    }

    public void a(int p4)
    {
        int v0 = 0;
        while (v0 < this.b.size()) {
            if ((p4 - 1) < v0) {
                ((android.widget.ImageView) this.b.get(v0)).setImageDrawable(this.c);
            } else {
                ((android.widget.ImageView) this.b.get(v0)).setImageDrawable(this.d);
            }
            v0++;
        }
        return;
    }

    public void setEmptyDotDrawable(int p2)
    {
        this.c = this.getResources().getDrawable(p2);
        return;
    }

    public void setFullDotDrawable(int p2)
    {
        this.d = this.getResources().getDrawable(p2);
        return;
    }

    public void setPinLength(int p7)
    {
        android.view.LayoutInflater v0_2 = ((android.view.LayoutInflater) this.a.getSystemService("layout_inflater"));
        this.e.removeAllViews();
        java.util.ArrayList v1_3 = new java.util.ArrayList(p7);
        int v3 = 0;
        while (v3 < p7) {
            android.widget.ImageView v4_4;
            if (v3 >= this.b.size()) {
                v4_4 = ((android.widget.ImageView) v0_2.inflate(2131560384, this.e, 0));
            } else {
                v4_4 = ((android.widget.ImageView) this.b.get(v3));
            }
            this.e.addView(v4_4);
            v1_3.add(v4_4);
            v3++;
        }
        this.b.clear();
        this.b.addAll(v1_3);
        this.a(0);
        return;
    }
}
