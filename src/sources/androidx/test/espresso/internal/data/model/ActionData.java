package androidx.test.espresso.internal.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.espresso.ViewAction;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActionData.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003J>\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0007HÖ\u0001J\t\u0010'\u001a\u00020\fHÖ\u0001R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016¨\u0006("}, d2 = {"Landroidx/test/espresso/internal/data/model/ActionData;", "", "source", "Landroidx/test/espresso/internal/data/model/ScreenData;", "dest", "(Landroidx/test/espresso/internal/data/model/ScreenData;Landroidx/test/espresso/internal/data/model/ScreenData;)V", FirebaseAnalytics.Param.INDEX, "", "viewAction", "Landroidx/test/espresso/ViewAction;", "(ILandroidx/test/espresso/ViewAction;)V", "name", "", "desc", "constraints", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getConstraints", "()Ljava/lang/String;", "getDesc", "getDest", "()Landroidx/test/espresso/internal/data/model/ScreenData;", "setDest", "(Landroidx/test/espresso/internal/data/model/ScreenData;)V", "getIndex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getName", "getSource", "setSource", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroidx/test/espresso/internal/data/model/ActionData;", "equals", "", "other", "hashCode", "toString", "third_party.android.androidx_test.espresso.core.java.androidx.test.espresso.internal.data.model_model"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final /* data */ class ActionData {
    private final String constraints;
    private final String desc;
    public ScreenData dest;
    private final Integer index;
    private final String name;
    public ScreenData source;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActionData(int i, ViewAction viewAction) {
        this(Integer.valueOf(i), viewAction.getClass().getSimpleName(), viewAction.getDescription(), viewAction.getConstraints().toString());
        Intrinsics.checkNotNullParameter(viewAction, "viewAction");
    }

    public static /* synthetic */ ActionData copy$default(ActionData actionData, Integer num, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = actionData.index;
        }
        if ((i & 2) != 0) {
            str = actionData.name;
        }
        if ((i & 4) != 0) {
            str2 = actionData.desc;
        }
        if ((i & 8) != 0) {
            str3 = actionData.constraints;
        }
        return actionData.copy(num, str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getIndex() {
        return this.index;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* renamed from: component4, reason: from getter */
    public final String getConstraints() {
        return this.constraints;
    }

    public final ActionData copy(Integer index, String name, String desc, String constraints) {
        return new ActionData(index, name, desc, constraints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionData)) {
            return false;
        }
        ActionData actionData = (ActionData) other;
        return Intrinsics.areEqual(this.index, actionData.index) && Intrinsics.areEqual(this.name, actionData.name) && Intrinsics.areEqual(this.desc, actionData.desc) && Intrinsics.areEqual(this.constraints, actionData.constraints);
    }

    public final String getConstraints() {
        return this.constraints;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final ScreenData getDest() {
        ScreenData screenData = this.dest;
        if (screenData != null) {
            return screenData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dest");
        return null;
    }

    public final Integer getIndex() {
        return this.index;
    }

    public final String getName() {
        return this.name;
    }

    public final ScreenData getSource() {
        ScreenData screenData = this.source;
        if (screenData != null) {
            return screenData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("source");
        return null;
    }

    public int hashCode() {
        Integer num = this.index;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.name;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.desc;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.constraints;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setDest(ScreenData screenData) {
        Intrinsics.checkNotNullParameter(screenData, "<set-?>");
        this.dest = screenData;
    }

    public final void setSource(ScreenData screenData) {
        Intrinsics.checkNotNullParameter(screenData, "<set-?>");
        this.source = screenData;
    }

    public String toString() {
        return "ActionData(index=" + this.index + ", name=" + this.name + ", desc=" + this.desc + ", constraints=" + this.constraints + ")";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActionData(ScreenData source, ScreenData dest) {
        this(null, null, null, null);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(dest, "dest");
        setSource(source);
        setDest(dest);
    }

    public ActionData(Integer num, String str, String str2, String str3) {
        this.index = num;
        this.name = str;
        this.desc = str2;
        this.constraints = str3;
    }
}
