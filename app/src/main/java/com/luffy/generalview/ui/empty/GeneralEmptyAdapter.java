package com.luffy.generalview.ui.empty;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luffy.generalandroidlib.android.list.adapter.BaseLayerAdapter;
import com.luffy.generalview.R;
import com.luffy.generalview.base.BaseViewHolder;
import com.luffy.generalview.model.GeneralEmptyBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lvlufei on 2019/8/6
 *
 * @name
 * @desc
 */
public class GeneralEmptyAdapter extends BaseLayerAdapter<GeneralEmptyBean.BodyBean, GeneralEmptyAdapter.ListViewHolder> {
    public GeneralEmptyAdapter(@Nullable List<GeneralEmptyBean.BodyBean> data) {
        super(data);
    }

    @Override
    protected ListViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        return super.createBaseViewHolder(parent, R.layout.item_general_empty);
    }

    @Override
    protected void convert(ListViewHolder helper, GeneralEmptyBean.BodyBean item) {
        /*顾问名称*/
        helper.txtCounselorName.setText(item.getName());
        /*顾问类别*/
        helper.txtCounselorType.setText(item.getType());
        /*点赞数量*/
        helper.txtPraise.setText(item.getAllProfessionalLevel());
        /*评价数量*/
        helper.txtEvaluate.setText(item.getTotalCount());
    }

    public static class ListViewHolder extends BaseViewHolder {

        @BindView(R.id.txt_counselor_name)
        public TextView txtCounselorName;
        @BindView(R.id.txt_counselor_type)
        public TextView txtCounselorType;
        @BindView(R.id.txt_praise)
        public TextView txtPraise;
        @BindView(R.id.txt_evaluate)
        public TextView txtEvaluate;

        public ListViewHolder(View itemView) {
            super(itemView);
        }
    }

}
