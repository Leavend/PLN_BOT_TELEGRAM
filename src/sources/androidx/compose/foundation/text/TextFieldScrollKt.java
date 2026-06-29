package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: TextFieldScroll.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002\u001a4\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015H\u0000\u001a*\u0010\u0017\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\nH\u0000¨\u0006\u001b"}, d2 = {"getCursorRectInScroller", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/Density;", "cursorOffset", "", "transformedText", "Landroidx/compose/ui/text/input/TransformedText;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "rtl", "", "textFieldWidth", "textFieldScroll", "Landroidx/compose/ui/Modifier;", "scrollerPosition", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "textLayoutResultProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "textFieldScrollable", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "enabled", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextFieldScrollKt {

    /* compiled from: TextFieldScroll.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ Modifier textFieldScrollable$default(Modifier modifier, TextFieldScrollerPosition textFieldScrollerPosition, MutableInteractionSource mutableInteractionSource, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            mutableInteractionSource = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return textFieldScrollable(modifier, textFieldScrollerPosition, mutableInteractionSource, z);
    }

    public static final Modifier textFieldScroll(Modifier modifier, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Function0<TextLayoutResultProxy> function0) {
        VerticalScrollLayoutModifier verticalScrollLayoutModifier;
        Orientation orientation = textFieldScrollerPosition.getOrientation();
        int iM929getOffsetToFollow5zctL8 = textFieldScrollerPosition.m929getOffsetToFollow5zctL8(textFieldValue.getSelection());
        textFieldScrollerPosition.m931setPreviousSelection5zctL8(textFieldValue.getSelection());
        TransformedText transformedTextFilterWithValidation = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation, textFieldValue.getAnnotatedString());
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            verticalScrollLayoutModifier = new VerticalScrollLayoutModifier(textFieldScrollerPosition, iM929getOffsetToFollow5zctL8, transformedTextFilterWithValidation, function0);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            verticalScrollLayoutModifier = new HorizontalScrollLayoutModifier(textFieldScrollerPosition, iM929getOffsetToFollow5zctL8, transformedTextFilterWithValidation, function0);
        }
        return ClipKt.clipToBounds(modifier).then(verticalScrollLayoutModifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect getCursorRectInScroller(Density density, int i, TransformedText transformedText, TextLayoutResult textLayoutResult, boolean z, int i2) {
        Rect zero;
        float left;
        float left2;
        if (textLayoutResult == null || (zero = textLayoutResult.getCursorRect(transformedText.getOffsetMapping().originalToTransformed(i))) == null) {
            zero = Rect.INSTANCE.getZero();
        }
        Rect rect = zero;
        int iMo310roundToPx0680j_4 = density.mo310roundToPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
        if (z) {
            left = (i2 - rect.getLeft()) - iMo310roundToPx0680j_4;
        } else {
            left = rect.getLeft();
        }
        float f = left;
        if (z) {
            left2 = i2 - rect.getLeft();
        } else {
            left2 = rect.getLeft() + iMo310roundToPx0680j_4;
        }
        return Rect.copy$default(rect, f, 0.0f, left2, 0.0f, 10, null);
    }

    public static final Modifier textFieldScrollable(Modifier modifier, final TextFieldScrollerPosition textFieldScrollerPosition, final MutableInteractionSource mutableInteractionSource, final boolean z) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("textFieldScrollable");
                inspectorInfo.getProperties().set("scrollerPosition", textFieldScrollerPosition);
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.TextFieldScrollKt.textFieldScrollable.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            /* JADX WARN: Removed duplicated region for block: B:33:0x00c5  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final androidx.compose.ui.Modifier invoke(androidx.compose.ui.Modifier r13, androidx.compose.runtime.Composer r14, int r15) {
                /*
                    r12 = this;
                    r13 = 805428266(0x3001dc2a, float:4.72428E-10)
                    r14.startReplaceableGroup(r13)
                    java.lang.String r0 = "C68@2901L7,70@3046L412,83@3610L352:TextFieldScroll.kt#423gt5"
                    androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                    boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                    if (r0 == 0) goto L18
                    r0 = -1
                    java.lang.String r1 = "androidx.compose.foundation.text.textFieldScrollable.<anonymous> (TextFieldScroll.kt:68)"
                    androidx.compose.runtime.ComposerKt.traceEventStart(r13, r15, r0, r1)
                L18:
                    androidx.compose.runtime.ProvidableCompositionLocal r13 = androidx.compose.ui.platform.CompositionLocalsKt.getLocalLayoutDirection()
                    androidx.compose.runtime.CompositionLocal r13 = (androidx.compose.runtime.CompositionLocal) r13
                    r15 = 2023513938(0x789c5f52, float:2.5372864E34)
                    java.lang.String r0 = "CC:CompositionLocal.kt#9igjgp"
                    androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r14, r15, r0)
                    java.lang.Object r13 = r14.consume(r13)
                    androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r14)
                    androidx.compose.ui.unit.LayoutDirection r15 = androidx.compose.ui.unit.LayoutDirection.Rtl
                    r0 = 1
                    r1 = 0
                    if (r13 != r15) goto L35
                    r13 = r0
                    goto L36
                L35:
                    r13 = r1
                L36:
                    androidx.compose.foundation.text.TextFieldScrollerPosition r15 = r1
                    androidx.compose.foundation.gestures.Orientation r15 = r15.getOrientation()
                    androidx.compose.foundation.gestures.Orientation r2 = androidx.compose.foundation.gestures.Orientation.Vertical
                    if (r15 == r2) goto L45
                    if (r13 != 0) goto L43
                    goto L45
                L43:
                    r7 = r1
                    goto L46
                L45:
                    r7 = r0
                L46:
                    r13 = 753734506(0x2ced136a, float:6.7381005E-12)
                    r14.startReplaceableGroup(r13)
                    androidx.compose.foundation.text.TextFieldScrollerPosition r13 = r1
                    boolean r13 = r14.changed(r13)
                    androidx.compose.foundation.text.TextFieldScrollerPosition r15 = r1
                    java.lang.Object r2 = r14.rememberedValue()
                    if (r13 != 0) goto L62
                    androidx.compose.runtime.Composer$Companion r13 = androidx.compose.runtime.Composer.INSTANCE
                    java.lang.Object r13 = r13.getEmpty()
                    if (r2 != r13) goto L6d
                L62:
                    androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$scrollableState$1$1 r13 = new androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$scrollableState$1$1
                    r13.<init>()
                    r2 = r13
                    kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
                    r14.updateRememberedValue(r2)
                L6d:
                    kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
                    r14.endReplaceableGroup()
                    androidx.compose.foundation.gestures.ScrollableState r13 = androidx.compose.foundation.gestures.ScrollableStateKt.rememberScrollableState(r2, r14, r1)
                    androidx.compose.foundation.text.TextFieldScrollerPosition r15 = r1
                    r2 = 511388516(0x1e7b2b64, float:1.3296802E-20)
                    r14.startReplaceableGroup(r2)
                    java.lang.String r2 = "CC(remember)P(1,2):Composables.kt#9igjgp"
                    androidx.compose.runtime.ComposerKt.sourceInformation(r14, r2)
                    boolean r2 = r14.changed(r13)
                    boolean r3 = r14.changed(r15)
                    r2 = r2 | r3
                    java.lang.Object r3 = r14.rememberedValue()
                    if (r2 != 0) goto L9a
                    androidx.compose.runtime.Composer$Companion r2 = androidx.compose.runtime.Composer.INSTANCE
                    java.lang.Object r2 = r2.getEmpty()
                    if (r3 != r2) goto La2
                L9a:
                    androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1 r3 = new androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1
                    r3.<init>(r15)
                    r14.updateRememberedValue(r3)
                La2:
                    r14.endReplaceableGroup()
                    androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1 r3 = (androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1) r3
                    androidx.compose.ui.Modifier$Companion r13 = androidx.compose.ui.Modifier.INSTANCE
                    androidx.compose.foundation.text.TextFieldScrollerPosition r15 = r1
                    androidx.compose.foundation.gestures.Orientation r5 = r15.getOrientation()
                    boolean r15 = r2
                    if (r15 == 0) goto Lc5
                    androidx.compose.foundation.text.TextFieldScrollerPosition r15 = r1
                    float r15 = r15.getMaximum()
                    r2 = 0
                    int r15 = (r15 > r2 ? 1 : (r15 == r2 ? 0 : -1))
                    if (r15 != 0) goto Lc0
                    r15 = r0
                    goto Lc1
                Lc0:
                    r15 = r1
                Lc1:
                    if (r15 != 0) goto Lc5
                    r6 = r0
                    goto Lc6
                Lc5:
                    r6 = r1
                Lc6:
                    androidx.compose.ui.Modifier r13 = (androidx.compose.ui.Modifier) r13
                    r4 = r3
                    androidx.compose.foundation.gestures.ScrollableState r4 = (androidx.compose.foundation.gestures.ScrollableState) r4
                    r8 = 0
                    androidx.compose.foundation.interaction.MutableInteractionSource r9 = r3
                    r10 = 16
                    r11 = 0
                    r3 = r13
                    androidx.compose.ui.Modifier r13 = androidx.compose.foundation.gestures.ScrollableKt.scrollable$default(r3, r4, r5, r6, r7, r8, r9, r10, r11)
                    boolean r15 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                    if (r15 == 0) goto Ldf
                    androidx.compose.runtime.ComposerKt.traceEventEnd()
                Ldf:
                    r14.endReplaceableGroup()
                    return r13
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextFieldScrollKt.AnonymousClass2.invoke(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):androidx.compose.ui.Modifier");
            }
        });
    }
}
