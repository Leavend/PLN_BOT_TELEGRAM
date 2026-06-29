package id.go.bpsfasih.domain.usecase;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.model.LocationTracking;
import id.go.bpsfasih.domain.repository.LocationTrackingRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: GetTodayLocationsUseCase.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/domain/usecase/GetTodayLocationsUseCase;", "", "repository", "Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;", "(Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "", "Lid/go/bpsfasih/domain/model/LocationTracking;", "date", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class GetTodayLocationsUseCase {
    public static final int $stable = 8;
    private final LocationTrackingRepository repository;

    public GetTodayLocationsUseCase(LocationTrackingRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public final Flow<List<LocationTracking>> invoke(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.repository.getLocationsByDate(date);
    }
}
