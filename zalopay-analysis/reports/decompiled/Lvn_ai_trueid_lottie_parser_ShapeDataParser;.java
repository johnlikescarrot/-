package vn.ai.trueid.lottie.parser;
public class ShapeDataParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.ShapeDataParser INSTANCE;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static ShapeDataParser()
    {
        vn.ai.trueid.lottie.parser.ShapeDataParser.INSTANCE = new vn.ai.trueid.lottie.parser.ShapeDataParser();
        vn.ai.trueid.lottie.parser.ShapeDataParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"c", "v", "i", "o"}));
        return;
    }

    private ShapeDataParser()
    {
        return;
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }

    public vn.ai.trueid.lottie.model.content.ShapeData parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p13, float p14)
    {
        if (p13.peek() == vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
            p13.beginArray();
        }
        p13.beginObject();
        android.graphics.PointF v0_4 = 0;
        java.util.List v2 = 0;
        java.util.List v3 = 0;
        boolean v4 = 0;
        while (p13.hasNext()) {
            java.util.ArrayList v5_5 = p13.selectName(vn.ai.trueid.lottie.parser.ShapeDataParser.NAMES);
            if (v5_5 == null) {
                v4 = p13.nextBoolean();
            } else {
                if (v5_5 == 1) {
                    v0_4 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoints(p13, p14);
                } else {
                    if (v5_5 == 2) {
                        v2 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoints(p13, p14);
                    } else {
                        if (v5_5 == 3) {
                            v3 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoints(p13, p14);
                        } else {
                            p13.skipName();
                            p13.skipValue();
                        }
                    }
                }
            }
        }
        p13.endObject();
        if (p13.peek() == vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_ARRAY) {
            p13.endArray();
        }
        if ((v0_4 == null) || ((v2 == null) || (v3 == null))) {
            throw new IllegalArgumentException("Shape data was missing information.");
        } else {
            if (!v0_4.isEmpty()) {
                vn.ai.trueid.lottie.model.content.ShapeData v13_4 = v0_4.size();
                android.graphics.PointF v14_4 = ((android.graphics.PointF) v0_4.get(0));
                java.util.ArrayList v5_3 = new java.util.ArrayList(v13_4);
                android.graphics.PointF v7_0 = 1;
                while (v7_0 < v13_4) {
                    android.graphics.PointF v8_1 = ((android.graphics.PointF) v0_4.get(v7_0));
                    android.graphics.PointF v9_0 = (v7_0 - 1);
                    v5_3.add(new vn.ai.trueid.lottie.model.CubicCurveData(vn.ai.trueid.lottie.utils.MiscUtils.addPoints(((android.graphics.PointF) v0_4.get(v9_0)), ((android.graphics.PointF) v3.get(v9_0))), vn.ai.trueid.lottie.utils.MiscUtils.addPoints(v8_1, ((android.graphics.PointF) v2.get(v7_0))), v8_1));
                    v7_0++;
                }
                if (v4) {
                    android.graphics.PointF v7_2 = ((android.graphics.PointF) v0_4.get(0));
                    vn.ai.trueid.lottie.model.content.ShapeData v13_5 = (v13_4 - 1);
                    v5_3.add(new vn.ai.trueid.lottie.model.CubicCurveData(vn.ai.trueid.lottie.utils.MiscUtils.addPoints(((android.graphics.PointF) v0_4.get(v13_5)), ((android.graphics.PointF) v3.get(v13_5))), vn.ai.trueid.lottie.utils.MiscUtils.addPoints(v7_2, ((android.graphics.PointF) v2.get(0))), v7_2));
                }
                return new vn.ai.trueid.lottie.model.content.ShapeData(v14_4, v4, v5_3);
            } else {
                return new vn.ai.trueid.lottie.model.content.ShapeData(new android.graphics.PointF(), 0, java.util.Collections.EMPTY_LIST);
            }
        }
    }
}
