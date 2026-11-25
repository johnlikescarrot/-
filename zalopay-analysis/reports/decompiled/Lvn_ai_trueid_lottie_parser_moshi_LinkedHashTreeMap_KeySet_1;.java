package vn.ai.trueid.lottie.parser.moshi;
 class LinkedHashTreeMap$KeySet$1 extends vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$LinkedTreeMapIterator {
    final synthetic vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$KeySet this$1;

    public LinkedHashTreeMap$KeySet$1(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$KeySet p1)
    {
        this.this$1 = p1;
        super(p1.this$0);
        return;
    }

    public Object next()
    {
        return this.nextNode().key;
    }
}
