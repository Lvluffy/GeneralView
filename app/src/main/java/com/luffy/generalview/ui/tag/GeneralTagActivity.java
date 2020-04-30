package com.luffy.generalview.ui.tag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luffy.generaldialoglib.toast.ToastBuilder;
import com.luffy.generalview.R;
import com.luffy.view.generaltaglib.TagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 标签
 * @desc
 */
public class GeneralTagActivity extends AppCompatActivity {

    @BindView(R.id.tag_view)
    TagView tagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_tag);
        ButterKnife.bind(this);
        final List<String> tagList = new ArrayList<>();
        tagList.add("好看");
        tagList.add("漂亮");
        tagList.add("美丽");
        tagView.setTags(tagList);
        tagView.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position) {
                new ToastBuilder(GeneralTagActivity.this).setTitle(tagList.get(position)).show();
            }
        });
    }
}
