package id.go.bpsfasih.data.localserver;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.apache.http.HttpVersion;

/* compiled from: Config.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/data/localserver/Config;", "", "()V", Config.BASE_URL, "", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Config {
    public static final int $stable = 0;
    public static final String ANALYTICS = "/mobile/analytic/api/v1/recap?year=2025";
    public static final String APPROVE_REJECT_PATH = "/mobile/assignment-approval/api/v2/";
    public static final String ASSIGNMENT_GENERAL_PATH_URL = "/mobile/assignment-general/api/mobile";
    public static final String ASSIGNMENT_SYNC = "/mobile/assignment-sync/api/mobile";
    public static final String ASSIGNMENT_SYNC_PATH_URL = "/mobile/assignment-sync/api";
    public static final String ASSINGMENT_SUBMIT_PATH_URL = "/mobile/assignment-submit-2/api";
    public static final String BASE_URL = "BASE_URL";
    public static final String BASE_URL_DEFAULT = "fasih-survey.bps.go.id";
    public static final String BASE_URL_DEFAULT_DEVELOPMENT = "fasih-survey-dev.bps.go.id";
    public static final String BASE_URL_SSO = "https://sso.bps.go.id";
    public static final String CLIENT_ID_SSO_EKSTERNAL = "03310-icscapi-k09";
    public static final String CLIENT_ID_SSO_INTERNAL = "03310-fasih-c0m";
    public static final String CONNECTOR_PATH_URL = "/mobile/connector/api/hit/";
    public static final String DEEPLINK_SSO_EKSTERNAL = "id.go.bps://fasih-sso-eksternal";
    public static final String DEEPLINK_SSO_INTERNAL = "id.go.bps://fasih-sso-internal";
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String LOCATION_TRACKING_PATH = "/location-tracking/api/v1/location/points";
    public static final String LOOKUP_PATH = "/mobile/lookup/api/";
    public static final String NOTIFICATION_PATH_URL = "/mobile/notification-service/api/mobile/";
    public static final String REGISTER_DEVICE_PATH = "/mobile/registration/api/mobile/";
    public static final String SAMPLING_PATH_URL = "mobile/sampling/api";
    public static final String SSO_EKSTERNAL_PATH_URL = "/auth/realms/eksternal/protocol/openid-connect";
    public static final String SSO_INTERNAL_PATH_URL = "/auth/realms/pegawai-bps/protocol/openid-connect";
    public static final String SURVEY_PATH = "/mobile/assignment-sync/api/mobile/";
    public static final String SURVEY_PATH_URL = "/mobile/survey/api/v1/mobile";
    public static final String TICKET_PATH_URL = "ticket/api";
    public static final String UPLOAD_PATH_2 = "/mobile/assignment-submit-2/api/";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isSecure = true;

    public final String BASE_URL() {
        String url = FasihApp.INSTANCE.getSession().getUrl();
        String str = url;
        if (str == null || str.length() == 0) {
            url = BASE_URL_DEFAULT;
        }
        return INSTANCE.protocol() + url;
    }

    /* compiled from: Config.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010%\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\"\"\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lid/go/bpsfasih/data/localserver/Config$Companion;", "", "()V", "ANALYTICS", "", "APPROVE_REJECT_PATH", "ASSIGNMENT_GENERAL_PATH_URL", "ASSIGNMENT_SYNC", "ASSIGNMENT_SYNC_PATH_URL", "ASSINGMENT_SUBMIT_PATH_URL", Config.BASE_URL, "BASE_URL_DEFAULT", "BASE_URL_DEFAULT_DEVELOPMENT", "BASE_URL_SSO", "CLIENT_ID_SSO_EKSTERNAL", "CLIENT_ID_SSO_INTERNAL", "CONNECTOR_PATH_URL", "DEEPLINK_SSO_EKSTERNAL", "DEEPLINK_SSO_INTERNAL", HttpVersion.HTTP, "HTTPS", "LOCATION_TRACKING_PATH", "LOOKUP_PATH", "NOTIFICATION_PATH_URL", "REGISTER_DEVICE_PATH", "SAMPLING_PATH_URL", "SSO_EKSTERNAL_PATH_URL", "SSO_INTERNAL_PATH_URL", "SURVEY_PATH", "SURVEY_PATH_URL", "TICKET_PATH_URL", "UPLOAD_PATH_2", "isSecure", "", "()Z", "setSecure", "(Z)V", "protocol", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isSecure() {
            return Config.isSecure;
        }

        public final void setSecure(boolean z) {
            Config.isSecure = z;
        }

        public final String protocol() {
            return isSecure() ? Config.HTTPS : Config.HTTP;
        }
    }
}
