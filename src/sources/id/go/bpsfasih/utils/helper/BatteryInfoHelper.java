package id.go.bpsfasih.utils.helper;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wangjie.rapidfloatingactionbutton.constants.RFABConstants;
import id.go.bpsfasih.domain.models.BatteryInfo;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: BatteryInfoHelper.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0007\u001a\u00020\bJ\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/utils/helper/BatteryInfoHelper;", "", "()V", "collectBatteryInfo", "Lkotlin/Pair;", "", "", "context", "Landroid/content/Context;", "getPerformanceMetrics", "Lid/go/bpsfasih/domain/models/BatteryInfo;", "getPerformanceMetricsAsync", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPerformanceMetricsJson", "", "getPerformanceMetricsJsonAsync", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BatteryInfoHelper {
    public static final int $stable = 0;
    public static final BatteryInfoHelper INSTANCE = new BatteryInfoHelper();

    /* compiled from: BatteryInfoHelper.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.helper.BatteryInfoHelper", f = "BatteryInfoHelper.kt", i = {}, l = {RFABConstants.SIZE.RFAB_NORMAL_SIZE_DP}, m = "getPerformanceMetricsJsonAsync", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.helper.BatteryInfoHelper$getPerformanceMetricsJsonAsync$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BatteryInfoHelper.this.getPerformanceMetricsJsonAsync(null, this);
        }
    }

    private BatteryInfoHelper() {
    }

    public final BatteryInfo getPerformanceMetrics(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            BatteryInfo batteryInfo = new BatteryInfo(null, null, 3, null);
            Pair<Integer, Float> pairCollectBatteryInfo = collectBatteryInfo(context);
            batteryInfo.setBatteryLevel(pairCollectBatteryInfo.getFirst());
            batteryInfo.setBatteryTemperature(pairCollectBatteryInfo.getSecond());
            return batteryInfo;
        } catch (Exception unused) {
            return null;
        }
    }

    public final String getPerformanceMetricsJson(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            BatteryInfo performanceMetrics = getPerformanceMetrics(context);
            if (performanceMetrics != null) {
                return performanceMetrics.toJsonString();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* compiled from: BatteryInfoHelper.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/domain/models/BatteryInfo;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.helper.BatteryInfoHelper$getPerformanceMetricsAsync$2", f = "BatteryInfoHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.helper.BatteryInfoHelper$getPerformanceMetricsAsync$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BatteryInfo>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super BatteryInfo> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                BatteryInfo batteryInfo = new BatteryInfo(null, null, 3, null);
                Pair pairCollectBatteryInfo = BatteryInfoHelper.INSTANCE.collectBatteryInfo(this.$context);
                batteryInfo.setBatteryLevel((Integer) pairCollectBatteryInfo.getFirst());
                batteryInfo.setBatteryTemperature((Float) pairCollectBatteryInfo.getSecond());
                return batteryInfo;
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public final Object getPerformanceMetricsAsync(Context context, Continuation<? super BatteryInfo> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(context, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPerformanceMetricsJsonAsync(android.content.Context r6, kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof id.go.bpsfasih.utils.helper.BatteryInfoHelper.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r7
            id.go.bpsfasih.utils.helper.BatteryInfoHelper$getPerformanceMetricsJsonAsync$1 r0 = (id.go.bpsfasih.utils.helper.BatteryInfoHelper.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            id.go.bpsfasih.utils.helper.BatteryInfoHelper$getPerformanceMetricsJsonAsync$1 r0 = new id.go.bpsfasih.utils.helper.BatteryInfoHelper$getPerformanceMetricsJsonAsync$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L33
            if (r2 != r4) goto L2b
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L49
            goto L3f
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L33:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.label = r4     // Catch: java.lang.Exception -> L49
            java.lang.Object r7 = r5.getPerformanceMetricsAsync(r6, r0)     // Catch: java.lang.Exception -> L49
            if (r7 != r1) goto L3f
            return r1
        L3f:
            id.go.bpsfasih.domain.models.BatteryInfo r7 = (id.go.bpsfasih.domain.models.BatteryInfo) r7     // Catch: java.lang.Exception -> L49
            if (r7 == 0) goto L4c
            java.lang.String r6 = r7.toJsonString()     // Catch: java.lang.Exception -> L49
            r3 = r6
            goto L4c
        L49:
            r6 = r3
            java.lang.String r6 = (java.lang.String) r6
        L4c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.helper.BatteryInfoHelper.getPerformanceMetricsJsonAsync(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Integer, Float> collectBatteryInfo(Context context) {
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1) : -1;
            int intExtra2 = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("scale", -1) : -1;
            int intExtra3 = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("temperature", -1) : -1;
            return new Pair<>((intExtra == -1 || intExtra2 == -1) ? null : Integer.valueOf((int) ((intExtra * 100) / intExtra2)), intExtra3 != -1 ? Float.valueOf(intExtra3 / 10.0f) : null);
        } catch (Exception unused) {
            return new Pair<>(null, null);
        }
    }
}
