package vn.ai.trueid.lottie.model;
public class MutablePair {
    Object first;
    Object second;

    public MutablePair()
    {
        return;
    }

    private static boolean objectsEqual(Object p0, Object p1)
    {
        if ((p0 != p1) && ((p0 == 0) || (!p0.equals(p1)))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean equals(Object p4)
    {
        if ((p4 instanceof j2.d)) {
            if ((!vn.ai.trueid.lottie.model.MutablePair.objectsEqual(((j2.d) p4).a, this.first)) || (!vn.ai.trueid.lottie.model.MutablePair.objectsEqual(((j2.d) p4).b, this.second))) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public int hashCode()
    {
        int v0_2;
        int v0_0 = this.first;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        Object v2 = this.second;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_2 ^ v1);
    }

    public void set(Object p1, Object p2)
    {
        this.first = p1;
        this.second = p2;
        return;
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("Pair{");
        v0_2.append(this.first);
        v0_2.append(" ");
        return androidx.activity.result.e.l(this.second, "}", v0_2);
    }
}
