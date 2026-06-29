package id.go.bpsfasih.utils.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import id.go.bpsfasih.BaseClassActivity;
import id.go.bpsfasih.BuildConfig;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.domain.models.AppConfigRemoteConfig;
import id.go.bpsfasih.domain.models.FeaturesRemoteConfig;
import id.go.bpsfasih.domain.models.UrlsRemoteConfig;
import id.go.bpsfasih.domain.models.WaitingTimeRemoteConfig;
import id.go.bpsfasih.remoteconfig.RemoteConfig;
import id.go.bpsfasih.remoteconfig.module.RemoteConfigModule;
import id.go.bpsfasih.utils.Session;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: RemoteConfigHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/utils/helper/RemoteConfigHelper;", "", "()V", "Companion", "LiveTrackingIntervalConfig", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RemoteConfigHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: RemoteConfigHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/utils/helper/RemoteConfigHelper$LiveTrackingIntervalConfig;", "", "saveDurationSeconds", "", "sendDurationSeconds", "(JJ)V", "getSaveDurationSeconds", "()J", "getSendDurationSeconds", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class LiveTrackingIntervalConfig {
        public static final int $stable = 0;
        private final long saveDurationSeconds;
        private final long sendDurationSeconds;

        public static /* synthetic */ LiveTrackingIntervalConfig copy$default(LiveTrackingIntervalConfig liveTrackingIntervalConfig, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = liveTrackingIntervalConfig.saveDurationSeconds;
            }
            if ((i & 2) != 0) {
                j2 = liveTrackingIntervalConfig.sendDurationSeconds;
            }
            return liveTrackingIntervalConfig.copy(j, j2);
        }

        /* renamed from: component1, reason: from getter */
        public final long getSaveDurationSeconds() {
            return this.saveDurationSeconds;
        }

        /* renamed from: component2, reason: from getter */
        public final long getSendDurationSeconds() {
            return this.sendDurationSeconds;
        }

        public final LiveTrackingIntervalConfig copy(long saveDurationSeconds, long sendDurationSeconds) {
            return new LiveTrackingIntervalConfig(saveDurationSeconds, sendDurationSeconds);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LiveTrackingIntervalConfig)) {
                return false;
            }
            LiveTrackingIntervalConfig liveTrackingIntervalConfig = (LiveTrackingIntervalConfig) other;
            return this.saveDurationSeconds == liveTrackingIntervalConfig.saveDurationSeconds && this.sendDurationSeconds == liveTrackingIntervalConfig.sendDurationSeconds;
        }

        public int hashCode() {
            return (Long.hashCode(this.saveDurationSeconds) * 31) + Long.hashCode(this.sendDurationSeconds);
        }

        public String toString() {
            return "LiveTrackingIntervalConfig(saveDurationSeconds=" + this.saveDurationSeconds + ", sendDurationSeconds=" + this.sendDurationSeconds + ")";
        }

        public LiveTrackingIntervalConfig(long j, long j2) {
            this.saveDurationSeconds = j;
            this.sendDurationSeconds = j2;
        }

        public final long getSaveDurationSeconds() {
            return this.saveDurationSeconds;
        }

        public final long getSendDurationSeconds() {
            return this.sendDurationSeconds;
        }
    }

    /* compiled from: RemoteConfigHelper.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000bJ\u0006\u0010\u0013\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0004¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/utils/helper/RemoteConfigHelper$Companion;", "", "()V", "getAppConfigRemoteConfig", "", "activity", "Landroid/app/Activity;", "isAlertShow", "", "getFeaturesRemoteConfigIsShow", "featureId", "", "getLiveTrackingIntervalConfig", "Lid/go/bpsfasih/utils/helper/RemoteConfigHelper$LiveTrackingIntervalConfig;", "defaultSaveSeconds", "", "defaultSendSeconds", "getWaitingTime", "name", "setImageCompressResolution", "setUrlsConfig", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void getAppConfigRemoteConfig(final Activity activity, boolean isAlertShow) {
            RemoteConfig remoteConfig;
            String string;
            Intrinsics.checkNotNullParameter(activity, "activity");
            if (!Network.INSTANCE.isOnline(activity) || (remoteConfig = RemoteConfigModule.INSTANCE.get()) == null || (string = remoteConfig.getString("app_config")) == null) {
                return;
            }
            Log.d("APP_CONFIG_REMOTE", string);
            AppConfigRemoteConfig appConfigRemoteConfig = (AppConfigRemoteConfig) new Gson().fromJson(string, AppConfigRemoteConfig.class);
            if (appConfigRemoteConfig != null) {
                Intrinsics.checkNotNullExpressionValue(appConfigRemoteConfig, "appConfigRemoteConfig");
                AppConfigRemoteConfig appConfigRemoteConfig2 = appConfigRemoteConfig;
                Object objFirst = null;
                for (AppConfigRemoteConfig.AppConfigRemoteConfigItem appConfigRemoteConfigItem : appConfigRemoteConfig2) {
                    if (CollectionsKt.contains(appConfigRemoteConfigItem.getUsers(), FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL()))) {
                        objFirst = appConfigRemoteConfigItem;
                    }
                }
                if (objFirst == null) {
                    ArrayList arrayList = new ArrayList();
                    for (AppConfigRemoteConfig.AppConfigRemoteConfigItem appConfigRemoteConfigItem2 : appConfigRemoteConfig2) {
                        if (appConfigRemoteConfigItem2.is_default()) {
                            arrayList.add(appConfigRemoteConfigItem2);
                        }
                    }
                    objFirst = CollectionsKt.first((List<? extends Object>) arrayList);
                }
                final AppConfigRemoteConfig.AppConfigRemoteConfigItem appConfigRemoteConfigItem3 = (AppConfigRemoteConfig.AppConfigRemoteConfigItem) objFirst;
                if (BuildConfig.VERSION_NAME.equals(appConfigRemoteConfigItem3 != null ? appConfigRemoteConfigItem3.getVersionName() : null)) {
                    if ("126".equals(appConfigRemoteConfigItem3 != null ? appConfigRemoteConfigItem3.getVersionCode() : null)) {
                        return;
                    }
                }
                if (isAlertShow) {
                    BaseClassActivity.showAlertDialog$default((BaseClassActivity) activity, "Update Aplikasi", appConfigRemoteConfigItem3 != null ? appConfigRemoteConfigItem3.getMessage() : null, null, "Unduh", new View.OnClickListener() { // from class: id.go.bpsfasih.utils.helper.RemoteConfigHelper$Companion$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            RemoteConfigHelper.Companion.getAppConfigRemoteConfig$lambda$5$lambda$4$lambda$3$lambda$2(appConfigRemoteConfigItem3, activity, view);
                        }
                    }, null, null, !Boolean.parseBoolean(appConfigRemoteConfigItem3 != null ? appConfigRemoteConfigItem3.is_force() : null), false, 256, null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void getAppConfigRemoteConfig$lambda$5$lambda$4$lambda$3$lambda$2(AppConfigRemoteConfig.AppConfigRemoteConfigItem appConfigRemoteConfigItem, Activity activity, View view) {
            Intrinsics.checkNotNullParameter(activity, "$activity");
            ((BaseClassActivity) activity).startActivity(new Intent("android.intent.action.VIEW", Uri.parse(appConfigRemoteConfigItem != null ? appConfigRemoteConfigItem.getLink_download() : null)));
        }

        public final boolean getFeaturesRemoteConfigIsShow(String featureId) {
            Intrinsics.checkNotNullParameter(featureId, "featureId");
            RemoteConfig remoteConfig = RemoteConfigModule.INSTANCE.get();
            if (remoteConfig != null) {
                try {
                    String string = remoteConfig.getString("features_config");
                    if (string != null) {
                        FeaturesRemoteConfig featuresConfig = (FeaturesRemoteConfig) new Gson().fromJson(string, FeaturesRemoteConfig.class);
                        Intrinsics.checkNotNullExpressionValue(featuresConfig, "featuresConfig");
                        for (FeaturesRemoteConfig.FeaturesRemoteConfigItem featuresRemoteConfigItem : featuresConfig) {
                            if (featuresRemoteConfigItem.getId().equals(featureId)) {
                                if (CollectionsKt.contains(featuresRemoteConfigItem.getUsers(), FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL()))) {
                                    return true;
                                }
                                return featuresRemoteConfigItem.is_active();
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
            return false;
        }

        public final void setUrlsConfig() {
            String string;
            try {
                RemoteConfig remoteConfig = RemoteConfigModule.INSTANCE.get();
                if (remoteConfig == null || (string = remoteConfig.getString("urls_config")) == null) {
                    return;
                }
                UrlsRemoteConfig urlsRemoteConfig = (UrlsRemoteConfig) new Gson().fromJson(string, UrlsRemoteConfig.class);
                Intrinsics.checkNotNullExpressionValue(urlsRemoteConfig, "urlsRemoteConfig");
                for (UrlsRemoteConfig.UrlRemoteConfigItem urlRemoteConfigItem : urlsRemoteConfig) {
                    Session session = FasihApp.INSTANCE.getSession();
                    Intrinsics.checkNotNull(urlRemoteConfigItem);
                    session.setUrlRemoteConfig(urlRemoteConfigItem.getId(), urlRemoteConfigItem.getUrl());
                }
            } catch (Exception unused) {
            }
        }

        public final void setImageCompressResolution() {
            String string;
            try {
                RemoteConfig remoteConfig = RemoteConfigModule.INSTANCE.get();
                if (remoteConfig == null || (string = remoteConfig.getString("image_compress_resolution")) == null) {
                    return;
                }
                FasihApp.INSTANCE.getSession().setImageCompressResolution(string);
                Log.d("IMAGE COMPRESS", string);
            } catch (Exception unused) {
                Log.d("IMAGE COMPRESS", "EXCEPTIO");
            }
        }

        public final long getWaitingTime(String name) {
            String string;
            Intrinsics.checkNotNullParameter(name, "name");
            long millis = TimeUnit.MINUTES.toMillis(10L);
            try {
                RemoteConfig remoteConfig = RemoteConfigModule.INSTANCE.get();
                if (remoteConfig != null && (string = remoteConfig.getString("waiting_time_config")) != null) {
                    WaitingTimeRemoteConfig listWaitingTime = (WaitingTimeRemoteConfig) new Gson().fromJson(string, WaitingTimeRemoteConfig.class);
                    Intrinsics.checkNotNullExpressionValue(listWaitingTime, "listWaitingTime");
                    for (WaitingTimeRemoteConfig.Item item : listWaitingTime) {
                        if (name.equals(item.getName())) {
                            String unit = item.getUnit();
                            switch (unit.hashCode()) {
                                case 99469071:
                                    if (unit.equals("hours")) {
                                        millis = TimeUnit.HOURS.toMillis(item.getTime());
                                        break;
                                    } else {
                                        millis = TimeUnit.MILLISECONDS.toMillis(item.getTime());
                                        break;
                                    }
                                case 891750470:
                                    if (unit.equals("miliseconds")) {
                                        millis = TimeUnit.MILLISECONDS.toMillis(item.getTime());
                                        break;
                                    } else {
                                        millis = TimeUnit.MILLISECONDS.toMillis(item.getTime());
                                        break;
                                    }
                                case 1064901855:
                                    if (unit.equals("minutes")) {
                                        millis = TimeUnit.MINUTES.toMillis(item.getTime());
                                        break;
                                    } else {
                                        millis = TimeUnit.MILLISECONDS.toMillis(item.getTime());
                                        break;
                                    }
                                case 1970096767:
                                    if (unit.equals("seconds")) {
                                        millis = TimeUnit.SECONDS.toMillis(item.getTime());
                                        break;
                                    } else {
                                        millis = TimeUnit.MILLISECONDS.toMillis(item.getTime());
                                        break;
                                    }
                                default:
                                    millis = TimeUnit.MILLISECONDS.toMillis(item.getTime());
                                    break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.d("WAITING TIME ERROR", String.valueOf(e.getMessage()));
            }
            return millis;
        }

        public final LiveTrackingIntervalConfig getLiveTrackingIntervalConfig(long defaultSaveSeconds, long defaultSendSeconds) {
            try {
                RemoteConfig remoteConfig = RemoteConfigModule.INSTANCE.get();
                String string = remoteConfig != null ? remoteConfig.getString(CommonCons.REMOTE_CONFIG_LIVE_TRACKING) : null;
                String str = string;
                if (!(str == null || StringsKt.isBlank(str))) {
                    JSONObject jSONObject = new JSONObject(string);
                    long jOptLong = jSONObject.optLong("save_interval", defaultSaveSeconds);
                    long jOptLong2 = jSONObject.optLong("send_interval", defaultSendSeconds);
                    if (jOptLong > 0) {
                        defaultSaveSeconds = jOptLong;
                    }
                    if (jOptLong2 > 0) {
                        defaultSendSeconds = jOptLong2;
                    }
                }
            } catch (Exception e) {
                Log.d("LIVE_TRACKING_CONFIG", "use default intervals: " + e.getMessage());
            }
            return new LiveTrackingIntervalConfig(defaultSaveSeconds, defaultSendSeconds);
        }
    }
}
