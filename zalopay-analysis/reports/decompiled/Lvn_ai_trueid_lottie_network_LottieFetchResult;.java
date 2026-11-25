package vn.ai.trueid.lottie.network;
public interface LottieFetchResult implements java.io.Closeable {

    public abstract java.io.InputStream bodyByteStream();

    public abstract String contentType();

    public abstract String error();

    public abstract boolean isSuccessful();
}
