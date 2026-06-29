package com.codekidlabs.storagechooser.adapters;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.codekidlabs.storagechooser.R;
import com.codekidlabs.storagechooser.fragments.ChooserDialogFragment;
import com.codekidlabs.storagechooser.utils.FileUtil;
import com.codekidlabs.storagechooser.utils.ResourceUtil;
import com.codekidlabs.storagechooser.utils.ThumbnailUtil;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes5.dex */
public class SecondaryChooserAdapter extends BaseAdapter {
    public static boolean shouldEnable = true;
    private boolean fromAssets;
    private String listTypeface;
    private Context mContext;
    public String prefixPath;
    private ResourceUtil resourceUtil;
    private int[] scheme;
    public ArrayList<Integer> selectedPaths = new ArrayList<>();
    private List<String> storagesList;
    private ThumbnailUtil thumbnailUtil;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SecondaryChooserAdapter(List<String> list, Context context, int[] iArr, String str, boolean z) {
        this.storagesList = list;
        this.mContext = context;
        this.scheme = iArr;
        this.listTypeface = str;
        this.fromAssets = z;
        this.thumbnailUtil = new ThumbnailUtil(context);
        this.resourceUtil = new ResourceUtil(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.storagesList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.storagesList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.row_custom_paths, viewGroup, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.path_folder_icon);
        if (FileUtil.isDir(this.prefixPath + InternalZipConstants.ZIP_FILE_SEPARATOR + this.storagesList.get(i))) {
            applyFolderTint(imageView);
        }
        this.thumbnailUtil.init(imageView, this.storagesList.get(i));
        TextView textView = (TextView) viewInflate.findViewById(R.id.storage_name);
        textView.setText(this.storagesList.get(i));
        String str = this.listTypeface;
        if (str != null) {
            textView.setTypeface(ChooserDialogFragment.getSCTypeface(this.mContext, str, this.fromAssets));
        }
        textView.setTextColor(this.scheme[8]);
        if (this.selectedPaths.contains(Integer.valueOf(i))) {
            viewInflate.setBackgroundColor(this.resourceUtil.getPrimaryColorWithAlpha());
        }
        return viewInflate;
    }

    private int getSpannableIndex(SpannableStringBuilder spannableStringBuilder) {
        return spannableStringBuilder.toString().indexOf("(") + 1;
    }

    public String getPrefixPath() {
        return this.prefixPath;
    }

    public void setPrefixPath(String str) {
        this.prefixPath = str;
    }

    private void applyFolderTint(ImageView imageView) {
        imageView.setColorFilter(this.scheme[6]);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return shouldEnable;
    }
}
