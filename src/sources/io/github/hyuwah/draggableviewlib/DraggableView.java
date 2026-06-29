package io.github.hyuwah.draggableviewlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DraggableView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002+,B)\b\u0002\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010\u001f\u001a\u00020\u001dJ\u000b\u0010 \u001a\u00028\u0000¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020$J\u0016\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'J\u0010\u0010)\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020$J\u0006\u0010*\u001a\u00020\u001dR$\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR(\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u0004\u001a\u00028\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001b¨\u0006-"}, d2 = {"Lio/github/hyuwah/draggableviewlib/DraggableView;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "", "targetView", "sticky", "Lio/github/hyuwah/draggableviewlib/DraggableView$Mode;", "animated", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lio/github/hyuwah/draggableviewlib/DraggableListener;", "(Landroid/view/View;Lio/github/hyuwah/draggableviewlib/DraggableView$Mode;ZLio/github/hyuwah/draggableviewlib/DraggableListener;)V", "value", "getAnimated", "()Z", "setAnimated", "(Z)V", "<set-?>", "isMinimized", "getListener", "()Lio/github/hyuwah/draggableviewlib/DraggableListener;", "setListener", "(Lio/github/hyuwah/draggableviewlib/DraggableListener;)V", "getSticky", "()Lio/github/hyuwah/draggableviewlib/DraggableView$Mode;", "setSticky", "(Lio/github/hyuwah/draggableviewlib/DraggableView$Mode;)V", "Landroid/view/View;", "disableDrag", "", "dockToEdge", "enableDrag", "getView", "()Landroid/view/View;", "hide", "durationMs", "", "setViewPosition", "x", "", "y", "show", "undock", "Builder", "Mode", "draggableviewlib_release"}, k = 1, mv = {1, 4, 1})
/* loaded from: classes2.dex */
public final class DraggableView<T extends View> {
    private boolean animated;
    private boolean isMinimized;
    private DraggableListener listener;
    private Mode sticky;
    private T targetView;

