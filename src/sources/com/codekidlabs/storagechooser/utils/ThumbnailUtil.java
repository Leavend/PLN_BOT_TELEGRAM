package com.codekidlabs.storagechooser.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.codekidlabs.storagechooser.R;

/* loaded from: classes5.dex */
public class ThumbnailUtil {
    private static final String APK_FILE = "apk";
    private static final String AUDIO_FILE = "mp3";
    private static final String CR_DL_FILE = "crdownload";
    private static final String CSS_FILE = "css";
    private static final String CSV_FILE = "csv";
    private static final String DOC_FILE = "doc";
    private static final String EXCEL_FILE = "xls";
    private static final String GIF_FILE = "gif";
    private static final String HTML_FILE = "html";
    private static final String JPEG_FILE = "jpeg";
    private static final String JPG_FILE = "jpg";
    private static final String LOG_FILE = "log";
    private static final String OTF_FILE = "otf";
    private static final String PDF_FILE = "pdf";
    private static final String PHP_FILE = "php";
    private static final String PNG_FILE = "png";
    private static final String PPT_FILE = "ppt";
    private static final String PROP_FILE = "prop";
    private static final String RAR_FILE = "rar";
    private static final String TAR_FILE = "tar";
    private static final String TAR_GZ_FILE = "gz";
    private static final String TEXT_FILE = "txt";
    private static final String TORRENtT_FILE = "torrent";
    private static final String TTF_FILE = "ttf";
    private static final String VIDEO_AVI_FILE = "avi";
    private static final String VIDEO_FILE = "mp4";
    private static final String VIDEO_MKV_FILE = "mkv";
    private static final String VIDEO_MOV_FILE = "mov";
    private static final String ZIP_FILE = "zip";
    private Context mContext;

    public ThumbnailUtil(Context context) {
        this.mContext = context;
    }

    public void init(ImageView imageView, String str) {
        thumbnailPipe(imageView, str);
    }

    private void thumbnailPipe(ImageView imageView, String str) {
        String extension = getExtension(str);
        extension.hashCode();
        switch (extension) {
            case "torrent":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.torrent));
                break;
            case "crdownload":
            case "css":
            case "php":
            case "html":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.web));
                break;
            case "gz":
            case "rar":
            case "tar":
            case "zip":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.zip));
                break;
            case "apk":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.apk));
                break;
            case "avi":
            case "mkv":
            case "mp4":
            case "mov":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.mov));
                break;
            case "csv":
            case "doc":
            case "log":
            case "txt":
            case "prop":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.doc));
                break;
            case "gif":
            case "jpg":
            case "png":
            case "jpeg":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.pic));
                break;
            case "mp3":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.music));
                break;
            case "otf":
            case "ttf":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.font));
                break;
            case "pdf":
                imageView.setImageDrawable(getDrawableFromRes(R.drawable.pdf));
                break;
        }
    }

    private Drawable getDrawableFromRes(int i) {
        return ContextCompat.getDrawable(this.mContext, i);
    }

    private String getExtension(String str) {
        return str.substring(str.lastIndexOf(".") + 1, str.length());
    }
}
