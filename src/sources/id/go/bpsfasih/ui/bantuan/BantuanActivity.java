package id.go.bpsfasih.ui.bantuan;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.repository.TicketRepositoryImpl;
import id.go.bpsfasih.databinding.ActivityBantuanBinding;
import id.go.bpsfasih.pusatbantuan.PusatBantuan;
import id.go.bpsfasih.pusatbantuan.PusatBantuanActivity;
import id.go.bpsfasih.utils.helper.DeviceInfoHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: BantuanActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\u0012\u0010\u001f\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0016\u0010\"\u001a\u00020\u00042\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lid/go/bpsfasih/ui/bantuan/BantuanActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "assignmentId", "", "getAssignmentId", "()Ljava/lang/String;", "setAssignmentId", "(Ljava/lang/String;)V", "binding", "Lid/go/bpsfasih/databinding/ActivityBantuanBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivityBantuanBinding;", "setBinding", "(Lid/go/bpsfasih/databinding/ActivityBantuanBinding;)V", "periodeList", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "getPeriodeList", "()Ljava/util/List;", "setPeriodeList", "(Ljava/util/List;)V", "viewModel", "Lid/go/bpsfasih/ui/bantuan/BantuanViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/bantuan/BantuanViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/bantuan/BantuanViewModel;)V", "initListener", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "transformData", "inputList", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BantuanActivity extends BaseClassActivityNew {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String assignmentId = "";
    public ActivityBantuanBinding binding;
    private List<PeriodeEntityNew> periodeList;
    public BantuanViewModel viewModel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

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

    public final ActivityBantuanBinding getBinding() {
        ActivityBantuanBinding activityBantuanBinding = this.binding;
        if (activityBantuanBinding != null) {
            return activityBantuanBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivityBantuanBinding activityBantuanBinding) {
        Intrinsics.checkNotNullParameter(activityBantuanBinding, "<set-?>");
        this.binding = activityBantuanBinding;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final void setAssignmentId(String str) {
        this.assignmentId = str;
    }

    public final BantuanViewModel getViewModel() {
        BantuanViewModel bantuanViewModel = this.viewModel;
        if (bantuanViewModel != null) {
            return bantuanViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(BantuanViewModel bantuanViewModel) {
        Intrinsics.checkNotNullParameter(bantuanViewModel, "<set-?>");
        this.viewModel = bantuanViewModel;
    }

    /* compiled from: BantuanActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/ui/bantuan/BantuanActivity$Companion;", "", "()V", "startActivity", "", "context", "Landroid/content/Context;", "assignmentId", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context, String assignmentId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) BantuanActivity.class);
            if (assignmentId != null) {
                intent.putExtra(PusatBantuanActivity.INSTANCE.getASSIGNMENT_ID(), assignmentId);
            }
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    public final List<PeriodeEntityNew> getPeriodeList() {
        return this.periodeList;
    }

    public final void setPeriodeList(List<PeriodeEntityNew> list) {
        this.periodeList = list;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBantuanBinding activityBantuanBindingInflate = ActivityBantuanBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBantuanBindingInflate, "inflate(layoutInflater)");
        setBinding(activityBantuanBindingInflate);
        this.assignmentId = getIntent().getStringExtra(PusatBantuanActivity.INSTANCE.getASSIGNMENT_ID());
        setContentView(getBinding().getRoot());
        setStatusBarColor();
        BaseClassActivityNew.setAppBar$default(this, 0, "Pusat Bantuan", null, null, null, 24, null);
        ViewModelProvider.AndroidViewModelFactory.Companion companion = ViewModelProvider.AndroidViewModelFactory.INSTANCE;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "application");
        setViewModel((BantuanViewModel) new ViewModelProvider(this, companion.getInstance(application)).get(BantuanViewModel.class));
        this.periodeList = getViewModel().getAllPeriode();
        initView();
        initListener();
    }

    public final String transformData(List<PeriodeEntityNew> inputList) {
        ArrayList arrayList;
        String str;
        if (inputList != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : inputList) {
                String id2 = ((PeriodeEntityNew) obj).getSurvey().getId();
                Object obj2 = linkedHashMap.get(id2);
                if (obj2 == null) {
                    obj2 = (List) new ArrayList();
                    linkedHashMap.put(id2, obj2);
                }
                ((List) obj2).add(obj);
            }
            ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                String str2 = (String) entry.getKey();
                List list = (List) entry.getValue();
                List list2 = list;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (true) {
                    str = "";
                    if (!it.hasNext()) {
                        break;
                    }
                    PeriodeEntityNew periodeEntityNew = (PeriodeEntityNew) it.next();
                    String id3 = periodeEntityNew.getId();
                    String name = periodeEntityNew.getName();
                    if (name != null) {
                        str = name;
                    }
                    arrayList3.add(new Periode(id3, str));
                }
                ArrayList arrayList4 = arrayList3;
                String name2 = ((PeriodeEntityNew) CollectionsKt.first(list)).getSurvey().getName();
                if (name2 != null) {
                    str = name2;
                }
                arrayList2.add(new SurveyWithPeriods(str2, str, arrayList4));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        String json = new Gson().toJson(arrayList);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(transformedData)");
        return json;
    }

    private final void initView() {
        TextView textView = getBinding().namaTv;
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME());
        if (sessionString == null) {
            sessionString = "";
        }
        textView.setText("Halo " + sessionString + ".");
    }

    private final void initListener() {
        getBinding().mulaiPercakapanB.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.bantuan.BantuanActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BantuanActivity.initListener$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [id.go.bpsfasih.ui.bantuan.BantuanActivity$initListener$1$pusatBantuan$1] */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.lang.String] */
    public static final void initListener$lambda$4(final BantuanActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("bantuan")) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ID());
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME());
            CharSequence charSequence = (CharSequence) objectRef.element;
            if (charSequence == null || charSequence.length() == 0) {
                String string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
                ?? lowerCase = string.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                objectRef.element = lowerCase;
                objectRef2.element = "Anonymous";
                FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_ID(), (String) objectRef.element);
                FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_USERNAME(), (String) objectRef2.element);
            }
            final ?? r2 = new PusatBantuan() { // from class: id.go.bpsfasih.ui.bantuan.BantuanActivity$initListener$1$pusatBantuan$1
                @Override // id.go.bpsfasih.pusatbantuan.PusatBantuan
                public void sendFile(String assignmentId, String ticketId, final Function2<? super Boolean, ? super String, Unit> callback) {
                    Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
                    Intrinsics.checkNotNullParameter(ticketId, "ticketId");
                    Intrinsics.checkNotNullParameter(callback, "callback");
                    AssignmentEntity assignmentEntity = (AssignmentEntity) BuildersKt__BuildersKt.runBlocking$default(null, new BantuanActivity$initListener$1$pusatBantuan$1$sendFile$assignment$1(assignmentId, null), 1, null);
                    if (assignmentEntity != null) {
                        FileHelper.Companion companion = FileHelper.INSTANCE;
                        String surveyIdNotPrimary = assignmentEntity.getSurveyIdNotPrimary();
                        if (surveyIdNotPrimary == null) {
                            surveyIdNotPrimary = "";
                        }
                        String periodeNotPrimary = assignmentEntity.getPeriodeNotPrimary();
                        File file = new File(companion.pathAnswerAssignment(surveyIdNotPrimary, periodeNotPrimary != null ? periodeNotPrimary : "", assignmentId) + "/data.json");
                        if (file.exists()) {
                            new TicketRepositoryImpl().sendFileAssignmentBantuan(ticketId, MultipartBody.Part.INSTANCE.createFormData("backup", file.getName(), RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("multipart/form-data"), file)), new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.ui.bantuan.BantuanActivity$initListener$1$pusatBantuan$1$sendFile$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
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
                                        Function2<Boolean, String, Unit> function2 = callback;
                                        Boolean success = baseResponse.getSuccess();
                                        Intrinsics.checkNotNull(success);
                                        String message = baseResponse.getMessage();
                                        Intrinsics.checkNotNull(message);
                                        function2.invoke(success, message);
                                        return;
                                    }
                                    callback.invoke(false, "Gagal mengirim data");
                                }
                            });
                            return;
                        } else {
                            callback.invoke(false, "Tidak terdapat file pada assignment berikut");
                            return;
                        }
                    }
                    callback.invoke(false, "Tidak terdapat data assignment");
                }
            };
            try {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { // from class: id.go.bpsfasih.ui.bantuan.BantuanActivity$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        BantuanActivity.initListener$lambda$4$lambda$3(this.f$0, objectRef2, objectRef, r2, task);
                    }
                });
                return;
            } catch (Exception unused) {
                PusatBantuanActivity.Companion companion = PusatBantuanActivity.INSTANCE;
                Context applicationContext = this$0.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                companion.startActivity(applicationContext, (String) objectRef2.element, (String) objectRef.element, FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ACCESS_TOKEN()), new Gson().toJson(DeviceInfoHelper.INSTANCE.getDeviceInfoBantuan()).toString(), null, this$0.assignmentId, Boolean.valueOf(Intrinsics.areEqual(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD)), this$0.transformData(this$0.periodeList), (PusatBantuan) r2);
                return;
            }
        }
        Toast.makeText(this$0.getApplicationContext(), "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void initListener$lambda$4$lambda$3(BantuanActivity this$0, Ref.ObjectRef userName, Ref.ObjectRef userId, BantuanActivity$initListener$1$pusatBantuan$1 pusatBantuan, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(userName, "$userName");
        Intrinsics.checkNotNullParameter(userId, "$userId");
        Intrinsics.checkNotNullParameter(pusatBantuan, "$pusatBantuan");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String str = (String) task.getResult();
            PusatBantuanActivity.Companion companion = PusatBantuanActivity.INSTANCE;
            Context applicationContext = this$0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            companion.startActivity(applicationContext, (String) userName.element, (String) userId.element, FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ACCESS_TOKEN()), new Gson().toJson(DeviceInfoHelper.INSTANCE.getDeviceInfoBantuan()).toString(), str, this$0.assignmentId, Boolean.valueOf(Intrinsics.areEqual(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD)), this$0.transformData(this$0.periodeList), pusatBantuan);
        }
    }
}
