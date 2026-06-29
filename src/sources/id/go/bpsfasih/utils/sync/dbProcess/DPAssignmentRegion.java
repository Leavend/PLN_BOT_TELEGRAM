package id.go.bpsfasih.utils.sync.dbProcess;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.repository.AssignmentRegionRepository;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DPAssignmentRegion.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B>\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0002\u0010\u000eJ\b\u0010\u0017\u001a\u00020\rH\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lid/go/bpsfasih/utils/sync/dbProcess/DPAssignmentRegion;", "", "periodeId", "", "list", "", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", FirebaseAnalytics.Param.SUCCESS, "", "(Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "assignmentRegionRepo", "Lid/go/bpsfasih/data/local/repository/AssignmentRegionRepository;", "getCallback", "()Lkotlin/jvm/functions/Function1;", "getList", "()Ljava/util/List;", "getPeriodeId", "()Ljava/lang/String;", "save", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DPAssignmentRegion {
    public static final int $stable = 8;
    private final AssignmentRegionRepository assignmentRegionRepo;
    private final Function1<Boolean, Unit> callback;
    private final List<AssignmentRegionEntity> list;
    private final String periodeId;

    /* JADX WARN: Multi-variable type inference failed */
    public DPAssignmentRegion(String periodeId, List<AssignmentRegionEntity> list, Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.periodeId = periodeId;
        this.list = list;
        this.callback = callback;
        this.assignmentRegionRepo = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository();
        save();
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final List<AssignmentRegionEntity> getList() {
        return this.list;
    }

    public final Function1<Boolean, Unit> getCallback() {
        return this.callback;
    }

    private final void save() {
        this.assignmentRegionRepo.deleteByPeriode(this.periodeId, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.dbProcess.DPAssignmentRegion.save.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AssignmentRegionRepository assignmentRegionRepository = DPAssignmentRegion.this.assignmentRegionRepo;
                List<AssignmentRegionEntity> list = DPAssignmentRegion.this.getList();
                final DPAssignmentRegion dPAssignmentRegion = DPAssignmentRegion.this;
                assignmentRegionRepository.insertAll(list, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.dbProcess.DPAssignmentRegion.save.1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        dPAssignmentRegion.getCallback().invoke(true);
                    }
                });
            }
        });
    }
}
