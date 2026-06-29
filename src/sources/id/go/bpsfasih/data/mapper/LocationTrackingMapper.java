package id.go.bpsfasih.data.mapper;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.LocationTrackingEntity;
import id.go.bpsfasih.domain.model.LocationTracking;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.http.cookie.ClientCookie;

/* compiled from: LocationTrackingMapper.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/data/mapper/LocationTrackingMapper;", "", "()V", "toDomain", "Lid/go/bpsfasih/domain/model/LocationTracking;", "entity", "Lid/go/bpsfasih/data/local/entities/LocationTrackingEntity;", "toDomainList", "", "entities", "toEntity", ClientCookie.DOMAIN_ATTR, "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationTrackingMapper {
    public static final int $stable = 0;
    public static final LocationTrackingMapper INSTANCE = new LocationTrackingMapper();

    private LocationTrackingMapper() {
    }

    public final LocationTracking toDomain(LocationTrackingEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        long id2 = entity.getId();
        String publicId = entity.getPublicId();
        String userId = entity.getUserId();
        String assignmentId = entity.getAssignmentId();
        String surveyPeriodeId = entity.getSurveyPeriodeId();
        String activity = entity.getActivity();
        String session = entity.getSession();
        if (StringsKt.isBlank(session)) {
            byte[] bytes = ("legacy-session-" + entity.getId()).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            session = UUID.nameUUIDFromBytes(bytes).toString();
            Intrinsics.checkNotNullExpressionValue(session, "nameUUIDFromBytes(\"legac…toByteArray()).toString()");
        }
        return new LocationTracking(id2, publicId, userId, assignmentId, surveyPeriodeId, activity, session, entity.getLatitude(), entity.getLongitude(), entity.getTimestamp(), entity.getAccuracy(), entity.getDate(), entity.isSynced());
    }

    public final LocationTrackingEntity toEntity(LocationTracking domain) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        return new LocationTrackingEntity(domain.getId(), domain.getPublicId(), domain.getUserId(), domain.getAssignmentId(), domain.getSurveyPeriodeId(), domain.getActivity(), domain.getSessionId(), domain.getLatitude(), domain.getLongitude(), domain.getTimestamp(), domain.getAccuracy(), domain.getDate(), domain.isSynced());
    }

    public final List<LocationTracking> toDomainList(List<LocationTrackingEntity> entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        List<LocationTrackingEntity> list = entities;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(INSTANCE.toDomain((LocationTrackingEntity) it.next()));
        }
        return arrayList;
    }
}
