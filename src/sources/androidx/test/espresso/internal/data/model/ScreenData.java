package androidx.test.espresso.internal.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenData.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000eJ\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0010J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0019J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0019J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\nj\b\u0012\u0004\u0012\u00020\u000e`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\nj\b\u0012\u0004\u0012\u00020\u0010`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/test/espresso/internal/data/model/ScreenData;", "", "()V", "actionIndex", "", "getActionIndex", "()I", "setActionIndex", "(I)V", "actions", "Ljava/util/ArrayList;", "Landroidx/test/espresso/internal/data/model/ActionData;", "Lkotlin/collections/ArrayList;", "artifacts", "Landroidx/test/espresso/internal/data/model/TestArtifact;", "views", "Landroidx/test/espresso/internal/data/model/ViewData;", "addAction", "", "action", "addArtifact", "artifact", "addViewData", "viewData", "getActions", "", "getArtifacts", "getViews", "third_party.android.androidx_test.espresso.core.java.androidx.test.espresso.internal.data.model_model"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ScreenData {
    private int actionIndex;
    private final ArrayList<ActionData> actions = new ArrayList<>();
    private final ArrayList<TestArtifact> artifacts = new ArrayList<>();
    private final ArrayList<ViewData> views = new ArrayList<>();

    public final void addAction(ActionData action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.actions.add(action);
    }

    public final void addArtifact(TestArtifact artifact) {
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        this.artifacts.add(artifact);
    }

    public final void addViewData(ViewData viewData) {
        Intrinsics.checkNotNullParameter(viewData, "viewData");
        this.views.add(viewData);
    }

    public final int getActionIndex() {
        return this.actionIndex;
    }

    public final List<ActionData> getActions() {
        return CollectionsKt.toList(this.actions);
    }

    public final List<TestArtifact> getArtifacts() {
        return CollectionsKt.toList(this.artifacts);
    }

    public final List<ViewData> getViews() {
        return CollectionsKt.toList(this.views);
    }

    public final void setActionIndex(int i) {
        this.actionIndex = i;
    }
}
