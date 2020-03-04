package com.luffy.generalview.ui.radarScanRandomText;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.luffy.generalview.R;
import com.luffy.radarscanlib.RadarScanView;
import com.luffy.randomtextlib.RandomTextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2020-03-02
 *
 * @name 雷达扫描控件
 * @desc
 */
public class RadarScanViewRandomTextActivity extends AppCompatActivity {

    private static final String[] datas = new String[]{"墨尔本大学", "澳大利亚国立大学", "新南威尔士大学（悉尼新南威尔士大学）", "莫道克大学", "塔斯马尼亚大学"};

    @BindView(R.id.radarView)
    RadarScanView radarView;
    @BindView(R.id.random_textview)
    RandomTextView randomTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_scan_view_random_text);
        ButterKnife.bind(this);
        initRandomText();
        initRadarScan();
    }

    private void initRandomText() {
        /*文本内容*/
        randomTextview.setViewHeight(dp2px(this, 300));
        randomTextview.setOnRippleViewClickListener(new RandomTextView.OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int position) {
                Toast.makeText(RadarScanViewRandomTextActivity.this, Arrays.asList(datas).get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRadarScan() {
        radarView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (String data : datas) {
                    randomTextview.addKeyWord(data);
                }
                randomTextview.show();
                radarView.stop();
            }
        }, 2 * 1000);
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
