package com.luffy.generalviewlib.combinationView.child.textIndicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luffy.generalviewlib.R;
import com.luffy.generalviewlib.combinationView.base.BaseCombinationView;

/**
 * Created by lvlufei on 2019/10/10
 *
 * @name 通用文本指示器（展开、收起）
 * @desc
 */
public class TextIndicatorView extends BaseCombinationView {

    /**
     * 组合控件
     */
    private TextView txtContent;
    private LinearLayout layoutIndicator;
    private TextView txtIndicator;
    private ImageView imgIndicator;

    /**
     * 默认值
     */
    private int defaultTextMaxLines = 2;
    private int defaultTextColor = Color.parseColor("#666666");
    private int defaultIndicatorLayoutBackground = Color.parseColor("#F6F6F6");
    private int defaultIndicatorTextColor = Color.parseColor("#666666");

    /**
     * 属性值
     */
    /*内容*/
    int textLayoutGravity;
    int textMaxLines;
    String textContent;
    int textColor;
    float textSize;
    float textLineSpacingExtra;
    /*指示器布局*/
    int indicatorLayoutGravity;
    int indicatorLayoutBackgroundResource;
    int indicatorLayoutMarginTop;
    int indicatorLayoutMarginBottom;
    int indicatorLayoutPadding;
    /*指示器文本*/
    String indicatorTextExpand;
    String indicatorTextPackUp;
    int indicatorTextColor;
    float indicatorTextSize;
    /*指示器图片*/
    int indicatorDrawableExpand;
    int indicatorDrawablePackUp;
    int indicatorDrawableWidth;
    int indicatorDrawableHeight;
    int indicatorDrawableMarginLeft;

    public TextIndicatorView(Context context) {
        super(context);
    }

