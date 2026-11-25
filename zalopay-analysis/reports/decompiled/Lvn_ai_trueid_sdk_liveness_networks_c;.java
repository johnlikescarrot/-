package vn.ai.trueid.sdk.liveness.networks;
public final class c extends okhttp3.RequestBody {
    public final synthetic int a;
    public final synthetic okhttp3.RequestBody b;

    public synthetic c(okhttp3.RequestBody p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final long contentLength()
    {
        switch (this.a) {
            case 0:
                return -1;
            default:
                return -1;
        }
    }

    public final okhttp3.MediaType contentType()
    {
        switch (this.a) {
            case 0:
                return this.b.contentType();
            default:
                return this.b.contentType();
        }
    }

    public final void writeTo(okio.BufferedSink p2)
    {
        switch (this.a) {
            case 0:
                okio.BufferedSink v2_2 = okio.Okio.buffer(new okio.GzipSink(p2));
                this.b.writeTo(v2_2);
                v2_2.close();
                return;
            default:
                okio.BufferedSink v2_1 = okio.Okio.buffer(new okio.GzipSink(p2));
                this.b.writeTo(v2_1);
                v2_1.close();
                return;
        }
    }
}
