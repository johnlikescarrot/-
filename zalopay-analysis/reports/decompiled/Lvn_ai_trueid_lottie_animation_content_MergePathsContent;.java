package vn.ai.trueid.lottie.animation.content;
public class MergePathsContent implements vn.ai.trueid.lottie.animation.content.PathContent, vn.ai.trueid.lottie.animation.content.GreedyContent {
    private final android.graphics.Path firstPath;
    private final vn.ai.trueid.lottie.model.content.MergePaths mergePaths;
    private final String name;
    private final android.graphics.Path path;
    private final java.util.List pathContents;
    private final android.graphics.Path remainderPath;

    public MergePathsContent(vn.ai.trueid.lottie.model.content.MergePaths p2)
    {
        this.firstPath = new android.graphics.Path();
        this.remainderPath = new android.graphics.Path();
        this.path = new android.graphics.Path();
        this.pathContents = new java.util.ArrayList();
        this.name = p2.getName();
        this.mergePaths = p2;
        return;
    }

    private void addPaths()
    {
        int v0 = 0;
        while (v0 < this.pathContents.size()) {
            this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) this.pathContents.get(v0)).getPath());
            v0++;
        }
        return;
    }

    private void opFirstPathWithRest(android.graphics.Path$Op p8)
    {
        this.remainderPath.reset();
        this.firstPath.reset();
        android.graphics.Path v0_10 = (this.pathContents.size() - 1);
        while (v0_10 >= 1) {
            android.graphics.Path v2_5 = ((vn.ai.trueid.lottie.animation.content.PathContent) this.pathContents.get(v0_10));
            if (!(v2_5 instanceof vn.ai.trueid.lottie.animation.content.ContentGroup)) {
                this.remainderPath.addPath(v2_5.getPath());
            } else {
                android.graphics.Path v2_7 = ((vn.ai.trueid.lottie.animation.content.ContentGroup) v2_5);
                android.graphics.Path v3_6 = v2_7.getPathList();
                android.graphics.Path v4_3 = (v3_6.size() - 1);
                while (v4_3 >= null) {
                    android.graphics.Path v5_2 = ((vn.ai.trueid.lottie.animation.content.PathContent) v3_6.get(v4_3)).getPath();
                    v5_2.transform(v2_7.getTransformationMatrix());
                    this.remainderPath.addPath(v5_2);
                    v4_3--;
                }
            }
            v0_10--;
        }
        int v1_0 = 0;
        android.graphics.Path v0_3 = ((vn.ai.trueid.lottie.animation.content.PathContent) this.pathContents.get(0));
        if (!(v0_3 instanceof vn.ai.trueid.lottie.animation.content.ContentGroup)) {
            this.firstPath.set(v0_3.getPath());
        } else {
            android.graphics.Path v0_6 = ((vn.ai.trueid.lottie.animation.content.ContentGroup) v0_3);
            android.graphics.Path v2_1 = v0_6.getPathList();
            while (v1_0 < v2_1.size()) {
                android.graphics.Path v3_3 = ((vn.ai.trueid.lottie.animation.content.PathContent) v2_1.get(v1_0)).getPath();
                v3_3.transform(v0_6.getTransformationMatrix());
                this.firstPath.addPath(v3_3);
                v1_0++;
            }
        }
        this.path.op(this.firstPath, this.remainderPath, p8);
        return;
    }

    public void absorbContent(java.util.ListIterator p3)
    {
        while (p3.hasPrevious()) {
            if (p3.previous() == this) {
                break;
            }
        }
        while (p3.hasPrevious()) {
            vn.ai.trueid.lottie.animation.content.PathContent v0_2 = ((vn.ai.trueid.lottie.animation.content.Content) p3.previous());
            if ((v0_2 instanceof vn.ai.trueid.lottie.animation.content.PathContent)) {
                this.pathContents.add(((vn.ai.trueid.lottie.animation.content.PathContent) v0_2));
                p3.remove();
            }
        }
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public android.graphics.Path getPath()
    {
        this.path.reset();
        if (!this.mergePaths.isHidden()) {
            android.graphics.Path$Op v0_1 = vn.ai.trueid.lottie.animation.content.MergePathsContent$1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[this.mergePaths.getMode().ordinal()];
            if (v0_1 == 1) {
                this.addPaths();
            } else {
                if (v0_1 == 2) {
                    this.opFirstPathWithRest(android.graphics.Path$Op.UNION);
                } else {
                    if (v0_1 == 3) {
                        this.opFirstPathWithRest(android.graphics.Path$Op.REVERSE_DIFFERENCE);
                    } else {
                        if (v0_1 == 4) {
                            this.opFirstPathWithRest(android.graphics.Path$Op.INTERSECT);
                        } else {
                            if (v0_1 == 5) {
                                this.opFirstPathWithRest(android.graphics.Path$Op.XOR);
                            }
                        }
                    }
                }
            }
            return this.path;
        } else {
            return this.path;
        }
    }

    public void setContents(java.util.List p3, java.util.List p4)
    {
        int v0 = 0;
        while (v0 < this.pathContents.size()) {
            ((vn.ai.trueid.lottie.animation.content.PathContent) this.pathContents.get(v0)).setContents(p3, p4);
            v0++;
        }
        return;
    }
}
