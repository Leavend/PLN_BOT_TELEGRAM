package com.codekidlabs.storagechooser.adapters;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import com.codekidlabs.storagechooser.R;
import com.codekidlabs.storagechooser.animators.MemorybarAnimation;
import com.codekidlabs.storagechooser.exceptions.MemoryNotAccessibleException;
import com.codekidlabs.storagechooser.fragments.ChooserDialogFragment;
import com.codekidlabs.storagechooser.models.Storages;
import com.codekidlabs.storagechooser.utils.DiskUtil;
import com.codekidlabs.storagechooser.utils.MemoryUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class StorageChooserListAdapter extends BaseAdapter {
    private static int memoryPercentile;
    private boolean fromAssets;
    private String listTypeface;
    private Context mContext;
    private ProgressBar memoryBar;
    private float memorybarHeight;
    private int[] scheme;
    private boolean shouldShowMemoryBar;
    private List<Storages> storagesList;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public StorageChooserListAdapter(List<Storages> list, Context context, boolean z, int[] iArr, float f, String str, boolean z2) {
        this.storagesList = list;
        this.mContext = context;
        this.shouldShowMemoryBar = z;
        this.scheme = iArr;
        this.memorybarHeight = f;
        this.listTypeface = str;
        this.fromAssets = z2;
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
        memoryPercentile = -1;
        View viewInflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.row_storage, viewGroup, false);
        TextView textView = (TextView) viewInflate.findViewById(R.id.storage_name);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.memory_status);
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.memory_bar);
        this.memoryBar = progressBar;
        progressBar.setScaleY(this.memorybarHeight);
        Storages storages = this.storagesList.get(i);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(storages.getStorageTitle() + " (" + storages.getMemoryTotalSize() + ")");
        spannableStringBuilder.setSpan(new StyleSpan(2), getSpannableIndex(spannableStringBuilder), spannableStringBuilder.length(), 33);
        String string = this.mContext.getString(R.string.text_freespace, storages.getMemoryAvailableSize());
        textView.setText(spannableStringBuilder);
        textView.setTextColor(this.scheme[3]);
        textView2.setText(string);
        String str = this.listTypeface;
        if (str != null) {
            textView.setTypeface(ChooserDialogFragment.getSCTypeface(this.mContext, str, this.fromAssets));
            textView2.setTypeface(ChooserDialogFragment.getSCTypeface(this.mContext, this.listTypeface, this.fromAssets));
        }
        textView2.setTextColor(this.scheme[4]);
        DrawableCompat.setTint(this.memoryBar.getProgressDrawable(), this.scheme[5]);
        try {
            memoryPercentile = getPercentile(storages.getStoragePath());
        } catch (MemoryNotAccessibleException e) {
            e.printStackTrace();
        }
        if (this.shouldShowMemoryBar && memoryPercentile != -1) {
            this.memoryBar.setMax(100);
            this.memoryBar.setProgress(memoryPercentile);
            runMemorybarAnimation(i);
        } else {
            this.memoryBar.setVisibility(8);
        }
        return viewInflate;
    }

    private void runMemorybarAnimation(int i) {
        MemorybarAnimation memorybarAnimation = new MemorybarAnimation(this.memoryBar, 0, memoryPercentile);
        memorybarAnimation.setDuration(500L);
        memorybarAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        if (i > 0) {
            memorybarAnimation.setStartOffset(300L);
        }
        this.memoryBar.startAnimation(memorybarAnimation);
    }

    private int getSpannableIndex(SpannableStringBuilder spannableStringBuilder) {
        return spannableStringBuilder.toString().indexOf("(") + 1;
    }

    private int getPercentile(String str) throws MemoryNotAccessibleException {
        MemoryUtil memoryUtil = new MemoryUtil();
        long availableMemorySize = memoryUtil.getAvailableMemorySize(str);
        long totalMemorySize = memoryUtil.getTotalMemorySize(str);
        if (totalMemorySize > 0) {
            return (int) (100 - ((availableMemorySize * 100) / totalMemorySize));
        }
        throw new MemoryNotAccessibleException("Cannot compute memory for " + str);
    }

    private long getMemoryFromString(String str) throws NumberFormatException {
        int i;
        if (str.contains(DiskUtil.IN_MB)) {
            i = Integer.parseInt(str.replace(",", "").replace(DiskUtil.IN_MB, ""));
        } else if (str.contains(DiskUtil.IN_GB)) {
            i = Integer.parseInt(str.replace(",", "").replace(DiskUtil.IN_GB, ""));
        } else {
            i = Integer.parseInt(str.replace(",", "").replace(DiskUtil.IN_KB, ""));
        }
        long j = i;
        Log.d("TAG", "Memory:" + j);
        return j;
    }
}
