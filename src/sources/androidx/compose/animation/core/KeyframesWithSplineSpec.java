package androidx.compose.animation.core;

import androidx.compose.animation.core.KeyframesSpec;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: AnimationSpec.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000eB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J,\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0001\u0010\n*\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\n0\rH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/animation/core/KeyframesWithSplineSpec;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "config", "Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "(Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;)V", "getConfig", "()Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "vectorize", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "KeyframesWithSplineSpecConfig", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class KeyframesWithSplineSpec<T> implements DurationBasedAnimationSpec<T> {
    public static final int $stable = 0;
    private final KeyframesWithSplineSpecConfig<T> config;

    public KeyframesWithSplineSpec(KeyframesWithSplineSpecConfig<T> keyframesWithSplineSpecConfig) {
        this.config = keyframesWithSplineSpecConfig;
    }

    public final KeyframesWithSplineSpecConfig<T> getConfig() {
        return this.config;
    }

    /* compiled from: AnimationSpec.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/core/KeyframesSpecBaseConfig;", "Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "()V", "createEntityFor", "value", "createEntityFor$animation_core_release", "(Ljava/lang/Object;)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class KeyframesWithSplineSpecConfig<T> extends KeyframesSpecBaseConfig<T, KeyframesSpec.KeyframeEntity<T>> {
        public static final int $stable = 0;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public /* bridge */ /* synthetic */ KeyframeBaseEntity createEntityFor$animation_core_release(Object obj) {
            return createEntityFor$animation_core_release((KeyframesWithSplineSpecConfig<T>) obj);
        }

        public KeyframesWithSplineSpecConfig() {
            super(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframesSpec.KeyframeEntity<T> createEntityFor$animation_core_release(T value) {
            return new KeyframesSpec.KeyframeEntity<>(value, null, 2, 0 == true ? 1 : 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0070  */
    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <V extends androidx.compose.animation.core.AnimationVector> androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec<V> vectorize(androidx.compose.animation.core.TwoWayConverter<T, V> r20) {
        /*
            r19 = this;
            r0 = r19
            androidx.collection.MutableIntList r1 = new androidx.collection.MutableIntList
            r2 = 0
            r3 = 1
            r4 = 0
            r1.<init>(r2, r3, r4)
            androidx.collection.MutableIntObjectMap r5 = new androidx.collection.MutableIntObjectMap
            r5.<init>(r2, r3, r4)
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r4 = r0.config
            androidx.collection.MutableIntObjectMap r4 = r4.getKeyframes$animation_core_release()
            androidx.collection.IntObjectMap r4 = (androidx.collection.IntObjectMap) r4
            int[] r6 = r4.keys
            java.lang.Object[] r7 = r4.values
            long[] r4 = r4.metadata
            int r8 = r4.length
            int r8 = r8 + (-2)
            if (r8 < 0) goto L77
            r9 = r2
        L23:
            r10 = r4[r9]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L70
            int r12 = r9 - r8
            int r12 = ~r12
            int r12 = r12 >>> 31
            r13 = 8
            int r12 = 8 - r12
            r14 = r2
        L3d:
            if (r14 >= r12) goto L6e
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r10
            r17 = 128(0x80, double:6.32E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L4a
            r15 = r3
            goto L4b
        L4a:
            r15 = r2
        L4b:
            if (r15 == 0) goto L68
            int r15 = r9 << 3
            int r15 = r15 + r14
            r2 = r6[r15]
            r15 = r7[r15]
            androidx.compose.animation.core.KeyframesSpec$KeyframeEntity r15 = (androidx.compose.animation.core.KeyframesSpec.KeyframeEntity) r15
            r1.add(r2)
            kotlin.jvm.functions.Function1 r3 = r20.getConvertToVector()
            java.lang.Object r15 = r15.getValue$animation_core_release()
            java.lang.Object r3 = r3.invoke(r15)
            r5.set(r2, r3)
        L68:
            long r10 = r10 >> r13
            int r14 = r14 + 1
            r2 = 0
            r3 = 1
            goto L3d
        L6e:
            if (r12 != r13) goto L77
        L70:
            if (r9 == r8) goto L77
            int r9 = r9 + 1
            r2 = 0
            r3 = 1
            goto L23
        L77:
            r1.sort()
            androidx.compose.animation.core.VectorizedMonoSplineKeyframesSpec r2 = new androidx.compose.animation.core.VectorizedMonoSplineKeyframesSpec
            androidx.collection.IntList r1 = (androidx.collection.IntList) r1
            androidx.collection.IntObjectMap r5 = (androidx.collection.IntObjectMap) r5
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r3 = r0.config
            int r3 = r3.getDurationMillis()
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r4 = r0.config
            int r4 = r4.getDelayMillis()
            r2.<init>(r1, r5, r3, r4)
            androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec r2 = (androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.KeyframesWithSplineSpec.vectorize(androidx.compose.animation.core.TwoWayConverter):androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec");
    }
}
