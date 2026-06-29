package id.go.bpsfasih.ui.uploadactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import id.go.bpsfasih.databinding.ActivityUploadBinding;
import id.go.bpsfasih.ui.hompage.uploadfragment.UploadingViewModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UploadActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/ui/uploadactivity/UploadActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "adapter", "Lid/go/bpsfasih/ui/uploadactivity/UploadingActivityAdapter;", "binding", "Lid/go/bpsfasih/databinding/ActivityUploadBinding;", "viewModel", "Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingViewModel;", "observableInit", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UploadActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private UploadingActivityAdapter adapter;
    private ActivityUploadBinding binding;
    private UploadingViewModel viewModel;

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

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (UploadingViewModel) ViewModelProviders.of(this).get(UploadingViewModel.class);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_upload);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.layout.activity_upload)");
        this.binding = (ActivityUploadBinding) contentView;
        BaseClassActivityNew.setAppBar$default(this, 0, "Daftar Upload", null, null, null, 24, null);
        observableInit();
    }

    private final void observableInit() {
        this.adapter = new UploadingActivityAdapter(getApplicationContext(), this);
        ((RecyclerView) _$_findCachedViewById(R.id.rv_upload)).setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rv_upload);
        UploadingActivityAdapter uploadingActivityAdapter = this.adapter;
        UploadingViewModel uploadingViewModel = null;
        if (uploadingActivityAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            uploadingActivityAdapter = null;
        }
        recyclerView.setAdapter(uploadingActivityAdapter);
        UploadingViewModel uploadingViewModel2 = this.viewModel;
        if (uploadingViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            uploadingViewModel = uploadingViewModel2;
        }
        uploadingViewModel.getUploadingData().observe(this, new Observer<List<? extends AssignmentUploadEntity>>() { // from class: id.go.bpsfasih.ui.uploadactivity.UploadActivity.observableInit.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends AssignmentUploadEntity> list) {
                onChanged2((List<AssignmentUploadEntity>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<AssignmentUploadEntity> list) {
                if (list != null) {
                    UploadActivity uploadActivity = UploadActivity.this;
                    if (list.size() == 0) {
                        ((LinearLayout) uploadActivity._$_findCachedViewById(R.id.ll_empty)).setVisibility(0);
                        ((RecyclerView) uploadActivity._$_findCachedViewById(R.id.rv_upload)).setVisibility(8);
                        return;
                    }
                    ((LinearLayout) uploadActivity._$_findCachedViewById(R.id.ll_empty)).setVisibility(8);
                    ((RecyclerView) uploadActivity._$_findCachedViewById(R.id.rv_upload)).setVisibility(0);
                    UploadingActivityAdapter uploadingActivityAdapter2 = uploadActivity.adapter;
                    if (uploadingActivityAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        uploadingActivityAdapter2 = null;
                    }
                    uploadingActivityAdapter2.setUploading$app_release(list);
                }
            }
        });
    }
}
