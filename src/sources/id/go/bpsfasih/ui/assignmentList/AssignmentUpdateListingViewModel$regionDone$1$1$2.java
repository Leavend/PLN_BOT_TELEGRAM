package id.go.bpsfasih.ui.assignmentList;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.data.local.repository.AssignmentRegionRepository;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1$1$2;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AssignmentUpdateListingViewModel.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/data/remote/dto/AssignmentRegionResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class AssignmentUpdateListingViewModel$regionDone$1$1$2 extends Lambda implements Function1<AssignmentRegionResponse, Unit> {
    final /* synthetic */ Ref.ObjectRef<AssignmentRegionRepository> $repoAssignmentRegion;
    final /* synthetic */ AssignmentUpdateListingViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssignmentUpdateListingViewModel$regionDone$1$1$2(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, Ref.ObjectRef<AssignmentRegionRepository> objectRef) {
        super(1);
        this.this$0 = assignmentUpdateListingViewModel;
        this.$repoAssignmentRegion = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
        invoke2(assignmentRegionResponse);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
        AssignmentListActivity activity = this.this$0.getActivity();
        final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.this$0;
        activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1$1$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentUpdateListingViewModel$regionDone$1$1$2.invoke$lambda$0(assignmentUpdateListingViewModel);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass2(assignmentRegionResponse, this.$repoAssignmentRegion, this.this$0, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(AssignmentUpdateListingViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getActivity().hideProgressBar();
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1$1$2$2", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1$1$2$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AssignmentRegionResponse $it;
        final /* synthetic */ Ref.ObjectRef<AssignmentRegionRepository> $repoAssignmentRegion;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AssignmentUpdateListingViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(AssignmentRegionResponse assignmentRegionResponse, Ref.ObjectRef<AssignmentRegionRepository> objectRef, AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$it = assignmentRegionResponse;
            this.$repoAssignmentRegion = objectRef;
            this.this$0 = assignmentUpdateListingViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$it, this.$repoAssignmentRegion, this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Unit unit;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final AssignmentRegionResponse assignmentRegionResponse = this.$it;
            if (assignmentRegionResponse != null) {
                Ref.ObjectRef<AssignmentRegionRepository> objectRef = this.$repoAssignmentRegion;
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.this$0;
                if (Intrinsics.areEqual(assignmentRegionResponse.getSuccess(), Boxing.boxBoolean(true))) {
                    objectRef.element.insertAll(assignmentRegionResponse.getData(), new AssignmentUpdateListingViewModel$regionDone$1$1$2$2$1$1(assignmentUpdateListingViewModel));
                } else {
                    assignmentUpdateListingViewModel.getActivity().runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1$1$2$2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            AssignmentUpdateListingViewModel$regionDone$1$1$2.AnonymousClass2.invokeSuspend$lambda$1$lambda$0(assignmentUpdateListingViewModel, assignmentRegionResponse);
                        }
                    });
                }
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = this.this$0;
                assignmentUpdateListingViewModel2.getActivity().runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1$1$2$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel$regionDone$1$1$2.AnonymousClass2.invokeSuspend$lambda$3$lambda$2(assignmentUpdateListingViewModel2);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1$lambda$0(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, AssignmentRegionResponse assignmentRegionResponse) {
            BaseClassActivityNew.showAlertDialog$default(assignmentUpdateListingViewModel.getActivity(), "info", assignmentRegionResponse.getMessage(), null, "Ok", null, null, null, false, false, 384, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$3$lambda$2(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            BaseClassActivityNew.showAlertDialog$default(assignmentUpdateListingViewModel.getActivity(), "info", "Gagal mengirim data region ini", null, "Ok", null, null, null, false, false, 384, null);
        }
    }
}
