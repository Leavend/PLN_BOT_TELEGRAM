package com.kdownloader.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.firebase.messaging.Constants;
import com.kdownloader.database.DbHelper;
import com.kdownloader.database.DownloadModel;
import com.kdownloader.httpclient.HttpClient;
import com.kdownloader.internal.DownloadRequest;
import com.kdownloader.internal.stream.FileDownloadOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: DownloadTask.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0011\u0010!\u001a\u00020\u001fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\u0010H\u0002J\u0013\u0010$\u001a\u0004\u0018\u00010\u001cH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\"J\u0012\u0010%\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\b\u0010&\u001a\u00020\u0010H\u0002J\u0011\u0010'\u001a\u00020\u001fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\"J\u008b\u0001\u0010(\u001a\u00020\u001f2\u000e\b\u0006\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001f0*2#\b\u0006\u0010+\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u001f0,2#\b\u0006\u00100\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\u001f0,2\u000e\b\u0006\u00102\u001a\b\u0012\u0004\u0012\u00020\u001f0*2\u000e\b\u0006\u00103\u001a\b\u0012\u0004\u0012\u00020\u001f0*H\u0086Hø\u0001\u0000¢\u0006\u0002\u00104J\u0019\u0010(\u001a\u00020\u001f2\u0006\u00105\u001a\u000206H\u0086@ø\u0001\u0000¢\u0006\u0002\u00107J\b\u00108\u001a\u00020\u001fH\u0002J\u0019\u00109\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0019\u0010:\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, d2 = {"Lcom/kdownloader/internal/DownloadTask;", "", "req", "Lcom/kdownloader/internal/DownloadRequest;", "dbHelper", "Lcom/kdownloader/database/DbHelper;", "(Lcom/kdownloader/internal/DownloadRequest;Lcom/kdownloader/database/DbHelper;)V", "dbScope", "Lkotlinx/coroutines/CoroutineScope;", "eTag", "", "httpClient", "Lcom/kdownloader/httpclient/HttpClient;", "inputStream", "Ljava/io/InputStream;", "isResumeSupported", "", "lastSyncBytes", "", "lastSyncTime", "outputStream", "Lcom/kdownloader/internal/stream/FileDownloadOutputStream;", "responseCode", "", "tempPath", "totalBytes", "checkIfFreshStartRequiredAndStart", "model", "Lcom/kdownloader/database/DownloadModel;", "(Lcom/kdownloader/database/DownloadModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeAllSafely", "", "(Lcom/kdownloader/internal/stream/FileDownloadOutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createAndInsertNewModel", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTempFile", "getDownloadModelIfAlreadyPresentInDatabase", "isETagChanged", "isSuccessful", "removeNoMoreNeededModelFromDatabase", "run", "onStart", "Lkotlin/Function0;", "onProgress", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "onError", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "onCompleted", "onPause", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/kdownloader/internal/DownloadRequest$Listener;", "(Lcom/kdownloader/internal/DownloadRequest$Listener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setResumeSupportedOrNot", "sync", "syncIfRequired", "Companion", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DownloadTask {
    private static final int BUFFER_SIZE = 4096;
    private static final long MIN_BYTES_FOR_SYNC = 65536;
    private static final long TIME_GAP_FOR_SYNC = 2000;
    private final DbHelper dbHelper;
    private final CoroutineScope dbScope;
    private String eTag;
    private HttpClient httpClient;
    private InputStream inputStream;
    private boolean isResumeSupported;
    private long lastSyncBytes;
    private long lastSyncTime;
    private FileDownloadOutputStream outputStream;
    private final DownloadRequest req;
    private int responseCode;
    private String tempPath;
    private long totalBytes;

    /* compiled from: DownloadTask.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadTask", f = "DownloadTask.kt", i = {0}, l = {285}, m = "checkIfFreshStartRequiredAndStart", n = {"this"}, s = {"L$0"})
    /* renamed from: com.kdownloader.internal.DownloadTask$checkIfFreshStartRequiredAndStart$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DownloadTask.this.checkIfFreshStartRequiredAndStart(null, this);
        }
    }

    /* compiled from: DownloadTask.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadTask", f = "DownloadTask.kt", i = {0}, l = {325}, m = "closeAllSafely", n = {"outputStream"}, s = {"L$0"})
    /* renamed from: com.kdownloader.internal.DownloadTask$closeAllSafely$1, reason: invalid class name and case insensitive filesystem */
    static final class C07071 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07071(Continuation<? super C07071> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DownloadTask.this.closeAllSafely(null, this);
        }
    }

    /* compiled from: DownloadTask.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadTask", f = "DownloadTask.kt", i = {0, 0, 0}, l = {344}, m = "syncIfRequired", n = {"this", "currentBytes", "currentTime"}, s = {"L$0", "J$0", "J$1"})
    /* renamed from: com.kdownloader.internal.DownloadTask$syncIfRequired$1, reason: invalid class name and case insensitive filesystem */
    static final class C07111 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07111(Continuation<? super C07111> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DownloadTask.this.syncIfRequired(null, this);
        }
    }

    public DownloadTask(DownloadRequest req, DbHelper dbHelper) {
        Intrinsics.checkNotNullParameter(req, "req");
        Intrinsics.checkNotNullParameter(dbHelper, "dbHelper");
        this.req = req;
        this.dbHelper = dbHelper;
        this.tempPath = "";
        this.isResumeSupported = true;
        this.eTag = "";
        this.dbScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()).plus(new DownloadTask$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE)));
    }

    public static /* synthetic */ Object run$default(DownloadTask downloadTask, Function0 function0, Function1 function1, Function1 function12, Function0 function02, Function0 function03, Continuation continuation, int i, Object obj) throws Throwable {
        if ((i & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadTask.run.2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function04 = function0;
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Unit>() { // from class: com.kdownloader.internal.DownloadTask.run.3
                public final void invoke(int i2) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 function13 = function1;
        if ((i & 4) != 0) {
            function12 = new Function1<String, Unit>() { // from class: com.kdownloader.internal.DownloadTask.run.4
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str) {
                    Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 function14 = function12;
        if ((i & 8) != 0) {
            function02 = new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadTask.run.5
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function05 = function02;
        if ((i & 16) != 0) {
            function03 = new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadTask.run.6
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        AnonymousClass7 anonymousClass7 = new AnonymousClass7(function04, function13, function14, function05, function03);
        InlineMarker.mark(0);
        downloadTask.run(anonymousClass7, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    /* compiled from: DownloadTask.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016¨\u0006\f"}, d2 = {"com/kdownloader/internal/DownloadTask$run$7", "Lcom/kdownloader/internal/DownloadRequest$Listener;", "onCompleted", "", "onError", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "onPause", "onProgress", "value", "", "onStart", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 176)
    /* renamed from: com.kdownloader.internal.DownloadTask$run$7, reason: invalid class name */
    public static final class AnonymousClass7 implements DownloadRequest.Listener {
        final /* synthetic */ Function0<Unit> $onCompleted;
        final /* synthetic */ Function1<String, Unit> $onError;
        final /* synthetic */ Function0<Unit> $onPause;
        final /* synthetic */ Function1<Integer, Unit> $onProgress;
        final /* synthetic */ Function0<Unit> $onStart;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass7(Function0<Unit> function0, Function1<? super Integer, Unit> function1, Function1<? super String, Unit> function12, Function0<Unit> function02, Function0<Unit> function03) {
            this.$onStart = function0;
            this.$onProgress = function1;
            this.$onError = function12;
            this.$onCompleted = function02;
            this.$onPause = function03;
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onStart() {
            this.$onStart.invoke();
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onProgress(int value) {
            this.$onProgress.invoke(Integer.valueOf(value));
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onError(String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            this.$onError.invoke(error);
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onCompleted() {
            this.$onCompleted.invoke();
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onPause() {
            this.$onPause.invoke();
        }
    }

    private final Object run$$forInline(Function0<Unit> function0, Function1<? super Integer, Unit> function1, Function1<? super String, Unit> function12, Function0<Unit> function02, Function0<Unit> function03, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass7 anonymousClass7 = new AnonymousClass7(function0, function1, function12, function02, function03);
        InlineMarker.mark(0);
        run(anonymousClass7, continuation);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    public final Object run(Function0<Unit> function0, Function1<? super Integer, Unit> function1, Function1<? super String, Unit> function12, Function0<Unit> function02, Function0<Unit> function03, Continuation<? super Unit> continuation) throws Throwable {
        Object objRun = run(new AnonymousClass7(function0, function1, function12, function02, function03), continuation);
        return objRun == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRun : Unit.INSTANCE;
    }

    /* compiled from: DownloadTask.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadTask$createAndInsertNewModel$2", f = "DownloadTask.kt", i = {}, l = {73}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.kdownloader.internal.DownloadTask$createAndInsertNewModel$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadTask.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DbHelper dbHelper = DownloadTask.this.dbHelper;
                int downloadId = DownloadTask.this.req.getDownloadId();
                String url = DownloadTask.this.req.getUrl();
                long totalBytes = DownloadTask.this.req.getTotalBytes();
                this.label = 1;
                if (dbHelper.insert(new DownloadModel(downloadId, url, DownloadTask.this.eTag, null, null, totalBytes, 0L, 0L, 216, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object createAndInsertNewModel(Continuation<? super Unit> continuation) {
        BuildersKt__Builders_commonKt.launch$default(this.dbScope, null, null, new AnonymousClass2(null), 3, null);
        return Unit.INSTANCE;
    }

    /* compiled from: DownloadTask.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadTask$removeNoMoreNeededModelFromDatabase$2", f = "DownloadTask.kt", i = {}, l = {ModuleDescriptor.MODULE_VERSION}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.kdownloader.internal.DownloadTask$removeNoMoreNeededModelFromDatabase$2, reason: invalid class name and case insensitive filesystem */
    static final class C07092 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07092(Continuation<? super C07092> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadTask.this.new C07092(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07092) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DownloadTask.this.dbHelper.remove(DownloadTask.this.req.getDownloadId(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object removeNoMoreNeededModelFromDatabase(Continuation<? super Unit> continuation) {
        BuildersKt__Builders_commonKt.launch$default(this.dbScope, null, null, new C07092(null), 3, null);
        return Unit.INSTANCE;
    }

    /* compiled from: DownloadTask.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadTask$run$9", f = "DownloadTask.kt", i = {0, 0, 1, 1, 2, 2, 2, 3, 3, 11, 11}, l = {97, 111, 129, 155, 264, 264, 184, 264, 264, 205, 264, 222, 264, 239, 264, 264, 264, 264, 264}, m = "invokeSuspend", n = {"$this$withContext", StorageChooser.FILE_PICKER, "$this$withContext", StorageChooser.FILE_PICKER, "$this$withContext", StorageChooser.FILE_PICKER, "model", "$this$withContext", StorageChooser.FILE_PICKER, "$this$withContext", "buff"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1"})
    /* renamed from: com.kdownloader.internal.DownloadTask$run$9, reason: invalid class name */
    static final class AnonymousClass9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DownloadRequest.Listener $listener;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass9(DownloadRequest.Listener listener, Continuation<? super AnonymousClass9> continuation) {
            super(2, continuation);
            this.$listener = listener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass9 anonymousClass9 = DownloadTask.this.new AnonymousClass9(this.$listener, continuation);
            anonymousClass9.L$0 = obj;
            return anonymousClass9;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Path cross not found for [B:52:0x0146, B:60:0x01ca], limit reached: 313 */
        /* JADX WARN: Removed duplicated region for block: B:100:0x02f0 A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:106:0x031a A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:109:0x0331 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:110:0x0332  */
        /* JADX WARN: Removed duplicated region for block: B:114:0x033c A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:117:0x034f A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TRY_LEAVE, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:125:0x0372  */
        /* JADX WARN: Removed duplicated region for block: B:157:0x0421  */
        /* JADX WARN: Removed duplicated region for block: B:158:0x0426  */
        /* JADX WARN: Removed duplicated region for block: B:161:0x0436 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:180:0x0486  */
        /* JADX WARN: Removed duplicated region for block: B:181:0x0488 A[Catch: Exception -> 0x0522, CancellationException -> 0x0526, all -> 0x0762, TryCatch #1 {all -> 0x0762, blocks: (B:236:0x05d1, B:260:0x0697, B:262:0x06a1, B:264:0x06b9, B:178:0x0473, B:218:0x055d, B:220:0x056b, B:228:0x05a2, B:230:0x05b0, B:232:0x05b8, B:233:0x05bc, B:245:0x05f7, B:181:0x0488, B:183:0x0496, B:191:0x04cd, B:193:0x04db, B:195:0x04e3, B:196:0x04e7, B:199:0x04fc, B:212:0x052a, B:214:0x0530, B:215:0x053f, B:217:0x054f, B:253:0x064c, B:255:0x0654, B:257:0x065b, B:272:0x06ca, B:274:0x06d2, B:275:0x06e0, B:286:0x071c), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:202:0x050b  */
        /* JADX WARN: Removed duplicated region for block: B:203:0x0510  */
        /* JADX WARN: Removed duplicated region for block: B:206:0x0520 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:220:0x056b A[Catch: Exception -> 0x0522, CancellationException -> 0x0526, all -> 0x0762, TRY_LEAVE, TryCatch #1 {all -> 0x0762, blocks: (B:236:0x05d1, B:260:0x0697, B:262:0x06a1, B:264:0x06b9, B:178:0x0473, B:218:0x055d, B:220:0x056b, B:228:0x05a2, B:230:0x05b0, B:232:0x05b8, B:233:0x05bc, B:245:0x05f7, B:181:0x0488, B:183:0x0496, B:191:0x04cd, B:193:0x04db, B:195:0x04e3, B:196:0x04e7, B:199:0x04fc, B:212:0x052a, B:214:0x0530, B:215:0x053f, B:217:0x054f, B:253:0x064c, B:255:0x0654, B:257:0x065b, B:272:0x06ca, B:274:0x06d2, B:275:0x06e0, B:286:0x071c), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:228:0x05a2 A[Catch: Exception -> 0x0522, CancellationException -> 0x0526, all -> 0x0762, TRY_ENTER, TryCatch #1 {all -> 0x0762, blocks: (B:236:0x05d1, B:260:0x0697, B:262:0x06a1, B:264:0x06b9, B:178:0x0473, B:218:0x055d, B:220:0x056b, B:228:0x05a2, B:230:0x05b0, B:232:0x05b8, B:233:0x05bc, B:245:0x05f7, B:181:0x0488, B:183:0x0496, B:191:0x04cd, B:193:0x04db, B:195:0x04e3, B:196:0x04e7, B:199:0x04fc, B:212:0x052a, B:214:0x0530, B:215:0x053f, B:217:0x054f, B:253:0x064c, B:255:0x0654, B:257:0x065b, B:272:0x06ca, B:274:0x06d2, B:275:0x06e0, B:286:0x071c), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:239:0x05e0  */
        /* JADX WARN: Removed duplicated region for block: B:240:0x05e5  */
        /* JADX WARN: Removed duplicated region for block: B:243:0x05f5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:262:0x06a1 A[Catch: Exception -> 0x0522, CancellationException -> 0x0526, all -> 0x0762, TryCatch #1 {all -> 0x0762, blocks: (B:236:0x05d1, B:260:0x0697, B:262:0x06a1, B:264:0x06b9, B:178:0x0473, B:218:0x055d, B:220:0x056b, B:228:0x05a2, B:230:0x05b0, B:232:0x05b8, B:233:0x05bc, B:245:0x05f7, B:181:0x0488, B:183:0x0496, B:191:0x04cd, B:193:0x04db, B:195:0x04e3, B:196:0x04e7, B:199:0x04fc, B:212:0x052a, B:214:0x0530, B:215:0x053f, B:217:0x054f, B:253:0x064c, B:255:0x0654, B:257:0x065b, B:272:0x06ca, B:274:0x06d2, B:275:0x06e0, B:286:0x071c), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:263:0x06b8  */
        /* JADX WARN: Removed duplicated region for block: B:274:0x06d2 A[Catch: all -> 0x0762, TryCatch #1 {all -> 0x0762, blocks: (B:236:0x05d1, B:260:0x0697, B:262:0x06a1, B:264:0x06b9, B:178:0x0473, B:218:0x055d, B:220:0x056b, B:228:0x05a2, B:230:0x05b0, B:232:0x05b8, B:233:0x05bc, B:245:0x05f7, B:181:0x0488, B:183:0x0496, B:191:0x04cd, B:193:0x04db, B:195:0x04e3, B:196:0x04e7, B:199:0x04fc, B:212:0x052a, B:214:0x0530, B:215:0x053f, B:217:0x054f, B:253:0x064c, B:255:0x0654, B:257:0x065b, B:272:0x06ca, B:274:0x06d2, B:275:0x06e0, B:286:0x071c), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:278:0x06fe  */
        /* JADX WARN: Removed duplicated region for block: B:281:0x0716 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:289:0x0748  */
        /* JADX WARN: Removed duplicated region for block: B:292:0x0760 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:298:0x076c  */
        /* JADX WARN: Removed duplicated region for block: B:301:0x0784 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:309:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:311:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:313:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:315:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:316:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:317:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x01cd A[Catch: all -> 0x06c0, Exception -> 0x06c6, CancellationException -> 0x0718, TRY_ENTER, TryCatch #7 {CancellationException -> 0x0718, Exception -> 0x06c6, all -> 0x06c0, blocks: (B:50:0x0142, B:62:0x01cd, B:64:0x01d3, B:46:0x0105), top: B:304:0x0105 }] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x021a  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0243 A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0258 A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0279 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:85:0x0282  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x028b A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:91:0x02a4 A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x02b7 A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:97:0x02d8 A[Catch: all -> 0x0438, Exception -> 0x043b, CancellationException -> 0x043e, TryCatch #8 {CancellationException -> 0x043e, Exception -> 0x043b, all -> 0x0438, blocks: (B:154:0x0412, B:112:0x0334, B:114:0x033c, B:115:0x0340, B:117:0x034f, B:126:0x0374, B:128:0x037c, B:130:0x0382, B:132:0x0388, B:134:0x038e, B:135:0x0392, B:136:0x0395, B:138:0x03ae, B:146:0x03e4, B:148:0x03f2, B:150:0x03fa, B:151:0x03fe, B:169:0x0441, B:171:0x0449, B:173:0x0457, B:175:0x045f, B:176:0x0463, B:83:0x027a, B:86:0x0283, B:88:0x028b, B:89:0x028f, B:91:0x02a4, B:92:0x02a8, B:94:0x02b7, B:95:0x02be, B:97:0x02d8, B:98:0x02e6, B:100:0x02f0, B:102:0x02f8, B:103:0x02fc, B:104:0x0312, B:107:0x031c, B:71:0x0204, B:74:0x021d, B:76:0x0243, B:77:0x0247, B:79:0x0258, B:80:0x025c, B:66:0x01da, B:67:0x01ef), top: B:303:0x0014 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:258:0x0694 -> B:306:0x0697). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r24) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 1970
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kdownloader.internal.DownloadTask.AnonymousClass9.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: DownloadTask.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.kdownloader.internal.DownloadTask$run$9$1", f = "DownloadTask.kt", i = {}, l = {223}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.kdownloader.internal.DownloadTask$run$9$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DownloadTask this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(DownloadTask downloadTask, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = downloadTask;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DownloadTask downloadTask = this.this$0;
                    FileDownloadOutputStream fileDownloadOutputStream = downloadTask.outputStream;
                    if (fileDownloadOutputStream == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("outputStream");
                        fileDownloadOutputStream = null;
                    }
                    this.label = 1;
                    if (downloadTask.syncIfRequired(fileDownloadOutputStream, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object run(DownloadRequest.Listener listener, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass9(listener, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setResumeSupportedOrNot() {
        this.isResumeSupported = this.responseCode == 206;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean deleteTempFile() {
        File file = new File(this.tempPath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkIfFreshStartRequiredAndStart(com.kdownloader.database.DownloadModel r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) throws java.lang.IllegalAccessException, java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.kdownloader.internal.DownloadTask.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            com.kdownloader.internal.DownloadTask$checkIfFreshStartRequiredAndStart$1 r0 = (com.kdownloader.internal.DownloadTask.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            com.kdownloader.internal.DownloadTask$checkIfFreshStartRequiredAndStart$1 r0 = new com.kdownloader.internal.DownloadTask$checkIfFreshStartRequiredAndStart$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            com.kdownloader.internal.DownloadTask r5 = (com.kdownloader.internal.DownloadTask) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5a
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            int r6 = r4.responseCode
            r2 = 416(0x1a0, float:5.83E-43)
            if (r6 == r2) goto L4c
            boolean r6 = r4.isETagChanged(r5)
            if (r6 == 0) goto L46
            goto L4c
        L46:
            r5 = 0
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        L4c:
            if (r5 == 0) goto L59
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.removeNoMoreNeededModelFromDatabase(r0)
            if (r5 != r1) goto L59
            return r1
        L59:
            r5 = r4
        L5a:
            r5.deleteTempFile()
            com.kdownloader.internal.DownloadRequest r6 = r5.req
            r0 = 0
            r6.setDownloadedBytes(r0)
            com.kdownloader.internal.DownloadRequest r6 = r5.req
            r6.setTotalBytes(r0)
            com.kdownloader.httpclient.DefaultHttpClient r6 = new com.kdownloader.httpclient.DefaultHttpClient
            r6.<init>()
            com.kdownloader.httpclient.HttpClient r6 = r6.m6560clone()
            r5.httpClient = r6
            r0 = 0
            java.lang.String r1 = "httpClient"
            if (r6 != 0) goto L7d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r6 = r0
        L7d:
            com.kdownloader.internal.DownloadRequest r2 = r5.req
            r6.connect(r2)
            com.kdownloader.httpclient.HttpClient r6 = r5.httpClient
            if (r6 != 0) goto L8a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r6 = r0
        L8a:
            com.kdownloader.internal.DownloadRequest r2 = r5.req
            com.kdownloader.httpclient.HttpClient r6 = com.kdownloader.utils.UtilsKt.getRedirectedConnectionIfAny(r6, r2)
            r5.httpClient = r6
            if (r6 != 0) goto L98
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L99
        L98:
            r0 = r6
        L99:
            int r6 = r0.getResponseCode()
            r5.responseCode = r6
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kdownloader.internal.DownloadTask.checkIfFreshStartRequiredAndStart(com.kdownloader.database.DownloadModel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean isETagChanged(DownloadModel model) {
        if (!(this.eTag.length() == 0) && model != null) {
            if (!(model.getETag().length() == 0) && !Intrinsics.areEqual(model.getETag(), this.eTag)) {
                return true;
            }
        }
        return false;
    }

    /* compiled from: DownloadTask.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/kdownloader/database/DownloadModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadTask$getDownloadModelIfAlreadyPresentInDatabase$2", f = "DownloadTask.kt", i = {}, l = {306}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.kdownloader.internal.DownloadTask$getDownloadModelIfAlreadyPresentInDatabase$2, reason: invalid class name and case insensitive filesystem */
    static final class C07082 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DownloadModel>, Object> {
        int label;

        C07082(Continuation<? super C07082> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadTask.this.new C07082(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DownloadModel> continuation) {
            return ((C07082) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DownloadTask.this.dbHelper.find(DownloadTask.this.req.getDownloadId(), this);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getDownloadModelIfAlreadyPresentInDatabase(Continuation<? super DownloadModel> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07082(null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object closeAllSafely(com.kdownloader.internal.stream.FileDownloadOutputStream r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) throws java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.kdownloader.internal.DownloadTask.C07071
            if (r0 == 0) goto L14
            r0 = r6
            com.kdownloader.internal.DownloadTask$closeAllSafely$1 r0 = (com.kdownloader.internal.DownloadTask.C07071) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            com.kdownloader.internal.DownloadTask$closeAllSafely$1 r0 = new com.kdownloader.internal.DownloadTask$closeAllSafely$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r5 = r0.L$0
            com.kdownloader.internal.stream.FileDownloadOutputStream r5 = (com.kdownloader.internal.stream.FileDownloadOutputStream) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            goto L67
        L2e:
            r6 = move-exception
            goto L79
        L30:
            r6 = move-exception
            goto L70
        L32:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.kdownloader.httpclient.HttpClient r6 = r4.httpClient     // Catch: java.lang.Exception -> L4b
            if (r6 != 0) goto L47
            java.lang.String r6 = "httpClient"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)     // Catch: java.lang.Exception -> L4b
            r6 = 0
        L47:
            r6.close()     // Catch: java.lang.Exception -> L4b
            goto L4f
        L4b:
            r6 = move-exception
            r6.printStackTrace()
        L4f:
            java.io.InputStream r6 = r4.inputStream     // Catch: java.io.IOException -> L58
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.io.IOException -> L58
            r6.close()     // Catch: java.io.IOException -> L58
            goto L5c
        L58:
            r6 = move-exception
            r6.printStackTrace()
        L5c:
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            r0.label = r3     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            java.lang.Object r6 = r4.sync(r5, r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            if (r6 != r1) goto L67
            return r1
        L67:
            r5.close()     // Catch: java.io.IOException -> L6b
            goto L76
        L6b:
            r5 = move-exception
            r5.printStackTrace()
            goto L76
        L70:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L2e
            r5.close()     // Catch: java.io.IOException -> L6b
        L76:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L79:
            r5.close()     // Catch: java.io.IOException -> L7d
            goto L81
        L7d:
            r5 = move-exception
            r5.printStackTrace()
        L81:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kdownloader.internal.DownloadTask.closeAllSafely(com.kdownloader.internal.stream.FileDownloadOutputStream, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object syncIfRequired(com.kdownloader.internal.stream.FileDownloadOutputStream r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof com.kdownloader.internal.DownloadTask.C07111
            if (r2 == 0) goto L18
            r2 = r1
            com.kdownloader.internal.DownloadTask$syncIfRequired$1 r2 = (com.kdownloader.internal.DownloadTask.C07111) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L18
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L1d
        L18:
            com.kdownloader.internal.DownloadTask$syncIfRequired$1 r2 = new com.kdownloader.internal.DownloadTask$syncIfRequired$1
            r2.<init>(r1)
        L1d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L3e
            if (r4 != r5) goto L36
            long r3 = r2.J$1
            long r5 = r2.J$0
            java.lang.Object r2 = r2.L$0
            com.kdownloader.internal.DownloadTask r2 = (com.kdownloader.internal.DownloadTask) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L74
        L36:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L3e:
            kotlin.ResultKt.throwOnFailure(r1)
            com.kdownloader.internal.DownloadRequest r1 = r0.req
            long r6 = r1.getDownloadedBytes()
            long r8 = java.lang.System.currentTimeMillis()
            long r10 = r0.lastSyncBytes
            long r10 = r6 - r10
            long r12 = r0.lastSyncTime
            long r12 = r8 - r12
            r14 = 65536(0x10000, double:3.2379E-319)
            int r1 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r1 <= 0) goto L78
            r10 = 2000(0x7d0, double:9.88E-321)
            int r1 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r1 <= 0) goto L78
            r2.L$0 = r0
            r2.J$0 = r6
            r2.J$1 = r8
            r2.label = r5
            r1 = r17
            java.lang.Object r1 = r0.sync(r1, r2)
            if (r1 != r3) goto L71
            return r3
        L71:
            r2 = r0
            r5 = r6
            r3 = r8
        L74:
            r2.lastSyncBytes = r5
            r2.lastSyncTime = r3
        L78:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kdownloader.internal.DownloadTask.syncIfRequired(com.kdownloader.internal.stream.FileDownloadOutputStream, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object sync(FileDownloadOutputStream fileDownloadOutputStream, Continuation<? super Unit> continuation) {
        boolean z;
        try {
            fileDownloadOutputStream.flushAndSync();
            z = true;
        } catch (IOException e) {
            e.printStackTrace();
            z = false;
        }
        if (z && this.isResumeSupported) {
            Object objUpdateProgress = this.dbHelper.updateProgress(this.req.getDownloadId(), this.req.getDownloadedBytes(), System.currentTimeMillis(), continuation);
            return objUpdateProgress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdateProgress : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSuccessful() {
        int i = this.responseCode;
        return i >= 200 && i < 300;
    }
}
