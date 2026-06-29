package id.go.bpsfasih.ui.splash;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashActivity.kt */
@Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ComposableSingletons$SplashActivityKt {
    public static final ComposableSingletons$SplashActivityKt INSTANCE = new ComposableSingletons$SplashActivityKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<AnimatedVisibilityScope, Composer, Integer, Unit> f60lambda1 = ComposableLambdaKt.composableLambdaInstance(-1233729670, false, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.ComposableSingletons$SplashActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
            invoke(animatedVisibilityScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1233729670, i, -1, "id.go.bpsfasih.ui.splash.ComposableSingletons$SplashActivityKt.lambda-1.<anonymous> (SplashActivity.kt:288)");
            }
            SplashActivityKt.ElegantLoadingAnimation(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f61lambda2 = ComposableLambdaKt.composableLambdaInstance(2010151550, false, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.ComposableSingletons$SplashActivityKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2010151550, i, -1, "id.go.bpsfasih.ui.splash.ComposableSingletons$SplashActivityKt.lambda-2.<anonymous> (SplashActivity.kt:477)");
            }
            SplashActivityKt.ElegantAnimatedSplashScreen(new Function0<Unit>() { // from class: id.go.bpsfasih.ui.splash.ComposableSingletons$SplashActivityKt$lambda-2$1.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            }, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function3<AnimatedVisibilityScope, Composer, Integer, Unit> m6821getLambda1$app_release() {
        return f60lambda1;
    }

    /* renamed from: getLambda-2$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6822getLambda2$app_release() {
        return f61lambda2;
    }
}
