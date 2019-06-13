package com.luffy.generalview.ui.wheel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luffy.generalview.R;
import com.luffy.generalviewlib.custom.wheel.WheelView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WheelViewActivity extends AppCompatActivity {

    @BindView(R.id.wheelView)
    WheelView wheelView;

    private static final String[] school = new String[]{"Top5", "Top30", "Top50", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        ButterKnife.bind(this);
        initWheelView();
    }

    private void initWheelView() {
        wheelView.setOffset(2);
        wheelView.setSeletion(0);
        wheelView.setItems(Arrays.asList(school));
    }
}
