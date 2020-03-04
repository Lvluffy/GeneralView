package com.luffy.generalspinnerlib;

import android.content.Context;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2019/12/2
 *
 * @name
 * @desc
 */
final class GeneralSpinnerAdapterWrapper extends GeneralSpinnerBaseAdapter {

    private final ListAdapter listAdapter;

    public GeneralSpinnerAdapterWrapper(Context context, ListAdapter toWrap) {
        super(context);
        listAdapter = toWrap;
    }

    @Override
    public int getCount() {
        return listAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return listAdapter.getItem(position);
    }

    @Override
    public Object get(int position) {
        return listAdapter.getItem(position);
    }

    @Override
    public List<Object> getItems() {
        List<Object> items = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            items.add(getItem(i));
        }
        return items;
    }

}