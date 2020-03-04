package com.luffy.generalview.ui.playingIcon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luffy.generalview.R;
import com.luffy.playingiconlib.PlayingIcon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 播放中控件
 * @desc
 */
public class PlayingIconActivity extends AppCompatActivity {

    @BindView(R.id.playing_icon)
    PlayingIcon playingIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_icon);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playingIcon.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playingIcon.stop();
    }
}
