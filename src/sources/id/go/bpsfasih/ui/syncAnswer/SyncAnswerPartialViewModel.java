package id.go.bpsfasih.ui.syncAnswer;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.domain.models.SyncAnswerPartial;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: SyncAnswerPartialViewModel.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010:\u001a\u00020;J\u0006\u0010<\u001a\u00020;J\u000e\u0010=\u001a\u00020;2\u0006\u0010>\u001a\u00020\fRJ\u0010\t\u001a2\u0012,\u0012*\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u000bj\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0007`\r\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\u001a\u0010(\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001c\"\u0004\b*\u0010\u001eR\"\u0010+\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u00101\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R\"\u00104\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010.\"\u0004\b6\u00100R\"\u00107\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010.\"\u0004\b9\u00100¨\u0006?"}, d2 = {"Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "periodeIdParam", "", "listAssignmentParam", "", "(Landroid/app/Application;Ljava/lang/String;Ljava/util/List;)V", "assignmentMap", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/HashMap;", "Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "Lkotlin/collections/HashMap;", "getAssignmentMap", "()Landroidx/lifecycle/MutableLiveData;", "setAssignmentMap", "(Landroidx/lifecycle/MutableLiveData;)V", "clickSelesai", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getClickSelesai", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setClickSelesai", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "jumlahAssignment", "", "getJumlahAssignment", "()I", "setJumlahAssignment", "(I)V", "periodeList", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "getPeriodeList", "()Ljava/util/List;", "setPeriodeList", "(Ljava/util/List;)V", "progress", "getProgress", "setProgress", "totalProgress", "getTotalProgress", "setTotalProgress", "visibilityButtonTutup", "Landroidx/databinding/ObservableField;", "getVisibilityButtonTutup", "()Landroidx/databinding/ObservableField;", "setVisibilityButtonTutup", "(Landroidx/databinding/ObservableField;)V", "visibilityButtonUnduh", "getVisibilityButtonUnduh", "setVisibilityButtonUnduh", "visibilityLayoutMulai", "getVisibilityLayoutMulai", "setVisibilityLayoutMulai", "visibilityLayoutSelesai", "getVisibilityLayoutSelesai", "setVisibilityLayoutSelesai", "finish", "", "unduhMulai", "unduhUlang", "unit", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SyncAnswerPartialViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap;
    private SingleLiveEvent<Boolean> clickSelesai;
    private int jumlahAssignment;
    private List<PeriodeEntityNew> periodeList;
    private int progress;
    private int totalProgress;
    private ObservableField<Integer> visibilityButtonTutup;
    private ObservableField<Integer> visibilityButtonUnduh;
    private ObservableField<Integer> visibilityLayoutMulai;
    private ObservableField<Integer> visibilityLayoutSelesai;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.util.List] */
    public SyncAnswerPartialViewModel(Application application, String str, List<String> list) throws InterruptedException {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.visibilityLayoutMulai = new ObservableField<>(0);
        this.visibilityLayoutSelesai = new ObservableField<>(8);
        this.visibilityButtonTutup = new ObservableField<>(8);
        this.visibilityButtonUnduh = new ObservableField<>(8);
        this.clickSelesai = new SingleLiveEvent<>();
        this.assignmentMap = new MutableLiveData<>();
        HashMap map = new HashMap();
        if (str == null) {
            this.periodeList = (List) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(null), 1, null);
        } else {
            this.periodeList = CollectionsKt.listOf(BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass2(str, null), 1, null));
        }
        List<PeriodeEntityNew> list2 = this.periodeList;
        if (list2 != null) {
            for (PeriodeEntityNew periodeEntityNew : list2) {
                String id2 = periodeEntityNew.getId();
                String id3 = periodeEntityNew.getSurvey().getId();
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = new ArrayList();
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = new ArrayList();
                BuildersKt__BuildersKt.runBlocking$default(null, new SyncAnswerPartialViewModel$3$1(list, objectRef2, this, objectRef, id3, id2, null), 1, null);
                this.totalProgress += ((ArrayList) objectRef.element).size();
                map.put(id2, objectRef.element);
            }
        }
        LiveData liveData = this.assignmentMap;
        if (liveData != null) {
            liveData.postValue(map);
        }
    }

    public final List<PeriodeEntityNew> getPeriodeList() {
        return this.periodeList;
    }

    public final void setPeriodeList(List<PeriodeEntityNew> list) {
        this.periodeList = list;
    }

    public final MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> getAssignmentMap() {
        return this.assignmentMap;
    }

    public final void setAssignmentMap(MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> mutableLiveData) {
        this.assignmentMap = mutableLiveData;
    }

    public final int getJumlahAssignment() {
        return this.jumlahAssignment;
    }

    public final void setJumlahAssignment(int i) {
        this.jumlahAssignment = i;
    }

    public final int getTotalProgress() {
        return this.totalProgress;
    }

    public final void setTotalProgress(int i) {
        this.totalProgress = i;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final void setProgress(int i) {
        this.progress = i;
    }

    public final ObservableField<Integer> getVisibilityLayoutMulai() {
        return this.visibilityLayoutMulai;
    }

    public final void setVisibilityLayoutMulai(ObservableField<Integer> observableField) {
        this.visibilityLayoutMulai = observableField;
    }

    public final ObservableField<Integer> getVisibilityLayoutSelesai() {
        return this.visibilityLayoutSelesai;
    }

    public final void setVisibilityLayoutSelesai(ObservableField<Integer> observableField) {
        this.visibilityLayoutSelesai = observableField;
    }

    public final ObservableField<Integer> getVisibilityButtonTutup() {
        return this.visibilityButtonTutup;
    }

    public final void setVisibilityButtonTutup(ObservableField<Integer> observableField) {
        this.visibilityButtonTutup = observableField;
    }

    public final ObservableField<Integer> getVisibilityButtonUnduh() {
        return this.visibilityButtonUnduh;
    }

    public final void setVisibilityButtonUnduh(ObservableField<Integer> observableField) {
        this.visibilityButtonUnduh = observableField;
    }

    public final SingleLiveEvent<Boolean> getClickSelesai() {
        return this.clickSelesai;
    }

    public final void setClickSelesai(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.clickSelesai = singleLiveEvent;
    }

    /* compiled from: SyncAnswerPartialViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel$1", f = "SyncAnswerPartialViewModel.kt", i = {}, l = {55}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PeriodeEntityNew>>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PeriodeEntityNew>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PeriodeEntityNew>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PeriodeEntityNew>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DataSurvey.Periode.INSTANCE.getPeriodeRepository().getPeriodeByUserId(this);
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

    /* compiled from: SyncAnswerPartialViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel$2", f = "SyncAnswerPartialViewModel.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PeriodeEntityNew>, Object> {
        final /* synthetic */ String $periodeIdParam;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$periodeIdParam = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$periodeIdParam, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PeriodeEntityNew> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DataSurvey.Periode.INSTANCE.getPeriodeRepository().getPeriodeByPrimaryId(this.$periodeIdParam, this);
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

    /* JADX WARN: Multi-variable type inference failed */
    public final void unduhMulai() {
        ObservableField<Integer> observableField = this.visibilityLayoutMulai;
        if (observableField != null) {
            observableField.set(8);
        }
        ObservableField<Integer> observableField2 = this.visibilityLayoutSelesai;
        if (observableField2 != null) {
            observableField2.set(0);
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> mutableLiveData = this.assignmentMap;
        objectRef.element = mutableLiveData != null ? mutableLiveData.getValue() : 0;
        HashMap map = (HashMap) objectRef.element;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                Iterator it = ((Iterable) entry.getValue()).iterator();
                while (it.hasNext()) {
                    BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new SyncAnswerPartialViewModel$unduhMulai$1$1$1((SyncAnswerPartial) it.next(), entry, objectRef, this, null), 2, null);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v5, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v6, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v0, types: [T, java.lang.Integer] */
    public final void unduhUlang(SyncAnswerPartial unit) {
        List list;
        Intrinsics.checkNotNullParameter(unit, "unit");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> mutableLiveData = this.assignmentMap;
        SyncAnswerPartial syncAnswerPartial = null;
        objectRef.element = mutableLiveData != null ? mutableLiveData.getValue() : 0;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = "";
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = 0;
        HashMap map = (HashMap) objectRef.element;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                for (SyncAnswerPartial syncAnswerPartial2 : (Iterable) entry.getValue()) {
                    int size = ((List) entry.getValue()).size();
                    for (int i = 0; i < size; i++) {
                        if (((SyncAnswerPartial) ((List) entry.getValue()).get(i)).equals(unit)) {
                            objectRef2.element = entry.getKey();
                            objectRef3.element = Integer.valueOf(i);
                        }
                    }
                }
            }
        }
        HashMap map2 = (HashMap) objectRef.element;
        if (map2 != null && (list = (List) map2.get(objectRef2.element)) != null) {
            T t = objectRef3.element;
            Intrinsics.checkNotNull(t);
            syncAnswerPartial = (SyncAnswerPartial) list.get(((Number) t).intValue());
        }
        if (syncAnswerPartial != null) {
            syncAnswerPartial.setProgress(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        }
        LiveData liveData = this.assignmentMap;
        if (liveData != null) {
            liveData.postValue(objectRef.element);
        }
        new RDAnswerPartial(unit, new Function3<String, Boolean, Double, Unit>() { // from class: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel.unduhUlang.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool, Double d) {
                invoke(str, bool.booleanValue(), d.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, boolean z, double d) {
                MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap;
                List<SyncAnswerPartial> list2;
                MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap2;
                List<SyncAnswerPartial> list3;
                SyncAnswerPartial syncAnswerPartial3 = null;
                if (!z) {
                    HashMap<String, List<SyncAnswerPartial>> map3 = objectRef.element;
                    if (map3 != null && (list3 = map3.get(objectRef2.element)) != null) {
                        Integer num = objectRef3.element;
                        Intrinsics.checkNotNull(num);
                        syncAnswerPartial3 = list3.get(num.intValue());
                    }
                    if (syncAnswerPartial3 != null) {
                        syncAnswerPartial3.setProgress(Double.valueOf(d));
                    }
                    if (MathKt.roundToInt(d) % 5 == 0 && (assignmentMap2 = this.getAssignmentMap()) != null) {
                        assignmentMap2.postValue(objectRef.element);
                    }
                    if (d == 100.0d) {
                        SyncAnswerPartialViewModel syncAnswerPartialViewModel = this;
                        syncAnswerPartialViewModel.setProgress(syncAnswerPartialViewModel.getProgress() + 1);
                    }
                    if (this.getTotalProgress() == this.getProgress()) {
                        this.finish();
                        return;
                    }
                    return;
                }
                HashMap<String, List<SyncAnswerPartial>> map4 = objectRef.element;
                if (map4 != null && (list2 = map4.get(objectRef2.element)) != null) {
                    Integer num2 = objectRef3.element;
                    Intrinsics.checkNotNull(num2);
                    syncAnswerPartial3 = list2.get(num2.intValue());
                }
                if (syncAnswerPartial3 != null) {
                    syncAnswerPartial3.setProgress(Double.valueOf(d));
                }
                if (MathKt.roundToInt(d) % 5 != 0 || (assignmentMap = this.getAssignmentMap()) == null) {
                    return;
                }
                assignmentMap.postValue(objectRef.element);
            }
        });
    }

    public final void finish() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.clickSelesai;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }
}
