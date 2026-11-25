package vn.com.vng.zalopay.logindevice.adapter;
public final class a extends androidx.recyclerview.widget.W {
    public java.util.List e;
    public final android.content.Context f;
    public final vn.com.vng.zalopay.logindevice.LoginDevicesFragment g;

    public a(android.content.Context p1, vn.com.vng.zalopay.logindevice.LoginDevicesFragment p2)
    {
        this.f = p1;
        this.g = p2;
        this.e = new java.util.ArrayList();
        return;
    }

    public final int getItemCount()
    {
        return this.e.size();
    }

    public final void onBindViewHolder(androidx.recyclerview.widget.y0 p4, int p5)
    {
        ft.m v0_2 = ((ft.m) this.e.get(p5));
        ((vn.com.vng.zalopay.logindevice.adapter.LoginDevicesAdapter$LoginDeviceHolder) p4).tv_device_model.setText(v0_2.c);
        if (p5 == (this.e.size() - 1)) {
            ((vn.com.vng.zalopay.logindevice.adapter.LoginDevicesAdapter$LoginDeviceHolder) p4).view_divider.setVisibility(8);
        }
        ((vn.com.vng.zalopay.logindevice.adapter.LoginDevicesAdapter$LoginDeviceHolder) p4).btn_device_logout.setOnClickListener(new UD.a(this, v0_2, p5));
        return;
    }

    public final androidx.recyclerview.widget.y0 onCreateViewHolder(android.view.ViewGroup p3, int p4)
    {
        android.view.View v3_1 = android.view.LayoutInflater.from(this.f).inflate(2131560236, p3, 0);
        vn.com.vng.zalopay.logindevice.adapter.LoginDevicesAdapter$LoginDeviceHolder v4_3 = new vn.com.vng.zalopay.logindevice.adapter.LoginDevicesAdapter$LoginDeviceHolder(v3_1);
        butterknife.ButterKnife.a(v3_1, v4_3);
        return v4_3;
    }
}
