package vn.ai.trueid.lottie.utils;
public class MeanCalculator {
    private int n;
    private float sum;

    public MeanCalculator()
    {
        return;
    }

    public void add(float p3)
    {
        float v0_1 = (this.sum + p3);
        this.sum = v0_1;
        int v3_3 = (this.n + 1);
        this.n = v3_3;
        if (v3_3 == 2147483647) {
            this.sum = (v0_1 / 1073741824);
            this.n = (v3_3 / 2);
        }
        return;
    }

    public float getMean()
    {
        float v0_0 = this.n;
        if (v0_0 != 0) {
            return (this.sum / ((float) v0_0));
        } else {
            return 0;
        }
    }
}
