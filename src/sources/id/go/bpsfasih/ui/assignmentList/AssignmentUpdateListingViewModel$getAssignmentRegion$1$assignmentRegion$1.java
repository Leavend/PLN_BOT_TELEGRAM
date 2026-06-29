package id.go.bpsfasih.ui.assignmentList;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.repository.AssignmentRegionRepository;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AssignmentUpdateListingViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$getAssignmentRegion$1$assignmentRegion$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {1097}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class AssignmentUpdateListingViewModel$getAssignmentRegion$1$assignmentRegion$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentRegionEntity>, Object> {
    final /* synthetic */ String $regionId;
    final /* synthetic */ String $surveyPeriodeId;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssignmentUpdateListingViewModel$getAssignmentRegion$1$assignmentRegion$1(String str, String str2, Continuation<? super AssignmentUpdateListingViewModel$getAssignmentRegion$1$assignmentRegion$1> continuation) {
        super(2, continuation);
        this.$regionId = str;
        this.$surveyPeriodeId = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssignmentUpdateListingViewModel$getAssignmentRegion$1$assignmentRegion$1(this.$regionId, this.$surveyPeriodeId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentRegionEntity> continuation) {
        return ((AssignmentUpdateListingViewModel$getAssignmentRegion$1$assignmentRegion$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AssignmentRegionRepository assignmentRegionRepository = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository();
            String str = this.$regionId;
            Intrinsics.checkNotNull(str);
            String str2 = this.$surveyPeriodeId;
            Intrinsics.checkNotNull(str2);
            this.label = 1;
            obj = assignmentRegionRepository.getStatusAssignmentRegion(str, str2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
