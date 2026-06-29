package id.go.bpsfasih.ui.unduhChangeMode;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
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

/* compiled from: UnduhChangeModeViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeViewModel$mapData$templateId$1", f = "UnduhChangeModeViewModel.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UnduhChangeModeViewModel$mapData$templateId$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ PeriodeEntityNew $periode;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UnduhChangeModeViewModel$mapData$templateId$1(PeriodeEntityNew periodeEntityNew, Continuation<? super UnduhChangeModeViewModel$mapData$templateId$1> continuation) {
        super(2, continuation);
        this.$periode = periodeEntityNew;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UnduhChangeModeViewModel$mapData$templateId$1(this.$periode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((UnduhChangeModeViewModel$mapData$templateId$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository().getDataBySurveyId(this.$periode.getSurvey().getId(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        TemplateValidationEntity templateValidationEntity = (TemplateValidationEntity) obj;
        if (templateValidationEntity != null) {
            return templateValidationEntity.getTemplate_id();
        }
        return null;
    }
}
