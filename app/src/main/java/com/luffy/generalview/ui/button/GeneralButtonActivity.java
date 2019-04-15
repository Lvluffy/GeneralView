package com.luffy.generalview.ui.button;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.luffy.generalview.R;
import com.luffy.generalview.utils.DensityUtils;
import com.luffy.generalviewlib.custom.button.GeneralButton;
import com.luffy.generalviewlib.custom.button.IGeneralButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GeneralButtonActivity extends AppCompatActivity implements IGeneralButton {

    @BindView(R.id.btn_confirm)
    GeneralButton btnConfirm;

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
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        btnConfirm.setLoading(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnConfirm.setLoading(false);
            }
        }, 3000);
    }
}
