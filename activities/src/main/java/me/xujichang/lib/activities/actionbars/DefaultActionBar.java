package me.xujichang.lib.activities.actionbars;

import android.graphics.drawable.Drawable;
import android.view.View;

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

    private ConstraintSet mConstraintSet = new ConstraintSet();

    private DefaultActionBarClick mActionBaClick;
    private DefaultActionBarInit mActionBarInit;

    @Override
    public int getActionBarLayoutRes() {
        return R.layout.actionbar_default;
    }

    @Override
    protected void onActionBarInit(ActionbarDefaultBinding pBinding) {
        mConstraintSet.clone(mBinding.getRoot());
    }

    @Override
    public void setActionBarTitle(String title) {
        checkAndSetVisible(mBinding.defaultActionbarTitle);
        mBinding.defaultActionbarTitle.setText(checkNull(title));
    }

    @Override
    public void setActionBarTitleSize(int size) {
        mBinding.defaultActionbarTitle.setTextSize(size);
    }

    @Override
    public void setActionBarLeftIcon(Drawable pDrawable) {
        checkAndSetVisible(mBinding.defaultActionbarLfImage);
        mBinding.defaultActionbarLfImage.setImageDrawable(pDrawable);
    }

    @Override
    public void setActionBarRightIcon(Drawable pDrawable) {
        checkAndSetVisible(mBinding.defaultActionbarRtImage);
        mBinding.defaultActionbarRtImage.setImageDrawable(pDrawable);
    }

    @Override
    public void setActionBarRightText(String pText) {
        checkAndSetVisible(mBinding.defaultActionbarRtText);
        mBinding.defaultActionbarRtText.setText(checkNull(pText));
    }

    @Override
    public void setActionBarLeftText(String pText) {
        checkAndSetVisible(mBinding.defaultActionbarLfText);
        mBinding.defaultActionbarLfText.setText(checkNull(pText));
    }


    private String checkNull(String pTitle) {
        return Strings.nullToEmpty(pTitle);
    }

    private void checkAndSetVisible(View pView) {
        if (pView.getVisibility() != View.VISIBLE) {
            mConstraintSet.setVisibility(pView.getId(), ConstraintSet.VISIBLE);
            TransitionManager.beginDelayedTransition(mBinding.getRoot());
            mConstraintSet.applyTo(mBinding.getRoot());
        }
    }

    public static class DefaultActionBarInit implements IActionBarInit {

    }

    public static class DefaultActionBarClick implements IActionBarClick {

    }
}
