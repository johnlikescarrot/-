package vn.ai.trueid.sdk.face.present.presentation.utils;
public final class UtilsKt {

    public static final void bindHtmlByKey(android.widget.TextView p4, String p5)
    {
        try {
            int v0_0 = p4.getContext();
            p4.setText(android.text.Html.fromHtml(v0_0.getString(v0_0.getResources().getIdentifier(p5, "string", v0_0.getPackageName())), 0));
            return;
        } catch (Exception v4_1) {
            v4_1.printStackTrace();
            return;
        }
    }

    public static final void bindHtmlToString(android.widget.TextView p1, String p2)
    {
        try {
            p1.setText(android.text.Html.fromHtml(p2, 0));
            return;
        } catch (Exception v1_1) {
            v1_1.printStackTrace();
            return;
        }
    }

    public static final String getTextByKey(android.content.Context p2, String p3, org.json.JSONObject p4)
    {
        if ((p4 == null) || ((!vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.isStringKeyExists(p2, p3)) || (p4.optString(p3, "").length() <= 0))) {
            return p2.getString(p2.getResources().getIdentifier(p3, "string", p2.getPackageName()));
        } else {
            return p4.getString(p3);
        }
    }

    public static final boolean isStringKeyExists(android.content.Context p2, String p3)
    {
        if (p2.getResources().getIdentifier(p3, "string", p2.getPackageName()) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static final void setTextByKey(android.widget.TextView p2, String p3, org.json.JSONObject p4)
    {
        if ((p4 == null) || ((!vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.isStringKeyExists(p2.getContext(), p3)) || (p4.optString(p3, "").length() <= 0))) {
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlByKey(p2, p3);
            return;
        } else {
            vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(p2, p4.optString(p3, ""));
            return;
        }
    }

    public static final String toBase64(android.graphics.Bitmap p3)
    {
        int v0_1 = new java.io.ByteArrayOutputStream();
        p3.compress(android.graphics.Bitmap$CompressFormat.PNG, 100, v0_1);
        return android.util.Base64.encodeToString(v0_1.toByteArray(), 2);
    }

    public static final java.util.Map toMap(org.json.JSONObject p9)
    {
        java.util.Iterator v0_1 = ci.o.b(p9.keys());
        java.util.LinkedHashMap v1_1 = new java.util.LinkedHashMap();
        java.util.Iterator v0_2 = v0_1.iterator();
        while (v0_2.hasNext()) {
            Object v2_0 = v0_2.next();
            int v3_2 = p9.get(((String) v2_0));
            if (!(v3_2 instanceof org.json.JSONArray)) {
                if (!(v3_2 instanceof org.json.JSONObject)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(v3_2, org.json.JSONObject.NULL)) {
                        v3_2 = 0;
                    }
                } else {
                    v3_2 = vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.toMap(((org.json.JSONObject) v3_2));
                }
            } else {
                int v3_4 = ((org.json.JSONArray) v3_2);
                boolean v4_5 = Og.x.until(0, v3_4.length());
                java.util.LinkedHashMap v6_2 = new java.util.LinkedHashMap(Og.x.coerceAtLeast(kotlin.collections.Z.mapCapacity(kotlin.collections.F.collectionSizeOrDefault(v4_5, 10)), 16));
                boolean v4_6 = v4_5.x();
                while (v4_6.c) {
                    Object v5_6 = v4_6.nextInt();
                    Object v7_1 = new kotlin.Pair(String.valueOf(v5_6), v3_4.get(v5_6));
                    v6_2.put(v7_1.getFirst(), v7_1.getSecond());
                }
                v3_2 = kotlin.collections.CollectionsKt.toList(vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.toMap(new org.json.JSONObject(v6_2)).values());
            }
            v1_1.put(v2_0, v3_2);
        }
        return v1_1;
    }

    public static final float toPx(android.content.Context p1, int p2)
    {
        return android.util.TypedValue.applyDimension(1, ((float) p2), p1.getResources().getDisplayMetrics());
    }

    public static final void updateFont(android.widget.Button p2, String p3, float p4)
    {
        if (p4 > 0) {
            p2.setTextSize(2, p4);
        }
        if (p3.length() != 0) {
            if (!kotlin.text.StringsKt.isBlank(p3)) {
                try {
                    p2.setTypeface(android.graphics.Typeface.createFromAsset(p2.getContext().getAssets(), "fonts/".concat(p3)), p2.getTypeface().getStyle());
                    return;
                } catch (Exception v2_1) {
                    v2_1.printStackTrace();
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    public static final void updateFont(android.widget.TextView p2, String p3, float p4)
    {
        if (p4 > 0) {
            p2.setTextSize(2, p4);
        }
        if (p3.length() != 0) {
            if (!kotlin.text.StringsKt.isBlank(p3)) {
                try {
                    p2.setTypeface(android.graphics.Typeface.createFromAsset(p2.getContext().getAssets(), "fonts/".concat(p3)), p2.getTypeface().getStyle());
                    return;
                } catch (Exception v2_1) {
                    v2_1.printStackTrace();
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    public static synthetic void updateFont$default(android.widget.Button p0, String p1, float p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = "";
        }
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(p0, p1, p2);
        return;
    }

    public static synthetic void updateFont$default(android.widget.TextView p0, String p1, float p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = "";
        }
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.updateFont(p0, p1, p2);
        return;
    }
}
