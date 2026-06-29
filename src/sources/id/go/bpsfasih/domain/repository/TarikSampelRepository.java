package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.GetSamplingPeriodeResponse;
import id.go.bpsfasih.data.remote.dto.SamplingRegionResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleConfigResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: TarikSampelRepository.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JN\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u00052#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&¢\u0006\u0002\u0010\u000eJ5\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0010\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00030\tH&J5\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0013\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&J5\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00052#\u0010\u0017\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&J¡\u0001\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u00052\b\u0010!\u001a\u0004\u0018\u00010\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u00052\b\u0010#\u001a\u0004\u0018\u00010\u00052\b\u0010$\u001a\u0004\u0018\u00010\u00052\b\u0010%\u001a\u0004\u0018\u00010\u00052#\u0010\u0010\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010&¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00030\tH&¨\u0006'"}, d2 = {"Lid/go/bpsfasih/domain/repository/TarikSampelRepository;", "", "getRegion", "", "surveyPeriodId", "", "fullCodes", "", "samplingRegionCallback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/SamplingRegionResponse;", "Lkotlin/ParameterName;", "name", "result", "(Ljava/lang/String;[Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getSamplingPeriode", "callback", "Lid/go/bpsfasih/data/remote/dto/GetSamplingPeriodeResponse;", "getTarikSample", "tarikSampleCallback", "Lid/go/bpsfasih/data/remote/dto/TarikSampleResponse;", "getTarikSampleConfig", DownloadModel.ID, "tarikSampleConfigCallback", "Lid/go/bpsfasih/data/remote/dto/TarikSampleConfigResponse;", "runTarikSampling", "samplingSurveyPeriodId", "samplingRegionFullCode", "region1Id", "region2Id", "region3Id", "region4Id", "region5Id", "region6Id", "region7Id", "region8Id", "region9Id", "region10Id", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TarikSampelRepository {
    void getRegion(String surveyPeriodId, String[] fullCodes, Function1<? super SamplingRegionResponse, Unit> samplingRegionCallback);

    void getSamplingPeriode(String surveyPeriodId, Function1<? super GetSamplingPeriodeResponse, Unit> callback);

    void getTarikSample(String surveyPeriodId, Function1<? super TarikSampleResponse, Unit> tarikSampleCallback);

    void getTarikSampleConfig(String id2, Function1<? super TarikSampleConfigResponse, Unit> tarikSampleConfigCallback);

    void runTarikSampling(String samplingSurveyPeriodId, String samplingRegionFullCode, String region1Id, String region2Id, String region3Id, String region4Id, String region5Id, String region6Id, String region7Id, String region8Id, String region9Id, String region10Id, Function1<? super BaseResponse, Unit> callback);
}
