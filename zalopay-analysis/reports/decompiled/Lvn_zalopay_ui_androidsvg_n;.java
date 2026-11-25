package vn.zalopay.ui.androidsvg;
public final class n {
    public java.util.ArrayList a;
    public int b;

    public n()
    {
        this.a = 0;
        this.b = 0;
        return;
    }

    public final void a()
    {
        this.b = (this.b + 1000);
        return;
    }

    public final String toString()
    {
        String v0_1 = new StringBuilder();
        int v1_2 = this.a.iterator();
        while (v1_2.hasNext()) {
            v0_1.append(((vn.zalopay.ui.androidsvg.o) v1_2.next()));
            v0_1.append(32);
        }
        v0_1.append(91);
        return SP.n.l(v0_1, this.b, 93);
    }
}
