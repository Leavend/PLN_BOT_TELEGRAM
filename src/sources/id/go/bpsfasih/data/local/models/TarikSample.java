package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TarikSampleModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R \u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/data/local/models/TarikSample;", "", FirebaseAnalytics.Param.CONTENT, "", "Lid/go/bpsfasih/data/local/models/ContentItem;", "(Ljava/util/List;)V", "getContent", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class TarikSample {
    public static final int $stable = 8;

    @SerializedName(FirebaseAnalytics.Param.CONTENT)
    private final List<ContentItem> content;

    /* JADX WARN: Multi-variable type inference failed */
    public TarikSample() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TarikSample copy$default(TarikSample tarikSample, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = tarikSample.content;
        }
        return tarikSample.copy(list);
    }

    public final List<ContentItem> component1() {
        return this.content;
    }

    public final TarikSample copy(List<ContentItem> content) {
        return new TarikSample(content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TarikSample) && Intrinsics.areEqual(this.content, ((TarikSample) other).content);
    }

    public int hashCode() {
        List<ContentItem> list = this.content;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "TarikSample(content=" + this.content + ")";
    }

    public TarikSample(List<ContentItem> list) {
        this.content = list;
    }

    public /* synthetic */ TarikSample(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public final List<ContentItem> getContent() {
        return this.content;
    }
}
