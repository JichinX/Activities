package me.xujichang.lib.activities.util;

import android.content.Context;
import android.widget.Toast;

/**
 * me.xujichang.lib.activities in Activities
 * description:
 * <p>
 * created by xujichang at 2020/4/28 6:56 PM
 */
public abstract class ToastDelegate {

    public static ToastDelegate useDefault(Context pContext) {
        return new DefaultToast(pContext);
    }

    public abstract void show(String pMessage);

    private static class DefaultToast extends ToastDelegate {
        private final Context mContext;

        public DefaultToast(Context pContext) {
            mContext = pContext;
        }

        @Override
        public void show(String pMessage) {
            Toast.makeText(mContext, pMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
