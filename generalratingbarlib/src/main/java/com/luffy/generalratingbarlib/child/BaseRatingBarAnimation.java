package com.luffy.generalratingbarlib.child;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.luffy.generalratingbarlib.base.BaseRatingBar;

import java.util.UUID;

/**
 * Created by lvlufei on 2019/1/4
 *
 * @desc 公用的-（带动画）星级评分控件
 */
public class BaseRatingBarAnimation extends BaseRatingBar {

    /*控制动画速度*/
    public static final long ANIMATION_DELAY = 15;

    protected Handler mHandler;
    protected Runnable mRunnable;
    protected String mRunnableToken = UUID.randomUUID().toString();

    protected BaseRatingBarAnimation(Context context) {
        super(context);
        init();
    }

    protected BaseRatingBarAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected BaseRatingBarAnimation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHandler = new Handler();
    }

    protected void postRunnable(Runnable runnable, long ANIMATION_DELAY) {
        if (mHandler == null) {
            mHandler = new Handler();
        }

        long timeMillis = SystemClock.uptimeMillis() + ANIMATION_DELAY;
        mHandler.postAtTime(runnable, mRunnableToken, timeMillis);
    }
}

