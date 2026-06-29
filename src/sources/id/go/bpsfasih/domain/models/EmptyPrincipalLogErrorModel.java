package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.database.core.ServerValues;
import com.google.firebase.firestore.FieldValue;
import id.go.bpsfasih.BuildConfig;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EmptyPrincipalLogErrorModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0014HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J©\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010?\u001a\u00020\u0007HÖ\u0001J\t\u0010@\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0017R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0017¨\u0006A"}, d2 = {"Lid/go/bpsfasih/domain/models/EmptyPrincipalLogErrorModel;", "", "userId", "", "email", "mobileVersionName", "mobileVersionCode", "", "formEngineId", "formEngineVersion", "templateId", "templateVersion", "validationId", "validationVersion", "assignmentId", "customData", "jsonString", "jsonArray", "principalMap", ServerValues.NAME_OP_TIMESTAMP, "Lcom/google/firebase/firestore/FieldValue;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/firestore/FieldValue;)V", "getAssignmentId", "()Ljava/lang/String;", "getCustomData", "getEmail", "getFormEngineId", "getFormEngineVersion", "getJsonArray", "getJsonString", "getMobileVersionCode", "()I", "getMobileVersionName", "getPrincipalMap", "getTemplateId", "getTemplateVersion", "getTimestamp", "()Lcom/google/firebase/firestore/FieldValue;", "setTimestamp", "(Lcom/google/firebase/firestore/FieldValue;)V", "getUserId", "getValidationId", "getValidationVersion", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class EmptyPrincipalLogErrorModel {
    public static final int $stable = 8;
    private final String assignmentId;
    private final String customData;
    private final String email;
    private final String formEngineId;
    private final String formEngineVersion;
    private final String jsonArray;
    private final String jsonString;
    private final int mobileVersionCode;
    private final String mobileVersionName;
    private final String principalMap;
    private final String templateId;
    private final String templateVersion;
    private FieldValue timestamp;
    private final String userId;
    private final String validationId;
    private final String validationVersion;

    /* renamed from: component1, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component10, reason: from getter */
    public final String getValidationVersion() {
        return this.validationVersion;
    }

    /* renamed from: component11, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getCustomData() {
        return this.customData;
    }

    /* renamed from: component13, reason: from getter */
    public final String getJsonString() {
        return this.jsonString;
    }

    /* renamed from: component14, reason: from getter */
    public final String getJsonArray() {
        return this.jsonArray;
    }

    /* renamed from: component15, reason: from getter */
    public final String getPrincipalMap() {
        return this.principalMap;
    }

    /* renamed from: component16, reason: from getter */
    public final FieldValue getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMobileVersionName() {
        return this.mobileVersionName;
    }

    /* renamed from: component4, reason: from getter */
    public final int getMobileVersionCode() {
        return this.mobileVersionCode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFormEngineId() {
        return this.formEngineId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getFormEngineVersion() {
        return this.formEngineVersion;
    }

    /* renamed from: component7, reason: from getter */
    public final String getTemplateId() {
        return this.templateId;
    }

    /* renamed from: component8, reason: from getter */
    public final String getTemplateVersion() {
        return this.templateVersion;
    }

    /* renamed from: component9, reason: from getter */
    public final String getValidationId() {
        return this.validationId;
    }

    public final EmptyPrincipalLogErrorModel copy(String userId, String email, String mobileVersionName, int mobileVersionCode, String formEngineId, String formEngineVersion, String templateId, String templateVersion, String validationId, String validationVersion, String assignmentId, String customData, String jsonString, String jsonArray, String principalMap, FieldValue timestamp) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(mobileVersionName, "mobileVersionName");
        Intrinsics.checkNotNullParameter(formEngineId, "formEngineId");
        Intrinsics.checkNotNullParameter(formEngineVersion, "formEngineVersion");
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(templateVersion, "templateVersion");
        Intrinsics.checkNotNullParameter(validationId, "validationId");
        Intrinsics.checkNotNullParameter(validationVersion, "validationVersion");
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(customData, "customData");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        Intrinsics.checkNotNullParameter(principalMap, "principalMap");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        return new EmptyPrincipalLogErrorModel(userId, email, mobileVersionName, mobileVersionCode, formEngineId, formEngineVersion, templateId, templateVersion, validationId, validationVersion, assignmentId, customData, jsonString, jsonArray, principalMap, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmptyPrincipalLogErrorModel)) {
            return false;
        }
        EmptyPrincipalLogErrorModel emptyPrincipalLogErrorModel = (EmptyPrincipalLogErrorModel) other;
        return Intrinsics.areEqual(this.userId, emptyPrincipalLogErrorModel.userId) && Intrinsics.areEqual(this.email, emptyPrincipalLogErrorModel.email) && Intrinsics.areEqual(this.mobileVersionName, emptyPrincipalLogErrorModel.mobileVersionName) && this.mobileVersionCode == emptyPrincipalLogErrorModel.mobileVersionCode && Intrinsics.areEqual(this.formEngineId, emptyPrincipalLogErrorModel.formEngineId) && Intrinsics.areEqual(this.formEngineVersion, emptyPrincipalLogErrorModel.formEngineVersion) && Intrinsics.areEqual(this.templateId, emptyPrincipalLogErrorModel.templateId) && Intrinsics.areEqual(this.templateVersion, emptyPrincipalLogErrorModel.templateVersion) && Intrinsics.areEqual(this.validationId, emptyPrincipalLogErrorModel.validationId) && Intrinsics.areEqual(this.validationVersion, emptyPrincipalLogErrorModel.validationVersion) && Intrinsics.areEqual(this.assignmentId, emptyPrincipalLogErrorModel.assignmentId) && Intrinsics.areEqual(this.customData, emptyPrincipalLogErrorModel.customData) && Intrinsics.areEqual(this.jsonString, emptyPrincipalLogErrorModel.jsonString) && Intrinsics.areEqual(this.jsonArray, emptyPrincipalLogErrorModel.jsonArray) && Intrinsics.areEqual(this.principalMap, emptyPrincipalLogErrorModel.principalMap) && Intrinsics.areEqual(this.timestamp, emptyPrincipalLogErrorModel.timestamp);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.userId.hashCode() * 31) + this.email.hashCode()) * 31) + this.mobileVersionName.hashCode()) * 31) + Integer.hashCode(this.mobileVersionCode)) * 31) + this.formEngineId.hashCode()) * 31) + this.formEngineVersion.hashCode()) * 31) + this.templateId.hashCode()) * 31) + this.templateVersion.hashCode()) * 31) + this.validationId.hashCode()) * 31) + this.validationVersion.hashCode()) * 31) + this.assignmentId.hashCode()) * 31) + this.customData.hashCode()) * 31) + this.jsonString.hashCode()) * 31) + this.jsonArray.hashCode()) * 31) + this.principalMap.hashCode()) * 31) + this.timestamp.hashCode();
    }

    public String toString() {
        return "EmptyPrincipalLogErrorModel(userId=" + this.userId + ", email=" + this.email + ", mobileVersionName=" + this.mobileVersionName + ", mobileVersionCode=" + this.mobileVersionCode + ", formEngineId=" + this.formEngineId + ", formEngineVersion=" + this.formEngineVersion + ", templateId=" + this.templateId + ", templateVersion=" + this.templateVersion + ", validationId=" + this.validationId + ", validationVersion=" + this.validationVersion + ", assignmentId=" + this.assignmentId + ", customData=" + this.customData + ", jsonString=" + this.jsonString + ", jsonArray=" + this.jsonArray + ", principalMap=" + this.principalMap + ", timestamp=" + this.timestamp + ")";
    }

    public EmptyPrincipalLogErrorModel(String userId, String email, String mobileVersionName, int i, String formEngineId, String formEngineVersion, String templateId, String templateVersion, String validationId, String validationVersion, String assignmentId, String customData, String jsonString, String jsonArray, String principalMap, FieldValue timestamp) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(mobileVersionName, "mobileVersionName");
        Intrinsics.checkNotNullParameter(formEngineId, "formEngineId");
        Intrinsics.checkNotNullParameter(formEngineVersion, "formEngineVersion");
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(templateVersion, "templateVersion");
        Intrinsics.checkNotNullParameter(validationId, "validationId");
        Intrinsics.checkNotNullParameter(validationVersion, "validationVersion");
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(customData, "customData");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        Intrinsics.checkNotNullParameter(principalMap, "principalMap");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        this.userId = userId;
        this.email = email;
        this.mobileVersionName = mobileVersionName;
        this.mobileVersionCode = i;
        this.formEngineId = formEngineId;
        this.formEngineVersion = formEngineVersion;
        this.templateId = templateId;
        this.templateVersion = templateVersion;
        this.validationId = validationId;
        this.validationVersion = validationVersion;
        this.assignmentId = assignmentId;
        this.customData = customData;
        this.jsonString = jsonString;
        this.jsonArray = jsonArray;
        this.principalMap = principalMap;
        this.timestamp = timestamp;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ EmptyPrincipalLogErrorModel(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, FieldValue fieldValue, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        String str15;
        FieldValue fieldValue2;
        String userId = (i2 & 1) != 0 ? FasihApp.INSTANCE.getSession().getUserId() : str;
        if ((i2 & 2) != 0) {
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL());
            str15 = sessionString == null ? "" : sessionString;
        } else {
            str15 = str2;
        }
        String str16 = (i2 & 4) != 0 ? BuildConfig.VERSION_NAME : str3;
        int i3 = (i2 & 8) != 0 ? 126 : i;
        if ((i2 & 32768) != 0) {
            FieldValue fieldValueServerTimestamp = FieldValue.serverTimestamp();
            Intrinsics.checkNotNullExpressionValue(fieldValueServerTimestamp, "serverTimestamp()");
            fieldValue2 = fieldValueServerTimestamp;
        } else {
            fieldValue2 = fieldValue;
        }
        this(userId, str15, str16, i3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, fieldValue2);
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getMobileVersionName() {
        return this.mobileVersionName;
    }

    public final int getMobileVersionCode() {
        return this.mobileVersionCode;
    }

    public final String getFormEngineId() {
        return this.formEngineId;
    }

    public final String getFormEngineVersion() {
        return this.formEngineVersion;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final String getTemplateVersion() {
        return this.templateVersion;
    }

    public final String getValidationId() {
        return this.validationId;
    }

    public final String getValidationVersion() {
        return this.validationVersion;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final String getCustomData() {
        return this.customData;
    }

    public final String getJsonString() {
        return this.jsonString;
    }

    public final String getJsonArray() {
        return this.jsonArray;
    }

    public final String getPrincipalMap() {
        return this.principalMap;
    }

    public final FieldValue getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(FieldValue fieldValue) {
        Intrinsics.checkNotNullParameter(fieldValue, "<set-?>");
        this.timestamp = fieldValue;
    }
}
