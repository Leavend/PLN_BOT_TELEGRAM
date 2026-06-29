package id.go.bpsfasih.domain.usecase;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.domain.model.LocationTracking;
import id.go.bpsfasih.domain.repository.LocationTrackingRepository;
import id.go.bpsfasih.domain.repository.TrackingRepository;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: SyncLocationTrackingUseCase.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dBO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00125\u0010\u0006\u001a1\b\u0001\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007ø\u0001\u0000¢\u0006\u0002\u0010\rJ,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0086Bø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000RB\u0010\u0006\u001a1\b\u0001\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Lid/go/bpsfasih/domain/usecase/SyncLocationTrackingUseCase;", "", "locationTrackingRepository", "Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;", "trackingRepository", "Lid/go/bpsfasih/domain/repository/TrackingRepository;", "resolveSurveyPeriodId", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "assignmentId", "Lkotlin/coroutines/Continuation;", "(Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;Lid/go/bpsfasih/domain/repository/TrackingRepository;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "syncMutex", "Lkotlinx/coroutines/sync/Mutex;", "invoke", "Lkotlin/Result;", "", "batchSize", "invoke-gIAlu-s", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "legacyUploadId", FirebaseAnalytics.Param.LOCATION, "Lid/go/bpsfasih/domain/model/LocationTracking;", "toUploadPoint", "Lid/go/bpsfasih/domain/model/LocationTrackingUploadPoint;", "(Lid/go/bpsfasih/domain/model/LocationTracking;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SyncLocationTrackingUseCase {
    private static final int DEFAULT_BATCH_SIZE = 100;
    private final LocationTrackingRepository locationTrackingRepository;
    private final Function2<String, Continuation<? super String>, Object> resolveSurveyPeriodId;
    private final Mutex syncMutex;
    private final TrackingRepository trackingRepository;
    public static final int $stable = 8;

    /* compiled from: SyncLocationTrackingUseCase.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase", f = "SyncLocationTrackingUseCase.kt", i = {0, 0}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX}, m = "toUploadPoint", n = {"this", FirebaseAnalytics.Param.LOCATION}, s = {"L$0", "L$1"})
    /* renamed from: id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase$toUploadPoint$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncLocationTrackingUseCase.this.toUploadPoint(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SyncLocationTrackingUseCase(LocationTrackingRepository locationTrackingRepository, TrackingRepository trackingRepository, Function2<? super String, ? super Continuation<? super String>, ? extends Object> resolveSurveyPeriodId) {
        Intrinsics.checkNotNullParameter(locationTrackingRepository, "locationTrackingRepository");
        Intrinsics.checkNotNullParameter(trackingRepository, "trackingRepository");
        Intrinsics.checkNotNullParameter(resolveSurveyPeriodId, "resolveSurveyPeriodId");
        this.locationTrackingRepository = locationTrackingRepository;
        this.trackingRepository = trackingRepository;
        this.resolveSurveyPeriodId = resolveSurveyPeriodId;
        this.syncMutex = MutexKt.Mutex$default(false, 1, null);
    }

    /* renamed from: invoke-gIAlu-s$default, reason: not valid java name */
    public static /* synthetic */ Object m6680invokegIAlus$default(SyncLocationTrackingUseCase syncLocationTrackingUseCase, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 100;
        }
        return syncLocationTrackingUseCase.m6681invokegIAlus(i, continuation);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(2:4|(1:6)(1:7))(0)|8|(1:92)|(1:(1:(1:(1:(1:(6:15|16|80|72|88|89)(2:22|23))(5:24|25|26|66|(6:68|(1:70)|71|72|88|89)(9:73|(2:76|74)|97|77|(1:79)|80|72|88|89)))(7:27|93|28|60|61|54|(2:56|(1:58)(5:59|60|61|54|(2:62|(1:64)(4:65|26|66|(0)(0)))(0)))(0)))(4:34|35|50|(3:52|88|89)(3:53|54|(0)(0))))(1:41))(2:42|(1:44)(1:45))|95|46|(1:48)(3:49|50|(0)(0))|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d5, code lost:
    
        r14 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01d6, code lost:
    
        r0 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01d8, code lost:
    
        r14 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01d9, code lost:
    
        r0 = r15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f8 A[Catch: all -> 0x009e, Exception -> 0x00a2, TryCatch #6 {Exception -> 0x00a2, all -> 0x009e, blocks: (B:25:0x005c, B:54:0x011a, B:56:0x0120, B:62:0x014b, B:35:0x009a, B:50:0x00df, B:52:0x00f8, B:53:0x0105), top: B:92:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0105 A[Catch: all -> 0x009e, Exception -> 0x00a2, TryCatch #6 {Exception -> 0x00a2, all -> 0x009e, blocks: (B:25:0x005c, B:54:0x011a, B:56:0x0120, B:62:0x014b, B:35:0x009a, B:50:0x00df, B:52:0x00f8, B:53:0x0105), top: B:92:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0120 A[Catch: all -> 0x009e, Exception -> 0x00a2, TRY_LEAVE, TryCatch #6 {Exception -> 0x00a2, all -> 0x009e, blocks: (B:25:0x005c, B:54:0x011a, B:56:0x0120, B:62:0x014b, B:35:0x009a, B:50:0x00df, B:52:0x00f8, B:53:0x0105), top: B:92:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014b A[Catch: all -> 0x009e, Exception -> 0x00a2, TRY_ENTER, TRY_LEAVE, TryCatch #6 {Exception -> 0x00a2, all -> 0x009e, blocks: (B:25:0x005c, B:54:0x011a, B:56:0x0120, B:62:0x014b, B:35:0x009a, B:50:0x00df, B:52:0x00f8, B:53:0x0105), top: B:92:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016e A[Catch: all -> 0x0042, Exception -> 0x0045, TryCatch #0 {all -> 0x0042, blocks: (B:16:0x003d, B:80:0x01c6, B:86:0x01da, B:66:0x0168, B:68:0x016e, B:70:0x0176, B:71:0x017f, B:73:0x0189, B:74:0x019d, B:76:0x01a3, B:77:0x01b5), top: B:92:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0189 A[Catch: all -> 0x0042, Exception -> 0x0045, TryCatch #0 {all -> 0x0042, blocks: (B:16:0x003d, B:80:0x01c6, B:86:0x01da, B:66:0x0168, B:68:0x016e, B:70:0x0176, B:71:0x017f, B:73:0x0189, B:74:0x019d, B:76:0x01a3, B:77:0x01b5), top: B:92:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase$invoke$1, kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r15v15, types: [id.go.bpsfasih.domain.repository.TrackingRepository] */
    /* JADX WARN: Type inference failed for: r15v3, types: [java.lang.Object, kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4, types: [id.go.bpsfasih.domain.repository.LocationTrackingRepository] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v3, types: [id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x013b -> B:60:0x0141). Please report as a decompilation issue!!! */
    /* renamed from: invoke-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6681invokegIAlus(int r14, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.Integer>> r15) {
        /*
            Method dump skipped, instructions count: 495
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase.m6681invokegIAlus(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object toUploadPoint(id.go.bpsfasih.domain.model.LocationTracking r14, kotlin.coroutines.Continuation<? super id.go.bpsfasih.domain.model.LocationTrackingUploadPoint> r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r15
            id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase$toUploadPoint$1 r0 = (id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase$toUploadPoint$1 r0 = new id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase$toUploadPoint$1
            r0.<init>(r15)
        L19:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r14 = r0.L$1
            id.go.bpsfasih.domain.model.LocationTracking r14 = (id.go.bpsfasih.domain.model.LocationTracking) r14
            java.lang.Object r0 = r0.L$0
            id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase r0 = (id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase) r0
            kotlin.ResultKt.throwOnFailure(r15)
            goto L57
        L32:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L3a:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.String r15 = r14.getSurveyPeriodeId()
            if (r15 != 0) goto L5a
            kotlin.jvm.functions.Function2<java.lang.String, kotlin.coroutines.Continuation<? super java.lang.String>, java.lang.Object> r15 = r13.resolveSurveyPeriodId
            java.lang.String r2 = r14.getAssignmentId()
            r0.L$0 = r13
            r0.L$1 = r14
            r0.label = r3
            java.lang.Object r15 = r15.invoke(r2, r0)
            if (r15 != r1) goto L56
            return r1
        L56:
            r0 = r13
        L57:
            java.lang.String r15 = (java.lang.String) r15
            goto L5b
        L5a:
            r0 = r13
        L5b:
            r12 = r15
            java.lang.String r15 = r14.getPublicId()
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            boolean r1 = kotlin.text.StringsKt.isBlank(r15)
            if (r1 == 0) goto L6c
            java.lang.String r15 = r0.legacyUploadId(r14)
        L6c:
            r1 = r15
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r15 = r14.getSessionId()
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            boolean r0 = kotlin.text.StringsKt.isBlank(r15)
            if (r0 == 0) goto La6
            long r2 = r14.getId()
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r0 = "legacy-session-"
            r15.<init>(r0)
            java.lang.StringBuilder r15 = r15.append(r2)
            java.lang.String r15 = r15.toString()
            java.nio.charset.Charset r0 = kotlin.text.Charsets.UTF_8
            byte[] r15 = r15.getBytes(r0)
            java.lang.String r0 = "this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r0)
            java.util.UUID r15 = java.util.UUID.nameUUIDFromBytes(r15)
            java.lang.String r15 = r15.toString()
            java.lang.String r0 = "nameUUIDFromBytes(\"legac…toByteArray()).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r0)
        La6:
            r2 = r15
            java.lang.String r2 = (java.lang.String) r2
            double r3 = r14.getLatitude()
            double r5 = r14.getLongitude()
            java.lang.Float r15 = r14.getAccuracy()
            if (r15 == 0) goto Lbc
            float r15 = r15.floatValue()
            goto Lbd
        Lbc:
            r15 = 0
        Lbd:
            r7 = r15
            long r8 = r14.getTimestamp()
            r10 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 / r10
            java.lang.String r15 = r14.getActivity()
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            boolean r0 = kotlin.text.StringsKt.isBlank(r15)
            if (r0 == 0) goto Ld3
            java.lang.String r15 = "ENTRY"
        Ld3:
            r10 = r15
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r11 = r14.getAssignmentId()
            id.go.bpsfasih.domain.model.LocationTrackingUploadPoint r14 = new id.go.bpsfasih.domain.model.LocationTrackingUploadPoint
            r0 = r14
            r0.<init>(r1, r2, r3, r5, r7, r8, r10, r11, r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase.toUploadPoint(id.go.bpsfasih.domain.model.LocationTracking, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String legacyUploadId(LocationTracking location) {
        StringBuilder sb = new StringBuilder("legacy-loc|");
        sb.append(location.getUserId());
        sb.append('|');
        String assignmentId = location.getAssignmentId();
        if (assignmentId == null) {
            assignmentId = "";
        }
        sb.append(assignmentId);
        sb.append('|');
        String surveyPeriodeId = location.getSurveyPeriodeId();
        sb.append(surveyPeriodeId != null ? surveyPeriodeId : "");
        sb.append('|');
        sb.append(location.getSessionId());
        sb.append('|');
        sb.append(location.getActivity());
        sb.append('|');
        sb.append(location.getTimestamp());
        sb.append('|');
        sb.append(location.getLatitude());
        sb.append('|');
        sb.append(location.getLongitude());
        sb.append('|');
        Float accuracy = location.getAccuracy();
        sb.append(accuracy != null ? accuracy.floatValue() : 0.0f);
        sb.append('|');
        sb.append(location.getId());
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        String string2 = UUID.nameUUIDFromBytes(bytes).toString();
        Intrinsics.checkNotNullExpressionValue(string2, "nameUUIDFromBytes(raw.toByteArray()).toString()");
        return string2;
    }
}
