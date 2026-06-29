package id.go.bpsfasih.domain.usecase;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: SaveLocationUseCase.kt */
@Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.domain.usecase.SaveLocationUseCase", f = "SaveLocationUseCase.kt", i = {}, l = {30}, m = "invoke-tZkwj4A", n = {}, s = {})
/* loaded from: classes2.dex */
final class SaveLocationUseCase$invoke$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SaveLocationUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SaveLocationUseCase$invoke$1(SaveLocationUseCase saveLocationUseCase, Continuation<? super SaveLocationUseCase$invoke$1> continuation) {
        super(continuation);
        this.this$0 = saveLocationUseCase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6677invoketZkwj4A = this.this$0.m6677invoketZkwj4A(null, null, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, null, null, null, null, this);
        return objM6677invoketZkwj4A == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM6677invoketZkwj4A : Result.m6851boximpl(objM6677invoketZkwj4A);
    }
}
