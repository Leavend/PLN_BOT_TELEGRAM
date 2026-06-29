package id.go.bpsfasih.ui.daftarwilayah;

import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.remote.dto.CheckNotificationAssignmentModel;
import id.go.bpsfasih.data.remote.dto.CheckNotificationAssignmentResponse;
import id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialActivity;
import id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UpdateListingViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$checkNotificationAssignment$1$1$1", f = "UpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UpdateListingViewModel$checkNotificationAssignment$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CheckNotificationAssignmentResponse $result;
    int label;
    final /* synthetic */ UpdateListingViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdateListingViewModel$checkNotificationAssignment$1$1$1(UpdateListingViewModel updateListingViewModel, CheckNotificationAssignmentResponse checkNotificationAssignmentResponse, Continuation<? super UpdateListingViewModel$checkNotificationAssignment$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = updateListingViewModel;
        this.$result = checkNotificationAssignmentResponse;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdateListingViewModel$checkNotificationAssignment$1$1$1(this.this$0, this.$result, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateListingViewModel$checkNotificationAssignment$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.getShowProgressBarDialog().postValue(Boxing.boxBoolean(true));
        String id2 = this.this$0.getPeriode().getSurvey().getId();
        String id3 = this.this$0.getPeriode().getId();
        CheckNotificationAssignmentModel data = this.$result.getData();
        List<String> idsDownload = data != null ? data.getIdsDownload() : null;
        Intrinsics.checkNotNull(idsDownload);
        final UpdateListingViewModel updateListingViewModel = this.this$0;
        final CheckNotificationAssignmentResponse checkNotificationAssignmentResponse = this.$result;
        new RDAssignmentNotif(id2, id3, idsDownload, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$checkNotificationAssignment$1$1$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, boolean z) {
                if (!z) {
                    Intent intent = new Intent(updateListingViewModel.getActivityNow(), (Class<?>) SyncAnswerPartialActivity.class);
                    String key_list_id_assignment = CommonCons.INSTANCE.getKEY_LIST_ID_ASSIGNMENT();
                    CheckNotificationAssignmentModel data2 = checkNotificationAssignmentResponse.getData();
                    intent.putStringArrayListExtra(key_list_id_assignment, new ArrayList<>(data2 != null ? data2.getIdsDownload() : null));
                    intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), updateListingViewModel.getPeriode().getPrimaryId());
                    updateListingViewModel.getActivityNow().getResultLauncher().launch(intent);
                }
                updateListingViewModel.getShowProgressBarDialog().postValue(false);
            }
        });
        return Unit.INSTANCE;
    }
}
