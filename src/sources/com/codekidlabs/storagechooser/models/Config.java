package com.codekidlabs.storagechooser.models;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import com.codekidlabs.storagechooser.Content;
import com.codekidlabs.storagechooser.StorageChooser;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class Config {
    private boolean actionSave;
    private boolean allowAddFolder;
    private boolean allowCustomPath;
    private boolean applyThreshold;
    private Content content;
    private ArrayList<String> customEnum;
    private boolean customFilter;
    private String dialogTitle;
    private FragmentManager fragmentManager;
    private String headingFont;
    private boolean headingFromAssets;
    private String internalStorageText;
    private boolean isGridView;
    private String listFont;
    private boolean listFromAssets;
    private int memoryThreshold;
    private float memorybarHeight;
    private boolean multiSelect = true;
    private String predefinedPath;
    private SharedPreferences preference;
    private String primaryPath;
    private boolean resumeSession;
    private int[] scheme;
    private String secondaryAction;
    private boolean showHidden;
    private boolean showMemoryBar;
    private StorageChooser.FileType singleFilter;
    private boolean skipOverview;
    private String thresholdSuffix;

    public FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public String getPredefinedPath() {
        return this.predefinedPath;
    }

    public void setPredefinedPath(String str) {
        this.predefinedPath = str;
    }

    public boolean isShowMemoryBar() {
        return this.showMemoryBar;
    }

    public void setShowMemoryBar(boolean z) {
        this.showMemoryBar = z;
    }

    public boolean isActionSave() {
        return this.actionSave;
    }

    public void setActionSave(boolean z) {
        this.actionSave = z;
    }

    public SharedPreferences getPreference() {
        return this.preference;
    }

    public void setPreference(SharedPreferences sharedPreferences) {
        this.preference = sharedPreferences;
    }

    public int getMemoryThreshold() {
        return this.memoryThreshold;
    }

    public void setMemoryThreshold(int i) {
        this.memoryThreshold = i;
    }

    public String getThresholdSuffix() {
        return this.thresholdSuffix;
    }

    public void setThresholdSuffix(String str) {
        this.thresholdSuffix = str;
    }

    public String getDialogTitle() {
        return this.dialogTitle;
    }

    public void setDialogTitle(String str) {
        this.dialogTitle = str;
    }

    public String getInternalStorageText() {
        return this.internalStorageText;
    }

    public void setInternalStorageText(String str) {
        this.internalStorageText = str;
    }

    public boolean isAllowCustomPath() {
        return this.allowCustomPath;
    }

    public void setAllowCustomPath(boolean z) {
        this.allowCustomPath = z;
    }

    public boolean isAllowAddFolder() {
        return this.allowAddFolder;
    }

    public void setAllowAddFolder(boolean z) {
        this.allowAddFolder = z;
    }

    public boolean isShowHidden() {
        return this.showHidden;
    }

    public void setShowHidden(boolean z) {
        this.showHidden = z;
    }

    public String getSecondaryAction() {
        return this.secondaryAction;
    }

    public void setSecondaryAction(String str) {
        this.secondaryAction = str;
    }

    public String getPrimaryPath() {
        return this.primaryPath;
    }

    public void setPrimaryPath(String str) {
        this.primaryPath = str;
    }

    public boolean isSkipOverview() {
        return this.skipOverview;
    }

    public void setSkipOverview(boolean z) {
        this.skipOverview = z;
    }

    public boolean isApplyThreshold() {
        return this.applyThreshold;
    }

    public void setApplyThreshold(boolean z) {
        this.applyThreshold = z;
    }

    public Content getContent() {
        return this.content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public StorageChooser.FileType getSingleFilter() {
        return this.singleFilter;
    }

    public void setSingleFilter(StorageChooser.FileType fileType) {
        this.singleFilter = fileType;
    }

    public ArrayList<String> getCustomEnum() {
        return this.customEnum;
    }

    public void setCustomEnum(ArrayList<String> arrayList) {
        this.customEnum = arrayList;
    }

    public boolean isCustomFilter() {
        return this.customFilter;
    }

    public void setCustomFilter(boolean z) {
        this.customFilter = z;
    }

    public boolean isGridView() {
        return this.isGridView;
    }

    public void setGridView(boolean z) {
        this.isGridView = z;
    }

    public boolean isResumeSession() {
        return this.resumeSession;
    }

    public void setResumeSession(boolean z) {
        this.resumeSession = z;
    }

    public int[] getScheme() {
        return this.scheme;
    }

    public void setScheme(int[] iArr) {
        this.scheme = iArr;
    }

    public float getMemorybarHeight() {
        return this.memorybarHeight;
    }

    public void setMemorybarHeight(float f) {
        this.memorybarHeight = f;
    }

    public String getHeadingFont() {
        return this.headingFont;
    }

    public void setHeadingFont(String str) {
        this.headingFont = str;
    }

    public String getListFont() {
        return this.listFont;
    }

    public void setListFont(String str) {
        this.listFont = str;
    }

    public boolean isHeadingFromAssets() {
        return this.headingFromAssets;
    }

    public void setHeadingFromAssets(boolean z) {
        this.headingFromAssets = z;
    }

    public boolean isListFromAssets() {
        return this.listFromAssets;
    }

    public void setListFromAssets(boolean z) {
        this.listFromAssets = z;
    }

    public boolean isMultiSelect() {
        return this.multiSelect;
    }

    public void setMultiSelect(boolean z) {
        this.multiSelect = z;
    }
}
