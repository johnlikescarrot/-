package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class SpiralLineView extends android.view.View {
    public final android.graphics.Paint a;
    public final android.graphics.Bitmap b;

    public SpiralLineView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        int v3_3 = new android.graphics.Paint(1024);
        super.setBackground(p2.getDrawable(2131100832));
        super.a = v3_3;
        super.b = m9.A.d(p2, -2079981493);
        return;
    }

    public final void onDraw(android.graphics.Canvas p4)
    {
        android.graphics.Bitmap v0 = this.b;
        if (v0 != null) {
            p4.drawBitmap(v0, 0, 0, this.a);
        }
        return;
    }
}
