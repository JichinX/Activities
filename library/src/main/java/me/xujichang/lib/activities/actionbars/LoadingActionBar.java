package me.xujichang.lib.activities.actionbars;

import android.graphics.drawable.Drawable;
import android.view.View;

import me.xujichang.lib.activities.R;
import me.xujichang.lib.activities.databinding.ActionbarLoadingBinding;

/**
 * me.xujichang.lib.activities.actionbars in Activities
 * description:数据加载时带有loading提示
 * <p>
 *
 * @author xujichang at 2020/5/8 7:02 PM
 */
public class LoadingActionBar extends AbstractActionBar<ActionbarLoadingBinding> implements ILoading {

    private LoadingActionBarClick mActionBarClick;
    private LoadingActionBarInit mActionBarInit;

    @Override
    public int getActionBarLayoutRes() {
        return R.layout.actionbar_loading;
    }

    @Override
    protected void onActionBarInit(ActionbarLoadingBinding pBinding) {

    }

    /**
     * 设置Title
     *
     * @param title
     */
    @Override
    public void setActionBarTitle(String title) {

    }

    /**
     * 设置Title Size
     *
     * @param size
     */
    @Override
    public void setActionBarTitleSize(int size) {

    }

    /**
     * 设置左侧图标
     *
     * @param pDrawable
     */
    @Override
    public void setActionBarLeftIcon(Drawable pDrawable) {

    }

    /**
     * 设置右侧图标
     *
     * @param pDrawable
     */
    @Override
    public void setActionBarRightIcon(Drawable pDrawable) {

    }

    /**
     * 设置右侧文字
     *
     * @param pText
     */
    @Override
    public void setActionBarRightText(String pText) {

    }

    /**
     * 设置左侧文字
     *
     * @param pText
     */
    @Override
    public void setActionBarLeftText(String pText) {

    }

    public static class LoadingActionBarInit implements IActionBarInit {

    }

    public static class LoadingActionBarClick implements IActionBarClick {

    }
}
