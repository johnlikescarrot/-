package vn.ai.trueid.lottie.parser;
 class JsonUtils {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options POINT_NAMES;

    static JsonUtils()
    {
        vn.ai.trueid.lottie.parser.JsonUtils.POINT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"x", "y"}));
        return;
    }

    private JsonUtils()
    {
        return;
    }

    private static android.graphics.PointF jsonArrayToPoint(vn.ai.trueid.lottie.parser.moshi.JsonReader p4, float p5)
    {
        p4.beginArray();
        float v0_2 = ((float) p4.nextDouble());
        float v1_2 = ((float) p4.nextDouble());
        while (p4.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_ARRAY) {
            p4.skipValue();
        }
        p4.endArray();
        return new android.graphics.PointF((v0_2 * p5), (v1_2 * p5));
    }

    private static android.graphics.PointF jsonNumbersToPoint(vn.ai.trueid.lottie.parser.moshi.JsonReader p3, float p4)
    {
        float v0_2 = ((float) p3.nextDouble());
        float v1_2 = ((float) p3.nextDouble());
        while (p3.hasNext()) {
            p3.skipValue();
        }
        return new android.graphics.PointF((v0_2 * p4), (v1_2 * p4));
    }

    private static android.graphics.PointF jsonObjectToPoint(vn.ai.trueid.lottie.parser.moshi.JsonReader p4, float p5)
    {
        p4.beginObject();
        float v0_0 = 0;
        float v1_0 = 0;
        while (p4.hasNext()) {
            int v2_1 = p4.selectName(vn.ai.trueid.lottie.parser.JsonUtils.POINT_NAMES);
            if (v2_1 == 0) {
                v0_0 = vn.ai.trueid.lottie.parser.JsonUtils.valueFromObject(p4);
            } else {
                if (v2_1 == 1) {
                    v1_0 = vn.ai.trueid.lottie.parser.JsonUtils.valueFromObject(p4);
                } else {
                    p4.skipName();
                    p4.skipValue();
                }
            }
        }
        p4.endObject();
        return new android.graphics.PointF((v0_0 * p5), (v1_0 * p5));
    }

    public static int jsonToColor(vn.ai.trueid.lottie.parser.moshi.JsonReader p6)
    {
        p6.beginArray();
        int v0_2 = ((int) (p6.nextDouble() * 4643176031446892544));
        int v1 = ((int) (p6.nextDouble() * 4643176031446892544));
        int v2_0 = ((int) (p6.nextDouble() * 4643176031446892544));
        while (p6.hasNext()) {
            p6.skipValue();
        }
        p6.endArray();
        return android.graphics.Color.argb(255, v0_2, v1, v2_0);
    }

    public static android.graphics.PointF jsonToPoint(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, float p3)
    {
        StringBuilder v0_3 = vn.ai.trueid.lottie.parser.JsonUtils$1.$SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token[p2.peek().ordinal()];
        if (v0_3 == 1) {
            return vn.ai.trueid.lottie.parser.JsonUtils.jsonNumbersToPoint(p2, p3);
        } else {
            if (v0_3 == 2) {
                return vn.ai.trueid.lottie.parser.JsonUtils.jsonArrayToPoint(p2, p3);
            } else {
                if (v0_3 != 3) {
                    StringBuilder v0_2 = new StringBuilder("Unknown point starts with ");
                    v0_2.append(p2.peek());
                    throw new IllegalArgumentException(v0_2.toString());
                } else {
                    return vn.ai.trueid.lottie.parser.JsonUtils.jsonObjectToPoint(p2, p3);
                }
            }
        }
    }

    public static java.util.List jsonToPoints(vn.ai.trueid.lottie.parser.moshi.JsonReader p3, float p4)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        p3.beginArray();
        while (p3.peek() == vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
            p3.beginArray();
            v0_1.add(vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p3, p4));
            p3.endArray();
        }
        p3.endArray();
        return v0_1;
    }

    public static float valueFromObject(vn.ai.trueid.lottie.parser.moshi.JsonReader p3)
    {
        String v0_0 = p3.peek();
        boolean v1_4 = vn.ai.trueid.lottie.parser.JsonUtils$1.$SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token[v0_0.ordinal()];
        if (v1_4 == 1) {
            return ((float) p3.nextDouble());
        } else {
            if (v1_4 != 2) {
                boolean v1_1 = new StringBuilder("Unknown value for token of type ");
                v1_1.append(v0_0);
                throw new IllegalArgumentException(v1_1.toString());
            } else {
                p3.beginArray();
                String v0_3 = ((float) p3.nextDouble());
                while (p3.hasNext()) {
                    p3.skipValue();
                }
                p3.endArray();
                return v0_3;
            }
        }
    }
}
