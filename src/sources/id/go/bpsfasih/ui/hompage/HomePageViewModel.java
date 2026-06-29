package id.go.bpsfasih.ui.hompage;

import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.utils.SingleLiveEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomePageViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/ui/hompage/HomePageViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "activity", "Lid/go/bpsfasih/ui/hompage/HomePageActivity;", "fragmentManager", "Landroid/app/FragmentManager;", "(Landroid/app/Application;Lid/go/bpsfasih/ui/hompage/HomePageActivity;Landroid/app/FragmentManager;)V", "getActivity", "()Lid/go/bpsfasih/ui/hompage/HomePageActivity;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "getFragmentManager", "()Landroid/app/FragmentManager;", "requesting", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getRequesting", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class HomePageViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final HomePageActivity activity;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final SingleLiveEvent<Boolean> requesting;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomePageViewModel(Application application, HomePageActivity activity, FragmentManager fragmentManager) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.context = FasihApp.INSTANCE.getContext();
        this.requesting = new SingleLiveEvent<>();
    }

    public final HomePageActivity getActivity() {
        return this.activity;
    }

    public final FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public final Context getContext() {
        return this.context;
    }

    public final SingleLiveEvent<Boolean> getRequesting() {
        return this.requesting;
    }
}
