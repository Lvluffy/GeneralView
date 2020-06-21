package com.luffy.generalview.ui.empty;

import android.view.View;

import com.google.gson.Gson;
import com.luffy.generalandroidlib.android.list.loader.BaseLayerLoadMoreView;
import com.luffy.generalandroidlib.template.listView.general.BaseLayerListActivity;
import com.luffy.generalutilslib.utils.DensityUtils;
import com.luffy.generalutilslib.utils.RxTimerUtils;
import com.luffy.generalview.R;
import com.luffy.generalview.manager.TestDataManager;
import com.luffy.generalview.model.GeneralEmptyBean;
import com.luffy.view.generalemptylib.GeneralEmpty;

import java.util.concurrent.TimeUnit;

/**
 * Created by lvlufei on 2019/9/18
 *
 * @name 空布局
 * @desc
 */
public class GeneralEmptyActivity extends BaseLayerListActivity {

    /*适配器*/
    GeneralEmptyAdapter adapter;

    /*数据*/
    GeneralEmptyBean mGeneralEmptyBean;

    @Override
    public int setLayoutView() {
        return R.layout.activity_general_empty;
    }

    @Override
    public void initPresenter() {
        onRefresh();
    }

    @Override
    public void initReceiveData() {

    }

    @Override
    public void initAdapter() {
        if (adapter == null) {
            adapter = new GeneralEmptyAdapter(null);
            adapter.setOnLoadMoreListener(this, recyclerView);
            adapter.setLoadMoreView(new BaseLayerLoadMoreView());
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        requestData();
    }

    @Override
    public void onLoadMoreRequested() {
        requestData();
    }

    private void requestData() {
        mGeneralEmptyBean = new Gson().fromJson(TestDataManager.message, GeneralEmptyBean.class);
        /*延时操作*/
        RxTimerUtils.getInstance().interval(2, TimeUnit.SECONDS, new RxTimerUtils.IRxNext() {
            @Override
            public void doNext(long l) {
                swipeRefreshLayout.setRefreshing(false);
                if (mGeneralEmptyBean.getBody() != null && mGeneralEmptyBean.getBody().size() == 0) {
                    adapter.setNewData(mGeneralEmptyBean.getBody());
                    adapter.loadMoreEnd();
                    recyclerView.scrollToPosition(0);
                } else {
                    View emptyView = new GeneralEmpty(mContext)
                            .setEmptyImg(R.mipmap.ic_launcher)
                            .setEmptyImgMargin(0, DensityUtils.getInstance().dp2px(mContext, 50), 0, 0)
                            .setEmptyTxt("暂无数据")
                            .setEmptyTxtMargin(0, DensityUtils.getInstance().dp2px(mContext, 10), 0, 0)
                            .setEmptyBtnWidthHeight(0, 0)
                            .setEmptyBtnMargin(0, DensityUtils.getInstance().dp2px(mContext, 10), 0, 0)
                            .setEmptyBtnPadding(DensityUtils.getInstance().dp2px(mContext, 30), DensityUtils.getInstance().dp2px(mContext, 10), DensityUtils.getInstance().dp2px(mContext, 30), DensityUtils.getInstance().dp2px(mContext, 10))
                            .setEmptyBtnBackground(R.drawable.bg_radius_ture_stroke_false_fff5e7_selector)
                            .setEmptyBtn("提交", new GeneralEmpty.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .show();
                    adapter.setEmptyView(emptyView);
                }
            }
        });
    }
}
