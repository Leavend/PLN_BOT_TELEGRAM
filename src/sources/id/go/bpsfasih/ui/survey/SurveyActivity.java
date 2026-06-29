package id.go.bpsfasih.ui.survey;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.BaseClassActivity;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.databinding.ActivitySurveyBinding;
import id.go.bpsfasih.utils.SingleLiveEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SurveyActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0001%B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\u0012\u0010\u001a\u001a\u00020\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0017H\u0014J\b\u0010\u001e\u001a\u00020\u0017H\u0014J\"\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lid/go/bpsfasih/ui/survey/SurveyActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "Lid/go/bpsfasih/BaseClassActivity$OnPermissionResult;", "()V", "adapter", "Lid/go/bpsfasih/ui/survey/SurveyAdapter;", "binding", "Lid/go/bpsfasih/databinding/ActivitySurveyBinding;", "isCheckingDestruct", "", "mSurveyName", "", "pencacahType", "survey", "", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "surveyId", "surveyParentId", "surveyTemplateId", "surveyViewModel", "Lid/go/bpsfasih/ui/survey/SurveyViewModel;", "updateListingType", "initListener", "", "initObservables", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStop", "permissionResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SurveyActivity extends BaseClassActivityNew implements BaseClassActivity.OnPermissionResult {
    private static final String I_SURVEY_NAME = "surveyName";
    private SurveyAdapter adapter;
    private ActivitySurveyBinding binding;
    private boolean isCheckingDestruct;
    private boolean pencacahType;
    private String surveyId;
    private String surveyParentId;
    private SurveyViewModel surveyViewModel;
    private boolean updateListingType;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String mSurveyName = "";
    private String surveyTemplateId = "";
    private List<SurveyPojo> survey = CollectionsKt.toMutableList((Collection) CollectionsKt.emptyList());

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

    /* compiled from: SurveyActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ*\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/ui/survey/SurveyActivity$Companion;", "", "()V", "I_SURVEY_NAME", "", "startActivity", "", "context", "Landroid/content/Context;", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "updateListingType", "", "areaType", SurveyActivity.I_SURVEY_NAME, "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) SurveyActivity.class);
            intent.putExtra(SurveyActivity.I_SURVEY_NAME, "Surveys");
            intent.setFlags(268435456);
            context.startActivity(intent);
        }

        public static /* synthetic */ void startActivity$default(Companion companion, Context context, boolean z, boolean z2, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            if ((i & 4) != 0) {
                z2 = false;
            }
            companion.startActivity(context, z, z2, str);
        }

        public final void startActivity(Context context, boolean updateListingType, boolean areaType, String surveyName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(surveyName, "surveyName");
            Intent intent = new Intent(context, (Class<?>) SurveyActivity.class);
            intent.putExtra(CommonCons.SURVEY_TYPE_IS_UPDATE, updateListingType);
            intent.putExtra(CommonCons.SURVEY_TYPE_IS_AREA, areaType);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), "");
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_PARENT_ID(), "");
            intent.putExtra(SurveyActivity.I_SURVEY_NAME, surveyName);
            intent.setFlags(268435456);
            FasihApp.INSTANCE.getSession().createSessionBoolean(CommonCons.INSTANCE.getKEY_TOKEN_ENABLE(), false);
            context.startActivity(intent);
        }

        public final void startActivity(Context context, SurveyEntity survey) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(survey, "survey");
            Intent intent = new Intent(context, (Class<?>) SurveyActivity.class);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), survey.getIdPrimary());
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_PARENT_ID(), survey.getSurveyParentId());
            intent.putExtra(CommonCons.SURVEY_TYPE_IS_UPDATE, survey.getUpdateListingType());
            intent.putExtra(CommonCons.SURVEY_TYPE_IS_PENCACAH, survey.getPanelType());
            intent.putExtra(CommonCons.INSTANCE.getTEMPLATE_ID(), survey.getSurveyTemplateId());
            intent.putExtra(SurveyActivity.I_SURVEY_NAME, CommonCons.INSTANCE.getSURVEY_TITLE());
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        this.binding = (ActivitySurveyBinding) DataBindingUtil.setContentView(this, R.layout.activity_survey);
        this.isCheckingDestruct = true;
        String stringExtra = getIntent().getStringExtra(I_SURVEY_NAME);
        Intrinsics.checkNotNull(stringExtra);
        this.mSurveyName = stringExtra;
        this.surveyId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID());
        this.updateListingType = getIntent().getBooleanExtra(CommonCons.SURVEY_TYPE_IS_UPDATE, false);
        this.pencacahType = getIntent().getBooleanExtra(CommonCons.SURVEY_TYPE_IS_PENCACAH, false);
        this.surveyParentId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SURVEY_PARENT_ID());
        this.surveyTemplateId = getIntent().getStringExtra(CommonCons.INSTANCE.getTEMPLATE_ID());
        setStatusBarColor();
        BaseClassActivityNew.setAppBar$default(this, 0, this.mSurveyName, null, null, null, 24, null);
        showProgressBar();
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_survey)).startShimmerAnimation();
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        this.surveyViewModel = (SurveyViewModel) ViewModelProviders.of(this, new SurveyViewModelFactory(application, this.surveyId, this.updateListingType, this.pencacahType, this.surveyParentId)).get(SurveyViewModel.class);
        initView();
        initObservables();
        initListener();
    }

    private final void initView() {
        SurveyActivity surveyActivity = this;
        SurveyViewModel surveyViewModel = this.surveyViewModel;
        SurveyViewModel surveyViewModel2 = null;
        if (surveyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surveyViewModel");
            surveyViewModel = null;
        }
        this.adapter = new SurveyAdapter(surveyActivity, surveyViewModel);
        ActivitySurveyBinding activitySurveyBinding = this.binding;
        if (activitySurveyBinding != null) {
            SurveyViewModel surveyViewModel3 = this.surveyViewModel;
            if (surveyViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("surveyViewModel");
            } else {
                surveyViewModel2 = surveyViewModel3;
            }
            activitySurveyBinding.setViewModel(surveyViewModel2);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(surveyActivity);
        ((RecyclerView) _$_findCachedViewById(R.id.surveyRecycler)).setAdapter(this.adapter);
        ((RecyclerView) _$_findCachedViewById(R.id.surveyRecycler)).setLayoutManager(linearLayoutManager);
    }

    private final void initListener() {
        ((EditText) _$_findCachedViewById(R.id.et_search)).addTextChangedListener(new TextWatcher() { // from class: id.go.bpsfasih.ui.survey.SurveyActivity.initListener.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strValueOf = String.valueOf(s);
                SurveyViewModel surveyViewModel = SurveyActivity.this.surveyViewModel;
                if (surveyViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("surveyViewModel");
                    surveyViewModel = null;
                }
                surveyViewModel.search(strValueOf);
                ((ImageView) SurveyActivity.this._$_findCachedViewById(R.id.iv_clear_search)).setVisibility(strValueOf.length() == 0 ? 8 : 0);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_clear_search)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.survey.SurveyActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SurveyActivity.initListener$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(SurveyActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((EditText) this$0._$_findCachedViewById(R.id.et_search)).getText().clear();
        ((EditText) this$0._$_findCachedViewById(R.id.et_search)).clearFocus();
    }

    private final void initObservables() {
        SingleLiveEvent<Boolean> singleLiveEventIsChanged;
        SurveyViewModel surveyViewModel = this.surveyViewModel;
        SurveyViewModel surveyViewModel2 = null;
        if (surveyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surveyViewModel");
            surveyViewModel = null;
        }
        SurveyActivity surveyActivity = this;
        surveyViewModel.getRequesting().observe(surveyActivity, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.survey.SurveyActivity.initObservables.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                onChanged(bool.booleanValue());
            }

            public final void onChanged(boolean z) {
                if (z) {
                    SurveyActivity.this.showProgressBar();
                } else {
                    SurveyActivity.this.hideProgressBar();
                }
            }
        });
        SurveyViewModel surveyViewModel3 = this.surveyViewModel;
        if (surveyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surveyViewModel");
            surveyViewModel3 = null;
        }
        surveyViewModel3.getAllSurvey().observe(surveyActivity, new Observer<List<? extends SurveyPojo>>() { // from class: id.go.bpsfasih.ui.survey.SurveyActivity.initObservables.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends SurveyPojo> list) {
                onChanged2((List<SurveyPojo>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<SurveyPojo> list) {
                if (list != null) {
                    SurveyActivity surveyActivity2 = SurveyActivity.this;
                    if (!list.isEmpty()) {
                        ((ShimmerFrameLayout) surveyActivity2._$_findCachedViewById(R.id.shimmer_view_container_survey)).setVisibility(8);
                        ((ShimmerFrameLayout) surveyActivity2._$_findCachedViewById(R.id.shimmer_view_container_survey)).stopShimmerAnimation();
                        ((LinearLayout) surveyActivity2._$_findCachedViewById(R.id.empty_state_layout)).setVisibility(8);
                        ((RecyclerView) surveyActivity2._$_findCachedViewById(R.id.surveyRecycler)).setVisibility(0);
                        SurveyAdapter surveyAdapter = surveyActivity2.adapter;
                        Intrinsics.checkNotNull(surveyAdapter);
                        surveyAdapter.setSurveys$app_release(list);
                    } else {
                        ((ShimmerFrameLayout) surveyActivity2._$_findCachedViewById(R.id.shimmer_view_container_survey)).setVisibility(8);
                        ((ShimmerFrameLayout) surveyActivity2._$_findCachedViewById(R.id.shimmer_view_container_survey)).stopShimmerAnimation();
                        ((RecyclerView) surveyActivity2._$_findCachedViewById(R.id.surveyRecycler)).setVisibility(8);
                        ((LinearLayout) surveyActivity2._$_findCachedViewById(R.id.empty_state_layout)).setVisibility(0);
                        ((TextView) surveyActivity2._$_findCachedViewById(R.id.tv_empty_message)).setText(((EditText) surveyActivity2._$_findCachedViewById(R.id.et_search)).getText().toString().length() == 0 ? "Data survei tidak tersedia" : "Survei \"" + ((Object) ((EditText) surveyActivity2._$_findCachedViewById(R.id.et_search)).getText()) + "\" tidak ditemukan");
                    }
                }
                SurveyActivity.this.hideProgressBar();
            }
        });
        SurveyViewModel surveyViewModel4 = this.surveyViewModel;
        if (surveyViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surveyViewModel");
        } else {
            surveyViewModel2 = surveyViewModel4;
        }
        surveyViewModel2.getReceiveFile().observe(surveyActivity, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.survey.SurveyActivity.initObservables.3
            public final void onChanged(boolean z) {
            }

            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                onChanged(bool.booleanValue());
            }
        });
        SurveyAdapter surveyAdapter = this.adapter;
        if (surveyAdapter == null || (singleLiveEventIsChanged = surveyAdapter.isChanged()) == null) {
            return;
        }
        singleLiveEventIsChanged.observe(surveyActivity, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.survey.SurveyActivity.initObservables.4
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                onChanged(bool.booleanValue());
            }

            public final void onChanged(boolean z) {
                ArrayList arrayList;
                List<SurveyPojo> survey;
                SurveyAdapter surveyAdapter2 = SurveyActivity.this.adapter;
                if (surveyAdapter2 == null || (survey = surveyAdapter2.getSurvey()) == null) {
                    arrayList = null;
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : survey) {
                        if (((SurveyPojo) obj).getSurvey().isSelected()) {
                            arrayList2.add(obj);
                        }
                    }
                    arrayList = arrayList2;
                }
                if ((arrayList != null ? arrayList.size() : 0) <= 0) {
                    SurveyAdapter surveyAdapter3 = SurveyActivity.this.adapter;
                    if (surveyAdapter3 != null) {
                        surveyAdapter3.hideCheckbox$app_release();
                        return;
                    }
                    return;
                }
                SurveyActivity surveyActivity2 = SurveyActivity.this;
                Intrinsics.checkNotNull(arrayList);
                surveyActivity2.survey = CollectionsKt.toMutableList((Collection) arrayList);
            }
        });
    }

    @Override // id.go.bpsfasih.BaseClassActivity.OnPermissionResult
    public void permissionResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "Bluetooth tidak aktif", 0).show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_survey)).stopShimmerAnimation();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_survey)).startShimmerAnimation();
    }
}
