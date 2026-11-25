package vn.zalopay.ui.password.view;
public class KeyboardButtonView extends android.widget.RelativeLayout implements android.view.View$OnClickListener {
    public final android.widget.RelativeLayout a;
    public final android.widget.TextView b;
    public final android.widget.ImageView c;

    public KeyboardButtonView(android.content.Context p6, android.util.AttributeSet p7)
    {
        super(p6, p7, 0);
        if (!super.isInEditMode()) {
            int v7_3 = p6.getTheme().obtainStyledAttributes(p7, sZ.h.k, 0, 0);
            android.widget.ImageView v1_1 = v7_3.getString(2);
            android.graphics.drawable.Drawable v2_0 = v7_3.getDrawable(0);
            int v7_1 = v7_3.getBoolean(1, 1);
            vn.zalopay.ui.password.view.KeyboardButtonView v3_5 = ((vn.zalopay.ui.password.view.KeyboardButtonView) ((android.view.LayoutInflater) p6.getSystemService("layout_inflater")).inflate(2131560381, super));
            android.widget.TextView v4_3 = ((android.widget.TextView) v3_5.findViewById(2131363289));
            super.b = v4_3;
            if (v1_1 != null) {
                v4_3.setText(v1_1);
            }
            android.widget.ImageView v1_5 = ((android.widget.ImageView) v3_5.findViewById(2131363288));
            super.c = v1_5;
            if ((v2_0 != null) && (v1_5 != null)) {
                v1_5.setImageDrawable(v2_0);
                super.c.setVisibility(0);
            }
            super.a = ((android.widget.RelativeLayout) v3_5.findViewById(2131363856));
            super.a.setBackgroundResource(p6.obtainStyledAttributes(new int[] {2130969966})).getResourceId(0, 0));
            super.a.setOnClickListener(super);
            android.widget.RelativeLayout v6_4 = super.a;
            if ((v6_4 != null) && (v7_1 == 0)) {
                v6_4.setVisibility(4);
            }
        }
        return;
    }

    public final void onClick(android.view.View p1)
    {
        return;
    }

    public final boolean onInterceptTouchEvent(android.view.MotionEvent p1)
    {
        this.onTouchEvent(p1);
        return 0;
    }

    public void setEnableRipple(boolean p3)
    {
        android.widget.RelativeLayout v0_0 = this.a;
        if (v0_0 != null) {
            v0_0.setVisibility(0);
            this.a.setClickable(p3);
            this.a.setEnabled(p3);
            return;
        } else {
            return;
        }
    }

    public void setImageDrawableButton(android.graphics.drawable.Drawable p2)
    {
        int v0_0 = this.c;
        if (v0_0 != 0) {
            v0_0.setImageDrawable(p2);
            this.c.setVisibility(0);
            return;
        } else {
            return;
        }
    }

    public void setOnRippleAnimationEndListener(WZ.f p1)
    {
        return;
    }

    public void setText(String p2)
    {
        this.b.setText(p2);
        return;
    }

    public void setTextNumberColor(int p2)
    {
        android.widget.TextView v0 = this.b;
        if (v0 != null) {
            v0.setTextColor(p2);
            return;
        } else {
            return;
        }
    }

    public void setTextSize(float p3)
    {
        this.b.setTextSize(0, p3);
        return;
    }
}
