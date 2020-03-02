package com.luffy.generalviewlib.customView.randomText;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luffy.generalviewlib.R;

import java.util.Random;
import java.util.Vector;

/**
 * Created by lvlufei on 2019/5/17
 *
 * @name 随机位置的文本框
 * @desc
 */
public class RandomTextView extends LinearLayout {

    private static final int MAX = 9;
    private static final int TEXT_SIZE = 13;
    private static final int TEXT_COLOR = Color.parseColor("#FFFFFF");

    Context mContext;

    int viewHeight;//父布局的高度
    int itemViewMaxHeightOffset;//子控件的最大高度偏移量

    private Random random;
    private Vector<String> vecKeywords;

    public interface OnItemViewClickListener {
        void onItemViewClick(View view, int position);
    }

    private OnItemViewClickListener onRippleOutViewClickListener;

    public RandomTextView(Context context) {
        super(context);
        init(context);
    }

    public RandomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RandomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(VERTICAL);
        random = new Random();
        vecKeywords = new Vector<>(MAX);
    }

    /**
     * 添加RippleOutView的内容
     *
     * @param keyword
     */
    public void addKeyWord(String keyword) {
        if (vecKeywords.size() < MAX) {
            vecKeywords.add(keyword);
        }
    }

    public void clearKeyWord() {
        if (vecKeywords.size() > 0) {
            vecKeywords.clear();
        }
    }

    public void show() {
        this.removeAllViews();
        if (vecKeywords != null && vecKeywords.size() > 0) {
            int itemViewMaxitemViewMaxHeight = getViewHeight() / vecKeywords.size();
            itemViewMaxHeightOffset = (itemViewMaxitemViewMaxHeight - dp2px(mContext, 24)) / 2;
            //关键字的个数。
            int size = vecKeywords.size();
            for (int i = 0; i < size; i++) {
                String keyword = vecKeywords.get(i);
                // 实例化RippleOutView
                final TextView txt = new TextView(mContext);
                final int finalI = i;
                txt.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onRippleOutViewClickListener != null)
                            onRippleOutViewClickListener.onItemViewClick(view, finalI);
                    }
                });
                txt.setText(keyword);
                txt.setTextColor(TEXT_COLOR);
                txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
                txt.setGravity(Gravity.CENTER);
                txt.setLines(1);
                txt.setBackgroundResource(R.drawable.random_text_radius_full_stroke_false_primary_selector);
                txt.setPadding(dp2px(mContext, 10), 0, dp2px(mContext, 10), 0);
                LayoutParams layParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp2px(mContext, 24));
                layParams.setMargins(random.nextInt(200) + 50, itemViewMaxHeightOffset, 0, itemViewMaxHeightOffset);
                addView(txt, layParams);
            }
        }
    }

    public void setOnRippleViewClickListener(OnItemViewClickListener listener) {
        onRippleOutViewClickListener = listener;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpVal,
                context.getResources().getDisplayMetrics());
    }

}
