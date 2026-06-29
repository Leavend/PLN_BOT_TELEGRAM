package id.go.bpsfasih.ui.daftarwilayah;

import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import kotlin.Metadata;

/* compiled from: DaftarWilayahActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n"}, d2 = {"<anonymous>", "", "result", "Landroidx/activity/result/ActivityResult;", "kotlin.jvm.PlatformType"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class DaftarWilayahActivity$resultIntentFromSyncAssignment$1 implements ActivityResultCallback<ActivityResult> {
    final /* synthetic */ DaftarWilayahActivity this$0;

    DaftarWilayahActivity$resultIntentFromSyncAssignment$1(DaftarWilayahActivity daftarWilayahActivity) {
        this.this$0 = daftarWilayahActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActivityResult$lambda$0(View view) {
    }

    @Override // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            FasihApp.INSTANCE.getSession().createSessionLong(CommonCons.TIMESTAMP_AVAIABLE_SYNC_ALL_ASS, System.currentTimeMillis() + RemoteConfigHelper.INSTANCE.getWaitingTime(CommonCons.REMOTE_CONFIG_WAITING_TIME_SYNC_ALL_ASSIGNMENT));
            int i = R.color.success30;
            int i2 = R.color.success30;
            this.this$0.showAlertDialogColor("Sukses", Integer.valueOf(i), "Sukses melakukan sync assignment", Integer.valueOf(i2), null, "Selesai", Integer.valueOf(R.drawable.layout_button_success), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$resultIntentFromSyncAssignment$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DaftarWilayahActivity$resultIntentFromSyncAssignment$1.onActivityResult$lambda$0(view);
                }
            }, null, null, null, Integer.valueOf(R.color.success30), true);
        }
    }
}
