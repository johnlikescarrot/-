package vn.com.vng.zalopay.passport.widget;
public class CustomTextView extends androidx.appcompat.widget.AppCompatEditText implements GZ.l {

    public CustomTextView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public final void b()
    {
        int v0_0 = this.getInputText();
        if (!android.text.TextUtils.isEmpty(v0_0)) {
            int v0_1 = v0_0.substring(0, (v0_0.length() - 1));
            this.setInputText(v0_1);
            this.setSelection(v0_1.length());
            return;
        } else {
            return;
        }
    }

    public final void c(int p1)
    {
        this.append(String.valueOf(p1));
        return;
    }

    public final boolean dispatchTouchEvent(android.view.MotionEvent p1)
    {
        return 1;
    }

    public String getInputText()
    {
        String v0_0 = super.getText();
        java.util.Objects.requireNonNull(v0_0);
        return ((android.text.Editable) v0_0).toString();
    }

    public final boolean onTouchEvent(android.view.MotionEvent p1)
    {
        return 1;
    }

    public void setInputText(String p1)
    {
        super.setText(p1);
        return;
    }
}
