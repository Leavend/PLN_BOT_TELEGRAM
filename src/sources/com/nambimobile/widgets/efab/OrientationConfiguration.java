package com.nambimobile.widgets.efab;

import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OrientationConfiguration.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cR(\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR0\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u000b0\n@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R(\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0003\u001a\u0004\u0018\u00010\u0011@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/nambimobile/widgets/efab/OrientationConfiguration;", "", "()V", "<set-?>", "Lcom/nambimobile/widgets/efab/ExpandableFab;", "efab", "getEfab", "()Lcom/nambimobile/widgets/efab/ExpandableFab;", "setEfab$expandable_fab_release", "(Lcom/nambimobile/widgets/efab/ExpandableFab;)V", "", "Lcom/nambimobile/widgets/efab/FabOption;", "fabOptions", "getFabOptions", "()Ljava/util/List;", "setFabOptions$expandable_fab_release", "(Ljava/util/List;)V", "Lcom/nambimobile/widgets/efab/Overlay;", "overlay", "getOverlay", "()Lcom/nambimobile/widgets/efab/Overlay;", "setOverlay$expandable_fab_release", "(Lcom/nambimobile/widgets/efab/Overlay;)V", "setFabOptionAnchor", "", "fabOption", FirebaseAnalytics.Param.INDEX, "", "setFabOptionAnchor$expandable_fab_release", "expandable-fab_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class OrientationConfiguration {
    private ExpandableFab efab;
    private List<FabOption> fabOptions = new ArrayList<FabOption>() { // from class: com.nambimobile.widgets.efab.OrientationConfiguration$fabOptions$1
        public /* bridge */ boolean contains(FabOption fabOption) {
            return super.contains((Object) fabOption);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof FabOption) {
                return contains((FabOption) obj);
            }
            return false;
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ int indexOf(FabOption fabOption) {
            return super.indexOf((Object) fabOption);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof FabOption) {
                return indexOf((FabOption) obj);
            }
            return -1;
        }

        public /* bridge */ int lastIndexOf(FabOption fabOption) {
            return super.lastIndexOf((Object) fabOption);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof FabOption) {
                return lastIndexOf((FabOption) obj);
            }
            return -1;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ FabOption remove(int i) {
            return removeAt(i);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof FabOption) {
                return remove((FabOption) obj);
            }
            return false;
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ int size() {
            return getSize();
        }

        public FabOption removeAt(int index) {
            Object objRemove = super.remove(index);
            Intrinsics.checkNotNullExpressionValue(objRemove, "super.removeAt(index)");
            OrientationConfiguration orientationConfiguration = this.this$0;
            FabOption fabOption = (FabOption) objRemove;
            if (size() != 0) {
                if (index == size()) {
                    int i = index - 1;
                    FabOption fabOption2 = get(i);
                    Intrinsics.checkNotNullExpressionValue(fabOption2, "this[index - 1]");
                    orientationConfiguration.setFabOptionAnchor$expandable_fab_release(fabOption2, i);
                } else {
                    FabOption fabOption3 = get(index);
                    Intrinsics.checkNotNullExpressionValue(fabOption3, "this[index]");
                    orientationConfiguration.setFabOptionAnchor$expandable_fab_release(fabOption3, index);
                }
            }
            ViewParent parent = fabOption.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(fabOption.getLabel());
            viewGroup.removeView(fabOption);
            return fabOption;
        }

        public boolean remove(FabOption element) {
            Intrinsics.checkNotNullParameter(element, "element");
            int i = 0;
            for (FabOption fabOption : this) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual(element, fabOption)) {
                    remove(i);
                    return true;
                }
                i = i2;
            }
            return false;
        }
    };
    private Overlay overlay;

    public final Overlay getOverlay() {
        return this.overlay;
    }

    public final /* synthetic */ void setOverlay$expandable_fab_release(Overlay overlay) {
        this.overlay = overlay;
    }

    public final ExpandableFab getEfab() {
        return this.efab;
    }

    public final /* synthetic */ void setEfab$expandable_fab_release(ExpandableFab expandableFab) {
        this.efab = expandableFab;
    }

    public final List<FabOption> getFabOptions() {
        return this.fabOptions;
    }

    public final /* synthetic */ void setFabOptions$expandable_fab_release(List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.fabOptions = list;
    }

    public final /* synthetic */ void setFabOptionAnchor$expandable_fab_release(FabOption fabOption, int index) {
        Intrinsics.checkNotNullParameter(fabOption, "fabOption");
        if (this.fabOptions.size() > index) {
            ViewGroup.LayoutParams layoutParams = fabOption.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            }
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            if (index == 0) {
                ExpandableFab efab = getEfab();
                if (efab != null) {
                    layoutParams2.setAnchorId(efab.getId());
                }
            } else {
                layoutParams2.setAnchorId(getFabOptions().get(index - 1).getId());
            }
            ExpandableFab efab2 = getEfab();
            if (efab2 != null) {
                layoutParams2.anchorGravity = efab2.getFabOptionPosition().getValue();
            }
            fabOption.setLayoutParams(layoutParams2);
        }
    }
}
