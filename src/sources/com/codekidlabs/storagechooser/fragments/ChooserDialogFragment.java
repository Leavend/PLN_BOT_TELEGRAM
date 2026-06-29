package com.codekidlabs.storagechooser.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.codekidlabs.storagechooser.Content;
import com.codekidlabs.storagechooser.R;
import com.codekidlabs.storagechooser.StorageChooser;
import com.codekidlabs.storagechooser.adapters.StorageChooserListAdapter;
import com.codekidlabs.storagechooser.models.Config;
import com.codekidlabs.storagechooser.models.Storages;
import com.codekidlabs.storagechooser.utils.DiskUtil;
import com.codekidlabs.storagechooser.utils.FileUtil;
import com.codekidlabs.storagechooser.utils.MemoryUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes5.dex */
public class ChooserDialogFragment extends DialogFragment {
    private static final boolean BUILD_DEBUG = true;
    private List<String> customStoragesList;
    private int mChooserMode;
    private Config mConfig;
    private ViewGroup mContainer;
    private Content mContent;
    private Handler mHandler;
    private View mLayout;
    private List<Storages> storagesList;
    private String TAG = "StorageChooser";
    private MemoryUtil memoryUtil = new MemoryUtil();
    private FileUtil fileUtil = new FileUtil();

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mContainer = viewGroup;
        if (getShowsDialog()) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }
        return getLayout(layoutInflater, viewGroup);
    }

    private View getLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.mConfig = StorageChooser.sConfig;
        this.mHandler = new Handler();
        if (this.mConfig.getContent() == null) {
            this.mContent = new Content();
        } else {
            this.mContent = this.mConfig.getContent();
        }
        this.mLayout = layoutInflater.inflate(R.layout.storage_list, viewGroup, false);
        initListView(getActivity().getApplicationContext(), this.mLayout, this.mConfig.isShowMemoryBar());
        if (this.mContent.getOverviewHeading() != null) {
            TextView textView = (TextView) this.mLayout.findViewById(R.id.dialog_title);
            textView.setTextColor(this.mConfig.getScheme()[1]);
            textView.setText(this.mContent.getOverviewHeading());
            if (this.mConfig.getHeadingFont() != null) {
                textView.setTypeface(getSCTypeface(getActivity().getApplicationContext(), this.mConfig.getHeadingFont(), this.mConfig.isHeadingFromAssets()));
            }
        }
        this.mLayout.findViewById(R.id.header_container).setBackgroundColor(this.mConfig.getScheme()[0]);
        this.mLayout.findViewById(R.id.overview_container).setBackgroundColor(this.mConfig.getScheme()[2]);
        return this.mLayout;
    }

    private void initListView(Context context, View view, boolean z) {
        ListView listView = (ListView) view.findViewById(R.id.storage_list_view);
        populateList();
        listView.setAdapter((ListAdapter) new StorageChooserListAdapter(this.storagesList, context, z, this.mConfig.getScheme(), this.mConfig.getMemorybarHeight(), this.mConfig.getListFont(), this.mConfig.isListFromAssets()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.codekidlabs.storagechooser.fragments.ChooserDialogFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                final String strEvaluatePath = ChooserDialogFragment.this.evaluatePath(i);
                if (new File(strEvaluatePath).canRead()) {
                    if (ChooserDialogFragment.this.mConfig.isAllowCustomPath()) {
                        if (ChooserDialogFragment.this.mConfig.isApplyThreshold()) {
                            ChooserDialogFragment.this.startThresholdTest(i);
                        } else {
                            ChooserDialogFragment.this.mHandler.postDelayed(new Runnable() { // from class: com.codekidlabs.storagechooser.fragments.ChooserDialogFragment.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    DiskUtil.showSecondaryChooser(strEvaluatePath, ChooserDialogFragment.this.mConfig);
                                }
                            }, 250L);
                        }
                    } else if (ChooserDialogFragment.this.mConfig.isActionSave()) {
                        String predefinedPath = ChooserDialogFragment.this.mConfig.getPredefinedPath();
                        if (predefinedPath == null) {
                            Log.w(ChooserDialogFragment.this.TAG, "Predefined path is null set it by .withPredefinedPath() to builder. Saving root directory");
                            DiskUtil.saveChooserPathPreference(ChooserDialogFragment.this.mConfig.getPreference(), null);
                        } else {
                            if (!predefinedPath.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                                predefinedPath = InternalZipConstants.ZIP_FILE_SEPARATOR + predefinedPath;
                            }
                            DiskUtil.saveChooserPathPreference(ChooserDialogFragment.this.mConfig.getPreference(), strEvaluatePath + predefinedPath);
                        }
                    } else if (ChooserDialogFragment.this.mConfig.isApplyThreshold()) {
                        ChooserDialogFragment.this.startThresholdTest(i);
                    } else if (StorageChooser.onSelectListener != null) {
                        StorageChooser.onSelectListener.onSelect(strEvaluatePath);
                    }
                    ChooserDialogFragment.this.dismiss();
                    return;
                }
                Toast.makeText(ChooserDialogFragment.this.getActivity(), R.string.toast_not_readable, 0).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startThresholdTest(int i) {
        String thresholdSuffix = this.mConfig.getThresholdSuffix();
        if (thresholdSuffix != null) {
            long availableMemorySize = this.memoryUtil.getAvailableMemorySize(evaluatePath(i));
            if (doesPassThresholdTest(this.mConfig.getMemoryThreshold(), thresholdSuffix, availableMemorySize)) {
                DiskUtil.showSecondaryChooser(evaluatePath(i), this.mConfig);
                return;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.toast_threshold_breached, new Object[]{String.valueOf(this.memoryUtil.suffixedSize(availableMemorySize, thresholdSuffix)) + " " + thresholdSuffix}), 0).show();
                return;
            }
        }
        Log.e(this.TAG, "add .withThreshold(int size, String suffix) to your StorageChooser.Builder instance");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String evaluatePath(int i) {
        if (i == 0) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "/storage/" + this.storagesList.get(i).getStorageTitle();
    }

    private boolean doesPassThresholdTest(long j, String str, long j2) {
        return this.memoryUtil.suffixedSize(j2, str) > j;
    }

    private void populateList() {
        this.storagesList = new ArrayList();
        File file = new File("/storage");
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File[] fileArrListFiles = file.listFiles();
        Storages storages = new Storages();
        storages.setStorageTitle(this.mContent.getInternalStorageText());
        storages.setStoragePath(absolutePath);
        MemoryUtil memoryUtil = this.memoryUtil;
        storages.setMemoryTotalSize(memoryUtil.formatSize(memoryUtil.getTotalMemorySize(absolutePath)));
        MemoryUtil memoryUtil2 = this.memoryUtil;
        storages.setMemoryAvailableSize(memoryUtil2.formatSize(memoryUtil2.getAvailableMemorySize(absolutePath)));
        this.storagesList.add(storages);
        for (File file2 : fileArrListFiles) {
            if (!file2.getName().equals(MemoryUtil.SELF_DIR_NAME) && !file2.getName().equals(MemoryUtil.EMULATED_DIR_KNOX) && !file2.getName().equals(MemoryUtil.EMULATED_DIR_NAME) && !file2.getName().equals(MemoryUtil.SDCARD0_DIR_NAME) && !file2.getName().equals(MemoryUtil.CONTAINER)) {
                Storages storages2 = new Storages();
                String absolutePath2 = file2.getAbsolutePath();
                storages2.setStorageTitle(file2.getName());
                MemoryUtil memoryUtil3 = this.memoryUtil;
                storages2.setMemoryTotalSize(memoryUtil3.formatSize(memoryUtil3.getTotalMemorySize(absolutePath2)));
                MemoryUtil memoryUtil4 = this.memoryUtil;
                storages2.setMemoryAvailableSize(memoryUtil4.formatSize(memoryUtil4.getAvailableMemorySize(absolutePath2)));
                storages2.setStoragePath(absolutePath2);
                this.storagesList.add(storages2);
            }
        }
    }

    public static Typeface getSCTypeface(Context context, String str, boolean z) {
        if (z) {
            return Typeface.createFromAsset(context.getAssets(), str);
        }
        return Typeface.createFromFile(str);
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        StorageChooser.onCancelListener.onCancel();
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = StorageChooser.dialog;
        dialog.setContentView(getLayout(LayoutInflater.from(getActivity().getApplicationContext()), this.mContainer));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = -2;
        layoutParams.height = -2;
        dialog.getWindow().setAttributes(layoutParams);
        return dialog;
    }
}
