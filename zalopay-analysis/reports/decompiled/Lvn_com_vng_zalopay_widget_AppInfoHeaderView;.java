package vn.com.vng.zalopay.widget;
public class AppInfoHeaderView extends android.widget.LinearLayout {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final int f;
    public final int g;
    public final int h;
    vn.zalopay.ui.svgutil.ZPSVGImageView mIconFont;
    android.view.View mLineConnector;
    android.widget.LinearLayout mLlIcon;
    android.widget.LinearLayout mLlText;
    android.widget.TextView mTvDesFirst;
    android.widget.TextView mTvDesSecond;
    android.widget.TextView mTvTitle;

    public AppInfoHeaderView(android.content.Context p4, android.util.AttributeSet p5)
    {
        super(p4, p5, 0);
        super.a = 0;
        super.b = 0;
        super.c = 0;
        super.d = "";
        android.view.View.inflate(super.getContext(), 2131559347, super);
        android.content.res.TypedArray v4_2 = p4.getTheme().obtainStyledAttributes(p5, sm.c.c, 0, 0);
        if (v4_2 != null) {
            super.a = v4_2.getString(6);
            super.b = v4_2.getString(0);
            super.c = v4_2.getString(1);
            super.d = v4_2.getString(2);
            super.e = v4_2.getBoolean(3, 1);
            super.f = ((int) super.getResources().getDimension(2131166263));
            super.g = v4_2.getDimensionPixelSize(4, 0);
            super.h = v4_2.getDimensionPixelSize(5, 0);
            v4_2.recycle();
            return;
        } else {
            return;
        }
    }

    public static void a(android.widget.TextView p3, String p4)
    {
        if ((p4 != null) && (!p4.isEmpty())) {
            android.text.Spanned v4_1 = android.text.Html.fromHtml(p4, 0);
            if (!android.text.TextUtils.isEmpty(v4_1)) {
                p3.setVisibility(0);
                p3.setText(v4_1);
                return;
            } else {
                p3.setVisibility(8);
                return;
            }
        } else {
            if (!android.text.TextUtils.isEmpty(p4)) {
                p3.setVisibility(0);
                p3.setText(p4);
                return;
            } else {
                p3.setVisibility(8);
                return;
            }
        }
    }

    private android.view.View getBottomView()
    {
        if (android.text.TextUtils.isEmpty(this.c)) {
            return this.mTvDesFirst;
        } else {
            return this.mTvDesSecond;
        }
    }

    public final void onFinishInflate()
    {
        super.onFinishInflate();
        butterknife.ButterKnife.a(this, this);
        vn.com.vng.zalopay.widget.AppInfoHeaderView.a(this.mTvTitle, this.a);
        vn.com.vng.zalopay.widget.AppInfoHeaderView.a(this.mTvDesFirst, this.b);
        vn.com.vng.zalopay.widget.AppInfoHeaderView.a(this.mTvDesSecond, this.c);
        int v0_0 = this.d;
        if (v0_0 != 0) {
            this.mIconFont.setImageName(v0_0);
        }
        android.view.View v1_2;
        if (!this.e) {
            v1_2 = 8;
        } else {
            v1_2 = 0;
        }
        this.mLineConnector.setVisibility(v1_2);
        android.view.View v1_3 = this.h;
        if (v1_3 > null) {
            android.widget.LinearLayout$LayoutParams v3_1 = new android.widget.FrameLayout$LayoutParams(-2, -2);
            v3_1.setMargins(0, v1_3, 0, 0);
            this.setLayoutParams(v3_1);
        }
        if ((this.g > 0) && ((!android.text.TextUtils.isEmpty(this.b)) || (!android.text.TextUtils.isEmpty(this.c)))) {
            android.view.View v1_10 = this.getBottomView();
            android.widget.LinearLayout$LayoutParams v3_3 = new android.widget.LinearLayout$LayoutParams(-2, -2);
            v3_3.setMargins(0, 0, 0, this.g);
            v1_10.setLayoutParams(v3_3);
        }
        return;
    }

    public final void onMeasure(int p10, int p11)
    {
        super.onMeasure(p10, p11);
        if (this.e) {
            int v1_4 = (this.getPaddingRight() + this.getPaddingLeft());
            int v8 = (this.getPaddingBottom() + this.getPaddingTop());
            int v5_0 = (this.mLlText.getMeasuredWidth() + v1_4);
            this.mLineConnector.getLayoutParams().height = (this.mLlText.getMeasuredHeight() - this.f);
            this.measureChildWithMargins(this.mLineConnector, p10, v5_0, p11, (this.mIconFont.getMeasuredHeight() + v8));
            this.measureChildWithMargins(this.mLlIcon, p10, v5_0, p11, v8);
            this.setMeasuredDimension(android.view.View.resolveSize(this.getMeasuredWidth(), p10), android.view.View.resolveSize((Math.max(this.mLlIcon.getMeasuredHeight(), this.mLlText.getMeasuredHeight()) + v8), p11));
            return;
        } else {
            return;
        }
    }

    public void setHeaderData(Fw.a p1)
    {
        throw 0;
    }

    public void setHeaderData(Fw.b p1)
    {
        throw 0;
    }
}
