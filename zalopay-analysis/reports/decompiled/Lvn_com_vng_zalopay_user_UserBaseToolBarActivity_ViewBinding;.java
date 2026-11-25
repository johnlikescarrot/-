package vn.com.vng.zalopay.user;
public class UserBaseToolBarActivity_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.user.UserBaseToolBarActivity b;

    public UserBaseToolBarActivity_ViewBinding(vn.com.vng.zalopay.user.UserBaseToolBarActivity p4, android.view.View p5)
    {
        this.b = p4;
        p4.mNavigationBar = ((vn.zalopay.ui.navigationbar.NavigationBar) b4.e.c(p5, 2131363687, "field \'mNavigationBar\'", vn.zalopay.ui.navigationbar.NavigationBar));
        return;
    }

    public void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.mNavigationBar = 0;
            return;
        }
    }
}
