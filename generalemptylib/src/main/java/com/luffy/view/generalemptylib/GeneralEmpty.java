package com.luffy.view.generalemptylib;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luffy.generalemptylib.R;

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

    public GeneralEmpty(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setLayoutView() {
        return R.layout.general_empty_layout;
    }

    @Override
    public void initView() {
        emptyLayout = findViewById(R.id.empty_layout);
        emptyImg = findViewById(R.id.empty_img);
        emptyTxt = findViewById(R.id.empty_txt);
        emptyBtn = findViewById(R.id.empty_btn);
    }

    @Override
    public void initAttrs(AttributeSet attrs) {

    }

    @Override
    public void bindAttrs() {

    }

    public GeneralEmpty setEmptyLayoutBackground(int resId) {
        emptyLayout.setVisibility(View.VISIBLE);
        emptyLayout.setBackgroundResource(resId);
        return this;
    }

    public GeneralEmpty setEmptyLayoutGravity(int gravity) {
        emptyLayout.setVisibility(View.VISIBLE);
        emptyLayout.setGravity(gravity);
        return this;
    }

    public GeneralEmpty setEmptyLayoutPadding(int top) {
        emptyLayout.setVisibility(View.VISIBLE);
        emptyLayout.setPadding(0, top, 0, 0);
        return this;
    }

    public GeneralEmpty setEmptyImg(int imgId) {
        emptyImg.setVisibility(View.VISIBLE);
        emptyImg.setImageResource(imgId);
        return this;
    }

    public GeneralEmpty setEmptyImgMargin(int left, int top, int right, int bottom) {
        emptyImg.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams mLayoutParams = (LinearLayout.LayoutParams) emptyImg.getLayoutParams();
        mLayoutParams.setMargins(left, top, right, bottom);
        return this;
    }

    public GeneralEmpty setEmptyTxt(int txtId) {
        emptyTxt.setVisibility(View.VISIBLE);
        emptyTxt.setText(txtId);
        return this;
    }

    public GeneralEmpty setEmptyTxt(String txtStr) {
        emptyTxt.setVisibility(View.VISIBLE);
        emptyTxt.setText(txtStr);
        return this;
    }

    public GeneralEmpty setEmptyTxtSize(float size) {
        emptyTxt.setVisibility(View.VISIBLE);
        emptyTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    public GeneralEmpty setEmptyTxtColor(int color) {
        emptyTxt.setVisibility(View.VISIBLE);
        emptyTxt.setTextColor(Build.VERSION.SDK_INT >= 23 ? mContext.getColor(color) : mContext.getResources().getColor(color));
        return this;
    }

    public GeneralEmpty setEmptyTxtStyle(int style) {
        emptyTxt.setVisibility(View.VISIBLE);
        emptyTxt.setTypeface(Typeface.defaultFromStyle(style));
        return this;
    }

    public GeneralEmpty setEmptyTxtGravity(int gravity) {
        emptyTxt.setVisibility(View.VISIBLE);
        emptyTxt.setGravity(gravity);
        return this;
    }

    public GeneralEmpty setEmptyTxtMargin(int left, int top, int right, int bottom) {
        emptyTxt.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams mLayoutParams = (LinearLayout.LayoutParams) emptyTxt.getLayoutParams();
        mLayoutParams.setMargins(left, top, right, bottom);
        return this;
    }

    public GeneralEmpty setEmptyBtnSize(float size) {
        emptyBtn.setVisibility(View.VISIBLE);
        emptyBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    public GeneralEmpty setEmptyBtnColor(int color) {
        emptyBtn.setVisibility(View.VISIBLE);
        emptyBtn.setTextColor(Build.VERSION.SDK_INT >= 23 ? mContext.getColor(color) : mContext.getResources().getColor(color));
        return this;
    }

    public GeneralEmpty setEmptyBtnStyle(int style) {
        emptyBtn.setVisibility(View.VISIBLE);
        emptyBtn.setTypeface(Typeface.defaultFromStyle(style));
        return this;
    }

    public GeneralEmpty setEmptyBtnBackground(int resId) {
        emptyBtn.setVisibility(View.VISIBLE);
        emptyBtn.setBackgroundResource(resId);
        return this;
    }

    public GeneralEmpty setEmptyBtnWidthHeight(int width, int height) {
        emptyBtn.setVisibility(View.VISIBLE);
        if (width == 0 && height == 0) {
            emptyBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        } else {
            emptyBtn.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        }
        return this;
    }

    public GeneralEmpty setEmptyBtnMargin(int left, int top, int right, int bottom) {
        emptyBtn.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams mLayoutParams = (LinearLayout.LayoutParams) emptyBtn.getLayoutParams();
        mLayoutParams.setMargins(left, top, right, bottom);
        return this;
    }

    public GeneralEmpty setEmptyBtnPadding(int left, int top, int right, int bottom) {
        emptyBtn.setVisibility(View.VISIBLE);
        emptyBtn.setPadding(left, top, right, bottom);
        return this;
    }

    public GeneralEmpty setEmptyBtnPadding(int padding) {
        emptyBtn.setVisibility(View.VISIBLE);
        emptyBtn.setPadding(padding, padding, padding, padding);
        return this;
    }

    public GeneralEmpty setEmptyBtn(int resId, final OnClickListener mOnClickListener) {
        emptyBtn.setVisibility(View.VISIBLE);
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
        emptyBtn.setVisibility(View.VISIBLE);
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
