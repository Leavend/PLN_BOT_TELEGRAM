package com.codekidlabs.storagechooser.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import at.markushi.ui.CircleButton;
import com.codekidlabs.storagechooser.Content;
import com.codekidlabs.storagechooser.R;
import com.codekidlabs.storagechooser.StorageChooser;
import com.codekidlabs.storagechooser.adapters.SecondaryChooserAdapter;
import com.codekidlabs.storagechooser.filters.UniversalFileFilter;
import com.codekidlabs.storagechooser.models.Config;
import com.codekidlabs.storagechooser.utils.DiskUtil;
import com.codekidlabs.storagechooser.utils.FileUtil;
import com.codekidlabs.storagechooser.utils.ResourceUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes5.dex */
public class SecondaryChooserFragment extends DialogFragment {
    private static final String EXTERNAL_STORAGE_TITLE = "ExtSD";
    private static final int FLAG_DISSMISS_INIT_DIALOG = 1;
    private static final int FLAG_DISSMISS_NORMAL = 0;
    private static final String INTERNAL_STORAGE_TITLE = "Internal Storage";
    private static boolean MODE_MULTIPLE = false;
    private static String mAddressClippedPath = "";
    private static String theSelectedPath = "";
    private List<String> customStoragesList;
    private FileUtil fileUtil;
    private boolean isFilePicker;
    private boolean isOpen;
    private boolean keyboardToggle;
    private ListView listView;
    private ImageButton mBackButton;
    private String mBundlePath;
    private Config mConfig;
    private ViewGroup mContainer;
    private Content mContent;
    private Context mContext;
    private Button mCreateButton;
    private EditText mFolderNameEditText;
    private Handler mHandler;
    private View mInactiveGradient;
    private View mLayout;
    private CircleButton mMultipleOnSelectButton;
    private ImageView mNewFolderImageView;
    private RelativeLayout mNewFolderView;
    private TextView mPathChosen;
    private ResourceUtil mResourceUtil;
    private Button mSelectButton;
    private int[] scheme;
    private SecondaryChooserAdapter secondaryChooserAdapter;
    private ArrayList<String> mMultipleModeList = new ArrayList<>();
    private View.OnClickListener mSelectButtonClickListener = new View.OnClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SecondaryChooserFragment.this.mConfig.isActionSave()) {
                DiskUtil.saveChooserPathPreference(SecondaryChooserFragment.this.mConfig.getPreference(), SecondaryChooserFragment.theSelectedPath);
            } else {
                Log.d("StorageChooser", "Chosen path: " + SecondaryChooserFragment.theSelectedPath);
            }
            StorageChooser.onSelectListener.onSelect(SecondaryChooserFragment.theSelectedPath);
            SecondaryChooserFragment.this.dissmissDialog(0);
        }
    };
    private View.OnClickListener mNewFolderButtonCloseListener = new View.OnClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            SecondaryChooserFragment.this.hideAddFolderView();
            SecondaryChooserFragment.this.hideKeyboard();
        }
    };
    private View.OnClickListener mNewFolderButtonClickListener = new View.OnClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            SecondaryChooserFragment.this.showAddFolderView();
        }
    };
    private String TAG = "StorageChooser";
    private View.OnClickListener mCreateButtonClickListener = new View.OnClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            if (SecondaryChooserFragment.this.validateFolderName()) {
                if (FileUtil.createDirectory(SecondaryChooserFragment.this.mFolderNameEditText.getText().toString().trim(), SecondaryChooserFragment.theSelectedPath)) {
                    Toast.makeText(SecondaryChooserFragment.this.mContext, SecondaryChooserFragment.this.mContent.getFolderCreatedToastText(), 0).show();
                    SecondaryChooserFragment.this.trimPopulate(SecondaryChooserFragment.theSelectedPath);
                    SecondaryChooserFragment.this.hideKeyboard();
                    SecondaryChooserFragment.this.hideAddFolderView();
                    return;
                }
                Toast.makeText(SecondaryChooserFragment.this.mContext, SecondaryChooserFragment.this.mContent.getFolderErrorToastText(), 0).show();
            }
        }
    };
    private AdapterView.OnItemClickListener mSingleModeClickListener = new AdapterView.OnItemClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.5
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
            SecondaryChooserFragment.this.mHandler.postDelayed(new Runnable() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.5.1
                @Override // java.lang.Runnable
                public void run() throws Resources.NotFoundException {
                    String str = SecondaryChooserFragment.theSelectedPath + InternalZipConstants.ZIP_FILE_SEPARATOR + ((String) SecondaryChooserFragment.this.customStoragesList.get(i));
                    if (FileUtil.isDir(str)) {
                        SecondaryChooserFragment.this.populateList(InternalZipConstants.ZIP_FILE_SEPARATOR + ((String) SecondaryChooserFragment.this.customStoragesList.get(i)));
                    } else {
                        StorageChooser.onSelectListener.onSelect(str);
                        SecondaryChooserFragment.this.dissmissDialog(0);
                    }
                }
            }, 300L);
        }
    };
    private AdapterView.OnItemLongClickListener mLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.6
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) throws Resources.NotFoundException {
            if (!FileUtil.isDir(SecondaryChooserFragment.theSelectedPath + InternalZipConstants.ZIP_FILE_SEPARATOR + ((String) SecondaryChooserFragment.this.customStoragesList.get(i)))) {
                boolean unused = SecondaryChooserFragment.MODE_MULTIPLE = true;
                SecondaryChooserFragment.this.listView.setOnItemClickListener(SecondaryChooserFragment.this.mMultipleModeClickListener);
                SecondaryChooserFragment.this.handleListMultipleAction(i, view);
            } else {
                SecondaryChooserFragment.this.populateList(InternalZipConstants.ZIP_FILE_SEPARATOR + ((String) SecondaryChooserFragment.this.customStoragesList.get(i)));
            }
            return true;
        }
    };
    private View.OnClickListener mBackButtonClickListener = new View.OnClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            SecondaryChooserFragment.this.performBackAction();
        }
    };
    private View.OnClickListener mMultipleModeDoneButtonClickListener = new View.OnClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            StorageChooser.onMultipleSelectListener.onDone(SecondaryChooserFragment.this.mMultipleModeList);
            SecondaryChooserFragment.this.bringBackSingleMode();
            SecondaryChooserFragment.this.dissmissDialog(0);
        }
    };
    private AdapterView.OnItemClickListener mMultipleModeClickListener = new AdapterView.OnItemClickListener() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.9
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) throws Resources.NotFoundException {
            if (!FileUtil.isDir(SecondaryChooserFragment.theSelectedPath + InternalZipConstants.ZIP_FILE_SEPARATOR + ((String) SecondaryChooserFragment.this.customStoragesList.get(i)))) {
                SecondaryChooserFragment.this.handleListMultipleAction(i, view);
            } else {
                SecondaryChooserFragment.this.bringBackSingleMode();
                SecondaryChooserFragment.this.populateList(InternalZipConstants.ZIP_FILE_SEPARATOR + ((String) SecondaryChooserFragment.this.customStoragesList.get(i)));
            }
        }
    };

    private boolean doesPassMemoryThreshold(long j, String str, long j2) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAddFolderView() throws Resources.NotFoundException {
        this.mNewFolderView.setVisibility(0);
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.anim_new_folder_view);
        this.mNewFolderView.startAnimation(animationLoadAnimation);
        this.mInactiveGradient.startAnimation(animationLoadAnimation);
        if (DiskUtil.isLollipopAndAbove()) {
            this.mNewFolderImageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.drawable_plus_to_close));
            ((Animatable) this.mNewFolderImageView.getDrawable()).start();
        }
        this.mNewFolderImageView.setOnClickListener(this.mNewFolderButtonCloseListener);
        SecondaryChooserAdapter.shouldEnable = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideAddFolderView() throws Resources.NotFoundException {
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.anim_close_folder_view);
        this.mNewFolderView.startAnimation(animationLoadAnimation);
        this.mNewFolderView.setVisibility(4);
        if (DiskUtil.isLollipopAndAbove()) {
            this.mNewFolderImageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.drawable_close_to_plus));
            ((Animatable) this.mNewFolderImageView.getDrawable()).start();
        }
        this.mNewFolderImageView.setOnClickListener(this.mNewFolderButtonClickListener);
        SecondaryChooserAdapter.shouldEnable = true;
        this.mInactiveGradient.startAnimation(animationLoadAnimation);
        this.mInactiveGradient.setVisibility(4);
    }

    private boolean isFolderViewVisible() {
        return this.mNewFolderView.getVisibility() == 0;
    }

    public void hideKeyboard() {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mFolderNameEditText.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performBackAction() throws Resources.NotFoundException {
        int iLastIndexOf = theSelectedPath.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR);
        if (MODE_MULTIPLE) {
            bringBackSingleMode();
            this.secondaryChooserAdapter.notifyDataSetChanged();
            return;
        }
        if (!this.mConfig.isSkipOverview()) {
            if (theSelectedPath.equals(this.mBundlePath)) {
                dismiss();
                this.mHandler.postDelayed(new Runnable() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.10
                    @Override // java.lang.Runnable
                    public void run() {
                        SecondaryChooserFragment.this.dissmissDialog(1);
                    }
                }, 200L);
                return;
            } else {
                theSelectedPath = theSelectedPath.substring(0, iLastIndexOf);
                Log.e("SCLib", "Performing back action: " + theSelectedPath);
                StorageChooser.LAST_SESSION_PATH = theSelectedPath;
                populateList("");
                return;
            }
        }
        dissmissDialog(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dissmissDialog(int i) {
        if (i == 0) {
            StorageChooser.LAST_SESSION_PATH = theSelectedPath;
            dismiss();
        } else {
            if (i != 1) {
                return;
            }
            new ChooserDialogFragment().show(this.mConfig.getFragmentManager(), "storagechooser_dialog");
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mContainer = viewGroup;
        if (getShowsDialog()) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }
        return getLayout(layoutInflater, viewGroup);
    }

    private View getLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) throws Resources.NotFoundException {
        Config config = StorageChooser.sConfig;
        this.mConfig = config;
        this.scheme = config.getScheme();
        this.mHandler = new Handler();
        if (this.mConfig.getContent() == null) {
            this.mContent = new Content();
        } else {
            this.mContent = this.mConfig.getContent();
        }
        this.mContext = getActivity().getApplicationContext();
        this.mResourceUtil = new ResourceUtil(this.mContext);
        View viewInflate = layoutInflater.inflate(R.layout.custom_storage_list, viewGroup, false);
        this.mLayout = viewInflate;
        initListView(this.mContext, viewInflate, this.mConfig.isShowMemoryBar());
        initUI();
        initNewFolderView();
        updateUI();
        return this.mLayout;
    }

    private void initUI() {
        this.mBackButton = (ImageButton) this.mLayout.findViewById(R.id.back_button);
        this.mSelectButton = (Button) this.mLayout.findViewById(R.id.select_button);
        this.mMultipleOnSelectButton = (CircleButton) this.mLayout.findViewById(R.id.multiple_selection_done_fab);
        this.mCreateButton = (Button) this.mLayout.findViewById(R.id.create_folder_button);
        RelativeLayout relativeLayout = (RelativeLayout) this.mLayout.findViewById(R.id.new_folder_view);
        this.mNewFolderView = relativeLayout;
        relativeLayout.setBackgroundColor(this.scheme[12]);
        this.mFolderNameEditText = (EditText) this.mLayout.findViewById(R.id.et_folder_name);
        this.mInactiveGradient = this.mLayout.findViewById(R.id.inactive_gradient);
        this.mLayout.findViewById(R.id.secondary_container).setBackgroundColor(this.scheme[7]);
    }

    private void updateUI() {
        this.mNewFolderView.setVisibility(4);
        this.mInactiveGradient.setVisibility(4);
        this.mFolderNameEditText.setHint(this.mContent.getTextfieldHintText());
        this.mFolderNameEditText.setHintTextColor(this.scheme[10]);
        this.mSelectButton.setText(this.mContent.getSelectLabel());
        this.mCreateButton.setText(this.mContent.getCreateLabel());
        this.mSelectButton.setTextColor(this.scheme[11]);
        this.mPathChosen.setTextColor(this.scheme[9]);
        if (this.mConfig.getHeadingFont() != null) {
            this.mPathChosen.setTypeface(ChooserDialogFragment.getSCTypeface(this.mContext, this.mConfig.getHeadingFont(), this.mConfig.isHeadingFromAssets()));
        }
        this.mNewFolderImageView.setImageTintList(ColorStateList.valueOf(this.scheme[9]));
        this.mBackButton.setImageTintList(ColorStateList.valueOf(this.scheme[9]));
        this.mMultipleOnSelectButton.setColor(this.scheme[13]);
        this.mLayout.findViewById(R.id.custom_path_header).setBackgroundColor(this.scheme[14]);
        this.mBackButton.setOnClickListener(this.mBackButtonClickListener);
        this.mSelectButton.setOnClickListener(this.mSelectButtonClickListener);
        this.mCreateButton.setOnClickListener(this.mCreateButtonClickListener);
        this.mMultipleOnSelectButton.setOnClickListener(this.mMultipleModeDoneButtonClickListener);
        if (this.mConfig.getSecondaryAction().equals(StorageChooser.FILE_PICKER)) {
            this.mSelectButton.setVisibility(8);
            setBottomNewFolderView();
        }
    }

    private void setBottomNewFolderView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics()));
        layoutParams.addRule(12);
        this.mNewFolderView.setLayoutParams(layoutParams);
    }

    private void initNewFolderView() {
        RelativeLayout relativeLayout = (RelativeLayout) this.mLayout.findViewById(R.id.new_folder_button_holder);
        ImageView imageView = (ImageView) this.mLayout.findViewById(R.id.new_folder_iv);
        this.mNewFolderImageView = imageView;
        imageView.setOnClickListener(this.mNewFolderButtonClickListener);
        if (this.mConfig.isAllowAddFolder()) {
            return;
        }
        relativeLayout.setVisibility(8);
    }

    private void initListView(Context context, View view, boolean z) throws Resources.NotFoundException {
        this.listView = (ListView) view.findViewById(R.id.storage_list_view);
        this.mPathChosen = (TextView) view.findViewById(R.id.path_chosen);
        this.mBundlePath = getArguments().getString(DiskUtil.SC_PREFERENCE_KEY);
        this.isFilePicker = getArguments().getBoolean(DiskUtil.SC_CHOOSER_FLAG, false);
        populateList(this.mBundlePath);
        SecondaryChooserAdapter secondaryChooserAdapter = new SecondaryChooserAdapter(this.customStoragesList, context, this.scheme, this.mConfig.getListFont(), this.mConfig.isListFromAssets());
        this.secondaryChooserAdapter = secondaryChooserAdapter;
        secondaryChooserAdapter.setPrefixPath(theSelectedPath);
        this.listView.setAdapter((ListAdapter) this.secondaryChooserAdapter);
        SecondaryChooserAdapter.shouldEnable = true;
        this.listView.setOnItemClickListener(this.mSingleModeClickListener);
        if (this.isFilePicker && this.mConfig.isMultiSelect()) {
            this.listView.setOnItemLongClickListener(this.mLongClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleListMultipleAction(int i, View view) throws Resources.NotFoundException {
        String str = theSelectedPath + InternalZipConstants.ZIP_FILE_SEPARATOR + this.customStoragesList.get(i);
        if (!this.secondaryChooserAdapter.selectedPaths.contains(Integer.valueOf(i))) {
            view.setBackgroundColor(this.mResourceUtil.getPrimaryColorWithAlpha());
            this.secondaryChooserAdapter.selectedPaths.add(Integer.valueOf(i));
            this.mMultipleModeList.add(str);
        } else {
            this.secondaryChooserAdapter.selectedPaths.remove(this.secondaryChooserAdapter.selectedPaths.indexOf(Integer.valueOf(i)));
            view.setBackgroundColor(this.scheme[7]);
            ArrayList<String> arrayList = this.mMultipleModeList;
            arrayList.remove(arrayList.indexOf(str));
        }
        if (this.mMultipleOnSelectButton.getVisibility() != 0 && MODE_MULTIPLE) {
            playTheMultipleButtonAnimation();
        }
        if (this.listView.getOnItemLongClickListener() != null && MODE_MULTIPLE) {
            this.listView.setOnItemLongClickListener(null);
        }
        if (this.mMultipleModeList.size() == 0) {
            bringBackSingleMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bringBackSingleMode() throws Resources.NotFoundException {
        MODE_MULTIPLE = false;
        this.listView.setOnItemClickListener(this.mSingleModeClickListener);
        this.mMultipleModeList.clear();
        this.secondaryChooserAdapter.selectedPaths.clear();
        playTheMultipleButtonEndAnimation();
        this.listView.setOnItemLongClickListener(this.mLongClickListener);
    }

    private void evaluateAction(int i) throws Resources.NotFoundException {
        String predefinedPath = this.mConfig.getPredefinedPath();
        boolean zIsAllowCustomPath = this.mConfig.isAllowCustomPath();
        if (predefinedPath == null) {
            Log.w(this.TAG, "No predefined path set");
        } else if (zIsAllowCustomPath) {
            populateList(InternalZipConstants.ZIP_FILE_SEPARATOR + this.customStoragesList.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateList(String str) throws Resources.NotFoundException {
        File[] fileArrListFilesAsDir;
        List<String> list = this.customStoragesList;
        if (list == null) {
            this.customStoragesList = new ArrayList();
        } else {
            list.clear();
        }
        this.fileUtil = new FileUtil();
        theSelectedPath += str;
        SecondaryChooserAdapter secondaryChooserAdapter = this.secondaryChooserAdapter;
        if (secondaryChooserAdapter != null && secondaryChooserAdapter.getPrefixPath() != null) {
            this.secondaryChooserAdapter.setPrefixPath(theSelectedPath);
        }
        int length = theSelectedPath.length();
        if (length >= 25) {
            int slashCount = getSlashCount(theSelectedPath);
            if (slashCount > 2) {
                String str2 = theSelectedPath;
                mAddressClippedPath = str2.substring(str2.indexOf(InternalZipConstants.ZIP_FILE_SEPARATOR, str2.indexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 2), length);
            } else if (slashCount <= 2) {
                String str3 = theSelectedPath;
                mAddressClippedPath = str3.substring(str3.indexOf(InternalZipConstants.ZIP_FILE_SEPARATOR, str3.indexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 2), length);
            }
        } else {
            mAddressClippedPath = theSelectedPath;
        }
        if (this.isFilePicker) {
            if (this.mConfig.isCustomFilter()) {
                fileArrListFilesAsDir = new File(theSelectedPath).listFiles(new UniversalFileFilter(this.mConfig.isCustomFilter(), this.mConfig.getCustomEnum()));
            } else if (this.mConfig.getSingleFilter() != null) {
                fileArrListFilesAsDir = new File(theSelectedPath).listFiles(new UniversalFileFilter(this.mConfig.getSingleFilter()));
            } else {
                fileArrListFilesAsDir = this.fileUtil.listFilesInDir(theSelectedPath);
            }
        } else {
            fileArrListFilesAsDir = this.fileUtil.listFilesAsDir(theSelectedPath);
        }
        Log.e("SCLib", theSelectedPath);
        if (fileArrListFilesAsDir != null) {
            for (File file : fileArrListFilesAsDir) {
                if (this.mConfig.isShowHidden()) {
                    this.customStoragesList.add(file.getName());
                } else if (!file.getName().startsWith(".")) {
                    this.customStoragesList.add(file.getName());
                }
            }
            Collections.sort(this.customStoragesList, new Comparator<String>() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.11
                @Override // java.util.Comparator
                public int compare(String str4, String str5) {
                    return str4.compareToIgnoreCase(str5);
                }
            });
        } else {
            this.customStoragesList.clear();
        }
        SecondaryChooserAdapter secondaryChooserAdapter2 = this.secondaryChooserAdapter;
        if (secondaryChooserAdapter2 != null) {
            secondaryChooserAdapter2.notifyDataSetChanged();
        }
        playTheAddressBarAnimation();
        if (!this.mConfig.isResumeSession() || StorageChooser.LAST_SESSION_PATH == null) {
            return;
        }
        if (StorageChooser.LAST_SESSION_PATH.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            this.mBundlePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            Log.e("Bundle_Path_Length", StorageChooser.LAST_SESSION_PATH);
            this.mBundlePath = StorageChooser.LAST_SESSION_PATH.substring(StorageChooser.LAST_SESSION_PATH.indexOf(InternalZipConstants.ZIP_FILE_SEPARATOR, 16), StorageChooser.LAST_SESSION_PATH.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trimPopulate(String str) {
        File[] fileArrListFilesAsDir;
        List<String> list = this.customStoragesList;
        if (list == null) {
            this.customStoragesList = new ArrayList();
        } else {
            list.clear();
        }
        if (this.isFilePicker) {
            fileArrListFilesAsDir = this.fileUtil.listFilesInDir(theSelectedPath);
        } else {
            fileArrListFilesAsDir = this.fileUtil.listFilesAsDir(theSelectedPath);
        }
        Log.e("SCLib", theSelectedPath);
        if (fileArrListFilesAsDir != null) {
            for (File file : fileArrListFilesAsDir) {
                if (!file.getName().startsWith(".")) {
                    this.customStoragesList.add(file.getName());
                }
            }
            Collections.sort(this.customStoragesList, new Comparator<String>() { // from class: com.codekidlabs.storagechooser.fragments.SecondaryChooserFragment.12
                @Override // java.util.Comparator
                public int compare(String str2, String str3) {
                    return str2.compareToIgnoreCase(str3);
                }
            });
        } else {
            this.customStoragesList.clear();
        }
        SecondaryChooserAdapter secondaryChooserAdapter = this.secondaryChooserAdapter;
        if (secondaryChooserAdapter != null) {
            secondaryChooserAdapter.setPrefixPath(str);
            this.secondaryChooserAdapter.notifyDataSetChanged();
        }
    }

    private void playTheAddressBarAnimation() throws Resources.NotFoundException {
        this.mPathChosen.setText(mAddressClippedPath);
        this.mPathChosen.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.anim_address_bar));
    }

    private void playTheMultipleButtonAnimation() throws Resources.NotFoundException {
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.anim_multiple_button);
        this.mMultipleOnSelectButton.setVisibility(0);
        this.mMultipleOnSelectButton.startAnimation(animationLoadAnimation);
    }

    private void playTheMultipleButtonEndAnimation() throws Resources.NotFoundException {
        this.mMultipleOnSelectButton.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.anim_multiple_button_end));
        this.mMultipleOnSelectButton.setVisibility(4);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = StorageChooser.dialog;
        dialog.setContentView(getLayout(LayoutInflater.from(getActivity().getApplicationContext()), this.mContainer));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        dialog.getWindow().setAttributes(layoutParams);
        return dialog;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        theSelectedPath = "";
        mAddressClippedPath = "";
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        StorageChooser.LAST_SESSION_PATH = theSelectedPath;
        theSelectedPath = "";
        mAddressClippedPath = "";
        StorageChooser.onCancelListener.onCancel();
    }

    private int getSlashCount(String str) {
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c == '/') {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean validateFolderName() {
        if (!this.mFolderNameEditText.getText().toString().trim().isEmpty()) {
            return true;
        }
        this.mFolderNameEditText.setError(this.mContent.getTextfieldErrorText());
        return false;
    }
}
