package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.models.SampleMethod;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SurveyEntity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b{\b\u0087\b\u0018\u0000 ¡\u00012\u00020\u0001:\u0002¡\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B¿\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0017\u001a\u00020\u0004\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u0007\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u001c\u001a\u00020\u0007\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\b\b\u0002\u0010 \u001a\u00020\u0007\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010$\u001a\u0004\u0018\u00010\r\u0012\u0010\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010'\u0018\u00010&\u0012\u0010\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010&\u0012\b\b\u0002\u0010)\u001a\u00020\u0007¢\u0006\u0002\u0010*J\t\u0010z\u001a\u00020\u0004HÆ\u0003J\t\u0010{\u001a\u00020\rHÆ\u0003J\t\u0010|\u001a\u00020\rHÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010~\u001a\u00020\u0004HÆ\u0003J\t\u0010\u007f\u001a\u00020\u0007HÆ\u0003J\f\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0083\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0085\u0001\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0007HÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0089\u0001\u001a\u00020\u0007HÆ\u0003J\u0011\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00102J\f\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u001fHÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020\u0007HÆ\u0003J\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00102J\f\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00102J\n\u0010\u0090\u0001\u001a\u00020\u0007HÆ\u0003J\u0011\u0010\u0091\u0001\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010^J\u0014\u0010\u0092\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010'\u0018\u00010&HÆ\u0003J\u0014\u0010\u0093\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010&HÆ\u0003J\n\u0010\u0094\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0095\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0096\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0097\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0098\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0099\u0001\u001a\u00020\rHÆ\u0003J\n\u0010\u009a\u0001\u001a\u00020\u0007HÆ\u0003J\u0088\u0003\u0010\u009b\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00072\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00072\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u00072\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00072\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\r2\u0012\b\u0002\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010'\u0018\u00010&2\u0012\b\u0002\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010&2\b\b\u0002\u0010)\u001a\u00020\u0007HÆ\u0001¢\u0006\u0003\u0010\u009c\u0001J\u0015\u0010\u009d\u0001\u001a\u00020\u00072\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010\u009f\u0001\u001a\u00020\rHÖ\u0001J\n\u0010 \u0001\u001a\u00020\u0004HÖ\u0001R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010,\"\u0004\b0\u0010.R\u001e\u0010#\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u00105\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010,\"\u0004\b7\u0010.R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010,\"\u0004\b9\u0010.R\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010 \u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010,\"\u0004\b?\u0010.R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001a\u0010\u000e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010,\"\u0004\bE\u0010.R\u001a\u0010\u000f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010A\"\u0004\bG\u0010CR\u001e\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010;\"\u0004\bI\u0010=R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010;\"\u0004\bK\u0010=R\u001a\u0010\u0010\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010A\"\u0004\bM\u0010CR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010;\"\u0004\bO\u0010=R\u001e\u0010!\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u00105\u001a\u0004\b!\u00102\"\u0004\bP\u00104R\u001a\u0010\u001c\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010,\"\u0004\bQ\u0010.R\u001a\u0010)\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010,\"\u0004\bR\u0010.R\u001e\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010;\"\u0004\bT\u0010=R\u001a\u0010\u0013\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010,\"\u0004\bV\u0010.R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010;\"\u0004\bX\u0010=R \u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001e\u0010$\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010a\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R$\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010;\"\u0004\bg\u0010=R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010;\"\u0004\bi\u0010=R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010;\"\u0004\bk\u0010=R\u001a\u0010\u0017\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010;\"\u0004\bm\u0010=R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010;\"\u0004\bo\u0010=R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010;\"\u0004\bq\u0010=R\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u00105\u001a\u0004\br\u00102\"\u0004\bs\u00104R$\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010'\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010c\"\u0004\bu\u0010eR\u001a\u0010\u001a\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010,\"\u0004\bw\u0010.R \u0010\u001b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010;\"\u0004\by\u0010=¨\u0006¢\u0001"}, d2 = {"Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "", "()V", "idPrimary", "", DownloadModel.ID, AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "", "areaType", "canAddSample", "cawiTokenAutoGenerated", "createdDate", "geoAccuracy", "", "geoLiveTracking", "geoRadius", "imageServiceType", "imageServiceUrl", "name", "panelType", "surveyParentId", "surveySampleMethodId", "surveySampleTypeId", "surveyStepTypeId", "surveyTemplateId", "surveyTemplateListingId", "updateListingType", "userId", "isSelected", "tarikSampleExclusive", "sampleMethod", "Lid/go/bpsfasih/data/local/models/SampleMethod;", "engineIcs2", "isMultiPencacah", "regionGroupId", "backgroundRecord", "smallestRegionType", "templateLookup", "", "Lid/go/bpsfasih/data/local/entities/TemplateLookupList;", "surveyModes", "is_pin", "(Ljava/lang/String;Ljava/lang/String;ZZZZLjava/lang/String;IZIILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/Boolean;Lid/go/bpsfasih/data/local/models/SampleMethod;ZLjava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Z)V", "getActive", "()Z", "setActive", "(Z)V", "getAreaType", "setAreaType", "getBackgroundRecord", "()Ljava/lang/Boolean;", "setBackgroundRecord", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCanAddSample", "setCanAddSample", "getCawiTokenAutoGenerated", "setCawiTokenAutoGenerated", "getCreatedDate", "()Ljava/lang/String;", "setCreatedDate", "(Ljava/lang/String;)V", "getEngineIcs2", "setEngineIcs2", "getGeoAccuracy", "()I", "setGeoAccuracy", "(I)V", "getGeoLiveTracking", "setGeoLiveTracking", "getGeoRadius", "setGeoRadius", "getId", "setId", "getIdPrimary", "setIdPrimary", "getImageServiceType", "setImageServiceType", "getImageServiceUrl", "setImageServiceUrl", "setMultiPencacah", "setSelected", "set_pin", "getName", "setName", "getPanelType", "setPanelType", "getRegionGroupId", "setRegionGroupId", "getSampleMethod", "()Lid/go/bpsfasih/data/local/models/SampleMethod;", "setSampleMethod", "(Lid/go/bpsfasih/data/local/models/SampleMethod;)V", "getSmallestRegionType", "()Ljava/lang/Integer;", "setSmallestRegionType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSurveyModes", "()Ljava/util/List;", "setSurveyModes", "(Ljava/util/List;)V", "getSurveyParentId", "setSurveyParentId", "getSurveySampleMethodId", "setSurveySampleMethodId", "getSurveySampleTypeId", "setSurveySampleTypeId", "getSurveyStepTypeId", "setSurveyStepTypeId", "getSurveyTemplateId", "setSurveyTemplateId", "getSurveyTemplateListingId", "setSurveyTemplateListingId", "getTarikSampleExclusive", "setTarikSampleExclusive", "getTemplateLookup", "setTemplateLookup", "getUpdateListingType", "setUpdateListingType", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;ZZZZLjava/lang/String;IZIILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/Boolean;Lid/go/bpsfasih/data/local/models/SampleMethod;ZLjava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Z)Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "equals", "other", "hashCode", "toString", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SurveyEntity {
    private boolean active;
    private boolean areaType;
    private Boolean backgroundRecord;
    private boolean canAddSample;
    private boolean cawiTokenAutoGenerated;
    private String createdDate;
    private boolean engineIcs2;
    private int geoAccuracy;
    private boolean geoLiveTracking;
    private int geoRadius;
    private String id;
    private String idPrimary;
    private int imageServiceType;
    private String imageServiceUrl;
    private Boolean isMultiPencacah;
    private boolean isSelected;
    private boolean is_pin;
    private String name;
    private boolean panelType;
    private String regionGroupId;
    private SampleMethod sampleMethod;
    private Integer smallestRegionType;
    private List<String> surveyModes;
    private String surveyParentId;
    private String surveySampleMethodId;
    private String surveySampleTypeId;
    private String surveyStepTypeId;
    private String surveyTemplateId;
    private String surveyTemplateListingId;
    private Boolean tarikSampleExclusive;
    private List<TemplateLookupList> templateLookup;
    private boolean updateListingType;
    private String userId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* renamed from: component1, reason: from getter */
    public final String getIdPrimary() {
        return this.idPrimary;
    }

    /* renamed from: component10, reason: from getter */
    public final int getGeoRadius() {
        return this.geoRadius;
    }

    /* renamed from: component11, reason: from getter */
    public final int getImageServiceType() {
        return this.imageServiceType;
    }

    /* renamed from: component12, reason: from getter */
    public final String getImageServiceUrl() {
        return this.imageServiceUrl;
    }

    /* renamed from: component13, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component14, reason: from getter */
    public final boolean getPanelType() {
        return this.panelType;
    }

    /* renamed from: component15, reason: from getter */
    public final String getSurveyParentId() {
        return this.surveyParentId;
    }

    /* renamed from: component16, reason: from getter */
    public final String getSurveySampleMethodId() {
        return this.surveySampleMethodId;
    }

    /* renamed from: component17, reason: from getter */
    public final String getSurveySampleTypeId() {
        return this.surveySampleTypeId;
    }

    /* renamed from: component18, reason: from getter */
    public final String getSurveyStepTypeId() {
        return this.surveyStepTypeId;
    }

    /* renamed from: component19, reason: from getter */
    public final String getSurveyTemplateId() {
        return this.surveyTemplateId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component20, reason: from getter */
    public final String getSurveyTemplateListingId() {
        return this.surveyTemplateListingId;
    }

    /* renamed from: component21, reason: from getter */
    public final boolean getUpdateListingType() {
        return this.updateListingType;
    }

    /* renamed from: component22, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component23, reason: from getter */
    public final boolean getIsSelected() {
        return this.isSelected;
    }

    /* renamed from: component24, reason: from getter */
    public final Boolean getTarikSampleExclusive() {
        return this.tarikSampleExclusive;
    }

    /* renamed from: component25, reason: from getter */
    public final SampleMethod getSampleMethod() {
        return this.sampleMethod;
    }

    /* renamed from: component26, reason: from getter */
    public final boolean getEngineIcs2() {
        return this.engineIcs2;
    }

    /* renamed from: component27, reason: from getter */
    public final Boolean getIsMultiPencacah() {
        return this.isMultiPencacah;
    }

    /* renamed from: component28, reason: from getter */
    public final String getRegionGroupId() {
        return this.regionGroupId;
    }

    /* renamed from: component29, reason: from getter */
    public final Boolean getBackgroundRecord() {
        return this.backgroundRecord;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getActive() {
        return this.active;
    }

    /* renamed from: component30, reason: from getter */
    public final Integer getSmallestRegionType() {
        return this.smallestRegionType;
    }

    public final List<TemplateLookupList> component31() {
        return this.templateLookup;
    }

    public final List<String> component32() {
        return this.surveyModes;
    }

    /* renamed from: component33, reason: from getter */
    public final boolean getIs_pin() {
        return this.is_pin;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getAreaType() {
        return this.areaType;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getCanAddSample() {
        return this.canAddSample;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getCawiTokenAutoGenerated() {
        return this.cawiTokenAutoGenerated;
    }

    /* renamed from: component7, reason: from getter */
    public final String getCreatedDate() {
        return this.createdDate;
    }

    /* renamed from: component8, reason: from getter */
    public final int getGeoAccuracy() {
        return this.geoAccuracy;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getGeoLiveTracking() {
        return this.geoLiveTracking;
    }

    public final SurveyEntity copy(String idPrimary, String id2, boolean active, boolean areaType, boolean canAddSample, boolean cawiTokenAutoGenerated, String createdDate, int geoAccuracy, boolean geoLiveTracking, int geoRadius, int imageServiceType, String imageServiceUrl, String name, boolean panelType, String surveyParentId, String surveySampleMethodId, String surveySampleTypeId, String surveyStepTypeId, String surveyTemplateId, String surveyTemplateListingId, boolean updateListingType, String userId, boolean isSelected, Boolean tarikSampleExclusive, SampleMethod sampleMethod, boolean engineIcs2, Boolean isMultiPencacah, String regionGroupId, Boolean backgroundRecord, Integer smallestRegionType, List<TemplateLookupList> templateLookup, List<String> surveyModes, boolean is_pin) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(surveyStepTypeId, "surveyStepTypeId");
        return new SurveyEntity(idPrimary, id2, active, areaType, canAddSample, cawiTokenAutoGenerated, createdDate, geoAccuracy, geoLiveTracking, geoRadius, imageServiceType, imageServiceUrl, name, panelType, surveyParentId, surveySampleMethodId, surveySampleTypeId, surveyStepTypeId, surveyTemplateId, surveyTemplateListingId, updateListingType, userId, isSelected, tarikSampleExclusive, sampleMethod, engineIcs2, isMultiPencacah, regionGroupId, backgroundRecord, smallestRegionType, templateLookup, surveyModes, is_pin);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SurveyEntity)) {
            return false;
        }
        SurveyEntity surveyEntity = (SurveyEntity) other;
        return Intrinsics.areEqual(this.idPrimary, surveyEntity.idPrimary) && Intrinsics.areEqual(this.id, surveyEntity.id) && this.active == surveyEntity.active && this.areaType == surveyEntity.areaType && this.canAddSample == surveyEntity.canAddSample && this.cawiTokenAutoGenerated == surveyEntity.cawiTokenAutoGenerated && Intrinsics.areEqual(this.createdDate, surveyEntity.createdDate) && this.geoAccuracy == surveyEntity.geoAccuracy && this.geoLiveTracking == surveyEntity.geoLiveTracking && this.geoRadius == surveyEntity.geoRadius && this.imageServiceType == surveyEntity.imageServiceType && Intrinsics.areEqual(this.imageServiceUrl, surveyEntity.imageServiceUrl) && Intrinsics.areEqual(this.name, surveyEntity.name) && this.panelType == surveyEntity.panelType && Intrinsics.areEqual(this.surveyParentId, surveyEntity.surveyParentId) && Intrinsics.areEqual(this.surveySampleMethodId, surveyEntity.surveySampleMethodId) && Intrinsics.areEqual(this.surveySampleTypeId, surveyEntity.surveySampleTypeId) && Intrinsics.areEqual(this.surveyStepTypeId, surveyEntity.surveyStepTypeId) && Intrinsics.areEqual(this.surveyTemplateId, surveyEntity.surveyTemplateId) && Intrinsics.areEqual(this.surveyTemplateListingId, surveyEntity.surveyTemplateListingId) && this.updateListingType == surveyEntity.updateListingType && Intrinsics.areEqual(this.userId, surveyEntity.userId) && this.isSelected == surveyEntity.isSelected && Intrinsics.areEqual(this.tarikSampleExclusive, surveyEntity.tarikSampleExclusive) && Intrinsics.areEqual(this.sampleMethod, surveyEntity.sampleMethod) && this.engineIcs2 == surveyEntity.engineIcs2 && Intrinsics.areEqual(this.isMultiPencacah, surveyEntity.isMultiPencacah) && Intrinsics.areEqual(this.regionGroupId, surveyEntity.regionGroupId) && Intrinsics.areEqual(this.backgroundRecord, surveyEntity.backgroundRecord) && Intrinsics.areEqual(this.smallestRegionType, surveyEntity.smallestRegionType) && Intrinsics.areEqual(this.templateLookup, surveyEntity.templateLookup) && Intrinsics.areEqual(this.surveyModes, surveyEntity.surveyModes) && this.is_pin == surveyEntity.is_pin;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.idPrimary.hashCode() * 31) + this.id.hashCode()) * 31;
        boolean z = this.active;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.areaType;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        boolean z3 = this.canAddSample;
        int i5 = z3;
        if (z3 != 0) {
            i5 = 1;
        }
        int i6 = (i4 + i5) * 31;
        boolean z4 = this.cawiTokenAutoGenerated;
        int i7 = z4;
        if (z4 != 0) {
            i7 = 1;
        }
        int iHashCode2 = (((((i6 + i7) * 31) + this.createdDate.hashCode()) * 31) + Integer.hashCode(this.geoAccuracy)) * 31;
        boolean z5 = this.geoLiveTracking;
        int i8 = z5;
        if (z5 != 0) {
            i8 = 1;
        }
        int iHashCode3 = (((((iHashCode2 + i8) * 31) + Integer.hashCode(this.geoRadius)) * 31) + Integer.hashCode(this.imageServiceType)) * 31;
        String str = this.imageServiceUrl;
        int iHashCode4 = (((iHashCode3 + (str == null ? 0 : str.hashCode())) * 31) + this.name.hashCode()) * 31;
        boolean z6 = this.panelType;
        int i9 = z6;
        if (z6 != 0) {
            i9 = 1;
        }
        int i10 = (iHashCode4 + i9) * 31;
        String str2 = this.surveyParentId;
        int iHashCode5 = (i10 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.surveySampleMethodId;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.surveySampleTypeId;
        int iHashCode7 = (((iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.surveyStepTypeId.hashCode()) * 31;
        String str5 = this.surveyTemplateId;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.surveyTemplateListingId;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        boolean z7 = this.updateListingType;
        int i11 = z7;
        if (z7 != 0) {
            i11 = 1;
        }
        int i12 = (iHashCode9 + i11) * 31;
        String str7 = this.userId;
        int iHashCode10 = (i12 + (str7 == null ? 0 : str7.hashCode())) * 31;
        boolean z8 = this.isSelected;
        int i13 = z8;
        if (z8 != 0) {
            i13 = 1;
        }
        int i14 = (iHashCode10 + i13) * 31;
        Boolean bool = this.tarikSampleExclusive;
        int iHashCode11 = (i14 + (bool == null ? 0 : bool.hashCode())) * 31;
        SampleMethod sampleMethod = this.sampleMethod;
        int iHashCode12 = (iHashCode11 + (sampleMethod == null ? 0 : sampleMethod.hashCode())) * 31;
        boolean z9 = this.engineIcs2;
        int i15 = z9;
        if (z9 != 0) {
            i15 = 1;
        }
        int i16 = (iHashCode12 + i15) * 31;
        Boolean bool2 = this.isMultiPencacah;
        int iHashCode13 = (i16 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str8 = this.regionGroupId;
        int iHashCode14 = (iHashCode13 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Boolean bool3 = this.backgroundRecord;
        int iHashCode15 = (iHashCode14 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Integer num = this.smallestRegionType;
        int iHashCode16 = (iHashCode15 + (num == null ? 0 : num.hashCode())) * 31;
        List<TemplateLookupList> list = this.templateLookup;
        int iHashCode17 = (iHashCode16 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.surveyModes;
        int iHashCode18 = (iHashCode17 + (list2 != null ? list2.hashCode() : 0)) * 31;
        boolean z10 = this.is_pin;
        return iHashCode18 + (z10 ? 1 : z10 ? 1 : 0);
    }

    public String toString() {
        return "SurveyEntity(idPrimary=" + this.idPrimary + ", id=" + this.id + ", active=" + this.active + ", areaType=" + this.areaType + ", canAddSample=" + this.canAddSample + ", cawiTokenAutoGenerated=" + this.cawiTokenAutoGenerated + ", createdDate=" + this.createdDate + ", geoAccuracy=" + this.geoAccuracy + ", geoLiveTracking=" + this.geoLiveTracking + ", geoRadius=" + this.geoRadius + ", imageServiceType=" + this.imageServiceType + ", imageServiceUrl=" + this.imageServiceUrl + ", name=" + this.name + ", panelType=" + this.panelType + ", surveyParentId=" + this.surveyParentId + ", surveySampleMethodId=" + this.surveySampleMethodId + ", surveySampleTypeId=" + this.surveySampleTypeId + ", surveyStepTypeId=" + this.surveyStepTypeId + ", surveyTemplateId=" + this.surveyTemplateId + ", surveyTemplateListingId=" + this.surveyTemplateListingId + ", updateListingType=" + this.updateListingType + ", userId=" + this.userId + ", isSelected=" + this.isSelected + ", tarikSampleExclusive=" + this.tarikSampleExclusive + ", sampleMethod=" + this.sampleMethod + ", engineIcs2=" + this.engineIcs2 + ", isMultiPencacah=" + this.isMultiPencacah + ", regionGroupId=" + this.regionGroupId + ", backgroundRecord=" + this.backgroundRecord + ", smallestRegionType=" + this.smallestRegionType + ", templateLookup=" + this.templateLookup + ", surveyModes=" + this.surveyModes + ", is_pin=" + this.is_pin + ")";
    }

    public SurveyEntity(String idPrimary, String id2, boolean z, boolean z2, boolean z3, boolean z4, String createdDate, int i, boolean z5, int i2, int i3, String str, String name, boolean z6, String str2, String str3, String str4, String surveyStepTypeId, String str5, String str6, boolean z7, String str7, boolean z8, Boolean bool, SampleMethod sampleMethod, boolean z9, Boolean bool2, String str8, Boolean bool3, Integer num, List<TemplateLookupList> list, List<String> list2, boolean z10) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(surveyStepTypeId, "surveyStepTypeId");
        this.idPrimary = idPrimary;
        this.id = id2;
        this.active = z;
        this.areaType = z2;
        this.canAddSample = z3;
        this.cawiTokenAutoGenerated = z4;
        this.createdDate = createdDate;
        this.geoAccuracy = i;
        this.geoLiveTracking = z5;
        this.geoRadius = i2;
        this.imageServiceType = i3;
        this.imageServiceUrl = str;
        this.name = name;
        this.panelType = z6;
        this.surveyParentId = str2;
        this.surveySampleMethodId = str3;
        this.surveySampleTypeId = str4;
        this.surveyStepTypeId = surveyStepTypeId;
        this.surveyTemplateId = str5;
        this.surveyTemplateListingId = str6;
        this.updateListingType = z7;
        this.userId = str7;
        this.isSelected = z8;
        this.tarikSampleExclusive = bool;
        this.sampleMethod = sampleMethod;
        this.engineIcs2 = z9;
        this.isMultiPencacah = bool2;
        this.regionGroupId = str8;
        this.backgroundRecord = bool3;
        this.smallestRegionType = num;
        this.templateLookup = list;
        this.surveyModes = list2;
        this.is_pin = z10;
    }

    public /* synthetic */ SurveyEntity(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, String str3, int i, boolean z5, int i2, int i3, String str4, String str5, boolean z6, String str6, String str7, String str8, String str9, String str10, String str11, boolean z7, String str12, boolean z8, Boolean bool, SampleMethod sampleMethod, boolean z9, Boolean bool2, String str13, Boolean bool3, Integer num, List list, List list2, boolean z10, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, z2, z3, z4, str3, i, z5, i2, i3, str4, str5, z6, str6, str7, str8, str9, str10, str11, z7, str12, z8, bool, sampleMethod, (i4 & 33554432) != 0 ? false : z9, bool2, str13, bool3, num, list, list2, (i5 & 1) != 0 ? false : z10);
    }

    public final String getIdPrimary() {
        return this.idPrimary;
    }

    public final void setIdPrimary(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.idPrimary = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final boolean getActive() {
        return this.active;
    }

    public final void setActive(boolean z) {
        this.active = z;
    }

    public final boolean getAreaType() {
        return this.areaType;
    }

    public final void setAreaType(boolean z) {
        this.areaType = z;
    }

    public final boolean getCanAddSample() {
        return this.canAddSample;
    }

    public final void setCanAddSample(boolean z) {
        this.canAddSample = z;
    }

    public final boolean getCawiTokenAutoGenerated() {
        return this.cawiTokenAutoGenerated;
    }

    public final void setCawiTokenAutoGenerated(boolean z) {
        this.cawiTokenAutoGenerated = z;
    }

    public final String getCreatedDate() {
        return this.createdDate;
    }

    public final void setCreatedDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.createdDate = str;
    }

    public final int getGeoAccuracy() {
        return this.geoAccuracy;
    }

    public final void setGeoAccuracy(int i) {
        this.geoAccuracy = i;
    }

    public final boolean getGeoLiveTracking() {
        return this.geoLiveTracking;
    }

    public final void setGeoLiveTracking(boolean z) {
        this.geoLiveTracking = z;
    }

    public final int getGeoRadius() {
        return this.geoRadius;
    }

    public final void setGeoRadius(int i) {
        this.geoRadius = i;
    }

    public final int getImageServiceType() {
        return this.imageServiceType;
    }

    public final void setImageServiceType(int i) {
        this.imageServiceType = i;
    }

    public final String getImageServiceUrl() {
        return this.imageServiceUrl;
    }

    public final void setImageServiceUrl(String str) {
        this.imageServiceUrl = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final boolean getPanelType() {
        return this.panelType;
    }

    public final void setPanelType(boolean z) {
        this.panelType = z;
    }

    public final String getSurveyParentId() {
        return this.surveyParentId;
    }

    public final void setSurveyParentId(String str) {
        this.surveyParentId = str;
    }

    public final String getSurveySampleMethodId() {
        return this.surveySampleMethodId;
    }

    public final void setSurveySampleMethodId(String str) {
        this.surveySampleMethodId = str;
    }

    public final String getSurveySampleTypeId() {
        return this.surveySampleTypeId;
    }

    public final void setSurveySampleTypeId(String str) {
        this.surveySampleTypeId = str;
    }

    public final String getSurveyStepTypeId() {
        return this.surveyStepTypeId;
    }

    public final void setSurveyStepTypeId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.surveyStepTypeId = str;
    }

    public final String getSurveyTemplateId() {
        return this.surveyTemplateId;
    }

    public final void setSurveyTemplateId(String str) {
        this.surveyTemplateId = str;
    }

    public final String getSurveyTemplateListingId() {
        return this.surveyTemplateListingId;
    }

    public final void setSurveyTemplateListingId(String str) {
        this.surveyTemplateListingId = str;
    }

    public final boolean getUpdateListingType() {
        return this.updateListingType;
    }

    public final void setUpdateListingType(boolean z) {
        this.updateListingType = z;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final Boolean getTarikSampleExclusive() {
        return this.tarikSampleExclusive;
    }

    public final void setTarikSampleExclusive(Boolean bool) {
        this.tarikSampleExclusive = bool;
    }

    public final SampleMethod getSampleMethod() {
        return this.sampleMethod;
    }

    public final void setSampleMethod(SampleMethod sampleMethod) {
        this.sampleMethod = sampleMethod;
    }

    public final boolean getEngineIcs2() {
        return this.engineIcs2;
    }

    public final void setEngineIcs2(boolean z) {
        this.engineIcs2 = z;
    }

    public final Boolean isMultiPencacah() {
        return this.isMultiPencacah;
    }

    public final void setMultiPencacah(Boolean bool) {
        this.isMultiPencacah = bool;
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

    public final List<TemplateLookupList> getTemplateLookup() {
        return this.templateLookup;
    }

    public final void setTemplateLookup(List<TemplateLookupList> list) {
        this.templateLookup = list;
    }

    public final List<String> getSurveyModes() {
        return this.surveyModes;
    }

    public final void setSurveyModes(List<String> list) {
        this.surveyModes = list;
    }

    public final boolean is_pin() {
        return this.is_pin;
    }

    public final void set_pin(boolean z) {
        this.is_pin = z;
    }

    public SurveyEntity() {
        this("", "", false, false, false, false, "", 0, false, 0, 0, "", "", false, "", "", "", "", "", "", false, null, false, false, null, false, null, null, null, null, null, null, false);
    }

    /* compiled from: SurveyEntity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/data/local/entities/SurveyEntity$Companion;", "", "()V", "mapIdToSurvey", "", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "surveys", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<SurveyEntity> mapIdToSurvey(List<SurveyEntity> surveys) {
            Intrinsics.checkNotNullParameter(surveys, "surveys");
            for (SurveyEntity surveyEntity : surveys) {
                String userId = FasihApp.INSTANCE.getSession().getUserId();
                surveyEntity.setUserId(userId);
                surveyEntity.setIdPrimary(surveyEntity.getId() + File.separator + userId);
            }
            return surveys;
        }
    }
}
