package com.luffy.generalviewlib.flipper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.luffy.generalviewlib.R;
import com.luffy.generalviewlib.base.BaseView;

import java.util.List;

/**
 * Created by lvlufei on 2019/8/15
 *
 * @name 通用翻滚控件
 * @desc
 */
public class GeneralFlipper extends BaseView {

    /**
     * 组合控件
     */
    private LinearLayout flipperRoot;
    private ImageView flipperLeftIcon;
    private ViewFlipper viewFlipper;
    private ImageView flipperRightIcon;

    /**
     * 默认值
     */
    private int defaultBackground = Color.parseColor("#F6F6F6");
    private int defaultTextColor = Color.parseColor("#666666");
    private int defaultImg = R.drawable.general_flipper_default;
    private int defaultInAnimation = R.anim.flipper_in;
    private int defaultOutAnimation = R.anim.flipper_out;

    /**
     * 属性值
     */
    /*根布局*/
    int flipper_root_background_resource;
    int flipper_root_width;
    int flipper_root_height;
    int flipper_root_marginLeft;
    int flipper_root_marginTop;
    int flipper_root_marginRight;
    int flipper_root_marginBottom;
    int flipper_root_paddingLeft;
    int flipper_root_paddingTop;
    int flipper_root_paddingRight;
    int flipper_root_paddingBottom;
    /*左边图片*/
    int flipper_left_icon_width;
    int flipper_left_icon_height;
    int flipper_left_icon_src;
    int flipper_left_icon_marginLeft;
    int flipper_left_icon_marginRight;
    int flipper_left_icon_visibility;
    /*右边图片*/
    int flipper_right_icon_width;
    int flipper_right_icon_height;
    int flipper_right_icon_src;
    int flipper_right_icon_marginLeft;
    int flipper_right_icon_marginRight;
    int flipper_right_icon_visibility;
    /*中间ViewFlipper*/
    int view_flipper_interval;
    int view_flipper_inAnimation;
    int view_flipper_outAnimation;
    boolean view_flipper_autoStart;
    /*内容*/
    int flipper_text_gravity;
    int flipper_text_maxLines;
    int flipper_text_color;
    float flipper_text_size;
    int flipper_text_textStyle;
    float flipper_text_lineSpacingExtra;

    public GeneralFlipper(Context context) {
        super(context);
    }

