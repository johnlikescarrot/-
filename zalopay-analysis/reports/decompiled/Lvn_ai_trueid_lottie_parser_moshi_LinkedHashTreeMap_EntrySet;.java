package vn.ai.trueid.lottie.parser.moshi;
final class LinkedHashTreeMap$EntrySet extends java.util.AbstractSet {
    final synthetic vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap this$0;

    public LinkedHashTreeMap$EntrySet(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap p1)
    {
        this.this$0 = p1;
        return;
    }

    public void clear()
    {
        this.this$0.clear();
        return;
    }

    public boolean contains(Object p2)
    {
        if ((!(p2 instanceof java.util.Map$Entry)) || (this.this$0.findByEntry(((java.util.Map$Entry) p2)) == null)) {
            return 0;
        } else {
            return 1;
        }
    }

    public java.util.Iterator iterator()
    {
        return new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$EntrySet$1(this);
    }

    public boolean remove(Object p3)
    {
        if ((p3 instanceof java.util.Map$Entry)) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v3_2 = this.this$0.findByEntry(((java.util.Map$Entry) p3));
            if (v3_2 != null) {
                this.this$0.removeInternal(v3_2, 1);
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int size()
    {
        return this.this$0.size;
    }
}
