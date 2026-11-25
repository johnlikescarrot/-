package vn.zalopay.ui.password.view;
public final class GrayPassCodeRoundView extends vn.zalopay.ui.password.view.PassCodeRoundView implements GZ.l {
    public final android.graphics.drawable.Drawable f;
    public String g;
    public kotlin.jvm.functions.Function1 h;

    public GrayPassCodeRoundView(android.content.Context p3, android.util.AttributeSet p4)
    {
        super(p3, p4, 0);
        super.g = "";
        if ((p4 != 0) && (!super.isInEditMode())) {
            int v3_3 = super.a.getTheme().obtainStyledAttributes(p4, sZ.h.l, 0, 0).getDrawable(0);
            super.f = v3_3;
            if (v3_3 == 0) {
                super.f = super.getContext().getDrawable(2131234709);
            }
            super.setFullDotDrawable(2131234712);
            super.setEmptyDotDrawable(2131234711);
        }
        return;
    }

    public final void a(int p2)
    {
        super.a(p2);
        if (p2 < this.b.size()) {
            ((android.widget.ImageView) this.b.get(p2)).setImageDrawable(this.f);
        }
        return;
    }

    public final void b()
    {
        if (this.g.length() != 0) {
            kotlin.jvm.functions.Function1 v0_5 = kotlin.text.F.dropLast(this.g, 1);
            this.g = v0_5;
            this.a(v0_5.length());
            kotlin.jvm.functions.Function1 v0_2 = this.h;
            if (v0_2 != null) {
                v0_2.invoke(this.g);
            }
            return;
        } else {
            return;
        }
    }

    public final void c(int p3)
    {
        if (this.g.length() < this.b.size()) {
            String v0_3 = this.g;
            StringBuilder v1_3 = new StringBuilder();
            v1_3.append(v0_3);
            v1_3.append(p3);
            kotlin.jvm.functions.Function1 v3_1 = v1_3.toString();
            this.g = v3_1;
            this.a(v3_1.length());
            kotlin.jvm.functions.Function1 v3_3 = this.h;
            if (v3_3 != null) {
                v3_3.invoke(this.g);
            }
        }
        return;
    }

    public final void d()
    {
        this.g = "";
        this.a(0);
        return;
    }

    public final void e()
    {
        if (this.b.size() > 0) {
            ((android.widget.ImageView) this.b.get(0)).setImageDrawable(this.getResources().getDrawable(2131234711));
        }
        return;
    }

    public String getInputText()
    {
        return this.g;
    }

    public final int getPinLength()
    {
        return this.b.size();
    }

    public void setInputText(String p1)
    {
        return;
    }

    public final void setOnPinChangeListener(kotlin.jvm.functions.Function1 p1)
    {
        this.h = p1;
        return;
    }
}
