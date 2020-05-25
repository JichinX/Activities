package me.xujichang.lib.activities.actionbars;

import androidx.lifecycle.LifecycleOwner;

/**
 * me.xujichang.lib.activities.actionbars in Activities
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/9 5:03 PM
 */
public interface IActionBarClick {
    void onLeftClick();

    void onRightClick();

    void onTitleClick();

    LifecycleOwner getLifecycleOwner();
}
