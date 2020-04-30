package com.luffy.generalview.ui.flipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.luffy.generalview.R;
import com.luffy.view.generalflipper.GeneralFlipper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 消息轮动
 * @desc
 */
public class GeneralFlipperActivity extends AppCompatActivity {

    @BindView(R.id.general_flipper)
    GeneralFlipper generalFlipper;

    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_flipper);
        ButterKnife.bind(this);

        stringList.add("中国");
        stringList.add("美国");
        stringList.add("英国");
        generalFlipper.bindData(stringList);
    }

    @OnClick(R.id.general_flipper)
    public void onViewClicked() {
        Toast.makeText(this, stringList.get(generalFlipper.getViewFlipper().getDisplayedChild()), Toast.LENGTH_SHORT).show();
    }
}
