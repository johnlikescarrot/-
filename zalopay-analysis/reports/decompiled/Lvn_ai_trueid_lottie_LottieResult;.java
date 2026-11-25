package vn.ai.trueid.lottie;
public final class LottieResult {
    private final Throwable exception;
    private final Object value;

    public LottieResult(Object p1)
    {
        this.value = p1;
        this.exception = 0;
        return;
    }

    public LottieResult(Throwable p1)
    {
        this.exception = p1;
        this.value = 0;
        return;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.lottie.LottieResult)) {
                if ((this.getValue() == null) || (!this.getValue().equals(((vn.ai.trueid.lottie.LottieResult) p5).getValue()))) {
                    if ((this.getException() == null) || (((vn.ai.trueid.lottie.LottieResult) p5).getException() == null)) {
                        return 0;
                    } else {
                        return this.getException().toString().equals(this.getException().toString());
                    }
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public Throwable getException()
    {
        return this.exception;
    }

    public Object getValue()
    {
        return this.value;
    }

    public int hashCode()
    {
        return java.util.Arrays.hashCode(new Object[] {this.getValue(), this.getException()}));
    }
}
