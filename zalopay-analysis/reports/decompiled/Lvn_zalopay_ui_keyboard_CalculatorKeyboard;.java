package vn.zalopay.ui.keyboard;
public class CalculatorKeyboard extends vn.zalopay.ui.keyboard.NumberKeyboard {

    public CalculatorKeyboard(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public final void a(android.content.Context p3)
    {
        this.d(android.view.LayoutInflater.from(p3).inflate(2131560372, this, 1));
        return;
    }

    public final void b()
    {
        super.b();
        android.util.SparseArray v1 = this.c;
        v1.put(2131362213, String.valueOf(43));
        v1.put(2131362212, String.valueOf(45));
        v1.put(2131362207, String.valueOf(247));
        v1.put(2131362211, String.valueOf(120));
        return;
    }

    public final void d(android.view.View p3)
    {
        super.d(p3);
        this.b.add(p3.findViewById(2131362213));
        this.b.add(p3.findViewById(2131362212));
        this.b.add(p3.findViewById(2131362211));
        this.b.add(p3.findViewById(2131362207));
        this.b.add(p3.findViewById(2131362203));
        return;
    }
}
