package id.go.bpsfasih.utils.sync.dbProcess;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;

/* compiled from: DPAssignment.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010$\n\u0000\b\u0007\u0018\u00002\u00020\u0001BF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u0002J*\u0010\u001c\u001a\u00020\u000e2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u001fH\u0002R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017¨\u0006 "}, d2 = {"Lid/go/bpsfasih/utils/sync/dbProcess/DPAssignment;", "", "surveyId", "", "periodeId", "listAssignment", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", FirebaseAnalytics.Param.SUCCESS, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "assignmentRepo", "Lid/go/bpsfasih/data/local/repository/AssignmentRepository;", "getCallback", "()Lkotlin/jvm/functions/Function1;", "getListAssignment", "()Ljava/util/List;", "getPeriodeId", "()Ljava/lang/String;", "getSurveyId", "preferLocalValue", "localValue", "serverValue", "preserveLocalSummary", "serverAssignments", "localAssignmentById", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DPAssignment {
    public static final int $stable = 8;
    private final AssignmentRepository assignmentRepo;
    private final Function1<Boolean, Unit> callback;
    private final List<AssignmentEntity> listAssignment;
    private final String periodeId;
    private final String surveyId;

    /* JADX WARN: Multi-variable type inference failed */
    public DPAssignment(String surveyId, String periodeId, List<AssignmentEntity> listAssignment, Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(listAssignment, "listAssignment");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.surveyId = surveyId;
        this.periodeId = periodeId;
        this.listAssignment = listAssignment;
        this.callback = callback;
        AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
        this.assignmentRepo = assignmentRepository;
        final Map map = (Map) BuildersKt__BuildersKt.runBlocking$default(null, new DPAssignment$localAssignmentById$1(this, null), 1, null);
        assignmentRepository.deleteByPeriode(periodeId, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.dbProcess.DPAssignment.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                List<AssignmentEntity> listMapIdToAssignment = AssignmentEntity.INSTANCE.mapIdToAssignment(DPAssignment.this.getSurveyId(), DPAssignment.this.getPeriodeId(), DPAssignment.this.getListAssignment());
                DPAssignment.this.preserveLocalSummary(listMapIdToAssignment, map);
                AssignmentRepository assignmentRepository2 = DPAssignment.this.assignmentRepo;
                final DPAssignment dPAssignment = DPAssignment.this;
                assignmentRepository2.insertAllData(listMapIdToAssignment, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.dbProcess.DPAssignment.1.1
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
                        dPAssignment.getCallback().invoke(true);
                    }
                });
            }
        });
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final List<AssignmentEntity> getListAssignment() {
        return this.listAssignment;
    }

    public final Function1<Boolean, Unit> getCallback() {
        return this.callback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void preserveLocalSummary(List<AssignmentEntity> serverAssignments, Map<String, AssignmentEntity> localAssignmentById) {
        for (AssignmentEntity assignmentEntity : serverAssignments) {
            AssignmentEntity assignmentEntity2 = localAssignmentById.get(assignmentEntity.getId());
            if (assignmentEntity2 != null) {
                assignmentEntity.setData1(preferLocalValue(assignmentEntity2.getData1(), assignmentEntity.getData1()));
                assignmentEntity.setData2(preferLocalValue(assignmentEntity2.getData2(), assignmentEntity.getData2()));
                assignmentEntity.setData3(preferLocalValue(assignmentEntity2.getData3(), assignmentEntity.getData3()));
                assignmentEntity.setData4(preferLocalValue(assignmentEntity2.getData4(), assignmentEntity.getData4()));
                assignmentEntity.setData5(preferLocalValue(assignmentEntity2.getData5(), assignmentEntity.getData5()));
                assignmentEntity.setData6(preferLocalValue(assignmentEntity2.getData6(), assignmentEntity.getData6()));
                assignmentEntity.setData7(preferLocalValue(assignmentEntity2.getData7(), assignmentEntity.getData7()));
                assignmentEntity.setData8(preferLocalValue(assignmentEntity2.getData8(), assignmentEntity.getData8()));
                assignmentEntity.setData9(preferLocalValue(assignmentEntity2.getData9(), assignmentEntity.getData9()));
                assignmentEntity.setData10(preferLocalValue(assignmentEntity2.getData10(), assignmentEntity.getData10()));
            }
        }
    }

    private final String preferLocalValue(String localValue, String serverValue) {
        String str = localValue;
        return !(str == null || StringsKt.isBlank(str)) ? localValue : serverValue;
    }
}
