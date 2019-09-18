package com.luffy.generalview.ui.ratingBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luffy.generalview.R;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 星级评分
 * @desc
 */
public class GeneralRatingBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_rating_bar);
        ButterKnife.bind(this);
    }
}
