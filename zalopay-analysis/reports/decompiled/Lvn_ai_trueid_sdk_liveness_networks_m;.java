package vn.ai.trueid.sdk.liveness.networks;
public final class m extends javax.net.ssl.SSLSocketFactory {
    public final synthetic int a;
    public final javax.net.ssl.SSLSocketFactory b;
    public final Object c;

    public m(int p4)
    {
        this.a = p4;
        switch (p4) {
            case 1:
                javax.net.ssl.SSLSocketFactory v4_13 = javax.net.ssl.TrustManagerFactory.getInstance(javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm());
                v4_13.init(0);
                javax.net.ssl.SSLSocketFactory v4_1 = v4_13.getTrustManagers();
                if ((v4_1.length != 1) || (!(v4_1[0] instanceof javax.net.ssl.X509TrustManager))) {
                    throw new IllegalStateException(androidx.camera.camera2.internal.G.e("Unexpected default trust managers:", java.util.Arrays.toString(v4_1)).toString());
                } else {
                    this.c = v4_1;
                    try {
                        javax.net.ssl.SSLSocketFactory v4_6 = javax.net.ssl.SSLContext.getInstance("TLSv1.3");
                    } catch (java.security.NoSuchAlgorithmException) {
                        v4_6 = javax.net.ssl.SSLContext.getInstance("TLSv1.2");
                    }
                    IllegalStateException v1_5 = ((javax.net.ssl.TrustManager[]) this.c);
                    if (v1_5 == null) {
                        v1_5 = 0;
                    }
                    v4_6.init(0, v1_5, 0);
                    this.b = v4_6.getSocketFactory();
                    return;
                }
            default:
                javax.net.ssl.SSLSocketFactory v4_11 = javax.net.ssl.TrustManagerFactory.getInstance(javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm());
                v4_11.init(0);
                javax.net.ssl.SSLSocketFactory v4_12 = v4_11.getTrustManagers();
                if ((v4_12.length != 1) || (!(v4_12[0] instanceof javax.net.ssl.X509TrustManager))) {
                    IllegalStateException v0_5 = new StringBuilder("Unexpected default trust managers:");
                    v0_5.append(java.util.Arrays.toString(v4_12));
                    throw new IllegalStateException(v0_5.toString().toString());
                } else {
                    this.c = v4_12;
                    try {
                        javax.net.ssl.SSLSocketFactory v4_18 = javax.net.ssl.SSLContext.getInstance("TLSv1.3");
                    } catch (java.security.NoSuchAlgorithmException) {
                        v4_18 = javax.net.ssl.SSLContext.getInstance("TLSv1.2");
                    }
                    IllegalStateException v1_12 = ((javax.net.ssl.TrustManager[]) this.c);
                    if (v1_12 == null) {
                        v1_12 = 0;
                    }
                    v4_18.init(0, v1_12, 0);
                    this.b = v4_18.getSocketFactory();
                    return;
                }
        }
    }

