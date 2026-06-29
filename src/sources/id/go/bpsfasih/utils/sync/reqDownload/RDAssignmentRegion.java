package id.go.bpsfasih.utils.sync.reqDownload;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.data.repository.RegionRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RDAssignmentRegion.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012)\u0010\u0004\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\u0010\fJ\b\u0010\u0016\u001a\u00020\u000bH\u0002R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R4\u0010\u0004\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDAssignmentRegion;", "", "periode", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "callback", "Lkotlin/Function1;", "", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "Lkotlin/ParameterName;", "name", "result", "", "(Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;Lkotlin/jvm/functions/Function1;)V", "assignmentRegionEntity", "getAssignmentRegionEntity", "()Ljava/util/List;", "setAssignmentRegionEntity", "(Ljava/util/List;)V", "getCallback", "()Lkotlin/jvm/functions/Function1;", "getPeriode", "()Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "request", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDAssignmentRegion {
    public static final int $stable = 8;
    private List<AssignmentRegionEntity> assignmentRegionEntity;
    private final Function1<List<AssignmentRegionEntity>, Unit> callback;
    private final PeriodeEntityNew periode;

    /* JADX WARN: Multi-variable type inference failed */
    public RDAssignmentRegion(PeriodeEntityNew periode, Function1<? super List<AssignmentRegionEntity>, Unit> callback) {
        Intrinsics.checkNotNullParameter(periode, "periode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.periode = periode;
        this.callback = callback;
        this.assignmentRegionEntity = new ArrayList();
        request();
    }

    public final PeriodeEntityNew getPeriode() {
        return this.periode;
    }

    public final Function1<List<AssignmentRegionEntity>, Unit> getCallback() {
        return this.callback;
    }

    public final List<AssignmentRegionEntity> getAssignmentRegionEntity() {
        return this.assignmentRegionEntity;
    }

    public final void setAssignmentRegionEntity(List<AssignmentRegionEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.assignmentRegionEntity = list;
    }

    private final void request() {
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentRegion$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RDAssignmentRegion.request$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void request$lambda$0(final RDAssignmentRegion this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new RegionRepositoryImpl().getAssignmentRegion(this$0.periode.getId(), new Function1<AssignmentRegionResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentRegion$request$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
                invoke2(assignmentRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
                if (assignmentRegionResponse != null) {
                    this.this$0.getAssignmentRegionEntity().addAll(AssignmentRegionEntity.INSTANCE.mapIdToAssignmentRegion(assignmentRegionResponse.getData()));
                    this.this$0.getCallback().invoke(this.this$0.getAssignmentRegionEntity());
                } else {
                    this.this$0.getCallback().invoke(null);
                }
            }
        });
    }
}
