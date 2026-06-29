package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.data.remote.dto.RegionMetadataResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: RegionRepository.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0006\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0007H&J5\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0007H&J5\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u0007H&J5\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u0007H&¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/domain/repository/RegionRepository;", "", "getAssignmentRegion", "", "surveyPeriodeId", "", "assignmentRegionCallBack", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/AssignmentRegionResponse;", "Lkotlin/ParameterName;", "name", "result", "getRegionMetadata", "callback", "Lid/go/bpsfasih/data/remote/dto/RegionMetadataResponse;", "regionDone", "assignmentRegionEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "regionUndone", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface RegionRepository {
    void getAssignmentRegion(String surveyPeriodeId, Function1<? super AssignmentRegionResponse, Unit> assignmentRegionCallBack);

    void getRegionMetadata(String surveyPeriodeId, Function1<? super RegionMetadataResponse, Unit> callback);

    void regionDone(AssignmentRegionEntity assignmentRegionEntity, Function1<? super AssignmentRegionResponse, Unit> callback);

    void regionUndone(AssignmentRegionEntity assignmentRegionEntity, Function1<? super AssignmentRegionResponse, Unit> callback);
}
