package id.go.bpsfasih.ui.onboarding;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: OnBoardingActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/ui/onboarding/OnBoardingPage;", "", "titleRes", "", "descriptionRes", "imageRes", "(III)V", "getDescriptionRes", "()I", "getImageRes", "getTitleRes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class OnBoardingPage {
    public static final int $stable = 0;
    private final int descriptionRes;
    private final int imageRes;
    private final int titleRes;

    public static /* synthetic */ OnBoardingPage copy$default(OnBoardingPage onBoardingPage, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = onBoardingPage.titleRes;
        }
        if ((i4 & 2) != 0) {
            i2 = onBoardingPage.descriptionRes;
        }
        if ((i4 & 4) != 0) {
            i3 = onBoardingPage.imageRes;
        }
        return onBoardingPage.copy(i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTitleRes() {
        return this.titleRes;
    }

    /* renamed from: component2, reason: from getter */
    public final int getDescriptionRes() {
        return this.descriptionRes;
    }

    /* renamed from: component3, reason: from getter */
    public final int getImageRes() {
        return this.imageRes;
    }

    public final OnBoardingPage copy(int titleRes, int descriptionRes, int imageRes) {
        return new OnBoardingPage(titleRes, descriptionRes, imageRes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnBoardingPage)) {
            return false;
        }
        OnBoardingPage onBoardingPage = (OnBoardingPage) other;
        return this.titleRes == onBoardingPage.titleRes && this.descriptionRes == onBoardingPage.descriptionRes && this.imageRes == onBoardingPage.imageRes;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.titleRes) * 31) + Integer.hashCode(this.descriptionRes)) * 31) + Integer.hashCode(this.imageRes);
    }

    public String toString() {
        return "OnBoardingPage(titleRes=" + this.titleRes + ", descriptionRes=" + this.descriptionRes + ", imageRes=" + this.imageRes + ")";
    }

    public OnBoardingPage(int i, int i2, int i3) {
        this.titleRes = i;
        this.descriptionRes = i2;
        this.imageRes = i3;
    }

    public final int getTitleRes() {
        return this.titleRes;
    }

    public final int getDescriptionRes() {
        return this.descriptionRes;
    }

    public final int getImageRes() {
        return this.imageRes;
    }
}
