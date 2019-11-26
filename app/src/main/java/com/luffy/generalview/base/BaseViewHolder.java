package com.luffy.generalview.base;

import android.view.View;

import com.luffy.generalandroidlib.android.list.viewHolder.BaseLayerViewHolder;

import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/8/6
 *
 * @name 公共-ViewHolder
 * @desc
 */
public class BaseViewHolder extends BaseLayerViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindButterKnife(View itemView) {
        ButterKnife.bind(this, itemView);
    }
}
