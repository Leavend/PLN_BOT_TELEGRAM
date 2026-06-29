package io.github.hyuwah.draggableviewlib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.HashMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DraggableImageView.kt */
@Deprecated(message = "Setup any view programmatically using DraggableView class instead")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\nJ\b\u0010\u0015\u001a\u00020\nH\u0016J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\nJ\u0010\u0010\u0018\u001a\u00020\u00132\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lio/github/hyuwah/draggableviewlib/DraggableImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "draggableListener", "Lio/github/hyuwah/draggableviewlib/DraggableListener;", "mAnimate", "", "stickyAxis", "", "widgetDX", "", "widgetDY", "widgetInitialX", "widgetInitialY", "draggableSetup", "", "isAnimate", "performClick", "setAnimate", "animate", "setListener", "setStickyAxis", "axis", "Companion", "draggableviewlib_release"}, k = 1, mv = {1, 4, 1})
/* loaded from: classes2.dex */
public final class DraggableImageView extends AppCompatImageView {
    public static final int NON_STICKY = 0;
    public static final int STICKY_AXIS_X = 1;
    public static final int STICKY_AXIS_XY = 3;
    public static final int STICKY_AXIS_Y = 2;
    private HashMap _$_findViewCache;
    private DraggableListener draggableListener;
    private boolean mAnimate;
    private int stickyAxis;
    private float widgetDX;
    private float widgetDY;
    private float widgetInitialX;
    private float widgetInitialY;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DraggableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DraggableImageView, 0, 0);
        try {
            this.stickyAxis = typedArrayObtainStyledAttributes.getInteger(R.styleable.DraggableImageView_sticky, 0);
            this.mAnimate = typedArrayObtainStyledAttributes.getBoolean(R.styleable.DraggableImageView_animate, false);
            typedArrayObtainStyledAttributes.recycle();
            draggableSetup();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private final void draggableSetup() {
        setOnTouchListener(new View.OnTouchListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1
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
                int width2 = width - v.getWidth();
                int i = width / 2;
                int height2 = height - v.getHeight();
                int i2 = height / 2;
                Intrinsics.checkNotNullExpressionValue(event, "event");
                int actionMasked = event.getActionMasked();
                if (actionMasked == 0) {
                    DraggableImageView.this.widgetDX = v.getX() - event.getRawX();
                    DraggableImageView.this.widgetDY = v.getY() - event.getRawY();
                    DraggableImageView.this.widgetInitialX = v.getX();
                    DraggableImageView.this.widgetInitialY = v.getY();
                } else if (actionMasked == 1) {
                    int i3 = DraggableImageView.this.stickyAxis;
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i3 == 3) {
                                if (event.getRawX() >= i) {
                                    if (DraggableImageView.this.mAnimate) {
                                        v.animate().x(width2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.6
                                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                                if (draggableListener != null) {
                                                    View v2 = v;
                                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                    draggableListener.onPositionChanged(v2);
                                                }
                                            }
                                        }).start();
                                    } else {
                                        v.setX(width2);
                                    }
                                } else {
                                    if (DraggableImageView.this.mAnimate) {
                                        v.animate().x(0.0f).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.7
                                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                                if (draggableListener != null) {
                                                    View v2 = v;
                                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                    draggableListener.onPositionChanged(v2);
                                                }
                                            }
                                        }).start();
                                    }
                                    v.setX(0.0f);
                                }
                                if (event.getRawY() >= i2) {
                                    if (DraggableImageView.this.mAnimate) {
                                        v.animate().y(height2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.8
                                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                                if (draggableListener != null) {
                                                    View v2 = v;
                                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                    draggableListener.onPositionChanged(v2);
                                                }
                                            }
                                        }).start();
                                    } else {
                                        v.setY(height2);
                                    }
                                } else if (DraggableImageView.this.mAnimate) {
                                    v.animate().y(0.0f).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.9
                                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                            DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                            if (draggableListener != null) {
                                                View v2 = v;
                                                Intrinsics.checkNotNullExpressionValue(v2, "v");
                                                draggableListener.onPositionChanged(v2);
                                            }
                                        }
                                    }).start();
                                } else {
                                    v.setY(0.0f);
                                }
                            }
                        } else if (event.getRawY() >= i2) {
                            if (DraggableImageView.this.mAnimate) {
                                v.animate().y(height2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.3
                                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                        DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                        if (draggableListener != null) {
                                            View v2 = v;
                                            Intrinsics.checkNotNullExpressionValue(v2, "v");
                                            draggableListener.onPositionChanged(v2);
                                        }
                                    }
                                }).start();
                            } else {
                                v.setY(height2);
                            }
                        } else if (!DraggableImageView.this.mAnimate) {
                            if (DraggableImageView.this.mAnimate) {
                                v.animate().y(0.0f).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.5
                                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                        DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                        if (draggableListener != null) {
                                            View v2 = v;
                                            Intrinsics.checkNotNullExpressionValue(v2, "v");
                                            draggableListener.onPositionChanged(v2);
                                        }
                                    }
                                }).start();
                            } else {
                                v.setY(0.0f);
                            }
                        } else {
                            v.animate().y(0.0f).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.4
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                    if (draggableListener != null) {
                                        View v2 = v;
                                        Intrinsics.checkNotNullExpressionValue(v2, "v");
                                        draggableListener.onPositionChanged(v2);
                                    }
                                }
                            }).start();
                        }
                    } else if (event.getRawX() >= i) {
                        if (DraggableImageView.this.mAnimate) {
                            v.animate().x(width2).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                    if (draggableListener != null) {
                                        View v2 = v;
                                        Intrinsics.checkNotNullExpressionValue(v2, "v");
                                        draggableListener.onPositionChanged(v2);
                                    }
                                }
                            }).start();
                        } else {
                            v.setX(width2);
                        }
                    } else if (DraggableImageView.this.mAnimate) {
                        v.animate().x(0.0f).setDuration(250L).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.github.hyuwah.draggableviewlib.DraggableImageView.draggableSetup.1.2
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                                if (draggableListener != null) {
                                    View v2 = v;
                                    Intrinsics.checkNotNullExpressionValue(v2, "v");
                                    draggableListener.onPositionChanged(v2);
                                }
                            }
                        }).start();
                    } else {
                        v.setX(0.0f);
                    }
                    float f = 16;
                    if (Math.abs(v.getX() - DraggableImageView.this.widgetInitialX) <= f && Math.abs(v.getY() - DraggableImageView.this.widgetInitialY) <= f) {
                        DraggableImageView.this.performClick();
                    }
                } else {
                    if (actionMasked != 2) {
                        return false;
                    }
                    v.setX(Math.min(width2, Math.max(0.0f, event.getRawX() + DraggableImageView.this.widgetDX)));
                    v.setY(Math.min(height2, Math.max(0.0f, event.getRawY() + DraggableImageView.this.widgetDY)));
                    DraggableListener draggableListener = DraggableImageView.this.draggableListener;
                    if (draggableListener != null) {
                        draggableListener.onPositionChanged(v);
                    }
                }
                return true;
            }
        });
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    public final void setStickyAxis(int axis) {
        if (axis == 0 || axis == 1 || axis == 2 || axis == 3) {
            this.stickyAxis = axis;
            invalidate();
            requestLayout();
        }
    }

    /* renamed from: isAnimate, reason: from getter */
    public final boolean getMAnimate() {
        return this.mAnimate;
    }

    public final void setAnimate(boolean animate) {
        this.mAnimate = animate;
        invalidate();
        requestLayout();
    }

    public final void setListener(DraggableListener draggableListener) {
        this.draggableListener = draggableListener;
    }
}
