package id.go.bpsfasih.utils.helper;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.JsonSyntaxException;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.utils.GetAssignment;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: AssignmentHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/AssignmentHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: AssignmentHelper.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u00062\u001c\u0010\t\u001a\u0018\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b\u0012\u0004\u0012\u00020\u00040\n¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/utils/helper/AssignmentHelper$Companion;", "", "()V", "assignmentToModel", "", "surveyId", "", "periodeId", ClientCookie.PATH_ATTR, "callback", "Lkotlin/Function1;", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void assignmentToModel(String surveyId, String periodeId, String path, final Function1<? super List<AssignmentEntity>, Unit> callback) throws JsonSyntaxException {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(callback, "callback");
            GetAssignment.INSTANCE.getAssignment(surveyId, periodeId, path, new Function1<List<? extends AssignmentEntity>, Unit>() { // from class: id.go.bpsfasih.utils.helper.AssignmentHelper$Companion$assignmentToModel$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends AssignmentEntity> list) {
                    invoke2((List<AssignmentEntity>) list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<AssignmentEntity> list) {
                    callback.invoke(list);
                }
            });
        }
    }
}
