package com.luffy.generalviewlib.customView.ratingBar.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Created by lvlufei on 2019/12/13
 *
 * @name
 * @desc
 */
interface IBaseRatingBar {

    void setNumStars(int numStars);

    int getNumStars();

    void setRating(float rating);

    float getRating();

    void setStarPadding(int ratingPadding);

    int getStarPadding();

    void setEmptyDrawable(Drawable drawable);

    void setEmptyDrawableRes(@DrawableRes int res);

    void setFilledDrawable(Drawable drawable);

    void setFilledDrawableRes(@DrawableRes int res);

}
