package vn.com.vng.zalopay.widget;
public class AppInfoHeaderView_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.widget.AppInfoHeaderView b;

    public AppInfoHeaderView_ViewBinding(vn.com.vng.zalopay.widget.AppInfoHeaderView p6, android.view.View p7)
    {
        this.b = p6;
        p6.mLlIcon = ((android.widget.LinearLayout) b4.e.c(p7, 2131363541, "field \'mLlIcon\'", android.widget.LinearLayout));
        p6.mIconFont = ((vn.zalopay.ui.svgutil.ZPSVGImageView) b4.e.a(b4.e.b(p7, 2131363032, "field \'mIconFont\'"), 2131363032, "field \'mIconFont\'", vn.zalopay.ui.svgutil.ZPSVGImageView));
        p6.mLineConnector = b4.e.b(p7, 2131363477, "field \'mLineConnector\'");
        p6.mLlText = ((android.widget.LinearLayout) b4.e.a(b4.e.b(p7, 2131363551, "field \'mLlText\'"), 2131363551, "field \'mLlText\'", android.widget.LinearLayout));
        p6.mTvTitle = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364982, "field \'mTvTitle\'"), 2131364982, "field \'mTvTitle\'", android.widget.TextView));
        p6.mTvDesFirst = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364851, "field \'mTvDesFirst\'"), 2131364851, "field \'mTvDesFirst\'", android.widget.TextView));
        p6.mTvDesSecond = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364852, "field \'mTvDesSecond\'"), 2131364852, "field \'mTvDesSecond\'", android.widget.TextView));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.mLlIcon = 0;
            v0_0.mIconFont = 0;
            v0_0.mLineConnector = 0;
            v0_0.mLlText = 0;
            v0_0.mTvTitle = 0;
            v0_0.mTvDesFirst = 0;
            v0_0.mTvDesSecond = 0;
            return;
        }
    }
}
