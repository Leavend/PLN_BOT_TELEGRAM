package id.go.bpsfasih.data.mapper;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.remote.dto.LocationTrackingPointRequest;
import id.go.bpsfasih.domain.model.LocationTrackingUploadPoint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationTrackingUploadMapper.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/data/mapper/LocationTrackingUploadMapper;", "", "()V", "toRequest", "Lid/go/bpsfasih/data/remote/dto/LocationTrackingPointRequest;", "point", "Lid/go/bpsfasih/domain/model/LocationTrackingUploadPoint;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationTrackingUploadMapper {
    public static final int $stable = 0;
    public static final LocationTrackingUploadMapper INSTANCE = new LocationTrackingUploadMapper();

    private LocationTrackingUploadMapper() {
    }

    public final LocationTrackingPointRequest toRequest(LocationTrackingUploadPoint point) {
        Intrinsics.checkNotNullParameter(point, "point");
        return new LocationTrackingPointRequest(point.getId(), point.getSessionId(), point.getLatitude(), point.getLongitude(), point.getAccuracy(), point.getTs(), point.getActivity(), point.getAssignmentId(), point.getSurveyPeriodId());
    }
}
