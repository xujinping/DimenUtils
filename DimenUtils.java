package com.example.xjp.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Window;

/**
 * Created by xujinping on 2018/9/20.
 */
public class DimenUtils {

    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static int sp2px(Context context, int sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity + 0.5f);
    }

    public static int px2dp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    public static int px2sp(Context context, int px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity + 0.5f);
    }

    /**
     * 屏幕宽
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 屏幕高
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏高度
     *该方法获取需要在onWindowFocusChanged方法回调之后
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = getStatusBarByResId(context);
        if (statusBarHeight <= 0) {
            statusBarHeight = getStatusBarByReflex(context);
        }
        return statusBarHeight;
    }

    /**
     * 通过状态栏资源id来获取状态栏高度
     * @param context
     * @return
     */
    private static int getStatusBarByResId(Context context) {
        int height = 0;
        //获取状态栏资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            try {
                height = context.getResources().getDimensionPixelSize(resourceId);
            } catch (Exception e) {
            }
        }
        return height;
    }

    /**
     * 通过Activity的内容距离顶部高度来获取状态栏高度，该方法获取需要在onWindowFocusChanged回调之后
     * @param activity
     * @return
     */
    public static int getStatusBarByTop(Activity activity) {
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /**
     * 通过反射获取状态栏高度
     * @param context
     * @return
     */
    private static int getStatusBarByReflex(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
	
	//test修改
	private static int get() {
		int a = 0;
		int b = 1;
		return 1;
	}
}
