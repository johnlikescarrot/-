package vn.ai.trueid.lottie.parser.moshi;
public final class JsonReader$Options {
    final okio.Options doubleQuoteSuffix;
    final String[] strings;

    private JsonReader$Options(String[] p1, okio.Options p2)
    {
        this.strings = p1;
        this.doubleQuoteSuffix = p2;
        return;
    }

    public static varargs vn.ai.trueid.lottie.parser.moshi.JsonReader$Options of(String[] p4)
    {
        try {
            okio.Options v0_1 = new okio.ByteString[p4.length];
            vn.ai.trueid.lottie.parser.moshi.JsonReader$Options v1_2 = new okio.Buffer();
            int v2 = 0;
        } catch (String[] v4_2) {
            throw new AssertionError(v4_2);
        }
        while (v2 < p4.length) {
            vn.ai.trueid.lottie.parser.moshi.JsonReader.access$000(v1_2, p4[v2]);
            v1_2.readByte();
            v0_1[v2] = v1_2.readByteString();
            v2++;
        }
        return new vn.ai.trueid.lottie.parser.moshi.JsonReader$Options(((String[]) p4.clone()), okio.Options.of(v0_1));
    }
}
