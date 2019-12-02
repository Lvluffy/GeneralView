package com.luffy.generalviewlib.inheritedView.spinner;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luffy.generalviewlib.R;

import java.util.List;

/**
 * Created by lvlufei on 2019/12/2
 *
 * @name list适配
 * @desc
 */
public abstract class GeneralSpinnerBaseAdapter<T> extends BaseAdapter {

    private final Context context;
    private int selectedIndex;
    private int textColor;
    private int backgroundSelector;

    public GeneralSpinnerBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView textView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_general_spinner, parent, false);
            textView = (TextView) convertView.findViewById(R.id.tv_tinted_spinner);
            textView.setTextColor(textColor);
            if (backgroundSelector != 0) {
                textView.setBackgroundResource(backgroundSelector);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Configuration config = context.getResources().getConfiguration();
                if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    textView.setTextDirection(View.TEXT_DIRECTION_RTL);
                }
            }
            convertView.setTag(new ViewHolder(textView));
        } else {
            textView = ((ViewHolder) convertView.getTag()).textView;
        }
        textView.setText(getItemText(position));
        return convertView;
    }

    public String getItemText(int position) {
        return getItem(position).toString();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract T getItem(int position);

    @Override
    public abstract int getCount();

    public abstract T get(int position);

    public abstract List<T> getItems();

    public GeneralSpinnerBaseAdapter<T> setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        return this;
    }

    public GeneralSpinnerBaseAdapter<T> setBackgroundSelector(@DrawableRes int backgroundSelector) {
        this.backgroundSelector = backgroundSelector;
        return this;
    }

    private static class ViewHolder {

        private TextView textView;

        private ViewHolder(TextView textView) {
            this.textView = textView;
        }
    }
}
