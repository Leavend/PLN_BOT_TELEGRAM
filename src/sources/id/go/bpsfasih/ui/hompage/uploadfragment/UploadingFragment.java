package id.go.bpsfasih.ui.hompage.uploadfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.codekidlabs.storagechooser.utils.MemoryUtil;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import id.go.bpsfasih.databinding.FragmentUploadingBinding;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UploadingFragment.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingAdapter;", "viewModel", "Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingViewModel;", "observableInit", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", MemoryUtil.CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UploadingFragment extends Fragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private UploadingAdapter adapter;
    private UploadingViewModel viewModel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @JvmStatic
    public static final UploadingFragment newInstance() {
        return INSTANCE.newInstance();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.viewModel = (UploadingViewModel) ViewModelProviders.of(this).get(UploadingViewModel.class);
        FragmentUploadingBinding fragmentUploadingBinding = (FragmentUploadingBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_uploading, container, false);
        observableInit();
        return fragmentUploadingBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ((RecyclerView) _$_findCachedViewById(R.id.fragment_uploading_recycler)).setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new UploadingAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.fragment_uploading_recycler);
        UploadingAdapter uploadingAdapter = this.adapter;
        if (uploadingAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            uploadingAdapter = null;
        }
        recyclerView.setAdapter(uploadingAdapter);
    }

    private final void observableInit() {
        UploadingViewModel uploadingViewModel = this.viewModel;
        if (uploadingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            uploadingViewModel = null;
        }
        uploadingViewModel.getUploadingData().observe(getViewLifecycleOwner(), new Observer<List<? extends AssignmentUploadEntity>>() { // from class: id.go.bpsfasih.ui.hompage.uploadfragment.UploadingFragment.observableInit.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends AssignmentUploadEntity> list) {
                onChanged2((List<AssignmentUploadEntity>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<AssignmentUploadEntity> list) {
                if (list != null) {
                    UploadingFragment uploadingFragment = UploadingFragment.this;
                    if (list.size() == 0) {
                        ((TextView) uploadingFragment._$_findCachedViewById(R.id.tv_empty)).setVisibility(0);
                        ((RecyclerView) uploadingFragment._$_findCachedViewById(R.id.fragment_uploading_recycler)).setVisibility(8);
                        return;
                    }
                    ((TextView) uploadingFragment._$_findCachedViewById(R.id.tv_empty)).setVisibility(8);
                    ((RecyclerView) uploadingFragment._$_findCachedViewById(R.id.fragment_uploading_recycler)).setVisibility(0);
                    UploadingAdapter uploadingAdapter = uploadingFragment.adapter;
                    if (uploadingAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        uploadingAdapter = null;
                    }
                    uploadingAdapter.setUploading$app_release(list);
                }
            }
        });
    }

    /* compiled from: UploadingFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingFragment$Companion;", "", "()V", "newInstance", "Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingFragment;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final UploadingFragment newInstance() {
            UploadingFragment uploadingFragment = new UploadingFragment();
            uploadingFragment.setArguments(new Bundle());
            return uploadingFragment;
        }
    }
}
