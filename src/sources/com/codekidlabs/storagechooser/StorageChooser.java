package com.codekidlabs.storagechooser;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import com.codekidlabs.storagechooser.fragments.ChooserDialogFragment;
import com.codekidlabs.storagechooser.models.Config;
import com.codekidlabs.storagechooser.utils.DiskUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class StorageChooser {
    public static final String DIRECTORY_CHOOSER = "dir";
    public static final String FILE_PICKER = "file";
    public static String LAST_SESSION_PATH = null;
    public static final String NONE = "none";
    public static Dialog dialog;
    public static OnCancelListener onCancelListener;
    public static OnMultipleSelectListener onMultipleSelectListener;
    public static OnSelectListener onSelectListener;
    public static Config sConfig;
    private final String TAG = getClass().getName();
    private Activity chooserActivity;

    public enum FileType {
        VIDEO,
        AUDIO,
        DOCS,
        IMAGES,
        ARCHIVE
    }

    public interface OnCancelListener {
        void onCancel();
    }

    public interface OnMultipleSelectListener {
        void onDone(ArrayList<String> arrayList);
    }

    public interface OnSelectListener {
        void onSelect(String str);
    }

    StorageChooser(Activity activity, Config config) {
        setsConfig(config);
        setChooserActivity(activity);
    }

    public StorageChooser() {
    }

    public static Config getsConfig() {
        return sConfig;
    }

    public static void setsConfig(Config config) {
        sConfig = config;
    }

    private static Dialog getStorageChooserDialog(Activity activity) {
        return new Dialog(activity, R.style.DialogTheme);
    }

    public void show() {
        init();
    }

    private void init() {
        String str;
        dialog = getStorageChooserDialog(getChooserActivity());
        if (onSelectListener == null) {
            onSelectListener = getDefaultOnSelectListener();
        }
        if (onCancelListener == null) {
            onCancelListener = getDefaultOnCancelListener();
        }
        if (onMultipleSelectListener == null) {
            onMultipleSelectListener = getDefaultMultipleSelectionListener();
        }
        if (sConfig.isResumeSession() && (str = LAST_SESSION_PATH) != null) {
            DiskUtil.showSecondaryChooser(str, sConfig);
            return;
        }
        if (sConfig.isSkipOverview()) {
            if (sConfig.getPrimaryPath() == null) {
                DiskUtil.showSecondaryChooser(Environment.getExternalStorageDirectory().getAbsolutePath(), sConfig);
                return;
            } else {
                DiskUtil.showSecondaryChooser(sConfig.getPrimaryPath(), sConfig);
                return;
            }
        }
        new ChooserDialogFragment().show(sConfig.getFragmentManager(), "storagechooser_dialog");
    }

    public void setOnSelectListener(OnSelectListener onSelectListener2) {
        onSelectListener = onSelectListener2;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener2) {
        onCancelListener = onCancelListener2;
    }

    public void setOnMultipleSelectListener(OnMultipleSelectListener onMultipleSelectListener2) {
        onMultipleSelectListener = onMultipleSelectListener2;
    }

    private Activity getChooserActivity() {
        return this.chooserActivity;
    }

    private void setChooserActivity(Activity activity) {
        this.chooserActivity = activity;
    }

    private OnSelectListener getDefaultOnSelectListener() {
        return new OnSelectListener() { // from class: com.codekidlabs.storagechooser.StorageChooser.1
            @Override // com.codekidlabs.storagechooser.StorageChooser.OnSelectListener
            public void onSelect(String str) {
                Log.e(StorageChooser.this.TAG, "You need to setup OnSelectListener from your side. OUTPUT: " + str);
            }
        };
    }

    private OnCancelListener getDefaultOnCancelListener() {
        return new OnCancelListener() { // from class: com.codekidlabs.storagechooser.StorageChooser.2
            @Override // com.codekidlabs.storagechooser.StorageChooser.OnCancelListener
            public void onCancel() {
                Log.e(StorageChooser.this.TAG, "You need to setup OnCancelListener from your side. This is default OnCancelListener fired.");
            }
        };
    }

    private OnMultipleSelectListener getDefaultMultipleSelectionListener() {
        return new OnMultipleSelectListener() { // from class: com.codekidlabs.storagechooser.StorageChooser.3
            @Override // com.codekidlabs.storagechooser.StorageChooser.OnMultipleSelectListener
            public void onDone(ArrayList<String> arrayList) {
                Log.e(StorageChooser.this.TAG, "You need to setup OnMultipleSelectListener from your side. This is default OnMultipleSelectListener fired.");
            }
        };
    }

    public static class Builder {
        private Content content;
        private FileType filter;
        private Activity mActivity;
        private ArrayList<FileType> multipleFilter;
        private Theme theme;
        private String type;
        private boolean mActionSave = false;
        private boolean mShowMemoryBar = false;
        private boolean mAllowCustomPath = false;
        private boolean mAllowAddFolder = false;
        private boolean mShowHidden = false;
        private boolean mSkipOverview = false;
        private boolean mApplyMemoryThreshold = false;
        private boolean mShowInGrid = false;
        private boolean mResumeSession = false;
        private boolean mHeadingFromAssets = false;
        private boolean mListFromAssets = false;
        private float mMemorybarHeight = 2.0f;
        Config devConfig = new Config();

        public Builder withActivity(Activity activity) {
            this.mActivity = activity;
            return this;
        }

        public Builder withFragmentManager(FragmentManager fragmentManager) {
            this.devConfig.setFragmentManager(fragmentManager);
            return this;
        }

        public Builder withMemoryBar(boolean z) {
            this.mShowMemoryBar = z;
            return this;
        }

        public Builder setMemoryBarHeight(float f) {
            this.mMemorybarHeight = f;
            return this;
        }

        public Builder withPredefinedPath(String str) {
            this.devConfig.setPredefinedPath(str);
            return this;
        }

        public Builder applyMemoryThreshold(boolean z) {
            this.mApplyMemoryThreshold = z;
            return this;
        }

        public Builder withThreshold(int i, String str) {
            this.devConfig.setMemoryThreshold(i);
            this.devConfig.setThresholdSuffix(str);
            return this;
        }

        public Builder withPreference(SharedPreferences sharedPreferences) {
            this.devConfig.setPreference(sharedPreferences);
            return this;
        }

        public Builder actionSave(boolean z) {
            this.mActionSave = z;
            return this;
        }

        public Builder setDialogTitle(String str) {
            this.devConfig.setDialogTitle(str);
            return this;
        }

        public Builder setInternalStorageText(String str) {
            this.devConfig.setInternalStorageText(str);
            return this;
        }

        public Builder allowCustomPath(boolean z) {
            this.mAllowCustomPath = z;
            return this;
        }

        public Builder allowAddFolder(boolean z) {
            this.mAllowAddFolder = z;
            return this;
        }

        public Builder showHidden(boolean z) {
            this.mShowHidden = z;
            return this;
        }

        public Builder setType(String str) {
            this.type = str;
            return this;
        }

        public Builder setTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public Builder skipOverview(boolean z, String str) {
            this.mSkipOverview = z;
            this.devConfig.setPrimaryPath(str);
            return this;
        }

        public Builder skipOverview(boolean z) {
            this.mSkipOverview = z;
            return this;
        }

        public Builder withContent(Content content) {
            this.content = content;
            return this;
        }

        public Builder filter(FileType fileType) {
            this.filter = fileType;
            return this;
        }

        public Builder crunch() {
            this.devConfig.setCustomFilter(false);
            return this;
        }

        public Builder customFilter(ArrayList<String> arrayList) {
            this.devConfig.setCustomFilter(true);
            this.devConfig.setCustomEnum(arrayList);
            return this;
        }

        public Builder showFoldersInGrid(boolean z) {
            this.devConfig.setGridView(z);
            return this;
        }

        public Builder shouldResumeSession(boolean z) {
            this.mResumeSession = z;
            return this;
        }

        public Builder setHeadingTypeface(String str, boolean z) {
            this.devConfig.setHeadingFont(str);
            this.mHeadingFromAssets = z;
            return this;
        }

        public Builder setListTypeface(String str, boolean z) {
            this.devConfig.setListFont(str);
            this.mListFromAssets = z;
            return this;
        }

        public Builder disableMultiSelect() {
            this.devConfig.setMultiSelect(false);
            return this;
        }

        public StorageChooser build() {
            this.devConfig.setActionSave(this.mActionSave);
            this.devConfig.setShowMemoryBar(this.mShowMemoryBar);
            this.devConfig.setAllowCustomPath(this.mAllowCustomPath);
            this.devConfig.setAllowAddFolder(this.mAllowAddFolder);
            this.devConfig.setShowHidden(this.mShowHidden);
            this.devConfig.setSkipOverview(this.mSkipOverview);
            this.devConfig.setApplyThreshold(this.mApplyMemoryThreshold);
            this.devConfig.setResumeSession(this.mResumeSession);
            this.devConfig.setGridView(this.mShowInGrid);
            this.devConfig.setContent(this.content);
            this.devConfig.setSingleFilter(this.filter);
            this.devConfig.setMemorybarHeight(this.mMemorybarHeight);
            this.devConfig.setHeadingFromAssets(this.mHeadingFromAssets);
            this.devConfig.setListFromAssets(this.mListFromAssets);
            String str = this.type;
            if (str == null) {
                str = "none";
            }
            this.type = str;
            this.devConfig.setSecondaryAction(str);
            Theme theme = this.theme;
            if (theme == null || theme.getScheme() == null) {
                Theme theme2 = new Theme(this.mActivity);
                this.theme = theme2;
                this.devConfig.setScheme(theme2.getDefaultScheme());
            } else {
                this.devConfig.setScheme(this.theme.getScheme());
            }
            return new StorageChooser(this.mActivity, this.devConfig);
        }
    }

    public static class Theme {
        public static final int OVERVIEW_BG_INDEX = 2;
        public static final int OVERVIEW_HEADER_INDEX = 0;
        public static final int OVERVIEW_INDICATOR_INDEX = 4;
        public static final int OVERVIEW_MEMORYBAR_INDEX = 5;
        public static final int OVERVIEW_STORAGE_TEXT_INDEX = 3;
        public static final int OVERVIEW_TEXT_INDEX = 1;
        public static final int SEC_ADDRESS_BAR_BG = 14;
        public static final int SEC_ADDRESS_TINT_INDEX = 9;
        public static final int SEC_BG_INDEX = 7;
        public static final int SEC_DONE_FAB_INDEX = 13;
        public static final int SEC_FOLDER_CREATION_BG_INDEX = 12;
        public static final int SEC_FOLDER_TINT_INDEX = 6;
        public static final int SEC_HINT_TINT_INDEX = 10;
        public static final int SEC_SELECT_LABEL_INDEX = 11;
        public static final int SEC_TEXT_INDEX = 8;
        Context context;
        int[] scheme;

        public Theme(Context context) {
            this.context = context;
        }

        public int[] getDefaultScheme() {
            return this.context.getResources().getIntArray(R.array.default_light);
        }

        public int[] getDefaultDarkScheme() {
            return this.context.getResources().getIntArray(R.array.default_dark);
        }

        public int[] getScheme() {
            return this.scheme;
        }

        public void setScheme(int[] iArr) {
            this.scheme = iArr;
        }
    }
}
