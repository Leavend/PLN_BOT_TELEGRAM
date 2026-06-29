package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChangeTracker.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0001 B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0003J*\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0006\u0010\u0012\u001a\u00020\rJ\u001d\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u0017J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u001e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/ChangeTracker;", "Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "initialChanges", "(Landroidx/compose/foundation/text2/input/internal/ChangeTracker;)V", "_changes", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/text2/input/internal/ChangeTracker$Change;", "_changesTemp", "changeCount", "", "getChangeCount", "()I", "appendNewChange", "", "mergedOverlappingChange", "preMin", "preMax", "postDelta", "clearChanges", "getOriginalRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getOriginalRange--jx7JFs", "(I)J", "getRange", "getRange--jx7JFs", "toString", "", "trackChange", "preStart", "preEnd", "postLength", "Change", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ChangeTracker implements TextFieldBuffer.ChangeList {
    public static final int $stable = 8;
    private MutableVector<Change> _changes;
    private MutableVector<Change> _changesTemp;

    /* JADX WARN: Multi-variable type inference failed */
    public ChangeTracker() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ChangeTracker(ChangeTracker changeTracker) {
        MutableVector<Change> mutableVector;
        int size;
        int i = 0;
        this._changes = new MutableVector<>(new Change[16], 0);
        this._changesTemp = new MutableVector<>(new Change[16], 0);
        if (changeTracker == null || (mutableVector = changeTracker._changes) == null || (size = mutableVector.getSize()) <= 0) {
            return;
        }
        Change[] content = mutableVector.getContent();
        do {
            Change change = content[i];
            this._changes.add(new Change(change.getPreStart(), change.getPreEnd(), change.getOriginalStart(), change.getOriginalEnd()));
            i++;
        } while (i < size);
    }

    public /* synthetic */ ChangeTracker(ChangeTracker changeTracker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : changeTracker);
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldBuffer.ChangeList
    public int getChangeCount() {
        return this._changes.getSize();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void trackChange(int r8, int r9, int r10) {
        /*
            r7 = this;
            if (r8 != r9) goto L5
            if (r10 != 0) goto L5
            return
        L5:
            int r0 = java.lang.Math.min(r8, r9)
            int r8 = java.lang.Math.max(r8, r9)
            int r9 = r8 - r0
            int r10 = r10 - r9
            r9 = 0
            r1 = 0
            r2 = r9
            r3 = r2
        L14:
            androidx.compose.runtime.collection.MutableVector<androidx.compose.foundation.text2.input.internal.ChangeTracker$Change> r4 = r7._changes
            int r4 = r4.getSize()
            if (r2 >= r4) goto L7b
            androidx.compose.runtime.collection.MutableVector<androidx.compose.foundation.text2.input.internal.ChangeTracker$Change> r4 = r7._changes
            java.lang.Object[] r4 = r4.getContent()
            r4 = r4[r2]
            androidx.compose.foundation.text2.input.internal.ChangeTracker$Change r4 = (androidx.compose.foundation.text2.input.internal.ChangeTracker.Change) r4
            int r5 = r4.getPreStart()
            r6 = 1
            if (r0 > r5) goto L31
            if (r5 > r8) goto L31
            r5 = r6
            goto L32
        L31:
            r5 = r9
        L32:
            if (r5 != 0) goto L66
            int r5 = r4.getPreEnd()
            if (r0 > r5) goto L3e
            if (r5 > r8) goto L3e
            r5 = r6
            goto L3f
        L3e:
            r5 = r9
        L3f:
            if (r5 == 0) goto L42
            goto L66
        L42:
            int r5 = r4.getPreStart()
            if (r5 <= r8) goto L4e
            if (r3 != 0) goto L4e
            r7.appendNewChange(r1, r0, r8, r10)
            r3 = r6
        L4e:
            if (r3 == 0) goto L60
            int r5 = r4.getPreStart()
            int r5 = r5 + r10
            r4.setPreStart(r5)
            int r5 = r4.getPreEnd()
            int r5 = r5 + r10
            r4.setPreEnd(r5)
        L60:
            androidx.compose.runtime.collection.MutableVector<androidx.compose.foundation.text2.input.internal.ChangeTracker$Change> r5 = r7._changesTemp
            r5.add(r4)
            goto L78
        L66:
            if (r1 != 0) goto L6a
            r1 = r4
            goto L78
        L6a:
            int r5 = r4.getPreEnd()
            r1.setPreEnd(r5)
            int r4 = r4.getOriginalEnd()
            r1.setOriginalEnd(r4)
        L78:
            int r2 = r2 + 1
            goto L14
        L7b:
            if (r3 != 0) goto L80
            r7.appendNewChange(r1, r0, r8, r10)
        L80:
            androidx.compose.runtime.collection.MutableVector<androidx.compose.foundation.text2.input.internal.ChangeTracker$Change> r8 = r7._changes
            androidx.compose.runtime.collection.MutableVector<androidx.compose.foundation.text2.input.internal.ChangeTracker$Change> r9 = r7._changesTemp
            r7._changes = r9
            r7._changesTemp = r8
            r8.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.ChangeTracker.trackChange(int, int, int):void");
    }

    public final void clearChanges() {
        this._changes.clear();
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldBuffer.ChangeList
    /* renamed from: getRange--jx7JFs */
    public long mo1080getRangejx7JFs(int changeIndex) {
        Change change = this._changes.getContent()[changeIndex];
        return TextRangeKt.TextRange(change.getPreStart(), change.getPreEnd());
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldBuffer.ChangeList
    /* renamed from: getOriginalRange--jx7JFs */
    public long mo1079getOriginalRangejx7JFs(int changeIndex) {
        Change change = this._changes.getContent()[changeIndex];
        return TextRangeKt.TextRange(change.getOriginalStart(), change.getOriginalEnd());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChangeList(changes=[");
        MutableVector<Change> mutableVector = this._changes;
        int size = mutableVector.getSize();
        if (size > 0) {
            Change[] content = mutableVector.getContent();
            int i = 0;
            do {
                Change change = content[i];
                sb.append("(" + change.getOriginalStart() + ',' + change.getOriginalEnd() + ")->(" + change.getPreStart() + ',' + change.getPreEnd() + ')');
                if (i < getChangeCount() - 1) {
                    sb.append(", ");
                }
                i++;
            } while (i < size);
        }
        sb.append("])");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final void appendNewChange(Change mergedOverlappingChange, int preMin, int preMax, int postDelta) {
        int preEnd;
        if (this._changesTemp.isEmpty()) {
            preEnd = 0;
        } else {
            Change changeLast = this._changesTemp.last();
            preEnd = changeLast.getPreEnd() - changeLast.getOriginalEnd();
        }
        if (mergedOverlappingChange == null) {
            int i = preMin - preEnd;
            mergedOverlappingChange = new Change(preMin, preMax + postDelta, i, (preMax - preMin) + i);
        } else {
            if (mergedOverlappingChange.getPreStart() > preMin) {
                mergedOverlappingChange.setPreStart(preMin);
                mergedOverlappingChange.setOriginalStart(preMin);
            }
            if (preMax > mergedOverlappingChange.getPreEnd()) {
                int preEnd2 = mergedOverlappingChange.getPreEnd() - mergedOverlappingChange.getOriginalEnd();
                mergedOverlappingChange.setPreEnd(preMax);
                mergedOverlappingChange.setOriginalEnd(preMax - preEnd2);
            }
            mergedOverlappingChange.setPreEnd(mergedOverlappingChange.getPreEnd() + postDelta);
        }
        this._changesTemp.add(mergedOverlappingChange);
    }

    /* compiled from: ChangeTracker.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/ChangeTracker$Change;", "", "preStart", "", "preEnd", "originalStart", "originalEnd", "(IIII)V", "getOriginalEnd", "()I", "setOriginalEnd", "(I)V", "getOriginalStart", "setOriginalStart", "getPreEnd", "setPreEnd", "getPreStart", "setPreStart", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final /* data */ class Change {
        private int originalEnd;
        private int originalStart;
        private int preEnd;
        private int preStart;

        public static /* synthetic */ Change copy$default(Change change, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = change.preStart;
            }
            if ((i5 & 2) != 0) {
                i2 = change.preEnd;
            }
            if ((i5 & 4) != 0) {
                i3 = change.originalStart;
            }
            if ((i5 & 8) != 0) {
                i4 = change.originalEnd;
            }
            return change.copy(i, i2, i3, i4);
        }

        /* renamed from: component1, reason: from getter */
        public final int getPreStart() {
            return this.preStart;
        }

        /* renamed from: component2, reason: from getter */
        public final int getPreEnd() {
            return this.preEnd;
        }

        /* renamed from: component3, reason: from getter */
        public final int getOriginalStart() {
            return this.originalStart;
        }

        /* renamed from: component4, reason: from getter */
        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final Change copy(int preStart, int preEnd, int originalStart, int originalEnd) {
            return new Change(preStart, preEnd, originalStart, originalEnd);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Change)) {
                return false;
            }
            Change change = (Change) other;
            return this.preStart == change.preStart && this.preEnd == change.preEnd && this.originalStart == change.originalStart && this.originalEnd == change.originalEnd;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.preStart) * 31) + Integer.hashCode(this.preEnd)) * 31) + Integer.hashCode(this.originalStart)) * 31) + Integer.hashCode(this.originalEnd);
        }

        public String toString() {
            return "Change(preStart=" + this.preStart + ", preEnd=" + this.preEnd + ", originalStart=" + this.originalStart + ", originalEnd=" + this.originalEnd + ')';
        }

        public Change(int i, int i2, int i3, int i4) {
            this.preStart = i;
            this.preEnd = i2;
            this.originalStart = i3;
            this.originalEnd = i4;
        }

        public final int getPreStart() {
            return this.preStart;
        }

        public final void setPreStart(int i) {
            this.preStart = i;
        }

        public final int getPreEnd() {
            return this.preEnd;
        }

        public final void setPreEnd(int i) {
            this.preEnd = i;
        }

        public final int getOriginalStart() {
            return this.originalStart;
        }

        public final void setOriginalStart(int i) {
            this.originalStart = i;
        }

        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final void setOriginalEnd(int i) {
            this.originalEnd = i;
        }
    }
}
