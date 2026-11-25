package vn.ai.trueid.lottie;
public class LottieAnimationView extends androidx.appcompat.widget.AppCompatImageView {
    private static final vn.ai.trueid.lottie.LottieListener DEFAULT_FAILURE_LISTENER = None;
    private static final String TAG = "LottieAnimationView";
    private String animationName;
    private int animationResId;
    private boolean autoPlay;
    private boolean cacheComposition;
    private vn.ai.trueid.lottie.LottieTask compositionTask;
    private vn.ai.trueid.lottie.LottieListener failureListener;
    private int fallbackResource;
    private boolean ignoreUnschedule;
    private final vn.ai.trueid.lottie.LottieListener loadedListener;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final java.util.Set lottieOnCompositionLoadedListeners;
    private final java.util.Set userActionsTaken;
    private final vn.ai.trueid.lottie.LottieListener wrappedFailureListener;

    static LottieAnimationView()
    {
        vn.ai.trueid.lottie.LottieAnimationView.DEFAULT_FAILURE_LISTENER = new vn.ai.trueid.lottie.d();
        return;
    }

    public LottieAnimationView(android.content.Context p3)
    {
        super(p3, 0);
        super.loadedListener = new vn.ai.trueid.lottie.LottieAnimationView$WeakSuccessListener(super);
        super.wrappedFailureListener = new vn.ai.trueid.lottie.LottieAnimationView$WeakFailureListener(super);
        super.fallbackResource = 0;
        super.lottieDrawable = new vn.ai.trueid.lottie.LottieDrawable();
        super.ignoreUnschedule = 0;
        super.autoPlay = 0;
        super.cacheComposition = 1;
        super.userActionsTaken = new java.util.HashSet();
        super.lottieOnCompositionLoadedListeners = new java.util.HashSet();
        super.init(0, vn.ai.trueid.lottie.R$attr.lottieAnimationViewStyle);
        return;
    }

