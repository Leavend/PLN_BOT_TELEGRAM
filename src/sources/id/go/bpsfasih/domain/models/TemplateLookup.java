package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Survey.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\u001f"}, d2 = {"Lid/go/bpsfasih/domain/models/TemplateLookup;", "", "templateId", "", "templateVersion", "formEngineId", "", "formEngineBrandName", "lookups", "", "Lid/go/bpsfasih/domain/models/Lookup;", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;)V", "getFormEngineBrandName", "()Ljava/lang/String;", "getFormEngineId", "()I", "getLookups", "()Ljava/util/List;", "getTemplateId", "getTemplateVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class TemplateLookup {
    public static final int $stable = 8;
    private final String formEngineBrandName;
    private final int formEngineId;
    private final List<Lookup> lookups;
    private final String templateId;
    private final String templateVersion;

    public static /* synthetic */ TemplateLookup copy$default(TemplateLookup templateLookup, String str, String str2, int i, String str3, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = templateLookup.templateId;
        }
        if ((i2 & 2) != 0) {
            str2 = templateLookup.templateVersion;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            i = templateLookup.formEngineId;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str3 = templateLookup.formEngineBrandName;
        }
        String str5 = str3;
        if ((i2 & 16) != 0) {
            list = templateLookup.lookups;
        }
        return templateLookup.copy(str, str4, i3, str5, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTemplateId() {
        return this.templateId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTemplateVersion() {
        return this.templateVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final int getFormEngineId() {
        return this.formEngineId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFormEngineBrandName() {
        return this.formEngineBrandName;
    }

    public final List<Lookup> component5() {
        return this.lookups;
    }

    public final TemplateLookup copy(String templateId, String templateVersion, int formEngineId, String formEngineBrandName, List<Lookup> lookups) {
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(templateVersion, "templateVersion");
        Intrinsics.checkNotNullParameter(formEngineBrandName, "formEngineBrandName");
        Intrinsics.checkNotNullParameter(lookups, "lookups");
        return new TemplateLookup(templateId, templateVersion, formEngineId, formEngineBrandName, lookups);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemplateLookup)) {
            return false;
        }
        TemplateLookup templateLookup = (TemplateLookup) other;
        return Intrinsics.areEqual(this.templateId, templateLookup.templateId) && Intrinsics.areEqual(this.templateVersion, templateLookup.templateVersion) && this.formEngineId == templateLookup.formEngineId && Intrinsics.areEqual(this.formEngineBrandName, templateLookup.formEngineBrandName) && Intrinsics.areEqual(this.lookups, templateLookup.lookups);
    }

    public int hashCode() {
        return (((((((this.templateId.hashCode() * 31) + this.templateVersion.hashCode()) * 31) + Integer.hashCode(this.formEngineId)) * 31) + this.formEngineBrandName.hashCode()) * 31) + this.lookups.hashCode();
    }

    public String toString() {
        return "TemplateLookup(templateId=" + this.templateId + ", templateVersion=" + this.templateVersion + ", formEngineId=" + this.formEngineId + ", formEngineBrandName=" + this.formEngineBrandName + ", lookups=" + this.lookups + ")";
    }

    public TemplateLookup(String templateId, String templateVersion, int i, String formEngineBrandName, List<Lookup> lookups) {
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(templateVersion, "templateVersion");
        Intrinsics.checkNotNullParameter(formEngineBrandName, "formEngineBrandName");
        Intrinsics.checkNotNullParameter(lookups, "lookups");
        this.templateId = templateId;
        this.templateVersion = templateVersion;
        this.formEngineId = i;
        this.formEngineBrandName = formEngineBrandName;
        this.lookups = lookups;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final String getTemplateVersion() {
        return this.templateVersion;
    }

    public final int getFormEngineId() {
        return this.formEngineId;
    }

    public final String getFormEngineBrandName() {
        return this.formEngineBrandName;
    }

    public /* synthetic */ TemplateLookup(String str, String str2, int i, String str3, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, str3, (i2 & 16) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<Lookup> getLookups() {
        return this.lookups;
    }
}
