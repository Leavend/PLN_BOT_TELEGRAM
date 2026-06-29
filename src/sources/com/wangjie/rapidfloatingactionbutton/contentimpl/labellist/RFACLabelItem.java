package com.wangjie.rapidfloatingactionbutton.contentimpl.labellist;

import android.graphics.drawable.Drawable;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class RFACLabelItem<T> implements Serializable {
    private Drawable drawable;
    private Integer iconNormalColor;
    private Integer iconPressedColor;
    private String label;
    private Drawable labelBackgroundDrawable;
    private Integer labelColor;
    private Integer labelSizeSp;
    private boolean labelTextBold;
    private int resId;
    private T wrapper;

    public RFACLabelItem() {
        this.resId = -1;
        this.labelTextBold = true;
    }

    public RFACLabelItem(int i, String str) {
        this.labelTextBold = true;
        this.resId = i;
        this.label = str;
    }

    public int getResId() {
        return this.resId;
    }

    public RFACLabelItem<T> setResId(int i) {
        this.resId = i;
        return this;
    }

    public String getLabel() {
        return this.label;
    }

    public RFACLabelItem<T> setLabel(String str) {
        this.label = str;
        return this;
    }

    public T getWrapper() {
        return this.wrapper;
    }

    public RFACLabelItem<T> setWrapper(T t) {
        this.wrapper = t;
        return this;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public RFACLabelItem<T> setDrawable(Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    public Integer getIconNormalColor() {
        return this.iconNormalColor;
    }

    public RFACLabelItem<T> setIconNormalColor(Integer num) {
        this.iconNormalColor = num;
        return this;
    }

    public Integer getIconPressedColor() {
        return this.iconPressedColor;
    }

    public RFACLabelItem<T> setIconPressedColor(Integer num) {
        this.iconPressedColor = num;
        return this;
    }

    public boolean isLabelTextBold() {
        return this.labelTextBold;
    }

    public RFACLabelItem<T> setLabelTextBold(boolean z) {
        this.labelTextBold = z;
        return this;
    }

    public Drawable getLabelBackgroundDrawable() {
        return this.labelBackgroundDrawable;
    }

    public RFACLabelItem<T> setLabelBackgroundDrawable(Drawable drawable) {
        this.labelBackgroundDrawable = drawable;
        return this;
    }

    public Integer getLabelColor() {
        return this.labelColor;
    }

    public RFACLabelItem<T> setLabelColor(Integer num) {
        this.labelColor = num;
        return this;
    }

    public Integer getLabelSizeSp() {
        return this.labelSizeSp;
    }

    public RFACLabelItem<T> setLabelSizeSp(Integer num) {
        this.labelSizeSp = num;
        return this;
    }
}
