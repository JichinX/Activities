package me.xujichang.lib.activities;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.xujichang.lib.activities.base.AbstractActionBarActivity;
import me.xujichang.lib.activities.base.IViewBinding;
import me.xujichang.lib.activities.base.IViewModel;
import me.xujichang.lib.common.util.ClassUtils;


/**
 * me.xujichang.lib.activities in Activities
 * description:
 * 处理Activity中ViewModel+ViewBinding
 * <p>
 * 处理ViewModel和ViewBinding
 *
 * @param <VM> ViewModel
 * @param <VB> ViewBinding
 * @author xujichang at 2020/5/7 4:02 PM
 */
public abstract class BaseVMActivity<VM extends ViewModel, VB extends ViewBinding>
        extends AbstractActionBarActivity
        implements IViewBinding<VB>, IViewModel<VM> {
    protected VM mViewModel;
    protected VB mViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class<VB> vVBClass = ClassUtils.getVMClass(getClass(), 1);
        if (vVBClass != null) {
            try {
                Method vMethod = vVBClass.getMethod("inflate", LayoutInflater.class);
                mViewBinding = (VB) vMethod.invoke(null, getLayoutInflater());
                if (mViewBinding != null) {
                    setContentView(mViewBinding.getRoot());
                    onBindingInit(mViewBinding);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException pE) {
                pE.printStackTrace();
            }
        }
        Class<VM> vVMClass = ClassUtils.getVMClass(getClass(), 0);
        if (null != vVMClass) {
            mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(vVMClass);
            onViewModelInit(mViewModel);
        }
    }
}
