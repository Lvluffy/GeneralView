package com.luffy.generalviewlib.custom.button;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luffy.generalviewlib.R;
import com.luffy.generalviewlib.base.BaseView;

/**
 * Created by lvlufei on 2017/1/1
 *
 * @desc 通用按钮
 */
public class GeneralButton extends BaseView {
    RelativeLayout layout;
    TextView textView;
    ImageView imageView;

    int enableTrue = R.drawable.general_btn_normal_full_radius_stroke_false_primary_selector;
    int enableFalse = R.drawable.general_btn_disable_full_radius_stroke_false_selector;

    public GeneralButton(Context context) {
        super(context);
    }

    public GeneralButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setLayoutView() {
        return R.layout.general_button_layout;
    }

    @Override
    public void initView() {
        layout = (RelativeLayout) findViewById(R.id.base_btn_layout);
        textView = (TextView) findViewById(R.id.base_btn_txt);
        imageView = (ImageView) findViewById(R.id.base_btn_icon);
    }

    /**
     * 设置文字内容
     *
     * @param content
     */
    public void setText(String content) {
        textView.setText(content);
    }

    /**
     * 设置文字内容
     *
     * @param content
     */
    public void setText(int content) {
        textView.setText(getResources().getString(content));
    }

    /**
     * @return 返回内容
     */
    public String getText() {
        return textView.getText().toString().trim();
    }


    /**
     * 设置文字的颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        textView.setTextColor(ContextCompat.getColor(mContext, color));
    }


    /**
     * 设置文字的大小
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    /**
     * 设置加载动画
     *
     * @param loadable
     */
    public void setLoading(boolean loadable) {
        if (loadable) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1000);
            rotateAnimation.setRepeatCount(Integer.MAX_VALUE);
            imageView.startAnimation(rotateAnimation);
            imageView.setVisibility(VISIBLE);
            //加载时，不能点击
            layout.setClickable(true);
        } else {
            imageView.setVisibility(GONE);
            imageView.clearAnimation();
            //加载完毕，能点击
            layout.setClickable(false);
        }
    }

    public int getEnableTrue() {
        return enableTrue;
    }

    public void setEnableTrue(int enableTrue) {
        this.enableTrue = enableTrue;
    }

    public int getEnableFalse() {
        return enableFalse;
    }

    public void setEnableFalse(int enableFalse) {
        this.enableFalse = enableFalse;
    }

    /**
     * 设置
     *
     * @param enable
     */
    public void setEnable(boolean enable) {
        textView.setEnabled(enable);
        if (enable) {
            layout.setBackgroundResource(getEnableTrue());
            //没有禁用可以点击
            layout.setClickable(false);
        } else {
            layout.setBackgroundResource(getEnableFalse());
            //禁用时候不能点击
            layout.setClickable(true);
        }
    }

    /**
     * 设置布局高度
     *
     * @param height
     */
    public void setLayoutHeight(int height) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.height = height;
        layout.setLayoutParams(layoutParams);
    }

    /**
     * 设置布局宽度
     *
     * @param width
     */
    public void setLayoutWidth(int width) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.width = width;
        layout.setLayoutParams(layoutParams);
    }

    /**
     * 设置布局高度、宽度
     *
     * @param height
     * @param width
     */
    public void setLayoutHeightWidth(int height, int width) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = width;
        layout.setLayoutParams(layoutParams);
    }
}