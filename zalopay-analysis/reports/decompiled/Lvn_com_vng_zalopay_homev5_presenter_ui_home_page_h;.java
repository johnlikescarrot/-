package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class h implements vn.com.vng.zalopay.homev5.presenter.ui.home_page.k {
    public final String a;

    public h(String p1)
    {
        this.a = p1;
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.h)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.h) p4).a)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int hashCode()
    {
        return this.a.hashCode();
    }

    public final String toString()
    {
        return A1.a.i("OpenDeepLink(deepLink=", this.a, ")");
    }
}
