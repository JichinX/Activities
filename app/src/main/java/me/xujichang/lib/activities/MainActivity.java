package me.xujichang.lib.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.paging.LoadState;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import me.xujichang.lib.activities.databinding.ActivityMainBinding;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableCustomActionBar();
    }

    @Override
    public void onViewModelInit(MainViewModel pViewModel) {

    }

    @Override
    public void onBindingInit(ActivityMainBinding pViewBinding) {
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
        setActionBarLeftText("左侧文字测试");
        setActionBarRightText("右侧文字测试");
        setActionBarTitle("首页测试");
        showBack();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        Log.i(TAG, "onRightClick: ");
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        Log.i(TAG, "onLeftClick: ");
    }

    @Override
    public void onTitleClick() {
        super.onTitleClick();
        Log.i(TAG, "onTitleClick: ");
    }

    @Override
    public void onAppend(LoadState pLoadState) {

    }

    @Override
    public void onPrepend(LoadState pLoadState) {

    }

    @Override
    public void onRefresh(LoadState pLoadState) {

    }
}
