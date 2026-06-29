package id.go.bpsfasih.ui.unduhChangeMode;

import android.app.Application;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.models.BaseResponseAssignmentChangeMode;
import id.go.bpsfasih.data.repository.NotificationRepositoryImpl;
import id.go.bpsfasih.domain.models.AssignmentChangeModeModel;
import id.go.bpsfasih.domain.models.UnduhChangeModeModel;
import id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeViewModel;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.Network;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: UnduhChangeModeViewModel.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010!\u001a\u00020\"2\u0010\u0010#\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u00010\u000bJ\u0006\u0010%\u001a\u00020\"R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0019\"\u0004\b \u0010\u001b¨\u0006&"}, d2 = {"Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "activity", "Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeActivity;", "periodeId", "", "(Landroid/app/Application;Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeActivity;Ljava/lang/String;)V", "_list", "Landroidx/lifecycle/MutableLiveData;", "", "Lid/go/bpsfasih/domain/models/UnduhChangeModeModel;", "get_list", "()Landroidx/lifecycle/MutableLiveData;", "_listDownloaded", "Landroidx/lifecycle/LiveData;", "get_listDownloaded", "()Landroidx/lifecycle/LiveData;", "getActivity", "()Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeActivity;", "finishActivity", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getFinishActivity", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setFinishActivity", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "getPeriodeId", "()Ljava/lang/String;", "showProgressBar", "getShowProgressBar", "setShowProgressBar", "mapData", "", "data", "Lid/go/bpsfasih/domain/models/AssignmentChangeModeModel;", "reqChangeMode", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UnduhChangeModeViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final MutableLiveData<List<UnduhChangeModeModel>> _list;
    private final LiveData<List<String>> _listDownloaded;
    private final UnduhChangeModeActivity activity;
    private SingleLiveEvent<Boolean> finishActivity;
    private final String periodeId;
    private SingleLiveEvent<Boolean> showProgressBar;

    public final UnduhChangeModeActivity getActivity() {
        return this.activity;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnduhChangeModeViewModel(Application application, UnduhChangeModeActivity activity, String periodeId) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        this.activity = activity;
        this.periodeId = periodeId;
        this._list = new MutableLiveData<>();
        this._listDownloaded = FileHelper.INSTANCE.readListDownloaded();
        this.showProgressBar = new SingleLiveEvent<>();
        this.finishActivity = new SingleLiveEvent<>();
        if (Network.INSTANCE.isOnline(activity)) {
            reqChangeMode();
            return;
        }
        int i = R.color.error30;
        int i2 = R.color.error30;
        activity.showAlertDialogColor("Peringatan", Integer.valueOf(i), "Anda tidak terhubung dengan internet", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UnduhChangeModeViewModel._init_$lambda$0(this.f$0, view);
            }
        }, null, null, null, Integer.valueOf(R.color.error30), true);
    }

    public final MutableLiveData<List<UnduhChangeModeModel>> get_list() {
        return this._list;
    }

    public final LiveData<List<String>> get_listDownloaded() {
        return this._listDownloaded;
    }

    public final SingleLiveEvent<Boolean> getShowProgressBar() {
        return this.showProgressBar;
    }

    public final void setShowProgressBar(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.showProgressBar = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getFinishActivity() {
        return this.finishActivity;
    }

    public final void setFinishActivity(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.finishActivity = singleLiveEvent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(UnduhChangeModeViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishActivity.postValue(true);
    }

    /* compiled from: UnduhChangeModeViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lid/go/bpsfasih/data/local/models/BaseResponseAssignmentChangeMode;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeViewModel$reqChangeMode$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function1<BaseResponseAssignmentChangeMode, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseResponseAssignmentChangeMode baseResponseAssignmentChangeMode) {
            invoke2(baseResponseAssignmentChangeMode);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseResponseAssignmentChangeMode baseResponseAssignmentChangeMode) {
            UnduhChangeModeViewModel.this.getShowProgressBar().postValue(false);
            if (baseResponseAssignmentChangeMode != null ? Intrinsics.areEqual((Object) baseResponseAssignmentChangeMode.getSuccess(), (Object) true) : false) {
                UnduhChangeModeViewModel.this.mapData(baseResponseAssignmentChangeMode.getData());
                return;
            }
            if (baseResponseAssignmentChangeMode != null ? Intrinsics.areEqual((Object) baseResponseAssignmentChangeMode.getSuccess(), (Object) false) : false) {
                UnduhChangeModeActivity activity = UnduhChangeModeViewModel.this.getActivity();
                int i = R.color.error30;
                String message = baseResponseAssignmentChangeMode != null ? baseResponseAssignmentChangeMode.getMessage() : null;
                int i2 = R.color.error30;
                final UnduhChangeModeViewModel unduhChangeModeViewModel = UnduhChangeModeViewModel.this;
                activity.showAlertDialogColor("Peringatan", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeViewModel$reqChangeMode$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UnduhChangeModeViewModel.AnonymousClass1.invoke$lambda$0(unduhChangeModeViewModel, view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
                return;
            }
            UnduhChangeModeActivity activity2 = UnduhChangeModeViewModel.this.getActivity();
            int i3 = R.color.error30;
            int i4 = R.color.error30;
            final UnduhChangeModeViewModel unduhChangeModeViewModel2 = UnduhChangeModeViewModel.this;
            activity2.showAlertDialogColor("Peringatan", Integer.valueOf(i3), "Kesalahan Koneksi", Integer.valueOf(i4), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeViewModel$reqChangeMode$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UnduhChangeModeViewModel.AnonymousClass1.invoke$lambda$1(unduhChangeModeViewModel2, view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(UnduhChangeModeViewModel this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getFinishActivity().postValue(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(UnduhChangeModeViewModel this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getFinishActivity().postValue(true);
        }
    }

    public final void reqChangeMode() {
        this.showProgressBar.postValue(true);
        NotificationRepositoryImpl notificationRepositoryImpl = new NotificationRepositoryImpl();
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        Intrinsics.checkNotNull(sessionString);
        notificationRepositoryImpl.reqAssignmentChangeMode(sessionString, this.periodeId, new AnonymousClass1());
    }

    public final void mapData(List<AssignmentChangeModeModel> data) {
        ArrayList arrayList;
        String userId = FasihApp.INSTANCE.getSession().getUserId();
        if (data != null) {
            List<AssignmentChangeModeModel> list = data;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (AssignmentChangeModeModel assignmentChangeModeModel : list) {
                Intrinsics.checkNotNull(assignmentChangeModeModel);
                arrayList2.add(assignmentChangeModeModel.getAssignment_id() + InternalZipConstants.ZIP_FILE_SEPARATOR + userId);
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        List<AssignmentEntity> list2 = (List) BuildersKt__BuildersKt.runBlocking$default(null, new UnduhChangeModeViewModel$mapData$assignmentList$1(arrayList, null), 1, null);
        CustomDataTemplateEntity customDataTemplateEntity = (CustomDataTemplateEntity) BuildersKt__BuildersKt.runBlocking$default(null, new UnduhChangeModeViewModel$mapData$customData$1((String) BuildersKt__BuildersKt.runBlocking$default(null, new UnduhChangeModeViewModel$mapData$templateId$1((PeriodeEntityNew) BuildersKt__BuildersKt.runBlocking$default(null, new UnduhChangeModeViewModel$mapData$periode$1(this, userId, null), 1, null), null), 1, null), null), 1, null);
        UnduhChangeModeModel.Companion companion = UnduhChangeModeModel.INSTANCE;
        Intrinsics.checkNotNull(customDataTemplateEntity);
        this._list.postValue(companion.map(list2, customDataTemplateEntity, this._listDownloaded.getValue()));
    }
}
