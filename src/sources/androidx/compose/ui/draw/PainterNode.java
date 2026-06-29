package androidx.compose.ui.draw;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;

/* compiled from: PainterModifier.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B?\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\u001a\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u001a\u00102\u001a\u0002032\u0006\u00104\u001a\u000203H\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00101J\b\u00106\u001a\u000207H\u0016J\f\u00108\u001a\u000209*\u00020:H\u0016J\u0016\u0010;\u001a\u00020\u0007*\u00020.H\u0002ø\u0001\u0000¢\u0006\u0004\b<\u0010=J\u0016\u0010>\u001a\u00020\u0007*\u00020.H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010=J\u001c\u0010@\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020AH\u0016J\u001c\u0010F\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020AH\u0016J&\u0010H\u001a\u00020I*\u00020J2\u0006\u0010C\u001a\u00020K2\u0006\u00104\u001a\u000203H\u0016ø\u0001\u0000¢\u0006\u0004\bL\u0010MJ\u001c\u0010N\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020AH\u0016J\u001c\u0010O\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020AH\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010'\"\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010'\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006P"}, d2 = {"Landroidx/compose/ui/draw/PainterNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "sizeToIntrinsics", "", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "(Landroidx/compose/ui/graphics/painter/Painter;ZLandroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "getAlignment", "()Landroidx/compose/ui/Alignment;", "setAlignment", "(Landroidx/compose/ui/Alignment;)V", "getAlpha", "()F", "setAlpha", "(F)V", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "getContentScale", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale", "(Landroidx/compose/ui/layout/ContentScale;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "getSizeToIntrinsics", "setSizeToIntrinsics", "(Z)V", "useIntrinsicSize", "getUseIntrinsicSize", "calculateScaledSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "modifyConstraints", "Landroidx/compose/ui/unit/Constraints;", "constraints", "modifyConstraints-ZezNO4M", "toString", "", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "hasSpecifiedAndFiniteHeight", "hasSpecifiedAndFiniteHeight-uvyYCjk", "(J)Z", "hasSpecifiedAndFiniteWidth", "hasSpecifiedAndFiniteWidth-uvyYCjk", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
final class PainterNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode {
    private Alignment alignment;
    private float alpha;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Painter painter;
    private boolean sizeToIntrinsics;

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    public final Painter getPainter() {
        return this.painter;
    }

    public final void setPainter(Painter painter) {
        this.painter = painter;
    }

    public final boolean getSizeToIntrinsics() {
        return this.sizeToIntrinsics;
    }

    public final void setSizeToIntrinsics(boolean z) {
        this.sizeToIntrinsics = z;
    }

    public /* synthetic */ PainterNode(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(painter, z, (i & 4) != 0 ? Alignment.INSTANCE.getCenter() : alignment, (i & 8) != 0 ? ContentScale.INSTANCE.getInside() : contentScale, (i & 16) != 0 ? 1.0f : f, (i & 32) != 0 ? null : colorFilter);
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final void setContentScale(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public PainterNode(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter) {
        this.painter = painter;
        this.sizeToIntrinsics = z;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = f;
        this.colorFilter = colorFilter;
    }

    private final boolean getUseIntrinsicSize() {
        if (this.sizeToIntrinsics) {
            return (this.painter.getIntrinsicSize() > Size.INSTANCE.m3250getUnspecifiedNHjbRc() ? 1 : (this.painter.getIntrinsicSize() == Size.INSTANCE.m3250getUnspecifiedNHjbRc() ? 0 : -1)) != 0;
        }
        return false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo90measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeableMo4684measureBRTryo0 = measurable.mo4684measureBRTryo0(m3084modifyConstraintsZezNO4M(j));
        return MeasureScope.layout$default(measureScope, placeableMo4684measureBRTryo0.getWidth(), placeableMo4684measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.draw.PainterNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4684measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (getUseIntrinsicSize()) {
            long jM3084modifyConstraintsZezNO4M = m3084modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null));
            return Math.max(Constraints.m5693getMinWidthimpl(jM3084modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicWidth(i));
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (getUseIntrinsicSize()) {
            long jM3084modifyConstraintsZezNO4M = m3084modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null));
            return Math.max(Constraints.m5693getMinWidthimpl(jM3084modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicWidth(i));
        }
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (getUseIntrinsicSize()) {
            long jM3084modifyConstraintsZezNO4M = m3084modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null));
            return Math.max(Constraints.m5692getMinHeightimpl(jM3084modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicHeight(i));
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (getUseIntrinsicSize()) {
            long jM3084modifyConstraintsZezNO4M = m3084modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null));
            return Math.max(Constraints.m5692getMinHeightimpl(jM3084modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicHeight(i));
        }
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    /* renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m3081calculateScaledSizeE7KxVPU(long dstSize) {
        float fM3242getWidthimpl;
        float fM3239getHeightimpl;
        if (!getUseIntrinsicSize()) {
            return dstSize;
        }
        if (!m3083hasSpecifiedAndFiniteWidthuvyYCjk(this.painter.getIntrinsicSize())) {
            fM3242getWidthimpl = Size.m3242getWidthimpl(dstSize);
        } else {
            fM3242getWidthimpl = Size.m3242getWidthimpl(this.painter.getIntrinsicSize());
        }
        if (!m3082hasSpecifiedAndFiniteHeightuvyYCjk(this.painter.getIntrinsicSize())) {
            fM3239getHeightimpl = Size.m3239getHeightimpl(dstSize);
        } else {
            fM3239getHeightimpl = Size.m3239getHeightimpl(this.painter.getIntrinsicSize());
        }
        long jSize = SizeKt.Size(fM3242getWidthimpl, fM3239getHeightimpl);
        if (!(Size.m3242getWidthimpl(dstSize) == 0.0f)) {
            if (!(Size.m3239getHeightimpl(dstSize) == 0.0f)) {
                return ScaleFactorKt.m4769timesUQTWf7w(jSize, this.contentScale.mo4675computeScaleFactorH7hwNQA(jSize, dstSize));
            }
        }
        return Size.INSTANCE.m3251getZeroNHjbRc();
    }

    /* renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m3084modifyConstraintsZezNO4M(long constraints) {
        int iM5693getMinWidthimpl;
        int iM5692getMinHeightimpl;
        boolean z = Constraints.m5687getHasBoundedWidthimpl(constraints) && Constraints.m5686getHasBoundedHeightimpl(constraints);
        boolean z2 = Constraints.m5689getHasFixedWidthimpl(constraints) && Constraints.m5688getHasFixedHeightimpl(constraints);
        if ((!getUseIntrinsicSize() && z) || z2) {
            return Constraints.m5682copyZbe2FdA$default(constraints, Constraints.m5691getMaxWidthimpl(constraints), 0, Constraints.m5690getMaxHeightimpl(constraints), 0, 10, null);
        }
        long jMo4033getIntrinsicSizeNHjbRc = this.painter.getIntrinsicSize();
        if (m3083hasSpecifiedAndFiniteWidthuvyYCjk(jMo4033getIntrinsicSizeNHjbRc)) {
            iM5693getMinWidthimpl = MathKt.roundToInt(Size.m3242getWidthimpl(jMo4033getIntrinsicSizeNHjbRc));
        } else {
            iM5693getMinWidthimpl = Constraints.m5693getMinWidthimpl(constraints);
        }
        if (m3082hasSpecifiedAndFiniteHeightuvyYCjk(jMo4033getIntrinsicSizeNHjbRc)) {
            iM5692getMinHeightimpl = MathKt.roundToInt(Size.m3239getHeightimpl(jMo4033getIntrinsicSizeNHjbRc));
        } else {
            iM5692getMinHeightimpl = Constraints.m5692getMinHeightimpl(constraints);
        }
        long jM3081calculateScaledSizeE7KxVPU = m3081calculateScaledSizeE7KxVPU(SizeKt.Size(ConstraintsKt.m5705constrainWidthK40F9xA(constraints, iM5693getMinWidthimpl), ConstraintsKt.m5704constrainHeightK40F9xA(constraints, iM5692getMinHeightimpl)));
        return Constraints.m5682copyZbe2FdA$default(constraints, ConstraintsKt.m5705constrainWidthK40F9xA(constraints, MathKt.roundToInt(Size.m3242getWidthimpl(jM3081calculateScaledSizeE7KxVPU))), 0, ConstraintsKt.m5704constrainHeightK40F9xA(constraints, MathKt.roundToInt(Size.m3239getHeightimpl(jM3081calculateScaledSizeE7KxVPU))), 0, 10, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    @Override // androidx.compose.ui.node.DrawModifierNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(androidx.compose.ui.graphics.drawscope.ContentDrawScope r13) {
        /*
            r12 = this;
            androidx.compose.ui.graphics.painter.Painter r0 = r12.painter
            long r0 = r0.getIntrinsicSize()
            boolean r2 = r12.m3083hasSpecifiedAndFiniteWidthuvyYCjk(r0)
            if (r2 == 0) goto L11
            float r2 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r0)
            goto L19
        L11:
            long r2 = r13.mo3963getSizeNHjbRc()
            float r2 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r2)
        L19:
            boolean r3 = r12.m3082hasSpecifiedAndFiniteHeightuvyYCjk(r0)
            if (r3 == 0) goto L24
            float r0 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r0)
            goto L2c
        L24:
            long r0 = r13.mo3963getSizeNHjbRc()
            float r0 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r0)
        L2c:
            long r0 = androidx.compose.ui.geometry.SizeKt.Size(r2, r0)
            long r2 = r13.mo3963getSizeNHjbRc()
            float r2 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r2)
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            r4 = 1
            r5 = 0
            if (r2 != 0) goto L41
            r2 = r4
            goto L42
        L41:
            r2 = r5
        L42:
            if (r2 != 0) goto L63
            long r6 = r13.mo3963getSizeNHjbRc()
            float r2 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r6)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L51
            goto L52
        L51:
            r4 = r5
        L52:
            if (r4 != 0) goto L63
            androidx.compose.ui.layout.ContentScale r2 = r12.contentScale
            long r3 = r13.mo3963getSizeNHjbRc()
            long r2 = r2.mo4675computeScaleFactorH7hwNQA(r0, r3)
            long r0 = androidx.compose.ui.layout.ScaleFactorKt.m4769timesUQTWf7w(r0, r2)
            goto L69
        L63:
            androidx.compose.ui.geometry.Size$Companion r0 = androidx.compose.ui.geometry.Size.INSTANCE
            long r0 = r0.m3251getZeroNHjbRc()
        L69:
            r4 = r0
            androidx.compose.ui.Alignment r6 = r12.alignment
            float r0 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r4)
            int r0 = kotlin.math.MathKt.roundToInt(r0)
            float r1 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r4)
            int r1 = kotlin.math.MathKt.roundToInt(r1)
            long r7 = androidx.compose.ui.unit.IntSizeKt.IntSize(r0, r1)
            long r0 = r13.mo3963getSizeNHjbRc()
            float r0 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r0)
            int r0 = kotlin.math.MathKt.roundToInt(r0)
            long r1 = r13.mo3963getSizeNHjbRc()
            float r1 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r1)
            int r1 = kotlin.math.MathKt.roundToInt(r1)
            long r9 = androidx.compose.ui.unit.IntSizeKt.IntSize(r0, r1)
            androidx.compose.ui.unit.LayoutDirection r11 = r13.getLayoutDirection()
            long r0 = r6.mo3050alignKFBX0sM(r7, r9, r11)
            int r2 = androidx.compose.ui.unit.IntOffset.m5863getXimpl(r0)
            float r8 = (float) r2
            int r0 = androidx.compose.ui.unit.IntOffset.m5864getYimpl(r0)
            float r0 = (float) r0
            r1 = r13
            androidx.compose.ui.graphics.drawscope.DrawScope r1 = (androidx.compose.ui.graphics.drawscope.DrawScope) r1
            androidx.compose.ui.graphics.drawscope.DrawContext r2 = r1.getDrawContext()
            androidx.compose.ui.graphics.drawscope.DrawTransform r2 = r2.getTransform()
            r2.translate(r8, r0)
            androidx.compose.ui.graphics.painter.Painter r2 = r12.painter
            float r6 = r12.alpha
            androidx.compose.ui.graphics.ColorFilter r7 = r12.colorFilter
            r3 = r1
            r2.m4039drawx_KDEd0(r3, r4, r6, r7)
            androidx.compose.ui.graphics.drawscope.DrawContext r1 = r1.getDrawContext()
            androidx.compose.ui.graphics.drawscope.DrawTransform r1 = r1.getTransform()
            float r2 = -r8
            float r0 = -r0
            r1.translate(r2, r0)
            r13.drawContent()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.draw.PainterNode.draw(androidx.compose.ui.graphics.drawscope.ContentDrawScope):void");
    }

    /* renamed from: hasSpecifiedAndFiniteWidth-uvyYCjk, reason: not valid java name */
    private final boolean m3083hasSpecifiedAndFiniteWidthuvyYCjk(long j) {
        if (Size.m3238equalsimpl0(j, Size.INSTANCE.m3250getUnspecifiedNHjbRc())) {
            return false;
        }
        float fM3242getWidthimpl = Size.m3242getWidthimpl(j);
        return !Float.isInfinite(fM3242getWidthimpl) && !Float.isNaN(fM3242getWidthimpl);
    }

    /* renamed from: hasSpecifiedAndFiniteHeight-uvyYCjk, reason: not valid java name */
    private final boolean m3082hasSpecifiedAndFiniteHeightuvyYCjk(long j) {
        if (Size.m3238equalsimpl0(j, Size.INSTANCE.m3250getUnspecifiedNHjbRc())) {
            return false;
        }
        float fM3239getHeightimpl = Size.m3239getHeightimpl(j);
        return !Float.isInfinite(fM3239getHeightimpl) && !Float.isNaN(fM3239getHeightimpl);
    }

    public String toString() {
        return "PainterModifier(painter=" + this.painter + ", sizeToIntrinsics=" + this.sizeToIntrinsics + ", alignment=" + this.alignment + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }
}
