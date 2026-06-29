package id.go.bpsfasih.ui.daftarwilayah;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateListingViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\fJ%\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0012H\u0016¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "mApplication", "Landroid/app/Application;", "activity", "Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity;", "vLId", "", "periodeIdPrimary", "", "periodeId", "templateId", "(Landroid/app/Application;Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Ljava/lang/Long;", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UpdateListingViewModelFactory implements ViewModelProvider.Factory {
    public static final int $stable = 8;
    private final DaftarWilayahActivity activity;
    private final Application mApplication;
    private final String periodeId;
    private final String periodeIdPrimary;
    private final String templateId;
    private final Long vLId;

    public UpdateListingViewModelFactory(Application mApplication, DaftarWilayahActivity activity, Long l, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(mApplication, "mApplication");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.mApplication = mApplication;
        this.activity = activity;
        this.vLId = l;
        this.periodeIdPrimary = str;
        this.periodeId = str2;
        this.templateId = str3;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        return new UpdateListingViewModel(this.mApplication, this.activity, this.vLId, this.periodeIdPrimary, this.periodeId, this.templateId);
    }
}
