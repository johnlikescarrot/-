package vn.ai.trueid.lottie.model.content;
public final enum class MergePaths$MergePathsMode extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode ADD;
    public static final enum vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode EXCLUDE_INTERSECTIONS;
    public static final enum vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode INTERSECT;
    public static final enum vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode MERGE;
    public static final enum vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode SUBTRACT;

    private static synthetic vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode[] {vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.MERGE, vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.ADD, vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.SUBTRACT, vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.INTERSECT, vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.EXCLUDE_INTERSECTIONS});
    }

    static MergePaths$MergePathsMode()
    {
        vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.MERGE = new vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode(wmoi.igob.ggcn(5110), 0);
        vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.ADD = new vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode(wmoi.igob.ggcn(5111), 1);
        vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.SUBTRACT = new vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode(wmoi.igob.ggcn(5112), 2);
        vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.INTERSECT = new vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode(wmoi.igob.ggcn(5113), 3);
        vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.EXCLUDE_INTERSECTIONS = new vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode(wmoi.igob.ggcn(5114), 4);
        vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.$VALUES = vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.$values();
        return;
    }

    private MergePaths$MergePathsMode(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode forId(int p1)
    {
        if (p1 == 1) {
            return vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.MERGE;
        } else {
            if (p1 == 2) {
                return vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.ADD;
            } else {
                if (p1 == 3) {
                    return vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.SUBTRACT;
                } else {
                    if (p1 == 4) {
                        return vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.INTERSECT;
                    } else {
                        if (p1 == 5) {
                            return vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.EXCLUDE_INTERSECTIONS;
                        } else {
                            return vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.MERGE;
                        }
                    }
                }
            }
        }
    }

    public static vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode) Enum.valueOf(vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode, p1));
    }

    public static vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode[]) vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.$VALUES.clone());
    }
}
