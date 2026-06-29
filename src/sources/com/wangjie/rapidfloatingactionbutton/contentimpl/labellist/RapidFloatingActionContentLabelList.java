package com.wangjie.rapidfloatingactionbutton.contentimpl.labellist;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.Key;
import com.wangjie.rapidfloatingactionbutton.R;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent;
import com.wangjie.rapidfloatingactionbutton.constants.RFABSize;
import com.wangjie.rapidfloatingactionbutton.util.RFABImageUtil;
import com.wangjie.rapidfloatingactionbutton.util.RFABShape;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;
import com.wangjie.rapidfloatingactionbutton.util.RFABViewUtil;
import com.wangjie.rapidfloatingactionbutton.widget.CircleButtonDrawable;
import com.wangjie.rapidfloatingactionbutton.widget.CircleButtonProperties;
import java.util.List;

/* loaded from: classes2.dex */
public class RapidFloatingActionContentLabelList extends RapidFloatingActionContent implements View.OnClickListener {
    private LinearLayout contentView;
    private int iconShadowColor;
    private int iconShadowDx;
    private int iconShadowDy;
    private int iconShadowRadius;
    private List<RFACLabelItem> items;
    private OvershootInterpolator mOvershootInterpolator;
    private OnRapidFloatingActionContentLabelListListener onRapidFloatingActionContentLabelListListener;
    private int rfacItemDrawableSizePx;

    public interface OnRapidFloatingActionContentLabelListListener<T> {
        void onRFACItemIconClick(int i, RFACLabelItem<T> rFACLabelItem);

        void onRFACItemLabelClick(int i, RFACLabelItem<T> rFACLabelItem);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    protected void initialContentViews(View view) {
    }

    public void setOnRapidFloatingActionContentLabelListListener(OnRapidFloatingActionContentLabelListListener onRapidFloatingActionContentLabelListListener) {
        this.onRapidFloatingActionContentLabelListListener = onRapidFloatingActionContentLabelListListener;
    }

    public RapidFloatingActionContentLabelList(Context context) {
        super(context);
        this.mOvershootInterpolator = new OvershootInterpolator();
    }

    public RapidFloatingActionContentLabelList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOvershootInterpolator = new OvershootInterpolator();
    }

