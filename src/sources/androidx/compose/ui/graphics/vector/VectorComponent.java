package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Vector.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u00106\u001a\u00020\u000eH\u0002J\b\u00107\u001a\u00020\"H\u0016J\f\u00108\u001a\u00020\u000e*\u00020\rH\u0016J\u001c\u00108\u001a\u00020\u000e*\u00020\r2\u0006\u00109\u001a\u00020-2\b\u0010:\u001a\u0004\u0018\u00010\u0011R\u001a\u0010\u0005\u001a\u00020\u00068@X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020(X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010)R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R1\u00100\u001a\u00020(2\u0006\u0010\u0010\u001a\u00020(8@@@X\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b5\u0010\u0018\u001a\u0004\b1\u00102\"\u0004\b3\u00104\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006;"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorComponent;", "Landroidx/compose/ui/graphics/vector/VNode;", "root", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "(Landroidx/compose/ui/graphics/vector/GroupComponent;)V", "cacheBitmapConfig", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "getCacheBitmapConfig-_sVssgQ$ui_release", "()I", "cacheDrawScope", "Landroidx/compose/ui/graphics/vector/DrawCache;", "drawVectorBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "<set-?>", "Landroidx/compose/ui/graphics/ColorFilter;", "intrinsicColorFilter", "getIntrinsicColorFilter$ui_release", "()Landroidx/compose/ui/graphics/ColorFilter;", "setIntrinsicColorFilter$ui_release", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "intrinsicColorFilter$delegate", "Landroidx/compose/runtime/MutableState;", "invalidateCallback", "Lkotlin/Function0;", "getInvalidateCallback$ui_release", "()Lkotlin/jvm/functions/Function0;", "setInvalidateCallback$ui_release", "(Lkotlin/jvm/functions/Function0;)V", "isDirty", "", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "previousDrawSize", "Landroidx/compose/ui/geometry/Size;", "J", "getRoot", "()Landroidx/compose/ui/graphics/vector/GroupComponent;", "rootScaleX", "", "rootScaleY", "tintFilter", "viewportSize", "getViewportSize-NH-jbRc$ui_release", "()J", "setViewportSize-uvyYCjk$ui_release", "(J)V", "viewportSize$delegate", "doInvalidate", "toString", "draw", "alpha", "colorFilter", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VectorComponent extends VNode {
    public static final int $stable = 8;
    private final DrawCache cacheDrawScope;
    private final Function1<DrawScope, Unit> drawVectorBlock;

    /* renamed from: intrinsicColorFilter$delegate, reason: from kotlin metadata */
    private final MutableState intrinsicColorFilter;
    private Function0<Unit> invalidateCallback;
    private boolean isDirty;
    private String name;
    private long previousDrawSize;
    private final GroupComponent root;
    private float rootScaleX;
    private float rootScaleY;
    private ColorFilter tintFilter;

    /* renamed from: viewportSize$delegate, reason: from kotlin metadata */
    private final MutableState viewportSize;

    public VectorComponent(GroupComponent groupComponent) {
        super(null);
        this.root = groupComponent;
        groupComponent.setInvalidateListener$ui_release(new Function1<VNode, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComponent.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VNode vNode) {
                invoke2(vNode);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VNode vNode) {
                VectorComponent.this.doInvalidate();
            }
        });
        this.name = "";
        this.isDirty = true;
        this.cacheDrawScope = new DrawCache();
        this.invalidateCallback = new Function0<Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComponent$invalidateCallback$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        };
        this.intrinsicColorFilter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.viewportSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m3230boximpl(Size.INSTANCE.m3251getZeroNHjbRc()), null, 2, null);
        this.previousDrawSize = Size.INSTANCE.m3250getUnspecifiedNHjbRc();
        this.rootScaleX = 1.0f;
        this.rootScaleY = 1.0f;
        this.drawVectorBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComponent$drawVectorBlock$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawScope) {
                GroupComponent root = this.this$0.getRoot();
                VectorComponent vectorComponent = this.this$0;
                float f = vectorComponent.rootScaleX;
                float f2 = vectorComponent.rootScaleY;
                long jM3189getZeroF1C5BW0 = Offset.INSTANCE.m3189getZeroF1C5BW0();
                DrawContext drawContext = drawScope.getDrawContext();
                long jMo3888getSizeNHjbRc = drawContext.mo3888getSizeNHjbRc();
                drawContext.getCanvas().save();
                drawContext.getTransform().mo3895scale0AR0LA0(f, f2, jM3189getZeroF1C5BW0);
                root.draw(drawScope);
                drawContext.getCanvas().restore();
                drawContext.mo3889setSizeuvyYCjk(jMo3888getSizeNHjbRc);
            }
        };
    }

    public final GroupComponent getRoot() {
        return this.root;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doInvalidate() {
        this.isDirty = true;
        this.invalidateCallback.invoke();
    }

    /* renamed from: getCacheBitmapConfig-_sVssgQ$ui_release, reason: not valid java name */
    public final int m4057getCacheBitmapConfig_sVssgQ$ui_release() {
        ImageBitmap mCachedImage = this.cacheDrawScope.getMCachedImage();
        return mCachedImage != null ? mCachedImage.mo3281getConfig_sVssgQ() : ImageBitmapConfig.INSTANCE.m3635getArgb8888_sVssgQ();
    }

    public final Function0<Unit> getInvalidateCallback$ui_release() {
        return this.invalidateCallback;
    }

    public final void setInvalidateCallback$ui_release(Function0<Unit> function0) {
        this.invalidateCallback = function0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ColorFilter getIntrinsicColorFilter$ui_release() {
        return (ColorFilter) this.intrinsicColorFilter.getValue();
    }

    public final void setIntrinsicColorFilter$ui_release(ColorFilter colorFilter) {
        this.intrinsicColorFilter.setValue(colorFilter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getViewportSize-NH-jbRc$ui_release, reason: not valid java name */
    public final long m4058getViewportSizeNHjbRc$ui_release() {
        return ((Size) this.viewportSize.getValue()).getPackedValue();
    }

    /* renamed from: setViewportSize-uvyYCjk$ui_release, reason: not valid java name */
    public final void m4059setViewportSizeuvyYCjk$ui_release(long j) {
        this.viewportSize.setValue(Size.m3230boximpl(j));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void draw(androidx.compose.ui.graphics.drawscope.DrawScope r11, float r12, androidx.compose.ui.graphics.ColorFilter r13) {
        /*
            r10 = this;
            androidx.compose.ui.graphics.vector.GroupComponent r0 = r10.root
            boolean r0 = r0.getIsTintable()
            r1 = 0
            if (r0 == 0) goto L20
            androidx.compose.ui.graphics.vector.GroupComponent r0 = r10.root
            long r2 = r0.getTintColor()
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.INSTANCE
            long r4 = r0.m3450getUnspecified0d7_KjU()
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r2 = 1
            if (r0 == 0) goto L1c
            r0 = r2
            goto L1d
        L1c:
            r0 = r1
        L1d:
            if (r0 == 0) goto L20
            goto L21
        L20:
            r2 = r1
        L21:
            if (r2 == 0) goto L3a
            androidx.compose.ui.graphics.ColorFilter r0 = r10.getIntrinsicColorFilter$ui_release()
            boolean r0 = androidx.compose.ui.graphics.vector.VectorKt.tintableWithAlphaMask(r0)
            if (r0 == 0) goto L3a
            boolean r0 = androidx.compose.ui.graphics.vector.VectorKt.tintableWithAlphaMask(r13)
            if (r0 == 0) goto L3a
            androidx.compose.ui.graphics.ImageBitmapConfig$Companion r0 = androidx.compose.ui.graphics.ImageBitmapConfig.INSTANCE
            int r0 = r0.m3634getAlpha8_sVssgQ()
            goto L40
        L3a:
            androidx.compose.ui.graphics.ImageBitmapConfig$Companion r0 = androidx.compose.ui.graphics.ImageBitmapConfig.INSTANCE
            int r0 = r0.m3635getArgb8888_sVssgQ()
        L40:
            r3 = r0
            boolean r0 = r10.isDirty
            if (r0 != 0) goto L5b
            long r4 = r10.previousDrawSize
            long r6 = r11.mo3963getSizeNHjbRc()
            boolean r0 = androidx.compose.ui.geometry.Size.m3238equalsimpl0(r4, r6)
            if (r0 == 0) goto L5b
            int r0 = r10.m4057getCacheBitmapConfig_sVssgQ$ui_release()
            boolean r0 = androidx.compose.ui.graphics.ImageBitmapConfig.m3630equalsimpl0(r3, r0)
            if (r0 != 0) goto Ld8
        L5b:
            androidx.compose.ui.graphics.ImageBitmapConfig$Companion r0 = androidx.compose.ui.graphics.ImageBitmapConfig.INSTANCE
            int r0 = r0.m3634getAlpha8_sVssgQ()
            boolean r0 = androidx.compose.ui.graphics.ImageBitmapConfig.m3630equalsimpl0(r3, r0)
            if (r0 == 0) goto L77
            androidx.compose.ui.graphics.ColorFilter$Companion r4 = androidx.compose.ui.graphics.ColorFilter.INSTANCE
            androidx.compose.ui.graphics.vector.GroupComponent r0 = r10.root
            long r5 = r0.getTintColor()
            r7 = 0
            r8 = 2
            r9 = 0
            androidx.compose.ui.graphics.ColorFilter r0 = androidx.compose.ui.graphics.ColorFilter.Companion.m3455tintxETnrds$default(r4, r5, r7, r8, r9)
            goto L78
        L77:
            r0 = 0
        L78:
            r10.tintFilter = r0
            long r4 = r11.mo3963getSizeNHjbRc()
            float r0 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r4)
            long r4 = r10.m4058getViewportSizeNHjbRc$ui_release()
            float r2 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r4)
            float r0 = r0 / r2
            r10.rootScaleX = r0
            long r4 = r11.mo3963getSizeNHjbRc()
            float r0 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r4)
            long r4 = r10.m4058getViewportSizeNHjbRc$ui_release()
            float r2 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r4)
            float r0 = r0 / r2
            r10.rootScaleY = r0
            androidx.compose.ui.graphics.vector.DrawCache r2 = r10.cacheDrawScope
            long r4 = r11.mo3963getSizeNHjbRc()
            float r0 = androidx.compose.ui.geometry.Size.m3242getWidthimpl(r4)
            double r4 = (double) r0
            double r4 = java.lang.Math.ceil(r4)
            float r0 = (float) r4
            int r0 = (int) r0
            long r4 = r11.mo3963getSizeNHjbRc()
            float r4 = androidx.compose.ui.geometry.Size.m3239getHeightimpl(r4)
            double r4 = (double) r4
            double r4 = java.lang.Math.ceil(r4)
            float r4 = (float) r4
            int r4 = (int) r4
            long r4 = androidx.compose.ui.unit.IntSizeKt.IntSize(r0, r4)
            r6 = r11
            androidx.compose.ui.unit.Density r6 = (androidx.compose.ui.unit.Density) r6
            androidx.compose.ui.unit.LayoutDirection r7 = r11.getLayoutDirection()
            kotlin.jvm.functions.Function1<androidx.compose.ui.graphics.drawscope.DrawScope, kotlin.Unit> r8 = r10.drawVectorBlock
            r2.m4040drawCachedImageFqjB98A(r3, r4, r6, r7, r8)
            r10.isDirty = r1
            long r0 = r11.mo3963getSizeNHjbRc()
            r10.previousDrawSize = r0
        Ld8:
            if (r13 == 0) goto Ldb
            goto Le8
        Ldb:
            androidx.compose.ui.graphics.ColorFilter r13 = r10.getIntrinsicColorFilter$ui_release()
            if (r13 == 0) goto Le6
            androidx.compose.ui.graphics.ColorFilter r13 = r10.getIntrinsicColorFilter$ui_release()
            goto Le8
        Le6:
            androidx.compose.ui.graphics.ColorFilter r13 = r10.tintFilter
        Le8:
            androidx.compose.ui.graphics.vector.DrawCache r0 = r10.cacheDrawScope
            r0.drawInto(r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.VectorComponent.draw(androidx.compose.ui.graphics.drawscope.DrawScope, float, androidx.compose.ui.graphics.ColorFilter):void");
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public void draw(DrawScope drawScope) {
        draw(drawScope, 1.0f, null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Params: \tname: ");
        sb.append(this.name).append("\n\tviewportWidth: ");
        sb.append(Size.m3242getWidthimpl(m4058getViewportSizeNHjbRc$ui_release())).append("\n\tviewportHeight: ");
        sb.append(Size.m3239getHeightimpl(m4058getViewportSizeNHjbRc$ui_release())).append("\n");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
