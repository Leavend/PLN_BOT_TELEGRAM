package id.go.bpsfasih.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.repository.DeviceRepositoryImpl;
import id.go.bpsfasih.utils.helper.FcmHelper;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: Session.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010&\u001a\u00020'J\u0016\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u001dJ\u0016\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u000fJ\u001e\u0010.\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00062\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010/J\u0016\u00100\u001a\u00020'2\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u000201J\u0018\u00102\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00062\b\u0010-\u001a\u0004\u0018\u00010\u0006J\r\u00103\u001a\u0004\u0018\u000101¢\u0006\u0002\u00104J\b\u00105\u001a\u0004\u0018\u00010\u0006J\b\u00106\u001a\u0004\u0018\u00010\u0006J\u0006\u00107\u001a\u00020\u000fJ\u0006\u00108\u001a\u00020\u000fJ\u000e\u00109\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u0006J\u0016\u00109\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u001dJ\u0016\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u000fJ&\u0010>\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010/2\u0006\u0010)\u001a\u00020\u00062\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010/J\u0016\u0010@\u001a\u0002012\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u000201J\u0010\u0010A\u001a\u0004\u0018\u00010\u00062\u0006\u0010)\u001a\u00020\u0006J\u0018\u0010A\u001a\u0004\u0018\u00010\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u0006J\u0006\u0010B\u001a\u00020\u000fJ\b\u0010C\u001a\u0004\u0018\u00010\u0006J\b\u0010D\u001a\u0004\u0018\u00010\u0006J\u0010\u0010E\u001a\u0004\u0018\u00010\u00062\u0006\u0010F\u001a\u00020\u0006J\u0006\u0010G\u001a\u00020\u0006J)\u0010H\u001a\u00020'2!\u0010I\u001a\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\bK\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020'0JJ\u000e\u0010L\u001a\u00020'2\u0006\u0010)\u001a\u00020\u0006J\u000e\u0010M\u001a\u00020'2\u0006\u0010N\u001a\u00020\u0006J\u0006\u0010O\u001a\u00020'J\u000e\u0010P\u001a\u00020'2\u0006\u0010Q\u001a\u00020\u0006J\u0006\u0010R\u001a\u00020'J\u0006\u0010S\u001a\u00020'J\u000e\u0010T\u001a\u00020'2\u0006\u0010U\u001a\u00020\u0006J\u000e\u0010V\u001a\u00020'2\u0006\u0010U\u001a\u00020\u0006J\u0016\u0010W\u001a\u00020'2\u0006\u0010X\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001eR\u001a\u0010 \u001a\u00020!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006Y"}, d2 = {"Lid/go/bpsfasih/utils/Session;", "", "_context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "BANNER_OFF", "", "FORM_GEAR_URL", "IMAGE_COMPRESSION", "MODE", "ONBOARD_OFF", "PENGUMUMAN_ACTIVE", "PENGUMUMAN_UNREAD", "PREF_NAME", "PRIVATE_MODE", "", "getPRIVATE_MODE$app_release", "()I", "setPRIVATE_MODE$app_release", "(I)V", "TRACK_USER", "URL", "editor", "Landroid/content/SharedPreferences$Editor;", "getEditor$app_release", "()Landroid/content/SharedPreferences$Editor;", "setEditor$app_release", "(Landroid/content/SharedPreferences$Editor;)V", "is_login", "", "()Z", "is_offline", "preferences", "Landroid/content/SharedPreferences;", "getPreferences$app_release", "()Landroid/content/SharedPreferences;", "setPreferences$app_release", "(Landroid/content/SharedPreferences;)V", "clearSession", "", "createSessionBoolean", "name", NotificationCompat.CATEGORY_STATUS, "createSessionInt", DatabaseFileArchive.COLUMN_KEY, "value", "createSessionList", "", "createSessionLong", "", "createSessionString", "getFlagId", "()Ljava/lang/Long;", "getImageCompressResolution", "getMode", "getOnboardOff", "getRoleId", "getSessionBoolean", "is", "getSessionInt", "arg0", "arg1", "getSessionList", RemoteConfigComponent.DEFAULTS_FILE_NAME, "getSessionLong", "getSessionString", "getTrackUser", "getUrl", "getUrlFormGear", "getUrlRemoteConfig", "idUrl", "getUserId", "logout", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "removeSession", "setImageCompressResolution", "str", "setLoggedIn", "setMode", "mode", "setOnboardOff", "setTrackUserNonactive", "setUrl", "url", "setUrlFormGear", "setUrlRemoteConfig", DownloadModel.ID, "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Session {
    public static final int $stable = 8;
    private final String BANNER_OFF;
    private final String FORM_GEAR_URL;
    private final String IMAGE_COMPRESSION;
    private final String MODE;
    private final String ONBOARD_OFF;
    private final String PENGUMUMAN_ACTIVE;
    private final String PENGUMUMAN_UNREAD;
    private final String PREF_NAME;
    private int PRIVATE_MODE;
    private final String TRACK_USER;
    private final String URL;
    private final Context _context;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    public Session(Context _context) {
        Intrinsics.checkNotNullParameter(_context, "_context");
        this._context = _context;
        this.PREF_NAME = "sangkuriang";
        this.BANNER_OFF = "is_banner_off";
        this.ONBOARD_OFF = "is_onboard_off";
        this.PENGUMUMAN_ACTIVE = "pengumuman_active";
        this.PENGUMUMAN_UNREAD = "pengumuman_unread";
        this.TRACK_USER = "track_user";
        this.URL = "url";
        this.MODE = "mode";
        this.FORM_GEAR_URL = "formgear_url";
        this.IMAGE_COMPRESSION = "image_compress_resolution";
        SharedPreferences sharedPreferences = _context.getSharedPreferences("sangkuriang", this.PRIVATE_MODE);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "_context.getSharedPrefer…(PREF_NAME, PRIVATE_MODE)");
        this.preferences = sharedPreferences;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editorEdit, "preferences.edit()");
        this.editor = editorEdit;
    }

    /* renamed from: getPreferences$app_release, reason: from getter */
    public final SharedPreferences getPreferences() {
        return this.preferences;
    }

    public final void setPreferences$app_release(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.preferences = sharedPreferences;
    }

    /* renamed from: getEditor$app_release, reason: from getter */
    public final SharedPreferences.Editor getEditor() {
        return this.editor;
    }

    public final void setEditor$app_release(SharedPreferences.Editor editor) {
        Intrinsics.checkNotNullParameter(editor, "<set-?>");
        this.editor = editor;
    }

    /* renamed from: getPRIVATE_MODE$app_release, reason: from getter */
    public final int getPRIVATE_MODE() {
        return this.PRIVATE_MODE;
    }

    public final void setPRIVATE_MODE$app_release(int i) {
        this.PRIVATE_MODE = i;
    }

    public final boolean is_login() {
        return this.preferences.getBoolean("is_login", false);
    }

    public final boolean is_offline() {
        return this.preferences.getBoolean("is_offline", false);
    }

    public final void createSessionBoolean(String name, boolean status) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.editor.putBoolean(name, status);
        this.editor.commit();
    }

    public final void createSessionInt(String key, int value) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.editor.putInt(key, value);
        this.editor.commit();
    }

    public final void createSessionLong(String key, long value) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.editor.putLong(key, value);
        this.editor.commit();
    }

    public final void createSessionString(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.editor.putString(name, value);
        this.editor.commit();
    }

    public final void createSessionList(String name, Set<String> value) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.editor.putStringSet(name, value);
        this.editor.commit();
    }

    public final int getSessionInt(String arg0, int arg1) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        return this.preferences.getInt(arg0, arg1);
    }

    public final long getSessionLong(String arg0, long arg1) {
        Intrinsics.checkNotNullParameter(arg0, "arg0");
        return this.preferences.getLong(arg0, arg1);
    }

    public final String getSessionString(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.preferences.getString(name, null);
    }

    public final String getSessionString(String name, String defaults) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(defaults, "defaults");
        return this.preferences.getString(name, defaults);
    }

    public final Set<String> getSessionList(String name, Set<String> defaults) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.preferences.getStringSet(name, defaults);
    }

    public final boolean getSessionBoolean(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.preferences.getBoolean(name, false);
    }

    public final boolean getSessionBoolean(String name, boolean is) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.preferences.getBoolean(name, is);
    }

    public final void clearSession() {
        this.editor.clear();
        this.editor.commit();
    }

    public final void removeSession(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.editor.remove(name);
        this.editor.commit();
    }

    public final String getUserId() {
        String sessionString = getSessionString(CommonCons.INSTANCE.getSESSION_ID(), "");
        Intrinsics.checkNotNull(sessionString);
        return sessionString;
    }

    public final Long getFlagId() {
        return Long.valueOf(getSessionLong(CommonCons.INSTANCE.getFLAG(), 0L));
    }

    public final int getRoleId() {
        return getSessionInt(CommonCons.INSTANCE.getSESSION_ROLE_ID(), 0);
    }

    public final void logout(final Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        final String userId = FasihApp.INSTANCE.getSession().getUserId();
        final String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        FcmHelper.INSTANCE.getToken(new Function1<String, Unit>() { // from class: id.go.bpsfasih.utils.Session.logout.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                if (str != null) {
                    new DeviceRepositoryImpl().unregisterDevice(userId, String.valueOf(sessionString), str, new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.utils.Session.logout.1.1
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(BaseResponse baseResponse) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(BaseResponse baseResponse) {
                            invoke2(baseResponse);
                            return Unit.INSTANCE;
                        }
                    });
                }
                Timer timer = new Timer();
                final Session session = this;
                final String str2 = sessionString;
                final Function1<Boolean, Unit> function1 = callback;
                timer.schedule(new TimerTask() { // from class: id.go.bpsfasih.utils.Session$logout$1$invoke$$inlined$timerTask$1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        session.getEditor().clear();
                        session.getEditor().commit();
                        FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID(), str2);
                        function1.invoke(true);
                    }
                }, 2000L);
            }
        });
    }

    public final void setLoggedIn() {
        createSessionBoolean("is_login", true);
    }

    public final void setOnboardOff() {
        this.editor.putInt(this.ONBOARD_OFF, 1);
        this.editor.commit();
    }

    public final int getOnboardOff() {
        return this.preferences.getInt(this.ONBOARD_OFF, -1);
    }

    public final int getTrackUser() {
        return this.preferences.getInt(this.TRACK_USER, 0);
    }

    public final void setTrackUserNonactive() {
        this.editor.putInt(this.TRACK_USER, -1);
        this.editor.commit();
    }

    public final void setUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.editor.putString(this.URL, url);
        this.editor.commit();
    }

    public final String getUrl() {
        return getSessionString(this.URL);
    }

    public final void setMode(String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.editor.putString(this.MODE, mode);
        this.editor.commit();
    }

    public final String getMode() {
        return getSessionString(this.MODE);
    }

    public final void setUrlFormGear(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.editor.putString(this.FORM_GEAR_URL, url);
        this.editor.commit();
    }

    public final String getUrlFormGear() {
        return getSessionString(this.FORM_GEAR_URL);
    }

    public final void setUrlRemoteConfig(String id2, String url) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(url, "url");
        this.editor.putString(id2, url);
        this.editor.commit();
    }

    public final String getUrlRemoteConfig(String idUrl) {
        Intrinsics.checkNotNullParameter(idUrl, "idUrl");
        return getSessionString(idUrl);
    }

    public final void setImageCompressResolution(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        this.editor.putString(this.IMAGE_COMPRESSION, str);
        this.editor.commit();
    }

    public final String getImageCompressResolution() {
        return getSessionString(this.IMAGE_COMPRESSION);
    }
}
