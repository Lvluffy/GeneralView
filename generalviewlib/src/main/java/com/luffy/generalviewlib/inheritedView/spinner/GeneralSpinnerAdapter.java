package com.luffy.generalviewlib.inheritedView.spinner;

import android.content.Context;

import java.util.List;

/**
 * Created by lvlufei on 2019/12/2
 *
 * @name
 * @desc
 */
public class GeneralSpinnerAdapter<T> extends GeneralSpinnerBaseAdapter {

    private final List<T> items;

    public GeneralSpinnerAdapter(Context context, List<T> items) {
        super(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public T get(int position) {
        return items.get(position);
    }

    @Override
    public List<T> getItems() {
        return items;
    }

}