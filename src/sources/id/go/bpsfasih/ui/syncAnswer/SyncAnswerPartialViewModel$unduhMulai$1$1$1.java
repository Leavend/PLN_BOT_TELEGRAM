package id.go.bpsfasih.ui.syncAnswer;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import id.go.bpsfasih.domain.models.SyncAnswerPartial;
import id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartial;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SyncAnswerPartialViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel$unduhMulai$1$1$1", f = "SyncAnswerPartialViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class SyncAnswerPartialViewModel$unduhMulai$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<HashMap<String, List<SyncAnswerPartial>>> $assignmentMapTemp;
    final /* synthetic */ SyncAnswerPartial $it;
    final /* synthetic */ Map.Entry<String, List<SyncAnswerPartial>> $map;
    int label;
    final /* synthetic */ SyncAnswerPartialViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SyncAnswerPartialViewModel$unduhMulai$1$1$1(SyncAnswerPartial syncAnswerPartial, Map.Entry<String, ? extends List<SyncAnswerPartial>> entry, Ref.ObjectRef<HashMap<String, List<SyncAnswerPartial>>> objectRef, SyncAnswerPartialViewModel syncAnswerPartialViewModel, Continuation<? super SyncAnswerPartialViewModel$unduhMulai$1$1$1> continuation) {
        super(2, continuation);
        this.$it = syncAnswerPartial;
        this.$map = entry;
        this.$assignmentMapTemp = objectRef;
        this.this$0 = syncAnswerPartialViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SyncAnswerPartialViewModel$unduhMulai$1$1$1(this.$it, this.$map, this.$assignmentMapTemp, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SyncAnswerPartialViewModel$unduhMulai$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SyncAnswerPartial syncAnswerPartial = this.$it;
        final Map.Entry<String, List<SyncAnswerPartial>> entry = this.$map;
        final SyncAnswerPartial syncAnswerPartial2 = this.$it;
        final Ref.ObjectRef<HashMap<String, List<SyncAnswerPartial>>> objectRef = this.$assignmentMapTemp;
        final SyncAnswerPartialViewModel syncAnswerPartialViewModel = this.this$0;
        new RDAnswerPartial(syncAnswerPartial, new Function3<String, Boolean, Double, Unit>() { // from class: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel$unduhMulai$1$1$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool, Double d) {
                invoke(str, bool.booleanValue(), d.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, boolean z, double d) {
                MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap;
                MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap2;
                if (!z) {
                    int size = entry.getValue().size();
                    for (int i = 0; i < size; i++) {
                        if (entry.getValue().get(i).equals(syncAnswerPartial2)) {
                            List<SyncAnswerPartial> list = objectRef.element.get(entry.getKey());
                            SyncAnswerPartial syncAnswerPartial3 = list != null ? list.get(i) : null;
                            if (syncAnswerPartial3 != null) {
                                syncAnswerPartial3.setProgress(Double.valueOf(d));
                            }
                        }
                    }
                    if ((d == 100.0d ? 1 : 0) != 0) {
                        SyncAnswerPartialViewModel syncAnswerPartialViewModel2 = syncAnswerPartialViewModel;
                        syncAnswerPartialViewModel2.setProgress(syncAnswerPartialViewModel2.getProgress() + 1);
                    }
                    if (MathKt.roundToInt(d) % 5 == 0 && (assignmentMap2 = syncAnswerPartialViewModel.getAssignmentMap()) != null) {
                        assignmentMap2.postValue(objectRef.element);
                    }
                    if (syncAnswerPartialViewModel.getTotalProgress() == syncAnswerPartialViewModel.getProgress()) {
                        syncAnswerPartialViewModel.finish();
                        return;
                    }
                    return;
                }
                int size2 = entry.getValue().size();
                while (i < size2) {
                    if (entry.getValue().get(i).equals(syncAnswerPartial2)) {
                        List<SyncAnswerPartial> list2 = objectRef.element.get(entry.getKey());
                        SyncAnswerPartial syncAnswerPartial4 = list2 != null ? list2.get(i) : null;
                        if (syncAnswerPartial4 != null) {
                            syncAnswerPartial4.setProgress(Double.valueOf(d));
                        }
                    }
                    i++;
                }
                if (MathKt.roundToInt(d) % 5 != 0 || (assignmentMap = syncAnswerPartialViewModel.getAssignmentMap()) == null) {
                    return;
                }
                assignmentMap.postValue(objectRef.element);
            }
        });
        return Unit.INSTANCE;
    }
}
