package com.wangjie.rapidfloatingactionbutton.widget;

import android.content.Context;
import com.wangjie.rapidfloatingactionbutton.constants.RFABSize;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class CircleButtonProperties implements Serializable {
    private int shadowColor;
    private int shadowDx;
    private int shadowDy;
    private int shadowRadius;
    private RFABSize standardSize;

    public RFABSize getStandardSize() {
        return this.standardSize;
    }

    public CircleButtonProperties setStandardSize(RFABSize rFABSize) {
        this.standardSize = rFABSize;
        return this;
    }

    public int getRealSizePx(Context context) {
        return getStandardSizePx(context) + (getShadowOffsetHalf() * 2);
    }

    public int getShadowOffsetHalf() {
        if (this.shadowRadius <= 0) {
            return 0;
        }
        return Math.max(this.shadowDx, this.shadowDy) + this.shadowRadius;
    }

    public int getStandardSizePx(Context context) {
        return RFABTextUtil.dip2px(context, this.standardSize.getDpSize());
    }

    public int getShadowColor() {
        return this.shadowColor;
    }

    public CircleButtonProperties setShadowColor(int i) {
        this.shadowColor = i;
        return this;
    }

    public float getShadowRadius() {
        return this.shadowRadius;
    }

    public CircleButtonProperties setShadowRadius(int i) {
        this.shadowRadius = i;
        return this;
    }

    public int getShadowDx() {
        return this.shadowDx;
    }

    public CircleButtonProperties setShadowDx(int i) {
        this.shadowDx = i;
        return this;
    }

    public int getShadowDy() {
        return this.shadowDy;
    }

    public CircleButtonProperties setShadowDy(int i) {
        this.shadowDy = i;
        return this;
    }
}
