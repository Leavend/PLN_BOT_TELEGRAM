package androidx.test.espresso.matcher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/* loaded from: classes5.dex */
public final class HasBackgroundMatcher extends TypeSafeMatcher<View> {
    private static final String TAG = "HasBackgroundMatcher";
    private final int drawableId;

    public HasBackgroundMatcher(int i) {
        this.drawableId = i;
    }

    private static boolean assertDrawable(Drawable drawable, int i, View view) throws Throwable {
        Bitmap bitmapDecodeResource;
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            return false;
        }
        try {
            bitmapDecodeResource = BitmapFactory.decodeResource(view.getContext().getResources(), i);
            try {
                boolean zSameAs = ((BitmapDrawable) drawable).getBitmap().sameAs(bitmapDecodeResource);
                if (bitmapDecodeResource != null) {
                    bitmapDecodeResource.recycle();
                }
                return zSameAs;
            } catch (Throwable th) {
                th = th;
                if (bitmapDecodeResource != null) {
                    bitmapDecodeResource.recycle();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bitmapDecodeResource = null;
        }
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("has background with drawable ID: " + this.drawableId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(View view) {
        return assertDrawable(view.getBackground(), this.drawableId, view);
    }
}
