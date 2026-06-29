package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.entities.LookupsList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JR\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000528\u0010\u0007\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00030\bH&JR\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052@\u0010\u0011\u001a<\u0012\u001d\u0012\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\u0012¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00030\bH&¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/domain/repository/TemplateRepository;", "", "getCustomData", "", "templateId", "", "templateVersion", "customDataCallback", "Lkotlin/Function2;", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "Lkotlin/ParameterName;", "name", "result", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "getListLookup", "surveyId", "callback", "", "Lid/go/bpsfasih/data/local/entities/LookupsList;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TemplateRepository {
    void getCustomData(String templateId, String templateVersion, Function2<? super CustomDataTemplateEntity, ? super Boolean, Unit> customDataCallback);

    void getListLookup(String surveyId, Function2<? super List<LookupsList>, ? super Boolean, Unit> callback);
}
