package vn.com.vng.zalopay.passport.widget;
public final class b extends b4.a {
    public final synthetic int c;
    public final synthetic vn.com.vng.zalopay.passport.widget.OnboardingView d;

    public synthetic b(vn.com.vng.zalopay.passport.widget.OnboardingView p1, int p2)
    {
        this.c = p2;
        this.d = p1;
        return;
    }

    public final void a(android.view.View p2)
    {
        switch (this.c) {
            case 0:
                this.d.onClick(p2);
                return;
            default:
                this.d.onSecondClick(p2);
                return;
        }
    }
}
