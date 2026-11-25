package vn.com.vng.zalopay.logindevice;
public class LoginDevicesFragment extends Ts.d implements TD.g, TD.j, androidx.swiperefreshlayout.widget.SwipeRefreshLayout$b {
    androidx.recyclerview.widget.RecyclerView mRcvLoginDevice;
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout mSwipeRefreshLayout;
    public TD.i v;
    public nY.p w;
    public vn.com.vng.zalopay.logindevice.adapter.a x;
    public int y;

    public LoginDevicesFragment()
    {
        return;
    }

    public final void O0()
    {
        androidx.swiperefreshlayout.widget.SwipeRefreshLayout v0 = this.mSwipeRefreshLayout;
        if ((v0 != null) && (v0.c)) {
            v0.setRefreshing(0);
        }
        return;
    }

    public final void Y0()
    {
        vn.com.vng.zalopay.logindevice.adapter.a v0 = this.x;
        if (v0 != null) {
            int v1 = this.y;
            v0.e.remove(v1);
            v0.notifyItemRemoved(v1);
            v0.notifyItemRangeRemoved(v1, v0.e.size());
            return;
        } else {
            return;
        }
    }

    public final int m1()
    {
        return 2131558944;
    }

    public final void n1()
    {
        q9.a5.b().z1(this);
        return;
    }

    public final void onDestroyView()
    {
        this.v.e0();
        this.x = 0;
        super.onDestroyView();
        return;
    }

    public final void onRefresh()
    {
        if (this.w.a()) {
            this.v.E0();
            return;
        } else {
            TD.i v0_3 = this.mSwipeRefreshLayout;
            if (v0_3 != null) {
                v0_3.setRefreshing(0);
            }
            return;
        }
    }

    public final void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        this.v.b = this;
        this.getContext();
        this.mRcvLoginDevice.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(1));
        TD.i v2_2 = new vn.com.vng.zalopay.logindevice.adapter.a(this.getContext(), this);
        this.x = v2_2;
        this.mRcvLoginDevice.setAdapter(v2_2);
        this.mSwipeRefreshLayout.setColorSchemeColors(new int[] {this.getResources().getColor(2131099828)}));
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.v.E0();
        return;
    }

    public final void p0(java.util.List p2)
    {
        vn.com.vng.zalopay.logindevice.adapter.a v0 = this.x;
        if (v0 != null) {
            v0.e = p2;
            v0.notifyDataSetChanged();
            return;
        } else {
            return;
        }
    }

    public final void t0(String p3)
    {
        this.f1(p3, this.getString(2131955623), 0);
        return;
    }
}
