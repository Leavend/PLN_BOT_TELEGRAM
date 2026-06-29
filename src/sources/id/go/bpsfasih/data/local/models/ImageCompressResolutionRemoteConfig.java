package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageCompressResolutionRemoteConfig.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ2\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\bR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\b¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/data/local/models/ImageCompressResolutionRemoteConfig;", "", "width", "", "height", "quality", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getHeight", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getQuality", "getWidth", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lid/go/bpsfasih/data/local/models/ImageCompressResolutionRemoteConfig;", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ImageCompressResolutionRemoteConfig {
    public static final int $stable = 0;

    @SerializedName("height")
    private final Integer height;

    @SerializedName("quality")
    private final Integer quality;

    @SerializedName("width")
    private final Integer width;

    public ImageCompressResolutionRemoteConfig() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ ImageCompressResolutionRemoteConfig copy$default(ImageCompressResolutionRemoteConfig imageCompressResolutionRemoteConfig, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = imageCompressResolutionRemoteConfig.width;
        }
        if ((i & 2) != 0) {
            num2 = imageCompressResolutionRemoteConfig.height;
        }
        if ((i & 4) != 0) {
            num3 = imageCompressResolutionRemoteConfig.quality;
        }
        return imageCompressResolutionRemoteConfig.copy(num, num2, num3);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getWidth() {
        return this.width;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getHeight() {
        return this.height;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getQuality() {
        return this.quality;
    }

    public final ImageCompressResolutionRemoteConfig copy(Integer width, Integer height, Integer quality) {
        return new ImageCompressResolutionRemoteConfig(width, height, quality);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageCompressResolutionRemoteConfig)) {
            return false;
        }
        ImageCompressResolutionRemoteConfig imageCompressResolutionRemoteConfig = (ImageCompressResolutionRemoteConfig) other;
        return Intrinsics.areEqual(this.width, imageCompressResolutionRemoteConfig.width) && Intrinsics.areEqual(this.height, imageCompressResolutionRemoteConfig.height) && Intrinsics.areEqual(this.quality, imageCompressResolutionRemoteConfig.quality);
    }

    public int hashCode() {
        Integer num = this.width;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.height;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.quality;
        return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        return "ImageCompressResolutionRemoteConfig(width=" + this.width + ", height=" + this.height + ", quality=" + this.quality + ")";
    }

    public ImageCompressResolutionRemoteConfig(Integer num, Integer num2, Integer num3) {
        this.width = num;
        this.height = num2;
        this.quality = num3;
    }

    public /* synthetic */ ImageCompressResolutionRemoteConfig(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3);
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final Integer getQuality() {
        return this.quality;
    }
}
