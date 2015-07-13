package com.lhh.sa.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by linhonghong on 2015/7/13.
 */
public class SlideActivityUtil {

    public static final int DISPLAY_WIDTH = 0;
    public static final int DISPLAY_HEIGHT = 1;
    public static final int DISPLAY_SIZE = 2;

    public static final float ACTION_START_X = 0.2f;

    public static int[] getDisplaySize(Context context){
        int[] display_size = new int[DISPLAY_SIZE];

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        display_size[DISPLAY_WIDTH] = dm.widthPixels;
        display_size[DISPLAY_HEIGHT] = dm.heightPixels;

        return display_size;
    }

    /**
     * 获取屏幕开始滑动的计算位置
     * @return
     */
    public static int getDisplayActionStartX(Context context, float x){
        int[] display_size = getDisplaySize(context);
        return (int)(display_size[DISPLAY_WIDTH] * x);
    }

    /**
     * 获取屏幕开始滑动的计算位置，初始为三分之一的屏幕
     * @return
     */
    public static int getDisplayActionStartX(Context context){
        int[] display_size = getDisplaySize(context);
        return getDisplayActionStartX(context, ACTION_START_X);
    }
}