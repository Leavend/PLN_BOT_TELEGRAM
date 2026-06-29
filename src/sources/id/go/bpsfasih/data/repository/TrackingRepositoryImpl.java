package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.api.TrackingApiService;
import id.go.bpsfasih.domain.repository.TrackingRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackingRepository.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002ø\u0001\u0002¢\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/data/repository/TrackingRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/TrackingRepository;", "apiService", "Lid/go/bpsfasih/data/remote/api/TrackingApiService;", "(Lid/go/bpsfasih/data/remote/api/TrackingApiService;)V", "createLiveTracking", "Lkotlin/Result;", "", "points", "", "Lid/go/bpsfasih/domain/model/LocationTrackingUploadPoint;", "createLiveTracking-gIAlu-s", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TrackingRepositoryImpl implements TrackingRepository {
    public static final int $stable = 8;
    private final TrackingApiService apiService;

    /* JADX WARN: Multi-variable type inference failed */
    public TrackingRepositoryImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TrackingRepositoryImpl(TrackingApiService apiService) {
        Intrinsics.checkNotNullParameter(apiService, "apiService");
        this.apiService = apiService;
    }

    public /* synthetic */ TrackingRepositoryImpl(TrackingApiService trackingApiService, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? RetrofitClient.INSTANCE.getTrackingApiService() : trackingApiService);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // id.go.bpsfasih.domain.repository.TrackingRepository
    /* renamed from: createLiveTracking-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo6675createLiveTrackinggIAlus(java.util.List<id.go.bpsfasih.domain.model.LocationTrackingUploadPoint> r6, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof id.go.bpsfasih.data.repository.TrackingRepositoryImpl$createLiveTracking$1
            if (r0 == 0) goto L14
            r0 = r7
            id.go.bpsfasih.data.repository.TrackingRepositoryImpl$createLiveTracking$1 r0 = (id.go.bpsfasih.data.repository.TrackingRepositoryImpl$createLiveTracking$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            id.go.bpsfasih.data.repository.TrackingRepositoryImpl$createLiveTracking$1 r0 = new id.go.bpsfasih.data.repository.TrackingRepositoryImpl$createLiveTracking$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L2a
            goto L82
        L2a:
            r6 = move-exception
            goto Lbc
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L47
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            java.lang.Object r6 = kotlin.Result.m6852constructorimpl(r6)
            return r6
        L47:
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch: java.lang.Exception -> L2a
            id.go.bpsfasih.data.mapper.LocationTrackingUploadMapper r7 = id.go.bpsfasih.data.mapper.LocationTrackingUploadMapper.INSTANCE     // Catch: java.lang.Exception -> L2a
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Exception -> L2a
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r4)     // Catch: java.lang.Exception -> L2a
            r2.<init>(r4)     // Catch: java.lang.Exception -> L2a
            java.util.Collection r2 = (java.util.Collection) r2     // Catch: java.lang.Exception -> L2a
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Exception -> L2a
        L5c:
            boolean r4 = r6.hasNext()     // Catch: java.lang.Exception -> L2a
            if (r4 == 0) goto L70
            java.lang.Object r4 = r6.next()     // Catch: java.lang.Exception -> L2a
            id.go.bpsfasih.domain.model.LocationTrackingUploadPoint r4 = (id.go.bpsfasih.domain.model.LocationTrackingUploadPoint) r4     // Catch: java.lang.Exception -> L2a
            id.go.bpsfasih.data.remote.dto.LocationTrackingPointRequest r4 = r7.toRequest(r4)     // Catch: java.lang.Exception -> L2a
            r2.add(r4)     // Catch: java.lang.Exception -> L2a
            goto L5c
        L70:
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Exception -> L2a
            id.go.bpsfasih.data.remote.dto.LocationTrackingPointsRequest r6 = new id.go.bpsfasih.data.remote.dto.LocationTrackingPointsRequest     // Catch: java.lang.Exception -> L2a
            r6.<init>(r2)     // Catch: java.lang.Exception -> L2a
            id.go.bpsfasih.data.remote.api.TrackingApiService r7 = r5.apiService     // Catch: java.lang.Exception -> L2a
            r0.label = r3     // Catch: java.lang.Exception -> L2a
            java.lang.Object r7 = r7.liveTracking(r6, r0)     // Catch: java.lang.Exception -> L2a
            if (r7 != r1) goto L82
            return r1
        L82:
            retrofit2.Response r7 = (retrofit2.Response) r7     // Catch: java.lang.Exception -> L2a
            boolean r6 = r7.isSuccessful()     // Catch: java.lang.Exception -> L2a
            if (r6 == 0) goto L93
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE     // Catch: java.lang.Exception -> L2a
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Exception -> L2a
            java.lang.Object r6 = kotlin.Result.m6852constructorimpl(r6)     // Catch: java.lang.Exception -> L2a
            goto Lc8
        L93:
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE     // Catch: java.lang.Exception -> L2a
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Exception -> L2a
            int r7 = r7.code()     // Catch: java.lang.Exception -> L2a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L2a
            r0.<init>()     // Catch: java.lang.Exception -> L2a
            java.lang.String r1 = "Failed to send live tracking data: HTTP "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L2a
            java.lang.StringBuilder r7 = r0.append(r7)     // Catch: java.lang.Exception -> L2a
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Exception -> L2a
            r6.<init>(r7)     // Catch: java.lang.Exception -> L2a
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch: java.lang.Exception -> L2a
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)     // Catch: java.lang.Exception -> L2a
            java.lang.Object r6 = kotlin.Result.m6852constructorimpl(r6)     // Catch: java.lang.Exception -> L2a
            goto Lc8
        Lbc:
            kotlin.Result$Companion r7 = kotlin.Result.INSTANCE
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m6852constructorimpl(r6)
        Lc8:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.TrackingRepositoryImpl.mo6675createLiveTrackinggIAlus(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
