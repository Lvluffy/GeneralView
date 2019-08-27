package com.luffy.generalview.ui.empty;

import android.view.View;

import com.google.gson.Gson;
import com.luffy.generalview.R;
import com.luffy.generalview.manager.TestDataManager;
import com.luffy.generalview.model.GeneralEmptyBean;
import com.luffy.generalviewlib.empty.GeneralEmpty;
import com.luffy.uilib.android.list.loader.BaseLayerLoadMoreView;
import com.luffy.uilib.template.listView.general.BaseLayerListActivity;
import com.luffy.utilslib.utils.DensityUtils;
import com.luffy.utilslib.utils.RxTimerUtils;

import java.util.concurrent.TimeUnit;

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
                    View emptyView = new GeneralEmpty(mContext).setEmptyLayoutPadding(DensityUtils.getInstance().dp2px(mContext, 50))
                            .setEmptyImg(R.mipmap.ic_launcher)
                            .setEmptyTxt("暂无数据")
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
