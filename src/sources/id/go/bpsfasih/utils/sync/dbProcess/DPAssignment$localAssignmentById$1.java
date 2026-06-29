package id.go.bpsfasih.utils.sync.dbProcess;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DPAssignment.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.sync.dbProcess.DPAssignment$localAssignmentById$1", f = "DPAssignment.kt", i = {}, l = {16}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DPAssignment$localAssignmentById$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, ? extends AssignmentEntity>>, Object> {
    int label;
    final /* synthetic */ DPAssignment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DPAssignment$localAssignmentById$1(DPAssignment dPAssignment, Continuation<? super DPAssignment$localAssignmentById$1> continuation) {
        super(2, continuation);
        this.this$0 = dPAssignment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DPAssignment$localAssignmentById$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, ? extends AssignmentEntity>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Map<String, AssignmentEntity>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Map<String, AssignmentEntity>> continuation) {
        return ((DPAssignment$localAssignmentById$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.assignmentRepo.getAssignmentByPeriodeId(this.this$0.getPeriodeId(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Iterable iterable = (Iterable) obj;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
        for (Object obj2 : iterable) {
            linkedHashMap.put(((AssignmentEntity) obj2).getId(), obj2);
        }
        return linkedHashMap;
    }
}
