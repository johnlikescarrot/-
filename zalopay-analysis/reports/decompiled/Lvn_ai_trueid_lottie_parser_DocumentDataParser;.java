package vn.ai.trueid.lottie.parser;
public class DocumentDataParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.DocumentDataParser INSTANCE;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static DocumentDataParser()
    {
        vn.ai.trueid.lottie.parser.DocumentDataParser.INSTANCE = new vn.ai.trueid.lottie.parser.DocumentDataParser();
        String v3 = "s";
        String v5 = "tr";
        String v7 = "ls";
        String v9 = "sc";
        String v11 = "of";
        vn.ai.trueid.lottie.parser.DocumentDataParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"t", "sz"}));
        return;
    }

    private DocumentDataParser()
    {
        return;
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }

    public vn.ai.trueid.lottie.model.DocumentData parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p20, float p21)
    {
        p20.beginObject();
        vn.ai.trueid.lottie.model.DocumentData$Justification v9 = vn.ai.trueid.lottie.model.DocumentData$Justification.CENTER;
        String v6 = 0;
        String v7 = 0;
        android.graphics.PointF v17 = 0;
        android.graphics.PointF v18 = 0;
        float v8 = 0;
        float v11 = 0;
        float v12 = 0;
        float v15 = 0;
        int v10 = 0;
        int v13 = 0;
        int v14 = 0;
        boolean v16 = 1;
        while (p20.hasNext()) {
            switch (p20.selectName(vn.ai.trueid.lottie.parser.DocumentDataParser.NAMES)) {
                case 0:
                    v6 = p20.nextString();
                    break;
                case 1:
                    v7 = p20.nextString();
                    break;
                case 2:
                    v8 = ((float) p20.nextDouble());
                    break;
                case 3:
                    android.graphics.PointF v0_8 = p20.nextInt();
                    v9 = vn.ai.trueid.lottie.model.DocumentData$Justification.CENTER;
                    if ((v0_8 <= v9.ordinal()) && (v0_8 >= null)) {
                        v9 = vn.ai.trueid.lottie.model.DocumentData$Justification.values()[v0_8];
                    }
                    break;
                case 4:
                    v10 = p20.nextInt();
                    break;
                case 5:
                    v11 = ((float) p20.nextDouble());
                    break;
                case 6:
                    v12 = ((float) p20.nextDouble());
                    break;
                case 7:
                    v13 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToColor(p20);
                    break;
                case 8:
                    v14 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToColor(p20);
                    break;
                case 9:
                    v15 = ((float) p20.nextDouble());
                    break;
                case 10:
                    v16 = p20.nextBoolean();
                    break;
                case 11:
                    p20.beginArray();
                    android.graphics.PointF v0_7 = new android.graphics.PointF((((float) p20.nextDouble()) * p21), (((float) p20.nextDouble()) * p21));
                    p20.endArray();
                    v17 = v0_7;
                    break;
                case 12:
                    p20.beginArray();
                    android.graphics.PointF v0_5 = new android.graphics.PointF((((float) p20.nextDouble()) * p21), (((float) p20.nextDouble()) * p21));
                    p20.endArray();
                    v18 = v0_5;
                    break;
                default:
                    p20.skipName();
                    p20.skipValue();
            }
        }
        p20.endObject();
        return new vn.ai.trueid.lottie.model.DocumentData(v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18);
    }
}
