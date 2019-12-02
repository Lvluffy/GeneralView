/*
 * Copyright (C) 2016 Jared Rummler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.luffy.generalviewlib.inheritedView.spinner;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.luffy.generalviewlib.R;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lvlufei on 2019/12/2
 *
 * @name 自定义 PopupWindow实现样式
 * @desc
 */
public class GeneralSpinner extends AppCompatTextView {

    private OnNothingSelectedListener onNothingSelectedListener;
    private OnItemSelectedListener onItemSelectedListener;
    private GeneralSpinnerBaseAdapter adapter; //listView的适配
    private PopupWindow popupWindow; //使用popupWindow控件 样式
    private ListView listView; //布局
    private Drawable arrowDrawable; //箭头布局 视图
    private boolean hideArrow;//xml中arrow是否隐藏，默认不隐藏false
    private boolean nothingSelected;
    private int popupWindowMaxHeight;//spinner下拉框整体高度 最大高度
    private int popupWindowHeight;//spinner下拉框整体高度
    private int backgroundColor;
    private int backgroundSelector;
    private int arrowColor;
    private int arrowColorDisabled;
    private int textColor; //字体颜色
    private Context context;

    public GeneralSpinner(Context context) {
        super(context);
        this.context = context;
        init(context, null);
    }

    public GeneralSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    public GeneralSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    /**
     * 构建
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        //获取attrs.xml中设置的参数
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GeneralSpinner);

        //默认色设置
        int defaultColor = getTextColors().getDefaultColor();

        boolean rtl = Utils.isRtl(context);

        try {
            //背景色 默认白色
            backgroundColor = typedArray.getColor(R.styleable.GeneralSpinner_ms_background_color, Color.WHITE);
            //背景 选中色
            backgroundSelector = typedArray.getResourceId(R.styleable.GeneralSpinner_ms_background_selector, 0);
            textColor = typedArray.getColor(R.styleable.GeneralSpinner_ms_text_color, defaultColor);
            arrowColor = typedArray.getColor(R.styleable.GeneralSpinner_ms_arrow_tint, textColor);
            hideArrow = typedArray.getBoolean(R.styleable.GeneralSpinner_ms_hide_arrow, false);
            popupWindowMaxHeight = typedArray.getDimensionPixelSize(R.styleable.GeneralSpinner_ms_popupwindow_maxheight, 0);
            popupWindowHeight = typedArray.getLayoutDimension(R.styleable.GeneralSpinner_ms_popupwindow_height, WindowManager.LayoutParams.WRAP_CONTENT);
            arrowColorDisabled = Utils.lighter(arrowColor, 0.8f);
        } finally {
            typedArray.recycle();
        }

        //设置字体显示 居中
        Resources resources = getResources();
        int left = resources.getDimensionPixelSize(R.dimen.spinner_padding_left);
        int right = resources.getDimensionPixelSize(R.dimen.spinner_padding_right);
        if (rtl) {
            right = resources.getDimensionPixelSize(R.dimen.spinner_padding_left);
        } else {
            left = resources.getDimensionPixelSize(R.dimen.spinner_padding_right);
        }

        setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        setClickable(true);
        setPadding(left, 0, right, 0);

        //设置 在界面中的显示样式，以及点击样式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && rtl) {
            setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            setTextDirection(View.TEXT_DIRECTION_RTL);
        }

        /**设置箭头布局
         *
         */
        if (!hideArrow) {
            arrowDrawable = ContextCompat.getDrawable(context, R.drawable.ms_arrow).mutate();
            arrowDrawable.setColorFilter(arrowColor, PorterDuff.Mode.SRC_IN);
            if (rtl) {
                setCompoundDrawablesWithIntrinsicBounds(arrowDrawable, null, null, null);
            } else {
                setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDrawable, null);
            }
        }

        //创建布局 使用listView控件展示items
        listView = new ListView(context);
        listView.setId(getId());
        listView.setDivider(new ColorDrawable(Color.GRAY));
        listView.setDividerHeight(1);
        listView.setItemsCanFocus(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nothingSelected = false;
                Object item = adapter.get(position);
                collapse();
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(GeneralSpinner.this, position, id, item);
                }
            }
        });

        //使用PopupWindow控件承载数据
        popupWindow = new PopupWindow(context);
        popupWindow.setContentView(listView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        //设置背景色、阴影
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(16);
            popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ms_popwindow_bg));// R.drawable.ms__drawable
        } else {
            popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ms_popwindow_bg));
        }

        //设置背景，默认是白色
        if (backgroundColor != Color.WHITE) {
            setBackgroundColor(backgroundColor);
        } else if (backgroundSelector != 0) {
            //改变最底层颜色
            setBackgroundResource(backgroundSelector);
        }

        //数据显示颜色
        if (textColor != defaultColor) {
            setTextColor(textColor);
        }

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                if (nothingSelected && onNothingSelectedListener != null) {
                    onNothingSelectedListener.onNothingSelected(GeneralSpinner.this);
                }
                if (!hideArrow) {
                    animateArrow(false);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        popupWindow.setWidth(MeasureSpec.getSize(widthMeasureSpec));
        popupWindow.setHeight(calculatePopupWindowHeight());

        if (adapter != null) {
            CharSequence currentText = getText();
            String longestItem = currentText.toString();
            for (int i = 0; i < adapter.getCount(); i++) {
                String itemText = adapter.getItemText(i);
                if (itemText.length() > longestItem.length()) {
                    longestItem = itemText;
                }
            }
            setText(longestItem);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setText(currentText);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 处理分发，该处处理简单，只对ACTION_UP做简单处理
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isEnabled() && isClickable()) {
                if (!popupWindow.isShowing()) {
                    expand();
                } else {
                    collapse();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 重写
     *
     * @param color
     */
    @Override
    public void setBackgroundColor(int color) {
        backgroundColor = color;
        Drawable background = getBackground();
        if (background instanceof StateListDrawable) {
            try {
                Method getStateDrawable = StateListDrawable.class.getDeclaredMethod("getStateDrawable", int.class);
                if (!getStateDrawable.isAccessible())
                    getStateDrawable.setAccessible(true);
                int[] colors = {Utils.darker(color, 0.85f), color};
                for (int i = 0; i < colors.length; i++) {
                    ColorDrawable drawable = (ColorDrawable) getStateDrawable.invoke(background, i);
                    drawable.setColor(colors[i]);
                }
            } catch (Exception e) {
                Log.e("GeneralSpinner", "Error setting background color", e);
            }
        } else if (background != null) { // 21+ (RippleDrawable)
            background.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        popupWindow.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    /**
     * 重写
     *
     * @param color
     */
    @Override
    public void setTextColor(int color) {
        textColor = color;
        super.setTextColor(color);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (arrowDrawable != null) {
            arrowDrawable.setColorFilter(enabled ? arrowColor : arrowColorDisabled, PorterDuff.Mode.SRC_IN);
        }
    }

    /**
     * @param position
     */
    public void setSelectedIndex(int position) {
        if (adapter != null) {
            if (position >= 0 && position < adapter.getCount()) {
                setText(adapter.get(position).toString());
            } else {
                throw new IllegalArgumentException("Position must be lower than adapter count!");
            }
        }
    }

    /**
     * @param items
     * @param <T>
     */
    public <T> void setItems(@NonNull T... items) {
        setItems(Arrays.asList(items));
    }

    /**
     * @param items
     * @param <T>
     */
    public <T> void setItems(@NonNull List<T> items) {
        adapter = new GeneralSpinnerAdapter<>(getContext(), items).setBackgroundSelector(backgroundSelector).setTextColor(textColor);
        setAdapterInternal(adapter);
    }

    /**
     * @param <T>
     * @return
     */
    public <T> List<T> getItems() {
        if (adapter == null) {
            return null;
        }
        return adapter.getItems();
    }

    /**
     * @param adapter
     */
    public void setAdapter(@NonNull ListAdapter adapter) {
        this.adapter = new GeneralSpinnerAdapterWrapper(getContext(), adapter).setBackgroundSelector(backgroundSelector)
                .setTextColor(textColor);
        setAdapterInternal(this.adapter);
    }

    /**
     * @param adapter
     * @param <T>
     */
    public <T> void setAdapter(GeneralSpinnerAdapter<T> adapter) {
        this.adapter = adapter;
        this.adapter.setTextColor(textColor);
        this.adapter.setBackgroundSelector(backgroundSelector);
        setAdapterInternal(adapter);
    }

    /**
     * 数据绑定+显示
     *
     * @param adapter
     */
    private void setAdapterInternal(@NonNull GeneralSpinnerBaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    /**
     * 展开
     */
    public void expand() {
        if (!hideArrow) {
            animateArrow(true);
        }
        nothingSelected = true;
        popupWindow.showAsDropDown(this);
    }

    /**
     * 收起
     */
    public void collapse() {
        if (!hideArrow) {
            animateArrow(false);
        }
        popupWindow.dismiss();
    }

    /**
     * @param color
     */
    public void setArrowColor(@ColorInt int color) {
        arrowColor = color;
        arrowColorDisabled = Utils.lighter(arrowColor, 0.8f);
        if (arrowDrawable != null) {
            arrowDrawable.setColorFilter(arrowColor, PorterDuff.Mode.SRC_IN);
        }
    }

    /**
     * @param shouldRotateUp
     */
    private void animateArrow(boolean shouldRotateUp) {
        int start = shouldRotateUp ? 0 : 10000;
        int end = shouldRotateUp ? 10000 : 0;
        ObjectAnimator animator = ObjectAnimator.ofInt(arrowDrawable, "level", start, end);
        animator.start();
    }

    /**
     * 计算popupWindow控件 弹窗的高度
     *
     * @return
     */
    private int calculatePopupWindowHeight() {
        if (adapter == null) {
            return WindowManager.LayoutParams.WRAP_CONTENT;
        }
        //计算出listView的总高度
        float listViewHeight = (adapter.getCount() - 1) * getResources().getDimension(R.dimen.spinner_item_height);

        //如果xml布局中设置了最高高度，且listViewHeight高度满足，优先使用最高高度
        if (popupWindowMaxHeight > 0 && listViewHeight > popupWindowMaxHeight) {

            return popupWindowMaxHeight;

        } else if (popupWindowHeight != WindowManager.LayoutParams.MATCH_PARENT
                && popupWindowHeight != WindowManager.LayoutParams.WRAP_CONTENT
                && popupWindowHeight <= listViewHeight) {
            return popupWindowHeight;
        }
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * @return
     */
    public PopupWindow getPopupWindow() {
        return popupWindow;
    }


    /**
     * @return
     */
    public ListView getListView() {
        return listView;
    }

    /**
     * @param <T>
     */
    public interface OnItemSelectedListener<T> {

        /**
         * @param view
         * @param position
         * @param id
         * @param item
         */
        void onItemSelected(GeneralSpinner view, int position, long id, T item);
    }

    /**
     * @param onItemSelectedListener
     */
    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnNothingSelectedListener {

        /**
         * @param spinner
         */
        void onNothingSelected(GeneralSpinner spinner);
    }

    public void setOnNothingSelectedListener(@Nullable OnNothingSelectedListener onNothingSelectedListener) {
        this.onNothingSelectedListener = onNothingSelectedListener;
    }
}
