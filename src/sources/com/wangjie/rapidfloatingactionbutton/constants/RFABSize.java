package com.wangjie.rapidfloatingactionbutton.constants;

/* loaded from: classes2.dex */
public enum RFABSize {
    NORMAL(0, 56),
    MINI(1, 40);

    int code;
    int dpSize;

    RFABSize(int i, int i2) {
        this.code = i;
        this.dpSize = i2;
    }

    public static RFABSize getRFABSizeByCode(int i) {
        for (RFABSize rFABSize : values()) {
            if (i == rFABSize.code) {
                return rFABSize;
            }
        }
        return NORMAL;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public int getDpSize() {
        return this.dpSize;
    }

    public void setDpSize(int i) {
        this.dpSize = i;
    }
}
