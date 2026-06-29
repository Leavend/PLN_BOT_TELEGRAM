package id.go.bpsfasih.ui.assignmentList;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1$1$1$1;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONException;

/* compiled from: AssignmentUpdateListingViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1$1$1$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class AssignmentUpdateListingViewModel$addAssignment$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AssignmentEntity $assignment;
    int label;
    final /* synthetic */ AssignmentUpdateListingViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssignmentUpdateListingViewModel$addAssignment$1$1$1$1(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, AssignmentEntity assignmentEntity, Continuation<? super AssignmentUpdateListingViewModel$addAssignment$1$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = assignmentUpdateListingViewModel;
        this.$assignment = assignmentEntity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssignmentUpdateListingViewModel$addAssignment$1$1$1$1(this.this$0, this.$assignment, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AssignmentUpdateListingViewModel$addAssignment$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean zIsAssignmentDone = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().isAssignmentDone((String) StringsKt.split$default((CharSequence) this.this$0.getPeriodeId(), new String[]{InternalZipConstants.ZIP_FILE_SEPARATOR}, false, 0, 6, (Object) null).get(0), this.this$0.getRegionId());
        Log.d("isDone", "-> " + zIsAssignmentDone);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(zIsAssignmentDone, this.this$0, this.$assignment, null), 2, null);
        return Unit.INSTANCE;
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1$1$1$1$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1$1$1$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AssignmentEntity $assignment;
        final /* synthetic */ boolean $isDone;
        int label;
        final /* synthetic */ AssignmentUpdateListingViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, AssignmentEntity assignmentEntity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$isDone = z;
            this.this$0 = assignmentUpdateListingViewModel;
            this.$assignment = assignmentEntity;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(View view) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$isDone, this.this$0, this.$assignment, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            Object itemById;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Log.d("isDone", "-> " + this.$isDone);
                if (!this.$isDone) {
                    this.label = 1;
                    itemById = DataSurvey.CustomTemplate.INSTANCE.getCustomTemplateRepo().getItemById(this.this$0.getTemplateId(), this);
                    if (itemById == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    BaseClassActivityNew.showAlertDialog$default(this.this$0.getActivity(), "Info", "Blok sensus ini sudah selesai dilisting.", null, "Ok", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1$1$1$1$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AssignmentUpdateListingViewModel$addAssignment$1$1$1$1.AnonymousClass1.invokeSuspend$lambda$0(view);
                        }
                    }, null, null, false, false, 384, null);
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                itemById = obj;
            }
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            String lowerCase = string.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            AssignmentEntity assignmentEntity = this.$assignment;
            AssignmentEntity assignmentEntityCopy = assignmentEntity.copy((2147374928 & 1) != 0 ? assignmentEntity.idPrimary : lowerCase + File.separator + FasihApp.INSTANCE.getSession().getUserId(), (2147374928 & 2) != 0 ? assignmentEntity.periodeId : null, (2147374928 & 4) != 0 ? assignmentEntity.periodeNotPrimary : null, (2147374928 & 8) != 0 ? assignmentEntity.id : lowerCase, (2147374928 & 16) != 0 ? assignmentEntity.userIdAssignment : null, (2147374928 & 32) != 0 ? assignmentEntity.preDefinedData : null, (2147374928 & 64) != 0 ? assignmentEntity.data : null, (2147374928 & 128) != 0 ? assignmentEntity.assignmentStatusId : Boxing.boxInt(CommonCons.INSTANCE.getASSIGNMENT_STATUS_OPEN()), (2147374928 & 256) != 0 ? assignmentEntity.cawiToken : null, (2147374928 & 512) != 0 ? assignmentEntity.cawiPin : null, (2147374928 & 1024) != 0 ? assignmentEntity.tarikSample : false, (2147374928 & 2048) != 0 ? assignmentEntity.isNew : true, (2147374928 & 4096) != 0 ? assignmentEntity.otherStatus : 0L, (2147374928 & 8192) != 0 ? assignmentEntity.pendingStatus : false, (2147374928 & 16384) != 0 ? assignmentEntity.parentUser : null, (2147374928 & 32768) != 0 ? assignmentEntity.surveyId : null, (2147374928 & 65536) != 0 ? assignmentEntity.surveyIdNotPrimary : null, (2147374928 & 131072) != 0 ? assignmentEntity.provinceId : null, (2147374928 & 262144) != 0 ? assignmentEntity.provinceName : null, (2147374928 & 524288) != 0 ? assignmentEntity.provinceCode : null, (2147374928 & 1048576) != 0 ? assignmentEntity.regencyId : null, (2147374928 & 2097152) != 0 ? assignmentEntity.regencyName : null, (2147374928 & 4194304) != 0 ? assignmentEntity.regencyCode : null, (2147374928 & 8388608) != 0 ? assignmentEntity.districtId : null, (2147374928 & 16777216) != 0 ? assignmentEntity.districtName : null, (2147374928 & 33554432) != 0 ? assignmentEntity.districtFullcode : null, (2147374928 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? assignmentEntity.villageId : null, (2147374928 & 134217728) != 0 ? assignmentEntity.villageName : null, (2147374928 & 268435456) != 0 ? assignmentEntity.villageFullcode : null, (2147374928 & 536870912) != 0 ? assignmentEntity.blokSensusId : null, (2147374928 & 1073741824) != 0 ? assignmentEntity.blokSensusFullCode : null, (2147374928 & Integer.MIN_VALUE) != 0 ? assignmentEntity.data1 : "", (402650096 & 1) != 0 ? assignmentEntity.data2 : "", (402650096 & 2) != 0 ? assignmentEntity.data3 : "", (402650096 & 4) != 0 ? assignmentEntity.data4 : "", (402650096 & 8) != 0 ? assignmentEntity.data5 : "", (402650096 & 16) != 0 ? assignmentEntity.sum_error : null, (402650096 & 32) != 0 ? assignmentEntity.sum_remark : null, (402650096 & 64) != 0 ? assignmentEntity.sum_clean : null, (402650096 & 128) != 0 ? assignmentEntity.latitude : null, (402650096 & 256) != 0 ? assignmentEntity.longitude : null, (402650096 & 512) != 0 ? assignmentEntity.strata : null, (402650096 & 1024) != 0 ? assignmentEntity.currentUserId : FasihApp.INSTANCE.getSession().getUserId(), (402650096 & 2048) != 0 ? assignmentEntity.currentUserUsername : null, (402650096 & 4096) != 0 ? assignmentEntity.currentUserFullname : null, (402650096 & 8192) != 0 ? assignmentEntity.currentUserSurveyRoleId : null, (402650096 & 16384) != 0 ? assignmentEntity.currentUserSurveyRoleName : null, (402650096 & 32768) != 0 ? assignmentEntity.currentUserSurveyRoleIsPencacah : null, (402650096 & 65536) != 0 ? assignmentEntity.currentUserSurveyRoleCanPullSample : null, (402650096 & 131072) != 0 ? assignmentEntity.offlineSend : false, (402650096 & 262144) != 0 ? assignmentEntity.responsibility : null, (402650096 & 524288) != 0 ? assignmentEntity.assignmentResponsibility : null, (402650096 & 1048576) != 0 ? assignmentEntity.assignmentHistories : null, (402650096 & 2097152) != 0 ? assignmentEntity.is_inside_blok_sensus : false, (402650096 & 4194304) != 0 ? assignmentEntity.latitude_if_outside : null, (402650096 & 8388608) != 0 ? assignmentEntity.longitude_if_outside : null, (402650096 & 16777216) != 0 ? assignmentEntity.keterangan_validasi : null, (402650096 & 33554432) != 0 ? assignmentEntity.isDone : null, (402650096 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? assignmentEntity.secondary : null, (402650096 & 134217728) != 0 ? assignmentEntity.copyFromId : this.$assignment.getId(), (402650096 & 268435456) != 0 ? assignmentEntity.original : false, (402650096 & 536870912) != 0 ? assignmentEntity.paradata : null, (402650096 & 1073741824) != 0 ? assignmentEntity.data6 : "", (402650096 & Integer.MIN_VALUE) != 0 ? assignmentEntity.data7 : "", (15584 & 1) != 0 ? assignmentEntity.data8 : "", (15584 & 2) != 0 ? assignmentEntity.data9 : "", (15584 & 4) != 0 ? assignmentEntity.data10 : "", (15584 & 8) != 0 ? assignmentEntity.mode : CollectionsKt.listOf("CAPI"), (15584 & 16) != 0 ? assignmentEntity.comment : "{\"dataKey\": \"\",\"notes\": []}", (15584 & 32) != 0 ? assignmentEntity.basePathComment : null, (15584 & 64) != 0 ? assignmentEntity.regionMetadata : null, (15584 & 128) != 0 ? assignmentEntity.region : null, (15584 & 256) != 0 ? assignmentEntity.assignmentStatusAlias : "OPEN", (15584 & 512) != 0 ? assignmentEntity.note : "", (15584 & 1024) != 0 ? assignmentEntity.isEncrypt : false, (15584 & 2048) != 0 ? assignmentEntity.basePath : null, (15584 & 4096) != 0 ? assignmentEntity.dataDownloadedAt : null, (15584 & 8192) != 0 ? assignmentEntity.submitVersionCode : 0);
            this.this$0.updatePreDefineData(assignmentEntityCopy.getPreDefinedData(), (CustomDataTemplateEntity) itemById, assignmentEntityCopy);
            assignmentEntityCopy.setOriginal(false);
            DataSurvey.Assignment.INSTANCE.getAssignmentRepository().insertData(assignmentEntityCopy);
            this.this$0.insertAssignmentJson(assignmentEntityCopy);
            this.this$0.getAssignmentAdded().postValue(assignmentEntityCopy);
            return Unit.INSTANCE;
        }
    }
}
