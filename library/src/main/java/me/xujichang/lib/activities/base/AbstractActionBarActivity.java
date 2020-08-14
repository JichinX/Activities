package me.xujichang.lib.activities.base;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import me.xujichang.lib.activities.R;
import me.xujichang.lib.activities.actionbars.DefaultActionBar;
import me.xujichang.lib.activities.actionbars.IActionBar;
import me.xujichang.lib.activities.actionbars.IActionBarClick;

/**
 * me.xujichang.lib.activities in Activities
 * description:
 * 处理顶部导航栏
 * <p>
 *
 * @author xujichang at 2020/5/7 4:01 PM
 */
public abstract class AbstractActionBarActivity extends AbstractBasicActivity implements IActionBar, IActionBarClick {
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
                mActionBarDelegate = new DefaultActionBar(this);
            }
        }
        return mActionBarDelegate;
    }

    /**
     * 创建ActionBar
     * 如果你想实现自己的ActionBar，需要重新实现此方法
     *
     * @return
     */
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
     * 设置Title
     */
    public void setActionBarTitle(@StringRes int res) {
        getActionBarDelegate().setActionBarTitle(getString(res));
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
     * 设置左侧图标
     */
    public void setActionBarLeftIcon(@DrawableRes int res) {
        getActionBarDelegate().setActionBarLeftIcon(getDrawable(res));
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

    public void setActionBarRightIcon(@DrawableRes int pDrawable) {
        getActionBarDelegate().setActionBarRightIcon(getDrawable(pDrawable));
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

    public void setActionBarRightText(@StringRes int pText) {
        getActionBarDelegate().setActionBarRightText(getString(pText));
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

    public void setActionBarLeftText(@StringRes int pText) {
        getActionBarDelegate().setActionBarLeftText(getString(pText));
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

    @Override
    public void onLeftClick() {
        dispatchActionbarClick();
    }

    @Override
    public void onRightClick() {
        dispatchActionbarClick();
    }

    @Override
    public void onTitleClick() {
        dispatchActionbarClick();
    }

    private void dispatchActionbarClick() {

    }
}
