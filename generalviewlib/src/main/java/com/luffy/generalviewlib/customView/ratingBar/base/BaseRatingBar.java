package com.luffy.generalviewlib.customView.ratingBar.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.luffy.generalviewlib.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2019/1/4
 *
 * @desc 公用的-星级评分控件
 */
public class BaseRatingBar extends LinearLayout implements IBaseRatingBar {

    public interface OnRatingChangeListener {
        void onRatingChange(BaseRatingBar ratingBar, float rating);
    }

    private static final int MAX_CLICK_DISTANCE = 5;
    private static final int MAX_CLICK_DURATION = 200;

    private DecimalFormat mDecimalFormat;

    private int mNumStars;
    private int mPadding = 20;
    private int mStarWidth;
    private int mStarHeight;
    private float mRating = -1;
    private float mStepSize = 1f;
    private float mPreviousRating = 0;

    private boolean mIsIndicator = false;
    private boolean mIsScrollable = true;
    private boolean mIsClickable = true;
    private boolean mClearRatingEnabled = true;

    private float mStartX;
    private float mStartY;

    private Drawable mEmptyDrawable;
    private Drawable mFilledDrawable;

    private OnRatingChangeListener mOnRatingChangeListener;

    public List<RatingBarPartialView> mRatingBarPartialViews;

    public BaseRatingBar(Context context) {
        this(context, null);
    }

