package id.go.bpsfasih.data.local.converter;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import id.go.bpsfasih.data.local.entities.Region;
import id.go.bpsfasih.data.local.entities.RegionMetadata;
import id.go.bpsfasih.domain.models.AssignmentHistory;
import id.go.bpsfasih.domain.models.AssignmentResponsibilityForResponseData;
import java.util.List;
import kotlin.Metadata;

/* compiled from: TyperConverterAll.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0004H\u0007J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004H\u0007J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0007J\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u000eH\u0007J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\fH\u0007¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/data/local/converter/TyperConverterAll;", "", "()V", "jsonToList", "", "", "json", "jsonToListHistory", "Lid/go/bpsfasih/domain/models/AssignmentHistory;", "jsonToListResponsibility", "Lid/go/bpsfasih/domain/models/AssignmentResponsibilityForResponseData;", "jsonToRegion", "Lid/go/bpsfasih/data/local/entities/Region;", "jsonToRegionMetadata", "Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "listHistoryToJson", "list", "listResponsibilityToJson", "listStringToJson", "regionMetadataToJson", "regionMetadata", "regionToJson", "region", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TyperConverterAll {
    public static final int $stable = 0;

    public final List<String> jsonToList(String json) {
        if (json != null) {
            return (List) new Gson().fromJson(json, new TypeToken<List<? extends String>>() { // from class: id.go.bpsfasih.data.local.converter.TyperConverterAll$jsonToList$1$type$1
            }.getType());
        }
        return null;
    }

    public final String listStringToJson(List<String> list) {
        if (list != null) {
            return new Gson().toJson(list, new TypeToken<List<? extends String>>() { // from class: id.go.bpsfasih.data.local.converter.TyperConverterAll$listStringToJson$1$type$1
            }.getType());
        }
        return null;
    }

    public final String listResponsibilityToJson(List<AssignmentResponsibilityForResponseData> list) {
        if (list != null) {
            return new Gson().toJson(list, new TypeToken<List<? extends AssignmentResponsibilityForResponseData>>() { // from class: id.go.bpsfasih.data.local.converter.TyperConverterAll$listResponsibilityToJson$1$type$1
            }.getType());
        }
        return null;
    }

    public final List<AssignmentResponsibilityForResponseData> jsonToListResponsibility(String json) {
        if (json != null) {
            return (List) new Gson().fromJson(json, new TypeToken<List<? extends AssignmentResponsibilityForResponseData>>() { // from class: id.go.bpsfasih.data.local.converter.TyperConverterAll$jsonToListResponsibility$1$type$1
            }.getType());
        }
        return null;
    }

    public final String listHistoryToJson(List<AssignmentHistory> list) {
        if (list != null) {
            return new Gson().toJson(list, new TypeToken<List<? extends AssignmentHistory>>() { // from class: id.go.bpsfasih.data.local.converter.TyperConverterAll$listHistoryToJson$1$type$1
            }.getType());
        }
        return null;
    }

    public final List<AssignmentHistory> jsonToListHistory(String json) {
        if (json != null) {
            return (List) new Gson().fromJson(json, new TypeToken<List<? extends AssignmentHistory>>() { // from class: id.go.bpsfasih.data.local.converter.TyperConverterAll$jsonToListHistory$1$type$1
            }.getType());
        }
        return null;
    }

    public final String regionToJson(Region region) {
        if (region != null) {
            return new Gson().toJson(region);
        }
        return null;
    }

    public final Region jsonToRegion(String json) {
        if (json != null) {
            return (Region) new Gson().fromJson(json, Region.class);
        }
        return null;
    }

    public final String regionMetadataToJson(RegionMetadata regionMetadata) {
        if (regionMetadata != null) {
            return new Gson().toJson(regionMetadata);
        }
        return null;
    }

    public final RegionMetadata jsonToRegionMetadata(String json) {
        if (json != null) {
            return (RegionMetadata) new Gson().fromJson(json, RegionMetadata.class);
        }
        return null;
    }
}
