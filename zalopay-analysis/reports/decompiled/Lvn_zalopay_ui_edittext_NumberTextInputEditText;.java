package vn.zalopay.ui.edittext;
public final class NumberTextInputEditText extends com.google.android.material.textfield.TextInputEditText implements GZ.l {

    public NumberTextInputEditText(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        super.setShowSoftInputOnFocus(0);
        Ek.U v2_5 = new GZ.g();
        v2_5.b = new Am.b(14, super);
        super.addTextChangedListener(v2_5);
        super.setOnTouchListener(new Ek.U(1, super));
        return;
    }

    public final void b()
    {
        if ((!android.text.TextUtils.isEmpty(this.getInputText())) && (this.getSelectionStart() >= 1)) {
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
