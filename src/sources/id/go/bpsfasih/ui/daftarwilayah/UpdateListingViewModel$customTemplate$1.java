package id.go.bpsfasih.ui.daftarwilayah;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository;
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

/* compiled from: UpdateListingViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$customTemplate$1", f = "UpdateListingViewModel.kt", i = {}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UpdateListingViewModel$customTemplate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CustomDataTemplateEntity>, Object> {
    final /* synthetic */ String $templateId;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdateListingViewModel$customTemplate$1(String str, Continuation<? super UpdateListingViewModel$customTemplate$1> continuation) {
        super(2, continuation);
        this.$templateId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdateListingViewModel$customTemplate$1(this.$templateId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CustomDataTemplateEntity> continuation) {
        return ((UpdateListingViewModel$customTemplate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CustomDataTemplateRepository customTemplateRepo = DataSurvey.CustomTemplate.INSTANCE.getCustomTemplateRepo();
            String str = this.$templateId;
            if (str == null) {
                str = "";
            }
            this.label = 1;
            obj = customTemplateRepo.getItemById(str, this);
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
