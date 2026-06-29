package id.go.bpsfasih.ui.formGear;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.local.repository.PeriodeRepository;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: FormGearJavascriptInterface.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getUserRole$rolePetugas$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {2628}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormGearJavascriptInterface$getUserRole$rolePetugas$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    int label;
    final /* synthetic */ FormGearJavascriptInterface this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FormGearJavascriptInterface$getUserRole$rolePetugas$1(FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super FormGearJavascriptInterface$getUserRole$rolePetugas$1> continuation) {
        super(2, continuation);
        this.this$0 = formGearJavascriptInterface;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormGearJavascriptInterface$getUserRole$rolePetugas$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((FormGearJavascriptInterface$getUserRole$rolePetugas$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UserRole userRole;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PeriodeRepository periodeRepository = DataSurvey.Periode.INSTANCE.getPeriodeRepository();
            FormGearActivity formGearActivity = this.this$0.activity;
            Intrinsics.checkNotNull(formGearActivity);
            String mPeriodeId = formGearActivity.getMPeriodeId();
            Intrinsics.checkNotNull(mPeriodeId);
            String userId = FasihApp.INSTANCE.getSession().getUserId();
            this.label = 1;
            obj = periodeRepository.getPeriodeByPrimaryId(mPeriodeId + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        List<UserRole> role = ((PeriodeEntityNew) obj).getRole();
        if (role == null || (userRole = role.get(0)) == null) {
            return null;
        }
        return userRole.getDescription();
    }
}
