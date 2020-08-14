package me.xujichang.lib.activities.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.CombinedLoadStates;
import androidx.paging.PagingData;
import androidx.paging.PagingDataAdapter;
import androidx.transition.TransitionManager;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import me.xujichang.lib.activities.databinding.RootContentBinding;
import me.xujichang.lib.activities.util.ActivityUtils;
import me.xujichang.lib.activities.util.ToastDelegate;
import me.xujichang.lib.common.rx.RxViews;
import me.xujichang.lib.common.status.ListStatus;
import me.xujichang.lib.permissions.IPermissionRequest;
import me.xujichang.lib.permissions.LivePermissions;
import me.xujichang.lib.permissions.PermissionResult;
import me.xujichang.lib.permissions.PermissionResultObserverConvert;

/**
 * me.xujichang.lib.activities in Activities
 * description:
 * 基类Activity
 * 1，Activity栈
 * 2，this
 * 3，权限申请
 * 4，事件总线
 * 5，Toast、SnakeBar、Progress、Dialog
 * <p>
 * created by xujichang at 2020/4/28 5:22 PM
 */
public abstract class AbstractBasicActivity extends AppCompatActivity implements IPaging3 {
    private long[] mHits = new long[2];
    public final String TAG = this.getClass().getSimpleName();
    protected ConstraintSet mConstraintSet = new ConstraintSet();
    protected RootContentBinding mContentBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        addActivity();
    }

    private void initContentView() {
        ViewGroup vContent = findViewById(android.R.id.content);
        vContent.removeAllViews();
        mContentBinding = RootContentBinding.inflate(getLayoutInflater(), vContent, true);
        mConstraintSet.clone(mContentBinding.getRoot());
        mContentBinding.glStatusBar.setGuidelineBegin(ActivityUtils.getStatusBarSize(getActivity()));
        mContentBinding.glActionBar.setGuidelineBegin(ActivityUtils.getStatusBarSize(getActivity()));
        //ActionBar
        @LayoutRes int actionbarRes = getActionBarLayoutRes();
        if (0 != actionbarRes) {
            mContentBinding.actionbarStub.setLayoutResource(actionbarRes);
            onActionBarInit(mContentBinding.actionbarStub.inflate());
        }
    }

    /**
     * 初始化ActionBarStub
     *
     * @param pView
     */
    protected abstract void onActionBarInit(View pView);

    @LayoutRes
    protected abstract int getActionBarLayoutRes();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity();
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (null != params) {
            mContentBinding.flContent.addView(view, params);
        } else {
            mContentBinding.flContent.addView(view);
        }
        onRootViewInit(mContentBinding, view);
    }

    protected void onRootViewInit(RootContentBinding pBinding, View pView) {

    }

    //=====================Activity 栈===========================
    private ConcurrentMap<String, AppCompatActivity> mActivities;

    public ConcurrentMap<String, AppCompatActivity> getActivities() {
        if (null == mActivities) {
            mActivities = Maps.newConcurrentMap();
        }
        return mActivities;
    }

    private void addActivity() {
        getActivities().put(getLocalClassName(), this);
    }

    private void removeActivity() {
        getActivities().remove(getLocalClassName());
    }

    //=====================Activity 栈===========================
    //=====================Context===========================
    public Context getContext() {
        return this;
    }

    public AppCompatActivity getActivity() {
        return this;
    }

    public LifecycleOwner getLifecycleOwner() {
        return this;
    }
    //=====================Context===========================
    //=====================权限申请===========================

    public void withPermissions(String[] permissions, Observer<PermissionResult> pObserver) {
        new LivePermissions(this)
                .requestPermissions(permissions)
                .observe(this, pObserver);
    }

    @Deprecated
    public void withPermissions(String[] permissions, @NonNull IPermissionRequest request) {
        withPermissions(permissions, new PermissionResultObserverConvert(request));
    }

    //=====================权限申请===========================
    @Override
    public void onBackPressed() {
        if (isTaskRoot()) {
            //实现的功能就相当于把mHits从索引1开始的数据向前移动一位（0位数据被覆盖），然后把当前距离时间写入到数组的最后一位
            System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
            //获取离开机的时间
            mHits[mHits.length - 1] = SystemClock.uptimeMillis();
            //单击时间的间隔，以500毫秒为临界值
            if (mHits[0] >= (SystemClock.uptimeMillis() - 2_000)) {
                System.out.println("我被三击了。。。");
                //一个三击（双击或多击事件完成），
                //把数组置为空并重写初始化，为下一次三击（双击或多击）做准备
                mHits = null;
                mHits = new long[2];
                super.onBackPressed();
            } else {
                toast("再按一次返回键退出");
            }
        } else {
            super.onBackPressed();
        }
    }

    //======================Toast
    public void toast(String message) {
        getToast().show(message);
    }

    public ToastDelegate getToast() {
        return ToastDelegate.useDefault(getApplicationContext());
    }
    //====================软键盘----------------------------

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public void hideKeyboard() {
        if (isIMOpen()) {
            View view = getCurrentFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                    Activity.INPUT_METHOD_SERVICE);
            if (view != null && null != inputMethodManager) {
                inputMethodManager
                        .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     */
    public void hideSoftKeyboard(Context context, List<View> viewList) {
        if (viewList == null || !isIMOpen()) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (null == inputMethodManager) {
            return;
        }
        for (View v : viewList) {
            inputMethodManager
                    .hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean isIMOpen() {
        InputMethodManager vMethodManager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        return null != vMethodManager && vMethodManager.isActive();
    }

    //--------------------------------点击事件--------------------------
    public void click(View pView, View.OnClickListener pListener) {
        RxViews.getInstance(this).click(pView, pListener);
    }
    //------------------------------跳转-------------------------------

    /**
     * 跳转
     * Activity
     *
     * @param aClass
     */
    public void toActivity(Class<? extends Activity> aClass) {
        startActivity(new Intent(getContext(), aClass));
    }

    public void toActivity(Class<? extends Activity> aClass, int flag) {
        Intent locIntent = new Intent(this, aClass);
        locIntent.setFlags(flag);
        startActivity(locIntent);
    }

    public void toNewTaskActivity(Class<? extends Activity> aClass) {
        Intent locIntent = new Intent(this, aClass);
        locIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(locIntent);
    }

    public void toActivity(Intent intent) {
        startActivity(intent);
    }
    //-----------------------------------------------------------------

    /**
     * 隐藏自定义ActionBar
     */
    protected void disableCustomActionBar() {
        mConstraintSet.setGuidelineBegin(mContentBinding.glActionBar.getId(), ActivityUtils.getStatusBarSize(getActivity()));
        TransitionManager.beginDelayedTransition(mContentBinding.getRoot());
        mConstraintSet.applyTo(mContentBinding.getRoot());
    }

    /**
     * 显示自定义ActionBar
     */
    protected void enableCustomActionBar() {
        mConstraintSet.setGuidelineBegin(mContentBinding.glActionBar.getId(), ActivityUtils.getStatusBarSize(getActivity()) + ActivityUtils.getActionBarSize(getActivity()));
        TransitionManager.beginDelayedTransition(mContentBinding.getRoot());
        mConstraintSet.applyTo(mContentBinding.getRoot());
    }

    /**
     * 设置状态栏 背景色
     */
    protected void setStatusBarColor(@ColorInt int pColor) {
        if (ActivityUtils.isWindowsTranslucent(getActivity())) {
            //状态栏透明 设置自定义的StatusBar
            mContentBinding.bgStatusBar.setBackgroundColor(pColor);
        } else {
            //状态栏不透明，设置系统状态栏颜色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(pColor);
        }
    }

    protected void setStatusBarColorRes(@ColorRes int pColorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarColor(getColor(pColorRes));
        } else {
            setStatusBarColor(getResources().getColor(pColorRes));
        }
    }

    protected void setActionBarColorRes(@ColorRes int pColorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setActionBarColor(getColor(pColorRes));
        } else {
            setActionBarColor(getResources().getColor(pColorRes));
        }
    }

    /**
     * 设置Actionbar颜色
     */
    protected void setActionBarColor(@ColorInt int pColor) {
        mContentBinding.bgActionBar.setBackgroundColor(pColor);
    }

    //====================Paging3========================
    @Override
    public <T> void bindPagingData(PagingDataAdapter<T, ?> pAdapter, LiveData<PagingData<T>> pListing) {
        pAdapter.addLoadStateListener(new Function1<CombinedLoadStates, Unit>() {
            @Override
            public Unit invoke(CombinedLoadStates pLoadStates) {
                attachPagingStatus(pLoadStates);
                return null;
            }
        });
        pListing.observe(getLifecycleOwner(), new PagingObserver<>(pAdapter, getLifecycle()));
    }

    private void attachPagingStatus(CombinedLoadStates pLoadStates) {
        onAppend(pLoadStates.getAppend());
        onPrepend(pLoadStates.getPrepend());
        onRefresh(pLoadStates.getRefresh());
    }

    @Override
    public void attachListStatus(LiveData<ListStatus> pStatus) {
        Log.d(TAG, "attachListStatus() called with: pStatus = [" + pStatus + "]");
    }

    private static class PagingObserver<T> implements Observer<PagingData<T>> {
        private final PagingDataAdapter<T, ?> mAdapter;
        private final Lifecycle mLifecycle;

        public PagingObserver(PagingDataAdapter<T, ?> pAdapter, Lifecycle pLifecycle) {
            mAdapter = pAdapter;
            mLifecycle = pLifecycle;
        }

        @Override
        public void onChanged(PagingData<T> pTPagingData) {
            mAdapter.submitData(mLifecycle, pTPagingData);
        }
    }
    //==================RequestStatus=====================

}
