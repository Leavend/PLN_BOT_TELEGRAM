package id.go.bpsfasih.ui.tariksampel;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.Content;
import id.go.bpsfasih.data.local.entities.Level1;
import id.go.bpsfasih.data.local.entities.Level10;
import id.go.bpsfasih.data.local.entities.Level2;
import id.go.bpsfasih.data.local.entities.Level3;
import id.go.bpsfasih.data.local.entities.Level4;
import id.go.bpsfasih.data.local.entities.Level5;
import id.go.bpsfasih.data.local.entities.Level6;
import id.go.bpsfasih.data.local.entities.Level7;
import id.go.bpsfasih.data.local.entities.Level8;
import id.go.bpsfasih.data.local.entities.Level9;
import id.go.bpsfasih.data.local.entities.Region;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.GetSamplingPeriodeResponse;
import id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl;
import id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONException;

/* compiled from: TarikSampelViewModel.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fJ\u0006\u0010\u0013\u001a\u00020\rJ3\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\r0\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lid/go/bpsfasih/ui/tariksampel/TarikSampelViewModel;", "Landroidx/lifecycle/ViewModel;", "activity", "Lid/go/bpsfasih/ui/tariksampel/TarikSampelActivity;", "(Lid/go/bpsfasih/ui/tariksampel/TarikSampelActivity;)V", "_showProgressBar", "Landroidx/lifecycle/MutableLiveData;", "", "get_showProgressBar", "()Landroidx/lifecycle/MutableLiveData;", "getActivity", "()Lid/go/bpsfasih/ui/tariksampel/TarikSampelActivity;", "execTariksampel", "", "samplingSurveyPeriodId", "", "periodeId", "regionFullCode", "regionId", "offlineSampling", "reqTarikSampel", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampelViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableLiveData<Boolean> _showProgressBar;
    private final TarikSampelActivity activity;

    public final void offlineSampling() {
    }

    public TarikSampelViewModel(TarikSampelActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this._showProgressBar = new MutableLiveData<>();
    }

    public final TarikSampelActivity getActivity() {
        return this.activity;
    }

    public final MutableLiveData<Boolean> get_showProgressBar() {
        return this._showProgressBar;
    }

    /* compiled from: TarikSampelViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/data/remote/dto/GetSamplingPeriodeResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$reqTarikSampel$1, reason: invalid class name and case insensitive filesystem */
    static final class C09211 extends Lambda implements Function1<GetSamplingPeriodeResponse, Unit> {
        final /* synthetic */ Function1<String, Unit> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C09211(Function1<? super String, Unit> function1) {
            super(1);
            this.$callback = function1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetSamplingPeriodeResponse getSamplingPeriodeResponse) {
            invoke2(getSamplingPeriodeResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(GetSamplingPeriodeResponse getSamplingPeriodeResponse) {
            TarikSampelViewModel.this.get_showProgressBar().postValue(false);
            if (getSamplingPeriodeResponse != null ? Intrinsics.areEqual((Object) getSamplingPeriodeResponse.getSuccess(), (Object) true) : false) {
                this.$callback.invoke(((Content) CollectionsKt.first((List) getSamplingPeriodeResponse.getData().getContent())).getId());
                return;
            }
            if (getSamplingPeriodeResponse != null ? Intrinsics.areEqual((Object) getSamplingPeriodeResponse.getSuccess(), (Object) false) : false) {
                TarikSampelActivity activity = TarikSampelViewModel.this.getActivity();
                int i = R.color.error30;
                activity.showAlertDialogColor("Peringatan", Integer.valueOf(i), getSamplingPeriodeResponse.getMessage(), Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$reqTarikSampel$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TarikSampelViewModel.C09211.invoke$lambda$0(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
                return;
            }
            TarikSampelViewModel.this.getActivity().showAlertDialogColor("Peringatan", Integer.valueOf(R.color.error30), "Kesalahan Koneksi", Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$reqTarikSampel$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TarikSampelViewModel.C09211.invoke$lambda$1(view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
        }
    }

    public final void reqTarikSampel(String periodeId, Function1<? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this._showProgressBar.postValue(true);
        new TarikSampelRepositoryImpl().getSamplingPeriode(periodeId, new C09211(callback));
    }

    /* compiled from: TarikSampelViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$execTariksampel$1", f = "TarikSampelViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$execTariksampel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $periodeId;
        final /* synthetic */ String $regionFullCode;
        final /* synthetic */ String $regionId;
        final /* synthetic */ String $samplingSurveyPeriodId;
        int label;
        final /* synthetic */ TarikSampelViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, String str2, String str3, String str4, TarikSampelViewModel tarikSampelViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$regionId = str;
            this.$periodeId = str2;
            this.$samplingSurveyPeriodId = str3;
            this.$regionFullCode = str4;
            this.this$0 = tarikSampelViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$regionId, this.$periodeId, this.$samplingSurveyPeriodId, this.$regionFullCode, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException {
            Region region;
            Level1 level1;
            Level2 level2;
            Level3 level3;
            Level4 level4;
            Level5 level5;
            Level6 level6;
            Level7 level7;
            Level8 level8;
            Level9 level9;
            Level10 level10;
            Region region2;
            Level1 level12;
            Level2 level22;
            Level3 level32;
            Level4 level42;
            Level5 level52;
            Level6 level62;
            Level7 level72;
            Level8 level82;
            Level9 level92;
            Region region3;
            Level1 level13;
            Level2 level23;
            Level3 level33;
            Level4 level43;
            Level5 level53;
            Level6 level63;
            Level7 level73;
            Level8 level83;
            Region region4;
            Level1 level14;
            Level2 level24;
            Level3 level34;
            Level4 level44;
            Level5 level54;
            Level6 level64;
            Level7 level74;
            Region region5;
            Level1 level15;
            Level2 level25;
            Level3 level35;
            Level4 level45;
            Level5 level55;
            Level6 level65;
            Region region6;
            Level1 level16;
            Level2 level26;
            Level3 level36;
            Level4 level46;
            Level5 level56;
            Region region7;
            Level1 level17;
            Level2 level27;
            Level3 level37;
            Level4 level47;
            Region region8;
            Level1 level18;
            Level2 level28;
            Level3 level38;
            Region region9;
            Level1 level19;
            Level2 level29;
            Region region10;
            Level1 level110;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AssignmentEntity assignmentEntity = (AssignmentEntity) CollectionsKt.first((List) DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentsByPeriodeRegionId(this.$regionId, this.$periodeId));
            new TarikSampelRepositoryImpl().runTarikSampling(this.$samplingSurveyPeriodId, this.$regionFullCode, (assignmentEntity == null || (region10 = assignmentEntity.getRegion()) == null || (level110 = region10.getLevel1()) == null) ? null : level110.getId(), (assignmentEntity == null || (region9 = assignmentEntity.getRegion()) == null || (level19 = region9.getLevel1()) == null || (level29 = level19.getLevel2()) == null) ? null : level29.getId(), (assignmentEntity == null || (region8 = assignmentEntity.getRegion()) == null || (level18 = region8.getLevel1()) == null || (level28 = level18.getLevel2()) == null || (level38 = level28.getLevel3()) == null) ? null : level38.getId(), (assignmentEntity == null || (region7 = assignmentEntity.getRegion()) == null || (level17 = region7.getLevel1()) == null || (level27 = level17.getLevel2()) == null || (level37 = level27.getLevel3()) == null || (level47 = level37.getLevel4()) == null) ? null : level47.getId(), (assignmentEntity == null || (region6 = assignmentEntity.getRegion()) == null || (level16 = region6.getLevel1()) == null || (level26 = level16.getLevel2()) == null || (level36 = level26.getLevel3()) == null || (level46 = level36.getLevel4()) == null || (level56 = level46.getLevel5()) == null) ? null : level56.getId(), (assignmentEntity == null || (region5 = assignmentEntity.getRegion()) == null || (level15 = region5.getLevel1()) == null || (level25 = level15.getLevel2()) == null || (level35 = level25.getLevel3()) == null || (level45 = level35.getLevel4()) == null || (level55 = level45.getLevel5()) == null || (level65 = level55.getLevel6()) == null) ? null : level65.getId(), (assignmentEntity == null || (region4 = assignmentEntity.getRegion()) == null || (level14 = region4.getLevel1()) == null || (level24 = level14.getLevel2()) == null || (level34 = level24.getLevel3()) == null || (level44 = level34.getLevel4()) == null || (level54 = level44.getLevel5()) == null || (level64 = level54.getLevel6()) == null || (level74 = level64.getLevel7()) == null) ? null : level74.getId(), (assignmentEntity == null || (region3 = assignmentEntity.getRegion()) == null || (level13 = region3.getLevel1()) == null || (level23 = level13.getLevel2()) == null || (level33 = level23.getLevel3()) == null || (level43 = level33.getLevel4()) == null || (level53 = level43.getLevel5()) == null || (level63 = level53.getLevel6()) == null || (level73 = level63.getLevel7()) == null || (level83 = level73.getLevel8()) == null) ? null : level83.getId(), (assignmentEntity == null || (region2 = assignmentEntity.getRegion()) == null || (level12 = region2.getLevel1()) == null || (level22 = level12.getLevel2()) == null || (level32 = level22.getLevel3()) == null || (level42 = level32.getLevel4()) == null || (level52 = level42.getLevel5()) == null || (level62 = level52.getLevel6()) == null || (level72 = level62.getLevel7()) == null || (level82 = level72.getLevel8()) == null || (level92 = level82.getLevel9()) == null) ? null : level92.getId(), (assignmentEntity == null || (region = assignmentEntity.getRegion()) == null || (level1 = region.getLevel1()) == null || (level2 = level1.getLevel2()) == null || (level3 = level2.getLevel3()) == null || (level4 = level3.getLevel4()) == null || (level5 = level4.getLevel5()) == null || (level6 = level5.getLevel6()) == null || (level7 = level6.getLevel7()) == null || (level8 = level7.getLevel8()) == null || (level9 = level8.getLevel9()) == null || (level10 = level9.getLevel10()) == null) ? null : level10.getId(), new C02541(this.this$0));
            return Unit.INSTANCE;
        }

        /* compiled from: TarikSampelViewModel.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* renamed from: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$execTariksampel$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02541 extends Lambda implements Function1<BaseResponse, Unit> {
            final /* synthetic */ TarikSampelViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02541(TarikSampelViewModel tarikSampelViewModel) {
                super(1);
                this.this$0 = tarikSampelViewModel;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$1(View view) {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$2(View view) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponse baseResponse) {
                invoke2(baseResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponse baseResponse) {
                this.this$0.get_showProgressBar().postValue(false);
                if (baseResponse != null ? Intrinsics.areEqual((Object) baseResponse.getSuccess(), (Object) true) : false) {
                    TarikSampelActivity activity = this.this$0.getActivity();
                    int i = R.color.success30;
                    String message = baseResponse.getMessage();
                    int i2 = R.color.success30;
                    final TarikSampelViewModel tarikSampelViewModel = this.this$0;
                    activity.showAlertDialogColor("Sukses", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Selesai", Integer.valueOf(R.drawable.layout_button_success), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$execTariksampel$1$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            TarikSampelViewModel.AnonymousClass1.C02541.invoke$lambda$0(tarikSampelViewModel, view);
                        }
                    }, null, null, null, Integer.valueOf(R.color.success30), true);
                    return;
                }
                if (baseResponse != null ? Intrinsics.areEqual((Object) baseResponse.getSuccess(), (Object) false) : false) {
                    TarikSampelActivity activity2 = this.this$0.getActivity();
                    int i3 = R.color.error30;
                    activity2.showAlertDialogColor("Peringatan", Integer.valueOf(i3), baseResponse.getMessage(), Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$execTariksampel$1$1$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            TarikSampelViewModel.AnonymousClass1.C02541.invoke$lambda$1(view);
                        }
                    }, null, null, null, Integer.valueOf(R.color.error30), true);
                    return;
                }
                this.this$0.getActivity().showAlertDialogColor("Peringatan", Integer.valueOf(R.color.error30), "Kesalahan Koneksi", Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel$execTariksampel$1$1$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TarikSampelViewModel.AnonymousClass1.C02541.invoke$lambda$2(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$0(TarikSampelViewModel this$0, View view) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                this$0.getActivity().finish();
            }
        }
    }

    public final void execTariksampel(String samplingSurveyPeriodId, String periodeId, String regionFullCode, String regionId) {
        Intrinsics.checkNotNullParameter(samplingSurveyPeriodId, "samplingSurveyPeriodId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(regionFullCode, "regionFullCode");
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        this._showProgressBar.postValue(true);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(regionId, periodeId, samplingSurveyPeriodId, regionFullCode, this, null), 2, null);
    }
}
