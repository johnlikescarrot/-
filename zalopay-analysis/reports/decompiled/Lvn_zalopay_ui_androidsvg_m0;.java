package vn.zalopay.ui.androidsvg;
public abstract class m0 extends vn.zalopay.ui.androidsvg.X {

    public final void m(vn.zalopay.ui.androidsvg.c0 p4)
    {
        if (!(p4 instanceof vn.zalopay.ui.androidsvg.l0)) {
            StringBuilder v1_1 = new StringBuilder("Text content elements cannot contain ");
            v1_1.append(p4);
            v1_1.append(" elements.");
            throw new vn.zalopay.ui.androidsvg.SVGParseException(v1_1.toString());
        } else {
            this.i.add(p4);
            return;
        }
    }
}
