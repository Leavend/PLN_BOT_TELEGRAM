package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: S3Model.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/local/models/PresignedUrlsItem;", "", "fileName", "", FirebaseAnalytics.Param.METHOD, "presignedUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFileName", "()Ljava/lang/String;", "getMethod", "getPresignedUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class PresignedUrlsItem {
    public static final int $stable = 0;

    @SerializedName("fileName")
    private final String fileName;

    @SerializedName(FirebaseAnalytics.Param.METHOD)
    private final String method;

    @SerializedName("presignedUrl")
    private final String presignedUrl;

    public PresignedUrlsItem() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ PresignedUrlsItem copy$default(PresignedUrlsItem presignedUrlsItem, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = presignedUrlsItem.fileName;
        }
        if ((i & 2) != 0) {
            str2 = presignedUrlsItem.method;
        }
        if ((i & 4) != 0) {
            str3 = presignedUrlsItem.presignedUrl;
        }
        return presignedUrlsItem.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPresignedUrl() {
        return this.presignedUrl;
    }

    public final PresignedUrlsItem copy(String fileName, String method, String presignedUrl) {
        return new PresignedUrlsItem(fileName, method, presignedUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PresignedUrlsItem)) {
            return false;
        }
        PresignedUrlsItem presignedUrlsItem = (PresignedUrlsItem) other;
        return Intrinsics.areEqual(this.fileName, presignedUrlsItem.fileName) && Intrinsics.areEqual(this.method, presignedUrlsItem.method) && Intrinsics.areEqual(this.presignedUrl, presignedUrlsItem.presignedUrl);
    }

    public int hashCode() {
        String str = this.fileName;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.method;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.presignedUrl;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "PresignedUrlsItem(fileName=" + this.fileName + ", method=" + this.method + ", presignedUrl=" + this.presignedUrl + ")";
    }

    public PresignedUrlsItem(String str, String str2, String str3) {
        this.fileName = str;
        this.method = str2;
        this.presignedUrl = str3;
    }

    public /* synthetic */ PresignedUrlsItem(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final String getMethod() {
        return this.method;
    }

    public final String getPresignedUrl() {
        return this.presignedUrl;
    }
}
