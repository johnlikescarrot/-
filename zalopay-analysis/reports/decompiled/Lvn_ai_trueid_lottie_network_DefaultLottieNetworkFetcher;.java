package vn.ai.trueid.lottie.network;
public class DefaultLottieNetworkFetcher implements vn.ai.trueid.lottie.network.LottieNetworkFetcher {

    public DefaultLottieNetworkFetcher()
    {
        return;
    }

    public vn.ai.trueid.lottie.network.LottieFetchResult fetchSync(String p2)
    {
        java.net.HttpURLConnection v2_4 = ((java.net.HttpURLConnection) ((java.net.URLConnection) com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(new java.net.URL(p2).openConnection())));
        v2_4.setRequestMethod("GET");
        v2_4.connect();
        return new vn.ai.trueid.lottie.network.DefaultLottieFetchResult(v2_4);
    }
}
