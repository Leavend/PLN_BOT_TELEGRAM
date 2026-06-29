package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.math.MathKt;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: TextPainter.kt */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001af\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001ah\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0080\u0001\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\u0014\b\u0002\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0(2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001aj\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020/2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010\u0013\u001a\u00020\u0014ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a&\u00102\u001a\u000203*\u00020\u00062\u0006\u0010+\u001a\u00020,2\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000¢\u0006\u0004\b4\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00066"}, d2 = {"clip", "", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "drawText", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "brush", "Landroidx/compose/ui/graphics/Brush;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "alpha", "", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawText-LVfH_YU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Brush;JFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "drawText-d8-rzKo", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;JJFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", TextBundle.TEXT_ENTRY, "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "size", "Landroidx/compose/ui/geometry/Size;", "drawText-JFhB2K4", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Landroidx/compose/ui/text/AnnotatedString;JLandroidx/compose/ui/text/TextStyle;IZILjava/util/List;JI)V", "", "drawText-TPWCCtM", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Ljava/lang/String;JLandroidx/compose/ui/text/TextStyle;IZIJI)V", "textLayoutConstraints", "Landroidx/compose/ui/unit/Constraints;", "textLayoutConstraints-v_w8tDc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJ)J", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class TextPainterKt {
    /* renamed from: drawText-JFhB2K4, reason: not valid java name */
    public static final void m5214drawTextJFhB2K4(DrawScope drawScope, TextMeasurer textMeasurer, AnnotatedString annotatedString, long j, TextStyle textStyle, int i, boolean z, int i2, List<AnnotatedString.Range<Placeholder>> list, long j2, int i3) {
        TextLayoutResult textLayoutResultM5211measurexDpz5zY$default = TextMeasurer.m5211measurexDpz5zY$default(textMeasurer, annotatedString, textStyle, i, z, i2, list, m5222textLayoutConstraintsv_w8tDc(drawScope, j2, j), drawScope.getLayoutDirection(), drawScope, null, false, 1536, null);
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3888getSizeNHjbRc = drawContext.mo3888getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m3173getXimpl(j), Offset.m3174getYimpl(j));
        clip(transform, textLayoutResultM5211measurexDpz5zY$default);
        textLayoutResultM5211measurexDpz5zY$default.getMultiParagraph().m5114paintLG529CI(drawScope.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m3450getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m3964getDefaultBlendMode0nO6VwU() : i3);
        drawContext.getCanvas().restore();
        drawContext.mo3889setSizeuvyYCjk(jMo3888getSizeNHjbRc);
    }

    /* renamed from: drawText-TPWCCtM, reason: not valid java name */
    public static final void m5218drawTextTPWCCtM(DrawScope drawScope, TextMeasurer textMeasurer, String str, long j, TextStyle textStyle, int i, boolean z, int i2, long j2, int i3) {
        TextLayoutResult textLayoutResultM5211measurexDpz5zY$default = TextMeasurer.m5211measurexDpz5zY$default(textMeasurer, new AnnotatedString(str, null, null, 6, null), textStyle, i, z, i2, null, m5222textLayoutConstraintsv_w8tDc(drawScope, j2, j), drawScope.getLayoutDirection(), drawScope, null, false, 1568, null);
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3888getSizeNHjbRc = drawContext.mo3888getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m3173getXimpl(j), Offset.m3174getYimpl(j));
        clip(transform, textLayoutResultM5211measurexDpz5zY$default);
        textLayoutResultM5211measurexDpz5zY$default.getMultiParagraph().m5114paintLG529CI(drawScope.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m3450getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m3964getDefaultBlendMode0nO6VwU() : i3);
        drawContext.getCanvas().restore();
        drawContext.mo3889setSizeuvyYCjk(jMo3888getSizeNHjbRc);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00a6  */
    /* renamed from: drawText-d8-rzKo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m5220drawTextd8rzKo(androidx.compose.ui.graphics.drawscope.DrawScope r13, androidx.compose.ui.text.TextLayoutResult r14, long r15, long r17, float r19, androidx.compose.ui.graphics.Shadow r20, androidx.compose.ui.text.style.TextDecoration r21, androidx.compose.ui.graphics.drawscope.DrawStyle r22, int r23) {
        /*
            if (r20 != 0) goto Lf
            androidx.compose.ui.text.TextLayoutInput r0 = r14.getLayoutInput()
            androidx.compose.ui.text.TextStyle r0 = r0.getStyle()
            androidx.compose.ui.graphics.Shadow r0 = r0.getShadow()
            goto L11
        Lf:
            r0 = r20
        L11:
            if (r21 != 0) goto L20
            androidx.compose.ui.text.TextLayoutInput r1 = r14.getLayoutInput()
            androidx.compose.ui.text.TextStyle r1 = r1.getStyle()
            androidx.compose.ui.text.style.TextDecoration r1 = r1.getTextDecoration()
            goto L22
        L20:
            r1 = r21
        L22:
            if (r22 != 0) goto L31
            androidx.compose.ui.text.TextLayoutInput r2 = r14.getLayoutInput()
            androidx.compose.ui.text.TextStyle r2 = r2.getStyle()
            androidx.compose.ui.graphics.drawscope.DrawStyle r2 = r2.getDrawStyle()
            goto L33
        L31:
            r2 = r22
        L33:
            androidx.compose.ui.graphics.drawscope.DrawContext r3 = r13.getDrawContext()
            long r4 = r3.mo3888getSizeNHjbRc()
            androidx.compose.ui.graphics.Canvas r6 = r3.getCanvas()
            r6.save()
            androidx.compose.ui.graphics.drawscope.DrawTransform r6 = r3.getTransform()
            float r7 = androidx.compose.ui.geometry.Offset.m3173getXimpl(r17)
            float r8 = androidx.compose.ui.geometry.Offset.m3174getYimpl(r17)
            r6.translate(r7, r8)
            r7 = r14
            clip(r6, r14)
            androidx.compose.ui.text.TextLayoutInput r6 = r14.getLayoutInput()
            androidx.compose.ui.text.TextStyle r6 = r6.getStyle()
            androidx.compose.ui.graphics.Brush r6 = r6.getBrush()
            r8 = 1
            r9 = 0
            if (r6 == 0) goto La6
            androidx.compose.ui.graphics.Color$Companion r10 = androidx.compose.ui.graphics.Color.INSTANCE
            long r10 = r10.m3450getUnspecified0d7_KjU()
            int r10 = (r15 > r10 ? 1 : (r15 == r10 ? 0 : -1))
            if (r10 != 0) goto L71
            r10 = r8
            goto L72
        L71:
            r10 = r9
        L72:
            if (r10 == 0) goto La6
            androidx.compose.ui.text.MultiParagraph r8 = r14.getMultiParagraph()
            androidx.compose.ui.graphics.drawscope.DrawContext r9 = r13.getDrawContext()
            androidx.compose.ui.graphics.Canvas r9 = r9.getCanvas()
            boolean r10 = java.lang.Float.isNaN(r19)
            if (r10 != 0) goto L89
            r7 = r19
            goto L95
        L89:
            androidx.compose.ui.text.TextLayoutInput r7 = r14.getLayoutInput()
            androidx.compose.ui.text.TextStyle r7 = r7.getStyle()
            float r7 = r7.getAlpha()
        L95:
            r13 = r8
            r14 = r9
            r15 = r6
            r16 = r7
            r17 = r0
            r18 = r1
            r19 = r2
            r20 = r23
            r13.m5116painthn5TExg(r14, r15, r16, r17, r18, r19, r20)
            goto Le2
        La6:
            androidx.compose.ui.text.MultiParagraph r6 = r14.getMultiParagraph()
            androidx.compose.ui.graphics.drawscope.DrawContext r10 = r13.getDrawContext()
            androidx.compose.ui.graphics.Canvas r10 = r10.getCanvas()
            androidx.compose.ui.graphics.Color$Companion r11 = androidx.compose.ui.graphics.Color.INSTANCE
            long r11 = r11.m3450getUnspecified0d7_KjU()
            int r11 = (r15 > r11 ? 1 : (r15 == r11 ? 0 : -1))
            if (r11 == 0) goto Lbd
            goto Lbe
        Lbd:
            r8 = r9
        Lbe:
            if (r8 == 0) goto Lc2
            r7 = r15
            goto Lce
        Lc2:
            androidx.compose.ui.text.TextLayoutInput r7 = r14.getLayoutInput()
            androidx.compose.ui.text.TextStyle r7 = r7.getStyle()
            long r7 = r7.m5265getColor0d7_KjU()
        Lce:
            r9 = r19
            long r7 = androidx.compose.ui.text.style.TextDrawStyleKt.m5647modulateDxMtmZc(r7, r9)
            r13 = r6
            r14 = r10
            r15 = r7
            r17 = r0
            r18 = r1
            r19 = r2
            r20 = r23
            r13.m5114paintLG529CI(r14, r15, r17, r18, r19, r20)
        Le2:
            androidx.compose.ui.graphics.Canvas r0 = r3.getCanvas()
            r0.restore()
            r3.mo3889setSizeuvyYCjk(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.TextPainterKt.m5220drawTextd8rzKo(androidx.compose.ui.graphics.drawscope.DrawScope, androidx.compose.ui.text.TextLayoutResult, long, long, float, androidx.compose.ui.graphics.Shadow, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.graphics.drawscope.DrawStyle, int):void");
    }

    /* renamed from: drawText-LVfH_YU, reason: not valid java name */
    public static final void m5216drawTextLVfH_YU(DrawScope drawScope, TextLayoutResult textLayoutResult, Brush brush, long j, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i) {
        Shadow shadow2 = shadow == null ? textLayoutResult.getLayoutInput().getStyle().getShadow() : shadow;
        TextDecoration textDecoration2 = textDecoration == null ? textLayoutResult.getLayoutInput().getStyle().getTextDecoration() : textDecoration;
        DrawStyle drawStyle2 = drawStyle == null ? textLayoutResult.getLayoutInput().getStyle().getDrawStyle() : drawStyle;
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3888getSizeNHjbRc = drawContext.mo3888getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m3173getXimpl(j), Offset.m3174getYimpl(j));
        clip(transform, textLayoutResult);
        textLayoutResult.getMultiParagraph().m5116painthn5TExg(drawScope.getDrawContext().getCanvas(), brush, !Float.isNaN(f) ? f : textLayoutResult.getLayoutInput().getStyle().getAlpha(), shadow2, textDecoration2, drawStyle2, i);
        drawContext.getCanvas().restore();
        drawContext.mo3889setSizeuvyYCjk(jMo3888getSizeNHjbRc);
    }

    private static final void clip(DrawTransform drawTransform, TextLayoutResult textLayoutResult) {
        if (!textLayoutResult.getHasVisualOverflow() || TextOverflow.m5669equalsimpl0(textLayoutResult.getLayoutInput().getOverflow(), TextOverflow.INSTANCE.m5678getVisiblegIe3tQ8())) {
            return;
        }
        DrawTransform.m4015clipRectN_I0leg$default(drawTransform, 0.0f, 0.0f, IntSize.m5905getWidthimpl(textLayoutResult.getSize()), IntSize.m5904getHeightimpl(textLayoutResult.getSize()), 0, 16, null);
    }

    /* renamed from: textLayoutConstraints-v_w8tDc, reason: not valid java name */
    private static final long m5222textLayoutConstraintsv_w8tDc(DrawScope drawScope, long j, long j2) {
        int iRoundToInt;
        int iRoundToInt2;
        int iRoundToInt3;
        boolean z = true;
        int iRoundToInt4 = 0;
        if (((j > Size.INSTANCE.m3250getUnspecifiedNHjbRc() ? 1 : (j == Size.INSTANCE.m3250getUnspecifiedNHjbRc() ? 0 : -1)) == 0) || Float.isNaN(Size.m3242getWidthimpl(j))) {
            iRoundToInt2 = MathKt.roundToInt((float) Math.ceil(Size.m3242getWidthimpl(drawScope.mo3963getSizeNHjbRc()) - Offset.m3173getXimpl(j2)));
            iRoundToInt = 0;
        } else {
            iRoundToInt = MathKt.roundToInt((float) Math.ceil(Size.m3242getWidthimpl(j)));
            iRoundToInt2 = iRoundToInt;
        }
        if (!(j == Size.INSTANCE.m3250getUnspecifiedNHjbRc()) && !Float.isNaN(Size.m3239getHeightimpl(j))) {
            z = false;
        }
        if (z) {
            iRoundToInt3 = MathKt.roundToInt((float) Math.ceil(Size.m3239getHeightimpl(drawScope.mo3963getSizeNHjbRc()) - Offset.m3174getYimpl(j2)));
        } else {
            iRoundToInt4 = MathKt.roundToInt((float) Math.ceil(Size.m3239getHeightimpl(j)));
            iRoundToInt3 = iRoundToInt4;
        }
        return ConstraintsKt.Constraints(iRoundToInt, iRoundToInt2, iRoundToInt4, iRoundToInt3);
    }
}
