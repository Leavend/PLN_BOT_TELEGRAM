package androidx.test.espresso.internal.data.model;

import android.graphics.Rect;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewData.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Landroidx/test/espresso/internal/data/model/ViewData;", "", "desc", "", "viewBox", "Landroid/graphics/Rect;", "visibleViewBox", "(Ljava/lang/String;Landroid/graphics/Rect;Landroid/graphics/Rect;)V", "getDesc", "()Ljava/lang/String;", "getViewBox", "()Landroid/graphics/Rect;", "getVisibleViewBox", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "third_party.android.androidx_test.espresso.core.java.androidx.test.espresso.internal.data.model_model"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final /* data */ class ViewData {
    private final String desc;
    private final Rect viewBox;
    private final Rect visibleViewBox;

    public ViewData(String desc, Rect viewBox, Rect visibleViewBox) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(viewBox, "viewBox");
        Intrinsics.checkNotNullParameter(visibleViewBox, "visibleViewBox");
        this.desc = desc;
        this.viewBox = viewBox;
        this.visibleViewBox = visibleViewBox;
    }

    public static /* synthetic */ ViewData copy$default(ViewData viewData, String str, Rect rect, Rect rect2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = viewData.desc;
        }
        if ((i & 2) != 0) {
            rect = viewData.viewBox;
        }
        if ((i & 4) != 0) {
            rect2 = viewData.visibleViewBox;
        }
        return viewData.copy(str, rect, rect2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* renamed from: component2, reason: from getter */
    public final Rect getViewBox() {
        return this.viewBox;
    }

    /* renamed from: component3, reason: from getter */
    public final Rect getVisibleViewBox() {
        return this.visibleViewBox;
    }

    public final ViewData copy(String desc, Rect viewBox, Rect visibleViewBox) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(viewBox, "viewBox");
        Intrinsics.checkNotNullParameter(visibleViewBox, "visibleViewBox");
        return new ViewData(desc, viewBox, visibleViewBox);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ViewData)) {
            return false;
        }
        ViewData viewData = (ViewData) other;
        return Intrinsics.areEqual(this.desc, viewData.desc) && Intrinsics.areEqual(this.viewBox, viewData.viewBox) && Intrinsics.areEqual(this.visibleViewBox, viewData.visibleViewBox);
    }

    public final String getDesc() {
        return this.desc;
    }

    public final Rect getViewBox() {
        return this.viewBox;
    }

    public final Rect getVisibleViewBox() {
        return this.visibleViewBox;
    }

    public int hashCode() {
        return (((this.desc.hashCode() * 31) + this.viewBox.hashCode()) * 31) + this.visibleViewBox.hashCode();
    }

    public String toString() {
        return "ViewData(desc=" + this.desc + ", viewBox=" + this.viewBox + ", visibleViewBox=" + this.visibleViewBox + ")";
    }
}
