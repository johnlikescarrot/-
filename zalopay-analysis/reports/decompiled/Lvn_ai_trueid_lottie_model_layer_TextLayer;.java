package vn.ai.trueid.lottie.model.layer;
public class TextLayer extends vn.ai.trueid.lottie.model.layer.BaseLayer {
    private final StringBuilder charStringBuilder;
    private final java.util.List charStrings;
    private final P.o codePointCache;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorCallbackAnimation;
    private final vn.ai.trueid.lottie.LottieComposition composition;
    private final java.util.Map contentsForCharacter;
    private final android.graphics.Paint fillPaint;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final android.graphics.Matrix matrix;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation opacityAnimation;
    private final android.graphics.RectF rectF;
    private final StringBuilder reorderingStringBuilder;
    private final StringBuilder reversingStringBuilder;
    private final StringBuilder stringBuilder;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation strokeColorAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation strokeColorCallbackAnimation;
    private final android.graphics.Paint strokePaint;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation strokeWidthAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation strokeWidthCallbackAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.TextKeyframeAnimation textAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation textRangeEndAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation textRangeOffsetAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation textRangeStartAnimation;
    private vn.ai.trueid.lottie.model.content.TextRangeUnits textRangeUnits;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation textSizeCallbackAnimation;
    private final java.util.List textSubLines;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation trackingAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation trackingCallbackAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation typefaceCallbackAnimation;

