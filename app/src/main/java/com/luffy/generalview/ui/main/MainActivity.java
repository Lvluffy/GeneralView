package com.luffy.generalview.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luffy.generalview.R;
import com.luffy.generalview.ui.empty.GeneralEmptyActivity;
import com.luffy.generalview.ui.flipper.GeneralFlipperActivity;
import com.luffy.generalview.ui.ratingBar.GeneralRatingBarActivity;
import com.luffy.generalview.ui.tag.GeneralTagActivity;
import com.luffy.generalview.ui.wheel.WheelViewActivity;
import com.luffy.generalviewlib.combinationView.child.button.GeneralButton;
import com.luffy.generalviewlib.combinationView.child.button.IGeneralButton;
import com.luffy.utilslib.utils.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 主界面
 * @desc
 */
public class MainActivity extends AppCompatActivity implements IGeneralButton {

    @BindView(R.id.btn_wheelView)
    GeneralButton btnConfirm;
    @BindView(R.id.btn_flipper)
    GeneralButton btnFlipper;
    @BindView(R.id.btn_empty)
    GeneralButton btnEmpty;
    @BindView(R.id.btn_tag)
    GeneralButton btnTag;
    @BindView(R.id.btn_ratingBar)
    GeneralButton btnRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initGeneralButton();
    }

    @Override
    public void initGeneralButton() {
        btnConfirm.setLayoutHeight(DensityUtils.getInstance().dp2px(this, 45));
        btnConfirm.setText("转动控件");
        btnConfirm.setTextSize(14);
        btnConfirm.setTextColor(R.color.base_btn_confirm_textcolor);
        btnConfirm.setEnable(true);

        btnFlipper.setLayoutHeight(DensityUtils.getInstance().dp2px(this, 45));
        btnFlipper.setText("消息轮动");
        btnFlipper.setTextSize(14);
        btnFlipper.setTextColor(R.color.base_btn_confirm_textcolor);
        btnFlipper.setEnable(true);

        btnEmpty.setLayoutHeight(DensityUtils.getInstance().dp2px(this, 45));
        btnEmpty.setText("空布局");
        btnEmpty.setTextSize(14);
        btnEmpty.setTextColor(R.color.base_btn_confirm_textcolor);
        btnEmpty.setEnable(true);

        btnTag.setLayoutHeight(DensityUtils.getInstance().dp2px(this, 45));
        btnTag.setText("标签");
        btnTag.setTextSize(14);
        btnTag.setTextColor(R.color.base_btn_confirm_textcolor);
        btnTag.setEnable(true);

        btnRatingBar.setLayoutHeight(DensityUtils.getInstance().dp2px(this, 45));
        btnRatingBar.setText("星级评分");
        btnRatingBar.setTextSize(14);
        btnRatingBar.setTextColor(R.color.base_btn_confirm_textcolor);
        btnRatingBar.setEnable(true);
    }

    @OnClick({R.id.btn_wheelView,
            R.id.btn_flipper,
            R.id.btn_empty,
            R.id.btn_tag,
            R.id.btn_ratingBar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_wheelView:
                /*转动控件*/
                btnConfirm.setLoading(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnConfirm.setLoading(false);
                        startActivity(new Intent(MainActivity.this, WheelViewActivity.class));
                    }
                }, 3000);
                break;
            case R.id.btn_flipper:
                /*消息轮动*/
                startActivity(new Intent(MainActivity.this, GeneralFlipperActivity.class));
                break;
            case R.id.btn_empty:
                /*空布局*/
                startActivity(new Intent(MainActivity.this, GeneralEmptyActivity.class));
                break;
            case R.id.btn_tag:
                /*标签*/
                startActivity(new Intent(MainActivity.this, GeneralTagActivity.class));
                break;
            case R.id.btn_ratingBar:
                /*星级评分*/
                startActivity(new Intent(MainActivity.this, GeneralRatingBarActivity.class));
                break;
        }
    }
}
