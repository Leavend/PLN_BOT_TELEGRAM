package id.go.bpsfasih.ui.daftarwilayah;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.android.gms.actions.SearchIntents;
import com.google.gson.Gson;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.pojo.AssignmentRegionWilayahPojo;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.CheckNotificationAssignmentModel;
import id.go.bpsfasih.data.remote.dto.CheckNotificationAssignmentResponse;
import id.go.bpsfasih.data.remote.dto.RegionMetadataResponse;
import id.go.bpsfasih.data.repository.NotificationRepositoryImpl;
import id.go.bpsfasih.data.repository.RegionRepositoryImpl;
import id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel;
import id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialActivity;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.sync.dbProcess.DPAssignment;
import id.go.bpsfasih.utils.sync.dbProcess.DPAssignmentRegion;
import id.go.bpsfasih.utils.sync.reqDownload.RDAssignment;
import id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentRegion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: UpdateListingViewModel.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\fJ\u000e\u00107\u001a\u0002082\u0006\u00109\u001a\u00020/J\u0016\u0010:\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00132\u0006\u0010;\u001a\u00020\tJ\u0006\u0010<\u001a\u000208J\"\u0010=\u001a\u0002082\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u00132\f\u0010@\u001a\b\u0012\u0004\u0012\u0002080AJ\"\u0010B\u001a\u0002082\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u00132\f\u0010@\u001a\b\u0012\u0004\u0012\u0002080AJ\u0006\u0010E\u001a\u000208J/\u0010F\u001a\u0002082'\u0010@\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020?0\u0013¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0004\u0012\u0002080GJ/\u0010K\u001a\u0002082'\u0010@\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020D0L¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0004\u0012\u0002080GJ\u0014\u0010M\u001a\u0002082\f\u0010@\u001a\b\u0012\u0004\u0012\u0002080AR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R(\u0010\u001b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u0013\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010(\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R \u00104\u001a\b\u0012\u0004\u0012\u00020/0.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00101\"\u0004\b6\u00103¨\u0006N"}, d2 = {"Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "activity", "Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity;", "viewLevelId", "", "periodeIdPrimary", "", "periodeId", "templateId", "(Landroid/app/Application;Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "activityNow", "getActivityNow", "()Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity;", "setActivityNow", "(Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity;)V", "assignmentIdsDeleted", "", "getAssignmentIdsDeleted", "()Ljava/util/List;", "setAssignmentIdsDeleted", "(Ljava/util/List;)V", "assignmentIdsDownloaded", "getAssignmentIdsDownloaded", "setAssignmentIdsDownloaded", "assignmentUpdateListing", "Landroidx/lifecycle/LiveData;", "Lid/go/bpsfasih/data/local/pojo/AssignmentRegionWilayahPojo;", "getAssignmentUpdateListing", "()Landroidx/lifecycle/LiveData;", "setAssignmentUpdateListing", "(Landroidx/lifecycle/LiveData;)V", "periode", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "getPeriode", "()Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "setPeriode", "(Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;)V", "periodeIdGlobal", "getPeriodeIdGlobal", "()Ljava/lang/String;", "setPeriodeIdGlobal", "(Ljava/lang/String;)V", "showProgressBar", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getShowProgressBar", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setShowProgressBar", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "showProgressBarDialog", "getShowProgressBarDialog", "setShowProgressBarDialog", "checkNotificationAssignment", "", "isAuto", "filter", SearchIntents.EXTRA_QUERY, "requestFlagNotification", "saveAssignment", "listAssignment", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "callback", "Lkotlin/Function0;", "saveAssignmentRegion", "assRegion", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "sync", "syncAssignment", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "data", "syncAssignmentRegion", "", "syncRegionMetadata", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UpdateListingViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private DaftarWilayahActivity activityNow;
    private List<String> assignmentIdsDeleted;
    private List<String> assignmentIdsDownloaded;
    private LiveData<List<AssignmentRegionWilayahPojo>> assignmentUpdateListing;
    public PeriodeEntityNew periode;
    private String periodeIdGlobal;
    private SingleLiveEvent<Boolean> showProgressBar;
    private SingleLiveEvent<Boolean> showProgressBarDialog;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateListingViewModel(Application application, DaftarWilayahActivity activity, Long l, String str, String str2, String str3) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.showProgressBar = new SingleLiveEvent<>();
        this.showProgressBarDialog = new SingleLiveEvent<>();
        this.activityNow = activity;
        this.periodeIdGlobal = str;
        if (l == null || str == null || str2 == null) {
            return;
        }
        DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().getAssignmentRegionWilayahByPeriode(str2);
        setPeriode((PeriodeEntityNew) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(str, null), 1, null));
        this.assignmentUpdateListing = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().getAssignmentRegionWilayahPojo();
        if (((CustomDataTemplateEntity) BuildersKt__BuildersKt.runBlocking$default(null, new UpdateListingViewModel$customTemplate$1(str3, null), 1, null)) == null) {
            BaseClassActivityNew.showAlertDialog$default(this.activityNow, "Terdapat kesalahan", "Custom template gagal terunduh. Silahkan melakukan sync template dan validasi kembali", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UpdateListingViewModel._init_$lambda$0(this.f$0, view);
                }
            }, "", null, false, false, 256, null);
        } else if (Network.INSTANCE.isOnline(activity)) {
            checkNotificationAssignment(true);
        }
    }

    public final LiveData<List<AssignmentRegionWilayahPojo>> getAssignmentUpdateListing() {
        return this.assignmentUpdateListing;
    }

    public final void setAssignmentUpdateListing(LiveData<List<AssignmentRegionWilayahPojo>> liveData) {
        this.assignmentUpdateListing = liveData;
    }

    public final PeriodeEntityNew getPeriode() {
        PeriodeEntityNew periodeEntityNew = this.periode;
        if (periodeEntityNew != null) {
            return periodeEntityNew;
        }
        Intrinsics.throwUninitializedPropertyAccessException("periode");
        return null;
    }

    public final void setPeriode(PeriodeEntityNew periodeEntityNew) {
        Intrinsics.checkNotNullParameter(periodeEntityNew, "<set-?>");
        this.periode = periodeEntityNew;
    }

    public final SingleLiveEvent<Boolean> getShowProgressBar() {
        return this.showProgressBar;
    }

    public final void setShowProgressBar(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.showProgressBar = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getShowProgressBarDialog() {
        return this.showProgressBarDialog;
    }

    public final void setShowProgressBarDialog(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.showProgressBarDialog = singleLiveEvent;
    }

    public final DaftarWilayahActivity getActivityNow() {
        return this.activityNow;
    }

    public final void setActivityNow(DaftarWilayahActivity daftarWilayahActivity) {
        Intrinsics.checkNotNullParameter(daftarWilayahActivity, "<set-?>");
        this.activityNow = daftarWilayahActivity;
    }

    public final List<String> getAssignmentIdsDownloaded() {
        return this.assignmentIdsDownloaded;
    }

    public final void setAssignmentIdsDownloaded(List<String> list) {
        this.assignmentIdsDownloaded = list;
    }

    public final List<String> getAssignmentIdsDeleted() {
        return this.assignmentIdsDeleted;
    }

    public final void setAssignmentIdsDeleted(List<String> list) {
        this.assignmentIdsDeleted = list;
    }

    public final String getPeriodeIdGlobal() {
        return this.periodeIdGlobal;
    }

    public final void setPeriodeIdGlobal(String str) {
        this.periodeIdGlobal = str;
    }

    /* compiled from: UpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$1", f = "UpdateListingViewModel.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PeriodeEntityNew>, Object> {
        final /* synthetic */ String $periodeIdPrimary;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$periodeIdPrimary = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$periodeIdPrimary, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PeriodeEntityNew> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DataSurvey.Periode.INSTANCE.getPeriodeRepository().getPeriodeByPrimaryId(this.$periodeIdPrimary, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(UpdateListingViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activityNow.finish();
    }

    public final List<AssignmentRegionWilayahPojo> filter(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        ArrayList arrayList = new ArrayList();
        if (!query.equals("")) {
            LiveData<List<AssignmentRegionWilayahPojo>> liveData = this.assignmentUpdateListing;
            Intrinsics.checkNotNull(liveData);
            List<AssignmentRegionWilayahPojo> value = liveData.getValue();
            Intrinsics.checkNotNull(value);
            for (AssignmentRegionWilayahPojo assignmentRegionWilayahPojo : value) {
                assignmentRegionWilayahPojo.getAssignmentRegion().getRegion();
                String str = new Gson().toJson(assignmentRegionWilayahPojo.getAssignmentRegion().getRegion());
                Intrinsics.checkNotNullExpressionValue(str, "str");
                String lowerCase2 = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                String str2 = lowerCase;
                if (!StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) str2, false, 2, (Object) null)) {
                    String smallest_region_full_code = assignmentRegionWilayahPojo.getAssignmentRegion().getSmallest_region_full_code();
                    if (smallest_region_full_code == null) {
                        smallest_region_full_code = "";
                    }
                    String lowerCase3 = smallest_region_full_code.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    if (StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) str2, false, 2, (Object) null)) {
                    }
                }
                arrayList.add(assignmentRegionWilayahPojo);
            }
        } else {
            LiveData<List<AssignmentRegionWilayahPojo>> liveData2 = this.assignmentUpdateListing;
            Intrinsics.checkNotNull(liveData2);
            List<AssignmentRegionWilayahPojo> value2 = liveData2.getValue();
            Intrinsics.checkNotNull(value2);
            arrayList.addAll(value2);
        }
        return arrayList;
    }

    /* compiled from: UpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$checkNotificationAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08601 extends Lambda implements Function1<CheckNotificationAssignmentResponse, Unit> {
        final /* synthetic */ boolean $isAuto;
        final /* synthetic */ UpdateListingViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08601(boolean z, UpdateListingViewModel updateListingViewModel) {
            super(1);
            this.$isAuto = z;
            this.this$0 = updateListingViewModel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CheckNotificationAssignmentResponse checkNotificationAssignmentResponse) {
            invoke2(checkNotificationAssignmentResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final CheckNotificationAssignmentResponse checkNotificationAssignmentResponse) {
            Integer numValueOf;
            List<String> idsDelete;
            List<String> idsDelete2;
            List<String> idsDownload;
            List<String> idsDownload2;
            Integer numValueOf2 = 0;
            if (this.$isAuto) {
                this.this$0.getShowProgressBar().postValue(false);
            } else {
                this.this$0.getActivityNow().hideProgressBar();
            }
            if (checkNotificationAssignmentResponse != null ? Intrinsics.areEqual((Object) checkNotificationAssignmentResponse.getSuccess(), (Object) true) : false) {
                UpdateListingViewModel updateListingViewModel = this.this$0;
                CheckNotificationAssignmentModel data = checkNotificationAssignmentResponse.getData();
                updateListingViewModel.setAssignmentIdsDownloaded(data != null ? data.getIdsDownload() : null);
                UpdateListingViewModel updateListingViewModel2 = this.this$0;
                CheckNotificationAssignmentModel data2 = checkNotificationAssignmentResponse.getData();
                updateListingViewModel2.setAssignmentIdsDeleted(data2 != null ? data2.getIdsDelete() : null);
                UpdateListingViewModel updateListingViewModel3 = this.this$0;
                updateListingViewModel3.setPeriodeIdGlobal(updateListingViewModel3.getPeriode().getId());
                CheckNotificationAssignmentModel data3 = checkNotificationAssignmentResponse.getData();
                if (((data3 == null || (idsDownload2 = data3.getIdsDownload()) == null) ? null : Integer.valueOf(idsDownload2.size())) != null) {
                    CheckNotificationAssignmentModel data4 = checkNotificationAssignmentResponse.getData();
                    numValueOf = (data4 == null || (idsDownload = data4.getIdsDownload()) == null) ? null : Integer.valueOf(idsDownload.size());
                } else {
                    numValueOf = numValueOf2;
                }
                CheckNotificationAssignmentModel data5 = checkNotificationAssignmentResponse.getData();
                if (((data5 == null || (idsDelete2 = data5.getIdsDelete()) == null) ? null : Integer.valueOf(idsDelete2.size())) != null) {
                    CheckNotificationAssignmentModel data6 = checkNotificationAssignmentResponse.getData();
                    numValueOf2 = (data6 == null || (idsDelete = data6.getIdsDelete()) == null) ? null : Integer.valueOf(idsDelete.size());
                }
                DaftarWilayahActivity activityNow = this.this$0.getActivityNow();
                Intrinsics.checkNotNull(numValueOf);
                int iIntValue = numValueOf.intValue();
                Intrinsics.checkNotNull(numValueOf2);
                String str = "Ada update " + (iIntValue + numValueOf2.intValue()) + "  assignment. Download perubahan data sekarang ?";
                final UpdateListingViewModel updateListingViewModel4 = this.this$0;
                BaseClassActivityNew.showAlertDialog$default(activityNow, "Ada Perubahan Data", str, null, "OK", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$checkNotificationAssignment$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UpdateListingViewModel.C08601.invoke$lambda$1(updateListingViewModel4, checkNotificationAssignmentResponse, view);
                    }
                }, "Cancel", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$checkNotificationAssignment$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UpdateListingViewModel.C08601.invoke$lambda$2(view);
                    }
                }, false, false, 384, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(UpdateListingViewModel this$0, CheckNotificationAssignmentResponse checkNotificationAssignmentResponse, View view) {
            List<String> idsDelete;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (Network.INSTANCE.isOnline(this$0.getActivityNow())) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UpdateListingViewModel$checkNotificationAssignment$1$1$1(this$0, checkNotificationAssignmentResponse, null), 2, null);
                CheckNotificationAssignmentModel data = checkNotificationAssignmentResponse.getData();
                if (data == null || (idsDelete = data.getIdsDelete()) == null) {
                    return;
                }
                Iterator<T> it = idsDelete.iterator();
                while (it.hasNext()) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UpdateListingViewModel$checkNotificationAssignment$1$1$2$1((String) it.next(), null), 2, null);
                }
            }
        }
    }

    public final void checkNotificationAssignment(boolean isAuto) {
        if (isAuto) {
            this.showProgressBar.postValue(true);
        } else {
            this.activityNow.showProgressBar();
        }
        NotificationRepositoryImpl notificationRepositoryImpl = new NotificationRepositoryImpl();
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        Intrinsics.checkNotNull(sessionString);
        notificationRepositoryImpl.checkNotificationAssignment(sessionString, getPeriode().getId(), new C08601(isAuto, this));
    }

    public final void requestFlagNotification() {
        NotificationRepositoryImpl notificationRepositoryImpl = new NotificationRepositoryImpl();
        String str = this.periodeIdGlobal;
        Intrinsics.checkNotNull(str);
        List<String> list = this.assignmentIdsDownloaded;
        Intrinsics.checkNotNull(list);
        List<String> list2 = this.assignmentIdsDeleted;
        Intrinsics.checkNotNull(list2);
        notificationRepositoryImpl.flagDoneNotification(str, list, list2, new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel.requestFlagNotification.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponse baseResponse) {
                invoke2(baseResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponse baseResponse) {
                if (baseResponse != null) {
                    Log.d("SYNC_FLAG_SUCCESS", new Gson().toJson(baseResponse));
                } else {
                    Log.d("SYNC_FLAG_ERROR", "Error flag notification");
                }
            }
        });
    }

    public final void sync() {
        this.activityNow.showProgressBar();
        syncAssignment(new Function1<List<? extends AssignmentEntity>, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel.sync.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends AssignmentEntity> list) {
                invoke2((List<AssignmentEntity>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final List<AssignmentEntity> assBeforeSaveModel) {
                Intrinsics.checkNotNullParameter(assBeforeSaveModel, "assBeforeSaveModel");
                UpdateListingViewModel updateListingViewModel = UpdateListingViewModel.this;
                final UpdateListingViewModel updateListingViewModel2 = UpdateListingViewModel.this;
                updateListingViewModel.syncAssignmentRegion(new Function1<List<AssignmentRegionEntity>, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel.sync.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(List<AssignmentRegionEntity> list) {
                        invoke2(list);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final List<AssignmentRegionEntity> assRegion) {
                        Intrinsics.checkNotNullParameter(assRegion, "assRegion");
                        UpdateListingViewModel updateListingViewModel3 = updateListingViewModel2;
                        List<AssignmentEntity> list = assBeforeSaveModel;
                        final UpdateListingViewModel updateListingViewModel4 = updateListingViewModel2;
                        updateListingViewModel3.saveAssignment(list, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel.sync.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                UpdateListingViewModel updateListingViewModel5 = updateListingViewModel4;
                                List<AssignmentRegionEntity> list2 = assRegion;
                                final UpdateListingViewModel updateListingViewModel6 = updateListingViewModel4;
                                updateListingViewModel5.saveAssignmentRegion(list2, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel.sync.1.1.1.1
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        UpdateListingViewModel updateListingViewModel7 = updateListingViewModel6;
                                        final UpdateListingViewModel updateListingViewModel8 = updateListingViewModel6;
                                        updateListingViewModel7.syncRegionMetadata(new Function0<Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel.sync.1.1.1.1.1
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                updateListingViewModel8.getActivityNow().hideProgressBar();
                                                Intent intent = new Intent(updateListingViewModel8.getActivityNow(), (Class<?>) SyncAnswerPartialActivity.class);
                                                intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), updateListingViewModel8.getPeriode().getPrimaryId());
                                                updateListingViewModel8.getActivityNow().getResultIntentFromSyncAssignment().launch(intent);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    /* compiled from: UpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/utils/sync/reqDownload/RDAssignment$DownloadResult;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08651 extends Lambda implements Function1<RDAssignment.DownloadResult, Unit> {
        final /* synthetic */ Function1<List<AssignmentEntity>, Unit> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08651(Function1<? super List<AssignmentEntity>, Unit> function1) {
            super(1);
            this.$callback = function1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2$lambda$1(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(RDAssignment.DownloadResult downloadResult) {
            invoke2(downloadResult);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(RDAssignment.DownloadResult it) {
            Unit unit;
            Intrinsics.checkNotNullParameter(it, "it");
            List<AssignmentEntity> assignments = it.getAssignments();
            if (assignments != null) {
                this.$callback.invoke(assignments);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                UpdateListingViewModel updateListingViewModel = UpdateListingViewModel.this;
                DaftarWilayahActivity activityNow = updateListingViewModel.getActivityNow();
                int i = R.color.error30;
                String errorMessage = it.getErrorMessage();
                if (errorMessage == null) {
                    errorMessage = "Terdapat kesalahan ketika mendownload daftar assignment (1)";
                }
                String str = errorMessage;
                int i2 = R.color.error30;
                activityNow.showAlertDialogColor("Gagal", Integer.valueOf(i), str, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncAssignment$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UpdateListingViewModel.C08651.invoke$lambda$2$lambda$1(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
                updateListingViewModel.getActivityNow().hideProgressBar();
            }
        }
    }

    public final void syncAssignment(Function1<? super List<AssignmentEntity>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        new RDAssignment(getPeriode().getId(), new C08651(callback));
    }

    /* compiled from: UpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncAssignmentRegion$1, reason: invalid class name and case insensitive filesystem */
    static final class C08661 extends Lambda implements Function1<List<AssignmentRegionEntity>, Unit> {
        final /* synthetic */ Function1<List<AssignmentRegionEntity>, Unit> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08661(Function1<? super List<AssignmentRegionEntity>, Unit> function1) {
            super(1);
            this.$callback = function1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2$lambda$1(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<AssignmentRegionEntity> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<AssignmentRegionEntity> list) {
            Unit unit;
            if (list != null) {
                this.$callback.invoke(list);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                UpdateListingViewModel updateListingViewModel = UpdateListingViewModel.this;
                DaftarWilayahActivity activityNow = updateListingViewModel.getActivityNow();
                int i = R.color.error30;
                int i2 = R.color.error30;
                activityNow.showAlertDialogColor("Gagal", Integer.valueOf(i), "Terdapat kesalahan ketika mendownload daftar assignment (2)", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncAssignmentRegion$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UpdateListingViewModel.C08661.invoke$lambda$2$lambda$1(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
                updateListingViewModel.getActivityNow().hideProgressBar();
            }
        }
    }

    public final void syncAssignmentRegion(Function1<? super List<AssignmentRegionEntity>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        new RDAssignmentRegion(getPeriode(), new C08661(callback));
    }

    /* compiled from: UpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$saveAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08621 extends Lambda implements Function1<Boolean, Unit> {
        final /* synthetic */ Function0<Unit> $callback;
        final /* synthetic */ UpdateListingViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08621(Function0<Unit> function0, UpdateListingViewModel updateListingViewModel) {
            super(1);
            this.$callback = function0;
            this.this$0 = updateListingViewModel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            if (z) {
                this.$callback.invoke();
                return;
            }
            DaftarWilayahActivity activityNow = this.this$0.getActivityNow();
            int i = R.color.error30;
            int i2 = R.color.error30;
            activityNow.showAlertDialogColor("Gagal", Integer.valueOf(i), "Terdapat kesalahan ketika menyimpan daftar assignment (1)", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$saveAssignment$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UpdateListingViewModel.C08621.invoke$lambda$0(view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
            this.this$0.getActivityNow().hideProgressBar();
        }
    }

    public final void saveAssignment(List<AssignmentEntity> listAssignment, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(listAssignment, "listAssignment");
        Intrinsics.checkNotNullParameter(callback, "callback");
        new DPAssignment(getPeriode().getSurvey().getId(), getPeriode().getId(), listAssignment, new C08621(callback, this));
    }

    /* compiled from: UpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$saveAssignmentRegion$1, reason: invalid class name and case insensitive filesystem */
    static final class C08631 extends Lambda implements Function1<Boolean, Unit> {
        final /* synthetic */ Function0<Unit> $callback;
        final /* synthetic */ UpdateListingViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08631(Function0<Unit> function0, UpdateListingViewModel updateListingViewModel) {
            super(1);
            this.$callback = function0;
            this.this$0 = updateListingViewModel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            if (z) {
                this.$callback.invoke();
                return;
            }
            DaftarWilayahActivity activityNow = this.this$0.getActivityNow();
            int i = R.color.error30;
            int i2 = R.color.error30;
            activityNow.showAlertDialogColor("Gagal", Integer.valueOf(i), "Terdapat kesalahan ketika menyimpan daftar assignment (2)", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$saveAssignmentRegion$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UpdateListingViewModel.C08631.invoke$lambda$0(view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
            this.this$0.getActivityNow().hideProgressBar();
        }
    }

    public final void saveAssignmentRegion(List<AssignmentRegionEntity> assRegion, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(assRegion, "assRegion");
        Intrinsics.checkNotNullParameter(callback, "callback");
        new DPAssignmentRegion(getPeriode().getId(), assRegion, new C08631(callback, this));
    }

    /* compiled from: UpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lid/go/bpsfasih/data/remote/dto/RegionMetadataResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncRegionMetadata$1, reason: invalid class name and case insensitive filesystem */
    static final class C08671 extends Lambda implements Function1<RegionMetadataResponse, Unit> {
        final /* synthetic */ Function0<Unit> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08671(Function0<Unit> function0) {
            super(1);
            this.$callback = function0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(RegionMetadataResponse regionMetadataResponse) {
            invoke2(regionMetadataResponse);
            return Unit.INSTANCE;
        }

        /* compiled from: UpdateListingViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncRegionMetadata$1$1", f = "UpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncRegionMetadata$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function0<Unit> $callback;
            final /* synthetic */ RegionMetadataResponse $result;
            int label;
            final /* synthetic */ UpdateListingViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02071(Function0<Unit> function0, UpdateListingViewModel updateListingViewModel, RegionMetadataResponse regionMetadataResponse, Continuation<? super C02071> continuation) {
                super(2, continuation);
                this.$callback = function0;
                this.this$0 = updateListingViewModel;
                this.$result = regionMetadataResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C02071(this.$callback, this.this$0, this.$result, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C02071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: UpdateListingViewModel.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncRegionMetadata$1$1$1", f = "UpdateListingViewModel.kt", i = {}, l = {320}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncRegionMetadata$1$1$1, reason: invalid class name and collision with other inner class name */
            static final class C02081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ RegionMetadataResponse $result;
                int label;
                final /* synthetic */ UpdateListingViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02081(UpdateListingViewModel updateListingViewModel, RegionMetadataResponse regionMetadataResponse, Continuation<? super C02081> continuation) {
                    super(2, continuation);
                    this.this$0 = updateListingViewModel;
                    this.$result = regionMetadataResponse;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C02081(this.this$0, this.$result, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().updateRegionMetadataByPeriode(this.this$0.getPeriode().getId(), this.$result.getData(), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws InterruptedException {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    BuildersKt__BuildersKt.runBlocking$default(null, new C02081(this.this$0, this.$result, null), 1, null);
                    this.$callback.invoke();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(RegionMetadataResponse regionMetadataResponse) {
            String message;
            if ((regionMetadataResponse != null ? Intrinsics.areEqual((Object) regionMetadataResponse.getSuccess(), (Object) true) : false) && regionMetadataResponse.getData() != null) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02071(this.$callback, UpdateListingViewModel.this, regionMetadataResponse, null), 2, null);
                return;
            }
            DaftarWilayahActivity activityNow = UpdateListingViewModel.this.getActivityNow();
            int i = R.color.error30;
            if (regionMetadataResponse == null || (message = regionMetadataResponse.getMessage()) == null) {
                message = "Terdapat kesalahan ketika mendownload metadata wilayah";
            }
            activityNow.showAlertDialogColor("Gagal", Integer.valueOf(i), message, Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel$syncRegionMetadata$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UpdateListingViewModel.C08671.invoke$lambda$0(view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
            UpdateListingViewModel.this.getActivityNow().hideProgressBar();
        }
    }

    public final void syncRegionMetadata(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        new RegionRepositoryImpl().getRegionMetadata(getPeriode().getId(), new C08671(callback));
    }
}
