package id.go.bpsfasih.ui.rekapWilayah;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import com.dewakoding.androiddatatable.DataTableView;
import com.dewakoding.androiddatatable.data.Column;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.databinding.ActivityRekapWilayahBinding;
import id.go.bpsfasih.domain.models.RekapWilayah;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RekapWilayahActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0002J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\u0014\u0010\u0016\u001a\u00020\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/ui/rekapWilayah/RekapWilayahActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "binding", "Lid/go/bpsfasih/databinding/ActivityRekapWilayahBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivityRekapWilayahBinding;", "binding$delegate", "Lkotlin/Lazy;", "columnName", "", "rekapWilayahViewModel", "Lid/go/bpsfasih/ui/rekapWilayah/RekapWilayahViewModel;", "loadData", "", "regionId", "periodeId", "columnIndex", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setDefaultAppBar", "setTable", "listRekap", "", "Lid/go/bpsfasih/domain/models/RekapWilayah;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RekapWilayahActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<ActivityRekapWilayahBinding>() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahActivity$binding$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityRekapWilayahBinding invoke() {
            return ActivityRekapWilayahBinding.inflate(this.this$0.getLayoutInflater());
        }
    });
    private String columnName;
    private RekapWilayahViewModel rekapWilayahViewModel;

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

    private final ActivityRekapWilayahBinding getBinding() {
        return (ActivityRekapWilayahBinding) this.binding.getValue();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBinding().getRoot());
        setDefaultAppBar();
        final String stringExtra = getIntent().getStringExtra("REGION_ID");
        final String stringExtra2 = getIntent().getStringExtra("PERIODE_ID");
        String stringExtra3 = getIntent().getStringExtra("TEMPLATE_ID");
        getBinding().tvWilayah.setText(getIntent().getStringExtra("WILAYAH"));
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "application");
        Intrinsics.checkNotNull(stringExtra2);
        Intrinsics.checkNotNull(stringExtra3);
        this.rekapWilayahViewModel = (RekapWilayahViewModel) new ViewModelProvider(this, new RekapWilayahViewModelFactory(application, this, stringExtra2, stringExtra3)).get(RekapWilayahViewModel.class);
        getBinding().btnChangeVariable.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RekapWilayahActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
        RekapWilayahViewModel rekapWilayahViewModel = this.rekapWilayahViewModel;
        if (rekapWilayahViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rekapWilayahViewModel");
            rekapWilayahViewModel = null;
        }
        RekapWilayahActivity rekapWilayahActivity = this;
        rekapWilayahViewModel.getSelectedColumnIndex().observe(rekapWilayahActivity, new RekapWilayahActivity$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahActivity.onCreate.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                RekapWilayahActivity rekapWilayahActivity2 = RekapWilayahActivity.this;
                String str = stringExtra;
                Intrinsics.checkNotNull(str);
                rekapWilayahActivity2.loadData(str, stringExtra2, String.valueOf(num));
            }
        }));
        RekapWilayahViewModel rekapWilayahViewModel2 = this.rekapWilayahViewModel;
        if (rekapWilayahViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rekapWilayahViewModel");
            rekapWilayahViewModel2 = null;
        }
        rekapWilayahViewModel2.getSelectedColumnName().observe(rekapWilayahActivity, new RekapWilayahActivity$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahActivity.onCreate.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String newColumnName) {
                RekapWilayahActivity rekapWilayahActivity2 = RekapWilayahActivity.this;
                Intrinsics.checkNotNullExpressionValue(newColumnName, "newColumnName");
                rekapWilayahActivity2.columnName = newColumnName;
            }
        }));
        Intrinsics.checkNotNull(stringExtra);
        loadData(stringExtra, stringExtra2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(RekapWilayahActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RekapWilayahViewModel rekapWilayahViewModel = this$0.rekapWilayahViewModel;
        if (rekapWilayahViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rekapWilayahViewModel");
            rekapWilayahViewModel = null;
        }
        rekapWilayahViewModel.customColumn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadData(String regionId, String periodeId, String columnIndex) {
        String str = columnIndex;
        if (!(str == null || str.length() == 0)) {
            getBinding().dtvTable.setVisibility(0);
            getBinding().tvEmpty.setVisibility(8);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RekapWilayahActivity$loadData$1$1(regionId, periodeId, columnIndex, this, null), 2, null);
            return;
        }
        getBinding().tvEmpty.setVisibility(0);
    }

    public final void setTable(List<RekapWilayah> listRekap) {
        Intrinsics.checkNotNullParameter(listRekap, "listRekap");
        getBinding().dtvTable.removeAllViews();
        ArrayList arrayList = new ArrayList();
        String str = this.columnName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("columnName");
            str = null;
        }
        arrayList.add(new Column("name", str));
        arrayList.add(new Column("total", "Total"));
        DataTableView dataTableView = getBinding().dtvTable;
        Intrinsics.checkNotNullExpressionValue(dataTableView, "binding.dtvTable");
        DataTableView.setTable$default(dataTableView, arrayList, listRekap, false, null, null, false, 28, null);
    }

    private final void setDefaultAppBar() {
        BaseClassActivityNew.setAppBar$default(this, 0, "Rekap Wilayah", null, null, null, 28, null);
    }
}
