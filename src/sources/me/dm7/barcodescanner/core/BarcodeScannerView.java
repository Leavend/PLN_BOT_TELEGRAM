package me.dm7.barcodescanner.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes3.dex */
public abstract class BarcodeScannerView extends FrameLayout implements Camera.PreviewCallback {
    private float mAspectTolerance;
    private boolean mAutofocusState;
    private float mBorderAlpha;
    private int mBorderColor;
    private int mBorderLength;
    private int mBorderWidth;
    private CameraHandlerThread mCameraHandlerThread;
    private CameraWrapper mCameraWrapper;
    private int mCornerRadius;
    private Boolean mFlashState;
    private Rect mFramingRectInPreview;
    private boolean mIsLaserEnabled;
    private int mLaserColor;
    private int mMaskColor;
    private CameraPreview mPreview;
    private boolean mRoundedCorner;
    private boolean mShouldScaleToFill;
    private boolean mSquaredFinder;
    private int mViewFinderOffset;
    private IViewFinder mViewFinderView;

    public BarcodeScannerView(Context context) {
        super(context);
        this.mAutofocusState = true;
        this.mShouldScaleToFill = true;
        this.mIsLaserEnabled = true;
        this.mLaserColor = getResources().getColor(R.color.viewfinder_laser);
        this.mBorderColor = getResources().getColor(R.color.viewfinder_border);
        this.mMaskColor = getResources().getColor(R.color.viewfinder_mask);
        this.mBorderWidth = getResources().getInteger(R.integer.viewfinder_border_width);
        this.mBorderLength = getResources().getInteger(R.integer.viewfinder_border_length);
        this.mRoundedCorner = false;
        this.mCornerRadius = 0;
        this.mSquaredFinder = false;
        this.mBorderAlpha = 1.0f;
        this.mViewFinderOffset = 0;
        this.mAspectTolerance = 0.1f;
        init();
    }

