package vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys;
public final class OptionsUIState {
    public static final int $stable;
    private final Integer id;
    private final String imgUrl;
    private final boolean isChecked;
    private final String name;
    private final int position;

    public OptionsUIState()
    {
        this(0, 0, 0, 0, 0, 31, 0);
        return;
    }

    public OptionsUIState(String p1, String p2, Integer p3, boolean p4, int p5)
    {
        this.name = p1;
        this.imgUrl = p2;
        this.id = p3;
        this.isChecked = p4;
        this.position = p5;
        return;
    }

    public synthetic OptionsUIState(String p2, String p3, Integer p4, boolean p5, int p6, int p7, kotlin.jvm.internal.DefaultConstructorMarker p8)
    {
        if ((p7 & 1) != 0) {
            p2 = "";
        }
        if ((p7 & 2) != 0) {
            p3 = "";
        }
        if ((p7 & 4) != 0) {
            p4 = Integer.valueOf(0);
        }
        if ((p7 & 8) != 0) {
            p5 = 0;
        }
        int v7_2;
        if ((p7 & 16) == 0) {
            v7_2 = p6;
        } else {
            v7_2 = 0;
        }
        this(p2, p3, p4, p5, v7_2);
        return;
    }

    public static synthetic vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState copy$default(vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState p0, String p1, String p2, Integer p3, boolean p4, int p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.name;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.imgUrl;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.id;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.isChecked;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.position;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.name;
    }

    public final String component2()
    {
        return this.imgUrl;
    }

    public final Integer component3()
    {
        return this.id;
    }

    public final boolean component4()
    {
        return this.isChecked;
    }

    public final int component5()
    {
        return this.position;
    }

    public final vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState copy(String p7, String p8, Integer p9, boolean p10, int p11)
    {
        return new vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState(p7, p8, p9, p10, p11);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState) p5).name)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.imgUrl, ((vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState) p5).imgUrl)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.id, ((vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState) p5).id)) {
                            if (this.isChecked == ((vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState) p5).isChecked) {
                                if (this.position == ((vn.com.vng.zalopay.homev4.presentation.ui.state.recommendsys.OptionsUIState) p5).position) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            } else {
                                return 0;
                            }
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final Integer getId()
    {
        return this.id;
    }

    public final String getImgUrl()
    {
        return this.imgUrl;
    }

    public final String getName()
    {
        return this.name;
    }

    public final int getPosition()
    {
        return this.position;
    }

    public int hashCode()
    {
        boolean v2_0;
        int v0_6 = e5.i.c((this.name.hashCode() * 31), 31, this.imgUrl);
        boolean v2_3 = this.id;
        if (v2_3) {
            v2_0 = v2_3.hashCode();
        } else {
            v2_0 = 0;
        }
        return (Integer.hashCode(this.position) + Kb.e.i(((v0_6 + v2_0) * 31), 31, this.isChecked));
    }

    public final boolean isChecked()
    {
        return this.isChecked;
    }

    public String toString()
    {
        Integer v2 = this.id;
        boolean v3 = this.isChecked;
        int v4 = this.position;
        String v0_2 = androidx.camera.camera2.internal.G.i("OptionsUIState(name=", this.name, ", imgUrl=", this.imgUrl, ", id=");
        v0_2.append(v2);
        v0_2.append(", isChecked=");
        v0_2.append(v3);
        v0_2.append(", position=");
        return A1.a.h(v4, ")", v0_2);
    }
}
