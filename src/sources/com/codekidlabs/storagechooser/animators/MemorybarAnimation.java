package com.codekidlabs.storagechooser.animators;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

/* loaded from: classes5.dex */
public class MemorybarAnimation extends Animation {
    private float from;
    private ProgressBar progressBar;
    private float to;

    public MemorybarAnimation(ProgressBar progressBar, int i, int i2) {
        this.progressBar = progressBar;
        this.from = i;
        this.to = i2;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        float f2 = this.from;
        this.progressBar.setProgress((int) (f2 + ((this.to - f2) * f)));
    }
}
