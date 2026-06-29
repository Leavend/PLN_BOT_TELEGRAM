package id.go.bpsfasih.ui.hompage.pengaturan_fragment;

import android.app.Application;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.FileHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PengaturanFragmentViewModel.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b&\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010;\u001a\u00020<J\u0006\u0010=\u001a\u00020<J\b\u0010>\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010?\u001a\u00020\u000eJ\u0006\u0010@\u001a\u00020<J\u0006\u0010A\u001a\u00020<J\u0006\u0010B\u001a\u00020<J\u0006\u0010C\u001a\u00020<R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u000bR\"\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\"\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\t\"\u0004\b!\u0010\u000bR\"\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\t\"\u0004\b$\u0010\u000bR\"\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\t\"\u0004\b'\u0010\u000bR\"\u0010(\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0010\"\u0004\b*\u0010\u0012R\"\u0010+\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0010\"\u0004\b-\u0010\u0012R\"\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\t\"\u0004\b0\u0010\u000bR\"\u00101\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0010\"\u0004\b3\u0010\u0012R\"\u00104\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0010\"\u0004\b7\u0010\u0012R\"\u00108\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0010\"\u0004\b:\u0010\u0012¨\u0006D"}, d2 = {"Lid/go/bpsfasih/ui/hompage/pengaturan_fragment/PengaturanFragmentViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "backupRestoreIsClicked", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getBackupRestoreIsClicked", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setBackupRestoreIsClicked", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "email", "Landroidx/databinding/ObservableField;", "", "getEmail", "()Landroidx/databinding/ObservableField;", "setEmail", "(Landroidx/databinding/ObservableField;)V", "engineVersion", "getEngineVersion", "setEngineVersion", "expiredSession", "getExpiredSession", "setExpiredSession", "faqIsClicked", "getFaqIsClicked", "setFaqIsClicked", "foto", "getFoto", "setFoto", "kebijakanPrivasiIsClicked", "getKebijakanPrivasiIsClicked", "setKebijakanPrivasiIsClicked", "liveTrackingIsClicked", "getLiveTrackingIsClicked", "setLiveTrackingIsClicked", "logoutIsClicked", "getLogoutIsClicked", "setLogoutIsClicked", "role", "getRole", "setRole", "serverurl", "getServerurl", "setServerurl", "sistemIsClicked", "getSistemIsClicked", "setSistemIsClicked", HintConstants.AUTOFILL_HINT_USERNAME, "getUsername", "setUsername", "visibilityModeDev_iv", "", "getVisibilityModeDev_iv", "setVisibilityModeDev_iv", "visibilityModeProd_iv", "getVisibilityModeProd_iv", "setVisibilityModeProd_iv", "backupResoreClicked", "", "faqClicked", "getEngineVer", "getSessionExpired", "kebijakanPrivasiClicked", "liveTrackingClicked", "logoutClicked", "sistemClicked", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PengaturanFragmentViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private SingleLiveEvent<Boolean> backupRestoreIsClicked;
    private ObservableField<String> email;
    private ObservableField<String> engineVersion;
    private ObservableField<String> expiredSession;
    private SingleLiveEvent<Boolean> faqIsClicked;
    private ObservableField<String> foto;
    private SingleLiveEvent<Boolean> kebijakanPrivasiIsClicked;
    private SingleLiveEvent<Boolean> liveTrackingIsClicked;
    private SingleLiveEvent<Boolean> logoutIsClicked;
    private ObservableField<String> role;
    private ObservableField<String> serverurl;
    private SingleLiveEvent<Boolean> sistemIsClicked;
    private ObservableField<String> username;
    private ObservableField<Integer> visibilityModeDev_iv;
    private ObservableField<Integer> visibilityModeProd_iv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PengaturanFragmentViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.kebijakanPrivasiIsClicked = new SingleLiveEvent<>();
        this.logoutIsClicked = new SingleLiveEvent<>();
        this.sistemIsClicked = new SingleLiveEvent<>();
        this.backupRestoreIsClicked = new SingleLiveEvent<>();
        this.faqIsClicked = new SingleLiveEvent<>();
        this.liveTrackingIsClicked = new SingleLiveEvent<>();
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME());
        this.username = new ObservableField<>(sessionString == null ? "" : sessionString);
        String sessionString2 = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME());
        String strSubstring = (sessionString2 == null ? "" : sessionString2).substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        this.foto = new ObservableField<>(strSubstring);
        String sessionString3 = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL());
        this.email = new ObservableField<>(sessionString3 == null ? "" : sessionString3);
        this.expiredSession = new ObservableField<>(getSessionExpired());
        String strBASE_URL = new Config().BASE_URL();
        this.serverurl = new ObservableField<>(strBASE_URL == null ? "" : strBASE_URL);
        String engineVer = getEngineVer();
        this.engineVersion = new ObservableField<>(engineVer != null ? engineVer : "");
        this.role = new ObservableField<>("Pencacah");
        if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_DEV, false, 2, null)) {
            this.visibilityModeDev_iv = new ObservableField<>(0);
            this.visibilityModeProd_iv = new ObservableField<>(8);
        } else if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD, false, 2, null)) {
            this.visibilityModeProd_iv = new ObservableField<>(0);
            this.visibilityModeDev_iv = new ObservableField<>(8);
        }
    }

    public final ObservableField<String> getRole() {
        return this.role;
    }

    public final void setRole(ObservableField<String> observableField) {
        this.role = observableField;
    }

    public final ObservableField<String> getUsername() {
        return this.username;
    }

    public final void setUsername(ObservableField<String> observableField) {
        this.username = observableField;
    }

    public final ObservableField<String> getEmail() {
        return this.email;
    }

    public final void setEmail(ObservableField<String> observableField) {
        this.email = observableField;
    }

    public final ObservableField<String> getFoto() {
        return this.foto;
    }

    public final void setFoto(ObservableField<String> observableField) {
        this.foto = observableField;
    }

    public final ObservableField<Integer> getVisibilityModeProd_iv() {
        return this.visibilityModeProd_iv;
    }

    public final void setVisibilityModeProd_iv(ObservableField<Integer> observableField) {
        this.visibilityModeProd_iv = observableField;
    }

    public final ObservableField<Integer> getVisibilityModeDev_iv() {
        return this.visibilityModeDev_iv;
    }

    public final void setVisibilityModeDev_iv(ObservableField<Integer> observableField) {
        this.visibilityModeDev_iv = observableField;
    }

    public final ObservableField<String> getServerurl() {
        return this.serverurl;
    }

    public final void setServerurl(ObservableField<String> observableField) {
        this.serverurl = observableField;
    }

    public final ObservableField<String> getEngineVersion() {
        return this.engineVersion;
    }

    public final void setEngineVersion(ObservableField<String> observableField) {
        this.engineVersion = observableField;
    }

    public final SingleLiveEvent<Boolean> getKebijakanPrivasiIsClicked() {
        return this.kebijakanPrivasiIsClicked;
    }

    public final void setKebijakanPrivasiIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.kebijakanPrivasiIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getLogoutIsClicked() {
        return this.logoutIsClicked;
    }

    public final void setLogoutIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.logoutIsClicked = singleLiveEvent;
    }

    public final ObservableField<String> getExpiredSession() {
        return this.expiredSession;
    }

    public final void setExpiredSession(ObservableField<String> observableField) {
        this.expiredSession = observableField;
    }

    public final SingleLiveEvent<Boolean> getSistemIsClicked() {
        return this.sistemIsClicked;
    }

    public final void setSistemIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.sistemIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getBackupRestoreIsClicked() {
        return this.backupRestoreIsClicked;
    }

    public final void setBackupRestoreIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.backupRestoreIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getFaqIsClicked() {
        return this.faqIsClicked;
    }

    public final void setFaqIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.faqIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getLiveTrackingIsClicked() {
        return this.liveTrackingIsClicked;
    }

    public final void setLiveTrackingIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.liveTrackingIsClicked = singleLiveEvent;
    }

    public final String getEngineVer() {
        try {
            return new JSONObject(FileHelper.INSTANCE.readFile(Directory.INSTANCE.getENGINE_VERSION()).toString()).getString(ClientCookie.VERSION_ATTR);
        } catch (JSONException unused) {
            return "";
        }
    }

    public final String getSessionExpired() {
        long sessionLong = FasihApp.INSTANCE.getSession().getSessionLong(CommonCons.INSTANCE.getSESSION_EXPIRED(), 0L);
        return ((int) sessionLong) == 0 ? "" : "session expired : " + new SimpleDateFormat("dd/MM/yy hh:mm a").format((Date) new java.sql.Date(sessionLong));
    }

    public final void kebijakanPrivasiClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.kebijakanPrivasiIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void logoutClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.logoutIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void sistemClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.sistemIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void backupResoreClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.backupRestoreIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void faqClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.faqIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void liveTrackingClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.liveTrackingIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }
}
