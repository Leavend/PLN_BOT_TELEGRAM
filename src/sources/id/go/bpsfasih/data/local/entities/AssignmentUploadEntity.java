package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentUploadEntity.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\bP\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002Bó\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c¢\u0006\u0002\u0010\u001dJ\t\u0010Q\u001a\u00020\u0004HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\\\u001a\u00020\u0004HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010a\u001a\u00020\u001cHÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0004HÆ\u0003J£\u0002\u0010i\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u001cHÆ\u0001J\u0013\u0010j\u001a\u00020\u001c2\b\u0010k\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010l\u001a\u00020mHÖ\u0001J\t\u0010n\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010!R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001f\"\u0004\b)\u0010!R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001f\"\u0004\b+\u0010!R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001f\"\u0004\b-\u0010!R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001f\"\u0004\b/\u0010!R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001f\"\u0004\b1\u0010!R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001f\"\u0004\b3\u0010!R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001f\"\u0004\b5\u0010!R\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u00106\"\u0004\b7\u00108R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001f\"\u0004\b:\u0010!R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u001f\"\u0004\b<\u0010!R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001f\"\u0004\b>\u0010!R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u001f\"\u0004\b@\u0010!R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001f\"\u0004\bB\u0010!R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001f\"\u0004\bD\u0010!R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\u001f\"\u0004\bF\u0010!R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u001f\"\u0004\bH\u0010!R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u001f\"\u0004\bJ\u0010!R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\u001f\"\u0004\bL\u0010!R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u001f\"\u0004\bN\u0010!R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u001f\"\u0004\bP\u0010!¨\u0006o"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentUploadEntity;", "", "()V", DownloadModel.ID, "", "userId", "templateId", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", "labelData1", "labelData2", "labelData3", "labelData4", "labelData5", "labelData6", "labelData7", "labelData8", "labelData9", "labelData10", "isUploadSuccessful", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getData1", "()Ljava/lang/String;", "setData1", "(Ljava/lang/String;)V", "getData10", "setData10", "getData2", "setData2", "getData3", "setData3", "getData4", "setData4", "getData5", "setData5", "getData6", "setData6", "getData7", "setData7", "getData8", "setData8", "getData9", "setData9", "getId", "setId", "()Z", "setUploadSuccessful", "(Z)V", "getLabelData1", "setLabelData1", "getLabelData10", "setLabelData10", "getLabelData2", "setLabelData2", "getLabelData3", "setLabelData3", "getLabelData4", "setLabelData4", "getLabelData5", "setLabelData5", "getLabelData6", "setLabelData6", "getLabelData7", "setLabelData7", "getLabelData8", "setLabelData8", "getLabelData9", "setLabelData9", "getTemplateId", "setTemplateId", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentUploadEntity {
    public static final int $stable = 8;
    private String data1;
    private String data10;
    private String data2;
    private String data3;
    private String data4;
    private String data5;
    private String data6;
    private String data7;
    private String data8;
    private String data9;
    private String id;
    private boolean isUploadSuccessful;
    private String labelData1;
    private String labelData10;
    private String labelData2;
    private String labelData3;
    private String labelData4;
    private String labelData5;
    private String labelData6;
    private String labelData7;
    private String labelData8;
    private String labelData9;
    private String templateId;
    private String userId;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getData7() {
        return this.data7;
    }

    /* renamed from: component11, reason: from getter */
    public final String getData8() {
        return this.data8;
    }

    /* renamed from: component12, reason: from getter */
    public final String getData9() {
        return this.data9;
    }

    /* renamed from: component13, reason: from getter */
    public final String getData10() {
        return this.data10;
    }

    /* renamed from: component14, reason: from getter */
    public final String getLabelData1() {
        return this.labelData1;
    }

    /* renamed from: component15, reason: from getter */
    public final String getLabelData2() {
        return this.labelData2;
    }

    /* renamed from: component16, reason: from getter */
    public final String getLabelData3() {
        return this.labelData3;
    }

    /* renamed from: component17, reason: from getter */
    public final String getLabelData4() {
        return this.labelData4;
    }

    /* renamed from: component18, reason: from getter */
    public final String getLabelData5() {
        return this.labelData5;
    }

    /* renamed from: component19, reason: from getter */
    public final String getLabelData6() {
        return this.labelData6;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component20, reason: from getter */
    public final String getLabelData7() {
        return this.labelData7;
    }

    /* renamed from: component21, reason: from getter */
    public final String getLabelData8() {
        return this.labelData8;
    }

    /* renamed from: component22, reason: from getter */
    public final String getLabelData9() {
        return this.labelData9;
    }

    /* renamed from: component23, reason: from getter */
    public final String getLabelData10() {
        return this.labelData10;
    }

    /* renamed from: component24, reason: from getter */
    public final boolean getIsUploadSuccessful() {
        return this.isUploadSuccessful;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTemplateId() {
        return this.templateId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getData1() {
        return this.data1;
    }

    /* renamed from: component5, reason: from getter */
    public final String getData2() {
        return this.data2;
    }

    /* renamed from: component6, reason: from getter */
    public final String getData3() {
        return this.data3;
    }

    /* renamed from: component7, reason: from getter */
    public final String getData4() {
        return this.data4;
    }

    /* renamed from: component8, reason: from getter */
    public final String getData5() {
        return this.data5;
    }

    /* renamed from: component9, reason: from getter */
    public final String getData6() {
        return this.data6;
    }

    public final AssignmentUploadEntity copy(String id2, String userId, String templateId, String data1, String data2, String data3, String data4, String data5, String data6, String data7, String data8, String data9, String data10, String labelData1, String labelData2, String labelData3, String labelData4, String labelData5, String labelData6, String labelData7, String labelData8, String labelData9, String labelData10, boolean isUploadSuccessful) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        return new AssignmentUploadEntity(id2, userId, templateId, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, labelData1, labelData2, labelData3, labelData4, labelData5, labelData6, labelData7, labelData8, labelData9, labelData10, isUploadSuccessful);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentUploadEntity)) {
            return false;
        }
        AssignmentUploadEntity assignmentUploadEntity = (AssignmentUploadEntity) other;
        return Intrinsics.areEqual(this.id, assignmentUploadEntity.id) && Intrinsics.areEqual(this.userId, assignmentUploadEntity.userId) && Intrinsics.areEqual(this.templateId, assignmentUploadEntity.templateId) && Intrinsics.areEqual(this.data1, assignmentUploadEntity.data1) && Intrinsics.areEqual(this.data2, assignmentUploadEntity.data2) && Intrinsics.areEqual(this.data3, assignmentUploadEntity.data3) && Intrinsics.areEqual(this.data4, assignmentUploadEntity.data4) && Intrinsics.areEqual(this.data5, assignmentUploadEntity.data5) && Intrinsics.areEqual(this.data6, assignmentUploadEntity.data6) && Intrinsics.areEqual(this.data7, assignmentUploadEntity.data7) && Intrinsics.areEqual(this.data8, assignmentUploadEntity.data8) && Intrinsics.areEqual(this.data9, assignmentUploadEntity.data9) && Intrinsics.areEqual(this.data10, assignmentUploadEntity.data10) && Intrinsics.areEqual(this.labelData1, assignmentUploadEntity.labelData1) && Intrinsics.areEqual(this.labelData2, assignmentUploadEntity.labelData2) && Intrinsics.areEqual(this.labelData3, assignmentUploadEntity.labelData3) && Intrinsics.areEqual(this.labelData4, assignmentUploadEntity.labelData4) && Intrinsics.areEqual(this.labelData5, assignmentUploadEntity.labelData5) && Intrinsics.areEqual(this.labelData6, assignmentUploadEntity.labelData6) && Intrinsics.areEqual(this.labelData7, assignmentUploadEntity.labelData7) && Intrinsics.areEqual(this.labelData8, assignmentUploadEntity.labelData8) && Intrinsics.areEqual(this.labelData9, assignmentUploadEntity.labelData9) && Intrinsics.areEqual(this.labelData10, assignmentUploadEntity.labelData10) && this.isUploadSuccessful == assignmentUploadEntity.isUploadSuccessful;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.userId.hashCode()) * 31;
        String str = this.templateId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.data1;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.data2;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.data3;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.data4;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.data5;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.data6;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.data7;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.data8;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.data9;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.data10;
        int iHashCode12 = (iHashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.labelData1;
        int iHashCode13 = (iHashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.labelData2;
        int iHashCode14 = (iHashCode13 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.labelData3;
        int iHashCode15 = (iHashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.labelData4;
        int iHashCode16 = (iHashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.labelData5;
        int iHashCode17 = (iHashCode16 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.labelData6;
        int iHashCode18 = (iHashCode17 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.labelData7;
        int iHashCode19 = (iHashCode18 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.labelData8;
        int iHashCode20 = (iHashCode19 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.labelData9;
        int iHashCode21 = (iHashCode20 + (str20 == null ? 0 : str20.hashCode())) * 31;
        String str21 = this.labelData10;
        int iHashCode22 = (iHashCode21 + (str21 != null ? str21.hashCode() : 0)) * 31;
        boolean z = this.isUploadSuccessful;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode22 + i;
    }

    public String toString() {
        return "AssignmentUploadEntity(id=" + this.id + ", userId=" + this.userId + ", templateId=" + this.templateId + ", data1=" + this.data1 + ", data2=" + this.data2 + ", data3=" + this.data3 + ", data4=" + this.data4 + ", data5=" + this.data5 + ", data6=" + this.data6 + ", data7=" + this.data7 + ", data8=" + this.data8 + ", data9=" + this.data9 + ", data10=" + this.data10 + ", labelData1=" + this.labelData1 + ", labelData2=" + this.labelData2 + ", labelData3=" + this.labelData3 + ", labelData4=" + this.labelData4 + ", labelData5=" + this.labelData5 + ", labelData6=" + this.labelData6 + ", labelData7=" + this.labelData7 + ", labelData8=" + this.labelData8 + ", labelData9=" + this.labelData9 + ", labelData10=" + this.labelData10 + ", isUploadSuccessful=" + this.isUploadSuccessful + ")";
    }

    public AssignmentUploadEntity(String id2, String userId, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, boolean z) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        this.id = id2;
        this.userId = userId;
        this.templateId = str;
        this.data1 = str2;
        this.data2 = str3;
        this.data3 = str4;
        this.data4 = str5;
        this.data5 = str6;
        this.data6 = str7;
        this.data7 = str8;
        this.data8 = str9;
        this.data9 = str10;
        this.data10 = str11;
        this.labelData1 = str12;
        this.labelData2 = str13;
        this.labelData3 = str14;
        this.labelData4 = str15;
        this.labelData5 = str16;
        this.labelData6 = str17;
        this.labelData7 = str18;
        this.labelData8 = str19;
        this.labelData9 = str20;
        this.labelData10 = str21;
        this.isUploadSuccessful = z;
    }

    public /* synthetic */ AssignmentUploadEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, (i & 8388608) != 0 ? false : z);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userId = str;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final void setTemplateId(String str) {
        this.templateId = str;
    }

    public final String getData1() {
        return this.data1;
    }

    public final void setData1(String str) {
        this.data1 = str;
    }

    public final String getData2() {
        return this.data2;
    }

    public final void setData2(String str) {
        this.data2 = str;
    }

    public final String getData3() {
        return this.data3;
    }

    public final void setData3(String str) {
        this.data3 = str;
    }

    public final String getData4() {
        return this.data4;
    }

    public final void setData4(String str) {
        this.data4 = str;
    }

    public final String getData5() {
        return this.data5;
    }

    public final void setData5(String str) {
        this.data5 = str;
    }

    public final String getData6() {
        return this.data6;
    }

    public final void setData6(String str) {
        this.data6 = str;
    }

    public final String getData7() {
        return this.data7;
    }

    public final void setData7(String str) {
        this.data7 = str;
    }

    public final String getData8() {
        return this.data8;
    }

    public final void setData8(String str) {
        this.data8 = str;
    }

    public final String getData9() {
        return this.data9;
    }

    public final void setData9(String str) {
        this.data9 = str;
    }

    public final String getData10() {
        return this.data10;
    }

    public final void setData10(String str) {
        this.data10 = str;
    }

    public final String getLabelData1() {
        return this.labelData1;
    }

    public final void setLabelData1(String str) {
        this.labelData1 = str;
    }

    public final String getLabelData2() {
        return this.labelData2;
    }

    public final void setLabelData2(String str) {
        this.labelData2 = str;
    }

    public final String getLabelData3() {
        return this.labelData3;
    }

    public final void setLabelData3(String str) {
        this.labelData3 = str;
    }

    public final String getLabelData4() {
        return this.labelData4;
    }

    public final void setLabelData4(String str) {
        this.labelData4 = str;
    }

    public final String getLabelData5() {
        return this.labelData5;
    }

    public final void setLabelData5(String str) {
        this.labelData5 = str;
    }

    public final String getLabelData6() {
        return this.labelData6;
    }

    public final void setLabelData6(String str) {
        this.labelData6 = str;
    }

    public final String getLabelData7() {
        return this.labelData7;
    }

    public final void setLabelData7(String str) {
        this.labelData7 = str;
    }

    public final String getLabelData8() {
        return this.labelData8;
    }

    public final void setLabelData8(String str) {
        this.labelData8 = str;
    }

    public final String getLabelData9() {
        return this.labelData9;
    }

    public final void setLabelData9(String str) {
        this.labelData9 = str;
    }

    public final String getLabelData10() {
        return this.labelData10;
    }

    public final void setLabelData10(String str) {
        this.labelData10 = str;
    }

    public final boolean isUploadSuccessful() {
        return this.isUploadSuccessful;
    }

    public final void setUploadSuccessful(boolean z) {
        this.isUploadSuccessful = z;
    }

    public AssignmentUploadEntity() {
        this("", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false);
    }
}
