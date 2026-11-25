package vn.ai.trueid.sdk.utils;
public final class h implements vn.ai.trueid.sdk.networks.s {

    public final void f(vn.ai.trueid.sdk.models.Person p7)
    {
        Ok.q.c.setCode(1);
        Ok.q.c.setRawImage(vn.ai.trueid.sdk.models.RawImage.Companion.fromBitmaps$trueid_release(fk.b.j, fk.b.m, fk.b.n));
        Ok.q.c.setIdInfo(p7);
        fk.b.d = p7;
        Ok.q.c.setRequestId(vn.ai.trueid.sdk.networks.q.f());
        java.util.HashMap v0_3 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        Ok.q.a = new vn.ai.trueid.sdk.models.CheckingRecord(v0_3.getResources().getString(2131955532), v0_3.getResources().getString(2131955478), vn.ai.trueid.sdk.models.DetectionType.ocrScan, 1);
        if (fk.b.i != null) {
            Ok.q.c.setEkycResult(fk.b.i);
        }
        vn.ai.trueid.sdk.utils.i.c();
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p6)
    {
        Ok.q.a = new vn.ai.trueid.sdk.models.CheckingRecord(vn.ai.trueid.sdk.utils.Helpers.Companion.getResources().getString(2131955532), p6.getMessage(), vn.ai.trueid.sdk.models.DetectionType.ocrScan, 0);
        Ok.q.c.setCode(p6.getCode());
        vn.ai.trueid.sdk.utils.i.b();
        return;
    }
}
