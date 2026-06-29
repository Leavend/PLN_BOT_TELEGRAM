package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TarikSampleModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/local/models/MainItem;", "", "templateType", "", "variable", "templateVariable", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTemplateType", "()Ljava/lang/String;", "getTemplateVariable", "getVariable", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class MainItem {
    public static final int $stable = 0;

    @SerializedName("templateType")
    private final String templateType;

    @SerializedName("templateVariable")
    private final String templateVariable;

    @SerializedName("variable")
    private final String variable;

    public MainItem() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ MainItem copy$default(MainItem mainItem, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mainItem.templateType;
        }
        if ((i & 2) != 0) {
            str2 = mainItem.variable;
        }
        if ((i & 4) != 0) {
            str3 = mainItem.templateVariable;
        }
        return mainItem.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTemplateType() {
        return this.templateType;
    }

    /* renamed from: component2, reason: from getter */
    public final String getVariable() {
        return this.variable;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTemplateVariable() {
        return this.templateVariable;
    }

    public final MainItem copy(String templateType, String variable, String templateVariable) {
        return new MainItem(templateType, variable, templateVariable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MainItem)) {
            return false;
        }
        MainItem mainItem = (MainItem) other;
        return Intrinsics.areEqual(this.templateType, mainItem.templateType) && Intrinsics.areEqual(this.variable, mainItem.variable) && Intrinsics.areEqual(this.templateVariable, mainItem.templateVariable);
    }

    public int hashCode() {
        String str = this.templateType;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.variable;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.templateVariable;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "MainItem(templateType=" + this.templateType + ", variable=" + this.variable + ", templateVariable=" + this.templateVariable + ")";
    }

    public MainItem(String str, String str2, String str3) {
        this.templateType = str;
        this.variable = str2;
        this.templateVariable = str3;
    }

    public /* synthetic */ MainItem(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getTemplateType() {
        return this.templateType;
    }

    public final String getVariable() {
        return this.variable;
    }

    public final String getTemplateVariable() {
        return this.templateVariable;
    }
}
