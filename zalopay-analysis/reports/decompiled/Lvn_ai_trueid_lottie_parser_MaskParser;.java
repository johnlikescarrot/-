package vn.ai.trueid.lottie.parser;
 class MaskParser {

    private MaskParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.Mask parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p11, vn.ai.trueid.lottie.LottieComposition p12)
    {
        p11.beginObject();
        vn.ai.trueid.lottie.model.content.Mask$MaskMode v0_0 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue v2 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v3 = 0;
        boolean v4 = 0;
        while (p11.hasNext()) {
            String v6_3;
            String v5_0 = p11.nextName();
            v5_0.getClass();
            int v7 = 3;
            switch (v5_0.hashCode()) {
                case 111:
                    if (v5_0.equals("o")) {
                        v6_3 = 0;
                    } else {
                        v6_3 = -1;
                    }
                    break;
                case 3588:
                    if (v5_0.equals("pt")) {
                        v6_3 = 1;
                    }
                    break;
                case 104433:
                    if (v5_0.equals("inv")) {
                        v6_3 = 2;
                    }
                    break;
                case 3357091:
                    if (v5_0.equals("mode")) {
                        v6_3 = 3;
                    }
                    break;
                default:
            }
            switch (v6_3) {
                case 0:
                    v3 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p11, p12);
                    break;
                case 1:
                    v2 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseShapeData(p11, p12);
                    break;
                case 2:
                    v4 = p11.nextBoolean();
                    break;
                case 3:
                    vn.ai.trueid.lottie.model.content.Mask$MaskMode v0_1 = p11.nextString();
                    v0_1.getClass();
                    switch (v0_1.hashCode()) {
                        case 97:
                            if (v0_1.equals("a")) {
                                v7 = 0;
                            } else {
                                v7 = -1;
                            }
                            break;
                        case 105:
                            if (v0_1.equals("i")) {
                                v7 = 1;
                            }
                            break;
                        case 110:
                            if (v0_1.equals("n")) {
                                v7 = 2;
                            }
                            break;
                        case 115:
                            if (v0_1.equals("s")) {
                            }
                            break;
                        default:
                    }
                    switch (v7) {
                        case 0:
                            v0_0 = vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_ADD;
                            break;
                        case 1:
                            p12.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            v0_0 = vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_INTERSECT;
                            break;
                        case 2:
                            v0_0 = vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_NONE;
                            break;
                        case 3:
                            v0_0 = vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_SUBTRACT;
                            break;
                        default:
                            vn.ai.trueid.lottie.model.content.Mask$MaskMode v0_8 = new StringBuilder("Unknown mask mode ");
                            v0_8.append(v5_0);
                            v0_8.append(". Defaulting to Add.");
                            vn.ai.trueid.lottie.utils.Logger.warning(v0_8.toString());
                            v0_0 = vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_ADD;
                    }
                    break;
                default:
                    p11.skipValue();
            }
        }
        p11.endObject();
        return new vn.ai.trueid.lottie.model.content.Mask(v0_0, v2, v3, v4);
    }
}
