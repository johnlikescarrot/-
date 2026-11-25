package vn.ai.trueid.lottie.parser;
 class ContentModelParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static ContentModelParser()
    {
        vn.ai.trueid.lottie.parser.ContentModelParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"ty", "d"}));
        return;
    }

    private ContentModelParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ContentModel parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p7, vn.ai.trueid.lottie.LottieComposition p8)
    {
        p7.beginObject();
        String v0_0 = 2;
        int v1 = 2;
        while(true) {
            String v2_1;
            vn.ai.trueid.lottie.model.animatable.AnimatableTransform v4 = 0;
            if (!p7.hasNext()) {
                v2_1 = 0;
            } else {
                String v2_0 = p7.selectName(vn.ai.trueid.lottie.parser.ContentModelParser.NAMES);
                if (v2_0 == null) {
                    break;
                }
                if (v2_0 == 1) {
                    v1 = p7.nextInt();
                } else {
                    p7.skipName();
                    p7.skipValue();
                }
            }
            if (v2_1 != null) {
                switch (v2_1.hashCode()) {
                    case 3239:
                        if (v2_1.equals("el")) {
                            v0_0 = 0;
                        } else {
                            v0_0 = -1;
                        }
                        break;
                    case 3270:
                        if (v2_1.equals("fl")) {
                            v0_0 = 1;
                        } else {
                        }
                        break;
                    case 3295:
                        if (v2_1.equals("gf")) {
                        } else {
                        }
                        break;
                    case 3307:
                        if (v2_1.equals("gr")) {
                            v0_0 = 3;
                        } else {
                        }
                        break;
                    case 3308:
                        if (v2_1.equals("gs")) {
                            v0_0 = 4;
                        } else {
                        }
                        break;
                    case 3488:
                        if (v2_1.equals("mm")) {
                            v0_0 = 5;
                        } else {
                        }
                        break;
                    case 3633:
                        if (v2_1.equals("rc")) {
                            v0_0 = 6;
                        } else {
                        }
                        break;
                    case 3634:
                        if (v2_1.equals("rd")) {
                            v0_0 = 7;
                        } else {
                        }
                        break;
                    case 3646:
                        if (v2_1.equals("rp")) {
                            v0_0 = 8;
                        } else {
                        }
                        break;
                    case 3669:
                        if (v2_1.equals("sh")) {
                            v0_0 = 9;
                        } else {
                        }
                        break;
                    case 3679:
                        if (v2_1.equals("sr")) {
                            v0_0 = 10;
                        } else {
                        }
                        break;
                    case 3681:
                        if (v2_1.equals("st")) {
                            v0_0 = 11;
                        } else {
                        }
                        break;
                    case 3705:
                        if (v2_1.equals("tm")) {
                            v0_0 = 12;
                        } else {
                        }
                        break;
                    case 3710:
                        if (v2_1.equals("tr")) {
                            v0_0 = 13;
                        } else {
                        }
                        break;
                    default:
                }
                switch (v0_0) {
                    case 0:
                        v4 = vn.ai.trueid.lottie.parser.CircleShapeParser.parse(p7, p8, v1);
                        break;
                    case 1:
                        v4 = vn.ai.trueid.lottie.parser.ShapeFillParser.parse(p7, p8);
                        break;
                    case 2:
                        v4 = vn.ai.trueid.lottie.parser.GradientFillParser.parse(p7, p8);
                        break;
                    case 3:
                        v4 = vn.ai.trueid.lottie.parser.ShapeGroupParser.parse(p7, p8);
                        break;
                    case 4:
                        v4 = vn.ai.trueid.lottie.parser.GradientStrokeParser.parse(p7, p8);
                        break;
                    case 5:
                        v4 = vn.ai.trueid.lottie.parser.MergePathsParser.parse(p7);
                        p8.addWarning("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                        break;
                    case 6:
                        v4 = vn.ai.trueid.lottie.parser.RectangleShapeParser.parse(p7, p8);
                        break;
                    case 7:
                        v4 = vn.ai.trueid.lottie.parser.RoundedCornersParser.parse(p7, p8);
                        break;
                    case 8:
                        v4 = vn.ai.trueid.lottie.parser.RepeaterParser.parse(p7, p8);
                        break;
                    case 9:
                        v4 = vn.ai.trueid.lottie.parser.ShapePathParser.parse(p7, p8);
                        break;
                    case 10:
                        v4 = vn.ai.trueid.lottie.parser.PolystarShapeParser.parse(p7, p8, v1);
                        break;
                    case 11:
                        v4 = vn.ai.trueid.lottie.parser.ShapeStrokeParser.parse(p7, p8);
                        break;
                    case 12:
                        v4 = vn.ai.trueid.lottie.parser.ShapeTrimPathParser.parse(p7, p8);
                        break;
                    case 13:
                        v4 = vn.ai.trueid.lottie.parser.AnimatableTransformParser.parse(p7, p8);
                        break;
                    default:
                        vn.ai.trueid.lottie.utils.Logger.warning("Unknown shape type ".concat(v2_1));
                }
                while (p7.hasNext()) {
                    p7.skipValue();
                }
                p7.endObject();
                return v4;
            } else {
                return 0;
            }
        }
        v2_1 = p7.nextString();
    }
}
