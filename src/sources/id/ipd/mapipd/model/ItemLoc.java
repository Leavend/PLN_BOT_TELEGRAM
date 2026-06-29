package id.ipd.mapipd.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.model.LatLng;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.MessageBundle;

/* compiled from: ItemLoc.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J5\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lid/ipd/mapipd/model/ItemLoc;", "", DownloadModel.ID, "", "position", "Lcom/google/android/gms/maps/model/LatLng;", MessageBundle.TITLE_ENTRY, "desription", "(Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/lang/String;)V", "getDesription", "()Ljava/lang/String;", "getId", "getPosition", "()Lcom/google/android/gms/maps/model/LatLng;", "getTitle", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "mapipd_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ItemLoc {
    private final String desription;
    private final String id;
    private final LatLng position;
    private final String title;

    public static /* synthetic */ ItemLoc copy$default(ItemLoc itemLoc, String str, LatLng latLng, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = itemLoc.id;
        }
        if ((i & 2) != 0) {
            latLng = itemLoc.position;
        }
        if ((i & 4) != 0) {
            str2 = itemLoc.title;
        }
        if ((i & 8) != 0) {
            str3 = itemLoc.desription;
        }
        return itemLoc.copy(str, latLng, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final LatLng getPosition() {
        return this.position;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDesription() {
        return this.desription;
    }

    public final ItemLoc copy(String id2, LatLng position, String title, String desription) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(position, "position");
        return new ItemLoc(id2, position, title, desription);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ItemLoc)) {
            return false;
        }
        ItemLoc itemLoc = (ItemLoc) other;
        return Intrinsics.areEqual(this.id, itemLoc.id) && Intrinsics.areEqual(this.position, itemLoc.position) && Intrinsics.areEqual(this.title, itemLoc.title) && Intrinsics.areEqual(this.desription, itemLoc.desription);
    }

    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.position.hashCode()) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.desription;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ItemLoc(id=" + this.id + ", position=" + this.position + ", title=" + this.title + ", desription=" + this.desription + ")";
    }

    public ItemLoc(String id2, LatLng position, String str, String str2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(position, "position");
        this.id = id2;
        this.position = position;
        this.title = str;
        this.desription = str2;
    }

    public final String getId() {
        return this.id;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDesription() {
        return this.desription;
    }
}
