package vn.zalopay.ui.topsnackbar;
public final class j implements vn.zalopay.ui.topsnackbar.c {
    public final synthetic vn.zalopay.ui.topsnackbar.p a;

    public j(vn.zalopay.ui.topsnackbar.p p1)
    {
        this.a = p1;
        return;
    }

    public final void a(int p5)
    {
        android.os.Handler v0 = vn.zalopay.ui.topsnackbar.p.e;
        v0.sendMessage(v0.obtainMessage(1, p5, 0, this.a));
        return;
    }

    public final void show()
    {
        android.os.Handler v0 = vn.zalopay.ui.topsnackbar.p.e;
        v0.sendMessage(v0.obtainMessage(0, this.a));
        return;
    }
}
