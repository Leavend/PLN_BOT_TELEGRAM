package org.metalev.multitouch.controller;

import android.util.Log;
import android.view.MotionEvent;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class MultiTouchController<T> {
    private static int ACTION_POINTER_INDEX_SHIFT = 8;
    private static int ACTION_POINTER_UP = 6;
    public static final boolean DEBUG = false;
    private static final long EVENT_SETTLE_TIME_INTERVAL = 20;
    private static final float MAX_MULTITOUCH_DIM_JUMP_SIZE = 40.0f;
    private static final float MAX_MULTITOUCH_POS_JUMP_SIZE = 30.0f;
    public static final int MAX_TOUCH_POINTS = 20;
    private static final float MIN_MULTITOUCH_SEPARATION = 30.0f;
    private static final int MODE_DRAG = 1;
    private static final int MODE_NOTHING = 0;
    private static final int MODE_PINCH = 2;
    private static Method m_getHistoricalPressure;
    private static Method m_getHistoricalX;
    private static Method m_getHistoricalY;
    private static Method m_getPointerCount;
    private static Method m_getPointerId;
    private static Method m_getPressure;
    private static Method m_getX;
    private static Method m_getY;
    public static final boolean multiTouchSupported;
    private static final int[] pointerIds;
    private static final float[] pressureVals;
    private static final float[] xVals;
    private static final float[] yVals;
    private boolean handleSingleTouchEvents;
    private PointInfo mCurrPt;
    private float mCurrPtAng;
    private float mCurrPtDiam;
    private float mCurrPtHeight;
    private float mCurrPtWidth;
    private float mCurrPtX;
    private float mCurrPtY;
    private PositionAndScale mCurrXform;
    private int mMode;
    private PointInfo mPrevPt;
    private long mSettleEndTime;
    private long mSettleStartTime;
    MultiTouchObjectCanvas<T> objectCanvas;
    private T selectedObject;
    private float startAngleMinusPinchAngle;
    private float startPosX;
    private float startPosY;
    private float startScaleOverPinchDiam;
    private float startScaleXOverPinchWidth;
    private float startScaleYOverPinchHeight;

    public interface MultiTouchObjectCanvas<T> {
        T getDraggableObjectAtPoint(PointInfo pointInfo);

        void getPositionAndScale(T t, PositionAndScale positionAndScale);

        void selectObject(T t, PointInfo pointInfo);

        boolean setPositionAndScale(T t, PositionAndScale positionAndScale, PointInfo pointInfo);
    }

    private void extractCurrPtInfo() {
        this.mCurrPtX = this.mCurrPt.getX();
        this.mCurrPtY = this.mCurrPt.getY();
        this.mCurrPtDiam = Math.max(21.3f, !this.mCurrXform.updateScale ? 0.0f : this.mCurrPt.getMultiTouchDiameter());
        this.mCurrPtWidth = Math.max(30.0f, !this.mCurrXform.updateScaleXY ? 0.0f : this.mCurrPt.getMultiTouchWidth());
        this.mCurrPtHeight = Math.max(30.0f, !this.mCurrXform.updateScaleXY ? 0.0f : this.mCurrPt.getMultiTouchHeight());
        this.mCurrPtAng = this.mCurrXform.updateAngle ? this.mCurrPt.getMultiTouchAngle() : 0.0f;
    }

    public MultiTouchController(MultiTouchObjectCanvas<T> multiTouchObjectCanvas) {
        this(multiTouchObjectCanvas, true);
    }

    public MultiTouchController(MultiTouchObjectCanvas<T> multiTouchObjectCanvas, boolean z) {
        this.selectedObject = null;
        this.mCurrXform = new PositionAndScale();
        this.mMode = 0;
        this.mCurrPt = new PointInfo();
        this.mPrevPt = new PointInfo();
        this.handleSingleTouchEvents = z;
        this.objectCanvas = multiTouchObjectCanvas;
    }

    protected void setHandleSingleTouchEvents(boolean z) {
        this.handleSingleTouchEvents = z;
    }

    protected boolean getHandleSingleTouchEvents() {
        return this.handleSingleTouchEvents;
    }

    static {
        boolean z = false;
        try {
            m_getPointerCount = MotionEvent.class.getMethod("getPointerCount", new Class[0]);
            m_getPointerId = MotionEvent.class.getMethod("getPointerId", Integer.TYPE);
            m_getPressure = MotionEvent.class.getMethod("getPressure", Integer.TYPE);
            m_getHistoricalX = MotionEvent.class.getMethod("getHistoricalX", Integer.TYPE, Integer.TYPE);
            m_getHistoricalY = MotionEvent.class.getMethod("getHistoricalY", Integer.TYPE, Integer.TYPE);
            m_getHistoricalPressure = MotionEvent.class.getMethod("getHistoricalPressure", Integer.TYPE, Integer.TYPE);
            m_getX = MotionEvent.class.getMethod("getX", Integer.TYPE);
            m_getY = MotionEvent.class.getMethod("getY", Integer.TYPE);
            z = true;
        } catch (Exception e) {
            Log.e("MultiTouchController", "static initializer failed", e);
        }
        multiTouchSupported = z;
        if (z) {
            try {
                ACTION_POINTER_UP = MotionEvent.class.getField("ACTION_POINTER_UP").getInt(null);
                ACTION_POINTER_INDEX_SHIFT = MotionEvent.class.getField("ACTION_POINTER_INDEX_SHIFT").getInt(null);
            } catch (Exception unused) {
            }
        }
        xVals = new float[20];
        yVals = new float[20];
        pressureVals = new float[20];
        pointerIds = new int[20];
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            int iIntValue = multiTouchSupported ? ((Integer) m_getPointerCount.invoke(motionEvent, new Object[0])).intValue() : 1;
            if (this.mMode == 0 && !this.handleSingleTouchEvents && iIntValue == 1) {
                return false;
            }
            int action = motionEvent.getAction();
            int historySize = motionEvent.getHistorySize() / iIntValue;
            int i = 0;
            while (i <= historySize) {
                boolean z = i < historySize;
                if (!multiTouchSupported || iIntValue == 1) {
                    xVals[0] = z ? motionEvent.getHistoricalX(i) : motionEvent.getX();
                    yVals[0] = z ? motionEvent.getHistoricalY(i) : motionEvent.getY();
                    pressureVals[0] = z ? motionEvent.getHistoricalPressure(i) : motionEvent.getPressure();
                } else {
                    int iMin = Math.min(iIntValue, 20);
                    for (int i2 = 0; i2 < iMin; i2++) {
                        pointerIds[i2] = ((Integer) m_getPointerId.invoke(motionEvent, Integer.valueOf(i2))).intValue();
                        xVals[i2] = ((Float) (z ? m_getHistoricalX.invoke(motionEvent, Integer.valueOf(i2), Integer.valueOf(i)) : m_getX.invoke(motionEvent, Integer.valueOf(i2)))).floatValue();
                        yVals[i2] = ((Float) (z ? m_getHistoricalY.invoke(motionEvent, Integer.valueOf(i2), Integer.valueOf(i)) : m_getY.invoke(motionEvent, Integer.valueOf(i2)))).floatValue();
                        pressureVals[i2] = ((Float) (z ? m_getHistoricalPressure.invoke(motionEvent, Integer.valueOf(i2), Integer.valueOf(i)) : m_getPressure.invoke(motionEvent, Integer.valueOf(i2)))).floatValue();
                    }
                }
                float[] fArr = xVals;
                float[] fArr2 = yVals;
                float[] fArr3 = pressureVals;
                int[] iArr = pointerIds;
                int i3 = historySize;
                int i4 = i;
                decodeTouchEvent(iIntValue, fArr, fArr2, fArr3, iArr, z ? 2 : action, z || !(action == 1 || (((1 << ACTION_POINTER_INDEX_SHIFT) - 1) & action) == ACTION_POINTER_UP || action == 3), z ? motionEvent.getHistoricalEventTime(i) : motionEvent.getEventTime());
                i = i4 + 1;
                historySize = i3;
            }
            return true;
        } catch (Exception e) {
            Log.e("MultiTouchController", "onTouchEvent() failed", e);
            return false;
        }
    }

    private void decodeTouchEvent(int i, float[] fArr, float[] fArr2, float[] fArr3, int[] iArr, int i2, boolean z, long j) {
        PointInfo pointInfo = this.mPrevPt;
        this.mPrevPt = this.mCurrPt;
        this.mCurrPt = pointInfo;
        pointInfo.set(i, fArr, fArr2, fArr3, iArr, i2, z, j);
        multiTouchController();
    }

    private void anchorAtThisPositionAndScale() {
        T t = this.selectedObject;
        if (t == null) {
            return;
        }
        this.objectCanvas.getPositionAndScale(t, this.mCurrXform);
        float f = 1.0f / ((this.mCurrXform.updateScale && this.mCurrXform.scale != 0.0f) ? this.mCurrXform.scale : 1.0f);
        extractCurrPtInfo();
        this.startPosX = (this.mCurrPtX - this.mCurrXform.xOff) * f;
        this.startPosY = (this.mCurrPtY - this.mCurrXform.yOff) * f;
        this.startScaleOverPinchDiam = this.mCurrXform.scale / this.mCurrPtDiam;
        this.startScaleXOverPinchWidth = this.mCurrXform.scaleX / this.mCurrPtWidth;
        this.startScaleYOverPinchHeight = this.mCurrXform.scaleY / this.mCurrPtHeight;
        this.startAngleMinusPinchAngle = this.mCurrXform.angle - this.mCurrPtAng;
    }

    private void performDragOrPinch() {
        if (this.selectedObject == null) {
            return;
        }
        float f = 1.0f;
        if (this.mCurrXform.updateScale && this.mCurrXform.scale != 0.0f) {
            f = this.mCurrXform.scale;
        }
        extractCurrPtInfo();
        this.mCurrXform.set(this.mCurrPtX - (this.startPosX * f), this.mCurrPtY - (this.startPosY * f), this.startScaleOverPinchDiam * this.mCurrPtDiam, this.startScaleXOverPinchWidth * this.mCurrPtWidth, this.startScaleYOverPinchHeight * this.mCurrPtHeight, this.startAngleMinusPinchAngle + this.mCurrPtAng);
        this.objectCanvas.setPositionAndScale(this.selectedObject, this.mCurrXform, this.mCurrPt);
    }

    public boolean isPinching() {
        return this.mMode == 2;
    }

    private void multiTouchController() {
        int i = this.mMode;
        if (i == 0) {
            if (this.mCurrPt.isDown()) {
                T draggableObjectAtPoint = this.objectCanvas.getDraggableObjectAtPoint(this.mCurrPt);
                this.selectedObject = draggableObjectAtPoint;
                if (draggableObjectAtPoint != null) {
                    this.mMode = 1;
                    this.objectCanvas.selectObject(draggableObjectAtPoint, this.mCurrPt);
                    anchorAtThisPositionAndScale();
                    long eventTime = this.mCurrPt.getEventTime();
                    this.mSettleEndTime = eventTime;
                    this.mSettleStartTime = eventTime;
                    return;
                }
                return;
            }
            return;
        }
        if (i == 1) {
            if (!this.mCurrPt.isDown()) {
                this.mMode = 0;
                MultiTouchObjectCanvas<T> multiTouchObjectCanvas = this.objectCanvas;
                this.selectedObject = null;
                multiTouchObjectCanvas.selectObject(null, this.mCurrPt);
                return;
            }
            if (!this.mCurrPt.isMultiTouch()) {
                if (this.mCurrPt.getEventTime() < this.mSettleEndTime) {
                    anchorAtThisPositionAndScale();
                    return;
                } else {
                    performDragOrPinch();
                    return;
                }
            }
            this.mMode = 2;
            anchorAtThisPositionAndScale();
            long eventTime2 = this.mCurrPt.getEventTime();
            this.mSettleStartTime = eventTime2;
            this.mSettleEndTime = eventTime2 + EVENT_SETTLE_TIME_INTERVAL;
            return;
        }
        if (i != 2) {
            return;
        }
        if (!this.mCurrPt.isMultiTouch() || !this.mCurrPt.isDown()) {
            if (!this.mCurrPt.isDown()) {
                this.mMode = 0;
                MultiTouchObjectCanvas<T> multiTouchObjectCanvas2 = this.objectCanvas;
                this.selectedObject = null;
                multiTouchObjectCanvas2.selectObject(null, this.mCurrPt);
                return;
            }
            this.mMode = 1;
            anchorAtThisPositionAndScale();
            long eventTime3 = this.mCurrPt.getEventTime();
            this.mSettleStartTime = eventTime3;
            this.mSettleEndTime = eventTime3 + EVENT_SETTLE_TIME_INTERVAL;
            return;
        }
        if (Math.abs(this.mCurrPt.getX() - this.mPrevPt.getX()) > 30.0f || Math.abs(this.mCurrPt.getY() - this.mPrevPt.getY()) > 30.0f || Math.abs(this.mCurrPt.getMultiTouchWidth() - this.mPrevPt.getMultiTouchWidth()) * 0.5f > MAX_MULTITOUCH_DIM_JUMP_SIZE || Math.abs(this.mCurrPt.getMultiTouchHeight() - this.mPrevPt.getMultiTouchHeight()) * 0.5f > MAX_MULTITOUCH_DIM_JUMP_SIZE) {
            anchorAtThisPositionAndScale();
            long eventTime4 = this.mCurrPt.getEventTime();
            this.mSettleStartTime = eventTime4;
            this.mSettleEndTime = eventTime4 + EVENT_SETTLE_TIME_INTERVAL;
            return;
        }
        if (this.mCurrPt.eventTime < this.mSettleEndTime) {
            anchorAtThisPositionAndScale();
        } else {
            performDragOrPinch();
        }
    }

    public int getMode() {
        return this.mMode;
    }

    public static class PointInfo {
        private int action;
        private float angle;
        private boolean angleIsCalculated;
        private float diameter;
        private boolean diameterIsCalculated;
        private float diameterSq;
        private boolean diameterSqIsCalculated;
        private float dx;
        private float dy;
        private long eventTime;
        private boolean isDown;
        private boolean isMultiTouch;
        private int numPoints;
        private float pressureMid;
        private float xMid;
        private float yMid;
        private float[] xs = new float[20];
        private float[] ys = new float[20];
        private float[] pressures = new float[20];
        private int[] pointerIds = new int[20];

        private int julery_isqrt(int i) {
            int i2 = 0;
            int i3 = 32768;
            int i4 = 15;
            while (true) {
                int i5 = i4 - 1;
                int i6 = ((i2 << 1) + i3) << i4;
                if (i >= i6) {
                    i2 += i3;
                    i -= i6;
                }
                i3 >>= 1;
                if (i3 <= 0) {
                    return i2;
                }
                i4 = i5;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void set(int i, float[] fArr, float[] fArr2, float[] fArr3, int[] iArr, int i2, boolean z, long j) {
            this.eventTime = j;
            this.action = i2;
            this.numPoints = i;
            for (int i3 = 0; i3 < i; i3++) {
                this.xs[i3] = fArr[i3];
                this.ys[i3] = fArr2[i3];
                this.pressures[i3] = fArr3[i3];
                this.pointerIds[i3] = iArr[i3];
            }
            this.isDown = z;
            boolean z2 = i >= 2;
            this.isMultiTouch = z2;
            if (z2) {
                float f = fArr[0];
                float f2 = fArr[1];
                this.xMid = (f + f2) * 0.5f;
                this.yMid = (fArr2[0] + fArr2[1]) * 0.5f;
                this.pressureMid = (fArr3[0] + fArr3[1]) * 0.5f;
                this.dx = Math.abs(f2 - f);
                this.dy = Math.abs(fArr2[1] - fArr2[0]);
            } else {
                this.xMid = fArr[0];
                this.yMid = fArr2[0];
                this.pressureMid = fArr3[0];
                this.dy = 0.0f;
                this.dx = 0.0f;
            }
            this.angleIsCalculated = false;
            this.diameterIsCalculated = false;
            this.diameterSqIsCalculated = false;
        }

        public void set(PointInfo pointInfo) {
            this.numPoints = pointInfo.numPoints;
            for (int i = 0; i < this.numPoints; i++) {
                this.xs[i] = pointInfo.xs[i];
                this.ys[i] = pointInfo.ys[i];
                this.pressures[i] = pointInfo.pressures[i];
                this.pointerIds[i] = pointInfo.pointerIds[i];
            }
            this.xMid = pointInfo.xMid;
            this.yMid = pointInfo.yMid;
            this.pressureMid = pointInfo.pressureMid;
            this.dx = pointInfo.dx;
            this.dy = pointInfo.dy;
            this.diameter = pointInfo.diameter;
            this.diameterSq = pointInfo.diameterSq;
            this.angle = pointInfo.angle;
            this.isDown = pointInfo.isDown;
            this.action = pointInfo.action;
            this.isMultiTouch = pointInfo.isMultiTouch;
            this.diameterIsCalculated = pointInfo.diameterIsCalculated;
            this.diameterSqIsCalculated = pointInfo.diameterSqIsCalculated;
            this.angleIsCalculated = pointInfo.angleIsCalculated;
            this.eventTime = pointInfo.eventTime;
        }

        public boolean isMultiTouch() {
            return this.isMultiTouch;
        }

        public float getMultiTouchWidth() {
            if (this.isMultiTouch) {
                return this.dx;
            }
            return 0.0f;
        }

        public float getMultiTouchHeight() {
            if (this.isMultiTouch) {
                return this.dy;
            }
            return 0.0f;
        }

        public float getMultiTouchDiameterSq() {
            float f;
            if (!this.diameterSqIsCalculated) {
                if (this.isMultiTouch) {
                    float f2 = this.dx;
                    float f3 = this.dy;
                    f = (f2 * f2) + (f3 * f3);
                } else {
                    f = 0.0f;
                }
                this.diameterSq = f;
                this.diameterSqIsCalculated = true;
            }
            return this.diameterSq;
        }

        public float getMultiTouchDiameter() {
            if (!this.diameterIsCalculated) {
                if (!this.isMultiTouch) {
                    this.diameter = 0.0f;
                } else {
                    float fJulery_isqrt = getMultiTouchDiameterSq() != 0.0f ? julery_isqrt((int) (r0 * 256.0f)) / 16.0f : 0.0f;
                    this.diameter = fJulery_isqrt;
                    float f = this.dx;
                    if (fJulery_isqrt < f) {
                        this.diameter = f;
                    }
                    float f2 = this.diameter;
                    float f3 = this.dy;
                    if (f2 < f3) {
                        this.diameter = f3;
                    }
                }
                this.diameterIsCalculated = true;
            }
            return this.diameter;
        }

        public float getMultiTouchAngle() {
            if (!this.angleIsCalculated) {
                if (!this.isMultiTouch) {
                    this.angle = 0.0f;
                } else {
                    float[] fArr = this.ys;
                    double d = fArr[1] - fArr[0];
                    float[] fArr2 = this.xs;
                    this.angle = (float) Math.atan2(d, fArr2[1] - fArr2[0]);
                }
                this.angleIsCalculated = true;
            }
            return this.angle;
        }

        public int getNumTouchPoints() {
            return this.numPoints;
        }

        public float getX() {
            return this.xMid;
        }

        public float[] getXs() {
            return this.xs;
        }

        public float getY() {
            return this.yMid;
        }

        public float[] getYs() {
            return this.ys;
        }

        public int[] getPointerIds() {
            return this.pointerIds;
        }

        public float getPressure() {
            return this.pressureMid;
        }

        public float[] getPressures() {
            return this.pressures;
        }

        public boolean isDown() {
            return this.isDown;
        }

        public int getAction() {
            return this.action;
        }

        public long getEventTime() {
            return this.eventTime;
        }
    }

    public static class PositionAndScale {
        private float angle;
        private float scale;
        private float scaleX;
        private float scaleY;
        private boolean updateAngle;
        private boolean updateScale;
        private boolean updateScaleXY;
        private float xOff;
        private float yOff;

        public void set(float f, float f2, boolean z, float f3, boolean z2, float f4, float f5, boolean z3, float f6) {
            this.xOff = f;
            this.yOff = f2;
            this.updateScale = z;
            if (f3 == 0.0f) {
                f3 = 1.0f;
            }
            this.scale = f3;
            this.updateScaleXY = z2;
            if (f4 == 0.0f) {
                f4 = 1.0f;
            }
            this.scaleX = f4;
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.scaleY = f5;
            this.updateAngle = z3;
            this.angle = f6;
        }

        protected void set(float f, float f2, float f3, float f4, float f5, float f6) {
            this.xOff = f;
            this.yOff = f2;
            if (f3 == 0.0f) {
                f3 = 1.0f;
            }
            this.scale = f3;
            if (f4 == 0.0f) {
                f4 = 1.0f;
            }
            this.scaleX = f4;
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.scaleY = f5;
            this.angle = f6;
        }

        public float getXOff() {
            return this.xOff;
        }

        public float getYOff() {
            return this.yOff;
        }

        public float getScale() {
            if (this.updateScale) {
                return this.scale;
            }
            return 1.0f;
        }

        public float getScaleX() {
            if (this.updateScaleXY) {
                return this.scaleX;
            }
            return 1.0f;
        }

        public float getScaleY() {
            if (this.updateScaleXY) {
                return this.scaleY;
            }
            return 1.0f;
        }

        public float getAngle() {
            if (this.updateAngle) {
                return this.angle;
            }
            return 0.0f;
        }
    }
}