    public TextIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        handlerBusines();
    }

    @Override
    public int setLayoutView() {
        return R.layout.general_text_indicator;
    }

    @Override
    public void initView() {
        txtContent = (TextView) findViewById(R.id.txt_content);
        layoutIndicator = (LinearLayout) findViewById(R.id.layout_indicator);
        txtIndicator = (TextView) findViewById(R.id.txt_indicator);
        imgIndicator = (ImageView) findViewById(R.id.img_indicator);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        addView(mView, llp);
    }

    @Override
    public void initAttrs(AttributeSet attrs) {
        TypedArray attributes = mContext.obtainStyledAttributes(attrs, R.styleable.TextIndicatorView);
        /**内容*/
        textLayoutGravity = attributes.getInt(R.styleable.TextIndicatorView_text_layout_gravity, Gravity.LEFT);
        /*最大行数*/
        textMaxLines = attributes.getInt(R.styleable.TextIndicatorView_text_maxLines, defaultTextMaxLines);
        /*内容*/
        textContent = attributes.getString(R.styleable.TextIndicatorView_text_content);
        /*颜色*/
        textColor = attributes.getColor(R.styleable.TextIndicatorView_text_color, defaultTextColor);
        /*大小*/
        textSize = attributes.getInteger(R.styleable.TextIndicatorView_text_size, 14);
        /*文字行间距*/
        textLineSpacingExtra = attributes.getDimensionPixelSize(R.styleable.TextIndicatorView_text_lineSpacingExtra, 5);
        /**指示器布局*/
        /*位置*/
        indicatorLayoutGravity = attributes.getInt(R.styleable.TextIndicatorView_indicator_layout_gravity, Gravity.RIGHT);
        /*背景色*/
        indicatorLayoutBackgroundResource = attributes.getResourceId(R.styleable.TextIndicatorView_indicator_layout_background_resource, defaultIndicatorLayoutBackground);
        /*外边距*/
        indicatorLayoutMarginTop = attributes.getDimensionPixelSize(R.styleable.TextIndicatorView_indicator_layout_marginTop, 0);
        indicatorLayoutMarginBottom = attributes.getDimensionPixelSize(R.styleable.TextIndicatorView_indicator_layout_marginBottom, 0);
        /*内边距*/
        indicatorLayoutPadding = attributes.getDimensionPixelSize(R.styleable.TextIndicatorView_indicator_layout_padding, 5);
        /**指示器文本*/
        /*内容-展开*/
        indicatorTextExpand = attributes.getString(R.styleable.TextIndicatorView_indicator_text_expand);
        /*内容-收起*/
        indicatorTextPackUp = attributes.getString(R.styleable.TextIndicatorView_indicator_text_pack_up);
        /*颜色*/
        indicatorTextColor = attributes.getColor(R.styleable.TextIndicatorView_indicator_text_color, defaultIndicatorTextColor);
        /*大小*/
        indicatorTextSize = attributes.getInteger(R.styleable.TextIndicatorView_indicator_text_size, 14);
        /**指示器图片*/
        /*图片-展开*/
        indicatorDrawableExpand = attributes.getResourceId(R.styleable.TextIndicatorView_indicator_drawable_expand, R.drawable.general_text_indicator_expand);
        /*内容-收起*/
        indicatorDrawablePackUp = attributes.getResourceId(R.styleable.TextIndicatorView_indicator_drawable_pack_up, R.drawable.general_text_indicator_pack_up);
        /*布局宽高*/
        indicatorDrawableWidth = attributes.getDimensionPixelSize(R.styleable.TextIndicatorView_indicator_drawable_width, 14);
        indicatorDrawableHeight = attributes.getDimensionPixelSize(R.styleable.TextIndicatorView_indicator_drawable_height, 14);
        indicatorDrawableMarginLeft = attributes.getDimensionPixelSize(R.styleable.TextIndicatorView_indicator_drawable_marginLeft, 14);
        /**回收*/
        attributes.recycle();
    }

    @Override
    public void bindAttrs() {
        /**内容*/
        /*位置*/
        switch (textLayoutGravity) {
            case 1:
                txtContent.setGravity(Gravity.LEFT);
                break;
            case 2:
                txtContent.setGravity(Gravity.RIGHT);
                break;
            case 3:
                txtContent.setGravity(Gravity.CENTER);
                break;
            case 4:
                txtContent.setGravity(Gravity.CENTER_VERTICAL);
                break;
            case 5:
                txtContent.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
        }
        /*最大行数*/
        txtContent.setMaxLines(textMaxLines);
        /*内容*/
        txtContent.setText(textContent);
        /*颜色*/
        txtContent.setTextColor(textColor);
        /*大小*/
        txtContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        /*文字行间距*/
        txtContent.setLineSpacing(textLineSpacingExtra, 1);
        /**指示器布局*/
        /*背景色*/
        layoutIndicator.setBackgroundResource(indicatorLayoutBackgroundResource);
        /*位置、外边距*/
        LayoutParams params = (LayoutParams) layoutIndicator.getLayoutParams();
        switch (indicatorLayoutGravity) {
            case 1:
                params.gravity = Gravity.LEFT;
                break;
            case 2:
                params.gravity = Gravity.RIGHT;
                break;
            case 3:
                params.gravity = Gravity.CENTER;
                break;
        }
        params.setMargins(0, indicatorLayoutMarginTop, 0, indicatorLayoutMarginBottom);
        layoutIndicator.setLayoutParams(params);
        /*内边距*/
        layoutIndicator.setPadding(indicatorLayoutPadding, indicatorLayoutPadding, indicatorLayoutPadding, indicatorLayoutPadding);
        /**指示器文本*/
        /*内容-展开、收起*/
        txtIndicator.setText(indicatorTextExpand);
        /*颜色*/
        txtIndicator.setTextColor(indicatorTextColor);
        /*大小*/
        txtIndicator.setTextSize(TypedValue.COMPLEX_UNIT_SP, indicatorTextSize);
        /**指示器图片*/
        /*图片-展开、收起*/
        imgIndicator.setImageResource(indicatorDrawableExpand);
        /*布局宽高*/
        LayoutParams layoutParamsIndicatorDrawable = (LayoutParams) imgIndicator.getLayoutParams();
        layoutParamsIndicatorDrawable.width = indicatorDrawableWidth;
        layoutParamsIndicatorDrawable.height = indicatorDrawableHeight;
        layoutParamsIndicatorDrawable.setMargins(indicatorDrawableMarginLeft, 0, 0, 0);
        imgIndicator.setLayoutParams(layoutParamsIndicatorDrawable);
    }

    /**
     * 处理业务
     */
    private void handlerBusines() {
        txtContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                int lines = txtContent.getLineCount();
                if (lines > textMaxLines) {
                    layoutIndicator.setVisibility(View.VISIBLE);
                } else {
                    layoutIndicator.setVisibility(View.GONE);
                }
                txtContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        layoutIndicator.setOnClickListener(new OnClickListener() {

            /*是否展开：默认显示展开*/
            boolean isExpand = false;

            @Override
            public void onClick(View v) {
                if (isExpand) {
                    /*显示-展开*/
                    imgIndicator.setImageResource(indicatorDrawableExpand);
                    txtIndicator.setText(indicatorTextExpand);
                    txtContent.setMaxLines(textMaxLines);
                } else {
                    /*显示-收起*/
                    imgIndicator.setImageResource(indicatorDrawablePackUp);
                    txtIndicator.setText(indicatorTextPackUp);
                    txtContent.setMaxLines(Integer.MAX_VALUE);
                }
                isExpand = !isExpand;
            }
        });
    }

    /**
     * 设置文本文字
     *
     * @param content
     */
    public void setContent(String content) {
        txtContent.setText(content);
        /*处理业务*/
        handlerBusines();
        invalidate();
    }

    /**
     * 设置html片段
     */
    public void setHtmlContent(String content) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txtContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
            } else {
                txtContent.setText(Html.fromHtml(content));
            }
        } catch (Exception e) {
            txtContent.setText(content);
        }
        /*处理业务*/
        handlerBusines();
        invalidate();
    }

}


