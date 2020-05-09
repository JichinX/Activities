package me.xujichang.lib.activities.util;

import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * me.xujichang.lib.activities.util in Activities
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/8 4:47 PM
 */
public class ActivityUtils {
    private static final String TAG = "ActivityUtils";

    public static int getActionBarSize(AppCompatActivity pActivity) {
        TypedValue vValue = new TypedValue();
        pActivity.getTheme().resolveAttribute(android.R.attr.actionBarSize, vValue, true);
        int vPx = TypedValue.complexToDimensionPixelSize(vValue.data, pActivity.getResources().getDisplayMetrics());
        Log.i(TAG, "getActionBarSize: " + vPx);
        return vPx;
    }

    public static int getStatusBarSize(AppCompatActivity pActivity) {
        int result = 0;
        if (isWindowsTranslucent(pActivity)) {
            int resourceId = pActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = pActivity.getResources().getDimensionPixelOffset(resourceId);
            }
        }
        Log.i(TAG, "getStatusBarSize: " + result);
        return result;
    }

    public static boolean isWindowsTranslucent(AppCompatActivity pActivity) {
        return isWindowHasFlag(pActivity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public static boolean isFullScreen(AppCompatActivity pActivity) {
        return isWindowHasFlag(pActivity, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static boolean isWindowHasFlag(AppCompatActivity pActivity, int flag) {
        return (pActivity.getWindow().getAttributes().flags & flag) == flag;
    }
}
