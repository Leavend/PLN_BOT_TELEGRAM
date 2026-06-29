package io.github.hyuwah.draggableviewlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.github.hyuwah.draggableviewlib.Draggable;
import io.github.hyuwah.draggableviewlib.DraggableView;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;

/* compiled from: Extensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007\u001a \u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\nH\u0007\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0000\u001a\f\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0000\u001a\f\u0010\u0011\u001a\u00020\u000f*\u00020\u0002H\u0000\u001a\f\u0010\u0012\u001a\u00020\u000f*\u00020\u0002H\u0000\u001a\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0014\"\b\b\u0000\u0010\u0015*\u00020\u0002*\u0002H\u0015¢\u0006\u0002\u0010\u0016\u001a,\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00172\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0001¨\u0006\u0018"}, d2 = {"makeDraggable", "", "Landroid/view/View;", "stickyAxis", "Lio/github/hyuwah/draggableviewlib/Draggable$STICKY;", "animated", "", "draggableListener", "Lio/github/hyuwah/draggableviewlib/DraggableListener;", "makeOverlayDraggable", "Landroid/view/WindowManager$LayoutParams;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lio/github/hyuwah/draggableviewlib/OverlayDraggableListener;", "layoutParams", "marginBottom", "", "marginEnd", "marginStart", "marginTop", "setupDraggable", "Lio/github/hyuwah/draggableviewlib/DraggableView$Builder;", ExifInterface.GPS_DIRECTION_TRUE, "(Landroid/view/View;)Lio/github/hyuwah/draggableviewlib/DraggableView$Builder;", "Lio/github/hyuwah/draggableviewlib/DraggableView$Mode;", "draggableviewlib_release"}, k = 2, mv = {1, 4, 1})
/* loaded from: classes2.dex */
public final class DraggableUtils {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DraggableView.Mode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DraggableView.Mode.STICKY_X.ordinal()] = 1;
            iArr[DraggableView.Mode.STICKY_Y.ordinal()] = 2;
            iArr[DraggableView.Mode.STICKY_XY.ordinal()] = 3;
            int[] iArr2 = new int[Draggable.STICKY.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[Draggable.STICKY.NONE.ordinal()] = 1;
            iArr2[Draggable.STICKY.AXIS_X.ordinal()] = 2;
            iArr2[Draggable.STICKY.AXIS_Y.ordinal()] = 3;
            iArr2[Draggable.STICKY.AXIS_XY.ordinal()] = 4;
        }
    }

    @Deprecated(message = "Use / Setup with DraggableView class or setupDraggable() builder instead", replaceWith = @ReplaceWith(expression = "DraggableView.Builder(view).build()", imports = {"io.github.hyuwah.draggableviewlib.DraggableView"}))
    public static final void makeDraggable(View view) {
        makeDraggable$default(view, null, false, null, 7, null);
    }

    @Deprecated(message = "Use / Setup with DraggableView class or setupDraggable() builder instead", replaceWith = @ReplaceWith(expression = "DraggableView.Builder(view).build()", imports = {"io.github.hyuwah.draggableviewlib.DraggableView"}))
    public static final void makeDraggable(View view, Draggable.STICKY sticky) {
        makeDraggable$default(view, sticky, false, null, 6, null);
    }

    @Deprecated(message = "Use / Setup with DraggableView class or setupDraggable() builder instead", replaceWith = @ReplaceWith(expression = "DraggableView.Builder(view).build()", imports = {"io.github.hyuwah.draggableviewlib.DraggableView"}))
    public static final void makeDraggable(View view, Draggable.STICKY sticky, boolean z) {
        makeDraggable$default(view, sticky, z, null, 4, null);
    }

    public static final WindowManager.LayoutParams makeOverlayDraggable(View view, OverlayDraggableListener overlayDraggableListener) {
        return makeOverlayDraggable$default(view, overlayDraggableListener, null, 2, null);
    }

    /* renamed from: setupDraggable, reason: collision with other method in class */
    public static final void m6847setupDraggable(View view) {
        setupDraggable$default(view, null, false, null, 7, null);
    }

    public static final void setupDraggable(View view, DraggableView.Mode mode) {
        setupDraggable$default(view, mode, false, null, 6, null);
    }

    public static final void setupDraggable(View view, DraggableView.Mode mode, boolean z) {
        setupDraggable$default(view, mode, z, null, 4, null);
    }

    public static final float marginStart(View marginStart) {
        Intrinsics.checkNotNullParameter(marginStart, "$this$marginStart");
        ViewGroup.LayoutParams layoutParams = marginStart.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        return ((ViewGroup.MarginLayoutParams) layoutParams) != null ? r1.getMarginStart() : 0;
    }

    public static final float marginEnd(View marginEnd) {
        Intrinsics.checkNotNullParameter(marginEnd, "$this$marginEnd");
        ViewGroup.LayoutParams layoutParams = marginEnd.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        return ((ViewGroup.MarginLayoutParams) layoutParams) != null ? r1.getMarginEnd() : 0;
    }

    public static final float marginTop(View marginTop) {
        Intrinsics.checkNotNullParameter(marginTop, "$this$marginTop");
        ViewGroup.LayoutParams layoutParams = marginTop.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        return ((ViewGroup.MarginLayoutParams) layoutParams) != null ? r1.topMargin : 0;
    }

    public static final float marginBottom(View marginBottom) {
        Intrinsics.checkNotNullParameter(marginBottom, "$this$marginBottom");
        ViewGroup.LayoutParams layoutParams = marginBottom.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        return ((ViewGroup.MarginLayoutParams) layoutParams) != null ? r1.bottomMargin : 0;
    }

    public static /* synthetic */ void setupDraggable$default(View view, DraggableView.Mode mode, boolean z, DraggableListener draggableListener, int i, Object obj) {
        if ((i & 1) != 0) {
            mode = DraggableView.Mode.NON_STICKY;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            draggableListener = null;
        }
        setupDraggable(view, mode, z, draggableListener);
    }

    public static final void setupDraggable(final View setupDraggable, final DraggableView.Mode stickyAxis, final boolean z, final DraggableListener draggableListener) {
        Intrinsics.checkNotNullParameter(setupDraggable, "$this$setupDraggable");
        Intrinsics.checkNotNullParameter(stickyAxis, "stickyAxis");
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = 0.0f;
        final Ref.FloatRef floatRef2 = new Ref.FloatRef();
        floatRef2.element = 0.0f;
        final Ref.FloatRef floatRef3 = new Ref.FloatRef();
        floatRef3.element = 0.0f;
        final Ref.FloatRef floatRef4 = new Ref.FloatRef();
        floatRef4.element = 0.0f;
        final float fMarginStart = marginStart(setupDraggable);
        final float fMarginTop = marginTop(setupDraggable);
        final float fMarginEnd = marginEnd(setupDraggable);
        final float fMarginBottom = marginBottom(setupDraggable);
        setupDraggable.setOnTouchListener(new View.OnTouchListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(final View v, MotionEvent event) {
                Intrinsics.checkNotNullExpressionValue(v, "v");
                Object parent = v.getParent();
                if (parent == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.View");
                }
                View view = (View) parent;
                int height = view.getHeight();
                int width = view.getWidth();
                float width2 = (width - v.getWidth()) - fMarginEnd;
                int i = width / 2;
                float height2 = (height - v.getHeight()) - fMarginBottom;
                int i2 = height / 2;
                Intrinsics.checkNotNullExpressionValue(event, "event");
                int actionMasked = event.getActionMasked();
                if (actionMasked == 0) {
                    floatRef2.element = v.getX() - event.getRawX();
                    floatRef4.element = v.getY() - event.getRawY();
                    floatRef.element = v.getX();
                    floatRef3.element = v.getY();
                } else if (actionMasked == 1) {
                    int i3 = WhenMappings.$EnumSwitchMapping$0[stickyAxis.ordinal()];
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i3 == 3) {
                                if (event.getRawX() >= i) {
                                    if (z) {
                                        v.animate().x(width2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.6
                                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                DraggableListener draggableListener2 = draggableListener;
                                                if (draggableListener2 != null) {
                                                    View v2 = v;
                                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                    draggableListener2.onPositionChanged(v2);
                                                }
                                            }
                                        }).start();
                                    } else {
                                        v.setX(width2);
                                    }
                                } else {
                                    if (z) {
                                        v.animate().x(fMarginStart).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.7
                                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                DraggableListener draggableListener2 = draggableListener;
                                                if (draggableListener2 != null) {
                                                    View v2 = v;
                                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                    draggableListener2.onPositionChanged(v2);
                                                }
                                            }
                                        }).start();
                                    }
                                    v.setX(fMarginStart);
                                }
                                if (event.getRawY() >= i2) {
                                    if (z) {
                                        v.animate().y(height2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.8
                                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                DraggableListener draggableListener2 = draggableListener;
                                                if (draggableListener2 != null) {
                                                    View v2 = v;
                                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                    draggableListener2.onPositionChanged(v2);
                                                }
                                            }
                                        }).start();
                                    } else {
                                        v.setY(height2);
                                    }
                                } else if (z) {
                                    v.animate().y(fMarginTop).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.9
                                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                            DraggableListener draggableListener2 = draggableListener;
                                            if (draggableListener2 != null) {
                                                View v2 = v;
                                                Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                draggableListener2.onPositionChanged(v2);
                                            }
                                        }
                                    }).start();
                                } else {
                                    v.setY(fMarginTop);
                                }
                            }
                        } else if (event.getRawY() >= i2) {
                            if (z) {
                                v.animate().y(height2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.4
                                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                        DraggableListener draggableListener2 = draggableListener;
                                        if (draggableListener2 != null) {
                                            View v2 = v;
                                            Intrinsics.checkNotNullExpressionValue(v2, "v");
                                            draggableListener2.onPositionChanged(v2);
                                        }
                                    }
                                }).start();
                            } else {
                                v.setY(height2);
                            }
                        } else if (z) {
                            v.animate().y(fMarginTop).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.5
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    DraggableListener draggableListener2 = draggableListener;
                                    if (draggableListener2 != null) {
                                        View v2 = v;
                                        Intrinsics.checkNotNullExpressionValue(v2, "v");
                                        draggableListener2.onPositionChanged(v2);
                                    }
                                }
                            }).start();
                        } else {
                            v.setY(fMarginTop);
                        }
                    } else if (event.getRawX() >= i) {
                        if (z) {
                            v.animate().x(width2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    DraggableListener draggableListener2 = draggableListener;
                                    if (draggableListener2 != null) {
                                        View v2 = v;
                                        Intrinsics.checkNotNullExpressionValue(v2, "v");
                                        draggableListener2.onPositionChanged(v2);
                                    }
                                }
                            }).setListener(new AnimatorListenerAdapter() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    Log.d("drg", "Animate END Sticky X RIGHT");
                                }
                            }).start();
                        } else {
                            v.setX(width2);
                        }
                    } else if (z) {
                        v.animate().x(fMarginStart).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.setupDraggable.1.3
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                DraggableListener draggableListener2 = draggableListener;
                                if (draggableListener2 != null) {
                                    View v2 = v;
                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                    draggableListener2.onPositionChanged(v2);
                                }
                            }
                        }).start();
                    } else {
                        v.setX(fMarginStart);
                    }
                    float f = 16;
                    if (Math.abs(v.getX() - floatRef.element) <= f && Math.abs(v.getY() - floatRef3.element) <= f) {
                        setupDraggable.performClick();
                    }
                } else {
                    if (actionMasked != 2) {
                        return false;
                    }
                    v.setX(Math.min(width2, Math.max(fMarginStart, event.getRawX() + floatRef2.element)));
                    v.setY(Math.min(height2, Math.max(fMarginTop, event.getRawY() + floatRef4.element)));
                    DraggableListener draggableListener2 = draggableListener;
                    if (draggableListener2 != null) {
                        draggableListener2.onPositionChanged(v);
                    }
                }
                return true;
            }
        });
    }

    public static /* synthetic */ void makeDraggable$default(View view, Draggable.STICKY sticky, boolean z, DraggableListener draggableListener, int i, Object obj) {
        if ((i & 1) != 0) {
            sticky = Draggable.STICKY.NONE;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            draggableListener = null;
        }
        makeDraggable(view, sticky, z, draggableListener);
    }

    @Deprecated(message = "Use / Setup with DraggableView class or setupDraggable() builder instead", replaceWith = @ReplaceWith(expression = "DraggableView.Builder(view).build()", imports = {"io.github.hyuwah.draggableviewlib.DraggableView"}))
    public static final void makeDraggable(View makeDraggable, Draggable.STICKY stickyAxis, boolean z, DraggableListener draggableListener) {
        DraggableView.Mode mode;
        Intrinsics.checkNotNullParameter(makeDraggable, "$this$makeDraggable");
        Intrinsics.checkNotNullParameter(stickyAxis, "stickyAxis");
        int i = WhenMappings.$EnumSwitchMapping$1[stickyAxis.ordinal()];
        if (i == 1) {
            mode = DraggableView.Mode.NON_STICKY;
        } else if (i == 2) {
            mode = DraggableView.Mode.STICKY_X;
        } else if (i == 3) {
            mode = DraggableView.Mode.STICKY_Y;
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            mode = DraggableView.Mode.STICKY_XY;
        }
        new DraggableView.Builder(makeDraggable).setStickyMode(mode).setAnimated(z).setListener(draggableListener).build();
    }

    public static final <T extends View> DraggableView.Builder<T> setupDraggable(T setupDraggable) {
        Intrinsics.checkNotNullParameter(setupDraggable, "$this$setupDraggable");
        return new DraggableView.Builder<>(setupDraggable);
    }

    public static /* synthetic */ WindowManager.LayoutParams makeOverlayDraggable$default(View view, OverlayDraggableListener overlayDraggableListener, WindowManager.LayoutParams layoutParams, int i, Object obj) {
        if ((i & 2) != 0) {
            layoutParams = null;
        }
        return makeOverlayDraggable(view, overlayDraggableListener, layoutParams);
    }

    public static final WindowManager.LayoutParams makeOverlayDraggable(final View makeOverlayDraggable, final OverlayDraggableListener listener, WindowManager.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(makeOverlayDraggable, "$this$makeOverlayDraggable");
        Intrinsics.checkNotNullParameter(listener, "listener");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = 0.0f;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = 0;
        final Ref.FloatRef floatRef2 = new Ref.FloatRef();
        floatRef2.element = 0.0f;
        WindowManager.LayoutParams layoutParams2 = layoutParams != null ? layoutParams : new WindowManager.LayoutParams(-2, -2, Build.VERSION.SDK_INT >= 26 ? 2038 : 2002, 8, -3);
        final WindowManager.LayoutParams layoutParams3 = layoutParams2;
        makeOverlayDraggable.setOnTouchListener(new View.OnTouchListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableUtils.makeOverlayDraggable.1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent event) {
                Intrinsics.checkNotNullExpressionValue(event, "event");
                int actionMasked = event.getActionMasked();
                if (actionMasked == 0) {
                    intRef.element = layoutParams3.x;
                    intRef2.element = layoutParams3.y;
                    floatRef.element = intRef.element - event.getRawX();
                    floatRef2.element = intRef2.element - event.getRawY();
                    return true;
                }
                if (actionMasked == 1) {
                    if (Math.abs(layoutParams3.x - intRef.element) <= 16 && Math.abs(layoutParams3.y - intRef2.element) <= 16) {
                        makeOverlayDraggable.performClick();
                    }
                    return true;
                }
                if (actionMasked != 2) {
                    return false;
                }
                float rawX = event.getRawX() + floatRef.element;
                float rawY = event.getRawY() + floatRef2.element;
                layoutParams3.x = MathKt.roundToInt(rawX);
                layoutParams3.y = MathKt.roundToInt(rawY);
                listener.onParamsChanged(layoutParams3);
                return true;
            }
        });
        return layoutParams2;
    }
}
