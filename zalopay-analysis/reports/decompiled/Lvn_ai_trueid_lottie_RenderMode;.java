package vn.ai.trueid.lottie;
public final enum class RenderMode extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.RenderMode[] $VALUES;
    public static final enum vn.ai.trueid.lottie.RenderMode AUTOMATIC;
    public static final enum vn.ai.trueid.lottie.RenderMode HARDWARE;
    public static final enum vn.ai.trueid.lottie.RenderMode SOFTWARE;

    private static synthetic vn.ai.trueid.lottie.RenderMode[] $values()
    {
        return new vn.ai.trueid.lottie.RenderMode[] {vn.ai.trueid.lottie.RenderMode.AUTOMATIC, vn.ai.trueid.lottie.RenderMode.HARDWARE, vn.ai.trueid.lottie.RenderMode.SOFTWARE});
    }

    static RenderMode()
    {
        vn.ai.trueid.lottie.RenderMode.AUTOMATIC = new vn.ai.trueid.lottie.RenderMode(wmoi.igob.ggcn(15111), 0);
        vn.ai.trueid.lottie.RenderMode.HARDWARE = new vn.ai.trueid.lottie.RenderMode(wmoi.igob.ggcn(15112), 1);
        vn.ai.trueid.lottie.RenderMode.SOFTWARE = new vn.ai.trueid.lottie.RenderMode(wmoi.igob.ggcn(15113), 2);
        vn.ai.trueid.lottie.RenderMode.$VALUES = vn.ai.trueid.lottie.RenderMode.$values();
        return;
    }

    private RenderMode(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.RenderMode valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.RenderMode) Enum.valueOf(vn.ai.trueid.lottie.RenderMode, p1));
    }

    public static vn.ai.trueid.lottie.RenderMode[] values()
    {
        return ((vn.ai.trueid.lottie.RenderMode[]) vn.ai.trueid.lottie.RenderMode.$VALUES.clone());
    }

    public boolean useSoftwareRendering(int p5, boolean p6, int p7)
    {
        int v0_1 = vn.ai.trueid.lottie.RenderMode$1.$SwitchMap$com$airbnb$lottie$RenderMode[this.ordinal()];
        if (v0_1 == 1) {
            return 0;
        } else {
            if (v0_1 == 2) {
                return 1;
            } else {
                if ((p6 == 0) || (p5 >= 28)) {
                    if (p7 <= 4) {
                        if (p5 > 25) {
                            return 0;
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        }
    }
}
