package vn.zalopay.ui.androidsvg;
public abstract class z extends vn.zalopay.ui.androidsvg.a0 implements vn.zalopay.ui.androidsvg.Y {
    public java.util.List h;
    public Boolean i;
    public android.graphics.Matrix j;
    public int k;
    public String l;

    public z()
    {
        this.h = new java.util.ArrayList();
        return;
    }

    public final java.util.List getChildren()
    {
        return this.h;
    }

    public final void m(vn.zalopay.ui.androidsvg.c0 p4)
    {
        if (!(p4 instanceof vn.zalopay.ui.androidsvg.T)) {
            StringBuilder v1_1 = new StringBuilder(wmoi.igob.ggcn(2960));
            v1_1.append(p4);
            v1_1.append(wmoi.igob.ggcn(2961));
            throw new vn.zalopay.ui.androidsvg.SVGParseException(v1_1.toString());
        } else {
            this.h.add(p4);
            return;
        }
    }
}
