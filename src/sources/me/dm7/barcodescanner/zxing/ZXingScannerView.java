package me.dm7.barcodescanner.zxing;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import me.dm7.barcodescanner.core.BarcodeScannerView;
import me.dm7.barcodescanner.core.DisplayUtils;

/* loaded from: classes3.dex */
public class ZXingScannerView extends BarcodeScannerView {
    public static final List<BarcodeFormat> ALL_FORMATS;
    private static final String TAG = "ZXingScannerView";
    private List<BarcodeFormat> mFormats;
    private MultiFormatReader mMultiFormatReader;
    private ResultHandler mResultHandler;

    public interface ResultHandler {
        void handleResult(Result result);
    }

    static {
        ArrayList arrayList = new ArrayList();
        ALL_FORMATS = arrayList;
        arrayList.add(BarcodeFormat.AZTEC);
        arrayList.add(BarcodeFormat.CODABAR);
        arrayList.add(BarcodeFormat.CODE_39);
        arrayList.add(BarcodeFormat.CODE_93);
        arrayList.add(BarcodeFormat.CODE_128);
        arrayList.add(BarcodeFormat.DATA_MATRIX);
        arrayList.add(BarcodeFormat.EAN_8);
        arrayList.add(BarcodeFormat.EAN_13);
        arrayList.add(BarcodeFormat.ITF);
        arrayList.add(BarcodeFormat.MAXICODE);
        arrayList.add(BarcodeFormat.PDF_417);
        arrayList.add(BarcodeFormat.QR_CODE);
        arrayList.add(BarcodeFormat.RSS_14);
        arrayList.add(BarcodeFormat.RSS_EXPANDED);
        arrayList.add(BarcodeFormat.UPC_A);
        arrayList.add(BarcodeFormat.UPC_E);
        arrayList.add(BarcodeFormat.UPC_EAN_EXTENSION);
    }

    public ZXingScannerView(Context context) {
        super(context);
        initMultiFormatReader();
    }

    public ZXingScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initMultiFormatReader();
    }

    public void setFormats(List<BarcodeFormat> list) {
        this.mFormats = list;
        initMultiFormatReader();
    }

    public void setResultHandler(ResultHandler resultHandler) {
        this.mResultHandler = resultHandler;
    }

    public Collection<BarcodeFormat> getFormats() {
        List<BarcodeFormat> list = this.mFormats;
        return list == null ? ALL_FORMATS : list;
    }

    private void initMultiFormatReader() {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) getFormats());
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        this.mMultiFormatReader = multiFormatReader;
        multiFormatReader.setHints(enumMap);
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        MultiFormatReader multiFormatReader;
        MultiFormatReader multiFormatReader2;
        if (this.mResultHandler == null) {
            return;
        }
        try {
            Camera.Size previewSize = camera.getParameters().getPreviewSize();
            int i = previewSize.width;
            int i2 = previewSize.height;
            if (DisplayUtils.getScreenOrientation(getContext()) == 1) {
                int rotationCount = getRotationCount();
                if (rotationCount == 1 || rotationCount == 3) {
                    i = i2;
                    i2 = i;
                }
                bArr = getRotatedData(bArr, camera);
            }
            PlanarYUVLuminanceSource planarYUVLuminanceSourceBuildLuminanceSource = buildLuminanceSource(bArr, i, i2);
            final Result resultDecodeWithState = null;
            if (planarYUVLuminanceSourceBuildLuminanceSource != null) {
                try {
                    try {
                        try {
                            resultDecodeWithState = this.mMultiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSourceBuildLuminanceSource)));
                            multiFormatReader = this.mMultiFormatReader;
                        } finally {
                            this.mMultiFormatReader.reset();
                        }
                    } catch (NullPointerException unused) {
                        multiFormatReader = this.mMultiFormatReader;
                    }
                } catch (ReaderException unused2) {
                    multiFormatReader = this.mMultiFormatReader;
                } catch (ArrayIndexOutOfBoundsException unused3) {
                    multiFormatReader = this.mMultiFormatReader;
                }
                multiFormatReader.reset();
                if (resultDecodeWithState == null) {
                    try {
                        resultDecodeWithState = this.mMultiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSourceBuildLuminanceSource.invert())));
                        multiFormatReader2 = this.mMultiFormatReader;
                    } catch (NotFoundException unused4) {
                        multiFormatReader2 = this.mMultiFormatReader;
                    } catch (Throwable th) {
                        throw th;
                    }
                    multiFormatReader2.reset();
                }
            }
            if (resultDecodeWithState != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: me.dm7.barcodescanner.zxing.ZXingScannerView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ResultHandler resultHandler = ZXingScannerView.this.mResultHandler;
                        ZXingScannerView.this.mResultHandler = null;
                        ZXingScannerView.this.stopCameraPreview();
                        if (resultHandler != null) {
                            resultHandler.handleResult(resultDecodeWithState);
                        }
                    }
                });
            } else {
                camera.setOneShotPreviewCallback(this);
            }
        } catch (RuntimeException e) {
            Log.e(TAG, e.toString(), e);
        }
    }

    public void resumeCameraPreview(ResultHandler resultHandler) throws IOException {
        this.mResultHandler = resultHandler;
        super.resumeCameraPreview();
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect framingRectInPreview = getFramingRectInPreview(i, i2);
        if (framingRectInPreview == null) {
            return null;
        }
        try {
            return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height(), false);
        } catch (Exception unused) {
            return null;
        }
    }
}
