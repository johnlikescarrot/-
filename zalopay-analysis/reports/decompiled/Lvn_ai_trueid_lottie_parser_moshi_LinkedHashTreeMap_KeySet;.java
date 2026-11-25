package vn.ai.trueid.lottie.parser.moshi;
final class LinkedHashTreeMap$KeySet extends java.util.AbstractSet {
    final synthetic vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap this$0;

    public LinkedHashTreeMap$KeySet(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap p1)
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
        return this.this$0.containsKey(p2);
    }

    public java.util.Iterator iterator()
    {
        return new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$KeySet$1(this);
    }

    public boolean remove(Object p2)
    {
        if (this.this$0.removeInternalByKey(p2) == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public int size()
    {
        return this.this$0.size;
    }
}
