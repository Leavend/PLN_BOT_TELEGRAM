package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: RowColumnMeasurementHelper.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012¢\u0006\u0002\u0010\u0014J2\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00132\b\u0010-\u001a\u0004\u0018\u00010&2\u0006\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020+H\u0002J(\u00102\u001a\u0002032\u0006\u00104\u001a\u00020+2\u0006\u00105\u001a\u0002032\u0006\u00102\u001a\u0002032\u0006\u00106\u001a\u000207H\u0002J0\u00108\u001a\u0002092\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020+2\u0006\u0010=\u001a\u00020+ø\u0001\u0000¢\u0006\u0004\b>\u0010?J&\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020+2\u0006\u0010/\u001a\u000200J\n\u0010\n\u001a\u00020+*\u00020\u0013J\n\u0010F\u001a\u00020+*\u00020\u0013R\u0019\u0010\b\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001b\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0018\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006G"}, d2 = {"Landroidx/compose/foundation/layout/RowColumnMeasurementHelper;", "", "orientation", "Landroidx/compose/foundation/layout/LayoutOrientation;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "arrangementSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSize", "Landroidx/compose/foundation/layout/SizeMode;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "(Landroidx/compose/foundation/layout/LayoutOrientation;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/SizeMode;Landroidx/compose/foundation/layout/CrossAxisAlignment;Ljava/util/List;[Landroidx/compose/ui/layout/Placeable;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getArrangementSpacing-D9Ej5fM", "()F", "F", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCrossAxisSize", "()Landroidx/compose/foundation/layout/SizeMode;", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getMeasurables", "()Ljava/util/List;", "getOrientation", "()Landroidx/compose/foundation/layout/LayoutOrientation;", "getPlaceables", "()[Landroidx/compose/ui/layout/Placeable;", "[Landroidx/compose/ui/layout/Placeable;", "rowColumnParentData", "Landroidx/compose/foundation/layout/RowColumnParentData;", "[Landroidx/compose/foundation/layout/RowColumnParentData;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getCrossAxisPosition", "", "placeable", "parentData", "crossAxisLayoutSize", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "beforeCrossAxisAlignmentLine", "mainAxisPositions", "", "mainAxisLayoutSize", "childrenMainAxisSize", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "measureWithoutPlacing", "Landroidx/compose/foundation/layout/RowColumnMeasureHelperResult;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "startIndex", "endIndex", "measureWithoutPlacing-_EkL_-Y", "(Landroidx/compose/ui/layout/MeasureScope;JII)Landroidx/compose/foundation/layout/RowColumnMeasureHelperResult;", "placeHelper", "", "placeableScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "measureResult", "crossAxisOffset", "mainAxisSize", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class RowColumnMeasurementHelper {
    public static final int $stable = 8;
    private final float arrangementSpacing;
    private final CrossAxisAlignment crossAxisAlignment;
    private final SizeMode crossAxisSize;
    private final Arrangement.Horizontal horizontalArrangement;
    private final List<Measurable> measurables;
    private final LayoutOrientation orientation;
    private final Placeable[] placeables;
    private final RowColumnParentData[] rowColumnParentData;
    private final Arrangement.Vertical verticalArrangement;

    public /* synthetic */ RowColumnMeasurementHelper(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, List list, Placeable[] placeableArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(layoutOrientation, horizontal, vertical, f, sizeMode, crossAxisAlignment, list, placeableArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private RowColumnMeasurementHelper(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, List<? extends Measurable> list, Placeable[] placeableArr) {
        this.orientation = layoutOrientation;
        this.horizontalArrangement = horizontal;
        this.verticalArrangement = vertical;
        this.arrangementSpacing = f;
        this.crossAxisSize = sizeMode;
        this.crossAxisAlignment = crossAxisAlignment;
        this.measurables = list;
        this.placeables = placeableArr;
        int size = list.size();
        RowColumnParentData[] rowColumnParentDataArr = new RowColumnParentData[size];
        for (int i = 0; i < size; i++) {
            rowColumnParentDataArr[i] = RowColumnImplKt.getRowColumnParentData(this.measurables.get(i));
        }
        this.rowColumnParentData = rowColumnParentDataArr;
    }

    public final LayoutOrientation getOrientation() {
        return this.orientation;
    }

    public final Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    public final Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    /* renamed from: getArrangementSpacing-D9Ej5fM, reason: not valid java name and from getter */
    public final float getArrangementSpacing() {
        return this.arrangementSpacing;
    }

    public final SizeMode getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    public final List<Measurable> getMeasurables() {
        return this.measurables;
    }

    public final Placeable[] getPlaceables() {
        return this.placeables;
    }

    public final int mainAxisSize(Placeable placeable) {
        return this.orientation == LayoutOrientation.Horizontal ? placeable.getWidth() : placeable.getHeight();
    }

    public final int crossAxisSize(Placeable placeable) {
        return this.orientation == LayoutOrientation.Horizontal ? placeable.getHeight() : placeable.getWidth();
    }

    /* renamed from: measureWithoutPlacing-_EkL_-Y, reason: not valid java name */
    public final RowColumnMeasureHelperResult m594measureWithoutPlacing_EkL_Y(MeasureScope measureScope, long constraints, int startIndex, int endIndex) throws Throwable {
        int iM5693getMinWidthimpl;
        int i;
        String str;
        String str2;
        float f;
        String str3;
        String str4;
        String str5;
        long j;
        String str6;
        String str7;
        String str8;
        int iMax;
        RowColumnMeasurementHelper rowColumnMeasurementHelper;
        int iCoerceIn;
        long j2;
        String str9;
        long j3;
        String str10;
        long j4;
        String str11;
        String str12;
        String str13;
        String str14;
        long j5;
        String str15;
        String str16;
        String str17;
        int i2;
        float f2;
        int i3;
        int i4;
        RowColumnMeasurementHelper rowColumnMeasurementHelper2;
        int iMax2;
        int iMax3;
        int iMax4;
        long j6;
        float f3;
        int i5;
        int i6;
        int i7;
        float f4;
        int i8;
        int i9;
        long j7;
        int iCoerceAtLeast;
        RowColumnMeasurementHelper rowColumnMeasurementHelper3 = this;
        int i10 = endIndex;
        long jM534constructorimpl = OrientationIndependentConstraints.m534constructorimpl(constraints, rowColumnMeasurementHelper3.orientation);
        long j8 = measureScope.mo310roundToPx0680j_4(rowColumnMeasurementHelper3.arrangementSpacing);
        int i11 = i10 - startIndex;
        int i12 = startIndex;
        float f5 = 0.0f;
        long jMainAxisSize = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        boolean z = false;
        while (true) {
            boolean z2 = true;
            if (i12 >= i10) {
                break;
            }
            Measurable measurable = rowColumnMeasurementHelper3.measurables.get(i12);
            RowColumnParentData rowColumnParentData = rowColumnMeasurementHelper3.rowColumnParentData[i12];
            float weight = RowColumnImplKt.getWeight(rowColumnParentData);
            if (weight > 0.0f) {
                f3 = f5 + weight;
                i5 = i13 + 1;
                i6 = i12;
            } else {
                int iM5691getMaxWidthimpl = Constraints.m5691getMaxWidthimpl(jM534constructorimpl);
                Placeable placeableMo4684measureBRTryo0 = rowColumnMeasurementHelper3.placeables[i12];
                if (placeableMo4684measureBRTryo0 == null) {
                    if (iM5691getMaxWidthimpl == Integer.MAX_VALUE) {
                        f4 = f5;
                        i8 = i13;
                        i9 = iM5691getMaxWidthimpl;
                        iCoerceAtLeast = Integer.MAX_VALUE;
                        j7 = 0;
                    } else {
                        f4 = f5;
                        i8 = i13;
                        i9 = iM5691getMaxWidthimpl;
                        j7 = 0;
                        iCoerceAtLeast = (int) RangesKt.coerceAtLeast(iM5691getMaxWidthimpl - jMainAxisSize, 0L);
                    }
                    j6 = jMainAxisSize;
                    f3 = f4;
                    i5 = i8;
                    i6 = i12;
                    i7 = i9;
                    placeableMo4684measureBRTryo0 = measurable.mo4684measureBRTryo0(OrientationIndependentConstraints.m547toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m536copyyUG9Ft0$default(jM534constructorimpl, 0, iCoerceAtLeast, 0, 0, 8, null), rowColumnMeasurementHelper3.orientation));
                } else {
                    j6 = jMainAxisSize;
                    f3 = f5;
                    i5 = i13;
                    i6 = i12;
                    i7 = iM5691getMaxWidthimpl;
                }
                long j9 = j6;
                int iMin = Math.min((int) j8, (int) RangesKt.coerceAtLeast((i7 - j9) - rowColumnMeasurementHelper3.mainAxisSize(placeableMo4684measureBRTryo0), 0L));
                jMainAxisSize = rowColumnMeasurementHelper3.mainAxisSize(placeableMo4684measureBRTryo0) + iMin + j9;
                int iMax5 = Math.max(i15, rowColumnMeasurementHelper3.crossAxisSize(placeableMo4684measureBRTryo0));
                if (!z && !RowColumnImplKt.isRelative(rowColumnParentData)) {
                    z2 = false;
                }
                rowColumnMeasurementHelper3.placeables[i6] = placeableMo4684measureBRTryo0;
                i14 = iMin;
                i15 = iMax5;
                z = z2;
            }
            i12 = i6 + 1;
            f5 = f3;
            i13 = i5;
        }
        long j10 = jMainAxisSize;
        float f6 = f5;
        int i16 = i13;
        if (i16 == 0) {
            j2 = j10 - i14;
            rowColumnMeasurementHelper = rowColumnMeasurementHelper3;
            i = i11;
            iMax = i15;
            iCoerceIn = 0;
        } else {
            float f7 = f6;
            if (f7 > 0.0f && Constraints.m5691getMaxWidthimpl(jM534constructorimpl) != Integer.MAX_VALUE) {
                iM5693getMinWidthimpl = Constraints.m5691getMaxWidthimpl(jM534constructorimpl);
            } else {
                iM5693getMinWidthimpl = Constraints.m5693getMinWidthimpl(jM534constructorimpl);
            }
            long j11 = (i16 - 1) * j8;
            long jCoerceAtLeast = RangesKt.coerceAtLeast((iM5693getMinWidthimpl - j10) - j11, 0L);
            float f8 = f7 > 0.0f ? jCoerceAtLeast / f7 : 0.0f;
            int i17 = startIndex;
            long jRoundToInt = jCoerceAtLeast;
            while (true) {
                i = i11;
                str = "weightedSize ";
                str2 = "weightUnitSpace ";
                f = f7;
                str3 = "fixedSpace ";
                str4 = "totalWeight ";
                str5 = "weightChildrenCount ";
                j = jCoerceAtLeast;
                str6 = "arrangementSpacingPx ";
                str7 = "targetSpace ";
                str8 = "remainingToTarget ";
                if (i17 >= i10) {
                    break;
                }
                float weight2 = RowColumnImplKt.getWeight(rowColumnMeasurementHelper3.rowColumnParentData[i17]);
                float f9 = f8 * weight2;
                try {
                    jRoundToInt -= MathKt.roundToInt(f9);
                    i17++;
                    rowColumnMeasurementHelper3 = this;
                    i11 = i;
                    i10 = endIndex;
                    jCoerceAtLeast = j;
                    f7 = f;
                } catch (IllegalArgumentException e) {
                    StringBuilder sb = new StringBuilder("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/297974033 mainAxisMax ");
                    sb.append(Constraints.m5691getMaxWidthimpl(jM534constructorimpl)).append("mainAxisMin ").append(Constraints.m5693getMinWidthimpl(jM534constructorimpl)).append("targetSpace ").append(iM5693getMinWidthimpl).append("arrangementSpacingPx ").append(j8).append("weightChildrenCount ").append(i16).append("fixedSpace ").append(j10).append("arrangementSpacingTotal ").append(j11).append(str8).append(j).append(str4).append(f).append(str2).append(f8).append("itemWeight ").append(weight2).append(str);
                    sb.append(f9);
                    throw new IllegalArgumentException(sb.toString()).initCause(e);
                }
            }
            long j12 = j11;
            long j13 = j;
            long j14 = j10;
            long j15 = j8;
            int i18 = endIndex;
            iMax = i15;
            int iMainAxisSize = 0;
            String str18 = "arrangementSpacingTotal ";
            int i19 = startIndex;
            while (i19 < i18) {
                String str19 = str3;
                if (this.placeables[i19] == null) {
                    Measurable measurable2 = this.measurables.get(i19);
                    int i20 = i16;
                    RowColumnParentData rowColumnParentData2 = this.rowColumnParentData[i19];
                    String str20 = str5;
                    float weight3 = RowColumnImplKt.getWeight(rowColumnParentData2);
                    if (!(weight3 > 0.0f)) {
                        throw new IllegalStateException("All weights <= 0 should have placeables".toString());
                    }
                    long j16 = j15;
                    int sign = MathKt.getSign(jRoundToInt);
                    String str21 = str6;
                    String str22 = str7;
                    jRoundToInt -= sign;
                    float f10 = f8 * weight3;
                    int iMax6 = Math.max(0, MathKt.roundToInt(f10) + sign);
                    try {
                        if (!RowColumnImplKt.getFill(rowColumnParentData2) || iMax6 == Integer.MAX_VALUE) {
                            i3 = sign;
                            i4 = 0;
                        } else {
                            i4 = iMax6;
                            i3 = sign;
                        }
                        try {
                            f2 = f10;
                            try {
                                Placeable placeableMo4684measureBRTryo02 = measurable2.mo4684measureBRTryo0(OrientationIndependentConstraints.m547toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m532constructorimpl(i4, iMax6, 0, Constraints.m5690getMaxHeightimpl(jM534constructorimpl)), this.orientation));
                                iMainAxisSize += mainAxisSize(placeableMo4684measureBRTryo02);
                                iMax = Math.max(iMax, crossAxisSize(placeableMo4684measureBRTryo02));
                                boolean z3 = z || RowColumnImplKt.isRelative(rowColumnParentData2);
                                this.placeables[i19] = placeableMo4684measureBRTryo02;
                                z = z3;
                                str9 = str21;
                                str13 = str;
                                str14 = str4;
                                j4 = j14;
                                str10 = str22;
                                j15 = j16;
                                str15 = str18;
                                str12 = str19;
                                str11 = str20;
                                str16 = str2;
                                str17 = str8;
                                j3 = j13;
                                i2 = i20;
                                j5 = j12;
                            } catch (IllegalArgumentException e2) {
                                e = e2;
                                StringBuilder sb2 = new StringBuilder("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax ");
                                sb2.append(Constraints.m5691getMaxWidthimpl(jM534constructorimpl)).append("mainAxisMin ").append(Constraints.m5693getMinWidthimpl(jM534constructorimpl)).append(str22).append(iM5693getMinWidthimpl).append(str21).append(j16).append(str20).append(i20).append(str19).append(j14).append(str18).append(j12).append(str8).append(j13).append(str4).append(f).append(str2).append(f8).append("weight ").append(weight3).append(str);
                                sb2.append(f2).append("remainderUnit ").append(i3).append("childMainAxisSize ").append(iMax6);
                                throw new IllegalArgumentException(sb2.toString()).initCause(e);
                            }
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                            f2 = f10;
                        }
                    } catch (IllegalArgumentException e4) {
                        e = e4;
                        f2 = f10;
                        i3 = sign;
                    }
                } else {
                    str9 = str6;
                    j3 = j13;
                    int i21 = i16;
                    str10 = str7;
                    j4 = j14;
                    str11 = str5;
                    str12 = str19;
                    str13 = str;
                    str14 = str4;
                    j5 = j12;
                    str15 = str18;
                    str16 = str2;
                    str17 = str8;
                    i2 = i21;
                }
                i19++;
                i18 = endIndex;
                j12 = j5;
                j13 = j3;
                long j17 = j4;
                str7 = str10;
                str6 = str9;
                str3 = str12;
                i16 = i2;
                str8 = str17;
                str2 = str16;
                str18 = str15;
                str4 = str14;
                str = str13;
                str5 = str11;
                j14 = j17;
            }
            rowColumnMeasurementHelper = this;
            long j18 = j14;
            iCoerceIn = (int) RangesKt.coerceIn(iMainAxisSize + j12, 0L, Constraints.m5691getMaxWidthimpl(jM534constructorimpl) - j18);
            j2 = j18;
        }
        if (z) {
            rowColumnMeasurementHelper2 = rowColumnMeasurementHelper;
            iMax2 = 0;
            iMax3 = 0;
            for (int i22 = startIndex; i22 < endIndex; i22++) {
                Placeable placeable = rowColumnMeasurementHelper2.placeables[i22];
                Intrinsics.checkNotNull(placeable);
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(rowColumnMeasurementHelper2.rowColumnParentData[i22]);
                Integer numCalculateAlignmentLinePosition$foundation_layout_release = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout_release(placeable) : null;
                if (numCalculateAlignmentLinePosition$foundation_layout_release != null) {
                    Integer num = numCalculateAlignmentLinePosition$foundation_layout_release;
                    int iIntValue = num.intValue();
                    if (iIntValue == Integer.MIN_VALUE) {
                        iIntValue = 0;
                    }
                    iMax2 = Math.max(iMax2, iIntValue);
                    int iCrossAxisSize = rowColumnMeasurementHelper2.crossAxisSize(placeable);
                    int iIntValue2 = num.intValue();
                    if (iIntValue2 == Integer.MIN_VALUE) {
                        iIntValue2 = rowColumnMeasurementHelper2.crossAxisSize(placeable);
                    }
                    iMax3 = Math.max(iMax3, iCrossAxisSize - iIntValue2);
                }
            }
        } else {
            rowColumnMeasurementHelper2 = rowColumnMeasurementHelper;
            iMax2 = 0;
            iMax3 = 0;
        }
        int iMax7 = Math.max((int) RangesKt.coerceAtLeast(j2 + iCoerceIn, 0L), Constraints.m5693getMinWidthimpl(jM534constructorimpl));
        if (Constraints.m5690getMaxHeightimpl(jM534constructorimpl) == Integer.MAX_VALUE || rowColumnMeasurementHelper2.crossAxisSize != SizeMode.Expand) {
            iMax4 = Math.max(iMax, Math.max(Constraints.m5692getMinHeightimpl(jM534constructorimpl), iMax3 + iMax2));
        } else {
            iMax4 = Constraints.m5690getMaxHeightimpl(jM534constructorimpl);
        }
        int i23 = iMax4;
        int i24 = i;
        int[] iArr = new int[i24];
        for (int i25 = 0; i25 < i24; i25++) {
            iArr[i25] = 0;
        }
        int[] iArr2 = new int[i24];
        for (int i26 = 0; i26 < i24; i26++) {
            Placeable placeable2 = rowColumnMeasurementHelper2.placeables[i26 + startIndex];
            Intrinsics.checkNotNull(placeable2);
            iArr2[i26] = rowColumnMeasurementHelper2.mainAxisSize(placeable2);
        }
        return new RowColumnMeasureHelperResult(i23, iMax7, startIndex, endIndex, iMax2, rowColumnMeasurementHelper2.mainAxisPositions(iMax7, iArr2, iArr, measureScope));
    }

    private final int[] mainAxisPositions(int mainAxisLayoutSize, int[] childrenMainAxisSize, int[] mainAxisPositions, MeasureScope measureScope) {
        if (this.orientation == LayoutOrientation.Vertical) {
            Arrangement.Vertical vertical = this.verticalArrangement;
            if (vertical == null) {
                throw new IllegalArgumentException("null verticalArrangement in Column".toString());
            }
            vertical.arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, mainAxisPositions);
        } else {
            Arrangement.Horizontal horizontal = this.horizontalArrangement;
            if (horizontal == null) {
                throw new IllegalArgumentException("null horizontalArrangement in Row".toString());
            }
            horizontal.arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, measureScope.getLayoutDirection(), mainAxisPositions);
        }
        return mainAxisPositions;
    }

    private final int getCrossAxisPosition(Placeable placeable, RowColumnParentData parentData, int crossAxisLayoutSize, LayoutDirection layoutDirection, int beforeCrossAxisAlignmentLine) {
        CrossAxisAlignment crossAxisAlignment;
        if (parentData == null || (crossAxisAlignment = parentData.getCrossAxisAlignment()) == null) {
            crossAxisAlignment = this.crossAxisAlignment;
        }
        int iCrossAxisSize = crossAxisLayoutSize - crossAxisSize(placeable);
        if (this.orientation == LayoutOrientation.Horizontal) {
            layoutDirection = LayoutDirection.Ltr;
        }
        return crossAxisAlignment.align$foundation_layout_release(iCrossAxisSize, layoutDirection, placeable, beforeCrossAxisAlignmentLine);
    }

    public final void placeHelper(Placeable.PlacementScope placeableScope, RowColumnMeasureHelperResult measureResult, int crossAxisOffset, LayoutDirection layoutDirection) {
        int endIndex = measureResult.getEndIndex();
        for (int startIndex = measureResult.getStartIndex(); startIndex < endIndex; startIndex++) {
            Placeable placeable = this.placeables[startIndex];
            Intrinsics.checkNotNull(placeable);
            int[] mainAxisPositions = measureResult.getMainAxisPositions();
            Object parentData = this.measurables.get(startIndex).getParentData();
            int crossAxisPosition = getCrossAxisPosition(placeable, parentData instanceof RowColumnParentData ? (RowColumnParentData) parentData : null, measureResult.getCrossAxisSize(), layoutDirection, measureResult.getBeforeCrossAxisAlignmentLine()) + crossAxisOffset;
            if (this.orientation == LayoutOrientation.Horizontal) {
                Placeable.PlacementScope.place$default(placeableScope, placeable, mainAxisPositions[startIndex - measureResult.getStartIndex()], crossAxisPosition, 0.0f, 4, null);
            } else {
                Placeable.PlacementScope.place$default(placeableScope, placeable, crossAxisPosition, mainAxisPositions[startIndex - measureResult.getStartIndex()], 0.0f, 4, null);
            }
        }
    }
}
