package com.luffy.generalview.ui.wheel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luffy.generalview.R;
import com.luffy.view.generalwheellib.WheelBean;
import com.luffy.view.generalwheellib.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 转动控件
 * @desc
 */
public class WheelViewActivity extends AppCompatActivity {

    @BindView(R.id.wheelView)
    WheelView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        ButterKnife.bind(this);
        initWheelView();
    }

    private void initWheelView() {
        List<WheelBean> wheelBeanList = new ArrayList<>();
        /*Top5*/
        WheelBean wheelBean = new WheelBean();
        wheelBean.setName("Top5");
        wheelBeanList.add(wheelBean);
        /*Top30*/
        WheelBean wheelBean1 = new WheelBean();
        wheelBean1.setName("Top30");
        wheelBeanList.add(wheelBean1);
        /*Top50*/
        WheelBean wheelBea2 = new WheelBean();
        wheelBea2.setName("Top50");
        wheelBeanList.add(wheelBea2);
        /*其他*/
        WheelBean wheelBean3 = new WheelBean();
        wheelBean3.setName("其他");
        wheelBeanList.add(wheelBean3);
        wheelView.setOffset(2);
        wheelView.setSeletion(0);
        wheelView.setItems(wheelBeanList);
    }
}
