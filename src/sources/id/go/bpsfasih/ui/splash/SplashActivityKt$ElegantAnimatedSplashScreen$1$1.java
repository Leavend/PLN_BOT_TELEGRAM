package id.go.bpsfasih.ui.splash;

import androidx.compose.runtime.MutableState;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SplashActivity.kt */
@Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.splash.SplashActivityKt$ElegantAnimatedSplashScreen$1$1", f = "SplashActivity.kt", i = {}, l = {195, 197, 199, 201}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class SplashActivityKt$ElegantAnimatedSplashScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $loadingVisible$delegate;
    final /* synthetic */ MutableState<Boolean> $logoVisible$delegate;
    final /* synthetic */ Function0<Unit> $onNavigationReady;
    final /* synthetic */ MutableState<Boolean> $startAnimation$delegate;
    final /* synthetic */ MutableState<Boolean> $textVisible$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SplashActivityKt$ElegantAnimatedSplashScreen$1$1(Function0<Unit> function0, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, Continuation<? super SplashActivityKt$ElegantAnimatedSplashScreen$1$1> continuation) {
        super(2, continuation);
        this.$onNavigationReady = function0;
        this.$startAnimation$delegate = mutableState;
        this.$logoVisible$delegate = mutableState2;
        this.$textVisible$delegate = mutableState3;
        this.$loadingVisible$delegate = mutableState4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SplashActivityKt$ElegantAnimatedSplashScreen$1$1(this.$onNavigationReady, this.$startAnimation$delegate, this.$logoVisible$delegate, this.$textVisible$delegate, this.$loadingVisible$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SplashActivityKt$ElegantAnimatedSplashScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007a A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L2c
            if (r1 == r5) goto L28
            if (r1 == r4) goto L24
            if (r1 == r3) goto L20
            if (r1 != r2) goto L18
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7b
        L18:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L20:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L68
        L24:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L55
        L28:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L42
        L2c:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$startAnimation$delegate
            id.go.bpsfasih.ui.splash.SplashActivityKt.access$ElegantAnimatedSplashScreen$lambda$2(r9, r5)
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.label = r5
            r6 = 300(0x12c, double:1.48E-321)
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r6, r9)
            if (r9 != r0) goto L42
            return r0
        L42:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$logoVisible$delegate
            id.go.bpsfasih.ui.splash.SplashActivityKt.access$ElegantAnimatedSplashScreen$lambda$5(r9, r5)
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.label = r4
            r6 = 1500(0x5dc, double:7.41E-321)
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r6, r9)
            if (r9 != r0) goto L55
            return r0
        L55:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$textVisible$delegate
            id.go.bpsfasih.ui.splash.SplashActivityKt.access$ElegantAnimatedSplashScreen$lambda$8(r9, r5)
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.label = r3
            r3 = 800(0x320, double:3.953E-321)
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r3, r9)
            if (r9 != r0) goto L68
            return r0
        L68:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$loadingVisible$delegate
            id.go.bpsfasih.ui.splash.SplashActivityKt.access$ElegantAnimatedSplashScreen$lambda$11(r9, r5)
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.label = r2
            r1 = 1400(0x578, double:6.917E-321)
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r1, r9)
            if (r9 != r0) goto L7b
            return r0
        L7b:
            kotlin.jvm.functions.Function0<kotlin.Unit> r9 = r8.$onNavigationReady
            r9.invoke()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.splash.SplashActivityKt$ElegantAnimatedSplashScreen$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
