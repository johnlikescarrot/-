package vn.com.vng.zalopay.core.ui.activity;
public abstract class BaseToolBarActivity extends vn.com.vng.zalopay.core.ui.activity.BaseOldActivity {

    public BaseToolBarActivity()
    {
        return;
    }

    public final int h1()
    {
        return 2131558452;
    }

    public void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        this.setSupportActionBar(((androidx.appcompat.widget.Toolbar) this.findViewById(2131364476)));
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().s(1);
        }
        return;
    }

    public final boolean onCreateOptionsMenu(android.view.Menu p3)
    {
        this.getMenuInflater().inflate(2131623943, p3);
        return 1;
    }

    public final void setTitle(int p2)
    {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().x(p2);
        }
        return;
    }

    public final void setTitle(CharSequence p2)
    {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().y(p2);
        }
        return;
    }
}
