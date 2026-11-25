package vn.com.vng.zalopay.passport.widget;
public class CustomKeyboardView extends vn.zalopay.ui.password.view.KeyboardView implements WZ.f {
    public GZ.l d;

    public CustomKeyboardView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        super.setKeyboardButtonClickedListener(super);
        return;
    }

    public void setEditable(GZ.l p1)
    {
        this.d = p1;
        return;
    }

    public final void v(int p2)
    {
        if (p2 != 12) {
            GZ.l v0_1 = this.d;
            if (v0_1 != null) {
                v0_1.c(SP.n.c(p2));
            }
        } else {
            int v2_2 = this.d;
            if (v2_2 != 0) {
                v2_2.b();
                return;
            }
        }
        return;
    }
}
