package com.kdownloader.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.kdownloader.Status;
import com.kdownloader.database.DbHelper;
import com.kdownloader.internal.DownloadRequest;
import com.kdownloader.utils.UtilsKt;
import io.grpc.internal.GrpcUtil;
import java.io.File;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: DownloadDispatchers.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\tJ\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u0019\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lcom/kdownloader/internal/DownloadDispatchers;", "", "dbHelper", "Lcom/kdownloader/database/DbHelper;", "(Lcom/kdownloader/database/DbHelper;)V", "dbScope", "Lkotlinx/coroutines/CoroutineScope;", "scope", "cancel", "", "req", "Lcom/kdownloader/internal/DownloadRequest;", "cancelAll", "cleanup", "days", "", "enqueue", "execute", "request", "(Lcom/kdownloader/internal/DownloadRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeOnMainThread", "block", "Lkotlin/Function0;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DownloadDispatchers {
    private final DbHelper dbHelper;
    private final CoroutineScope dbScope;
    private final CoroutineScope scope;

    public DownloadDispatchers(DbHelper dbHelper) {
        Intrinsics.checkNotNullParameter(dbHelper, "dbHelper");
        this.dbHelper = dbHelper;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain()).plus(new DownloadDispatchers$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE)));
        this.dbScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()).plus(new DownloadDispatchers$special$$inlined$CoroutineExceptionHandler$2(CoroutineExceptionHandler.INSTANCE)));
    }

    public final int enqueue(DownloadRequest req) {
        Intrinsics.checkNotNullParameter(req, "req");
        req.setJob$library_release(BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new DownloadDispatchers$enqueue$job$1(this, req, null), 3, null));
        return req.getDownloadId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object execute(final DownloadRequest downloadRequest, Continuation<? super Unit> continuation) throws Throwable {
        Object objRun = new DownloadTask(downloadRequest, this.dbHelper).run(new DownloadRequest.Listener(downloadRequest, this, downloadRequest, this, downloadRequest, this, downloadRequest, this, downloadRequest) { // from class: com.kdownloader.internal.DownloadDispatchers$execute$$inlined$run$1
            final /* synthetic */ DownloadRequest $request$inlined;
            final /* synthetic */ DownloadRequest $request$inlined$1;
            final /* synthetic */ DownloadRequest $request$inlined$2;
            final /* synthetic */ DownloadRequest $request$inlined$3;
            final /* synthetic */ DownloadRequest $request$inlined$4;

            {
                this.$request$inlined$1 = downloadRequest;
                this.$request$inlined$2 = downloadRequest;
                this.$request$inlined$3 = downloadRequest;
                this.$request$inlined$4 = downloadRequest;
            }

            @Override // com.kdownloader.internal.DownloadRequest.Listener
            public void onStart() {
                DownloadDispatchers downloadDispatchers = this.this$0;
                final DownloadRequest downloadRequest2 = this.$request$inlined;
                downloadDispatchers.executeOnMainThread(new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadDispatchers$execute$2$1
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
                        DownloadRequest.Listener listener = downloadRequest2.getListener();
                        if (listener != null) {
                            listener.onStart();
                        }
                    }
                });
            }

            @Override // com.kdownloader.internal.DownloadRequest.Listener
            public void onProgress(final int value) {
                DownloadDispatchers downloadDispatchers = this.this$0;
                final DownloadRequest downloadRequest2 = this.$request$inlined$1;
                downloadDispatchers.executeOnMainThread(new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadDispatchers$execute$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        DownloadRequest.Listener listener = downloadRequest2.getListener();
                        if (listener != null) {
                            listener.onProgress(value);
                        }
                    }
                });
            }

            @Override // com.kdownloader.internal.DownloadRequest.Listener
            public void onPause() {
                DownloadDispatchers downloadDispatchers = this.this$0;
                final DownloadRequest downloadRequest2 = this.$request$inlined$4;
                downloadDispatchers.executeOnMainThread(new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadDispatchers$execute$6$1
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
                        DownloadRequest.Listener listener = downloadRequest2.getListener();
                        if (listener != null) {
                            listener.onPause();
                        }
                    }
                });
            }

            @Override // com.kdownloader.internal.DownloadRequest.Listener
            public void onCompleted() {
                DownloadDispatchers downloadDispatchers = this.this$0;
                final DownloadRequest downloadRequest2 = this.$request$inlined$3;
                downloadDispatchers.executeOnMainThread(new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadDispatchers$execute$5$1
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
                        DownloadRequest.Listener listener = downloadRequest2.getListener();
                        if (listener != null) {
                            listener.onCompleted();
                        }
                    }
                });
            }

            @Override // com.kdownloader.internal.DownloadRequest.Listener
            public void onError(final String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DownloadDispatchers downloadDispatchers = this.this$0;
                final DownloadRequest downloadRequest2 = this.$request$inlined$2;
                downloadDispatchers.executeOnMainThread(new Function0<Unit>() { // from class: com.kdownloader.internal.DownloadDispatchers$execute$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        DownloadRequest.Listener listener = downloadRequest2.getListener();
                        if (listener != null) {
                            listener.onError(error);
                        }
                    }
                });
            }
        }, continuation);
        return objRun == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRun : Unit.INSTANCE;
    }

    /* compiled from: DownloadDispatchers.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadDispatchers$executeOnMainThread$1", f = "DownloadDispatchers.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.kdownloader.internal.DownloadDispatchers$executeOnMainThread$1, reason: invalid class name and case insensitive filesystem */
    static final class C07061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07061(Function0<Unit> function0, Continuation<? super C07061> continuation) {
            super(2, continuation);
            this.$block = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07061(this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$block.invoke();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void executeOnMainThread(Function0<Unit> block) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C07061(block, null), 3, null);
    }

    public final void cancel(DownloadRequest req) {
        Intrinsics.checkNotNullParameter(req, "req");
        if (req.getStatus() == Status.PAUSED) {
            File file = new File(UtilsKt.getTempPath(req.getDirPath(), req.getFileName()));
            if (file.exists()) {
                file.delete();
            }
            req.reset();
        }
        req.setStatus$library_release(Status.CANCELLED);
        Job.DefaultImpls.cancel$default(req.getJob$library_release(), (CancellationException) null, 1, (Object) null);
        DownloadRequest.Listener listener = req.getListener();
        if (listener != null) {
            listener.onError("Cancelled");
        }
        BuildersKt__Builders_commonKt.launch$default(this.dbScope, null, null, new AnonymousClass1(req, null), 3, null);
    }

    /* compiled from: DownloadDispatchers.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadDispatchers$cancel$1", f = "DownloadDispatchers.kt", i = {}, l = {73}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.kdownloader.internal.DownloadDispatchers$cancel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DownloadRequest $req;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DownloadRequest downloadRequest, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$req = downloadRequest;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadDispatchers.this.new AnonymousClass1(this.$req, continuation);
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
                this.label = 1;
                if (DownloadDispatchers.this.dbHelper.remove(this.$req.getDownloadId(), this) == coroutine_suspended) {
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

    /* compiled from: DownloadDispatchers.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadDispatchers$cancelAll$1", f = "DownloadDispatchers.kt", i = {}, l = {GrpcUtil.DEFAULT_PORT_PLAINTEXT}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.kdownloader.internal.DownloadDispatchers$cancelAll$1, reason: invalid class name and case insensitive filesystem */
    static final class C07041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07041(Continuation<? super C07041> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadDispatchers.this.new C07041(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DownloadDispatchers.this.dbHelper.empty(this) == coroutine_suspended) {
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

    public final void cancelAll() {
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        BuildersKt__Builders_commonKt.launch$default(this.dbScope, null, null, new C07041(null), 3, null);
    }

    /* compiled from: DownloadDispatchers.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.internal.DownloadDispatchers$cleanup$1", f = "DownloadDispatchers.kt", i = {1}, l = {ModuleDescriptor.MODULE_VERSION, 93}, m = "invokeSuspend", n = {"tempPath"}, s = {"L$1"})
    /* renamed from: com.kdownloader.internal.DownloadDispatchers$cleanup$1, reason: invalid class name and case insensitive filesystem */
    static final class C07051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $days;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07051(int i, Continuation<? super C07051> continuation) {
            super(2, continuation);
            this.$days = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadDispatchers.this.new C07051(this.$days, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0086  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x007a -> B:22:0x007b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L27
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r1 = r7.L$1
                java.lang.String r1 = (java.lang.String) r1
                java.lang.Object r3 = r7.L$0
                java.util.Iterator r3 = (java.util.Iterator) r3
                kotlin.ResultKt.throwOnFailure(r8)
                r8 = r7
                goto L7b
            L1b:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L23:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L3e
            L27:
                kotlin.ResultKt.throwOnFailure(r8)
                com.kdownloader.internal.DownloadDispatchers r8 = com.kdownloader.internal.DownloadDispatchers.this
                com.kdownloader.database.DbHelper r8 = com.kdownloader.internal.DownloadDispatchers.access$getDbHelper$p(r8)
                int r1 = r7.$days
                r4 = r7
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r7.label = r3
                java.lang.Object r8 = r8.getUnwantedModels(r1, r4)
                if (r8 != r0) goto L3e
                return r0
            L3e:
                java.util.List r8 = (java.util.List) r8
                if (r8 == 0) goto L8a
                java.util.Iterator r8 = r8.iterator()
                r3 = r8
                r8 = r7
            L48:
                boolean r1 = r3.hasNext()
                if (r1 == 0) goto L8a
                java.lang.Object r1 = r3.next()
                com.kdownloader.database.DownloadModel r1 = (com.kdownloader.database.DownloadModel) r1
                java.lang.String r4 = r1.getDirPath()
                java.lang.String r5 = r1.getFileName()
                java.lang.String r4 = com.kdownloader.utils.UtilsKt.getTempPath(r4, r5)
                com.kdownloader.internal.DownloadDispatchers r5 = com.kdownloader.internal.DownloadDispatchers.this
                com.kdownloader.database.DbHelper r5 = com.kdownloader.internal.DownloadDispatchers.access$getDbHelper$p(r5)
                int r1 = r1.getId()
                r6 = r8
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r8.L$0 = r3
                r8.L$1 = r4
                r8.label = r2
                java.lang.Object r1 = r5.remove(r1, r6)
                if (r1 != r0) goto L7a
                return r0
            L7a:
                r1 = r4
            L7b:
                java.io.File r4 = new java.io.File
                r4.<init>(r1)
                boolean r1 = r4.exists()
                if (r1 == 0) goto L48
                r4.delete()
                goto L48
            L8a:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kdownloader.internal.DownloadDispatchers.C07051.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void cleanup(int days) {
        BuildersKt__Builders_commonKt.launch$default(this.dbScope, null, null, new C07051(days, null), 3, null);
    }
}
