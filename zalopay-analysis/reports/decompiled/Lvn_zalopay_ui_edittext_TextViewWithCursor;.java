package vn.zalopay.ui.edittext;
public final class TextViewWithCursor extends android.widget.EditText {

    public TextViewWithCursor(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.requestFocus();
        super.setShowSoftInputOnFocus(0);
        return;
    }

    public final void onSelectionChanged(int p2, int p3)
    {
        int v0_1;
        int v0_0 = this.getText();
        if (v0_0 == 0) {
            v0_1 = 0;
        } else {
            v0_1 = v0_0.length();
        }
        if ((p2 == v0_1) && (p3 == v0_1)) {
            super.onSelectionChanged(p2, p3);
            return;
        } else {
            this.setSelection(v0_1, v0_1);
            return;
        }
    }

    public final void onTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        super.onTextChanged(p1, p2, p3, p4);
        int v1_1 = this.getText();
        if (v1_1 != 0) {
            this.setSelection(v1_1.length());
        }
        return;
    }
}
