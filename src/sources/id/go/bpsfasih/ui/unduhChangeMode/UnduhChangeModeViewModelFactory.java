package id.go.bpsfasih.ui.unduhChangeMode;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UnduhChangeModeViewModel.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "mApplication", "Landroid/app/Application;", "activity", "Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeActivity;", "periodeId", "", "(Landroid/app/Application;Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeActivity;Ljava/lang/String;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UnduhChangeModeViewModelFactory implements ViewModelProvider.Factory {
    public static final int $stable = 8;
    private final UnduhChangeModeActivity activity;
    private final Application mApplication;
    private final String periodeId;

    public UnduhChangeModeViewModelFactory(Application mApplication, UnduhChangeModeActivity activity, String periodeId) {
        Intrinsics.checkNotNullParameter(mApplication, "mApplication");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        this.mApplication = mApplication;
        this.activity = activity;
        this.periodeId = periodeId;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        return new UnduhChangeModeViewModel(this.mApplication, this.activity, this.periodeId);
    }
}
