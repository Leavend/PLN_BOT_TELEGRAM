package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationTrackingPointsRequest.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/LocationTrackingPointsRequest;", "", "points", "", "Lid/go/bpsfasih/data/remote/dto/LocationTrackingPointRequest;", "(Ljava/util/List;)V", "getPoints", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class LocationTrackingPointsRequest {
    public static final int $stable = 8;

    @SerializedName("points")
    private final List<LocationTrackingPointRequest> points;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LocationTrackingPointsRequest copy$default(LocationTrackingPointsRequest locationTrackingPointsRequest, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = locationTrackingPointsRequest.points;
        }
        return locationTrackingPointsRequest.copy(list);
    }

    public final List<LocationTrackingPointRequest> component1() {
        return this.points;
    }

    public final LocationTrackingPointsRequest copy(List<LocationTrackingPointRequest> points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return new LocationTrackingPointsRequest(points);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LocationTrackingPointsRequest) && Intrinsics.areEqual(this.points, ((LocationTrackingPointsRequest) other).points);
    }

    public int hashCode() {
        return this.points.hashCode();
    }

    public String toString() {
        return "LocationTrackingPointsRequest(points=" + this.points + ")";
    }

    public LocationTrackingPointsRequest(List<LocationTrackingPointRequest> points) {
        Intrinsics.checkNotNullParameter(points, "points");
        this.points = points;
    }

    public final List<LocationTrackingPointRequest> getPoints() {
        return this.points;
    }
}
