package vn.ai.trueid.lottie.manager;
public class ImageAssetManager {
    private static final Object bitmapHashLock;
    private final android.content.Context context;
    private vn.ai.trueid.lottie.ImageAssetDelegate delegate;
    private final java.util.Map imageAssets;
    private final String imagesFolder;

    static ImageAssetManager()
    {
        vn.ai.trueid.lottie.manager.ImageAssetManager.bitmapHashLock = new Object();
        return;
    }

    public ImageAssetManager(android.graphics.drawable.Drawable$Callback p3, String p4, vn.ai.trueid.lottie.ImageAssetDelegate p5, java.util.Map p6)
    {
        if ((android.text.TextUtils.isEmpty(p4)) || (p4.charAt((p4.length() - 1)) == 47)) {
            this.imagesFolder = p4;
        } else {
            this.imagesFolder = p4.concat("/");
        }
        this.imageAssets = p6;
        this.setDelegate(p5);
        if ((p3 instanceof android.view.View)) {
            this.context = ((android.view.View) p3).getContext().getApplicationContext();
            return;
        } else {
            this.context = 0;
            return;
        }
    }

    private android.graphics.Bitmap putBitmap(String p3, android.graphics.Bitmap p4)
    {
        try {
            ((vn.ai.trueid.lottie.LottieImageAsset) this.imageAssets.get(p3)).setBitmap(p4);
            return p4;
        } catch (Throwable v3_3) {
            throw v3_3;
        }
    }

    public android.graphics.Bitmap bitmapForId(String p12)
    {
        android.graphics.Bitmap v0_8 = ((vn.ai.trueid.lottie.LottieImageAsset) this.imageAssets.get(p12));
        if (v0_8 != null) {
            StringBuilder v2_9 = v0_8.getBitmap();
            if (v2_9 == null) {
                StringBuilder v2_13 = this.delegate;
                if (v2_13 == null) {
                    StringBuilder v2_3 = this.context;
                    if (v2_3 != null) {
                        int v3_0 = v0_8.getFileName();
                        android.graphics.BitmapFactory$Options v4_1 = new android.graphics.BitmapFactory$Options();
                        v4_1.inScaled = 1;
                        v4_1.inDensity = 160;
                        if ((!v3_0.startsWith("data:")) || (v3_0.indexOf("base64,") <= 0)) {
                            try {
                                if (android.text.TextUtils.isEmpty(this.imagesFolder)) {
                                    throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
                                } else {
                                    StringBuilder v2_4 = v2_3.getAssets();
                                    int v5_4 = new StringBuilder();
                                    v5_4.append(this.imagesFolder);
                                    v5_4.append(v3_0);
                                    try {
                                        StringBuilder v2_6 = android.graphics.BitmapFactory.decodeStream(v2_4.open(v5_4.toString()), 0, v4_1);
                                    } catch (android.graphics.Bitmap v0_11) {
                                        StringBuilder v2_8 = new StringBuilder("Unable to decode image `");
                                        v2_8.append(p12);
                                        v2_8.append("`.");
                                        vn.ai.trueid.lottie.utils.Logger.warning(v2_8.toString(), v0_11);
                                        return 0;
                                    }
                                    if (v2_6 != null) {
                                        return this.putBitmap(p12, vn.ai.trueid.lottie.utils.Utils.resizeBitmapIfNeeded(v2_6, v0_8.getWidth(), v0_8.getHeight()));
                                    } else {
                                        android.graphics.Bitmap v0_15 = new StringBuilder("Decoded image `");
                                        v0_15.append(p12);
                                        v0_15.append("` is null.");
                                        vn.ai.trueid.lottie.utils.Logger.warning(v0_15.toString());
                                        return 0;
                                    }
                                }
                            } catch (android.graphics.Bitmap v12_6) {
                                vn.ai.trueid.lottie.utils.Logger.warning("Unable to open asset.", v12_6);
                                return 0;
                            }
                        } else {
                            try {
                                StringBuilder v2_15 = android.util.Base64.decode(v3_0.substring((v3_0.indexOf(44) + 1)), 0);
                                try {
                                    StringBuilder v2_0 = android.graphics.BitmapFactory.decodeByteArray(v2_15, 0, v2_15.length, v4_1);
                                } catch (android.graphics.Bitmap v0_1) {
                                    StringBuilder v2_2 = new StringBuilder("Unable to decode image `");
                                    v2_2.append(p12);
                                    v2_2.append("`.");
                                    vn.ai.trueid.lottie.utils.Logger.warning(v2_2.toString(), v0_1);
                                    return 0;
                                }
                                if (v2_0 != null) {
                                    return this.putBitmap(p12, vn.ai.trueid.lottie.utils.Utils.resizeBitmapIfNeeded(v2_0, v0_8.getWidth(), v0_8.getHeight()));
                                } else {
                                    android.graphics.Bitmap v0_5 = new StringBuilder("Decoded image `");
                                    v0_5.append(p12);
                                    v0_5.append("` is null.");
                                    vn.ai.trueid.lottie.utils.Logger.warning(v0_5.toString());
                                    return 0;
                                }
                            } catch (android.graphics.Bitmap v12_10) {
                                vn.ai.trueid.lottie.utils.Logger.warning("data URL did not have correct base64 format.", v12_10);
                                return 0;
                            }
                        }
                    } else {
                        return 0;
                    }
                } else {
                    android.graphics.Bitmap v0_6 = v2_13.fetchBitmap(v0_8);
                    if (v0_6 != null) {
                        this.putBitmap(p12, v0_6);
                    }
                    return v0_6;
                }
            } else {
                return v2_9;
            }
        } else {
            return 0;
        }
    }

    public vn.ai.trueid.lottie.LottieImageAsset getImageAssetById(String p2)
    {
        return ((vn.ai.trueid.lottie.LottieImageAsset) this.imageAssets.get(p2));
    }

    public boolean hasSameContext(android.content.Context p4)
    {
        if (p4 != null) {
            if ((this.context instanceof android.app.Application)) {
                p4 = p4.getApplicationContext();
            }
            if (p4 != this.context) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (this.context != null) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public void setDelegate(vn.ai.trueid.lottie.ImageAssetDelegate p1)
    {
        this.delegate = p1;
        return;
    }

    public android.graphics.Bitmap updateBitmap(String p2, android.graphics.Bitmap p3)
    {
        if (p3 != null) {
            android.graphics.Bitmap v0_4 = ((vn.ai.trueid.lottie.LottieImageAsset) this.imageAssets.get(p2)).getBitmap();
            this.putBitmap(p2, p3);
            return v0_4;
        } else {
            vn.ai.trueid.lottie.LottieImageAsset v2_2 = ((vn.ai.trueid.lottie.LottieImageAsset) this.imageAssets.get(p2));
            android.graphics.Bitmap v3_1 = v2_2.getBitmap();
            v2_2.setBitmap(0);
            return v3_1;
        }
    }
}
