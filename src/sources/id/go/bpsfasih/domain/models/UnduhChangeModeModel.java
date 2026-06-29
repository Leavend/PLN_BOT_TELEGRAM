package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UnduhChangeModeModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\bV\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 w2\u00020\u0001:\u0001wB©\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u001eJ\t\u0010V\u001a\u00020\u0003HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010g\u001a\u00020\u001cHÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010i\u001a\u00020\u0003HÆ\u0003J\t\u0010j\u001a\u00020\u0003HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003J·\u0002\u0010p\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010q\u001a\u00020\u001c2\b\u0010r\u001a\u0004\u0018\u00010sHÖ\u0003J\t\u0010t\u001a\u00020uHÖ\u0001J\t\u0010v\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010 \"\u0004\b$\u0010\"R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010 \"\u0004\b*\u0010\"R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010 \"\u0004\b.\u0010\"R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010 \"\u0004\b0\u0010\"R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010 \"\u0004\b2\u0010\"R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010 \"\u0004\b4\u0010\"R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010 \"\u0004\b6\u0010\"R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010 \"\u0004\b8\u0010\"R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010 \"\u0004\b:\u0010\"R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010;\"\u0004\b<\u0010=R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010 \"\u0004\b?\u0010\"R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010 \"\u0004\bA\u0010\"R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010 \"\u0004\bC\u0010\"R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010 \"\u0004\bE\u0010\"R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010 \"\u0004\bG\u0010\"R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010 \"\u0004\bI\u0010\"R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010 \"\u0004\bK\u0010\"R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010 \"\u0004\bM\u0010\"R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010 \"\u0004\bO\u0010\"R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010 \"\u0004\bQ\u0010\"R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010 \"\u0004\bS\u0010\"R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010 \"\u0004\bU\u0010\"¨\u0006x"}, d2 = {"Lid/go/bpsfasih/domain/models/UnduhChangeModeModel;", "Ljava/io/Serializable;", "idPrimary", "", DownloadModel.ID, "surveyId", "periodeId", "label1", "data1", "label2", "data2", "label3", "data3", "label4", "data4", "label5", "data5", "label6", "data6", "label7", "data7", "label8", "data8", "label9", "data9", "label10", "data10", "isDownloaded", "", "basePath", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getBasePath", "()Ljava/lang/String;", "setBasePath", "(Ljava/lang/String;)V", "getData1", "setData1", "getData10", "setData10", "getData2", "setData2", "getData3", "setData3", "getData4", "setData4", "getData5", "setData5", "getData6", "setData6", "getData7", "setData7", "getData8", "setData8", "getData9", "setData9", "getId", "setId", "getIdPrimary", "setIdPrimary", "()Z", "setDownloaded", "(Z)V", "getLabel1", "setLabel1", "getLabel10", "setLabel10", "getLabel2", "setLabel2", "getLabel3", "setLabel3", "getLabel4", "setLabel4", "getLabel5", "setLabel5", "getLabel6", "setLabel6", "getLabel7", "setLabel7", "getLabel8", "setLabel8", "getLabel9", "setLabel9", "getPeriodeId", "setPeriodeId", "getSurveyId", "setSurveyId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class UnduhChangeModeModel implements Serializable {
    private String basePath;
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
    private String idPrimary;
    private boolean isDownloaded;
    private String label1;
    private String label10;
    private String label2;
    private String label3;
    private String label4;
    private String label5;
    private String label6;
    private String label7;
    private String label8;
    private String label9;
    private String periodeId;
    private String surveyId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* renamed from: component1, reason: from getter */
    public final String getIdPrimary() {
        return this.idPrimary;
    }

    /* renamed from: component10, reason: from getter */
    public final String getData3() {
        return this.data3;
    }

    /* renamed from: component11, reason: from getter */
    public final String getLabel4() {
        return this.label4;
    }

    /* renamed from: component12, reason: from getter */
    public final String getData4() {
        return this.data4;
    }

    /* renamed from: component13, reason: from getter */
    public final String getLabel5() {
        return this.label5;
    }

    /* renamed from: component14, reason: from getter */
    public final String getData5() {
        return this.data5;
    }

    /* renamed from: component15, reason: from getter */
    public final String getLabel6() {
        return this.label6;
    }

    /* renamed from: component16, reason: from getter */
    public final String getData6() {
        return this.data6;
    }

    /* renamed from: component17, reason: from getter */
    public final String getLabel7() {
        return this.label7;
    }

    /* renamed from: component18, reason: from getter */
    public final String getData7() {
        return this.data7;
    }

    /* renamed from: component19, reason: from getter */
    public final String getLabel8() {
        return this.label8;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component20, reason: from getter */
    public final String getData8() {
        return this.data8;
    }

    /* renamed from: component21, reason: from getter */
    public final String getLabel9() {
        return this.label9;
    }

    /* renamed from: component22, reason: from getter */
    public final String getData9() {
        return this.data9;
    }

    /* renamed from: component23, reason: from getter */
    public final String getLabel10() {
        return this.label10;
    }

    /* renamed from: component24, reason: from getter */
    public final String getData10() {
        return this.data10;
    }

    /* renamed from: component25, reason: from getter */
    public final boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    /* renamed from: component26, reason: from getter */
    public final String getBasePath() {
        return this.basePath;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPeriodeId() {
        return this.periodeId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getLabel1() {
        return this.label1;
    }

    /* renamed from: component6, reason: from getter */
    public final String getData1() {
        return this.data1;
    }

    /* renamed from: component7, reason: from getter */
    public final String getLabel2() {
        return this.label2;
    }

    /* renamed from: component8, reason: from getter */
    public final String getData2() {
        return this.data2;
    }

    /* renamed from: component9, reason: from getter */
    public final String getLabel3() {
        return this.label3;
    }

    public final UnduhChangeModeModel copy(String idPrimary, String id2, String surveyId, String periodeId, String label1, String data1, String label2, String data2, String label3, String data3, String label4, String data4, String label5, String data5, String label6, String data6, String label7, String data7, String label8, String data8, String label9, String data9, String label10, String data10, boolean isDownloaded, String basePath) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        return new UnduhChangeModeModel(idPrimary, id2, surveyId, periodeId, label1, data1, label2, data2, label3, data3, label4, data4, label5, data5, label6, data6, label7, data7, label8, data8, label9, data9, label10, data10, isDownloaded, basePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UnduhChangeModeModel)) {
            return false;
        }
        UnduhChangeModeModel unduhChangeModeModel = (UnduhChangeModeModel) other;
        return Intrinsics.areEqual(this.idPrimary, unduhChangeModeModel.idPrimary) && Intrinsics.areEqual(this.id, unduhChangeModeModel.id) && Intrinsics.areEqual(this.surveyId, unduhChangeModeModel.surveyId) && Intrinsics.areEqual(this.periodeId, unduhChangeModeModel.periodeId) && Intrinsics.areEqual(this.label1, unduhChangeModeModel.label1) && Intrinsics.areEqual(this.data1, unduhChangeModeModel.data1) && Intrinsics.areEqual(this.label2, unduhChangeModeModel.label2) && Intrinsics.areEqual(this.data2, unduhChangeModeModel.data2) && Intrinsics.areEqual(this.label3, unduhChangeModeModel.label3) && Intrinsics.areEqual(this.data3, unduhChangeModeModel.data3) && Intrinsics.areEqual(this.label4, unduhChangeModeModel.label4) && Intrinsics.areEqual(this.data4, unduhChangeModeModel.data4) && Intrinsics.areEqual(this.label5, unduhChangeModeModel.label5) && Intrinsics.areEqual(this.data5, unduhChangeModeModel.data5) && Intrinsics.areEqual(this.label6, unduhChangeModeModel.label6) && Intrinsics.areEqual(this.data6, unduhChangeModeModel.data6) && Intrinsics.areEqual(this.label7, unduhChangeModeModel.label7) && Intrinsics.areEqual(this.data7, unduhChangeModeModel.data7) && Intrinsics.areEqual(this.label8, unduhChangeModeModel.label8) && Intrinsics.areEqual(this.data8, unduhChangeModeModel.data8) && Intrinsics.areEqual(this.label9, unduhChangeModeModel.label9) && Intrinsics.areEqual(this.data9, unduhChangeModeModel.data9) && Intrinsics.areEqual(this.label10, unduhChangeModeModel.label10) && Intrinsics.areEqual(this.data10, unduhChangeModeModel.data10) && this.isDownloaded == unduhChangeModeModel.isDownloaded && Intrinsics.areEqual(this.basePath, unduhChangeModeModel.basePath);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((this.idPrimary.hashCode() * 31) + this.id.hashCode()) * 31) + this.surveyId.hashCode()) * 31) + this.periodeId.hashCode()) * 31;
        String str = this.label1;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.data1;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.label2;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.data2;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.label3;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.data3;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.label4;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.data4;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.label5;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.data5;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.label6;
        int iHashCode12 = (iHashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.data6;
        int iHashCode13 = (iHashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.label7;
        int iHashCode14 = (iHashCode13 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.data7;
        int iHashCode15 = (iHashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.label8;
        int iHashCode16 = (iHashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.data8;
        int iHashCode17 = (iHashCode16 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.label9;
        int iHashCode18 = (iHashCode17 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.data9;
        int iHashCode19 = (iHashCode18 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.label10;
        int iHashCode20 = (iHashCode19 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.data10;
        int iHashCode21 = (iHashCode20 + (str20 == null ? 0 : str20.hashCode())) * 31;
        boolean z = this.isDownloaded;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode21 + i) * 31;
        String str21 = this.basePath;
        return i2 + (str21 != null ? str21.hashCode() : 0);
    }

    public String toString() {
        return "UnduhChangeModeModel(idPrimary=" + this.idPrimary + ", id=" + this.id + ", surveyId=" + this.surveyId + ", periodeId=" + this.periodeId + ", label1=" + this.label1 + ", data1=" + this.data1 + ", label2=" + this.label2 + ", data2=" + this.data2 + ", label3=" + this.label3 + ", data3=" + this.data3 + ", label4=" + this.label4 + ", data4=" + this.data4 + ", label5=" + this.label5 + ", data5=" + this.data5 + ", label6=" + this.label6 + ", data6=" + this.data6 + ", label7=" + this.label7 + ", data7=" + this.data7 + ", label8=" + this.label8 + ", data8=" + this.data8 + ", label9=" + this.label9 + ", data9=" + this.data9 + ", label10=" + this.label10 + ", data10=" + this.data10 + ", isDownloaded=" + this.isDownloaded + ", basePath=" + this.basePath + ")";
    }

    public UnduhChangeModeModel(String idPrimary, String id2, String surveyId, String periodeId, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, boolean z, String str21) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        this.idPrimary = idPrimary;
        this.id = id2;
        this.surveyId = surveyId;
        this.periodeId = periodeId;
        this.label1 = str;
        this.data1 = str2;
        this.label2 = str3;
        this.data2 = str4;
        this.label3 = str5;
        this.data3 = str6;
        this.label4 = str7;
        this.data4 = str8;
        this.label5 = str9;
        this.data5 = str10;
        this.label6 = str11;
        this.data6 = str12;
        this.label7 = str13;
        this.data7 = str14;
        this.label8 = str15;
        this.data8 = str16;
        this.label9 = str17;
        this.data9 = str18;
        this.label10 = str19;
        this.data10 = str20;
        this.isDownloaded = z;
        this.basePath = str21;
    }

    public /* synthetic */ UnduhChangeModeModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, boolean z, String str25, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : str10, (i & 1024) != 0 ? null : str11, (i & 2048) != 0 ? null : str12, (i & 4096) != 0 ? null : str13, (i & 8192) != 0 ? null : str14, (i & 16384) != 0 ? null : str15, (32768 & i) != 0 ? null : str16, (65536 & i) != 0 ? null : str17, (131072 & i) != 0 ? null : str18, (262144 & i) != 0 ? null : str19, (524288 & i) != 0 ? null : str20, (1048576 & i) != 0 ? null : str21, (2097152 & i) != 0 ? null : str22, (4194304 & i) != 0 ? null : str23, (8388608 & i) != 0 ? null : str24, (i & 16777216) != 0 ? false : z, str25);
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

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.surveyId = str;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final void setPeriodeId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.periodeId = str;
    }

    public final String getLabel1() {
        return this.label1;
    }

    public final void setLabel1(String str) {
        this.label1 = str;
    }

    public final String getData1() {
        return this.data1;
    }

    public final void setData1(String str) {
        this.data1 = str;
    }

    public final String getLabel2() {
        return this.label2;
    }

    public final void setLabel2(String str) {
        this.label2 = str;
    }

    public final String getData2() {
        return this.data2;
    }

    public final void setData2(String str) {
        this.data2 = str;
    }

    public final String getLabel3() {
        return this.label3;
    }

    public final void setLabel3(String str) {
        this.label3 = str;
    }

    public final String getData3() {
        return this.data3;
    }

    public final void setData3(String str) {
        this.data3 = str;
    }

    public final String getLabel4() {
        return this.label4;
    }

    public final void setLabel4(String str) {
        this.label4 = str;
    }

    public final String getData4() {
        return this.data4;
    }

    public final void setData4(String str) {
        this.data4 = str;
    }

    public final String getLabel5() {
        return this.label5;
    }

    public final void setLabel5(String str) {
        this.label5 = str;
    }

    public final String getData5() {
        return this.data5;
    }

    public final void setData5(String str) {
        this.data5 = str;
    }

    public final String getLabel6() {
        return this.label6;
    }

    public final void setLabel6(String str) {
        this.label6 = str;
    }

    public final String getData6() {
        return this.data6;
    }

    public final void setData6(String str) {
        this.data6 = str;
    }

    public final String getLabel7() {
        return this.label7;
    }

    public final void setLabel7(String str) {
        this.label7 = str;
    }

    public final String getData7() {
        return this.data7;
    }

    public final void setData7(String str) {
        this.data7 = str;
    }

    public final String getLabel8() {
        return this.label8;
    }

    public final void setLabel8(String str) {
        this.label8 = str;
    }

    public final String getData8() {
        return this.data8;
    }

    public final void setData8(String str) {
        this.data8 = str;
    }

    public final String getLabel9() {
        return this.label9;
    }

    public final void setLabel9(String str) {
        this.label9 = str;
    }

    public final String getData9() {
        return this.data9;
    }

    public final void setData9(String str) {
        this.data9 = str;
    }

    public final String getLabel10() {
        return this.label10;
    }

    public final void setLabel10(String str) {
        this.label10 = str;
    }

    public final String getData10() {
        return this.data10;
    }

    public final void setData10(String str) {
        this.data10 = str;
    }

    public final boolean isDownloaded() {
        return this.isDownloaded;
    }

    public final void setDownloaded(boolean z) {
        this.isDownloaded = z;
    }

    public final String getBasePath() {
        return this.basePath;
    }

    public final void setBasePath(String str) {
        this.basePath = str;
    }

    /* compiled from: UnduhChangeModeModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0004¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/domain/models/UnduhChangeModeModel$Companion;", "", "()V", "map", "", "Lid/go/bpsfasih/domain/models/UnduhChangeModeModel;", "assignments", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "customData", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "listDownloaded", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:65:0x0119  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.util.List<id.go.bpsfasih.domain.models.UnduhChangeModeModel> map(java.util.List<id.go.bpsfasih.data.local.entities.AssignmentEntity> r33, id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity r34, java.util.List<java.lang.String> r35) {
            /*
                Method dump skipped, instructions count: 322
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.domain.models.UnduhChangeModeModel.Companion.map(java.util.List, id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity, java.util.List):java.util.List");
        }
    }
}
