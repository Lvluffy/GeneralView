package com.luffy.generalview.ui.button;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luffy.generalview.R;
import com.luffy.generalview.ui.flipper.GeneralFlipperActivity;
import com.luffy.generalview.ui.wheel.WheelViewActivity;
import com.luffy.generalview.utils.DensityUtils;
import com.luffy.generalviewlib.custom.button.GeneralButton;
import com.luffy.generalviewlib.custom.button.IGeneralButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GeneralButtonActivity extends AppCompatActivity implements IGeneralButton {

    @BindView(R.id.btn_confirm)
    GeneralButton btnConfirm;
    @BindView(R.id.btn_flipper)
    GeneralButton btnFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_button);
        ButterKnife.bind(this);
        initGeneralButton();
    }

    @Override
    public void initGeneralButton() {
        btnConfirm.setLayoutHeight(DensityUtils.getInstance().dp2px(this, 45));
        btnConfirm.setText("退出");
        btnConfirm.setTextSize(14);
        btnConfirm.setTextColor(R.color.base_btn_confirm_textcolor);
        btnConfirm.setEnable(true);

        btnFlipper.setLayoutHeight(DensityUtils.getInstance().dp2px(this, 45));
        btnFlipper.setText("去滚动界面");
        btnFlipper.setTextSize(14);
        btnFlipper.setTextColor(R.color.base_btn_confirm_textcolor);
        btnFlipper.setEnable(true);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {

    }

    @OnClick({R.id.btn_confirm, R.id.btn_flipper})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                btnConfirm.setLoading(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnConfirm.setLoading(false);
                        startActivity(new Intent(GeneralButtonActivity.this, WheelViewActivity.class));
                    }
                }, 3000);
                break;
            case R.id.btn_flipper:
                startActivity(new Intent(GeneralButtonActivity.this, GeneralFlipperActivity.class));
                break;
        }
    }
}
