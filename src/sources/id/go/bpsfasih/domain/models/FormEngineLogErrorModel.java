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

/* compiled from: FormEngineLogErrorModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bw\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0011HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u008b\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u0007HÖ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014¨\u00068"}, d2 = {"Lid/go/bpsfasih/domain/models/FormEngineLogErrorModel;", "", "userId", "", "email", "mobileVersionName", "mobileVersionCode", "", "formEngineId", "formEngineVersion", "templateId", "templateVersion", "validationId", "validationVersion", "assignmentId", "message", ServerValues.NAME_OP_TIMESTAMP, "Lcom/google/firebase/firestore/FieldValue;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/firestore/FieldValue;)V", "getAssignmentId", "()Ljava/lang/String;", "getEmail", "getFormEngineId", "getFormEngineVersion", "getMessage", "getMobileVersionCode", "()I", "getMobileVersionName", "getTemplateId", "getTemplateVersion", "getTimestamp", "()Lcom/google/firebase/firestore/FieldValue;", "setTimestamp", "(Lcom/google/firebase/firestore/FieldValue;)V", "getUserId", "getValidationId", "getValidationVersion", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class FormEngineLogErrorModel {
    public static final int $stable = 8;
    private final String assignmentId;
    private final String email;
    private final String formEngineId;
    private final String formEngineVersion;
    private final String message;
    private final int mobileVersionCode;
    private final String mobileVersionName;
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
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component13, reason: from getter */
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

    public final FormEngineLogErrorModel copy(String userId, String email, String mobileVersionName, int mobileVersionCode, String formEngineId, String formEngineVersion, String templateId, String templateVersion, String validationId, String validationVersion, String assignmentId, String message, FieldValue timestamp) {
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
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        return new FormEngineLogErrorModel(userId, email, mobileVersionName, mobileVersionCode, formEngineId, formEngineVersion, templateId, templateVersion, validationId, validationVersion, assignmentId, message, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FormEngineLogErrorModel)) {
            return false;
        }
        FormEngineLogErrorModel formEngineLogErrorModel = (FormEngineLogErrorModel) other;
        return Intrinsics.areEqual(this.userId, formEngineLogErrorModel.userId) && Intrinsics.areEqual(this.email, formEngineLogErrorModel.email) && Intrinsics.areEqual(this.mobileVersionName, formEngineLogErrorModel.mobileVersionName) && this.mobileVersionCode == formEngineLogErrorModel.mobileVersionCode && Intrinsics.areEqual(this.formEngineId, formEngineLogErrorModel.formEngineId) && Intrinsics.areEqual(this.formEngineVersion, formEngineLogErrorModel.formEngineVersion) && Intrinsics.areEqual(this.templateId, formEngineLogErrorModel.templateId) && Intrinsics.areEqual(this.templateVersion, formEngineLogErrorModel.templateVersion) && Intrinsics.areEqual(this.validationId, formEngineLogErrorModel.validationId) && Intrinsics.areEqual(this.validationVersion, formEngineLogErrorModel.validationVersion) && Intrinsics.areEqual(this.assignmentId, formEngineLogErrorModel.assignmentId) && Intrinsics.areEqual(this.message, formEngineLogErrorModel.message) && Intrinsics.areEqual(this.timestamp, formEngineLogErrorModel.timestamp);
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.userId.hashCode() * 31) + this.email.hashCode()) * 31) + this.mobileVersionName.hashCode()) * 31) + Integer.hashCode(this.mobileVersionCode)) * 31) + this.formEngineId.hashCode()) * 31) + this.formEngineVersion.hashCode()) * 31) + this.templateId.hashCode()) * 31) + this.templateVersion.hashCode()) * 31) + this.validationId.hashCode()) * 31) + this.validationVersion.hashCode()) * 31) + this.assignmentId.hashCode()) * 31) + this.message.hashCode()) * 31) + this.timestamp.hashCode();
    }

    public String toString() {
        return "FormEngineLogErrorModel(userId=" + this.userId + ", email=" + this.email + ", mobileVersionName=" + this.mobileVersionName + ", mobileVersionCode=" + this.mobileVersionCode + ", formEngineId=" + this.formEngineId + ", formEngineVersion=" + this.formEngineVersion + ", templateId=" + this.templateId + ", templateVersion=" + this.templateVersion + ", validationId=" + this.validationId + ", validationVersion=" + this.validationVersion + ", assignmentId=" + this.assignmentId + ", message=" + this.message + ", timestamp=" + this.timestamp + ")";
    }

    public FormEngineLogErrorModel(String userId, String email, String mobileVersionName, int i, String formEngineId, String formEngineVersion, String templateId, String templateVersion, String validationId, String validationVersion, String assignmentId, String message, FieldValue timestamp) {
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
        Intrinsics.checkNotNullParameter(message, "message");
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
        this.message = message;
        this.timestamp = timestamp;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FormEngineLogErrorModel(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, FieldValue fieldValue, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        String str12;
        FieldValue fieldValue2;
        String userId = (i2 & 1) != 0 ? FasihApp.INSTANCE.getSession().getUserId() : str;
        if ((i2 & 2) != 0) {
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL());
            str12 = sessionString == null ? "" : sessionString;
        } else {
            str12 = str2;
        }
        String str13 = (i2 & 4) != 0 ? BuildConfig.VERSION_NAME : str3;
        int i3 = (i2 & 8) != 0 ? 126 : i;
        if ((i2 & 4096) != 0) {
            FieldValue fieldValueServerTimestamp = FieldValue.serverTimestamp();
            Intrinsics.checkNotNullExpressionValue(fieldValueServerTimestamp, "serverTimestamp()");
            fieldValue2 = fieldValueServerTimestamp;
        } else {
            fieldValue2 = fieldValue;
        }
        this(userId, str12, str13, i3, str4, str5, str6, str7, str8, str9, str10, str11, fieldValue2);
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

    public final String getMessage() {
        return this.message;
    }

    public final FieldValue getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(FieldValue fieldValue) {
        Intrinsics.checkNotNullParameter(fieldValue, "<set-?>");
        this.timestamp = fieldValue;
    }
}
