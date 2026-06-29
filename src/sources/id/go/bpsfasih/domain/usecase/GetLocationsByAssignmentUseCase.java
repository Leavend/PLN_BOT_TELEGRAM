package id.go.bpsfasih.domain.usecase;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.model.LocationTracking;
import id.go.bpsfasih.domain.repository.LocationTrackingRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetLocationsByAssignmentUseCase.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086Bø\u0001\u0000¢\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/domain/usecase/GetLocationsByAssignmentUseCase;", "", "repository", "Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;", "(Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;)V", "byDate", "", "Lid/go/bpsfasih/domain/model/LocationTracking;", "assignmentId", "", "date", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invoke", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class GetLocationsByAssignmentUseCase {
    public static final int $stable = 8;
    private final LocationTrackingRepository repository;

    public GetLocationsByAssignmentUseCase(LocationTrackingRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public final Object invoke(String str, Continuation<? super List<LocationTracking>> continuation) {
        return this.repository.getLocationsByAssignment(str, continuation);
    }

    public final Object byDate(String str, String str2, Continuation<? super List<LocationTracking>> continuation) {
        return this.repository.getLocationsByAssignmentAndDate(str, str2, continuation);
    }
}
