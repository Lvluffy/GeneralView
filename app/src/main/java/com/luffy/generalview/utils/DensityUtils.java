package com.luffy.generalview.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 单位转换-辅助工具
 */
public class DensityUtils {

    private DensityUtils() {
    }

    public static DensityUtils getInstance() {
        return DensityUtilsHelper.mDensityUtils;
    }

    private static class DensityUtilsHelper {
        private static DensityUtils mDensityUtils;

        static {
            mDensityUtils = new DensityUtils();
        }
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public int px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public int px2sp(Context context, float pxVal) {
        return (int) (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}