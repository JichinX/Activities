package me.xujichang.lib.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import me.xujichang.lib.activities.actionbars.IActionBar;
import me.xujichang.lib.activities.actionbars.SearchActionBar;
import me.xujichang.lib.activities.databinding.ActivityMainBinding;
import me.xujichang.lib.activities.databinding.RootContentBinding;

/**
 * me.xujichang.lib.activities in Activities
 * description:
 * <p>
 * created by xujichang at 2020/4/28 5:33 PM
 */
public class MainActivity extends BaseVMActivity<MainViewModel, ActivityMainBinding> {
    private AtomicInteger mAtomicInteger = new AtomicInteger();
    private AtomicBoolean mAtomicBoolean = new AtomicBoolean(false);

    @Override
    protected void onViewModelInit(MainViewModel pViewModel) {

    }

    @Override
    protected void onBindingInit(ActivityMainBinding pViewBinding) {
        click(pViewBinding.tvGreeting, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                toast("You are click " + mAtomicInteger.getAndIncrement());
                boolean show = mAtomicBoolean.get();
                if (show) {
                    disableCustomActionBar();
                } else {
                    enableCustomActionBar();
                }
                mAtomicBoolean.set(!show);
            }
        });
        setStatusBarColorRes(R.color.design_default_color_primary_dark);
        setActionBarColorRes(R.color.design_default_color_primary);
        setActionBarTitle("首页测试");
        showBack();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
