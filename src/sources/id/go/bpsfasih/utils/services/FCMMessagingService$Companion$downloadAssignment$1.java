package id.go.bpsfasih.utils.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.io.File;
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

/* compiled from: FCMMessagingService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.services.FCMMessagingService$Companion$downloadAssignment$1", f = "FCMMessagingService.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FCMMessagingService$Companion$downloadAssignment$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $assignmentId;
    final /* synthetic */ String $body;
    final /* synthetic */ Context $context;
    final /* synthetic */ int $notificationId;
    final /* synthetic */ String $title;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FCMMessagingService$Companion$downloadAssignment$1(String str, Context context, String str2, String str3, int i, Continuation<? super FCMMessagingService$Companion$downloadAssignment$1> continuation) {
        super(2, continuation);
        this.$assignmentId = str;
        this.$context = context;
        this.$title = str2;
        this.$body = str3;
        this.$notificationId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FCMMessagingService$Companion$downloadAssignment$1(this.$assignmentId, this.$context, this.$title, this.$body, this.$notificationId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FCMMessagingService$Companion$downloadAssignment$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Uri.parse(new Config().BASE_URL() + "/mobile/assignment-sync/api/mobile/assignment/download-answer-by-assignment-id?assignmentId=" + this.$assignmentId);
            this.label = 1;
            obj = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentOnly(this.$assignmentId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        AssignmentEntity assignmentEntity = (AssignmentEntity) obj;
        if (assignmentEntity != null) {
            String str = this.$assignmentId;
            Context context = this.$context;
            String str2 = this.$title;
            String str3 = this.$body;
            int i2 = this.$notificationId;
            assignmentEntity.setAssignmentStatusId(Boxing.boxInt(CommonCons.INSTANCE.getASSIGNMENT_STATUS_REJECTED()));
            DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateAssignment(assignmentEntity);
            String str4 = (Directory.INSTANCE.getABSOLUTEPATHANSWERPREFIX() + File.separator + assignmentEntity.getSurveyIdNotPrimary() + File.separator + assignmentEntity.getPeriodeNotPrimary() + File.separator) + "answer_" + str + CommonCons.INSTANCE.getEXTENSION_7ZIP();
            Object systemService = context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationCompat.Builder ongoing = new NotificationCompat.Builder(context, context.getString(R.string.channel_id)).setSmallIcon(R.drawable.bps_icon).setContentTitle(str2).setContentText(str3).addAction(R.drawable.ic_check_black_24dp, "Download", PendingIntent.getBroadcast(context, 1, new Intent(context, (Class<?>) NeverEndingReceiver.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL)).setOngoing(true);
            Intrinsics.checkNotNull(ongoing, "null cannot be cast to non-null type androidx.core.app.NotificationCompat.Builder");
            ((NotificationManager) systemService).notify(i2, ongoing.build());
        }
        return Unit.INSTANCE;
    }
}
