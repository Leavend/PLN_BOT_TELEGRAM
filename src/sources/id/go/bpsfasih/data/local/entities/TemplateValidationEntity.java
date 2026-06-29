package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import id.go.bpsfasih.FasihApp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateValidationEntity.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BK\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\fJ\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010$\u001a\u00020\nHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0004HÆ\u0003JU\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\nHÖ\u0001J\t\u0010+\u001a\u00020\u0004HÖ\u0001R \u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001e\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R \u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001e\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R \u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010¨\u0006-"}, d2 = {"Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;", "", "()V", "survey_id", "", "userId", "template_id", "template_version", "validasi_version", "formEngineId", "", "formEngineBrandName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getFormEngineBrandName", "()Ljava/lang/String;", "setFormEngineBrandName", "(Ljava/lang/String;)V", "getFormEngineId", "()I", "setFormEngineId", "(I)V", "getSurvey_id", "setSurvey_id", "getTemplate_id", "setTemplate_id", "getTemplate_version", "setTemplate_version", "getUserId", "setUserId", "getValidasi_version", "setValidasi_version", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class TemplateValidationEntity {

    @SerializedName("form_engine_brand_name")
    private String formEngineBrandName;

    @SerializedName("form_engine_id")
    private int formEngineId;

    @SerializedName("survey_id")
    private String survey_id;

    @SerializedName("template_id")
    private String template_id;

    @SerializedName("template_version")
    private String template_version;

    @SerializedName("userId")
    private String userId;

    @SerializedName("validation_version")
    private String validasi_version;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ TemplateValidationEntity copy$default(TemplateValidationEntity templateValidationEntity, String str, String str2, String str3, String str4, String str5, int i, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = templateValidationEntity.survey_id;
        }
        if ((i2 & 2) != 0) {
            str2 = templateValidationEntity.userId;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = templateValidationEntity.template_id;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            str4 = templateValidationEntity.template_version;
        }
        String str9 = str4;
        if ((i2 & 16) != 0) {
            str5 = templateValidationEntity.validasi_version;
        }
        String str10 = str5;
        if ((i2 & 32) != 0) {
            i = templateValidationEntity.formEngineId;
        }
        int i3 = i;
        if ((i2 & 64) != 0) {
            str6 = templateValidationEntity.formEngineBrandName;
        }
        return templateValidationEntity.copy(str, str7, str8, str9, str10, i3, str6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSurvey_id() {
        return this.survey_id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTemplate_id() {
        return this.template_id;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTemplate_version() {
        return this.template_version;
    }

    /* renamed from: component5, reason: from getter */
    public final String getValidasi_version() {
        return this.validasi_version;
    }

    /* renamed from: component6, reason: from getter */
    public final int getFormEngineId() {
        return this.formEngineId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getFormEngineBrandName() {
        return this.formEngineBrandName;
    }

    public final TemplateValidationEntity copy(String survey_id, String userId, String template_id, String template_version, String validasi_version, int formEngineId, String formEngineBrandName) {
        Intrinsics.checkNotNullParameter(survey_id, "survey_id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(template_id, "template_id");
        return new TemplateValidationEntity(survey_id, userId, template_id, template_version, validasi_version, formEngineId, formEngineBrandName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemplateValidationEntity)) {
            return false;
        }
        TemplateValidationEntity templateValidationEntity = (TemplateValidationEntity) other;
        return Intrinsics.areEqual(this.survey_id, templateValidationEntity.survey_id) && Intrinsics.areEqual(this.userId, templateValidationEntity.userId) && Intrinsics.areEqual(this.template_id, templateValidationEntity.template_id) && Intrinsics.areEqual(this.template_version, templateValidationEntity.template_version) && Intrinsics.areEqual(this.validasi_version, templateValidationEntity.validasi_version) && this.formEngineId == templateValidationEntity.formEngineId && Intrinsics.areEqual(this.formEngineBrandName, templateValidationEntity.formEngineBrandName);
    }

    public int hashCode() {
        int iHashCode = ((((this.survey_id.hashCode() * 31) + this.userId.hashCode()) * 31) + this.template_id.hashCode()) * 31;
        String str = this.template_version;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.validasi_version;
        int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + Integer.hashCode(this.formEngineId)) * 31;
        String str3 = this.formEngineBrandName;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "TemplateValidationEntity(survey_id=" + this.survey_id + ", userId=" + this.userId + ", template_id=" + this.template_id + ", template_version=" + this.template_version + ", validasi_version=" + this.validasi_version + ", formEngineId=" + this.formEngineId + ", formEngineBrandName=" + this.formEngineBrandName + ")";
    }

    public TemplateValidationEntity(String survey_id, String userId, String template_id, String str, String str2, int i, String str3) {
        Intrinsics.checkNotNullParameter(survey_id, "survey_id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(template_id, "template_id");
        this.survey_id = survey_id;
        this.userId = userId;
        this.template_id = template_id;
        this.template_version = str;
        this.validasi_version = str2;
        this.formEngineId = i;
        this.formEngineBrandName = str3;
    }

    public /* synthetic */ TemplateValidationEntity(String str, String str2, String str3, String str4, String str5, int i, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? 1 : i, (i2 & 64) != 0 ? null : str6);
    }

    public final String getSurvey_id() {
        return this.survey_id;
    }

    public final void setSurvey_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.survey_id = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userId = str;
    }

    public final String getTemplate_id() {
        return this.template_id;
    }

    public final void setTemplate_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.template_id = str;
    }

    public final String getTemplate_version() {
        return this.template_version;
    }

    public final void setTemplate_version(String str) {
        this.template_version = str;
    }

    public final String getValidasi_version() {
        return this.validasi_version;
    }

    public final void setValidasi_version(String str) {
        this.validasi_version = str;
    }

    public final int getFormEngineId() {
        return this.formEngineId;
    }

    public final void setFormEngineId(int i) {
        this.formEngineId = i;
    }

    public final String getFormEngineBrandName() {
        return this.formEngineBrandName;
    }

    public final void setFormEngineBrandName(String str) {
        this.formEngineBrandName = str;
    }

    public TemplateValidationEntity() {
        this("", "", "", "", "", 1, "");
    }

    /* compiled from: TemplateValidationEntity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity$Companion;", "", "()V", "mapIdToTemplateValidation", "Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;", "templateValidationEntity", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TemplateValidationEntity mapIdToTemplateValidation(TemplateValidationEntity templateValidationEntity) {
            Intrinsics.checkNotNullParameter(templateValidationEntity, "templateValidationEntity");
            templateValidationEntity.setUserId(FasihApp.INSTANCE.getSession().getUserId().toString());
            return templateValidationEntity;
        }
    }
}
