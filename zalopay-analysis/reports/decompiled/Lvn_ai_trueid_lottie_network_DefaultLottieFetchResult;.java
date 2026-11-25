package vn.ai.trueid.lottie.network;
public class DefaultLottieFetchResult implements vn.ai.trueid.lottie.network.LottieFetchResult {
    private final java.net.HttpURLConnection connection;

    public DefaultLottieFetchResult(java.net.HttpURLConnection p1)
    {
        this.connection = p1;
        return;
    }

    private String getErrorFromConnection(java.net.HttpURLConnection p3)
    {
        java.io.BufferedReader v0_1 = new java.io.BufferedReader(new java.io.InputStreamReader(p3.getErrorStream()));
        String v3_5 = new StringBuilder();
        try {
            while(true) {
                int v1_3 = v0_1.readLine();
                v3_5.append(v1_3);
                v3_5.append(10);
            }
            try {
                v0_1.close();
            } catch (Exception) {
            }
            return v3_5.toString();
        } catch (String v3_1) {
            try {
                v0_1.close();
            } catch (Exception) {
            }
            throw v3_1;
        }
        if (v1_3 != 0) {
        }
    }

    public java.io.InputStream bodyByteStream()
    {
        return this.connection.getInputStream();
    }

    public void close()
    {
        this.connection.disconnect();
        return;
    }

    public String contentType()
    {
        return this.connection.getContentType();
    }

    public String error()
    {
        try {
            if (!this.isSuccessful()) {
                String v1_3 = new StringBuilder("Unable to fetch ");
                v1_3.append(this.connection.getURL());
                v1_3.append(". Failed with ");
                v1_3.append(this.connection.getResponseCode());
                v1_3.append("\n");
                v1_3.append(this.getErrorFromConnection(this.connection));
                return v1_3.toString();
            } else {
                return 0;
            }
        } catch (String v0_9) {
        } catch (String v0_9) {
        }
        vn.ai.trueid.lottie.utils.Logger.warning("get error failed ", v0_9);
        return v0_9.getMessage();
    }

    public boolean isSuccessful()
    {
        int v0 = 0;
        try {
            if ((this.connection.getResponseCode() / 100) == 2) {
                v0 = 1;
            }
        } catch (java.io.IOException) {
        }
        return v0;
    }
}
