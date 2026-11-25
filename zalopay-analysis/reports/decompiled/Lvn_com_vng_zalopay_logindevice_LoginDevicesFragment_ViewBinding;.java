package vn.com.vng.zalopay.logindevice;
public class LoginDevicesFragment_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.logindevice.LoginDevicesFragment b;

    public LoginDevicesFragment_ViewBinding(vn.com.vng.zalopay.logindevice.LoginDevicesFragment p4, android.view.View p5)
    {
        this.b = p4;
        p4.mRcvLoginDevice = ((androidx.recyclerview.widget.RecyclerView) b4.e.c(p5, 2131364003, "field \'mRcvLoginDevice\'", androidx.recyclerview.widget.RecyclerView));
        p4.mSwipeRefreshLayout = ((androidx.swiperefreshlayout.widget.SwipeRefreshLayout) b4.e.a(b4.e.b(p5, 2131364317, "field \'mSwipeRefreshLayout\'"), 2131364317, "field \'mSwipeRefreshLayout\'", androidx.swiperefreshlayout.widget.SwipeRefreshLayout));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.mRcvLoginDevice = 0;
            v0_0.mSwipeRefreshLayout = 0;
            return;
        }
    }
}
