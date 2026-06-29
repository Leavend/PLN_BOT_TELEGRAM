package id.go.bpsfasih.ui.unduhChangeMode;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.repository.PeriodeRepository;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: UnduhChangeModeViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeViewModel$mapData$periode$1", f = "UnduhChangeModeViewModel.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UnduhChangeModeViewModel$mapData$periode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PeriodeEntityNew>, Object> {
    final /* synthetic */ String $userId;
    int label;
    final /* synthetic */ UnduhChangeModeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UnduhChangeModeViewModel$mapData$periode$1(UnduhChangeModeViewModel unduhChangeModeViewModel, String str, Continuation<? super UnduhChangeModeViewModel$mapData$periode$1> continuation) {
        super(2, continuation);
        this.this$0 = unduhChangeModeViewModel;
        this.$userId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UnduhChangeModeViewModel$mapData$periode$1(this.this$0, this.$userId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PeriodeEntityNew> continuation) {
        return ((UnduhChangeModeViewModel$mapData$periode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PeriodeRepository periodeRepository = DataSurvey.Periode.INSTANCE.getPeriodeRepository();
            String periodeId = this.this$0.getPeriodeId();
            String str = this.$userId;
            this.label = 1;
            obj = periodeRepository.getPeriodeByPrimaryId(periodeId + InternalZipConstants.ZIP_FILE_SEPARATOR + str, this);
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
