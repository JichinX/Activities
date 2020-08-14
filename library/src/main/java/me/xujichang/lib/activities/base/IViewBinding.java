package me.xujichang.lib.activities.base;

import androidx.viewbinding.ViewBinding;

public interface IViewBinding<VB extends ViewBinding> {
    void onBindingInit(VB pVB);
}
