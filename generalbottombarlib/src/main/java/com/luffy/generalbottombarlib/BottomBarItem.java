package com.luffy.generalbottombarlib;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by lvlufei on 2019/4/12
 *
 * @desc 底部tab条目
 */
public class BottomBarItem extends LinearLayout {

    private Context context;
    private Drawable normalIcon;//普通状态图标的资源id
    private Drawable selectedIcon;//选中状态图标的资源id
    private String title;//文本
    private int titleTextSize = 12;//文字大小 默认为12sp
    private int titleNormalColor;    //描述文本的默认显示颜色
    private int titleSelectedColor;  //述文本的默认选中显示颜色
    private int marginTop = 0;//文字和图标的距离,默认0dp
    private boolean openTouchBg = false;// 是否开启触摸背景，默认关闭
    private Drawable touchDrawable;//触摸时的背景
    private int iconWidth;//图标的宽度
    private int iconHeight;//图标的高度
    private int itemPadding;//BottomBarItem的padding
    private int unreadTextSize = 10; //未读数默认字体大小10sp
    private int unreadNumThreshold = 99;//未读数阈值
    private int unreadTextColor;//未读数字体颜色
    private Drawable unreadTextBg;//未读数字体背景
    private int msgTextSize = 6; //消息默认字体大小6sp
    private int msgTextColor;//消息文字颜色
    private Drawable msgTextBg;//消息文字背景
    private Drawable notifyPointBg;//小红点背景

    private ImageView mImageView;
    private TextView mTvUnread;
    private TextView mTvNotify;
    private TextView mTvMsg;
    private TextView mTextView;

    public BottomBarItem(Context context) {
        super(context);
    }

