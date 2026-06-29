package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LookUpModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003J9\u0010\u0015\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lid/go/bpsfasih/domain/models/LookUpModel;", "", "fields", "", "", "data", "tableName", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "getFields", "setFields", "getTableName", "()Ljava/lang/String;", "setTableName", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class LookUpModel {
    public static final int $stable = 8;
    private List<String> data;
    private List<String> fields;
    private String tableName;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LookUpModel copy$default(LookUpModel lookUpModel, List list, List list2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = lookUpModel.fields;
        }
        if ((i & 2) != 0) {
            list2 = lookUpModel.data;
        }
        if ((i & 4) != 0) {
            str = lookUpModel.tableName;
        }
        return lookUpModel.copy(list, list2, str);
    }

    public final List<String> component1() {
        return this.fields;
    }

    public final List<String> component2() {
        return this.data;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTableName() {
        return this.tableName;
    }

    public final LookUpModel copy(List<String> fields, List<String> data, String tableName) {
        return new LookUpModel(fields, data, tableName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LookUpModel)) {
            return false;
        }
        LookUpModel lookUpModel = (LookUpModel) other;
        return Intrinsics.areEqual(this.fields, lookUpModel.fields) && Intrinsics.areEqual(this.data, lookUpModel.data) && Intrinsics.areEqual(this.tableName, lookUpModel.tableName);
    }

    public int hashCode() {
        List<String> list = this.fields;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<String> list2 = this.data;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str = this.tableName;
        return iHashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LookUpModel(fields=" + this.fields + ", data=" + this.data + ", tableName=" + this.tableName + ")";
    }

    public LookUpModel(List<String> list, List<String> list2, String str) {
        this.fields = list;
        this.data = list2;
        this.tableName = str;
    }

    public final List<String> getFields() {
        return this.fields;
    }

    public final void setFields(List<String> list) {
        this.fields = list;
    }

    public final List<String> getData() {
        return this.data;
    }

    public final void setData(List<String> list) {
        this.data = list;
    }

    public final String getTableName() {
        return this.tableName;
    }

    public final void setTableName(String str) {
        this.tableName = str;
    }
}
