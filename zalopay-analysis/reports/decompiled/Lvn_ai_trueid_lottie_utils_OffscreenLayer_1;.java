package vn.ai.trueid.lottie.utils;
synthetic class OffscreenLayer$1 {
    static final synthetic int[] $SwitchMap$com$airbnb$lottie$utils$OffscreenLayer$RenderStrategy;

    static OffscreenLayer$1()
    {
        int[] v0_5 = new int[vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.values().length];
        vn.ai.trueid.lottie.utils.OffscreenLayer$1.$SwitchMap$com$airbnb$lottie$utils$OffscreenLayer$RenderStrategy = v0_5;
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.DIRECT.ordinal()[int v1_7] = 1;
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.SAVE_LAYER.ordinal()[int v1_1] = 2;
        vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.BITMAP.ordinal()[int v1_3] = 3;
        try {
            vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.RENDER_NODE.ordinal()[int v1_5] = 4;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
