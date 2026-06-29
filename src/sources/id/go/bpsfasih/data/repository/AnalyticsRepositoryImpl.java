package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.api.AnalyticApiService;
import id.go.bpsfasih.data.remote.dto.RecapResponse;
import id.go.bpsfasih.domain.repository.AnalyticsRepository;
import io.reactivex.Observable;
import kotlin.Metadata;

/* compiled from: AnalyticsRepository.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lid/go/bpsfasih/data/repository/AnalyticsRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/AnalyticsRepository;", "()V", "analyticApiService", "Lid/go/bpsfasih/data/remote/api/AnalyticApiService;", "getRecap", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/RecapResponse;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AnalyticsRepositoryImpl implements AnalyticsRepository {
    public static final int $stable = 8;
    private final AnalyticApiService analyticApiService = RetrofitClient.INSTANCE.getAnalyticApiService();

    @Override // id.go.bpsfasih.domain.repository.AnalyticsRepository
    public Observable<RecapResponse> getRecap() {
        return this.analyticApiService.getRecap();
    }
}