    public TextLayer(vn.ai.trueid.lottie.LottieDrawable p3, vn.ai.trueid.lottie.model.layer.Layer p4)
    {
        super(p3, p4);
        super.stringBuilder = new StringBuilder(2);
        super.charStringBuilder = new StringBuilder(0);
        super.reorderingStringBuilder = new StringBuilder(0);
        super.reversingStringBuilder = new StringBuilder(0);
        super.rectF = new android.graphics.RectF();
        super.matrix = new android.graphics.Matrix();
        super.fillPaint = new vn.ai.trueid.lottie.model.layer.TextLayer$1(super, 1);
        super.strokePaint = new vn.ai.trueid.lottie.model.layer.TextLayer$2(super, 1);
        super.contentsForCharacter = new java.util.HashMap();
        super.codePointCache = new P.o(0);
        super.charStrings = new java.util.ArrayList();
        super.textSubLines = new java.util.ArrayList();
        super.textRangeUnits = vn.ai.trueid.lottie.model.content.TextRangeUnits.INDEX;
        super.lottieDrawable = p3;
        super.composition = p4.getComposition();
        vn.ai.trueid.lottie.model.content.TextRangeUnits v3_5 = p4.getText().createAnimation();
        super.textAnimation = v3_5;
        v3_5.addUpdateListener(super);
        super.addAnimation(v3_5);
        vn.ai.trueid.lottie.model.content.TextRangeUnits v3_6 = p4.getTextProperties();
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_17 = v3_6.textStyle;
            if (v4_17 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_18 = v4_17.color;
                if (v4_18 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_19 = v4_18.createAnimation();
                    super.colorAnimation = v4_19;
                    v4_19.addUpdateListener(super);
                    super.addAnimation(super.colorAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_21 = v3_6.textStyle;
            if (v4_21 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_22 = v4_21.stroke;
                if (v4_22 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_23 = v4_22.createAnimation();
                    super.strokeColorAnimation = v4_23;
                    v4_23.addUpdateListener(super);
                    super.addAnimation(super.strokeColorAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_25 = v3_6.textStyle;
            if (v4_25 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_26 = v4_25.strokeWidth;
                if (v4_26 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_27 = v4_26.createAnimation();
                    super.strokeWidthAnimation = v4_27;
                    v4_27.addUpdateListener(super);
                    super.addAnimation(super.strokeWidthAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_29 = v3_6.textStyle;
            if (v4_29 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_30 = v4_29.tracking;
                if (v4_30 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_31 = v4_30.createAnimation();
                    super.trackingAnimation = v4_31;
                    v4_31.addUpdateListener(super);
                    super.addAnimation(super.trackingAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_1 = v3_6.textStyle;
            if (v4_1 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_2 = v4_1.opacity;
                if (v4_2 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_3 = v4_2.createAnimation();
                    super.opacityAnimation = v4_3;
                    v4_3.addUpdateListener(super);
                    super.addAnimation(super.opacityAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_5 = v3_6.rangeSelector;
            if (v4_5 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_6 = v4_5.start;
                if (v4_6 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_7 = v4_6.createAnimation();
                    super.textRangeStartAnimation = v4_7;
                    v4_7.addUpdateListener(super);
                    super.addAnimation(super.textRangeStartAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_9 = v3_6.rangeSelector;
            if (v4_9 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_10 = v4_9.end;
                if (v4_10 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_11 = v4_10.createAnimation();
                    super.textRangeEndAnimation = v4_11;
                    v4_11.addUpdateListener(super);
                    super.addAnimation(super.textRangeEndAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_13 = v3_6.rangeSelector;
            if (v4_13 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_14 = v4_13.offset;
                if (v4_14 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_15 = v4_14.createAnimation();
                    super.textRangeOffsetAnimation = v4_15;
                    v4_15.addUpdateListener(super);
                    super.addAnimation(super.textRangeOffsetAnimation);
                }
            }
        }
        if (v3_6 != null) {
            vn.ai.trueid.lottie.model.content.TextRangeUnits v3_1 = v3_6.rangeSelector;
            if (v3_1 != null) {
                super.textRangeUnits = v3_1.units;
            }
        }
        return;
    }

    private String codePointToString(String p6, int p7)
    {
        int v0_0 = p6.codePointAt(p7);
        int v1_0 = (Character.charCount(v0_0) + p7);
        while (v1_0 < p6.length()) {
            StringBuilder v2_4 = p6.codePointAt(v1_0);
            if (!this.isModifier(v2_4)) {
                break;
            }
            v1_0 += Character.charCount(v2_4);
            v0_0 = ((v0_0 * 31) + v2_4);
        }
        int v3_2 = ((long) v0_0);
        if (this.codePointCache.e(v3_2) < 0) {
            this.stringBuilder.setLength(0);
            while (p7 < v1_0) {
                int v0_4 = p6.codePointAt(p7);
                this.stringBuilder.appendCodePoint(v0_4);
                p7 += Character.charCount(v0_4);
            }
            String v6_2 = this.stringBuilder.toString();
            this.codePointCache.i(v3_2, v6_2);
            return v6_2;
        } else {
            return ((String) this.codePointCache.b(v3_2));
        }
    }

    private void configurePaint(vn.ai.trueid.lottie.model.DocumentData p5, int p6, int p7)
    {
        android.graphics.Paint v0_0 = this.colorCallbackAnimation;
        if (v0_0 == null) {
            if ((this.colorAnimation == null) || (!this.isIndexInRangeSelection(p7))) {
                this.fillPaint.setColor(p5.color);
            } else {
                this.fillPaint.setColor(((Integer) this.colorAnimation.getValue()).intValue());
            }
        } else {
            this.fillPaint.setColor(((Integer) v0_0.getValue()).intValue());
        }
        android.graphics.Paint v0_6 = this.strokeColorCallbackAnimation;
        if (v0_6 == null) {
            if ((this.strokeColorAnimation == null) || (!this.isIndexInRangeSelection(p7))) {
                this.strokePaint.setColor(p5.strokeColor);
            } else {
                this.strokePaint.setColor(((Integer) this.strokeColorAnimation.getValue()).intValue());
            }
        } else {
            this.strokePaint.setColor(((Integer) v0_6.getValue()).intValue());
        }
        android.graphics.Paint v0_21;
        int v1_11 = 100;
        if (this.transform.getOpacity() != null) {
            v0_21 = ((Integer) this.transform.getOpacity().getValue()).intValue();
        } else {
            v0_21 = 100;
        }
        if ((this.opacityAnimation != null) && (this.isIndexInRangeSelection(p7))) {
            v1_11 = ((Integer) this.opacityAnimation.getValue()).intValue();
        }
        float v6_9 = Math.round(((((((float) v1_11) / 1120403456) * ((((float) v0_21) * 1132396544) / 1120403456)) * ((float) p6)) / 1132396544));
        this.fillPaint.setAlpha(v6_9);
        this.strokePaint.setAlpha(v6_9);
        float v6_10 = this.strokeWidthCallbackAnimation;
        if (v6_10 == 0) {
            if ((this.strokeWidthAnimation == null) || (!this.isIndexInRangeSelection(p7))) {
                this.strokePaint.setStrokeWidth((vn.ai.trueid.lottie.utils.Utils.dpScale() * p5.strokeWidth));
                return;
            } else {
                this.strokePaint.setStrokeWidth(((Float) this.strokeWidthAnimation.getValue()).floatValue());
                return;
            }
        } else {
            this.strokePaint.setStrokeWidth(((Float) v6_10.getValue()).floatValue());
            return;
        }
    }

    private void drawCharacter(String p9, android.graphics.Paint p10, android.graphics.Canvas p11)
    {
        if ((p10.getColor() != 0) && ((p10.getStyle() != android.graphics.Paint$Style.STROKE) || (p10.getStrokeWidth() != 0))) {
            p11.drawText(p9, 0, p9.length(), 0, 0, p10);
            return;
        } else {
            return;
        }
    }

    private void drawCharacterAsGlyph(vn.ai.trueid.lottie.model.FontCharacter p5, float p6, vn.ai.trueid.lottie.model.DocumentData p7, android.graphics.Canvas p8, int p9, int p10)
    {
        this.configurePaint(p7, p10, p9);
        java.util.List v5_1 = this.getContentsForCharacter(p5);
        int v10_1 = 0;
        while (v10_1 < v5_1.size()) {
            android.graphics.Path v0_2 = ((vn.ai.trueid.lottie.animation.content.ContentGroup) v5_1.get(v10_1)).getPath();
            v0_2.computeBounds(this.rectF, 0);
            this.matrix.reset();
            this.matrix.preTranslate(0, (vn.ai.trueid.lottie.utils.Utils.dpScale() * (- p7.baselineShift)));
            this.matrix.preScale(p6, p6);
            v0_2.transform(this.matrix);
            if (!p7.strokeOverFill) {
                this.drawGlyph(v0_2, this.strokePaint, p8);
                this.drawGlyph(v0_2, this.fillPaint, p8);
            } else {
                this.drawGlyph(v0_2, this.fillPaint, p8);
                this.drawGlyph(v0_2, this.strokePaint, p8);
            }
            v10_1++;
        }
        return;
    }

    private void drawCharacterFromFont(String p1, vn.ai.trueid.lottie.model.DocumentData p2, android.graphics.Canvas p3, int p4, int p5)
    {
        this.configurePaint(p2, p5, p4);
        if (!p2.strokeOverFill) {
            this.drawCharacter(p1, this.strokePaint, p3);
            this.drawCharacter(p1, this.fillPaint, p3);
            return;
        } else {
            this.drawCharacter(p1, this.fillPaint, p3);
            this.drawCharacter(p1, this.strokePaint, p3);
            return;
        }
    }

    private void drawFontTextLine(String p10, vn.ai.trueid.lottie.model.DocumentData p11, android.graphics.Canvas p12, float p13, int p14, int p15)
    {
        this.charStrings.clear();
        int v1_4 = 0;
        while (v1_4 < p10.length()) {
            StringBuilder v2_10 = this.codePointToString(p10, v1_4);
            this.charStrings.add(v2_10);
            v1_4 += v2_10.length();
        }
        int v10_4 = 0;
        while (v10_4 < this.charStrings.size()) {
            this.charStringBuilder.setLength(0);
            this.charStringBuilder.append(((String) this.charStrings.get(v10_4)));
            int v1_3 = (v10_4 + 1);
            while (v1_3 < this.charStrings.size()) {
                StringBuilder v2_7 = ((String) this.charStrings.get(v1_3));
                if (!this.isJoiningRightToLeft(v2_7)) {
                    break;
                }
                this.charStringBuilder.insert(0, v2_7);
                v1_3++;
            }
            String v4 = this.charStringBuilder.toString();
            p12.translate((this.drawCharacterFromFont(v4, p11, p12, (p14 + v10_4), p15).fillPaint.measureText(v4) + p13), 0);
            v10_4 = v1_3;
        }
        return;
    }

    private void drawGlyph(android.graphics.Path p3, android.graphics.Paint p4, android.graphics.Canvas p5)
    {
        if ((p4.getColor() != 0) && ((p4.getStyle() != android.graphics.Paint$Style.STROKE) || (p4.getStrokeWidth() != 0))) {
            p5.drawPath(p3, p4);
            return;
        } else {
            return;
        }
    }

    private void drawGlyphTextLine(String p8, vn.ai.trueid.lottie.model.DocumentData p9, vn.ai.trueid.lottie.model.Font p10, android.graphics.Canvas p11, float p12, float p13, float p14, int p15)
    {
        int v5 = 0;
        while (v5 < p8.length()) {
            android.graphics.Canvas v4;
            int v6;
            float v2;
            vn.ai.trueid.lottie.model.DocumentData v3;
            vn.ai.trueid.lottie.model.FontCharacter v1_2 = ((vn.ai.trueid.lottie.model.FontCharacter) this.composition.getCharacters().f(vn.ai.trueid.lottie.model.FontCharacter.hashFor(p8.charAt(v5), p10.getFamily(), p10.getStyle())));
            if (v1_2 != null) {
                v3 = p9;
                v4 = p11;
                v2 = p13;
                v6 = p15;
                this.drawCharacterAsGlyph(v1_2, p13, p9, p11, v5, p15);
                p11.translate(((vn.ai.trueid.lottie.utils.Utils.dpScale() * (((float) v1_2.getWidth()) * p13)) + p14), 0);
            } else {
                v3 = p9;
                v4 = p11;
                v2 = p13;
                v6 = p15;
            }
            v5++;
            p13 = v2;
            p9 = v3;
            p11 = v4;
            p15 = v6;
        }
        return;
    }

    private void drawTextWithFont(vn.ai.trueid.lottie.model.DocumentData p18, vn.ai.trueid.lottie.model.Font p19, android.graphics.Canvas p20, int p21)
    {
        vn.ai.trueid.lottie.model.layer.TextLayer v0_0 = this;
        vn.ai.trueid.lottie.model.DocumentData v7 = p18;
        android.graphics.Canvas v3_1 = p19;
        String v1_11 = this.getTypeface(p19);
        if (v1_11 != null) {
            vn.ai.trueid.lottie.model.DocumentData v2_4 = p18.text;
            int v4_12 = this.lottieDrawable.getTextDelegate();
            if (v4_12 != 0) {
                v2_4 = v4_12.getTextInternal(this.getName(), v2_4);
            }
            String v1_8;
            this.fillPaint.setTypeface(v1_11);
            String v1_7 = this.textSizeCallbackAnimation;
            if (v1_7 == null) {
                v1_8 = p18.size;
            } else {
                v1_8 = ((Float) v1_7.getValue()).floatValue();
            }
            int v5_14;
            this.fillPaint.setTextSize((vn.ai.trueid.lottie.utils.Utils.dpScale() * v1_8));
            this.strokePaint.setTypeface(this.fillPaint.getTypeface());
            this.strokePaint.setTextSize(this.fillPaint.getTextSize());
            int v4_8 = (((float) p18.tracking) / 1092616192);
            int v5_10 = this.trackingCallbackAnimation;
            if (v5_10 == 0) {
                int v5_11 = this.trackingAnimation;
                if (v5_11 != 0) {
                    v5_14 = ((Float) v5_11.getValue()).floatValue();
                    v4_8 += v5_14;
                }
            } else {
                v5_14 = ((Float) v5_10.getValue()).floatValue();
            }
            int v4_9 = (((vn.ai.trueid.lottie.utils.Utils.dpScale() * v4_8) * v1_8) / 1120403456);
            java.util.List v8 = this.getTextLines(v2_4);
            int v9 = v8.size();
            int v11 = -1;
            int v12 = 0;
            int v13 = 0;
            while (v12 < v9) {
                vn.ai.trueid.lottie.model.DocumentData v2_6;
                String v1_14 = v8.get(v12);
                vn.ai.trueid.lottie.model.DocumentData v2_5 = v7.boxSize;
                if (v2_5 != null) {
                    v2_6 = v2_5.x;
                } else {
                    v2_6 = 0;
                }
                int v5_2 = v4_9;
                java.util.List v14 = v0_0.splitGlyphTextIntoLines(((String) v1_14), v2_6, v3_1, 0, v5_2, 0);
                int v15 = 0;
                while (v15 < v14.size()) {
                    String v1_4;
                    vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine v16_0 = ((vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine) v14.get(v15));
                    v11++;
                    p20.save();
                    if ((v0_0.textAnimation != null) || ((v0_0.textSizeCallbackAnimation != null) || (v0_0.trackingCallbackAnimation != null))) {
                        v1_4 = v0_0.fillPaint.measureText(vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine.access$100(v16_0));
                    } else {
                        v1_4 = vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine.access$000(v16_0);
                    }
                    int v4_0;
                    int v5_1;
                    if (!v0_0.offsetCanvas(p20, v7, v11, v1_4)) {
                        v4_0 = v5_2;
                        v5_1 = v13;
                    } else {
                        String v1_6 = vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine.access$100(v16_0);
                        if (java.text.Bidi.requiresBidi(v1_6.toCharArray(), 0, v1_6.length())) {
                            v1_6 = v0_0.reorderLineVisually(v1_6);
                        }
                        v4_0 = v5_2;
                        v5_1 = v13;
                        v0_0.drawFontTextLine(v1_6, v7, p20, v4_0, v5_1, p21);
                    }
                    v13 = (vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine.access$100(v16_0).length() + v5_1);
                    p20.restore();
                    v15++;
                    v0_0 = this;
                    v7 = p18;
                    v5_2 = v4_0;
                }
                v4_9 = v5_2;
                v12++;
                v0_0 = this;
                v7 = p18;
                v3_1 = p19;
            }
        }
        return;
    }

    private void drawTextWithGlyphs(vn.ai.trueid.lottie.model.DocumentData p17, android.graphics.Matrix p18, vn.ai.trueid.lottie.model.Font p19, android.graphics.Canvas p20, int p21)
    {
        String v1_5;
        vn.ai.trueid.lottie.model.layer.TextLayer v0 = this;
        vn.ai.trueid.lottie.model.DocumentData v7_0 = p17;
        String v1_2 = this.textSizeCallbackAnimation;
        if (v1_2 == null) {
            v1_5 = p17.size;
        } else {
            v1_5 = ((Float) v1_2.getValue()).floatValue();
        }
        vn.ai.trueid.lottie.model.DocumentData v2_6;
        float v4_0 = (v1_5 / 1120403456);
        int v8_0 = vn.ai.trueid.lottie.utils.Utils.getScale(p18);
        java.util.List v9 = this.getTextLines(p17.text);
        int v10 = v9.size();
        String v1_4 = (((float) p17.tracking) / 1092616192);
        vn.ai.trueid.lottie.model.DocumentData v2_2 = this.trackingCallbackAnimation;
        if (v2_2 == null) {
            vn.ai.trueid.lottie.model.DocumentData v2_3 = this.trackingAnimation;
            if (v2_3 != null) {
                v2_6 = ((Float) v2_3.getValue()).floatValue();
                v1_4 += v2_6;
            }
        } else {
            v2_6 = ((Float) v2_2.getValue()).floatValue();
        }
        float v5_0 = v1_4;
        int v12 = -1;
        int v13 = 0;
        while (v13 < v10) {
            vn.ai.trueid.lottie.model.DocumentData v2_10;
            String v1_7 = v9.get(v13);
            vn.ai.trueid.lottie.model.DocumentData v2_9 = v7_0.boxSize;
            if (v2_9 != null) {
                v2_10 = v2_9.x;
            } else {
                v2_10 = 0;
            }
            java.util.List v14 = v0.splitGlyphTextIntoLines(((String) v1_7), v2_10, p19, v4_0, v5_0, 1);
            int v15 = 0;
            while (v15 < v14.size()) {
                float v5_1;
                vn.ai.trueid.lottie.model.DocumentData v7_2;
                String v1_12 = ((vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine) v14.get(v15));
                v12++;
                p20.save();
                if (!v0.offsetCanvas(p20, v7_0, v12, vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine.access$000(v1_12))) {
                    v7_2 = v5_0;
                    v5_1 = v8_0;
                } else {
                    float v6_1 = v4_0;
                    v7_2 = v5_0;
                    v5_1 = v8_0;
                    v0.drawGlyphTextLine(vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine.access$100(v1_12), v7_0, p19, p20, v5_1, v6_1, v7_2, p21);
                    v4_0 = v6_1;
                }
                p20.restore();
                v15++;
                v0 = this;
                v8_0 = v5_1;
                v5_0 = v7_2;
                v7_0 = p17;
            }
            v13++;
            v0 = this;
            v7_0 = p17;
        }
        return;
    }

    private vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine ensureEnoughSubLines(int p5)
    {
        java.util.List v0_1 = this.textSubLines.size();
        while (v0_1 < p5) {
            this.textSubLines.add(new vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine(0));
            v0_1++;
        }
        return ((vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine) this.textSubLines.get((p5 - 1)));
    }

    private java.util.List getContentsForCharacter(vn.ai.trueid.lottie.model.FontCharacter p9)
    {
        if (!this.contentsForCharacter.containsKey(p9)) {
            java.util.Map v0_4 = p9.getShapes();
            int v1 = v0_4.size();
            java.util.ArrayList v2_1 = new java.util.ArrayList(v1);
            int v3 = 0;
            while (v3 < v1) {
                v2_1.add(new vn.ai.trueid.lottie.animation.content.ContentGroup(this.lottieDrawable, this, ((vn.ai.trueid.lottie.model.content.ShapeGroup) v0_4.get(v3)), this.composition));
                v3++;
            }
            this.contentsForCharacter.put(p9, v2_1);
            return v2_1;
        } else {
            return ((java.util.List) this.contentsForCharacter.get(p9));
        }
    }

    private java.util.List getTextLines(String p3)
    {
        return java.util.Arrays.asList(p3.replaceAll("\r\n", "\r").replaceAll("\u0003", "\r").replaceAll("\n", "\r").split("\r"));
    }

    private android.graphics.Typeface getTypeface(vn.ai.trueid.lottie.model.Font p2)
    {
        android.graphics.Typeface v0_0 = this.typefaceCallbackAnimation;
        if (v0_0 != null) {
            android.graphics.Typeface v0_2 = ((android.graphics.Typeface) v0_0.getValue());
            if (v0_2 != null) {
                return v0_2;
            }
        }
        android.graphics.Typeface v0_4 = this.lottieDrawable.getTypeface(p2);
        if (v0_4 == null) {
            return p2.getTypeface();
        } else {
            return v0_4;
        }
    }

    private boolean isIndexInRangeSelection(int p8)
    {
        float v0_8 = ((vn.ai.trueid.lottie.model.DocumentData) this.textAnimation.getValue()).text.length();
        int v1_4 = this.textRangeStartAnimation;
        if ((v1_4 == 0) || (this.textRangeEndAnimation == null)) {
            return 1;
        } else {
            int v1_3 = Math.min(((Integer) v1_4.getValue()).intValue(), ((Integer) this.textRangeEndAnimation.getValue()).intValue());
            int v3_9 = Math.max(((Integer) this.textRangeStartAnimation.getValue()).intValue(), ((Integer) this.textRangeEndAnimation.getValue()).intValue());
            vn.ai.trueid.lottie.model.content.TextRangeUnits v4_4 = this.textRangeOffsetAnimation;
            if (v4_4 != null) {
                vn.ai.trueid.lottie.model.content.TextRangeUnits v4_7 = ((Integer) v4_4.getValue()).intValue();
                v1_3 += v4_7;
                v3_9 += v4_7;
            }
            if (this.textRangeUnits != vn.ai.trueid.lottie.model.content.TextRangeUnits.INDEX) {
                float v8_3 = ((((float) p8) / ((float) v0_8)) * 1120403456);
                if ((v8_3 < ((float) v1_3)) || (v8_3 >= ((float) v3_9))) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if ((p8 < v1_3) || (p8 >= v3_9)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    }

    private boolean isJoiningRightToLeft(String p5)
    {
        int v1 = 0;
        while (v1 < p5.length()) {
            if (Character.getDirectionality(p5.codePointAt(v1)) != 2) {
                v1++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    private boolean isModifier(int p3)
    {
        if ((Character.getType(p3) != 16) && ((Character.getType(p3) != 27) && ((Character.getType(p3) != 6) && ((Character.getType(p3) != 28) && ((Character.getType(p3) != 8) && (Character.getType(p3) != 19)))))) {
            return 0;
        } else {
            return 1;
        }
    }

    private boolean offsetCanvas(android.graphics.Canvas p7, vn.ai.trueid.lottie.model.DocumentData p8, int p9, float p10)
    {
        float v4_0;
        float v0_0 = p8.boxPosition;
        int v1_0 = p8.boxSize;
        int v2_6 = vn.ai.trueid.lottie.utils.Utils.dpScale();
        float v3_0 = 0;
        if (v0_0 != 0) {
            v4_0 = ((p8.lineHeight * v2_6) + v0_0.y);
        } else {
            v4_0 = 0;
        }
        float v9_4 = (((((float) p9) * p8.lineHeight) * v2_6) + v4_0);
        if ((!this.lottieDrawable.getClipTextToBoundingBox()) || ((v1_0 == 0) || ((v0_0 == 0) || (v9_4 < ((v0_0.y + v1_0.y) + p8.size))))) {
            float v0_1;
            if (v0_0 != 0) {
                v0_1 = v0_0.x;
            } else {
                v0_1 = 0;
            }
            if (v1_0 != 0) {
                v3_0 = v1_0.x;
            }
            int v8_3 = vn.ai.trueid.lottie.model.layer.TextLayer$3.$SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[p8.justification.ordinal()];
            if (v8_3 == 1) {
                p7.translate(v0_1, v9_4);
            } else {
                if (v8_3 == 2) {
                    p7.translate(((v0_1 + v3_0) - p10), v9_4);
                } else {
                    if (v8_3 == 3) {
                        p7.translate((((v3_0 / 1073741824) + v0_1) - (p10 / 1073741824)), v9_4);
                    }
                }
            }
            return 1;
        } else {
            return 0;
        }
    }

    private String reorderLineVisually(String p10)
    {
        java.text.Bidi v0_1 = new java.text.Bidi(p10, -2);
        int v1_1 = v0_1.getRunCount();
        int v2_2 = new byte[v1_1];
        Integer[] v3 = new Integer[v1_1];
        StringBuilder v5_8 = 0;
        while (v5_8 < v1_1) {
            v2_2[v5_8] = ((byte) v0_1.getRunLevel(v5_8));
            v3[v5_8] = Integer.valueOf(v5_8);
            v5_8++;
        }
        java.text.Bidi.reorderVisually(v2_2, 0, v3, 0, v1_1);
        this.reorderingStringBuilder.setLength(0);
        int v2_1 = 0;
        while (v2_1 < v1_1) {
            StringBuilder v5_1 = v3[v2_1].intValue();
            StringBuilder v6_0 = v0_1.getRunStart(v5_1);
            int v7_0 = v0_1.getRunLimit(v5_1);
            StringBuilder v5_2 = v0_1.getRunLevel(v5_1);
            StringBuilder v6_1 = p10.substring(v6_0, v7_0);
            if ((v5_2 & 1) != 0) {
                this.reversingStringBuilder.setLength(0);
                StringBuilder v5_5 = 0;
                while (v5_5 < v6_1.length()) {
                    int v7_2 = this.codePointToString(v6_1, v5_5);
                    this.reversingStringBuilder.insert(0, v7_2);
                    v5_5 += v7_2.length();
                }
                this.reorderingStringBuilder.append(this.reversingStringBuilder);
            } else {
                this.reorderingStringBuilder.append(v6_1);
            }
            v2_1++;
        }
        return this.reorderingStringBuilder.toString();
    }

    private java.util.List splitGlyphTextIntoLines(String p17, float p18, vn.ai.trueid.lottie.model.Font p19, float p20, float p21, boolean p22)
    {
        int v4 = 0;
        int v6 = 0;
        int v7_3 = 0;
        int v8 = 0;
        int v10_2 = 0;
        float v5_2 = 0;
        float v9_0 = 0;
        float v11 = 0;
        while (v4 < p17.length()) {
            int v14_2;
            vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine v12_1 = p17.charAt(v4);
            if (!p22) {
                v14_2 = (this.fillPaint.measureText(p17.substring(v4, (v4 + 1))) + p21);
                if (v12_1 != 32) {
                    if (v8 == 0) {
                        v9_0 += v14_2;
                    } else {
                        v8 = 0;
                        v10_2 = v4;
                        v9_0 = v14_2;
                    }
                } else {
                    v8 = 1;
                    v11 = v14_2;
                }
                v5_2 += v14_2;
                if ((p18 > 0) && ((v5_2 >= p18) && (v12_1 != 32))) {
                    v6++;
                    vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine v12_2 = this.ensureEnoughSubLines(v6);
                    if (v10_2 != v7_3) {
                        int v7_4 = p17.substring(v7_3, (v10_2 - 1));
                        String v13_11 = v7_4.trim();
                        v12_2.set(v13_11, (((v5_2 - v9_0) - (((float) (v7_4.length() - v13_11.length())) * v11)) - v11));
                        v5_2 = v9_0;
                        v7_3 = v10_2;
                    } else {
                        int v7_9 = p17.substring(v7_3, v4);
                        float v9_1 = v7_9.trim();
                        v12_2.set(v9_1, ((v5_2 - v14_2) - (((float) (v9_1.length() - v7_9.length())) * v11)));
                        v7_3 = v4;
                        v10_2 = v7_3;
                        v5_2 = v14_2;
                        v9_0 = v5_2;
                    }
                }
            } else {
                String v13_5 = ((vn.ai.trueid.lottie.model.FontCharacter) this.composition.getCharacters().f(vn.ai.trueid.lottie.model.FontCharacter.hashFor(v12_1, p19.getFamily(), p19.getStyle())));
                if (v13_5 != null) {
                    v14_2 = ((vn.ai.trueid.lottie.utils.Utils.dpScale() * (((float) v13_5.getWidth()) * p20)) + p21);
                }
            }
            v4++;
        }
        if (v5_2 > 0) {
            v6++;
            this.ensureEnoughSubLines(v6).set(p17.substring(v7_3), v5_2);
        }
        return this.textSubLines.subList(0, v6);
    }

    public void addValueCallback(Object p3, vn.ai.trueid.lottie.value.LottieValueCallback p4)
    {
        super.addValueCallback(p3, p4);
        if (p3 != vn.ai.trueid.lottie.LottieProperty.COLOR) {
            if (p3 != vn.ai.trueid.lottie.LottieProperty.STROKE_COLOR) {
                if (p3 != vn.ai.trueid.lottie.LottieProperty.STROKE_WIDTH) {
                    if (p3 != vn.ai.trueid.lottie.LottieProperty.TEXT_TRACKING) {
                        if (p3 != vn.ai.trueid.lottie.LottieProperty.TEXT_SIZE) {
                            if (p3 != vn.ai.trueid.lottie.LottieProperty.TYPEFACE) {
                                if (p3 == vn.ai.trueid.lottie.LottieProperty.TEXT) {
                                    this.textAnimation.setStringValueCallback(p4);
                                }
                                return;
                            } else {
                                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_2 = this.typefaceCallbackAnimation;
                                if (v3_2 != null) {
                                    this.removeAnimation(v3_2);
                                }
                                if (p4 != null) {
                                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_4 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                                    this.typefaceCallbackAnimation = v3_4;
                                    v3_4.addUpdateListener(this);
                                    this.addAnimation(this.typefaceCallbackAnimation);
                                    return;
                                } else {
                                    this.typefaceCallbackAnimation = 0;
                                    return;
                                }
                            }
                        } else {
                            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_6 = this.textSizeCallbackAnimation;
                            if (v3_6 != null) {
                                this.removeAnimation(v3_6);
                            }
                            if (p4 != null) {
                                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_8 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                                this.textSizeCallbackAnimation = v3_8;
                                v3_8.addUpdateListener(this);
                                this.addAnimation(this.textSizeCallbackAnimation);
                                return;
                            } else {
                                this.textSizeCallbackAnimation = 0;
                                return;
                            }
                        }
                    } else {
                        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_10 = this.trackingCallbackAnimation;
                        if (v3_10 != null) {
                            this.removeAnimation(v3_10);
                        }
                        if (p4 != null) {
                            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_12 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                            this.trackingCallbackAnimation = v3_12;
                            v3_12.addUpdateListener(this);
                            this.addAnimation(this.trackingCallbackAnimation);
                            return;
                        } else {
                            this.trackingCallbackAnimation = 0;
                            return;
                        }
                    }
                } else {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_14 = this.strokeWidthCallbackAnimation;
                    if (v3_14 != null) {
                        this.removeAnimation(v3_14);
                    }
                    if (p4 != null) {
                        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_16 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                        this.strokeWidthCallbackAnimation = v3_16;
                        v3_16.addUpdateListener(this);
                        this.addAnimation(this.strokeWidthCallbackAnimation);
                        return;
                    } else {
                        this.strokeWidthCallbackAnimation = 0;
                        return;
                    }
                }
            } else {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_18 = this.strokeColorCallbackAnimation;
                if (v3_18 != null) {
                    this.removeAnimation(v3_18);
                }
                if (p4 != null) {
                    vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_20 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                    this.strokeColorCallbackAnimation = v3_20;
                    v3_20.addUpdateListener(this);
                    this.addAnimation(this.strokeColorCallbackAnimation);
                    return;
                } else {
                    this.strokeColorCallbackAnimation = 0;
                    return;
                }
            }
        } else {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_22 = this.colorCallbackAnimation;
            if (v3_22 != null) {
                this.removeAnimation(v3_22);
            }
            if (p4 != null) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_24 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                this.colorCallbackAnimation = v3_24;
                v3_24.addUpdateListener(this);
                this.addAnimation(this.colorCallbackAnimation);
                return;
            } else {
                this.colorCallbackAnimation = 0;
                return;
            }
        }
    }

    public void drawLayer(android.graphics.Canvas p7, android.graphics.Matrix p8, int p9, vn.ai.trueid.lottie.utils.DropShadow p10)
    {
        vn.ai.trueid.lottie.model.DocumentData v1_1 = ((vn.ai.trueid.lottie.model.DocumentData) this.textAnimation.getValue());
        vn.ai.trueid.lottie.model.Font v3_1 = ((vn.ai.trueid.lottie.model.Font) this.composition.getFonts().get(v1_1.fontName));
        if (v3_1 != null) {
            android.graphics.Canvas v4;
            p7.save();
            p7.concat(p8);
            this.configurePaint(v1_1, p9, 0);
            if (!this.lottieDrawable.useTextGlyphs()) {
                v4 = p7;
                this.drawTextWithFont(v1_1, v3_1, p7, p9);
            } else {
                v4 = p7;
                this.drawTextWithGlyphs(v1_1, p8, v3_1, p7, p9);
            }
            v4.restore();
            return;
        } else {
            return;
        }
    }

    public void getBounds(android.graphics.RectF p2, android.graphics.Matrix p3, boolean p4)
    {
        super.getBounds(p2, p3, p4);
        p2.set(0, 0, ((float) this.composition.getBounds().width()), ((float) this.composition.getBounds().height()));
        return;
    }
}
