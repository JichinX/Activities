package me.xujichang.lib.activities.actionbars;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * me.xujichang.lib.activities in Activities
 * description:定义ActionBar的功能
 * <p>
 *
 * @author xujichang at 2020/5/8 6:55 PM
 */
public interface IActionBar {
    /**
     * ActionBar 所使用的资源文件
     *
     * @return
     */
    int getActionBarLayoutRes();

    /**
     * ActionBar初始化完成
     *
     * @param pView
     */
    void onActionBarInit(View pView);

    /**
     * 设置Title
     *
     * @param title
     */
    void setActionBarTitle(String title);

    /**
     * 设置Title Size
     *
     * @param size
     */
    void setActionBarTitleSize(int size);

    /**
     * 设置左侧图标
     */
    void setActionBarLeftIcon(Drawable pDrawable);

    /**
     * 设置右侧图标
     */
    void setActionBarRightIcon(Drawable pDrawable);

    /**
     * 设置右侧文字
     */
    void setActionBarRightText(String pText);

    /**
     * 设置左侧文字
     */
    void setActionBarLeftText(String pText);
}
