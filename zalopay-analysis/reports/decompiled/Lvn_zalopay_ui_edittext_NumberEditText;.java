package vn.zalopay.ui.edittext;
public final class NumberEditText extends androidx.appcompat.widget.AppCompatEditText implements GZ.l {

    public NumberEditText(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.setShowSoftInputOnFocus(0);
        return;
    }

    public final void b()
    {
        if ((this.getInputText().length() != 0) && (this.getSelectionStart() >= 1)) {
            String v0_1 = this.getSelectionStart();
            int v1_0 = (v0_1 - 1);
            this.setInputText(kotlin.text.StringsKt.s(v1_0, v0_1, this.getInputText()).toString());
            this.setSelection(v1_0);
        }
        return;
    }

    public final void c(int p4)
    {
        int v0_0 = this.getSelectionStart();
        if (this.getSelectionStart() == this.getInputText().length()) {
            this.append(String.valueOf(p4));
            return;
        } else {
            int v4_2;
            android.text.Editable v1_0 = this.getText();
            if (v1_0 == null) {
                v4_2 = 0;
            } else {
                v4_2 = v1_0.insert(v0_0, String.valueOf(p4));
            }
            this.setText(v4_2);
            this.setSelection((v0_0 + 1));
            return;
        }
    }

    public String getInputText()
    {
        return String.valueOf(super.getText());
    }

    public void setInputText(String p1)
    {
        this.setText(p1);
        return;
    }
}
