package id.go.bpsfasih.ui.sistem;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import id.go.bpsfasih.BuildConfig;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.FileHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SistemViewModel.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u000208J\u0010\u0010:\u001a\u0004\u0018\u00010\u00102\u0006\u0010;\u001a\u00020\u0010J\u0006\u0010<\u001a\u000208R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000bR\"\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR\"\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\t\"\u0004\b&\u0010\u000bR \u0010'\u001a\b\u0012\u0004\u0012\u00020\u00100(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R\"\u00100\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010*\"\u0004\b3\u0010,R\"\u00104\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010*\"\u0004\b6\u0010,¨\u0006="}, d2 = {"Lid/go/bpsfasih/ui/sistem/SistemViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "changeLogAplikasiIsClicked", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getChangeLogAplikasiIsClicked", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setChangeLogAplikasiIsClicked", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "changeLogFormGearIsClicked", "getChangeLogFormGearIsClicked", "setChangeLogFormGearIsClicked", "downloadFormGearMessage", "", "getDownloadFormGearMessage", "()Ljava/lang/String;", "setDownloadFormGearMessage", "(Ljava/lang/String;)V", "downloadFormGearStatsus", "getDownloadFormGearStatsus", "()Ljava/lang/Boolean;", "setDownloadFormGearStatsus", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "downloadIsDone", "getDownloadIsDone", "setDownloadIsDone", "formGearVersionIsClicked", "getFormGearVersionIsClicked", "setFormGearVersionIsClicked", "memoryInfo", "getMemoryInfo", "setMemoryInfo", "sessionLogout", "getSessionLogout", "setSessionLogout", "versiApp", "Landroidx/databinding/ObservableField;", "getVersiApp", "()Landroidx/databinding/ObservableField;", "setVersiApp", "(Landroidx/databinding/ObservableField;)V", "versiCode", "getVersiCode", "setVersiCode", "visibilityFormGearDownload", "", "getVisibilityFormGearDownload", "setVisibilityFormGearDownload", "visibilityFormGearVersi", "getVisibilityFormGearVersi", "setVisibilityFormGearVersi", "changeLogAplikasiClicked", "", "changeLogFormGearClicked", "getFormGearVer", "formgearId", "memoryInfoClicked", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SistemViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private SingleLiveEvent<Boolean> changeLogAplikasiIsClicked;
    private SingleLiveEvent<Boolean> changeLogFormGearIsClicked;
    private String downloadFormGearMessage;
    private Boolean downloadFormGearStatsus;
    private SingleLiveEvent<Boolean> downloadIsDone;
    private SingleLiveEvent<Boolean> formGearVersionIsClicked;
    private SingleLiveEvent<Boolean> memoryInfo;
    private SingleLiveEvent<Boolean> sessionLogout;
    private ObservableField<String> versiApp;
    private ObservableField<String> versiCode;
    private ObservableField<Integer> visibilityFormGearDownload;
    private ObservableField<Integer> visibilityFormGearVersi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SistemViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.visibilityFormGearVersi = new ObservableField<>(8);
        this.visibilityFormGearDownload = new ObservableField<>(0);
        this.versiApp = new ObservableField<>(BuildConfig.VERSION_NAME);
        this.versiCode = new ObservableField<>("126");
        this.downloadFormGearStatsus = false;
        this.memoryInfo = new SingleLiveEvent<>();
        this.changeLogAplikasiIsClicked = new SingleLiveEvent<>();
        this.changeLogFormGearIsClicked = new SingleLiveEvent<>();
        this.formGearVersionIsClicked = new SingleLiveEvent<>();
        this.downloadIsDone = new SingleLiveEvent<>();
        this.sessionLogout = new SingleLiveEvent<>();
    }

    public final ObservableField<Integer> getVisibilityFormGearVersi() {
        return this.visibilityFormGearVersi;
    }

    public final void setVisibilityFormGearVersi(ObservableField<Integer> observableField) {
        this.visibilityFormGearVersi = observableField;
    }

    public final ObservableField<Integer> getVisibilityFormGearDownload() {
        return this.visibilityFormGearDownload;
    }

    public final void setVisibilityFormGearDownload(ObservableField<Integer> observableField) {
        this.visibilityFormGearDownload = observableField;
    }

    public final ObservableField<String> getVersiApp() {
        return this.versiApp;
    }

    public final void setVersiApp(ObservableField<String> observableField) {
        Intrinsics.checkNotNullParameter(observableField, "<set-?>");
        this.versiApp = observableField;
    }

    public final ObservableField<String> getVersiCode() {
        return this.versiCode;
    }

    public final void setVersiCode(ObservableField<String> observableField) {
        Intrinsics.checkNotNullParameter(observableField, "<set-?>");
        this.versiCode = observableField;
    }

    public final SingleLiveEvent<Boolean> getMemoryInfo() {
        return this.memoryInfo;
    }

    public final void setMemoryInfo(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.memoryInfo = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getFormGearVersionIsClicked() {
        return this.formGearVersionIsClicked;
    }

    public final void setFormGearVersionIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.formGearVersionIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getChangeLogAplikasiIsClicked() {
        return this.changeLogAplikasiIsClicked;
    }

    public final void setChangeLogAplikasiIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.changeLogAplikasiIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getChangeLogFormGearIsClicked() {
        return this.changeLogFormGearIsClicked;
    }

    public final void setChangeLogFormGearIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.changeLogFormGearIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getDownloadIsDone() {
        return this.downloadIsDone;
    }

    public final void setDownloadIsDone(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.downloadIsDone = singleLiveEvent;
    }

    public final Boolean getDownloadFormGearStatsus() {
        return this.downloadFormGearStatsus;
    }

    public final void setDownloadFormGearStatsus(Boolean bool) {
        this.downloadFormGearStatsus = bool;
    }

    public final String getDownloadFormGearMessage() {
        return this.downloadFormGearMessage;
    }

    public final void setDownloadFormGearMessage(String str) {
        this.downloadFormGearMessage = str;
    }

    public final SingleLiveEvent<Boolean> getSessionLogout() {
        return this.sessionLogout;
    }

    public final void setSessionLogout(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.sessionLogout = singleLiveEvent;
    }

    public final void memoryInfoClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.memoryInfo;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void changeLogAplikasiClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.changeLogAplikasiIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void changeLogFormGearClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.changeLogFormGearIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final String getFormGearVer(String formgearId) throws JSONException {
        Intrinsics.checkNotNullParameter(formgearId, "formgearId");
        try {
            String string = new JSONObject(FileHelper.INSTANCE.readFile(Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + formgearId + "/version.json").toString()).getString(ClientCookie.VERSION_ATTR);
            ObservableField<Integer> observableField = this.visibilityFormGearVersi;
            if (observableField != null) {
                observableField.set(0);
            }
            return string;
        } catch (JSONException unused) {
            ObservableField<Integer> observableField2 = this.visibilityFormGearDownload;
            if (observableField2 != null) {
                observableField2.set(0);
            }
            ObservableField<Integer> observableField3 = this.visibilityFormGearVersi;
            if (observableField3 == null) {
                return "";
            }
            observableField3.set(8);
            return "";
        }
    }
}
