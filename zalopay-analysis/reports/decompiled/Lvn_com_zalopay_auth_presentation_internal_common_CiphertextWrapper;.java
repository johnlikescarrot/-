package vn.com.zalopay.auth.presentation.internal.common;
public final class CiphertextWrapper {
    public static final int $stable = 8;
    private final byte[] ciphertext;
    private final byte[] initializationVector;

    public CiphertextWrapper(byte[] p1, byte[] p2)
    {
        this.ciphertext = p1;
        this.initializationVector = p2;
        return;
    }

    public final byte[] a()
    {
        return this.ciphertext;
    }

    public final byte[] b()
    {
        return this.initializationVector;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            byte[] v1_3;
            if (!p5) {
                v1_3 = 0;
            } else {
                v1_3 = p5.getClass();
            }
            if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.auth.presentation.internal.common.CiphertextWrapper, v1_3)) {
                if (java.util.Arrays.equals(this.ciphertext, ((vn.com.zalopay.auth.presentation.internal.common.CiphertextWrapper) p5).ciphertext)) {
                    if (java.util.Arrays.equals(this.initializationVector, ((vn.com.zalopay.auth.presentation.internal.common.CiphertextWrapper) p5).initializationVector)) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int hashCode()
    {
        return (java.util.Arrays.hashCode(this.initializationVector) + (java.util.Arrays.hashCode(this.ciphertext) * 31));
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.f("CiphertextWrapper(ciphertext=", java.util.Arrays.toString(this.ciphertext), ", initializationVector=", java.util.Arrays.toString(this.initializationVector), ")");
    }
}
