package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetSamplingPeriodeData.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R.\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/data/local/entities/GetSamplingPeriodeData;", "", FirebaseAnalytics.Param.CONTENT, "Ljava/util/ArrayList;", "Lid/go/bpsfasih/data/local/entities/Content;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getContent", "()Ljava/util/ArrayList;", "setContent", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class GetSamplingPeriodeData {
    public static final int $stable = 8;

    @SerializedName(FirebaseAnalytics.Param.CONTENT)
    private ArrayList<Content> content;

    /* JADX WARN: Multi-variable type inference failed */
    public GetSamplingPeriodeData() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GetSamplingPeriodeData copy$default(GetSamplingPeriodeData getSamplingPeriodeData, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = getSamplingPeriodeData.content;
        }
        return getSamplingPeriodeData.copy(arrayList);
    }

    public final ArrayList<Content> component1() {
        return this.content;
    }

    public final GetSamplingPeriodeData copy(ArrayList<Content> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return new GetSamplingPeriodeData(content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof GetSamplingPeriodeData) && Intrinsics.areEqual(this.content, ((GetSamplingPeriodeData) other).content);
    }

    public int hashCode() {
        return this.content.hashCode();
    }

    public String toString() {
        return "GetSamplingPeriodeData(content=" + this.content + ")";
    }

    public GetSamplingPeriodeData(ArrayList<Content> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.content = content;
    }

    public /* synthetic */ GetSamplingPeriodeData(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    public final ArrayList<Content> getContent() {
        return this.content;
    }

    public final void setContent(ArrayList<Content> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.content = arrayList;
    }
}
