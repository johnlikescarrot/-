package vn.com.vng.zalopay.user;
public abstract class BaseToolBarActivity extends vn.com.vng.zalopay.ui.activity.SkinActivity {
    protected vn.zalopay.ui.navigationbar.NavigationBar mNavigationBar;

    public BaseToolBarActivity()
    {
        return;
    }

    public final int h1()
    {
        return 2131558452;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        super.onCreate(p3);
        this.mNavigationBar.setListener(new IK.n(this, 2));
        this.setTitle(this.getTitle());
        return;
    }

    public final void setTitle(int p2)
    {
        this.mNavigationBar.setTitle(this.getString(p2));
        return;
    }

    public final void setTitle(CharSequence p2)
    {
        this.mNavigationBar.setTitle(p2.toString());
        return;
    }
}
