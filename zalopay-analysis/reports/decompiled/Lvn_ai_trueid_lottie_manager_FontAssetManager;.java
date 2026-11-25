package vn.ai.trueid.lottie.manager;
public class FontAssetManager {
    private final android.content.res.AssetManager assetManager;
    private String defaultFontFileExtension;
    private vn.ai.trueid.lottie.FontAssetDelegate delegate;
    private final java.util.Map fontFamilies;
    private final java.util.Map fontMap;
    private final vn.ai.trueid.lottie.model.MutablePair tempPair;

    public FontAssetManager(android.graphics.drawable.Drawable$Callback p2, vn.ai.trueid.lottie.FontAssetDelegate p3)
    {
        this.tempPair = new vn.ai.trueid.lottie.model.MutablePair();
        this.fontMap = new java.util.HashMap();
        this.fontFamilies = new java.util.HashMap();
        this.defaultFontFileExtension = ".ttf";
        this.delegate = p3;
        if ((p2 instanceof android.view.View)) {
            this.assetManager = ((android.view.View) p2).getContext().getAssets();
            return;
        } else {
            vn.ai.trueid.lottie.utils.Logger.warning("LottieDrawable must be inside of a view for images to work.");
            this.assetManager = 0;
            return;
        }
    }

    private android.graphics.Typeface getFontFamily(vn.ai.trueid.lottie.model.Font p6)
    {
        String v0 = p6.getFamily();
        android.content.res.AssetManager v1_8 = ((android.graphics.Typeface) this.fontFamilies.get(v0));
        if (v1_8 == null) {
            android.graphics.Typeface v3_1;
            android.content.res.AssetManager v1_9 = p6.getStyle();
            android.content.res.AssetManager v2_0 = p6.getName();
            android.graphics.Typeface v3_0 = this.delegate;
            if (v3_0 == null) {
                v3_1 = 0;
            } else {
                v3_1 = v3_0.fetchFont(v0, v1_9, v2_0);
                if (v3_1 == null) {
                    v3_1 = this.delegate.fetchFont(v0);
                }
            }
            vn.ai.trueid.lottie.FontAssetDelegate v4 = this.delegate;
            if ((v4 != null) && (v3_1 == null)) {
                android.content.res.AssetManager v1_1 = v4.getFontPath(v0, v1_9, v2_0);
                if (v1_1 == null) {
                    v1_1 = this.delegate.getFontPath(v0);
                }
                if (v1_1 != null) {
                    v3_1 = android.graphics.Typeface.createFromAsset(this.assetManager, v1_1);
                }
            }
            if (p6.getTypeface() == null) {
                if (v3_1 == null) {
                    java.util.Map v6_2 = new StringBuilder("fonts/");
                    v6_2.append(v0);
                    v6_2.append(this.defaultFontFileExtension);
                    v3_1 = android.graphics.Typeface.createFromAsset(this.assetManager, v6_2.toString());
                }
                this.fontFamilies.put(v0, v3_1);
                return v3_1;
            } else {
                return p6.getTypeface();
            }
        } else {
            return v1_8;
        }
    }

    private android.graphics.Typeface typefaceForStyle(android.graphics.Typeface p3, String p4)
    {
        int v4_1;
        int v0_2 = p4.contains("Italic");
        int v4_2 = p4.contains("Bold");
        if ((v0_2 == 0) || (v4_2 == 0)) {
            if (v0_2 == 0) {
                if (v4_2 == 0) {
                    v4_1 = 0;
                } else {
                    v4_1 = 1;
                }
            } else {
                v4_1 = 2;
            }
        } else {
            v4_1 = 3;
        }
        if (p3.getStyle() != v4_1) {
            return android.graphics.Typeface.create(p3, v4_1);
        } else {
            return p3;
        }
    }

    public android.graphics.Typeface getTypeface(vn.ai.trueid.lottie.model.Font p4)
    {
        this.tempPair.set(p4.getFamily(), p4.getStyle());
        java.util.Map v0_1 = ((android.graphics.Typeface) this.fontMap.get(this.tempPair));
        if (v0_1 == null) {
            android.graphics.Typeface v4_2 = this.typefaceForStyle(this.getFontFamily(p4), p4.getStyle());
            this.fontMap.put(this.tempPair, v4_2);
            return v4_2;
        } else {
            return v0_1;
        }
    }

    public void setDefaultFontFileExtension(String p1)
    {
        this.defaultFontFileExtension = p1;
        return;
    }

    public void setDelegate(vn.ai.trueid.lottie.FontAssetDelegate p1)
    {
        this.delegate = p1;
        return;
    }
}
