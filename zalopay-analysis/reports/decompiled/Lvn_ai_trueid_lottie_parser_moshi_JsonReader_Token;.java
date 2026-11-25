package vn.ai.trueid.lottie.parser.moshi;
public final enum class JsonReader$Token extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.parser.moshi.JsonReader$Token[] $VALUES;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token BEGIN_ARRAY;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token BEGIN_OBJECT;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token BOOLEAN;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token END_ARRAY;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token END_DOCUMENT;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token END_OBJECT;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token NAME;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token NULL;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token NUMBER;
    public static final enum vn.ai.trueid.lottie.parser.moshi.JsonReader$Token STRING;

    private static synthetic vn.ai.trueid.lottie.parser.moshi.JsonReader$Token[] $values()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v2 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT;
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NAME;
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v6 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER;
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v8 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NULL;
        return new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token[] {vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY, vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_DOCUMENT});
    }

    static JsonReader$Token()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14953), 0);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_ARRAY = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14954), 1);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14955), 2);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_OBJECT = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14956), 3);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NAME = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14957), 4);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.STRING = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14958), 5);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14959), 6);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BOOLEAN = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14960), 7);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NULL = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14961), 8);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_DOCUMENT = new vn.ai.trueid.lottie.parser.moshi.JsonReader$Token(wmoi.igob.ggcn(14962), 9);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.$VALUES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.$values();
        return;
    }

    private JsonReader$Token(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.parser.moshi.JsonReader$Token valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.parser.moshi.JsonReader$Token) Enum.valueOf(vn.ai.trueid.lottie.parser.moshi.JsonReader$Token, p1));
    }

    public static vn.ai.trueid.lottie.parser.moshi.JsonReader$Token[] values()
    {
        return ((vn.ai.trueid.lottie.parser.moshi.JsonReader$Token[]) vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.$VALUES.clone());
    }
}
