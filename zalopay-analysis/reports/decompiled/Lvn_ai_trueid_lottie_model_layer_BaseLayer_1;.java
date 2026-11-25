package vn.ai.trueid.lottie.model.layer;
synthetic class BaseLayer$1 {
    static final synthetic int[] $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode;
    static final synthetic int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType;

    static BaseLayer$1()
    {
        int[] v0_3 = new int[vn.ai.trueid.lottie.model.content.Mask$MaskMode.values().length];
        vn.ai.trueid.lottie.model.layer.BaseLayer$1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode = v0_3;
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_NONE.ordinal()[int v2_6] = 1;
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_SUBTRACT.ordinal()[int v3_1] = 2;
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_INTERSECT.ordinal()[int v4_1] = 3;
        try {
            vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_ADD.ordinal()[int v5_1] = 4;
        } catch (NoSuchFieldError) {
        }
        int v4_5 = new int[vn.ai.trueid.lottie.model.layer.Layer$LayerType.values().length];
        vn.ai.trueid.lottie.model.layer.BaseLayer$1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType = v4_5;
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.SHAPE.ordinal()[int v5_3] = 1;
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.PRE_COMP.ordinal()[int v4_7] = 2;
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.SOLID.ordinal()[int v1_2] = 3;
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.IMAGE.ordinal()[int v1_5] = 4;
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.NULL.ordinal()[int v1_7] = 5;
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.TEXT.ordinal()[int v1_9] = 6;
        try {
            vn.ai.trueid.lottie.model.layer.Layer$LayerType.UNKNOWN.ordinal()[int v1_11] = 7;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
