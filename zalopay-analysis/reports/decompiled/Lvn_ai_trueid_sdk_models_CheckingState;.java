package vn.ai.trueid.sdk.models;
public final enum class CheckingState extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.models.CheckingState[] $VALUES;
    public static final enum vn.ai.trueid.sdk.models.CheckingState checking;
    public static final enum vn.ai.trueid.sdk.models.CheckingState everythingDone;
    public static final enum vn.ai.trueid.sdk.models.CheckingState failed;
    public static final enum vn.ai.trueid.sdk.models.CheckingState notYet;
    public static final enum vn.ai.trueid.sdk.models.CheckingState passed;

    private static final synthetic vn.ai.trueid.sdk.models.CheckingState[] $values()
    {
        return new vn.ai.trueid.sdk.models.CheckingState[] {vn.ai.trueid.sdk.models.CheckingState.notYet, vn.ai.trueid.sdk.models.CheckingState.checking, vn.ai.trueid.sdk.models.CheckingState.passed, vn.ai.trueid.sdk.models.CheckingState.failed, vn.ai.trueid.sdk.models.CheckingState.everythingDone});
    }

    static CheckingState()
    {
        vn.ai.trueid.sdk.models.CheckingState.notYet = new vn.ai.trueid.sdk.models.CheckingState("notYet", 0);
        vn.ai.trueid.sdk.models.CheckingState.checking = new vn.ai.trueid.sdk.models.CheckingState("checking", 1);
        vn.ai.trueid.sdk.models.CheckingState.passed = new vn.ai.trueid.sdk.models.CheckingState("passed", 2);
        vn.ai.trueid.sdk.models.CheckingState.failed = new vn.ai.trueid.sdk.models.CheckingState("failed", 3);
        vn.ai.trueid.sdk.models.CheckingState.everythingDone = new vn.ai.trueid.sdk.models.CheckingState("everythingDone", 4);
        vn.ai.trueid.sdk.models.CheckingState.$VALUES = vn.ai.trueid.sdk.models.CheckingState.$values();
        return;
    }

    private CheckingState(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.models.CheckingState valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.models.CheckingState) Enum.valueOf(vn.ai.trueid.sdk.models.CheckingState, p1));
    }

    public static vn.ai.trueid.sdk.models.CheckingState[] values()
    {
        return ((vn.ai.trueid.sdk.models.CheckingState[]) vn.ai.trueid.sdk.models.CheckingState.$VALUES.clone());
    }
}
