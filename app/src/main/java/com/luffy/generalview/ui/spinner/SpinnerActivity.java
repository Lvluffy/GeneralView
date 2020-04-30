package com.luffy.generalview.ui.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luffy.generalview.R;
import com.luffy.view.generalspinnerlib.GeneralSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvlufei on 2019/12/2
 *
 * @name Spinner
 * @desc
 */
public class SpinnerActivity extends AppCompatActivity {

    @BindView(R.id.spinner)
    GeneralSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.bind(this);

        final String[] typeArrays = getResources().getStringArray(R.array.identify_types);
        spinner.setItems(typeArrays);
        spinner.setSelectedIndex(0);
        spinner.setOnItemSelectedListener(new GeneralSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(GeneralSpinner view, int position, long id, String item) {
                view.setText(typeArrays[position]);
            }
        });
    }
}
