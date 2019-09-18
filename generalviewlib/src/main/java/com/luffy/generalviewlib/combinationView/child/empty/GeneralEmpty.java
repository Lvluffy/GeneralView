package com.luffy.generalviewlib.combinationView.child.empty;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luffy.generalviewlib.R;
import com.luffy.generalviewlib.combinationView.base.BaseCombinationView;

/**
 * Created by lvlufei on 2019/8/27
 *
 * @name 通用空布局
 * @desc
 */
public class GeneralEmpty extends BaseCombinationView {

    LinearLayout emptyLayout;
    ImageView emptyImg;
    TextView emptyTxt;
    TextView emptyBtn;

    public GeneralEmpty(Context context) {
        super(context);
    }

    public GeneralEmpty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setLayoutView() {
        return R.layout.general_empty_layout;
    }

    @Override
    public void initView() {
        emptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
        emptyImg = (ImageView) findViewById(R.id.empty_img);
        emptyTxt = (TextView) findViewById(R.id.empty_txt);
        emptyBtn = (TextView) findViewById(R.id.empty_btn);
    }

    @Override
    public void initAttrs(AttributeSet attrs) {

    }

    @Override
    public void bindAttrs() {

    }

    public GeneralEmpty setEmptyLayoutGravity(int gravity) {
        emptyLayout.setVisibility(VISIBLE);
        emptyLayout.setGravity(gravity);
        return this;
    }

    public GeneralEmpty setEmptyLayoutPadding(int top) {
        emptyLayout.setVisibility(VISIBLE);
        emptyLayout.setPadding(0, top, 0, 0);
        return this;
    }

    public GeneralEmpty setEmptyImg(int imgId) {
        emptyImg.setVisibility(VISIBLE);
        emptyImg.setImageResource(imgId);
        return this;
    }

    public GeneralEmpty setEmptyTxt(int txtId) {
        emptyTxt.setVisibility(VISIBLE);
        emptyTxt.setText(txtId);
        return this;
    }

    public GeneralEmpty setEmptyTxt(String txtStr) {
        emptyTxt.setVisibility(VISIBLE);
        emptyTxt.setText(txtStr);
        return this;
    }

    public GeneralEmpty setEmptyTxtSize(float size) {
        emptyTxt.setVisibility(VISIBLE);
        emptyTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    public GeneralEmpty setEmptyTxtColor(int color) {
        emptyTxt.setVisibility(VISIBLE);
        emptyTxt.setTextColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public GeneralEmpty setEmptyTxtStyle(int style) {
        emptyTxt.setVisibility(VISIBLE);
        emptyTxt.setTypeface(Typeface.defaultFromStyle(style));
        return this;
    }

    public GeneralEmpty setEmptyTxtGravity(int gravity) {
        emptyTxt.setVisibility(VISIBLE);
        emptyTxt.setGravity(gravity);
        return this;
    }

    public GeneralEmpty setEmptyBtnSize(float size) {
        emptyBtn.setVisibility(VISIBLE);
        emptyBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    public GeneralEmpty setEmptyBtnColor(int color) {
        emptyBtn.setVisibility(VISIBLE);
        emptyBtn.setTextColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public GeneralEmpty setEmptyBtnStyle(int style) {
        emptyBtn.setVisibility(VISIBLE);
        emptyBtn.setTypeface(Typeface.defaultFromStyle(style));
        return this;
    }

    public GeneralEmpty setEmptyBtnBackground(int resId) {
        emptyBtn.setVisibility(VISIBLE);
        emptyBtn.setBackgroundResource(resId);
        return this;
    }

    public GeneralEmpty setEmptyBtnWidthHeight(int width, int height) {
        emptyBtn.setVisibility(VISIBLE);
        emptyBtn.setLayoutParams(new LayoutParams(width, height));
        return this;
    }

    public GeneralEmpty setEmptyBtn(int resId, final OnClickListener mOnClickListener) {
        emptyBtn.setVisibility(VISIBLE);
        emptyBtn.setText(resId);
        emptyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(emptyBtn);
                }
            }
        });
        return this;
    }

    public GeneralEmpty setEmptyBtn(String resStr, final OnClickListener mOnClickListener) {
        emptyBtn.setVisibility(VISIBLE);
        emptyBtn.setText(resStr);
        emptyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(emptyBtn);
                }
            }
        });
        return this;
    }

    public View show() {
        return mView;
    }

    public interface OnClickListener {
        void onClick(View v);
    }
}