    public LottieAnimationView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        super.loadedListener = new vn.ai.trueid.lottie.LottieAnimationView$WeakSuccessListener(super);
        super.wrappedFailureListener = new vn.ai.trueid.lottie.LottieAnimationView$WeakFailureListener(super);
        super.fallbackResource = 0;
        super.lottieDrawable = new vn.ai.trueid.lottie.LottieDrawable();
        super.ignoreUnschedule = 0;
        super.autoPlay = 0;
        super.cacheComposition = 1;
        super.userActionsTaken = new java.util.HashSet();
        super.lottieOnCompositionLoadedListeners = new java.util.HashSet();
        super.init(p3, vn.ai.trueid.lottie.R$attr.lottieAnimationViewStyle);
        return;
    }

    public LottieAnimationView(android.content.Context p2, android.util.AttributeSet p3, int p4)
    {
        super(p2, p3, p4);
        super.loadedListener = new vn.ai.trueid.lottie.LottieAnimationView$WeakSuccessListener(super);
        super.wrappedFailureListener = new vn.ai.trueid.lottie.LottieAnimationView$WeakFailureListener(super);
        super.fallbackResource = 0;
        super.lottieDrawable = new vn.ai.trueid.lottie.LottieDrawable();
        super.ignoreUnschedule = 0;
        super.autoPlay = 0;
        super.cacheComposition = 1;
        super.userActionsTaken = new java.util.HashSet();
        super.lottieOnCompositionLoadedListeners = new java.util.HashSet();
        super.init(p3, p4);
        return;
    }

    public static synthetic int access$000(vn.ai.trueid.lottie.LottieAnimationView p0)
    {
        return p0.fallbackResource;
    }

    public static synthetic vn.ai.trueid.lottie.LottieListener access$100(vn.ai.trueid.lottie.LottieAnimationView p0)
    {
        return p0.failureListener;
    }

    public static synthetic vn.ai.trueid.lottie.LottieListener access$200()
    {
        return vn.ai.trueid.lottie.LottieAnimationView.DEFAULT_FAILURE_LISTENER;
    }

    private void cancelLoaderTask()
    {
        vn.ai.trueid.lottie.LottieTask v0_0 = this.compositionTask;
        if (v0_0 != null) {
            v0_0.removeListener(this.loadedListener);
            this.compositionTask.removeFailureListener(this.wrappedFailureListener);
        }
        return;
    }

    private void clearComposition()
    {
        this.lottieDrawable.clearComposition();
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult d(vn.ai.trueid.lottie.LottieAnimationView p0, int p1)
    {
        return p0.lambda$fromRawRes$1(p1);
    }

    public static synthetic void e(Throwable p0)
    {
        vn.ai.trueid.lottie.LottieAnimationView.lambda$static$0(p0);
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult f(vn.ai.trueid.lottie.LottieAnimationView p0, String p1)
    {
        return p0.lambda$fromAssets$2(p1);
    }

    private vn.ai.trueid.lottie.LottieTask fromAssets(String p4)
    {
        if (!this.isInEditMode()) {
            if (!this.cacheComposition) {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromAsset(this.getContext(), p4, 0);
            } else {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromAsset(this.getContext(), p4);
            }
        } else {
            return new vn.ai.trueid.lottie.LottieTask(new vn.ai.trueid.lottie.c(p4, 0, this), 1);
        }
    }

    private vn.ai.trueid.lottie.LottieTask fromRawRes(int p3)
    {
        if (!this.isInEditMode()) {
            if (!this.cacheComposition) {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromRawRes(this.getContext(), p3, 0);
            } else {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromRawRes(this.getContext(), p3);
            }
        } else {
            return new vn.ai.trueid.lottie.LottieTask(new vn.ai.trueid.lottie.b(this, p3), 1);
        }
    }

    private void init(android.util.AttributeSet p6, int p7)
    {
        IllegalArgumentException v6_3 = this.getContext().obtainStyledAttributes(p6, vn.ai.trueid.lottie.R$styleable.LottieAnimationView, p7, 0);
        this.cacheComposition = v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_cacheComposition, 1);
        boolean v7_15 = v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_rawRes);
        int v1_6 = v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_fileName);
        boolean vtmp6 = v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_url);
        if ((v7_15) && (v1_6 != 0)) {
            throw new IllegalArgumentException(wmoi.igob.ggcn(891));
        } else {
            if (!v7_15) {
                if (v1_6 == 0) {
                    if (vtmp6) {
                        boolean v7_45 = v6_3.getString(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_url);
                        if (v7_45) {
                            this.setAnimationFromUrl(v7_45);
                        }
                    }
                } else {
                    boolean v7_47 = v6_3.getString(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_fileName);
                    if (v7_47) {
                        this.setAnimation(v7_47);
                    }
                }
            } else {
                boolean v7_49 = v6_3.getResourceId(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_rawRes, 0);
                if (v7_49) {
                    this.setAnimation(v7_49);
                }
            }
            this.setFallbackResource(v6_3.getResourceId(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_fallbackRes, 0));
            if (v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_autoPlay, 0)) {
                this.autoPlay = 1;
            }
            if (v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_loop, 0)) {
                this.lottieDrawable.setRepeatCount(-1);
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_repeatMode)) {
                this.setRepeatMode(v6_3.getInt(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_repeatMode, 1));
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_repeatCount)) {
                this.setRepeatCount(v6_3.getInt(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_repeatCount, -1));
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_speed)) {
                this.setSpeed(v6_3.getFloat(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_speed, 1065353216));
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_clipToCompositionBounds)) {
                this.setClipToCompositionBounds(v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_clipToCompositionBounds, 1));
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_clipTextToBoundingBox)) {
                this.setClipTextToBoundingBox(v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_clipTextToBoundingBox, 0));
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_defaultFontFileExtension)) {
                this.setDefaultFontFileExtension(v6_3.getString(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_defaultFontFileExtension));
            }
            this.setImageAssetsFolder(v6_3.getString(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_imageAssetsFolder));
            this.setProgressInternal(v6_3.getFloat(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_progress, 0), v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_progress));
            this.enableMergePathsForKitKatAndAbove(v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, 0));
            this.setApplyingOpacityToLayersEnabled(v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_applyOpacityToLayers, 0));
            this.setApplyingShadowToLayersEnabled(v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_applyShadowToLayers, 1));
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_colorFilter)) {
                this.addValueCallback(new vn.ai.trueid.lottie.model.KeyPath(new String[] {wmoi.igob.ggcn(892)})), vn.ai.trueid.lottie.LottieProperty.COLOR_FILTER, new vn.ai.trueid.lottie.value.LottieValueCallback(new vn.ai.trueid.lottie.SimpleColorFilter(androidx.core.content.c.b(this.getContext(), v6_3.getResourceId(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_colorFilter, -1)).getDefaultColor())));
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_renderMode)) {
                vn.ai.trueid.lottie.AsyncUpdates[] v0_5 = vn.ai.trueid.lottie.RenderMode.AUTOMATIC;
                boolean v7_29 = v6_3.getInt(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_renderMode, v0_5.ordinal());
                if (v7_29 >= vn.ai.trueid.lottie.RenderMode.values().length) {
                    v7_29 = v0_5.ordinal();
                }
                this.setRenderMode(vn.ai.trueid.lottie.RenderMode.values()[v7_29]);
            }
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_asyncUpdates)) {
                vn.ai.trueid.lottie.AsyncUpdates[] v0_7 = vn.ai.trueid.lottie.AsyncUpdates.AUTOMATIC;
                boolean v7_34 = v6_3.getInt(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_asyncUpdates, v0_7.ordinal());
                if (v7_34 >= vn.ai.trueid.lottie.RenderMode.values().length) {
                    v7_34 = v0_7.ordinal();
                }
                this.setAsyncUpdates(vn.ai.trueid.lottie.AsyncUpdates.values()[v7_34]);
            }
            this.setIgnoreDisabledSystemAnimations(v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_ignoreDisabledSystemAnimations, 0));
            if (v6_3.hasValue(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_useCompositionFrameRate)) {
                this.setUseCompositionFrameRate(v6_3.getBoolean(vn.ai.trueid.lottie.R$styleable.LottieAnimationView_lottie_useCompositionFrameRate, 0));
            }
            v6_3.recycle();
            return;
        }
    }

    private synthetic vn.ai.trueid.lottie.LottieResult lambda$fromAssets$2(String p3)
    {
        if (!this.cacheComposition) {
            return vn.ai.trueid.lottie.LottieCompositionFactory.fromAssetSync(this.getContext(), p3, 0);
        } else {
            return vn.ai.trueid.lottie.LottieCompositionFactory.fromAssetSync(this.getContext(), p3);
        }
    }

    private synthetic vn.ai.trueid.lottie.LottieResult lambda$fromRawRes$1(int p3)
    {
        if (!this.cacheComposition) {
            return vn.ai.trueid.lottie.LottieCompositionFactory.fromRawResSync(this.getContext(), p3, 0);
        } else {
            return vn.ai.trueid.lottie.LottieCompositionFactory.fromRawResSync(this.getContext(), p3);
        }
    }

    private static synthetic void lambda$static$0(Throwable p2)
    {
        if (!vn.ai.trueid.lottie.utils.Utils.isNetworkException(p2)) {
            throw new IllegalStateException(wmoi.igob.ggcn(894), p2);
        } else {
            vn.ai.trueid.lottie.utils.Logger.warning(wmoi.igob.ggcn(893), p2);
            return;
        }
    }

    private void setCompositionTask(vn.ai.trueid.lottie.LottieTask p4)
    {
        Object v0_0 = p4.getResult();
        vn.ai.trueid.lottie.LottieComposition v1_1 = this.lottieDrawable;
        if ((v0_0 == null) || ((v1_1 != this.getDrawable()) || (v1_1.getComposition() != v0_0.getValue()))) {
            this.userActionsTaken.add(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_ANIMATION);
            this.clearComposition();
            this.cancelLoaderTask();
            this.compositionTask = p4.addListener(this.loadedListener).addFailureListener(this.wrappedFailureListener);
            return;
        } else {
            return;
        }
    }

    private void setLottieDrawable()
    {
        vn.ai.trueid.lottie.LottieDrawable v0_0 = this.isAnimating();
        this.setImageDrawable(0);
        this.setImageDrawable(this.lottieDrawable);
        if (v0_0 != null) {
            this.lottieDrawable.resumeAnimation();
        }
        return;
    }

    private void setProgressInternal(float p2, boolean p3)
    {
        if (p3 != null) {
            this.userActionsTaken.add(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_PROGRESS);
        }
        this.lottieDrawable.setProgress(p2);
        return;
    }

    public void addAnimatorListener(android.animation.Animator$AnimatorListener p2)
    {
        this.lottieDrawable.addAnimatorListener(p2);
        return;
    }

    public void addAnimatorPauseListener(android.animation.Animator$AnimatorPauseListener p2)
    {
        this.lottieDrawable.addAnimatorPauseListener(p2);
        return;
    }

    public void addAnimatorUpdateListener(android.animation.ValueAnimator$AnimatorUpdateListener p2)
    {
        this.lottieDrawable.addAnimatorUpdateListener(p2);
        return;
    }

    public boolean addLottieOnCompositionLoadedListener(vn.ai.trueid.lottie.LottieOnCompositionLoadedListener p2)
    {
        java.util.Set v0_0 = this.getComposition();
        if (v0_0 != null) {
            p2.onCompositionLoaded(v0_0);
        }
        return this.lottieOnCompositionLoadedListeners.add(p2);
    }

    public void addValueCallback(vn.ai.trueid.lottie.model.KeyPath p2, Object p3, vn.ai.trueid.lottie.value.LottieValueCallback p4)
    {
        this.lottieDrawable.addValueCallback(p2, p3, p4);
        return;
    }

    public void addValueCallback(vn.ai.trueid.lottie.model.KeyPath p3, Object p4, vn.ai.trueid.lottie.value.SimpleLottieValueCallback p5)
    {
        this.lottieDrawable.addValueCallback(p3, p4, new vn.ai.trueid.lottie.LottieAnimationView$1(this, p5));
        return;
    }

    public void cancelAnimation()
    {
        this.autoPlay = 0;
        this.userActionsTaken.add(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.cancelAnimation();
        return;
    }

    public void clearValueCallback(vn.ai.trueid.lottie.model.KeyPath p3, Object p4)
    {
        this.lottieDrawable.addValueCallback(p3, p4, 0);
        return;
    }

    public void disableExtraScaleModeInFitXY()
    {
        this.lottieDrawable.disableExtraScaleModeInFitXY();
        return;
    }

    public void enableFeatureFlag(vn.ai.trueid.lottie.LottieFeatureFlag p2, boolean p3)
    {
        this.lottieDrawable.enableFeatureFlag(p2, p3);
        return;
    }

    public void enableMergePathsForKitKatAndAbove(boolean p3)
    {
        this.lottieDrawable.enableFeatureFlag(vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19, p3);
        return;
    }

    public vn.ai.trueid.lottie.AsyncUpdates getAsyncUpdates()
    {
        return this.lottieDrawable.getAsyncUpdates();
    }

    public boolean getAsyncUpdatesEnabled()
    {
        return this.lottieDrawable.getAsyncUpdatesEnabled();
    }

    public boolean getClipTextToBoundingBox()
    {
        return this.lottieDrawable.getClipTextToBoundingBox();
    }

    public boolean getClipToCompositionBounds()
    {
        return this.lottieDrawable.getClipToCompositionBounds();
    }

    public vn.ai.trueid.lottie.LottieComposition getComposition()
    {
        vn.ai.trueid.lottie.LottieDrawable v1 = this.lottieDrawable;
        if (this.getDrawable() != v1) {
            return 0;
        } else {
            return v1.getComposition();
        }
    }

    public long getDuration()
    {
        long v0_0 = this.getComposition();
        if (v0_0 == 0) {
            return 0;
        } else {
            return ((long) v0_0.getDuration());
        }
    }

    public int getFrame()
    {
        return this.lottieDrawable.getFrame();
    }

    public String getImageAssetsFolder()
    {
        return this.lottieDrawable.getImageAssetsFolder();
    }

    public boolean getMaintainOriginalImageBounds()
    {
        return this.lottieDrawable.getMaintainOriginalImageBounds();
    }

    public float getMaxFrame()
    {
        return this.lottieDrawable.getMaxFrame();
    }

    public float getMinFrame()
    {
        return this.lottieDrawable.getMinFrame();
    }

    public vn.ai.trueid.lottie.PerformanceTracker getPerformanceTracker()
    {
        return this.lottieDrawable.getPerformanceTracker();
    }

    public float getProgress()
    {
        return this.lottieDrawable.getProgress();
    }

    public vn.ai.trueid.lottie.RenderMode getRenderMode()
    {
        return this.lottieDrawable.getRenderMode();
    }

    public int getRepeatCount()
    {
        return this.lottieDrawable.getRepeatCount();
    }

    public int getRepeatMode()
    {
        return this.lottieDrawable.getRepeatMode();
    }

    public float getSpeed()
    {
        return this.lottieDrawable.getSpeed();
    }

    public boolean hasMasks()
    {
        return this.lottieDrawable.hasMasks();
    }

    public boolean hasMatte()
    {
        return this.lottieDrawable.hasMatte();
    }

    public void invalidate()
    {
        super.invalidate();
        vn.ai.trueid.lottie.LottieDrawable v0_1 = this.getDrawable();
        if (((v0_1 instanceof vn.ai.trueid.lottie.LottieDrawable)) && (((vn.ai.trueid.lottie.LottieDrawable) v0_1).getRenderMode() == vn.ai.trueid.lottie.RenderMode.SOFTWARE)) {
            this.lottieDrawable.invalidateSelf();
        }
        return;
    }

    public void invalidateDrawable(android.graphics.drawable.Drawable p3)
    {
        vn.ai.trueid.lottie.LottieDrawable v1 = this.lottieDrawable;
        if (this.getDrawable() != v1) {
            super.invalidateDrawable(p3);
            return;
        } else {
            super.invalidateDrawable(v1);
            return;
        }
    }

    public boolean isAnimating()
    {
        return this.lottieDrawable.isAnimating();
    }

    public boolean isFeatureFlagEnabled(vn.ai.trueid.lottie.LottieFeatureFlag p2)
    {
        return this.lottieDrawable.isFeatureFlagEnabled(p2);
    }

    public boolean isMergePathsEnabledForKitKatAndAbove()
    {
        return this.lottieDrawable.isFeatureFlagEnabled(vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19);
    }

    public void loop(boolean p2)
    {
        int v2_1;
        if (p2 == 0) {
            v2_1 = 0;
        } else {
            v2_1 = -1;
        }
        this.lottieDrawable.setRepeatCount(v2_1);
        return;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if ((!this.isInEditMode()) && (this.autoPlay)) {
            this.lottieDrawable.playAnimation();
        }
        return;
    }

    public void onRestoreInstanceState(android.os.Parcelable p3)
    {
        if ((p3 instanceof vn.ai.trueid.lottie.LottieAnimationView$SavedState)) {
            super.onRestoreInstanceState(((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).getSuperState());
            this.animationName = ((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).animationName;
            vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken v1_6 = vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_ANIMATION;
            if ((!this.userActionsTaken.contains(v1_6)) && (!android.text.TextUtils.isEmpty(this.animationName))) {
                this.setAnimation(this.animationName);
            }
            this.animationResId = ((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).animationResId;
            if (!this.userActionsTaken.contains(v1_6)) {
                boolean v0_8 = this.animationResId;
                if (v0_8) {
                    this.setAnimation(v0_8);
                }
            }
            if (!this.userActionsTaken.contains(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_PROGRESS)) {
                this.setProgressInternal(((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).progress, 0);
            }
            if ((!this.userActionsTaken.contains(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.PLAY_OPTION)) && (((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).isAnimating)) {
                this.playAnimation();
            }
            if (!this.userActionsTaken.contains(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_IMAGE_ASSETS)) {
                this.setImageAssetsFolder(((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).imageAssetsFolder);
            }
            if (!this.userActionsTaken.contains(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_REPEAT_MODE)) {
                this.setRepeatMode(((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).repeatMode);
            }
            if (!this.userActionsTaken.contains(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_REPEAT_COUNT)) {
                this.setRepeatCount(((vn.ai.trueid.lottie.LottieAnimationView$SavedState) p3).repeatCount);
            }
            return;
        } else {
            super.onRestoreInstanceState(p3);
            return;
        }
    }

    public android.os.Parcelable onSaveInstanceState()
    {
        vn.ai.trueid.lottie.LottieAnimationView$SavedState v1_1 = new vn.ai.trueid.lottie.LottieAnimationView$SavedState(super.onSaveInstanceState());
        v1_1.animationName = this.animationName;
        v1_1.animationResId = this.animationResId;
        v1_1.progress = this.lottieDrawable.getProgress();
        v1_1.isAnimating = this.lottieDrawable.isAnimatingOrWillAnimateOnVisible();
        v1_1.imageAssetsFolder = this.lottieDrawable.getImageAssetsFolder();
        v1_1.repeatMode = this.lottieDrawable.getRepeatMode();
        v1_1.repeatCount = this.lottieDrawable.getRepeatCount();
        return v1_1;
    }

    public void pauseAnimation()
    {
        this.autoPlay = 0;
        this.lottieDrawable.pauseAnimation();
        return;
    }

    public void playAnimation()
    {
        this.userActionsTaken.add(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.playAnimation();
        return;
    }

    public void removeAllAnimatorListeners()
    {
        this.lottieDrawable.removeAllAnimatorListeners();
        return;
    }

    public void removeAllLottieOnCompositionLoadedListener()
    {
        this.lottieOnCompositionLoadedListeners.clear();
        return;
    }

    public void removeAllUpdateListeners()
    {
        this.lottieDrawable.removeAllUpdateListeners();
        return;
    }

    public void removeAnimatorListener(android.animation.Animator$AnimatorListener p2)
    {
        this.lottieDrawable.removeAnimatorListener(p2);
        return;
    }

    public void removeAnimatorPauseListener(android.animation.Animator$AnimatorPauseListener p2)
    {
        this.lottieDrawable.removeAnimatorPauseListener(p2);
        return;
    }

    public boolean removeLottieOnCompositionLoadedListener(vn.ai.trueid.lottie.LottieOnCompositionLoadedListener p2)
    {
        return this.lottieOnCompositionLoadedListeners.remove(p2);
    }

    public void removeUpdateListener(android.animation.ValueAnimator$AnimatorUpdateListener p2)
    {
        this.lottieDrawable.removeAnimatorUpdateListener(p2);
        return;
    }

    public java.util.List resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p2)
    {
        return this.lottieDrawable.resolveKeyPath(p2);
    }

    public void resumeAnimation()
    {
        this.userActionsTaken.add(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.resumeAnimation();
        return;
    }

    public void reverseAnimationSpeed()
    {
        this.lottieDrawable.reverseAnimationSpeed();
        return;
    }

    public void setAnimation(int p2)
    {
        this.animationResId = p2;
        this.animationName = 0;
        this.setCompositionTask(this.fromRawRes(p2));
        return;
    }

    public void setAnimation(java.io.InputStream p1, String p2)
    {
        this.setCompositionTask(vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStream(p1, p2));
        return;
    }

    public void setAnimation(String p2)
    {
        this.animationName = p2;
        this.animationResId = 0;
        this.setCompositionTask(this.fromAssets(p2));
        return;
    }

    public void setAnimation(java.util.zip.ZipInputStream p1, String p2)
    {
        this.setCompositionTask(vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStream(p1, p2));
        return;
    }

    public void setAnimationFromJson(String p2)
    {
        this.setAnimationFromJson(p2, 0);
        return;
    }

    public void setAnimationFromJson(String p2, String p3)
    {
        this.setAnimation(new java.io.ByteArrayInputStream(p2.getBytes()), p3);
        return;
    }

    public void setAnimationFromUrl(String p3)
    {
        vn.ai.trueid.lottie.LottieTask v3_1;
        if (!this.cacheComposition) {
            v3_1 = vn.ai.trueid.lottie.LottieCompositionFactory.fromUrl(this.getContext(), p3, 0);
        } else {
            v3_1 = vn.ai.trueid.lottie.LottieCompositionFactory.fromUrl(this.getContext(), p3);
        }
        this.setCompositionTask(v3_1);
        return;
    }

    public void setAnimationFromUrl(String p2, String p3)
    {
        this.setCompositionTask(vn.ai.trueid.lottie.LottieCompositionFactory.fromUrl(this.getContext(), p2, p3));
        return;
    }

    public void setApplyingOpacityToLayersEnabled(boolean p2)
    {
        this.lottieDrawable.setApplyingOpacityToLayersEnabled(p2);
        return;
    }

    public void setApplyingShadowToLayersEnabled(boolean p2)
    {
        this.lottieDrawable.setApplyingShadowToLayersEnabled(p2);
        return;
    }

    public void setAsyncUpdates(vn.ai.trueid.lottie.AsyncUpdates p2)
    {
        this.lottieDrawable.setAsyncUpdates(p2);
        return;
    }

    public void setCacheComposition(boolean p1)
    {
        this.cacheComposition = p1;
        return;
    }

    public void setClipTextToBoundingBox(boolean p2)
    {
        this.lottieDrawable.setClipTextToBoundingBox(p2);
        return;
    }

    public void setClipToCompositionBounds(boolean p2)
    {
        this.lottieDrawable.setClipToCompositionBounds(p2);
        return;
    }

    public void setComposition(vn.ai.trueid.lottie.LottieComposition p4)
    {
        if (vn.ai.trueid.lottie.L.DBG) {
            java.util.Objects.toString(p4);
        }
        this.lottieDrawable.setCallback(this);
        this.ignoreUnschedule = 1;
        java.util.Iterator v0_7 = this.lottieDrawable.setComposition(p4);
        if (this.autoPlay) {
            this.lottieDrawable.playAnimation();
        }
        this.ignoreUnschedule = 0;
        if ((this.getDrawable() != this.lottieDrawable) || (v0_7 != null)) {
            if (v0_7 == null) {
                this.setLottieDrawable();
            }
            this.onVisibilityChanged(this, this.getVisibility());
            this.requestLayout();
            java.util.Iterator v0_3 = this.lottieOnCompositionLoadedListeners.iterator();
            while (v0_3.hasNext()) {
                ((vn.ai.trueid.lottie.LottieOnCompositionLoadedListener) v0_3.next()).onCompositionLoaded(p4);
            }
        }
        return;
    }

    public void setDefaultFontFileExtension(String p2)
    {
        this.lottieDrawable.setDefaultFontFileExtension(p2);
        return;
    }

    public void setFailureListener(vn.ai.trueid.lottie.LottieListener p1)
    {
        this.failureListener = p1;
        return;
    }

    public void setFallbackResource(int p1)
    {
        this.fallbackResource = p1;
        return;
    }

    public void setFontAssetDelegate(vn.ai.trueid.lottie.FontAssetDelegate p2)
    {
        this.lottieDrawable.setFontAssetDelegate(p2);
        return;
    }

    public void setFontMap(java.util.Map p2)
    {
        this.lottieDrawable.setFontMap(p2);
        return;
    }

    public void setFrame(int p2)
    {
        this.lottieDrawable.setFrame(p2);
        return;
    }

    public void setIgnoreDisabledSystemAnimations(boolean p2)
    {
        this.lottieDrawable.setIgnoreDisabledSystemAnimations(p2);
        return;
    }

    public void setImageAssetDelegate(vn.ai.trueid.lottie.ImageAssetDelegate p2)
    {
        this.lottieDrawable.setImageAssetDelegate(p2);
        return;
    }

    public void setImageAssetsFolder(String p2)
    {
        this.lottieDrawable.setImagesAssetsFolder(p2);
        return;
    }

    public void setImageBitmap(android.graphics.Bitmap p2)
    {
        this.animationResId = 0;
        this.animationName = 0;
        this.cancelLoaderTask();
        super.setImageBitmap(p2);
        return;
    }

    public void setImageDrawable(android.graphics.drawable.Drawable p2)
    {
        this.animationResId = 0;
        this.animationName = 0;
        this.cancelLoaderTask();
        super.setImageDrawable(p2);
        return;
    }

    public void setImageResource(int p2)
    {
        this.animationResId = 0;
        this.animationName = 0;
        this.cancelLoaderTask();
        super.setImageResource(p2);
        return;
    }

    public void setMaintainOriginalImageBounds(boolean p2)
    {
        this.lottieDrawable.setMaintainOriginalImageBounds(p2);
        return;
    }

    public void setMaxFrame(int p2)
    {
        this.lottieDrawable.setMaxFrame(p2);
        return;
    }

    public void setMaxFrame(String p2)
    {
        this.lottieDrawable.setMaxFrame(p2);
        return;
    }

    public void setMaxProgress(float p2)
    {
        this.lottieDrawable.setMaxProgress(p2);
        return;
    }

    public void setMinAndMaxFrame(int p2, int p3)
    {
        this.lottieDrawable.setMinAndMaxFrame(p2, p3);
        return;
    }

    public void setMinAndMaxFrame(String p2)
    {
        this.lottieDrawable.setMinAndMaxFrame(p2);
        return;
    }

    public void setMinAndMaxFrame(String p2, String p3, boolean p4)
    {
        this.lottieDrawable.setMinAndMaxFrame(p2, p3, p4);
        return;
    }

    public void setMinAndMaxProgress(float p2, float p3)
    {
        this.lottieDrawable.setMinAndMaxProgress(p2, p3);
        return;
    }

    public void setMinFrame(int p2)
    {
        this.lottieDrawable.setMinFrame(p2);
        return;
    }

    public void setMinFrame(String p2)
    {
        this.lottieDrawable.setMinFrame(p2);
        return;
    }

    public void setMinProgress(float p2)
    {
        this.lottieDrawable.setMinProgress(p2);
        return;
    }

    public void setOutlineMasksAndMattes(boolean p2)
    {
        this.lottieDrawable.setOutlineMasksAndMattes(p2);
        return;
    }

    public void setPerformanceTrackingEnabled(boolean p2)
    {
        this.lottieDrawable.setPerformanceTrackingEnabled(p2);
        return;
    }

    public void setProgress(float p2)
    {
        this.setProgressInternal(p2, 1);
        return;
    }

    public void setRenderMode(vn.ai.trueid.lottie.RenderMode p2)
    {
        this.lottieDrawable.setRenderMode(p2);
        return;
    }

    public void setRepeatCount(int p3)
    {
        this.userActionsTaken.add(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_REPEAT_COUNT);
        this.lottieDrawable.setRepeatCount(p3);
        return;
    }

    public void setRepeatMode(int p3)
    {
        this.userActionsTaken.add(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_REPEAT_MODE);
        this.lottieDrawable.setRepeatMode(p3);
        return;
    }

    public void setSafeMode(boolean p2)
    {
        this.lottieDrawable.setSafeMode(p2);
        return;
    }

    public void setSpeed(float p2)
    {
        this.lottieDrawable.setSpeed(p2);
        return;
    }

    public void setTextDelegate(vn.ai.trueid.lottie.TextDelegate p2)
    {
        this.lottieDrawable.setTextDelegate(p2);
        return;
    }

    public void setUseCompositionFrameRate(boolean p2)
    {
        this.lottieDrawable.setUseCompositionFrameRate(p2);
        return;
    }

    public void unscheduleDrawable(android.graphics.drawable.Drawable p3)
    {
        if (this.ignoreUnschedule) {
            if ((!this.ignoreUnschedule) && (((p3 instanceof vn.ai.trueid.lottie.LottieDrawable)) && (((vn.ai.trueid.lottie.LottieDrawable) p3).isAnimating()))) {
                ((vn.ai.trueid.lottie.LottieDrawable) p3).pauseAnimation();
            }
        } else {
            vn.ai.trueid.lottie.LottieDrawable v0_3 = this.lottieDrawable;
            if ((p3 != v0_3) || (!v0_3.isAnimating())) {
            } else {
                this.pauseAnimation();
            }
        }
        super.unscheduleDrawable(p3);
        return;
    }

    public android.graphics.Bitmap updateBitmap(String p2, android.graphics.Bitmap p3)
    {
        return this.lottieDrawable.updateBitmap(p2, p3);
    }
}
