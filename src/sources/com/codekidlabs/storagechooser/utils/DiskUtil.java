package com.codekidlabs.storagechooser.utils;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.codekidlabs.storagechooser.StorageChooser;
import com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment;
import com.codekidlabs.storagechooser.models.Config;

/* loaded from: classes5.dex */
public class DiskUtil {
    public static final String IN_GB = "GiB";
    public static final String IN_KB = "KiB";
    public static final String IN_MB = "MiB";
    public static String SC_CHOOSER_FLAG = "storage_chooser_type";
    public static final String SC_PREFERENCE_KEY = "storage_chooser_path";

    public static boolean isLollipopAndAbove() {
        return true;
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static void saveChooserPathPreference(SharedPreferences sharedPreferences, String str) {
        try {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(SC_PREFERENCE_KEY, str);
            editorEdit.apply();
        } catch (NullPointerException unused) {
            Log.e("StorageChooser", "No sharedPreference was supplied. Supply sharedPreferencesObject via withPreference() or disable saving with actionSave(false)");
        }
    }

    public static void showSecondaryChooser(String str, Config config) {
        Bundle bundle = new Bundle();
        bundle.putString(SC_PREFERENCE_KEY, str);
        String secondaryAction = config.getSecondaryAction();
        secondaryAction.hashCode();
        if (secondaryAction.equals(StorageChooser.DIRECTORY_CHOOSER)) {
            bundle.putBoolean(SC_CHOOSER_FLAG, false);
            SecondaryChooserFragment secondaryChooserFragment = new SecondaryChooserFragment();
            secondaryChooserFragment.setArguments(bundle);
            secondaryChooserFragment.show(config.getFragmentManager(), "custom_chooser");
            return;
        }
        if (secondaryAction.equals(StorageChooser.FILE_PICKER)) {
            bundle.putBoolean(SC_CHOOSER_FLAG, true);
            SecondaryChooserFragment secondaryChooserFragment2 = new SecondaryChooserFragment();
            secondaryChooserFragment2.setArguments(bundle);
            secondaryChooserFragment2.show(config.getFragmentManager(), "file_picker");
        }
    }
}
