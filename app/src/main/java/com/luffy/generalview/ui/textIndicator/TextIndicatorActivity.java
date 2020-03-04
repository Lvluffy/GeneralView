package com.luffy.generalview.ui.textIndicator;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.luffy.generaltextindicatorlib.TextIndicatorView;
import com.luffy.generalview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvlufei on 2019/10/10
 *
 * @name 文本指示器
 * @desc
 */
public class TextIndicatorActivity extends AppCompatActivity {

    @BindView(R.id.textIndicatorView)
    TextIndicatorView textIndicatorView;
    @BindView(R.id.root)
    LinearLayout root;

    boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_indicator);
        ButterKnife.bind(this);

        final String str = "1月8日，中共中央总书记、国家主席习近平同当日抵京对中国进行访问的朝鲜劳动党委员长、国务委员会委员长金正恩举行会谈。两国领导人在亲切友好的气氛中，就中朝关系和共同关心的问题深入交换意见，达成重要共识。双方一致表示，愿共同努力推动中朝关系在新的时期不断取得新的发展，持续推进半岛问题政治解决进程，更好造福两国人民，为地区和世界和平稳定与繁荣发展作出积极贡献。";
        textIndicatorView.setContent(str);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textIndicatorView.setContent(str);
                root.setVisibility(View.VISIBLE);
            }
        }, 3 * 1000);
    }


    @OnClick({R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (isShow) {
                    root.setVisibility(View.VISIBLE);
                } else {
                    root.setVisibility(View.GONE);
                }
                isShow = !isShow;
                break;
        }
    }
}