    /* compiled from: DraggableView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lio/github/hyuwah/draggableviewlib/DraggableView$Mode;", "", "(Ljava/lang/String;I)V", "NON_STICKY", "STICKY_X", "STICKY_Y", "STICKY_XY", "draggableviewlib_release"}, k = 1, mv = {1, 4, 1})
    public enum Mode {
        NON_STICKY,
        STICKY_X,
        STICKY_Y,
        STICKY_XY
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Mode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Mode.STICKY_X.ordinal()] = 1;
            iArr[Mode.STICKY_Y.ordinal()] = 2;
            int[] iArr2 = new int[Mode.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[Mode.STICKY_X.ordinal()] = 1;
            iArr2[Mode.STICKY_Y.ordinal()] = 2;
        }
    }

    private DraggableView(T t, Mode mode, boolean z, DraggableListener draggableListener) {
        this.targetView = t;
        this.sticky = Mode.NON_STICKY;
        this.animated = true;
        setSticky(mode);
        setAnimated(z);
        setListener(draggableListener);
        enableDrag();
    }

    public /* synthetic */ DraggableView(View view, Mode mode, boolean z, DraggableListener draggableListener, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, mode, z, draggableListener);
    }

    public final Mode getSticky() {
        return this.sticky;
    }

    public final void setSticky(Mode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.sticky = value;
        enableDrag();
    }

    public final boolean getAnimated() {
        return this.animated;
    }

    public final void setAnimated(boolean z) {
        this.animated = z;
        enableDrag();
    }

    public final DraggableListener getListener() {
        return this.listener;
    }

    public final void setListener(DraggableListener draggableListener) {
        this.listener = draggableListener;
        enableDrag();
    }

    /* renamed from: isMinimized, reason: from getter */
    public final boolean getIsMinimized() {
        return this.isMinimized;
    }

    public final T getView() {
        return this.targetView;
    }

    public final void setViewPosition(float x, float y) {
        this.targetView.setX(x);
        this.targetView.setY(y);
    }

    public final void disableDrag() {
        this.targetView.setOnTouchListener(null);
    }

    public final void enableDrag() {
        DraggableUtils.setupDraggable(this.targetView, this.sticky, this.animated, this.listener);
    }

    public static /* synthetic */ void show$default(DraggableView draggableView, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 300;
        }
        draggableView.show(i);
    }

    public final void show(int durationMs) {
        T t = this.targetView;
        if (t.getVisibility() != 0) {
            t.setVisibility(0);
            t.animate().scaleY(1.0f).scaleX(1.0f).setDuration(durationMs).setInterpolator(new DecelerateInterpolator()).setListener(null).start();
        }
    }

    public static /* synthetic */ void hide$default(DraggableView draggableView, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 300;
        }
        draggableView.hide(i);
    }

    public final void hide(int durationMs) {
        final T t = this.targetView;
        if (t.getVisibility() != 8) {
            Animation animation = t.getAnimation();
            if (animation == null || animation.hasEnded()) {
                t.animate().scaleY(0.0f).scaleX(0.0f).setInterpolator(new AccelerateInterpolator()).setDuration(durationMs).setListener(new AnimatorListenerAdapter() { // from class: io.github.hyuwah.draggableviewlib.DraggableView$hide$1$1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation2) {
                        super.onAnimationEnd(animation2);
                        t.setVisibility(8);
                    }
                }).start();
            }
        }
    }

    public final void undock() {
        if (this.isMinimized) {
            T t = this.targetView;
            Object parent = t.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            float width = ((View) parent).getWidth();
            if (WhenMappings.$EnumSwitchMapping$0[this.sticky.ordinal()] != 1) {
                return;
            }
            if (t.getX() < DraggableUtils.marginStart(t)) {
                t.animate().translationX(0.0f).start();
            } else if (t.getX() > (width - t.getWidth()) - DraggableUtils.marginEnd(t)) {
                t.animate().translationXBy((-(t.getWidth() / 2.0f)) - DraggableUtils.marginEnd(t)).start();
            }
            enableDrag();
            this.isMinimized = false;
        }
    }

    public final void dockToEdge() {
        if (this.isMinimized) {
            return;
        }
        T t = this.targetView;
        Object parent = t.getParent();
        if (parent == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.View");
        }
        float width = ((View) parent).getWidth();
        if (WhenMappings.$EnumSwitchMapping$1[this.sticky.ordinal()] != 1) {
            return;
        }
        float x = t.getX();
        if (x == DraggableUtils.marginStart(t)) {
            t.animate().translationXBy(-((t.getWidth() / 2) + DraggableUtils.marginStart(t))).start();
        } else if (x == (width - t.getWidth()) - DraggableUtils.marginEnd(t)) {
            t.animate().translationXBy((t.getWidth() / 2) + DraggableUtils.marginEnd(t)).start();
        }
        disableDrag();
        this.isMinimized = true;
    }

    /* compiled from: DraggableView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0010\u001a\u00020\u0007J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0013\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00028\u0001X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0014"}, d2 = {"Lio/github/hyuwah/draggableviewlib/DraggableView$Builder;", "VIEW", "Landroid/view/View;", "", "targetView", "(Landroid/view/View;)V", "animated", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lio/github/hyuwah/draggableviewlib/DraggableListener;", "stickyMode", "Lio/github/hyuwah/draggableviewlib/DraggableView$Mode;", "Landroid/view/View;", "build", "Lio/github/hyuwah/draggableviewlib/DraggableView;", "setAnimated", "value", "setListener", "setStickyMode", "mode", "draggableviewlib_release"}, k = 1, mv = {1, 4, 1})
    public static final class Builder<VIEW extends View> {
        private boolean animated;
        private DraggableListener listener;
        private Mode stickyMode;
        private VIEW targetView;

        public Builder(VIEW targetView) {
            Intrinsics.checkNotNullParameter(targetView, "targetView");
            this.targetView = targetView;
            this.stickyMode = Mode.NON_STICKY;
            this.animated = true;
        }

        public final Builder<VIEW> setStickyMode(Mode mode) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            this.stickyMode = mode;
            return this;
        }

        public final Builder<VIEW> setAnimated(boolean value) {
            this.animated = value;
            return this;
        }

        public final Builder<VIEW> setListener(DraggableListener listener) {
            this.listener = listener;
            return this;
        }

        public final DraggableView<VIEW> build() {
            return new DraggableView<>(this.targetView, this.stickyMode, this.animated, this.listener, null);
        }
    }
}
