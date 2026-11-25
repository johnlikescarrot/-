package vn.com.vng.zalopay.user;
public abstract class UserBaseToolBarActivity extends vn.com.vng.zalopay.user.UserBaseActivity {
    protected vn.zalopay.ui.navigationbar.NavigationBar mNavigationBar;

    public UserBaseToolBarActivity()
    {
        return;
    }

    public int h1()
    {
        return 2131558452;
    }

    public void onCreate(android.os.Bundle p3)
    {
        super.onCreate(p3);
        this.mNavigationBar.setListener(new IK.n(this, 3));
        this.setTitle(this.getTitle());
        return;
    }

    public void setTitle(int p2)
    {
        this.mNavigationBar.setTitle(this.getString(p2));
        return;
    }

    public void setTitle(CharSequence p2)
    {
        this.mNavigationBar.setTitle(p2.toString());
        return;
    }
}
