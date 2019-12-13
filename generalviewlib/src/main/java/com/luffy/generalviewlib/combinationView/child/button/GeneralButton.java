package com.luffy.generalviewlib.combinationView.child.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.luffy.generalviewlib.combinationView.base.BaseCombinationView;

/**
 * Created by lvlufei on 2017/1/1
 *
 * @desc 通用按钮
 */
public class GeneralButton extends BaseCombinationView {
    /**
     * 组合控件
     */
    private RelativeLayout layout;
    private TextView textView;
    private ImageView imageView;

    /**
     * 默认值
     */
    private int enableTrue = R.drawable.general_btn_normal_full_radius_stroke_false_primary_selector;
    private int enableFalse = R.drawable.general_btn_disable_full_radius_stroke_false_selector;

    /**
     * 属性值
     */
    /*根布局*/
    private int button_root_enable_true_background_resource;
    private int button_root_enable_false_background_resource;
    private int button_root_width;
    private int button_root_height;
    private boolean button_root_clickable;
    /*文本*/
    private int button_text_color;
    private int button_text_size;
    private String button_text_content;
    private int button_text_style;

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
        layout = findViewById(R.id.base_btn_layout);
        textView = findViewById(R.id.base_btn_txt);
        imageView = findViewById(R.id.base_btn_icon);
    }

    @Override
    public void initAttrs(AttributeSet attrs) {
        TypedArray attributes = mContext.obtainStyledAttributes(attrs, R.styleable.GeneralButton);
        /*根布局*/
        //背景色
        button_root_enable_true_background_resource = attributes.getResourceId(R.styleable.GeneralButton_button_root_enable_true_background_resource, enableTrue);
        button_root_enable_false_background_resource = attributes.getResourceId(R.styleable.GeneralButton_button_root_enable_false_background_resource, enableFalse);
        //布局宽高
        button_root_width = attributes.getDimensionPixelSize(R.styleable.GeneralButton_button_root_width, LayoutParams.MATCH_PARENT);
        button_root_height = attributes.getDimensionPixelSize(R.styleable.GeneralButton_button_root_height, LayoutParams.WRAP_CONTENT);
        //是否可点击
        button_root_clickable = attributes.getBoolean(R.styleable.GeneralButton_button_root_clickable, true);
        /*文本*/
        //颜色
        button_text_color = attributes.getColor(R.styleable.GeneralButton_button_text_color, Color.parseColor("#ffffff"));
        //大小
        button_text_size = attributes.getInteger(R.styleable.GeneralButton_button_text_size, 14);
        //内容
        button_text_content = attributes.getString(R.styleable.GeneralButton_button_text_content);
        //样式
        button_text_style = attributes.getInt(R.styleable.GeneralButton_button_text_style, 1);
        /*回收*/
        attributes.recycle();
    }

    @Override
    public void bindAttrs() {
        /*根布局*/
        //宽高
        LinearLayout.LayoutParams paramsButtonRoot = (LayoutParams) layout.getLayoutParams();
        paramsButtonRoot.width = button_root_width;
        paramsButtonRoot.height = button_root_height;
        layout.setLayoutParams(paramsButtonRoot);
        //是否可点击+背景色
        if (button_root_clickable) {
            layout.setBackgroundResource(button_root_enable_true_background_resource);
            //没有禁用可以点击
            layout.setClickable(false);
        } else {
            layout.setBackgroundResource(button_root_enable_false_background_resource);
            //禁用时候不能点击
            layout.setClickable(true);
        }
        /*文本*/
        //颜色
        textView.setTextColor(button_text_color);
        //大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, button_text_size);
        //内容
        textView.setText(button_text_content);
        //样式
        switch (button_text_style) {
            case 1:
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                break;
            case 2:
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
            case 3:
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                break;
        }
    }

    /**
     * 设置文字内容
     *
     * @param content 内容
     */
    public void setText(String content) {
        textView.setText(content);
    }

    /**
     * 设置文字内容
     *
     * @param content 内容
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
     * @param color 颜色
     */
    public void setTextColor(int color) {
        textView.setTextColor(ContextCompat.getColor(mContext, color));
    }

    /**
     * 设置文字的大小
     *
     * @param textSize 文字大小
     */
    public void setTextSize(int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    /**
     * 设置加载动画
     *
     * @param loadable 是否加载动画
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

    private int getEnableTrue() {
        return enableTrue;
    }

    public void setEnableTrue(int enableTrue) {
        this.enableTrue = enableTrue;
    }

    private int getEnableFalse() {
        return enableFalse;
    }

    public void setEnableFalse(int enableFalse) {
        this.enableFalse = enableFalse;
    }

    /**
     * 设置
     *
     * @param enable 是否可点击
     */
    public void setEnable(boolean enable) {
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
     * @param height 高度
     */
    public void setLayoutHeight(int height) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.height = height;
        layout.setLayoutParams(layoutParams);
    }

    /**
     * 设置布局宽度
     *
     * @param width 宽度
     */
    public void setLayoutWidth(int width) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.width = width;
        layout.setLayoutParams(layoutParams);
    }

    /**
     * 设置布局高度、宽度
     *
     * @param height 高度
     * @param width  宽度
     */
    public void setLayoutHeightWidth(int height, int width) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = width;
        layout.setLayoutParams(layoutParams);
    }
}