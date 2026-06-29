package id.go.bpsfasih.ui.syncAnswer;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.databinding.ActivitySyncAnswerPartialBinding;
import id.go.bpsfasih.domain.models.SyncAnswerPartial;
import id.go.bpsfasih.utils.SingleLiveEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncAnswerPartialActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u001eH\u0016J\u0012\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006$"}, d2 = {"Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "adapter", "Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialAdapter;", "binding", "Lid/go/bpsfasih/databinding/ActivitySyncAnswerPartialBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivitySyncAnswerPartialBinding;", "setBinding", "(Lid/go/bpsfasih/databinding/ActivitySyncAnswerPartialBinding;)V", "idPeriode", "", "getIdPeriode", "()Ljava/lang/String;", "setIdPeriode", "(Ljava/lang/String;)V", "listAssignment", "", "getListAssignment", "()Ljava/util/List;", "setListAssignment", "(Ljava/util/List;)V", "viewModel", "Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;)V", "initObservables", "", "initVew", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SyncAnswerPartialActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private SyncAnswerPartialAdapter adapter;
    public ActivitySyncAnswerPartialBinding binding;
    private String idPeriode;
    private List<String> listAssignment;
    public SyncAnswerPartialViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBackPressed$lambda$0(View view) {
    }

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

    public final ActivitySyncAnswerPartialBinding getBinding() {
        ActivitySyncAnswerPartialBinding activitySyncAnswerPartialBinding = this.binding;
        if (activitySyncAnswerPartialBinding != null) {
            return activitySyncAnswerPartialBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivitySyncAnswerPartialBinding activitySyncAnswerPartialBinding) {
        Intrinsics.checkNotNullParameter(activitySyncAnswerPartialBinding, "<set-?>");
        this.binding = activitySyncAnswerPartialBinding;
    }

    public final SyncAnswerPartialViewModel getViewModel() {
        SyncAnswerPartialViewModel syncAnswerPartialViewModel = this.viewModel;
        if (syncAnswerPartialViewModel != null) {
            return syncAnswerPartialViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(SyncAnswerPartialViewModel syncAnswerPartialViewModel) {
        Intrinsics.checkNotNullParameter(syncAnswerPartialViewModel, "<set-?>");
        this.viewModel = syncAnswerPartialViewModel;
    }

    public final List<String> getListAssignment() {
        return this.listAssignment;
    }

    public final void setListAssignment(List<String> list) {
        this.listAssignment = list;
    }

    public final String getIdPeriode() {
        return this.idPeriode;
    }

    public final void setIdPeriode(String str) {
        this.idPeriode = str;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_answer_partial);
        this.listAssignment = getIntent().getStringArrayListExtra(CommonCons.INSTANCE.getKEY_LIST_ID_ASSIGNMENT());
        this.idPeriode = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID());
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        setViewModel((SyncAnswerPartialViewModel) ViewModelProviders.of(this, new SyncAnswerPartialViewModelFactory(application, this.idPeriode, this.listAssignment)).get(SyncAnswerPartialViewModel.class));
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_sync_answer_partial);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.l…vity_sync_answer_partial)");
        setBinding((ActivitySyncAnswerPartialBinding) contentView);
        getBinding().setViewModel(getViewModel());
        setStatusBarColor();
        BaseClassActivityNew.setAppBar$default(this, 0, "Unduh Answer", null, null, null, 24, null);
        initVew();
        initObservables();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BaseClassActivityNew.showAlertDialog$default(this, "Peringatan", "Apakah anda yakin menutup halaman ini?", null, "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncAnswerPartialActivity.onBackPressed$lambda$0(view);
            }
        }, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncAnswerPartialActivity.onBackPressed$lambda$1(this.f$0, view);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBackPressed$lambda$1(SyncAnswerPartialActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        super.onBackPressed();
    }

    private final void initVew() {
        if (getViewModel().getJumlahAssignment() > 0) {
            ObservableField<Integer> visibilityButtonTutup = getViewModel().getVisibilityButtonTutup();
            if (visibilityButtonTutup != null) {
                visibilityButtonTutup.set(8);
            }
            ObservableField<Integer> visibilityButtonUnduh = getViewModel().getVisibilityButtonUnduh();
            if (visibilityButtonUnduh != null) {
                visibilityButtonUnduh.set(0);
            }
            getBinding().judul.setText("Anda perlu mengunduh data " + getViewModel().getJumlahAssignment() + " assignment");
            getBinding().keterangan.setText("Apakah ingin unduh sekarang? Pastikan perangkat anda terhubung dengan internet.");
            getBinding().icon.setImageResource(R.drawable.icon_answer_partial_1);
            return;
        }
        ObservableField<Integer> visibilityButtonTutup2 = getViewModel().getVisibilityButtonTutup();
        if (visibilityButtonTutup2 != null) {
            visibilityButtonTutup2.set(0);
        }
        ObservableField<Integer> visibilityButtonUnduh2 = getViewModel().getVisibilityButtonUnduh();
        if (visibilityButtonUnduh2 != null) {
            visibilityButtonUnduh2.set(8);
        }
        getBinding().judul.setText("Tidak ada assignment yang di unduh");
        getBinding().keterangan.setText("Hal ini terjadi karena assignment yang baru diunduh berstatus open semua atau kesalahan sinkronisasi");
        getBinding().icon.setImageResource(R.drawable.icon_answer_partial_2);
    }

    private final void initObservables() {
        SingleLiveEvent<Boolean> clickSelesai = getViewModel().getClickSelesai();
        if (clickSelesai != null) {
            clickSelesai.observe(this, new SyncAnswerPartialActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialActivity.initObservables.1
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
                        SyncAnswerPartialActivity.this.setResult(-1);
                        SyncAnswerPartialActivity.this.finish();
                    }
                }
            }));
        }
        MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap = getViewModel().getAssignmentMap();
        if (assignmentMap != null) {
            assignmentMap.observe(this, new SyncAnswerPartialActivity$sam$androidx_lifecycle_Observer$0(new Function1<HashMap<String, List<? extends SyncAnswerPartial>>, Unit>() { // from class: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialActivity.initObservables.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HashMap<String, List<? extends SyncAnswerPartial>> map) {
                    invoke2((HashMap<String, List<SyncAnswerPartial>>) map);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HashMap<String, List<SyncAnswerPartial>> map) {
                    SyncAnswerPartialActivity syncAnswerPartialActivity = SyncAnswerPartialActivity.this;
                    SyncAnswerPartialActivity syncAnswerPartialActivity2 = SyncAnswerPartialActivity.this;
                    syncAnswerPartialActivity.adapter = new SyncAnswerPartialAdapter(syncAnswerPartialActivity2, syncAnswerPartialActivity2.getViewModel());
                    SyncAnswerPartialActivity.this.getBinding().kontenRv.setLayoutManager(new LinearLayoutManager(SyncAnswerPartialActivity.this, 1, false));
                    SyncAnswerPartialActivity.this.getBinding().kontenRv.setAdapter(SyncAnswerPartialActivity.this.adapter);
                }
            }));
        }
    }
}
