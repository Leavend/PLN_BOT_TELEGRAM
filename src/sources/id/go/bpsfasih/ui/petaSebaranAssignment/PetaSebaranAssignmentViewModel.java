package id.go.bpsfasih.ui.petaSebaranAssignment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import id.ipd.mapipd.model.ItemLoc;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: PetaSebaranAssignmentViewModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0011\u001a\u00020\u0012R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/ui/petaSebaranAssignment/PetaSebaranAssignmentViewModel;", "Landroidx/lifecycle/ViewModel;", "regionId", "", "surveyId", "periodeId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "_listLocation", "Landroidx/lifecycle/MutableLiveData;", "", "Lid/ipd/mapipd/model/ItemLoc;", "get_listLocation", "()Landroidx/lifecycle/MutableLiveData;", "getPeriodeId", "()Ljava/lang/String;", "getRegionId", "getSurveyId", "setListLocation", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PetaSebaranAssignmentViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableLiveData<List<ItemLoc>> _listLocation;
    private final String periodeId;
    private final String regionId;
    private final String surveyId;

    public final String getRegionId() {
        return this.regionId;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public PetaSebaranAssignmentViewModel(String regionId, String surveyId, String periodeId) {
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        this.regionId = regionId;
        this.surveyId = surveyId;
        this.periodeId = periodeId;
        this._listLocation = new MutableLiveData<>();
        setListLocation();
    }

    public final MutableLiveData<List<ItemLoc>> get_listLocation() {
        return this._listLocation;
    }

    /* compiled from: PetaSebaranAssignmentViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.petaSebaranAssignment.PetaSebaranAssignmentViewModel$setListLocation$1", f = "PetaSebaranAssignmentViewModel.kt", i = {0, 1}, l = {27, 33}, m = "invokeSuspend", n = {"listAssignment", "listAssignment"}, s = {"L$0", "L$0"})
    /* renamed from: id.go.bpsfasih.ui.petaSebaranAssignment.PetaSebaranAssignmentViewModel$setListLocation$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PetaSebaranAssignmentViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0095  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instructions count: 760
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.petaSebaranAssignment.PetaSebaranAssignmentViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void setListLocation() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }
}
