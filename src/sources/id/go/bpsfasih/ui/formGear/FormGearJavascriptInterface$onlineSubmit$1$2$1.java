package id.go.bpsfasih.ui.formGear;

import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.Region;
import id.go.bpsfasih.data.local.models.AssignmentSubmitS3Response;
import id.go.bpsfasih.data.local.models.BaseResponseDataUpload;
import id.go.bpsfasih.data.local.models.PresignedUrlsItem;
import id.go.bpsfasih.data.local.repository.AssignmentUploadRepository;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: FormGearJavascriptInterface.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "s3response", "Lid/go/bpsfasih/data/local/models/AssignmentSubmitS3Response;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class FormGearJavascriptInterface$onlineSubmit$1$2$1 extends Lambda implements Function1<AssignmentSubmitS3Response, Unit> {
    final /* synthetic */ AssignmentEntity $assignmentEntity;
    final /* synthetic */ List<String> $fileNames;
    final /* synthetic */ FormGearJavascriptInterface this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FormGearJavascriptInterface$onlineSubmit$1$2$1(FormGearJavascriptInterface formGearJavascriptInterface, List<String> list, AssignmentEntity assignmentEntity) {
        super(1);
        this.this$0 = formGearJavascriptInterface;
        this.$fileNames = list;
        this.$assignmentEntity = assignmentEntity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AssignmentSubmitS3Response assignmentSubmitS3Response) throws Throwable {
        invoke2(assignmentSubmitS3Response);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final AssignmentSubmitS3Response assignmentSubmitS3Response) throws Throwable {
        PresignedUrlsItem presignedUrlsItem;
        Log.d(">>> uload assignment", new Gson().toJson(assignmentSubmitS3Response));
        boolean z = true;
        if (!(assignmentSubmitS3Response != null ? Intrinsics.areEqual((Object) assignmentSubmitS3Response.getSuccess(), (Object) true) : false)) {
            FormGearActivity formGearActivity = this.this$0.activity;
            if (formGearActivity != null) {
                final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface$onlineSubmit$1$2$1.invoke$lambda$1(formGearJavascriptInterface, assignmentSubmitS3Response);
                    }
                });
                return;
            }
            return;
        }
        List<PresignedUrlsItem> presignedUrls = assignmentSubmitS3Response.getData().getPresignedUrls();
        String presignedUrl = (presignedUrls == null || (presignedUrlsItem = (PresignedUrlsItem) CollectionsKt.firstOrNull((List) presignedUrls)) == null) ? null : presignedUrlsItem.getPresignedUrl();
        String str = presignedUrl;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z) {
            FormGearActivity formGearActivity2 = this.this$0.activity;
            final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
            formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface$onlineSubmit$1$2$1.invoke$lambda$0(formGearJavascriptInterface2);
                }
            });
        } else {
            Pair pairCreate7zFile = this.this$0.create7zFile();
            new AssignmentRepositoryImpl().assignmentSubmitS3Upload(presignedUrl, (File) pairCreate7zFile.component1(), new AnonymousClass2(this.$fileNames, (String) pairCreate7zFile.component2(), this.$assignmentEntity, this.this$0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.hideProgressBar();
        Toast.makeText(this$0.activity, "URL upload data tidak tersedia", 0).show();
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends Lambda implements Function1<Boolean, Unit> {
        final /* synthetic */ AssignmentEntity $assignmentEntity;
        final /* synthetic */ List<String> $fileNames;
        final /* synthetic */ String $md5;
        final /* synthetic */ FormGearJavascriptInterface this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List<String> list, String str, AssignmentEntity assignmentEntity, FormGearJavascriptInterface formGearJavascriptInterface) {
            super(1);
            this.$fileNames = list;
            this.$md5 = str;
            this.$assignmentEntity = assignmentEntity;
            this.this$0 = formGearJavascriptInterface;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        /* compiled from: FormGearJavascriptInterface.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "response", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends Lambda implements Function1<BaseResponseDataUpload, Unit> {
            final /* synthetic */ FormGearJavascriptInterface this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(FormGearJavascriptInterface formGearJavascriptInterface) {
                super(1);
                this.this$0 = formGearJavascriptInterface;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                invoke2(baseResponseDataUpload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final BaseResponseDataUpload baseResponseDataUpload) {
                if (baseResponseDataUpload != null ? Intrinsics.areEqual((Object) baseResponseDataUpload.getSuccess(), (Object) true) : false) {
                    FormGearActivity formGearActivity = this.this$0.activity;
                    if (formGearActivity != null) {
                        final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                        formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                FormGearJavascriptInterface$onlineSubmit$1$2$1.AnonymousClass2.AnonymousClass1.invoke$lambda$0(formGearJavascriptInterface);
                            }
                        });
                    }
                    Log.d(">>> verification", String.valueOf(baseResponseDataUpload != null ? baseResponseDataUpload.getMessage() : null));
                    try {
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02202(baseResponseDataUpload, this.this$0, null), 2, null);
                        return;
                    } catch (Exception e) {
                        Log.d("FOUR", "onlinew: " + e);
                        FormGearActivity formGearActivity2 = this.this$0.activity;
                        final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
                        formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2$1$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                FormGearJavascriptInterface$onlineSubmit$1$2$1.AnonymousClass2.AnonymousClass1.invoke$lambda$1(formGearJavascriptInterface2);
                            }
                        });
                        return;
                    }
                }
                FormGearActivity formGearActivity3 = this.this$0.activity;
                if (formGearActivity3 != null) {
                    final FormGearJavascriptInterface formGearJavascriptInterface3 = this.this$0;
                    formGearActivity3.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2$1$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface$onlineSubmit$1$2$1.AnonymousClass2.AnonymousClass1.invoke$lambda$2(formGearJavascriptInterface3, baseResponseDataUpload);
                        }
                    });
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                FormGearActivity formGearActivity = this$0.activity;
                if (formGearActivity != null) {
                    formGearActivity.hideProgressBar();
                }
                FormGearActivity formGearActivity2 = this$0.activity;
                if (formGearActivity2 != null) {
                    formGearActivity2.finish();
                }
            }

            /* compiled from: FormGearJavascriptInterface.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2$1$2", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2$1$2, reason: invalid class name and collision with other inner class name */
            static final class C02202 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ BaseResponseDataUpload $response;
                int label;
                final /* synthetic */ FormGearJavascriptInterface this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02202(BaseResponseDataUpload baseResponseDataUpload, FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super C02202> continuation) {
                    super(2, continuation);
                    this.$response = baseResponseDataUpload;
                    this.this$0 = formGearJavascriptInterface;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C02202(this.$response, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02202) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateAssignmentAfterUpload(this.$response.getData());
                    AssignmentUploadRepository assignmentUploadRepository = DataSurvey.AssignmentUpload.INSTANCE.getAssignmentUploadRepository();
                    FormGearActivity formGearActivity = this.this$0.activity;
                    assignmentUploadRepository.updateUploadSuccessful(formGearActivity != null ? formGearActivity.getMAssignmentId() : null);
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$1(FormGearJavascriptInterface this$0) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Toast.makeText(this$0.activity, "Kesalahan saat update data di perangkat", 0).show();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$2(FormGearJavascriptInterface this$0, BaseResponseDataUpload baseResponseDataUpload) {
                String message;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                FormGearActivity formGearActivity = this$0.activity;
                if (formGearActivity != null) {
                    formGearActivity.hideProgressBar();
                }
                FormGearActivity formGearActivity2 = this$0.activity;
                if (baseResponseDataUpload == null || (message = baseResponseDataUpload.getMessage()) == null) {
                    message = "Gagal mengirimkan data ke server";
                }
                Toast.makeText(formGearActivity2, message, 0).show();
            }
        }

        public final void invoke(boolean z) {
            if (!z) {
                FormGearActivity formGearActivity = this.this$0.activity;
                if (formGearActivity != null) {
                    final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                    formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$2$1$2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface$onlineSubmit$1$2$1.AnonymousClass2.invoke$lambda$0(formGearJavascriptInterface);
                        }
                    });
                    return;
                }
                return;
            }
            AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
            String str = this.$fileNames.get(0);
            String str2 = this.$md5;
            String periodeNotPrimary = this.$assignmentEntity.getPeriodeNotPrimary();
            Intrinsics.checkNotNull(periodeNotPrimary);
            String id2 = this.$assignmentEntity.getId();
            Intrinsics.checkNotNull(id2);
            boolean zIsNew = this.$assignmentEntity.isNew();
            Region region = this.$assignmentEntity.getRegion();
            String id3 = region != null ? region.getId() : null;
            Intrinsics.checkNotNull(id3);
            assignmentRepositoryImpl.assignmentSubmitS3Post(str, str2, periodeNotPrimary, id2, zIsNew, false, id3, this.$assignmentEntity.getData1(), this.$assignmentEntity.getData2(), this.$assignmentEntity.getData3(), this.$assignmentEntity.getData4(), this.$assignmentEntity.getData5(), this.$assignmentEntity.getData6(), this.$assignmentEntity.getData7(), this.$assignmentEntity.getData8(), this.$assignmentEntity.getData9(), this.$assignmentEntity.getData10(), this.$assignmentEntity.getLatitude(), this.$assignmentEntity.getLongitude(), this.$assignmentEntity.getCopyFromId(), this.$assignmentEntity.getParadata(), this.$assignmentEntity.getComment(), this.$assignmentEntity.getNote(), new AnonymousClass1(this.this$0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            FormGearActivity formGearActivity = this$0.activity;
            if (formGearActivity != null) {
                formGearActivity.hideProgressBar();
            }
            Toast.makeText(this$0.activity, "Kesalahan saat upload data", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(FormGearJavascriptInterface this$0, AssignmentSubmitS3Response assignmentSubmitS3Response) {
        String message;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearActivity formGearActivity = this$0.activity;
        if (formGearActivity != null) {
            formGearActivity.hideProgressBar();
        }
        FormGearActivity formGearActivity2 = this$0.activity;
        if (assignmentSubmitS3Response == null || (message = assignmentSubmitS3Response.getMessage()) == null) {
            message = "Kesalahan saat request presign url";
        }
        Toast.makeText(formGearActivity2, message, 0).show();
    }
}
