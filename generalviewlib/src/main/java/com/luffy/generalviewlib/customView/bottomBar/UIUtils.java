package com.luffy.generalviewlib.customView.bottomBar;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by lvlufei on 2019/4/12
 *
 * @desc
 */
public class UIUtils {

    /**
     * dip转px
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dip2Px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getColor(Context context, int colorId) {
        return context.getResources().getColor(colorId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }
}
