package id.go.bpsfasih.ui.sistem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.databinding.ActivitySistemBinding;
import id.go.bpsfasih.ui.memoryInfo.MemoryInfoActivity;
import id.go.bpsfasih.ui.sistem.SistemActivity;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.SsoHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SistemActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/ui/sistem/SistemActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "binding", "Lid/go/bpsfasih/databinding/ActivitySistemBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivitySistemBinding;", "setBinding", "(Lid/go/bpsfasih/databinding/ActivitySistemBinding;)V", "viewModel", "Lid/go/bpsfasih/ui/sistem/SistemViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/sistem/SistemViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/sistem/SistemViewModel;)V", "initObservables", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SistemActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivitySistemBinding binding;
    public SistemViewModel viewModel;

    @Override // id.go.bpsfasih.BaseClassActivityNew
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public final ActivitySistemBinding getBinding() {
        ActivitySistemBinding activitySistemBinding = this.binding;
        if (activitySistemBinding != null) {
            return activitySistemBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivitySistemBinding activitySistemBinding) {
        Intrinsics.checkNotNullParameter(activitySistemBinding, "<set-?>");
        this.binding = activitySistemBinding;
    }

    public final SistemViewModel getViewModel() {
        SistemViewModel sistemViewModel = this.viewModel;
        if (sistemViewModel != null) {
            return sistemViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(SistemViewModel sistemViewModel) {
        Intrinsics.checkNotNullParameter(sistemViewModel, "<set-?>");
        this.viewModel = sistemViewModel;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistem);
        setViewModel((SistemViewModel) ViewModelProviders.of(this).get(SistemViewModel.class));
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_sistem);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.layout.activity_sistem)");
        setBinding((ActivitySistemBinding) contentView);
        getBinding().setViewModel(getViewModel());
        setStatusBarColor();
        BaseClassActivityNew.setAppBar$default(this, 0, "Sistem", null, null, null, 24, null);
        initObservables();
    }

    private final void initObservables() {
        SingleLiveEvent<Boolean> memoryInfo = getViewModel().getMemoryInfo();
        if (memoryInfo != null) {
            memoryInfo.observe(this, new SistemActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.sistem.SistemActivity.initObservables.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        SistemActivity.this.startActivity(new Intent(SistemActivity.this, (Class<?>) MemoryInfoActivity.class));
                    }
                }
            }));
        }
        SingleLiveEvent<Boolean> formGearVersionIsClicked = getViewModel().getFormGearVersionIsClicked();
        if (formGearVersionIsClicked != null) {
            formGearVersionIsClicked.observe(this, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.sistem.SistemActivity.initObservables.2
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        SistemActivity.this.showProgressBar();
                    }
                }
            });
        }
        SingleLiveEvent<Boolean> downloadIsDone = getViewModel().getDownloadIsDone();
        if (downloadIsDone != null) {
            downloadIsDone.observe(this, new AnonymousClass3());
        }
        SingleLiveEvent<Boolean> sessionLogout = getViewModel().getSessionLogout();
        if (sessionLogout != null) {
            sessionLogout.observe(this, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.sistem.SistemActivity.initObservables.4
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        SistemActivity.this.showProgressBar();
                        SsoHelper.Companion companion = SsoHelper.INSTANCE;
                        SistemActivity sistemActivity = SistemActivity.this;
                        final SistemActivity sistemActivity2 = SistemActivity.this;
                        companion.requestRefreshToken(sistemActivity, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.sistem.SistemActivity$initObservables$4$onChanged$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z2) {
                                sistemActivity2.hideProgressBar();
                                if (z2) {
                                    Toast.makeText(sistemActivity2, "Silahkan melakukan download engine", 1).show();
                                } else {
                                    sistemActivity2.showAlertDialogKodeVerifikasiLogout();
                                }
                            }
                        });
                    }
                }
            });
        }
        SingleLiveEvent<Boolean> changeLogAplikasiIsClicked = getViewModel().getChangeLogAplikasiIsClicked();
        if (changeLogAplikasiIsClicked != null) {
            changeLogAplikasiIsClicked.observe(this, new SistemActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.sistem.SistemActivity.initObservables.5
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        String urlRemoteConfig = FasihApp.INSTANCE.getSession().getUrlRemoteConfig(CommonCons.URL_REMOTE_CONFIG_CHANGELOG_APPLICATION);
                        String str = urlRemoteConfig;
                        if (str == null || str.length() == 0) {
                            Toast.makeText(SistemActivity.this, "Alamat URL tidak ditemukan", 1).show();
                            return;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(urlRemoteConfig));
                        SistemActivity.this.startActivity(intent);
                    }
                }
            }));
        }
        SingleLiveEvent<Boolean> changeLogFormGearIsClicked = getViewModel().getChangeLogFormGearIsClicked();
        if (changeLogFormGearIsClicked != null) {
            changeLogFormGearIsClicked.observe(this, new SistemActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.sistem.SistemActivity.initObservables.6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        String urlRemoteConfig = FasihApp.INSTANCE.getSession().getUrlRemoteConfig(CommonCons.URL_REMOTE_CONFIG_CHANGELOG_FORM_GEAR);
                        String str = urlRemoteConfig;
                        if (str == null || str.length() == 0) {
                            Toast.makeText(SistemActivity.this, "Alamat URL tidak ditemukan", 1).show();
                            return;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(urlRemoteConfig));
                        SistemActivity.this.startActivity(intent);
                    }
                }
            }));
        }
    }

    /* compiled from: SistemActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.sistem.SistemActivity$initObservables$3, reason: invalid class name */
    static final class AnonymousClass3 implements Observer<Boolean> {
        AnonymousClass3() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onChanged$lambda$0(View view) {
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
            onChanged(bool.booleanValue());
        }

        public final void onChanged(boolean z) {
            Integer numValueOf;
            String downloadFormGearMessage;
            Integer numValueOf2;
            Integer numValueOf3;
            Integer numValueOf4;
            String str;
            if (z) {
                Boolean downloadFormGearStatsus = SistemActivity.this.getViewModel().getDownloadFormGearStatsus();
                Intrinsics.checkNotNull(downloadFormGearStatsus);
                if (downloadFormGearStatsus.booleanValue()) {
                    numValueOf = Integer.valueOf(R.color.success30);
                    downloadFormGearMessage = SistemActivity.this.getViewModel().getDownloadFormGearMessage();
                    numValueOf2 = Integer.valueOf(R.color.success30);
                    numValueOf3 = Integer.valueOf(R.drawable.layout_button_success);
                    numValueOf4 = Integer.valueOf(R.color.success30);
                    str = "Update sukses";
                } else {
                    numValueOf = Integer.valueOf(R.color.error30);
                    downloadFormGearMessage = SistemActivity.this.getViewModel().getDownloadFormGearMessage();
                    numValueOf2 = Integer.valueOf(R.color.error30);
                    numValueOf3 = Integer.valueOf(R.drawable.layout_button_error);
                    numValueOf4 = Integer.valueOf(R.color.button_error);
                    str = "Update error";
                }
                Integer num = numValueOf3;
                Integer num2 = numValueOf4;
                Integer num3 = numValueOf2;
                Integer num4 = numValueOf;
                SistemActivity.this.hideProgressBar();
                SistemActivity sistemActivity = SistemActivity.this;
                sistemActivity.showAlertDialogColor(str, num4, downloadFormGearMessage, num3, null, "Tutup", num, new View.OnClickListener() { // from class: id.go.bpsfasih.ui.sistem.SistemActivity$initObservables$3$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SistemActivity.AnonymousClass3.onChanged$lambda$0(view);
                    }
                }, null, null, null, num2, false);
            }
        }
    }
}
