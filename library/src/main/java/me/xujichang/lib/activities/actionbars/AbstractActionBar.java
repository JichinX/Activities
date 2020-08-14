package me.xujichang.lib.activities.actionbars;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.xujichang.lib.common.rx.RxViews;
import me.xujichang.lib.common.util.ClassUtils;


/**
 * me.xujichang.lib.activities.actionbars in Activities
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/9 4:47 PM
 */
public abstract class AbstractActionBar<AVB extends ViewBinding> implements IActionBar {
    protected AVB mBinding;

    /**
     * ActionBar初始化完成
     *
     * @param pView
     */
    @Override
    public void onActionBarInit(View pView) {
        Class<AVB> vVBClass = ClassUtils.getVMClass(getClass(), 0);
        if (vVBClass != null) {
            try {
                Method vMethod = vVBClass.getMethod("bind", View.class);
                mBinding = (AVB) vMethod.invoke(null, pView);
                onActionBarInit(mBinding);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException pE) {
                pE.printStackTrace();
            }
        }
    }

    protected abstract void onActionBarInit(AVB pBinding);


    protected void attachTitleClick(final IActionBarClick pClick, View... pViews) {
        if (null != pViews) {
            for (View vView : pViews) {
                click(pClick.getLifecycleOwner(), vView, v -> pClick.onTitleClick());
            }
        }
    }

    protected void attachLeftClick(final IActionBarClick pClick, View... pViews) {
        if (null != pViews) {
            for (View vView : pViews) {
                click(pClick.getLifecycleOwner(), vView, v -> pClick.onLeftClick());
            }
        }
    }

    protected void attachRightClick(IActionBarClick pClick, View... pViews) {
        if (null != pViews) {
            for (View vView : pViews) {
                click(pClick.getLifecycleOwner(), vView, v -> pClick.onRightClick());
            }
        }
    }

    //--------------------------------点击事件--------------------------
    public void click(LifecycleOwner pOwner, View pView, View.OnClickListener pListener) {
        RxViews.getInstance(pOwner).click(pView, pListener);
    }
}
