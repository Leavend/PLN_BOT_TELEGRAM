package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SurveyResponse.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bJ\b\u0087\b\u0018\u00002\u00020\u0001B·\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018\u0012\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001f\u001a\u00020\r\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018\u0012\b\b\u0002\u0010#\u001a\u00020\u0006¢\u0006\u0002\u0010$J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\rHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\u000f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\u000f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018HÆ\u0003J\u000f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010S\u001a\u00020\u0006HÆ\u0003J\t\u0010T\u001a\u00020\rHÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018HÆ\u0003J\t\u0010X\u001a\u00020\u0006HÆ\u0003J\t\u0010Y\u001a\u00020\u0006HÆ\u0003J\t\u0010Z\u001a\u00020\u0006HÆ\u0003J\t\u0010[\u001a\u00020\u0006HÆ\u0003J\t\u0010\\\u001a\u00020\u0006HÆ\u0003J\t\u0010]\u001a\u00020\u0006HÆ\u0003J\t\u0010^\u001a\u00020\u0006HÆ\u0003J\t\u0010_\u001a\u00020\rHÆ\u0003JÇ\u0002\u0010`\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00182\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00062\b\b\u0002\u0010\u001f\u001a\u00020\r2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\b\b\u0002\u0010#\u001a\u00020\u0006HÆ\u0001J\u0013\u0010a\u001a\u00020\u00062\b\u0010b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010c\u001a\u00020\rHÖ\u0001J\t\u0010d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010#\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0011\u0010\u001e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010+R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010+R\u0013\u0010 \u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010+R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010+R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010&R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010+R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b7\u0010&R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010+R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010+R\u0011\u0010\u001f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b:\u0010.R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b;\u00105R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018¢\u0006\b\n\u0000\u001a\u0004\b<\u00105R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b=\u0010+R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u0010+R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b?\u0010+R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b@\u0010+R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\bA\u00105R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bB\u0010&R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bC\u0010+¨\u0006e"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/SurveyDto;", "", DownloadModel.ID, "", "name", "panelType", "", "areaType", "updateListingType", "canAddSample", "isMultiPencacah", "geoLiveTracking", "geoRadius", "", "geoAccuracy", "surveySampleTypeId", "surveySampleMethodId", "userId", "createdDate", "surveyStepTypeId", "surveyParentId", "cawiTokenAutoGenerated", "regionGroupId", "templateLookup", "", "Lid/go/bpsfasih/data/remote/dto/TemplateLookupDto;", "listPeriode", "Lid/go/bpsfasih/data/remote/dto/SurveyPeriodDto;", "surveyGlossaries", "sampleMethod", "backgroundRecord", "smallestRegionType", "imageServiceType", "imageServiceUrl", "surveyModes", "allowSync", "(Ljava/lang/String;Ljava/lang/String;ZZZZZZIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZILjava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V", "getAllowSync", "()Z", "getAreaType", "getBackgroundRecord", "getCanAddSample", "getCawiTokenAutoGenerated", "()Ljava/lang/String;", "getCreatedDate", "getGeoAccuracy", "()I", "getGeoLiveTracking", "getGeoRadius", "getId", "getImageServiceType", "getImageServiceUrl", "getListPeriode", "()Ljava/util/List;", "getName", "getPanelType", "getRegionGroupId", "getSampleMethod", "getSmallestRegionType", "getSurveyGlossaries", "getSurveyModes", "getSurveyParentId", "getSurveySampleMethodId", "getSurveySampleTypeId", "getSurveyStepTypeId", "getTemplateLookup", "getUpdateListingType", "getUserId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SurveyDto {
    public static final int $stable = 8;
    private final boolean allowSync;
    private final boolean areaType;
    private final boolean backgroundRecord;
    private final boolean canAddSample;
    private final String cawiTokenAutoGenerated;
    private final String createdDate;
    private final int geoAccuracy;
    private final boolean geoLiveTracking;
    private final int geoRadius;
    private final String id;
    private final String imageServiceType;
    private final String imageServiceUrl;
    private final boolean isMultiPencacah;
    private final List<SurveyPeriodDto> listPeriode;
    private final String name;
    private final boolean panelType;
    private final String regionGroupId;
    private final String sampleMethod;
    private final int smallestRegionType;
    private final List<Object> surveyGlossaries;
    private final List<String> surveyModes;
    private final String surveyParentId;
    private final String surveySampleMethodId;
    private final String surveySampleTypeId;
    private final String surveyStepTypeId;
    private final List<TemplateLookupDto> templateLookup;
    private final boolean updateListingType;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getGeoAccuracy() {
        return this.geoAccuracy;
    }

    /* renamed from: component11, reason: from getter */
    public final String getSurveySampleTypeId() {
        return this.surveySampleTypeId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getSurveySampleMethodId() {
        return this.surveySampleMethodId;
    }

    /* renamed from: component13, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component14, reason: from getter */
    public final String getCreatedDate() {
        return this.createdDate;
    }

    /* renamed from: component15, reason: from getter */
    public final String getSurveyStepTypeId() {
        return this.surveyStepTypeId;
    }

    /* renamed from: component16, reason: from getter */
    public final String getSurveyParentId() {
        return this.surveyParentId;
    }

    /* renamed from: component17, reason: from getter */
    public final String getCawiTokenAutoGenerated() {
        return this.cawiTokenAutoGenerated;
    }

    /* renamed from: component18, reason: from getter */
    public final String getRegionGroupId() {
        return this.regionGroupId;
    }

    public final List<TemplateLookupDto> component19() {
        return this.templateLookup;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<SurveyPeriodDto> component20() {
        return this.listPeriode;
    }

    public final List<Object> component21() {
        return this.surveyGlossaries;
    }

    /* renamed from: component22, reason: from getter */
    public final String getSampleMethod() {
        return this.sampleMethod;
    }

    /* renamed from: component23, reason: from getter */
    public final boolean getBackgroundRecord() {
        return this.backgroundRecord;
    }

    /* renamed from: component24, reason: from getter */
    public final int getSmallestRegionType() {
        return this.smallestRegionType;
    }

    /* renamed from: component25, reason: from getter */
    public final String getImageServiceType() {
        return this.imageServiceType;
    }

    /* renamed from: component26, reason: from getter */
    public final String getImageServiceUrl() {
        return this.imageServiceUrl;
    }

    public final List<String> component27() {
        return this.surveyModes;
    }

    /* renamed from: component28, reason: from getter */
    public final boolean getAllowSync() {
        return this.allowSync;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getPanelType() {
        return this.panelType;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getAreaType() {
        return this.areaType;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getUpdateListingType() {
        return this.updateListingType;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getCanAddSample() {
        return this.canAddSample;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getIsMultiPencacah() {
        return this.isMultiPencacah;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getGeoLiveTracking() {
        return this.geoLiveTracking;
    }

    /* renamed from: component9, reason: from getter */
    public final int getGeoRadius() {
        return this.geoRadius;
    }

    public final SurveyDto copy(String id2, String name, boolean panelType, boolean areaType, boolean updateListingType, boolean canAddSample, boolean isMultiPencacah, boolean geoLiveTracking, int geoRadius, int geoAccuracy, String surveySampleTypeId, String surveySampleMethodId, String userId, String createdDate, String surveyStepTypeId, String surveyParentId, String cawiTokenAutoGenerated, String regionGroupId, List<TemplateLookupDto> templateLookup, List<SurveyPeriodDto> listPeriode, List<? extends Object> surveyGlossaries, String sampleMethod, boolean backgroundRecord, int smallestRegionType, String imageServiceType, String imageServiceUrl, List<String> surveyModes, boolean allowSync) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(surveyStepTypeId, "surveyStepTypeId");
        Intrinsics.checkNotNullParameter(regionGroupId, "regionGroupId");
        Intrinsics.checkNotNullParameter(templateLookup, "templateLookup");
        Intrinsics.checkNotNullParameter(listPeriode, "listPeriode");
        Intrinsics.checkNotNullParameter(surveyGlossaries, "surveyGlossaries");
        Intrinsics.checkNotNullParameter(surveyModes, "surveyModes");
        return new SurveyDto(id2, name, panelType, areaType, updateListingType, canAddSample, isMultiPencacah, geoLiveTracking, geoRadius, geoAccuracy, surveySampleTypeId, surveySampleMethodId, userId, createdDate, surveyStepTypeId, surveyParentId, cawiTokenAutoGenerated, regionGroupId, templateLookup, listPeriode, surveyGlossaries, sampleMethod, backgroundRecord, smallestRegionType, imageServiceType, imageServiceUrl, surveyModes, allowSync);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SurveyDto)) {
            return false;
        }
        SurveyDto surveyDto = (SurveyDto) other;
        return Intrinsics.areEqual(this.id, surveyDto.id) && Intrinsics.areEqual(this.name, surveyDto.name) && this.panelType == surveyDto.panelType && this.areaType == surveyDto.areaType && this.updateListingType == surveyDto.updateListingType && this.canAddSample == surveyDto.canAddSample && this.isMultiPencacah == surveyDto.isMultiPencacah && this.geoLiveTracking == surveyDto.geoLiveTracking && this.geoRadius == surveyDto.geoRadius && this.geoAccuracy == surveyDto.geoAccuracy && Intrinsics.areEqual(this.surveySampleTypeId, surveyDto.surveySampleTypeId) && Intrinsics.areEqual(this.surveySampleMethodId, surveyDto.surveySampleMethodId) && Intrinsics.areEqual(this.userId, surveyDto.userId) && Intrinsics.areEqual(this.createdDate, surveyDto.createdDate) && Intrinsics.areEqual(this.surveyStepTypeId, surveyDto.surveyStepTypeId) && Intrinsics.areEqual(this.surveyParentId, surveyDto.surveyParentId) && Intrinsics.areEqual(this.cawiTokenAutoGenerated, surveyDto.cawiTokenAutoGenerated) && Intrinsics.areEqual(this.regionGroupId, surveyDto.regionGroupId) && Intrinsics.areEqual(this.templateLookup, surveyDto.templateLookup) && Intrinsics.areEqual(this.listPeriode, surveyDto.listPeriode) && Intrinsics.areEqual(this.surveyGlossaries, surveyDto.surveyGlossaries) && Intrinsics.areEqual(this.sampleMethod, surveyDto.sampleMethod) && this.backgroundRecord == surveyDto.backgroundRecord && this.smallestRegionType == surveyDto.smallestRegionType && Intrinsics.areEqual(this.imageServiceType, surveyDto.imageServiceType) && Intrinsics.areEqual(this.imageServiceUrl, surveyDto.imageServiceUrl) && Intrinsics.areEqual(this.surveyModes, surveyDto.surveyModes) && this.allowSync == surveyDto.allowSync;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.name.hashCode()) * 31;
        boolean z = this.panelType;
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
        boolean z3 = this.updateListingType;
        int i5 = z3;
        if (z3 != 0) {
            i5 = 1;
        }
        int i6 = (i4 + i5) * 31;
        boolean z4 = this.canAddSample;
        int i7 = z4;
        if (z4 != 0) {
            i7 = 1;
        }
        int i8 = (i6 + i7) * 31;
        boolean z5 = this.isMultiPencacah;
        int i9 = z5;
        if (z5 != 0) {
            i9 = 1;
        }
        int i10 = (i8 + i9) * 31;
        boolean z6 = this.geoLiveTracking;
        int i11 = z6;
        if (z6 != 0) {
            i11 = 1;
        }
        int iHashCode2 = (((((i10 + i11) * 31) + Integer.hashCode(this.geoRadius)) * 31) + Integer.hashCode(this.geoAccuracy)) * 31;
        String str = this.surveySampleTypeId;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.surveySampleMethodId;
        int iHashCode4 = (((((((iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.userId.hashCode()) * 31) + this.createdDate.hashCode()) * 31) + this.surveyStepTypeId.hashCode()) * 31;
        String str3 = this.surveyParentId;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.cawiTokenAutoGenerated;
        int iHashCode6 = (((((((((iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.regionGroupId.hashCode()) * 31) + this.templateLookup.hashCode()) * 31) + this.listPeriode.hashCode()) * 31) + this.surveyGlossaries.hashCode()) * 31;
        String str5 = this.sampleMethod;
        int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        boolean z7 = this.backgroundRecord;
        int i12 = z7;
        if (z7 != 0) {
            i12 = 1;
        }
        int iHashCode8 = (((iHashCode7 + i12) * 31) + Integer.hashCode(this.smallestRegionType)) * 31;
        String str6 = this.imageServiceType;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.imageServiceUrl;
        int iHashCode10 = (((iHashCode9 + (str7 != null ? str7.hashCode() : 0)) * 31) + this.surveyModes.hashCode()) * 31;
        boolean z8 = this.allowSync;
        return iHashCode10 + (z8 ? 1 : z8 ? 1 : 0);
    }

    public String toString() {
        return "SurveyDto(id=" + this.id + ", name=" + this.name + ", panelType=" + this.panelType + ", areaType=" + this.areaType + ", updateListingType=" + this.updateListingType + ", canAddSample=" + this.canAddSample + ", isMultiPencacah=" + this.isMultiPencacah + ", geoLiveTracking=" + this.geoLiveTracking + ", geoRadius=" + this.geoRadius + ", geoAccuracy=" + this.geoAccuracy + ", surveySampleTypeId=" + this.surveySampleTypeId + ", surveySampleMethodId=" + this.surveySampleMethodId + ", userId=" + this.userId + ", createdDate=" + this.createdDate + ", surveyStepTypeId=" + this.surveyStepTypeId + ", surveyParentId=" + this.surveyParentId + ", cawiTokenAutoGenerated=" + this.cawiTokenAutoGenerated + ", regionGroupId=" + this.regionGroupId + ", templateLookup=" + this.templateLookup + ", listPeriode=" + this.listPeriode + ", surveyGlossaries=" + this.surveyGlossaries + ", sampleMethod=" + this.sampleMethod + ", backgroundRecord=" + this.backgroundRecord + ", smallestRegionType=" + this.smallestRegionType + ", imageServiceType=" + this.imageServiceType + ", imageServiceUrl=" + this.imageServiceUrl + ", surveyModes=" + this.surveyModes + ", allowSync=" + this.allowSync + ")";
    }

    public SurveyDto(String id2, String name, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, int i2, String str, String str2, String userId, String createdDate, String surveyStepTypeId, String str3, String str4, String regionGroupId, List<TemplateLookupDto> templateLookup, List<SurveyPeriodDto> listPeriode, List<? extends Object> surveyGlossaries, String str5, boolean z7, int i3, String str6, String str7, List<String> surveyModes, boolean z8) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(surveyStepTypeId, "surveyStepTypeId");
        Intrinsics.checkNotNullParameter(regionGroupId, "regionGroupId");
        Intrinsics.checkNotNullParameter(templateLookup, "templateLookup");
        Intrinsics.checkNotNullParameter(listPeriode, "listPeriode");
        Intrinsics.checkNotNullParameter(surveyGlossaries, "surveyGlossaries");
        Intrinsics.checkNotNullParameter(surveyModes, "surveyModes");
        this.id = id2;
        this.name = name;
        this.panelType = z;
        this.areaType = z2;
        this.updateListingType = z3;
        this.canAddSample = z4;
        this.isMultiPencacah = z5;
        this.geoLiveTracking = z6;
        this.geoRadius = i;
        this.geoAccuracy = i2;
        this.surveySampleTypeId = str;
        this.surveySampleMethodId = str2;
        this.userId = userId;
        this.createdDate = createdDate;
        this.surveyStepTypeId = surveyStepTypeId;
        this.surveyParentId = str3;
        this.cawiTokenAutoGenerated = str4;
        this.regionGroupId = regionGroupId;
        this.templateLookup = templateLookup;
        this.listPeriode = listPeriode;
        this.surveyGlossaries = surveyGlossaries;
        this.sampleMethod = str5;
        this.backgroundRecord = z7;
        this.smallestRegionType = i3;
        this.imageServiceType = str6;
        this.imageServiceUrl = str7;
        this.surveyModes = surveyModes;
        this.allowSync = z8;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getPanelType() {
        return this.panelType;
    }

    public final boolean getAreaType() {
        return this.areaType;
    }

    public final boolean getUpdateListingType() {
        return this.updateListingType;
    }

    public final boolean getCanAddSample() {
        return this.canAddSample;
    }

    public final boolean isMultiPencacah() {
        return this.isMultiPencacah;
    }

    public final boolean getGeoLiveTracking() {
        return this.geoLiveTracking;
    }

    public final int getGeoRadius() {
        return this.geoRadius;
    }

    public final int getGeoAccuracy() {
        return this.geoAccuracy;
    }

    public final String getSurveySampleTypeId() {
        return this.surveySampleTypeId;
    }

    public final String getSurveySampleMethodId() {
        return this.surveySampleMethodId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getCreatedDate() {
        return this.createdDate;
    }

    public final String getSurveyStepTypeId() {
        return this.surveyStepTypeId;
    }

    public final String getSurveyParentId() {
        return this.surveyParentId;
    }

    public final String getCawiTokenAutoGenerated() {
        return this.cawiTokenAutoGenerated;
    }

    public final String getRegionGroupId() {
        return this.regionGroupId;
    }

    public /* synthetic */ SurveyDto(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, int i2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, List list, List list2, List list3, String str11, boolean z7, int i3, String str12, String str13, List list4, boolean z8, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i4 & 4) != 0 ? false : z, (i4 & 8) != 0 ? false : z2, (i4 & 16) != 0 ? false : z3, (i4 & 32) != 0 ? false : z4, (i4 & 64) != 0 ? false : z5, (i4 & 128) != 0 ? false : z6, (i4 & 256) != 0 ? 0 : i, (i4 & 512) != 0 ? 50 : i2, (i4 & 1024) != 0 ? null : str3, (i4 & 2048) != 0 ? null : str4, str5, str6, str7, (32768 & i4) != 0 ? null : str8, (65536 & i4) != 0 ? null : str9, str10, (262144 & i4) != 0 ? CollectionsKt.emptyList() : list, (524288 & i4) != 0 ? CollectionsKt.emptyList() : list2, (1048576 & i4) != 0 ? CollectionsKt.emptyList() : list3, (2097152 & i4) != 0 ? null : str11, (4194304 & i4) != 0 ? false : z7, (8388608 & i4) != 0 ? 0 : i3, (16777216 & i4) != 0 ? null : str12, (33554432 & i4) != 0 ? null : str13, (67108864 & i4) != 0 ? CollectionsKt.emptyList() : list4, (i4 & 134217728) != 0 ? true : z8);
    }

    public final List<TemplateLookupDto> getTemplateLookup() {
        return this.templateLookup;
    }

    public final List<SurveyPeriodDto> getListPeriode() {
        return this.listPeriode;
    }

    public final List<Object> getSurveyGlossaries() {
        return this.surveyGlossaries;
    }

    public final String getSampleMethod() {
        return this.sampleMethod;
    }

    public final boolean getBackgroundRecord() {
        return this.backgroundRecord;
    }

    public final int getSmallestRegionType() {
        return this.smallestRegionType;
    }

    public final String getImageServiceType() {
        return this.imageServiceType;
    }

    public final String getImageServiceUrl() {
        return this.imageServiceUrl;
    }

    public final List<String> getSurveyModes() {
        return this.surveyModes;
    }

    public final boolean getAllowSync() {
        return this.allowSync;
    }
}
