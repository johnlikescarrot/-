package vn.ai.trueid.lottie.network;
public final enum class FileExtension extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.network.FileExtension[] $VALUES;
    public static final enum vn.ai.trueid.lottie.network.FileExtension GZIP;
    public static final enum vn.ai.trueid.lottie.network.FileExtension JSON;
    public static final enum vn.ai.trueid.lottie.network.FileExtension ZIP;
    public final String extension;

    private static synthetic vn.ai.trueid.lottie.network.FileExtension[] $values()
    {
        return new vn.ai.trueid.lottie.network.FileExtension[] {vn.ai.trueid.lottie.network.FileExtension.JSON, vn.ai.trueid.lottie.network.FileExtension.ZIP, vn.ai.trueid.lottie.network.FileExtension.GZIP});
    }

    static FileExtension()
    {
        vn.ai.trueid.lottie.network.FileExtension.JSON = new vn.ai.trueid.lottie.network.FileExtension("JSON", 0, ".json");
        vn.ai.trueid.lottie.network.FileExtension.ZIP = new vn.ai.trueid.lottie.network.FileExtension("ZIP", 1, ".zip");
        vn.ai.trueid.lottie.network.FileExtension.GZIP = new vn.ai.trueid.lottie.network.FileExtension("GZIP", 2, ".gz");
        vn.ai.trueid.lottie.network.FileExtension.$VALUES = vn.ai.trueid.lottie.network.FileExtension.$values();
        return;
    }

    private FileExtension(String p1, int p2, String p3)
    {
        super(p1, p2);
        super.extension = p3;
        return;
    }

    public static vn.ai.trueid.lottie.network.FileExtension valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.network.FileExtension) Enum.valueOf(vn.ai.trueid.lottie.network.FileExtension, p1));
    }

    public static vn.ai.trueid.lottie.network.FileExtension[] values()
    {
        return ((vn.ai.trueid.lottie.network.FileExtension[]) vn.ai.trueid.lottie.network.FileExtension.$VALUES.clone());
    }

    public String tempExtension()
    {
        String v0_1 = new StringBuilder(".temp");
        v0_1.append(this.extension);
        return v0_1.toString();
    }

    public String toString()
    {
        return this.extension;
    }
}
