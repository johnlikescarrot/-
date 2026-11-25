package vn.ai.trueid.lottie;
 class LottieAnimationView$SavedState extends android.view.View$BaseSavedState {
    public static final android.os.Parcelable$Creator CREATOR;
    String animationName;
    int animationResId;
    String imageAssetsFolder;
    boolean isAnimating;
    float progress;
    int repeatCount;
    int repeatMode;

    static LottieAnimationView$SavedState()
    {
        vn.ai.trueid.lottie.LottieAnimationView$SavedState.CREATOR = new vn.ai.trueid.lottie.LottieAnimationView$SavedState$1();
        return;
    }

    private LottieAnimationView$SavedState(android.os.Parcel p3)
    {
        super(p3);
        super.animationName = p3.readString();
        super.progress = p3.readFloat();
        int v1 = 1;
        if (p3.readInt() != 1) {
            v1 = 0;
        }
        super.isAnimating = v1;
        super.imageAssetsFolder = p3.readString();
        super.repeatMode = p3.readInt();
        super.repeatCount = p3.readInt();
        return;
    }

    public synthetic LottieAnimationView$SavedState(android.os.Parcel p1, vn.ai.trueid.lottie.LottieAnimationView$1 p2)
    {
        this(p1);
        return;
    }

    public LottieAnimationView$SavedState(android.os.Parcelable p1)
    {
        super(p1);
        return;
    }

    public void writeToParcel(android.os.Parcel p1, int p2)
    {
        super.writeToParcel(p1, p2);
        p1.writeString(this.animationName);
        p1.writeFloat(this.progress);
        p1.writeInt(this.isAnimating);
        p1.writeString(this.imageAssetsFolder);
        p1.writeInt(this.repeatMode);
        p1.writeInt(this.repeatCount);
        return;
    }
}
