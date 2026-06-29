package com.codekidlabs.storagechooser.filters;

import android.util.Log;
import com.codekidlabs.storagechooser.StorageChooser;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class UniversalFileFilter implements FileFilter {
    protected static final String TAG = "UniversalFileFilter";
    private final boolean allowDirectories;
    private ArrayList<String> customEnum;
    private boolean customEnumLock;
    private StorageChooser.FileType fileType;

    public UniversalFileFilter(StorageChooser.FileType fileType) {
        this.allowDirectories = true;
        this.customEnumLock = false;
        this.fileType = fileType;
    }

    public UniversalFileFilter(boolean z, ArrayList<String> arrayList) {
        this.allowDirectories = true;
        this.customEnumLock = z;
        this.customEnum = arrayList;
    }

    private String getFileExtension(File file) {
        return getFileExtension(file.getName());
    }

    private String getFileExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf > 0) {
            return str.substring(iLastIndexOf + 1);
        }
        return null;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        if (file.isHidden() || !file.canRead()) {
            return false;
        }
        if (file.isDirectory()) {
            return findInDirectory(file);
        }
        return isFileExtension(file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFileExtension(File file) {
        String fileExtension = getFileExtension(file);
        if (fileExtension == null) {
            return false;
        }
        if (this.customEnumLock) {
            return this.customEnum.contains(fileExtension);
        }
        return getFormatExtention(fileExtension) != null;
    }

    /* renamed from: com.codekidlabs.storagechooser.filters.UniversalFileFilter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$codekidlabs$storagechooser$StorageChooser$FileType;

        static {
            int[] iArr = new int[StorageChooser.FileType.values().length];
            $SwitchMap$com$codekidlabs$storagechooser$StorageChooser$FileType = iArr;
            try {
                iArr[StorageChooser.FileType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$codekidlabs$storagechooser$StorageChooser$FileType[StorageChooser.FileType.AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$codekidlabs$storagechooser$StorageChooser$FileType[StorageChooser.FileType.IMAGES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$codekidlabs$storagechooser$StorageChooser$FileType[StorageChooser.FileType.DOCS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$codekidlabs$storagechooser$StorageChooser$FileType[StorageChooser.FileType.ARCHIVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Object getFormatExtention(String str) {
        int i = AnonymousClass2.$SwitchMap$com$codekidlabs$storagechooser$StorageChooser$FileType[this.fileType.ordinal()];
        if (i == 1) {
            return VideoFormat.valueOf(str.toUpperCase());
        }
        if (i == 2) {
            return AudioFormat.valueOf(str.toUpperCase());
        }
        if (i == 3) {
            return ImageFormat.valueOf(str.toUpperCase());
        }
        if (i == 4) {
            return DocsFormat.valueOf(str.toUpperCase());
        }
        if (i != 5) {
            return null;
        }
        return ArchiveFormat.valueOf(str.toUpperCase());
    }

    private boolean findInDirectory(File file) {
        final ArrayList arrayList = new ArrayList();
        int length = file.listFiles(new FileFilter() { // from class: com.codekidlabs.storagechooser.filters.UniversalFileFilter.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                if (file2.isFile()) {
                    if (file2.getName().equals(".nomedia")) {
                        return false;
                    }
                    return UniversalFileFilter.this.isFileExtension(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.add(file2);
                }
                return false;
            }
        }).length;
        if (length > 0) {
            Log.i(TAG, "findInDirectory => " + file.getName() + " return true for => " + length);
            return true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File file2 = (File) it.next();
            if (findInDirectory(file2)) {
                Log.i(TAG, "findInDirectory => " + file2.toString());
                return true;
            }
        }
        return false;
    }

    public enum ArchiveFormat {
        ZIP("zip"),
        RAR("rar");

        private String filesuffix;

        ArchiveFormat(String str) {
            this.filesuffix = str;
        }
    }

    public enum ImageFormat {
        JPG("jpg"),
        JPEG("jpeg"),
        PNG("png"),
        TIFF("tiff"),
        GIF("gif");

        private String filesuffix;

        ImageFormat(String str) {
            this.filesuffix = str;
        }
    }

    public enum VideoFormat {
        MP4("mp4"),
        TS("ts"),
        MKV("mkv"),
        AVI("avi"),
        FLV("flv");

        private String filesuffix;

        VideoFormat(String str) {
            this.filesuffix = str;
        }
    }

    public enum AudioFormat {
        MP3("mp3"),
        OGG("ogg");

        private String filesuffix;

        AudioFormat(String str) {
            this.filesuffix = str;
        }
    }

    public enum DocsFormat {
        PDF("pdf"),
        PPT("ppt"),
        DOC("doc"),
        DOCX("docx"),
        EXCEL("xls");

        private String filesuffix;

        DocsFormat(String str) {
            this.filesuffix = str;
        }
    }
}
