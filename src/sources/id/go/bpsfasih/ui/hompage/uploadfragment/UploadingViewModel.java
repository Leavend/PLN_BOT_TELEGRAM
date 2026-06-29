package id.go.bpsfasih.ui.hompage.uploadfragment;

import android.app.Application;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UploadingViewModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "uploadingData", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/entities/AssignmentUploadEntity;", "getUploadingData", "()Landroidx/lifecycle/LiveData;", "setUploadingData", "(Landroidx/lifecycle/LiveData;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UploadingViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final Context context;
    private LiveData<List<AssignmentUploadEntity>> uploadingData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadingViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.context = FasihApp.INSTANCE.getContext();
        this.uploadingData = DataSurvey.Upload.INSTANCE.getUploadRepository().getListUploadByUser();
    }

    public final Context getContext() {
        return this.context;
    }

    public final LiveData<List<AssignmentUploadEntity>> getUploadingData() {
        return this.uploadingData;
    }

    public final void setUploadingData(LiveData<List<AssignmentUploadEntity>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.uploadingData = liveData;
    }
}
