package vn.ai.trueid.lottie.parser.moshi;
abstract class LinkedHashTreeMap$LinkedTreeMapIterator implements java.util.Iterator {
    int expectedModCount;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node lastReturned;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node next;
    final synthetic vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap this$0;

    public LinkedHashTreeMap$LinkedTreeMapIterator(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap p2)
    {
        this.this$0 = p2;
        this.next = p2.header.next;
        this.lastReturned = 0;
        this.expectedModCount = p2.modCount;
        return;
    }

    public final boolean hasNext()
    {
        if (this.next == this.this$0.header) {
            return 0;
        } else {
            return 1;
        }
    }

    public final vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node nextNode()
    {
        java.util.ConcurrentModificationException v0_0 = this.next;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_0 = this.this$0;
        if (v0_0 == v1_0.header) {
            throw new java.util.NoSuchElementException();
        } else {
            if (v1_0.modCount != this.expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            } else {
                this.next = v0_0.next;
                this.lastReturned = v0_0;
                return v0_0;
            }
        }
    }

    public final void remove()
    {
        IllegalStateException v0_0 = this.lastReturned;
        if (v0_0 == null) {
            throw new IllegalStateException();
        } else {
            this.this$0.removeInternal(v0_0, 1);
            this.lastReturned = 0;
            this.expectedModCount = this.this$0.modCount;
            return;
        }
    }
}
