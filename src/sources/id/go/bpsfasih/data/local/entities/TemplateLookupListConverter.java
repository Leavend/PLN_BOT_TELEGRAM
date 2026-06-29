package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SurveyEntity.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00072\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0007¨\u0006\n"}, d2 = {"Lid/go/bpsfasih/data/local/entities/TemplateLookupListConverter;", "", "()V", "jsonToList", "", "Lid/go/bpsfasih/data/local/entities/TemplateLookupList;", "json", "", "listStringToJson", "list", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TemplateLookupListConverter {
    public static final int $stable = 0;

    public final List<TemplateLookupList> jsonToList(String json) {
        if (json != null) {
            return (List) new Gson().fromJson(json, new TypeToken<List<? extends TemplateLookupList>>() { // from class: id.go.bpsfasih.data.local.entities.TemplateLookupListConverter$jsonToList$1$type$1
            }.getType());
        }
        return null;
    }

    public final String listStringToJson(List<TemplateLookupList> list) {
        if (list != null) {
            return new Gson().toJson(list, new TypeToken<List<? extends TemplateLookupList>>() { // from class: id.go.bpsfasih.data.local.entities.TemplateLookupListConverter$listStringToJson$1$type$1
            }.getType());
        }
        return null;
    }
}
