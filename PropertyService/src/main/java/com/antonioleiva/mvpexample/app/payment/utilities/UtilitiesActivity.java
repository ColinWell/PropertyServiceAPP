package com.antonioleiva.mvpexample.app.payment.utilities;

import android.os.Bundle;
import android.app.Activity;

import com.antonioleiva.androidmvp.R;

public class UtilitiesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
