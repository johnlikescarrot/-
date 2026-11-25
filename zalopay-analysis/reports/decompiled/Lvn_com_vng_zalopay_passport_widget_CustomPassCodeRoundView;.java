package vn.com.vng.zalopay.passport.widget;
public class CustomPassCodeRoundView extends vn.zalopay.ui.password.view.PassCodeRoundView implements GZ.l {
    public PH.a f;
    public String g;

    public CustomPassCodeRoundView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        if ((p3 != null) && (!super.isInEditMode())) {
            super.setPinLength(6);
        }
        return;
    }

    private void setInternalText(String p2)
    {
        if (p2 == null) {
            p2 = "";
        }
        PH.a v2_1 = p2.length();
        this.a(v2_1);
        if (v2_1 >= 6) {
            PH.a v2_2 = this.f;
            if (v2_2 != null) {
                v2_2.c();
            }
        }
        return;
    }

    public final void b()
    {
        if (!android.text.TextUtils.isEmpty(this.g)) {
            String v0_3 = androidx.media3.exoplayer.source.r.c(1, 0, this.g);
            this.g = v0_3;
            this.setInternalText(v0_3);
            return;
        } else {
            return;
        }
    }

    public final void c(int p3)
    {
        if (this.g == null) {
            this.g = "";
        }
        if (this.g.length() < 6) {
            String v3_1 = androidx.activity.result.e.i(p3, this.g, new StringBuilder());
            this.g = v3_1;
            this.setInternalText(v3_1);
            return;
        } else {
            return;
        }
    }

    public String getInputText()
    {
        String v0 = this.g;
        if (v0 == null) {
            v0 = "";
        }
        return v0;
    }

    public void setInputText(String p3)
    {
        if (p3 == null) {
            p3 = "";
        }
        if (p3.length() >= 6) {
            p3 = p3.substring(0, 6);
        }
        this.g = p3;
        this.setInternalText(p3);
        return;
    }

    public void setPinEnteredListener(PH.a p1)
    {
        this.f = p1;
        return;
    }
}
