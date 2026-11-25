package vn.ai.trueid.lottie.parser;
public class DropShadowEffectParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options DROP_SHADOW_EFFECT_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options INNER_EFFECT_NAMES;
    private vn.ai.trueid.lottie.model.animatable.AnimatableColorValue color;
    private vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue direction;
    private vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue distance;
    private vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue opacity;
    private vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue radius;

    static DropShadowEffectParser()
    {
        vn.ai.trueid.lottie.parser.DropShadowEffectParser.DROP_SHADOW_EFFECT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"ef"}));
        vn.ai.trueid.lottie.parser.DropShadowEffectParser.INNER_EFFECT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "v"}));
        return;
    }

    public DropShadowEffectParser()
    {
        return;
    }

    private void maybeParseInnerEffect(vn.ai.trueid.lottie.parser.moshi.JsonReader p6, vn.ai.trueid.lottie.LottieComposition p7)
    {
        p6.beginObject();
        String v0 = "";
        while (p6.hasNext()) {
            vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v1_18 = p6.selectName(vn.ai.trueid.lottie.parser.DropShadowEffectParser.INNER_EFFECT_NAMES);
            if (v1_18 == null) {
                v0 = p6.nextString();
            } else {
                int v2 = 1;
                if (v1_18 == 1) {
                    v0.getClass();
                    switch (v0.hashCode()) {
                        case 353103893:
                            if (v0.equals("Distance")) {
                                v2 = 0;
                            } else {
                                v2 = -1;
                            }
                            break;
                        case 397447147:
                            if (v0.equals("Opacity")) {
                            }
                            break;
                        case 1041377119:
                            if (v0.equals("Direction")) {
                                v2 = 2;
                            }
                            break;
                        case 1379387491:
                            if (v0.equals("Shadow Color")) {
                                v2 = 3;
                            }
                            break;
                        case 1383710113:
                            if (v0.equals("Softness")) {
                                v2 = 4;
                            }
                            break;
                        default:
                    }
                    switch (v2) {
                        case 0:
                            this.distance = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p6, p7);
                            break;
                        case 1:
                            this.opacity = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p6, p7, 0);
                            break;
                        case 2:
                            this.direction = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p6, p7, 0);
                            break;
                        case 3:
                            this.color = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseColor(p6, p7);
                            break;
                        case 4:
                            this.radius = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p6, p7);
                            break;
                        default:
                            p6.skipValue();
                    }
                } else {
                    p6.skipName();
                    p6.skipValue();
                }
            }
        }
        p6.endObject();
        return;
    }

    public vn.ai.trueid.lottie.parser.DropShadowEffect parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p8, vn.ai.trueid.lottie.LottieComposition p9)
    {
        while (p8.hasNext()) {
            if (p8.selectName(vn.ai.trueid.lottie.parser.DropShadowEffectParser.DROP_SHADOW_EFFECT_NAMES) == 0) {
                p8.beginArray();
                while (p8.hasNext()) {
                    this.maybeParseInnerEffect(p8, p9);
                }
                p8.endArray();
            } else {
                p8.skipName();
                p8.skipValue();
            }
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableColorValue v2 = this.color;
        if (v2 != null) {
            vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v3 = this.opacity;
            if (v3 != null) {
                vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v4 = this.direction;
                if (v4 != null) {
                    vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v5 = this.distance;
                    if (v5 != null) {
                        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v6 = this.radius;
                        if (v6 != null) {
                            return new vn.ai.trueid.lottie.parser.DropShadowEffect(v2, v3, v4, v5, v6);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
