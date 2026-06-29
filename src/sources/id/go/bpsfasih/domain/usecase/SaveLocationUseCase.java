package id.go.bpsfasih.domain.usecase;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.repository.LocationTrackingRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SaveLocationUseCase.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Jl\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\tH\u0086Bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002ø\u0001\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/domain/usecase/SaveLocationUseCase;", "", "repository", "Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;", "(Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;)V", "invoke", "Lkotlin/Result;", "", "userId", "", "assignmentId", "latitude", "", "longitude", "accuracy", "", "date", "activity", "sessionId", "invoke-tZkwj4A", "(Ljava/lang/String;Ljava/lang/String;DDLjava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SaveLocationUseCase {
    public static final int $stable = 8;
    private final LocationTrackingRepository repository;

    public SaveLocationUseCase(LocationTrackingRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* renamed from: invoke-tZkwj4A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6677invoketZkwj4A(java.lang.String r21, java.lang.String r22, double r23, double r25, java.lang.Float r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.Long>> r31) {
        /*
            r20 = this;
            r1 = r20
            r0 = r31
            boolean r2 = r0 instanceof id.go.bpsfasih.domain.usecase.SaveLocationUseCase$invoke$1
            if (r2 == 0) goto L18
            r2 = r0
            id.go.bpsfasih.domain.usecase.SaveLocationUseCase$invoke$1 r2 = (id.go.bpsfasih.domain.usecase.SaveLocationUseCase$invoke$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L18
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L1d
        L18:
            id.go.bpsfasih.domain.usecase.SaveLocationUseCase$invoke$1 r2 = new id.go.bpsfasih.domain.usecase.SaveLocationUseCase$invoke$1
            r2.<init>(r1, r0)
        L1d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L3e
            if (r4 != r5) goto L36
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> L34
            kotlin.Result r0 = (kotlin.Result) r0     // Catch: java.lang.Exception -> L34
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Exception -> L34
            goto L73
        L34:
            r0 = move-exception
            goto L67
        L36:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L3e:
            kotlin.ResultKt.throwOnFailure(r0)
            id.go.bpsfasih.domain.model.LocationTracking$Companion r6 = id.go.bpsfasih.domain.model.LocationTracking.INSTANCE     // Catch: java.lang.Exception -> L34
            r9 = 0
            r18 = 4
            r19 = 0
            r7 = r21
            r8 = r22
            r10 = r29
            r11 = r30
            r12 = r23
            r14 = r25
            r16 = r27
            r17 = r28
            id.go.bpsfasih.domain.model.LocationTracking r0 = id.go.bpsfasih.domain.model.LocationTracking.Companion.create$default(r6, r7, r8, r9, r10, r11, r12, r14, r16, r17, r18, r19)     // Catch: java.lang.Exception -> L34
            id.go.bpsfasih.domain.repository.LocationTrackingRepository r4 = r1.repository     // Catch: java.lang.Exception -> L34
            r2.label = r5     // Catch: java.lang.Exception -> L34
            java.lang.Object r0 = r4.mo6659saveLocationgIAlus(r0, r2)     // Catch: java.lang.Exception -> L34
            if (r0 != r3) goto L73
            return r3
        L67:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m6852constructorimpl(r0)
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.domain.usecase.SaveLocationUseCase.m6677invoketZkwj4A(java.lang.String, java.lang.String, double, double, java.lang.Float, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
