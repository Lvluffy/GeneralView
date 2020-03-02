package com.luffy.generalview.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luffy.generalview.R;
import com.luffy.generalview.ui.bottombar.BottomBarActivity;
import com.luffy.generalview.ui.empty.GeneralEmptyActivity;
import com.luffy.generalview.ui.flipper.GeneralFlipperActivity;
import com.luffy.generalview.ui.playingIcon.PlayingIconActivity;
import com.luffy.generalview.ui.radarScanRandomText.RadarScanViewRandomTextActivity;
import com.luffy.generalview.ui.ratingBar.GeneralRatingBarActivity;
import com.luffy.generalview.ui.spinner.SpinnerActivity;
import com.luffy.generalview.ui.tag.GeneralTagActivity;
import com.luffy.generalview.ui.textIndicator.TextIndicatorActivity;
import com.luffy.generalview.ui.wheel.WheelViewActivity;
import com.luffy.generalviewlib.combinationView.child.button.GeneralButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 主界面
 * @desc
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_wheelView)
    GeneralButton btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_wheelView,
            R.id.btn_flipper,
            R.id.btn_empty,
            R.id.btn_tag,
            R.id.btn_ratingBar,
            R.id.btn_playing_icon,
            R.id.btn_bottom_bar,
            R.id.btn_text_indicator,
            R.id.btn_spinner,
            R.id.btn_radar_scan
    })
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
            case R.id.btn_playing_icon:
                /*播放中控件*/
                startActivity(new Intent(MainActivity.this, PlayingIconActivity.class));
                break;
            case R.id.btn_bottom_bar:
                /*底部栏*/
                startActivity(new Intent(MainActivity.this, BottomBarActivity.class));
                break;
            case R.id.btn_text_indicator:
                /*文本指示器*/
                startActivity(new Intent(MainActivity.this, TextIndicatorActivity.class));
                break;
            case R.id.btn_spinner:
                /*spinner*/
                startActivity(new Intent(MainActivity.this, SpinnerActivity.class));
                break;
            case R.id.btn_radar_scan:
                /*RadarScan*/
                startActivity(new Intent(MainActivity.this, RadarScanViewRandomTextActivity.class));
                break;
        }
    }
}
