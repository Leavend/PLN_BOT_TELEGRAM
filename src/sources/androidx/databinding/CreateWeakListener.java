package androidx.databinding;

import java.lang.ref.ReferenceQueue;

/* loaded from: classes5.dex */
interface CreateWeakListener {
    WeakListener create(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue);
}
