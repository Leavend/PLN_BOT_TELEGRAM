package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.Expose;
import kotlin.Metadata;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: SignalInfoModel.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/domain/models/SignalInfoModel;", "", "()V", "detailSignalStrength", "", "getDetailSignalStrength", "()Ljava/lang/String;", "setDetailSignalStrength", "(Ljava/lang/String;)V", DatabaseFileArchive.COLUMN_PROVIDER, "getProvider", "setProvider", "type", "getType", "setType", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SignalInfoModel {
    public static final int $stable = 8;

    @Expose
    private String detailSignalStrength;

    @Expose
    private String provider;

    @Expose
    private String type;

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final void setProvider(String str) {
        this.provider = str;
    }

    public final String getDetailSignalStrength() {
        return this.detailSignalStrength;
    }

    public final void setDetailSignalStrength(String str) {
        this.detailSignalStrength = str;
    }
}
