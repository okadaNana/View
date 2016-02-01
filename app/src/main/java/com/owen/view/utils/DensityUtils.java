package com.owen.view.utils;

import android.content.Context;

/**
 *
 * Created by mike on 16/2/1.
 */
public class DensityUtils {

    private static DensityUtils sInstance;

    private Context mContext;

    private DensityUtils(Context context) {
        mContext = context.getApplicationContext();
    }

    public static DensityUtils getInstance(Context context) {
        if (null == sInstance) {
            sInstance = new DensityUtils(context);
        }

        return sInstance;
    }

    public int getScreenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public int dip2px(int i) {
        return (int) (0.5D + (double) (getDensity() * (float) i));
    }

    public float getDensity() {
        return mContext.getResources().getDisplayMetrics().density;
    }

}
