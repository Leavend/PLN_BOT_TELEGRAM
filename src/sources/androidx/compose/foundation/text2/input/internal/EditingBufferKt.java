package androidx.compose.foundation.text2.input.internal;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: EditingBuffer.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", TypedValues.AttributesType.S_TARGET, "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class EditingBufferKt {
    /* renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m1111updateRangeAfterDeletepWDy79M(long j, long j2) {
        int iM5231getLengthimpl;
        int iM5233getMinimpl = TextRange.m5233getMinimpl(j);
        int iM5232getMaximpl = TextRange.m5232getMaximpl(j);
        if (TextRange.m5237intersects5zctL8(j2, j)) {
            if (TextRange.m5225contains5zctL8(j2, j)) {
                iM5233getMinimpl = TextRange.m5233getMinimpl(j2);
                iM5232getMaximpl = iM5233getMinimpl;
            } else {
                if (TextRange.m5225contains5zctL8(j, j2)) {
                    iM5231getLengthimpl = TextRange.m5231getLengthimpl(j2);
                } else if (TextRange.m5226containsimpl(j2, iM5233getMinimpl)) {
                    iM5233getMinimpl = TextRange.m5233getMinimpl(j2);
                    iM5231getLengthimpl = TextRange.m5231getLengthimpl(j2);
                } else {
                    iM5232getMaximpl = TextRange.m5233getMinimpl(j2);
                }
                iM5232getMaximpl -= iM5231getLengthimpl;
            }
        } else if (iM5232getMaximpl > TextRange.m5233getMinimpl(j2)) {
            iM5233getMinimpl -= TextRange.m5231getLengthimpl(j2);
            iM5231getLengthimpl = TextRange.m5231getLengthimpl(j2);
            iM5232getMaximpl -= iM5231getLengthimpl;
        }
        return TextRangeKt.TextRange(iM5233getMinimpl, iM5232getMaximpl);
    }
}
