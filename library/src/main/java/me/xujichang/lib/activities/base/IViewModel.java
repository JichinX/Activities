package me.xujichang.lib.activities.base;

import androidx.lifecycle.ViewModel;

public interface IViewModel<VM extends ViewModel> {
    void onViewModelInit(VM pVM);
}