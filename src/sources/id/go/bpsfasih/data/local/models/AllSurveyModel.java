package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.TemplateLookupList;
import id.go.bpsfasih.domain.models.BaseErrorModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AllSurveyModel.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bm\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BÑ\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018\u0012\u0010\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u0018\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\u0012\b\u0002\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010&\u0018\u00010\u0018\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010(J\u000b\u0010r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010y\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010z\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010{\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010|\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0013\u0010~\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u0018HÆ\u0003J\u000b\u0010\u007f\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\u0011\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u0011\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u0011\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u0011\u0010\u0085\u0001\u001a\u0004\u0018\u00010#HÆ\u0003¢\u0006\u0002\u0010DJ\u0011\u0010\u0086\u0001\u001a\u0004\u0018\u00010#HÆ\u0003¢\u0006\u0002\u0010DJ\u0014\u0010\u0087\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010&\u0018\u00010\u0018HÆ\u0003J\u0011\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\f\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u0011\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010*J\u0011\u0010\u008e\u0001\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u00109J\u0011\u0010\u008f\u0001\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u00109J\u008c\u0003\u0010\u0090\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\u0012\b\u0002\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\u0012\b\u0002\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010&\u0018\u00010\u00182\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0003\u0010\u0091\u0001J\u0016\u0010\u0092\u0001\u001a\u00020\u00062\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u0001HÖ\u0003J\n\u0010\u0095\u0001\u001a\u00020#HÖ\u0001J\n\u0010\u0096\u0001\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010!\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b0\u0010*\"\u0004\b1\u0010,R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b2\u0010*\"\u0004\b3\u0010,R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001e\u0010\r\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010<\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b=\u0010*\"\u0004\b>\u0010,R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010<\u001a\u0004\b?\u00109\"\u0004\b@\u0010;R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u00105\"\u0004\bB\u00107R\u001e\u0010$\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u0010\n\u0002\u0010G\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001c\u0010'\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u00105\"\u0004\bI\u00107R\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010-\u001a\u0004\b\u001f\u0010*R$\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u00105\"\u0004\bO\u00107R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u00105\"\u0004\bQ\u00107R\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\bR\u0010*\"\u0004\bS\u0010,R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u00105\"\u0004\bU\u00107R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\bV\u0010*\"\u0004\bW\u0010,R\u001c\u0010 \u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00105\"\u0004\bY\u00107R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001e\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u0010\n\u0002\u0010G\u001a\u0004\b^\u0010D\"\u0004\b_\u0010FR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u00105\"\u0004\ba\u00107R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u00105\"\u0004\bc\u00107R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u00105\"\u0004\be\u00107R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u00105\"\u0004\bg\u00107R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\bh\u0010*\"\u0004\bi\u0010,R$\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010&\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010K\"\u0004\bk\u0010MR \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010K\"\u0004\bm\u0010MR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\bn\u0010*\"\u0004\bo\u0010,R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u00105\"\u0004\bq\u00107¨\u0006\u0097\u0001"}, d2 = {"Lid/go/bpsfasih/data/local/models/AllSurveyModel;", "Lid/go/bpsfasih/domain/models/BaseErrorModel;", DownloadModel.ID, "", "name", "panelType", "", "areaType", "updateListingType", "canAddSample", "geoLiveTracking", "geoRadius", "", "geoAccuracy", "surveySampleTypeId", "surveySampleMethodId", "userId", "createdDate", "surveyStepTypeId", "surveyParentId", "cawiTokenAutoGenerated", "mfdId", "mfdName", "templatesIds", "", "listPeriode", "Lid/go/bpsfasih/data/local/models/PeriodeList;", "sampleMethod", "Lid/go/bpsfasih/data/local/models/SampleMethod;", "multiPencacah", "tarikSampleExclusive", "isMultiPencacah", "regionGroupId", "backgroundRecord", "smallestRegionType", "", "imageServiceType", "templateLookup", "Lid/go/bpsfasih/data/local/entities/TemplateLookupList;", "imageServiceUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lid/go/bpsfasih/data/local/models/SampleMethod;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;)V", "getAreaType", "()Ljava/lang/Boolean;", "setAreaType", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getBackgroundRecord", "setBackgroundRecord", "getCanAddSample", "setCanAddSample", "getCawiTokenAutoGenerated", "setCawiTokenAutoGenerated", "getCreatedDate", "()Ljava/lang/String;", "setCreatedDate", "(Ljava/lang/String;)V", "getGeoAccuracy", "()Ljava/lang/Long;", "setGeoAccuracy", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getGeoLiveTracking", "setGeoLiveTracking", "getGeoRadius", "setGeoRadius", "getId", "setId", "getImageServiceType", "()Ljava/lang/Integer;", "setImageServiceType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getImageServiceUrl", "setImageServiceUrl", "getListPeriode", "()Ljava/util/List;", "setListPeriode", "(Ljava/util/List;)V", "getMfdId", "setMfdId", "getMfdName", "setMfdName", "getMultiPencacah", "setMultiPencacah", "getName", "setName", "getPanelType", "setPanelType", "getRegionGroupId", "setRegionGroupId", "getSampleMethod", "()Lid/go/bpsfasih/data/local/models/SampleMethod;", "setSampleMethod", "(Lid/go/bpsfasih/data/local/models/SampleMethod;)V", "getSmallestRegionType", "setSmallestRegionType", "getSurveyParentId", "setSurveyParentId", "getSurveySampleMethodId", "setSurveySampleMethodId", "getSurveySampleTypeId", "setSurveySampleTypeId", "getSurveyStepTypeId", "setSurveyStepTypeId", "getTarikSampleExclusive", "setTarikSampleExclusive", "getTemplateLookup", "setTemplateLookup", "getTemplatesIds", "setTemplatesIds", "getUpdateListingType", "setUpdateListingType", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lid/go/bpsfasih/data/local/models/SampleMethod;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;)Lid/go/bpsfasih/data/local/models/AllSurveyModel;", "equals", "other", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AllSurveyModel extends BaseErrorModel {
    public static final int $stable = 8;
    private Boolean areaType;
    private Boolean backgroundRecord;
    private Boolean canAddSample;
    private Boolean cawiTokenAutoGenerated;
    private String createdDate;
    private Long geoAccuracy;
    private Boolean geoLiveTracking;
    private Long geoRadius;
    private String id;
    private Integer imageServiceType;
    private String imageServiceUrl;
    private final Boolean isMultiPencacah;
    private List<PeriodeList> listPeriode;
    private String mfdId;
    private String mfdName;
    private Boolean multiPencacah;
    private String name;
    private Boolean panelType;
    private String regionGroupId;
    private SampleMethod sampleMethod;
    private Integer smallestRegionType;
    private String surveyParentId;
    private String surveySampleMethodId;
    private String surveySampleTypeId;
    private String surveyStepTypeId;
    private Boolean tarikSampleExclusive;
    private List<TemplateLookupList> templateLookup;
    private List<String> templatesIds;
    private Boolean updateListingType;
    private String userId;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getSurveySampleTypeId() {
        return this.surveySampleTypeId;
    }

    /* renamed from: component11, reason: from getter */
    public final String getSurveySampleMethodId() {
        return this.surveySampleMethodId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component13, reason: from getter */
    public final String getCreatedDate() {
        return this.createdDate;
    }

    /* renamed from: component14, reason: from getter */
    public final String getSurveyStepTypeId() {
        return this.surveyStepTypeId;
    }

    /* renamed from: component15, reason: from getter */
    public final String getSurveyParentId() {
        return this.surveyParentId;
    }

    /* renamed from: component16, reason: from getter */
    public final Boolean getCawiTokenAutoGenerated() {
        return this.cawiTokenAutoGenerated;
    }

    /* renamed from: component17, reason: from getter */
    public final String getMfdId() {
        return this.mfdId;
    }

    /* renamed from: component18, reason: from getter */
    public final String getMfdName() {
        return this.mfdName;
    }

    public final List<String> component19() {
        return this.templatesIds;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<PeriodeList> component20() {
        return this.listPeriode;
    }

    /* renamed from: component21, reason: from getter */
    public final SampleMethod getSampleMethod() {
        return this.sampleMethod;
    }

    /* renamed from: component22, reason: from getter */
    public final Boolean getMultiPencacah() {
        return this.multiPencacah;
    }

    /* renamed from: component23, reason: from getter */
    public final Boolean getTarikSampleExclusive() {
        return this.tarikSampleExclusive;
    }

    /* renamed from: component24, reason: from getter */
    public final Boolean getIsMultiPencacah() {
        return this.isMultiPencacah;
    }

    /* renamed from: component25, reason: from getter */
    public final String getRegionGroupId() {
        return this.regionGroupId;
    }

    /* renamed from: component26, reason: from getter */
    public final Boolean getBackgroundRecord() {
        return this.backgroundRecord;
    }

    /* renamed from: component27, reason: from getter */
    public final Integer getSmallestRegionType() {
        return this.smallestRegionType;
    }

    /* renamed from: component28, reason: from getter */
    public final Integer getImageServiceType() {
        return this.imageServiceType;
    }

    public final List<TemplateLookupList> component29() {
        return this.templateLookup;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getPanelType() {
        return this.panelType;
    }

    /* renamed from: component30, reason: from getter */
    public final String getImageServiceUrl() {
        return this.imageServiceUrl;
    }

    /* renamed from: component4, reason: from getter */
    public final Boolean getAreaType() {
        return this.areaType;
    }

    /* renamed from: component5, reason: from getter */
    public final Boolean getUpdateListingType() {
        return this.updateListingType;
    }

    /* renamed from: component6, reason: from getter */
    public final Boolean getCanAddSample() {
        return this.canAddSample;
    }

    /* renamed from: component7, reason: from getter */
    public final Boolean getGeoLiveTracking() {
        return this.geoLiveTracking;
    }

    /* renamed from: component8, reason: from getter */
    public final Long getGeoRadius() {
        return this.geoRadius;
    }

    /* renamed from: component9, reason: from getter */
    public final Long getGeoAccuracy() {
        return this.geoAccuracy;
    }

    public final AllSurveyModel copy(String id2, String name, Boolean panelType, Boolean areaType, Boolean updateListingType, Boolean canAddSample, Boolean geoLiveTracking, Long geoRadius, Long geoAccuracy, String surveySampleTypeId, String surveySampleMethodId, String userId, String createdDate, String surveyStepTypeId, String surveyParentId, Boolean cawiTokenAutoGenerated, String mfdId, String mfdName, List<String> templatesIds, List<PeriodeList> listPeriode, SampleMethod sampleMethod, Boolean multiPencacah, Boolean tarikSampleExclusive, Boolean isMultiPencacah, String regionGroupId, Boolean backgroundRecord, Integer smallestRegionType, Integer imageServiceType, List<TemplateLookupList> templateLookup, String imageServiceUrl) {
        Intrinsics.checkNotNullParameter(templatesIds, "templatesIds");
        return new AllSurveyModel(id2, name, panelType, areaType, updateListingType, canAddSample, geoLiveTracking, geoRadius, geoAccuracy, surveySampleTypeId, surveySampleMethodId, userId, createdDate, surveyStepTypeId, surveyParentId, cawiTokenAutoGenerated, mfdId, mfdName, templatesIds, listPeriode, sampleMethod, multiPencacah, tarikSampleExclusive, isMultiPencacah, regionGroupId, backgroundRecord, smallestRegionType, imageServiceType, templateLookup, imageServiceUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AllSurveyModel)) {
            return false;
        }
        AllSurveyModel allSurveyModel = (AllSurveyModel) other;
        return Intrinsics.areEqual(this.id, allSurveyModel.id) && Intrinsics.areEqual(this.name, allSurveyModel.name) && Intrinsics.areEqual(this.panelType, allSurveyModel.panelType) && Intrinsics.areEqual(this.areaType, allSurveyModel.areaType) && Intrinsics.areEqual(this.updateListingType, allSurveyModel.updateListingType) && Intrinsics.areEqual(this.canAddSample, allSurveyModel.canAddSample) && Intrinsics.areEqual(this.geoLiveTracking, allSurveyModel.geoLiveTracking) && Intrinsics.areEqual(this.geoRadius, allSurveyModel.geoRadius) && Intrinsics.areEqual(this.geoAccuracy, allSurveyModel.geoAccuracy) && Intrinsics.areEqual(this.surveySampleTypeId, allSurveyModel.surveySampleTypeId) && Intrinsics.areEqual(this.surveySampleMethodId, allSurveyModel.surveySampleMethodId) && Intrinsics.areEqual(this.userId, allSurveyModel.userId) && Intrinsics.areEqual(this.createdDate, allSurveyModel.createdDate) && Intrinsics.areEqual(this.surveyStepTypeId, allSurveyModel.surveyStepTypeId) && Intrinsics.areEqual(this.surveyParentId, allSurveyModel.surveyParentId) && Intrinsics.areEqual(this.cawiTokenAutoGenerated, allSurveyModel.cawiTokenAutoGenerated) && Intrinsics.areEqual(this.mfdId, allSurveyModel.mfdId) && Intrinsics.areEqual(this.mfdName, allSurveyModel.mfdName) && Intrinsics.areEqual(this.templatesIds, allSurveyModel.templatesIds) && Intrinsics.areEqual(this.listPeriode, allSurveyModel.listPeriode) && Intrinsics.areEqual(this.sampleMethod, allSurveyModel.sampleMethod) && Intrinsics.areEqual(this.multiPencacah, allSurveyModel.multiPencacah) && Intrinsics.areEqual(this.tarikSampleExclusive, allSurveyModel.tarikSampleExclusive) && Intrinsics.areEqual(this.isMultiPencacah, allSurveyModel.isMultiPencacah) && Intrinsics.areEqual(this.regionGroupId, allSurveyModel.regionGroupId) && Intrinsics.areEqual(this.backgroundRecord, allSurveyModel.backgroundRecord) && Intrinsics.areEqual(this.smallestRegionType, allSurveyModel.smallestRegionType) && Intrinsics.areEqual(this.imageServiceType, allSurveyModel.imageServiceType) && Intrinsics.areEqual(this.templateLookup, allSurveyModel.templateLookup) && Intrinsics.areEqual(this.imageServiceUrl, allSurveyModel.imageServiceUrl);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.panelType;
        int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.areaType;
        int iHashCode4 = (iHashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.updateListingType;
        int iHashCode5 = (iHashCode4 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.canAddSample;
        int iHashCode6 = (iHashCode5 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.geoLiveTracking;
        int iHashCode7 = (iHashCode6 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        Long l = this.geoRadius;
        int iHashCode8 = (iHashCode7 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.geoAccuracy;
        int iHashCode9 = (iHashCode8 + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str3 = this.surveySampleTypeId;
        int iHashCode10 = (iHashCode9 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.surveySampleMethodId;
        int iHashCode11 = (iHashCode10 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.userId;
        int iHashCode12 = (iHashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.createdDate;
        int iHashCode13 = (iHashCode12 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.surveyStepTypeId;
        int iHashCode14 = (iHashCode13 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.surveyParentId;
        int iHashCode15 = (iHashCode14 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Boolean bool6 = this.cawiTokenAutoGenerated;
        int iHashCode16 = (iHashCode15 + (bool6 == null ? 0 : bool6.hashCode())) * 31;
        String str9 = this.mfdId;
        int iHashCode17 = (iHashCode16 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.mfdName;
        int iHashCode18 = (((iHashCode17 + (str10 == null ? 0 : str10.hashCode())) * 31) + this.templatesIds.hashCode()) * 31;
        List<PeriodeList> list = this.listPeriode;
        int iHashCode19 = (iHashCode18 + (list == null ? 0 : list.hashCode())) * 31;
        SampleMethod sampleMethod = this.sampleMethod;
        int iHashCode20 = (iHashCode19 + (sampleMethod == null ? 0 : sampleMethod.hashCode())) * 31;
        Boolean bool7 = this.multiPencacah;
        int iHashCode21 = (iHashCode20 + (bool7 == null ? 0 : bool7.hashCode())) * 31;
        Boolean bool8 = this.tarikSampleExclusive;
        int iHashCode22 = (iHashCode21 + (bool8 == null ? 0 : bool8.hashCode())) * 31;
        Boolean bool9 = this.isMultiPencacah;
        int iHashCode23 = (iHashCode22 + (bool9 == null ? 0 : bool9.hashCode())) * 31;
        String str11 = this.regionGroupId;
        int iHashCode24 = (iHashCode23 + (str11 == null ? 0 : str11.hashCode())) * 31;
        Boolean bool10 = this.backgroundRecord;
        int iHashCode25 = (iHashCode24 + (bool10 == null ? 0 : bool10.hashCode())) * 31;
        Integer num = this.smallestRegionType;
        int iHashCode26 = (iHashCode25 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.imageServiceType;
        int iHashCode27 = (iHashCode26 + (num2 == null ? 0 : num2.hashCode())) * 31;
        List<TemplateLookupList> list2 = this.templateLookup;
        int iHashCode28 = (iHashCode27 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str12 = this.imageServiceUrl;
        return iHashCode28 + (str12 != null ? str12.hashCode() : 0);
    }

    public String toString() {
        return "AllSurveyModel(id=" + this.id + ", name=" + this.name + ", panelType=" + this.panelType + ", areaType=" + this.areaType + ", updateListingType=" + this.updateListingType + ", canAddSample=" + this.canAddSample + ", geoLiveTracking=" + this.geoLiveTracking + ", geoRadius=" + this.geoRadius + ", geoAccuracy=" + this.geoAccuracy + ", surveySampleTypeId=" + this.surveySampleTypeId + ", surveySampleMethodId=" + this.surveySampleMethodId + ", userId=" + this.userId + ", createdDate=" + this.createdDate + ", surveyStepTypeId=" + this.surveyStepTypeId + ", surveyParentId=" + this.surveyParentId + ", cawiTokenAutoGenerated=" + this.cawiTokenAutoGenerated + ", mfdId=" + this.mfdId + ", mfdName=" + this.mfdName + ", templatesIds=" + this.templatesIds + ", listPeriode=" + this.listPeriode + ", sampleMethod=" + this.sampleMethod + ", multiPencacah=" + this.multiPencacah + ", tarikSampleExclusive=" + this.tarikSampleExclusive + ", isMultiPencacah=" + this.isMultiPencacah + ", regionGroupId=" + this.regionGroupId + ", backgroundRecord=" + this.backgroundRecord + ", smallestRegionType=" + this.smallestRegionType + ", imageServiceType=" + this.imageServiceType + ", templateLookup=" + this.templateLookup + ", imageServiceUrl=" + this.imageServiceUrl + ")";
    }

    public /* synthetic */ AllSurveyModel(String str, String str2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Long l, Long l2, String str3, String str4, String str5, String str6, String str7, String str8, Boolean bool6, String str9, String str10, List list, List list2, SampleMethod sampleMethod, Boolean bool7, Boolean bool8, Boolean bool9, String str11, Boolean bool10, Integer num, Integer num2, List list3, String str12, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, bool, bool2, bool3, bool4, bool5, l, l2, str3, str4, str5, str6, str7, str8, bool6, str9, str10, list, list2, sampleMethod, bool7, bool8, bool9, (i & 16777216) != 0 ? null : str11, (i & 33554432) != 0 ? null : bool10, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? null : num, (i & 134217728) != 0 ? null : num2, (i & 268435456) != 0 ? null : list3, (i & 536870912) != 0 ? null : str12);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final Boolean getPanelType() {
        return this.panelType;
    }

    public final void setPanelType(Boolean bool) {
        this.panelType = bool;
    }

    public final Boolean getAreaType() {
        return this.areaType;
    }

    public final void setAreaType(Boolean bool) {
        this.areaType = bool;
    }

    public final Boolean getUpdateListingType() {
        return this.updateListingType;
    }

    public final void setUpdateListingType(Boolean bool) {
        this.updateListingType = bool;
    }

    public final Boolean getCanAddSample() {
        return this.canAddSample;
    }

    public final void setCanAddSample(Boolean bool) {
        this.canAddSample = bool;
    }

    public final Boolean getGeoLiveTracking() {
        return this.geoLiveTracking;
    }

    public final void setGeoLiveTracking(Boolean bool) {
        this.geoLiveTracking = bool;
    }

    public final Long getGeoRadius() {
        return this.geoRadius;
    }

    public final void setGeoRadius(Long l) {
        this.geoRadius = l;
    }

    public final Long getGeoAccuracy() {
        return this.geoAccuracy;
    }

    public final void setGeoAccuracy(Long l) {
        this.geoAccuracy = l;
    }

    public final String getSurveySampleTypeId() {
        return this.surveySampleTypeId;
    }

    public final void setSurveySampleTypeId(String str) {
        this.surveySampleTypeId = str;
    }

    public final String getSurveySampleMethodId() {
        return this.surveySampleMethodId;
    }

    public final void setSurveySampleMethodId(String str) {
        this.surveySampleMethodId = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final String getCreatedDate() {
        return this.createdDate;
    }

    public final void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public final String getSurveyStepTypeId() {
        return this.surveyStepTypeId;
    }

    public final void setSurveyStepTypeId(String str) {
        this.surveyStepTypeId = str;
    }

    public final String getSurveyParentId() {
        return this.surveyParentId;
    }

    public final void setSurveyParentId(String str) {
        this.surveyParentId = str;
    }

    public final Boolean getCawiTokenAutoGenerated() {
        return this.cawiTokenAutoGenerated;
    }

    public final void setCawiTokenAutoGenerated(Boolean bool) {
        this.cawiTokenAutoGenerated = bool;
    }

    public final String getMfdId() {
        return this.mfdId;
    }

    public final void setMfdId(String str) {
        this.mfdId = str;
    }

    public final String getMfdName() {
        return this.mfdName;
    }

    public final void setMfdName(String str) {
        this.mfdName = str;
    }

    public final List<String> getTemplatesIds() {
        return this.templatesIds;
    }

    public final void setTemplatesIds(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.templatesIds = list;
    }

    public final List<PeriodeList> getListPeriode() {
        return this.listPeriode;
    }

    public final void setListPeriode(List<PeriodeList> list) {
        this.listPeriode = list;
    }

    public final SampleMethod getSampleMethod() {
        return this.sampleMethod;
    }

    public final void setSampleMethod(SampleMethod sampleMethod) {
        this.sampleMethod = sampleMethod;
    }

    public final Boolean getMultiPencacah() {
        return this.multiPencacah;
    }

    public final void setMultiPencacah(Boolean bool) {
        this.multiPencacah = bool;
    }

    public final Boolean getTarikSampleExclusive() {
        return this.tarikSampleExclusive;
    }

    public final void setTarikSampleExclusive(Boolean bool) {
        this.tarikSampleExclusive = bool;
    }

    public final Boolean isMultiPencacah() {
        return this.isMultiPencacah;
    }

    public final String getRegionGroupId() {
        return this.regionGroupId;
    }

    public final void setRegionGroupId(String str) {
        this.regionGroupId = str;
    }

    public final Boolean getBackgroundRecord() {
        return this.backgroundRecord;
    }

    public final void setBackgroundRecord(Boolean bool) {
        this.backgroundRecord = bool;
    }

    public final Integer getSmallestRegionType() {
        return this.smallestRegionType;
    }

    public final void setSmallestRegionType(Integer num) {
        this.smallestRegionType = num;
    }

    public final Integer getImageServiceType() {
        return this.imageServiceType;
    }

    public final void setImageServiceType(Integer num) {
        this.imageServiceType = num;
    }

    public final List<TemplateLookupList> getTemplateLookup() {
        return this.templateLookup;
    }

    public final void setTemplateLookup(List<TemplateLookupList> list) {
        this.templateLookup = list;
    }

    public final String getImageServiceUrl() {
        return this.imageServiceUrl;
    }

    public final void setImageServiceUrl(String str) {
        this.imageServiceUrl = str;
    }

    public AllSurveyModel(String str, String str2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Long l, Long l2, String str3, String str4, String str5, String str6, String str7, String str8, Boolean bool6, String str9, String str10, List<String> templatesIds, List<PeriodeList> list, SampleMethod sampleMethod, Boolean bool7, Boolean bool8, Boolean bool9, String str11, Boolean bool10, Integer num, Integer num2, List<TemplateLookupList> list2, String str12) {
        Intrinsics.checkNotNullParameter(templatesIds, "templatesIds");
        this.id = str;
        this.name = str2;
        this.panelType = bool;
        this.areaType = bool2;
        this.updateListingType = bool3;
        this.canAddSample = bool4;
        this.geoLiveTracking = bool5;
        this.geoRadius = l;
        this.geoAccuracy = l2;
        this.surveySampleTypeId = str3;
        this.surveySampleMethodId = str4;
        this.userId = str5;
        this.createdDate = str6;
        this.surveyStepTypeId = str7;
        this.surveyParentId = str8;
        this.cawiTokenAutoGenerated = bool6;
        this.mfdId = str9;
        this.mfdName = str10;
        this.templatesIds = templatesIds;
        this.listPeriode = list;
        this.sampleMethod = sampleMethod;
        this.multiPencacah = bool7;
        this.tarikSampleExclusive = bool8;
        this.isMultiPencacah = bool9;
        this.regionGroupId = str11;
        this.backgroundRecord = bool10;
        this.smallestRegionType = num;
        this.imageServiceType = num2;
        this.templateLookup = list2;
        this.imageServiceUrl = str12;
    }
}
