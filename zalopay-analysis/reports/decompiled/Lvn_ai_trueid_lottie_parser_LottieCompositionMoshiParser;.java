package vn.ai.trueid.lottie.parser;
public class LottieCompositionMoshiParser {
    static vn.ai.trueid.lottie.parser.moshi.JsonReader$Options ASSETS_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options FONT_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options MARKER_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static LottieCompositionMoshiParser()
    {
        String v2 = "ip";
        String v4 = "fr";
        String v6 = "layers";
        String v8 = "fonts";
        vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"w", "markers"}));
        String v3 = "w";
        vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.ASSETS_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"id", "u"}));
        vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.FONT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"list"}));
        vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.MARKER_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"cm", "tm", "dr"}));
        return;
    }

    public LottieCompositionMoshiParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.LottieComposition parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p23)
    {
        String v0_0 = p23;
        int v1_0 = vn.ai.trueid.lottie.utils.Utils.dpScale();
        P.o v8_1 = new P.o(0);
        java.util.ArrayList v7_1 = new java.util.ArrayList();
        java.util.HashMap v9_1 = new java.util.HashMap();
        java.util.HashMap v10_1 = new java.util.HashMap();
        java.util.HashMap v13_1 = new java.util.HashMap();
        java.util.ArrayList v14_1 = new java.util.ArrayList();
        int v2_1 = 0;
        P.O v12_1 = new P.O(0);
        android.graphics.Rect v3_1 = new vn.ai.trueid.lottie.LottieComposition();
        p23.beginObject();
        float v4 = 0;
        int v11_0 = 0;
        int v15 = 0;
        float v5 = 0;
        float v6 = 0;
        while (v0_0.hasNext()) {
            int v2_0;
            int v16_3 = v2_1;
            switch (v0_0.selectName(vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.NAMES)) {
                case 0:
                    v2_0 = v1_0;
                    v15 = ((int) p23.nextDouble());
                    v0_0 = p23;
                    v1_0 = v2_0;
                    break;
                case 1:
                    v2_0 = v1_0;
                    v11_0 = ((int) p23.nextDouble());
                    break;
                case 2:
                    v2_0 = v1_0;
                    v4 = ((float) p23.nextDouble());
                    break;
                case 3:
                    v2_0 = v1_0;
                    v5 = (((float) p23.nextDouble()) - 1008981770);
                    break;
                case 4:
                    v2_0 = v1_0;
                    v6 = ((float) p23.nextDouble());
                    break;
                case 5:
                    String v0_6 = v0_0.nextString().split("\\.");
                    if (vn.ai.trueid.lottie.utils.Utils.isAtLeastVersion(Integer.parseInt(v0_6[v16_3]), Integer.parseInt(v0_6[1]), Integer.parseInt(v0_6[2]), 4, 4, 0)) {
                        v0_0 = p23;
                    } else {
                        v3_1.addWarning("Lottie only supports bodymovin >= 4.4.0");
                    }
                    break;
                case 6:
                    vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.parseLayers(v0_0, v3_1, v7_1, v8_1);
                    break;
                case 7:
                    vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.parseAssets(v0_0, v3_1, v9_1, v10_1);
                    break;
                case 8:
                    vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.parseFonts(v0_0, v13_1);
                    break;
                case 9:
                    vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.parseChars(v0_0, v3_1, v12_1);
                    break;
                case 10:
                    vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.parseMarkers(v0_0, v14_1);
                    break;
                default:
                    v0_0.skipName();
                    v0_0.skipValue();
            }
            v2_1 = v16_3;
        }
        int v2_2 = v1_0;
        int v2_6 = v3_1;
        v2_6.init(new android.graphics.Rect(v2_1, v2_1, ((int) (((float) v15) * v2_2)), ((int) (((float) v11_0) * v2_2))), v4, v5, v6, v7_1, v8_1, v9_1, v10_1, vn.ai.trueid.lottie.utils.Utils.dpScale(), v12_1, v13_1, v14_1, v15, v11_0);
        return v2_6;
    }

    private static void parseAssets(vn.ai.trueid.lottie.parser.moshi.JsonReader p10, vn.ai.trueid.lottie.LottieComposition p11, java.util.Map p12, java.util.Map p13)
    {
        p10.beginArray();
        while (p10.hasNext()) {
            String v0_3 = new java.util.ArrayList();
            P.o v1_0 = new P.o(0);
            p10.beginObject();
            String v7 = 0;
            String v8 = 0;
            String v9 = 0;
            int v5 = 0;
            int v6 = 0;
            while (p10.hasNext()) {
                vn.ai.trueid.lottie.model.layer.Layer v2_2 = p10.selectName(vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.ASSETS_NAMES);
                if (v2_2 == null) {
                    v7 = p10.nextString();
                } else {
                    if (v2_2 == 1) {
                        p10.beginArray();
                        while (p10.hasNext()) {
                            vn.ai.trueid.lottie.model.layer.Layer v2_4 = vn.ai.trueid.lottie.parser.LayerParser.parse(p10, p11);
                            v1_0.i(v2_4.getId(), v2_4);
                            v0_3.add(v2_4);
                        }
                        p10.endArray();
                    } else {
                        if (v2_2 == 2) {
                            v5 = p10.nextInt();
                        } else {
                            if (v2_2 == 3) {
                                v6 = p10.nextInt();
                            } else {
                                if (v2_2 == 4) {
                                    v8 = p10.nextString();
                                } else {
                                    if (v2_2 == 5) {
                                        v9 = p10.nextString();
                                    } else {
                                        p10.skipName();
                                        p10.skipValue();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            p10.endObject();
            if (v8 == null) {
                p12.put(v7, v0_3);
            } else {
                vn.ai.trueid.lottie.LottieImageAsset v4_1 = new vn.ai.trueid.lottie.LottieImageAsset(v5, v6, v7, v8, v9);
                p13.put(v4_1.getId(), v4_1);
            }
        }
        p10.endArray();
        return;
    }

    private static void parseChars(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3, P.O p4)
    {
        p2.beginArray();
        while (p2.hasNext()) {
            vn.ai.trueid.lottie.model.FontCharacter v0_1 = vn.ai.trueid.lottie.parser.FontCharacterParser.parse(p2, p3);
            p4.j(v0_1.hashCode(), v0_1);
        }
        p2.endArray();
        return;
    }

    private static void parseFonts(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, java.util.Map p3)
    {
        p2.beginObject();
        while (p2.hasNext()) {
            if (p2.selectName(vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.FONT_NAMES) == 0) {
                p2.beginArray();
                while (p2.hasNext()) {
                    vn.ai.trueid.lottie.model.Font v0_1 = vn.ai.trueid.lottie.parser.FontParser.parse(p2);
                    p3.put(v0_1.getName(), v0_1);
                }
                p2.endArray();
            } else {
                p2.skipName();
                p2.skipValue();
            }
        }
        p2.endObject();
        return;
    }

    private static void parseLayers(vn.ai.trueid.lottie.parser.moshi.JsonReader p4, vn.ai.trueid.lottie.LottieComposition p5, java.util.List p6, P.o p7)
    {
        p4.beginArray();
        int v0 = 0;
        while (p4.hasNext()) {
            String v1_5 = vn.ai.trueid.lottie.parser.LayerParser.parse(p4, p5);
            if (v1_5.getLayerType() == vn.ai.trueid.lottie.model.layer.Layer$LayerType.IMAGE) {
                v0++;
            }
            p6.add(v1_5);
            p7.i(v1_5.getId(), v1_5);
            if (v0 > 4) {
                String v1_2 = new StringBuilder("You have ");
                v1_2.append(v0);
                v1_2.append(" images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
                vn.ai.trueid.lottie.utils.Logger.warning(v1_2.toString());
            }
        }
        p4.endArray();
        return;
    }

    private static void parseMarkers(vn.ai.trueid.lottie.parser.moshi.JsonReader p5, java.util.List p6)
    {
        p5.beginArray();
        while (p5.hasNext()) {
            p5.beginObject();
            float v0_1 = 0;
            String v2 = 0;
            float v1_0 = 0;
            while (p5.hasNext()) {
                double v3_4 = p5.selectName(vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.MARKER_NAMES);
                if (v3_4 == 0) {
                    v2 = p5.nextString();
                } else {
                    if (v3_4 == 1) {
                        v0_1 = ((float) p5.nextDouble());
                    } else {
                        if (v3_4 == 2) {
                            v1_0 = ((float) p5.nextDouble());
                        } else {
                            p5.skipName();
                            p5.skipValue();
                        }
                    }
                }
            }
            p5.endObject();
            p6.add(new vn.ai.trueid.lottie.model.Marker(v2, v0_1, v1_0));
        }
        p5.endArray();
        return;
    }
}
