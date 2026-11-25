package vn.ai.trueid.lottie.utils;
public class LottieValueAnimator extends vn.ai.trueid.lottie.utils.BaseLottieAnimator implements android.view.Choreographer$FrameCallback {
    private vn.ai.trueid.lottie.LottieComposition composition;
    private float frame;
    private float frameRaw;
    private long lastFrameTimeNs;
    private float maxFrame;
    private float minFrame;
    private int repeatCount;
    protected boolean running;
    private float speed;
    private boolean speedReversedForRepeatMode;
    private boolean useCompositionFrameRate;

    public LottieValueAnimator()
    {
        this.speed = 1065353216;
        this.speedReversedForRepeatMode = 0;
        this.lastFrameTimeNs = 0;
        this.frameRaw = 0;
        this.frame = 0;
        this.repeatCount = 0;
        this.minFrame = -822083584;
        this.maxFrame = 1325400064;
        this.running = 0;
        this.useCompositionFrameRate = 0;
        return;
    }

    private void checkNotifyUpdate(float p2)
    {
        if ((this.useCompositionFrameRate) && (this.frameRaw == p2)) {
            return;
        } else {
            this.notifyUpdate();
            return;
        }
    }

    private float getFrameDurationNs()
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            return ((1315859240 / v0_0.getFrameRate()) / Math.abs(this.speed));
        } else {
            return 2139095039;
        }
    }

    private boolean isReversed()
    {
        if (this.getSpeed() >= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    private void verifyFrame()
    {
        if (this.composition != null) {
            IllegalStateException v0_1 = this.frame;
            if ((v0_1 < this.minFrame) || (v0_1 > this.maxFrame)) {
                throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", new Object[] {Float.valueOf(this.minFrame), Float.valueOf(this.maxFrame), Float.valueOf(this.frame)})));
            }
        }
        return;
    }

    public void cancel()
    {
        this.notifyCancel();
        this.removeFrameCallback();
        return;
    }

    public void clearComposition()
    {
        this.composition = 0;
        this.minFrame = -822083584;
        this.maxFrame = 1325400064;
        return;
    }

    public void doFrame(long p7)
    {
        this.postFrameCallback();
        if ((this.composition != null) && (this.isRunning())) {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.beginSection("LottieValueAnimator#doFrame");
            }
            int v2_0 = this.lastFrameTimeNs;
            double v4_0 = 0;
            if (v2_0 != 0) {
                v4_0 = (p7 - v2_0);
            }
            int v2_2 = (((float) v4_0) / this.getFrameDurationNs());
            if (this.isReversed()) {
                v2_2 = (- v2_2);
            }
            float v0_4 = (this.frameRaw + v2_2);
            int v2_4 = vn.ai.trueid.lottie.utils.MiscUtils.contains(v0_4, this.getMinFrame(), this.getMaxFrame());
            float v3_2 = this.frameRaw;
            float v0_5 = vn.ai.trueid.lottie.utils.MiscUtils.clamp(v0_4, this.getMinFrame(), this.getMaxFrame());
            this.frameRaw = v0_5;
            if (this.useCompositionFrameRate) {
                v0_5 = ((float) Math.floor(((double) v0_5)));
            }
            this.frame = v0_5;
            this.lastFrameTimeNs = p7;
            if (v2_4 != 0) {
                this.checkNotifyUpdate(v3_2);
            } else {
                if ((this.getRepeatCount() == -1) || (this.repeatCount < this.getRepeatCount())) {
                    if (this.getRepeatMode() != 2) {
                        float v0_11;
                        if (!this.isReversed()) {
                            v0_11 = this.getMinFrame();
                        } else {
                            v0_11 = this.getMaxFrame();
                        }
                        this.frameRaw = v0_11;
                        this.frame = v0_11;
                    } else {
                        this.speedReversedForRepeatMode = (this.speedReversedForRepeatMode ^ 1);
                        this.reverseAnimationSpeed();
                    }
                    this.lastFrameTimeNs = p7;
                    this.checkNotifyUpdate(v3_2);
                    this.notifyRepeat();
                    this.repeatCount = (this.repeatCount + 1);
                } else {
                    boolean v7_5;
                    if (this.speed >= 0) {
                        v7_5 = this.getMaxFrame();
                    } else {
                        v7_5 = this.getMinFrame();
                    }
                    this.frameRaw = v7_5;
                    this.frame = v7_5;
                    this.removeFrameCallback();
                    this.checkNotifyUpdate(v3_2);
                    this.notifyEnd(this.isReversed());
                }
            }
            this.verifyFrame();
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("LottieValueAnimator#doFrame");
            }
        }
        return;
    }

    public void endAnimation()
    {
        this.removeFrameCallback();
        this.notifyEnd(this.isReversed());
        return;
    }

    public float getAnimatedFraction()
    {
        if (this.composition != null) {
            float v0_2;
            float v2;
            float v1_0;
            if (!this.isReversed()) {
                v0_2 = (this.frame - this.getMinFrame());
                v1_0 = this.getMaxFrame();
                v2 = this.getMinFrame();
            } else {
                v0_2 = (this.getMaxFrame() - this.frame);
                v1_0 = this.getMaxFrame();
                v2 = this.getMinFrame();
            }
            return (v0_2 / (v1_0 - v2));
        } else {
            return 0;
        }
    }

    public Object getAnimatedValue()
    {
        return Float.valueOf(this.getAnimatedValueAbsolute());
    }

    public float getAnimatedValueAbsolute()
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            return ((this.frame - v0_0.getStartFrame()) / (this.composition.getEndFrame() - this.composition.getStartFrame()));
        } else {
            return 0;
        }
    }

    public long getDuration()
    {
        long v0_0 = this.composition;
        if (v0_0 != 0) {
            return ((long) v0_0.getDuration());
        } else {
            return 0;
        }
    }

    public float getFrame()
    {
        return this.frame;
    }

    public float getMaxFrame()
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            float v1 = this.maxFrame;
            if (v1 != 1325400064) {
                return v1;
            } else {
                return v0_0.getEndFrame();
            }
        } else {
            return 0;
        }
    }

    public float getMinFrame()
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            float v1 = this.minFrame;
            if (v1 != -822083584) {
                return v1;
            } else {
                return v0_0.getStartFrame();
            }
        } else {
            return 0;
        }
    }

    public float getSpeed()
    {
        return this.speed;
    }

    public boolean isRunning()
    {
        return this.running;
    }

    public void notifyCancel()
    {
        super.notifyCancel();
        this.notifyEnd(this.isReversed());
        return;
    }

    public void pauseAnimation()
    {
        this.removeFrameCallback();
        this.notifyPause();
        return;
    }

    public void playAnimation()
    {
        int v0_1;
        this.running = 1;
        this.notifyStart(this.isReversed());
        if (!this.isReversed()) {
            v0_1 = this.getMinFrame();
        } else {
            v0_1 = this.getMaxFrame();
        }
        this.setFrame(((float) ((int) v0_1)));
        this.lastFrameTimeNs = 0;
        this.repeatCount = 0;
        this.postFrameCallback();
        return;
    }

    public void postFrameCallback()
    {
        if (this.isRunning()) {
            this.removeFrameCallback(0);
            android.view.Choreographer.getInstance().postFrameCallback(this);
        }
        return;
    }

    public void removeFrameCallback()
    {
        this.removeFrameCallback(1);
        return;
    }

    public void removeFrameCallback(boolean p2)
    {
        android.view.Choreographer.getInstance().removeFrameCallback(this);
        if (p2 != 0) {
            this.running = 0;
        }
        return;
    }

    public void resumeAnimation()
    {
        this.running = 1;
        this.postFrameCallback();
        this.lastFrameTimeNs = 0;
        if ((!this.isReversed()) || (this.getFrame() != this.getMinFrame())) {
            if ((!this.isReversed()) && (this.getFrame() == this.getMaxFrame())) {
                this.setFrame(this.getMinFrame());
            }
        } else {
            this.setFrame(this.getMaxFrame());
        }
        this.notifyResume();
        return;
    }

    public void reverseAnimationSpeed()
    {
        this.setSpeed((- this.getSpeed()));
        return;
    }

    public void setComposition(vn.ai.trueid.lottie.LottieComposition p3)
    {
        int v0_3;
        if (this.composition != null) {
            v0_3 = 0;
        } else {
            v0_3 = 1;
        }
        this.composition = p3;
        if (v0_3 == 0) {
            this.setMinAndMaxFrames(((float) ((int) p3.getStartFrame())), ((float) ((int) p3.getEndFrame())));
        } else {
            this.setMinAndMaxFrames(Math.max(this.minFrame, p3.getStartFrame()), Math.min(this.maxFrame, p3.getEndFrame()));
        }
        float v3_6 = this.frame;
        this.frame = 0;
        this.frameRaw = 0;
        this.setFrame(((float) ((int) v3_6)));
        this.notifyUpdate();
        return;
    }

    public void setFrame(float p3)
    {
        if (this.frameRaw != p3) {
            float v3_1 = vn.ai.trueid.lottie.utils.MiscUtils.clamp(p3, this.getMinFrame(), this.getMaxFrame());
            this.frameRaw = v3_1;
            if (this.useCompositionFrameRate) {
                v3_1 = ((float) Math.floor(((double) v3_1)));
            }
            this.frame = v3_1;
            this.lastFrameTimeNs = 0;
            this.notifyUpdate();
            return;
        } else {
            return;
        }
    }

    public void setMaxFrame(float p2)
    {
        this.setMinAndMaxFrames(this.minFrame, p2);
        return;
    }

    public void setMinAndMaxFrames(float p4, float p5)
    {
        if (p4 > p5) {
            float v1_3 = new StringBuilder("minFrame (");
            v1_3.append(p4);
            v1_3.append(") must be <= maxFrame (");
            v1_3.append(p5);
            v1_3.append(")");
            throw new IllegalArgumentException(v1_3.toString());
        } else {
            float v0_3;
            float v0_2 = this.composition;
            if (v0_2 != 0) {
                v0_3 = v0_2.getStartFrame();
            } else {
                v0_3 = -8388609;
            }
            float v1_1;
            float v1_0 = this.composition;
            if (v1_0 != 0) {
                v1_1 = v1_0.getEndFrame();
            } else {
                v1_1 = 2139095039;
            }
            float v4_3 = vn.ai.trueid.lottie.utils.MiscUtils.clamp(p4, v0_3, v1_1);
            float v5_1 = vn.ai.trueid.lottie.utils.MiscUtils.clamp(p5, v0_3, v1_1);
            if ((v4_3 == this.minFrame) && (v5_1 == this.maxFrame)) {
                return;
            } else {
                this.minFrame = v4_3;
                this.maxFrame = v5_1;
                this.setFrame(((float) ((int) vn.ai.trueid.lottie.utils.MiscUtils.clamp(this.frame, v4_3, v5_1))));
                return;
            }
        }
    }

    public void setMinFrame(int p2)
    {
        this.setMinAndMaxFrames(((float) p2), ((float) ((int) this.maxFrame)));
        return;
    }

    public void setRepeatMode(int p2)
    {
        super.setRepeatMode(p2);
        if ((p2 != 2) && (this.speedReversedForRepeatMode)) {
            this.speedReversedForRepeatMode = 0;
            this.reverseAnimationSpeed();
        }
        return;
    }

    public void setSpeed(float p1)
    {
        this.speed = p1;
        return;
    }

    public void setUseCompositionFrameRate(boolean p1)
    {
        this.useCompositionFrameRate = p1;
        return;
    }
}
