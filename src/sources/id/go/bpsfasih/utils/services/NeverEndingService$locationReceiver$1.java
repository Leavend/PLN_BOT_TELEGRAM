package id.go.bpsfasih.utils.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NeverEndingService.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"id/go/bpsfasih/utils/services/NeverEndingService$locationReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "mContext", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NeverEndingService$locationReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ NeverEndingService this$0;

    NeverEndingService$locationReceiver$1(NeverEndingService neverEndingService) {
        this.this$0 = neverEndingService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context mContext, Intent intent) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(intent, "intent");
        double doubleExtra = intent.getDoubleExtra("Latitude", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        double doubleExtra2 = intent.getDoubleExtra("Longitude", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        Log.d("Lokasi Terbaru", doubleExtra + ", " + doubleExtra2 + ", " + intent.getFloatExtra("Accuracy", 0.0f) + ", " + intent.getFloatExtra("Distance", 0.0f));
        this.this$0.latLng = new LatLng(doubleExtra, doubleExtra2);
        final NeverEndingService neverEndingService = this.this$0;
        new Thread(new Runnable() { // from class: id.go.bpsfasih.utils.services.NeverEndingService$locationReceiver$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NeverEndingService$locationReceiver$1.onReceive$lambda$1(neverEndingService);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive$lambda$1(NeverEndingService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<SurveyEntity> listM6642getSurveys = DataSurvey.Survey.INSTANCE.getSurveyRepo().m6642getSurveys();
        List<SurveyEntity> list = listM6642getSurveys;
        boolean z = false;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<T> it = listM6642getSurveys.iterator();
        while (it.hasNext()) {
            if (((SurveyEntity) it.next()).getGeoLiveTracking()) {
                z = true;
            }
        }
        if (z) {
            this$0.updateToServer();
            LatLng latLng = this$0.latLng;
            if (latLng == null) {
                Intrinsics.throwUninitializedPropertyAccessException("latLng");
                latLng = null;
            }
            Log.d("Location", latLng.toString());
        }
    }
}
