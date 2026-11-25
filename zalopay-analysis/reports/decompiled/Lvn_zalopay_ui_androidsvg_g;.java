package vn.zalopay.ui.androidsvg;
public final class g implements vn.zalopay.ui.androidsvg.e {
    public final synthetic int a;

    public synthetic g(int p1)
    {
        this.a = p1;
        return;
    }

    public final boolean a(vn.zalopay.ui.androidsvg.a0 p2)
    {
        switch (this.a) {
            case 0:
                if (((p2 instanceof vn.zalopay.ui.androidsvg.Y)) && (((vn.zalopay.ui.androidsvg.Y) p2).getChildren().size() != 0)) {
                    int v2_3 = 0;
                } else {
                    v2_3 = 1;
                }
                return v2_3;
            case 1:
                int v2_6;
                if (p2.b != null) {
                    v2_6 = 0;
                } else {
                    v2_6 = 1;
                }
                return v2_6;
            default:
                return 0;
        }
    }

    public final String toString()
    {
        switch (this.a) {
            case 0:
                return "empty";
            case 1:
                return "root";
            default:
                return "target";
        }
    }
}
