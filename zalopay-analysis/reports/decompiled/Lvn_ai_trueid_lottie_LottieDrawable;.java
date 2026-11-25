package vn.ai.trueid.lottie;
public class LottieDrawable extends android.graphics.drawable.Drawable implements android.graphics.drawable.Drawable$Callback, android.graphics.drawable.Animatable {
    private static final java.util.List ALLOWED_REDUCED_MOTION_MARKERS = None;
    public static final int INFINITE = 255;
    private static final float MAX_DELTA_MS_ASYNC_SET_PROGRESS = 16968;
    private static final long MAX_SOFTWARE_BITMAP_PIXELS = 50000000;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private static final boolean invalidateSelfOnMainThread;
    private static final java.util.concurrent.Executor setProgressExecutor;
    private int alpha;
    private final vn.ai.trueid.lottie.utils.LottieValueAnimator animator;
    private vn.ai.trueid.lottie.AsyncUpdates asyncUpdates;
    private android.graphics.Rect canvasClipBounds;
    private android.graphics.RectF canvasClipBoundsRectF;
    private boolean clipTextToBoundingBox;
    private boolean clipToCompositionBounds;
    private vn.ai.trueid.lottie.LottieComposition composition;
    private vn.ai.trueid.lottie.model.layer.CompositionLayer compositionLayer;
    String defaultFontFileExtension;
    vn.ai.trueid.lottie.FontAssetDelegate fontAssetDelegate;
    private vn.ai.trueid.lottie.manager.FontAssetManager fontAssetManager;
    private java.util.Map fontMap;
    private boolean ignoreSystemAnimationsDisabled;
    private vn.ai.trueid.lottie.ImageAssetDelegate imageAssetDelegate;
    private vn.ai.trueid.lottie.manager.ImageAssetManager imageAssetManager;
    private String imageAssetsFolder;
    private Runnable invalidateSelfRunnable;
    private boolean isApplyingOpacityToLayersEnabled;
    private boolean isApplyingShadowToLayersEnabled;
    private boolean isDirty;
    private float lastDrawnProgress;
    private final java.util.ArrayList lazyCompositionTasks;
    private final vn.ai.trueid.lottie.LottieFeatureFlags lottieFeatureFlags;
    private android.os.Handler mainThreadHandler;
    private boolean maintainOriginalImageBounds;
    private vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction onVisibleAction;
    private boolean outlineMasksAndMattes;
    private boolean performanceTrackingEnabled;
    private final android.animation.ValueAnimator$AnimatorUpdateListener progressUpdateListener;
    private vn.ai.trueid.lottie.RenderMode renderMode;
    private final android.graphics.Matrix renderingMatrix;
    private boolean safeMode;
    private final java.util.concurrent.Semaphore setProgressDrawLock;
    private android.graphics.Bitmap softwareRenderingBitmap;
    private android.graphics.Canvas softwareRenderingCanvas;
    private android.graphics.Rect softwareRenderingDstBoundsRect;
    private android.graphics.RectF softwareRenderingDstBoundsRectF;
    private android.graphics.Matrix softwareRenderingOriginalCanvasMatrix;
    private float[] softwareRenderingOriginalCanvasMatrixElements;
    private android.graphics.Matrix softwareRenderingOriginalCanvasMatrixInverse;
    private android.graphics.Paint softwareRenderingPaint;
    private android.graphics.Rect softwareRenderingSrcBoundsRect;
    private android.graphics.RectF softwareRenderingTransformedBounds;
    private boolean systemAnimationsEnabled;
    vn.ai.trueid.lottie.TextDelegate textDelegate;
    private final Runnable updateProgressRunnable;
    private boolean useSoftwareRendering;