    /* Call by xml layout */
    public BaseRatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context      context
     * @param attrs        attributes from XML => app:mainText="mainText"
     * @param defStyleAttr attributes from default style (Application theme or activity theme)
     */
    public BaseRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseRatingBar);
        final float rating = typedArray.getFloat(R.styleable.BaseRatingBar_srb_rating, 0);

        initParamsValue(typedArray, context);
        verifyParamsValue();
        initRatingView();
        setRating(rating);
    }

    private void initParamsValue(TypedArray typedArray, Context context) {
        mNumStars = typedArray.getInt(R.styleable.BaseRatingBar_srb_numStars, mNumStars);
        mPadding = typedArray.getDimensionPixelSize(R.styleable.BaseRatingBar_srb_starPadding, mPadding);
        mStarWidth = typedArray.getDimensionPixelSize(R.styleable.BaseRatingBar_srb_starWidth, 0);
        mStarHeight = typedArray.getDimensionPixelSize(R.styleable.BaseRatingBar_srb_starHeight, 0);
        mStepSize = typedArray.getFloat(R.styleable.BaseRatingBar_srb_stepSize, mStepSize);
        mEmptyDrawable = typedArray.hasValue(R.styleable.BaseRatingBar_srb_drawableEmpty) ? ContextCompat.getDrawable(context, typedArray.getResourceId(R.styleable.BaseRatingBar_srb_drawableEmpty, View.NO_ID)) : null;
        mFilledDrawable = typedArray.hasValue(R.styleable.BaseRatingBar_srb_drawableFilled) ? ContextCompat.getDrawable(context, typedArray.getResourceId(R.styleable.BaseRatingBar_srb_drawableFilled, View.NO_ID)) : null;
        mIsIndicator = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_isIndicator, mIsIndicator);
        mIsScrollable = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_scrollable, mIsScrollable);
        mIsClickable = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_clickable, mIsClickable);
        mClearRatingEnabled = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_clearRatingEnabled, mClearRatingEnabled);
        typedArray.recycle();
    }

    private void verifyParamsValue() {
        if (mNumStars <= 0) {
            mNumStars = 5;
        }
        if (mPadding < 0) {
            mPadding = 0;
        }
        if (mEmptyDrawable == null) {
            mEmptyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.general_rating_bar_empty);
        }
        if (mFilledDrawable == null) {
            mFilledDrawable = ContextCompat.getDrawable(getContext(), R.drawable.general_rating_bar_filled);
        }
        if (mStepSize > 1.0f) {
            mStepSize = 1.0f;
        } else if (mStepSize < 0.1f) {
            mStepSize = 0.1f;
        }
    }

    private void initRatingView() {
        mRatingBarPartialViews = new ArrayList<>();

        for (int i = 1; i <= mNumStars; i++) {
            RatingBarPartialView ratingBarPartialView = getPartialView(i, mFilledDrawable, mEmptyDrawable);
            addView(ratingBarPartialView);

            mRatingBarPartialViews.add(ratingBarPartialView);
        }
    }

    private RatingBarPartialView getPartialView(final int ratingViewId, Drawable filledDrawable, Drawable emptyDrawable) {
        RatingBarPartialView ratingBarPartialView = new RatingBarPartialView(getContext(), mStarWidth, mStarHeight);
        ratingBarPartialView.setTag(ratingViewId);
        ratingBarPartialView.setPadding(mPadding, mPadding, mPadding, mPadding);
        ratingBarPartialView.setFilledDrawable(filledDrawable);
        ratingBarPartialView.setEmptyDrawable(emptyDrawable);
        return ratingBarPartialView;
    }

    /**
     * Retain this method to let other RatingBar can custom their decrease animation.
     */
    protected void emptyRatingBar() {
        fillRatingBar(0);
    }

    /**
     * Use {maxIntOfRating} because if the rating is 3.5
     * the view which id is 3 also need to be general_rating_bar_filled.
     */
    protected void fillRatingBar(final float rating) {
        for (RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            int ratingViewId = (int) ratingBarPartialView.getTag();
            double maxIntOfRating = Math.ceil(rating);

            if (ratingViewId > maxIntOfRating) {
                ratingBarPartialView.setEmpty();
                continue;
            }

            if (ratingViewId == maxIntOfRating) {
                ratingBarPartialView.setPartialFilled(rating);
            } else {
                ratingBarPartialView.setFilled();
            }
        }
    }

    @Override
    public void setNumStars(int numStars) {
        if (numStars <= 0) {
            return;
        }

        mRatingBarPartialViews.clear();
        removeAllViews();

        mNumStars = numStars;
        initRatingView();
    }

    @Override
    public int getNumStars() {
        return mNumStars;
    }

    @Override
    public void setRating(float rating) {
        if (rating > mNumStars) {
            rating = mNumStars;
        }

        if (rating < 0) {
            rating = 0;
        }

        if (mRating == rating) {
            return;
        }

        mRating = rating;

        if (mOnRatingChangeListener != null) {
            mOnRatingChangeListener.onRatingChange(this, mRating);
        }

        fillRatingBar(rating);
    }

    @Override
    public float getRating() {
        return mRating;
    }

    @Override
    public void setStarPadding(int ratingPadding) {
        if (ratingPadding < 0) {
            return;
        }

        mPadding = ratingPadding;

        for (RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            ratingBarPartialView.setPadding(mPadding, mPadding, mPadding, mPadding);
        }
    }

    @Override
    public int getStarPadding() {
        return mPadding;
    }

    @Override
    public void setEmptyDrawableRes(@DrawableRes int res) {
        setEmptyDrawable(ContextCompat.getDrawable(getContext(), res));
    }

    @Override
    public void setFilledDrawableRes(@DrawableRes int res) {
        setFilledDrawable(ContextCompat.getDrawable(getContext(), res));
    }

    @Override
    public void setEmptyDrawable(Drawable drawable) {
        mEmptyDrawable = drawable;

        for (RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            ratingBarPartialView.setEmptyDrawable(drawable);
        }
    }

    @Override
    public void setFilledDrawable(Drawable drawable) {
        mFilledDrawable = drawable;

        for (RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            ratingBarPartialView.setFilledDrawable(drawable);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isIndicator()) {
            return false;
        }

        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = eventX;
                mStartY = eventY;
                mPreviousRating = mRating;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isScrollable()) {
                    return false;
                }

                handleMoveEvent(eventX);
                break;
            case MotionEvent.ACTION_UP:
                if (!isClickEvent(mStartX, mStartY, event) || !isClickable()) {
                    return false;
                }

                handleClickEvent(eventX);
        }

        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }

    private void handleMoveEvent(float eventX) {
        for (RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            if (eventX < ratingBarPartialView.getWidth() / 10f) {
                setRating(0);
                return;
            }

            if (!isPositionInRatingView(eventX, ratingBarPartialView)) {
                continue;
            }

            float rating = calculateRating(eventX, ratingBarPartialView);

            if (mRating != rating) {
                setRating(rating);
            }
        }
    }

    private float calculateRating(float eventX, RatingBarPartialView ratingBarPartialView) {
        DecimalFormat decimalFormat = getDecimalFormat();
        float ratioOfView = Float.parseFloat(decimalFormat.format((eventX - ratingBarPartialView.getLeft()) / ratingBarPartialView.getWidth()));
        float steps = Math.round(ratioOfView / mStepSize) * mStepSize;
        return Float.parseFloat(decimalFormat.format((int) ratingBarPartialView.getTag() - (1 - steps)));
    }

    private void handleClickEvent(float eventX) {
        for (RatingBarPartialView ratingBarPartialView : mRatingBarPartialViews) {
            if (!isPositionInRatingView(eventX, ratingBarPartialView)) {
                continue;
            }

            float rating = mStepSize == 1 ? (int) ratingBarPartialView.getTag() : calculateRating(eventX, ratingBarPartialView);

            if (mPreviousRating == rating && isClearRatingEnabled()) {
                setRating(0);
            } else {
                setRating(rating);
            }
            break;
        }
    }

    private boolean isPositionInRatingView(float eventX, View ratingView) {
        return eventX > ratingView.getLeft() && eventX < ratingView.getRight();
    }

    private boolean isClickEvent(float startX, float startY, MotionEvent event) {
        float duration = event.getEventTime() - event.getDownTime();
        if (duration > MAX_CLICK_DURATION) {
            return false;
        }

        float differenceX = Math.abs(startX - event.getX());
        float differenceY = Math.abs(startY - event.getY());
        return !(differenceX > MAX_CLICK_DISTANCE || differenceY > MAX_CLICK_DISTANCE);
    }

    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        mOnRatingChangeListener = onRatingChangeListener;
    }

    public boolean isIndicator() {
        return mIsIndicator;
    }

    public void setIsIndicator(boolean indicator) {
        mIsIndicator = indicator;
    }

    public boolean isScrollable() {
        return mIsScrollable;
    }

    public void setScrollable(boolean scrollable) {
        mIsScrollable = scrollable;
    }

    public boolean isClickable() {
        return mIsClickable;
    }

    public void setClickable(boolean clickable) {
        this.mIsClickable = clickable;
    }

    public void setClearRatingEnabled(boolean enabled) {
        this.mClearRatingEnabled = enabled;
    }

    public boolean isClearRatingEnabled() {
        return mClearRatingEnabled;
    }

    public float getStepSize() {
        return mStepSize;
    }

    public void setStepSize(@FloatRange(from = 0.1, to = 1.0) float stepSize) {
        this.mStepSize = stepSize;
    }

    public DecimalFormat getDecimalFormat() {
        if (mDecimalFormat == null) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            mDecimalFormat = new DecimalFormat("#.##", symbols);
        }
        return mDecimalFormat;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        RatingBarSavedState ss = new RatingBarSavedState(superState);
        ss.setRating(mRating);
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        RatingBarSavedState ss = (RatingBarSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setRating(ss.getRating());
    }
}