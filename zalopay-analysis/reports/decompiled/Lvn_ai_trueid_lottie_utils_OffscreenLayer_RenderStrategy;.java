package vn.ai.trueid.lottie.utils;
public final enum class OffscreenLayer$RenderStrategy extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy[] $VALUES;
    public static final enum vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy BITMAP;
    public static final enum vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy DIRECT;
    public static final enum vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy RENDER_NODE;
    public static final enum vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy SAVE_LAYER;

    private static synthetic vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy[] $values()
    {
        return new vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy[] {vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.DIRECT, vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.SAVE_LAYER, vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.BITMAP, vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.RENDER_NODE});
    }

    static OffscreenLayer$RenderStrategy()
    {
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.DIRECT = new vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy(wmoi.igob.ggcn(15030), 0);
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.SAVE_LAYER = new vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy(wmoi.igob.ggcn(15031), 1);
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.BITMAP = new vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy(wmoi.igob.ggcn(15032), 2);
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.RENDER_NODE = new vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy(wmoi.igob.ggcn(15033), 3);
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.$VALUES = vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.$values();
        return;
    }

    private OffscreenLayer$RenderStrategy(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy) Enum.valueOf(vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy, p1));
    }

    public static vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy[] values()
    {
        return ((vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy[]) vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.$VALUES.clone());
    }
}
