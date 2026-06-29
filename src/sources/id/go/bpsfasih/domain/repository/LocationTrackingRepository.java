package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.domain.model.LocationTracking;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: LocationTrackingRepository.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u000e\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00050\u00122\u0006\u0010\t\u001a\u00020\nH&J'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J!\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J*\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001a2\u0006\u0010\u001b\u001a\u00020\rH¦@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;", "", "deleteLocationsByIds", "", "ids", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldLocations", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationsByAssignment", "Lid/go/bpsfasih/domain/model/LocationTracking;", "assignmentId", "getLocationsByAssignmentAndDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationsByDate", "Lkotlinx/coroutines/flow/Flow;", "getLocationsByUserAndDate", "userId", "getTrackingCountToday", "", "getUnsyncedLocations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveLocation", "Lkotlin/Result;", FirebaseAnalytics.Param.LOCATION, "saveLocation-gIAlu-s", "(Lid/go/bpsfasih/domain/model/LocationTracking;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface LocationTrackingRepository {
    Object deleteLocationsByIds(List<Long> list, Continuation<? super Unit> continuation);

    Object deleteOldLocations(String str, Continuation<? super Unit> continuation);

    Object getLocationsByAssignment(String str, Continuation<? super List<LocationTracking>> continuation);

    Object getLocationsByAssignmentAndDate(String str, String str2, Continuation<? super List<LocationTracking>> continuation);

    Flow<List<LocationTracking>> getLocationsByDate(String date);

    Object getLocationsByUserAndDate(String str, String str2, Continuation<? super List<LocationTracking>> continuation);

    Object getTrackingCountToday(String str, String str2, Continuation<? super Integer> continuation);

    Object getUnsyncedLocations(Continuation<? super List<LocationTracking>> continuation);

    /* renamed from: saveLocation-gIAlu-s */
    Object mo6659saveLocationgIAlus(LocationTracking locationTracking, Continuation<? super Result<Long>> continuation);
}
