package vn.com.vng.zalopay.authentication.secret;
public final class c implements vn.com.vng.zalopay.authentication.secret.b {
    public final java.security.KeyStore a;
    public final Pt.n b;

    public c()
    {
        Pt.n v0_0 = 0;
        try {
            CC.f v1_2 = java.security.KeyStore.getInstance("AndroidKeyStore");
        } catch (java.security.KeyStoreException) {
            v1_2 = 0;
        }
        this.a = v1_2;
        CC.f v1_3 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v1_3 == null) {
            v1_3 = 0;
        }
        CC.f v1_0 = v1_3.a;
        if (v1_0 != null) {
            v0_0 = v1_0;
        }
        this.b = v0_0.D();
        return;
    }

    public final void a(String p4, boolean p5)
    {
        String v0_0 = this.a;
        try {
            v0_0.load(0);
            android.security.keystore.KeyGenParameterSpec v4_2 = new android.security.keystore.KeyGenParameterSpec$Builder(p4, 3).setBlockModes(new String[] {"CBC"})).setEncryptionPaddings(new String[] {"PKCS7Padding"}));
        } catch (Exception) {
            return;
        }
        if (p5 != null) {
            v4_2.setUserAuthenticationRequired(1);
            v4_2.setInvalidatedByBiometricEnrollment(1);
        }
        android.security.keystore.KeyGenParameterSpec v4_3 = v4_2.build();
        javax.crypto.KeyGenerator v5_3 = javax.crypto.KeyGenerator.getInstance("AES", v0_0.getType());
        v5_3.init(v4_3);
        v5_3.generateKey();
        return;
    }

    public final javax.crypto.Cipher b(int p5, String p6)
    {
        byte[] v0_0 = this.a;
        try {
            v0_0.load(0);
            javax.crypto.Cipher v2_1 = javax.crypto.Cipher.getInstance("AES/CBC/PKCS7Padding");
        } catch (android.security.keystore.KeyPermanentlyInvalidatedException) {
            throw new vn.com.vng.zalopay.exception.KeyInvalidatedException();
        } catch (android.security.keystore.KeyPermanentlyInvalidatedException) {
            return 0;
        }
        if (p5 != 1) {
            v2_1.init(p5, ((java.security.KeyStore$SecretKeyEntry) v0_0.getEntry("@zalopay", 0)).getSecretKey(), new javax.crypto.spec.IvParameterSpec(android.util.Base64.decode(this.b.t(), 0)));
            return v2_1;
        } else {
            try {
                v0_0.load(0);
                int v6_5 = ((javax.crypto.SecretKey) v0_0.getKey(p6, 0));
            } catch (Exception) {
                v6_5 = 0;
            }
            v2_1.init(p5, v6_5);
            return v2_1;
        }
    }
}
