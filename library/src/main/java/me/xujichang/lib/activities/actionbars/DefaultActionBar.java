package me.xujichang.lib.activities.actionbars;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.TransitionManager;

import com.google.common.base.Strings;

import me.xujichang.lib.activities.R;
import me.xujichang.lib.activities.databinding.ActionbarDefaultBinding;

/**
 * me.xujichang.lib.activities.actionbars in Activities
 * description:默认ActionBar类型。
 * Title居中 左右各Text+Image
 * <p>
 *
 * @author xujichang at 2020/5/8 7:01 PM
 */
public class DefaultActionBar extends AbstractActionBar<ActionbarDefaultBinding> {

    private final ConstraintSet mConstraintSet = new ConstraintSet();

    private final IActionBarClick mBarClick;

    public DefaultActionBar(IActionBarClick pBarClick) {
        mBarClick = pBarClick;
    }

    @Override
    public int getActionBarLayoutRes() {
        return R.layout.actionbar_default;
    }

    @Override
    protected void onActionBarInit(ActionbarDefaultBinding pBinding) {
        mConstraintSet.clone(mBinding.getRoot());
        if (null != mBarClick) {
            attachRightClick(mBarClick, pBinding.defaultActionbarRtImage, pBinding.defaultActionbarRtText);
            attachLeftClick(mBarClick, pBinding.defaultActionbarLfImage, pBinding.defaultActionbarLfText);
            attachTitleClick(mBarClick, pBinding.defaultActionbarTitle);
        }
    }

    @Override
    public void setActionBarTitle(String title) {
        if (checkAndSetVisible(mBinding.defaultActionbarTitle, checkIsNull(title))) {
            mBinding.defaultActionbarTitle.setText(checkNull(title));
        }
    }

    @Override
    public void setActionBarTitleSize(int size) {
        mBinding.defaultActionbarTitle.setTextSize(size);
    }

    @Override
    public void setActionBarLeftIcon(Drawable pDrawable) {
        if (checkAndSetVisible(mBinding.defaultActionbarLfImage, checkIsNull(pDrawable))) {
            mBinding.defaultActionbarLfImage.setImageDrawable(pDrawable);
        }
    }


    @Override
    public void setActionBarRightIcon(Drawable pDrawable) {
        if (checkAndSetVisible(mBinding.defaultActionbarRtImage, checkIsNull(pDrawable))) {
            mBinding.defaultActionbarRtImage.setImageDrawable(pDrawable);
        }
    }

    @Override
    public void setActionBarRightText(String pText) {
        if (checkAndSetVisible(mBinding.defaultActionbarRtText, checkIsNull(pText))) {
            mBinding.defaultActionbarRtText.setText(checkNull(pText));
        }
    }

    @Override
    public void setActionBarLeftText(String pText) {
        if (checkAndSetVisible(mBinding.defaultActionbarLfText, checkIsNull(pText))) {
            mBinding.defaultActionbarLfText.setText(checkNull(pText));
        }
    }

    private String checkNull(String pTitle) {
        return Strings.nullToEmpty(pTitle);
    }

    private boolean checkIsNull(Object pDrawable) {
        return null == pDrawable;
    }

    private boolean checkAndSetVisible(View pView, boolean valueIsNull) {
        if (valueIsNull) {
            //value is null，hide the view
            hideView(pView);
            return false;
        }
        showView(pView);
        return true;
    }

    private void hideView(View pView) {
        if (pView.getVisibility() != View.GONE) {
            mConstraintSet.setVisibility(pView.getId(), ConstraintSet.GONE);
            TransitionManager.beginDelayedTransition(mBinding.getRoot());
            mConstraintSet.applyTo(mBinding.getRoot());
        }
    }

    private void showView(View pView) {
        if (pView.getVisibility() != View.VISIBLE) {
            mConstraintSet.setVisibility(pView.getId(), ConstraintSet.VISIBLE);
            TransitionManager.beginDelayedTransition(mBinding.getRoot());
            mConstraintSet.applyTo(mBinding.getRoot());
        }
    }

}
