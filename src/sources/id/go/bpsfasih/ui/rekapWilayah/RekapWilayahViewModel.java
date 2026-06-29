package id.go.bpsfasih.ui.rekapWilayah;

import android.app.Activity;
import android.app.Application;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.entities.Data;
import id.go.bpsfasih.data.local.pojo.AssignmentWilayahPojo;
import id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository;
import id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RekapWilayahViewModel.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\u001d\u001a\u00020\u001eR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lid/go/bpsfasih/ui/rekapWilayah/RekapWilayahViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "activityNow", "Landroid/app/Activity;", "periodeId", "", "templateId", "(Landroid/app/Application;Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V", "_selectedColumnIndex", "Landroidx/lifecycle/MutableLiveData;", "", "_selectedColumnName", "assignmentUpdateListing", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/pojo/AssignmentWilayahPojo;", "getAssignmentUpdateListing", "()Landroidx/lifecycle/LiveData;", "setAssignmentUpdateListing", "(Landroidx/lifecycle/LiveData;)V", "selectedColumnIndex", "getSelectedColumnIndex", "selectedColumnName", "getSelectedColumnName", "templateIdGlobal", "getTemplateIdGlobal", "()Ljava/lang/String;", "customColumn", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RekapWilayahViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final MutableLiveData<Integer> _selectedColumnIndex;
    private final MutableLiveData<String> _selectedColumnName;
    private final Activity activityNow;
    private LiveData<List<AssignmentWilayahPojo>> assignmentUpdateListing;
    private final String periodeId;
    private final LiveData<Integer> selectedColumnIndex;
    private final LiveData<String> selectedColumnName;
    private final String templateId;
    private final String templateIdGlobal;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RekapWilayahViewModel(Application application, Activity activityNow, String periodeId, String templateId) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(activityNow, "activityNow");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        this.activityNow = activityNow;
        this.periodeId = periodeId;
        this.templateId = templateId;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        this._selectedColumnIndex = mutableLiveData;
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>();
        this._selectedColumnName = mutableLiveData2;
        this.selectedColumnIndex = mutableLiveData;
        this.selectedColumnName = mutableLiveData2;
        this.templateIdGlobal = templateId;
        DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentWilayahPojoByPeriode(periodeId);
        this.assignmentUpdateListing = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentWilayahPojo();
    }

    public final LiveData<Integer> getSelectedColumnIndex() {
        return this.selectedColumnIndex;
    }

    public final LiveData<String> getSelectedColumnName() {
        return this.selectedColumnName;
    }

    public final LiveData<List<AssignmentWilayahPojo>> getAssignmentUpdateListing() {
        return this.assignmentUpdateListing;
    }

    public final void setAssignmentUpdateListing(LiveData<List<AssignmentWilayahPojo>> liveData) {
        this.assignmentUpdateListing = liveData;
    }

    public final String getTemplateIdGlobal() {
        return this.templateIdGlobal;
    }

    public final void customColumn() {
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("custom_column_assignment")) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
        } else {
            this.activityNow.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RekapWilayahViewModel.customColumn$lambda$0(this.f$0);
                }
            });
        }
    }

    /* compiled from: RekapWilayahViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$customColumn$1", f = "RekapWilayahViewModel.kt", i = {0}, l = {55}, m = "invokeSuspend", n = {"listColumn"}, s = {"L$0"})
    /* renamed from: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$customColumn$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RekapWilayahViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [T, java.util.List] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final Ref.ObjectRef objectRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = new ArrayList();
                CustomDataTemplateRepository customTemplateRepo = DataSurvey.CustomTemplate.INSTANCE.getCustomTemplateRepo();
                String templateIdGlobal = RekapWilayahViewModel.this.getTemplateIdGlobal();
                Intrinsics.checkNotNull(templateIdGlobal);
                this.L$0 = objectRef2;
                this.label = 1;
                Object itemById = customTemplateRepo.getItemById(templateIdGlobal, this);
                if (itemById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                obj = itemById;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            CustomDataTemplateEntity customDataTemplateEntity = (CustomDataTemplateEntity) obj;
            if (customDataTemplateEntity != null) {
                if (customDataTemplateEntity.getData1() != null) {
                    List list = (List) objectRef.element;
                    Data data1 = customDataTemplateEntity.getData1();
                    Intrinsics.checkNotNull(data1);
                    list.add(data1);
                }
                if (customDataTemplateEntity.getData2() != null) {
                    List list2 = (List) objectRef.element;
                    Data data2 = customDataTemplateEntity.getData2();
                    Intrinsics.checkNotNull(data2);
                    list2.add(data2);
                }
                if (customDataTemplateEntity.getData3() != null) {
                    List list3 = (List) objectRef.element;
                    Data data3 = customDataTemplateEntity.getData3();
                    Intrinsics.checkNotNull(data3);
                    list3.add(data3);
                }
                if (customDataTemplateEntity.getData4() != null) {
                    List list4 = (List) objectRef.element;
                    Data data4 = customDataTemplateEntity.getData4();
                    Intrinsics.checkNotNull(data4);
                    list4.add(data4);
                }
                if (customDataTemplateEntity.getData5() != null) {
                    List list5 = (List) objectRef.element;
                    Data data5 = customDataTemplateEntity.getData5();
                    Intrinsics.checkNotNull(data5);
                    list5.add(data5);
                }
                if (customDataTemplateEntity.getData6() != null) {
                    List list6 = (List) objectRef.element;
                    Data data6 = customDataTemplateEntity.getData6();
                    Intrinsics.checkNotNull(data6);
                    list6.add(data6);
                }
                if (customDataTemplateEntity.getData7() != null) {
                    List list7 = (List) objectRef.element;
                    Data data7 = customDataTemplateEntity.getData7();
                    Intrinsics.checkNotNull(data7);
                    list7.add(data7);
                }
                if (customDataTemplateEntity.getData8() != null) {
                    List list8 = (List) objectRef.element;
                    Data data8 = customDataTemplateEntity.getData8();
                    Intrinsics.checkNotNull(data8);
                    list8.add(data8);
                }
                if (customDataTemplateEntity.getData9() != null) {
                    List list9 = (List) objectRef.element;
                    Data data9 = customDataTemplateEntity.getData9();
                    Intrinsics.checkNotNull(data9);
                    list9.add(data9);
                }
                if (customDataTemplateEntity.getData10() != null) {
                    List list10 = (List) objectRef.element;
                    Data data10 = customDataTemplateEntity.getData10();
                    Intrinsics.checkNotNull(data10);
                    list10.add(data10);
                }
                final Ref.IntRef intRef = new Ref.IntRef();
                intRef.element = -1;
                Activity activity = RekapWilayahViewModel.this.activityNow;
                final RekapWilayahViewModel rekapWilayahViewModel = RekapWilayahViewModel.this;
                activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$customColumn$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        RekapWilayahViewModel.AnonymousClass1.invokeSuspend$lambda$5(rekapWilayahViewModel, objectRef, intRef);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$5(final RekapWilayahViewModel rekapWilayahViewModel, final Ref.ObjectRef objectRef, final Ref.IntRef intRef) {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(rekapWilayahViewModel.activityNow);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_lihat_rekapan);
            Window window = bottomSheetDialog.getWindow();
            Intrinsics.checkNotNull(window);
            window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            bottomSheetDialog.setCancelable(true);
            final int i = 0;
            for (Object obj : (Iterable) objectRef.element) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                RadioButton radioButton = new RadioButton(rekapWilayahViewModel.activityNow);
                radioButton.setText(((Data) obj).getColumnName());
                radioButton.setId(View.generateViewId());
                radioButton.setTextColor(ContextCompat.getColor(rekapWilayahViewModel.activityNow, R.color.primary30));
                radioButton.setButtonTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{-16842912}}, new int[]{ContextCompat.getColor(rekapWilayahViewModel.activityNow, R.color.primary30), ContextCompat.getColor(rekapWilayahViewModel.activityNow, R.color.primary30)}));
                if (i == intRef.element) {
                    radioButton.setChecked(true);
                }
                radioButton.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$customColumn$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        intRef.element = i;
                    }
                });
                ((RadioGroup) bottomSheetDialog.findViewById(R.id.listColumn_rg)).addView(radioButton);
                i = i2;
            }
            ((Button) bottomSheetDialog.findViewById(R.id.btn_lihat_rekapan)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$customColumn$1$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RekapWilayahViewModel.AnonymousClass1.invokeSuspend$lambda$5$lambda$2(bottomSheetDialog, rekapWilayahViewModel, intRef, objectRef, view);
                }
            });
            ((Button) bottomSheetDialog.findViewById(R.id.btn_batal)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$customColumn$1$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });
            ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialogFilterAssignment)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.rekapWilayah.RekapWilayahViewModel$customColumn$1$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$5$lambda$2(BottomSheetDialog bottomSheetDialog, RekapWilayahViewModel rekapWilayahViewModel, Ref.IntRef intRef, Ref.ObjectRef objectRef, View view) {
            bottomSheetDialog.dismiss();
            rekapWilayahViewModel._selectedColumnIndex.setValue(Integer.valueOf(intRef.element));
            rekapWilayahViewModel._selectedColumnName.setValue(((Data) ((List) objectRef.element).get(intRef.element)).getColumnName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void customColumn$lambda$0(RekapWilayahViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activityNow, "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
    }
}
