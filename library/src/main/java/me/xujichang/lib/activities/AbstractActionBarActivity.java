package me.xujichang.lib.activities;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;

import me.xujichang.lib.activities.actionbars.DefaultActionBar;
import me.xujichang.lib.activities.actionbars.IActionBar;

/**
 * me.xujichang.lib.activities in Activities
 * description:
 * 处理顶部导航栏
 * <p>
 *
 * @author xujichang at 2020/5/7 4:01 PM
 */
public abstract class AbstractActionBarActivity extends AbstractBasicActivity implements IActionBar {
    private IActionBar mActionBarDelegate;

    @Override
    public void onActionBarInit(View pActionBarView) {
        getActionBarDelegate().onActionBarInit(pActionBarView);
    }


    @Override
    public int getActionBarLayoutRes() {
        return getActionBarDelegate().getActionBarLayoutRes();
    }


    public IActionBar getActionBarDelegate() {
        if (null == mActionBarDelegate) {
            mActionBarDelegate = onCreateActionBar();
            if (null == mActionBarDelegate) {
                mActionBarDelegate = new DefaultActionBar();
            }
        }
        return mActionBarDelegate;
    }

    protected IActionBar onCreateActionBar() {
        return null;
    }


    /**
     * 设置Title
     *
     * @param title
     */
    @Override
    public void setActionBarTitle(String title) {
        getActionBarDelegate().setActionBarTitle(title);
    }

    /**
     * 设置Title Size
     *
     * @param size
     */
    @Override
    public void setActionBarTitleSize(int size) {
        getActionBarDelegate().setActionBarTitleSize(size);
    }

    /**
     * 设置左侧图标
     *
     * @param pDrawable
     */
    @Override
    public void setActionBarLeftIcon(Drawable pDrawable) {
        getActionBarDelegate().setActionBarLeftIcon(pDrawable);
    }

    /**
     * 设置右侧图标
     *
     * @param pDrawable
     */
    @Override
    public void setActionBarRightIcon(Drawable pDrawable) {
        getActionBarDelegate().setActionBarRightIcon(pDrawable);
    }

    /**
     * 设置右侧文字
     *
     * @param pText
     */
    @Override
    public void setActionBarRightText(String pText) {
        getActionBarDelegate().setActionBarRightText(pText);
    }

    /**
     * 设置左侧文字
     *
     * @param pText
     */
    @Override
    public void setActionBarLeftText(String pText) {
        getActionBarDelegate().setActionBarLeftText(pText);
    }

    public void showBack() {
        showBack(R.drawable.default_actionbar_back);
    }

    public void showBack(Drawable pDrawable) {
        getActionBarDelegate().setActionBarLeftIcon(pDrawable);
    }

    public void showBack(@DrawableRes int pRes) {
        showBack(getResources().getDrawable(pRes));
    }
}
