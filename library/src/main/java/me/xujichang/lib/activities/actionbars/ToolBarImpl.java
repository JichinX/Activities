package me.xujichang.lib.activities.actionbars;

import android.graphics.drawable.Drawable;
import android.view.View;

import me.xujichang.lib.activities.R;
import me.xujichang.lib.activities.databinding.ActionbarToolbarBinding;

/**
 * me.xujichang.lib.activities.actionbars in Activities
 * description:使用ToolBar也就是官方推荐的替代ActionBar的控件
 * <p>
 *
 * @author xujichang at 2020/5/8 7:03 PM
 */
public class ToolBarImpl extends AbstractActionBar<ActionbarToolbarBinding> {
    private ToolBarClick mToolBarClick;
    private ToolBarInit mToolBarInit;

    @Override
    public int getActionBarLayoutRes() {
        return R.layout.actionbar_toolbar;
    }

    @Override
    protected void onActionBarInit(ActionbarToolbarBinding pBinding) {

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

    public static class ToolBarInit implements IActionBarInit {

    }

    public static abstract class ToolBarClick implements IActionBarClick {

    }
}