    public BarcodeScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAutofocusState = true;
        this.mShouldScaleToFill = true;
        this.mIsLaserEnabled = true;
        this.mLaserColor = getResources().getColor(R.color.viewfinder_laser);
        this.mBorderColor = getResources().getColor(R.color.viewfinder_border);
        this.mMaskColor = getResources().getColor(R.color.viewfinder_mask);
        this.mBorderWidth = getResources().getInteger(R.integer.viewfinder_border_width);
        this.mBorderLength = getResources().getInteger(R.integer.viewfinder_border_length);
        this.mRoundedCorner = false;
        this.mCornerRadius = 0;
        this.mSquaredFinder = false;
        this.mBorderAlpha = 1.0f;
        this.mViewFinderOffset = 0;
        this.mAspectTolerance = 0.1f;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.BarcodeScannerView, 0, 0);
        try {
            setShouldScaleToFill(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BarcodeScannerView_shouldScaleToFill, true));
            this.mIsLaserEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BarcodeScannerView_laserEnabled, this.mIsLaserEnabled);
            this.mLaserColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BarcodeScannerView_laserColor, this.mLaserColor);
            this.mBorderColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BarcodeScannerView_borderColor, this.mBorderColor);
            this.mMaskColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BarcodeScannerView_maskColor, this.mMaskColor);
            this.mBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BarcodeScannerView_borderWidth, this.mBorderWidth);
            this.mBorderLength = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BarcodeScannerView_borderLength, this.mBorderLength);
            this.mRoundedCorner = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BarcodeScannerView_roundedCorner, this.mRoundedCorner);
            this.mCornerRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BarcodeScannerView_cornerRadius, this.mCornerRadius);
            this.mSquaredFinder = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BarcodeScannerView_squaredFinder, this.mSquaredFinder);
            this.mBorderAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.BarcodeScannerView_borderAlpha, this.mBorderAlpha);
            this.mViewFinderOffset = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BarcodeScannerView_finderOffset, this.mViewFinderOffset);
            typedArrayObtainStyledAttributes.recycle();
            init();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void init() {
        this.mViewFinderView = createViewFinderView(getContext());
    }

    public final void setupLayout(CameraWrapper cameraWrapper) {
        removeAllViews();
        CameraPreview cameraPreview = new CameraPreview(getContext(), cameraWrapper, this);
        this.mPreview = cameraPreview;
        cameraPreview.setAspectTolerance(this.mAspectTolerance);
        this.mPreview.setShouldScaleToFill(this.mShouldScaleToFill);
        if (!this.mShouldScaleToFill) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setGravity(17);
            relativeLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            relativeLayout.addView(this.mPreview);
            addView(relativeLayout);
        } else {
            addView(this.mPreview);
        }
        Object obj = this.mViewFinderView;
        if (obj instanceof View) {
            addView((View) obj);
            return;
        }
        throw new IllegalArgumentException("IViewFinder object returned by 'createViewFinderView()' should be instance of android.view.View");
    }

    protected IViewFinder createViewFinderView(Context context) {
        ViewFinderView viewFinderView = new ViewFinderView(context);
        viewFinderView.setBorderColor(this.mBorderColor);
        viewFinderView.setLaserColor(this.mLaserColor);
        viewFinderView.setLaserEnabled(this.mIsLaserEnabled);
        viewFinderView.setBorderStrokeWidth(this.mBorderWidth);
        viewFinderView.setBorderLineLength(this.mBorderLength);
        viewFinderView.setMaskColor(this.mMaskColor);
        viewFinderView.setBorderCornerRounded(this.mRoundedCorner);
        viewFinderView.setBorderCornerRadius(this.mCornerRadius);
        viewFinderView.setSquareViewFinder(this.mSquaredFinder);
        viewFinderView.setViewFinderOffset(this.mViewFinderOffset);
        return viewFinderView;
    }

    public void setLaserColor(int i) {
        this.mLaserColor = i;
        this.mViewFinderView.setLaserColor(i);
        this.mViewFinderView.setupViewFinder();
    }

    public void setMaskColor(int i) {
        this.mMaskColor = i;
        this.mViewFinderView.setMaskColor(i);
        this.mViewFinderView.setupViewFinder();
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        this.mViewFinderView.setBorderColor(i);
        this.mViewFinderView.setupViewFinder();
    }

    public void setBorderStrokeWidth(int i) {
        this.mBorderWidth = i;
        this.mViewFinderView.setBorderStrokeWidth(i);
        this.mViewFinderView.setupViewFinder();
    }

    public void setBorderLineLength(int i) {
        this.mBorderLength = i;
        this.mViewFinderView.setBorderLineLength(i);
        this.mViewFinderView.setupViewFinder();
    }

    public void setLaserEnabled(boolean z) {
        this.mIsLaserEnabled = z;
        this.mViewFinderView.setLaserEnabled(z);
        this.mViewFinderView.setupViewFinder();
    }

    public void setIsBorderCornerRounded(boolean z) {
        this.mRoundedCorner = z;
        this.mViewFinderView.setBorderCornerRounded(z);
        this.mViewFinderView.setupViewFinder();
    }

    public void setBorderCornerRadius(int i) {
        this.mCornerRadius = i;
        this.mViewFinderView.setBorderCornerRadius(i);
        this.mViewFinderView.setupViewFinder();
    }

    public void setSquareViewFinder(boolean z) {
        this.mSquaredFinder = z;
        this.mViewFinderView.setSquareViewFinder(z);
        this.mViewFinderView.setupViewFinder();
    }

    public void setBorderAlpha(float f) {
        this.mBorderAlpha = f;
        this.mViewFinderView.setBorderAlpha(f);
        this.mViewFinderView.setupViewFinder();
    }

    public void startCamera(int i) {
        if (this.mCameraHandlerThread == null) {
            this.mCameraHandlerThread = new CameraHandlerThread(this);
        }
        this.mCameraHandlerThread.startCamera(i);
    }

    public void setupCameraPreview(CameraWrapper cameraWrapper) {
        this.mCameraWrapper = cameraWrapper;
        if (cameraWrapper != null) {
            setupLayout(cameraWrapper);
            this.mViewFinderView.setupViewFinder();
            Boolean bool = this.mFlashState;
            if (bool != null) {
                setFlash(bool.booleanValue());
            }
            setAutoFocus(this.mAutofocusState);
        }
    }

    public void startCamera() {
        startCamera(CameraUtils.getDefaultCameraId());
    }

    public void stopCamera() {
        if (this.mCameraWrapper != null) {
            this.mPreview.stopCameraPreview();
            this.mPreview.setCamera(null, null);
            this.mCameraWrapper.mCamera.release();
            this.mCameraWrapper = null;
        }
        CameraHandlerThread cameraHandlerThread = this.mCameraHandlerThread;
        if (cameraHandlerThread != null) {
            cameraHandlerThread.quit();
            this.mCameraHandlerThread = null;
        }
    }

    public void stopCameraPreview() {
        CameraPreview cameraPreview = this.mPreview;
        if (cameraPreview != null) {
            cameraPreview.stopCameraPreview();
        }
    }

    protected void resumeCameraPreview() throws IOException {
        CameraPreview cameraPreview = this.mPreview;
        if (cameraPreview != null) {
            cameraPreview.showCameraPreview();
        }
    }

    public synchronized Rect getFramingRectInPreview(int i, int i2) {
        if (this.mFramingRectInPreview == null) {
            Rect framingRect = this.mViewFinderView.getFramingRect();
            int width = this.mViewFinderView.getWidth();
            int height = this.mViewFinderView.getHeight();
            if (framingRect != null && width != 0 && height != 0) {
                Rect rect = new Rect(framingRect);
                if (i < width) {
                    rect.left = (rect.left * i) / width;
                    rect.right = (rect.right * i) / width;
                }
                if (i2 < height) {
                    rect.top = (rect.top * i2) / height;
                    rect.bottom = (rect.bottom * i2) / height;
                }
                this.mFramingRectInPreview = rect;
            }
            return null;
        }
        return this.mFramingRectInPreview;
    }

    public void setFlash(boolean z) {
        this.mFlashState = Boolean.valueOf(z);
        CameraWrapper cameraWrapper = this.mCameraWrapper;
        if (cameraWrapper == null || !CameraUtils.isFlashSupported(cameraWrapper.mCamera)) {
            return;
        }
        Camera.Parameters parameters = this.mCameraWrapper.mCamera.getParameters();
        if (z) {
            if (parameters.getFlashMode().equals("torch")) {
                return;
            } else {
                parameters.setFlashMode("torch");
            }
        } else if (parameters.getFlashMode().equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
            return;
        } else {
            parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        }
        this.mCameraWrapper.mCamera.setParameters(parameters);
    }

    public boolean getFlash() {
        CameraWrapper cameraWrapper = this.mCameraWrapper;
        return cameraWrapper != null && CameraUtils.isFlashSupported(cameraWrapper.mCamera) && this.mCameraWrapper.mCamera.getParameters().getFlashMode().equals("torch");
    }

    public void toggleFlash() {
        CameraWrapper cameraWrapper = this.mCameraWrapper;
        if (cameraWrapper == null || !CameraUtils.isFlashSupported(cameraWrapper.mCamera)) {
            return;
        }
        Camera.Parameters parameters = this.mCameraWrapper.mCamera.getParameters();
        if (parameters.getFlashMode().equals("torch")) {
            parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        } else {
            parameters.setFlashMode("torch");
        }
        this.mCameraWrapper.mCamera.setParameters(parameters);
    }

    public void setAutoFocus(boolean z) {
        this.mAutofocusState = z;
        CameraPreview cameraPreview = this.mPreview;
        if (cameraPreview != null) {
            cameraPreview.setAutoFocus(z);
        }
    }

    public void setShouldScaleToFill(boolean z) {
        this.mShouldScaleToFill = z;
    }

    public void setAspectTolerance(float f) {
        this.mAspectTolerance = f;
    }

    public byte[] getRotatedData(byte[] bArr, Camera camera) {
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        int i = previewSize.width;
        int i2 = previewSize.height;
        int rotationCount = getRotationCount();
        if (rotationCount == 1 || rotationCount == 3) {
            int i3 = 0;
            while (i3 < rotationCount) {
                byte[] bArr2 = new byte[bArr.length];
                for (int i4 = 0; i4 < i2; i4++) {
                    for (int i5 = 0; i5 < i; i5++) {
                        bArr2[(((i5 * i2) + i2) - i4) - 1] = bArr[(i4 * i) + i5];
                    }
                }
                i3++;
                bArr = bArr2;
                int i6 = i;
                i = i2;
                i2 = i6;
            }
        }
        return bArr;
    }

    public int getRotationCount() {
        return this.mPreview.getDisplayOrientation() / 90;
    }
}
