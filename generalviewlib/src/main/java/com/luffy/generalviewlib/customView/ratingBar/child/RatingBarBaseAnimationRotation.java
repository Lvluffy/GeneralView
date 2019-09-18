package com.luffy.generalviewlib.customView.ratingBar.child;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.luffy.generalviewlib.R;
import com.luffy.generalviewlib.customView.ratingBar.base.RatingBarPartialView;

/**
 * Created by lvlufei on 2019/1/4
 *
 * @desc （带旋转动画）星级评分控件
 */
public class RatingBarBaseAnimationRotation extends BaseRatingBarAnimation {

    public RatingBarBaseAnimationRotation(Context context) {
        super(context);
    }

    public RatingBarBaseAnimationRotation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RatingBarBaseAnimationRotation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void emptyRatingBar() {
        // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        if (mRunnable != null) {
            mHandler.removeCallbacksAndMessages(mRunnableToken);
        }

        long delay = 0;
        for (final RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ratingBarPartialView.setEmpty();
                }
            }, delay += 5);
        }
    }

    @Override
    protected void fillRatingBar(final float rating) {
        // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        if (mRunnable != null) {
            mHandler.removeCallbacksAndMessages(mRunnableToken);
        }

        for (final RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            final int ratingViewId = (int) ratingBarPartialView.getTag();
            final double maxIntOfRating = Math.ceil(rating);

            if (ratingViewId > maxIntOfRating) {
                ratingBarPartialView.setEmpty();
                continue;
            }

            mRunnable = getAnimationRunnable(rating, ratingBarPartialView, ratingViewId, maxIntOfRating);
            postRunnable(mRunnable, ANIMATION_DELAY);
        }
    }

    @NonNull
    private Runnable getAnimationRunnable(final float rating, final RatingBarPartialView ratingBarPartialView, final int ratingViewId, final double maxIntOfRating) {
        return new Runnable() {
            @Override
            public void run() {
                if (ratingViewId == maxIntOfRating) {
                    ratingBarPartialView.setPartialFilled(rating);
                } else {
                    ratingBarPartialView.setFilled();
                }

                if (ratingViewId == rating) {
                    Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rating_bar_rotation);
                    ratingBarPartialView.startAnimation(rotation);
                }
            }
        };
    }
}