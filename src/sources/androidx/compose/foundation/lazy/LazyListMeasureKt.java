package androidx.compose.foundation.lazy;

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
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyListMeasure.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a\\\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002\u001a4\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010#\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002\u001a\u0095\u0002\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00100\u001a\u0002012\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u00102\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u00010!2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072/\u00108\u001a+\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<0:¢\u0006\u0002\b=\u0012\u0004\u0012\u00020>09H\u0000ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006A"}, d2 = {"calculateItemsOffsets", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", FirebaseAnalytics.Param.ITEMS, "", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "createItemsAfterList", "visibleItems", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "itemsCount", "beyondBoundsItemCount", "pinnedItems", "consumedScroll", "", "isLookingAhead", "lastPostLookaheadLayoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "createItemsBeforeList", "currentFirstItemIndex", "measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "constraints", "Landroidx/compose/ui/unit/Constraints;", "headerIndexes", "itemAnimator", "Landroidx/compose/foundation/lazy/LazyListItemAnimator;", "hasLookaheadPassOccurred", "postLookaheadLayoutInfo", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-5IMabDg", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/LazyListItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/LazyListLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* renamed from: measureLazyList-5IMabDg, reason: not valid java name */
    public static final LazyListMeasureResult m674measureLazyList5IMabDg(int i, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, List<Integer> list, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyListItemAnimator lazyListItemAnimator, int i8, List<Integer> list2, boolean z3, final boolean z4, LazyListLayoutInfo lazyListLayoutInfo, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        int i10;
        int i11;
        int iMax;
        int i12;
        int i13;
        int i14;
        int i15;
        LazyListMeasuredItem lazyListMeasuredItem;
        int i16;
        List<Integer> list3;
        int i17;
        List<LazyListMeasuredItem> list4;
        int i18;
        int i19;
        int i20 = 0;
        if (!(i3 >= 0)) {
            throw new IllegalArgumentException("invalid beforeContentPadding".toString());
        }
        if (!(i4 >= 0)) {
            throw new IllegalArgumentException("invalid afterContentPadding".toString());
        }
        if (i <= 0) {
            int iM5693getMinWidthimpl = Constraints.m5693getMinWidthimpl(j);
            int iM5692getMinHeightimpl = Constraints.m5692getMinHeightimpl(j);
            lazyListItemAnimator.onMeasured(0, iM5693getMinWidthimpl, iM5692getMinHeightimpl, new ArrayList(), lazyListMeasuredItemProvider, z, z4, z3, coroutineScope);
            return new LazyListMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(iM5693getMinWidthimpl), Integer.valueOf(iM5692getMinHeightimpl), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }), 0.0f, false, CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
        }
        int i21 = i6;
        if (i21 >= i) {
            i21 = i - 1;
            i9 = 0;
        } else {
            i9 = i7;
        }
        int iRoundToInt = MathKt.roundToInt(f);
        int i22 = i9 - iRoundToInt;
        if (i21 == 0 && i22 < 0) {
            iRoundToInt += i22;
            i22 = 0;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i23 = -i3;
        int i24 = (i5 < 0 ? i5 : 0) + i23;
        int sizeWithSpacings = i22 + i24;
        int iMax2 = 0;
        while (sizeWithSpacings < 0 && i21 > 0) {
            i21--;
            int i25 = i23;
            LazyListMeasuredItem andMeasure = lazyListMeasuredItemProvider.getAndMeasure(i21);
            arrayDeque.add(i20, andMeasure);
            iMax2 = Math.max(iMax2, andMeasure.getCrossAxisSize());
            sizeWithSpacings += andMeasure.getSizeWithSpacings();
            i23 = i25;
            i20 = 0;
        }
        int i26 = i23;
        if (sizeWithSpacings < i24) {
            iRoundToInt += sizeWithSpacings;
            sizeWithSpacings = i24;
        }
        int i27 = sizeWithSpacings - i24;
        int i28 = i2 + i4;
        int i29 = i21;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i28, 0);
        int i30 = i29;
        int i31 = iMax2;
        int sizeWithSpacings2 = -i27;
        int i32 = 0;
        boolean z5 = false;
        while (i32 < arrayDeque.size()) {
            if (sizeWithSpacings2 >= iCoerceAtLeast) {
                arrayDeque.remove(i32);
                z5 = true;
            } else {
                i30++;
                sizeWithSpacings2 += ((LazyListMeasuredItem) arrayDeque.get(i32)).getSizeWithSpacings();
                i32++;
            }
        }
        int sizeWithSpacings3 = i27;
        int sizeWithSpacings4 = sizeWithSpacings2;
        boolean z6 = z5;
        int i33 = i30;
        int iMax3 = i31;
        while (i33 < i && (sizeWithSpacings4 < iCoerceAtLeast || sizeWithSpacings4 <= 0 || arrayDeque.isEmpty())) {
            int i34 = iCoerceAtLeast;
            LazyListMeasuredItem andMeasure2 = lazyListMeasuredItemProvider.getAndMeasure(i33);
            sizeWithSpacings4 += andMeasure2.getSizeWithSpacings();
            if (sizeWithSpacings4 <= i24) {
                i18 = i24;
                if (i33 != i - 1) {
                    i19 = i33 + 1;
                    sizeWithSpacings3 -= andMeasure2.getSizeWithSpacings();
                    z6 = true;
                }
                i33++;
                iCoerceAtLeast = i34;
                i29 = i19;
                i24 = i18;
            } else {
                i18 = i24;
            }
            iMax3 = Math.max(iMax3, andMeasure2.getCrossAxisSize());
            arrayDeque.add(andMeasure2);
            i19 = i29;
            i33++;
            iCoerceAtLeast = i34;
            i29 = i19;
            i24 = i18;
        }
        if (sizeWithSpacings4 < i2) {
            int i35 = i2 - sizeWithSpacings4;
            sizeWithSpacings3 -= i35;
            int i36 = sizeWithSpacings4 + i35;
            iMax = iMax3;
            i14 = i29;
            while (sizeWithSpacings3 < i3 && i14 > 0) {
                i14--;
                int i37 = i28;
                LazyListMeasuredItem andMeasure3 = lazyListMeasuredItemProvider.getAndMeasure(i14);
                arrayDeque.add(0, andMeasure3);
                iMax = Math.max(iMax, andMeasure3.getCrossAxisSize());
                sizeWithSpacings3 += andMeasure3.getSizeWithSpacings();
                i28 = i37;
                i33 = i33;
            }
            i10 = i28;
            i11 = i33;
            i12 = i35 + iRoundToInt;
            if (sizeWithSpacings3 < 0) {
                i12 += sizeWithSpacings3;
                i13 = i36 + sizeWithSpacings3;
                sizeWithSpacings3 = 0;
            } else {
                i13 = i36;
            }
        } else {
            i10 = i28;
            i11 = i33;
            iMax = iMax3;
            i12 = iRoundToInt;
            i13 = sizeWithSpacings4;
            i14 = i29;
        }
        float f2 = (MathKt.getSign(MathKt.roundToInt(f)) != MathKt.getSign(i12) || Math.abs(MathKt.roundToInt(f)) < Math.abs(i12)) ? f : i12;
        float f3 = f - f2;
        float f4 = (!z4 || i12 <= iRoundToInt || f3 > 0.0f) ? 0.0f : (i12 - iRoundToInt) + f3;
        if (!(sizeWithSpacings3 >= 0)) {
            throw new IllegalArgumentException("negative currentFirstItemScrollOffset".toString());
        }
        int i38 = -sizeWithSpacings3;
        LazyListMeasuredItem lazyListMeasuredItem2 = (LazyListMeasuredItem) arrayDeque.first();
        if (i3 > 0 || i5 < 0) {
            int size = arrayDeque.size();
            LazyListMeasuredItem lazyListMeasuredItem3 = lazyListMeasuredItem2;
            int i39 = sizeWithSpacings3;
            int i40 = 0;
            while (i40 < size) {
                int i41 = size;
                int sizeWithSpacings5 = ((LazyListMeasuredItem) arrayDeque.get(i40)).getSizeWithSpacings();
                if (i39 == 0 || sizeWithSpacings5 > i39) {
                    break;
                }
                i15 = iMax;
                if (i40 == CollectionsKt.getLastIndex(arrayDeque)) {
                    break;
                }
                i39 -= sizeWithSpacings5;
                i40++;
                lazyListMeasuredItem3 = (LazyListMeasuredItem) arrayDeque.get(i40);
                iMax = i15;
                size = i41;
            }
            i15 = iMax;
            lazyListMeasuredItem = lazyListMeasuredItem3;
            i16 = i8;
            list3 = list2;
            i17 = i39;
        } else {
            list3 = list2;
            i15 = iMax;
            i17 = sizeWithSpacings3;
            i16 = i8;
            lazyListMeasuredItem = lazyListMeasuredItem2;
        }
        List<LazyListMeasuredItem> listCreateItemsBeforeList = createItemsBeforeList(i14, lazyListMeasuredItemProvider, i16, list3);
        int iMax4 = i15;
        int i42 = 0;
        for (int size2 = listCreateItemsBeforeList.size(); i42 < size2; size2 = size2) {
            iMax4 = Math.max(iMax4, listCreateItemsBeforeList.get(i42).getCrossAxisSize());
            i42++;
        }
        ArrayDeque arrayDeque2 = arrayDeque;
        int i43 = iMax4;
        LazyListMeasuredItem lazyListMeasuredItem4 = lazyListMeasuredItem;
        int i44 = i10;
        float f5 = f2;
        List<LazyListMeasuredItem> listCreateItemsAfterList = createItemsAfterList(arrayDeque2, lazyListMeasuredItemProvider, i, i8, list2, f2, z4, lazyListLayoutInfo);
        int size3 = listCreateItemsAfterList.size();
        int iMax5 = i43;
        for (int i45 = 0; i45 < size3; i45++) {
            iMax5 = Math.max(iMax5, listCreateItemsAfterList.get(i45).getCrossAxisSize());
        }
        boolean z7 = Intrinsics.areEqual(lazyListMeasuredItem4, arrayDeque.first()) && listCreateItemsBeforeList.isEmpty() && listCreateItemsAfterList.isEmpty();
        int iM5705constrainWidthK40F9xA = ConstraintsKt.m5705constrainWidthK40F9xA(j, z ? iMax5 : i13);
        if (z) {
            iMax5 = i13;
        }
        int iM5704constrainHeightK40F9xA = ConstraintsKt.m5704constrainHeightK40F9xA(j, iMax5);
        int i46 = i13;
        int i47 = i11;
        final List<LazyListMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(arrayDeque2, listCreateItemsBeforeList, listCreateItemsAfterList, iM5705constrainWidthK40F9xA, iM5704constrainHeightK40F9xA, i46, i2, i38, z, vertical, horizontal, z2, density);
        lazyListItemAnimator.onMeasured((int) f5, iM5705constrainWidthK40F9xA, iM5704constrainHeightK40F9xA, listCalculateItemsOffsets, lazyListMeasuredItemProvider, z, z4, z3, coroutineScope);
        final LazyListMeasuredItem lazyListMeasuredItemFindOrComposeLazyListHeader = list.isEmpty() ^ true ? LazyListHeadersKt.findOrComposeLazyListHeader(listCalculateItemsOffsets, lazyListMeasuredItemProvider, list, i3, iM5705constrainWidthK40F9xA, iM5704constrainHeightK40F9xA) : null;
        boolean z8 = i47 < i || i46 > i2;
        MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(iM5705constrainWidthK40F9xA), Integer.valueOf(iM5704constrainHeightK40F9xA), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$7
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
                List<LazyListMeasuredItem> list5 = listCalculateItemsOffsets;
                LazyListMeasuredItem lazyListMeasuredItem5 = lazyListMeasuredItemFindOrComposeLazyListHeader;
                boolean z9 = z4;
                int size4 = list5.size();
                for (int i48 = 0; i48 < size4; i48++) {
                    LazyListMeasuredItem lazyListMeasuredItem6 = list5.get(i48);
                    if (lazyListMeasuredItem6 != lazyListMeasuredItem5) {
                        lazyListMeasuredItem6.place(placementScope, z9);
                    }
                }
                LazyListMeasuredItem lazyListMeasuredItem7 = lazyListMeasuredItemFindOrComposeLazyListHeader;
                if (lazyListMeasuredItem7 != null) {
                    lazyListMeasuredItem7.place(placementScope, z4);
                }
                ObservableScopeInvalidator.m733attachToScopeimpl(mutableState);
            }
        });
        if (z7) {
            list4 = listCalculateItemsOffsets;
        } else {
            ArrayList arrayList = new ArrayList(listCalculateItemsOffsets.size());
            int size4 = listCalculateItemsOffsets.size();
            for (int i48 = 0; i48 < size4; i48++) {
                LazyListMeasuredItem lazyListMeasuredItem5 = listCalculateItemsOffsets.get(i48);
                LazyListMeasuredItem lazyListMeasuredItem6 = lazyListMeasuredItem5;
                if ((lazyListMeasuredItem6.getIndex() >= ((LazyListMeasuredItem) arrayDeque.first()).getIndex() && lazyListMeasuredItem6.getIndex() <= ((LazyListMeasuredItem) arrayDeque.last()).getIndex()) || lazyListMeasuredItem6 == lazyListMeasuredItemFindOrComposeLazyListHeader) {
                    arrayList.add(lazyListMeasuredItem5);
                }
            }
            list4 = arrayList;
        }
        return new LazyListMeasureResult(lazyListMeasuredItem4, i17, z8, f5, measureResultInvoke, f4, z6, list4, i26, i44, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i, int i2, List<Integer> list2, float f, boolean z, LazyListLayoutInfo lazyListLayoutInfo) {
        LazyListItemInfo lazyListItemInfo;
        LazyListMeasuredItem lazyListMeasuredItem;
        LazyListMeasuredItem lazyListMeasuredItem2;
        int sizeWithSpacings;
        LazyListMeasuredItem lazyListMeasuredItem3;
        int index;
        int iMin;
        LazyListMeasuredItem lazyListMeasuredItem4;
        LazyListMeasuredItem lazyListMeasuredItem5;
        int i3 = i - 1;
        int iMin2 = Math.min(((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + i2, i3);
        int index2 = ((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + 1;
        ArrayList arrayList = null;
        if (index2 <= iMin2) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(index2));
                if (index2 == iMin2) {
                    break;
                }
                index2++;
            }
        }
        if (z && lazyListLayoutInfo != null && (!lazyListLayoutInfo.getVisibleItemsInfo().isEmpty())) {
            List<LazyListItemInfo> visibleItemsInfo = lazyListLayoutInfo.getVisibleItemsInfo();
            for (int size = visibleItemsInfo.size() - 1; -1 < size; size--) {
                if (visibleItemsInfo.get(size).getIndex() > iMin2 && (size == 0 || visibleItemsInfo.get(size - 1).getIndex() <= iMin2)) {
                    lazyListItemInfo = visibleItemsInfo.get(size);
                    break;
                }
            }
            lazyListItemInfo = null;
            LazyListItemInfo lazyListItemInfo2 = (LazyListItemInfo) CollectionsKt.last((List) lazyListLayoutInfo.getVisibleItemsInfo());
            if (lazyListItemInfo != null && (index = lazyListItemInfo.getIndex()) <= (iMin = Math.min(lazyListItemInfo2.getIndex(), i3))) {
                while (true) {
                    if (arrayList != null) {
                        int size2 = arrayList.size();
                        int i4 = 0;
                        while (true) {
                            if (i4 >= size2) {
                                lazyListMeasuredItem5 = null;
                                break;
                            }
                            lazyListMeasuredItem5 = arrayList.get(i4);
                            if (lazyListMeasuredItem5.getIndex() == index) {
                                break;
                            }
                            i4++;
                        }
                        lazyListMeasuredItem4 = lazyListMeasuredItem5;
                    } else {
                        lazyListMeasuredItem4 = null;
                    }
                    if (lazyListMeasuredItem4 == null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(index));
                    }
                    if (index == iMin) {
                        break;
                    }
                    index++;
                }
            }
            float viewportEndOffset = ((lazyListLayoutInfo.getViewportEndOffset() - lazyListItemInfo2.getOffset()) - lazyListItemInfo2.getSize()) - f;
            if (viewportEndOffset > 0.0f) {
                int index3 = lazyListItemInfo2.getIndex() + 1;
                int i5 = 0;
                while (index3 < i && i5 < viewportEndOffset) {
                    if (index3 <= iMin2) {
                        int size3 = list.size();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size3) {
                                lazyListMeasuredItem3 = null;
                                break;
                            }
                            lazyListMeasuredItem3 = list.get(i6);
                            if (lazyListMeasuredItem3.getIndex() == index3) {
                                break;
                            }
                            i6++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem3;
                    } else if (arrayList != null) {
                        int size4 = arrayList.size();
                        int i7 = 0;
                        while (true) {
                            if (i7 >= size4) {
                                lazyListMeasuredItem2 = null;
                                break;
                            }
                            lazyListMeasuredItem2 = arrayList.get(i7);
                            if (lazyListMeasuredItem2.getIndex() == index3) {
                                break;
                            }
                            i7++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem2;
                    } else {
                        lazyListMeasuredItem = null;
                    }
                    if (lazyListMeasuredItem != null) {
                        index3++;
                        sizeWithSpacings = lazyListMeasuredItem.getSizeWithSpacings();
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(index3));
                        index3++;
                        sizeWithSpacings = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getSizeWithSpacings();
                    }
                    i5 += sizeWithSpacings;
                }
            }
        }
        if (arrayList != null && ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex() > iMin2) {
            iMin2 = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex();
        }
        int size5 = list2.size();
        for (int i8 = 0; i8 < size5; i8++) {
            int iIntValue = list2.get(i8).intValue();
            if (iIntValue > iMin2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(iIntValue));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int i, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, List<Integer> list) {
        int iMax = Math.max(0, i - i2);
        int i3 = i - 1;
        ArrayList arrayList = null;
        if (iMax <= i3) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(i3));
                if (i3 == iMax) {
                    break;
                }
                i3--;
            }
        }
        int size = list.size() - 1;
        if (size >= 0) {
            while (true) {
                int i4 = size - 1;
                int iIntValue = list.get(size).intValue();
                if (iIntValue < iMax) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(lazyListMeasuredItemProvider.getAndMeasure(iIntValue));
                }
                if (i4 < 0) {
                    break;
                }
                size = i4;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3) {
            if (!(i5 == 0)) {
                throw new IllegalStateException("non-zero itemsScrollOffset".toString());
            }
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        if (z3) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("no extra items".toString());
            }
            int size = list.size();
            int[] iArr = new int[size];
            for (int i7 = 0; i7 < size; i7++) {
                iArr[i7] = list.get(calculateItemsOffsets$reverseAware(i7, z2, size)).getSize();
            }
            int[] iArr2 = new int[size];
            for (int i8 = 0; i8 < size; i8++) {
                iArr2[i8] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement when isVertical == true".toString());
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement when isVertical == false".toString());
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
                    int size2 = iArr2[first];
                    LazyListMeasuredItem lazyListMeasuredItem = list.get(calculateItemsOffsets$reverseAware(first, z2, size));
                    if (z2) {
                        size2 = (i6 - size2) - lazyListMeasuredItem.getSize();
                    }
                    lazyListMeasuredItem.position(size2, i, i2);
                    arrayList.add(lazyListMeasuredItem);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size();
            int sizeWithSpacings = i5;
            for (int i9 = 0; i9 < size3; i9++) {
                LazyListMeasuredItem lazyListMeasuredItem2 = list2.get(i9);
                sizeWithSpacings -= lazyListMeasuredItem2.getSizeWithSpacings();
                lazyListMeasuredItem2.position(sizeWithSpacings, i, i2);
                arrayList.add(lazyListMeasuredItem2);
            }
            int size4 = list.size();
            int sizeWithSpacings2 = i5;
            for (int i10 = 0; i10 < size4; i10++) {
                LazyListMeasuredItem lazyListMeasuredItem3 = list.get(i10);
                lazyListMeasuredItem3.position(sizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem3);
                sizeWithSpacings2 += lazyListMeasuredItem3.getSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i11 = 0; i11 < size5; i11++) {
                LazyListMeasuredItem lazyListMeasuredItem4 = list3.get(i11);
                lazyListMeasuredItem4.position(sizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem4);
                sizeWithSpacings2 += lazyListMeasuredItem4.getSizeWithSpacings();
            }
        }
        return arrayList;
    }
}
