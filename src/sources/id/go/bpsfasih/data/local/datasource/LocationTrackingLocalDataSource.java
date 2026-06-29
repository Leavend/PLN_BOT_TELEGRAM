package id.go.bpsfasih.data.local.datasource;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.data.local.dao.LocationTrackingDAO;
import id.go.bpsfasih.data.local.entities.LocationTrackingEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: LocationTrackingLocalDataSource.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ'\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\b0\u00152\u0006\u0010\f\u001a\u00020\rJ'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lid/go/bpsfasih/data/local/datasource/LocationTrackingLocalDataSource;", "", "locationTrackingDAO", "Lid/go/bpsfasih/data/local/dao/LocationTrackingDAO;", "(Lid/go/bpsfasih/data/local/dao/LocationTrackingDAO;)V", "deleteLocationsByIds", "", "ids", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldLocations", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationsByAssignment", "Lid/go/bpsfasih/data/local/entities/LocationTrackingEntity;", "assignmentId", "getLocationsByAssignmentAndDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationsByDate", "Lkotlinx/coroutines/flow/Flow;", "getLocationsByUserAndDate", "userId", "getTrackingCountToday", "", "getUnsyncedLocations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertLocation", FirebaseAnalytics.Param.LOCATION, "(Lid/go/bpsfasih/data/local/entities/LocationTrackingEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationTrackingLocalDataSource {
    public static final int $stable = 8;
    private final LocationTrackingDAO locationTrackingDAO;

    public LocationTrackingLocalDataSource(LocationTrackingDAO locationTrackingDAO) {
        Intrinsics.checkNotNullParameter(locationTrackingDAO, "locationTrackingDAO");
        this.locationTrackingDAO = locationTrackingDAO;
    }

    public final Object insertLocation(LocationTrackingEntity locationTrackingEntity, Continuation<? super Long> continuation) {
        return this.locationTrackingDAO.insert(locationTrackingEntity, continuation);
    }

    public final Flow<List<LocationTrackingEntity>> getLocationsByDate(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.locationTrackingDAO.getLocationsByDate(date);
    }

    public final Object getLocationsByUserAndDate(String str, String str2, Continuation<? super List<LocationTrackingEntity>> continuation) {
        return this.locationTrackingDAO.getLocationsByUserAndDate(str, str2, continuation);
    }

    public final Object getLocationsByAssignment(String str, Continuation<? super List<LocationTrackingEntity>> continuation) {
        return this.locationTrackingDAO.getLocationsByAssignment(str, continuation);
    }

    public final Object getLocationsByAssignmentAndDate(String str, String str2, Continuation<? super List<LocationTrackingEntity>> continuation) {
        return this.locationTrackingDAO.getLocationsByAssignmentAndDate(str, str2, continuation);
    }

    public final Object getUnsyncedLocations(Continuation<? super List<LocationTrackingEntity>> continuation) {
        return this.locationTrackingDAO.getUnsyncedLocations(continuation);
    }

    public final Object deleteLocationsByIds(List<Long> list, Continuation<? super Unit> continuation) {
        Object objDeleteLocationsByIds = this.locationTrackingDAO.deleteLocationsByIds(list, continuation);
        return objDeleteLocationsByIds == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteLocationsByIds : Unit.INSTANCE;
    }

    public final Object deleteOldLocations(String str, Continuation<? super Unit> continuation) {
        Object objDeleteOldLocations = this.locationTrackingDAO.deleteOldLocations(str, continuation);
        return objDeleteOldLocations == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteOldLocations : Unit.INSTANCE;
    }

    public final Object getTrackingCountToday(String str, String str2, Continuation<? super Integer> continuation) {
        return this.locationTrackingDAO.getTrackingCountToday(str, str2, continuation);
    }
}