    public BottomBarItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomBarItem);
        /*初始化属性*/
        initAttrs(ta);
        ta.recycle();
        /*检查值是否合法*/
        checkValues();
        /*初始化相关操作*/
        init();
    }

    private void initAttrs(TypedArray ta) {
        normalIcon = ta.getDrawable(R.styleable.BottomBarItem_iconNormal);
        selectedIcon = ta.getDrawable(R.styleable.BottomBarItem_iconSelected);

        title = ta.getString(R.styleable.BottomBarItem_itemText);
        titleTextSize = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemTextSize, UIUtils.sp2px(context, titleTextSize));

        titleNormalColor = ta.getColor(R.styleable.BottomBarItem_textColorNormal, Color.parseColor("#999999"));
        titleSelectedColor = ta.getColor(R.styleable.BottomBarItem_textColorSelected, Color.parseColor("#FF0000"));

        marginTop = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemMarginTop, UIUtils.dip2Px(context, marginTop));

        openTouchBg = ta.getBoolean(R.styleable.BottomBarItem_openTouchBg, openTouchBg);
        touchDrawable = ta.getDrawable(R.styleable.BottomBarItem_touchDrawable);

        iconWidth = ta.getDimensionPixelSize(R.styleable.BottomBarItem_iconWidth, 0);
        iconHeight = ta.getDimensionPixelSize(R.styleable.BottomBarItem_iconHeight, 0);
        itemPadding = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemPadding, 0);

        unreadTextSize = ta.getDimensionPixelSize(R.styleable.BottomBarItem_unreadTextSize, UIUtils.sp2px(context, unreadTextSize));
        unreadTextColor = ta.getColor(R.styleable.BottomBarItem_unreadTextColor, Color.parseColor("#FF0000"));
        unreadTextBg = ta.getDrawable(R.styleable.BottomBarItem_unreadTextBg);

        msgTextSize = ta.getDimensionPixelSize(R.styleable.BottomBarItem_msgTextSize, UIUtils.sp2px(context, msgTextSize));
        msgTextColor = ta.getColor(R.styleable.BottomBarItem_msgTextColor, Color.parseColor("#FF0000"));
        msgTextBg = ta.getDrawable(R.styleable.BottomBarItem_msgTextBg);

        notifyPointBg = ta.getDrawable(R.styleable.BottomBarItem_notifyPointBg);

        unreadNumThreshold = ta.getInteger(R.styleable.BottomBarItem_unreadThreshold, unreadNumThreshold);
    }

    /**
     * 检查传入的值是否完善
     */
    private void checkValues() {
        if (normalIcon == null) {
            throw new IllegalStateException("You have not set the normal icon");
        }
        if (selectedIcon == null) {
            throw new IllegalStateException("You have not set the selected icon");
        }
        if (openTouchBg && touchDrawable == null) {
            //如果有开启触摸背景效果但是没有传对应的drawable
            throw new IllegalStateException("Touch effect is turned on, but touchDrawable is not specified");
        }
        if (unreadTextBg == null) {
            unreadTextBg = getResources().getDrawable(R.drawable.bottom_bar_shape_unread);
        }
        if (msgTextBg == null) {
            msgTextBg = getResources().getDrawable(R.drawable.bottom_bar_shape_msg);
        }
        if (notifyPointBg == null) {
            notifyPointBg = getResources().getDrawable(R.drawable.bottom_bar_shape_notify_point);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        View view = initView();
        mImageView.setImageDrawable(normalIcon);
        if (iconWidth != 0 && iconHeight != 0) {
            //如果有设置图标的宽度和高度，则设置ImageView的宽高
            FrameLayout.LayoutParams imageLayoutParams = (FrameLayout.LayoutParams) mImageView.getLayoutParams();
            imageLayoutParams.width = iconWidth;
            imageLayoutParams.height = iconHeight;
            mImageView.setLayoutParams(imageLayoutParams);
        }

        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);//设置底部文字字体大小

        mTvUnread.setTextSize(TypedValue.COMPLEX_UNIT_PX, unreadTextSize);//设置未读数的字体大小
        mTvUnread.setTextColor(unreadTextColor);//设置未读数字体颜色
        mTvUnread.setBackground(unreadTextBg);//设置未读数背景

        mTvMsg.setTextSize(TypedValue.COMPLEX_UNIT_PX, msgTextSize);//设置提示文字的字体大小
        mTvMsg.setTextColor(msgTextColor);//设置提示文字的字体颜色
        mTvMsg.setBackground(msgTextBg);//设置提示文字的背景颜色

        mTvNotify.setBackground(notifyPointBg);//设置提示点的背景颜色

        mTextView.setTextColor(titleNormalColor);//设置底部文字字体颜色
        mTextView.setText(title);//设置标签文字

        LayoutParams textLayoutParams = (LayoutParams) mTextView.getLayoutParams();
        textLayoutParams.topMargin = marginTop;
        mTextView.setLayoutParams(textLayoutParams);

        if (openTouchBg) {
            //如果有开启触摸背景
            setBackground(touchDrawable);
        }

        addView(view);
    }

    @NonNull
    private View initView() {
        View view = View.inflate(context, R.layout.item_bottom_bar, null);
        if (itemPadding != 0) {
            //如果有设置item的padding
            view.setPadding(itemPadding, itemPadding, itemPadding, itemPadding);
        }
        mImageView = view.findViewById(R.id.iv_icon);
        mTvUnread = view.findViewById(R.id.tv_unred_num);
        mTvMsg = view.findViewById(R.id.tv_msg);
        mTvNotify = view.findViewById(R.id.tv_point);
        mTextView = view.findViewById(R.id.tv_text);
        return view;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public void setNormalIcon(Drawable normalIcon) {
        this.normalIcon = normalIcon;
        refreshTab();
    }

    public void setNormalIcon(int resId) {
        setNormalIcon(UIUtils.getDrawable(context, resId));
    }

    public void setSelectedIcon(Drawable selectedIcon) {
        this.selectedIcon = selectedIcon;
        refreshTab();
    }

    public void setSelectedIcon(int resId) {
        setSelectedIcon(UIUtils.getDrawable(context, resId));
    }

    public void refreshTab(boolean isSelected) {
        setSelected(isSelected);
        refreshTab();
    }

    public void refreshTab() {
        mImageView.setImageDrawable(isSelected() ? selectedIcon : normalIcon);
        mTextView.setTextColor(isSelected() ? titleSelectedColor : titleNormalColor);
    }

    private void setTvVisible(TextView tv) {
        //都设置为不可见
        mTvUnread.setVisibility(GONE);
        mTvMsg.setVisibility(GONE);
        mTvNotify.setVisibility(GONE);
        //设置为可见
        tv.setVisibility(VISIBLE);
    }

    public int getUnreadNumThreshold() {
        return unreadNumThreshold;
    }

    public void setUnreadNumThreshold(int unreadNumThreshold) {
        this.unreadNumThreshold = unreadNumThreshold;
    }

    public void setUnreadNum(int unreadNum) {
        setTvVisible(mTvUnread);
        if (unreadNum <= 0) {
            mTvUnread.setVisibility(GONE);
        } else if (unreadNum <= unreadNumThreshold) {
            mTvUnread.setText(String.valueOf(unreadNum));
        } else {
            mTvUnread.setText(String.format(Locale.CHINA, "%d+", unreadNumThreshold));
        }
    }

    public void setMsg(String msg) {
        setTvVisible(mTvMsg);
        mTvMsg.setText(msg);
    }

    public void hideMsg() {
        mTvMsg.setVisibility(GONE);
    }

    public void showNotify() {
        setTvVisible(mTvNotify);
    }

    public void hideNotify() {
        mTvNotify.setVisibility(GONE);
    }

    public BottomBarItem create(Builder builder) {
        this.context = builder.context;
        this.normalIcon = builder.normalIcon;
        this.selectedIcon = builder.selectedIcon;
        this.title = builder.title;
        this.titleTextSize = builder.titleTextSize;
        this.titleNormalColor = builder.titleNormalColor;
        this.titleSelectedColor = builder.titleSelectedColor;
        this.marginTop = builder.marginTop;
        this.openTouchBg = builder.openTouchBg;
        this.touchDrawable = builder.touchDrawable;
        this.iconWidth = builder.iconWidth;
        this.iconHeight = builder.iconHeight;
        this.itemPadding = builder.itemPadding;
        this.unreadTextSize = builder.unreadTextSize;
        this.unreadTextColor = builder.unreadTextColor;
        this.unreadTextBg = builder.unreadTextBg;
        this.unreadNumThreshold = builder.unreadNumThreshold;
        this.msgTextSize = builder.msgTextSize;
        this.msgTextColor = builder.msgTextColor;
        this.msgTextBg = builder.msgTextBg;
        this.notifyPointBg = builder.notifyPointBg;

        checkValues();
        init();
        return this;
    }

    public static final class Builder {
        private Context context;
        private Drawable normalIcon;//普通状态图标的资源id
        private Drawable selectedIcon;//选中状态图标的资源id
        private String title;//标题
        private int titleTextSize;//字体大小
        private int titleNormalColor;    //描述文本的默认显示颜色
        private int titleSelectedColor;  //述文本的默认选中显示颜色
        private int marginTop;//文字和图标的距离
        private boolean openTouchBg;// 是否开启触摸背景，默认关闭
        private Drawable touchDrawable;//触摸时的背景
        private int iconWidth;//图标的宽度
        private int iconHeight;//图标的高度
        private int itemPadding;//BottomBarItem的padding
        private int unreadTextSize; //未读数字体大小
        private int unreadNumThreshold;//未读数阈值
        private int unreadTextColor;//未读数字体颜色
        private Drawable unreadTextBg;//未读数文字背景
        private int msgTextSize; //消息字体大小
        private int msgTextColor;//消息文字颜色
        private Drawable msgTextBg;//消息提醒背景颜色
        private Drawable notifyPointBg;//小红点背景颜色

        public Builder(Context context) {
            this.context = context;
            titleTextSize = UIUtils.sp2px(context, 12);
            titleNormalColor = Color.parseColor("#999999");
            titleSelectedColor = Color.parseColor("#ff0000");
            unreadTextSize = UIUtils.sp2px(context, 10);
            msgTextSize = UIUtils.sp2px(context, 6);
            unreadTextColor = Color.parseColor("#FFFFFF");
            unreadNumThreshold = 99;
            msgTextColor = Color.parseColor("#FFFFFF");
        }

        public Builder normalIcon(Drawable normalIcon) {
            this.normalIcon = normalIcon;
            return this;
        }

        public Builder selectedIcon(Drawable selectedIcon) {
            this.selectedIcon = selectedIcon;
            return this;
        }

        public Builder title(int titleId) {
            this.title = context.getString(titleId);
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder titleTextSize(int titleTextSize) {
            this.titleTextSize = UIUtils.sp2px(context, titleTextSize);
            return this;
        }

        public Builder titleNormalColor(int titleNormalColor) {
            this.titleNormalColor = getColor(titleNormalColor);
            return this;
        }

        public Builder titleSelectedColor(int titleSelectedColor) {
            this.titleSelectedColor = getColor(titleSelectedColor);
            return this;
        }

        public Builder marginTop(int marginTop) {
            this.marginTop = marginTop;
            return this;
        }

        public Builder openTouchBg(boolean openTouchBg) {
            this.openTouchBg = openTouchBg;
            return this;
        }

        public Builder touchDrawable(Drawable touchDrawable) {
            this.touchDrawable = touchDrawable;
            return this;
        }

        public Builder iconWidth(int iconWidth) {
            this.iconWidth = iconWidth;
            return this;
        }

        public Builder iconHeight(int iconHeight) {
            this.iconHeight = iconHeight;
            return this;
        }

        public Builder itemPadding(int itemPadding) {
            this.itemPadding = itemPadding;
            return this;
        }

        public Builder unreadTextSize(int unreadTextSize) {
            this.unreadTextSize = UIUtils.sp2px(context, unreadTextSize);
            return this;
        }

        public Builder unreadNumThreshold(int unreadNumThreshold) {
            this.unreadNumThreshold = unreadNumThreshold;
            return this;
        }

        public Builder msgTextSize(int msgTextSize) {
            this.msgTextSize = UIUtils.sp2px(context, msgTextSize);
            return this;
        }

        public Builder unreadTextBg(Drawable unreadTextBg) {
            this.unreadTextBg = unreadTextBg;
            return this;
        }

        public Builder unreadTextColor(int unreadTextColor) {
            this.unreadTextColor = getColor(unreadTextColor);
            return this;
        }

        public Builder msgTextColor(int msgTextColor) {
            this.msgTextColor = getColor(msgTextColor);
            return this;
        }

        public Builder msgTextBg(Drawable msgTextBg) {
            this.msgTextBg = msgTextBg;
            return this;
        }

        public Builder notifyPointBg(Drawable notifyPointBg) {
            this.notifyPointBg = notifyPointBg;
            return this;
        }

        public BottomBarItem create(Drawable normalIcon, Drawable selectedIcon, String text) {
            this.normalIcon = normalIcon;
            this.selectedIcon = selectedIcon;
            title = text;

            BottomBarItem bottomBarItem = new BottomBarItem(context);
            return bottomBarItem.create(this);
        }

        public BottomBarItem create(int normalIconId, int selectedIconId, String text) {
            return create(UIUtils.getDrawable(context, normalIconId), UIUtils.getDrawable(context, selectedIconId), text);
        }

        private int getColor(int colorId) {
            return context.getResources().getColor(colorId);
        }
    }
}
