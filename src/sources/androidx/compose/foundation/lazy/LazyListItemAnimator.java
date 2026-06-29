package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode;
import androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: LazyListItemAnimator.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0004J\"\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\tH\u0002JT\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020\u001cJ\u0010\u0010-\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u000bj\b\u0012\u0004\u0012\u00020\u0001`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\u00020\u0014*\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006/"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemAnimator;", "", "()V", "firstVisibleIndex", "", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "keyToItemInfoMap", "", "Landroidx/compose/foundation/lazy/LazyListItemAnimator$ItemInfo;", "movingAwayKeys", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "movingAwayToEndBound", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "movingAwayToStartBound", "movingInFromEndBound", "movingInFromStartBound", "hasAnimations", "", "getHasAnimations", "(Landroidx/compose/foundation/lazy/LazyListMeasuredItem;)Z", "getAnimation", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", DatabaseFileArchive.COLUMN_KEY, "placeableIndex", "initializeAnimation", "", "item", "mainAxisOffset", "itemInfo", "onMeasured", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "isVertical", "isLookingAhead", "hasLookaheadOccurred", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "reset", "startPlacementAnimationsIfNeeded", "ItemInfo", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListItemAnimator {
    public static final int $stable = 8;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final Map<Object, ItemInfo> keyToItemInfoMap = new LinkedHashMap();
    private final LinkedHashSet<Object> movingAwayKeys = new LinkedHashSet<>();
    private final List<LazyListMeasuredItem> movingInFromStartBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingInFromEndBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingAwayToStartBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingAwayToEndBound = new ArrayList();

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyListMeasuredItem> positionedItems, LazyListMeasuredItemProvider itemProvider, boolean isVertical, boolean isLookingAhead, boolean hasLookaheadOccurred, CoroutineScope coroutineScope) {
        boolean z;
        long jIntOffset;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap;
        int offset;
        boolean z2;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap2;
        int i;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap3;
        List<LazyListMeasuredItem> list = positionedItems;
        CoroutineScope coroutineScope2 = coroutineScope;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap4 = this.keyIndexMap;
        final LazyLayoutKeyIndexMap keyIndexMap = itemProvider.getKeyIndexMap();
        this.keyIndexMap = keyIndexMap;
        int size = positionedItems.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                z = false;
                break;
            } else {
                if (getHasAnimations(list.get(i2))) {
                    z = true;
                    break;
                }
                i2++;
            }
        }
        if (!z && this.keyToItemInfoMap.isEmpty()) {
            reset();
            return;
        }
        int i3 = this.firstVisibleIndex;
        LazyListMeasuredItem lazyListMeasuredItem = (LazyListMeasuredItem) CollectionsKt.firstOrNull((List) positionedItems);
        this.firstVisibleIndex = lazyListMeasuredItem != null ? lazyListMeasuredItem.getIndex() : 0;
        int i4 = isVertical ? layoutHeight : layoutWidth;
        if (isVertical) {
            jIntOffset = IntOffsetKt.IntOffset(0, consumedScroll);
        } else {
            jIntOffset = IntOffsetKt.IntOffset(consumedScroll, 0);
        }
        boolean z3 = isLookingAhead || !hasLookaheadOccurred;
        this.movingAwayKeys.addAll(this.keyToItemInfoMap.keySet());
        int size2 = positionedItems.size();
        int i5 = 0;
        while (i5 < size2) {
            LazyListMeasuredItem lazyListMeasuredItem2 = list.get(i5);
            int i6 = size2;
            this.movingAwayKeys.remove(lazyListMeasuredItem2.getKey());
            if (getHasAnimations(lazyListMeasuredItem2)) {
                ItemInfo itemInfo = this.keyToItemInfoMap.get(lazyListMeasuredItem2.getKey());
                if (itemInfo == null) {
                    ItemInfo itemInfo2 = new ItemInfo();
                    itemInfo2.updateAnimation(lazyListMeasuredItem2, coroutineScope2);
                    this.keyToItemInfoMap.put(lazyListMeasuredItem2.getKey(), itemInfo2);
                    int index = lazyLayoutKeyIndexMap4 != null ? lazyLayoutKeyIndexMap4.getIndex(lazyListMeasuredItem2.getKey()) : -1;
                    if (lazyListMeasuredItem2.getIndex() == index || index == -1) {
                        long jM677getOffsetBjo55l4 = lazyListMeasuredItem2.m677getOffsetBjo55l4(0);
                        initializeAnimation(lazyListMeasuredItem2, lazyListMeasuredItem2.getIsVertical() ? IntOffset.m5864getYimpl(jM677getOffsetBjo55l4) : IntOffset.m5863getXimpl(jM677getOffsetBjo55l4), itemInfo2);
                        if (index == -1 && lazyLayoutKeyIndexMap4 != null) {
                            for (LazyLayoutAnimation lazyLayoutAnimation : itemInfo2.getAnimations()) {
                                if (lazyLayoutAnimation != null) {
                                    lazyLayoutAnimation.animateAppearance();
                                }
                            }
                        }
                    } else if (index < i3) {
                        this.movingInFromStartBound.add(lazyListMeasuredItem2);
                    } else {
                        this.movingInFromEndBound.add(lazyListMeasuredItem2);
                    }
                } else if (z3) {
                    itemInfo.updateAnimation(lazyListMeasuredItem2, coroutineScope2);
                    LazyLayoutAnimation[] animations = itemInfo.getAnimations();
                    int length = animations.length;
                    int i7 = 0;
                    while (i7 < length) {
                        LazyLayoutAnimation lazyLayoutAnimation2 = animations[i7];
                        if (lazyLayoutAnimation2 != null) {
                            i = length;
                            lazyLayoutKeyIndexMap3 = lazyLayoutKeyIndexMap4;
                            if (!IntOffset.m5862equalsimpl0(lazyLayoutAnimation2.getRawOffset(), LazyLayoutAnimation.INSTANCE.m719getNotInitializednOccac())) {
                                long rawOffset = lazyLayoutAnimation2.getRawOffset();
                                lazyLayoutAnimation2.m718setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5863getXimpl(rawOffset) + IntOffset.m5863getXimpl(jIntOffset), IntOffset.m5864getYimpl(rawOffset) + IntOffset.m5864getYimpl(jIntOffset)));
                            }
                        } else {
                            i = length;
                            lazyLayoutKeyIndexMap3 = lazyLayoutKeyIndexMap4;
                        }
                        i7++;
                        lazyLayoutKeyIndexMap4 = lazyLayoutKeyIndexMap3;
                        length = i;
                    }
                    lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap4;
                    startPlacementAnimationsIfNeeded(lazyListMeasuredItem2);
                }
                lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap4;
            } else {
                lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap4;
                this.keyToItemInfoMap.remove(lazyListMeasuredItem2.getKey());
            }
            i5++;
            list = positionedItems;
            size2 = i6;
            coroutineScope2 = coroutineScope;
            lazyLayoutKeyIndexMap4 = lazyLayoutKeyIndexMap2;
        }
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap5 = lazyLayoutKeyIndexMap4;
        if (!z3 || lazyLayoutKeyIndexMap5 == null) {
            lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap5;
        } else {
            List<LazyListMeasuredItem> list2 = this.movingInFromStartBound;
            if (list2.size() > 1) {
                lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap5;
                CollectionsKt.sortWith(list2, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t).getKey())));
                    }
                });
            } else {
                lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap5;
            }
            List<LazyListMeasuredItem> list3 = this.movingInFromStartBound;
            int size3 = list3.size();
            int i8 = 0;
            int i9 = 0;
            while (i9 < size3) {
                LazyListMeasuredItem lazyListMeasuredItem3 = list3.get(i9);
                int sizeWithSpacings = i8 + lazyListMeasuredItem3.getSizeWithSpacings();
                initializeAnimation$default(this, lazyListMeasuredItem3, 0 - sizeWithSpacings, null, 4, null);
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem3);
                i9++;
                i8 = sizeWithSpacings;
            }
            List<LazyListMeasuredItem> list4 = this.movingInFromEndBound;
            if (list4.size() > 1) {
                CollectionsKt.sortWith(list4, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())));
                    }
                });
            }
            List<LazyListMeasuredItem> list5 = this.movingInFromEndBound;
            int size4 = list5.size();
            int i10 = 0;
            int i11 = 0;
            while (i11 < size4) {
                LazyListMeasuredItem lazyListMeasuredItem4 = list5.get(i11);
                int sizeWithSpacings2 = i10 + lazyListMeasuredItem4.getSizeWithSpacings();
                initializeAnimation$default(this, lazyListMeasuredItem4, i4 + i10, null, 4, null);
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem4);
                i11++;
                i10 = sizeWithSpacings2;
            }
        }
        for (Object obj : this.movingAwayKeys) {
            int index2 = keyIndexMap.getIndex(obj);
            if (index2 == -1) {
                this.keyToItemInfoMap.remove(obj);
            } else {
                LazyListMeasuredItem andMeasure = itemProvider.getAndMeasure(index2);
                boolean z4 = true;
                andMeasure.setNonScrollableItem(true);
                LazyLayoutAnimation[] animations2 = ((ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, obj)).getAnimations();
                int length2 = animations2.length;
                int i12 = 0;
                while (true) {
                    if (i12 >= length2) {
                        z2 = false;
                        break;
                    }
                    LazyLayoutAnimation lazyLayoutAnimation3 = animations2[i12];
                    if (lazyLayoutAnimation3 != null && lazyLayoutAnimation3.isPlacementAnimationInProgress() == z4) {
                        z2 = true;
                        break;
                    } else {
                        i12++;
                        z4 = true;
                    }
                }
                if (!z2) {
                    if (lazyLayoutKeyIndexMap != null && index2 == lazyLayoutKeyIndexMap.getIndex(obj)) {
                        this.keyToItemInfoMap.remove(obj);
                    }
                }
                if (index2 < this.firstVisibleIndex) {
                    this.movingAwayToStartBound.add(andMeasure);
                } else {
                    this.movingAwayToEndBound.add(andMeasure);
                }
            }
        }
        List<LazyListMeasuredItem> list6 = this.movingAwayToStartBound;
        if (list6.size() > 1) {
            CollectionsKt.sortWith(list6, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())), Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t).getKey())));
                }
            });
        }
        List<LazyListMeasuredItem> list7 = this.movingAwayToStartBound;
        int size5 = list7.size();
        int sizeWithSpacings3 = 0;
        for (int i13 = 0; i13 < size5; i13++) {
            LazyListMeasuredItem lazyListMeasuredItem5 = list7.get(i13);
            sizeWithSpacings3 += lazyListMeasuredItem5.getSizeWithSpacings();
            lazyListMeasuredItem5.position(isLookingAhead ? ((LazyListMeasuredItem) CollectionsKt.first((List) positionedItems)).getOffset() - sizeWithSpacings3 : 0 - sizeWithSpacings3, layoutWidth, layoutHeight);
            if (z3) {
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem5);
            }
        }
        List<LazyListMeasuredItem> list8 = this.movingAwayToEndBound;
        if (list8.size() > 1) {
            CollectionsKt.sortWith(list8, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t).getKey())), Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())));
                }
            });
        }
        List<LazyListMeasuredItem> list9 = this.movingAwayToEndBound;
        int size6 = list9.size();
        int sizeWithSpacings4 = 0;
        for (int i14 = 0; i14 < size6; i14++) {
            LazyListMeasuredItem lazyListMeasuredItem6 = list9.get(i14);
            if (isLookingAhead) {
                LazyListMeasuredItem lazyListMeasuredItem7 = (LazyListMeasuredItem) CollectionsKt.last((List) positionedItems);
                offset = lazyListMeasuredItem7.getOffset() + lazyListMeasuredItem7.getSizeWithSpacings() + sizeWithSpacings4;
            } else {
                offset = i4 + sizeWithSpacings4;
            }
            sizeWithSpacings4 += lazyListMeasuredItem6.getSizeWithSpacings();
            lazyListMeasuredItem6.position(offset, layoutWidth, layoutHeight);
            if (z3) {
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem6);
            }
        }
        List<LazyListMeasuredItem> list10 = this.movingAwayToStartBound;
        CollectionsKt.reverse(list10);
        Unit unit = Unit.INSTANCE;
        positionedItems.addAll(0, list10);
        positionedItems.addAll(this.movingAwayToEndBound);
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    public final void reset() {
        this.keyToItemInfoMap.clear();
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        this.firstVisibleIndex = -1;
    }

    static /* synthetic */ void initializeAnimation$default(LazyListItemAnimator lazyListItemAnimator, LazyListMeasuredItem lazyListMeasuredItem, int i, ItemInfo itemInfo, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            itemInfo = (ItemInfo) MapsKt.getValue(lazyListItemAnimator.keyToItemInfoMap, lazyListMeasuredItem.getKey());
        }
        lazyListItemAnimator.initializeAnimation(lazyListMeasuredItem, i, itemInfo);
    }

    private final void initializeAnimation(LazyListMeasuredItem item, int mainAxisOffset, ItemInfo itemInfo) {
        long jM5859copyiSbpLlY$default;
        int i = 0;
        long jM677getOffsetBjo55l4 = item.m677getOffsetBjo55l4(0);
        if (item.getIsVertical()) {
            jM5859copyiSbpLlY$default = IntOffset.m5859copyiSbpLlY$default(jM677getOffsetBjo55l4, 0, mainAxisOffset, 1, null);
        } else {
            jM5859copyiSbpLlY$default = IntOffset.m5859copyiSbpLlY$default(jM677getOffsetBjo55l4, mainAxisOffset, 0, 2, null);
        }
        LazyLayoutAnimation[] animations = itemInfo.getAnimations();
        int length = animations.length;
        int i2 = 0;
        while (i < length) {
            LazyLayoutAnimation lazyLayoutAnimation = animations[i];
            int i3 = i2 + 1;
            if (lazyLayoutAnimation != null) {
                long jM677getOffsetBjo55l42 = item.m677getOffsetBjo55l4(i2);
                long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m5863getXimpl(jM677getOffsetBjo55l42) - IntOffset.m5863getXimpl(jM677getOffsetBjo55l4), IntOffset.m5864getYimpl(jM677getOffsetBjo55l42) - IntOffset.m5864getYimpl(jM677getOffsetBjo55l4));
                lazyLayoutAnimation.m718setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5863getXimpl(jM5859copyiSbpLlY$default) + IntOffset.m5863getXimpl(jIntOffset), IntOffset.m5864getYimpl(jM5859copyiSbpLlY$default) + IntOffset.m5864getYimpl(jIntOffset)));
            }
            i++;
            i2 = i3;
        }
    }

    private final void startPlacementAnimationsIfNeeded(LazyListMeasuredItem item) {
        LazyLayoutAnimation[] animations = ((ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, item.getKey())).getAnimations();
        int length = animations.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            LazyLayoutAnimation lazyLayoutAnimation = animations[i];
            int i3 = i2 + 1;
            if (lazyLayoutAnimation != null) {
                long jM677getOffsetBjo55l4 = item.m677getOffsetBjo55l4(i2);
                long rawOffset = lazyLayoutAnimation.getRawOffset();
                if (!IntOffset.m5862equalsimpl0(rawOffset, LazyLayoutAnimation.INSTANCE.m719getNotInitializednOccac()) && !IntOffset.m5862equalsimpl0(rawOffset, jM677getOffsetBjo55l4)) {
                    lazyLayoutAnimation.m713animatePlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5863getXimpl(jM677getOffsetBjo55l4) - IntOffset.m5863getXimpl(rawOffset), IntOffset.m5864getYimpl(jM677getOffsetBjo55l4) - IntOffset.m5864getYimpl(rawOffset)));
                }
                lazyLayoutAnimation.m718setRawOffsetgyyYBs(jM677getOffsetBjo55l4);
            }
            i++;
            i2 = i3;
        }
    }

    public final LazyLayoutAnimation getAnimation(Object key, int placeableIndex) {
        LazyLayoutAnimation[] animations;
        ItemInfo itemInfo = this.keyToItemInfoMap.get(key);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[placeableIndex];
    }

    private final boolean getHasAnimations(LazyListMeasuredItem lazyListMeasuredItem) {
        int placeablesCount = lazyListMeasuredItem.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            if (LazyListItemAnimatorKt.getSpecs(lazyListMeasuredItem.getParentData(i)) != null) {
                return true;
            }
        }
        return false;
    }

    /* compiled from: LazyListItemAnimator.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR0\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemAnimator$ItemInfo;", "", "()V", "<set-?>", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "animations", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "updateAnimation", "", "positionedItem", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class ItemInfo {
        private LazyLayoutAnimation[] animations = LazyListItemAnimatorKt.EmptyArray;

        public final LazyLayoutAnimation[] getAnimations() {
            return this.animations;
        }

        public final void updateAnimation(LazyListMeasuredItem positionedItem, CoroutineScope coroutineScope) {
            int length = this.animations.length;
            for (int placeablesCount = positionedItem.getPlaceablesCount(); placeablesCount < length; placeablesCount++) {
                LazyLayoutAnimation lazyLayoutAnimation = this.animations[placeablesCount];
                if (lazyLayoutAnimation != null) {
                    lazyLayoutAnimation.stopAnimations();
                }
            }
            if (this.animations.length != positionedItem.getPlaceablesCount()) {
                Object[] objArrCopyOf = Arrays.copyOf(this.animations, positionedItem.getPlaceablesCount());
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
                this.animations = (LazyLayoutAnimation[]) objArrCopyOf;
            }
            int placeablesCount2 = positionedItem.getPlaceablesCount();
            for (int i = 0; i < placeablesCount2; i++) {
                LazyLayoutAnimationSpecsNode specs = LazyListItemAnimatorKt.getSpecs(positionedItem.getParentData(i));
                if (specs == null) {
                    LazyLayoutAnimation lazyLayoutAnimation2 = this.animations[i];
                    if (lazyLayoutAnimation2 != null) {
                        lazyLayoutAnimation2.stopAnimations();
                    }
                    this.animations[i] = null;
                } else {
                    LazyLayoutAnimation lazyLayoutAnimation3 = this.animations[i];
                    if (lazyLayoutAnimation3 == null) {
                        lazyLayoutAnimation3 = new LazyLayoutAnimation(coroutineScope);
                        this.animations[i] = lazyLayoutAnimation3;
                    }
                    lazyLayoutAnimation3.setAppearanceSpec(specs.getAppearanceSpec());
                    lazyLayoutAnimation3.setPlacementSpec(specs.getPlacementSpec());
                }
            }
        }
    }
}
