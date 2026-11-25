package vn.zalopay.ui.countdown;
public final class CountDownTextView extends android.widget.TextView implements androidx.lifecycle.E {

    public CountDownTextView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.getText();
        if ((super.getContext() instanceof androidx.lifecycle.F)) {
            ((androidx.lifecycle.F) super.getContext()).getLifecycle().a(super);
        }
        return;
    }

    public final void onDestroy()
    {
        return;
    }
}
