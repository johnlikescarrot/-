package vn.ai.trueid.sdk.face;
public abstract class h {

    public static String a(String p6, String p7)
    {
        java.nio.charset.Charset v0_1 = new byte[4];
        Mg.e.a.getClass();
        Mg.e.b.g().nextBytes(v0_1);
        byte[] v1_4 = java.nio.ByteBuffer.allocate(8).order(java.nio.ByteOrder.LITTLE_ENDIAN).putLong((System.currentTimeMillis() * 1000000)).array();
        if (kotlin.jvm.internal.Intrinsics.areEqual(p7, "1")) {
            p6 = p6.substring(0, 52);
        }
        return android.util.Base64.encodeToString(kotlin.collections.w.plus(kotlin.collections.w.plus(v0_1, v1_4), p6.getBytes(kotlin.text.Charsets.UTF_8)), 2);
    }

    public static String a(String p9, String p10, String p11, java.util.List p12)
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p10, "1")) {
            String v10_4 = A1.a.o(p10, p11);
            v10_4.append(kotlin.collections.CollectionsKt.l(p12, "", 0, 0, vn.ai.trueid.sdk.face.g.a, 30));
            String v10_1 = v10_4.toString();
            String v9_1 = android.util.Base64.decode(p9, 0);
            javax.crypto.Mac v11_3 = javax.crypto.Mac.getInstance("HmacSHA256");
            v11_3.init(new javax.crypto.spec.SecretKeySpec(v9_1, "HmacSHA256"));
            return android.util.Base64.encodeToString(v11_3.doFinal(v10_1.getBytes(kotlin.text.Charsets.UTF_8)), 2);
        } else {
            String v10_2 = m4.d.o(p11);
            v10_2.append(kotlin.collections.CollectionsKt.l(p12, "", 0, 0, vn.ai.trueid.sdk.face.f.a, 30));
            String v10_3 = v10_2.toString();
            javax.crypto.Mac v11_5 = kotlin.text.Charsets.UTF_8;
            String v9_6 = p9.getBytes(v11_5);
            javax.crypto.spec.SecretKeySpec v12_3 = javax.crypto.Mac.getInstance("HmacSHA256");
            v12_3.init(new javax.crypto.spec.SecretKeySpec(v9_6, "HmacSHA256"));
            return android.util.Base64.encodeToString(v12_3.doFinal(v10_3.getBytes(v11_5)), 2);
        }
    }
}
