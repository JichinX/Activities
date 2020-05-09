package me.xujichang.lib.activities.actionbars;

import android.graphics.drawable.Drawable;
import android.view.View;

import me.xujichang.lib.activities.R;
import me.xujichang.lib.activities.databinding.ActionbarSearchBinding;

/**
 * me.xujichang.lib.activities.actionbars in Activities
 * description:具有搜索框功能
 * <p>
 *
 * @author xujichang at 2020/5/8 7:02 PM
 */
public class SearchActionBar extends AbstractActionBar<ActionbarSearchBinding> {
    private SearchActionBarClick mActionBarClick;
    private SearchActionBarInit mActionBarInit;

    @Override
    public int getActionBarLayoutRes() {
        return R.layout.actionbar_search;
    }

    @Override
    protected void onActionBarInit(ActionbarSearchBinding pBinding) {

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

    /**
     * 初始化
     */
    public static class SearchActionBarInit implements IActionBarInit {

    }

    /**
     * 事件点击
     */
    public static class SearchActionBarClick implements IActionBarClick {

    }
}
