package vn.ai.trueid.lottie.parser.moshi;
final class JsonScope {
    static final int CLOSED = 8;
    static final int DANGLING_NAME = 4;
    static final int EMPTY_ARRAY = 1;
    static final int EMPTY_DOCUMENT = 6;
    static final int EMPTY_OBJECT = 3;
    static final int NONEMPTY_ARRAY = 2;
    static final int NONEMPTY_DOCUMENT = 7;
    static final int NONEMPTY_OBJECT = 5;

    private JsonScope()
    {
        return;
    }

    public static String getPath(int p4, int[] p5, String[] p6, int[] p7)
    {
        StringBuilder v0_1 = new StringBuilder("$");
        int v1_1 = 0;
        while (v1_1 < p4) {
            String v2_5 = p5[v1_1];
            if ((v2_5 == 1) || (v2_5 == 2)) {
                v0_1.append(91);
                v0_1.append(p7[v1_1]);
                v0_1.append(93);
            } else {
                if ((v2_5 == 3) || ((v2_5 == 4) || (v2_5 == 5))) {
                    v0_1.append(46);
                    String v2_4 = p6[v1_1];
                    if (v2_4 != null) {
                        v0_1.append(v2_4);
                    }
                }
            }
            v1_1++;
        }
        return v0_1.toString();
    }
}
