package vn.ai.trueid.lottie.parser.moshi;
final class LinkedHashTreeMap$Node implements java.util.Map$Entry {
    final int hash;
    int height;
    final Object key;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node left;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node next;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node parent;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node prev;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node right;
    Object value;

    public LinkedHashTreeMap$Node()
    {
        this.key = 0;
        this.hash = -1;
        this.prev = this;
        this.next = this;
        return;
    }

    public LinkedHashTreeMap$Node(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p1, Object p2, int p3, vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p4, vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p5)
    {
        this.parent = p1;
        this.key = p2;
        this.hash = p3;
        this.height = 1;
        this.next = p4;
        this.prev = p5;
        p5.next = this;
        p4.prev = this;
        return;
    }

    public boolean equals(Object p4)
    {
        if ((p4 instanceof java.util.Map$Entry)) {
            Object v0_3 = this.key;
            if (v0_3 != null) {
                if (!v0_3.equals(((java.util.Map$Entry) p4).getKey())) {
                    return 0;
                }
            } else {
                if (((java.util.Map$Entry) p4).getKey() != null) {
                    return 0;
                }
            }
            Object v0_2 = this.value;
            if (v0_2 != null) {
                if (!v0_2.equals(((java.util.Map$Entry) p4).getValue())) {
                    return 0;
                }
            } else {
                if (((java.util.Map$Entry) p4).getValue() != null) {
                    return 0;
                }
            }
            return 1;
        }
        return 0;
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node first()
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v0 = this.left;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_0 = this;
        while (v0 != null) {
            v1_0 = v0;
            v0 = v0.left;
        }
        return v1_0;
    }

    public Object getKey()
    {
        return this.key;
    }

    public Object getValue()
    {
        return this.value;
    }

    public int hashCode()
    {
        int v0_2;
        int v0_0 = this.key;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        Object v2 = this.value;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_2 ^ v1);
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node last()
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v0 = this.right;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_0 = this;
        while (v0 != null) {
            v1_0 = v0;
            v0 = v0.right;
        }
        return v1_0;
    }

    public Object setValue(Object p2)
    {
        Object v0 = this.value;
        this.value = p2;
        return v0;
    }

    public String toString()
    {
        String v0_1 = new StringBuilder();
        v0_1.append(this.key);
        v0_1.append("=");
        v0_1.append(this.value);
        return v0_1.toString();
    }
}
