package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource;
import id.go.bpsfasih.data.local.entities.LocationTrackingEntity;
import id.go.bpsfasih.domain.model.LocationTracking;
import id.go.bpsfasih.domain.repository.LocationTrackingRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: LocationTrackingRepositoryImpl.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ'\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b0\u00152\u0006\u0010\f\u001a\u00020\rH\u0016J'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ*\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u001d2\u0006\u0010\u001e\u001a\u00020\u0010H\u0096@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Lid/go/bpsfasih/data/repository/LocationTrackingRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;", "localDataSource", "Lid/go/bpsfasih/data/local/datasource/LocationTrackingLocalDataSource;", "(Lid/go/bpsfasih/data/local/datasource/LocationTrackingLocalDataSource;)V", "deleteLocationsByIds", "", "ids", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldLocations", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationsByAssignment", "Lid/go/bpsfasih/domain/model/LocationTracking;", "assignmentId", "getLocationsByAssignmentAndDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationsByDate", "Lkotlinx/coroutines/flow/Flow;", "getLocationsByUserAndDate", "userId", "getTrackingCountToday", "", "getUnsyncedLocations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveLocation", "Lkotlin/Result;", FirebaseAnalytics.Param.LOCATION, "saveLocation-gIAlu-s", "(Lid/go/bpsfasih/domain/model/LocationTracking;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationTrackingRepositoryImpl implements LocationTrackingRepository {
    public static final int $stable = 8;
    private final LocationTrackingLocalDataSource localDataSource;

    /* compiled from: LocationTrackingRepositoryImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl", f = "LocationTrackingRepositoryImpl.kt", i = {}, l = {35}, m = "getLocationsByAssignment", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignment$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocationTrackingRepositoryImpl.this.getLocationsByAssignment(null, this);
        }
    }

    /* compiled from: LocationTrackingRepositoryImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl", f = "LocationTrackingRepositoryImpl.kt", i = {}, l = {40}, m = "getLocationsByAssignmentAndDate", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignmentAndDate$1, reason: invalid class name and case insensitive filesystem */
    static final class C07831 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C07831(Continuation<? super C07831> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocationTrackingRepositoryImpl.this.getLocationsByAssignmentAndDate(null, null, this);
        }
    }

    /* compiled from: LocationTrackingRepositoryImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl", f = "LocationTrackingRepositoryImpl.kt", i = {}, l = {30}, m = "getLocationsByUserAndDate", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByUserAndDate$1, reason: invalid class name and case insensitive filesystem */
    static final class C07841 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C07841(Continuation<? super C07841> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocationTrackingRepositoryImpl.this.getLocationsByUserAndDate(null, null, this);
        }
    }

    /* compiled from: LocationTrackingRepositoryImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl", f = "LocationTrackingRepositoryImpl.kt", i = {}, l = {45}, m = "getUnsyncedLocations", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getUnsyncedLocations$1, reason: invalid class name and case insensitive filesystem */
    static final class C07851 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C07851(Continuation<? super C07851> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocationTrackingRepositoryImpl.this.getUnsyncedLocations(this);
        }
    }

    public LocationTrackingRepositoryImpl(LocationTrackingLocalDataSource localDataSource) {
        Intrinsics.checkNotNullParameter(localDataSource, "localDataSource");
        this.localDataSource = localDataSource;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    /* renamed from: saveLocation-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo6659saveLocationgIAlus(id.go.bpsfasih.domain.model.LocationTracking r5, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.Long>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$saveLocation$1
            if (r0 == 0) goto L14
            r0 = r6
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$saveLocation$1 r0 = (id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$saveLocation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$saveLocation$1 r0 = new id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$saveLocation$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Exception -> L2a
            goto L48
        L2a:
            r5 = move-exception
            goto L59
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            id.go.bpsfasih.data.mapper.LocationTrackingMapper r6 = id.go.bpsfasih.data.mapper.LocationTrackingMapper.INSTANCE     // Catch: java.lang.Exception -> L2a
            id.go.bpsfasih.data.local.entities.LocationTrackingEntity r5 = r6.toEntity(r5)     // Catch: java.lang.Exception -> L2a
            id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource r6 = r4.localDataSource     // Catch: java.lang.Exception -> L2a
            r0.label = r3     // Catch: java.lang.Exception -> L2a
            java.lang.Object r6 = r6.insertLocation(r5, r0)     // Catch: java.lang.Exception -> L2a
            if (r6 != r1) goto L48
            return r1
        L48:
            java.lang.Number r6 = (java.lang.Number) r6     // Catch: java.lang.Exception -> L2a
            long r5 = r6.longValue()     // Catch: java.lang.Exception -> L2a
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Exception -> L2a
            java.lang.Long r5 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r5)     // Catch: java.lang.Exception -> L2a
            java.lang.Object r5 = kotlin.Result.m6852constructorimpl(r5)     // Catch: java.lang.Exception -> L2a
            goto L65
        L59:
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)
            java.lang.Object r5 = kotlin.Result.m6852constructorimpl(r5)
        L65:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.mo6659saveLocationgIAlus(id.go.bpsfasih.domain.model.LocationTracking, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    public Flow<List<LocationTracking>> getLocationsByDate(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        final Flow<List<LocationTrackingEntity>> locationsByDate = this.localDataSource.getLocationsByDate(date);
        return (Flow) new Flow<List<? extends LocationTracking>>() { // from class: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends LocationTracking>> flowCollector, Continuation continuation) {
                Object objCollect = locationsByDate.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            /* renamed from: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1$2", f = "LocationTrackingRepositoryImpl.kt", i = {}, l = {223}, m = "emit", n = {}, s = {})
                /* renamed from: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r6
                        id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1$2$1 r0 = (id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r6 = r0.label
                        int r6 = r6 - r2
                        r0.label = r6
                        goto L19
                    L14:
                        id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1$2$1 r0 = new id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L19:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L2a:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L32:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlinx.coroutines.flow.FlowCollector r6 = r4.$this_unsafeFlow
                        r2 = r0
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        java.util.List r5 = (java.util.List) r5
                        id.go.bpsfasih.data.mapper.LocationTrackingMapper r2 = id.go.bpsfasih.data.mapper.LocationTrackingMapper.INSTANCE
                        java.util.List r5 = r2.toDomainList(r5)
                        r0.label = r3
                        java.lang.Object r5 = r6.emit(r5, r0)
                        if (r5 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByDate$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getLocationsByUserAndDate(java.lang.String r5, java.lang.String r6, kotlin.coroutines.Continuation<? super java.util.List<id.go.bpsfasih.domain.model.LocationTracking>> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.C07841
            if (r0 == 0) goto L14
            r0 = r7
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByUserAndDate$1 r0 = (id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.C07841) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByUserAndDate$1 r0 = new id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByUserAndDate$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L40
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r7)
            id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource r7 = r4.localDataSource
            r0.label = r3
            java.lang.Object r7 = r7.getLocationsByUserAndDate(r5, r6, r0)
            if (r7 != r1) goto L40
            return r1
        L40:
            java.util.List r7 = (java.util.List) r7
            id.go.bpsfasih.data.mapper.LocationTrackingMapper r5 = id.go.bpsfasih.data.mapper.LocationTrackingMapper.INSTANCE
            java.util.List r5 = r5.toDomainList(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.getLocationsByUserAndDate(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getLocationsByAssignment(java.lang.String r5, kotlin.coroutines.Continuation<? super java.util.List<id.go.bpsfasih.domain.model.LocationTracking>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignment$1 r0 = (id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignment$1 r0 = new id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignment$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L40
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource r6 = r4.localDataSource
            r0.label = r3
            java.lang.Object r6 = r6.getLocationsByAssignment(r5, r0)
            if (r6 != r1) goto L40
            return r1
        L40:
            java.util.List r6 = (java.util.List) r6
            id.go.bpsfasih.data.mapper.LocationTrackingMapper r5 = id.go.bpsfasih.data.mapper.LocationTrackingMapper.INSTANCE
            java.util.List r5 = r5.toDomainList(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.getLocationsByAssignment(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getLocationsByAssignmentAndDate(java.lang.String r5, java.lang.String r6, kotlin.coroutines.Continuation<? super java.util.List<id.go.bpsfasih.domain.model.LocationTracking>> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.C07831
            if (r0 == 0) goto L14
            r0 = r7
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignmentAndDate$1 r0 = (id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.C07831) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignmentAndDate$1 r0 = new id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getLocationsByAssignmentAndDate$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L40
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r7)
            id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource r7 = r4.localDataSource
            r0.label = r3
            java.lang.Object r7 = r7.getLocationsByAssignmentAndDate(r5, r6, r0)
            if (r7 != r1) goto L40
            return r1
        L40:
            java.util.List r7 = (java.util.List) r7
            id.go.bpsfasih.data.mapper.LocationTrackingMapper r5 = id.go.bpsfasih.data.mapper.LocationTrackingMapper.INSTANCE
            java.util.List r5 = r5.toDomainList(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.getLocationsByAssignmentAndDate(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getUnsyncedLocations(kotlin.coroutines.Continuation<? super java.util.List<id.go.bpsfasih.domain.model.LocationTracking>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.C07851
            if (r0 == 0) goto L14
            r0 = r5
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getUnsyncedLocations$1 r0 = (id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.C07851) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getUnsyncedLocations$1 r0 = new id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl$getUnsyncedLocations$1
            r0.<init>(r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L40
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource r5 = r4.localDataSource
            r0.label = r3
            java.lang.Object r5 = r5.getUnsyncedLocations(r0)
            if (r5 != r1) goto L40
            return r1
        L40:
            java.util.List r5 = (java.util.List) r5
            id.go.bpsfasih.data.mapper.LocationTrackingMapper r0 = id.go.bpsfasih.data.mapper.LocationTrackingMapper.INSTANCE
            java.util.List r5 = r0.toDomainList(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl.getUnsyncedLocations(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    public Object deleteLocationsByIds(List<Long> list, Continuation<? super Unit> continuation) {
        Object objDeleteLocationsByIds = this.localDataSource.deleteLocationsByIds(list, continuation);
        return objDeleteLocationsByIds == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteLocationsByIds : Unit.INSTANCE;
    }

    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    public Object deleteOldLocations(String str, Continuation<? super Unit> continuation) {
        Object objDeleteOldLocations = this.localDataSource.deleteOldLocations(str, continuation);
        return objDeleteOldLocations == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteOldLocations : Unit.INSTANCE;
    }

    @Override // id.go.bpsfasih.domain.repository.LocationTrackingRepository
    public Object getTrackingCountToday(String str, String str2, Continuation<? super Integer> continuation) {
        return this.localDataSource.getTrackingCountToday(str, str2, continuation);
    }
}
