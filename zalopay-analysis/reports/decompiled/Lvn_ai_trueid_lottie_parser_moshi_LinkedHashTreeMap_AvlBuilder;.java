package vn.ai.trueid.lottie.parser.moshi;
final class LinkedHashTreeMap$AvlBuilder {
    private int leavesSkipped;
    private int leavesToSkip;
    private int size;
    private vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node stack;

    public LinkedHashTreeMap$AvlBuilder()
    {
        return;
    }

    public void add(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p7)
    {
        p7.right = 0;
        p7.parent = 0;
        p7.left = 0;
        p7.height = 1;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_11 = this.leavesToSkip;
        if (v1_11 > null) {
            int v2_6 = this.size;
            if ((v2_6 & 1) == 0) {
                this.size = (v2_6 + 1);
                this.leavesToSkip = (v1_11 - 1);
                this.leavesSkipped = (this.leavesSkipped + 1);
            }
        }
        p7.parent = this.stack;
        this.stack = p7;
        int v7_1 = this.size;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_4 = (v7_1 + 1);
        this.size = v1_4;
        int v2_1 = this.leavesToSkip;
        if ((v2_1 > 0) && ((v1_4 & 1) == 0)) {
            this.size = (v7_1 + 2);
            this.leavesToSkip = (v2_1 - 1);
            this.leavesSkipped = (this.leavesSkipped + 1);
        }
        int v7_5 = 4;
        while(true) {
            int v2_3 = (v7_5 - 1);
            if ((this.size & v2_3) != v2_3) {
                break;
            }
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_8 = this.leavesSkipped;
            if (v1_8 != null) {
                if (v1_8 != 1) {
                    if (v1_8 == 2) {
                        this.leavesSkipped = 0;
                    }
                } else {
                    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_9 = this.stack;
                    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v4_0 = v1_9.parent;
                    this.stack = v4_0;
                    v4_0.right = v1_9;
                    v4_0.height = (v1_9.height + 1);
                    v1_9.parent = v4_0;
                    this.leavesSkipped = 0;
                }
            } else {
                vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_10 = this.stack;
                int v2_5 = v1_10.parent;
                vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v4_1 = v2_5.parent;
                v2_5.parent = v4_1.parent;
                this.stack = v2_5;
                v2_5.left = v4_1;
                v2_5.right = v1_10;
                v2_5.height = (v1_10.height + 1);
                v4_1.parent = v2_5;
                v1_10.parent = v2_5;
            }
            v7_5 *= 2;
        }
        return;
    }

    public void reset(int p2)
    {
        this.leavesToSkip = (((Integer.highestOneBit(p2) * 2) - 1) - p2);
        this.size = 0;
        this.leavesSkipped = 0;
        this.stack = 0;
        return;
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node root()
    {
        IllegalStateException v0_0 = this.stack;
        if (v0_0.parent != null) {
            throw new IllegalStateException();
        } else {
            return v0_0;
        }
    }
}