    static LottieDrawable()
    {
        vn.ai.trueid.lottie.LottieDrawable.invalidateSelfOnMainThread = 0;
        vn.ai.trueid.lottie.LottieDrawable.ALLOWED_REDUCED_MOTION_MARKERS = java.util.Arrays.asList(new String[] {"reduced motion", "reduced_motion", "reduced-motion", "reducedmotion"}));
        vn.ai.trueid.lottie.LottieDrawable.setProgressExecutor = new java.util.concurrent.ThreadPoolExecutor(0, 2, 35, java.util.concurrent.TimeUnit.MILLISECONDS, new java.util.concurrent.LinkedBlockingQueue(), new vn.ai.trueid.lottie.utils.LottieThreadFactory());
        return;
    }

    public LottieDrawable()
    {
        vn.ai.trueid.lottie.utils.LottieValueAnimator v0_1 = new vn.ai.trueid.lottie.utils.LottieValueAnimator();
        this.animator = v0_1;
        this.systemAnimationsEnabled = 1;
        this.ignoreSystemAnimationsDisabled = 0;
        this.safeMode = 0;
        this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
        this.lazyCompositionTasks = new java.util.ArrayList();
        this.lottieFeatureFlags = new vn.ai.trueid.lottie.LottieFeatureFlags();
        this.maintainOriginalImageBounds = 0;
        this.clipToCompositionBounds = 1;
        this.alpha = 255;
        this.clipTextToBoundingBox = 0;
        this.renderMode = vn.ai.trueid.lottie.RenderMode.AUTOMATIC;
        this.useSoftwareRendering = 0;
        this.renderingMatrix = new android.graphics.Matrix();
        int v3_9 = new float[9];
        this.softwareRenderingOriginalCanvasMatrixElements = v3_9;
        this.isDirty = 0;
        vn.ai.trueid.lottie.s v2_1 = new vn.ai.trueid.lottie.s(this);
        this.progressUpdateListener = v2_1;
        this.setProgressDrawLock = new java.util.concurrent.Semaphore(1);
        this.updateProgressRunnable = new vn.ai.trueid.lottie.p(this, 1);
        this.lastDrawnProgress = -8388609;
        v0_1.addUpdateListener(v2_1);
        return;
    }

    public static synthetic void a(vn.ai.trueid.lottie.LottieDrawable p0, String p1, String p2, boolean p3, vn.ai.trueid.lottie.LottieComposition p4)
    {
        p0.lambda$setMinAndMaxFrame$12(p1, p2, p3, p4);
        return;
    }

    public static synthetic void b(vn.ai.trueid.lottie.LottieDrawable p0)
    {
        p0.lambda$new$1();
        return;
    }

    private void buildCompositionLayer()
    {
        vn.ai.trueid.lottie.model.layer.CompositionLayer v0_0 = this.composition;
        if (v0_0 != null) {
            boolean v1_2 = new vn.ai.trueid.lottie.model.layer.CompositionLayer(this, vn.ai.trueid.lottie.parser.LayerParser.parse(v0_0), v0_0.getLayers(), v0_0);
            this.compositionLayer = v1_2;
            if (this.outlineMasksAndMattes) {
                v1_2.setOutlineMasksAndMattes(1);
            }
            this.compositionLayer.setClipToCompositionBounds(this.clipToCompositionBounds);
            return;
        } else {
            return;
        }
    }

    public static synthetic void c(vn.ai.trueid.lottie.LottieDrawable p0, int p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setMaxFrame$7(p1, p2);
        return;
    }

    private void computeRenderMode()
    {
        boolean v0_0 = this.composition;
        if (v0_0) {
            this.useSoftwareRendering = this.renderMode.useSoftwareRendering(android.os.Build$VERSION.SDK_INT, v0_0.hasDashPattern(), v0_0.getMaskAndMatteCount());
            return;
        } else {
            return;
        }
    }

    private void convertRect(android.graphics.Rect p4, android.graphics.RectF p5)
    {
        p5.set(((float) p4.left), ((float) p4.top), ((float) p4.right), ((float) p4.bottom));
        return;
    }

    private void convertRect(android.graphics.RectF p6, android.graphics.Rect p7)
    {
        p7.set(((int) Math.floor(((double) p6.left))), ((int) Math.floor(((double) p6.top))), ((int) Math.ceil(((double) p6.right))), ((int) Math.ceil(((double) p6.bottom))));
        return;
    }

    public static synthetic void d(vn.ai.trueid.lottie.LottieDrawable p0, float p1, float p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        p0.lambda$setMinAndMaxProgress$14(p1, p2, p3);
        return;
    }

    private void draw(android.graphics.Canvas p2, android.graphics.Matrix p3, vn.ai.trueid.lottie.model.layer.CompositionLayer p4, int p5)
    {
        if (!this.useSoftwareRendering) {
            p4.draw(p2, p3, p5, 0);
            return;
        } else {
            p2.save();
            p2.concat(p3);
            this.renderAndDrawAsBitmap(p2, p4);
            p2.restore();
            return;
        }
    }

    private void drawDirectlyToCanvas(android.graphics.Canvas p7)
    {
        vn.ai.trueid.lottie.model.layer.CompositionLayer v0 = this.compositionLayer;
        android.graphics.Matrix v1_0 = this.composition;
        if ((v0 != null) && (v1_0 != null)) {
            this.renderingMatrix.reset();
            int v2_4 = this.getBounds();
            if (!v2_4.isEmpty()) {
                int v3_2 = (((float) v2_4.width()) / ((float) v1_0.getBounds().width()));
                float v4_5 = (((float) v2_4.height()) / ((float) v1_0.getBounds().height()));
                this.renderingMatrix.preTranslate(((float) v2_4.left), ((float) v2_4.top));
                this.renderingMatrix.preScale(v3_2, v4_5);
            }
            v0.draw(p7, this.renderingMatrix, this.alpha, 0);
        }
        return;
    }

    public static synthetic void e(vn.ai.trueid.lottie.LottieDrawable p0, vn.ai.trueid.lottie.LottieComposition p1)
    {
        p0.lambda$resumeAnimation$4(p1);
        return;
    }

    private void ensureSoftwareRenderingBitmap(int p4, int p5)
    {
        android.graphics.Bitmap v0_0 = this.softwareRenderingBitmap;
        if ((v0_0 != null) && ((v0_0.getWidth() >= p4) && (this.softwareRenderingBitmap.getHeight() >= p5))) {
            if ((this.softwareRenderingBitmap.getWidth() <= p4) && (this.softwareRenderingBitmap.getHeight() <= p5)) {
                return;
            } else {
                android.graphics.Bitmap v4_1 = android.graphics.Bitmap.createBitmap(this.softwareRenderingBitmap, 0, 0, p4, p5);
                this.softwareRenderingBitmap = v4_1;
                this.softwareRenderingCanvas.setBitmap(v4_1);
                this.isDirty = 1;
                return;
            }
        } else {
            android.graphics.Bitmap v4_2 = android.graphics.Bitmap.createBitmap(p4, p5, android.graphics.Bitmap$Config.ARGB_8888);
            this.softwareRenderingBitmap = v4_2;
            this.softwareRenderingCanvas.setBitmap(v4_2);
            this.isDirty = 1;
            return;
        }
    }

    private void ensureSoftwareRenderingObjectsInitialized()
    {
        if (this.softwareRenderingCanvas == null) {
            this.softwareRenderingCanvas = new android.graphics.Canvas();
            this.softwareRenderingTransformedBounds = new android.graphics.RectF();
            this.softwareRenderingOriginalCanvasMatrix = new android.graphics.Matrix();
            this.softwareRenderingOriginalCanvasMatrixInverse = new android.graphics.Matrix();
            this.canvasClipBounds = new android.graphics.Rect();
            this.canvasClipBoundsRectF = new android.graphics.RectF();
            this.softwareRenderingPaint = new vn.ai.trueid.lottie.animation.LPaint();
            this.softwareRenderingSrcBoundsRect = new android.graphics.Rect();
            this.softwareRenderingDstBoundsRect = new android.graphics.Rect();
            this.softwareRenderingDstBoundsRectF = new android.graphics.RectF();
            return;
        } else {
            return;
        }
    }

    public static synthetic void f(vn.ai.trueid.lottie.LottieDrawable p0, int p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setMinFrame$5(p1, p2);
        return;
    }

    public static synthetic void g(vn.ai.trueid.lottie.LottieDrawable p0, String p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setMinAndMaxFrame$11(p1, p2);
        return;
    }

    private android.content.Context getContext()
    {
        android.content.Context v0_0 = this.getCallback();
        if (v0_0 != null) {
            if (!(v0_0 instanceof android.view.View)) {
                return 0;
            } else {
                return ((android.view.View) v0_0).getContext();
            }
        } else {
            return 0;
        }
    }

    private vn.ai.trueid.lottie.manager.FontAssetManager getFontAssetManager()
    {
        if (this.getCallback() != null) {
            if (this.fontAssetManager == null) {
                vn.ai.trueid.lottie.manager.FontAssetManager v0_5 = new vn.ai.trueid.lottie.manager.FontAssetManager(this.getCallback(), this.fontAssetDelegate);
                this.fontAssetManager = v0_5;
                String v1_0 = this.defaultFontFileExtension;
                if (v1_0 != null) {
                    v0_5.setDefaultFontFileExtension(v1_0);
                }
            }
            return this.fontAssetManager;
        } else {
            return 0;
        }
    }

    private vn.ai.trueid.lottie.manager.ImageAssetManager getImageAssetManager()
    {
        vn.ai.trueid.lottie.manager.ImageAssetManager v0_0 = this.imageAssetManager;
        if ((v0_0 != null) && (!v0_0.hasSameContext(this.getContext()))) {
            this.imageAssetManager = 0;
        }
        if (this.imageAssetManager == null) {
            this.imageAssetManager = new vn.ai.trueid.lottie.manager.ImageAssetManager(this.getCallback(), this.imageAssetsFolder, this.imageAssetDelegate, this.composition.getImages());
        }
        return this.imageAssetManager;
    }

    public static synthetic void h(vn.ai.trueid.lottie.LottieDrawable p0, float p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setMaxProgress$8(p1, p2);
        return;
    }

    public static synthetic void i(vn.ai.trueid.lottie.LottieDrawable p0)
    {
        p0.lambda$new$2();
        return;
    }

    private boolean ignoreCanvasClipBounds()
    {
        int v0_0 = this.getCallback();
        if ((v0_0 instanceof android.view.View)) {
            int v0_5 = ((android.view.View) v0_0).getParent();
            if (!(v0_5 instanceof android.view.ViewGroup)) {
                return 0;
            } else {
                return (((android.view.ViewGroup) v0_5).getClipChildren() ^ 1);
            }
        } else {
            return 0;
        }
    }

    private static boolean isFinite(float p1)
    {
        if ((Float.isNaN(p1)) || (Float.isInfinite(p1))) {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean isFiniteRect(android.graphics.RectF p1)
    {
        if ((!vn.ai.trueid.lottie.LottieDrawable.isFinite(p1.left)) || ((!vn.ai.trueid.lottie.LottieDrawable.isFinite(p1.top)) || ((!vn.ai.trueid.lottie.LottieDrawable.isFinite(p1.right)) || (!vn.ai.trueid.lottie.LottieDrawable.isFinite(p1.bottom))))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static synthetic void j(vn.ai.trueid.lottie.LottieDrawable p0, float p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setProgress$16(p1, p2);
        return;
    }

    public static synthetic void k(vn.ai.trueid.lottie.LottieDrawable p0, vn.ai.trueid.lottie.model.KeyPath p1, Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3, vn.ai.trueid.lottie.LottieComposition p4)
    {
        p0.lambda$addValueCallback$17(p1, p2, p3, p4);
        return;
    }

    public static synthetic void l(vn.ai.trueid.lottie.LottieDrawable p0, String p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setMaxFrame$10(p1, p2);
        return;
    }

    private synthetic void lambda$addValueCallback$17(vn.ai.trueid.lottie.model.KeyPath p1, Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3, vn.ai.trueid.lottie.LottieComposition p4)
    {
        this.addValueCallback(p1, p2, p3);
        return;
    }

    private synthetic void lambda$new$0(android.animation.ValueAnimator p2)
    {
        if (!this.getAsyncUpdatesEnabled()) {
            vn.ai.trueid.lottie.model.layer.CompositionLayer v2_1 = this.compositionLayer;
            if (v2_1 != null) {
                v2_1.setProgress(this.animator.getAnimatedValueAbsolute());
            }
            return;
        } else {
            this.invalidateSelf();
            return;
        }
    }

    private synthetic void lambda$new$1()
    {
        android.graphics.drawable.Drawable$Callback v0 = this.getCallback();
        if (v0 != null) {
            v0.invalidateDrawable(this);
        }
        return;
    }

    private synthetic void lambda$new$2()
    {
        android.os.Handler v0_0 = this.compositionLayer;
        if (v0_0 != null) {
            try {
                this.setProgressDrawLock.acquire();
                v0_0.setProgress(this.animator.getAnimatedValueAbsolute());
            } catch (InterruptedException) {
                this.setProgressDrawLock.release();
                return;
            }
            if ((!vn.ai.trueid.lottie.LottieDrawable.invalidateSelfOnMainThread) || (!this.isDirty)) {
                this.setProgressDrawLock.release();
                return;
            } else {
                if (this.mainThreadHandler == null) {
                    this.mainThreadHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                    this.invalidateSelfRunnable = new vn.ai.trueid.lottie.p(this, 0);
                }
                this.mainThreadHandler.post(this.invalidateSelfRunnable);
                this.setProgressDrawLock.release();
                return;
            }
        } else {
            return;
        }
    }

    private synthetic void lambda$playAnimation$3(vn.ai.trueid.lottie.LottieComposition p1)
    {
        this.playAnimation();
        return;
    }

    private synthetic void lambda$resumeAnimation$4(vn.ai.trueid.lottie.LottieComposition p1)
    {
        this.resumeAnimation();
        return;
    }

    private synthetic void lambda$setFrame$15(int p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setFrame(p1);
        return;
    }

    private synthetic void lambda$setMaxFrame$10(String p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setMaxFrame(p1);
        return;
    }

    private synthetic void lambda$setMaxFrame$7(int p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setMaxFrame(p1);
        return;
    }

    private synthetic void lambda$setMaxProgress$8(float p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setMaxProgress(p1);
        return;
    }

    private synthetic void lambda$setMinAndMaxFrame$11(String p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setMinAndMaxFrame(p1);
        return;
    }

    private synthetic void lambda$setMinAndMaxFrame$12(String p1, String p2, boolean p3, vn.ai.trueid.lottie.LottieComposition p4)
    {
        this.setMinAndMaxFrame(p1, p2, p3);
        return;
    }

    private synthetic void lambda$setMinAndMaxFrame$13(int p1, int p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        this.setMinAndMaxFrame(p1, p2);
        return;
    }

    private synthetic void lambda$setMinAndMaxProgress$14(float p1, float p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        this.setMinAndMaxProgress(p1, p2);
        return;
    }

    private synthetic void lambda$setMinFrame$5(int p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setMinFrame(p1);
        return;
    }

    private synthetic void lambda$setMinFrame$9(String p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setMinFrame(p1);
        return;
    }

    private synthetic void lambda$setMinProgress$6(float p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setMinProgress(p1);
        return;
    }

    private synthetic void lambda$setProgress$16(float p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        this.setProgress(p1);
        return;
    }

    public static synthetic void m(vn.ai.trueid.lottie.LottieDrawable p0, int p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setFrame$15(p1, p2);
        return;
    }

    public static synthetic void n(vn.ai.trueid.lottie.LottieDrawable p0, String p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setMinFrame$9(p1, p2);
        return;
    }

    public static synthetic void o(vn.ai.trueid.lottie.LottieDrawable p0, int p1, int p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        p0.lambda$setMinAndMaxFrame$13(p1, p2, p3);
        return;
    }

    public static synthetic void p(vn.ai.trueid.lottie.LottieDrawable p0, float p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p0.lambda$setMinProgress$6(p1, p2);
        return;
    }

    public static synthetic void q(vn.ai.trueid.lottie.LottieDrawable p0, vn.ai.trueid.lottie.LottieComposition p1)
    {
        p0.lambda$playAnimation$3(p1);
        return;
    }

    public static synthetic void r(vn.ai.trueid.lottie.LottieDrawable p0, android.animation.ValueAnimator p1)
    {
        p0.lambda$new$0(p1);
        return;
    }

    private void renderAndDrawAsBitmap(android.graphics.Canvas p11, vn.ai.trueid.lottie.model.layer.CompositionLayer p12)
    {
        if ((this.composition != null) && (p12 != null)) {
            this.ensureSoftwareRenderingObjectsInitialized();
            p11.getMatrix(this.softwareRenderingOriginalCanvasMatrix);
            p11.getClipBounds(this.canvasClipBounds);
            this.convertRect(this.canvasClipBounds, this.canvasClipBoundsRectF);
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.canvasClipBoundsRectF);
            this.convertRect(this.canvasClipBoundsRectF, this.canvasClipBounds);
            if (!this.clipToCompositionBounds) {
                p12.getBounds(this.softwareRenderingTransformedBounds, 0, 0);
            } else {
                this.softwareRenderingTransformedBounds.set(0, 0, ((float) this.getIntrinsicWidth()), ((float) this.getIntrinsicHeight()));
            }
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.softwareRenderingTransformedBounds);
            android.graphics.Rect v0_17 = this.getBounds();
            android.graphics.Matrix v3_12 = (((float) v0_17.width()) / ((float) this.getIntrinsicWidth()));
            android.graphics.Rect v0_21 = (((float) v0_17.height()) / ((float) this.getIntrinsicHeight()));
            this.scaleRect(this.softwareRenderingTransformedBounds, v3_12, v0_21);
            if (!this.ignoreCanvasClipBounds()) {
                int v5_1 = this.canvasClipBounds;
                this.softwareRenderingTransformedBounds.intersect(((float) v5_1.left), ((float) v5_1.top), ((float) v5_1.right), ((float) v5_1.bottom));
            }
            if (vn.ai.trueid.lottie.LottieDrawable.isFiniteRect(this.softwareRenderingTransformedBounds)) {
                int v4_15 = ((int) Math.ceil(((double) this.softwareRenderingTransformedBounds.width())));
                int v5_8 = ((int) Math.ceil(((double) this.softwareRenderingTransformedBounds.height())));
                if ((v4_15 > 0) && (v5_8 > 0)) {
                    int v6_5 = (((long) v4_15) * ((long) v5_8));
                    if (v6_5 <= 50000000) {
                        this.ensureSoftwareRenderingBitmap(v4_15, v5_8);
                        if (this.isDirty) {
                            this.softwareRenderingOriginalCanvasMatrix.getValues(this.softwareRenderingOriginalCanvasMatrixElements);
                            int v6_8 = this.softwareRenderingOriginalCanvasMatrixElements;
                            float v7_0 = v6_8[0];
                            int v6_0 = v6_8[4];
                            this.renderingMatrix.set(this.softwareRenderingOriginalCanvasMatrix);
                            this.renderingMatrix.preScale(v3_12, v0_21);
                            android.graphics.Matrix v3_0 = this.softwareRenderingTransformedBounds;
                            this.renderingMatrix.postTranslate((- v3_0.left), (- v3_0.top));
                            this.renderingMatrix.postScale((1065353216 / v7_0), (1065353216 / v6_0));
                            this.softwareRenderingBitmap.eraseColor(0);
                            this.softwareRenderingCanvas.setMatrix(vn.ai.trueid.lottie.utils.Utils.IDENTITY_MATRIX);
                            this.softwareRenderingCanvas.scale(v7_0, v6_0);
                            p12.draw(this.softwareRenderingCanvas, this.renderingMatrix, this.alpha, 0);
                            this.softwareRenderingOriginalCanvasMatrix.invert(this.softwareRenderingOriginalCanvasMatrixInverse);
                            this.softwareRenderingOriginalCanvasMatrixInverse.mapRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingTransformedBounds);
                            this.convertRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingDstBoundsRect);
                        }
                        this.softwareRenderingSrcBoundsRect.set(0, 0, v4_15, v5_8);
                        p11.drawBitmap(this.softwareRenderingBitmap, this.softwareRenderingSrcBoundsRect, this.softwareRenderingDstBoundsRect, this.softwareRenderingPaint);
                        return;
                    } else {
                        String v11_2 = new StringBuilder("Skipping software rendering: bitmap request exceeds safe pixel count (");
                        v11_2.append(v6_5);
                        v11_2.append(")");
                        vn.ai.trueid.lottie.utils.Logger.warning(v11_2.toString());
                        return;
                    }
                } else {
                    vn.ai.trueid.lottie.utils.Logger.warning("Skipping software rendering: transformed bounds have negative values.");
                }
            } else {
                vn.ai.trueid.lottie.utils.Logger.warning("Skipping software rendering: transformed bounds contain non-finite values.");
                return;
            }
        }
        return;
    }

    private void scaleRect(android.graphics.RectF p4, float p5, float p6)
    {
        p4.set((p4.left * p5), (p4.top * p6), (p4.right * p5), (p4.bottom * p6));
        return;
    }

    private boolean shouldSetProgressBeforeDrawing()
    {
        int v0_0 = this.composition;
        if (v0_0 != 0) {
            float v2_2 = this.lastDrawnProgress;
            float v3_2 = this.animator.getAnimatedValueAbsolute();
            this.lastDrawnProgress = v3_2;
            if ((Math.abs((v3_2 - v2_2)) * v0_0.getDuration()) < 1112014848) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public void addAnimatorListener(android.animation.Animator$AnimatorListener p2)
    {
        this.animator.addListener(p2);
        return;
    }

    public void addAnimatorPauseListener(android.animation.Animator$AnimatorPauseListener p2)
    {
        this.animator.addPauseListener(p2);
        return;
    }

    public void addAnimatorUpdateListener(android.animation.ValueAnimator$AnimatorUpdateListener p2)
    {
        this.animator.addUpdateListener(p2);
        return;
    }

    public void addValueCallback(vn.ai.trueid.lottie.model.KeyPath p4, Object p5, vn.ai.trueid.lottie.value.LottieValueCallback p6)
    {
        int v0_0 = this.compositionLayer;
        if (v0_0 != 0) {
            int v2 = 1;
            if (p4 != vn.ai.trueid.lottie.model.KeyPath.COMPOSITION) {
                if (p4.getResolvedElement() == null) {
                    boolean v4_5 = this.resolveKeyPath(p4);
                    int v0_1 = 0;
                    while (v0_1 < v4_5.size()) {
                        ((vn.ai.trueid.lottie.model.KeyPath) v4_5.get(v0_1)).getResolvedElement().addValueCallback(p5, p6);
                        v0_1++;
                    }
                    v2 = (1 ^ v4_5.isEmpty());
                } else {
                    p4.getResolvedElement().addValueCallback(p5, p6);
                }
            } else {
                v0_0.addValueCallback(p5, p6);
            }
            if (v2 != 0) {
                this.invalidateSelf();
                if (p5 == vn.ai.trueid.lottie.LottieProperty.TIME_REMAP) {
                    this.setProgress(this.getProgress());
                }
            }
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.o(this, p4, p5, p6));
            return;
        }
    }

    public void addValueCallback(vn.ai.trueid.lottie.model.KeyPath p2, Object p3, vn.ai.trueid.lottie.value.SimpleLottieValueCallback p4)
    {
        this.addValueCallback(p2, p3, new vn.ai.trueid.lottie.LottieDrawable$1(this, p4));
        return;
    }

    public boolean animationsEnabled(android.content.Context p3)
    {
        if (!this.ignoreSystemAnimationsDisabled) {
            if ((!this.systemAnimationsEnabled) || (vn.ai.trueid.lottie.L.getReducedMotionOption().getCurrentReducedMotionMode(p3) != vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.STANDARD_MOTION)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    public void cancelAnimation()
    {
        this.lazyCompositionTasks.clear();
        this.animator.cancel();
        if (!this.isVisible()) {
            this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
        }
        return;
    }

    public void clearComposition()
    {
        if (this.animator.isRunning()) {
            this.animator.cancel();
            if (!this.isVisible()) {
                this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
            }
        }
        this.composition = 0;
        this.compositionLayer = 0;
        this.imageAssetManager = 0;
        this.lastDrawnProgress = -8388609;
        this.animator.clearComposition();
        this.invalidateSelf();
        return;
    }

    public void disableExtraScaleModeInFitXY()
    {
        return;
    }

    public void draw(android.graphics.Canvas p5)
    {
        float v0_0 = this.compositionLayer;
        if (v0_0 != 0) {
            Runnable v1_0 = this.getAsyncUpdatesEnabled();
            try {
                if (v1_0 == null) {
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.beginSection("Drawable#draw");
                    }
                    if ((v1_0 != null) && (this.shouldSetProgressBeforeDrawing())) {
                        this.setProgress(this.animator.getAnimatedValueAbsolute());
                    }
                    if (!this.safeMode) {
                        if (!this.useSoftwareRendering) {
                            this.drawDirectlyToCanvas(p5);
                        } else {
                            this.renderAndDrawAsBitmap(p5, v0_0);
                        }
                    } else {
                        if (!this.useSoftwareRendering) {
                            this.drawDirectlyToCanvas(p5);
                        } else {
                            this.renderAndDrawAsBitmap(p5, v0_0);
                        }
                    }
                    this.isDirty = 0;
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.endSection("Drawable#draw");
                    }
                    if (v1_0 == null) {
                        return;
                    } else {
                        this.setProgressDrawLock.release();
                        if (v0_0.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                            return;
                        }
                    }
                } else {
                    this.setProgressDrawLock.acquire();
                }
            } catch (InterruptedException) {
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("Drawable#draw");
                }
                if (v1_0 == null) {
                    return;
                } else {
                    this.setProgressDrawLock.release();
                    if (v0_0.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                        return;
                    } else {
                    }
                }
            }
            vn.ai.trueid.lottie.LottieDrawable.setProgressExecutor.execute(this.updateProgressRunnable);
        }
        return;
    }

    public void draw(android.graphics.Canvas p4, android.graphics.Matrix p5)
    {
        Runnable v0_0 = this.compositionLayer;
        if ((v0_0 != null) && (this.composition != null)) {
            boolean v1_1 = this.getAsyncUpdatesEnabled();
            try {
                if (!v1_1) {
                    if (!this.safeMode) {
                        this.draw(p4, p5, v0_0, this.alpha);
                    } else {
                        try {
                            this.draw(p4, p5, v0_0, this.alpha);
                        } catch (java.util.concurrent.Executor v4_1) {
                            vn.ai.trueid.lottie.utils.Logger.error("Lottie crashed in draw!", v4_1);
                        }
                    }
                    this.isDirty = 0;
                    if (!v1_1) {
                        return;
                    } else {
                        this.setProgressDrawLock.release();
                        if (v0_0.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                            return;
                        }
                    }
                } else {
                    this.setProgressDrawLock.acquire();
                    if (!this.shouldSetProgressBeforeDrawing()) {
                    } else {
                        this.setProgress(this.animator.getAnimatedValueAbsolute());
                    }
                }
            } catch (InterruptedException) {
                if (!v1_1) {
                    return;
                } else {
                    this.setProgressDrawLock.release();
                    if (v0_0.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                        return;
                    } else {
                    }
                }
            }
            vn.ai.trueid.lottie.LottieDrawable.setProgressExecutor.execute(this.updateProgressRunnable);
        }
        return;
    }

    public void enableFeatureFlag(vn.ai.trueid.lottie.LottieFeatureFlag p2, boolean p3)
    {
        boolean vtmp1 = this.lottieFeatureFlags.enableFlag(p2, p3);
        if ((this.composition != null) && (vtmp1)) {
            this.buildCompositionLayer();
        }
        return;
    }

    public void enableMergePathsForKitKatAndAbove(boolean p3)
    {
        boolean vtmp1 = this.lottieFeatureFlags.enableFlag(vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19, p3);
        if ((this.composition != null) && (vtmp1)) {
            this.buildCompositionLayer();
        }
        return;
    }

    public boolean enableMergePathsForKitKatAndAbove()
    {
        return this.lottieFeatureFlags.isFlagEnabled(vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19);
    }

    public void endAnimation()
    {
        this.lazyCompositionTasks.clear();
        this.animator.endAnimation();
        if (!this.isVisible()) {
            this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
        }
        return;
    }

    public int getAlpha()
    {
        return this.alpha;
    }

    public vn.ai.trueid.lottie.AsyncUpdates getAsyncUpdates()
    {
        vn.ai.trueid.lottie.AsyncUpdates v0_0 = this.asyncUpdates;
        if (v0_0 == null) {
            return vn.ai.trueid.lottie.L.getDefaultAsyncUpdates();
        } else {
            return v0_0;
        }
    }

    public boolean getAsyncUpdatesEnabled()
    {
        if (this.getAsyncUpdates() != vn.ai.trueid.lottie.AsyncUpdates.ENABLED) {
            return 0;
        } else {
            return 1;
        }
    }

    public android.graphics.Bitmap getBitmapForId(String p2)
    {
        vn.ai.trueid.lottie.manager.ImageAssetManager v0 = this.getImageAssetManager();
        if (v0 == null) {
            return 0;
        } else {
            return v0.bitmapForId(p2);
        }
    }

    public boolean getClipTextToBoundingBox()
    {
        return this.clipTextToBoundingBox;
    }

    public boolean getClipToCompositionBounds()
    {
        return this.clipToCompositionBounds;
    }

    public vn.ai.trueid.lottie.LottieComposition getComposition()
    {
        return this.composition;
    }

    public int getFrame()
    {
        return ((int) this.animator.getFrame());
    }

    public android.graphics.Bitmap getImageAsset(String p3)
    {
        java.util.Map v0_0 = this.getImageAssetManager();
        if (v0_0 == null) {
            android.graphics.Bitmap v3_1;
            java.util.Map v0_1 = this.composition;
            if (v0_1 != null) {
                v3_1 = ((vn.ai.trueid.lottie.LottieImageAsset) v0_1.getImages().get(p3));
            } else {
                v3_1 = 0;
            }
            if (v3_1 == null) {
                return 0;
            } else {
                return v3_1.getBitmap();
            }
        } else {
            return v0_0.bitmapForId(p3);
        }
    }

    public String getImageAssetsFolder()
    {
        return this.imageAssetsFolder;
    }

    public int getIntrinsicHeight()
    {
        int v0_0 = this.composition;
        if (v0_0 != 0) {
            return v0_0.getBounds().height();
        } else {
            return -1;
        }
    }

    public int getIntrinsicWidth()
    {
        int v0_0 = this.composition;
        if (v0_0 != 0) {
            return v0_0.getBounds().width();
        } else {
            return -1;
        }
    }

    public vn.ai.trueid.lottie.LottieImageAsset getLottieImageAssetForId(String p2)
    {
        java.util.Map v0_0 = this.composition;
        if (v0_0 != null) {
            return ((vn.ai.trueid.lottie.LottieImageAsset) v0_0.getImages().get(p2));
        } else {
            return 0;
        }
    }

    public boolean getMaintainOriginalImageBounds()
    {
        return this.maintainOriginalImageBounds;
    }

    public vn.ai.trueid.lottie.model.Marker getMarkerForAnimationsDisabled()
    {
        java.util.Iterator v0_1 = vn.ai.trueid.lottie.LottieDrawable.ALLOWED_REDUCED_MOTION_MARKERS.iterator();
        vn.ai.trueid.lottie.model.Marker v1_0 = 0;
        while (v0_1.hasNext()) {
            v1_0 = this.composition.getMarker(((String) v0_1.next()));
            if (v1_0 != null) {
                break;
            }
        }
        return v1_0;
    }

    public float getMaxFrame()
    {
        return this.animator.getMaxFrame();
    }

    public float getMinFrame()
    {
        return this.animator.getMinFrame();
    }

    public int getOpacity()
    {
        return -3;
    }

    public vn.ai.trueid.lottie.PerformanceTracker getPerformanceTracker()
    {
        int v0_0 = this.composition;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0.getPerformanceTracker();
        }
    }

    public float getProgress()
    {
        return this.animator.getAnimatedValueAbsolute();
    }

    public vn.ai.trueid.lottie.RenderMode getRenderMode()
    {
        if (!this.useSoftwareRendering) {
            return vn.ai.trueid.lottie.RenderMode.HARDWARE;
        } else {
            return vn.ai.trueid.lottie.RenderMode.SOFTWARE;
        }
    }

    public int getRepeatCount()
    {
        return this.animator.getRepeatCount();
    }

    public int getRepeatMode()
    {
        return this.animator.getRepeatMode();
    }

    public float getSpeed()
    {
        return this.animator.getSpeed();
    }

    public vn.ai.trueid.lottie.TextDelegate getTextDelegate()
    {
        return this.textDelegate;
    }

    public android.graphics.Typeface getTypeface(vn.ai.trueid.lottie.model.Font p4)
    {
        vn.ai.trueid.lottie.manager.FontAssetManager v0_0 = this.fontMap;
        if (v0_0 != null) {
            String v1_3 = p4.getFamily();
            if (!v0_0.containsKey(v1_3)) {
                String v1_4 = p4.getName();
                if (!v0_0.containsKey(v1_4)) {
                    String v1_1 = new StringBuilder();
                    v1_1.append(p4.getFamily());
                    v1_1.append("-");
                    v1_1.append(p4.getStyle());
                    String v1_2 = v1_1.toString();
                    if (v0_0.containsKey(v1_2)) {
                        return ((android.graphics.Typeface) v0_0.get(v1_2));
                    }
                } else {
                    return ((android.graphics.Typeface) v0_0.get(v1_4));
                }
            } else {
                return ((android.graphics.Typeface) v0_0.get(v1_3));
            }
        }
        vn.ai.trueid.lottie.manager.FontAssetManager v0_1 = this.getFontAssetManager();
        if (v0_1 == null) {
            return 0;
        } else {
            return v0_1.getTypeface(p4);
        }
    }

    public boolean hasMasks()
    {
        int v0_0 = this.compositionLayer;
        if ((v0_0 == 0) || (!v0_0.hasMasks())) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean hasMatte()
    {
        int v0_0 = this.compositionLayer;
        if ((v0_0 == 0) || (!v0_0.hasMatte())) {
            return 0;
        } else {
            return 1;
        }
    }

    public void invalidateDrawable(android.graphics.drawable.Drawable p1)
    {
        p1 = this.getCallback();
        if (p1 != null) {
            p1.invalidateDrawable(this);
            return;
        } else {
            return;
        }
    }

    public void invalidateSelf()
    {
        if (!this.isDirty) {
            this.isDirty = 1;
            if ((!vn.ai.trueid.lottie.LottieDrawable.invalidateSelfOnMainThread) || (android.os.Looper.getMainLooper() == android.os.Looper.myLooper())) {
                android.graphics.drawable.Drawable$Callback v0_1 = this.getCallback();
                if (v0_1 != null) {
                    v0_1.invalidateDrawable(this);
                }
            }
        }
        return;
    }

    public boolean isAnimating()
    {
        boolean v0_0 = this.animator;
        if (v0_0) {
            return v0_0.isRunning();
        } else {
            return 0;
        }
    }

    public boolean isAnimatingOrWillAnimateOnVisible()
    {
        if (!this.isVisible()) {
            int v0_4 = this.onVisibleAction;
            if ((v0_4 != vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.PLAY) && (v0_4 != vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.RESUME)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return this.animator.isRunning();
        }
    }

    public boolean isApplyingOpacityToLayersEnabled()
    {
        return this.isApplyingOpacityToLayersEnabled;
    }

    public boolean isApplyingShadowToLayersEnabled()
    {
        return this.isApplyingShadowToLayersEnabled;
    }

    public boolean isFeatureFlagEnabled(vn.ai.trueid.lottie.LottieFeatureFlag p2)
    {
        return this.lottieFeatureFlags.isFlagEnabled(p2);
    }

    public boolean isLooping()
    {
        if (this.animator.getRepeatCount() != -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isMergePathsEnabledForKitKatAndAbove()
    {
        return this.lottieFeatureFlags.isFlagEnabled(vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19);
    }

    public boolean isRunning()
    {
        return this.isAnimating();
    }

    public void loop(boolean p2)
    {
        int v2_1;
        if (p2 == 0) {
            v2_1 = 0;
        } else {
            v2_1 = -1;
        }
        this.animator.setRepeatCount(v2_1);
        return;
    }

    public void pauseAnimation()
    {
        this.lazyCompositionTasks.clear();
        this.animator.pauseAnimation();
        if (!this.isVisible()) {
            this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
        }
        return;
    }

    public void playAnimation()
    {
        if (this.compositionLayer != null) {
            this.computeRenderMode();
            if ((this.animationsEnabled(this.getContext())) || (this.getRepeatCount() == 0)) {
                if (!this.isVisible()) {
                    this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.PLAY;
                } else {
                    this.animator.playAnimation();
                    this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
                }
            }
            if (!this.animationsEnabled(this.getContext())) {
                int v0_7 = this.getMarkerForAnimationsDisabled();
                if (v0_7 == 0) {
                    int v0_10;
                    if (this.getSpeed() >= 0) {
                        v0_10 = this.getMaxFrame();
                    } else {
                        v0_10 = this.getMinFrame();
                    }
                    this.setFrame(((int) v0_10));
                } else {
                    this.setFrame(((int) v0_7.startFrame));
                }
                this.animator.endAnimation();
                if (!this.isVisible()) {
                    this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
                }
            }
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.m(this, 1));
            return;
        }
    }

    public void removeAllAnimatorListeners()
    {
        this.animator.removeAllListeners();
        return;
    }

    public void removeAllUpdateListeners()
    {
        this.animator.removeAllUpdateListeners();
        this.animator.addUpdateListener(this.progressUpdateListener);
        return;
    }

    public void removeAnimatorListener(android.animation.Animator$AnimatorListener p2)
    {
        this.animator.removeListener(p2);
        return;
    }

    public void removeAnimatorPauseListener(android.animation.Animator$AnimatorPauseListener p2)
    {
        this.animator.removePauseListener(p2);
        return;
    }

    public void removeAnimatorUpdateListener(android.animation.ValueAnimator$AnimatorUpdateListener p2)
    {
        this.animator.removeUpdateListener(p2);
        return;
    }

    public java.util.List resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p6)
    {
        if (this.compositionLayer != null) {
            java.util.ArrayList v0_2 = new java.util.ArrayList();
            String[] v4 = new String[0];
            this.compositionLayer.resolveKeyPath(p6, 0, v0_2, new vn.ai.trueid.lottie.model.KeyPath(v4));
            return v0_2;
        } else {
            vn.ai.trueid.lottie.utils.Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return java.util.Collections.EMPTY_LIST;
        }
    }

    public void resumeAnimation()
    {
        if (this.compositionLayer != null) {
            this.computeRenderMode();
            if ((this.animationsEnabled(this.getContext())) || (this.getRepeatCount() == 0)) {
                if (!this.isVisible()) {
                    this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.RESUME;
                } else {
                    this.animator.resumeAnimation();
                    this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
                }
            }
            if (!this.animationsEnabled(this.getContext())) {
                vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction v0_9;
                if (this.getSpeed() >= 0) {
                    v0_9 = this.getMaxFrame();
                } else {
                    v0_9 = this.getMinFrame();
                }
                this.setFrame(((int) v0_9));
                this.animator.endAnimation();
                if (!this.isVisible()) {
                    this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
                }
            }
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.m(this, 0));
            return;
        }
    }

    public void reverseAnimationSpeed()
    {
        this.animator.reverseAnimationSpeed();
        return;
    }

    public void scheduleDrawable(android.graphics.drawable.Drawable p1, Runnable p2, long p3)
    {
        p1 = this.getCallback();
        if (p1 != null) {
            p1.scheduleDrawable(this, p2, p3);
            return;
        } else {
            return;
        }
    }

    public void setAlpha(int p1)
    {
        this.alpha = p1;
        this.invalidateSelf();
        return;
    }

    public void setApplyingOpacityToLayersEnabled(boolean p1)
    {
        this.isApplyingOpacityToLayersEnabled = p1;
        return;
    }

    public void setApplyingShadowToLayersEnabled(boolean p1)
    {
        this.isApplyingShadowToLayersEnabled = p1;
        return;
    }

    public void setAsyncUpdates(vn.ai.trueid.lottie.AsyncUpdates p1)
    {
        this.asyncUpdates = p1;
        return;
    }

    public void setClipTextToBoundingBox(boolean p2)
    {
        if (p2 != this.clipTextToBoundingBox) {
            this.clipTextToBoundingBox = p2;
            this.invalidateSelf();
        }
        return;
    }

    public void setClipToCompositionBounds(boolean p2)
    {
        if (p2 != this.clipToCompositionBounds) {
            this.clipToCompositionBounds = p2;
            vn.ai.trueid.lottie.model.layer.CompositionLayer v0_1 = this.compositionLayer;
            if (v0_1 != null) {
                v0_1.setClipToCompositionBounds(p2);
            }
            this.invalidateSelf();
        }
        return;
    }

    public void setColorFilter(android.graphics.ColorFilter p1)
    {
        vn.ai.trueid.lottie.utils.Logger.warning("Use addColorFilter instead.");
        return;
    }

    public boolean setComposition(vn.ai.trueid.lottie.LottieComposition p4)
    {
        if (this.composition != p4) {
            this.isDirty = 1;
            this.clearComposition();
            this.composition = p4;
            this.buildCompositionLayer();
            this.animator.setComposition(p4);
            this.setProgress(this.animator.getAnimatedFraction());
            int v1_3 = new java.util.ArrayList(this.lazyCompositionTasks).iterator();
            while (v1_3.hasNext()) {
                vn.ai.trueid.lottie.LottieDrawable$LazyCompositionTask v2_3 = ((vn.ai.trueid.lottie.LottieDrawable$LazyCompositionTask) v1_3.next());
                if (v2_3 != null) {
                    v2_3.run(p4);
                }
                v1_3.remove();
            }
            this.lazyCompositionTasks.clear();
            p4.setPerformanceTrackingEnabled(this.performanceTrackingEnabled);
            this.computeRenderMode();
            android.widget.ImageView v4_1 = this.getCallback();
            if ((v4_1 instanceof android.widget.ImageView)) {
                android.widget.ImageView v4_2 = ((android.widget.ImageView) v4_1);
                v4_2.setImageDrawable(0);
                v4_2.setImageDrawable(this);
            }
            return 1;
        } else {
            return 0;
        }
    }

    public void setDefaultFontFileExtension(String p2)
    {
        this.defaultFontFileExtension = p2;
        vn.ai.trueid.lottie.manager.FontAssetManager v0 = this.getFontAssetManager();
        if (v0 != null) {
            v0.setDefaultFontFileExtension(p2);
        }
        return;
    }

    public void setFontAssetDelegate(vn.ai.trueid.lottie.FontAssetDelegate p2)
    {
        this.fontAssetDelegate = p2;
        vn.ai.trueid.lottie.manager.FontAssetManager v0 = this.fontAssetManager;
        if (v0 != null) {
            v0.setDelegate(p2);
        }
        return;
    }

    public void setFontMap(java.util.Map p2)
    {
        if (p2 != this.fontMap) {
            this.fontMap = p2;
            this.invalidateSelf();
            return;
        } else {
            return;
        }
    }

    public void setFrame(int p4)
    {
        if (this.composition != null) {
            this.animator.setFrame(((float) p4));
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.j(this, p4, 0));
            return;
        }
    }

    public void setIgnoreDisabledSystemAnimations(boolean p1)
    {
        this.ignoreSystemAnimationsDisabled = p1;
        return;
    }

    public void setImageAssetDelegate(vn.ai.trueid.lottie.ImageAssetDelegate p2)
    {
        this.imageAssetDelegate = p2;
        vn.ai.trueid.lottie.manager.ImageAssetManager v0 = this.imageAssetManager;
        if (v0 != null) {
            v0.setDelegate(p2);
        }
        return;
    }

    public void setImagesAssetsFolder(String p1)
    {
        this.imageAssetsFolder = p1;
        return;
    }

    public void setMaintainOriginalImageBounds(boolean p1)
    {
        this.maintainOriginalImageBounds = p1;
        return;
    }

    public void setMaxFrame(int p4)
    {
        if (this.composition != null) {
            this.animator.setMaxFrame((((float) p4) + 1065185444));
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.j(this, p4, 1));
            return;
        }
    }

    public void setMaxFrame(String p4)
    {
        IllegalArgumentException v0_0 = this.composition;
        if (v0_0 != null) {
            IllegalArgumentException v0_4 = v0_0.getMarker(p4);
            if (v0_4 == null) {
                throw new IllegalArgumentException(A1.a.i("Cannot find marker with name ", p4, "."));
            } else {
                this.setMaxFrame(((int) (v0_4.startFrame + v0_4.durationFrames)));
                return;
            }
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.k(this, p4, 2));
            return;
        }
    }

    public void setMaxProgress(float p4)
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            this.animator.setMaxFrame(vn.ai.trueid.lottie.utils.MiscUtils.lerp(v0_0.getStartFrame(), this.composition.getEndFrame(), p4));
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.n(this, p4, 0));
            return;
        }
    }

    public void setMinAndMaxFrame(int p3, int p4)
    {
        if (this.composition != null) {
            this.animator.setMinAndMaxFrames(((float) p3), (((float) p4) + 1065185444));
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.l(this, p3, p4));
            return;
        }
    }

    public void setMinAndMaxFrame(String p4)
    {
        IllegalArgumentException v0_0 = this.composition;
        if (v0_0 != null) {
            IllegalArgumentException v0_6 = v0_0.getMarker(p4);
            if (v0_6 == null) {
                throw new IllegalArgumentException(A1.a.i("Cannot find marker with name ", p4, "."));
            } else {
                String v4_2 = ((int) v0_6.startFrame);
                this.setMinAndMaxFrame(v4_2, (((int) v0_6.durationFrames) + v4_2));
                return;
            }
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.k(this, p4, 0));
            return;
        }
    }

    public void setMinAndMaxFrame(String p4, String p5, boolean p6)
    {
        vn.ai.trueid.lottie.model.Marker v0_0 = this.composition;
        if (v0_0 != null) {
            vn.ai.trueid.lottie.model.Marker v0_3 = v0_0.getMarker(p4);
            if (v0_3 == null) {
                throw new IllegalArgumentException(A1.a.i("Cannot find marker with name ", p4, "."));
            } else {
                IllegalArgumentException v4_2 = ((int) v0_3.startFrame);
                vn.ai.trueid.lottie.model.Marker v0_2 = this.composition.getMarker(p5);
                if (v0_2 == null) {
                    throw new IllegalArgumentException(A1.a.i("Cannot find marker with name ", p5, "."));
                } else {
                    int v6_1;
                    if (p6 == 0) {
                        v6_1 = 0;
                    } else {
                        v6_1 = 1065353216;
                    }
                    this.setMinAndMaxFrame(v4_2, ((int) (v0_2.startFrame + v6_1)));
                    return;
                }
            }
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.q(this, p4, p5, p6));
            return;
        }
    }

    public void setMinAndMaxProgress(float p3, float p4)
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            this.setMinAndMaxFrame(((int) vn.ai.trueid.lottie.utils.MiscUtils.lerp(v0_0.getStartFrame(), this.composition.getEndFrame(), p3)), ((int) vn.ai.trueid.lottie.utils.MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), p4)));
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.r(this, p3, p4));
            return;
        }
    }

    public void setMinFrame(int p4)
    {
        if (this.composition != null) {
            this.animator.setMinFrame(p4);
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.j(this, p4, 2));
            return;
        }
    }

    public void setMinFrame(String p4)
    {
        IllegalArgumentException v0_0 = this.composition;
        if (v0_0 != null) {
            IllegalArgumentException v0_3 = v0_0.getMarker(p4);
            if (v0_3 == null) {
                throw new IllegalArgumentException(A1.a.i("Cannot find marker with name ", p4, "."));
            } else {
                this.setMinFrame(((int) v0_3.startFrame));
                return;
            }
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.k(this, p4, 1));
            return;
        }
    }

    public void setMinProgress(float p4)
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            this.setMinFrame(((int) vn.ai.trueid.lottie.utils.MiscUtils.lerp(v0_0.getStartFrame(), this.composition.getEndFrame(), p4)));
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.n(this, p4, 2));
            return;
        }
    }

    public void setOutlineMasksAndMattes(boolean p2)
    {
        if (this.outlineMasksAndMattes != p2) {
            this.outlineMasksAndMattes = p2;
            vn.ai.trueid.lottie.model.layer.CompositionLayer v0_1 = this.compositionLayer;
            if (v0_1 != null) {
                v0_1.setOutlineMasksAndMattes(p2);
            }
        }
        return;
    }

    public void setPerformanceTrackingEnabled(boolean p2)
    {
        this.performanceTrackingEnabled = p2;
        vn.ai.trueid.lottie.LottieComposition v0 = this.composition;
        if (v0 != null) {
            v0.setPerformanceTrackingEnabled(p2);
        }
        return;
    }

    public void setProgress(float p4)
    {
        if (this.composition != null) {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.beginSection("Drawable#setProgress");
            }
            this.animator.setFrame(this.composition.getFrameForProgress(p4));
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("Drawable#setProgress");
            }
            return;
        } else {
            this.lazyCompositionTasks.add(new vn.ai.trueid.lottie.n(this, p4, 1));
            return;
        }
    }

    public void setRenderMode(vn.ai.trueid.lottie.RenderMode p1)
    {
        this.renderMode = p1;
        this.computeRenderMode();
        return;
    }

    public void setRepeatCount(int p2)
    {
        this.animator.setRepeatCount(p2);
        return;
    }

    public void setRepeatMode(int p2)
    {
        this.animator.setRepeatMode(p2);
        return;
    }

    public void setSafeMode(boolean p1)
    {
        this.safeMode = p1;
        return;
    }

    public void setSpeed(float p2)
    {
        this.animator.setSpeed(p2);
        return;
    }

    public void setSystemAnimationsAreEnabled(Boolean p1)
    {
        this.systemAnimationsEnabled = p1.booleanValue();
        return;
    }

    public void setTextDelegate(vn.ai.trueid.lottie.TextDelegate p1)
    {
        this.textDelegate = p1;
        return;
    }

    public void setUseCompositionFrameRate(boolean p2)
    {
        this.animator.setUseCompositionFrameRate(p2);
        return;
    }

    public boolean setVisible(boolean p2, boolean p3)
    {
        vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction v0_0 = this.isVisible();
        boolean v3_1 = super.setVisible(p2, p3);
        if (p2 == null) {
            if (!this.animator.isRunning()) {
                if (v0_0 != null) {
                    this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE;
                }
            } else {
                this.pauseAnimation();
                this.onVisibleAction = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.RESUME;
                return v3_1;
            }
        } else {
            vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction v2_3 = this.onVisibleAction;
            if (v2_3 != vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.PLAY) {
                if (v2_3 == vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.RESUME) {
                    this.resumeAnimation();
                    return v3_1;
                }
            } else {
                this.playAnimation();
                return v3_1;
            }
        }
        return v3_1;
    }

    public void start()
    {
        boolean v0_0 = this.getCallback();
        if ((!(v0_0 instanceof android.view.View)) || (!((android.view.View) v0_0).isInEditMode())) {
            this.playAnimation();
            return;
        } else {
            return;
        }
    }

    public void stop()
    {
        this.endAnimation();
        return;
    }

    public void unscheduleDrawable(android.graphics.drawable.Drawable p1, Runnable p2)
    {
        p1 = this.getCallback();
        if (p1 != null) {
            p1.unscheduleDrawable(this, p2);
            return;
        } else {
            return;
        }
    }

    public android.graphics.Bitmap updateBitmap(String p2, android.graphics.Bitmap p3)
    {
        vn.ai.trueid.lottie.manager.ImageAssetManager v0 = this.getImageAssetManager();
        if (v0 != null) {
            android.graphics.Bitmap v2_1 = v0.updateBitmap(p2, p3);
            this.invalidateSelf();
            return v2_1;
        } else {
            vn.ai.trueid.lottie.utils.Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return 0;
        }
    }

    public boolean useTextGlyphs()
    {
        if ((this.fontMap != null) || ((this.textDelegate != null) || (this.composition.getCharacters().l() <= 0))) {
            return 0;
        } else {
            return 1;
        }
    }
}