    public RapidFloatingActionContentLabelList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOvershootInterpolator = new OvershootInterpolator();
    }

    public RapidFloatingActionContentLabelList(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mOvershootInterpolator = new OvershootInterpolator();
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    protected void initInConstructor() {
        this.rfacItemDrawableSizePx = RFABTextUtil.dip2px(getContext(), 24.0f);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.contentView = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.contentView.setOrientation(1);
        setRootView(this.contentView);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    protected void initAfterRFABHelperBuild() {
        refreshItems();
    }

    public List<RFACLabelItem> getItems() {
        return this.items;
    }

    public RapidFloatingActionContentLabelList setItems(List<RFACLabelItem> list) {
        if (!RFABTextUtil.isEmpty(list)) {
            this.items = list;
        }
        return this;
    }

    private void refreshItems() {
        if (RFABTextUtil.isEmpty(this.items)) {
            throw new RuntimeException(getClass().getSimpleName() + "[items] can not be empty!");
        }
        this.contentView.removeAllViews();
        int size = this.items.size();
        for (int i = 0; i < size; i++) {
            RFACLabelItem rFACLabelItem = this.items.get(i);
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.rfab__content_label_list_item, (ViewGroup) null);
            View viewObtainView = RFABViewUtil.obtainView(viewInflate, R.id.rfab__content_label_list_root_view);
            TextView textView = (TextView) RFABViewUtil.obtainView(viewInflate, R.id.rfab__content_label_list_label_tv);
            ImageView imageView = (ImageView) RFABViewUtil.obtainView(viewInflate, R.id.rfab__content_label_list_icon_iv);
            viewObtainView.setOnClickListener(this);
            textView.setOnClickListener(this);
            imageView.setOnClickListener(this);
            viewObtainView.setTag(R.id.rfab__id_content_label_list_item_position, Integer.valueOf(i));
            textView.setTag(R.id.rfab__id_content_label_list_item_position, Integer.valueOf(i));
            imageView.setTag(R.id.rfab__id_content_label_list_item_position, Integer.valueOf(i));
            CircleButtonProperties shadowDy = new CircleButtonProperties().setStandardSize(RFABSize.MINI).setShadowColor(this.iconShadowColor).setShadowRadius(this.iconShadowRadius).setShadowDx(this.iconShadowDx).setShadowDy(this.iconShadowDy);
            int shadowOffsetHalf = shadowDy.getShadowOffsetHalf();
            int iDip2px = RFABTextUtil.dip2px(getContext(), 8.0f);
            if (shadowOffsetHalf < iDip2px) {
                int i2 = iDip2px - shadowOffsetHalf;
                viewObtainView.setPadding(0, i2, 0, i2);
            }
            int realSizePx = shadowDy.getRealSizePx(getContext());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(-2, -2);
            }
            layoutParams.rightMargin = (this.onRapidFloatingActionListener.obtainRFAButton().getRfabProperties().getRealSizePx(getContext()) - realSizePx) / 2;
            layoutParams.width = realSizePx;
            layoutParams.height = realSizePx;
            imageView.setLayoutParams(layoutParams);
            Integer iconNormalColor = rFACLabelItem.getIconNormalColor();
            Integer iconPressedColor = rFACLabelItem.getIconPressedColor();
            CircleButtonDrawable circleButtonDrawable = new CircleButtonDrawable(getContext(), shadowDy, iconNormalColor == null ? getResources().getColor(R.color.rfab__color_background_normal) : iconNormalColor.intValue());
            CircleButtonDrawable circleButtonDrawable2 = new CircleButtonDrawable(getContext(), shadowDy, iconPressedColor == null ? getResources().getColor(R.color.rfab__color_background_pressed) : iconPressedColor.intValue());
            imageView.setLayerType(1, circleButtonDrawable.getPaint());
            RFABViewUtil.setBackgroundDrawable(imageView, RFABShape.selectorClickSimple(circleButtonDrawable, circleButtonDrawable2));
            int iDip2px2 = RFABTextUtil.dip2px(getContext(), 8.0f) + shadowOffsetHalf;
            imageView.setPadding(iDip2px2, iDip2px2, iDip2px2, iDip2px2);
            String label = rFACLabelItem.getLabel();
            if (RFABTextUtil.isEmpty(label)) {
                textView.setVisibility(8);
            } else {
                if (rFACLabelItem.isLabelTextBold()) {
                    textView.getPaint().setFakeBoldText(true);
                }
                textView.setVisibility(0);
                textView.setText(label);
                Drawable labelBackgroundDrawable = rFACLabelItem.getLabelBackgroundDrawable();
                if (labelBackgroundDrawable != null) {
                    RFABViewUtil.setBackgroundDrawable(textView, labelBackgroundDrawable);
                }
                Integer labelColor = rFACLabelItem.getLabelColor();
                if (labelColor != null) {
                    textView.setTextColor(labelColor.intValue());
                }
                if (rFACLabelItem.getLabelSizeSp() != null) {
                    textView.setTextSize(2, r6.intValue());
                }
            }
            Drawable drawable = rFACLabelItem.getDrawable();
            if (drawable != null) {
                imageView.setVisibility(0);
                int i3 = this.rfacItemDrawableSizePx;
                drawable.setBounds(0, 0, i3, i3);
                imageView.setImageDrawable(drawable);
            } else {
                int resId = rFACLabelItem.getResId();
                if (resId > 0) {
                    imageView.setVisibility(0);
                    imageView.setImageDrawable(RFABImageUtil.getResourceDrawableBounded(getContext(), resId, this.rfacItemDrawableSizePx));
                } else {
                    imageView.setVisibility(8);
                }
            }
            this.contentView.addView(viewInflate);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Integer num;
        if (this.onRapidFloatingActionContentLabelListListener == null || (num = (Integer) view.getTag(R.id.rfab__id_content_label_list_item_position)) == null) {
            return;
        }
        int id2 = view.getId();
        if (id2 == R.id.rfab__content_label_list_label_tv) {
            this.onRapidFloatingActionContentLabelListListener.onRFACItemLabelClick(num.intValue(), this.items.get(num.intValue()));
        } else if (id2 == R.id.rfab__content_label_list_icon_iv) {
            this.onRapidFloatingActionContentLabelListListener.onRFACItemIconClick(num.intValue(), this.items.get(num.intValue()));
        } else if (id2 == R.id.rfab__content_label_list_root_view) {
            this.onRapidFloatingActionListener.collapseContent();
        }
    }

    public RapidFloatingActionContentLabelList setIconShadowRadius(int i) {
        this.iconShadowRadius = i;
        return this;
    }

    public RapidFloatingActionContentLabelList setIconShadowColor(int i) {
        this.iconShadowColor = i;
        return this;
    }

    public RapidFloatingActionContentLabelList setIconShadowDx(int i) {
        this.iconShadowDx = i;
        return this;
    }

    public RapidFloatingActionContentLabelList setIconShadowDy(int i) {
        this.iconShadowDy = i;
        return this;
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    public void onExpandAnimator(AnimatorSet animatorSet) {
        int childCount = this.contentView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) RFABViewUtil.obtainView(this.contentView.getChildAt(i), R.id.rfab__content_label_list_icon_iv);
            if (imageView == null) {
                return;
            }
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(imageView);
            objectAnimator.setFloatValues(45.0f, 0.0f);
            objectAnimator.setPropertyName(Key.ROTATION);
            objectAnimator.setInterpolator(this.mOvershootInterpolator);
            objectAnimator.setStartDelay(childCount * i * 20);
            animatorSet.playTogether(objectAnimator);
        }
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    public void onCollapseAnimator(AnimatorSet animatorSet) {
        int childCount = this.contentView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) RFABViewUtil.obtainView(this.contentView.getChildAt(i), R.id.rfab__content_label_list_icon_iv);
            if (imageView == null) {
                return;
            }
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(imageView);
            objectAnimator.setFloatValues(0.0f, 45.0f);
            objectAnimator.setPropertyName(Key.ROTATION);
            objectAnimator.setInterpolator(this.mOvershootInterpolator);
            objectAnimator.setStartDelay(childCount * i * 20);
            animatorSet.playTogether(objectAnimator);
        }
    }
}
