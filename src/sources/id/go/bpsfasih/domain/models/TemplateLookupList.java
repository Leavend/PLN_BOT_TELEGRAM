package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Survey.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/domain/models/TemplateLookupList;", "", "templateId", "", "lookups", "", "Lid/go/bpsfasih/domain/models/LookupsList;", "(Ljava/lang/String;Ljava/util/List;)V", "getLookups", "()Ljava/util/List;", "getTemplateId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class TemplateLookupList {
    public static final int $stable = 8;
    private final List<LookupsList> lookups;
    private final String templateId;

    /* JADX WARN: Multi-variable type inference failed */
    public TemplateLookupList() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TemplateLookupList copy$default(TemplateLookupList templateLookupList, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = templateLookupList.templateId;
        }
        if ((i & 2) != 0) {
            list = templateLookupList.lookups;
        }
        return templateLookupList.copy(str, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTemplateId() {
        return this.templateId;
    }

    public final List<LookupsList> component2() {
        return this.lookups;
    }

    public final TemplateLookupList copy(String templateId, List<LookupsList> lookups) {
        return new TemplateLookupList(templateId, lookups);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemplateLookupList)) {
            return false;
        }
        TemplateLookupList templateLookupList = (TemplateLookupList) other;
        return Intrinsics.areEqual(this.templateId, templateLookupList.templateId) && Intrinsics.areEqual(this.lookups, templateLookupList.lookups);
    }

    public int hashCode() {
        String str = this.templateId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        List<LookupsList> list = this.lookups;
        return iHashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "TemplateLookupList(templateId=" + this.templateId + ", lookups=" + this.lookups + ")";
    }

    public TemplateLookupList(String str, List<LookupsList> list) {
        this.templateId = str;
        this.lookups = list;
    }

    public /* synthetic */ TemplateLookupList(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : list);
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final List<LookupsList> getLookups() {
        return this.lookups;
    }
}
