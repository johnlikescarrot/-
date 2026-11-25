package vn.ai.trueid.lottie.utils;
public class GammaEvaluator {

    public GammaEvaluator()
    {
        return;
    }

    private static float EOCF_sRGB(float p4)
    {
        if (p4 > 1025879782) {
            return ((float) Math.pow(((double) ((p4 + 1029785518) / 1065814589)), 4612586738567610368));
        } else {
            return (p4 / 1095678034);
        }
    }

    private static float OECF_sRGB(float p4)
    {
        if (p4 > 994913820) {
            return ((float) ((Math.pow(((double) p4), 4601177619117899776) * 4607430116543299584) - 4588087156337016832));
        } else {
            return (p4 * 1095678034);
        }
    }

    public static int evaluate(float p7, int p8, int p9)
    {
        if ((p8 != p9) && (p7 > 0)) {
            if (p7 < 1065353216) {
                return (Math.round((vn.ai.trueid.lottie.utils.GammaEvaluator.OECF_sRGB(androidx.activity.result.e.a(vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) (p9 & 255)) / 1132396544)), vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) (p8 & 255)) / 1132396544)), p7, vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) (p8 & 255)) / 1132396544)))) * 1132396544)) | (((Math.round((vn.ai.trueid.lottie.utils.GammaEvaluator.OECF_sRGB(androidx.activity.result.e.a(vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) ((p9 >> 16) & 255)) / 1132396544)), vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) ((p8 >> 16) & 255)) / 1132396544)), p7, vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) ((p8 >> 16) & 255)) / 1132396544)))) * 1132396544)) << 16) | (Math.round((androidx.activity.result.e.a((((float) ((p9 >> 24) & 255)) / 1132396544), (((float) ((p8 >> 24) & 255)) / 1132396544), p7, (((float) ((p8 >> 24) & 255)) / 1132396544)) * 1132396544)) << 24)) | (Math.round((vn.ai.trueid.lottie.utils.GammaEvaluator.OECF_sRGB(androidx.activity.result.e.a(vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) ((p9 >> 8) & 255)) / 1132396544)), vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) ((p8 >> 8) & 255)) / 1132396544)), p7, vn.ai.trueid.lottie.utils.GammaEvaluator.EOCF_sRGB((((float) ((p8 >> 8) & 255)) / 1132396544)))) * 1132396544)) << 8)));
            } else {
                return p9;
            }
        } else {
            return p8;
        }
    }
}
