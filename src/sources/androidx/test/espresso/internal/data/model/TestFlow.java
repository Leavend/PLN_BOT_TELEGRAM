package androidx.test.espresso.internal.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TestFlow.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0010R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u0019"}, d2 = {"Landroidx/test/espresso/internal/data/model/TestFlow;", "", "()V", "allScreenData", "Ljava/util/ArrayList;", "Landroidx/test/espresso/internal/data/model/ScreenData;", "Lkotlin/collections/ArrayList;", "head", "getHead", "()Landroidx/test/espresso/internal/data/model/ScreenData;", "setHead", "(Landroidx/test/espresso/internal/data/model/ScreenData;)V", "tail", "getTail", "setTail", "addScreen", "", "screen", "action", "Landroidx/test/espresso/internal/data/model/ActionData;", "getEdge", FirebaseAnalytics.Param.INDEX, "", "getSize", "resetTraversal", "third_party.android.androidx_test.espresso.core.java.androidx.test.espresso.internal.data.model_model"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class TestFlow {
    private final ArrayList<ScreenData> allScreenData = new ArrayList<>();
    private ScreenData head;
    private ScreenData tail;

    public final void addScreen(ScreenData screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        this.allScreenData.add(screen);
        if (this.head == null) {
            this.head = screen;
        }
        ScreenData screenData = this.tail;
        if (screenData != null) {
            ScreenData screenData2 = this.tail;
            Intrinsics.checkNotNull(screenData2);
            screenData.addAction(new ActionData(screenData2, screen));
        }
        this.tail = screen;
    }

    public final ActionData getEdge(int index) {
        Integer index2;
        ScreenData dest = this.head;
        if (dest == null) {
            return null;
        }
        Intrinsics.checkNotNull(dest);
        resetTraversal();
        while (!dest.getActions().isEmpty()) {
            ActionData actionData = dest.getActions().get(dest.getActionIndex());
            dest.setActionIndex(dest.getActionIndex() + 1);
            if (actionData.getIndex() != null && (index2 = actionData.getIndex()) != null && index == index2.intValue()) {
                return actionData;
            }
            dest = actionData.getDest();
        }
        return null;
    }

    public final ScreenData getHead() {
        return this.head;
    }

    public final int getSize() {
        return this.allScreenData.size();
    }

    public final ScreenData getTail() {
        return this.tail;
    }

    public final void resetTraversal() {
        Iterator<ScreenData> it = this.allScreenData.iterator();
        while (it.hasNext()) {
            it.next().setActionIndex(0);
        }
    }

    public final void setHead(ScreenData screenData) {
        this.head = screenData;
    }

    public final void setTail(ScreenData screenData) {
        this.tail = screenData;
    }

    public final void addScreen(ScreenData screen, ActionData action) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        Intrinsics.checkNotNullParameter(action, "action");
        this.allScreenData.add(screen);
        if (this.head == null) {
            this.head = screen;
        }
        ScreenData screenData = this.tail;
        Intrinsics.checkNotNull(screenData);
        screenData.addAction(action);
        this.tail = screen;
    }
}
