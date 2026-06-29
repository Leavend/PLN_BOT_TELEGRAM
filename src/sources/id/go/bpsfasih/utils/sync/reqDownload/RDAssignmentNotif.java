package id.go.bpsfasih.utils.sync.reqDownload;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.repository.AssignmentRegionRepository;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.data.remote.dto.AssignmentResponse;
import id.go.bpsfasih.data.remote.dto.DataAssignmentResponse;
import id.go.bpsfasih.data.remote.dto.RegionMetadataResponse;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.data.repository.RegionRepositoryImpl;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONException;

/* compiled from: RDAssignmentNotif.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00128\u0010\u0007\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0002\u0010\u000fJ!\u0010(\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\u0010)\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010*J@\u0010+\u001a\u00020\u000e26\u0010\u0007\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\bH\u0002J\b\u0010,\u001a\u00020\u000eH\u0002J\b\u0010-\u001a\u00020\u000eH\u0002J>\u0010.\u001a\u00020\u000e26\u0010\u0007\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\bRC\u0010\u0007\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0015\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0019R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020#0\"j\b\u0012\u0004\u0012\u00020#`$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010 ¨\u0006/"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDAssignmentNotif;", "", "surveyId", "", "periodeId", "listAssignment", "", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "message", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function2;)V", "getCallback", "()Lkotlin/jvm/functions/Function2;", "count", "", "getCount", "()I", "indexEnd", "getIndexEnd", "setIndexEnd", "(I)V", "indexStart", "getIndexStart", "setIndexStart", "getListAssignment", "()Ljava/util/List;", "getPeriodeId", "()Ljava/lang/String;", "result", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlin/collections/ArrayList;", "sizeAssignment", "getSizeAssignment", "getSurveyId", "formatErrorMessage", "errorCode", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/String;", "insertAssignment", "requestAssignment", "requestAssignmentRecursive", "requestAssignmentRegionNotif", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDAssignmentNotif {
    public static final int $stable = 8;
    private final Function2<String, Boolean, Unit> callback;
    private final int count;
    private int indexEnd;
    private int indexStart;
    private final List<String> listAssignment;
    private final String periodeId;
    private ArrayList<AssignmentEntity> result;
    private final int sizeAssignment;
    private final String surveyId;

    /* JADX WARN: Multi-variable type inference failed */
    public RDAssignmentNotif(String surveyId, String periodeId, List<String> listAssignment, Function2<? super String, ? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(listAssignment, "listAssignment");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.surveyId = surveyId;
        this.periodeId = periodeId;
        this.listAssignment = listAssignment;
        this.callback = callback;
        int size = listAssignment.size();
        this.sizeAssignment = size;
        this.indexStart = -1;
        this.count = 50;
        this.result = new ArrayList<>();
        if (size <= 50) {
            requestAssignment();
        } else {
            requestAssignmentRecursive();
        }
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final List<String> getListAssignment() {
        return this.listAssignment;
    }

    public final Function2<String, Boolean, Unit> getCallback() {
        return this.callback;
    }

    public final int getSizeAssignment() {
        return this.sizeAssignment;
    }

    public final int getIndexStart() {
        return this.indexStart;
    }

    public final void setIndexStart(int i) {
        this.indexStart = i;
    }

    public final int getIndexEnd() {
        return this.indexEnd;
    }

    public final void setIndexEnd(int i) {
        this.indexEnd = i;
    }

    public final int getCount() {
        return this.count;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatErrorMessage(String message, Integer errorCode) {
        if (message == null) {
            message = "Tidak dapat mendownload assignment. Periksa koneksi internet atau coba lagi nanti.";
        }
        return (StringsKt.contains((CharSequence) message, (CharSequence) "Error code :", true) || errorCode == null) ? message : message + " (Error code : " + errorCode + ")";
    }

    private final void requestAssignment() {
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                RDAssignmentNotif.requestAssignment$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestAssignment$lambda$0(final RDAssignmentNotif this$0) throws JSONException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new AssignmentRepositoryImpl().getAssignmentNotif(this$0.periodeId, this$0.listAssignment, new Function1<AssignmentResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignment$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentResponse assignmentResponse) {
                invoke2(assignmentResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentResponse assignmentResponse) {
                DataAssignmentResponse data = assignmentResponse != null ? assignmentResponse.getData() : null;
                if ((assignmentResponse != null ? Intrinsics.areEqual((Object) assignmentResponse.getSuccess(), (Object) true) : false) && data != null) {
                    this.this$0.result.addAll(data.getContent());
                    RDAssignmentNotif rDAssignmentNotif = this.this$0;
                    final RDAssignmentNotif rDAssignmentNotif2 = this.this$0;
                    rDAssignmentNotif.insertAssignment(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignment$1$1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                            invoke(str, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String message, boolean z) {
                            Intrinsics.checkNotNullParameter(message, "message");
                            rDAssignmentNotif2.getCallback().invoke(message, Boolean.valueOf(z));
                        }
                    });
                    return;
                }
                this.this$0.getCallback().invoke(this.this$0.formatErrorMessage(assignmentResponse != null ? assignmentResponse.getMessage() : null, assignmentResponse != null ? assignmentResponse.getErrorCode() : null), true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestAssignmentRecursive() {
        int i = this.indexStart;
        if (i == -1) {
            this.indexStart = 0;
        } else {
            this.indexStart = i + this.count;
        }
        int i2 = this.sizeAssignment;
        int i3 = this.indexEnd;
        int i4 = this.count;
        if (i2 > i3 + i4) {
            this.indexEnd = i3 + i4;
        } else {
            this.indexEnd = i2;
        }
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                RDAssignmentNotif.requestAssignmentRecursive$lambda$1(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestAssignmentRecursive$lambda$1(final RDAssignmentNotif this$0) throws JSONException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new AssignmentRepositoryImpl().getAssignmentNotif(this$0.periodeId, this$0.listAssignment.subList(this$0.indexStart, this$0.indexEnd), new Function1<AssignmentResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignmentRecursive$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentResponse assignmentResponse) {
                invoke2(assignmentResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentResponse assignmentResponse) {
                DataAssignmentResponse data = assignmentResponse != null ? assignmentResponse.getData() : null;
                if ((assignmentResponse != null ? Intrinsics.areEqual((Object) assignmentResponse.getSuccess(), (Object) true) : false) && data != null) {
                    this.this$0.result.addAll(data.getContent());
                    if (this.this$0.getIndexEnd() != this.this$0.getSizeAssignment()) {
                        this.this$0.requestAssignmentRecursive();
                        return;
                    }
                    RDAssignmentNotif rDAssignmentNotif = this.this$0;
                    final RDAssignmentNotif rDAssignmentNotif2 = this.this$0;
                    rDAssignmentNotif.insertAssignment(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignmentRecursive$1$1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                            invoke(str, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String message, boolean z) {
                            Intrinsics.checkNotNullParameter(message, "message");
                            rDAssignmentNotif2.getCallback().invoke(message, Boolean.valueOf(z));
                        }
                    });
                    return;
                }
                this.this$0.getCallback().invoke(this.this$0.formatErrorMessage(assignmentResponse != null ? assignmentResponse.getMessage() : null, assignmentResponse != null ? assignmentResponse.getErrorCode() : null), true);
            }
        });
    }

    /* compiled from: RDAssignmentNotif.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$insertAssignment$1", f = "RDAssignmentNotif.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$insertAssignment$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<String, Boolean, Unit> $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function2<? super String, ? super Boolean, Unit> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$callback = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RDAssignmentNotif.this.new AnonymousClass1(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<AssignmentEntity> listMapIdToAssignment = AssignmentEntity.INSTANCE.mapIdToAssignment(RDAssignmentNotif.this.getSurveyId(), RDAssignmentNotif.this.getPeriodeId(), RDAssignmentNotif.this.result);
                AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                final RDAssignmentNotif rDAssignmentNotif = RDAssignmentNotif.this;
                final Function2<String, Boolean, Unit> function2 = this.$callback;
                assignmentRepository.insertAllData(listMapIdToAssignment, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif.insertAssignment.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        RDAssignmentNotif rDAssignmentNotif2 = rDAssignmentNotif;
                        final Function2<String, Boolean, Unit> function22 = function2;
                        rDAssignmentNotif2.requestAssignmentRegionNotif(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif.insertAssignment.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                                invoke(str, bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(String message, boolean z) {
                                Intrinsics.checkNotNullParameter(message, "message");
                                function22.invoke(message, Boolean.valueOf(z));
                            }
                        });
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertAssignment(Function2<? super String, ? super Boolean, Unit> callback) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(callback, null), 3, null);
    }

    public final void requestAssignmentRegionNotif(final Function2<? super String, ? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        new RegionRepositoryImpl().getAssignmentRegion(this.periodeId, new Function1<AssignmentRegionResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif.requestAssignmentRegionNotif.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
                invoke2(assignmentRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
                String message;
                if (assignmentRegionResponse != null ? Intrinsics.areEqual((Object) assignmentRegionResponse.getSuccess(), (Object) true) : false) {
                    List<AssignmentRegionEntity> data = assignmentRegionResponse.getData();
                    if (!(data == null || data.isEmpty())) {
                        if (assignmentRegionResponse != null) {
                            final RDAssignmentNotif rDAssignmentNotif = this;
                            final Function2<String, Boolean, Unit> function2 = callback;
                            final List<AssignmentRegionEntity> listMapIdToAssignmentRegion = AssignmentRegionEntity.INSTANCE.mapIdToAssignmentRegion(assignmentRegionResponse.getData());
                            DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().deleteByPeriode(rDAssignmentNotif.getPeriodeId(), new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignmentRegionNotif$1$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    AssignmentRegionRepository assignmentRegionRepository = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository();
                                    List<AssignmentRegionEntity> list = listMapIdToAssignmentRegion;
                                    final RDAssignmentNotif rDAssignmentNotif2 = rDAssignmentNotif;
                                    final Function2<String, Boolean, Unit> function22 = function2;
                                    assignmentRegionRepository.insertAll(list, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignmentRegionNotif$1$1$1$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            RegionRepositoryImpl regionRepositoryImpl = new RegionRepositoryImpl();
                                            String periodeId = rDAssignmentNotif2.getPeriodeId();
                                            final Function2<String, Boolean, Unit> function23 = function22;
                                            final RDAssignmentNotif rDAssignmentNotif3 = rDAssignmentNotif2;
                                            regionRepositoryImpl.getRegionMetadata(periodeId, new Function1<RegionMetadataResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif.requestAssignmentRegionNotif.1.1.1.1.1.1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                /* JADX WARN: Multi-variable type inference failed */
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(RegionMetadataResponse regionMetadataResponse) {
                                                    invoke2(regionMetadataResponse);
                                                    return Unit.INSTANCE;
                                                }

                                                /* compiled from: RDAssignmentNotif.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                                @DebugMetadata(c = "id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignmentRegionNotif$1$1$1$1$1$1$1", f = "RDAssignmentNotif.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {})
                                                /* renamed from: id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif$requestAssignmentRegionNotif$1$1$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                                static final class C02661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ Function2<String, Boolean, Unit> $callback;
                                                    final /* synthetic */ RegionMetadataResponse $metadataResult;
                                                    int label;
                                                    final /* synthetic */ RDAssignmentNotif this$0;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    /* JADX WARN: Multi-variable type inference failed */
                                                    C02661(RDAssignmentNotif rDAssignmentNotif, RegionMetadataResponse regionMetadataResponse, Function2<? super String, ? super Boolean, Unit> function2, Continuation<? super C02661> continuation) {
                                                        super(2, continuation);
                                                        this.this$0 = rDAssignmentNotif;
                                                        this.$metadataResult = regionMetadataResponse;
                                                        this.$callback = function2;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new C02661(this.this$0, this.$metadataResult, this.$callback, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((C02661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object obj) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        int i = this.label;
                                                        if (i == 0) {
                                                            ResultKt.throwOnFailure(obj);
                                                            this.label = 1;
                                                            if (DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().updateRegionMetadataByPeriode(this.this$0.getPeriodeId(), this.$metadataResult.getData(), this) == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                        } else {
                                                            if (i != 1) {
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            ResultKt.throwOnFailure(obj);
                                                        }
                                                        this.$callback.invoke("Sukses menyimpan data", Boxing.boxBoolean(false));
                                                        return Unit.INSTANCE;
                                                    }
                                                }

                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(RegionMetadataResponse regionMetadataResponse) {
                                                    String message2;
                                                    if ((regionMetadataResponse != null ? Intrinsics.areEqual((Object) regionMetadataResponse.getSuccess(), (Object) true) : false) && regionMetadataResponse.getData() != null) {
                                                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C02661(rDAssignmentNotif3, regionMetadataResponse, function23, null), 3, null);
                                                        return;
                                                    }
                                                    Function2<String, Boolean, Unit> function24 = function23;
                                                    if (regionMetadataResponse == null || (message2 = regionMetadataResponse.getMessage()) == null) {
                                                        message2 = "Gagal unduh Metadata Wilayah";
                                                    }
                                                    function24.invoke(message2, true);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        return;
                    }
                }
                Function2<String, Boolean, Unit> function22 = callback;
                if (assignmentRegionResponse == null || (message = assignmentRegionResponse.getMessage()) == null) {
                    message = "Gagal unduh Assignment Region";
                }
                function22.invoke(message, true);
            }
        });
    }
}
