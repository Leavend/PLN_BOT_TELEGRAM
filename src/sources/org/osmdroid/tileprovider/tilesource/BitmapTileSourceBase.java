package org.osmdroid.tileprovider.tilesource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import org.osmdroid.api.IMapView;
import org.osmdroid.tileprovider.BitmapPool;
import org.osmdroid.tileprovider.ReusableBitmapDrawable;
import org.osmdroid.tileprovider.util.Counters;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public abstract class BitmapTileSourceBase implements ITileSource {
    private static int globalOrdinal;
    protected String mCopyright;
    protected final String mImageFilenameEnding;
    private final int mMaximumZoomLevel;
    private final int mMinimumZoomLevel;
    protected String mName;
    private final int mOrdinal;
    private final int mTileSizePixels;
    protected final Random random;

    public BitmapTileSourceBase(String str, int i, int i2, int i3, String str2) {
        this(str, i, i2, i3, str2, null);
    }

    public BitmapTileSourceBase(String str, int i, int i2, int i3, String str2, String str3) {
        this.random = new Random();
        int i4 = globalOrdinal;
        globalOrdinal = i4 + 1;
        this.mOrdinal = i4;
        this.mName = str;
        this.mMinimumZoomLevel = i;
        this.mMaximumZoomLevel = i2;
        this.mTileSizePixels = i3;
        this.mImageFilenameEnding = str2;
        this.mCopyright = str3;
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public int ordinal() {
        return this.mOrdinal;
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public String name() {
        return this.mName;
    }

    public String pathBase() {
        return this.mName;
    }

    public String imageFilenameEnding() {
        return this.mImageFilenameEnding;
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public int getMinimumZoomLevel() {
        return this.mMinimumZoomLevel;
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public int getMaximumZoomLevel() {
        return this.mMaximumZoomLevel;
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public int getTileSizePixels() {
        return this.mTileSizePixels;
    }

    public String toString() {
        return name();
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public Drawable getDrawable(String str) throws LowMemoryException {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i = options.outHeight;
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            BitmapPool.getInstance().applyReusableOptions(options2, i, i);
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options2);
            if (bitmapDecodeFile != null) {
                return new ReusableBitmapDrawable(bitmapDecodeFile);
            }
            if (new File(str).exists()) {
                Log.d(IMapView.LOGTAG, str + " is an invalid image file, deleting...");
                try {
                    new File(str).delete();
                    return null;
                } catch (Throwable th) {
                    Log.e(IMapView.LOGTAG, "Error deleting invalid file: " + str, th);
                    return null;
                }
            }
            Log.d(IMapView.LOGTAG, "Request tile: " + str + " does not exist");
            return null;
        } catch (Exception e) {
            Log.e(IMapView.LOGTAG, "Unexpected error loading bitmap: " + str, e);
            Counters.tileDownloadErrors++;
            System.gc();
            return null;
        } catch (OutOfMemoryError e2) {
            Log.e(IMapView.LOGTAG, "OutOfMemoryError loading bitmap: " + str);
            System.gc();
            throw new LowMemoryException(e2);
        }
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public String getTileRelativeFilenameString(long j) {
        return pathBase() + '/' + MapTileIndex.getZoom(j) + '/' + MapTileIndex.getX(j) + '/' + MapTileIndex.getY(j) + imageFilenameEnding();
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public Drawable getDrawable(InputStream inputStream) throws LowMemoryException, IOException {
        try {
            int i = this.mTileSizePixels;
            if (inputStream.markSupported()) {
                inputStream.mark(1048576);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, null, options);
                i = options.outHeight;
                inputStream.reset();
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            BitmapPool.getInstance().applyReusableOptions(options2, i, i);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, null, options2);
            if (bitmapDecodeStream != null) {
                return new ReusableBitmapDrawable(bitmapDecodeStream);
            }
        } catch (Exception e) {
            Log.w(IMapView.LOGTAG, "#547 Error loading bitmap" + pathBase(), e);
        } catch (OutOfMemoryError e2) {
            Log.e(IMapView.LOGTAG, "OutOfMemoryError loading bitmap");
            System.gc();
            throw new LowMemoryException(e2);
        }
        return null;
    }

    public static final class LowMemoryException extends Exception {
        private static final long serialVersionUID = 146526524087765134L;

        public LowMemoryException(String str) {
            super(str);
        }

        public LowMemoryException(Throwable th) {
            super(th);
        }
    }

    @Override // org.osmdroid.tileprovider.tilesource.ITileSource
    public String getCopyrightNotice() {
        return this.mCopyright;
    }
}
