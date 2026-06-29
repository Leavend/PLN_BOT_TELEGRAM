package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyGridMeasure.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aM\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\bH\u0083\b\u001a\u008c\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001aõ\u0001\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042/\u00105\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002080\b¢\u0006\u0002\b9\u0012\u0004\u0012\u00020:06H\u0000ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"calculateExtraItems", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "pinnedItems", "", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "itemConstraints", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Constraints;", "filter", "", "calculateItemsOffsets", "", "lines", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "isVertical", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "placementAnimator", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "spanLayoutProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-W2FL7xs", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;Ljava/util/List;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyGridMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* renamed from: measureLazyGrid-W2FL7xs, reason: not valid java name */
    public static final LazyGridMeasureResult m703measureLazyGridW2FL7xs(int i, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyGridItemPlacementAnimator lazyGridItemPlacementAnimator, LazyGridSpanLayoutProvider lazyGridSpanLayoutProvider, List<Integer> list, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        boolean z3;
        int i8;
        int index;
        int mainAxisSizeWithSpacings;
        LazyGridMeasuredLine lazyGridMeasuredLine;
        LazyGridMeasuredLine lazyGridMeasuredLine2;
        int i9;
        int iM5705constrainWidthK40F9xA;
        int iM5690getMaxHeightimpl;
        List<LazyGridMeasuredItem> list2;
        int i10;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        int i11;
        int i12;
        boolean z4;
        int i13;
        List<Integer> list3 = list;
        if (!(i3 >= 0)) {
            throw new IllegalArgumentException("negative beforeContentPadding".toString());
        }
        if (!(i4 >= 0)) {
            throw new IllegalArgumentException("negative afterContentPadding".toString());
        }
        if (i <= 0) {
            return new LazyGridMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(Constraints.m5693getMinWidthimpl(j)), Integer.valueOf(Constraints.m5692getMinHeightimpl(j)), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }), false, CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
        }
        int iRoundToInt = MathKt.roundToInt(f);
        int i14 = i7 - iRoundToInt;
        if (i6 == 0 && i14 < 0) {
            iRoundToInt += i14;
            i14 = 0;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i15 = -i3;
        int i16 = (i5 < 0 ? i5 : 0) + i15;
        int mainAxisSizeWithSpacings2 = i14 + i16;
        int i17 = i6;
        while (mainAxisSizeWithSpacings2 < 0 && i17 > 0) {
            i17--;
            LazyGridMeasuredLine andMeasure = lazyGridMeasuredLineProvider.getAndMeasure(i17);
            arrayDeque.add(0, andMeasure);
            mainAxisSizeWithSpacings2 += andMeasure.getMainAxisSizeWithSpacings();
        }
        if (mainAxisSizeWithSpacings2 < i16) {
            iRoundToInt += mainAxisSizeWithSpacings2;
            mainAxisSizeWithSpacings2 = i16;
        }
        int i18 = mainAxisSizeWithSpacings2 - i16;
        int i19 = i2 + i4;
        int i20 = i17;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i19, 0);
        int mainAxisSizeWithSpacings3 = -i18;
        int i21 = i20;
        int mainAxisSizeWithSpacings4 = i18;
        int i22 = 0;
        boolean z5 = false;
        while (i22 < arrayDeque.size()) {
            if (mainAxisSizeWithSpacings3 >= iCoerceAtLeast) {
                arrayDeque.remove(i22);
                z5 = true;
            } else {
                i21++;
                mainAxisSizeWithSpacings3 += ((LazyGridMeasuredLine) arrayDeque.get(i22)).getMainAxisSizeWithSpacings();
                i22++;
            }
        }
        int i23 = i21;
        boolean z6 = z5;
        while (i23 < i && (mainAxisSizeWithSpacings3 < iCoerceAtLeast || mainAxisSizeWithSpacings3 <= 0 || arrayDeque.isEmpty())) {
            int i24 = iCoerceAtLeast;
            LazyGridMeasuredLine andMeasure2 = lazyGridMeasuredLineProvider.getAndMeasure(i23);
            if (andMeasure2.isEmpty()) {
                break;
            }
            int mainAxisSizeWithSpacings5 = mainAxisSizeWithSpacings3 + andMeasure2.getMainAxisSizeWithSpacings();
            if (mainAxisSizeWithSpacings5 <= i16) {
                i11 = mainAxisSizeWithSpacings5;
                i12 = i16;
                if (((LazyGridMeasuredItem) ArraysKt.last(andMeasure2.getItems())).getIndex() != i - 1) {
                    i13 = i23 + 1;
                    mainAxisSizeWithSpacings4 -= andMeasure2.getMainAxisSizeWithSpacings();
                    z4 = true;
                }
                i23++;
                i20 = i13;
                z6 = z4;
                iCoerceAtLeast = i24;
                i16 = i12;
                mainAxisSizeWithSpacings3 = i11;
            } else {
                i11 = mainAxisSizeWithSpacings5;
                i12 = i16;
            }
            arrayDeque.add(andMeasure2);
            z4 = z6;
            i13 = i20;
            i23++;
            i20 = i13;
            z6 = z4;
            iCoerceAtLeast = i24;
            i16 = i12;
            mainAxisSizeWithSpacings3 = i11;
        }
        if (mainAxisSizeWithSpacings3 < i2) {
            int i25 = i2 - mainAxisSizeWithSpacings3;
            mainAxisSizeWithSpacings3 += i25;
            int i26 = i20;
            mainAxisSizeWithSpacings = mainAxisSizeWithSpacings4 - i25;
            while (mainAxisSizeWithSpacings < i3 && i26 > 0) {
                i26--;
                int i27 = i15;
                LazyGridMeasuredLine andMeasure3 = lazyGridMeasuredLineProvider.getAndMeasure(i26);
                arrayDeque.add(0, andMeasure3);
                mainAxisSizeWithSpacings += andMeasure3.getMainAxisSizeWithSpacings();
                z6 = z6;
                i15 = i27;
            }
            z3 = z6;
            i8 = i15;
            index = 0;
            iRoundToInt += i25;
            if (mainAxisSizeWithSpacings < 0) {
                iRoundToInt += mainAxisSizeWithSpacings;
                mainAxisSizeWithSpacings3 += mainAxisSizeWithSpacings;
                mainAxisSizeWithSpacings = 0;
            }
        } else {
            z3 = z6;
            i8 = i15;
            index = 0;
            mainAxisSizeWithSpacings = mainAxisSizeWithSpacings4;
        }
        float f2 = (MathKt.getSign(MathKt.roundToInt(f)) != MathKt.getSign(iRoundToInt) || Math.abs(MathKt.roundToInt(f)) < Math.abs(iRoundToInt)) ? f : iRoundToInt;
        if ((mainAxisSizeWithSpacings >= 0 ? 1 : index) == 0) {
            throw new IllegalArgumentException("negative initial offset".toString());
        }
        int i28 = -mainAxisSizeWithSpacings;
        LazyGridMeasuredLine lazyGridMeasuredLine3 = (LazyGridMeasuredLine) arrayDeque.first();
        LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(lazyGridMeasuredLine3.getItems());
        int index2 = lazyGridMeasuredItem2 != null ? lazyGridMeasuredItem2.getIndex() : index;
        LazyGridMeasuredLine lazyGridMeasuredLine4 = (LazyGridMeasuredLine) arrayDeque.lastOrNull();
        if (lazyGridMeasuredLine4 == null || (items = lazyGridMeasuredLine4.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) {
            lazyGridMeasuredLine = lazyGridMeasuredLine3;
        } else {
            lazyGridMeasuredLine = lazyGridMeasuredLine3;
            index = lazyGridMeasuredItem.getIndex();
        }
        int size = list.size();
        ArrayList arrayListEmptyList = null;
        int i29 = mainAxisSizeWithSpacings;
        ArrayList arrayListEmptyList2 = null;
        int i30 = 0;
        while (i30 < size) {
            int i31 = size;
            int iIntValue = list3.get(i30).intValue();
            if (iIntValue >= 0 && iIntValue < index2) {
                LazyGridMeasuredItem lazyGridMeasuredItemM706getAndMeasure3p2s80s$default = LazyGridMeasuredItemProvider.m706getAndMeasure3p2s80s$default(lazyGridMeasuredItemProvider, iIntValue, 0, lazyGridMeasuredLineProvider.m709itemConstraintsOenEA2s(iIntValue), 2, null);
                if (arrayListEmptyList2 == null) {
                    arrayListEmptyList2 = new ArrayList();
                }
                i10 = index2;
                List list4 = arrayListEmptyList2;
                list4.add(lazyGridMeasuredItemM706getAndMeasure3p2s80s$default);
                arrayListEmptyList2 = list4;
            } else {
                i10 = index2;
            }
            i30++;
            index2 = i10;
            size = i31;
        }
        int i32 = index2;
        if (arrayListEmptyList2 == null) {
            arrayListEmptyList2 = CollectionsKt.emptyList();
        }
        List list5 = arrayListEmptyList2;
        int size2 = list.size();
        int i33 = 0;
        while (i33 < size2) {
            int iIntValue2 = list3.get(i33).intValue();
            if (index + 1 <= iIntValue2 && iIntValue2 < i) {
                LazyGridMeasuredItem lazyGridMeasuredItemM706getAndMeasure3p2s80s$default2 = LazyGridMeasuredItemProvider.m706getAndMeasure3p2s80s$default(lazyGridMeasuredItemProvider, iIntValue2, 0, lazyGridMeasuredLineProvider.m709itemConstraintsOenEA2s(iIntValue2), 2, null);
                if (arrayListEmptyList == null) {
                    arrayListEmptyList = new ArrayList();
                }
                List list6 = arrayListEmptyList;
                list6.add(lazyGridMeasuredItemM706getAndMeasure3p2s80s$default2);
                arrayListEmptyList = list6;
            }
            i33++;
            list3 = list;
        }
        if (arrayListEmptyList == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        }
        List list7 = arrayListEmptyList;
        if (i3 > 0 || i5 < 0) {
            int size3 = arrayDeque.size();
            LazyGridMeasuredLine lazyGridMeasuredLine5 = lazyGridMeasuredLine;
            int i34 = i29;
            int i35 = 0;
            while (i35 < size3) {
                int mainAxisSizeWithSpacings6 = ((LazyGridMeasuredLine) arrayDeque.get(i35)).getMainAxisSizeWithSpacings();
                if (i34 == 0 || mainAxisSizeWithSpacings6 > i34) {
                    break;
                }
                int i36 = size3;
                if (i35 == CollectionsKt.getLastIndex(arrayDeque)) {
                    break;
                }
                i34 -= mainAxisSizeWithSpacings6;
                i35++;
                lazyGridMeasuredLine5 = (LazyGridMeasuredLine) arrayDeque.get(i35);
                size3 = i36;
            }
            lazyGridMeasuredLine2 = lazyGridMeasuredLine5;
            i9 = i34;
        } else {
            lazyGridMeasuredLine2 = lazyGridMeasuredLine;
            i9 = i29;
        }
        if (z) {
            iM5705constrainWidthK40F9xA = Constraints.m5691getMaxWidthimpl(j);
        } else {
            iM5705constrainWidthK40F9xA = ConstraintsKt.m5705constrainWidthK40F9xA(j, mainAxisSizeWithSpacings3);
        }
        int i37 = iM5705constrainWidthK40F9xA;
        if (z) {
            iM5690getMaxHeightimpl = ConstraintsKt.m5704constrainHeightK40F9xA(j, mainAxisSizeWithSpacings3);
        } else {
            iM5690getMaxHeightimpl = Constraints.m5690getMaxHeightimpl(j);
        }
        int i38 = i8;
        final List<LazyGridMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(arrayDeque, list5, list7, i37, iM5690getMaxHeightimpl, mainAxisSizeWithSpacings3, i2, i28, z, vertical, horizontal, z2, density);
        lazyGridItemPlacementAnimator.onMeasured((int) f2, i37, iM5690getMaxHeightimpl, listCalculateItemsOffsets, lazyGridMeasuredItemProvider, lazyGridSpanLayoutProvider, z, coroutineScope);
        boolean z7 = index != i + (-1) || mainAxisSizeWithSpacings3 > i2;
        MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(i37), Integer.valueOf(iM5690getMaxHeightimpl), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                List<LazyGridMeasuredItem> list8 = listCalculateItemsOffsets;
                int size4 = list8.size();
                for (int i39 = 0; i39 < size4; i39++) {
                    list8.get(i39).place(placementScope);
                }
                ObservableScopeInvalidator.m733attachToScopeimpl(mutableState);
            }
        });
        if (list5.isEmpty() && list7.isEmpty()) {
            list2 = listCalculateItemsOffsets;
        } else {
            ArrayList arrayList = new ArrayList(listCalculateItemsOffsets.size());
            int size4 = listCalculateItemsOffsets.size();
            for (int i39 = 0; i39 < size4; i39++) {
                LazyGridMeasuredItem lazyGridMeasuredItem3 = listCalculateItemsOffsets.get(i39);
                int index3 = lazyGridMeasuredItem3.getIndex();
                if (i32 <= index3 && index3 <= index) {
                    arrayList.add(lazyGridMeasuredItem3);
                }
            }
            list2 = arrayList;
        }
        return new LazyGridMeasureResult(lazyGridMeasuredLine2, i9, z7, f2, measureResultInvoke, z3, list2, i38, i19, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3) {
            if (!(i5 == 0)) {
                throw new IllegalStateException("non-zero firstLineScrollOffset".toString());
            }
        }
        int size = list.size();
        int length = 0;
        for (int i7 = 0; i7 < size; i7++) {
            length += list.get(i7).getItems().length;
        }
        ArrayList arrayList = new ArrayList(length);
        if (z3) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("no items".toString());
            }
            int size2 = list.size();
            int[] iArr = new int[size2];
            for (int i8 = 0; i8 < size2; i8++) {
                iArr[i8] = list.get(calculateItemsOffsets$reverseAware(i8, z2, size2)).getMainAxisSize();
            }
            int[] iArr2 = new int[size2];
            for (int i9 = 0; i9 < size2; i9++) {
                iArr2[i9] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement".toString());
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement".toString());
                }
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntRange indices = ArraysKt.getIndices(iArr2);
            if (z2) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int mainAxisSize = iArr2[first];
                    LazyGridMeasuredLine lazyGridMeasuredLine = list.get(calculateItemsOffsets$reverseAware(first, z2, size2));
                    if (z2) {
                        mainAxisSize = (i6 - mainAxisSize) - lazyGridMeasuredLine.getMainAxisSize();
                    }
                    CollectionsKt.addAll(arrayList, lazyGridMeasuredLine.position(mainAxisSize, i, i2));
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size() - 1;
            if (size3 >= 0) {
                int i10 = i5;
                while (true) {
                    int i11 = size3 - 1;
                    LazyGridMeasuredItem lazyGridMeasuredItem = list2.get(size3);
                    int mainAxisSizeWithSpacings = i10 - lazyGridMeasuredItem.getMainAxisSizeWithSpacings();
                    lazyGridMeasuredItem.position(mainAxisSizeWithSpacings, 0, i, i2, (48 & 16) != 0 ? -1 : 0, (48 & 32) != 0 ? -1 : 0);
                    arrayList.add(lazyGridMeasuredItem);
                    if (i11 < 0) {
                        break;
                    }
                    size3 = i11;
                    i10 = mainAxisSizeWithSpacings;
                }
            }
            int size4 = list.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i12 = 0; i12 < size4; i12++) {
                LazyGridMeasuredLine lazyGridMeasuredLine2 = list.get(i12);
                CollectionsKt.addAll(arrayList, lazyGridMeasuredLine2.position(mainAxisSizeWithSpacings2, i, i2));
                mainAxisSizeWithSpacings2 += lazyGridMeasuredLine2.getMainAxisSizeWithSpacings();
            }
            int mainAxisSizeWithSpacings3 = mainAxisSizeWithSpacings2;
            int i13 = 0;
            for (int size5 = list3.size(); i13 < size5; size5 = size5) {
                LazyGridMeasuredItem lazyGridMeasuredItem2 = list3.get(i13);
                lazyGridMeasuredItem2.position(mainAxisSizeWithSpacings3, 0, i, i2, (48 & 16) != 0 ? -1 : 0, (48 & 32) != 0 ? -1 : 0);
                arrayList.add(lazyGridMeasuredItem2);
                mainAxisSizeWithSpacings3 += lazyGridMeasuredItem2.getMainAxisSizeWithSpacings();
                i13++;
            }
        }
        return arrayList;
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, Function1<? super Integer, Constraints> function1, Function1<? super Integer, Boolean> function12) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            int iIntValue = list.get(i).intValue();
            if (function12.invoke(Integer.valueOf(iIntValue)).booleanValue()) {
                LazyGridMeasuredItem lazyGridMeasuredItemM706getAndMeasure3p2s80s$default = LazyGridMeasuredItemProvider.m706getAndMeasure3p2s80s$default(lazyGridMeasuredItemProvider, iIntValue, 0, function1.invoke(Integer.valueOf(iIntValue)).getValue(), 2, null);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyGridMeasuredItemM706getAndMeasure3p2s80s$default);
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }
}
