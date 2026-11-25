package vn.ai.trueid.lottie.utils;
public abstract class BaseLottieAnimator extends android.animation.ValueAnimator {
    private final java.util.Set listeners;
    private final java.util.Set pauseListeners;
    private final java.util.Set updateListeners;

    public BaseLottieAnimator()
    {
        this.updateListeners = new java.util.concurrent.CopyOnWriteArraySet();
        this.listeners = new java.util.concurrent.CopyOnWriteArraySet();
        this.pauseListeners = new java.util.concurrent.CopyOnWriteArraySet();
        return;
    }

    public void addListener(android.animation.Animator$AnimatorListener p2)
    {
        this.listeners.add(p2);
        return;
    }

    public void addPauseListener(android.animation.Animator$AnimatorPauseListener p2)
    {
        this.pauseListeners.add(p2);
        return;
    }

    public void addUpdateListener(android.animation.ValueAnimator$AnimatorUpdateListener p2)
    {
        this.updateListeners.add(p2);
        return;
    }

    public long getStartDelay()
    {
        throw new UnsupportedOperationException(wmoi.igob.ggcn(10170));
    }

    public void notifyCancel()
    {
        java.util.Iterator v0_1 = this.listeners.iterator();
        while (v0_1.hasNext()) {
            ((android.animation.Animator$AnimatorListener) v0_1.next()).onAnimationCancel(this);
        }
        return;
    }

    public void notifyEnd(boolean p3)
    {
        java.util.Iterator v0_1 = this.listeners.iterator();
        while (v0_1.hasNext()) {
            ((android.animation.Animator$AnimatorListener) v0_1.next()).onAnimationEnd(this, p3);
        }
        return;
    }

    public void notifyPause()
    {
        java.util.Iterator v0_1 = this.pauseListeners.iterator();
        while (v0_1.hasNext()) {
            ((android.animation.Animator$AnimatorPauseListener) v0_1.next()).onAnimationPause(this);
        }
        return;
    }

    public void notifyRepeat()
    {
        java.util.Iterator v0_1 = this.listeners.iterator();
        while (v0_1.hasNext()) {
            ((android.animation.Animator$AnimatorListener) v0_1.next()).onAnimationRepeat(this);
        }
        return;
    }

    public void notifyResume()
    {
        java.util.Iterator v0_1 = this.pauseListeners.iterator();
        while (v0_1.hasNext()) {
            ((android.animation.Animator$AnimatorPauseListener) v0_1.next()).onAnimationResume(this);
        }
        return;
    }

    public void notifyStart(boolean p3)
    {
        java.util.Iterator v0_1 = this.listeners.iterator();
        while (v0_1.hasNext()) {
            ((android.animation.Animator$AnimatorListener) v0_1.next()).onAnimationStart(this, p3);
        }
        return;
    }

    public void notifyUpdate()
    {
        java.util.Iterator v0_1 = this.updateListeners.iterator();
        while (v0_1.hasNext()) {
            ((android.animation.ValueAnimator$AnimatorUpdateListener) v0_1.next()).onAnimationUpdate(this);
        }
        return;
    }

    public void removeAllListeners()
    {
        this.listeners.clear();
        return;
    }

    public void removeAllUpdateListeners()
    {
        this.updateListeners.clear();
        return;
    }

    public void removeListener(android.animation.Animator$AnimatorListener p2)
    {
        this.listeners.remove(p2);
        return;
    }

    public void removePauseListener(android.animation.Animator$AnimatorPauseListener p2)
    {
        this.pauseListeners.remove(p2);
        return;
    }

    public void removeUpdateListener(android.animation.ValueAnimator$AnimatorUpdateListener p2)
    {
        this.updateListeners.remove(p2);
        return;
    }

    public bridge synthetic android.animation.Animator setDuration(long p1)
    {
        return this.setDuration(p1);
    }

    public android.animation.ValueAnimator setDuration(long p1)
    {
        throw new UnsupportedOperationException(wmoi.igob.ggcn(10171));
    }

    public void setInterpolator(android.animation.TimeInterpolator p2)
    {
        throw new UnsupportedOperationException(wmoi.igob.ggcn(10172));
    }

    public void setStartDelay(long p1)
    {
        throw new UnsupportedOperationException(wmoi.igob.ggcn(10173));
    }
}