    public m(com.google.android.gms.internal.ads.Hf p2)
    {
        this.a = 2;
        this.c = p2;
        this.b = ((javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault());
        return;
    }

    public static void a(java.net.Socket p9)
    {
        if ((p9 != null) && ((p9 instanceof javax.net.ssl.SSLSocket))) {
            String[] v0_4 = ((javax.net.ssl.SSLSocket) p9).getSupportedProtocols();
            java.util.ArrayList v1_1 = new java.util.ArrayList();
            int v2 = v0_4.length;
            int v4 = 0;
            while (v4 < v2) {
                String v5 = v0_4[v4];
                if ((kotlin.text.y.startsWith$default(v5, "TLSv1.2", 0, 2, 0)) || (kotlin.text.y.startsWith$default(v5, "TLSv1.3", 0, 2, 0))) {
                    v1_1.add(v5);
                }
                v4++;
            }
            String[] v0_1 = new String[0];
            ((javax.net.ssl.SSLSocket) p9).setEnabledProtocols(((String[]) v1_1.toArray(v0_1)));
        }
        return;
    }

    public static void b(java.net.Socket p9)
    {
        if ((p9 != null) && ((p9 instanceof javax.net.ssl.SSLSocket))) {
            String[] v0_4 = ((javax.net.ssl.SSLSocket) p9).getSupportedProtocols();
            java.util.ArrayList v1_1 = new java.util.ArrayList();
            int v2 = v0_4.length;
            int v4 = 0;
            while (v4 < v2) {
                String v5 = v0_4[v4];
                if ((kotlin.text.y.startsWith$default(v5, "TLSv1.2", 0, 2, 0)) || (kotlin.text.y.startsWith$default(v5, "TLSv1.3", 0, 2, 0))) {
                    v1_1.add(v5);
                }
                v4++;
            }
            String[] v0_1 = new String[0];
            ((javax.net.ssl.SSLSocket) p9).setEnabledProtocols(((String[]) v1_1.toArray(v0_1)));
        }
        return;
    }

    public void c(java.net.Socket p3)
    {
        java.util.HashSet v0_1 = ((com.google.android.gms.internal.ads.Hf) this.c);
        int v1 = v0_1.r;
        if (v1 > 0) {
            p3.setReceiveBufferSize(v1);
        }
        v0_1.s.add(p3);
        return;
    }

    public java.net.Socket createSocket()
    {
        switch (this.a) {
            case 0:
                java.net.Socket v0_5 = this.b.createSocket();
                vn.ai.trueid.sdk.liveness.networks.m.a(v0_5);
                return v0_5;
            case 1:
                java.net.Socket v0_3 = this.b.createSocket();
                vn.ai.trueid.sdk.liveness.networks.m.b(v0_3);
                return v0_3;
            default:
                return super.createSocket();
        }
    }

    public final java.net.Socket createSocket(String p2, int p3)
    {
        switch (this.a) {
            case 0:
                java.net.Socket v2_3 = this.b.createSocket(p2, p3);
                vn.ai.trueid.sdk.liveness.networks.m.a(v2_3);
                return v2_3;
            case 1:
                java.net.Socket v2_2 = this.b.createSocket(p2, p3);
                vn.ai.trueid.sdk.liveness.networks.m.b(v2_2);
                return v2_2;
            default:
                java.net.Socket v2_1 = this.b.createSocket(p2, p3);
                this.c(v2_1);
                return v2_1;
        }
    }

    public final java.net.Socket createSocket(String p2, int p3, java.net.InetAddress p4, int p5)
    {
        switch (this.a) {
            case 0:
                java.net.Socket v2_3 = this.b.createSocket(p2, p3, p4, p5);
                vn.ai.trueid.sdk.liveness.networks.m.a(v2_3);
                return v2_3;
            case 1:
                java.net.Socket v2_2 = this.b.createSocket(p2, p3, p4, p5);
                vn.ai.trueid.sdk.liveness.networks.m.b(v2_2);
                return v2_2;
            default:
                java.net.Socket v2_1 = this.b.createSocket(p2, p3, p4, p5);
                this.c(v2_1);
                return v2_1;
        }
    }

    public final java.net.Socket createSocket(java.net.InetAddress p2, int p3)
    {
        switch (this.a) {
            case 0:
                java.net.Socket v2_3 = this.b.createSocket(p2, p3);
                vn.ai.trueid.sdk.liveness.networks.m.a(v2_3);
                return v2_3;
            case 1:
                java.net.Socket v2_2 = this.b.createSocket(p2, p3);
                vn.ai.trueid.sdk.liveness.networks.m.b(v2_2);
                return v2_2;
            default:
                java.net.Socket v2_1 = this.b.createSocket(p2, p3);
                this.c(v2_1);
                return v2_1;
        }
    }

    public final java.net.Socket createSocket(java.net.InetAddress p2, int p3, java.net.InetAddress p4, int p5)
    {
        switch (this.a) {
            case 0:
                java.net.Socket v2_3 = this.b.createSocket(p2, p3, p4, p5);
                vn.ai.trueid.sdk.liveness.networks.m.a(v2_3);
                return v2_3;
            case 1:
                java.net.Socket v2_2 = this.b.createSocket(p2, p3, p4, p5);
                vn.ai.trueid.sdk.liveness.networks.m.b(v2_2);
                return v2_2;
            default:
                java.net.Socket v2_1 = this.b.createSocket(p2, p3, p4, p5);
                this.c(v2_1);
                return v2_1;
        }
    }

    public final java.net.Socket createSocket(java.net.Socket p2, String p3, int p4, boolean p5)
    {
        switch (this.a) {
            case 0:
                java.net.Socket v2_3 = this.b.createSocket(p2, p3, p4, p5);
                vn.ai.trueid.sdk.liveness.networks.m.a(v2_3);
                return v2_3;
            case 1:
                java.net.Socket v2_2 = this.b.createSocket(p2, p3, p4, p5);
                vn.ai.trueid.sdk.liveness.networks.m.b(v2_2);
                return v2_2;
            default:
                java.net.Socket v2_1 = this.b.createSocket(p2, p3, p4, p5);
                this.c(v2_1);
                return v2_1;
        }
    }

    public final String[] getDefaultCipherSuites()
    {
        switch (this.a) {
            case 0:
                return this.b.getDefaultCipherSuites();
            case 1:
                return this.b.getDefaultCipherSuites();
            default:
                return this.b.getDefaultCipherSuites();
        }
    }

    public final String[] getSupportedCipherSuites()
    {
        switch (this.a) {
            case 0:
                return this.b.getSupportedCipherSuites();
            case 1:
                return this.b.getSupportedCipherSuites();
            default:
                return this.b.getSupportedCipherSuites();
        }
    }
}
