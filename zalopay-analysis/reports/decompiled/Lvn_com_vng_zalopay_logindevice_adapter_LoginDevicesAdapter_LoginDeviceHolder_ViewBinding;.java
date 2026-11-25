package vn.com.vng.zalopay.logindevice.adapter;
public class LoginDevicesAdapter$LoginDeviceHolder_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.logindevice.adapter.LoginDevicesAdapter$LoginDeviceHolder b;

    public LoginDevicesAdapter$LoginDeviceHolder_ViewBinding(vn.com.vng.zalopay.logindevice.adapter.LoginDevicesAdapter$LoginDeviceHolder p5, android.view.View p6)
    {
        this.b = p5;
        p5.tv_device_model = ((android.widget.TextView) b4.e.c(p6, 2131364855, "field \'tv_device_model\'", android.widget.TextView));
        p5.btn_device_logout = ((android.widget.Button) b4.e.a(b4.e.b(p6, 2131362147, "field \'btn_device_logout\'"), 2131362147, "field \'btn_device_logout\'", android.widget.Button));
        p5.view_divider = b4.e.b(p6, 2131365051, "field \'view_divider\'");
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.tv_device_model = 0;
            v0_0.btn_device_logout = 0;
            v0_0.view_divider = 0;
            return;
        }
    }
}
