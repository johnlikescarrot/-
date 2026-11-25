package vn.ai.trueid.lottie.parser.moshi;
public abstract class JsonReader implements java.io.Closeable {
    private static final String[] REPLACEMENT_CHARS;
    boolean failOnUnknown;
    boolean lenient;
    int[] pathIndices;
    String[] pathNames;
    int[] scopes;
    int stackSize;

    static JsonReader()
    {
        String[] v0_1 = new String[128];
        vn.ai.trueid.lottie.parser.moshi.JsonReader.REPLACEMENT_CHARS = v0_1;
        String[] v0_2 = 0;
        while (v0_2 <= 31) {
            vn.ai.trueid.lottie.parser.moshi.JsonReader.REPLACEMENT_CHARS[v0_2] = String.format("\\u%04x", new Object[] {Integer.valueOf(v0_2)}));
            v0_2++;
        }
        String[] v0_3 = vn.ai.trueid.lottie.parser.moshi.JsonReader.REPLACEMENT_CHARS;
        v0_3[34] = "\\\"";
        v0_3[92] = "\\\\";
        v0_3[9] = "\\t";
        v0_3[8] = "\\b";
        v0_3[10] = "\\n";
        v0_3[13] = "\\r";
        v0_3[12] = "\\f";
        return;
    }

    public JsonReader()
    {
        String[] v1_0 = new int[32];
        this.scopes = v1_0;
        String[] v1_1 = new String[32];
        this.pathNames = v1_1;
        int[] v0_1 = new int[32];
        this.pathIndices = v0_1;
        return;
    }

    public static synthetic void access$000(okio.BufferedSink p0, String p1)
    {
        vn.ai.trueid.lottie.parser.moshi.JsonReader.string(p0, p1);
        return;
    }

    public static vn.ai.trueid.lottie.parser.moshi.JsonReader of(okio.BufferedSource p1)
    {
        return new vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader(p1);
    }

    private static void string(okio.BufferedSink p7, String p8)
    {
        p7.writeByte(34);
        int v2 = p8.length();
        int v3 = 0;
        int v4 = 0;
        while (v3 < v2) {
            String v5_1;
            String v5_0 = p8.charAt(v3);
            if (v5_0 >= 128) {
                if (v5_0 != 8232) {
                    if (v5_0 == 8233) {
                        v5_1 = "\\u2029";
                        if (v4 < v3) {
                            p7.writeUtf8(p8, v4, v3);
                        }
                        p7.writeUtf8(v5_1);
                        v4 = (v3 + 1);
                    }
                } else {
                    v5_1 = "\\u2028";
                }
            } else {
                v5_1 = vn.ai.trueid.lottie.parser.moshi.JsonReader.REPLACEMENT_CHARS[v5_0];
                if (v5_1 != null) {
                }
            }
            v3++;
        }
        if (v4 < v2) {
            p7.writeUtf8(p8, v4, v2);
        }
        p7.writeByte(34);
        return;
    }

    public abstract void beginArray();

    public abstract void beginObject();

    public abstract void endArray();

    public abstract void endObject();

    public final String getPath()
    {
        return vn.ai.trueid.lottie.parser.moshi.JsonScope.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }

    public abstract boolean hasNext();

    public abstract boolean nextBoolean();

    public abstract double nextDouble();

    public abstract int nextInt();

    public abstract String nextName();

    public abstract String nextString();

    public abstract vn.ai.trueid.lottie.parser.moshi.JsonReader$Token peek();

    public final void pushScope(int p4)
    {
        String v0_0 = this.stackSize;
        String v1_0 = this.scopes;
        if (v0_0 == v1_0.length) {
            if (v0_0 == 256) {
                String v0_12 = new StringBuilder("Nesting too deep at ");
                v0_12.append(this.getPath());
                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v0_12.toString());
            } else {
                this.scopes = java.util.Arrays.copyOf(v1_0, (v1_0.length * 2));
                String v0_5 = this.pathNames;
                this.pathNames = ((String[]) java.util.Arrays.copyOf(v0_5, (v0_5.length * 2)));
                String v0_8 = this.pathIndices;
                this.pathIndices = java.util.Arrays.copyOf(v0_8, (v0_8.length * 2));
            }
        }
        String v0_10 = this.scopes;
        String v1_6 = this.stackSize;
        this.stackSize = (v1_6 + 1);
        v0_10[v1_6] = p4;
        return;
    }

    public abstract int selectName(vn.ai.trueid.lottie.parser.moshi.JsonReader$Options p0);

    public abstract void skipName();

    public abstract void skipValue();

    public final vn.ai.trueid.lottie.parser.moshi.JsonEncodingException syntaxError(String p3)
    {
        String v3_1 = A1.a.o(p3, " at path ");
        v3_1.append(this.getPath());
        throw new vn.ai.trueid.lottie.parser.moshi.JsonEncodingException(v3_1.toString());
    }
}