    public GeneralFlipper(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setLayoutView() {
        return R.layout.general_flipper_layout;
    }

    @Override
    public void initView() {
        flipperRoot = mView.findViewById(R.id.flipper_root);
        flipperLeftIcon = mView.findViewById(R.id.flipper_left_icon);
        viewFlipper = mView.findViewById(R.id.viewFlipper);
        flipperRightIcon = mView.findViewById(R.id.flipper_right_icon);
    }

    @Override
    public void initAttrs(AttributeSet attrs) {
        TypedArray attributes = mContext.obtainStyledAttributes(attrs, R.styleable.GeneralFlipper);
        /**根布局*/
        /*背景色*/
        flipper_root_background_resource = attributes.getResourceId(R.styleable.GeneralFlipper_flipper_root_background_resource, defaultBackground);
        /*布局宽高*/
        flipper_root_width = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_width, LayoutParams.MATCH_PARENT);
        flipper_root_height = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_height, LayoutParams.WRAP_CONTENT);
        /*外边距*/
        flipper_root_marginLeft = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_marginLeft, 0);
        flipper_root_marginTop = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_marginTop, 0);
        flipper_root_marginRight = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_marginRight, 0);
        flipper_root_marginBottom = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_marginBottom, 0);
        /*内边距*/
        flipper_root_paddingLeft = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_paddingLeft, 0);
        flipper_root_paddingTop = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_paddingTop, 0);
        flipper_root_paddingRight = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_paddingRight, 0);
        flipper_root_paddingBottom = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_root_paddingBottom, 0);

        /**左边图片*/
        /*布局宽高*/
        flipper_left_icon_width = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_left_icon_width, LayoutParams.WRAP_CONTENT);
        flipper_left_icon_height = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_left_icon_height, LayoutParams.WRAP_CONTENT);
        /*图片资源*/
        flipper_left_icon_src = attributes.getResourceId(R.styleable.GeneralFlipper_flipper_left_icon_src, defaultImg);
        /*外边距*/
        flipper_left_icon_marginLeft = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_left_icon_marginLeft, 0);
        flipper_left_icon_marginRight = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_left_icon_marginRight, 0);
        /*是否显示*/
        flipper_left_icon_visibility = attributes.getInt(R.styleable.GeneralFlipper_flipper_left_icon_visibility, GONE);

        /**右边图片*/
        /*布局宽高*/
        flipper_right_icon_width = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_right_icon_width, LayoutParams.WRAP_CONTENT);
        flipper_right_icon_height = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_right_icon_height, LayoutParams.WRAP_CONTENT);
        /*图片资源*/
        flipper_right_icon_src = attributes.getResourceId(R.styleable.GeneralFlipper_flipper_right_icon_src, defaultImg);
        /*外边距*/
        flipper_right_icon_marginLeft = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_right_icon_marginLeft, 0);
        flipper_right_icon_marginRight = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_right_icon_marginRight, 0);
        /*是否显示*/
        flipper_right_icon_visibility = attributes.getInt(R.styleable.GeneralFlipper_flipper_right_icon_visibility, GONE);

        /**中间ViewFlipper*/
        /*间隔时间*/
        view_flipper_interval = attributes.getInt(R.styleable.GeneralFlipper_view_flipper_interval, 3 * 1000);
        /*进入动画*/
        view_flipper_inAnimation = attributes.getResourceId(R.styleable.GeneralFlipper_view_flipper_inAnimation, defaultInAnimation);
        /*出去动画*/
        view_flipper_outAnimation = attributes.getResourceId(R.styleable.GeneralFlipper_view_flipper_outAnimation, defaultOutAnimation);
        /*是否自动开始*/
        view_flipper_autoStart = attributes.getBoolean(R.styleable.GeneralFlipper_view_flipper_autoStart, true);

        /**内容*/
        flipper_text_gravity = attributes.getInt(R.styleable.GeneralFlipper_flipper_text_gravity, 1);
        /*最大行数*/
        flipper_text_maxLines = attributes.getInt(R.styleable.GeneralFlipper_flipper_text_maxLines, 1);
        /*颜色*/
        flipper_text_color = attributes.getColor(R.styleable.GeneralFlipper_flipper_text_color, defaultTextColor);
        /*大小*/
        flipper_text_size = attributes.getInteger(R.styleable.GeneralFlipper_flipper_text_size, 14);
        /*字体*/
        flipper_text_textStyle = attributes.getInt(R.styleable.GeneralFlipper_flipper_text_textStyle, 0);
        /*文字行间距*/
        flipper_text_lineSpacingExtra = attributes.getDimensionPixelSize(R.styleable.GeneralFlipper_flipper_text_lineSpacingExtra, 5);

        /**回收*/
        attributes.recycle();
    }

    @Override
    public void bindAttrs() {
        /**根布局*/
        /*背景色*/
        flipperRoot.setBackgroundResource(flipper_root_background_resource);
        /*宽高、外边距*/
        LinearLayout.LayoutParams paramsFlipperRoot = (LayoutParams) flipperRoot.getLayoutParams();
        paramsFlipperRoot.width = flipper_root_width;
        paramsFlipperRoot.height = flipper_root_height;
        paramsFlipperRoot.setMargins(flipper_root_marginLeft, flipper_root_marginTop, flipper_root_marginRight, flipper_root_marginBottom);
        flipperRoot.setLayoutParams(paramsFlipperRoot);
        /*内边距*/
        flipperRoot.setPadding(flipper_root_paddingLeft, flipper_root_paddingTop, flipper_root_paddingRight, flipper_root_paddingBottom);

        /**左边图片*/
        /*宽高、外边距*/
        LinearLayout.LayoutParams paramsFlipperLeftIcon = (LayoutParams) flipperLeftIcon.getLayoutParams();
        paramsFlipperLeftIcon.width = flipper_left_icon_width;
        paramsFlipperLeftIcon.height = flipper_left_icon_height;
        paramsFlipperLeftIcon.setMargins(flipper_left_icon_marginLeft, 0, flipper_left_icon_marginRight, 0);
        flipperLeftIcon.setLayoutParams(paramsFlipperLeftIcon);
        /*图片资源*/
        flipperLeftIcon.setImageResource(flipper_left_icon_src);
        /*是否显示*/
        switch (flipper_left_icon_visibility) {
            case 1:
                flipperLeftIcon.setVisibility(VISIBLE);
                break;
            case 2:
                flipperLeftIcon.setVisibility(GONE);
                break;
            case 3:
                flipperLeftIcon.setVisibility(INVISIBLE);
                break;
        }

        /**右边图片*/
        /*宽高、外边距*/
        LinearLayout.LayoutParams paramsFlipperRightIcon = (LayoutParams) flipperRightIcon.getLayoutParams();
        paramsFlipperRightIcon.width = flipper_right_icon_width;
        paramsFlipperRightIcon.height = flipper_right_icon_height;
        paramsFlipperRightIcon.setMargins(flipper_right_icon_marginLeft, 0, flipper_right_icon_marginRight, 0);
        flipperRightIcon.setLayoutParams(paramsFlipperRightIcon);
        /*图片资源*/
        flipperRightIcon.setImageResource(flipper_right_icon_src);
        /*是否显示*/
        switch (flipper_right_icon_visibility) {
            case 1:
                flipperRightIcon.setVisibility(VISIBLE);
                break;
            case 2:
                flipperRightIcon.setVisibility(GONE);
                break;
            case 3:
                flipperRightIcon.setVisibility(INVISIBLE);
                break;
        }

        /**中间ViewFlipper*/
        /*间隔时间*/
        viewFlipper.setFlipInterval(view_flipper_interval);
        /*进入动画*/
        viewFlipper.setInAnimation(mContext, view_flipper_inAnimation);
        /*出去动画*/
        viewFlipper.setOutAnimation(mContext, view_flipper_outAnimation);
        /*自动开始*/
        viewFlipper.setAutoStart(view_flipper_autoStart);
    }

    /**
     * 获取ViewFlipper控件
     *
     * @return
     */
    public ViewFlipper getViewFlipper() {
        return viewFlipper;
    }

    public ImageView getFlipperLeftIcon() {
        return flipperLeftIcon;
    }

    public ImageView getFlipperRightIcon() {
        return flipperRightIcon;
    }

    /**
     * 绑定数据
     *
     * @param data
     */
    public void bindData(List<String> data) {
        if (data == null || data.size() == 0) {
            this.setVisibility(GONE);
            viewFlipper.removeAllViews();
            viewFlipper.stopFlipping();
            return;
        } else {
            this.setVisibility(VISIBLE);
            viewFlipper.removeAllViews();
            viewFlipper.stopFlipping();
            for (String content : data) {
                TextView textView = new TextView(mContext);
                switch (flipper_text_gravity) {
                    case 1:
                        textView.setGravity(Gravity.LEFT);
                        break;
                    case 2:
                        textView.setGravity(Gravity.RIGHT);
                        break;
                    case 3:
                        textView.setGravity(Gravity.CENTER);
                        break;
                    case 4:
                        textView.setGravity(Gravity.CENTER_VERTICAL);
                        break;
                    case 5:
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        break;
                }
                switch (flipper_text_textStyle) {
                    case 0:
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        break;
                    case 1:
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        break;
                    case 2:
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                        break;
                    case 3:
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                        break;
                }
                textView.setMaxLines(flipper_text_maxLines);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setTextColor(flipper_text_color);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, flipper_text_size);
                textView.setText(content);
                viewFlipper.addView(textView);
            }
            viewFlipper.startFlipping();
        }
    }
}
