package vn.ai.trueid.lottie.parser.moshi;
 class LinkedHashTreeMap$AvlIterator {
    private vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node stackTop;

    public LinkedHashTreeMap$AvlIterator()
    {
        return;
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node next()
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v0 = this.stackTop;
        if (v0 != null) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v2 = v0.parent;
            v0.parent = 0;
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_1 = v0.right;
            while(true) {
                vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v3 = v2;
                v2 = v1_1;
                vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_2 = v3;
                if (v2 == null) {
                    break;
                }
                v2.parent = v1_2;
                v1_1 = v2.left;
            }
            this.stackTop = v1_2;
            return v0;
        } else {
            return 0;
        }
    }

    public void reset(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p3)
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v0_0 = 0;
        while (p3 != null) {
            p3.parent = v0_0;
            v0_0 = p3;
            p3 = p3.left;
        }
        this.stackTop = v0_0;
        return;
    }
}
